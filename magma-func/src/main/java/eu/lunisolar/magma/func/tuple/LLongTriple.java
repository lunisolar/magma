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
 * Exact equivalent of input parameters used in LTriLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongTriple extends LTuple<Long>, Comparable<LLongTriple> {

	int SIZE = 3;

	long first();

	default long value() {
		return first();
	}

	long second();

	long third();

	@Override
	default Long get(int index) {
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

	default long getLong(int index) {
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
	@Override
	default int tupleSize() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LLongTriple and calculates hash from it. */
	static int argHashCode(long a1, long a2, long a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(a1);
		result = prime * result + Long.hashCode(a2);
		result = prime * result + Long.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LLongTriple and checks if all values are equal. */
	static boolean argEquals(long a1, long a2, long a3, long b1, long b2, long b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LLongTriple interface (among others) and their LLongTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LLongTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongTriple are allowed.
				if (!(two instanceof LLongTriple)) {
					return false;
				}

				LLongTriple other = (LLongTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LLongTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongTriple are allowed.
				if (!(two instanceof LLongTriple)) {
					return false;
				}

				LLongTriple other = (LLongTriple) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	@Override
	default Iterator<Long> iterator() {
		return new Iterator<Long>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Long next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfLong longIterator() {
		return new PrimitiveIterator.OfLong() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public long nextLong() {
				index++;
				return getLong(index);
			}
		};
	}
	@Override
	default int compareTo(LLongTriple that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Long.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Long.compare(one.second(), two.second())) != 0 ? retval : //
							(retval = Long.compare(one.third(), two.third())) != 0 ? retval : 0; //
			});
	}

	abstract class AbstractLongTriple implements LLongTriple {

		@Override
		public boolean equals(Object that) {
			return LLongTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LLongTriple.argHashCode(first(), second(), third());
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
	 * Mutable tuple.
	 */

	interface Mut<SELF extends Mut<SELF>> extends LLongTriple {

		SELF first(long first);
		SELF second(long second);
		SELF third(long third);

		default SELF setFirst(long first) {
			this.first(first);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFirstIfArg(long first, LLongPredicate predicate) {
			if (predicate.test(first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFirstIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				return this.first(func.applyAsLong(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFirstIf(LLongPredicate predicate, long first) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFirstIf(long first, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFirstIf(LBiLongPredicate predicate, long first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		default SELF setSecond(long second) {
			this.second(second);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setSecondIfArg(long second, LLongPredicate predicate) {
			if (predicate.test(second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setSecondIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				return this.second(func.applyAsLong(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setSecondIf(LLongPredicate predicate, long second) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setSecondIf(long second, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setSecondIf(LBiLongPredicate predicate, long second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF setThird(long third) {
			this.third(third);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setThirdIfArg(long third, LLongPredicate predicate) {
			if (predicate.test(third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setThirdIfArgNotNull(R arg, LToLongFunction<R> func) {
			if (arg != null) {
				return this.third(func.applyAsLong(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setThirdIf(LLongPredicate predicate, long third) {
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setThirdIf(long third, LBiLongPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setThirdIf(LBiLongPredicate predicate, long third) {
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(0L);
			this.second(0L);
			this.third(0L);
			return (SELF) this;
		}
	}

	public static MutLongTriple of() {
		return of(0L, 0L, 0L);
	}

	public static MutLongTriple of(long a1, long a2, long a3) {
		return new MutLongTriple(a1, a2, a3);
	}

	public static MutLongTriple copyOf(LLongTriple tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutLongTriple extends AbstractLongTriple implements Mut<MutLongTriple> {

		private long first;
		private long second;
		private long third;

		public MutLongTriple(long a1, long a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override long first() {
			return first;
		}

		public @Override MutLongTriple first(long first) {
			this.first = first;
			return this;
		}

		public @Override long second() {
			return second;
		}

		public @Override MutLongTriple second(long second) {
			this.second = second;
			return this;
		}

		public @Override long third() {
			return third;
		}

		public @Override MutLongTriple third(long third) {
			this.third = third;
			return this;
		}

	}

	public static ImmLongTriple immutableOf(long a1, long a2, long a3) {
		return new ImmLongTriple(a1, a2, a3);
	}

	public static ImmLongTriple immutableCopyOf(LLongTriple tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmLongTriple extends AbstractLongTriple {

		private final long first;
		private final long second;
		private final long third;

		public ImmLongTriple(long a1, long a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override long first() {
			return first;
		}

		public @Override long second() {
			return second;
		}

		public @Override long third() {
			return third;
		}

	}

}
