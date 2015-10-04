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

	default int size() {
		return SIZE;
	}

	static int hashCode(boolean first) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Boolean.hashCode(first);
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

	default boolean[] toBoolArray(boolean[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();

		return array;
	}

	default boolean[] toBoolArray(boolean[] array) {
		return toBoolArray(array, 0);
	}

	default boolean[] toBoolArray() {
		boolean[] array = new boolean[size()];

		return toBoolArray(array);
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
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LBoolSingle are allowed.
					if (!(two instanceof LBoolSingle)) {
						return false;
					}

					LBoolSingle other = (LBoolSingle) two;

					return one.first() == other.first(); //
				});
		}

		@Override
		public int hashCode() {
			return LBoolSingle.hashCode(first());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBoolSingle extends AbstractBoolSingle {

		private boolean first;

		public MutBoolSingle(boolean first) {
			this.first = first;
		}

		public static MutBoolSingle of(boolean first) {
			return new MutBoolSingle(first);
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

		public MutCompBoolSingle(boolean first) {
			this.first = first;
		}

		public static MutCompBoolSingle of(boolean first) {
			return new MutCompBoolSingle(first);
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

		public ImmBoolSingle(boolean first) {
			this.first = first;
		}

		public static ImmBoolSingle of(boolean first) {
			return new ImmBoolSingle(first);
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

		public ImmCompBoolSingle(boolean first) {
			this.first = first;
		}

		public static ImmCompBoolSingle of(boolean first) {
			return new ImmCompBoolSingle(first);
		}

		public static ImmCompBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.first());
		}

		public boolean first() {
			return first;
		}

	}

}
