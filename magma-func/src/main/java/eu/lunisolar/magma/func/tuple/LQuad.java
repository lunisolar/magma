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
 * Exact equivalent of input parameters used in LQuadConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LQuad<T1, T2, T3, T4> extends LTuple<Object>, LTriple<T1, T2, T3>, Map.Entry<T2, T1> {

	int SIZE = 4;

	T1 first();

	default T1 value() {
		return first();
	}

	T2 second();

	T3 third();

	T4 fourth();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LQuad and calculates hash from it. */
	static <T1, T2, T3, T4> int argHashCode(T1 a1, T2 a2, T3 a3, T4 a4) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + ((a3 == null) ? 0 : a3.hashCode());
		result = prime * result + ((a4 == null) ? 0 : a4.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LQuad and checks if all values are equal. */
	static <T1, T2, T3, T4> boolean argEquals(T1 a1, T2 a2, T3 a3, T4 a4, T1 b1, T2 b2, T3 b3, T4 b4) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2) && //
				Null.equals(a3, b3) && //
				Null.equals(a4, b4); //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LQuad interface (among others) and their LQuad values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LQuad the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LQuad are allowed.
				if (!(two instanceof LQuad)) {
					return false;
				}

				LQuad other = (LQuad) two;

				return argEquals(one.first(), one.second(), one.third(), one.fourth(), other.first(), other.second(), other.third(), other.fourth());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LQuad the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LQuad are allowed.
				if (!(two instanceof LQuad)) {
					return false;
				}

				LQuad other = (LQuad) two;

				return the.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), one.fourth(), other.first(), other.second(), other.third(), other.fourth());
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

	interface ComparableQuad<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> extends LQuad<T1, T2, T3, T4>, Comparable<LQuad<T1, T2, T3, T4>> {

		@Override
		default int compareTo(LQuad<T1, T2, T3, T4> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Null.compare(one.third(), two.third())) != 0 ? retval : //
										(retval = Null.compare(one.fourth(), two.fourth())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractQuad<T1, T2, T3, T4> implements LQuad<T1, T2, T3, T4> {

		@Override
		public boolean equals(Object that) {
			return LQuad.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LQuad.argHashCode(first(), second(), third(), fourth());
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
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutQuad<T1, T2, T3, T4> extends AbstractQuad<T1, T2, T3, T4> {

		private T1 first;
		private T2 second;
		private T3 third;
		private T4 fourth;

		public MutQuad(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
		}

		public static <T1, T2, T3, T4> MutQuad<T1, T2, T3, T4> of(T1 a1, T2 a2, T3 a3, T4 a4) {
			return new MutQuad(a1, a2, a3, a4);
		}

		public static <T1, T2, T3, T4> MutQuad<T1, T2, T3, T4> copyOf(LQuad<T1, T2, T3, T4> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
		}

		public T1 first() {
			return first;
		}

		public MutQuad<T1, T2, T3, T4> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutQuad<T1, T2, T3, T4> second(T2 second) {
			this.second = second;
			return this;
		}

		public T3 third() {
			return third;
		}

		public MutQuad<T1, T2, T3, T4> third(T3 third) {
			this.third = third;
			return this;
		}

		public T4 fourth() {
			return fourth;
		}

		public MutQuad<T1, T2, T3, T4> fourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

		public MutQuad<T1, T2, T3, T4> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuad<T1, T2, T3, T4> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuad<T1, T2, T3, T4> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuad<T1, T2, T3, T4> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuad<T1, T2, T3, T4> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuad<T1, T2, T3, T4> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutQuad<T1, T2, T3, T4> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuad<T1, T2, T3, T4> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuad<T1, T2, T3, T4> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuad<T1, T2, T3, T4> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuad<T1, T2, T3, T4> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuad<T1, T2, T3, T4> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutQuad<T1, T2, T3, T4> setThird(T3 third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuad<T1, T2, T3, T4> setThirdIfArg(T3 third, LPredicate<T3> predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuad<T1, T2, T3, T4> setThirdIfArgNotNull(R arg, LFunction<R, T3> func) {
			if (arg != null) {
				this.third = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuad<T1, T2, T3, T4> setThirdIf(LPredicate<T3> predicate, T3 third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuad<T1, T2, T3, T4> setThirdIf(T3 third, LBiPredicate<T3, T3> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuad<T1, T2, T3, T4> setThirdIf(LBiPredicate<T3, T3> predicate, T3 third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public MutQuad<T1, T2, T3, T4> setFourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutQuad<T1, T2, T3, T4> setFourthIfArg(T4 fourth, LPredicate<T4> predicate) {
			if (predicate.test(fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutQuad<T1, T2, T3, T4> setFourthIfArgNotNull(R arg, LFunction<R, T4> func) {
			if (arg != null) {
				this.fourth = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutQuad<T1, T2, T3, T4> setFourthIf(LPredicate<T4> predicate, T4 fourth) {
			if (predicate.test(this.fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutQuad<T1, T2, T3, T4> setFourthIf(T4 fourth, LBiPredicate<T4, T4> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(fourth, this.fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutQuad<T1, T2, T3, T4> setFourthIf(LBiPredicate<T4, T4> predicate, T4 fourth) {

			if (predicate.test(this.fourth, fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = null;
			fourth = null;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompQuad<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> extends AbstractQuad<T1, T2, T3, T4> implements ComparableQuad<T1, T2, T3, T4> {

		private T1 first;
		private T2 second;
		private T3 third;
		private T4 fourth;

		public MutCompQuad(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> MutCompQuad<T1, T2, T3, T4> of(T1 a1, T2 a2, T3 a3, T4 a4) {
			return new MutCompQuad(a1, a2, a3, a4);
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> MutCompQuad<T1, T2, T3, T4> copyOf(LQuad<T1, T2, T3, T4> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
		}

		public T1 first() {
			return first;
		}

		public MutCompQuad<T1, T2, T3, T4> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompQuad<T1, T2, T3, T4> second(T2 second) {
			this.second = second;
			return this;
		}

		public T3 third() {
			return third;
		}

		public MutCompQuad<T1, T2, T3, T4> third(T3 third) {
			this.third = third;
			return this;
		}

		public T4 fourth() {
			return fourth;
		}

		public MutCompQuad<T1, T2, T3, T4> fourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

		public MutCompQuad<T1, T2, T3, T4> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuad<T1, T2, T3, T4> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuad<T1, T2, T3, T4> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuad<T1, T2, T3, T4> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuad<T1, T2, T3, T4> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuad<T1, T2, T3, T4> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompQuad<T1, T2, T3, T4> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuad<T1, T2, T3, T4> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuad<T1, T2, T3, T4> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuad<T1, T2, T3, T4> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuad<T1, T2, T3, T4> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuad<T1, T2, T3, T4> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompQuad<T1, T2, T3, T4> setThird(T3 third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuad<T1, T2, T3, T4> setThirdIfArg(T3 third, LPredicate<T3> predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuad<T1, T2, T3, T4> setThirdIfArgNotNull(R arg, LFunction<R, T3> func) {
			if (arg != null) {
				this.third = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuad<T1, T2, T3, T4> setThirdIf(LPredicate<T3> predicate, T3 third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuad<T1, T2, T3, T4> setThirdIf(T3 third, LBiPredicate<T3, T3> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuad<T1, T2, T3, T4> setThirdIf(LBiPredicate<T3, T3> predicate, T3 third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public MutCompQuad<T1, T2, T3, T4> setFourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompQuad<T1, T2, T3, T4> setFourthIfArg(T4 fourth, LPredicate<T4> predicate) {
			if (predicate.test(fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompQuad<T1, T2, T3, T4> setFourthIfArgNotNull(R arg, LFunction<R, T4> func) {
			if (arg != null) {
				this.fourth = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompQuad<T1, T2, T3, T4> setFourthIf(LPredicate<T4> predicate, T4 fourth) {
			if (predicate.test(this.fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompQuad<T1, T2, T3, T4> setFourthIf(T4 fourth, LBiPredicate<T4, T4> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(fourth, this.fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompQuad<T1, T2, T3, T4> setFourthIf(LBiPredicate<T4, T4> predicate, T4 fourth) {

			if (predicate.test(this.fourth, fourth)) {
				this.fourth = fourth;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = null;
			fourth = null;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmQuad<T1, T2, T3, T4> extends AbstractQuad<T1, T2, T3, T4> {

		private final T1 first;
		private final T2 second;
		private final T3 third;
		private final T4 fourth;

		public ImmQuad(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
		}

		public static <T1, T2, T3, T4> ImmQuad<T1, T2, T3, T4> of(T1 a1, T2 a2, T3 a3, T4 a4) {
			return new ImmQuad(a1, a2, a3, a4);
		}

		public static <T1, T2, T3, T4> ImmQuad<T1, T2, T3, T4> copyOf(LQuad<T1, T2, T3, T4> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
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

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompQuad<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> extends AbstractQuad<T1, T2, T3, T4> implements ComparableQuad<T1, T2, T3, T4> {

		private final T1 first;
		private final T2 second;
		private final T3 third;
		private final T4 fourth;

		public ImmCompQuad(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> ImmCompQuad<T1, T2, T3, T4> of(T1 a1, T2 a2, T3 a3, T4 a4) {
			return new ImmCompQuad(a1, a2, a3, a4);
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> ImmCompQuad<T1, T2, T3, T4> copyOf(LQuad<T1, T2, T3, T4> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
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

	}

}
