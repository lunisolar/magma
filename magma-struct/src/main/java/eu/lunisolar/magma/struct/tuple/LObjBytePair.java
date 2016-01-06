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
 * Exact equivalent of input parameters used in LObjByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBytePair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	byte second();

	default T getFirst() {
		return first();
	}

	default byte getSecond() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjBytePair and calculates hash from it. */
	static <T> int argHashCode(T first, byte second) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + Byte.hashCode(second);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjBytePair and checks if all values are equal. */
	static <T> boolean argEquals(T first, byte second, T firstOfOther, byte secondOfOther) {
		return Null.equals(first, firstOfOther) && //
				second == secondOfOther; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static <T> boolean argEquals(LObjBytePair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjBytePair are allowed.
				if (!(two instanceof LObjBytePair)) {
					return false;
				}

				LObjBytePair other = (LObjBytePair) two;

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

	interface ComparableObjBytePair<T extends Comparable<T>> extends LObjBytePair<T>, Comparable<LObjBytePair<T>> {

		@Override
		default int compareTo(LObjBytePair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Byte.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjBytePair<T> implements LObjBytePair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjBytePair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjBytePair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjBytePair<T> extends AbstractObjBytePair<T> {

		private T first;
		private byte second;

		public MutObjBytePair(T first, byte second) {
			this.first = first;
			this.second = second;
		}

		public static <T> MutObjBytePair<T> of(T first, byte second) {
			return new MutObjBytePair(first, second);
		}

		public static <T> MutObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjBytePair<T> first(T first) {
			this.first = first;
			return this;
		}

		public byte second() {
			return second;
		}

		public MutObjBytePair<T> second(byte second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(byte second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjBytePair<T extends Comparable<T>> extends AbstractObjBytePair<T> implements ComparableObjBytePair<T> {

		private T first;
		private byte second;

		public MutCompObjBytePair(T first, byte second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> MutCompObjBytePair<T> of(T first, byte second) {
			return new MutCompObjBytePair(first, second);
		}

		public static <T extends Comparable<T>> MutCompObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjBytePair<T> first(T first) {
			this.first = first;
			return this;
		}

		public byte second() {
			return second;
		}

		public MutCompObjBytePair<T> second(byte second) {
			this.second = second;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void setSecond(byte second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjBytePair<T> extends AbstractObjBytePair<T> {

		private final T first;
		private final byte second;

		public ImmObjBytePair(T first, byte second) {
			this.first = first;
			this.second = second;
		}

		public static <T> ImmObjBytePair<T> of(T first, byte second) {
			return new ImmObjBytePair(first, second);
		}

		public static <T> ImmObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public byte second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjBytePair<T extends Comparable<T>> extends AbstractObjBytePair<T> implements ComparableObjBytePair<T> {

		private final T first;
		private final byte second;

		public ImmCompObjBytePair(T first, byte second) {
			this.first = first;
			this.second = second;
		}

		public static <T extends Comparable<T>> ImmCompObjBytePair<T> of(T first, byte second) {
			return new ImmCompObjBytePair(first, second);
		}

		public static <T extends Comparable<T>> ImmCompObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public byte second() {
			return second;
		}

	}

}
