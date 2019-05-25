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
 * Exact equivalent of input parameters used in LObjFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjFltPair<T> extends LTuple<Object>, LSingle<T> {

	int SIZE = 2;

	T first();

	default T value() {
		return first();
	}

	float second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjFltPair and calculates hash from it. */
	static <T> int argHashCode(T a1, float a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Float.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjFltPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, float a2, T b1, float b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjFltPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjFltPair are allowed.
				if (!(two instanceof LObjFltPair)) {
					return false;
				}

				LObjFltPair other = (LObjFltPair) two;

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

	interface ComparableObjFltPair<T extends Comparable<T>> extends LObjFltPair<T>, Comparable<LObjFltPair<T>> {

		@Override
		default int compareTo(LObjFltPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Float.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjFltPair<T> implements LObjFltPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjFltPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjFltPair.argHashCode(first(), second());
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
	final class MutObjFltPair<T> extends AbstractObjFltPair<T> {

		private T first;
		private float second;

		public MutObjFltPair(T a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> MutObjFltPair<T> of(T a1, float a2) {
			return new MutObjFltPair(a1, a2);
		}

		public static <T> MutObjFltPair<T> copyOf(LObjFltPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjFltPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutObjFltPair<T> second(float second) {
			this.second = second;
			return this;
		}

		public MutObjFltPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjFltPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjFltPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjFltPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjFltPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjFltPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjFltPair<T> setSecond(float second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjFltPair<T> setSecondIfArg(float second, LFltPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjFltPair<T> setSecondIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjFltPair<T> setSecondIf(LFltPredicate predicate, float second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjFltPair<T> setSecondIf(float second, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjFltPair<T> setSecondIf(LBiFltPredicate predicate, float second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjFltPair<T extends Comparable<T>> extends AbstractObjFltPair<T> implements ComparableObjFltPair<T> {

		private T first;
		private float second;

		public MutCompObjFltPair(T a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> MutCompObjFltPair<T> of(T a1, float a2) {
			return new MutCompObjFltPair(a1, a2);
		}

		public static <T extends Comparable<T>> MutCompObjFltPair<T> copyOf(LObjFltPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjFltPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutCompObjFltPair<T> second(float second) {
			this.second = second;
			return this;
		}

		public MutCompObjFltPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjFltPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjFltPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjFltPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjFltPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjFltPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjFltPair<T> setSecond(float second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjFltPair<T> setSecondIfArg(float second, LFltPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjFltPair<T> setSecondIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjFltPair<T> setSecondIf(LFltPredicate predicate, float second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjFltPair<T> setSecondIf(float second, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjFltPair<T> setSecondIf(LBiFltPredicate predicate, float second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjFltPair<T> extends AbstractObjFltPair<T> {

		private final T first;
		private final float second;

		public ImmObjFltPair(T a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> ImmObjFltPair<T> of(T a1, float a2) {
			return new ImmObjFltPair(a1, a2);
		}

		public static <T> ImmObjFltPair<T> copyOf(LObjFltPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public float second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjFltPair<T extends Comparable<T>> extends AbstractObjFltPair<T> implements ComparableObjFltPair<T> {

		private final T first;
		private final float second;

		public ImmCompObjFltPair(T a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> ImmCompObjFltPair<T> of(T a1, float a2) {
			return new ImmCompObjFltPair(a1, a2);
		}

		public static <T extends Comparable<T>> ImmCompObjFltPair<T> copyOf(LObjFltPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public float second() {
			return second;
		}

	}

}
