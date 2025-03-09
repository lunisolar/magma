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
 * Exact equivalent of input parameters used in LTieSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntSrtTriple<T> extends LTuple<Object> {

	int SIZE = 3;

	T first();

	int second();

	short third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntSrtTriple and calculates hash from it. */
	static <T> int argHashCode(T a1, int a2, short a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Integer.hashCode(a2);
		result = prime * result + Short.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntSrtTriple and checks if all values are equal. */
	static <T> boolean argEquals(T a1, int a2, short a3, T b1, int b2, short b3) {
		return Null.equals(a1, b1) && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LObjIntSrtTriple interface (among others) and their LObjIntSrtTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LObjIntSrtTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntSrtTriple are allowed.
			if (!(two instanceof LObjIntSrtTriple)) {
				return false;
			}

			LObjIntSrtTriple other = (LObjIntSrtTriple) two;

			return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LObjIntSrtTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntSrtTriple are allowed.
			if (!(two instanceof LObjIntSrtTriple)) {
				return false;
			}

			LObjIntSrtTriple other = (LObjIntSrtTriple) two;

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

	interface ComparableObjIntSrtTriple<T extends Comparable<? super T>> extends LObjIntSrtTriple<T>, Comparable<LObjIntSrtTriple<T>> {
		@Override
		default int compareTo(LObjIntSrtTriple<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Short.compare(one.third(), two.third())) != 0 ? retval : 0; //
			});
		}

	}

	abstract class AbstractObjIntSrtTriple<T> implements LObjIntSrtTriple<T> {

		@Override
		public boolean equals(Object that) {
			return LObjIntSrtTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntSrtTriple.argHashCode(first(), second(), third());
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
	interface Mut<T, SELF extends Mut<T, SELF>> extends LObjIntSrtTriple<T> {

		SELF first(T first);

		SELF second(int second);

		SELF third(short third);

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

		/** Sets value if predicate(current) is true */
		default SELF setThirdIfCurrent(short third, LSrtPredicate predicate) {//1
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setThirdIfNew(short third, LSrtPredicate predicate) {//1
			if (predicate.test(third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setThirdIf(short third, LBiSrtPredicate predicate) {//2
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second(0);
			this.third((short) 0);
			return (SELF) this;
		}
	}

	public static <T> MutObjIntSrtTriple<T> of() {
		return of(null, 0, (short) 0);
	}

	public static <T> MutObjIntSrtTriple<T> of(T a1, int a2, short a3) {
		return new MutObjIntSrtTriple(a1, a2, a3);
	}

	public static <T> MutObjIntSrtTriple<T> copyOf(LObjIntSrtTriple<T> tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutObjIntSrtTriple<T> extends AbstractObjIntSrtTriple<T> implements Mut<T, MutObjIntSrtTriple<T>> {

		private T first;
		private int second;
		private short third;

		public MutObjIntSrtTriple(T a1, int a2, short a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override T first() {
			return first;
		}

		public @Override MutObjIntSrtTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public @Override int second() {
			return second;
		}

		public @Override MutObjIntSrtTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public @Override short third() {
			return third;
		}

		public @Override MutObjIntSrtTriple<T> third(short third) {
			this.third = third;
			return this;
		}

	}

	public static <T> LObjIntSrtTriple<T> immutableOf(T a1, int a2, short a3) {
		return new ImmObjIntSrtTriple(a1, a2, a3);
	}

	public static <T> LObjIntSrtTriple<T> immutableCopyOf(LObjIntSrtTriple<T> tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntSrtTriple<T> extends AbstractObjIntSrtTriple<T> {

		private final T first;
		private final int second;
		private final short third;

		public ImmObjIntSrtTriple(T a1, int a2, short a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override T first() {
			return first;
		}

		public @Override int second() {
			return second;
		}

		public @Override short third() {
			return third;
		}
	}

}
