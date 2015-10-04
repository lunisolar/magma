/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

	default int size() {
		return SIZE;
	}

	static <T1, T2, T3> int hashCode(T1 first, T2 second, T3 third) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + ((third == null) ? 0 : third.hashCode());
		return result;
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
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LTriple are allowed.
					if (!(two instanceof LTriple)) {
						return false;
					}

					LTriple other = (LTriple) two;

					return Null.equals(one.first(), other.first()) && //
							Null.equals(one.second(), other.second()) && //
							Null.equals(one.third(), other.third()); //
				});
		}

		@Override
		public int hashCode() {
			return LTriple.hashCode(first(), second(), third());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutTriple<T1, T2, T3> extends AbstractTriple<T1, T2, T3> {

		private T1 first;
		private T2 second;
		private T3 third;

		public MutTriple(T1 first, T2 second, T3 third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1, T2, T3> MutTriple<T1, T2, T3> of(T1 first, T2 second, T3 third) {
			return new MutTriple(first, second, third);
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

		public MutCompTriple(T1 first, T2 second, T3 third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> MutCompTriple<T1, T2, T3> of(T1 first, T2 second, T3 third) {
			return new MutCompTriple(first, second, third);
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

		public ImmTriple(T1 first, T2 second, T3 third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1, T2, T3> ImmTriple<T1, T2, T3> of(T1 first, T2 second, T3 third) {
			return new ImmTriple(first, second, third);
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

		public ImmCompTriple(T1 first, T2 second, T3 third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> ImmCompTriple<T1, T2, T3> of(T1 first, T2 second, T3 third) {
			return new ImmCompTriple(first, second, third);
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
