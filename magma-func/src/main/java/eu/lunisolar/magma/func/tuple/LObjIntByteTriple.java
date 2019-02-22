/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
 * Exact equivalent of input parameters used in LTieByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntByteTriple<T> extends LTuple<Object>, LObjIntPair<T> {

	int SIZE = 3;

	T first();

	default T value() {
		return first();
	}

	int second();

	byte third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntByteTriple and calculates hash from it. */
	static <T> int argHashCode(T a1, int a2, byte a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Integer.hashCode(a2);
		result = prime * result + Byte.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntByteTriple and checks if all values are equal. */
	static <T> boolean argEquals(T a1, int a2, byte a3, T b1, int b2, byte b3) {
		return Null.equals(a1, b1) && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjIntByteTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntByteTriple are allowed.
				if (!(two instanceof LObjIntByteTriple)) {
					return false;
				}

				LObjIntByteTriple other = (LObjIntByteTriple) two;

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

	interface ComparableObjIntByteTriple<T extends Comparable<T>> extends LObjIntByteTriple<T>, Comparable<LObjIntByteTriple<T>> {

		@Override
		default int compareTo(LObjIntByteTriple<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Byte.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjIntByteTriple<T> implements LObjIntByteTriple<T> {

		@Override
		public boolean equals(Object that) {
			return LObjIntByteTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntByteTriple.argHashCode(first(), second(), third());
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
	final class MutObjIntByteTriple<T> extends AbstractObjIntByteTriple<T> {

		private T first;
		private int second;
		private byte third;

		public MutObjIntByteTriple(T a1, int a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T> MutObjIntByteTriple<T> of(T a1, int a2, byte a3) {
			return new MutObjIntByteTriple(a1, a2, a3);
		}

		public static <T> MutObjIntByteTriple<T> copyOf(LObjIntByteTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public MutObjIntByteTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutObjIntByteTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public byte third() {
			return third;
		}

		public MutObjIntByteTriple<T> third(byte third) {
			this.third = third;
			return this;
		}

		public MutObjIntByteTriple<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntByteTriple<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntByteTriple<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntByteTriple<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntByteTriple<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntByteTriple<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjIntByteTriple<T> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntByteTriple<T> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntByteTriple<T> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntByteTriple<T> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntByteTriple<T> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntByteTriple<T> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutObjIntByteTriple<T> setThird(byte third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntByteTriple<T> setThirdIfArg(byte third, LBytePredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntByteTriple<T> setThirdIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntByteTriple<T> setThirdIf(LBytePredicate predicate, byte third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntByteTriple<T> setThirdIf(byte third, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntByteTriple<T> setThirdIf(LBiBytePredicate predicate, byte third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjIntByteTriple<T extends Comparable<T>> extends AbstractObjIntByteTriple<T> implements ComparableObjIntByteTriple<T> {

		private T first;
		private int second;
		private byte third;

		public MutCompObjIntByteTriple(T a1, int a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T extends Comparable<T>> MutCompObjIntByteTriple<T> of(T a1, int a2, byte a3) {
			return new MutCompObjIntByteTriple(a1, a2, a3);
		}

		public static <T extends Comparable<T>> MutCompObjIntByteTriple<T> copyOf(LObjIntByteTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public MutCompObjIntByteTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompObjIntByteTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public byte third() {
			return third;
		}

		public MutCompObjIntByteTriple<T> third(byte third) {
			this.third = third;
			return this;
		}

		public MutCompObjIntByteTriple<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntByteTriple<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntByteTriple<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntByteTriple<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntByteTriple<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntByteTriple<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjIntByteTriple<T> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntByteTriple<T> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntByteTriple<T> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntByteTriple<T> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntByteTriple<T> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntByteTriple<T> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompObjIntByteTriple<T> setThird(byte third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntByteTriple<T> setThirdIfArg(byte third, LBytePredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntByteTriple<T> setThirdIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntByteTriple<T> setThirdIf(LBytePredicate predicate, byte third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntByteTriple<T> setThirdIf(byte third, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntByteTriple<T> setThirdIf(LBiBytePredicate predicate, byte third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntByteTriple<T> extends AbstractObjIntByteTriple<T> {

		private final T first;
		private final int second;
		private final byte third;

		public ImmObjIntByteTriple(T a1, int a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T> ImmObjIntByteTriple<T> of(T a1, int a2, byte a3) {
			return new ImmObjIntByteTriple(a1, a2, a3);
		}

		public static <T> ImmObjIntByteTriple<T> copyOf(LObjIntByteTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

		public byte third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjIntByteTriple<T extends Comparable<T>> extends AbstractObjIntByteTriple<T> implements ComparableObjIntByteTriple<T> {

		private final T first;
		private final int second;
		private final byte third;

		public ImmCompObjIntByteTriple(T a1, int a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T extends Comparable<T>> ImmCompObjIntByteTriple<T> of(T a1, int a2, byte a3) {
			return new ImmCompObjIntByteTriple(a1, a2, a3);
		}

		public static <T extends Comparable<T>> ImmCompObjIntByteTriple<T> copyOf(LObjIntByteTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

		public byte third() {
			return third;
		}

	}

}
