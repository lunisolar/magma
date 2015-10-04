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
 * Exact equivalent of input parameters used in LObjShortConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjShortPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	short second();

	default T getFirst() {
		return first();
	}

	default short getSecond() {
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

	static <T> int hashCode(T first, short second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + Short.hashCode(second);
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

	interface ComparableObjShortPair<T extends Comparable<T>> extends LObjShortPair<T>, Comparable<LObjShortPair<T>> {

		@Override
		default int compareTo(LObjShortPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Short.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjShortPair<T> implements LObjShortPair<T> {

		@Override
		public boolean equals(Object that) {
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LObjShortPair are allowed.
					if (!(two instanceof LObjShortPair)) {
						return false;
					}

					LObjShortPair other = (LObjShortPair) two;

					return Null.equals(one.first(), other.first()) && //
							one.second() == other.second(); //
				});
		}

		@Override
		public int hashCode() {
			return LObjShortPair.hashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjShortPair<T> extends AbstractObjShortPair<T> {

		private T first;
		private short second;

		public MutObjShortPair(T first, short second) {
			this.first = first;
			this.second = second;
		}

		public static <T> MutObjShortPair<T> of(T first, short second) {
			return new MutObjShortPair(first, second);
		}

		public static <T> MutObjShortPair<T> copyOf(LObjShortPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjShortPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutObjShortPair<T> second(short second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(short second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = (short) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjShortPair<T extends Comparable<T>> extends AbstractObjShortPair<T> implements ComparableObjShortPair<T> {

		private T first;
		private short second;

		public MutCompObjShortPair(T first, short second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> MutCompObjShortPair<T> of(T first, short second) {
			return new MutCompObjShortPair(first, second);
		}

		public static <T extends Comparable<T>> MutCompObjShortPair<T> copyOf(LObjShortPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjShortPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutCompObjShortPair<T> second(short second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(short second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = (short) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjShortPair<T> extends AbstractObjShortPair<T> {

		private final T first;
		private final short second;

		public ImmObjShortPair(T first, short second) {
			this.first = first;
			this.second = second;
		}

		public static <T> ImmObjShortPair<T> of(T first, short second) {
			return new ImmObjShortPair(first, second);
		}

		public static <T> ImmObjShortPair<T> copyOf(LObjShortPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public short second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjShortPair<T extends Comparable<T>> extends AbstractObjShortPair<T> implements ComparableObjShortPair<T> {

		private final T first;
		private final short second;

		public ImmCompObjShortPair(T first, short second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> ImmCompObjShortPair<T> of(T first, short second) {
			return new ImmCompObjShortPair(first, second);
		}

		public static <T extends Comparable<T>> ImmCompObjShortPair<T> copyOf(LObjShortPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public short second() {
			return second;
		}

	}

}
