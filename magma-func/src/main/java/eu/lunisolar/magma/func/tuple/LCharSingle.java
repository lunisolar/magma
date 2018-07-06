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

package eu.lunisolar.magma.func.tuple;

import eu.lunisolar.magma.basics.meta.LTuple;
import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.operator.unary.*;
import eu.lunisolar.magma.func.operator.binary.*;
import eu.lunisolar.magma.func.predicate.*;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.*;

/**
 * Exact equivalent of input parameters used in LCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharSingle extends LTuple<Character> {

	int SIZE = 1;

	char value();

	default char getValue() {
		return value();
	}

	default Character get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default char getChar(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LCharSingle and calculates hash from it. */
	static int argHashCode(char a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Character.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LCharSingle and checks if all values are equal. */
	static boolean argEquals(char a, char b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LCharSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharSingle are allowed.
				if (!(two instanceof LCharSingle)) {
					return false;
				}

				LCharSingle other = (LCharSingle) two;

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

	default Character[] toVoArray(Character[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default Character[] toVoArray(Character[] array) {
		return toVoArray(array, 0);
	}

	default Character[] toVoArray() {
		Character[] array = new Character[size()];

		return toVoArray(array);
	}

	default char[] toCharArray(char[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default char[] toCharArray(char[] array) {
		return toCharArray(array, 0);
	}

	default char[] toCharArray() {
		char[] array = new char[size()];

		return toCharArray(array);
	}

	@Override
	default Iterator<Character> iterator() {
		return new Iterator<Character>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Character next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfInt intIterator() {
		return new PrimitiveIterator.OfInt() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public int nextInt() {
				index++;
				return getChar(index);
			}
		};
	}

	interface ComparableCharSingle extends LCharSingle, Comparable<LCharSingle> {

		@Override
		default int compareTo(LCharSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Character.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractCharSingle implements LCharSingle {

		@Override
		public boolean equals(Object that) {
			return LCharSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LCharSingle.argHashCode(value());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(getValue());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutCharSingle extends AbstractCharSingle {

		private char value;

		public MutCharSingle(char a) {
			this.value = a;
		}

		public static MutCharSingle of(char a) {
			return new MutCharSingle(a);
		}

		public static MutCharSingle copyOf(LCharSingle tuple) {
			return of(tuple.value());
		}

		public char value() {
			return value;
		}

		public MutCharSingle value(char value) {
			this.value = value;
			return this;
		}

		public MutCharSingle setValue(char value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCharSingle setValueIfArg(char value, LCharPredicate predicate) {
			if (predicate.doTest(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCharSingle setValueIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.value = func.doApplyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCharSingle setValueIf(LCharPredicate predicate, char value) {
			if (predicate.doTest(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCharSingle setValueIf(char value, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCharSingle setValueIf(LBiCharPredicate predicate, char value) {

			if (predicate.doTest(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = '\u0000';
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompCharSingle extends AbstractCharSingle implements ComparableCharSingle {

		private char value;

		public MutCompCharSingle(char a) {
			this.value = a;
		}

		public static MutCompCharSingle of(char a) {
			return new MutCompCharSingle(a);
		}

		public static MutCompCharSingle copyOf(LCharSingle tuple) {
			return of(tuple.value());
		}

		public char value() {
			return value;
		}

		public MutCompCharSingle value(char value) {
			this.value = value;
			return this;
		}

		public MutCompCharSingle setValue(char value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompCharSingle setValueIfArg(char value, LCharPredicate predicate) {
			if (predicate.doTest(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompCharSingle setValueIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.value = func.doApplyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompCharSingle setValueIf(LCharPredicate predicate, char value) {
			if (predicate.doTest(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompCharSingle setValueIf(char value, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompCharSingle setValueIf(LBiCharPredicate predicate, char value) {

			if (predicate.doTest(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = '\u0000';
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmCharSingle extends AbstractCharSingle {

		private final char value;

		public ImmCharSingle(char a) {
			this.value = a;
		}

		public static ImmCharSingle of(char a) {
			return new ImmCharSingle(a);
		}

		public static ImmCharSingle copyOf(LCharSingle tuple) {
			return of(tuple.value());
		}

		public char value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompCharSingle extends AbstractCharSingle implements ComparableCharSingle {

		private final char value;

		public ImmCompCharSingle(char a) {
			this.value = a;
		}

		public static ImmCompCharSingle of(char a) {
			return new ImmCompCharSingle(a);
		}

		public static ImmCompCharSingle copyOf(LCharSingle tuple) {
			return of(tuple.value());
		}

		public char value() {
			return value;
		}

	}

}
