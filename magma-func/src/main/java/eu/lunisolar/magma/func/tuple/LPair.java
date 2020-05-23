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
 * Exact equivalent of input parameters used in LBiConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LPair<T1, T2> extends LTuple<Object>, Map.Entry<T2, T1> {

	int SIZE = 2;

	T1 first();

	default T1 value() {
		return first();
	}

	T2 second();

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

	// <editor-fold desc="Map.Entry">

	/** Returns key as Entry.key() */
	@Override
	default T2 getKey() {
		return second();
	}

	/** Returns value as Entry.value(). 'Value' is assigned to first tuple element. */
	@Override
	default T1 getValue() {
		return first();
	}

	@Override
	default T1 setValue(T1 value) {
		throw new UnsupportedOperationException();
	}

	// </editor-fold>

	/** Static hashCode() implementation method that takes same arguments as fields of the LPair and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 a1, T2 a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LPair and checks if all values are equal. */
	static <T1, T2> boolean argEquals(T1 a1, T2 a2, T1 b1, T2 b2) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2); //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LPair interface (among others) and their LPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LPair are allowed.
				if (!(two instanceof LPair)) {
					return false;
				}

				LPair other = (LPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LPair are allowed.
				if (!(two instanceof LPair)) {
					return false;
				}

				LPair other = (LPair) two;

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

	interface ComparablePair<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> extends LPair<T1, T2>, Comparable<LPair<T1, T2>> {
		@Override
		default int compareTo(LPair<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractPair<T1, T2> implements LPair<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LPair.argHashCode(first(), second());
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

	interface Mut<T1, T2, SELF extends Mut<T1, T2, SELF>> extends LPair<T1, T2> {

		SELF first(T1 first);
		SELF second(T2 second);

		default SELF setFirst(T1 first) {
			this.first(first);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				return this.first(func.apply(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		default SELF setSecond(T2 second) {
			this.second(second);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				return this.second(func.apply(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second(null);
			return (SELF) this;
		}
	}

	public static <T1, T2> MutPair<T1, T2> of() {
		return of(null, null);
	}

	public static <T1, T2> MutPair<T1, T2> of(T1 a1, T2 a2) {
		return new MutPair(a1, a2);
	}

	public static <T1, T2> MutPair<T1, T2> copyOf(LPair<T1, T2> tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutPair<T1, T2> extends AbstractPair<T1, T2> implements Mut<T1, T2, MutPair<T1, T2>> {

		private T1 first;
		private T2 second;

		public MutPair(T1 a1, T2 a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override MutPair<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public @Override T2 second() {
			return second;
		}

		public @Override MutPair<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> MutCompPair<T1, T2> comparableOf() {
		return comparableOf(null, null);
	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> MutCompPair<T1, T2> comparableOf(T1 a1, T2 a2) {
		return new MutCompPair(a1, a2);
	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> MutCompPair<T1, T2> comparableCopyOf(LPair<T1, T2> tuple) {
		return comparableOf(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, comparable tuple.
	 */

	final class MutCompPair<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> extends AbstractPair<T1, T2> implements ComparablePair<T1, T2>, Mut<T1, T2, MutCompPair<T1, T2>> {

		private T1 first;
		private T2 second;

		public MutCompPair(T1 a1, T2 a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override MutCompPair<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public @Override T2 second() {
			return second;
		}

		public @Override MutCompPair<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

	}

	public static <T1, T2> ImmPair<T1, T2> immutableOf(T1 a1, T2 a2) {
		return new ImmPair(a1, a2);
	}

	public static <T1, T2> ImmPair<T1, T2> immutableCopyOf(LPair<T1, T2> tuple) {
		return immutableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmPair<T1, T2> extends AbstractPair<T1, T2> {

		private final T1 first;
		private final T2 second;

		public ImmPair(T1 a1, T2 a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override T2 second() {
			return second;
		}

	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> ImmCompPair<T1, T2> immutableComparableOf(T1 a1, T2 a2) {
		return new ImmCompPair(a1, a2);
	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> ImmCompPair<T1, T2> immutableComparableCopyOf(LPair<T1, T2> tuple) {
		return immutableComparableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompPair<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> extends AbstractPair<T1, T2> implements ComparablePair<T1, T2> {

		private final T1 first;
		private final T2 second;

		public ImmCompPair(T1 a1, T2 a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override T2 second() {
			return second;
		}

	}

}
