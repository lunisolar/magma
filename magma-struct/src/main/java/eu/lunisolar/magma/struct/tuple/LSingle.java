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
 * Exact equivalent of input parameters used in LConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSingle<T> extends LTuple<T> {

	int SIZE = 1;

	T value();

	default T getValue() {
		return value();
	}

	default T get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LSingle and calculates hash from it. */
	static <T> int argHashCode(T a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LSingle and checks if all values are equal. */
	static <T> boolean argEquals(T a, T b) {
		return Null.equals(a, b); //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSingle are allowed.
				if (!(two instanceof LSingle)) {
					return false;
				}

				LSingle other = (LSingle) two;

				return argEquals(one.value(), other.value());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

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
	default Iterator<T> iterator() {
		return new Iterator<T>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public T next() {
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

				return (retval = Null.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractSingle<T> implements LSingle<T> {

		@Override
		public boolean equals(Object that) {
			return LSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LSingle.argHashCode(value());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutSingle<T> extends AbstractSingle<T> {

		private T value;

		public MutSingle(T a) {
			this.value = a;
		}

		public static <T> MutSingle<T> of(T a) {
			return new MutSingle(a);
		}

		public static <T> MutSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.value());
		}

		public T value() {
			return value;
		}

		public MutSingle<T> value(T value) {
			this.value = value;
			return this;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public void reset() {
			value = null;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompSingle<T extends Comparable<T>> extends AbstractSingle<T> implements ComparableSingle<T> {

		private T value;

		public MutCompSingle(T a) {
			this.value = a;
		}

		public static <T extends Comparable<T>> MutCompSingle<T> of(T a) {
			return new MutCompSingle(a);
		}

		public static <T extends Comparable<T>> MutCompSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.value());
		}

		public T value() {
			return value;
		}

		public MutCompSingle<T> value(T value) {
			this.value = value;
			return this;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public void reset() {
			value = null;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmSingle<T> extends AbstractSingle<T> {

		private final T value;

		public ImmSingle(T a) {
			this.value = a;
		}

		public static <T> ImmSingle<T> of(T a) {
			return new ImmSingle(a);
		}

		public static <T> ImmSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.value());
		}

		public T value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompSingle<T extends Comparable<T>> extends AbstractSingle<T> implements ComparableSingle<T> {

		private final T value;

		public ImmCompSingle(T a) {
			this.value = a;
		}

		public static <T extends Comparable<T>> ImmCompSingle<T> of(T a) {
			return new ImmCompSingle(a);
		}

		public static <T extends Comparable<T>> ImmCompSingle<T> copyOf(LSingle<T> tuple) {
			return of(tuple.value());
		}

		public T value() {
			return value;
		}

	}

}
