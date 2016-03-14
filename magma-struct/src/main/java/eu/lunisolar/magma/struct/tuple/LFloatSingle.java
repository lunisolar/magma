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
 * Exact equivalent of input parameters used in LFloatConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFloatSingle extends LTuple<Float> {

	int SIZE = 1;

	float first();

	default float getFirst() {
		return first();
	}

	default Float get(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	default float getFloat(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LFloatSingle and calculates hash from it. */
	static int argHashCode(float a1) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(a1);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LFloatSingle and checks if all values are equal. */
	static boolean argEquals(float a1, float b1) {
		return a1 == b1; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LFloatSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFloatSingle are allowed.
				if (!(two instanceof LFloatSingle)) {
					return false;
				}

				LFloatSingle other = (LFloatSingle) two;

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

	default Float[] toVoArray(Float[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

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

	interface ComparableFloatSingle extends LFloatSingle, Comparable<LFloatSingle> {

		@Override
		default int compareTo(LFloatSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Float.compare(one.first(), two.first())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractFloatSingle extends Number implements LFloatSingle {

		@Override
		public boolean equals(Object that) {
			return LFloatSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LFloatSingle.argHashCode(first());
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
	final class MutFloatSingle extends AbstractFloatSingle {

		private float first;

		public MutFloatSingle(float a1) {
			this.first = a1;
		}

		public static MutFloatSingle of(float a1) {
			return new MutFloatSingle(a1);
		}

		public static MutFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.first());
		}

		public float first() {
			return first;
		}

		public MutFloatSingle first(float first) {
			this.first = first;
			return this;
		}

		public void setFirst(float first) {
			this.first = first;
		}

		public void reset() {
			first = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompFloatSingle extends AbstractFloatSingle implements ComparableFloatSingle {

		private float first;

		public MutCompFloatSingle(float a1) {
			this.first = a1;
		}

		public static MutCompFloatSingle of(float a1) {
			return new MutCompFloatSingle(a1);
		}

		public static MutCompFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.first());
		}

		public float first() {
			return first;
		}

		public MutCompFloatSingle first(float first) {
			this.first = first;
			return this;
		}

		public void setFirst(float first) {
			this.first = first;
		}

		public void reset() {
			first = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmFloatSingle extends AbstractFloatSingle {

		private final float first;

		public ImmFloatSingle(float a1) {
			this.first = a1;
		}

		public static ImmFloatSingle of(float a1) {
			return new ImmFloatSingle(a1);
		}

		public static ImmFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.first());
		}

		public float first() {
			return first;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompFloatSingle extends AbstractFloatSingle implements ComparableFloatSingle {

		private final float first;

		public ImmCompFloatSingle(float a1) {
			this.first = a1;
		}

		public static ImmCompFloatSingle of(float a1) {
			return new ImmCompFloatSingle(a1);
		}

		public static ImmCompFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.first());
		}

		public float first() {
			return first;
		}

	}

}
