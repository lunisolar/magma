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
 * Exact equivalent of input parameters used in LObjFloatConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjFloatPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	float second();

	default T getFirst() {
		return first();
	}

	default float getSecond() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjFloatPair and calculates hash from it. */
	static <T> int argHashCode(T first, float second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + Float.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjFloatPair and checks if all values are equal. */
	static <T> boolean argEquals(T first, float second, T firstOfOther, float secondOfOther) {
		return Null.equals(first, firstOfOther) && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static <T> boolean argEquals(LObjFloatPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjFloatPair are allowed.
				if (!(two instanceof LObjFloatPair)) {
					return false;
				}

				LObjFloatPair other = (LObjFloatPair) two;

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

	interface ComparableObjFloatPair<T extends Comparable<T>> extends LObjFloatPair<T>, Comparable<LObjFloatPair<T>> {

		@Override
		default int compareTo(LObjFloatPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Float.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjFloatPair<T> implements LObjFloatPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjFloatPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjFloatPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjFloatPair<T> extends AbstractObjFloatPair<T> {

		private T first;
		private float second;

		public MutObjFloatPair(T first, float second) {
			this.first = first;
			this.second = second;
		}

		public static <T> MutObjFloatPair<T> of(T first, float second) {
			return new MutObjFloatPair(first, second);
		}

		public static <T> MutObjFloatPair<T> copyOf(LObjFloatPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjFloatPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutObjFloatPair<T> second(float second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(float second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjFloatPair<T extends Comparable<T>> extends AbstractObjFloatPair<T> implements ComparableObjFloatPair<T> {

		private T first;
		private float second;

		public MutCompObjFloatPair(T first, float second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> MutCompObjFloatPair<T> of(T first, float second) {
			return new MutCompObjFloatPair(first, second);
		}

		public static <T extends Comparable<T>> MutCompObjFloatPair<T> copyOf(LObjFloatPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjFloatPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutCompObjFloatPair<T> second(float second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(float second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjFloatPair<T> extends AbstractObjFloatPair<T> {

		private final T first;
		private final float second;

		public ImmObjFloatPair(T first, float second) {
			this.first = first;
			this.second = second;
		}

		public static <T> ImmObjFloatPair<T> of(T first, float second) {
			return new ImmObjFloatPair(first, second);
		}

		public static <T> ImmObjFloatPair<T> copyOf(LObjFloatPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
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
	final class ImmCompObjFloatPair<T extends Comparable<T>> extends AbstractObjFloatPair<T> implements ComparableObjFloatPair<T> {

		private final T first;
		private final float second;

		public ImmCompObjFloatPair(T first, float second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> ImmCompObjFloatPair<T> of(T first, float second) {
			return new ImmCompObjFloatPair(first, second);
		}

		public static <T extends Comparable<T>> ImmCompObjFloatPair<T> copyOf(LObjFloatPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public float second() {
			return second;
		}

	}

}
