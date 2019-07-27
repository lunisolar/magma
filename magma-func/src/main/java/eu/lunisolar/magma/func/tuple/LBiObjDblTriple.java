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
 * Exact equivalent of input parameters used in LBiObjDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjDblTriple<T1, T2> extends LTuple<Object>, LPair<T1, T2> {

	int SIZE = 3;

	T1 first();

	default T1 value() {
		return first();
	}

	T2 second();

	double third();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBiObjDblTriple and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 a1, T2 a2, double a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + Double.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBiObjDblTriple and checks if all values are equal. */
	static <T1, T2> boolean argEquals(T1 a1, T2 a2, double a3, T1 b1, T2 b2, double b3) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2) && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LBiObjDblTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBiObjDblTriple are allowed.
				if (!(two instanceof LBiObjDblTriple)) {
					return false;
				}

				LBiObjDblTriple other = (LBiObjDblTriple) two;

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

	interface ComparableBiObjDblTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends LBiObjDblTriple<T1, T2>, Comparable<LBiObjDblTriple<T1, T2>> {

		@Override
		default int compareTo(LBiObjDblTriple<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Double.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBiObjDblTriple<T1, T2> implements LBiObjDblTriple<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LBiObjDblTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBiObjDblTriple.argHashCode(first(), second(), third());
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
	final class MutBiObjDblTriple<T1, T2> extends AbstractBiObjDblTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private double third;

		public MutBiObjDblTriple(T1 a1, T2 a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> MutBiObjDblTriple<T1, T2> of(T1 a1, T2 a2, double a3) {
			return new MutBiObjDblTriple(a1, a2, a3);
		}

		public static <T1, T2> MutBiObjDblTriple<T1, T2> copyOf(LBiObjDblTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutBiObjDblTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutBiObjDblTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public double third() {
			return third;
		}

		public MutBiObjDblTriple<T1, T2> third(double third) {
			this.third = third;
			return this;
		}

		public MutBiObjDblTriple<T1, T2> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjDblTriple<T1, T2> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjDblTriple<T1, T2> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjDblTriple<T1, T2> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjDblTriple<T1, T2> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjDblTriple<T1, T2> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutBiObjDblTriple<T1, T2> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjDblTriple<T1, T2> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjDblTriple<T1, T2> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjDblTriple<T1, T2> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjDblTriple<T1, T2> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjDblTriple<T1, T2> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutBiObjDblTriple<T1, T2> setThird(double third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjDblTriple<T1, T2> setThirdIfArg(double third, LDblPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjDblTriple<T1, T2> setThirdIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjDblTriple<T1, T2> setThirdIf(LDblPredicate predicate, double third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjDblTriple<T1, T2> setThirdIf(double third, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjDblTriple<T1, T2> setThirdIf(LBiDblPredicate predicate, double third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = 0d;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBiObjDblTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjDblTriple<T1, T2> implements ComparableBiObjDblTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private double third;

		public MutCompBiObjDblTriple(T1 a1, T2 a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjDblTriple<T1, T2> of(T1 a1, T2 a2, double a3) {
			return new MutCompBiObjDblTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjDblTriple<T1, T2> copyOf(LBiObjDblTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutCompBiObjDblTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompBiObjDblTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public double third() {
			return third;
		}

		public MutCompBiObjDblTriple<T1, T2> third(double third) {
			this.third = third;
			return this;
		}

		public MutCompBiObjDblTriple<T1, T2> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjDblTriple<T1, T2> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjDblTriple<T1, T2> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjDblTriple<T1, T2> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjDblTriple<T1, T2> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjDblTriple<T1, T2> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompBiObjDblTriple<T1, T2> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjDblTriple<T1, T2> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjDblTriple<T1, T2> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.apply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjDblTriple<T1, T2> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjDblTriple<T1, T2> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjDblTriple<T1, T2> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompBiObjDblTriple<T1, T2> setThird(double third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjDblTriple<T1, T2> setThirdIfArg(double third, LDblPredicate predicate) {
			if (predicate.test(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjDblTriple<T1, T2> setThirdIfArgNotNull(R arg, LToDblFunction<R> func) {
			if (arg != null) {
				this.third = func.applyAsDbl(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjDblTriple<T1, T2> setThirdIf(LDblPredicate predicate, double third) {
			if (predicate.test(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjDblTriple<T1, T2> setThirdIf(double third, LBiDblPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjDblTriple<T1, T2> setThirdIf(LBiDblPredicate predicate, double third) {

			if (predicate.test(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = 0d;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBiObjDblTriple<T1, T2> extends AbstractBiObjDblTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final double third;

		public ImmBiObjDblTriple(T1 a1, T2 a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> ImmBiObjDblTriple<T1, T2> of(T1 a1, T2 a2, double a3) {
			return new ImmBiObjDblTriple(a1, a2, a3);
		}

		public static <T1, T2> ImmBiObjDblTriple<T1, T2> copyOf(LBiObjDblTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

		public double third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompBiObjDblTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjDblTriple<T1, T2> implements ComparableBiObjDblTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final double third;

		public ImmCompBiObjDblTriple(T1 a1, T2 a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjDblTriple<T1, T2> of(T1 a1, T2 a2, double a3) {
			return new ImmCompBiObjDblTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjDblTriple<T1, T2> copyOf(LBiObjDblTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

		public double third() {
			return third;
		}

	}

}