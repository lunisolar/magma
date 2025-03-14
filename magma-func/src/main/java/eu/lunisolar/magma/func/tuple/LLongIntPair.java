/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.*;
import eu.lunisolar.magma.basics.meta.functional.*;
import eu.lunisolar.magma.func.*;
import eu.lunisolar.magma.func.consumer.*;
import eu.lunisolar.magma.func.consumer.primitives.bi.*;
import eu.lunisolar.magma.func.consumer.primitives.tri.*;
import eu.lunisolar.magma.func.function.*;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.function.from.*;
import eu.lunisolar.magma.func.operator.unary.*;
import eu.lunisolar.magma.func.operator.binary.*;
import eu.lunisolar.magma.func.predicate.*;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.*;
import java.util.stream.*;

/**
 * Exact equivalent of input parameters used in LLongIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LLongIntPair extends LTuple<Number> {

	int SIZE = 2;

	long first();

	int second();

	@Override
	default Number get(int index) {
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
	@Override
	default int tupleSize() {
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
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LLongIntPair interface (among others) and their LLongIntPair values are equal regardless of the implementing class
	 * and how many more values there are.
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

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LLongIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LLongIntPair are allowed.
			if (!(two instanceof LLongIntPair)) {
				return false;
			}

			LLongIntPair other = (LLongIntPair) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	@Override
	default Iterator<Number> iterator() {
		return new Iterator<Number>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Number next() {
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
			return LLongIntPair.tupleEquals(this, that);
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
	 * Mutable tuple.
	 */
	interface Mut<SELF extends Mut<SELF>> extends LLongIntPair {

		SELF first(long first);

		SELF second(int second);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIfCurrent(long first, LLongPredicate predicate) {//1
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setFirstIfNew(long first, LLongPredicate predicate) {//1
			if (predicate.test(first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(long first, LBiLongPredicate predicate) {//2
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setSecondIfCurrent(int second, LIntPredicate predicate) {//1
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setSecondIfNew(int second, LIntPredicate predicate) {//1
			if (predicate.test(second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(int second, LBiIntPredicate predicate) {//2
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(0L);
			this.second(0);
			return (SELF) this;
		}
	}

	public static MutLongIntPair of() {
		return of(0L, 0);
	}

	public static MutLongIntPair of(long a1, int a2) {
		return new MutLongIntPair(a1, a2);
	}

	public static MutLongIntPair copyOf(LLongIntPair tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutLongIntPair extends AbstractLongIntPair implements Mut<MutLongIntPair> {

		private long first;
		private int second;

		public MutLongIntPair(long a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override long first() {
			return first;
		}

		public @Override MutLongIntPair first(long first) {
			this.first = first;
			return this;
		}

		public @Override int second() {
			return second;
		}

		public @Override MutLongIntPair second(int second) {
			this.second = second;
			return this;
		}

	}

	public static LLongIntPair immutableOf(long a1, int a2) {
		return new ImmLongIntPair(a1, a2);
	}

	public static LLongIntPair immutableCopyOf(LLongIntPair tuple) {
		return immutableOf(tuple.first(), tuple.second());
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

		public @Override long first() {
			return first;
		}

		public @Override int second() {
			return second;
		}
	}

}
