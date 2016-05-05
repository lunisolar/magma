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
 * Exact equivalent of input parameters used in LBiBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolPair extends LTuple<Boolean> {

	int SIZE = 2;

	boolean first();

	boolean second();

	default boolean getFirst() {
		return first();
	}

	default boolean getSecond() {
		return second();
	}

	default Boolean get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default boolean getBoolean(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBoolPair and calculates hash from it. */
	static int argHashCode(boolean a1, boolean a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Boolean.hashCode(a1);
		result = prime * result + Boolean.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolPair and checks if all values are equal. */
	static boolean argEquals(boolean a1, boolean a2, boolean b1, boolean b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LBoolPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBoolPair are allowed.
				if (!(two instanceof LBoolPair)) {
					return false;
				}

				LBoolPair other = (LBoolPair) two;

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

	default Boolean[] toVoArray(Boolean[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

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
		i++;
		array[i] = second();

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

	interface ComparableBoolPair extends LBoolPair, Comparable<LBoolPair> {

		@Override
		default int compareTo(LBoolPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Boolean.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Boolean.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBoolPair implements LBoolPair {

		@Override
		public boolean equals(Object that) {
			return LBoolPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBoolPair.argHashCode(first(), second());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBoolPair extends AbstractBoolPair {

		private boolean first;
		private boolean second;

		public MutBoolPair(boolean a1, boolean a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutBoolPair of(boolean a1, boolean a2) {
			return new MutBoolPair(a1, a2);
		}

		public static MutBoolPair copyOf(LBoolPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public boolean first() {
			return first;
		}

		public MutBoolPair first(boolean first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutBoolPair second(boolean second) {
			this.second = second;
			return this;
		}

		public void setFirst(boolean first) {
			this.first = first;
		}

		public void setSecond(boolean second) {
			this.second = second;
		}

		public void reset() {
			first = false;
			second = false;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBoolPair extends AbstractBoolPair implements ComparableBoolPair {

		private boolean first;
		private boolean second;

		public MutCompBoolPair(boolean a1, boolean a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompBoolPair of(boolean a1, boolean a2) {
			return new MutCompBoolPair(a1, a2);
		}

		public static MutCompBoolPair copyOf(LBoolPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public boolean first() {
			return first;
		}

		public MutCompBoolPair first(boolean first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutCompBoolPair second(boolean second) {
			this.second = second;
			return this;
		}

		public void setFirst(boolean first) {
			this.first = first;
		}

		public void setSecond(boolean second) {
			this.second = second;
		}

		public void reset() {
			first = false;
			second = false;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBoolPair extends AbstractBoolPair {

		private final boolean first;
		private final boolean second;

		public ImmBoolPair(boolean a1, boolean a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmBoolPair of(boolean a1, boolean a2) {
			return new ImmBoolPair(a1, a2);
		}

		public static ImmBoolPair copyOf(LBoolPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public boolean first() {
			return first;
		}

		public boolean second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompBoolPair extends AbstractBoolPair implements ComparableBoolPair {

		private final boolean first;
		private final boolean second;

		public ImmCompBoolPair(boolean a1, boolean a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompBoolPair of(boolean a1, boolean a2) {
			return new ImmCompBoolPair(a1, a2);
		}

		public static ImmCompBoolPair copyOf(LBoolPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public boolean first() {
			return first;
		}

		public boolean second() {
			return second;
		}

	}

}
