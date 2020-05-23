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
 * Exact equivalent of input parameters used in LCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharSingle extends LTuple<Character>, Comparable<LCharSingle> {

	int SIZE = 1;

	char value();

	default char first() {
		return value();
	}

	@Override
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
	@Override
	default int tupleSize() {
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
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LCharSingle interface (among others) and their LCharSingle values are equal regardless of the implementing class
	 * and how many more values there are.
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

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LCharSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharSingle are allowed.
				if (!(two instanceof LCharSingle)) {
					return false;
				}

				LCharSingle other = (LCharSingle) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.value(), other.value());
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
	default int compareTo(LCharSingle that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Character.compare(one.value(), two.value())) != 0 ? retval : 0; //
			});
	}

	abstract class AbstractCharSingle implements LCharSingle {

		@Override
		public boolean equals(Object that) {
			return LCharSingle.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LCharSingle.argHashCode(value());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(value());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable tuple.
	 */

	interface Mut<SELF extends Mut<SELF>> extends LCharSingle {

		SELF value(char value);

		default SELF setValue(char value) {
			this.value(value);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setValueIfArg(char value, LCharPredicate predicate) {
			if (predicate.test(value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setValueIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				return this.value(func.applyAsChar(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setValueIf(LCharPredicate predicate, char value) {
			if (predicate.test(this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setValueIf(char value, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setValueIf(LBiCharPredicate predicate, char value) {
			if (predicate.test(this.value(), value)) {
				return this.value(value);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.value('\u0000');
			return (SELF) this;
		}
	}

	public static MutCharSingle of() {
		return of('\u0000');
	}

	public static MutCharSingle of(char a) {
		return new MutCharSingle(a);
	}

	public static MutCharSingle copyOf(LCharSingle tuple) {
		return of(tuple.value());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutCharSingle extends AbstractCharSingle implements Mut<MutCharSingle> {

		private char value;

		public MutCharSingle(char a) {
			this.value = a;
		}

		public @Override char value() {
			return value;
		}

		public @Override MutCharSingle value(char value) {
			this.value = value;
			return this;
		}

	}

	public static ImmCharSingle immutableOf(char a) {
		return new ImmCharSingle(a);
	}

	public static ImmCharSingle immutableCopyOf(LCharSingle tuple) {
		return immutableOf(tuple.value());
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

		public @Override char value() {
			return value;
		}

	}

}
