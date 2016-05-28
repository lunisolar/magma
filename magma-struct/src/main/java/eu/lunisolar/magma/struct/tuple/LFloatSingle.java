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

	float value();

	default float getValue() {
		return value();
	}

	default Float get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default float getFloat(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LFloatSingle and calculates hash from it. */
	static int argHashCode(float a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LFloatSingle and checks if all values are equal. */
	static boolean argEquals(float a, float b) {
		return a == b; //
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

	default Float[] toVoArray(Float[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

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

		array[i] = value();

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

				return (retval = Float.compare(one.value(), two.value())) != 0 ? retval : 0; //
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
			return LFloatSingle.argHashCode(value());
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
	final class MutFloatSingle extends AbstractFloatSingle {

		private float value;

		public MutFloatSingle(float a) {
			this.value = a;
		}

		public static MutFloatSingle of(float a) {
			return new MutFloatSingle(a);
		}

		public static MutFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.value());
		}

		public float value() {
			return value;
		}

		public MutFloatSingle value(float value) {
			this.value = value;
			return this;
		}

		public void setValue(float value) {
			this.value = value;
		}

		public void reset() {
			value = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompFloatSingle extends AbstractFloatSingle implements ComparableFloatSingle {

		private float value;

		public MutCompFloatSingle(float a) {
			this.value = a;
		}

		public static MutCompFloatSingle of(float a) {
			return new MutCompFloatSingle(a);
		}

		public static MutCompFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.value());
		}

		public float value() {
			return value;
		}

		public MutCompFloatSingle value(float value) {
			this.value = value;
			return this;
		}

		public void setValue(float value) {
			this.value = value;
		}

		public void reset() {
			value = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmFloatSingle extends AbstractFloatSingle {

		private final float value;

		public ImmFloatSingle(float a) {
			this.value = a;
		}

		public static ImmFloatSingle of(float a) {
			return new ImmFloatSingle(a);
		}

		public static ImmFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.value());
		}

		public float value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompFloatSingle extends AbstractFloatSingle implements ComparableFloatSingle {

		private final float value;

		public ImmCompFloatSingle(float a) {
			this.value = a;
		}

		public static ImmCompFloatSingle of(float a) {
			return new ImmCompFloatSingle(a);
		}

		public static ImmCompFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.value());
		}

		public float value() {
			return value;
		}

	}

}
