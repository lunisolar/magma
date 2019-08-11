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
 * Exact equivalent of input parameters used in LTieConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntObjTriple<T1, T2> extends LTuple<Object>, LObjIntPair<T1> {

	int SIZE = 3;

	T1 first();

	default T1 value() {
		return first();
	}

	int second();

	T2 third();

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
	default int tupleSize() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntObjTriple and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 a1, int a2, T2 a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Integer.hashCode(a2);
		result = prime * result + ((a3 == null) ? 0 : a3.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntObjTriple and checks if all values are equal. */
	static <T1, T2> boolean argEquals(T1 a1, int a2, T2 a3, T1 b1, int b2, T2 b3) {
		return Null.equals(a1, b1) && //
				a2 == b2 && //
				Null.equals(a3, b3); //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjIntObjTriple interface (among others) and their LObjIntObjTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjIntObjTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntObjTriple are allowed.
				if (!(two instanceof LObjIntObjTriple)) {
					return false;
				}

				LObjIntObjTriple other = (LObjIntObjTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjIntObjTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntObjTriple are allowed.
				if (!(two instanceof LObjIntObjTriple)) {
					return false;
				}

				LObjIntObjTriple other = (LObjIntObjTriple) two;

				return the.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
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

	interface ComparableObjIntObjTriple<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> extends LObjIntObjTriple<T1, T2>, Comparable<LObjIntObjTriple<T1, T2>> {

		@Override
		default int compareTo(LObjIntObjTriple<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Null.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjIntObjTriple<T1, T2> implements LObjIntObjTriple<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LObjIntObjTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntObjTriple.argHashCode(first(), second(), third());
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
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjIntObjTriple<T1, T2> extends AbstractObjIntObjTriple<T1, T2> {

		private T1 first;
		private int second;
		private T2 third;

		public MutObjIntObjTriple(T1 a1, int a2, T2 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> MutObjIntObjTriple<T1, T2> of(T1 a1, int a2, T2 a3) {
			return new MutObjIntObjTriple(a1, a2, a3);
		}

		public static <T1, T2> MutObjIntObjTriple<T1, T2> copyOf(LObjIntObjTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutObjIntObjTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutObjIntObjTriple<T1, T2> second(int second) {
			this.second = second;
			return this;
		}

		public T2 third() {
			return third;
		}

		public MutObjIntObjTriple<T1, T2> third(T2 third) {
			this.third = third;
			return this;
		}

		public MutObjIntObjTriple<T1, T2> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntObjTriple<T1, T2> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntObjTriple<T1, T2> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntObjTriple<T1, T2> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntObjTriple<T1, T2> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntObjTriple<T1, T2> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjIntObjTriple<T1, T2> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntObjTriple<T1, T2> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntObjTriple<T1, T2> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntObjTriple<T1, T2> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntObjTriple<T1, T2> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntObjTriple<T1, T2> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutObjIntObjTriple<T1, T2> setThird(T2 third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntObjTriple<T1, T2> setThirdIfArg(T2 third, LPredicate<T2> predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntObjTriple<T1, T2> setThirdIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.third = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntObjTriple<T1, T2> setThirdIf(LPredicate<T2> predicate, T2 third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntObjTriple<T1, T2> setThirdIf(T2 third, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntObjTriple<T1, T2> setThirdIf(LBiPredicate<T2, T2> predicate, T2 third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = null;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjIntObjTriple<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> extends AbstractObjIntObjTriple<T1, T2> implements ComparableObjIntObjTriple<T1, T2> {

		private T1 first;
		private int second;
		private T2 third;

		public MutCompObjIntObjTriple(T1 a1, int a2, T2 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> MutCompObjIntObjTriple<T1, T2> of(T1 a1, int a2, T2 a3) {
			return new MutCompObjIntObjTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> MutCompObjIntObjTriple<T1, T2> copyOf(LObjIntObjTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutCompObjIntObjTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompObjIntObjTriple<T1, T2> second(int second) {
			this.second = second;
			return this;
		}

		public T2 third() {
			return third;
		}

		public MutCompObjIntObjTriple<T1, T2> third(T2 third) {
			this.third = third;
			return this;
		}

		public MutCompObjIntObjTriple<T1, T2> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntObjTriple<T1, T2> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntObjTriple<T1, T2> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntObjTriple<T1, T2> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntObjTriple<T1, T2> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntObjTriple<T1, T2> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjIntObjTriple<T1, T2> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntObjTriple<T1, T2> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntObjTriple<T1, T2> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntObjTriple<T1, T2> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntObjTriple<T1, T2> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntObjTriple<T1, T2> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompObjIntObjTriple<T1, T2> setThird(T2 third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntObjTriple<T1, T2> setThirdIfArg(T2 third, LPredicate<T2> predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntObjTriple<T1, T2> setThirdIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.third = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntObjTriple<T1, T2> setThirdIf(LPredicate<T2> predicate, T2 third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntObjTriple<T1, T2> setThirdIf(T2 third, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntObjTriple<T1, T2> setThirdIf(LBiPredicate<T2, T2> predicate, T2 third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = null;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntObjTriple<T1, T2> extends AbstractObjIntObjTriple<T1, T2> {

		private final T1 first;
		private final int second;
		private final T2 third;

		public ImmObjIntObjTriple(T1 a1, int a2, T2 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> ImmObjIntObjTriple<T1, T2> of(T1 a1, int a2, T2 a3) {
			return new ImmObjIntObjTriple(a1, a2, a3);
		}

		public static <T1, T2> ImmObjIntObjTriple<T1, T2> copyOf(LObjIntObjTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public int second() {
			return second;
		}

		public T2 third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjIntObjTriple<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> extends AbstractObjIntObjTriple<T1, T2> implements ComparableObjIntObjTriple<T1, T2> {

		private final T1 first;
		private final int second;
		private final T2 third;

		public ImmCompObjIntObjTriple(T1 a1, int a2, T2 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> ImmCompObjIntObjTriple<T1, T2> of(T1 a1, int a2, T2 a3) {
			return new ImmCompObjIntObjTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> ImmCompObjIntObjTriple<T1, T2> copyOf(LObjIntObjTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public int second() {
			return second;
		}

		public T2 third() {
			return third;
		}

	}

}
