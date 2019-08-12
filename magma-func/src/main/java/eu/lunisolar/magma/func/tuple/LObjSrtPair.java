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
 * Exact equivalent of input parameters used in LObjSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjSrtPair<T> extends LTuple<Object>, LSingle<T> {

	int SIZE = 2;

	T first();

	default T value() {
		return first();
	}

	short second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjSrtPair and calculates hash from it. */
	static <T> int argHashCode(T a1, short a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Short.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjSrtPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, short a2, T b1, short b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjSrtPair interface (among others) and their LObjSrtPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjSrtPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjSrtPair are allowed.
				if (!(two instanceof LObjSrtPair)) {
					return false;
				}

				LObjSrtPair other = (LObjSrtPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjSrtPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjSrtPair are allowed.
				if (!(two instanceof LObjSrtPair)) {
					return false;
				}

				LObjSrtPair other = (LObjSrtPair) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
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

	interface ComparableObjSrtPair<T extends Comparable<? super T>> extends LObjSrtPair<T>, Comparable<LObjSrtPair<T>> {

		@Override
		default int compareTo(LObjSrtPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Short.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjSrtPair<T> implements LObjSrtPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjSrtPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjSrtPair.argHashCode(first(), second());
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
	final class MutObjSrtPair<T> extends AbstractObjSrtPair<T> {

		private T first;
		private short second;

		public MutObjSrtPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> MutObjSrtPair<T> of(T a1, short a2) {
			return new MutObjSrtPair(a1, a2);
		}

		public static <T> MutObjSrtPair<T> copyOf(LObjSrtPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjSrtPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutObjSrtPair<T> second(short second) {
			this.second = second;
			return this;
		}

		public MutObjSrtPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjSrtPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjSrtPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjSrtPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjSrtPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjSrtPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjSrtPair<T> setSecond(short second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjSrtPair<T> setSecondIfArg(short second, LSrtPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjSrtPair<T> setSecondIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjSrtPair<T> setSecondIf(LSrtPredicate predicate, short second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjSrtPair<T> setSecondIf(short second, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjSrtPair<T> setSecondIf(LBiSrtPredicate predicate, short second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = (short) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjSrtPair<T extends Comparable<? super T>> extends AbstractObjSrtPair<T> implements ComparableObjSrtPair<T> {

		private T first;
		private short second;

		public MutCompObjSrtPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<? super T>> MutCompObjSrtPair<T> of(T a1, short a2) {
			return new MutCompObjSrtPair(a1, a2);
		}

		public static <T extends Comparable<? super T>> MutCompObjSrtPair<T> copyOf(LObjSrtPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjSrtPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutCompObjSrtPair<T> second(short second) {
			this.second = second;
			return this;
		}

		public MutCompObjSrtPair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjSrtPair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjSrtPair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjSrtPair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjSrtPair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjSrtPair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjSrtPair<T> setSecond(short second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjSrtPair<T> setSecondIfArg(short second, LSrtPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjSrtPair<T> setSecondIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjSrtPair<T> setSecondIf(LSrtPredicate predicate, short second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjSrtPair<T> setSecondIf(short second, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjSrtPair<T> setSecondIf(LBiSrtPredicate predicate, short second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = (short) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjSrtPair<T> extends AbstractObjSrtPair<T> {

		private final T first;
		private final short second;

		public ImmObjSrtPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> ImmObjSrtPair<T> of(T a1, short a2) {
			return new ImmObjSrtPair(a1, a2);
		}

		public static <T> ImmObjSrtPair<T> copyOf(LObjSrtPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public short second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjSrtPair<T extends Comparable<? super T>> extends AbstractObjSrtPair<T> implements ComparableObjSrtPair<T> {

		private final T first;
		private final short second;

		public ImmCompObjSrtPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<? super T>> ImmCompObjSrtPair<T> of(T a1, short a2) {
			return new ImmCompObjSrtPair(a1, a2);
		}

		public static <T extends Comparable<? super T>> ImmCompObjSrtPair<T> copyOf(LObjSrtPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public short second() {
			return second;
		}

	}

}
