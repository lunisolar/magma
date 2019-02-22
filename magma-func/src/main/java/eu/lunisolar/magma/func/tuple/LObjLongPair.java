/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
 * Exact equivalent of input parameters used in LObjLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjLongPair<T> extends LTuple<Object>, LSingle<T> {

	int SIZE = 2;

	T first();

	default T value() {
		return first();
	}

	long second();

	default Object get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjLongPair and calculates hash from it. */
	static <T> int argHashCode(T a1, long a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Long.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjLongPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, long a2, T b1, long b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjLongPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjLongPair are allowed.
				if (!(two instanceof LObjLongPair)) {
					return false;
				}

				LObjLongPair other = (LObjLongPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
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

	interface ComparableObjLongPair<T extends Comparable<T>> extends LObjLongPair<T>, Comparable<LObjLongPair<T>> {

		@Override
		default int compareTo(LObjLongPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Long.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjLongPair<T> implements LObjLongPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjLongPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjLongPair.argHashCode(first(), second());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(first());
			sb.append(',');
			sb.append(second());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjLongPair<T> extends AbstractObjLongPair<T> {

		private T first;
		private long second;

		public MutObjLongPair(T a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> MutObjLongPair<T> of(T a1, long a2) {
			return new MutObjLongPair(a1, a2);
		}

		public static <T> MutObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjLongPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public long second() {
			return second;
		}

		public MutObjLongPair<T> second(long second) {
			this.second = second;
			return this;
		}

		public MutObjLongPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjLongPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjLongPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjLongPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjLongPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjLongPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjLongPair<T> setSecond(long second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjLongPair<T> setSecondIfArg(long second, LLongPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjLongPair<T> setSecondIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjLongPair<T> setSecondIf(LLongPredicate predicate, long second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjLongPair<T> setSecondIf(long second, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjLongPair<T> setSecondIf(LBiLongPredicate predicate, long second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0L;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjLongPair<T extends Comparable<T>> extends AbstractObjLongPair<T> implements ComparableObjLongPair<T> {

		private T first;
		private long second;

		public MutCompObjLongPair(T a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> MutCompObjLongPair<T> of(T a1, long a2) {
			return new MutCompObjLongPair(a1, a2);
		}

		public static <T extends Comparable<T>> MutCompObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjLongPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public long second() {
			return second;
		}

		public MutCompObjLongPair<T> second(long second) {
			this.second = second;
			return this;
		}

		public MutCompObjLongPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjLongPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjLongPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjLongPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjLongPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjLongPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjLongPair<T> setSecond(long second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjLongPair<T> setSecondIfArg(long second, LLongPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjLongPair<T> setSecondIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjLongPair<T> setSecondIf(LLongPredicate predicate, long second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjLongPair<T> setSecondIf(long second, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjLongPair<T> setSecondIf(LBiLongPredicate predicate, long second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0L;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjLongPair<T> extends AbstractObjLongPair<T> {

		private final T first;
		private final long second;

		public ImmObjLongPair(T a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> ImmObjLongPair<T> of(T a1, long a2) {
			return new ImmObjLongPair(a1, a2);
		}

		public static <T> ImmObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public long second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjLongPair<T extends Comparable<T>> extends AbstractObjLongPair<T> implements ComparableObjLongPair<T> {

		private final T first;
		private final long second;

		public ImmCompObjLongPair(T a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> ImmCompObjLongPair<T> of(T a1, long a2) {
			return new ImmCompObjLongPair(a1, a2);
		}

		public static <T extends Comparable<T>> ImmCompObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public long second() {
			return second;
		}

	}

}
