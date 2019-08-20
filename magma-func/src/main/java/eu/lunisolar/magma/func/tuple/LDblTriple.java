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
 * Exact equivalent of input parameters used in LTriDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDblTriple extends LTuple<Object>, LDblPair {

	int SIZE = 3;

	double first();

	default double value() {
		return first();
	}

	double second();

	double third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LDblTriple and calculates hash from it. */
	static int argHashCode(double a1, double a2, double a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(a1);
		result = prime * result + Double.hashCode(a2);
		result = prime * result + Double.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LDblTriple and checks if all values are equal. */
	static boolean argEquals(double a1, double a2, double a3, double b1, double b2, double b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LDblTriple interface (among others) and their LDblTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LDblTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblTriple are allowed.
				if (!(two instanceof LDblTriple)) {
					return false;
				}

				LDblTriple other = (LDblTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LDblTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblTriple are allowed.
				if (!(two instanceof LDblTriple)) {
					return false;
				}

				LDblTriple other = (LDblTriple) two;

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

	interface ComparableDblTriple extends LDblTriple, Comparable<LDblTriple> {

		@Override
		default int compareTo(LDblTriple that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Double.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Double.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Double.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractDblTriple implements LDblTriple {

		@Override
		public boolean equals(Object that) {
			return LDblTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LDblTriple.argHashCode(first(), second(), third());
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
	final class MutDblTriple extends AbstractDblTriple {

		private double first;
		private double second;
		private double third;

		public MutDblTriple(double a1, double a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutDblTriple of(double a1, double a2, double a3) {
			return new MutDblTriple(a1, a2, a3);
		}

		public static MutDblTriple copyOf(LDblTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public double first() {
			return first;
		}

		public MutDblTriple first(double first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutDblTriple second(double second) {
			this.second = second;
			return this;
		}

		public double third() {
			return third;
		}

		public MutDblTriple third(double third) {
			this.third = third;
			return this;
		}

		public MutDblTriple setFirst(double first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutDblTriple setFirstIfArg(double first, LDblPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutDblTriple setFirstIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutDblTriple setFirstIf(LDblPredicate predicate, double first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutDblTriple setFirstIf(double first, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutDblTriple setFirstIf(LBiDblPredicate predicate, double first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutDblTriple setSecond(double second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutDblTriple setSecondIfArg(double second, LDblPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutDblTriple setSecondIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutDblTriple setSecondIf(LDblPredicate predicate, double second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutDblTriple setSecondIf(double second, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutDblTriple setSecondIf(LBiDblPredicate predicate, double second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutDblTriple setThird(double third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutDblTriple setThirdIfArg(double third, LDblPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutDblTriple setThirdIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutDblTriple setThirdIf(LDblPredicate predicate, double third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutDblTriple setThirdIf(double third, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutDblTriple setThirdIf(LBiDblPredicate predicate, double third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = 0d;
			second = 0d;
			third = 0d;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompDblTriple extends AbstractDblTriple implements ComparableDblTriple {

		private double first;
		private double second;
		private double third;

		public MutCompDblTriple(double a1, double a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutCompDblTriple of(double a1, double a2, double a3) {
			return new MutCompDblTriple(a1, a2, a3);
		}

		public static MutCompDblTriple copyOf(LDblTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public double first() {
			return first;
		}

		public MutCompDblTriple first(double first) {
			this.first = first;
			return this;
		}

		public double second() {
			return second;
		}

		public MutCompDblTriple second(double second) {
			this.second = second;
			return this;
		}

		public double third() {
			return third;
		}

		public MutCompDblTriple third(double third) {
			this.third = third;
			return this;
		}

		public MutCompDblTriple setFirst(double first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompDblTriple setFirstIfArg(double first, LDblPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompDblTriple setFirstIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompDblTriple setFirstIf(LDblPredicate predicate, double first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompDblTriple setFirstIf(double first, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompDblTriple setFirstIf(LBiDblPredicate predicate, double first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompDblTriple setSecond(double second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompDblTriple setSecondIfArg(double second, LDblPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompDblTriple setSecondIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompDblTriple setSecondIf(LDblPredicate predicate, double second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompDblTriple setSecondIf(double second, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompDblTriple setSecondIf(LBiDblPredicate predicate, double second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompDblTriple setThird(double third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompDblTriple setThirdIfArg(double third, LDblPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompDblTriple setThirdIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompDblTriple setThirdIf(LDblPredicate predicate, double third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompDblTriple setThirdIf(double third, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompDblTriple setThirdIf(LBiDblPredicate predicate, double third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = 0d;
			second = 0d;
			third = 0d;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmDblTriple extends AbstractDblTriple {

		private final double first;
		private final double second;
		private final double third;

		public ImmDblTriple(double a1, double a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmDblTriple of(double a1, double a2, double a3) {
			return new ImmDblTriple(a1, a2, a3);
		}

		public static ImmDblTriple copyOf(LDblTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public double first() {
			return first;
		}

		public double second() {
			return second;
		}

		public double third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompDblTriple extends AbstractDblTriple implements ComparableDblTriple {

		private final double first;
		private final double second;
		private final double third;

		public ImmCompDblTriple(double a1, double a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmCompDblTriple of(double a1, double a2, double a3) {
			return new ImmCompDblTriple(a1, a2, a3);
		}

		public static ImmCompDblTriple copyOf(LDblTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public double first() {
			return first;
		}

		public double second() {
			return second;
		}

		public double third() {
			return third;
		}

	}

}
