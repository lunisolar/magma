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
 * Exact equivalent of input parameters used in LObjIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	int second();

	default T getFirst() {
		return first();
	}

	default int getSecond() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntPair and calculates hash from it. */
	static <T> int argHashCode(T first, int second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + Integer.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntPair and checks if all values are equal. */
	static <T> boolean argEquals(T first, int second, T firstOfOther, int secondOfOther) {
		return Null.equals(first, firstOfOther) && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static <T> boolean argEquals(LObjIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntPair are allowed.
				if (!(two instanceof LObjIntPair)) {
					return false;
				}

				LObjIntPair other = (LObjIntPair) two;

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

	interface ComparableObjIntPair<T extends Comparable<T>> extends LObjIntPair<T>, Comparable<LObjIntPair<T>> {

		@Override
		default int compareTo(LObjIntPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjIntPair<T> implements LObjIntPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjIntPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjIntPair<T> extends AbstractObjIntPair<T> {

		private T first;
		private int second;

		public MutObjIntPair(T first, int second) {
			this.first = first;
			this.second = second;
		}

		public static <T> MutObjIntPair<T> of(T first, int second) {
			return new MutObjIntPair(first, second);
		}

		public static <T> MutObjIntPair<T> copyOf(LObjIntPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjIntPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutObjIntPair<T> second(int second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjIntPair<T extends Comparable<T>> extends AbstractObjIntPair<T> implements ComparableObjIntPair<T> {

		private T first;
		private int second;

		public MutCompObjIntPair(T first, int second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> MutCompObjIntPair<T> of(T first, int second) {
			return new MutCompObjIntPair(first, second);
		}

		public static <T extends Comparable<T>> MutCompObjIntPair<T> copyOf(LObjIntPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjIntPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompObjIntPair<T> second(int second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(int second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntPair<T> extends AbstractObjIntPair<T> {

		private final T first;
		private final int second;

		public ImmObjIntPair(T first, int second) {
			this.first = first;
			this.second = second;
		}

		public static <T> ImmObjIntPair<T> of(T first, int second) {
			return new ImmObjIntPair(first, second);
		}

		public static <T> ImmObjIntPair<T> copyOf(LObjIntPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjIntPair<T extends Comparable<T>> extends AbstractObjIntPair<T> implements ComparableObjIntPair<T> {

		private final T first;
		private final int second;

		public ImmCompObjIntPair(T first, int second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> ImmCompObjIntPair<T> of(T first, int second) {
			return new ImmCompObjIntPair(first, second);
		}

		public static <T extends Comparable<T>> ImmCompObjIntPair<T> copyOf(LObjIntPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
