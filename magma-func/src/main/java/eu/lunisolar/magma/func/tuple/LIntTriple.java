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
 * Exact equivalent of input parameters used in LTriIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LIntTriple extends LTuple<Object>, LIntPair {

	int SIZE = 3;

	int first();

	default int value() {
		return first();
	}

	int second();

	int third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LIntTriple and calculates hash from it. */
	static int argHashCode(int a1, int a2, int a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		result = prime * result + Integer.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LIntTriple and checks if all values are equal. */
	static boolean argEquals(int a1, int a2, int a3, int b1, int b2, int b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LIntTriple interface (among others) and their LIntTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LIntTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LIntTriple are allowed.
				if (!(two instanceof LIntTriple)) {
					return false;
				}

				LIntTriple other = (LIntTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LIntTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LIntTriple are allowed.
				if (!(two instanceof LIntTriple)) {
					return false;
				}

				LIntTriple other = (LIntTriple) two;

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

	interface ComparableIntTriple extends LIntTriple, Comparable<LIntTriple> {

		@Override
		default int compareTo(LIntTriple that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Integer.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Integer.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractIntTriple implements LIntTriple {

		@Override
		public boolean equals(Object that) {
			return LIntTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LIntTriple.argHashCode(first(), second(), third());
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
	final class MutIntTriple extends AbstractIntTriple {

		private int first;
		private int second;
		private int third;

		public MutIntTriple(int a1, int a2, int a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutIntTriple of(int a1, int a2, int a3) {
			return new MutIntTriple(a1, a2, a3);
		}

		public static MutIntTriple copyOf(LIntTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public int first() {
			return first;
		}

		public MutIntTriple first(int first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutIntTriple second(int second) {
			this.second = second;
			return this;
		}

		public int third() {
			return third;
		}

		public MutIntTriple third(int third) {
			this.third = third;
			return this;
		}

		public MutIntTriple setFirst(int first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutIntTriple setFirstIfArg(int first, LIntPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutIntTriple setFirstIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutIntTriple setFirstIf(LIntPredicate predicate, int first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutIntTriple setFirstIf(int first, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutIntTriple setFirstIf(LBiIntPredicate predicate, int first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutIntTriple setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutIntTriple setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutIntTriple setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutIntTriple setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutIntTriple setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutIntTriple setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutIntTriple setThird(int third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutIntTriple setThirdIfArg(int third, LIntPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutIntTriple setThirdIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutIntTriple setThirdIf(LIntPredicate predicate, int third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutIntTriple setThirdIf(int third, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutIntTriple setThirdIf(LBiIntPredicate predicate, int third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = 0;
			second = 0;
			third = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompIntTriple extends AbstractIntTriple implements ComparableIntTriple {

		private int first;
		private int second;
		private int third;

		public MutCompIntTriple(int a1, int a2, int a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutCompIntTriple of(int a1, int a2, int a3) {
			return new MutCompIntTriple(a1, a2, a3);
		}

		public static MutCompIntTriple copyOf(LIntTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public int first() {
			return first;
		}

		public MutCompIntTriple first(int first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompIntTriple second(int second) {
			this.second = second;
			return this;
		}

		public int third() {
			return third;
		}

		public MutCompIntTriple third(int third) {
			this.third = third;
			return this;
		}

		public MutCompIntTriple setFirst(int first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompIntTriple setFirstIfArg(int first, LIntPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompIntTriple setFirstIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompIntTriple setFirstIf(LIntPredicate predicate, int first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompIntTriple setFirstIf(int first, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompIntTriple setFirstIf(LBiIntPredicate predicate, int first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompIntTriple setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompIntTriple setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompIntTriple setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompIntTriple setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompIntTriple setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompIntTriple setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompIntTriple setThird(int third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompIntTriple setThirdIfArg(int third, LIntPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompIntTriple setThirdIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompIntTriple setThirdIf(LIntPredicate predicate, int third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompIntTriple setThirdIf(int third, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompIntTriple setThirdIf(LBiIntPredicate predicate, int third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = 0;
			second = 0;
			third = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmIntTriple extends AbstractIntTriple {

		private final int first;
		private final int second;
		private final int third;

		public ImmIntTriple(int a1, int a2, int a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmIntTriple of(int a1, int a2, int a3) {
			return new ImmIntTriple(a1, a2, a3);
		}

		public static ImmIntTriple copyOf(LIntTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public int first() {
			return first;
		}

		public int second() {
			return second;
		}

		public int third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompIntTriple extends AbstractIntTriple implements ComparableIntTriple {

		private final int first;
		private final int second;
		private final int third;

		public ImmCompIntTriple(int a1, int a2, int a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmCompIntTriple of(int a1, int a2, int a3) {
			return new ImmCompIntTriple(a1, a2, a3);
		}

		public static ImmCompIntTriple copyOf(LIntTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public int first() {
			return first;
		}

		public int second() {
			return second;
		}

		public int third() {
			return third;
		}

	}

}
