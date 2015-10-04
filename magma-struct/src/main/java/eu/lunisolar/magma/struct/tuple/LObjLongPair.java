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
 * Exact equivalent of input parameters used in LObjLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjLongPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	long second();

	default T getFirst() {
		return first();
	}

	default long getSecond() {
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

	default int size() {
		return SIZE;
	}

	static <T> int hashCode(T first, long second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + Long.hashCode(second);
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

	interface ComparableObjLongPair<T extends Comparable<T>> extends LObjLongPair<T>, Comparable<LObjLongPair<T>> {

		@Override
		default int compareTo(LObjLongPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Long.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjLongPair<T> implements LObjLongPair<T> {

		@Override
		public boolean equals(Object that) {
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LObjLongPair are allowed.
					if (!(two instanceof LObjLongPair)) {
						return false;
					}

					LObjLongPair other = (LObjLongPair) two;

					return Null.equals(one.first(), other.first()) && //
							one.second() == other.second(); //
				});
		}

		@Override
		public int hashCode() {
			return LObjLongPair.hashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjLongPair<T> extends AbstractObjLongPair<T> {

		private T first;
		private long second;

		public MutObjLongPair(T first, long second) {
			this.first = first;
			this.second = second;
		}

		public static <T> MutObjLongPair<T> of(T first, long second) {
			return new MutObjLongPair(first, second);
		}

		public static <T> MutObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjLongPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public long second() {
			return second;
		}

		public MutObjLongPair<T> second(long second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(long second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = 0L;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjLongPair<T extends Comparable<T>> extends AbstractObjLongPair<T> implements ComparableObjLongPair<T> {

		private T first;
		private long second;

		public MutCompObjLongPair(T first, long second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> MutCompObjLongPair<T> of(T first, long second) {
			return new MutCompObjLongPair(first, second);
		}

		public static <T extends Comparable<T>> MutCompObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjLongPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public long second() {
			return second;
		}

		public MutCompObjLongPair<T> second(long second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(long second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = 0L;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjLongPair<T> extends AbstractObjLongPair<T> {

		private final T first;
		private final long second;

		public ImmObjLongPair(T first, long second) {
			this.first = first;
			this.second = second;
		}

		public static <T> ImmObjLongPair<T> of(T first, long second) {
			return new ImmObjLongPair(first, second);
		}

		public static <T> ImmObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public long second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjLongPair<T extends Comparable<T>> extends AbstractObjLongPair<T> implements ComparableObjLongPair<T> {

		private final T first;
		private final long second;

		public ImmCompObjLongPair(T first, long second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> ImmCompObjLongPair<T> of(T first, long second) {
			return new ImmCompObjLongPair(first, second);
		}

		public static <T extends Comparable<T>> ImmCompObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public long second() {
			return second;
		}

	}

}
