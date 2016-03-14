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

package eu.lunisolar.magma.struct.tuple;

import eu.lunisolar.magma.basics.meta.LTuple;
import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;
import javax.annotation.concurrent.Immutable;
import java.util.*;

/**
 * Exact equivalent of input parameters used in LTriConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LTriple<T1, T2, T3> extends LTuple<Object> {

	int SIZE = 3;

	T1 first();

	T2 second();

	T3 third();

	default T1 getFirst() {
		return first();
	}

	default T2 getSecond() {
		return second();
	}

	default T3 getThird() {
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

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void setThird(T3 third) {
			this.third = third;
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

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void setThird(T3 third) {
			this.third = third;
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
