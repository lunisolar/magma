/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.operator.unary.*;
import eu.lunisolar.magma.func.operator.binary.*;
import eu.lunisolar.magma.func.predicate.*;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.*;

/**
 * Exact equivalent of input parameters used in LConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSingle<T> extends LTuple<Object> {

	int SIZE = 1;

	T value();

	default T first() {
		return value();
	}

	default Object get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
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

	default Iterator<Object> iterator() {
		return new Iterator<Object>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Object next() {
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
	 * Mutable, non-comparable tuple.
	 */
	final class MutSingle<T> extends AbstractSingle<T> {

		private T value;

		public MutSingle(T a) {
			this.value = a;
		}

		public static <T> MutSingle<T> of(T a) {
			return new MutSingle(a);
		}

		public static <T> MutSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.value());
		}

		public T value() {
			return value;
		}

		public MutSingle<T> value(T value) {
			this.value = value;
			return this;
		}

		public MutSingle<T> setValue(T value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSingle<T> setValueIfArg(T value, LPredicate<T> predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSingle<T> setValueIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.value = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSingle<T> setValueIf(LPredicate<T> predicate, T value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSingle<T> setValueIf(T value, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSingle<T> setValueIf(LBiPredicate<T, T> predicate, T value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = null;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompSingle<T extends Comparable<? super T>> extends AbstractSingle<T> implements ComparableSingle<T> {

		private T value;

		public MutCompSingle(T a) {
			this.value = a;
		}

		public static <T extends Comparable<? super T>> MutCompSingle<T> of(T a) {
			return new MutCompSingle(a);
		}

		public static <T extends Comparable<? super T>> MutCompSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.value());
		}

		public T value() {
			return value;
		}

		public MutCompSingle<T> value(T value) {
			this.value = value;
			return this;
		}

		public MutCompSingle<T> setValue(T value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSingle<T> setValueIfArg(T value, LPredicate<T> predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSingle<T> setValueIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.value = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSingle<T> setValueIf(LPredicate<T> predicate, T value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSingle<T> setValueIf(T value, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSingle<T> setValueIf(LBiPredicate<T, T> predicate, T value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = null;
		}
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

		public static <T> ImmSingle<T> of(T a) {
			return new ImmSingle(a);
		}

		public static <T> ImmSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.value());
		}

		public T value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompSingle<T extends Comparable<? super T>> extends AbstractSingle<T> implements ComparableSingle<T> {

		private final T value;

		public ImmCompSingle(T a) {
			this.value = a;
		}

		public static <T extends Comparable<? super T>> ImmCompSingle<T> of(T a) {
			return new ImmCompSingle(a);
		}

		public static <T extends Comparable<? super T>> ImmCompSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.value());
		}

		public T value() {
			return value;
		}

	}

}
