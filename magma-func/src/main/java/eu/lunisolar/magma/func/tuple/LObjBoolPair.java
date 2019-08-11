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
 * Exact equivalent of input parameters used in LObjBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBoolPair<T> extends LTuple<Object>, LSingle<T> {

	int SIZE = 2;

	T first();

	default T value() {
		return first();
	}

	boolean second();

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
	default int tupleSize() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjBoolPair and calculates hash from it. */
	static <T> int argHashCode(T a1, boolean a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Boolean.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjBoolPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, boolean a2, T b1, boolean b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjBoolPair interface (among others) and their LObjBoolPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjBoolPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjBoolPair are allowed.
				if (!(two instanceof LObjBoolPair)) {
					return false;
				}

				LObjBoolPair other = (LObjBoolPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjBoolPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjBoolPair are allowed.
				if (!(two instanceof LObjBoolPair)) {
					return false;
				}

				LObjBoolPair other = (LObjBoolPair) two;

				return the.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
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

	interface ComparableObjBoolPair<T extends Comparable<? super T>> extends LObjBoolPair<T>, Comparable<LObjBoolPair<T>> {

		@Override
		default int compareTo(LObjBoolPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Boolean.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjBoolPair<T> implements LObjBoolPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjBoolPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjBoolPair.argHashCode(first(), second());
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
	final class MutObjBoolPair<T> extends AbstractObjBoolPair<T> {

		private T first;
		private boolean second;

		public MutObjBoolPair(T a1, boolean a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> MutObjBoolPair<T> of(T a1, boolean a2) {
			return new MutObjBoolPair(a1, a2);
		}

		public static <T> MutObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjBoolPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutObjBoolPair<T> second(boolean second) {
			this.second = second;
			return this;
		}

		public MutObjBoolPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjBoolPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjBoolPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjBoolPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjBoolPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjBoolPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjBoolPair<T> setSecond(boolean second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjBoolPair<T> setSecondIfArg(boolean second, LLogicalOperator predicate) {
			if (predicate.apply(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjBoolPair<T> setSecondIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.second = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjBoolPair<T> setSecondIf(LLogicalOperator predicate, boolean second) {
			if (predicate.apply(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjBoolPair<T> setSecondIf(boolean second, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjBoolPair<T> setSecondIf(LLogicalBinaryOperator predicate, boolean second) {

			if (predicate.apply(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = false;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjBoolPair<T extends Comparable<? super T>> extends AbstractObjBoolPair<T> implements ComparableObjBoolPair<T> {

		private T first;
		private boolean second;

		public MutCompObjBoolPair(T a1, boolean a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<? super T>> MutCompObjBoolPair<T> of(T a1, boolean a2) {
			return new MutCompObjBoolPair(a1, a2);
		}

		public static <T extends Comparable<? super T>> MutCompObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjBoolPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutCompObjBoolPair<T> second(boolean second) {
			this.second = second;
			return this;
		}

		public MutCompObjBoolPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjBoolPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjBoolPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjBoolPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjBoolPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjBoolPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjBoolPair<T> setSecond(boolean second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjBoolPair<T> setSecondIfArg(boolean second, LLogicalOperator predicate) {
			if (predicate.apply(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjBoolPair<T> setSecondIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.second = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjBoolPair<T> setSecondIf(LLogicalOperator predicate, boolean second) {
			if (predicate.apply(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjBoolPair<T> setSecondIf(boolean second, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjBoolPair<T> setSecondIf(LLogicalBinaryOperator predicate, boolean second) {

			if (predicate.apply(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = false;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjBoolPair<T> extends AbstractObjBoolPair<T> {

		private final T first;
		private final boolean second;

		public ImmObjBoolPair(T a1, boolean a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> ImmObjBoolPair<T> of(T a1, boolean a2) {
			return new ImmObjBoolPair(a1, a2);
		}

		public static <T> ImmObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public boolean second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjBoolPair<T extends Comparable<? super T>> extends AbstractObjBoolPair<T> implements ComparableObjBoolPair<T> {

		private final T first;
		private final boolean second;

		public ImmCompObjBoolPair(T a1, boolean a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<? super T>> ImmCompObjBoolPair<T> of(T a1, boolean a2) {
			return new ImmCompObjBoolPair(a1, a2);
		}

		public static <T extends Comparable<? super T>> ImmCompObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public boolean second() {
			return second;
		}

	}

}
