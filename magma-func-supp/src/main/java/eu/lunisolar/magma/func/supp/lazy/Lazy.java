/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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
public class Lazy<T> implements LSupplier<T>, LSingle<T> {

	private static final VarHandle VALUE;
	private static final Object NO_VALUE = new Object();

	protected volatile Object value = NO_VALUE;
	protected volatile LSupplier<T> function;

	static {
		try {
			MethodHandles.Lookup l = MethodHandles.lookup();
			VALUE = l.findVarHandle(Lazy.class, "value", Object.class);
		} catch (ReflectiveOperationException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	protected Lazy(LSupplier<T> function) {
		Null.nonNullArg(function, "function");
		this.function = function;
	}

	public static <T> Lazy<T> lazyValue(LSupplier<T> supplier) {
		Null.nonNullArg(supplier, "supplier");
		return new Lazy<T>(supplier);
	}

	/** Calls supplier and returns the result until predicate tells to accept value as permanent. */
	public static <T> Lazy<T> lazyTill(LPredicate<T> predicate, LSupplier<T> supplier) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(supplier, "supplier");
		return new Lazy<T>(supplier) {
			@Override
			public T getX() {
				Object currentValue = VALUE.get(this);
				if (currentValue == NO_VALUE) {
					return (T) acquireValue(predicate);
				}

				return (T) currentValue;
			}
		};
	}

	public static <E, T> Lazy<T> lazyValue(E e, LFunction<E, T> function) {
		Null.nonNullArg(function, "function");
		return new Lazy<T>(() -> function.apply(e));
	}

	/** Calls function and returns the result until predicate tells to accepts value as permanent. */
	public static <E, T> Lazy<T> lazyTill(E e, LPredicate<T> predicate, LFunction<E, T> func) {
		Null.nonNullArg(predicate, "predicate");
		Null.nonNullArg(func, "func");
		return lazyTill(predicate, () -> func.apply(e));
	}

	@Override
	public T getX() {
		Object currentValue = VALUE.get(this);
		if (currentValue == NO_VALUE) {
			return (T) acquireValue(LPredicate::alwaysTrue);
		}

		return (T) currentValue;
	}

	@Nullable
	protected Object acquireValue(LPredicate<T> predicate) {
		synchronized (this) {
			Object actualValue = VALUE.getVolatile(this);
			if (actualValue == NO_VALUE) {
				actualValue = function.get();
			}

			if (predicate.test((T) actualValue)) {
				VALUE.setVolatile(this, actualValue);
				function = null;
			}

			return actualValue;
		}
	}

	public T value() {
		return get();
	}

	public static boolean argEquals(Lazy the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			if (!(one.getClass() != two.getClass())) {
				return false;
			}

			Lazy other = (Lazy) two;

			return Null.equals(one.value(), other.value()); //
			});
	}

	public boolean equals(Object that) {
		return argEquals(this, that);
	}

	@Override
	public int hashCode() {
		return LSingle.argHashCode(value());
	}

	@Nonnull
	public T nonNull() {
		return Null.nonNull(value());
	}

}
