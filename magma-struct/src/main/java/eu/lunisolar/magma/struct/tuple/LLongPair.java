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
 * Exact equivalent of input parameters used in LBiLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongPair extends LTuple<Long> {

	int SIZE = 2;

	long first();

	long second();

	default long getFirst() {
		return first();
	}

	default long getSecond() {
		return second();
	}

	default Long get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default long getLong(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LLongPair and calculates hash from it. */
	static int argHashCode(long first, long second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(first);
		result = prime * result + Long.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LLongPair and checks if all values are equal. */
	static boolean argEquals(long first, long second, long firstOfOther, long secondOfOther) {
		return first == firstOfOther && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LLongPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongPair are allowed.
				if (!(two instanceof LLongPair)) {
					return false;
				}

				LLongPair other = (LLongPair) two;

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

	default Long[] toVoArray(Long[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

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

		array[i] = first();
		i++;
		array[i] = second();

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

	interface ComparableLongPair extends LLongPair, Comparable<LLongPair> {

		@Override
		default int compareTo(LLongPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Long.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Long.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractLongPair implements LLongPair {

		@Override
		public boolean equals(Object that) {
			return LLongPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LLongPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutLongPair extends AbstractLongPair {

		private long first;
		private long second;

		public MutLongPair(long first, long second) {
			this.first = first;
			this.second = second;
		}

		public static MutLongPair of(long first, long second) {
			return new MutLongPair(first, second);
		}

		public static MutLongPair copyOf(LLongPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public MutLongPair first(long first) {
			this.first = first;
			return this;
		}

		public long second() {
			return second;
		}

		public MutLongPair second(long second) {
			this.second = second;
			return this;
		}

		public void setFirst(long first) {
			this.first = first;
		}

		public void setSecond(long second) {
			this.second = second;
		}

		public void reset() {
			first = 0L;
			second = 0L;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompLongPair extends AbstractLongPair implements ComparableLongPair {

		private long first;
		private long second;

		public MutCompLongPair(long first, long second) {
			this.first = first;
			this.second = second;
		}

		public static MutCompLongPair of(long first, long second) {
			return new MutCompLongPair(first, second);
		}

		public static MutCompLongPair copyOf(LLongPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public MutCompLongPair first(long first) {
			this.first = first;
			return this;
		}

		public long second() {
			return second;
		}

		public MutCompLongPair second(long second) {
			this.second = second;
			return this;
		}

		public void setFirst(long first) {
			this.first = first;
		}

		public void setSecond(long second) {
			this.second = second;
		}

		public void reset() {
			first = 0L;
			second = 0L;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmLongPair extends AbstractLongPair {

		private final long first;
		private final long second;

		public ImmLongPair(long first, long second) {
			this.first = first;
			this.second = second;
		}

		public static ImmLongPair of(long first, long second) {
			return new ImmLongPair(first, second);
		}

		public static ImmLongPair copyOf(LLongPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public long second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompLongPair extends AbstractLongPair implements ComparableLongPair {

		private final long first;
		private final long second;

		public ImmCompLongPair(long first, long second) {
			this.first = first;
			this.second = second;
		}

		public static ImmCompLongPair of(long first, long second) {
			return new ImmCompLongPair(first, second);
		}

		public static ImmCompLongPair copyOf(LLongPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public long second() {
			return second;
		}

	}

}
