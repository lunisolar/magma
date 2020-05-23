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
public interface LDblTriple extends LTuple<Double>, Comparable<LDblTriple> {

	int SIZE = 3;

	double first();

	default double value() {
		return first();
	}

	double second();

	double third();

	@Override
	default Double get(int index) {
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

	default double getDouble(int index) {
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

	@Override
	default Iterator<Double> iterator() {
		return new Iterator<Double>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Double next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfDouble doubleIterator() {
		return new PrimitiveIterator.OfDouble() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public double nextDouble() {
				index++;
				return getDouble(index);
			}
		};
	}
	@Override
	default int compareTo(LDblTriple that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Double.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Double.compare(one.second(), two.second())) != 0 ? retval : //
							(retval = Double.compare(one.third(), two.third())) != 0 ? retval : 0; //
			});
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
	 * Mutable tuple.
	 */

	interface Mut<SELF extends Mut<SELF>> extends LDblTriple {

		SELF first(double first);
		SELF second(double second);
		SELF third(double third);

		default SELF setFirst(double first) {
			this.first(first);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFirstIfArg(double first, LDblPredicate predicate) {
			if (predicate.test(first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFirstIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				return this.first(func.applyAsDbl(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFirstIf(LDblPredicate predicate, double first) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFirstIf(double first, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFirstIf(LBiDblPredicate predicate, double first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		default SELF setSecond(double second) {
			this.second(second);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setSecondIfArg(double second, LDblPredicate predicate) {
			if (predicate.test(second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setSecondIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				return this.second(func.applyAsDbl(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setSecondIf(LDblPredicate predicate, double second) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setSecondIf(double second, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setSecondIf(LBiDblPredicate predicate, double second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF setThird(double third) {
			this.third(third);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setThirdIfArg(double third, LDblPredicate predicate) {
			if (predicate.test(third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setThirdIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				return this.third(func.applyAsDbl(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setThirdIf(LDblPredicate predicate, double third) {
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setThirdIf(double third, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setThirdIf(LBiDblPredicate predicate, double third) {
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(0d);
			this.second(0d);
			this.third(0d);
			return (SELF) this;
		}
	}

	public static MutDblTriple of() {
		return of(0d, 0d, 0d);
	}

	public static MutDblTriple of(double a1, double a2, double a3) {
		return new MutDblTriple(a1, a2, a3);
	}

	public static MutDblTriple copyOf(LDblTriple tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutDblTriple extends AbstractDblTriple implements Mut<MutDblTriple> {

		private double first;
		private double second;
		private double third;

		public MutDblTriple(double a1, double a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override double first() {
			return first;
		}

		public @Override MutDblTriple first(double first) {
			this.first = first;
			return this;
		}

		public @Override double second() {
			return second;
		}

		public @Override MutDblTriple second(double second) {
			this.second = second;
			return this;
		}

		public @Override double third() {
			return third;
		}

		public @Override MutDblTriple third(double third) {
			this.third = third;
			return this;
		}

	}

	public static ImmDblTriple immutableOf(double a1, double a2, double a3) {
		return new ImmDblTriple(a1, a2, a3);
	}

	public static ImmDblTriple immutableCopyOf(LDblTriple tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
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

		public @Override double first() {
			return first;
		}

		public @Override double second() {
			return second;
		}

		public @Override double third() {
			return third;
		}

	}

}
