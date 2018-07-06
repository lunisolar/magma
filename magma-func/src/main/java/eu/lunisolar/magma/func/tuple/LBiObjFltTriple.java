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
 * Exact equivalent of input parameters used in LBiObjFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjFltTriple<T1, T2> extends LTuple<Object> {

	int SIZE = 3;

	T1 first();

	T2 second();

	float third();

	default T1 getFirst() {
		return first();
	}

	default T2 getSecond() {
		return second();
	}

	default float getThird() {
		return third();
	}

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBiObjFltTriple and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 a1, T2 a2, float a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + Float.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBiObjFltTriple and checks if all values are equal. */
	static <T1, T2> boolean argEquals(T1 a1, T2 a2, float a3, T1 b1, T2 b2, float b3) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2) && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LBiObjFltTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBiObjFltTriple are allowed.
				if (!(two instanceof LBiObjFltTriple)) {
					return false;
				}

				LBiObjFltTriple other = (LBiObjFltTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();
		i++;
		array[i] = third();

		return array;
	}

	default Object[] toArray(Object[] array) {
		return toArray(array, 0);
	}

	default Object[] toArray() {
		Object[] array = new Object[size()];

		return toArray(array);
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

	interface ComparableBiObjFltTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends LBiObjFltTriple<T1, T2>, Comparable<LBiObjFltTriple<T1, T2>> {

		@Override
		default int compareTo(LBiObjFltTriple<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Float.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBiObjFltTriple<T1, T2> implements LBiObjFltTriple<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LBiObjFltTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBiObjFltTriple.argHashCode(first(), second(), third());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(getFirst());
			sb.append(',');
			sb.append(getSecond());
			sb.append(',');
			sb.append(getThird());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBiObjFltTriple<T1, T2> extends AbstractBiObjFltTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private float third;

		public MutBiObjFltTriple(T1 a1, T2 a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> MutBiObjFltTriple<T1, T2> of(T1 a1, T2 a2, float a3) {
			return new MutBiObjFltTriple(a1, a2, a3);
		}

		public static <T1, T2> MutBiObjFltTriple<T1, T2> copyOf(LBiObjFltTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutBiObjFltTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutBiObjFltTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public float third() {
			return third;
		}

		public MutBiObjFltTriple<T1, T2> third(float third) {
			this.third = third;
			return this;
		}

		public MutBiObjFltTriple<T1, T2> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjFltTriple<T1, T2> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjFltTriple<T1, T2> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjFltTriple<T1, T2> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjFltTriple<T1, T2> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjFltTriple<T1, T2> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutBiObjFltTriple<T1, T2> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjFltTriple<T1, T2> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjFltTriple<T1, T2> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjFltTriple<T1, T2> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjFltTriple<T1, T2> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjFltTriple<T1, T2> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutBiObjFltTriple<T1, T2> setThird(float third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutBiObjFltTriple<T1, T2> setThirdIfArg(float third, LFltPredicate predicate) {
			if (predicate.doTest(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutBiObjFltTriple<T1, T2> setThirdIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.third = func.doApplyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutBiObjFltTriple<T1, T2> setThirdIf(LFltPredicate predicate, float third) {
			if (predicate.doTest(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutBiObjFltTriple<T1, T2> setThirdIf(float third, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutBiObjFltTriple<T1, T2> setThirdIf(LBiFltPredicate predicate, float third) {

			if (predicate.doTest(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompBiObjFltTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjFltTriple<T1, T2> implements ComparableBiObjFltTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private float third;

		public MutCompBiObjFltTriple(T1 a1, T2 a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjFltTriple<T1, T2> of(T1 a1, T2 a2, float a3) {
			return new MutCompBiObjFltTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjFltTriple<T1, T2> copyOf(LBiObjFltTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutCompBiObjFltTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompBiObjFltTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public float third() {
			return third;
		}

		public MutCompBiObjFltTriple<T1, T2> third(float third) {
			this.third = third;
			return this;
		}

		public MutCompBiObjFltTriple<T1, T2> setFirst(T1 first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjFltTriple<T1, T2> setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjFltTriple<T1, T2> setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				this.first = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjFltTriple<T1, T2> setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjFltTriple<T1, T2> setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjFltTriple<T1, T2> setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompBiObjFltTriple<T1, T2> setSecond(T2 second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjFltTriple<T1, T2> setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjFltTriple<T1, T2> setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				this.second = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjFltTriple<T1, T2> setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjFltTriple<T1, T2> setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjFltTriple<T1, T2> setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompBiObjFltTriple<T1, T2> setThird(float third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompBiObjFltTriple<T1, T2> setThirdIfArg(float third, LFltPredicate predicate) {
			if (predicate.doTest(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompBiObjFltTriple<T1, T2> setThirdIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.third = func.doApplyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompBiObjFltTriple<T1, T2> setThirdIf(LFltPredicate predicate, float third) {
			if (predicate.doTest(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompBiObjFltTriple<T1, T2> setThirdIf(float third, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompBiObjFltTriple<T1, T2> setThirdIf(LBiFltPredicate predicate, float third) {

			if (predicate.doTest(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = null;
			third = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmBiObjFltTriple<T1, T2> extends AbstractBiObjFltTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final float third;

		public ImmBiObjFltTriple(T1 a1, T2 a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> ImmBiObjFltTriple<T1, T2> of(T1 a1, T2 a2, float a3) {
			return new ImmBiObjFltTriple(a1, a2, a3);
		}

		public static <T1, T2> ImmBiObjFltTriple<T1, T2> copyOf(LBiObjFltTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
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
	final class ImmCompBiObjFltTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjFltTriple<T1, T2> implements ComparableBiObjFltTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final float third;

		public ImmCompBiObjFltTriple(T1 a1, T2 a2, float a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjFltTriple<T1, T2> of(T1 a1, T2 a2, float a3) {
			return new ImmCompBiObjFltTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjFltTriple<T1, T2> copyOf(LBiObjFltTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public T2 second() {
			return second;
		}

		public float third() {
			return third;
		}

	}

}
