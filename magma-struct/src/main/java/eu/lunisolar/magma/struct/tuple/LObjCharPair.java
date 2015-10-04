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
 * Exact equivalent of input parameters used in LObjCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjCharPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	char second();

	default T getFirst() {
		return first();
	}

	default char getSecond() {
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

	static <T> int hashCode(T first, char second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + Character.hashCode(second);
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

	interface ComparableObjCharPair<T extends Comparable<T>> extends LObjCharPair<T>, Comparable<LObjCharPair<T>> {

		@Override
		default int compareTo(LObjCharPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Character.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjCharPair<T> implements LObjCharPair<T> {

		@Override
		public boolean equals(Object that) {
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LObjCharPair are allowed.
					if (!(two instanceof LObjCharPair)) {
						return false;
					}

					LObjCharPair other = (LObjCharPair) two;

					return Null.equals(one.first(), other.first()) && //
							one.second() == other.second(); //
				});
		}

		@Override
		public int hashCode() {
			return LObjCharPair.hashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjCharPair<T> extends AbstractObjCharPair<T> {

		private T first;
		private char second;

		public MutObjCharPair(T first, char second) {
			this.first = first;
			this.second = second;
		}

		public static <T> MutObjCharPair<T> of(T first, char second) {
			return new MutObjCharPair(first, second);
		}

		public static <T> MutObjCharPair<T> copyOf(LObjCharPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjCharPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public char second() {
			return second;
		}

		public MutObjCharPair<T> second(char second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(char second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = '\u0000';
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjCharPair<T extends Comparable<T>> extends AbstractObjCharPair<T> implements ComparableObjCharPair<T> {

		private T first;
		private char second;

		public MutCompObjCharPair(T first, char second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> MutCompObjCharPair<T> of(T first, char second) {
			return new MutCompObjCharPair(first, second);
		}

		public static <T extends Comparable<T>> MutCompObjCharPair<T> copyOf(LObjCharPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjCharPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public char second() {
			return second;
		}

		public MutCompObjCharPair<T> second(char second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(char second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = '\u0000';
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjCharPair<T> extends AbstractObjCharPair<T> {

		private final T first;
		private final char second;

		public ImmObjCharPair(T first, char second) {
			this.first = first;
			this.second = second;
		}

		public static <T> ImmObjCharPair<T> of(T first, char second) {
			return new ImmObjCharPair(first, second);
		}

		public static <T> ImmObjCharPair<T> copyOf(LObjCharPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public char second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjCharPair<T extends Comparable<T>> extends AbstractObjCharPair<T> implements ComparableObjCharPair<T> {

		private final T first;
		private final char second;

		public ImmCompObjCharPair(T first, char second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> ImmCompObjCharPair<T> of(T first, char second) {
			return new ImmCompObjCharPair(first, second);
		}

		public static <T extends Comparable<T>> ImmCompObjCharPair<T> copyOf(LObjCharPair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public char second() {
			return second;
		}

	}

}
