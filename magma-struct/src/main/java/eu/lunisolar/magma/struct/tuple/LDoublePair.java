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
 * Exact equivalent of input parameters used in LBiDoubleConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDoublePair extends LTuple<Double> {

	int SIZE = 2;

	double first();

	double second();

	default double getFirst() {
		return first();
	}

	default double getSecond() {
		return second();
	}

	default Double get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default double getDouble(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default int size() {
		return SIZE;
	}

	static int hashCode(double first, double second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(first);
		result = prime * result + Double.hashCode(second);
		return result;
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

	default Double[] toVoArray(Double[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

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
		i++;
		array[i] = second();

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

	interface ComparableDoublePair extends LDoublePair, Comparable<LDoublePair> {

		@Override
		default int compareTo(LDoublePair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Double.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Double.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractDoublePair implements LDoublePair {

		@Override
		public boolean equals(Object that) {
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LDoublePair are allowed.
					if (!(two instanceof LDoublePair)) {
						return false;
					}

					LDoublePair other = (LDoublePair) two;

					return one.first() == other.first() && //
							one.second() == other.second(); //
				});
		}

		@Override
		public int hashCode() {
			return LDoublePair.hashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutDoublePair extends AbstractDoublePair {

		private double first;
		private double second;

		public MutDoublePair(double first, double second) {
			this.first = first;
			this.second = second;
		}

		public static MutDoublePair of(double first, double second) {
			return new MutDoublePair(first, second);
		}

		public static MutDoublePair copyOf(LDoublePair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public MutDoublePair first(double first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutDoublePair second(double second) {
			this.second = second;
			return this;
		}

		public void setFirst(double first) {
			this.first = first;
		}

		public void setSecond(double second) {
			this.second = second;
		}

		public void reset() {
			first = 0d;
			second = 0d;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompDoublePair extends AbstractDoublePair implements ComparableDoublePair {

		private double first;
		private double second;

		public MutCompDoublePair(double first, double second) {
			this.first = first;
			this.second = second;
		}

		public static MutCompDoublePair of(double first, double second) {
			return new MutCompDoublePair(first, second);
		}

		public static MutCompDoublePair copyOf(LDoublePair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public MutCompDoublePair first(double first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutCompDoublePair second(double second) {
			this.second = second;
			return this;
		}

		public void setFirst(double first) {
			this.first = first;
		}

		public void setSecond(double second) {
			this.second = second;
		}

		public void reset() {
			first = 0d;
			second = 0d;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmDoublePair extends AbstractDoublePair {

		private final double first;
		private final double second;

		public ImmDoublePair(double first, double second) {
			this.first = first;
			this.second = second;
		}

		public static ImmDoublePair of(double first, double second) {
			return new ImmDoublePair(first, second);
		}

		public static ImmDoublePair copyOf(LDoublePair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public double second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompDoublePair extends AbstractDoublePair implements ComparableDoublePair {

		private final double first;
		private final double second;

		public ImmCompDoublePair(double first, double second) {
			this.first = first;
			this.second = second;
		}

		public static ImmCompDoublePair of(double first, double second) {
			return new ImmCompDoublePair(first, second);
		}

		public static ImmCompDoublePair copyOf(LDoublePair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public double second() {
			return second;
		}

	}

}
