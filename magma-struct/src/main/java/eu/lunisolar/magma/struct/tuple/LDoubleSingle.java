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
 * Exact equivalent of input parameters used in LDoubleConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleSingle extends LTuple<Double> {

	int SIZE = 1;

	double first();

	default double getFirst() {
		return first();
	}

	default Double get(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	default double getDouble(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LDoubleSingle and calculates hash from it. */
	static int argHashCode(double first) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(first);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LDoubleSingle and checks if all values are equal. */
	static boolean argEquals(double first, double firstOfOther) {
		return first == firstOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LDoubleSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDoubleSingle are allowed.
				if (!(two instanceof LDoubleSingle)) {
					return false;
				}

				LDoubleSingle other = (LDoubleSingle) two;

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

	default Double[] toVoArray(Double[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

		return array;
	}

	default Double[] toVoArray(Double[] array) {
		return toVoArray(array, 0);
	}

	default Double[] toVoArray() {
		Double[] array = new Double[size()];

		return toVoArray(array);
	}

	default double[] toDoubleArray(double[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

		return array;
	}

	default double[] toDoubleArray(double[] array) {
		return toDoubleArray(array, 0);
	}

	default double[] toDoubleArray() {
		double[] array = new double[size()];

		return toDoubleArray(array);
	}

	@Override
	default Iterator<Double> iterator() {
		return new Iterator<Double>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Double next() {
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
				return getDouble(index);
			}
		};
	}

	interface ComparableDoubleSingle extends LDoubleSingle, Comparable<LDoubleSingle> {

		@Override
		default int compareTo(LDoubleSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Double.compare(one.first(), two.first())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractDoubleSingle extends Number implements LDoubleSingle {

		@Override
		public boolean equals(Object that) {
			return LDoubleSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LDoubleSingle.argHashCode(first());
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
			return first();
		}
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutDoubleSingle extends AbstractDoubleSingle {

		private double first;

		public MutDoubleSingle(double first) {
			this.first = first;
		}

		public static MutDoubleSingle of(double first) {
			return new MutDoubleSingle(first);
		}

		public static MutDoubleSingle copyOf(LDoubleSingle tuple) {
			return of(tuple.first());
		}

		public double first() {
			return first;
		}

		public MutDoubleSingle first(double first) {
			this.first = first;
			return this;
		}

		public void setFirst(double first) {
			this.first = first;
		}

		public void reset() {
			first = 0d;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompDoubleSingle extends AbstractDoubleSingle implements ComparableDoubleSingle {

		private double first;

		public MutCompDoubleSingle(double first) {
			this.first = first;
		}

		public static MutCompDoubleSingle of(double first) {
			return new MutCompDoubleSingle(first);
		}

		public static MutCompDoubleSingle copyOf(LDoubleSingle tuple) {
			return of(tuple.first());
		}

		public double first() {
			return first;
		}

		public MutCompDoubleSingle first(double first) {
			this.first = first;
			return this;
		}

		public void setFirst(double first) {
			this.first = first;
		}

		public void reset() {
			first = 0d;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmDoubleSingle extends AbstractDoubleSingle {

		private final double first;

		public ImmDoubleSingle(double first) {
			this.first = first;
		}

		public static ImmDoubleSingle of(double first) {
			return new ImmDoubleSingle(first);
		}

		public static ImmDoubleSingle copyOf(LDoubleSingle tuple) {
			return of(tuple.first());
		}

		public double first() {
			return first;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompDoubleSingle extends AbstractDoubleSingle implements ComparableDoubleSingle {

		private final double first;

		public ImmCompDoubleSingle(double first) {
			this.first = first;
		}

		public static ImmCompDoubleSingle of(double first) {
			return new ImmCompDoubleSingle(first);
		}

		public static ImmCompDoubleSingle copyOf(LDoubleSingle tuple) {
			return of(tuple.first());
		}

		public double first() {
			return first;
		}

	}

}
