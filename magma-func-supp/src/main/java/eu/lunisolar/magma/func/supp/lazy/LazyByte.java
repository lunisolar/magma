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
public class LazyByte implements LByteSupplier, LByteSingle {

	private static final VarHandle VALUE;
	private static final Object NO_VALUE = new Object();

	protected volatile Object value = NO_VALUE;
	protected volatile LByteSupplier function;

	static {
		try {
			MethodHandles.Lookup l = MethodHandles.lookup();
			VALUE = l.findVarHandle(LazyByte.class, "value", Object.class);
		} catch (ReflectiveOperationException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	protected LazyByte(LByteSupplier function) {
		Null.nonNullArg(function, "function");
		this.function = function;
	}

	public static LazyByte lazyValue(LByteSupplier supplier) {
		Null.nonNullArg(supplier, "supplier");
		return new LazyByte(supplier);
	}

	/** Calls supplier and returns the result until predicate tells to accept value as permanent. */
	public static LazyByte lazyTill(LBytePredicate predicate, LByteSupplier supplier) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(supplier, "supplier");
		return new LazyByte(supplier) {
			@Override
			public byte getAsByteX() {
				Object currentValue = VALUE.get(this);
				if (currentValue == NO_VALUE) {
					return (byte) acquireValue(predicate);
				}

				return (byte) currentValue;
			}
		};
	}

	public static <E> LazyByte lazyValue(E e, LToByteFunction<E> function) {
		Null.nonNullArg(function, "function");
		return new LazyByte(() -> function.applyAsByte(e));
	}

	/** Calls function and returns the result until predicate tells to accepts value as permanent. */
	public static <E> LazyByte lazyTill(E e, LBytePredicate predicate, LToByteFunction<E> func) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(func, "func");
		return lazyTill(predicate, () -> func.applyAsByte(e));
	}

	@Override
	public byte getAsByteX() {
		Object currentValue = VALUE.get(this);
		if (currentValue == NO_VALUE) {
			return (byte) acquireValue(LPredicate::alwaysTrue);
		}

		return (byte) currentValue;
	}

	@Nullable
	protected Object acquireValue(LBytePredicate predicate) {
		synchronized (this) {
			Object actualValue = VALUE.getVolatile(this);
			if (actualValue == NO_VALUE) {
				actualValue = function.getAsByte();
			}

			if (predicate.test((byte) actualValue)) {
				VALUE.setVolatile(this, actualValue);
				function = null;
			}

			return actualValue;
		}
	}

	public byte value() {
		return getAsByte();
	}

	public static boolean argEquals(LazyByte the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			if (!(one.getClass() != two.getClass())) {
				return false;
			}

			LazyByte other = (LazyByte) two;

			return one.value() == other.value();
		});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LByteSingle.argHashCode(value());
	}

}
