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
 * Exact equivalent of input parameters used in LTieConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntObjTriple<T1, T2> extends LTuple<Object> {

	int SIZE = 3;

	T1 first();

	int second();

	T2 third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntObjTriple and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 a1, int a2, T2 a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Integer.hashCode(a2);
		result = prime * result + ((a3 == null) ? 0 : a3.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntObjTriple and checks if all values are equal. */
	static <T1, T2> boolean argEquals(T1 a1, int a2, T2 a3, T1 b1, int b2, T2 b3) {
		return Null.equals(a1, b1) && //
				a2 == b2 && //
				Null.equals(a3, b3); //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjIntObjTriple interface (among others) and their LObjIntObjTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjIntObjTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntObjTriple are allowed.
			if (!(two instanceof LObjIntObjTriple)) {
				return false;
			}

			LObjIntObjTriple other = (LObjIntObjTriple) two;

			return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjIntObjTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntObjTriple are allowed.
			if (!(two instanceof LObjIntObjTriple)) {
				return false;
			}

			LObjIntObjTriple other = (LObjIntObjTriple) two;

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

	interface ComparableObjIntObjTriple<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>> extends LObjIntObjTriple<T1, T2>, Comparable<LObjIntObjTriple<T1, T2>> {
		@Override
		default int compareTo(LObjIntObjTriple<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Null.compare(one.third(), two.third())) != 0 ? retval : 0; //
			});
		}

	}

	abstract class AbstractObjIntObjTriple<T1, T2> implements LObjIntObjTriple<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LObjIntObjTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntObjTriple.argHashCode(first(), second(), third());
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
	interface Mut<T1, T2, SELF extends Mut<T1, T2, SELF>> extends LObjIntObjTriple<T1, T2> {

		SELF first(T1 first);

		SELF second(int second);

		SELF third(T2 third);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIfCurrent(T1 first, LPredicate<T1> predicate) {//1
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setFirstIfNew(T1 first, LPredicate<T1> predicate) {//1
			if (predicate.test(first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {//2
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

		/** Sets value if predicate(current) is true */
		default SELF setThirdIfCurrent(T2 third, LPredicate<T2> predicate) {//1
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setThirdIfNew(T2 third, LPredicate<T2> predicate) {//1
			if (predicate.test(third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setThirdIf(T2 third, LBiPredicate<T2, T2> predicate) {//2
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second(0);
			this.third(null);
			return (SELF) this;
		}
	}

	public static <T1, T2> MutObjIntObjTriple<T1, T2> of() {
		return of(null, 0, null);
	}

	public static <T1, T2> MutObjIntObjTriple<T1, T2> of(T1 a1, int a2, T2 a3) {
		return new MutObjIntObjTriple(a1, a2, a3);
	}

	public static <T1, T2> MutObjIntObjTriple<T1, T2> copyOf(LObjIntObjTriple<T1, T2> tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutObjIntObjTriple<T1, T2> extends AbstractObjIntObjTriple<T1, T2> implements Mut<T1, T2, MutObjIntObjTriple<T1, T2>> {

		private T1 first;
		private int second;
		private T2 third;

		public MutObjIntObjTriple(T1 a1, int a2, T2 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override MutObjIntObjTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public @Override int second() {
			return second;
		}

		public @Override MutObjIntObjTriple<T1, T2> second(int second) {
			this.second = second;
			return this;
		}

		public @Override T2 third() {
			return third;
		}

		public @Override MutObjIntObjTriple<T1, T2> third(T2 third) {
			this.third = third;
			return this;
		}

	}

	public static <T1, T2> LObjIntObjTriple<T1, T2> immutableOf(T1 a1, int a2, T2 a3) {
		return new ImmObjIntObjTriple(a1, a2, a3);
	}

	public static <T1, T2> LObjIntObjTriple<T1, T2> immutableCopyOf(LObjIntObjTriple<T1, T2> tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntObjTriple<T1, T2> extends AbstractObjIntObjTriple<T1, T2> {

		private final T1 first;
		private final int second;
		private final T2 third;

		public ImmObjIntObjTriple(T1 a1, int a2, T2 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override int second() {
			return second;
		}

		public @Override T2 third() {
			return third;
		}
	}

}
