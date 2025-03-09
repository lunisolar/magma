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
 * Exact equivalent of input parameters used in LObjIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	int second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntPair and calculates hash from it. */
	static <T> int argHashCode(T a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, int a2, T b1, int b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjIntPair interface (among others) and their LObjIntPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntPair are allowed.
			if (!(two instanceof LObjIntPair)) {
				return false;
			}

			LObjIntPair other = (LObjIntPair) two;

			return argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntPair are allowed.
			if (!(two instanceof LObjIntPair)) {
				return false;
			}

			LObjIntPair other = (LObjIntPair) two;

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

	interface ComparableObjIntPair<T extends Comparable<? super T>> extends LObjIntPair<T>, Comparable<LObjIntPair<T>> {
		@Override
		default int compareTo(LObjIntPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
			});
		}

	}

	abstract class AbstractObjIntPair<T> implements LObjIntPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjIntPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntPair.argHashCode(first(), second());
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
	interface Mut<T, SELF extends Mut<T, SELF>> extends LObjIntPair<T> {

		SELF first(T first);

		SELF second(int second);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIfCurrent(T first, LPredicate<T> predicate) {//1
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setFirstIfNew(T first, LPredicate<T> predicate) {//1
			if (predicate.test(first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(T first, LBiPredicate<T, T> predicate) {//2
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
			this.first(null);
			this.second(0);
			return (SELF) this;
		}
	}

	public static <T> MutObjIntPair<T> of() {
		return of(null, 0);
	}

	public static <T> MutObjIntPair<T> of(T a1, int a2) {
		return new MutObjIntPair(a1, a2);
	}

	public static <T> MutObjIntPair<T> copyOf(LObjIntPair<T> tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutObjIntPair<T> extends AbstractObjIntPair<T> implements Mut<T, MutObjIntPair<T>> {

		private T first;
		private int second;

		public MutObjIntPair(T a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override MutObjIntPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public @Override int second() {
			return second;
		}

		public @Override MutObjIntPair<T> second(int second) {
			this.second = second;
			return this;
		}

	}

	public static <T> LObjIntPair<T> immutableOf(T a1, int a2) {
		return new ImmObjIntPair(a1, a2);
	}

	public static <T> LObjIntPair<T> immutableCopyOf(LObjIntPair<T> tuple) {
		return immutableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntPair<T> extends AbstractObjIntPair<T> {

		private final T first;
		private final int second;

		public ImmObjIntPair(T a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override int second() {
			return second;
		}
	}

}
