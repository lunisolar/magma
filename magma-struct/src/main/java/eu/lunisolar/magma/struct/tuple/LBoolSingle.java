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
 * Exact equivalent of input parameters used in LBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolSingle extends LTuple<Boolean> {

	int SIZE = 1;

	boolean first();

	default boolean getFirst() {
		return first();
	}

	default Boolean get(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	default boolean getBoolean(int index) {
		switch (index) {
			case 1 :
				return first();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LBoolSingle and calculates hash from it. */
	static int argHashCode(boolean a1) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Boolean.hashCode(a1);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolSingle and checks if all values are equal. */
	static boolean argEquals(boolean a1, boolean b1) {
		return a1 == b1; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LBoolSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBoolSingle are allowed.
				if (!(two instanceof LBoolSingle)) {
					return false;
				}

				LBoolSingle other = (LBoolSingle) two;

				return argEquals(one.first(), other.first());
			});
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

	default Boolean[] toVoArray(Boolean[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

		return array;
	}

	default Boolean[] toVoArray(Boolean[] array) {
		return toVoArray(array, 0);
	}

	default Boolean[] toVoArray() {
		Boolean[] array = new Boolean[size()];

		return toVoArray(array);
	}

	default boolean[] toBooleanArray(boolean[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

		return array;
	}

	default boolean[] toBooleanArray(boolean[] array) {
		return toBooleanArray(array, 0);
	}

	default boolean[] toBooleanArray() {
		boolean[] array = new boolean[size()];

		return toBooleanArray(array);
	}

	@Override
	default Iterator<Boolean> iterator() {
		return new Iterator<Boolean>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Boolean next() {
				index++;
				return get(index);
			}
		};
	}

	interface ComparableBoolSingle extends LBoolSingle, Comparable<LBoolSingle> {

		@Override
		default int compareTo(LBoolSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Boolean.compare(one.first(), two.first())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBoolSingle implements LBoolSingle {

		@Override
		public boolean equals(Object that) {
			return LBoolSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBoolSingle.argHashCode(first());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBoolSingle extends AbstractBoolSingle {

		private boolean first;

		public MutBoolSingle(boolean a1) {
			this.first = a1;
		}

		public static MutBoolSingle of(boolean a1) {
			return new MutBoolSingle(a1);
		}

		public static MutBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.first());
		}

		public boolean first() {
			return first;
		}

		public MutBoolSingle first(boolean first) {
			this.first = first;
			return this;
		}

		public void setFirst(boolean first) {
			this.first = first;
		}

		public void reset() {
			first = false;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBoolSingle extends AbstractBoolSingle implements ComparableBoolSingle {

		private boolean first;

		public MutCompBoolSingle(boolean a1) {
			this.first = a1;
		}

		public static MutCompBoolSingle of(boolean a1) {
			return new MutCompBoolSingle(a1);
		}

		public static MutCompBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.first());
		}

		public boolean first() {
			return first;
		}

		public MutCompBoolSingle first(boolean first) {
			this.first = first;
			return this;
		}

		public void setFirst(boolean first) {
			this.first = first;
		}

		public void reset() {
			first = false;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBoolSingle extends AbstractBoolSingle {

		private final boolean first;

		public ImmBoolSingle(boolean a1) {
			this.first = a1;
		}

		public static ImmBoolSingle of(boolean a1) {
			return new ImmBoolSingle(a1);
		}

		public static ImmBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.first());
		}

		public boolean first() {
			return first;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompBoolSingle extends AbstractBoolSingle implements ComparableBoolSingle {

		private final boolean first;

		public ImmCompBoolSingle(boolean a1) {
			this.first = a1;
		}

		public static ImmCompBoolSingle of(boolean a1) {
			return new ImmCompBoolSingle(a1);
		}

		public static ImmCompBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.first());
		}

		public boolean first() {
			return first;
		}

	}

}
