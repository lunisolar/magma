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
 * Exact equivalent of input parameters used in LTriSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtTriple extends LTuple<Object>, LSrtPair {

	int SIZE = 3;

	short first();

	default short value() {
		return first();
	}

	short second();

	short third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LSrtTriple and calculates hash from it. */
	static int argHashCode(short a1, short a2, short a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Short.hashCode(a1);
		result = prime * result + Short.hashCode(a2);
		result = prime * result + Short.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LSrtTriple and checks if all values are equal. */
	static boolean argEquals(short a1, short a2, short a3, short b1, short b2, short b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LSrtTriple interface (among others) and their LSrtTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LSrtTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtTriple are allowed.
				if (!(two instanceof LSrtTriple)) {
					return false;
				}

				LSrtTriple other = (LSrtTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LSrtTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtTriple are allowed.
				if (!(two instanceof LSrtTriple)) {
					return false;
				}

				LSrtTriple other = (LSrtTriple) two;

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

	interface ComparableSrtTriple extends LSrtTriple, Comparable<LSrtTriple> {

		@Override
		default int compareTo(LSrtTriple that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Short.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Short.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Short.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractSrtTriple implements LSrtTriple {

		@Override
		public boolean equals(Object that) {
			return LSrtTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LSrtTriple.argHashCode(first(), second(), third());
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
	final class MutSrtTriple extends AbstractSrtTriple {

		private short first;
		private short second;
		private short third;

		public MutSrtTriple(short a1, short a2, short a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutSrtTriple of(short a1, short a2, short a3) {
			return new MutSrtTriple(a1, a2, a3);
		}

		public static MutSrtTriple copyOf(LSrtTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public short first() {
			return first;
		}

		public MutSrtTriple first(short first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutSrtTriple second(short second) {
			this.second = second;
			return this;
		}

		public short third() {
			return third;
		}

		public MutSrtTriple third(short third) {
			this.third = third;
			return this;
		}

		public MutSrtTriple setFirst(short first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSrtTriple setFirstIfArg(short first, LSrtPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSrtTriple setFirstIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSrtTriple setFirstIf(LSrtPredicate predicate, short first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSrtTriple setFirstIf(short first, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSrtTriple setFirstIf(LBiSrtPredicate predicate, short first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutSrtTriple setSecond(short second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSrtTriple setSecondIfArg(short second, LSrtPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSrtTriple setSecondIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSrtTriple setSecondIf(LSrtPredicate predicate, short second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSrtTriple setSecondIf(short second, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSrtTriple setSecondIf(LBiSrtPredicate predicate, short second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutSrtTriple setThird(short third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSrtTriple setThirdIfArg(short third, LSrtPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSrtTriple setThirdIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSrtTriple setThirdIf(LSrtPredicate predicate, short third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSrtTriple setThirdIf(short third, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSrtTriple setThirdIf(LBiSrtPredicate predicate, short third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = (short) 0;
			second = (short) 0;
			third = (short) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompSrtTriple extends AbstractSrtTriple implements ComparableSrtTriple {

		private short first;
		private short second;
		private short third;

		public MutCompSrtTriple(short a1, short a2, short a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutCompSrtTriple of(short a1, short a2, short a3) {
			return new MutCompSrtTriple(a1, a2, a3);
		}

		public static MutCompSrtTriple copyOf(LSrtTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public short first() {
			return first;
		}

		public MutCompSrtTriple first(short first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutCompSrtTriple second(short second) {
			this.second = second;
			return this;
		}

		public short third() {
			return third;
		}

		public MutCompSrtTriple third(short third) {
			this.third = third;
			return this;
		}

		public MutCompSrtTriple setFirst(short first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSrtTriple setFirstIfArg(short first, LSrtPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSrtTriple setFirstIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSrtTriple setFirstIf(LSrtPredicate predicate, short first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSrtTriple setFirstIf(short first, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSrtTriple setFirstIf(LBiSrtPredicate predicate, short first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompSrtTriple setSecond(short second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSrtTriple setSecondIfArg(short second, LSrtPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSrtTriple setSecondIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSrtTriple setSecondIf(LSrtPredicate predicate, short second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSrtTriple setSecondIf(short second, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSrtTriple setSecondIf(LBiSrtPredicate predicate, short second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompSrtTriple setThird(short third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSrtTriple setThirdIfArg(short third, LSrtPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSrtTriple setThirdIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSrtTriple setThirdIf(LSrtPredicate predicate, short third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSrtTriple setThirdIf(short third, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSrtTriple setThirdIf(LBiSrtPredicate predicate, short third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = (short) 0;
			second = (short) 0;
			third = (short) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmSrtTriple extends AbstractSrtTriple {

		private final short first;
		private final short second;
		private final short third;

		public ImmSrtTriple(short a1, short a2, short a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmSrtTriple of(short a1, short a2, short a3) {
			return new ImmSrtTriple(a1, a2, a3);
		}

		public static ImmSrtTriple copyOf(LSrtTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public short first() {
			return first;
		}

		public short second() {
			return second;
		}

		public short third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompSrtTriple extends AbstractSrtTriple implements ComparableSrtTriple {

		private final short first;
		private final short second;
		private final short third;

		public ImmCompSrtTriple(short a1, short a2, short a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmCompSrtTriple of(short a1, short a2, short a3) {
			return new ImmCompSrtTriple(a1, a2, a3);
		}

		public static ImmCompSrtTriple copyOf(LSrtTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public short first() {
			return first;
		}

		public short second() {
			return second;
		}

		public short third() {
			return third;
		}

	}

}
