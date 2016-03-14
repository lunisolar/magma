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
 * Exact equivalent of input parameters used in LBiByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBytePair extends LTuple<Byte> {

	int SIZE = 2;

	byte first();

	byte second();

	default byte getFirst() {
		return first();
	}

	default byte getSecond() {
		return second();
	}

	default Byte get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default byte getByte(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBytePair and calculates hash from it. */
	static int argHashCode(byte a1, byte a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Byte.hashCode(a1);
		result = prime * result + Byte.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBytePair and checks if all values are equal. */
	static boolean argEquals(byte a1, byte a2, byte b1, byte b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LBytePair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBytePair are allowed.
				if (!(two instanceof LBytePair)) {
					return false;
				}

				LBytePair other = (LBytePair) two;

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

	default Byte[] toVoArray(Byte[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

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
		i++;
		array[i] = second();

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

	interface ComparableBytePair extends LBytePair, Comparable<LBytePair> {

		@Override
		default int compareTo(LBytePair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Byte.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Byte.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBytePair implements LBytePair {

		@Override
		public boolean equals(Object that) {
			return LBytePair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBytePair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBytePair extends AbstractBytePair {

		private byte first;
		private byte second;

		public MutBytePair(byte a1, byte a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutBytePair of(byte a1, byte a2) {
			return new MutBytePair(a1, a2);
		}

		public static MutBytePair copyOf(LBytePair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public byte first() {
			return first;
		}

		public MutBytePair first(byte first) {
			this.first = first;
			return this;
		}

		public byte second() {
			return second;
		}

		public MutBytePair second(byte second) {
			this.second = second;
			return this;
		}

		public void setFirst(byte first) {
			this.first = first;
		}

		public void setSecond(byte second) {
			this.second = second;
		}

		public void reset() {
			first = (byte) 0;
			second = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBytePair extends AbstractBytePair implements ComparableBytePair {

		private byte first;
		private byte second;

		public MutCompBytePair(byte a1, byte a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompBytePair of(byte a1, byte a2) {
			return new MutCompBytePair(a1, a2);
		}

		public static MutCompBytePair copyOf(LBytePair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public byte first() {
			return first;
		}

		public MutCompBytePair first(byte first) {
			this.first = first;
			return this;
		}

		public byte second() {
			return second;
		}

		public MutCompBytePair second(byte second) {
			this.second = second;
			return this;
		}

		public void setFirst(byte first) {
			this.first = first;
		}

		public void setSecond(byte second) {
			this.second = second;
		}

		public void reset() {
			first = (byte) 0;
			second = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBytePair extends AbstractBytePair {

		private final byte first;
		private final byte second;

		public ImmBytePair(byte a1, byte a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmBytePair of(byte a1, byte a2) {
			return new ImmBytePair(a1, a2);
		}

		public static ImmBytePair copyOf(LBytePair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public byte first() {
			return first;
		}

		public byte second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompBytePair extends AbstractBytePair implements ComparableBytePair {

		private final byte first;
		private final byte second;

		public ImmCompBytePair(byte a1, byte a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompBytePair of(byte a1, byte a2) {
			return new ImmCompBytePair(a1, a2);
		}

		public static ImmCompBytePair copyOf(LBytePair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public byte first() {
			return first;
		}

		public byte second() {
			return second;
		}

	}

}
