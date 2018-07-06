/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
 * Exact equivalent of input parameters used in LBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolSingle extends LTuple<Boolean> {

	int SIZE = 1;

	boolean value();

	default boolean getValue() {
		return value();
	}

	default Boolean get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default boolean getBoolean(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LBoolSingle and calculates hash from it. */
	static int argHashCode(boolean a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Boolean.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolSingle and checks if all values are equal. */
	static boolean argEquals(boolean a, boolean b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LBoolSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBoolSingle are allowed.
				if (!(two instanceof LBoolSingle)) {
					return false;
				}

				LBoolSingle other = (LBoolSingle) two;

				return argEquals(one.value(), other.value());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default Object[] toArray(Object[] array) {
		return toArray(array, 0);
	}

	default Object[] toArray() {
		Object[] array = new Object[size()];

		return toArray(array);
	}

	default Boolean[] toVoArray(Boolean[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default Boolean[] toVoArray(Boolean[] array) {
		return toVoArray(array, 0);
	}

	default Boolean[] toVoArray() {
		Boolean[] array = new Boolean[size()];

		return toVoArray(array);
	}

	default boolean[] toBoolArray(boolean[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = value();

		return array;
	}

	default boolean[] toBoolArray(boolean[] array) {
		return toBoolArray(array, 0);
	}

	default boolean[] toBoolArray() {
		boolean[] array = new boolean[size()];

		return toBoolArray(array);
	}

	@Override
	default Iterator<Boolean> iterator() {
		return new Iterator<Boolean>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Boolean next() {
				index++;
				return get(index);
			}
		};
	}

	interface ComparableBoolSingle extends LBoolSingle, Comparable<LBoolSingle> {

		@Override
		default int compareTo(LBoolSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Boolean.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBoolSingle implements LBoolSingle {

		@Override
		public boolean equals(Object that) {
			return LBoolSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBoolSingle.argHashCode(value());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(getValue());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBoolSingle extends AbstractBoolSingle {

		private boolean value;

		public MutBoolSingle(boolean a) {
			this.value = a;
		}

		public static MutBoolSingle of(boolean a) {
			return new MutBoolSingle(a);
		}

		public static MutBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.value());
		}

		public boolean value() {
			return value;
		}

		public MutBoolSingle value(boolean value) {
			this.value = value;
			return this;
		}

		public MutBoolSingle setValue(boolean value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBoolSingle setValueIfArg(boolean value, LLogicalOperator predicate) {
			if (predicate.doTest(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBoolSingle setValueIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.value = func.doTest(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBoolSingle setValueIf(LLogicalOperator predicate, boolean value) {
			if (predicate.doTest(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBoolSingle setValueIf(boolean value, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBoolSingle setValueIf(LLogicalBinaryOperator predicate, boolean value) {

			if (predicate.doTest(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = false;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBoolSingle extends AbstractBoolSingle implements ComparableBoolSingle {

		private boolean value;

		public MutCompBoolSingle(boolean a) {
			this.value = a;
		}

		public static MutCompBoolSingle of(boolean a) {
			return new MutCompBoolSingle(a);
		}

		public static MutCompBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.value());
		}

		public boolean value() {
			return value;
		}

		public MutCompBoolSingle value(boolean value) {
			this.value = value;
			return this;
		}

		public MutCompBoolSingle setValue(boolean value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBoolSingle setValueIfArg(boolean value, LLogicalOperator predicate) {
			if (predicate.doTest(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBoolSingle setValueIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.value = func.doTest(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBoolSingle setValueIf(LLogicalOperator predicate, boolean value) {
			if (predicate.doTest(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBoolSingle setValueIf(boolean value, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBoolSingle setValueIf(LLogicalBinaryOperator predicate, boolean value) {

			if (predicate.doTest(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = false;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBoolSingle extends AbstractBoolSingle {

		private final boolean value;

		public ImmBoolSingle(boolean a) {
			this.value = a;
		}

		public static ImmBoolSingle of(boolean a) {
			return new ImmBoolSingle(a);
		}

		public static ImmBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.value());
		}

		public boolean value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompBoolSingle extends AbstractBoolSingle implements ComparableBoolSingle {

		private final boolean value;

		public ImmCompBoolSingle(boolean a) {
			this.value = a;
		}

		public static ImmCompBoolSingle of(boolean a) {
			return new ImmCompBoolSingle(a);
		}

		public static ImmCompBoolSingle copyOf(LBoolSingle tuple) {
			return of(tuple.value());
		}

		public boolean value() {
			return value;
		}

	}

}
