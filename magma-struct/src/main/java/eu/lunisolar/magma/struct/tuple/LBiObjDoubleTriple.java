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

package eu.lunisolar.magma.struct.tuple;

import eu.lunisolar.magma.basics.meta.LTuple;
import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;
import javax.annotation.concurrent.Immutable;
import java.util.*;

/**
 * Exact equivalent of input parameters used in LBiObjDoubleConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjDoubleTriple<T1, T2> extends LTuple<Object> {

	int SIZE = 3;

	T1 first();

	T2 second();

	double third();

	default T1 getFirst() {
		return first();
	}

	default T2 getSecond() {
		return second();
	}

	default double getThird() {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LBiObjDoubleTriple and calculates hash from it. */
	static <T1, T2> int argHashCode(T1 a1, T2 a2, double a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + Double.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LBiObjDoubleTriple and checks if all values are equal. */
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
	static boolean argEquals(LBiObjDoubleTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LBiObjDoubleTriple are allowed.
				if (!(two instanceof LBiObjDoubleTriple)) {
					return false;
				}

				LBiObjDoubleTriple other = (LBiObjDoubleTriple) two;

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

	interface ComparableBiObjDoubleTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends LBiObjDoubleTriple<T1, T2>, Comparable<LBiObjDoubleTriple<T1, T2>> {

		@Override
		default int compareTo(LBiObjDoubleTriple<T1, T2> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Double.compare(one.third(), two.third())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractBiObjDoubleTriple<T1, T2> implements LBiObjDoubleTriple<T1, T2> {

		@Override
		public boolean equals(Object that) {
			return LBiObjDoubleTriple.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LBiObjDoubleTriple.argHashCode(first(), second(), third());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBiObjDoubleTriple<T1, T2> extends AbstractBiObjDoubleTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private double third;

		public MutBiObjDoubleTriple(T1 a1, T2 a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> MutBiObjDoubleTriple<T1, T2> of(T1 a1, T2 a2, double a3) {
			return new MutBiObjDoubleTriple(a1, a2, a3);
		}

		public static <T1, T2> MutBiObjDoubleTriple<T1, T2> copyOf(LBiObjDoubleTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutBiObjDoubleTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutBiObjDoubleTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public double third() {
			return third;
		}

		public MutBiObjDoubleTriple<T1, T2> third(double third) {
			this.third = third;
			return this;
		}

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void setThird(double third) {
			this.third = third;
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
	final class MutCompBiObjDoubleTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjDoubleTriple<T1, T2> implements ComparableBiObjDoubleTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private double third;

		public MutCompBiObjDoubleTriple(T1 a1, T2 a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjDoubleTriple<T1, T2> of(T1 a1, T2 a2, double a3) {
			return new MutCompBiObjDoubleTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjDoubleTriple<T1, T2> copyOf(LBiObjDoubleTriple<T1, T2> tuple) {
			return of(tuple.first(), tuple.second(), tuple.third());
		}

		public T1 first() {
			return first;
		}

		public MutCompBiObjDoubleTriple<T1, T2> first(T1 first) {
			this.first = first;
			return this;
		}

		public T2 second() {
			return second;
		}

		public MutCompBiObjDoubleTriple<T1, T2> second(T2 second) {
			this.second = second;
			return this;
		}

		public double third() {
			return third;
		}

		public MutCompBiObjDoubleTriple<T1, T2> third(double third) {
			this.third = third;
			return this;
		}

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void setThird(double third) {
			this.third = third;
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
	final class ImmBiObjDoubleTriple<T1, T2> extends AbstractBiObjDoubleTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final double third;

		public ImmBiObjDoubleTriple(T1 a1, T2 a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1, T2> ImmBiObjDoubleTriple<T1, T2> of(T1 a1, T2 a2, double a3) {
			return new ImmBiObjDoubleTriple(a1, a2, a3);
		}

		public static <T1, T2> ImmBiObjDoubleTriple<T1, T2> copyOf(LBiObjDoubleTriple<T1, T2> tuple) {
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
	final class ImmCompBiObjDoubleTriple<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends AbstractBiObjDoubleTriple<T1, T2> implements ComparableBiObjDoubleTriple<T1, T2> {

		private final T1 first;
		private final T2 second;
		private final double third;

		public ImmCompBiObjDoubleTriple(T1 a1, T2 a2, double a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjDoubleTriple<T1, T2> of(T1 a1, T2 a2, double a3) {
			return new ImmCompBiObjDoubleTriple(a1, a2, a3);
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjDoubleTriple<T1, T2> copyOf(LBiObjDoubleTriple<T1, T2> tuple) {
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
