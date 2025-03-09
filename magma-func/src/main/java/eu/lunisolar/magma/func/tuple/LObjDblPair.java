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
 * Exact equivalent of input parameters used in LObjDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjDblPair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	double second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjDblPair and calculates hash from it. */
	static <T> int argHashCode(T a1, double a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Double.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjDblPair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, double a2, T b1, double b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjDblPair interface (among others) and their LObjDblPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjDblPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjDblPair are allowed.
			if (!(two instanceof LObjDblPair)) {
				return false;
			}

			LObjDblPair other = (LObjDblPair) two;

			return argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjDblPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjDblPair are allowed.
			if (!(two instanceof LObjDblPair)) {
				return false;
			}

			LObjDblPair other = (LObjDblPair) two;

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

	interface ComparableObjDblPair<T extends Comparable<? super T>> extends LObjDblPair<T>, Comparable<LObjDblPair<T>> {
		@Override
		default int compareTo(LObjDblPair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Double.compare(one.second(), two.second())) != 0 ? retval : 0; //
			});
		}

	}

	abstract class AbstractObjDblPair<T> implements LObjDblPair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjDblPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjDblPair.argHashCode(first(), second());
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
	interface Mut<T, SELF extends Mut<T, SELF>> extends LObjDblPair<T> {

		SELF first(T first);

		SELF second(double second);

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
		default SELF setSecondIfCurrent(double second, LDblPredicate predicate) {//1
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setSecondIfNew(double second, LDblPredicate predicate) {//1
			if (predicate.test(second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(double second, LBiDblPredicate predicate) {//2
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second(0d);
			return (SELF) this;
		}
	}

	public static <T> MutObjDblPair<T> of() {
		return of(null, 0d);
	}

	public static <T> MutObjDblPair<T> of(T a1, double a2) {
		return new MutObjDblPair(a1, a2);
	}

	public static <T> MutObjDblPair<T> copyOf(LObjDblPair<T> tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutObjDblPair<T> extends AbstractObjDblPair<T> implements Mut<T, MutObjDblPair<T>> {

		private T first;
		private double second;

		public MutObjDblPair(T a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override MutObjDblPair<T> first(T first) {
			this.first = first;
			return this;
		}

		public @Override double second() {
			return second;
		}

		public @Override MutObjDblPair<T> second(double second) {
			this.second = second;
			return this;
		}

	}

	public static <T> LObjDblPair<T> immutableOf(T a1, double a2) {
		return new ImmObjDblPair(a1, a2);
	}

	public static <T> LObjDblPair<T> immutableCopyOf(LObjDblPair<T> tuple) {
		return immutableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjDblPair<T> extends AbstractObjDblPair<T> {

		private final T first;
		private final double second;

		public ImmObjDblPair(T a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override T first() {
			return first;
		}

		public @Override double second() {
			return second;
		}
	}

}
