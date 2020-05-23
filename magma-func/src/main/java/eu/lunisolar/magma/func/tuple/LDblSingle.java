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
 * Exact equivalent of input parameters used in LDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDblSingle extends LTuple<Double>, Comparable<LDblSingle> {

	int SIZE = 1;

	double value();

	default double first() {
		return value();
	}

	@Override
	default Double get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default double getDouble(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LDblSingle and calculates hash from it. */
	static int argHashCode(double a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LDblSingle and checks if all values are equal. */
	static boolean argEquals(double a, double b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LDblSingle interface (among others) and their LDblSingle values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LDblSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblSingle are allowed.
				if (!(two instanceof LDblSingle)) {
					return false;
				}

				LDblSingle other = (LDblSingle) two;

				return argEquals(one.value(), other.value());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LDblSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblSingle are allowed.
				if (!(two instanceof LDblSingle)) {
					return false;
				}

				LDblSingle other = (LDblSingle) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.value(), other.value());
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
	default int compareTo(LDblSingle that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Double.compare(one.value(), two.value())) != 0 ? retval : 0; //
			});
	}

	abstract class AbstractDblSingle extends Number implements LDblSingle {

		@Override
		public boolean equals(Object that) {
			return LDblSingle.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LDblSingle.argHashCode(value());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(value());
			sb.append(')');
			return sb.toString();
		}

		@Override
		public byte byteValue() {
			return (byte) value();
		}

		@Override
		public short shortValue() {
			return (short) value();
		}

		@Override
		public int intValue() {
			return (int) value();
		}

		@Override
		public long longValue() {
			return (long) value();
		}

		@Override
		public float floatValue() {
			return (float) value();
		}

		@Override
		public double doubleValue() {
			return (double) value();
		}
	}

	/**
	 * Mutable tuple.
	 */

	interface Mut<SELF extends Mut<SELF>> extends LDblSingle {

		SELF value(double value);

		default SELF setValue(double value) {
			this.value(value);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setValueIfArg(double value, LDblPredicate predicate) {
			if (predicate.test(value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setValueIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				return this.value(func.applyAsDbl(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setValueIf(LDblPredicate predicate, double value) {
			if (predicate.test(this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setValueIf(double value, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setValueIf(LBiDblPredicate predicate, double value) {
			if (predicate.test(this.value(), value)) {
				return this.value(value);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.value(0d);
			return (SELF) this;
		}
	}

	public static MutDblSingle of() {
		return of(0d);
	}

	public static MutDblSingle of(double a) {
		return new MutDblSingle(a);
	}

	public static MutDblSingle copyOf(LDblSingle tuple) {
		return of(tuple.value());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutDblSingle extends AbstractDblSingle implements Mut<MutDblSingle> {

		private double value;

		public MutDblSingle(double a) {
			this.value = a;
		}

		public @Override double value() {
			return value;
		}

		public @Override MutDblSingle value(double value) {
			this.value = value;
			return this;
		}

	}

	public static ImmDblSingle immutableOf(double a) {
		return new ImmDblSingle(a);
	}

	public static ImmDblSingle immutableCopyOf(LDblSingle tuple) {
		return immutableOf(tuple.value());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmDblSingle extends AbstractDblSingle {

		private final double value;

		public ImmDblSingle(double a) {
			this.value = a;
		}

		public @Override double value() {
			return value;
		}

	}

}
