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
 * Exact equivalent of input parameters used in LSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtSingle extends LTuple<Object> {

	int SIZE = 1;

	short value();

	default short first() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LSrtSingle and calculates hash from it. */
	static int argHashCode(short a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Short.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LSrtSingle and checks if all values are equal. */
	static boolean argEquals(short a, short b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LSrtSingle interface (among others) and their LSrtSingle values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LSrtSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtSingle are allowed.
				if (!(two instanceof LSrtSingle)) {
					return false;
				}

				LSrtSingle other = (LSrtSingle) two;

				return argEquals(one.value(), other.value());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LSrtSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtSingle are allowed.
				if (!(two instanceof LSrtSingle)) {
					return false;
				}

				LSrtSingle other = (LSrtSingle) two;

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

	interface ComparableSrtSingle extends LSrtSingle, Comparable<LSrtSingle> {

		@Override
		default int compareTo(LSrtSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Short.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractSrtSingle implements LSrtSingle {

		@Override
		public boolean equals(Object that) {
			return LSrtSingle.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LSrtSingle.argHashCode(value());
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
	final class MutSrtSingle extends AbstractSrtSingle {

		private short value;

		public MutSrtSingle(short a) {
			this.value = a;
		}

		public static MutSrtSingle of(short a) {
			return new MutSrtSingle(a);
		}

		public static MutSrtSingle copyOf(LSrtSingle tuple) {
			return of(tuple.value());
		}

		public short value() {
			return value;
		}

		public MutSrtSingle value(short value) {
			this.value = value;
			return this;
		}

		public MutSrtSingle setValue(short value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSrtSingle setValueIfArg(short value, LSrtPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSrtSingle setValueIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSrtSingle setValueIf(LSrtPredicate predicate, short value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSrtSingle setValueIf(short value, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSrtSingle setValueIf(LBiSrtPredicate predicate, short value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = (short) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompSrtSingle extends AbstractSrtSingle implements ComparableSrtSingle {

		private short value;

		public MutCompSrtSingle(short a) {
			this.value = a;
		}

		public static MutCompSrtSingle of(short a) {
			return new MutCompSrtSingle(a);
		}

		public static MutCompSrtSingle copyOf(LSrtSingle tuple) {
			return of(tuple.value());
		}

		public short value() {
			return value;
		}

		public MutCompSrtSingle value(short value) {
			this.value = value;
			return this;
		}

		public MutCompSrtSingle setValue(short value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSrtSingle setValueIfArg(short value, LSrtPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSrtSingle setValueIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSrtSingle setValueIf(LSrtPredicate predicate, short value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSrtSingle setValueIf(short value, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSrtSingle setValueIf(LBiSrtPredicate predicate, short value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = (short) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmSrtSingle extends AbstractSrtSingle {

		private final short value;

		public ImmSrtSingle(short a) {
			this.value = a;
		}

		public static ImmSrtSingle of(short a) {
			return new ImmSrtSingle(a);
		}

		public static ImmSrtSingle copyOf(LSrtSingle tuple) {
			return of(tuple.value());
		}

		public short value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompSrtSingle extends AbstractSrtSingle implements ComparableSrtSingle {

		private final short value;

		public ImmCompSrtSingle(short a) {
			this.value = a;
		}

		public static ImmCompSrtSingle of(short a) {
			return new ImmCompSrtSingle(a);
		}

		public static ImmCompSrtSingle copyOf(LSrtSingle tuple) {
			return of(tuple.value());
		}

		public short value() {
			return value;
		}

	}

}
