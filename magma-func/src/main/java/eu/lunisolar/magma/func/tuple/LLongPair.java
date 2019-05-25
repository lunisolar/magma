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
 * Exact equivalent of input parameters used in LBiLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongPair extends LTuple<Object>, LLongSingle {

	int SIZE = 2;

	long first();

	default long value() {
		return first();
	}

	long second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LLongPair and calculates hash from it. */
	static int argHashCode(long a1, long a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(a1);
		result = prime * result + Long.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LLongPair and checks if all values are equal. */
	static boolean argEquals(long a1, long a2, long b1, long b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LLongPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongPair are allowed.
				if (!(two instanceof LLongPair)) {
					return false;
				}

				LLongPair other = (LLongPair) two;

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

	interface ComparableLongPair extends LLongPair, Comparable<LLongPair> {

		@Override
		default int compareTo(LLongPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Long.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Long.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractLongPair implements LLongPair {

		@Override
		public boolean equals(Object that) {
			return LLongPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LLongPair.argHashCode(first(), second());
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
	final class MutLongPair extends AbstractLongPair {

		private long first;
		private long second;

		public MutLongPair(long a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutLongPair of(long a1, long a2) {
			return new MutLongPair(a1, a2);
		}

		public static MutLongPair copyOf(LLongPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public MutLongPair first(long first) {
			this.first = first;
			return this;
		}

		public long second() {
			return second;
		}

		public MutLongPair second(long second) {
			this.second = second;
			return this;
		}

		public MutLongPair setFirst(long first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutLongPair setFirstIfArg(long first, LLongPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutLongPair setFirstIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutLongPair setFirstIf(LLongPredicate predicate, long first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutLongPair setFirstIf(long first, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutLongPair setFirstIf(LBiLongPredicate predicate, long first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutLongPair setSecond(long second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutLongPair setSecondIfArg(long second, LLongPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutLongPair setSecondIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutLongPair setSecondIf(LLongPredicate predicate, long second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutLongPair setSecondIf(long second, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutLongPair setSecondIf(LBiLongPredicate predicate, long second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0L;
			second = 0L;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompLongPair extends AbstractLongPair implements ComparableLongPair {

		private long first;
		private long second;

		public MutCompLongPair(long a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompLongPair of(long a1, long a2) {
			return new MutCompLongPair(a1, a2);
		}

		public static MutCompLongPair copyOf(LLongPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public MutCompLongPair first(long first) {
			this.first = first;
			return this;
		}

		public long second() {
			return second;
		}

		public MutCompLongPair second(long second) {
			this.second = second;
			return this;
		}

		public MutCompLongPair setFirst(long first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompLongPair setFirstIfArg(long first, LLongPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompLongPair setFirstIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompLongPair setFirstIf(LLongPredicate predicate, long first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompLongPair setFirstIf(long first, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompLongPair setFirstIf(LBiLongPredicate predicate, long first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompLongPair setSecond(long second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompLongPair setSecondIfArg(long second, LLongPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompLongPair setSecondIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompLongPair setSecondIf(LLongPredicate predicate, long second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompLongPair setSecondIf(long second, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompLongPair setSecondIf(LBiLongPredicate predicate, long second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0L;
			second = 0L;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmLongPair extends AbstractLongPair {

		private final long first;
		private final long second;

		public ImmLongPair(long a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmLongPair of(long a1, long a2) {
			return new ImmLongPair(a1, a2);
		}

		public static ImmLongPair copyOf(LLongPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public long second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompLongPair extends AbstractLongPair implements ComparableLongPair {

		private final long first;
		private final long second;

		public ImmCompLongPair(long a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompLongPair of(long a1, long a2) {
			return new ImmCompLongPair(a1, a2);
		}

		public static ImmCompLongPair copyOf(LLongPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public long first() {
			return first;
		}

		public long second() {
			return second;
		}

	}

}
