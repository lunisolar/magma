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
 * Exact equivalent of input parameters used in LBiCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharPair extends LTuple<Object>, LCharSingle {

	int SIZE = 2;

	char first();

	default char value() {
		return first();
	}

	char second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LCharPair and calculates hash from it. */
	static int argHashCode(char a1, char a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Character.hashCode(a1);
		result = prime * result + Character.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LCharPair and checks if all values are equal. */
	static boolean argEquals(char a1, char a2, char b1, char b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LCharPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharPair are allowed.
				if (!(two instanceof LCharPair)) {
					return false;
				}

				LCharPair other = (LCharPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

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

	interface ComparableCharPair extends LCharPair, Comparable<LCharPair> {

		@Override
		default int compareTo(LCharPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Character.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Character.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractCharPair implements LCharPair {

		@Override
		public boolean equals(Object that) {
			return LCharPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LCharPair.argHashCode(first(), second());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(first());
			sb.append(',');
			sb.append(second());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutCharPair extends AbstractCharPair {

		private char first;
		private char second;

		public MutCharPair(char a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCharPair of(char a1, char a2) {
			return new MutCharPair(a1, a2);
		}

		public static MutCharPair copyOf(LCharPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public char first() {
			return first;
		}

		public MutCharPair first(char first) {
			this.first = first;
			return this;
		}

		public char second() {
			return second;
		}

		public MutCharPair second(char second) {
			this.second = second;
			return this;
		}

		public MutCharPair setFirst(char first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCharPair setFirstIfArg(char first, LCharPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCharPair setFirstIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCharPair setFirstIf(LCharPredicate predicate, char first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCharPair setFirstIf(char first, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCharPair setFirstIf(LBiCharPredicate predicate, char first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCharPair setSecond(char second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCharPair setSecondIfArg(char second, LCharPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCharPair setSecondIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCharPair setSecondIf(LCharPredicate predicate, char second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCharPair setSecondIf(char second, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCharPair setSecondIf(LBiCharPredicate predicate, char second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = '\u0000';
			second = '\u0000';
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompCharPair extends AbstractCharPair implements ComparableCharPair {

		private char first;
		private char second;

		public MutCompCharPair(char a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompCharPair of(char a1, char a2) {
			return new MutCompCharPair(a1, a2);
		}

		public static MutCompCharPair copyOf(LCharPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public char first() {
			return first;
		}

		public MutCompCharPair first(char first) {
			this.first = first;
			return this;
		}

		public char second() {
			return second;
		}

		public MutCompCharPair second(char second) {
			this.second = second;
			return this;
		}

		public MutCompCharPair setFirst(char first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompCharPair setFirstIfArg(char first, LCharPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompCharPair setFirstIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompCharPair setFirstIf(LCharPredicate predicate, char first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompCharPair setFirstIf(char first, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompCharPair setFirstIf(LBiCharPredicate predicate, char first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompCharPair setSecond(char second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompCharPair setSecondIfArg(char second, LCharPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompCharPair setSecondIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompCharPair setSecondIf(LCharPredicate predicate, char second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompCharPair setSecondIf(char second, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompCharPair setSecondIf(LBiCharPredicate predicate, char second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = '\u0000';
			second = '\u0000';
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmCharPair extends AbstractCharPair {

		private final char first;
		private final char second;

		public ImmCharPair(char a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCharPair of(char a1, char a2) {
			return new ImmCharPair(a1, a2);
		}

		public static ImmCharPair copyOf(LCharPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public char first() {
			return first;
		}

		public char second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompCharPair extends AbstractCharPair implements ComparableCharPair {

		private final char first;
		private final char second;

		public ImmCompCharPair(char a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompCharPair of(char a1, char a2) {
			return new ImmCompCharPair(a1, a2);
		}

		public static ImmCompCharPair copyOf(LCharPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public char first() {
			return first;
		}

		public char second() {
			return second;
		}

	}

}
