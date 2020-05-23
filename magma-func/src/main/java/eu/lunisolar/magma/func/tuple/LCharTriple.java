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
public interface LCharTriple extends LTuple<Character>, Comparable<LCharTriple> {

	int SIZE = 3;

	char first();

	default char value() {
		return first();
	}

	char second();

	char third();

	@Override
	default Character get(int index) {
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

	default char getChar(int index) {
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
	@Override
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
	@Override
	default int compareTo(LCharTriple that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Character.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Character.compare(one.second(), two.second())) != 0 ? retval : //
							(retval = Character.compare(one.third(), two.third())) != 0 ? retval : 0; //
			});
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
	 * Mutable tuple.
	 */

	interface Mut<SELF extends Mut<SELF>> extends LCharTriple {

		SELF first(char first);
		SELF second(char second);
		SELF third(char third);

		default SELF setFirst(char first) {
			this.first(first);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFirstIfArg(char first, LCharPredicate predicate) {
			if (predicate.test(first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFirstIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				return this.first(func.applyAsChar(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFirstIf(LCharPredicate predicate, char first) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFirstIf(char first, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFirstIf(LBiCharPredicate predicate, char first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		default SELF setSecond(char second) {
			this.second(second);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setSecondIfArg(char second, LCharPredicate predicate) {
			if (predicate.test(second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setSecondIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				return this.second(func.applyAsChar(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setSecondIf(LCharPredicate predicate, char second) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setSecondIf(char second, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setSecondIf(LBiCharPredicate predicate, char second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF setThird(char third) {
			this.third(third);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setThirdIfArg(char third, LCharPredicate predicate) {
			if (predicate.test(third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setThirdIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				return this.third(func.applyAsChar(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setThirdIf(LCharPredicate predicate, char third) {
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setThirdIf(char third, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setThirdIf(LBiCharPredicate predicate, char third) {
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first('\u0000');
			this.second('\u0000');
			this.third('\u0000');
			return (SELF) this;
		}
	}

	public static MutCharTriple of() {
		return of('\u0000', '\u0000', '\u0000');
	}

	public static MutCharTriple of(char a1, char a2, char a3) {
		return new MutCharTriple(a1, a2, a3);
	}

	public static MutCharTriple copyOf(LCharTriple tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutCharTriple extends AbstractCharTriple implements Mut<MutCharTriple> {

		private char first;
		private char second;
		private char third;

		public MutCharTriple(char a1, char a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override char first() {
			return first;
		}

		public @Override MutCharTriple first(char first) {
			this.first = first;
			return this;
		}

		public @Override char second() {
			return second;
		}

		public @Override MutCharTriple second(char second) {
			this.second = second;
			return this;
		}

		public @Override char third() {
			return third;
		}

		public @Override MutCharTriple third(char third) {
			this.third = third;
			return this;
		}

	}

	public static ImmCharTriple immutableOf(char a1, char a2, char a3) {
		return new ImmCharTriple(a1, a2, a3);
	}

	public static ImmCharTriple immutableCopyOf(LCharTriple tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
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

		public @Override char first() {
			return first;
		}

		public @Override char second() {
			return second;
		}

		public @Override char third() {
			return third;
		}

	}

}
