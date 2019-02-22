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
 * Exact equivalent of input parameters used in LObjDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjDblPair<T> extends LTuple<Object>, LSingle<T> {

	int SIZE = 2;

	T first();

	default T value() {
		return first();
	}

	double second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjDblPair and calculates hash from it. */
	static <T> int argHashCode(T a1, double a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Double.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjDblPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, double a2, T b1, double b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjDblPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjDblPair are allowed.
				if (!(two instanceof LObjDblPair)) {
					return false;
				}

				LObjDblPair other = (LObjDblPair) two;

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

	interface ComparableObjDblPair<T extends Comparable<T>> extends LObjDblPair<T>, Comparable<LObjDblPair<T>> {

		@Override
		default int compareTo(LObjDblPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Double.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjDblPair<T> implements LObjDblPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjDblPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjDblPair.argHashCode(first(), second());
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
	final class MutObjDblPair<T> extends AbstractObjDblPair<T> {

		private T first;
		private double second;

		public MutObjDblPair(T a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> MutObjDblPair<T> of(T a1, double a2) {
			return new MutObjDblPair(a1, a2);
		}

		public static <T> MutObjDblPair<T> copyOf(LObjDblPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjDblPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutObjDblPair<T> second(double second) {
			this.second = second;
			return this;
		}

		public MutObjDblPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjDblPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjDblPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjDblPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjDblPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjDblPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjDblPair<T> setSecond(double second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjDblPair<T> setSecondIfArg(double second, LDblPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjDblPair<T> setSecondIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjDblPair<T> setSecondIf(LDblPredicate predicate, double second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjDblPair<T> setSecondIf(double second, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjDblPair<T> setSecondIf(LBiDblPredicate predicate, double second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0d;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjDblPair<T extends Comparable<T>> extends AbstractObjDblPair<T> implements ComparableObjDblPair<T> {

		private T first;
		private double second;

		public MutCompObjDblPair(T a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> MutCompObjDblPair<T> of(T a1, double a2) {
			return new MutCompObjDblPair(a1, a2);
		}

		public static <T extends Comparable<T>> MutCompObjDblPair<T> copyOf(LObjDblPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjDblPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutCompObjDblPair<T> second(double second) {
			this.second = second;
			return this;
		}

		public MutCompObjDblPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjDblPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjDblPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjDblPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjDblPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjDblPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjDblPair<T> setSecond(double second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjDblPair<T> setSecondIfArg(double second, LDblPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjDblPair<T> setSecondIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjDblPair<T> setSecondIf(LDblPredicate predicate, double second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjDblPair<T> setSecondIf(double second, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjDblPair<T> setSecondIf(LBiDblPredicate predicate, double second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0d;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjDblPair<T> extends AbstractObjDblPair<T> {

		private final T first;
		private final double second;

		public ImmObjDblPair(T a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> ImmObjDblPair<T> of(T a1, double a2) {
			return new ImmObjDblPair(a1, a2);
		}

		public static <T> ImmObjDblPair<T> copyOf(LObjDblPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public double second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjDblPair<T extends Comparable<T>> extends AbstractObjDblPair<T> implements ComparableObjDblPair<T> {

		private final T first;
		private final double second;

		public ImmCompObjDblPair(T a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> ImmCompObjDblPair<T> of(T a1, double a2) {
			return new ImmCompObjDblPair(a1, a2);
		}

		public static <T extends Comparable<T>> ImmCompObjDblPair<T> copyOf(LObjDblPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public double second() {
			return second;
		}

	}

}
