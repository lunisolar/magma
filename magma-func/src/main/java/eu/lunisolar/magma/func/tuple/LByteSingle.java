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
 * Exact equivalent of input parameters used in LByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LByteSingle extends LTuple<Object> {

	int SIZE = 1;

	byte value();

	default byte first() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LByteSingle and calculates hash from it. */
	static int argHashCode(byte a) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Byte.hashCode(a);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LByteSingle and checks if all values are equal. */
	static boolean argEquals(byte a, byte b) {
		return a == b; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LByteSingle the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LByteSingle are allowed.
				if (!(two instanceof LByteSingle)) {
					return false;
				}

				LByteSingle other = (LByteSingle) two;

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

	interface ComparableByteSingle extends LByteSingle, Comparable<LByteSingle> {

		@Override
		default int compareTo(LByteSingle that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Byte.compare(one.value(), two.value())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractByteSingle implements LByteSingle {

		@Override
		public boolean equals(Object that) {
			return LByteSingle.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LByteSingle.argHashCode(value());
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
	final class MutByteSingle extends AbstractByteSingle {

		private byte value;

		public MutByteSingle(byte a) {
			this.value = a;
		}

		public static MutByteSingle of(byte a) {
			return new MutByteSingle(a);
		}

		public static MutByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.value());
		}

		public byte value() {
			return value;
		}

		public MutByteSingle value(byte value) {
			this.value = value;
			return this;
		}

		public MutByteSingle setValue(byte value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutByteSingle setValueIfArg(byte value, LBytePredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutByteSingle setValueIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutByteSingle setValueIf(LBytePredicate predicate, byte value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutByteSingle setValueIf(byte value, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutByteSingle setValueIf(LBiBytePredicate predicate, byte value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompByteSingle extends AbstractByteSingle implements ComparableByteSingle {

		private byte value;

		public MutCompByteSingle(byte a) {
			this.value = a;
		}

		public static MutCompByteSingle of(byte a) {
			return new MutCompByteSingle(a);
		}

		public static MutCompByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.value());
		}

		public byte value() {
			return value;
		}

		public MutCompByteSingle value(byte value) {
			this.value = value;
			return this;
		}

		public MutCompByteSingle setValue(byte value) {
			this.value = value;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompByteSingle setValueIfArg(byte value, LBytePredicate predicate) {
			if (predicate.test(value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompByteSingle setValueIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.value = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompByteSingle setValueIf(LBytePredicate predicate, byte value) {
			if (predicate.test(this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompByteSingle setValueIf(byte value, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value)) {
				this.value = value;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompByteSingle setValueIf(LBiBytePredicate predicate, byte value) {

			if (predicate.test(this.value, value)) {
				this.value = value;
			}
			return this;
		}

		public void reset() {
			value = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmByteSingle extends AbstractByteSingle {

		private final byte value;

		public ImmByteSingle(byte a) {
			this.value = a;
		}

		public static ImmByteSingle of(byte a) {
			return new ImmByteSingle(a);
		}

		public static ImmByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.value());
		}

		public byte value() {
			return value;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompByteSingle extends AbstractByteSingle implements ComparableByteSingle {

		private final byte value;

		public ImmCompByteSingle(byte a) {
			this.value = a;
		}

		public static ImmCompByteSingle of(byte a) {
			return new ImmCompByteSingle(a);
		}

		public static ImmCompByteSingle copyOf(LByteSingle tuple) {
			return of(tuple.value());
		}

		public byte value() {
			return value;
		}

	}

}
