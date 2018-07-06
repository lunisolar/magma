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
 * Exact equivalent of input parameters used in LDblIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDblIntPair extends LTuple<Object> {

	int SIZE = 2;

	double first();

	int second();

	default double getFirst() {
		return first();
	}

	default int getSecond() {
		return second();
	}

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
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LDblIntPair and calculates hash from it. */
	static int argHashCode(double a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LDblIntPair and checks if all values are equal. */
	static boolean argEquals(double a1, int a2, double b1, int b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LDblIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblIntPair are allowed.
				if (!(two instanceof LDblIntPair)) {
					return false;
				}

				LDblIntPair other = (LDblIntPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default Object[] toArray(Object[] array) {
		return toArray(array, 0);
	}

	default Object[] toArray() {
		Object[] array = new Object[size()];

		return toArray(array);
	}

	@Override
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

	interface ComparableDblIntPair extends LDblIntPair, Comparable<LDblIntPair> {

		@Override
		default int compareTo(LDblIntPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Double.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractDblIntPair implements LDblIntPair {

		@Override
		public boolean equals(Object that) {
			return LDblIntPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LDblIntPair.argHashCode(first(), second());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(getFirst());
			sb.append(',');
			sb.append(getSecond());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutDblIntPair extends AbstractDblIntPair {

		private double first;
		private int second;

		public MutDblIntPair(double a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutDblIntPair of(double a1, int a2) {
			return new MutDblIntPair(a1, a2);
		}

		public static MutDblIntPair copyOf(LDblIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public MutDblIntPair first(double first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutDblIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutDblIntPair setFirst(double first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutDblIntPair setFirstIfArg(double first, LDblPredicate predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutDblIntPair setFirstIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.first = func.doApplyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutDblIntPair setFirstIf(LDblPredicate predicate, double first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutDblIntPair setFirstIf(double first, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutDblIntPair setFirstIf(LBiDblPredicate predicate, double first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutDblIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutDblIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutDblIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutDblIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutDblIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutDblIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0d;
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompDblIntPair extends AbstractDblIntPair implements ComparableDblIntPair {

		private double first;
		private int second;

		public MutCompDblIntPair(double a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompDblIntPair of(double a1, int a2) {
			return new MutCompDblIntPair(a1, a2);
		}

		public static MutCompDblIntPair copyOf(LDblIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public MutCompDblIntPair first(double first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompDblIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutCompDblIntPair setFirst(double first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompDblIntPair setFirstIfArg(double first, LDblPredicate predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompDblIntPair setFirstIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.first = func.doApplyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompDblIntPair setFirstIf(LDblPredicate predicate, double first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompDblIntPair setFirstIf(double first, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompDblIntPair setFirstIf(LBiDblPredicate predicate, double first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompDblIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompDblIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompDblIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompDblIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompDblIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompDblIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0d;
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmDblIntPair extends AbstractDblIntPair {

		private final double first;
		private final int second;

		public ImmDblIntPair(double a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmDblIntPair of(double a1, int a2) {
			return new ImmDblIntPair(a1, a2);
		}

		public static ImmDblIntPair copyOf(LDblIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
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
	final class ImmCompDblIntPair extends AbstractDblIntPair implements ComparableDblIntPair {

		private final double first;
		private final int second;

		public ImmCompDblIntPair(double a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompDblIntPair of(double a1, int a2) {
			return new ImmCompDblIntPair(a1, a2);
		}

		public static ImmCompDblIntPair copyOf(LDblIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public double first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
