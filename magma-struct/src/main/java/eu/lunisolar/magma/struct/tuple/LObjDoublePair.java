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
 * Exact equivalent of input parameters used in LObjDoubleConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjDoublePair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	double second();

	default T getFirst() {
		return first();
	}

	default double getSecond() {
		return second();
	}

	default Object get(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjDoublePair and calculates hash from it. */
	static <T> int argHashCode(T first, double second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + Double.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjDoublePair and checks if all values are equal. */
	static <T> boolean argEquals(T first, double second, T firstOfOther, double secondOfOther) {
		return Null.equals(first, firstOfOther) && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static <T> boolean argEquals(LObjDoublePair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjDoublePair are allowed.
				if (!(two instanceof LObjDoublePair)) {
					return false;
				}

				LObjDoublePair other = (LObjDoublePair) two;

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

	@Override
	default Iterator<Object> iterator() {
		return new Iterator<Object>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Object next() {
				index++;
				return get(index);
			}
		};
	}

	interface ComparableObjDoublePair<T extends Comparable<T>> extends LObjDoublePair<T>, Comparable<LObjDoublePair<T>> {

		@Override
		default int compareTo(LObjDoublePair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Double.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjDoublePair<T> implements LObjDoublePair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjDoublePair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjDoublePair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjDoublePair<T> extends AbstractObjDoublePair<T> {

		private T first;
		private double second;

		public MutObjDoublePair(T first, double second) {
			this.first = first;
			this.second = second;
		}

		public static <T> MutObjDoublePair<T> of(T first, double second) {
			return new MutObjDoublePair(first, second);
		}

		public static <T> MutObjDoublePair<T> copyOf(LObjDoublePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjDoublePair<T> first(T first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutObjDoublePair<T> second(double second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(double second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = 0d;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjDoublePair<T extends Comparable<T>> extends AbstractObjDoublePair<T> implements ComparableObjDoublePair<T> {

		private T first;
		private double second;

		public MutCompObjDoublePair(T first, double second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> MutCompObjDoublePair<T> of(T first, double second) {
			return new MutCompObjDoublePair(first, second);
		}

		public static <T extends Comparable<T>> MutCompObjDoublePair<T> copyOf(LObjDoublePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjDoublePair<T> first(T first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutCompObjDoublePair<T> second(double second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(double second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = 0d;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjDoublePair<T> extends AbstractObjDoublePair<T> {

		private final T first;
		private final double second;

		public ImmObjDoublePair(T first, double second) {
			this.first = first;
			this.second = second;
		}

		public static <T> ImmObjDoublePair<T> of(T first, double second) {
			return new ImmObjDoublePair(first, second);
		}

		public static <T> ImmObjDoublePair<T> copyOf(LObjDoublePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
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
	final class ImmCompObjDoublePair<T extends Comparable<T>> extends AbstractObjDoublePair<T> implements ComparableObjDoublePair<T> {

		private final T first;
		private final double second;

		public ImmCompObjDoublePair(T first, double second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> ImmCompObjDoublePair<T> of(T first, double second) {
			return new ImmCompObjDoublePair(first, second);
		}

		public static <T extends Comparable<T>> ImmCompObjDoublePair<T> copyOf(LObjDoublePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public double second() {
			return second;
		}

	}

}
