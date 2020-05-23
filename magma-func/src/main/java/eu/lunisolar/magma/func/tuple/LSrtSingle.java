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
public interface LSrtSingle extends LTuple<Short>, Comparable<LSrtSingle> {

	int SIZE = 1;

	short value();

	default short first() {
		return value();
	}

	@Override
	default Short get(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	default short getShort(int index) {
		switch (index) {
			case 1 :
				return value();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	@Override
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

				return one.tupleSize() == other.tupleSize() && argEquals(one.value(), other.value());
			});
	}

	@Override
	default Iterator<Short> iterator() {
		return new Iterator<Short>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Short next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfInt intIterator() {
		return new PrimitiveIterator.OfInt() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public int nextInt() {
				index++;
				return getShort(index);
			}
		};
	}
	@Override
	default int compareTo(LSrtSingle that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Short.compare(one.value(), two.value())) != 0 ? retval : 0; //
			});
	}

	abstract class AbstractSrtSingle extends Number implements LSrtSingle {

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

		@Override
		public byte byteValue() {
			return (byte) value();
		}

		@Override
		public short shortValue() {
			return (short) value();
		}

		@Override
		public int intValue() {
			return (int) value();
		}

		@Override
		public long longValue() {
			return (long) value();
		}

		@Override
		public float floatValue() {
			return (float) value();
		}

		@Override
		public double doubleValue() {
			return (double) value();
		}
	}

	/**
	 * Mutable tuple.
	 */

	interface Mut<SELF extends Mut<SELF>> extends LSrtSingle {

		SELF value(short value);

		default SELF setValue(short value) {
			this.value(value);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setValueIfArg(short value, LSrtPredicate predicate) {
			if (predicate.test(value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setValueIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				return this.value(func.applyAsSrt(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setValueIf(LSrtPredicate predicate, short value) {
			if (predicate.test(this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setValueIf(short value, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(value, this.value())) {
				return this.value(value);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setValueIf(LBiSrtPredicate predicate, short value) {
			if (predicate.test(this.value(), value)) {
				return this.value(value);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.value((short) 0);
			return (SELF) this;
		}
	}

	public static MutSrtSingle of() {
		return of((short) 0);
	}

	public static MutSrtSingle of(short a) {
		return new MutSrtSingle(a);
	}

	public static MutSrtSingle copyOf(LSrtSingle tuple) {
		return of(tuple.value());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutSrtSingle extends AbstractSrtSingle implements Mut<MutSrtSingle> {

		private short value;

		public MutSrtSingle(short a) {
			this.value = a;
		}

		public @Override short value() {
			return value;
		}

		public @Override MutSrtSingle value(short value) {
			this.value = value;
			return this;
		}

	}

	public static ImmSrtSingle immutableOf(short a) {
		return new ImmSrtSingle(a);
	}

	public static ImmSrtSingle immutableCopyOf(LSrtSingle tuple) {
		return immutableOf(tuple.value());
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

		public @Override short value() {
			return value;
		}

	}

}
