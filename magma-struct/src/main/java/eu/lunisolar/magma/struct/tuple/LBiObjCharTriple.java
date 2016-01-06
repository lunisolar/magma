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
 * Exact equivalent of input parameters used in LBiObjCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjCharTriple<T1, T2> extends LTuple<Object> {

	int SIZE = 3;

	T1 first();

	T2 second();

	char third();

	default T1 getFirst() {
		return first();
	}

	default T2 getSecond() {
		return second();
	}

	default char getThird() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBiObjCharTriple and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 first, T2 second, char third) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + Character.hashCode(third);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBiObjCharTriple and checks if all values are equal. */
	static <T1, T2> boolean argEquals(T1 first, T2 second, char third, T1 firstOfOther, T2 secondOfOther, char thirdOfOther) {
		return Null.equals(first, firstOfOther) && //
				Null.equals(second, secondOfOther) && //
				third == thirdOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static <T1, T2> boolean argEquals(LBiObjCharTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBiObjCharTriple are allowed.
				if (!(two instanceof LBiObjCharTriple)) {
					return false;
				}

				LBiObjCharTriple other = (LBiObjCharTriple) two;

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

	interface ComparableBiObjCharTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends LBiObjCharTriple<T1, T2>, Comparable<LBiObjCharTriple<T1, T2>> {

		@Override
		default int compareTo(LBiObjCharTriple<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Character.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBiObjCharTriple<T1, T2> implements LBiObjCharTriple<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LBiObjCharTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBiObjCharTriple.argHashCode(first(), second(), third());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBiObjCharTriple<T1, T2> extends AbstractBiObjCharTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private char third;

		public MutBiObjCharTriple(T1 first, T2 second, char third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1, T2> MutBiObjCharTriple<T1, T2> of(T1 first, T2 second, char third) {
			return new MutBiObjCharTriple(first, second, third);
		}

		public static <T1, T2> MutBiObjCharTriple<T1, T2> copyOf(LBiObjCharTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutBiObjCharTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutBiObjCharTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public char third() {
			return third;
		}

		public MutBiObjCharTriple<T1, T2> third(char third) {
			this.third = third;
			return this;
		}

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void setThird(char third) {
			this.third = third;
		}

		public void reset() {
			first = null;
			second = null;
			third = '\u0000';
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBiObjCharTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjCharTriple<T1, T2> implements ComparableBiObjCharTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private char third;

		public MutCompBiObjCharTriple(T1 first, T2 second, char third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjCharTriple<T1, T2> of(T1 first, T2 second, char third) {
			return new MutCompBiObjCharTriple(first, second, third);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjCharTriple<T1, T2> copyOf(LBiObjCharTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutCompBiObjCharTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompBiObjCharTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public char third() {
			return third;
		}

		public MutCompBiObjCharTriple<T1, T2> third(char third) {
			this.third = third;
			return this;
		}

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void setThird(char third) {
			this.third = third;
		}

		public void reset() {
			first = null;
			second = null;
			third = '\u0000';
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBiObjCharTriple<T1, T2> extends AbstractBiObjCharTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final char third;

		public ImmBiObjCharTriple(T1 first, T2 second, char third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1, T2> ImmBiObjCharTriple<T1, T2> of(T1 first, T2 second, char third) {
			return new ImmBiObjCharTriple(first, second, third);
		}

		public static <T1, T2> ImmBiObjCharTriple<T1, T2> copyOf(LBiObjCharTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

		public char third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompBiObjCharTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjCharTriple<T1, T2> implements ComparableBiObjCharTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final char third;

		public ImmCompBiObjCharTriple(T1 first, T2 second, char third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjCharTriple<T1, T2> of(T1 first, T2 second, char third) {
			return new ImmCompBiObjCharTriple(first, second, third);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjCharTriple<T1, T2> copyOf(LBiObjCharTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

		public char third() {
			return third;
		}

	}

}
