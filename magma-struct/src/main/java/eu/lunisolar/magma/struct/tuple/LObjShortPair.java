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

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjShortPair and calculates hash from it. */
	static <T> int argHashCode(T a1, short a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Short.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjShortPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, short a2, T b1, short b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjShortPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjShortPair are allowed.
				if (!(two instanceof LObjShortPair)) {
					return false;
				}

				LObjShortPair other = (LObjShortPair) two;

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
			return LObjShortPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjShortPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjShortPair<T> extends AbstractObjShortPair<T> {

		private T first;
		private short second;

		public MutObjShortPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> MutObjShortPair<T> of(T a1, short a2) {
			return new MutObjShortPair(a1, a2);
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

		public MutCompObjShortPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> MutCompObjShortPair<T> of(T a1, short a2) {
			return new MutCompObjShortPair(a1, a2);
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

		public ImmObjShortPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> ImmObjShortPair<T> of(T a1, short a2) {
			return new ImmObjShortPair(a1, a2);
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

		public ImmCompObjShortPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> ImmCompObjShortPair<T> of(T a1, short a2) {
			return new ImmCompObjShortPair(a1, a2);
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
