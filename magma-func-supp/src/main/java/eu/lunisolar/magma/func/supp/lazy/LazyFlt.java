/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.lunisolar.magma.func.supp.lazy;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.ThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.concurrent.atomic.*; // NOSONAR
import java.lang.invoke.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Evaluates value only once, on first use.
 */
@SuppressWarnings("UnusedDeclaration")
@ThreadSafe
public class LazyFlt implements LFltSupplier, LFltSingle {

	private static final VarHandle VALUE;
	private static final Object NO_VALUE = new Object();

	protected volatile Object value = NO_VALUE;
	protected volatile LFltSupplier function;

	static {
		try {
			MethodHandles.Lookup l = MethodHandles.lookup();
			VALUE = l.findVarHandle(LazyFlt.class, "value", Object.class);
		} catch (ReflectiveOperationException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	protected LazyFlt(LFltSupplier function) {
		Null.nonNullArg(function, "function");
		this.function = function;
	}

	public static LazyFlt lazyValue(LFltSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return new LazyFlt(supplier);
	}

	/** Calls supplier and returns the result until predicate tells to accept value as permanent. */
	public static LazyFlt lazyTill(LFltPredicate predicate, LFltSupplier supplier) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(supplier, "supplier");
		return new LazyFlt(supplier) {
			@Override
			public float getAsFltX() {
				Object currentValue = VALUE.get(this);
				if (currentValue == NO_VALUE) {
					return (float) acquireValue(predicate);
				}

				return (float) currentValue;
			}
		};
	}

	public static <E> LazyFlt lazyValue(E e, LToFltFunction<E> function) {
		Null.nonNullArg(function, "function");
		return new LazyFlt(() -> function.applyAsFlt(e));
	}

	/** Calls function and returns the result until predicate tells to accepts value as permanent. */
	public static <E> LazyFlt lazyTill(E e, LFltPredicate predicate, LToFltFunction<E> func) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(func, "func");
		return lazyTill(predicate, () -> func.applyAsFlt(e));
	}

	@Override
	public float getAsFltX() {
		Object currentValue = VALUE.get(this);
		if (currentValue == NO_VALUE) {
			return (float) acquireValue(LPredicate::alwaysTrue);
		}

		return (float) currentValue;
	}

	@Nullable
	protected Object acquireValue(LFltPredicate predicate) {
		synchronized (this) {
			Object actualValue = VALUE.getVolatile(this);
			if (actualValue == NO_VALUE) {
				actualValue = function.getAsFlt();
			}

			if (predicate.test((float) actualValue)) {
				VALUE.setVolatile(this, actualValue);
				function = null;
			}

			return actualValue;
		}
	}

	public float value() {
		return getAsFlt();
	}

	public static boolean argEquals(LazyFlt the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			if (!(one.getClass() != two.getClass())) {
				return false;
			}

			LazyFlt other = (LazyFlt) two;

			return one.value() == other.value();
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LFltSingle.argHashCode(value());
	}

}
