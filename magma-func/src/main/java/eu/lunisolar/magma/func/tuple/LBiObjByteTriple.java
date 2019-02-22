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
 * Exact equivalent of input parameters used in LBiObjByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjByteTriple<T1, T2> extends LTuple<Object>, LPair<T1, T2> {

	int SIZE = 3;

	T1 first();

	default T1 value() {
		return first();
	}

	T2 second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBiObjByteTriple and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 a1, T2 a2, byte a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + Byte.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBiObjByteTriple and checks if all values are equal. */
	static <T1, T2> boolean argEquals(T1 a1, T2 a2, byte a3, T1 b1, T2 b2, byte b3) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2) && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LBiObjByteTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBiObjByteTriple are allowed.
				if (!(two instanceof LBiObjByteTriple)) {
					return false;
				}

				LBiObjByteTriple other = (LBiObjByteTriple) two;

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

	interface ComparableBiObjByteTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends LBiObjByteTriple<T1, T2>, Comparable<LBiObjByteTriple<T1, T2>> {

		@Override
		default int compareTo(LBiObjByteTriple<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Byte.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBiObjByteTriple<T1, T2> implements LBiObjByteTriple<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LBiObjByteTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBiObjByteTriple.argHashCode(first(), second(), third());
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
	final class MutBiObjByteTriple<T1, T2> extends AbstractBiObjByteTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private byte third;

		public MutBiObjByteTriple(T1 a1, T2 a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> MutBiObjByteTriple<T1, T2> of(T1 a1, T2 a2, byte a3) {
			return new MutBiObjByteTriple(a1, a2, a3);
		}

		public static <T1, T2> MutBiObjByteTriple<T1, T2> copyOf(LBiObjByteTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutBiObjByteTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutBiObjByteTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public byte third() {
			return third;
		}

		public MutBiObjByteTriple<T1, T2> third(byte third) {
			this.third = third;
			return this;
		}

		public MutBiObjByteTriple<T1, T2> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjByteTriple<T1, T2> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjByteTriple<T1, T2> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjByteTriple<T1, T2> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjByteTriple<T1, T2> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjByteTriple<T1, T2> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutBiObjByteTriple<T1, T2> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjByteTriple<T1, T2> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjByteTriple<T1, T2> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjByteTriple<T1, T2> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjByteTriple<T1, T2> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjByteTriple<T1, T2> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutBiObjByteTriple<T1, T2> setThird(byte third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjByteTriple<T1, T2> setThirdIfArg(byte third, LBytePredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjByteTriple<T1, T2> setThirdIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjByteTriple<T1, T2> setThirdIf(LBytePredicate predicate, byte third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjByteTriple<T1, T2> setThirdIf(byte third, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjByteTriple<T1, T2> setThirdIf(LBiBytePredicate predicate, byte third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBiObjByteTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjByteTriple<T1, T2> implements ComparableBiObjByteTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private byte third;

		public MutCompBiObjByteTriple(T1 a1, T2 a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjByteTriple<T1, T2> of(T1 a1, T2 a2, byte a3) {
			return new MutCompBiObjByteTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjByteTriple<T1, T2> copyOf(LBiObjByteTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutCompBiObjByteTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompBiObjByteTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public byte third() {
			return third;
		}

		public MutCompBiObjByteTriple<T1, T2> third(byte third) {
			this.third = third;
			return this;
		}

		public MutCompBiObjByteTriple<T1, T2> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjByteTriple<T1, T2> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjByteTriple<T1, T2> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjByteTriple<T1, T2> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjByteTriple<T1, T2> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjByteTriple<T1, T2> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompBiObjByteTriple<T1, T2> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjByteTriple<T1, T2> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjByteTriple<T1, T2> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjByteTriple<T1, T2> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjByteTriple<T1, T2> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjByteTriple<T1, T2> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompBiObjByteTriple<T1, T2> setThird(byte third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjByteTriple<T1, T2> setThirdIfArg(byte third, LBytePredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjByteTriple<T1, T2> setThirdIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjByteTriple<T1, T2> setThirdIf(LBytePredicate predicate, byte third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjByteTriple<T1, T2> setThirdIf(byte third, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjByteTriple<T1, T2> setThirdIf(LBiBytePredicate predicate, byte third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBiObjByteTriple<T1, T2> extends AbstractBiObjByteTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final byte third;

		public ImmBiObjByteTriple(T1 a1, T2 a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> ImmBiObjByteTriple<T1, T2> of(T1 a1, T2 a2, byte a3) {
			return new ImmBiObjByteTriple(a1, a2, a3);
		}

		public static <T1, T2> ImmBiObjByteTriple<T1, T2> copyOf(LBiObjByteTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
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
	final class ImmCompBiObjByteTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjByteTriple<T1, T2> implements ComparableBiObjByteTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final byte third;

		public ImmCompBiObjByteTriple(T1 a1, T2 a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjByteTriple<T1, T2> of(T1 a1, T2 a2, byte a3) {
			return new ImmCompBiObjByteTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjByteTriple<T1, T2> copyOf(LBiObjByteTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

		public byte third() {
			return third;
		}

	}

}
