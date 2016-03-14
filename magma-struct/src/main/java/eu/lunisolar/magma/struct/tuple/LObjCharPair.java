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

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjCharPair and calculates hash from it. */
	static <T> int argHashCode(T a1, char a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Character.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjCharPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, char a2, T b1, char b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjCharPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjCharPair are allowed.
				if (!(two instanceof LObjCharPair)) {
					return false;
				}

				LObjCharPair other = (LObjCharPair) two;

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
			return LObjCharPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjCharPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjCharPair<T> extends AbstractObjCharPair<T> {

		private T first;
		private char second;

		public MutObjCharPair(T a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> MutObjCharPair<T> of(T a1, char a2) {
			return new MutObjCharPair(a1, a2);
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

		public MutCompObjCharPair(T a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> MutCompObjCharPair<T> of(T a1, char a2) {
			return new MutCompObjCharPair(a1, a2);
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

		public ImmObjCharPair(T a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> ImmObjCharPair<T> of(T a1, char a2) {
			return new ImmObjCharPair(a1, a2);
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

		public ImmCompObjCharPair(T a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> ImmCompObjCharPair<T> of(T a1, char a2) {
			return new ImmCompObjCharPair(a1, a2);
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
