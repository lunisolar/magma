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

package eu.lunisolar.magma.func.tuple;

import eu.lunisolar.magma.basics.meta.LTuple;
import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.*;
import eu.lunisolar.magma.basics.meta.functional.*;
import eu.lunisolar.magma.func.*;
import eu.lunisolar.magma.func.consumer.*;
import eu.lunisolar.magma.func.consumer.primitives.bi.*;
import eu.lunisolar.magma.func.consumer.primitives.tri.*;
import eu.lunisolar.magma.func.function.*;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.function.from.*;
import eu.lunisolar.magma.func.operator.unary.*;
import eu.lunisolar.magma.func.operator.binary.*;
import eu.lunisolar.magma.func.predicate.*;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.atomic.*;
import java.lang.invoke.*;

/**
 * Exact equivalent of input parameters used in LConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSingle<T> extends LTuple<T> {

	int SIZE = 1;

	T value();

	@Override
	default T get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	@Override
	default int tupleSize() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LSingle and calculates hash from it. */
	static <T> int argHashCode(T a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LSingle and checks if all values are equal. */
	static <T> boolean argEquals(T a, T b) {
		return Null.equals(a, b); //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LSingle interface (among others) and their LSingle values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSingle are allowed.
			if (!(two instanceof LSingle)) {
				return false;
			}

			LSingle other = (LSingle) two;

			return argEquals(one.value(), other.value());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSingle are allowed.
			if (!(two instanceof LSingle)) {
				return false;
			}

			LSingle other = (LSingle) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.value(), other.value());
		});
	}

	@Override
	default Iterator<T> iterator() {
		return new Iterator<T>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public T next() {
				index++;
				return get(index);
			}
		};
	}

	interface ComparableSingle<T extends Comparable<? super T>> extends LSingle<T>, Comparable<LSingle<T>> {
		@Override
		default int compareTo(LSingle<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.value(), two.value())) != 0 ? retval : 0; //
			});
		}

	}

	abstract class AbstractSingle<T> implements LSingle<T> {

		@Override
		public boolean equals(Object that) {
			return LSingle.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LSingle.argHashCode(value());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(value());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable tuple.
	 */
	interface Mut<T, SELF extends Mut<T, SELF>> extends LSingle<T> {

		SELF value(T value);

		/** Sets value if predicate(current) is true */
		default SELF setValueIfCurrent(T value, LPredicate<T> predicate) {//1
			if (predicate.test(this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setValueIfNew(T value, LPredicate<T> predicate) {//1
			if (predicate.test(value)) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setValueIf(T value, LBiPredicate<T, T> predicate) {//2
			if (predicate.test(this.value(), value)) {
				return this.value(value);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.value(null);
			return (SELF) this;
		}
	}

	public static <T> MutSingle<T> of() {
		return of(null);
	}

	public static <T> MutSingle<T> of(T a) {
		return new MutSingle(a);
	}

	public static <T> MutSingle<T> copyOf(LSingle<T> tuple) {
		return of(tuple.value());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutSingle<T> extends AbstractSingle<T> implements Mut<T, MutSingle<T>> {

		private T value;

		public MutSingle(T a) {
			this.value = a;
		}

		public @Override T value() {
			return value;
		}

		public @Override MutSingle<T> value(T value) {
			this.value = value;
			return this;
		}

	}

	public static <T> LSingle<T> immutableOf(T a) {
		return new ImmSingle(a);
	}

	public static <T> LSingle<T> immutableCopyOf(LSingle<T> tuple) {
		return immutableOf(tuple.value());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmSingle<T> extends AbstractSingle<T> {

		private final T value;

		public ImmSingle(T a) {
			this.value = a;
		}

		public @Override T value() {
			return value;
		}
	}

	public static <T> AtomicSingle<T> atomicOf() {
		return atomicOf(null);
	}

	public static <T> AtomicSingle<T> atomicOf(T a) {
		return new AtomicSingle(a);
	}

	public static <T> AtomicSingle<T> atomicCopyOf(LSingle<T> tuple) {
		return atomicOf(tuple.value());
	}

	public static <T> Mut<T, ?> of(boolean atomic, T a) {
		return atomic ? atomicOf(a) : of(a);
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class AtomicSingle<T> extends AtomicReference<T> implements Mut<T, AtomicSingle<T>> {

		public AtomicSingle(T a) {
			set(a);

		}

		public @Override T value() {
			return get();
		}

		public @Override AtomicSingle<T> value(T value) {
			set(value);
			return this;
		}

		@Override
		public boolean equals(Object that) {
			return LSingle.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LSingle.argHashCode(value());
		}
	}

}
