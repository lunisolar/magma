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
 * Exact equivalent of input parameters used in LLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongSingle extends LTuple<Object> {

	int SIZE = 1;

	long value();

	default long first() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LLongSingle and calculates hash from it. */
	static int argHashCode(long a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LLongSingle and checks if all values are equal. */
	static boolean argEquals(long a, long b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LLongSingle interface (among others) and their LLongSingle values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LLongSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongSingle are allowed.
				if (!(two instanceof LLongSingle)) {
					return false;
				}

				LLongSingle other = (LLongSingle) two;

				return argEquals(one.value(), other.value());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LLongSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongSingle are allowed.
				if (!(two instanceof LLongSingle)) {
					return false;
				}

				LLongSingle other = (LLongSingle) two;

				return the.tupleSize() == other.tupleSize() && argEquals(one.value(), other.value());
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

	interface ComparableLongSingle extends LLongSingle, Comparable<LLongSingle> {

		@Override
		default int compareTo(LLongSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Long.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractLongSingle implements LLongSingle {

		@Override
		public boolean equals(Object that) {
			return LLongSingle.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LLongSingle.argHashCode(value());
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
	final class MutLongSingle extends AbstractLongSingle {

		private long value;

		public MutLongSingle(long a) {
			this.value = a;
		}

		public static MutLongSingle of(long a) {
			return new MutLongSingle(a);
		}

		public static MutLongSingle copyOf(LLongSingle tuple) {
			return of(tuple.value());
		}

		public long value() {
			return value;
		}

		public MutLongSingle value(long value) {
			this.value = value;
			return this;
		}

		public MutLongSingle setValue(long value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutLongSingle setValueIfArg(long value, LLongPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutLongSingle setValueIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutLongSingle setValueIf(LLongPredicate predicate, long value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutLongSingle setValueIf(long value, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutLongSingle setValueIf(LBiLongPredicate predicate, long value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = 0L;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompLongSingle extends AbstractLongSingle implements ComparableLongSingle {

		private long value;

		public MutCompLongSingle(long a) {
			this.value = a;
		}

		public static MutCompLongSingle of(long a) {
			return new MutCompLongSingle(a);
		}

		public static MutCompLongSingle copyOf(LLongSingle tuple) {
			return of(tuple.value());
		}

		public long value() {
			return value;
		}

		public MutCompLongSingle value(long value) {
			this.value = value;
			return this;
		}

		public MutCompLongSingle setValue(long value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompLongSingle setValueIfArg(long value, LLongPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompLongSingle setValueIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsLong(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompLongSingle setValueIf(LLongPredicate predicate, long value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompLongSingle setValueIf(long value, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompLongSingle setValueIf(LBiLongPredicate predicate, long value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = 0L;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmLongSingle extends AbstractLongSingle {

		private final long value;

		public ImmLongSingle(long a) {
			this.value = a;
		}

		public static ImmLongSingle of(long a) {
			return new ImmLongSingle(a);
		}

		public static ImmLongSingle copyOf(LLongSingle tuple) {
			return of(tuple.value());
		}

		public long value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompLongSingle extends AbstractLongSingle implements ComparableLongSingle {

		private final long value;

		public ImmCompLongSingle(long a) {
			this.value = a;
		}

		public static ImmCompLongSingle of(long a) {
			return new ImmCompLongSingle(a);
		}

		public static ImmCompLongSingle copyOf(LLongSingle tuple) {
			return of(tuple.value());
		}

		public long value() {
			return value;
		}

	}

}
