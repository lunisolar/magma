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
 * Exact equivalent of input parameters used in LBiConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LPair<T1, T2> extends LTuple<Object> {

	int SIZE = 2;

	T1 first();

	T2 second();

	default T1 getFirst() {
		return first();
	}

	default T2 getSecond() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LPair and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 a1, T2 a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LPair and checks if all values are equal. */
	static <T1, T2> boolean argEquals(T1 a1, T2 a2, T1 b1, T2 b2) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2); //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LPair are allowed.
				if (!(two instanceof LPair)) {
					return false;
				}

				LPair other = (LPair) two;

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

	interface ComparablePair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends LPair<T1, T2>, Comparable<LPair<T1, T2>> {

		@Override
		default int compareTo(LPair<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractPair<T1, T2> implements LPair<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutPair<T1, T2> extends AbstractPair<T1, T2> {

		private T1 first;
		private T2 second;

		public MutPair(T1 a1, T2 a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T1, T2> MutPair<T1, T2> of(T1 a1, T2 a2) {
			return new MutPair(a1, a2);
		}

		public static <T1, T2> MutPair<T1, T2> copyOf(LPair<T1, T2> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T1 first() {
			return first;
		}

		public MutPair<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutPair<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = null;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompPair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractPair<T1, T2> implements ComparablePair<T1, T2> {

		private T1 first;
		private T2 second;

		public MutCompPair(T1 a1, T2 a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompPair<T1, T2> of(T1 a1, T2 a2) {
			return new MutCompPair(a1, a2);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompPair<T1, T2> copyOf(LPair<T1, T2> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T1 first() {
			return first;
		}

		public MutCompPair<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompPair<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void reset() {
			first = null;
			second = null;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmPair<T1, T2> extends AbstractPair<T1, T2> {

		private final T1 first;
		private final T2 second;

		public ImmPair(T1 a1, T2 a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T1, T2> ImmPair<T1, T2> of(T1 a1, T2 a2) {
			return new ImmPair(a1, a2);
		}

		public static <T1, T2> ImmPair<T1, T2> copyOf(LPair<T1, T2> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompPair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractPair<T1, T2> implements ComparablePair<T1, T2> {

		private final T1 first;
		private final T2 second;

		public ImmCompPair(T1 a1, T2 a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompPair<T1, T2> of(T1 a1, T2 a2) {
			return new ImmCompPair(a1, a2);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompPair<T1, T2> copyOf(LPair<T1, T2> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

	}

}
