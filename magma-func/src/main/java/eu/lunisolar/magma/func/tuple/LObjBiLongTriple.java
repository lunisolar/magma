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
 * Exact equivalent of input parameters used in LObjBiLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBiLongTriple<T> extends LTuple<Object> {

	int SIZE = 3;

	T first();

	long second();

	long third();

	default long value() {
		return third();
	}

	@Override
	default Object get(int index) {
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

	default long getValue() {
		return third();
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjBiLongTriple and calculates hash from it. */
	static <T> int argHashCode(T a1, long a2, long a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Long.hashCode(a2);
		result = prime * result + Long.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjBiLongTriple and checks if all values are equal. */
	static <T> boolean argEquals(T a1, long a2, long a3, T b1, long b2, long b3) {
		return Null.equals(a1, b1) && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjBiLongTriple interface (among others) and their LObjBiLongTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjBiLongTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjBiLongTriple are allowed.
			if (!(two instanceof LObjBiLongTriple)) {
				return false;
			}

			LObjBiLongTriple other = (LObjBiLongTriple) two;

			return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjBiLongTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjBiLongTriple are allowed.
			if (!(two instanceof LObjBiLongTriple)) {
				return false;
			}

			LObjBiLongTriple other = (LObjBiLongTriple) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
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

	interface ComparableObjBiLongTriple<T extends Comparable<? super T>> extends LObjBiLongTriple<T>, Comparable<LObjBiLongTriple<T>> {
		@Override
		default int compareTo(LObjBiLongTriple<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Long.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Long.compare(one.third(), two.third())) != 0 ? retval : 0; //
			});
		}

	}

	abstract class AbstractObjBiLongTriple<T> implements LObjBiLongTriple<T> {

		@Override
		public boolean equals(Object that) {
			return LObjBiLongTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjBiLongTriple.argHashCode(first(), second(), third());
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
	interface Mut<T, SELF extends Mut<T, SELF>> extends LObjBiLongTriple<T> {

		SELF first(T first);

		SELF second(long second);

		SELF third(long third);

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

		default LObjBiLongTriple<T> value(long value) {
			third(value);
			return this;
		}

		default long setValue(long value) {
			var old = third();
			third(value);
			return old;
		}

		/** Sets value if predicate(current) is true */
		default SELF setThirdIf(long third, LLongPredicate predicate) {
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setThirdIf(long third, LBiLongPredicate predicate) {
			if (predicate.test(third, this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) is true. */
		default SELF setThirdIf(LBiLongPredicate predicate, long third) {
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second(0L);
			this.third(0L);
			return (SELF) this;
		}
	}

	public static <T> MutObjBiLongTriple<T> of() {
		return of(null, 0L, 0L);
	}

	public static <T> MutObjBiLongTriple<T> of(T a1, long a2, long a3) {
		return new MutObjBiLongTriple(a1, a2, a3);
	}

	public static <T> MutObjBiLongTriple<T> copyOf(LObjBiLongTriple<T> tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutObjBiLongTriple<T> extends AbstractObjBiLongTriple<T> implements Mut<T, MutObjBiLongTriple<T>> {

		private T first;
		private long second;
		private long third;

		public MutObjBiLongTriple(T a1, long a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override T first() {
			return first;
		}

		public @Override MutObjBiLongTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public @Override long second() {
			return second;
		}

		public @Override MutObjBiLongTriple<T> second(long second) {
			this.second = second;
			return this;
		}

		public @Override long third() {
			return third;
		}

		public @Override MutObjBiLongTriple<T> third(long third) {
			this.third = third;
			return this;
		}

	}

	public static <T> LObjBiLongTriple<T> immutableOf(T a1, long a2, long a3) {
		return new ImmObjBiLongTriple(a1, a2, a3);
	}

	public static <T> LObjBiLongTriple<T> immutableCopyOf(LObjBiLongTriple<T> tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjBiLongTriple<T> extends AbstractObjBiLongTriple<T> {

		private final T first;
		private final long second;
		private final long third;

		public ImmObjBiLongTriple(T a1, long a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override T first() {
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
