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

	default int size() {
		return SIZE;
	}

	static int hashCode(int first) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.hashCode(first);
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
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LIntSingle are allowed.
					if (!(two instanceof LIntSingle)) {
						return false;
					}

					LIntSingle other = (LIntSingle) two;

					return one.first() == other.first(); //
				});
		}

		@Override
		public int hashCode() {
			return LIntSingle.hashCode(first());
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
			return first();
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

		public MutIntSingle(int first) {
			this.first = first;
		}

		public static MutIntSingle of(int first) {
			return new MutIntSingle(first);
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

		public MutCompIntSingle(int first) {
			this.first = first;
		}

		public static MutCompIntSingle of(int first) {
			return new MutCompIntSingle(first);
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

		public ImmIntSingle(int first) {
			this.first = first;
		}

		public static ImmIntSingle of(int first) {
			return new ImmIntSingle(first);
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

		public ImmCompIntSingle(int first) {
			this.first = first;
		}

		public static ImmCompIntSingle of(int first) {
			return new ImmCompIntSingle(first);
		}

		public static ImmCompIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.first());
		}

		public int first() {
			return first;
		}

	}

}
