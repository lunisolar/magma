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
 * Exact equivalent of input parameters used in LCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharSingle extends LTuple<Character> {

	int SIZE = 1;

	char first();

	default char getFirst() {
		return first();
	}

	default Character get(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	default char getChar(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LCharSingle and calculates hash from it. */
	static int argHashCode(char a1) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Character.hashCode(a1);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LCharSingle and checks if all values are equal. */
	static boolean argEquals(char a1, char b1) {
		return a1 == b1; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LCharSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharSingle are allowed.
				if (!(two instanceof LCharSingle)) {
					return false;
				}

				LCharSingle other = (LCharSingle) two;

				return argEquals(one.first(), other.first());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

		return array;
	}

	default Object[] toArray(Object[] array) {
		return toArray(array, 0);
	}

	default Object[] toArray() {
		Object[] array = new Object[size()];

		return toArray(array);
	}

	default Character[] toVoArray(Character[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

		return array;
	}

	default Character[] toVoArray(Character[] array) {
		return toVoArray(array, 0);
	}

	default Character[] toVoArray() {
		Character[] array = new Character[size()];

		return toVoArray(array);
	}

	default char[] toCharacterArray(char[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

		return array;
	}

	default char[] toCharacterArray(char[] array) {
		return toCharacterArray(array, 0);
	}

	default char[] toCharacterArray() {
		char[] array = new char[size()];

		return toCharacterArray(array);
	}

	@Override
	default Iterator<Character> iterator() {
		return new Iterator<Character>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Character next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfInt intIterator() {
		return new PrimitiveIterator.OfInt() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public int nextInt() {
				index++;
				return getChar(index);
			}
		};
	}

	interface ComparableCharSingle extends LCharSingle, Comparable<LCharSingle> {

		@Override
		default int compareTo(LCharSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Character.compare(one.first(), two.first())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractCharSingle implements LCharSingle {

		@Override
		public boolean equals(Object that) {
			return LCharSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LCharSingle.argHashCode(first());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutCharSingle extends AbstractCharSingle {

		private char first;

		public MutCharSingle(char a1) {
			this.first = a1;
		}

		public static MutCharSingle of(char a1) {
			return new MutCharSingle(a1);
		}

		public static MutCharSingle copyOf(LCharSingle tuple) {
			return of(tuple.first());
		}

		public char first() {
			return first;
		}

		public MutCharSingle first(char first) {
			this.first = first;
			return this;
		}

		public void setFirst(char first) {
			this.first = first;
		}

		public void reset() {
			first = '\u0000';
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompCharSingle extends AbstractCharSingle implements ComparableCharSingle {

		private char first;

		public MutCompCharSingle(char a1) {
			this.first = a1;
		}

		public static MutCompCharSingle of(char a1) {
			return new MutCompCharSingle(a1);
		}

		public static MutCompCharSingle copyOf(LCharSingle tuple) {
			return of(tuple.first());
		}

		public char first() {
			return first;
		}

		public MutCompCharSingle first(char first) {
			this.first = first;
			return this;
		}

		public void setFirst(char first) {
			this.first = first;
		}

		public void reset() {
			first = '\u0000';
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmCharSingle extends AbstractCharSingle {

		private final char first;

		public ImmCharSingle(char a1) {
			this.first = a1;
		}

		public static ImmCharSingle of(char a1) {
			return new ImmCharSingle(a1);
		}

		public static ImmCharSingle copyOf(LCharSingle tuple) {
			return of(tuple.first());
		}

		public char first() {
			return first;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompCharSingle extends AbstractCharSingle implements ComparableCharSingle {

		private final char first;

		public ImmCompCharSingle(char a1) {
			this.first = a1;
		}

		public static ImmCompCharSingle of(char a1) {
			return new ImmCompCharSingle(a1);
		}

		public static ImmCompCharSingle copyOf(LCharSingle tuple) {
			return of(tuple.first());
		}

		public char first() {
			return first;
		}

	}

}
