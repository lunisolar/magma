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
 * Exact equivalent of input parameters used in LTriBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolTriple extends LTuple<Boolean> {

	int SIZE = 3;

	boolean first();

	boolean second();

	boolean third();

	default boolean getFirst() {
		return first();
	}

	default boolean getSecond() {
		return second();
	}

	default boolean getThird() {
		return third();
	}

	default Boolean get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			case 3 :
				return third();
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
			case 3 :
				return third();
			default :
				throw new NoSuchElementException();
		}
	}

	default int size() {
		return SIZE;
	}

	static int hashCode(boolean first, boolean second, boolean third) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Boolean.hashCode(first);
		result = prime * result + Boolean.hashCode(second);
		result = prime * result + Boolean.hashCode(third);
		return result;
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();
		i++;
		array[i] = third();

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
		i++;
		array[i] = third();

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
		i++;
		array[i] = third();

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

	interface ComparableBoolTriple extends LBoolTriple, Comparable<LBoolTriple> {

		@Override
		default int compareTo(LBoolTriple that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Boolean.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Boolean.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Boolean.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBoolTriple implements LBoolTriple {

		@Override
		public boolean equals(Object that) {
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LBoolTriple are allowed.
					if (!(two instanceof LBoolTriple)) {
						return false;
					}

					LBoolTriple other = (LBoolTriple) two;

					return one.first() == other.first() && //
							one.second() == other.second() && //
							one.third() == other.third(); //
				});
		}

		@Override
		public int hashCode() {
			return LBoolTriple.hashCode(first(), second(), third());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBoolTriple extends AbstractBoolTriple {

		private boolean first;
		private boolean second;
		private boolean third;

		public MutBoolTriple(boolean first, boolean second, boolean third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static MutBoolTriple of(boolean first, boolean second, boolean third) {
			return new MutBoolTriple(first, second, third);
		}

		public static MutBoolTriple copyOf(LBoolTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public boolean first() {
			return first;
		}

		public MutBoolTriple first(boolean first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutBoolTriple second(boolean second) {
			this.second = second;
			return this;
		}

		public boolean third() {
			return third;
		}

		public MutBoolTriple third(boolean third) {
			this.third = third;
			return this;
		}

		public void setFirst(boolean first) {
			this.first = first;
		}

		public void setSecond(boolean second) {
			this.second = second;
		}

		public void setThird(boolean third) {
			this.third = third;
		}

		public void reset() {
			first = false;
			second = false;
			third = false;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBoolTriple extends AbstractBoolTriple implements ComparableBoolTriple {

		private boolean first;
		private boolean second;
		private boolean third;

		public MutCompBoolTriple(boolean first, boolean second, boolean third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static MutCompBoolTriple of(boolean first, boolean second, boolean third) {
			return new MutCompBoolTriple(first, second, third);
		}

		public static MutCompBoolTriple copyOf(LBoolTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public boolean first() {
			return first;
		}

		public MutCompBoolTriple first(boolean first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutCompBoolTriple second(boolean second) {
			this.second = second;
			return this;
		}

		public boolean third() {
			return third;
		}

		public MutCompBoolTriple third(boolean third) {
			this.third = third;
			return this;
		}

		public void setFirst(boolean first) {
			this.first = first;
		}

		public void setSecond(boolean second) {
			this.second = second;
		}

		public void setThird(boolean third) {
			this.third = third;
		}

		public void reset() {
			first = false;
			second = false;
			third = false;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBoolTriple extends AbstractBoolTriple {

		private final boolean first;
		private final boolean second;
		private final boolean third;

		public ImmBoolTriple(boolean first, boolean second, boolean third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static ImmBoolTriple of(boolean first, boolean second, boolean third) {
			return new ImmBoolTriple(first, second, third);
		}

		public static ImmBoolTriple copyOf(LBoolTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public boolean first() {
			return first;
		}

		public boolean second() {
			return second;
		}

		public boolean third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompBoolTriple extends AbstractBoolTriple implements ComparableBoolTriple {

		private final boolean first;
		private final boolean second;
		private final boolean third;

		public ImmCompBoolTriple(boolean first, boolean second, boolean third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static ImmCompBoolTriple of(boolean first, boolean second, boolean third) {
			return new ImmCompBoolTriple(first, second, third);
		}

		public static ImmCompBoolTriple copyOf(LBoolTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public boolean first() {
			return first;
		}

		public boolean second() {
			return second;
		}

		public boolean third() {
			return third;
		}

	}

}
