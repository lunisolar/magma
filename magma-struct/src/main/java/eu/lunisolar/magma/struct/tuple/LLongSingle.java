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
 * Exact equivalent of input parameters used in LLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongSingle extends LTuple<Long> {

	int SIZE = 1;

	long value();

	default long getValue() {
		return value();
	}

	default Long get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default long getLong(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LLongSingle and calculates hash from it. */
	static int argHashCode(long a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LLongSingle and checks if all values are equal. */
	static boolean argEquals(long a, long b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LLongSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongSingle are allowed.
				if (!(two instanceof LLongSingle)) {
					return false;
				}

				LLongSingle other = (LLongSingle) two;

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

	default Long[] toVoArray(Long[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default Long[] toVoArray(Long[] array) {
		return toVoArray(array, 0);
	}

	default Long[] toVoArray() {
		Long[] array = new Long[size()];

		return toVoArray(array);
	}

	default long[] toLongArray(long[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default long[] toLongArray(long[] array) {
		return toLongArray(array, 0);
	}

	default long[] toLongArray() {
		long[] array = new long[size()];

		return toLongArray(array);
	}

	@Override
	default Iterator<Long> iterator() {
		return new Iterator<Long>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Long next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfLong longIterator() {
		return new PrimitiveIterator.OfLong() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public long nextLong() {
				index++;
				return getLong(index);
			}
		};
	}

	interface ComparableLongSingle extends LLongSingle, Comparable<LLongSingle> {

		@Override
		default int compareTo(LLongSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Long.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractLongSingle extends Number implements LLongSingle {

		@Override
		public boolean equals(Object that) {
			return LLongSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LLongSingle.argHashCode(value());
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
	final class MutLongSingle extends AbstractLongSingle {

		private long value;

		public MutLongSingle(long a) {
			this.value = a;
		}

		public static MutLongSingle of(long a) {
			return new MutLongSingle(a);
		}

		public static MutLongSingle copyOf(LLongSingle tuple) {
			return of(tuple.value());
		}

		public long value() {
			return value;
		}

		public MutLongSingle value(long value) {
			this.value = value;
			return this;
		}

		public void setValue(long value) {
			this.value = value;
		}

		public void reset() {
			value = 0L;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompLongSingle extends AbstractLongSingle implements ComparableLongSingle {

		private long value;

		public MutCompLongSingle(long a) {
			this.value = a;
		}

		public static MutCompLongSingle of(long a) {
			return new MutCompLongSingle(a);
		}

		public static MutCompLongSingle copyOf(LLongSingle tuple) {
			return of(tuple.value());
		}

		public long value() {
			return value;
		}

		public MutCompLongSingle value(long value) {
			this.value = value;
			return this;
		}

		public void setValue(long value) {
			this.value = value;
		}

		public void reset() {
			value = 0L;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmLongSingle extends AbstractLongSingle {

		private final long value;

		public ImmLongSingle(long a) {
			this.value = a;
		}

		public static ImmLongSingle of(long a) {
			return new ImmLongSingle(a);
		}

		public static ImmLongSingle copyOf(LLongSingle tuple) {
			return of(tuple.value());
		}

		public long value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompLongSingle extends AbstractLongSingle implements ComparableLongSingle {

		private final long value;

		public ImmCompLongSingle(long a) {
			this.value = a;
		}

		public static ImmCompLongSingle of(long a) {
			return new ImmCompLongSingle(a);
		}

		public static ImmCompLongSingle copyOf(LLongSingle tuple) {
			return of(tuple.value());
		}

		public long value() {
			return value;
		}

	}

}
