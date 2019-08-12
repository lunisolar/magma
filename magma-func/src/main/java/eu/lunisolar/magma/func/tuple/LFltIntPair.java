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
 * Exact equivalent of input parameters used in LFltIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFltIntPair extends LTuple<Object>, LFltSingle {

	int SIZE = 2;

	float first();

	default float value() {
		return first();
	}

	int second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LFltIntPair and calculates hash from it. */
	static int argHashCode(float a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LFltIntPair and checks if all values are equal. */
	static boolean argEquals(float a1, int a2, float b1, int b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LFltIntPair interface (among others) and their LFltIntPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LFltIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFltIntPair are allowed.
				if (!(two instanceof LFltIntPair)) {
					return false;
				}

				LFltIntPair other = (LFltIntPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LFltIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFltIntPair are allowed.
				if (!(two instanceof LFltIntPair)) {
					return false;
				}

				LFltIntPair other = (LFltIntPair) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
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

	interface ComparableFltIntPair extends LFltIntPair, Comparable<LFltIntPair> {

		@Override
		default int compareTo(LFltIntPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Float.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractFltIntPair implements LFltIntPair {

		@Override
		public boolean equals(Object that) {
			return LFltIntPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LFltIntPair.argHashCode(first(), second());
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
	final class MutFltIntPair extends AbstractFltIntPair {

		private float first;
		private int second;

		public MutFltIntPair(float a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutFltIntPair of(float a1, int a2) {
			return new MutFltIntPair(a1, a2);
		}

		public static MutFltIntPair copyOf(LFltIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public MutFltIntPair first(float first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutFltIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutFltIntPair setFirst(float first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutFltIntPair setFirstIfArg(float first, LFltPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutFltIntPair setFirstIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutFltIntPair setFirstIf(LFltPredicate predicate, float first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutFltIntPair setFirstIf(float first, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutFltIntPair setFirstIf(LBiFltPredicate predicate, float first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutFltIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutFltIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutFltIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutFltIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutFltIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutFltIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0f;
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompFltIntPair extends AbstractFltIntPair implements ComparableFltIntPair {

		private float first;
		private int second;

		public MutCompFltIntPair(float a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompFltIntPair of(float a1, int a2) {
			return new MutCompFltIntPair(a1, a2);
		}

		public static MutCompFltIntPair copyOf(LFltIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public MutCompFltIntPair first(float first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompFltIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutCompFltIntPair setFirst(float first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompFltIntPair setFirstIfArg(float first, LFltPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompFltIntPair setFirstIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompFltIntPair setFirstIf(LFltPredicate predicate, float first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompFltIntPair setFirstIf(float first, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompFltIntPair setFirstIf(LBiFltPredicate predicate, float first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompFltIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompFltIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompFltIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompFltIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompFltIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompFltIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0f;
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmFltIntPair extends AbstractFltIntPair {

		private final float first;
		private final int second;

		public ImmFltIntPair(float a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmFltIntPair of(float a1, int a2) {
			return new ImmFltIntPair(a1, a2);
		}

		public static ImmFltIntPair copyOf(LFltIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
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
	final class ImmCompFltIntPair extends AbstractFltIntPair implements ComparableFltIntPair {

		private final float first;
		private final int second;

		public ImmCompFltIntPair(float a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompFltIntPair of(float a1, int a2) {
			return new ImmCompFltIntPair(a1, a2);
		}

		public static ImmCompFltIntPair copyOf(LFltIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
