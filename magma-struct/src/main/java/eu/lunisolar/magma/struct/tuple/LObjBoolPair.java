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
 * Exact equivalent of input parameters used in LObjBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBoolPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	boolean second();

	default T getFirst() {
		return first();
	}

	default boolean getSecond() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjBoolPair and calculates hash from it. */
	static <T> int argHashCode(T first, boolean second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + Boolean.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjBoolPair and checks if all values are equal. */
	static <T> boolean argEquals(T first, boolean second, T firstOfOther, boolean secondOfOther) {
		return Null.equals(first, firstOfOther) && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static <T> boolean argEquals(LObjBoolPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjBoolPair are allowed.
				if (!(two instanceof LObjBoolPair)) {
					return false;
				}

				LObjBoolPair other = (LObjBoolPair) two;

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

	interface ComparableObjBoolPair<T extends Comparable<T>> extends LObjBoolPair<T>, Comparable<LObjBoolPair<T>> {

		@Override
		default int compareTo(LObjBoolPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Boolean.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjBoolPair<T> implements LObjBoolPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjBoolPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjBoolPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjBoolPair<T> extends AbstractObjBoolPair<T> {

		private T first;
		private boolean second;

		public MutObjBoolPair(T first, boolean second) {
			this.first = first;
			this.second = second;
		}

		public static <T> MutObjBoolPair<T> of(T first, boolean second) {
			return new MutObjBoolPair(first, second);
		}

		public static <T> MutObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjBoolPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutObjBoolPair<T> second(boolean second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(boolean second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = false;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjBoolPair<T extends Comparable<T>> extends AbstractObjBoolPair<T> implements ComparableObjBoolPair<T> {

		private T first;
		private boolean second;

		public MutCompObjBoolPair(T first, boolean second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> MutCompObjBoolPair<T> of(T first, boolean second) {
			return new MutCompObjBoolPair(first, second);
		}

		public static <T extends Comparable<T>> MutCompObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjBoolPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutCompObjBoolPair<T> second(boolean second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(boolean second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = false;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjBoolPair<T> extends AbstractObjBoolPair<T> {

		private final T first;
		private final boolean second;

		public ImmObjBoolPair(T first, boolean second) {
			this.first = first;
			this.second = second;
		}

		public static <T> ImmObjBoolPair<T> of(T first, boolean second) {
			return new ImmObjBoolPair(first, second);
		}

		public static <T> ImmObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public boolean second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjBoolPair<T extends Comparable<T>> extends AbstractObjBoolPair<T> implements ComparableObjBoolPair<T> {

		private final T first;
		private final boolean second;

		public ImmCompObjBoolPair(T first, boolean second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> ImmCompObjBoolPair<T> of(T first, boolean second) {
			return new ImmCompObjBoolPair(first, second);
		}

		public static <T extends Comparable<T>> ImmCompObjBoolPair<T> copyOf(LObjBoolPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public boolean second() {
			return second;
		}

	}

}
