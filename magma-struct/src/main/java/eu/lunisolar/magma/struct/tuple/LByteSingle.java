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

	default int size() {
		return SIZE;
	}

	static int hashCode(byte first) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Byte.hashCode(first);
		return result;
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
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LByteSingle are allowed.
					if (!(two instanceof LByteSingle)) {
						return false;
					}

					LByteSingle other = (LByteSingle) two;

					return one.first() == other.first(); //
				});
		}

		@Override
		public int hashCode() {
			return LByteSingle.hashCode(first());
		}

		@Override
		public byte byteValue() {
			return first();
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

		public MutByteSingle(byte first) {
			this.first = first;
		}

		public static MutByteSingle of(byte first) {
			return new MutByteSingle(first);
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

		public MutCompByteSingle(byte first) {
			this.first = first;
		}

		public static MutCompByteSingle of(byte first) {
			return new MutCompByteSingle(first);
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

		public ImmByteSingle(byte first) {
			this.first = first;
		}

		public static ImmByteSingle of(byte first) {
			return new ImmByteSingle(first);
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

		public ImmCompByteSingle(byte first) {
			this.first = first;
		}

		public static ImmCompByteSingle of(byte first) {
			return new ImmCompByteSingle(first);
		}

		public static ImmCompByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.first());
		}

		public byte first() {
			return first;
		}

	}

}
