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
 * Exact equivalent of input parameters used in LLongIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongIntPair extends LTuple<Object>, LLongSingle {

	int SIZE = 2;

	long first();

	default long value() {
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
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LLongIntPair and calculates hash from it. */
	static int argHashCode(long a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LLongIntPair and checks if all values are equal. */
	static boolean argEquals(long a1, int a2, long b1, int b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LLongIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongIntPair are allowed.
				if (!(two instanceof LLongIntPair)) {
					return false;
				}

				LLongIntPair other = (LLongIntPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
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

	interface ComparableLongIntPair extends LLongIntPair, Comparable<LLongIntPair> {

		@Override
		default int compareTo(LLongIntPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Long.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractLongIntPair implements LLongIntPair {

		@Override
		public boolean equals(Object that) {
			return LLongIntPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LLongIntPair.argHashCode(first(), second());
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
	final class MutLongIntPair extends AbstractLongIntPair {

		private long first;
		private int second;

		public MutLongIntPair(long a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutLongIntPair of(long a1, int a2) {
			return new MutLongIntPair(a1, a2);
		}

		public static MutLongIntPair copyOf(LLongIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public MutLongIntPair first(long first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutLongIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutLongIntPair setFirst(long first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutLongIntPair setFirstIfArg(long first, LLongPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutLongIntPair setFirstIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutLongIntPair setFirstIf(LLongPredicate predicate, long first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutLongIntPair setFirstIf(long first, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutLongIntPair setFirstIf(LBiLongPredicate predicate, long first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutLongIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutLongIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutLongIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutLongIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutLongIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutLongIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0L;
			second = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompLongIntPair extends AbstractLongIntPair implements ComparableLongIntPair {

		private long first;
		private int second;

		public MutCompLongIntPair(long a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompLongIntPair of(long a1, int a2) {
			return new MutCompLongIntPair(a1, a2);
		}

		public static MutCompLongIntPair copyOf(LLongIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public MutCompLongIntPair first(long first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompLongIntPair second(int second) {
			this.second = second;
			return this;
		}

		public MutCompLongIntPair setFirst(long first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompLongIntPair setFirstIfArg(long first, LLongPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompLongIntPair setFirstIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompLongIntPair setFirstIf(LLongPredicate predicate, long first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompLongIntPair setFirstIf(long first, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompLongIntPair setFirstIf(LBiLongPredicate predicate, long first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompLongIntPair setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompLongIntPair setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompLongIntPair setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompLongIntPair setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompLongIntPair setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompLongIntPair setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0L;
			second = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmLongIntPair extends AbstractLongIntPair {

		private final long first;
		private final int second;

		public ImmLongIntPair(long a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmLongIntPair of(long a1, int a2) {
			return new ImmLongIntPair(a1, a2);
		}

		public static ImmLongIntPair copyOf(LLongIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
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
	final class ImmCompLongIntPair extends AbstractLongIntPair implements ComparableLongIntPair {

		private final long first;
		private final int second;

		public ImmCompLongIntPair(long a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompLongIntPair of(long a1, int a2) {
			return new ImmCompLongIntPair(a1, a2);
		}

		public static ImmCompLongIntPair copyOf(LLongIntPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public int second() {
			return second;
		}

	}

}
