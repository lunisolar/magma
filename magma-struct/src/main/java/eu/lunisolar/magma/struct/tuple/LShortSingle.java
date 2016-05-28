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
 * Exact equivalent of input parameters used in LShortConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LShortSingle extends LTuple<Short> {

	int SIZE = 1;

	short value();

	default short getValue() {
		return value();
	}

	default Short get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default short getShort(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LShortSingle and calculates hash from it. */
	static int argHashCode(short a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Short.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LShortSingle and checks if all values are equal. */
	static boolean argEquals(short a, short b) {
		return a == b; //
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

				return argEquals(one.value(), other.value());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

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

		array[i] = value();

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

		array[i] = value();

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

				return (retval = Short.compare(one.value(), two.value())) != 0 ? retval : 0; //
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
			return LShortSingle.argHashCode(value());
		}

		@Override
		public byte byteValue() {
			return (byte) value();
		}

		@Override
		public short shortValue() {
			return (short) value();
		}

		@Override
		public int intValue() {
			return (int) value();
		}

		@Override
		public long longValue() {
			return (long) value();
		}

		@Override
		public float floatValue() {
			return (float) value();
		}

		@Override
		public double doubleValue() {
			return (double) value();
		}
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutShortSingle extends AbstractShortSingle {

		private short value;

		public MutShortSingle(short a) {
			this.value = a;
		}

		public static MutShortSingle of(short a) {
			return new MutShortSingle(a);
		}

		public static MutShortSingle copyOf(LShortSingle tuple) {
			return of(tuple.value());
		}

		public short value() {
			return value;
		}

		public MutShortSingle value(short value) {
			this.value = value;
			return this;
		}

		public void setValue(short value) {
			this.value = value;
		}

		public void reset() {
			value = (short) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompShortSingle extends AbstractShortSingle implements ComparableShortSingle {

		private short value;

		public MutCompShortSingle(short a) {
			this.value = a;
		}

		public static MutCompShortSingle of(short a) {
			return new MutCompShortSingle(a);
		}

		public static MutCompShortSingle copyOf(LShortSingle tuple) {
			return of(tuple.value());
		}

		public short value() {
			return value;
		}

		public MutCompShortSingle value(short value) {
			this.value = value;
			return this;
		}

		public void setValue(short value) {
			this.value = value;
		}

		public void reset() {
			value = (short) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmShortSingle extends AbstractShortSingle {

		private final short value;

		public ImmShortSingle(short a) {
			this.value = a;
		}

		public static ImmShortSingle of(short a) {
			return new ImmShortSingle(a);
		}

		public static ImmShortSingle copyOf(LShortSingle tuple) {
			return of(tuple.value());
		}

		public short value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompShortSingle extends AbstractShortSingle implements ComparableShortSingle {

		private final short value;

		public ImmCompShortSingle(short a) {
			this.value = a;
		}

		public static ImmCompShortSingle of(short a) {
			return new ImmCompShortSingle(a);
		}

		public static ImmCompShortSingle copyOf(LShortSingle tuple) {
			return of(tuple.value());
		}

		public short value() {
			return value;
		}

	}

}
