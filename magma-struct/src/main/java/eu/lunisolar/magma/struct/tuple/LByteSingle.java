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

	byte first();

	default byte getFirst() {
		return first();
	}

	default Byte get(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	default byte getByte(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LByteSingle and calculates hash from it. */
	static int argHashCode(byte a1) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Byte.hashCode(a1);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LByteSingle and checks if all values are equal. */
	static boolean argEquals(byte a1, byte b1) {
		return a1 == b1; //
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

	default Byte[] toVoArray(Byte[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

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

		array[i] = first();

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

				return (retval = Byte.compare(one.first(), two.first())) != 0 ? retval : 0; //
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
			return LByteSingle.argHashCode(first());
		}

		@Override
		public byte byteValue() {
			return (byte) first();
		}

		@Override
		public short shortValue() {
			return (short) first();
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
	final class MutByteSingle extends AbstractByteSingle {

		private byte first;

		public MutByteSingle(byte a1) {
			this.first = a1;
		}

		public static MutByteSingle of(byte a1) {
			return new MutByteSingle(a1);
		}

		public static MutByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.first());
		}

		public byte first() {
			return first;
		}

		public MutByteSingle first(byte first) {
			this.first = first;
			return this;
		}

		public void setFirst(byte first) {
			this.first = first;
		}

		public void reset() {
			first = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompByteSingle extends AbstractByteSingle implements ComparableByteSingle {

		private byte first;

		public MutCompByteSingle(byte a1) {
			this.first = a1;
		}

		public static MutCompByteSingle of(byte a1) {
			return new MutCompByteSingle(a1);
		}

		public static MutCompByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.first());
		}

		public byte first() {
			return first;
		}

		public MutCompByteSingle first(byte first) {
			this.first = first;
			return this;
		}

		public void setFirst(byte first) {
			this.first = first;
		}

		public void reset() {
			first = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmByteSingle extends AbstractByteSingle {

		private final byte first;

		public ImmByteSingle(byte a1) {
			this.first = a1;
		}

		public static ImmByteSingle of(byte a1) {
			return new ImmByteSingle(a1);
		}

		public static ImmByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.first());
		}

		public byte first() {
			return first;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompByteSingle extends AbstractByteSingle implements ComparableByteSingle {

		private final byte first;

		public ImmCompByteSingle(byte a1) {
			this.first = a1;
		}

		public static ImmCompByteSingle of(byte a1) {
			return new ImmCompByteSingle(a1);
		}

		public static ImmCompByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.first());
		}

		public byte first() {
			return first;
		}

	}

}
