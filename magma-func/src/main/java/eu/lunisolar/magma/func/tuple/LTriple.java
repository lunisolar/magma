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
 * Exact equivalent of input parameters used in LTriConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LTriple<T1, T2, T3> extends LTuple<Object>, LPair<T1, T2>, Map.Entry<T2, T1> {

	int SIZE = 3;

	T1 first();

	default T1 value() {
		return first();
	}

	T2 second();

	T3 third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LTriple and calculates hash from it. */
	static <T1, T2, T3> int argHashCode(T1 a1, T2 a2, T3 a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + ((a3 == null) ? 0 : a3.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LTriple and checks if all values are equal. */
	static <T1, T2, T3> boolean argEquals(T1 a1, T2 a2, T3 a3, T1 b1, T2 b2, T3 b3) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2) && //
				Null.equals(a3, b3); //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LTriple are allowed.
				if (!(two instanceof LTriple)) {
					return false;
				}

				LTriple other = (LTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
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

	interface ComparableTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> extends LTriple<T1, T2, T3>, Comparable<LTriple<T1, T2, T3>> {

		@Override
		default int compareTo(LTriple<T1, T2, T3> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Null.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractTriple<T1, T2, T3> implements LTriple<T1, T2, T3> {

		@Override
		public boolean equals(Object that) {
			return LTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LTriple.argHashCode(first(), second(), third());
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
	final class MutTriple<T1, T2, T3> extends AbstractTriple<T1, T2, T3> {

		private T1 first;
		private T2 second;
		private T3 third;

		public MutTriple(T1 a1, T2 a2, T3 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2, T3> MutTriple<T1, T2, T3> of(T1 a1, T2 a2, T3 a3) {
			return new MutTriple(a1, a2, a3);
		}

		public static <T1, T2, T3> MutTriple<T1, T2, T3> copyOf(LTriple<T1, T2, T3> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutTriple<T1, T2, T3> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutTriple<T1, T2, T3> second(T2 second) {
			this.second = second;
			return this;
		}

		public T3 third() {
			return third;
		}

		public MutTriple<T1, T2, T3> third(T3 third) {
			this.third = third;
			return this;
		}

		public MutTriple<T1, T2, T3> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutTriple<T1, T2, T3> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutTriple<T1, T2, T3> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutTriple<T1, T2, T3> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutTriple<T1, T2, T3> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutTriple<T1, T2, T3> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutTriple<T1, T2, T3> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutTriple<T1, T2, T3> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutTriple<T1, T2, T3> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutTriple<T1, T2, T3> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutTriple<T1, T2, T3> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutTriple<T1, T2, T3> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutTriple<T1, T2, T3> setThird(T3 third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutTriple<T1, T2, T3> setThirdIfArg(T3 third, LPredicate<T3> predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutTriple<T1, T2, T3> setThirdIfArgNotNull(R arg, LFunction<R, T3> func) {
			if (arg != null) {
				this.third = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutTriple<T1, T2, T3> setThirdIf(LPredicate<T3> predicate, T3 third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutTriple<T1, T2, T3> setThirdIf(T3 third, LBiPredicate<T3, T3> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutTriple<T1, T2, T3> setThirdIf(LBiPredicate<T3, T3> predicate, T3 third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = null;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> extends AbstractTriple<T1, T2, T3> implements ComparableTriple<T1, T2, T3> {

		private T1 first;
		private T2 second;
		private T3 third;

		public MutCompTriple(T1 a1, T2 a2, T3 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> MutCompTriple<T1, T2, T3> of(T1 a1, T2 a2, T3 a3) {
			return new MutCompTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> MutCompTriple<T1, T2, T3> copyOf(LTriple<T1, T2, T3> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutCompTriple<T1, T2, T3> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompTriple<T1, T2, T3> second(T2 second) {
			this.second = second;
			return this;
		}

		public T3 third() {
			return third;
		}

		public MutCompTriple<T1, T2, T3> third(T3 third) {
			this.third = third;
			return this;
		}

		public MutCompTriple<T1, T2, T3> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompTriple<T1, T2, T3> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompTriple<T1, T2, T3> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompTriple<T1, T2, T3> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompTriple<T1, T2, T3> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompTriple<T1, T2, T3> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompTriple<T1, T2, T3> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompTriple<T1, T2, T3> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompTriple<T1, T2, T3> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompTriple<T1, T2, T3> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompTriple<T1, T2, T3> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompTriple<T1, T2, T3> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompTriple<T1, T2, T3> setThird(T3 third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompTriple<T1, T2, T3> setThirdIfArg(T3 third, LPredicate<T3> predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompTriple<T1, T2, T3> setThirdIfArgNotNull(R arg, LFunction<R, T3> func) {
			if (arg != null) {
				this.third = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompTriple<T1, T2, T3> setThirdIf(LPredicate<T3> predicate, T3 third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompTriple<T1, T2, T3> setThirdIf(T3 third, LBiPredicate<T3, T3> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompTriple<T1, T2, T3> setThirdIf(LBiPredicate<T3, T3> predicate, T3 third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = null;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmTriple<T1, T2, T3> extends AbstractTriple<T1, T2, T3> {

		private final T1 first;
		private final T2 second;
		private final T3 third;

		public ImmTriple(T1 a1, T2 a2, T3 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2, T3> ImmTriple<T1, T2, T3> of(T1 a1, T2 a2, T3 a3) {
			return new ImmTriple(a1, a2, a3);
		}

		public static <T1, T2, T3> ImmTriple<T1, T2, T3> copyOf(LTriple<T1, T2, T3> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
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

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> extends AbstractTriple<T1, T2, T3> implements ComparableTriple<T1, T2, T3> {

		private final T1 first;
		private final T2 second;
		private final T3 third;

		public ImmCompTriple(T1 a1, T2 a2, T3 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> ImmCompTriple<T1, T2, T3> of(T1 a1, T2 a2, T3 a3) {
			return new ImmCompTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> ImmCompTriple<T1, T2, T3> copyOf(LTriple<T1, T2, T3> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
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

	}

}
