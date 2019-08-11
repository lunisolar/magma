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
 * Exact equivalent of input parameters used in LSrtIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtIntPair extends LTuple<Object>, LSrtSingle {

	int SIZE = 2;

	short first();

	default short value() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LSrtIntPair and calculates hash from it. */
	static int argHashCode(short a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Short.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LSrtIntPair and checks if all values are equal. */
	static boolean argEquals(short a1, int a2, short b1, int b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LSrtIntPair interface (among others) and their LSrtIntPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LSrtIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtIntPair are allowed.
				if (!(two instanceof LSrtIntPair)) {
					return false;
				}

				LSrtIntPair other = (LSrtIntPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LSrtIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtIntPair are allowed.
				if (!(two instanceof LSrtIntPair)) {
					return false;
				}

				LSrtIntPair other = (LSrtIntPair) two;

				return the.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
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

	interface ComparableSrtIntPair extends LSrtIntPair, Comparable<LSrtIntPair> {

		@Override
		default int compareTo(LSrtIntPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Short.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractSrtIntPair implements LSrtIntPair {

		@Override
		public boolean equals(Object that) {
			return LSrtIntPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LSrtIntPair.argHashCode(first(), second());
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
	final class MutSrtIntPair extends AbstractSrtIntPair {

		private short first;
		private int second;

		public MutSrtIntPair(short a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutSrtIntPair of(short a1, int a2) {
			return new MutSrtIntPair(a1, a2);
		}

		public static MutSrtIntPair copyOf(LSrtIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public MutSrtIntPair first(short first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutSrtIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutSrtIntPair setFirst(short first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSrtIntPair setFirstIfArg(short first, LSrtPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSrtIntPair setFirstIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSrtIntPair setFirstIf(LSrtPredicate predicate, short first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSrtIntPair setFirstIf(short first, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSrtIntPair setFirstIf(LBiSrtPredicate predicate, short first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutSrtIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSrtIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSrtIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSrtIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSrtIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSrtIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = (short) 0;
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompSrtIntPair extends AbstractSrtIntPair implements ComparableSrtIntPair {

		private short first;
		private int second;

		public MutCompSrtIntPair(short a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompSrtIntPair of(short a1, int a2) {
			return new MutCompSrtIntPair(a1, a2);
		}

		public static MutCompSrtIntPair copyOf(LSrtIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public MutCompSrtIntPair first(short first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompSrtIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutCompSrtIntPair setFirst(short first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSrtIntPair setFirstIfArg(short first, LSrtPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSrtIntPair setFirstIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSrtIntPair setFirstIf(LSrtPredicate predicate, short first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSrtIntPair setFirstIf(short first, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSrtIntPair setFirstIf(LBiSrtPredicate predicate, short first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompSrtIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSrtIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSrtIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSrtIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSrtIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSrtIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = (short) 0;
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmSrtIntPair extends AbstractSrtIntPair {

		private final short first;
		private final int second;

		public ImmSrtIntPair(short a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmSrtIntPair of(short a1, int a2) {
			return new ImmSrtIntPair(a1, a2);
		}

		public static ImmSrtIntPair copyOf(LSrtIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
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
	final class ImmCompSrtIntPair extends AbstractSrtIntPair implements ComparableSrtIntPair {

		private final short first;
		private final int second;

		public ImmCompSrtIntPair(short a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompSrtIntPair of(short a1, int a2) {
			return new ImmCompSrtIntPair(a1, a2);
		}

		public static ImmCompSrtIntPair copyOf(LSrtIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
