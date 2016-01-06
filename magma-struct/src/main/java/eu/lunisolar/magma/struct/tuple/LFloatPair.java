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
 * Exact equivalent of input parameters used in LBiFloatConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFloatPair extends LTuple<Float> {

	int SIZE = 2;

	float first();

	float second();

	default float getFirst() {
		return first();
	}

	default float getSecond() {
		return second();
	}

	default Float get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default float getFloat(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LFloatPair and calculates hash from it. */
	static int argHashCode(float first, float second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(first);
		result = prime * result + Float.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LFloatPair and checks if all values are equal. */
	static boolean argEquals(float first, float second, float firstOfOther, float secondOfOther) {
		return first == firstOfOther && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LFloatPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFloatPair are allowed.
				if (!(two instanceof LFloatPair)) {
					return false;
				}

				LFloatPair other = (LFloatPair) two;

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

	default Float[] toVoArray(Float[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default Float[] toVoArray(Float[] array) {
		return toVoArray(array, 0);
	}

	default Float[] toVoArray() {
		Float[] array = new Float[size()];

		return toVoArray(array);
	}

	default float[] toFloatArray(float[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default float[] toFloatArray(float[] array) {
		return toFloatArray(array, 0);
	}

	default float[] toFloatArray() {
		float[] array = new float[size()];

		return toFloatArray(array);
	}

	@Override
	default Iterator<Float> iterator() {
		return new Iterator<Float>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Float next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfDouble doubleIterator() {
		return new PrimitiveIterator.OfDouble() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public double nextDouble() {
				index++;
				return getFloat(index);
			}
		};
	}

	interface ComparableFloatPair extends LFloatPair, Comparable<LFloatPair> {

		@Override
		default int compareTo(LFloatPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Float.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Float.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractFloatPair implements LFloatPair {

		@Override
		public boolean equals(Object that) {
			return LFloatPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LFloatPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutFloatPair extends AbstractFloatPair {

		private float first;
		private float second;

		public MutFloatPair(float first, float second) {
			this.first = first;
			this.second = second;
		}

		public static MutFloatPair of(float first, float second) {
			return new MutFloatPair(first, second);
		}

		public static MutFloatPair copyOf(LFloatPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public MutFloatPair first(float first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutFloatPair second(float second) {
			this.second = second;
			return this;
		}

		public void setFirst(float first) {
			this.first = first;
		}

		public void setSecond(float second) {
			this.second = second;
		}

		public void reset() {
			first = 0f;
			second = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompFloatPair extends AbstractFloatPair implements ComparableFloatPair {

		private float first;
		private float second;

		public MutCompFloatPair(float first, float second) {
			this.first = first;
			this.second = second;
		}

		public static MutCompFloatPair of(float first, float second) {
			return new MutCompFloatPair(first, second);
		}

		public static MutCompFloatPair copyOf(LFloatPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public MutCompFloatPair first(float first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutCompFloatPair second(float second) {
			this.second = second;
			return this;
		}

		public void setFirst(float first) {
			this.first = first;
		}

		public void setSecond(float second) {
			this.second = second;
		}

		public void reset() {
			first = 0f;
			second = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmFloatPair extends AbstractFloatPair {

		private final float first;
		private final float second;

		public ImmFloatPair(float first, float second) {
			this.first = first;
			this.second = second;
		}

		public static ImmFloatPair of(float first, float second) {
			return new ImmFloatPair(first, second);
		}

		public static ImmFloatPair copyOf(LFloatPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public float second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompFloatPair extends AbstractFloatPair implements ComparableFloatPair {

		private final float first;
		private final float second;

		public ImmCompFloatPair(float first, float second) {
			this.first = first;
			this.second = second;
		}

		public static ImmCompFloatPair of(float first, float second) {
			return new ImmCompFloatPair(first, second);
		}

		public static ImmCompFloatPair copyOf(LFloatPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public float second() {
			return second;
		}

	}

}
