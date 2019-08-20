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
 * Exact equivalent of input parameters used in LTriCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharTriple extends LTuple<Object>, LCharPair {

	int SIZE = 3;

	char first();

	default char value() {
		return first();
	}

	char second();

	char third();

	default Object get(int index) {
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

	/** Tuple size */
	default int tupleSize() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LCharTriple and calculates hash from it. */
	static int argHashCode(char a1, char a2, char a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Character.hashCode(a1);
		result = prime * result + Character.hashCode(a2);
		result = prime * result + Character.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LCharTriple and checks if all values are equal. */
	static boolean argEquals(char a1, char a2, char a3, char b1, char b2, char b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LCharTriple interface (among others) and their LCharTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LCharTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharTriple are allowed.
				if (!(two instanceof LCharTriple)) {
					return false;
				}

				LCharTriple other = (LCharTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LCharTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharTriple are allowed.
				if (!(two instanceof LCharTriple)) {
					return false;
				}

				LCharTriple other = (LCharTriple) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
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

	interface ComparableCharTriple extends LCharTriple, Comparable<LCharTriple> {

		@Override
		default int compareTo(LCharTriple that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Character.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Character.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Character.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractCharTriple implements LCharTriple {

		@Override
		public boolean equals(Object that) {
			return LCharTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LCharTriple.argHashCode(first(), second(), third());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(first());
			sb.append(',');
			sb.append(second());
			sb.append(',');
			sb.append(third());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutCharTriple extends AbstractCharTriple {

		private char first;
		private char second;
		private char third;

		public MutCharTriple(char a1, char a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutCharTriple of(char a1, char a2, char a3) {
			return new MutCharTriple(a1, a2, a3);
		}

		public static MutCharTriple copyOf(LCharTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public char first() {
			return first;
		}

		public MutCharTriple first(char first) {
			this.first = first;
			return this;
		}

		public char second() {
			return second;
		}

		public MutCharTriple second(char second) {
			this.second = second;
			return this;
		}

		public char third() {
			return third;
		}

		public MutCharTriple third(char third) {
			this.third = third;
			return this;
		}

		public MutCharTriple setFirst(char first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCharTriple setFirstIfArg(char first, LCharPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCharTriple setFirstIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCharTriple setFirstIf(LCharPredicate predicate, char first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCharTriple setFirstIf(char first, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCharTriple setFirstIf(LBiCharPredicate predicate, char first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCharTriple setSecond(char second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCharTriple setSecondIfArg(char second, LCharPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCharTriple setSecondIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCharTriple setSecondIf(LCharPredicate predicate, char second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCharTriple setSecondIf(char second, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCharTriple setSecondIf(LBiCharPredicate predicate, char second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCharTriple setThird(char third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCharTriple setThirdIfArg(char third, LCharPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCharTriple setThirdIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCharTriple setThirdIf(LCharPredicate predicate, char third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCharTriple setThirdIf(char third, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCharTriple setThirdIf(LBiCharPredicate predicate, char third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = '\u0000';
			second = '\u0000';
			third = '\u0000';
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompCharTriple extends AbstractCharTriple implements ComparableCharTriple {

		private char first;
		private char second;
		private char third;

		public MutCompCharTriple(char a1, char a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutCompCharTriple of(char a1, char a2, char a3) {
			return new MutCompCharTriple(a1, a2, a3);
		}

		public static MutCompCharTriple copyOf(LCharTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public char first() {
			return first;
		}

		public MutCompCharTriple first(char first) {
			this.first = first;
			return this;
		}

		public char second() {
			return second;
		}

		public MutCompCharTriple second(char second) {
			this.second = second;
			return this;
		}

		public char third() {
			return third;
		}

		public MutCompCharTriple third(char third) {
			this.third = third;
			return this;
		}

		public MutCompCharTriple setFirst(char first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompCharTriple setFirstIfArg(char first, LCharPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompCharTriple setFirstIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompCharTriple setFirstIf(LCharPredicate predicate, char first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompCharTriple setFirstIf(char first, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompCharTriple setFirstIf(LBiCharPredicate predicate, char first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompCharTriple setSecond(char second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompCharTriple setSecondIfArg(char second, LCharPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompCharTriple setSecondIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompCharTriple setSecondIf(LCharPredicate predicate, char second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompCharTriple setSecondIf(char second, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompCharTriple setSecondIf(LBiCharPredicate predicate, char second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompCharTriple setThird(char third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompCharTriple setThirdIfArg(char third, LCharPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompCharTriple setThirdIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompCharTriple setThirdIf(LCharPredicate predicate, char third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompCharTriple setThirdIf(char third, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompCharTriple setThirdIf(LBiCharPredicate predicate, char third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = '\u0000';
			second = '\u0000';
			third = '\u0000';
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmCharTriple extends AbstractCharTriple {

		private final char first;
		private final char second;
		private final char third;

		public ImmCharTriple(char a1, char a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmCharTriple of(char a1, char a2, char a3) {
			return new ImmCharTriple(a1, a2, a3);
		}

		public static ImmCharTriple copyOf(LCharTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public char first() {
			return first;
		}

		public char second() {
			return second;
		}

		public char third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompCharTriple extends AbstractCharTriple implements ComparableCharTriple {

		private final char first;
		private final char second;
		private final char third;

		public ImmCompCharTriple(char a1, char a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmCompCharTriple of(char a1, char a2, char a3) {
			return new ImmCompCharTriple(a1, a2, a3);
		}

		public static ImmCompCharTriple copyOf(LCharTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public char first() {
			return first;
		}

		public char second() {
			return second;
		}

		public char third() {
			return third;
		}

	}

}
