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
 * Exact equivalent of input parameters used in LTieFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntFltTriple<T> extends LTuple<Object>, LObjIntPair<T> {

	int SIZE = 3;

	T first();

	default T value() {
		return first();
	}

	int second();

	float third();

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
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntFltTriple and calculates hash from it. */
	static <T> int argHashCode(T a1, int a2, float a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Integer.hashCode(a2);
		result = prime * result + Float.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntFltTriple and checks if all values are equal. */
	static <T> boolean argEquals(T a1, int a2, float a3, T b1, int b2, float b3) {
		return Null.equals(a1, b1) && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjIntFltTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntFltTriple are allowed.
				if (!(two instanceof LObjIntFltTriple)) {
					return false;
				}

				LObjIntFltTriple other = (LObjIntFltTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

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

	interface ComparableObjIntFltTriple<T extends Comparable<T>> extends LObjIntFltTriple<T>, Comparable<LObjIntFltTriple<T>> {

		@Override
		default int compareTo(LObjIntFltTriple<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Float.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjIntFltTriple<T> implements LObjIntFltTriple<T> {

		@Override
		public boolean equals(Object that) {
			return LObjIntFltTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntFltTriple.argHashCode(first(), second(), third());
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
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjIntFltTriple<T> extends AbstractObjIntFltTriple<T> {

		private T first;
		private int second;
		private float third;

		public MutObjIntFltTriple(T a1, int a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T> MutObjIntFltTriple<T> of(T a1, int a2, float a3) {
			return new MutObjIntFltTriple(a1, a2, a3);
		}

		public static <T> MutObjIntFltTriple<T> copyOf(LObjIntFltTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public MutObjIntFltTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutObjIntFltTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public float third() {
			return third;
		}

		public MutObjIntFltTriple<T> third(float third) {
			this.third = third;
			return this;
		}

		public MutObjIntFltTriple<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntFltTriple<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntFltTriple<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntFltTriple<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntFltTriple<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntFltTriple<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjIntFltTriple<T> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntFltTriple<T> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntFltTriple<T> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntFltTriple<T> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntFltTriple<T> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntFltTriple<T> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutObjIntFltTriple<T> setThird(float third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntFltTriple<T> setThirdIfArg(float third, LFltPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntFltTriple<T> setThirdIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntFltTriple<T> setThirdIf(LFltPredicate predicate, float third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntFltTriple<T> setThirdIf(float third, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntFltTriple<T> setThirdIf(LBiFltPredicate predicate, float third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjIntFltTriple<T extends Comparable<T>> extends AbstractObjIntFltTriple<T> implements ComparableObjIntFltTriple<T> {

		private T first;
		private int second;
		private float third;

		public MutCompObjIntFltTriple(T a1, int a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T extends Comparable<T>> MutCompObjIntFltTriple<T> of(T a1, int a2, float a3) {
			return new MutCompObjIntFltTriple(a1, a2, a3);
		}

		public static <T extends Comparable<T>> MutCompObjIntFltTriple<T> copyOf(LObjIntFltTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public MutCompObjIntFltTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompObjIntFltTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public float third() {
			return third;
		}

		public MutCompObjIntFltTriple<T> third(float third) {
			this.third = third;
			return this;
		}

		public MutCompObjIntFltTriple<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntFltTriple<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntFltTriple<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntFltTriple<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntFltTriple<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntFltTriple<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjIntFltTriple<T> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntFltTriple<T> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntFltTriple<T> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntFltTriple<T> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntFltTriple<T> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntFltTriple<T> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompObjIntFltTriple<T> setThird(float third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntFltTriple<T> setThirdIfArg(float third, LFltPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntFltTriple<T> setThirdIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntFltTriple<T> setThirdIf(LFltPredicate predicate, float third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntFltTriple<T> setThirdIf(float third, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntFltTriple<T> setThirdIf(LBiFltPredicate predicate, float third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntFltTriple<T> extends AbstractObjIntFltTriple<T> {

		private final T first;
		private final int second;
		private final float third;

		public ImmObjIntFltTriple(T a1, int a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T> ImmObjIntFltTriple<T> of(T a1, int a2, float a3) {
			return new ImmObjIntFltTriple(a1, a2, a3);
		}

		public static <T> ImmObjIntFltTriple<T> copyOf(LObjIntFltTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

		public float third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjIntFltTriple<T extends Comparable<T>> extends AbstractObjIntFltTriple<T> implements ComparableObjIntFltTriple<T> {

		private final T first;
		private final int second;
		private final float third;

		public ImmCompObjIntFltTriple(T a1, int a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T extends Comparable<T>> ImmCompObjIntFltTriple<T> of(T a1, int a2, float a3) {
			return new ImmCompObjIntFltTriple(a1, a2, a3);
		}

		public static <T extends Comparable<T>> ImmCompObjIntFltTriple<T> copyOf(LObjIntFltTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

		public float third() {
			return third;
		}

	}

}
