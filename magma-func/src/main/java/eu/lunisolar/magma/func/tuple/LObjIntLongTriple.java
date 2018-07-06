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
 * Exact equivalent of input parameters used in LTieLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntLongTriple<T> extends LTuple<Object> {

	int SIZE = 3;

	T first();

	int second();

	long third();

	default T getFirst() {
		return first();
	}

	default int getSecond() {
		return second();
	}

	default long getThird() {
		return third();
	}

	default Object get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			case 3 :
				return third();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntLongTriple and calculates hash from it. */
	static <T> int argHashCode(T a1, int a2, long a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Integer.hashCode(a2);
		result = prime * result + Long.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntLongTriple and checks if all values are equal. */
	static <T> boolean argEquals(T a1, int a2, long a3, T b1, int b2, long b3) {
		return Null.equals(a1, b1) && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjIntLongTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntLongTriple are allowed.
				if (!(two instanceof LObjIntLongTriple)) {
					return false;
				}

				LObjIntLongTriple other = (LObjIntLongTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();
		i++;
		array[i] = third();

		return array;
	}

	default Object[] toArray(Object[] array) {
		return toArray(array, 0);
	}

	default Object[] toArray() {
		Object[] array = new Object[size()];

		return toArray(array);
	}

	@Override
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

	interface ComparableObjIntLongTriple<T extends Comparable<T>> extends LObjIntLongTriple<T>, Comparable<LObjIntLongTriple<T>> {

		@Override
		default int compareTo(LObjIntLongTriple<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Long.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjIntLongTriple<T> implements LObjIntLongTriple<T> {

		@Override
		public boolean equals(Object that) {
			return LObjIntLongTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntLongTriple.argHashCode(first(), second(), third());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(getFirst());
			sb.append(',');
			sb.append(getSecond());
			sb.append(',');
			sb.append(getThird());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjIntLongTriple<T> extends AbstractObjIntLongTriple<T> {

		private T first;
		private int second;
		private long third;

		public MutObjIntLongTriple(T a1, int a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T> MutObjIntLongTriple<T> of(T a1, int a2, long a3) {
			return new MutObjIntLongTriple(a1, a2, a3);
		}

		public static <T> MutObjIntLongTriple<T> copyOf(LObjIntLongTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public MutObjIntLongTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutObjIntLongTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public long third() {
			return third;
		}

		public MutObjIntLongTriple<T> third(long third) {
			this.third = third;
			return this;
		}

		public MutObjIntLongTriple<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntLongTriple<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntLongTriple<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntLongTriple<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntLongTriple<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntLongTriple<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjIntLongTriple<T> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntLongTriple<T> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntLongTriple<T> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntLongTriple<T> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntLongTriple<T> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntLongTriple<T> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutObjIntLongTriple<T> setThird(long third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntLongTriple<T> setThirdIfArg(long third, LLongPredicate predicate) {
			if (predicate.doTest(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntLongTriple<T> setThirdIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.third = func.doApplyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntLongTriple<T> setThirdIf(LLongPredicate predicate, long third) {
			if (predicate.doTest(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntLongTriple<T> setThirdIf(long third, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntLongTriple<T> setThirdIf(LBiLongPredicate predicate, long third) {

			if (predicate.doTest(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = 0L;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjIntLongTriple<T extends Comparable<T>> extends AbstractObjIntLongTriple<T> implements ComparableObjIntLongTriple<T> {

		private T first;
		private int second;
		private long third;

		public MutCompObjIntLongTriple(T a1, int a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T extends Comparable<T>> MutCompObjIntLongTriple<T> of(T a1, int a2, long a3) {
			return new MutCompObjIntLongTriple(a1, a2, a3);
		}

		public static <T extends Comparable<T>> MutCompObjIntLongTriple<T> copyOf(LObjIntLongTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public MutCompObjIntLongTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompObjIntLongTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public long third() {
			return third;
		}

		public MutCompObjIntLongTriple<T> third(long third) {
			this.third = third;
			return this;
		}

		public MutCompObjIntLongTriple<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntLongTriple<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntLongTriple<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntLongTriple<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntLongTriple<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntLongTriple<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjIntLongTriple<T> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntLongTriple<T> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntLongTriple<T> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntLongTriple<T> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntLongTriple<T> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntLongTriple<T> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompObjIntLongTriple<T> setThird(long third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntLongTriple<T> setThirdIfArg(long third, LLongPredicate predicate) {
			if (predicate.doTest(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntLongTriple<T> setThirdIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.third = func.doApplyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntLongTriple<T> setThirdIf(LLongPredicate predicate, long third) {
			if (predicate.doTest(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntLongTriple<T> setThirdIf(long third, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntLongTriple<T> setThirdIf(LBiLongPredicate predicate, long third) {

			if (predicate.doTest(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = 0L;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntLongTriple<T> extends AbstractObjIntLongTriple<T> {

		private final T first;
		private final int second;
		private final long third;

		public ImmObjIntLongTriple(T a1, int a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T> ImmObjIntLongTriple<T> of(T a1, int a2, long a3) {
			return new ImmObjIntLongTriple(a1, a2, a3);
		}

		public static <T> ImmObjIntLongTriple<T> copyOf(LObjIntLongTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

		public long third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjIntLongTriple<T extends Comparable<T>> extends AbstractObjIntLongTriple<T> implements ComparableObjIntLongTriple<T> {

		private final T first;
		private final int second;
		private final long third;

		public ImmCompObjIntLongTriple(T a1, int a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T extends Comparable<T>> ImmCompObjIntLongTriple<T> of(T a1, int a2, long a3) {
			return new ImmCompObjIntLongTriple(a1, a2, a3);
		}

		public static <T extends Comparable<T>> ImmCompObjIntLongTriple<T> copyOf(LObjIntLongTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

		public long third() {
			return third;
		}

	}

}
