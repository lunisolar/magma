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
 * Exact equivalent of input parameters used in LObjLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjLongPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	long second();

	default long value() {
		return second();
	}

	@Override
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
	@Override
	default int tupleSize() {
		return SIZE;
	}

	default long getValue() {
		return second();
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjLongPair and calculates hash from it. */
	static <T> int argHashCode(T a1, long a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Long.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjLongPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, long a2, T b1, long b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjLongPair interface (among others) and their LObjLongPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjLongPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjLongPair are allowed.
			if (!(two instanceof LObjLongPair)) {
				return false;
			}

			LObjLongPair other = (LObjLongPair) two;

			return argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjLongPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjLongPair are allowed.
			if (!(two instanceof LObjLongPair)) {
				return false;
			}

			LObjLongPair other = (LObjLongPair) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	@Override
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

	interface ComparableObjLongPair<T extends Comparable<? super T>> extends LObjLongPair<T>, Comparable<LObjLongPair<T>> {
		@Override
		default int compareTo(LObjLongPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Long.compare(one.second(), two.second())) != 0 ? retval : 0; //
			});
		}

	}

	abstract class AbstractObjLongPair<T> implements LObjLongPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjLongPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjLongPair.argHashCode(first(), second());
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
	interface Mut<T, SELF extends Mut<T, SELF>> extends LObjLongPair<T> {

		SELF first(T first);

		SELF second(long second);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIf(T first, LPredicate<T> predicate) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(T first, LBiPredicate<T, T> predicate) {
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) is true. */
		default SELF setFirstIf(LBiPredicate<T, T> predicate, T first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		default LObjLongPair<T> value(long value) {
			second(value);
			return this;
		}

		default long setValue(long value) {
			var old = second();
			second(value);
			return old;
		}

		/** Sets value if predicate(current) is true */
		default SELF setSecondIf(long second, LLongPredicate predicate) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(long second, LBiLongPredicate predicate) {
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) is true. */
		default SELF setSecondIf(LBiLongPredicate predicate, long second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second(0L);
			return (SELF) this;
		}
	}

	public static <T> MutObjLongPair<T> of() {
		return of(null, 0L);
	}

	public static <T> MutObjLongPair<T> of(T a1, long a2) {
		return new MutObjLongPair(a1, a2);
	}

	public static <T> MutObjLongPair<T> copyOf(LObjLongPair<T> tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutObjLongPair<T> extends AbstractObjLongPair<T> implements Mut<T, MutObjLongPair<T>> {

		private T first;
		private long second;

		public MutObjLongPair(T a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override MutObjLongPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public @Override long second() {
			return second;
		}

		public @Override MutObjLongPair<T> second(long second) {
			this.second = second;
			return this;
		}

	}

	public static <T> LObjLongPair<T> immutableOf(T a1, long a2) {
		return new ImmObjLongPair(a1, a2);
	}

	public static <T> LObjLongPair<T> immutableCopyOf(LObjLongPair<T> tuple) {
		return immutableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjLongPair<T> extends AbstractObjLongPair<T> {

		private final T first;
		private final long second;

		public ImmObjLongPair(T a1, long a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override long second() {
			return second;
		}
	}

}
