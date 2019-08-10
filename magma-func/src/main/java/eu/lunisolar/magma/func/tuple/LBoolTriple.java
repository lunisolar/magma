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
 * Exact equivalent of input parameters used in LTriBoolConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBoolTriple extends LTuple<Object>, LBoolPair {

	int SIZE = 3;

	boolean first();

	default boolean value() {
		return first();
	}

	boolean second();

	boolean third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBoolTriple and calculates hash from it. */
	static int argHashCode(boolean a1, boolean a2, boolean a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Boolean.hashCode(a1);
		result = prime * result + Boolean.hashCode(a2);
		result = prime * result + Boolean.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBoolTriple and checks if all values are equal. */
	static boolean argEquals(boolean a1, boolean a2, boolean a3, boolean b1, boolean b2, boolean b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LBoolTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBoolTriple are allowed.
				if (!(two instanceof LBoolTriple)) {
					return false;
				}

				LBoolTriple other = (LBoolTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
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

	interface ComparableBoolTriple extends LBoolTriple, Comparable<LBoolTriple> {

		@Override
		default int compareTo(LBoolTriple that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Boolean.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Boolean.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Boolean.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBoolTriple implements LBoolTriple {

		@Override
		public boolean equals(Object that) {
			return LBoolTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBoolTriple.argHashCode(first(), second(), third());
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
	final class MutBoolTriple extends AbstractBoolTriple {

		private boolean first;
		private boolean second;
		private boolean third;

		public MutBoolTriple(boolean a1, boolean a2, boolean a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutBoolTriple of(boolean a1, boolean a2, boolean a3) {
			return new MutBoolTriple(a1, a2, a3);
		}

		public static MutBoolTriple copyOf(LBoolTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public boolean first() {
			return first;
		}

		public MutBoolTriple first(boolean first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutBoolTriple second(boolean second) {
			this.second = second;
			return this;
		}

		public boolean third() {
			return third;
		}

		public MutBoolTriple third(boolean third) {
			this.third = third;
			return this;
		}

		public MutBoolTriple setFirst(boolean first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBoolTriple setFirstIfArg(boolean first, LLogicalOperator predicate) {
			if (predicate.apply(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBoolTriple setFirstIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.first = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBoolTriple setFirstIf(LLogicalOperator predicate, boolean first) {
			if (predicate.apply(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBoolTriple setFirstIf(boolean first, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBoolTriple setFirstIf(LLogicalBinaryOperator predicate, boolean first) {

			if (predicate.apply(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutBoolTriple setSecond(boolean second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBoolTriple setSecondIfArg(boolean second, LLogicalOperator predicate) {
			if (predicate.apply(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBoolTriple setSecondIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.second = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBoolTriple setSecondIf(LLogicalOperator predicate, boolean second) {
			if (predicate.apply(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBoolTriple setSecondIf(boolean second, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBoolTriple setSecondIf(LLogicalBinaryOperator predicate, boolean second) {

			if (predicate.apply(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutBoolTriple setThird(boolean third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBoolTriple setThirdIfArg(boolean third, LLogicalOperator predicate) {
			if (predicate.apply(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBoolTriple setThirdIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.third = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBoolTriple setThirdIf(LLogicalOperator predicate, boolean third) {
			if (predicate.apply(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBoolTriple setThirdIf(boolean third, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBoolTriple setThirdIf(LLogicalBinaryOperator predicate, boolean third) {

			if (predicate.apply(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = false;
			second = false;
			third = false;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBoolTriple extends AbstractBoolTriple implements ComparableBoolTriple {

		private boolean first;
		private boolean second;
		private boolean third;

		public MutCompBoolTriple(boolean a1, boolean a2, boolean a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutCompBoolTriple of(boolean a1, boolean a2, boolean a3) {
			return new MutCompBoolTriple(a1, a2, a3);
		}

		public static MutCompBoolTriple copyOf(LBoolTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public boolean first() {
			return first;
		}

		public MutCompBoolTriple first(boolean first) {
			this.first = first;
			return this;
		}

		public boolean second() {
			return second;
		}

		public MutCompBoolTriple second(boolean second) {
			this.second = second;
			return this;
		}

		public boolean third() {
			return third;
		}

		public MutCompBoolTriple third(boolean third) {
			this.third = third;
			return this;
		}

		public MutCompBoolTriple setFirst(boolean first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBoolTriple setFirstIfArg(boolean first, LLogicalOperator predicate) {
			if (predicate.apply(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBoolTriple setFirstIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.first = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBoolTriple setFirstIf(LLogicalOperator predicate, boolean first) {
			if (predicate.apply(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBoolTriple setFirstIf(boolean first, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBoolTriple setFirstIf(LLogicalBinaryOperator predicate, boolean first) {

			if (predicate.apply(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompBoolTriple setSecond(boolean second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBoolTriple setSecondIfArg(boolean second, LLogicalOperator predicate) {
			if (predicate.apply(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBoolTriple setSecondIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.second = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBoolTriple setSecondIf(LLogicalOperator predicate, boolean second) {
			if (predicate.apply(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBoolTriple setSecondIf(boolean second, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBoolTriple setSecondIf(LLogicalBinaryOperator predicate, boolean second) {

			if (predicate.apply(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompBoolTriple setThird(boolean third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBoolTriple setThirdIfArg(boolean third, LLogicalOperator predicate) {
			if (predicate.apply(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBoolTriple setThirdIfArgNotNull(R arg, LPredicate<R> func) {
			if (arg != null) {
				this.third = func.test(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBoolTriple setThirdIf(LLogicalOperator predicate, boolean third) {
			if (predicate.apply(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBoolTriple setThirdIf(boolean third, LLogicalBinaryOperator predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.apply(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBoolTriple setThirdIf(LLogicalBinaryOperator predicate, boolean third) {

			if (predicate.apply(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = false;
			second = false;
			third = false;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBoolTriple extends AbstractBoolTriple {

		private final boolean first;
		private final boolean second;
		private final boolean third;

		public ImmBoolTriple(boolean a1, boolean a2, boolean a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmBoolTriple of(boolean a1, boolean a2, boolean a3) {
			return new ImmBoolTriple(a1, a2, a3);
		}

		public static ImmBoolTriple copyOf(LBoolTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public boolean first() {
			return first;
		}

		public boolean second() {
			return second;
		}

		public boolean third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompBoolTriple extends AbstractBoolTriple implements ComparableBoolTriple {

		private final boolean first;
		private final boolean second;
		private final boolean third;

		public ImmCompBoolTriple(boolean a1, boolean a2, boolean a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmCompBoolTriple of(boolean a1, boolean a2, boolean a3) {
			return new ImmCompBoolTriple(a1, a2, a3);
		}

		public static ImmCompBoolTriple copyOf(LBoolTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public boolean first() {
			return first;
		}

		public boolean second() {
			return second;
		}

		public boolean third() {
			return third;
		}

	}

}
