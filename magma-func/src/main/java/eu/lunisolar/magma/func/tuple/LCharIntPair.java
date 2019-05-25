/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
 * Exact equivalent of input parameters used in LCharIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharIntPair extends LTuple<Object>, LCharSingle {

	int SIZE = 2;

	char first();

	default char value() {
		return first();
	}

	int second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LCharIntPair and calculates hash from it. */
	static int argHashCode(char a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Character.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LCharIntPair and checks if all values are equal. */
	static boolean argEquals(char a1, int a2, char b1, int b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LCharIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharIntPair are allowed.
				if (!(two instanceof LCharIntPair)) {
					return false;
				}

				LCharIntPair other = (LCharIntPair) two;

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

	interface ComparableCharIntPair extends LCharIntPair, Comparable<LCharIntPair> {

		@Override
		default int compareTo(LCharIntPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Character.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractCharIntPair implements LCharIntPair {

		@Override
		public boolean equals(Object that) {
			return LCharIntPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LCharIntPair.argHashCode(first(), second());
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
	final class MutCharIntPair extends AbstractCharIntPair {

		private char first;
		private int second;

		public MutCharIntPair(char a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCharIntPair of(char a1, int a2) {
			return new MutCharIntPair(a1, a2);
		}

		public static MutCharIntPair copyOf(LCharIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public char first() {
			return first;
		}

		public MutCharIntPair first(char first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCharIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutCharIntPair setFirst(char first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCharIntPair setFirstIfArg(char first, LCharPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCharIntPair setFirstIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCharIntPair setFirstIf(LCharPredicate predicate, char first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCharIntPair setFirstIf(char first, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCharIntPair setFirstIf(LBiCharPredicate predicate, char first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCharIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCharIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCharIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCharIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCharIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCharIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = '\u0000';
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompCharIntPair extends AbstractCharIntPair implements ComparableCharIntPair {

		private char first;
		private int second;

		public MutCompCharIntPair(char a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompCharIntPair of(char a1, int a2) {
			return new MutCompCharIntPair(a1, a2);
		}

		public static MutCompCharIntPair copyOf(LCharIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public char first() {
			return first;
		}

		public MutCompCharIntPair first(char first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompCharIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutCompCharIntPair setFirst(char first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompCharIntPair setFirstIfArg(char first, LCharPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompCharIntPair setFirstIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompCharIntPair setFirstIf(LCharPredicate predicate, char first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompCharIntPair setFirstIf(char first, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompCharIntPair setFirstIf(LBiCharPredicate predicate, char first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompCharIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompCharIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompCharIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompCharIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompCharIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompCharIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = '\u0000';
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmCharIntPair extends AbstractCharIntPair {

		private final char first;
		private final int second;

		public ImmCharIntPair(char a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCharIntPair of(char a1, int a2) {
			return new ImmCharIntPair(a1, a2);
		}

		public static ImmCharIntPair copyOf(LCharIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public char first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompCharIntPair extends AbstractCharIntPair implements ComparableCharIntPair {

		private final char first;
		private final int second;

		public ImmCompCharIntPair(char a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompCharIntPair of(char a1, int a2) {
			return new ImmCompCharIntPair(a1, a2);
		}

		public static ImmCompCharIntPair copyOf(LCharIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public char first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
