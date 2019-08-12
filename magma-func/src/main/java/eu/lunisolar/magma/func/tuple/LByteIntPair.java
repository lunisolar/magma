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
 * Exact equivalent of input parameters used in LByteIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LByteIntPair extends LTuple<Object>, LByteSingle {

	int SIZE = 2;

	byte first();

	default byte value() {
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
	default int tupleSize() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LByteIntPair and calculates hash from it. */
	static int argHashCode(byte a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Byte.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LByteIntPair and checks if all values are equal. */
	static boolean argEquals(byte a1, int a2, byte b1, int b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LByteIntPair interface (among others) and their LByteIntPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LByteIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LByteIntPair are allowed.
				if (!(two instanceof LByteIntPair)) {
					return false;
				}

				LByteIntPair other = (LByteIntPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LByteIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LByteIntPair are allowed.
				if (!(two instanceof LByteIntPair)) {
					return false;
				}

				LByteIntPair other = (LByteIntPair) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
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

	interface ComparableByteIntPair extends LByteIntPair, Comparable<LByteIntPair> {

		@Override
		default int compareTo(LByteIntPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Byte.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractByteIntPair implements LByteIntPair {

		@Override
		public boolean equals(Object that) {
			return LByteIntPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LByteIntPair.argHashCode(first(), second());
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
	final class MutByteIntPair extends AbstractByteIntPair {

		private byte first;
		private int second;

		public MutByteIntPair(byte a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutByteIntPair of(byte a1, int a2) {
			return new MutByteIntPair(a1, a2);
		}

		public static MutByteIntPair copyOf(LByteIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public byte first() {
			return first;
		}

		public MutByteIntPair first(byte first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutByteIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutByteIntPair setFirst(byte first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutByteIntPair setFirstIfArg(byte first, LBytePredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutByteIntPair setFirstIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutByteIntPair setFirstIf(LBytePredicate predicate, byte first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutByteIntPair setFirstIf(byte first, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutByteIntPair setFirstIf(LBiBytePredicate predicate, byte first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutByteIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutByteIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutByteIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutByteIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutByteIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutByteIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = (byte) 0;
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompByteIntPair extends AbstractByteIntPair implements ComparableByteIntPair {

		private byte first;
		private int second;

		public MutCompByteIntPair(byte a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompByteIntPair of(byte a1, int a2) {
			return new MutCompByteIntPair(a1, a2);
		}

		public static MutCompByteIntPair copyOf(LByteIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public byte first() {
			return first;
		}

		public MutCompByteIntPair first(byte first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompByteIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutCompByteIntPair setFirst(byte first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompByteIntPair setFirstIfArg(byte first, LBytePredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompByteIntPair setFirstIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompByteIntPair setFirstIf(LBytePredicate predicate, byte first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompByteIntPair setFirstIf(byte first, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompByteIntPair setFirstIf(LBiBytePredicate predicate, byte first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompByteIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompByteIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompByteIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompByteIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompByteIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompByteIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = (byte) 0;
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmByteIntPair extends AbstractByteIntPair {

		private final byte first;
		private final int second;

		public ImmByteIntPair(byte a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmByteIntPair of(byte a1, int a2) {
			return new ImmByteIntPair(a1, a2);
		}

		public static ImmByteIntPair copyOf(LByteIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public byte first() {
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
	final class ImmCompByteIntPair extends AbstractByteIntPair implements ComparableByteIntPair {

		private final byte first;
		private final int second;

		public ImmCompByteIntPair(byte a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompByteIntPair of(byte a1, int a2) {
			return new ImmCompByteIntPair(a1, a2);
		}

		public static ImmCompByteIntPair copyOf(LByteIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public byte first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
