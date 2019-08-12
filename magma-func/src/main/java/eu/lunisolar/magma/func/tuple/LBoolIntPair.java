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
 * Exact equivalent of input parameters used in LBoolIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolIntPair extends LTuple<Object>, LBoolSingle {

	int SIZE = 2;

	boolean first();

	default boolean value() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBoolIntPair and calculates hash from it. */
	static int argHashCode(boolean a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Boolean.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolIntPair and checks if all values are equal. */
	static boolean argEquals(boolean a1, int a2, boolean b1, int b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LBoolIntPair interface (among others) and their LBoolIntPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LBoolIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBoolIntPair are allowed.
				if (!(two instanceof LBoolIntPair)) {
					return false;
				}

				LBoolIntPair other = (LBoolIntPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LBoolIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBoolIntPair are allowed.
				if (!(two instanceof LBoolIntPair)) {
					return false;
				}

				LBoolIntPair other = (LBoolIntPair) two;

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

	interface ComparableBoolIntPair extends LBoolIntPair, Comparable<LBoolIntPair> {

		@Override
		default int compareTo(LBoolIntPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Boolean.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBoolIntPair implements LBoolIntPair {

		@Override
		public boolean equals(Object that) {
			return LBoolIntPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBoolIntPair.argHashCode(first(), second());
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
	final class MutBoolIntPair extends AbstractBoolIntPair {

		private boolean first;
		private int second;

		public MutBoolIntPair(boolean a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutBoolIntPair of(boolean a1, int a2) {
			return new MutBoolIntPair(a1, a2);
		}

		public static MutBoolIntPair copyOf(LBoolIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public boolean first() {
			return first;
		}

		public MutBoolIntPair first(boolean first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutBoolIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutBoolIntPair setFirst(boolean first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBoolIntPair setFirstIfArg(boolean first, LLogicalOperator predicate) {
			if (predicate.apply(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBoolIntPair setFirstIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.first = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBoolIntPair setFirstIf(LLogicalOperator predicate, boolean first) {
			if (predicate.apply(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBoolIntPair setFirstIf(boolean first, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBoolIntPair setFirstIf(LLogicalBinaryOperator predicate, boolean first) {

			if (predicate.apply(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutBoolIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBoolIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBoolIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBoolIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBoolIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBoolIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = false;
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBoolIntPair extends AbstractBoolIntPair implements ComparableBoolIntPair {

		private boolean first;
		private int second;

		public MutCompBoolIntPair(boolean a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompBoolIntPair of(boolean a1, int a2) {
			return new MutCompBoolIntPair(a1, a2);
		}

		public static MutCompBoolIntPair copyOf(LBoolIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public boolean first() {
			return first;
		}

		public MutCompBoolIntPair first(boolean first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompBoolIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutCompBoolIntPair setFirst(boolean first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBoolIntPair setFirstIfArg(boolean first, LLogicalOperator predicate) {
			if (predicate.apply(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBoolIntPair setFirstIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.first = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBoolIntPair setFirstIf(LLogicalOperator predicate, boolean first) {
			if (predicate.apply(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBoolIntPair setFirstIf(boolean first, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBoolIntPair setFirstIf(LLogicalBinaryOperator predicate, boolean first) {

			if (predicate.apply(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompBoolIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBoolIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBoolIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBoolIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBoolIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBoolIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = false;
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBoolIntPair extends AbstractBoolIntPair {

		private final boolean first;
		private final int second;

		public ImmBoolIntPair(boolean a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmBoolIntPair of(boolean a1, int a2) {
			return new ImmBoolIntPair(a1, a2);
		}

		public static ImmBoolIntPair copyOf(LBoolIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public boolean first() {
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
	final class ImmCompBoolIntPair extends AbstractBoolIntPair implements ComparableBoolIntPair {

		private final boolean first;
		private final int second;

		public ImmCompBoolIntPair(boolean a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompBoolIntPair of(boolean a1, int a2) {
			return new ImmCompBoolIntPair(a1, a2);
		}

		public static ImmCompBoolIntPair copyOf(LBoolIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public boolean first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
