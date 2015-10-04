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

	default int size() {
		return SIZE;
	}

	static int hashCode(float first) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(first);
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
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LFloatSingle are allowed.
					if (!(two instanceof LFloatSingle)) {
						return false;
					}

					LFloatSingle other = (LFloatSingle) two;

					return one.first() == other.first(); //
				});
		}

		@Override
		public int hashCode() {
			return LFloatSingle.hashCode(first());
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
			return first();
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

		public MutFloatSingle(float first) {
			this.first = first;
		}

		public static MutFloatSingle of(float first) {
			return new MutFloatSingle(first);
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

		public MutCompFloatSingle(float first) {
			this.first = first;
		}

		public static MutCompFloatSingle of(float first) {
			return new MutCompFloatSingle(first);
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

		public ImmFloatSingle(float first) {
			this.first = first;
		}

		public static ImmFloatSingle of(float first) {
			return new ImmFloatSingle(first);
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

		public ImmCompFloatSingle(float first) {
			this.first = first;
		}

		public static ImmCompFloatSingle of(float first) {
			return new ImmCompFloatSingle(first);
		}

		public static ImmCompFloatSingle copyOf(LFloatSingle tuple) {
			return of(tuple.first());
		}

		public float first() {
			return first;
		}

	}

}
