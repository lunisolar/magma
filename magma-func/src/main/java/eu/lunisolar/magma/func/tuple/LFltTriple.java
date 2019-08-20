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
 * Exact equivalent of input parameters used in LTriFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFltTriple extends LTuple<Object>, LFltPair {

	int SIZE = 3;

	float first();

	default float value() {
		return first();
	}

	float second();

	float third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LFltTriple and calculates hash from it. */
	static int argHashCode(float a1, float a2, float a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(a1);
		result = prime * result + Float.hashCode(a2);
		result = prime * result + Float.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LFltTriple and checks if all values are equal. */
	static boolean argEquals(float a1, float a2, float a3, float b1, float b2, float b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LFltTriple interface (among others) and their LFltTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LFltTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFltTriple are allowed.
				if (!(two instanceof LFltTriple)) {
					return false;
				}

				LFltTriple other = (LFltTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LFltTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFltTriple are allowed.
				if (!(two instanceof LFltTriple)) {
					return false;
				}

				LFltTriple other = (LFltTriple) two;

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

	interface ComparableFltTriple extends LFltTriple, Comparable<LFltTriple> {

		@Override
		default int compareTo(LFltTriple that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Float.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Float.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Float.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractFltTriple implements LFltTriple {

		@Override
		public boolean equals(Object that) {
			return LFltTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LFltTriple.argHashCode(first(), second(), third());
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
	final class MutFltTriple extends AbstractFltTriple {

		private float first;
		private float second;
		private float third;

		public MutFltTriple(float a1, float a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutFltTriple of(float a1, float a2, float a3) {
			return new MutFltTriple(a1, a2, a3);
		}

		public static MutFltTriple copyOf(LFltTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public float first() {
			return first;
		}

		public MutFltTriple first(float first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutFltTriple second(float second) {
			this.second = second;
			return this;
		}

		public float third() {
			return third;
		}

		public MutFltTriple third(float third) {
			this.third = third;
			return this;
		}

		public MutFltTriple setFirst(float first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutFltTriple setFirstIfArg(float first, LFltPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutFltTriple setFirstIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutFltTriple setFirstIf(LFltPredicate predicate, float first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutFltTriple setFirstIf(float first, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutFltTriple setFirstIf(LBiFltPredicate predicate, float first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutFltTriple setSecond(float second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutFltTriple setSecondIfArg(float second, LFltPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutFltTriple setSecondIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutFltTriple setSecondIf(LFltPredicate predicate, float second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutFltTriple setSecondIf(float second, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutFltTriple setSecondIf(LBiFltPredicate predicate, float second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutFltTriple setThird(float third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutFltTriple setThirdIfArg(float third, LFltPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutFltTriple setThirdIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutFltTriple setThirdIf(LFltPredicate predicate, float third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutFltTriple setThirdIf(float third, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutFltTriple setThirdIf(LBiFltPredicate predicate, float third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = 0f;
			second = 0f;
			third = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompFltTriple extends AbstractFltTriple implements ComparableFltTriple {

		private float first;
		private float second;
		private float third;

		public MutCompFltTriple(float a1, float a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static MutCompFltTriple of(float a1, float a2, float a3) {
			return new MutCompFltTriple(a1, a2, a3);
		}

		public static MutCompFltTriple copyOf(LFltTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public float first() {
			return first;
		}

		public MutCompFltTriple first(float first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutCompFltTriple second(float second) {
			this.second = second;
			return this;
		}

		public float third() {
			return third;
		}

		public MutCompFltTriple third(float third) {
			this.third = third;
			return this;
		}

		public MutCompFltTriple setFirst(float first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompFltTriple setFirstIfArg(float first, LFltPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompFltTriple setFirstIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompFltTriple setFirstIf(LFltPredicate predicate, float first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompFltTriple setFirstIf(float first, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompFltTriple setFirstIf(LBiFltPredicate predicate, float first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompFltTriple setSecond(float second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompFltTriple setSecondIfArg(float second, LFltPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompFltTriple setSecondIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompFltTriple setSecondIf(LFltPredicate predicate, float second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompFltTriple setSecondIf(float second, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompFltTriple setSecondIf(LBiFltPredicate predicate, float second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompFltTriple setThird(float third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompFltTriple setThirdIfArg(float third, LFltPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompFltTriple setThirdIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompFltTriple setThirdIf(LFltPredicate predicate, float third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompFltTriple setThirdIf(float third, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompFltTriple setThirdIf(LBiFltPredicate predicate, float third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = 0f;
			second = 0f;
			third = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmFltTriple extends AbstractFltTriple {

		private final float first;
		private final float second;
		private final float third;

		public ImmFltTriple(float a1, float a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmFltTriple of(float a1, float a2, float a3) {
			return new ImmFltTriple(a1, a2, a3);
		}

		public static ImmFltTriple copyOf(LFltTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public float first() {
			return first;
		}

		public float second() {
			return second;
		}

		public float third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompFltTriple extends AbstractFltTriple implements ComparableFltTriple {

		private final float first;
		private final float second;
		private final float third;

		public ImmCompFltTriple(float a1, float a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static ImmCompFltTriple of(float a1, float a2, float a3) {
			return new ImmCompFltTriple(a1, a2, a3);
		}

		public static ImmCompFltTriple copyOf(LFltTriple tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public float first() {
			return first;
		}

		public float second() {
			return second;
		}

		public float third() {
			return third;
		}

	}

}
