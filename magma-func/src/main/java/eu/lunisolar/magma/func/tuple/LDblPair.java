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
 * Exact equivalent of input parameters used in LBiDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDblPair extends LTuple<Object>, LDblSingle {

	int SIZE = 2;

	double first();

	default double value() {
		return first();
	}

	double second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LDblPair and calculates hash from it. */
	static int argHashCode(double a1, double a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(a1);
		result = prime * result + Double.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LDblPair and checks if all values are equal. */
	static boolean argEquals(double a1, double a2, double b1, double b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LDblPair interface (among others) and their LDblPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LDblPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblPair are allowed.
				if (!(two instanceof LDblPair)) {
					return false;
				}

				LDblPair other = (LDblPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LDblPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblPair are allowed.
				if (!(two instanceof LDblPair)) {
					return false;
				}

				LDblPair other = (LDblPair) two;

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

	interface ComparableDblPair extends LDblPair, Comparable<LDblPair> {

		@Override
		default int compareTo(LDblPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Double.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Double.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractDblPair implements LDblPair {

		@Override
		public boolean equals(Object that) {
			return LDblPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LDblPair.argHashCode(first(), second());
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
	final class MutDblPair extends AbstractDblPair {

		private double first;
		private double second;

		public MutDblPair(double a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutDblPair of(double a1, double a2) {
			return new MutDblPair(a1, a2);
		}

		public static MutDblPair copyOf(LDblPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public MutDblPair first(double first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutDblPair second(double second) {
			this.second = second;
			return this;
		}

		public MutDblPair setFirst(double first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutDblPair setFirstIfArg(double first, LDblPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutDblPair setFirstIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutDblPair setFirstIf(LDblPredicate predicate, double first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutDblPair setFirstIf(double first, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutDblPair setFirstIf(LBiDblPredicate predicate, double first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutDblPair setSecond(double second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutDblPair setSecondIfArg(double second, LDblPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutDblPair setSecondIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutDblPair setSecondIf(LDblPredicate predicate, double second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutDblPair setSecondIf(double second, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutDblPair setSecondIf(LBiDblPredicate predicate, double second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0d;
			second = 0d;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompDblPair extends AbstractDblPair implements ComparableDblPair {

		private double first;
		private double second;

		public MutCompDblPair(double a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompDblPair of(double a1, double a2) {
			return new MutCompDblPair(a1, a2);
		}

		public static MutCompDblPair copyOf(LDblPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public MutCompDblPair first(double first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutCompDblPair second(double second) {
			this.second = second;
			return this;
		}

		public MutCompDblPair setFirst(double first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompDblPair setFirstIfArg(double first, LDblPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompDblPair setFirstIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompDblPair setFirstIf(LDblPredicate predicate, double first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompDblPair setFirstIf(double first, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompDblPair setFirstIf(LBiDblPredicate predicate, double first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompDblPair setSecond(double second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompDblPair setSecondIfArg(double second, LDblPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompDblPair setSecondIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompDblPair setSecondIf(LDblPredicate predicate, double second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompDblPair setSecondIf(double second, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompDblPair setSecondIf(LBiDblPredicate predicate, double second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0d;
			second = 0d;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmDblPair extends AbstractDblPair {

		private final double first;
		private final double second;

		public ImmDblPair(double a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmDblPair of(double a1, double a2) {
			return new ImmDblPair(a1, a2);
		}

		public static ImmDblPair copyOf(LDblPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public double second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompDblPair extends AbstractDblPair implements ComparableDblPair {

		private final double first;
		private final double second;

		public ImmCompDblPair(double a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompDblPair of(double a1, double a2) {
			return new ImmCompDblPair(a1, a2);
		}

		public static ImmCompDblPair copyOf(LDblPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public double second() {
			return second;
		}

	}

}
