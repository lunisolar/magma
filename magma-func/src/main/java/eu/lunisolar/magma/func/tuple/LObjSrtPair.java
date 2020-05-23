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
 * Exact equivalent of input parameters used in LObjSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjSrtPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	default T value() {
		return first();
	}

	short second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjSrtPair and calculates hash from it. */
	static <T> int argHashCode(T a1, short a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Short.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjSrtPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, short a2, T b1, short b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjSrtPair interface (among others) and their LObjSrtPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjSrtPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjSrtPair are allowed.
				if (!(two instanceof LObjSrtPair)) {
					return false;
				}

				LObjSrtPair other = (LObjSrtPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjSrtPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjSrtPair are allowed.
				if (!(two instanceof LObjSrtPair)) {
					return false;
				}

				LObjSrtPair other = (LObjSrtPair) two;

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

	interface ComparableObjSrtPair<T extends Comparable<? super T>> extends LObjSrtPair<T>, Comparable<LObjSrtPair<T>> {
		@Override
		default int compareTo(LObjSrtPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Short.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjSrtPair<T> implements LObjSrtPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjSrtPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjSrtPair.argHashCode(first(), second());
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

	interface Mut<T, SELF extends Mut<T, SELF>> extends LObjSrtPair<T> {

		SELF first(T first);
		SELF second(short second);

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

		default SELF setSecond(short second) {
			this.second(second);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setSecondIfArg(short second, LSrtPredicate predicate) {
			if (predicate.test(second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setSecondIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				return this.second(func.applyAsSrt(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setSecondIf(LSrtPredicate predicate, short second) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setSecondIf(short second, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setSecondIf(LBiSrtPredicate predicate, short second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second((short) 0);
			return (SELF) this;
		}
	}

	public static <T> MutObjSrtPair<T> of() {
		return of(null, (short) 0);
	}

	public static <T> MutObjSrtPair<T> of(T a1, short a2) {
		return new MutObjSrtPair(a1, a2);
	}

	public static <T> MutObjSrtPair<T> copyOf(LObjSrtPair<T> tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutObjSrtPair<T> extends AbstractObjSrtPair<T> implements Mut<T, MutObjSrtPair<T>> {

		private T first;
		private short second;

		public MutObjSrtPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override MutObjSrtPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public @Override short second() {
			return second;
		}

		public @Override MutObjSrtPair<T> second(short second) {
			this.second = second;
			return this;
		}

	}

	public static <T extends Comparable<? super T>> MutCompObjSrtPair<T> comparableOf() {
		return comparableOf(null, (short) 0);
	}

	public static <T extends Comparable<? super T>> MutCompObjSrtPair<T> comparableOf(T a1, short a2) {
		return new MutCompObjSrtPair(a1, a2);
	}

	public static <T extends Comparable<? super T>> MutCompObjSrtPair<T> comparableCopyOf(LObjSrtPair<T> tuple) {
		return comparableOf(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, comparable tuple.
	 */

	final class MutCompObjSrtPair<T extends Comparable<? super T>> extends AbstractObjSrtPair<T> implements ComparableObjSrtPair<T>, Mut<T, MutCompObjSrtPair<T>> {

		private T first;
		private short second;

		public MutCompObjSrtPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override MutCompObjSrtPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public @Override short second() {
			return second;
		}

		public @Override MutCompObjSrtPair<T> second(short second) {
			this.second = second;
			return this;
		}

	}

	public static <T> ImmObjSrtPair<T> immutableOf(T a1, short a2) {
		return new ImmObjSrtPair(a1, a2);
	}

	public static <T> ImmObjSrtPair<T> immutableCopyOf(LObjSrtPair<T> tuple) {
		return immutableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjSrtPair<T> extends AbstractObjSrtPair<T> {

		private final T first;
		private final short second;

		public ImmObjSrtPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override short second() {
			return second;
		}

	}

	public static <T extends Comparable<? super T>> ImmCompObjSrtPair<T> immutableComparableOf(T a1, short a2) {
		return new ImmCompObjSrtPair(a1, a2);
	}

	public static <T extends Comparable<? super T>> ImmCompObjSrtPair<T> immutableComparableCopyOf(LObjSrtPair<T> tuple) {
		return immutableComparableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjSrtPair<T extends Comparable<? super T>> extends AbstractObjSrtPair<T> implements ComparableObjSrtPair<T> {

		private final T first;
		private final short second;

		public ImmCompObjSrtPair(T a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override short second() {
			return second;
		}

	}

}
