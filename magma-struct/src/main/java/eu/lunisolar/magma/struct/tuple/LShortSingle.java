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
 * Exact equivalent of input parameters used in LShortConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LShortSingle extends LTuple<Short> {

	int SIZE = 1;

	short first();

	default short getFirst() {
		return first();
	}

	default Short get(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	default short getShort(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LShortSingle and calculates hash from it. */
	static int argHashCode(short first) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Short.hashCode(first);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LShortSingle and checks if all values are equal. */
	static boolean argEquals(short first, short firstOfOther) {
		return first == firstOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LShortSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LShortSingle are allowed.
				if (!(two instanceof LShortSingle)) {
					return false;
				}

				LShortSingle other = (LShortSingle) two;

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

	default Short[] toVoArray(Short[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

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

	interface ComparableShortSingle extends LShortSingle, Comparable<LShortSingle> {

		@Override
		default int compareTo(LShortSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Short.compare(one.first(), two.first())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractShortSingle extends Number implements LShortSingle {

		@Override
		public boolean equals(Object that) {
			return LShortSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LShortSingle.argHashCode(first());
		}

		@Override
		public byte byteValue() {
			return (byte) first();
		}

		@Override
		public short shortValue() {
			return first();
		}

		@Override
		public int intValue() {
			return (int) first();
		}

		@Override
		public long longValue() {
			return (long) first();
		}

		@Override
		public float floatValue() {
			return (float) first();
		}

		@Override
		public double doubleValue() {
			return (double) first();
		}
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutShortSingle extends AbstractShortSingle {

		private short first;

		public MutShortSingle(short first) {
			this.first = first;
		}

		public static MutShortSingle of(short first) {
			return new MutShortSingle(first);
		}

		public static MutShortSingle copyOf(LShortSingle tuple) {
			return of(tuple.first());
		}

		public short first() {
			return first;
		}

		public MutShortSingle first(short first) {
			this.first = first;
			return this;
		}

		public void setFirst(short first) {
			this.first = first;
		}

		public void reset() {
			first = (short) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompShortSingle extends AbstractShortSingle implements ComparableShortSingle {

		private short first;

		public MutCompShortSingle(short first) {
			this.first = first;
		}

		public static MutCompShortSingle of(short first) {
			return new MutCompShortSingle(first);
		}

		public static MutCompShortSingle copyOf(LShortSingle tuple) {
			return of(tuple.first());
		}

		public short first() {
			return first;
		}

		public MutCompShortSingle first(short first) {
			this.first = first;
			return this;
		}

		public void setFirst(short first) {
			this.first = first;
		}

		public void reset() {
			first = (short) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmShortSingle extends AbstractShortSingle {

		private final short first;

		public ImmShortSingle(short first) {
			this.first = first;
		}

		public static ImmShortSingle of(short first) {
			return new ImmShortSingle(first);
		}

		public static ImmShortSingle copyOf(LShortSingle tuple) {
			return of(tuple.first());
		}

		public short first() {
			return first;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompShortSingle extends AbstractShortSingle implements ComparableShortSingle {

		private final short first;

		public ImmCompShortSingle(short first) {
			this.first = first;
		}

		public static ImmCompShortSingle of(short first) {
			return new ImmCompShortSingle(first);
		}

		public static ImmCompShortSingle copyOf(LShortSingle tuple) {
			return of(tuple.first());
		}

		public short first() {
			return first;
		}

	}

}
