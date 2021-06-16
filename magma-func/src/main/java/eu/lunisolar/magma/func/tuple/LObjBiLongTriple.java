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
 * Exact equivalent of input parameters used in LObjBiLongConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBiLongTriple<T> extends LTuple<Object> {

	int SIZE = 3;

	T first();

	default T value() {
		return first();
	}

	long second();

	long third();

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

		default SELF setFirst(T first) {
			this.first(first);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				return this.first(func.apply(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFirstIf(LBiPredicate<T, T> predicate, T first) {
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

	public static <T extends Comparable<? super T>> MutCompObjBiLongTriple<T> comparableOf() {
		return comparableOf(null, 0L, 0L);
	}

	public static <T extends Comparable<? super T>> MutCompObjBiLongTriple<T> comparableOf(T a1, long a2, long a3) {
		return new MutCompObjBiLongTriple(a1, a2, a3);
	}

	public static <T extends Comparable<? super T>> MutCompObjBiLongTriple<T> comparableCopyOf(LObjBiLongTriple<T> tuple) {
		return comparableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, comparable tuple.
	 */

	final class MutCompObjBiLongTriple<T extends Comparable<? super T>> extends AbstractObjBiLongTriple<T> implements ComparableObjBiLongTriple<T>, Mut<T, MutCompObjBiLongTriple<T>> {

		private T first;
		private long second;
		private long third;

		public MutCompObjBiLongTriple(T a1, long a2, long a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override T first() {
			return first;
		}

		public @Override MutCompObjBiLongTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public @Override long second() {
			return second;
		}

		public @Override MutCompObjBiLongTriple<T> second(long second) {
			this.second = second;
			return this;
		}

		public @Override long third() {
			return third;
		}

		public @Override MutCompObjBiLongTriple<T> third(long third) {
			this.third = third;
			return this;
		}

	}

	public static <T> ImmObjBiLongTriple<T> immutableOf(T a1, long a2, long a3) {
		return new ImmObjBiLongTriple(a1, a2, a3);
	}

	public static <T> ImmObjBiLongTriple<T> immutableCopyOf(LObjBiLongTriple<T> tuple) {
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

	public static <T extends Comparable<? super T>> ImmCompObjBiLongTriple<T> immutableComparableOf(T a1, long a2, long a3) {
		return new ImmCompObjBiLongTriple(a1, a2, a3);
	}

	public static <T extends Comparable<? super T>> ImmCompObjBiLongTriple<T> immutableComparableCopyOf(LObjBiLongTriple<T> tuple) {
		return immutableComparableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjBiLongTriple<T extends Comparable<? super T>> extends AbstractObjBiLongTriple<T> implements ComparableObjBiLongTriple<T> {

		private final T first;
		private final long second;
		private final long third;

		public ImmCompObjBiLongTriple(T a1, long a2, long a3) {
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
