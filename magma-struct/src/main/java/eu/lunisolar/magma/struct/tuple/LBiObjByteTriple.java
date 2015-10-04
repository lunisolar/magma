/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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
 * Exact equivalent of input parameters used in LBiObjByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjByteTriple<T1, T2> extends LTuple<Object> {

	int SIZE = 3;

	T1 first();

	T2 second();

	byte third();

	default T1 getFirst() {
		return first();
	}

	default T2 getSecond() {
		return second();
	}

	default byte getThird() {
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

	default int size() {
		return SIZE;
	}

	static <T1, T2> int hashCode(T1 first, T2 second, byte third) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + Byte.hashCode(third);
		return result;
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
			return Null.equals(this, that, (one, two) -> {

				// Intentionally all subclasses of LBiObjByteTriple are allowed.
					if (!(two instanceof LBiObjByteTriple)) {
						return false;
					}

					LBiObjByteTriple other = (LBiObjByteTriple) two;

					return Null.equals(one.first(), other.first()) && //
							Null.equals(one.second(), other.second()) && //
							one.third() == other.third(); //
				});
		}

		@Override
		public int hashCode() {
			return LBiObjByteTriple.hashCode(first(), second(), third());
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutBiObjByteTriple<T1, T2> extends AbstractBiObjByteTriple<T1, T2> {

		private T1 first;
		private T2 second;
		private byte third;

		public MutBiObjByteTriple(T1 first, T2 second, byte third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1, T2> MutBiObjByteTriple<T1, T2> of(T1 first, T2 second, byte third) {
			return new MutBiObjByteTriple(first, second, third);
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

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void setThird(byte third) {
			this.third = third;
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

		public MutCompBiObjByteTriple(T1 first, T2 second, byte third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> MutCompBiObjByteTriple<T1, T2> of(T1 first, T2 second, byte third) {
			return new MutCompBiObjByteTriple(first, second, third);
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

		public void setFirst(T1 first) {
			this.first = first;
		}

		public void setSecond(T2 second) {
			this.second = second;
		}

		public void setThird(byte third) {
			this.third = third;
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

		public ImmBiObjByteTriple(T1 first, T2 second, byte third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1, T2> ImmBiObjByteTriple<T1, T2> of(T1 first, T2 second, byte third) {
			return new ImmBiObjByteTriple(first, second, third);
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

		public ImmCompBiObjByteTriple(T1 first, T2 second, byte third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> ImmCompBiObjByteTriple<T1, T2> of(T1 first, T2 second, byte third) {
			return new ImmCompBiObjByteTriple(first, second, third);
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
