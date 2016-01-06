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
 * Exact equivalent of input parameters used in LBiIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LIntPair extends LTuple<Integer> {

	int SIZE = 2;

	int first();

	int second();

	default int getFirst() {
		return first();
	}

	default int getSecond() {
		return second();
	}

	default Integer get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default int getInt(int index) {
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
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LIntPair and calculates hash from it. */
	static int argHashCode(int first, int second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.hashCode(first);
		result = prime * result + Integer.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LIntPair and checks if all values are equal. */
	static boolean argEquals(int first, int second, int firstOfOther, int secondOfOther) {
		return first == firstOfOther && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LIntPair are allowed.
				if (!(two instanceof LIntPair)) {
					return false;
				}

				LIntPair other = (LIntPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default Object[] toArray(Object[] array) {
		return toArray(array, 0);
	}

	default Object[] toArray() {
		Object[] array = new Object[size()];

		return toArray(array);
	}

	default Integer[] toVoArray(Integer[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default Integer[] toVoArray(Integer[] array) {
		return toVoArray(array, 0);
	}

	default Integer[] toVoArray() {
		Integer[] array = new Integer[size()];

		return toVoArray(array);
	}

	default int[] toIntArray(int[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default int[] toIntArray(int[] array) {
		return toIntArray(array, 0);
	}

	default int[] toIntArray() {
		int[] array = new int[size()];

		return toIntArray(array);
	}

	@Override
	default Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Integer next() {
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
				return getInt(index);
			}
		};
	}

	interface ComparableIntPair extends LIntPair, Comparable<LIntPair> {

		@Override
		default int compareTo(LIntPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Integer.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractIntPair implements LIntPair {

		@Override
		public boolean equals(Object that) {
			return LIntPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LIntPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutIntPair extends AbstractIntPair {

		private int first;
		private int second;

		public MutIntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public static MutIntPair of(int first, int second) {
			return new MutIntPair(first, second);
		}

		public static MutIntPair copyOf(LIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public int first() {
			return first;
		}

		public MutIntPair first(int first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutIntPair second(int second) {
			this.second = second;
			return this;
		}

		public void setFirst(int first) {
			this.first = first;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		public void reset() {
			first = 0;
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompIntPair extends AbstractIntPair implements ComparableIntPair {

		private int first;
		private int second;

		public MutCompIntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public static MutCompIntPair of(int first, int second) {
			return new MutCompIntPair(first, second);
		}

		public static MutCompIntPair copyOf(LIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public int first() {
			return first;
		}

		public MutCompIntPair first(int first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompIntPair second(int second) {
			this.second = second;
			return this;
		}

		public void setFirst(int first) {
			this.first = first;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		public void reset() {
			first = 0;
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmIntPair extends AbstractIntPair {

		private final int first;
		private final int second;

		public ImmIntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public static ImmIntPair of(int first, int second) {
			return new ImmIntPair(first, second);
		}

		public static ImmIntPair copyOf(LIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public int first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompIntPair extends AbstractIntPair implements ComparableIntPair {

		private final int first;
		private final int second;

		public ImmCompIntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		public static ImmCompIntPair of(int first, int second) {
			return new ImmCompIntPair(first, second);
		}

		public static ImmCompIntPair copyOf(LIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public int first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
