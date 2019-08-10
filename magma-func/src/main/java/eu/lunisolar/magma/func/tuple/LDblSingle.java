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
public interface LDblSingle extends LTuple<Object> {

	int SIZE = 1;

	double value();

	default double first() {
		return value();
	}

	default Object get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
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
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
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

	interface ComparableDblSingle extends LDblSingle, Comparable<LDblSingle> {

		@Override
		default int compareTo(LDblSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Double.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractDblSingle implements LDblSingle {

		@Override
		public boolean equals(Object that) {
			return LDblSingle.argEquals(this, that);
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

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutDblSingle extends AbstractDblSingle {

		private double value;

		public MutDblSingle(double a) {
			this.value = a;
		}

		public static MutDblSingle of(double a) {
			return new MutDblSingle(a);
		}

		public static MutDblSingle copyOf(LDblSingle tuple) {
			return of(tuple.value());
		}

		public double value() {
			return value;
		}

		public MutDblSingle value(double value) {
			this.value = value;
			return this;
		}

		public MutDblSingle setValue(double value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutDblSingle setValueIfArg(double value, LDblPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutDblSingle setValueIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutDblSingle setValueIf(LDblPredicate predicate, double value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutDblSingle setValueIf(double value, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutDblSingle setValueIf(LBiDblPredicate predicate, double value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = 0d;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompDblSingle extends AbstractDblSingle implements ComparableDblSingle {

		private double value;

		public MutCompDblSingle(double a) {
			this.value = a;
		}

		public static MutCompDblSingle of(double a) {
			return new MutCompDblSingle(a);
		}

		public static MutCompDblSingle copyOf(LDblSingle tuple) {
			return of(tuple.value());
		}

		public double value() {
			return value;
		}

		public MutCompDblSingle value(double value) {
			this.value = value;
			return this;
		}

		public MutCompDblSingle setValue(double value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompDblSingle setValueIfArg(double value, LDblPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompDblSingle setValueIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompDblSingle setValueIf(LDblPredicate predicate, double value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompDblSingle setValueIf(double value, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompDblSingle setValueIf(LBiDblPredicate predicate, double value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = 0d;
		}
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

		public static ImmDblSingle of(double a) {
			return new ImmDblSingle(a);
		}

		public static ImmDblSingle copyOf(LDblSingle tuple) {
			return of(tuple.value());
		}

		public double value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompDblSingle extends AbstractDblSingle implements ComparableDblSingle {

		private final double value;

		public ImmCompDblSingle(double a) {
			this.value = a;
		}

		public static ImmCompDblSingle of(double a) {
			return new ImmCompDblSingle(a);
		}

		public static ImmCompDblSingle copyOf(LDblSingle tuple) {
			return of(tuple.value());
		}

		public double value() {
			return value;
		}

	}

}
