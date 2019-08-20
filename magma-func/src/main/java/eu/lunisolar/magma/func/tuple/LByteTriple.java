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
 * Exact equivalent of input parameters used in LTriByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LByteTriple extends LTuple<Object>, LBytePair {

	int SIZE = 3;

	byte first();

	default byte value() {
		return first();
	}

	byte second();

	byte third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LByteTriple and calculates hash from it. */
	static int argHashCode(byte a1, byte a2, byte a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Byte.hashCode(a1);
		result = prime * result + Byte.hashCode(a2);
		result = prime * result + Byte.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LByteTriple and checks if all values are equal. */
	static boolean argEquals(byte a1, byte a2, byte a3, byte b1, byte b2, byte b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LByteTriple interface (among others) and their LByteTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LByteTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LByteTriple are allowed.
				if (!(two instanceof LByteTriple)) {
					return false;
				}

				LByteTriple other = (LByteTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LByteTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LByteTriple are allowed.
				if (!(two instanceof LByteTriple)) {
					return false;
				}

				LByteTriple other = (LByteTriple) two;

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

	interface ComparableByteTriple extends LByteTriple, Comparable<LByteTriple> {

		@Override
		default int compareTo(LByteTriple that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Byte.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Byte.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Byte.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractByteTriple implements LByteTriple {

		@Override
		public boolean equals(Object that) {
			return LByteTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LByteTriple.argHashCode(first(), second(), third());
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
	final class MutByteTriple extends AbstractByteTriple {

		private byte first;
		private byte second;
		private byte third;

		public MutByteTriple(byte a1, byte a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutByteTriple of(byte a1, byte a2, byte a3) {
			return new MutByteTriple(a1, a2, a3);
		}

		public static MutByteTriple copyOf(LByteTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public byte first() {
			return first;
		}

		public MutByteTriple first(byte first) {
			this.first = first;
			return this;
		}

		public byte second() {
			return second;
		}

		public MutByteTriple second(byte second) {
			this.second = second;
			return this;
		}

		public byte third() {
			return third;
		}

		public MutByteTriple third(byte third) {
			this.third = third;
			return this;
		}

		public MutByteTriple setFirst(byte first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutByteTriple setFirstIfArg(byte first, LBytePredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutByteTriple setFirstIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutByteTriple setFirstIf(LBytePredicate predicate, byte first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutByteTriple setFirstIf(byte first, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutByteTriple setFirstIf(LBiBytePredicate predicate, byte first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutByteTriple setSecond(byte second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutByteTriple setSecondIfArg(byte second, LBytePredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutByteTriple setSecondIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutByteTriple setSecondIf(LBytePredicate predicate, byte second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutByteTriple setSecondIf(byte second, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutByteTriple setSecondIf(LBiBytePredicate predicate, byte second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutByteTriple setThird(byte third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutByteTriple setThirdIfArg(byte third, LBytePredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutByteTriple setThirdIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutByteTriple setThirdIf(LBytePredicate predicate, byte third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutByteTriple setThirdIf(byte third, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutByteTriple setThirdIf(LBiBytePredicate predicate, byte third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = (byte) 0;
			second = (byte) 0;
			third = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompByteTriple extends AbstractByteTriple implements ComparableByteTriple {

		private byte first;
		private byte second;
		private byte third;

		public MutCompByteTriple(byte a1, byte a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutCompByteTriple of(byte a1, byte a2, byte a3) {
			return new MutCompByteTriple(a1, a2, a3);
		}

		public static MutCompByteTriple copyOf(LByteTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public byte first() {
			return first;
		}

		public MutCompByteTriple first(byte first) {
			this.first = first;
			return this;
		}

		public byte second() {
			return second;
		}

		public MutCompByteTriple second(byte second) {
			this.second = second;
			return this;
		}

		public byte third() {
			return third;
		}

		public MutCompByteTriple third(byte third) {
			this.third = third;
			return this;
		}

		public MutCompByteTriple setFirst(byte first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompByteTriple setFirstIfArg(byte first, LBytePredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompByteTriple setFirstIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompByteTriple setFirstIf(LBytePredicate predicate, byte first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompByteTriple setFirstIf(byte first, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompByteTriple setFirstIf(LBiBytePredicate predicate, byte first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompByteTriple setSecond(byte second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompByteTriple setSecondIfArg(byte second, LBytePredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompByteTriple setSecondIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompByteTriple setSecondIf(LBytePredicate predicate, byte second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompByteTriple setSecondIf(byte second, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompByteTriple setSecondIf(LBiBytePredicate predicate, byte second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompByteTriple setThird(byte third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompByteTriple setThirdIfArg(byte third, LBytePredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompByteTriple setThirdIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompByteTriple setThirdIf(LBytePredicate predicate, byte third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompByteTriple setThirdIf(byte third, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompByteTriple setThirdIf(LBiBytePredicate predicate, byte third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = (byte) 0;
			second = (byte) 0;
			third = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmByteTriple extends AbstractByteTriple {

		private final byte first;
		private final byte second;
		private final byte third;

		public ImmByteTriple(byte a1, byte a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmByteTriple of(byte a1, byte a2, byte a3) {
			return new ImmByteTriple(a1, a2, a3);
		}

		public static ImmByteTriple copyOf(LByteTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public byte first() {
			return first;
		}

		public byte second() {
			return second;
		}

		public byte third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompByteTriple extends AbstractByteTriple implements ComparableByteTriple {

		private final byte first;
		private final byte second;
		private final byte third;

		public ImmCompByteTriple(byte a1, byte a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmCompByteTriple of(byte a1, byte a2, byte a3) {
			return new ImmCompByteTriple(a1, a2, a3);
		}

		public static ImmCompByteTriple copyOf(LByteTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public byte first() {
			return first;
		}

		public byte second() {
			return second;
		}

		public byte third() {
			return third;
		}

	}

}
