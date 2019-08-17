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
 * Exact equivalent of input parameters used in LQuintConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LQuint<T1, T2, T3, T4, T5> extends LTuple<Object>, LQuad<T1, T2, T3, T4>, Map.Entry<T2, T1> {

	int SIZE = 5;

	T1 first();

	default T1 value() {
		return first();
	}

	T2 second();

	T3 third();

	T4 fourth();

	T5 fifth();

	default Object get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			case 3 :
				return third();
			case 4 :
				return fourth();
			case 5 :
				return fifth();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int tupleSize() {
		return SIZE;
	}

	// <editor-fold desc="Map.Entry">

	/** Returns key as Entry.key() */
	@Override
	default T2 getKey() {
		return second();
	}

	/** Returns value as Entry.value(). 'Value' is assigned to first tuple element. */
	@Override
	default T1 getValue() {
		return first();
	}

	@Override
	default T1 setValue(T1 value) {
		throw new UnsupportedOperationException();
	}

	// </editor-fold>

	/** Static hashCode() implementation method that takes same arguments as fields of the LQuint and calculates hash from it. */
	static <T1, T2, T3, T4, T5> int argHashCode(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + ((a3 == null) ? 0 : a3.hashCode());
		result = prime * result + ((a4 == null) ? 0 : a4.hashCode());
		result = prime * result + ((a5 == null) ? 0 : a5.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LQuint and checks if all values are equal. */
	static <T1, T2, T3, T4, T5> boolean argEquals(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, T1 b1, T2 b2, T3 b3, T4 b4, T5 b5) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2) && //
				Null.equals(a3, b3) && //
				Null.equals(a4, b4) && //
				Null.equals(a5, b5); //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LQuint interface (among others) and their LQuint values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LQuint the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LQuint are allowed.
				if (!(two instanceof LQuint)) {
					return false;
				}

				LQuint other = (LQuint) two;

				return argEquals(one.first(), one.second(), one.third(), one.fourth(), one.fifth(), other.first(), other.second(), other.third(), other.fourth(), other.fifth());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LQuint the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LQuint are allowed.
				if (!(two instanceof LQuint)) {
					return false;
				}

				LQuint other = (LQuint) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), one.fourth(), one.fifth(), other.first(), other.second(), other.third(), other.fourth(), other.fifth());
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

	interface ComparableQuint<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>, T5 extends Comparable<? super T5>>
			extends
				LQuint<T1, T2, T3, T4, T5>,
				Comparable<LQuint<T1, T2, T3, T4, T5>> {

		@Override
		default int compareTo(LQuint<T1, T2, T3, T4, T5> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Null.compare(one.third(), two.third())) != 0 ? retval : //
										(retval = Null.compare(one.fourth(), two.fourth())) != 0 ? retval : //
												(retval = Null.compare(one.fifth(), two.fifth())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractQuint<T1, T2, T3, T4, T5> implements LQuint<T1, T2, T3, T4, T5> {

		@Override
		public boolean equals(Object that) {
			return LQuint.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LQuint.argHashCode(first(), second(), third(), fourth(), fifth());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(first());
			sb.append(',');
			sb.append(second());
			sb.append(',');
			sb.append(third());
			sb.append(',');
			sb.append(fourth());
			sb.append(',');
			sb.append(fifth());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutQuint<T1, T2, T3, T4, T5> extends AbstractQuint<T1, T2, T3, T4, T5> {

		private T1 first;
		private T2 second;
		private T3 third;
		private T4 fourth;
		private T5 fifth;

		public MutQuint(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
			this.fifth = a5;
		}

		public static <T1, T2, T3, T4, T5> MutQuint<T1, T2, T3, T4, T5> of(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
			return new MutQuint(a1, a2, a3, a4, a5);
		}

		public static <T1, T2, T3, T4, T5> MutQuint<T1, T2, T3, T4, T5> copyOf(LQuint<T1, T2, T3, T4, T5> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
		}

		public T1 first() {
			return first;
		}

		public MutQuint<T1, T2, T3, T4, T5> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutQuint<T1, T2, T3, T4, T5> second(T2 second) {
			this.second = second;
			return this;
		}

		public T3 third() {
			return third;
		}

		public MutQuint<T1, T2, T3, T4, T5> third(T3 third) {
			this.third = third;
			return this;
		}

		public T4 fourth() {
			return fourth;
		}

		public MutQuint<T1, T2, T3, T4, T5> fourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

		public T5 fifth() {
			return fifth;
		}

		public MutQuint<T1, T2, T3, T4, T5> fifth(T5 fifth) {
			this.fifth = fifth;
			return this;
		}

		public MutQuint<T1, T2, T3, T4, T5> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuint<T1, T2, T3, T4, T5> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutQuint<T1, T2, T3, T4, T5> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuint<T1, T2, T3, T4, T5> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutQuint<T1, T2, T3, T4, T5> setThird(T3 third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setThirdIfArg(T3 third, LPredicate<T3> predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuint<T1, T2, T3, T4, T5> setThirdIfArgNotNull(R arg, LFunction<R, T3> func) {
			if (arg != null) {
				this.third = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setThirdIf(LPredicate<T3> predicate, T3 third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setThirdIf(T3 third, LBiPredicate<T3, T3> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setThirdIf(LBiPredicate<T3, T3> predicate, T3 third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public MutQuint<T1, T2, T3, T4, T5> setFourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setFourthIfArg(T4 fourth, LPredicate<T4> predicate) {
			if (predicate.test(fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuint<T1, T2, T3, T4, T5> setFourthIfArgNotNull(R arg, LFunction<R, T4> func) {
			if (arg != null) {
				this.fourth = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setFourthIf(LPredicate<T4> predicate, T4 fourth) {
			if (predicate.test(this.fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setFourthIf(T4 fourth, LBiPredicate<T4, T4> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(fourth, this.fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setFourthIf(LBiPredicate<T4, T4> predicate, T4 fourth) {

			if (predicate.test(this.fourth, fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		public MutQuint<T1, T2, T3, T4, T5> setFifth(T5 fifth) {
			this.fifth = fifth;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setFifthIfArg(T5 fifth, LPredicate<T5> predicate) {
			if (predicate.test(fifth)) {
				this.fifth = fifth;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuint<T1, T2, T3, T4, T5> setFifthIfArgNotNull(R arg, LFunction<R, T5> func) {
			if (arg != null) {
				this.fifth = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuint<T1, T2, T3, T4, T5> setFifthIf(LPredicate<T5> predicate, T5 fifth) {
			if (predicate.test(this.fifth)) {
				this.fifth = fifth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setFifthIf(T5 fifth, LBiPredicate<T5, T5> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(fifth, this.fifth)) {
				this.fifth = fifth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuint<T1, T2, T3, T4, T5> setFifthIf(LBiPredicate<T5, T5> predicate, T5 fifth) {

			if (predicate.test(this.fifth, fifth)) {
				this.fifth = fifth;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = null;
			fourth = null;
			fifth = null;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompQuint<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>, T5 extends Comparable<? super T5>> extends AbstractQuint<T1, T2, T3, T4, T5>
			implements
				ComparableQuint<T1, T2, T3, T4, T5> {

		private T1 first;
		private T2 second;
		private T3 third;
		private T4 fourth;
		private T5 fifth;

		public MutCompQuint(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
			this.fifth = a5;
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>, T5 extends Comparable<? super T5>> MutCompQuint<T1, T2, T3, T4, T5> of(T1 a1, T2 a2, T3 a3,
				T4 a4, T5 a5) {
			return new MutCompQuint(a1, a2, a3, a4, a5);
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>, T5 extends Comparable<? super T5>> MutCompQuint<T1, T2, T3, T4, T5> copyOf(
				LQuint<T1, T2, T3, T4, T5> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
		}

		public T1 first() {
			return first;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> second(T2 second) {
			this.second = second;
			return this;
		}

		public T3 third() {
			return third;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> third(T3 third) {
			this.third = third;
			return this;
		}

		public T4 fourth() {
			return fourth;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> fourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

		public T5 fifth() {
			return fifth;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> fifth(T5 fifth) {
			this.fifth = fifth;
			return this;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuint<T1, T2, T3, T4, T5> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuint<T1, T2, T3, T4, T5> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> setThird(T3 third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setThirdIfArg(T3 third, LPredicate<T3> predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuint<T1, T2, T3, T4, T5> setThirdIfArgNotNull(R arg, LFunction<R, T3> func) {
			if (arg != null) {
				this.third = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setThirdIf(LPredicate<T3> predicate, T3 third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setThirdIf(T3 third, LBiPredicate<T3, T3> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setThirdIf(LBiPredicate<T3, T3> predicate, T3 third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> setFourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setFourthIfArg(T4 fourth, LPredicate<T4> predicate) {
			if (predicate.test(fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuint<T1, T2, T3, T4, T5> setFourthIfArgNotNull(R arg, LFunction<R, T4> func) {
			if (arg != null) {
				this.fourth = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setFourthIf(LPredicate<T4> predicate, T4 fourth) {
			if (predicate.test(this.fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setFourthIf(T4 fourth, LBiPredicate<T4, T4> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(fourth, this.fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setFourthIf(LBiPredicate<T4, T4> predicate, T4 fourth) {

			if (predicate.test(this.fourth, fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		public MutCompQuint<T1, T2, T3, T4, T5> setFifth(T5 fifth) {
			this.fifth = fifth;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setFifthIfArg(T5 fifth, LPredicate<T5> predicate) {
			if (predicate.test(fifth)) {
				this.fifth = fifth;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuint<T1, T2, T3, T4, T5> setFifthIfArgNotNull(R arg, LFunction<R, T5> func) {
			if (arg != null) {
				this.fifth = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuint<T1, T2, T3, T4, T5> setFifthIf(LPredicate<T5> predicate, T5 fifth) {
			if (predicate.test(this.fifth)) {
				this.fifth = fifth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setFifthIf(T5 fifth, LBiPredicate<T5, T5> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(fifth, this.fifth)) {
				this.fifth = fifth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuint<T1, T2, T3, T4, T5> setFifthIf(LBiPredicate<T5, T5> predicate, T5 fifth) {

			if (predicate.test(this.fifth, fifth)) {
				this.fifth = fifth;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = null;
			fourth = null;
			fifth = null;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmQuint<T1, T2, T3, T4, T5> extends AbstractQuint<T1, T2, T3, T4, T5> {

		private final T1 first;
		private final T2 second;
		private final T3 third;
		private final T4 fourth;
		private final T5 fifth;

		public ImmQuint(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
			this.fifth = a5;
		}

		public static <T1, T2, T3, T4, T5> ImmQuint<T1, T2, T3, T4, T5> of(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
			return new ImmQuint(a1, a2, a3, a4, a5);
		}

		public static <T1, T2, T3, T4, T5> ImmQuint<T1, T2, T3, T4, T5> copyOf(LQuint<T1, T2, T3, T4, T5> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

		public T3 third() {
			return third;
		}

		public T4 fourth() {
			return fourth;
		}

		public T5 fifth() {
			return fifth;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompQuint<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>, T5 extends Comparable<? super T5>> extends AbstractQuint<T1, T2, T3, T4, T5>
			implements
				ComparableQuint<T1, T2, T3, T4, T5> {

		private final T1 first;
		private final T2 second;
		private final T3 third;
		private final T4 fourth;
		private final T5 fifth;

		public ImmCompQuint(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
			this.fifth = a5;
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>, T5 extends Comparable<? super T5>> ImmCompQuint<T1, T2, T3, T4, T5> of(T1 a1, T2 a2, T3 a3,
				T4 a4, T5 a5) {
			return new ImmCompQuint(a1, a2, a3, a4, a5);
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>, T5 extends Comparable<? super T5>> ImmCompQuint<T1, T2, T3, T4, T5> copyOf(
				LQuint<T1, T2, T3, T4, T5> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

		public T3 third() {
			return third;
		}

		public T4 fourth() {
			return fourth;
		}

		public T5 fifth() {
			return fifth;
		}

	}

}
