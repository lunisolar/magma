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
 * Exact equivalent of input parameters used in LTieCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntCharTriple<T> extends LTuple<Object> {

	int SIZE = 3;

	T first();

	int second();

	char third();

	default T getFirst() {
		return first();
	}

	default int getSecond() {
		return second();
	}

	default char getThird() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjIntCharTriple and calculates hash from it. */
	static <T> int argHashCode(T a1, int a2, char a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Integer.hashCode(a2);
		result = prime * result + Character.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjIntCharTriple and checks if all values are equal. */
	static <T> boolean argEquals(T a1, int a2, char a3, T b1, int b2, char b3) {
		return Null.equals(a1, b1) && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjIntCharTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjIntCharTriple are allowed.
				if (!(two instanceof LObjIntCharTriple)) {
					return false;
				}

				LObjIntCharTriple other = (LObjIntCharTriple) two;

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

	interface ComparableObjIntCharTriple<T extends Comparable<T>> extends LObjIntCharTriple<T>, Comparable<LObjIntCharTriple<T>> {

		@Override
		default int compareTo(LObjIntCharTriple<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Character.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjIntCharTriple<T> implements LObjIntCharTriple<T> {

		@Override
		public boolean equals(Object that) {
			return LObjIntCharTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjIntCharTriple.argHashCode(first(), second(), third());
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
	final class MutObjIntCharTriple<T> extends AbstractObjIntCharTriple<T> {

		private T first;
		private int second;
		private char third;

		public MutObjIntCharTriple(T a1, int a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T> MutObjIntCharTriple<T> of(T a1, int a2, char a3) {
			return new MutObjIntCharTriple(a1, a2, a3);
		}

		public static <T> MutObjIntCharTriple<T> copyOf(LObjIntCharTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public MutObjIntCharTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutObjIntCharTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public char third() {
			return third;
		}

		public MutObjIntCharTriple<T> third(char third) {
			this.third = third;
			return this;
		}

		public MutObjIntCharTriple<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntCharTriple<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntCharTriple<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntCharTriple<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntCharTriple<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntCharTriple<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjIntCharTriple<T> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntCharTriple<T> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntCharTriple<T> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntCharTriple<T> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntCharTriple<T> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntCharTriple<T> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutObjIntCharTriple<T> setThird(char third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjIntCharTriple<T> setThirdIfArg(char third, LCharPredicate predicate) {
			if (predicate.doTest(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjIntCharTriple<T> setThirdIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.third = func.doApplyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjIntCharTriple<T> setThirdIf(LCharPredicate predicate, char third) {
			if (predicate.doTest(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjIntCharTriple<T> setThirdIf(char third, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjIntCharTriple<T> setThirdIf(LBiCharPredicate predicate, char third) {

			if (predicate.doTest(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = '\u0000';
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjIntCharTriple<T extends Comparable<T>> extends AbstractObjIntCharTriple<T> implements ComparableObjIntCharTriple<T> {

		private T first;
		private int second;
		private char third;

		public MutCompObjIntCharTriple(T a1, int a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T extends Comparable<T>> MutCompObjIntCharTriple<T> of(T a1, int a2, char a3) {
			return new MutCompObjIntCharTriple(a1, a2, a3);
		}

		public static <T extends Comparable<T>> MutCompObjIntCharTriple<T> copyOf(LObjIntCharTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public MutCompObjIntCharTriple<T> first(T first) {
			this.first = first;
			return this;
		}

		public int second() {
			return second;
		}

		public MutCompObjIntCharTriple<T> second(int second) {
			this.second = second;
			return this;
		}

		public char third() {
			return third;
		}

		public MutCompObjIntCharTriple<T> third(char third) {
			this.third = third;
			return this;
		}

		public MutCompObjIntCharTriple<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntCharTriple<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntCharTriple<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntCharTriple<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntCharTriple<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntCharTriple<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjIntCharTriple<T> setSecond(int second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntCharTriple<T> setSecondIfArg(int second, LIntPredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntCharTriple<T> setSecondIfArgNotNull(R arg, LToIntFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsInt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntCharTriple<T> setSecondIf(LIntPredicate predicate, int second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntCharTriple<T> setSecondIf(int second, LBiIntPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntCharTriple<T> setSecondIf(LBiIntPredicate predicate, int second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public MutCompObjIntCharTriple<T> setThird(char third) {
			this.third = third;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjIntCharTriple<T> setThirdIfArg(char third, LCharPredicate predicate) {
			if (predicate.doTest(third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjIntCharTriple<T> setThirdIfArgNotNull(R arg, LToCharFunction<R> func) {
			if (arg != null) {
				this.third = func.doApplyAsChar(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjIntCharTriple<T> setThirdIf(LCharPredicate predicate, char third) {
			if (predicate.doTest(this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjIntCharTriple<T> setThirdIf(char third, LBiCharPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(third, this.third)) {
				this.third = third;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjIntCharTriple<T> setThirdIf(LBiCharPredicate predicate, char third) {

			if (predicate.doTest(this.third, third)) {
				this.third = third;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = 0;
			third = '\u0000';
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjIntCharTriple<T> extends AbstractObjIntCharTriple<T> {

		private final T first;
		private final int second;
		private final char third;

		public ImmObjIntCharTriple(T a1, int a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T> ImmObjIntCharTriple<T> of(T a1, int a2, char a3) {
			return new ImmObjIntCharTriple(a1, a2, a3);
		}

		public static <T> ImmObjIntCharTriple<T> copyOf(LObjIntCharTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

		public char third() {
			return third;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjIntCharTriple<T extends Comparable<T>> extends AbstractObjIntCharTriple<T> implements ComparableObjIntCharTriple<T> {

		private final T first;
		private final int second;
		private final char third;

		public ImmCompObjIntCharTriple(T a1, int a2, char a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T extends Comparable<T>> ImmCompObjIntCharTriple<T> of(T a1, int a2, char a3) {
			return new ImmCompObjIntCharTriple(a1, a2, a3);
		}

		public static <T extends Comparable<T>> ImmCompObjIntCharTriple<T> copyOf(LObjIntCharTriple<T> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T first() {
			return first;
		}

		public int second() {
			return second;
		}

		public char third() {
			return third;
		}

	}

}
