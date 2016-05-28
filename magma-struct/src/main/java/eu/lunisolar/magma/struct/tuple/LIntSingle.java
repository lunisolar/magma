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
 * Exact equivalent of input parameters used in LIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LIntSingle extends LTuple<Integer> {

	int SIZE = 1;

	int value();

	default int getValue() {
		return value();
	}

	default Integer get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default int getInt(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LIntSingle and calculates hash from it. */
	static int argHashCode(int a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LIntSingle and checks if all values are equal. */
	static boolean argEquals(int a, int b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LIntSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LIntSingle are allowed.
				if (!(two instanceof LIntSingle)) {
					return false;
				}

				LIntSingle other = (LIntSingle) two;

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

	default Integer[] toVoArray(Integer[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

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

		array[i] = value();

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

	interface ComparableIntSingle extends LIntSingle, Comparable<LIntSingle> {

		@Override
		default int compareTo(LIntSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Integer.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractIntSingle extends Number implements LIntSingle {

		@Override
		public boolean equals(Object that) {
			return LIntSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LIntSingle.argHashCode(value());
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
	final class MutIntSingle extends AbstractIntSingle {

		private int value;

		public MutIntSingle(int a) {
			this.value = a;
		}

		public static MutIntSingle of(int a) {
			return new MutIntSingle(a);
		}

		public static MutIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.value());
		}

		public int value() {
			return value;
		}

		public MutIntSingle value(int value) {
			this.value = value;
			return this;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public void reset() {
			value = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompIntSingle extends AbstractIntSingle implements ComparableIntSingle {

		private int value;

		public MutCompIntSingle(int a) {
			this.value = a;
		}

		public static MutCompIntSingle of(int a) {
			return new MutCompIntSingle(a);
		}

		public static MutCompIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.value());
		}

		public int value() {
			return value;
		}

		public MutCompIntSingle value(int value) {
			this.value = value;
			return this;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public void reset() {
			value = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmIntSingle extends AbstractIntSingle {

		private final int value;

		public ImmIntSingle(int a) {
			this.value = a;
		}

		public static ImmIntSingle of(int a) {
			return new ImmIntSingle(a);
		}

		public static ImmIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.value());
		}

		public int value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompIntSingle extends AbstractIntSingle implements ComparableIntSingle {

		private final int value;

		public ImmCompIntSingle(int a) {
			this.value = a;
		}

		public static ImmCompIntSingle of(int a) {
			return new ImmCompIntSingle(a);
		}

		public static ImmCompIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.value());
		}

		public int value() {
			return value;
		}

	}

}
