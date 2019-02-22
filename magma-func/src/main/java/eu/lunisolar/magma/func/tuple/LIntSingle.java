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
 * Exact equivalent of input parameters used in LIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LIntSingle extends LTuple<Object> {

	int SIZE = 1;

	int value();

	default int first() {
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
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LIntSingle and calculates hash from it. */
	static int argHashCode(int a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LIntSingle and checks if all values are equal. */
	static boolean argEquals(int a, int b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LIntSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LIntSingle are allowed.
				if (!(two instanceof LIntSingle)) {
					return false;
				}

				LIntSingle other = (LIntSingle) two;

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

	interface ComparableIntSingle extends LIntSingle, Comparable<LIntSingle> {

		@Override
		default int compareTo(LIntSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Integer.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractIntSingle implements LIntSingle {

		@Override
		public boolean equals(Object that) {
			return LIntSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LIntSingle.argHashCode(value());
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
	final class MutIntSingle extends AbstractIntSingle {

		private int value;

		public MutIntSingle(int a) {
			this.value = a;
		}

		public static MutIntSingle of(int a) {
			return new MutIntSingle(a);
		}

		public static MutIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.value());
		}

		public int value() {
			return value;
		}

		public MutIntSingle value(int value) {
			this.value = value;
			return this;
		}

		public MutIntSingle setValue(int value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutIntSingle setValueIfArg(int value, LIntPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutIntSingle setValueIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutIntSingle setValueIf(LIntPredicate predicate, int value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutIntSingle setValueIf(int value, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutIntSingle setValueIf(LBiIntPredicate predicate, int value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompIntSingle extends AbstractIntSingle implements ComparableIntSingle {

		private int value;

		public MutCompIntSingle(int a) {
			this.value = a;
		}

		public static MutCompIntSingle of(int a) {
			return new MutCompIntSingle(a);
		}

		public static MutCompIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.value());
		}

		public int value() {
			return value;
		}

		public MutCompIntSingle value(int value) {
			this.value = value;
			return this;
		}

		public MutCompIntSingle setValue(int value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompIntSingle setValueIfArg(int value, LIntPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompIntSingle setValueIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompIntSingle setValueIf(LIntPredicate predicate, int value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompIntSingle setValueIf(int value, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompIntSingle setValueIf(LBiIntPredicate predicate, int value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmIntSingle extends AbstractIntSingle {

		private final int value;

		public ImmIntSingle(int a) {
			this.value = a;
		}

		public static ImmIntSingle of(int a) {
			return new ImmIntSingle(a);
		}

		public static ImmIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.value());
		}

		public int value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompIntSingle extends AbstractIntSingle implements ComparableIntSingle {

		private final int value;

		public ImmCompIntSingle(int a) {
			this.value = a;
		}

		public static ImmCompIntSingle of(int a) {
			return new ImmCompIntSingle(a);
		}

		public static ImmCompIntSingle copyOf(LIntSingle tuple) {
			return of(tuple.value());
		}

		public int value() {
			return value;
		}

	}

}
