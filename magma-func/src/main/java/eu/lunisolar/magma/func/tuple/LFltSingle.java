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
 * Exact equivalent of input parameters used in LFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFltSingle extends LTuple<Object> {

	int SIZE = 1;

	float value();

	default float first() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LFltSingle and calculates hash from it. */
	static int argHashCode(float a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LFltSingle and checks if all values are equal. */
	static boolean argEquals(float a, float b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LFltSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFltSingle are allowed.
				if (!(two instanceof LFltSingle)) {
					return false;
				}

				LFltSingle other = (LFltSingle) two;

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

	interface ComparableFltSingle extends LFltSingle, Comparable<LFltSingle> {

		@Override
		default int compareTo(LFltSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Float.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractFltSingle implements LFltSingle {

		@Override
		public boolean equals(Object that) {
			return LFltSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LFltSingle.argHashCode(value());
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
	final class MutFltSingle extends AbstractFltSingle {

		private float value;

		public MutFltSingle(float a) {
			this.value = a;
		}

		public static MutFltSingle of(float a) {
			return new MutFltSingle(a);
		}

		public static MutFltSingle copyOf(LFltSingle tuple) {
			return of(tuple.value());
		}

		public float value() {
			return value;
		}

		public MutFltSingle value(float value) {
			this.value = value;
			return this;
		}

		public MutFltSingle setValue(float value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutFltSingle setValueIfArg(float value, LFltPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutFltSingle setValueIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutFltSingle setValueIf(LFltPredicate predicate, float value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutFltSingle setValueIf(float value, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutFltSingle setValueIf(LBiFltPredicate predicate, float value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompFltSingle extends AbstractFltSingle implements ComparableFltSingle {

		private float value;

		public MutCompFltSingle(float a) {
			this.value = a;
		}

		public static MutCompFltSingle of(float a) {
			return new MutCompFltSingle(a);
		}

		public static MutCompFltSingle copyOf(LFltSingle tuple) {
			return of(tuple.value());
		}

		public float value() {
			return value;
		}

		public MutCompFltSingle value(float value) {
			this.value = value;
			return this;
		}

		public MutCompFltSingle setValue(float value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompFltSingle setValueIfArg(float value, LFltPredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompFltSingle setValueIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompFltSingle setValueIf(LFltPredicate predicate, float value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompFltSingle setValueIf(float value, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompFltSingle setValueIf(LBiFltPredicate predicate, float value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmFltSingle extends AbstractFltSingle {

		private final float value;

		public ImmFltSingle(float a) {
			this.value = a;
		}

		public static ImmFltSingle of(float a) {
			return new ImmFltSingle(a);
		}

		public static ImmFltSingle copyOf(LFltSingle tuple) {
			return of(tuple.value());
		}

		public float value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompFltSingle extends AbstractFltSingle implements ComparableFltSingle {

		private final float value;

		public ImmCompFltSingle(float a) {
			this.value = a;
		}

		public static ImmCompFltSingle of(float a) {
			return new ImmCompFltSingle(a);
		}

		public static ImmCompFltSingle copyOf(LFltSingle tuple) {
			return of(tuple.value());
		}

		public float value() {
			return value;
		}

	}

}
