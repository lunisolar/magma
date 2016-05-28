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
 * Exact equivalent of input parameters used in LByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LByteSingle extends LTuple<Byte> {

	int SIZE = 1;

	byte value();

	default byte getValue() {
		return value();
	}

	default Byte get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default byte getByte(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LByteSingle and calculates hash from it. */
	static int argHashCode(byte a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Byte.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LByteSingle and checks if all values are equal. */
	static boolean argEquals(byte a, byte b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LByteSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LByteSingle are allowed.
				if (!(two instanceof LByteSingle)) {
					return false;
				}

				LByteSingle other = (LByteSingle) two;

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

	default Byte[] toVoArray(Byte[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default Byte[] toVoArray(Byte[] array) {
		return toVoArray(array, 0);
	}

	default Byte[] toVoArray() {
		Byte[] array = new Byte[size()];

		return toVoArray(array);
	}

	default byte[] toByteArray(byte[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default byte[] toByteArray(byte[] array) {
		return toByteArray(array, 0);
	}

	default byte[] toByteArray() {
		byte[] array = new byte[size()];

		return toByteArray(array);
	}

	@Override
	default Iterator<Byte> iterator() {
		return new Iterator<Byte>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Byte next() {
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
				return getByte(index);
			}
		};
	}

	interface ComparableByteSingle extends LByteSingle, Comparable<LByteSingle> {

		@Override
		default int compareTo(LByteSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Byte.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractByteSingle extends Number implements LByteSingle {

		@Override
		public boolean equals(Object that) {
			return LByteSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LByteSingle.argHashCode(value());
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
	final class MutByteSingle extends AbstractByteSingle {

		private byte value;

		public MutByteSingle(byte a) {
			this.value = a;
		}

		public static MutByteSingle of(byte a) {
			return new MutByteSingle(a);
		}

		public static MutByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.value());
		}

		public byte value() {
			return value;
		}

		public MutByteSingle value(byte value) {
			this.value = value;
			return this;
		}

		public void setValue(byte value) {
			this.value = value;
		}

		public void reset() {
			value = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompByteSingle extends AbstractByteSingle implements ComparableByteSingle {

		private byte value;

		public MutCompByteSingle(byte a) {
			this.value = a;
		}

		public static MutCompByteSingle of(byte a) {
			return new MutCompByteSingle(a);
		}

		public static MutCompByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.value());
		}

		public byte value() {
			return value;
		}

		public MutCompByteSingle value(byte value) {
			this.value = value;
			return this;
		}

		public void setValue(byte value) {
			this.value = value;
		}

		public void reset() {
			value = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmByteSingle extends AbstractByteSingle {

		private final byte value;

		public ImmByteSingle(byte a) {
			this.value = a;
		}

		public static ImmByteSingle of(byte a) {
			return new ImmByteSingle(a);
		}

		public static ImmByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.value());
		}

		public byte value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompByteSingle extends AbstractByteSingle implements ComparableByteSingle {

		private final byte value;

		public ImmCompByteSingle(byte a) {
			this.value = a;
		}

		public static ImmCompByteSingle of(byte a) {
			return new ImmCompByteSingle(a);
		}

		public static ImmCompByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.value());
		}

		public byte value() {
			return value;
		}

	}

}
