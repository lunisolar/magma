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
 * Exact equivalent of input parameters used in LBiShortConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LShortPair extends LTuple<Short> {

	int SIZE = 2;

	short first();

	short second();

	default short getFirst() {
		return first();
	}

	default short getSecond() {
		return second();
	}

	default Short get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default short getShort(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LShortPair and calculates hash from it. */
	static int argHashCode(short first, short second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Short.hashCode(first);
		result = prime * result + Short.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LShortPair and checks if all values are equal. */
	static boolean argEquals(short first, short second, short firstOfOther, short secondOfOther) {
		return first == firstOfOther && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LShortPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LShortPair are allowed.
				if (!(two instanceof LShortPair)) {
					return false;
				}

				LShortPair other = (LShortPair) two;

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

	default Short[] toVoArray(Short[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default Short[] toVoArray(Short[] array) {
		return toVoArray(array, 0);
	}

	default Short[] toVoArray() {
		Short[] array = new Short[size()];

		return toVoArray(array);
	}

	default short[] toShortArray(short[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default short[] toShortArray(short[] array) {
		return toShortArray(array, 0);
	}

	default short[] toShortArray() {
		short[] array = new short[size()];

		return toShortArray(array);
	}

	@Override
	default Iterator<Short> iterator() {
		return new Iterator<Short>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Short next() {
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
				return getShort(index);
			}
		};
	}

	interface ComparableShortPair extends LShortPair, Comparable<LShortPair> {

		@Override
		default int compareTo(LShortPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Short.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Short.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractShortPair implements LShortPair {

		@Override
		public boolean equals(Object that) {
			return LShortPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LShortPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutShortPair extends AbstractShortPair {

		private short first;
		private short second;

		public MutShortPair(short first, short second) {
			this.first = first;
			this.second = second;
		}

		public static MutShortPair of(short first, short second) {
			return new MutShortPair(first, second);
		}

		public static MutShortPair copyOf(LShortPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public MutShortPair first(short first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutShortPair second(short second) {
			this.second = second;
			return this;
		}

		public void setFirst(short first) {
			this.first = first;
		}

		public void setSecond(short second) {
			this.second = second;
		}

		public void reset() {
			first = (short) 0;
			second = (short) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompShortPair extends AbstractShortPair implements ComparableShortPair {

		private short first;
		private short second;

		public MutCompShortPair(short first, short second) {
			this.first = first;
			this.second = second;
		}

		public static MutCompShortPair of(short first, short second) {
			return new MutCompShortPair(first, second);
		}

		public static MutCompShortPair copyOf(LShortPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public MutCompShortPair first(short first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutCompShortPair second(short second) {
			this.second = second;
			return this;
		}

		public void setFirst(short first) {
			this.first = first;
		}

		public void setSecond(short second) {
			this.second = second;
		}

		public void reset() {
			first = (short) 0;
			second = (short) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmShortPair extends AbstractShortPair {

		private final short first;
		private final short second;

		public ImmShortPair(short first, short second) {
			this.first = first;
			this.second = second;
		}

		public static ImmShortPair of(short first, short second) {
			return new ImmShortPair(first, second);
		}

		public static ImmShortPair copyOf(LShortPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public short second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompShortPair extends AbstractShortPair implements ComparableShortPair {

		private final short first;
		private final short second;

		public ImmCompShortPair(short first, short second) {
			this.first = first;
			this.second = second;
		}

		public static ImmCompShortPair of(short first, short second) {
			return new ImmCompShortPair(first, second);
		}

		public static ImmCompShortPair copyOf(LShortPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public short second() {
			return second;
		}

	}

}
