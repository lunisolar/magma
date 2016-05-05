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

	int first();

	default int getFirst() {
		return first();
	}

	default Integer get(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	default int getInt(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LIntSingle and calculates hash from it. */
	static int argHashCode(int a1) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.hashCode(a1);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LIntSingle and checks if all values are equal. */
	static boolean argEquals(int a1, int b1) {
		return a1 == b1; //
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

	default Integer[] toVoArray(Integer[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

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

				return (retval = Integer.compare(one.first(), two.first())) != 0 ? retval : 0; //
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
			return LIntSingle.argHashCode(first());
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
	final class MutIntSingle extends AbstractIntSingle {

		private int first;

		public MutIntSingle(int a1) {
			this.first = a1;
		}

		public static MutIntSingle of(int a1) {
			return new MutIntSingle(a1);
		}

		public static MutIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.first());
		}

		public int first() {
			return first;
		}

		public MutIntSingle first(int first) {
			this.first = first;
			return this;
		}

		public void setFirst(int first) {
			this.first = first;
		}

		public void reset() {
			first = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompIntSingle extends AbstractIntSingle implements ComparableIntSingle {

		private int first;

		public MutCompIntSingle(int a1) {
			this.first = a1;
		}

		public static MutCompIntSingle of(int a1) {
			return new MutCompIntSingle(a1);
		}

		public static MutCompIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.first());
		}

		public int first() {
			return first;
		}

		public MutCompIntSingle first(int first) {
			this.first = first;
			return this;
		}

		public void setFirst(int first) {
			this.first = first;
		}

		public void reset() {
			first = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmIntSingle extends AbstractIntSingle {

		private final int first;

		public ImmIntSingle(int a1) {
			this.first = a1;
		}

		public static ImmIntSingle of(int a1) {
			return new ImmIntSingle(a1);
		}

		public static ImmIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.first());
		}

		public int first() {
			return first;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompIntSingle extends AbstractIntSingle implements ComparableIntSingle {

		private final int first;

		public ImmCompIntSingle(int a1) {
			this.first = a1;
		}

		public static ImmCompIntSingle of(int a1) {
			return new ImmCompIntSingle(a1);
		}

		public static ImmCompIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.first());
		}

		public int first() {
			return first;
		}

	}

}
