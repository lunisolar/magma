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
 * Exact equivalent of input parameters used in LConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSingle<T> extends LTuple<Object> {

	int SIZE = 1;

	T first();

	default T getFirst() {
		return first();
	}

	default Object get(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	default int size() {
		return SIZE;
	}

	static <T> int hashCode(T first) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		return result;
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

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

	interface ComparableSingle<T extends Comparable<T>> extends LSingle<T>, Comparable<LSingle<T>> {

		@Override
		default int compareTo(LSingle<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractSingle<T> implements LSingle<T> {

		@Override
		public boolean equals(Object that) {
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LSingle are allowed.
					if (!(two instanceof LSingle)) {
						return false;
					}

					LSingle other = (LSingle) two;

					return Null.equals(one.first(), other.first()); //
				});
		}

		@Override
		public int hashCode() {
			return LSingle.hashCode(first());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutSingle<T> extends AbstractSingle<T> {

		private T first;

		public MutSingle(T first) {
			this.first = first;
		}

		public static <T> MutSingle<T> of(T first) {
			return new MutSingle(first);
		}

		public static <T> MutSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.first());
		}

		public T first() {
			return first;
		}

		public MutSingle<T> first(T first) {
			this.first = first;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void reset() {
			first = null;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompSingle<T extends Comparable<T>> extends AbstractSingle<T> implements ComparableSingle<T> {

		private T first;

		public MutCompSingle(T first) {
			this.first = first;
		}

		public static <T extends Comparable<T>> MutCompSingle<T> of(T first) {
			return new MutCompSingle(first);
		}

		public static <T extends Comparable<T>> MutCompSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.first());
		}

		public T first() {
			return first;
		}

		public MutCompSingle<T> first(T first) {
			this.first = first;
			return this;
		}

		public void setFirst(T first) {
			this.first = first;
		}

		public void reset() {
			first = null;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmSingle<T> extends AbstractSingle<T> {

		private final T first;

		public ImmSingle(T first) {
			this.first = first;
		}

		public static <T> ImmSingle<T> of(T first) {
			return new ImmSingle(first);
		}

		public static <T> ImmSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.first());
		}

		public T first() {
			return first;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompSingle<T extends Comparable<T>> extends AbstractSingle<T> implements ComparableSingle<T> {

		private final T first;

		public ImmCompSingle(T first) {
			this.first = first;
		}

		public static <T extends Comparable<T>> ImmCompSingle<T> of(T first) {
			return new ImmCompSingle(first);
		}

		public static <T extends Comparable<T>> ImmCompSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.first());
		}

		public T first() {
			return first;
		}

	}

}
