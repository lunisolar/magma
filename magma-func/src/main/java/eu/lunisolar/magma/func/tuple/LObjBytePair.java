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
 * Exact equivalent of input parameters used in LObjByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LObjBytePair<T> extends LTuple<Object> {

	int SIZE = 2;

	T first();

	byte second();

	default T getFirst() {
		return first();
	}

	default byte getSecond() {
		return second();
	}

	default Object get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	default int size() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LObjBytePair and calculates hash from it. */
	static <T> int argHashCode(T a1, byte a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + Byte.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LObjBytePair and checks if all values are equal. */
	static <T> boolean argEquals(T a1, byte a2, T b1, byte b2) {
		return Null.equals(a1, b1) && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LObjBytePair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LObjBytePair are allowed.
				if (!(two instanceof LObjBytePair)) {
					return false;
				}

				LObjBytePair other = (LObjBytePair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
			});
	}

	default Object[] toArray(Object[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

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

	interface ComparableObjBytePair<T extends Comparable<T>> extends LObjBytePair<T>, Comparable<LObjBytePair<T>> {

		@Override
		default int compareTo(LObjBytePair<T> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Byte.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractObjBytePair<T> implements LObjBytePair<T> {

		@Override
		public boolean equals(Object that) {
			return LObjBytePair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LObjBytePair.argHashCode(first(), second());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(getFirst());
			sb.append(',');
			sb.append(getSecond());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutObjBytePair<T> extends AbstractObjBytePair<T> {

		private T first;
		private byte second;

		public MutObjBytePair(T a1, byte a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> MutObjBytePair<T> of(T a1, byte a2) {
			return new MutObjBytePair(a1, a2);
		}

		public static <T> MutObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutObjBytePair<T> first(T first) {
			this.first = first;
			return this;
		}

		public byte second() {
			return second;
		}

		public MutObjBytePair<T> second(byte second) {
			this.second = second;
			return this;
		}

		public MutObjBytePair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjBytePair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjBytePair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjBytePair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjBytePair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjBytePair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutObjBytePair<T> setSecond(byte second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutObjBytePair<T> setSecondIfArg(byte second, LBytePredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutObjBytePair<T> setSecondIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutObjBytePair<T> setSecondIf(LBytePredicate predicate, byte second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutObjBytePair<T> setSecondIf(byte second, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutObjBytePair<T> setSecondIf(LBiBytePredicate predicate, byte second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = (byte) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompObjBytePair<T extends Comparable<T>> extends AbstractObjBytePair<T> implements ComparableObjBytePair<T> {

		private T first;
		private byte second;

		public MutCompObjBytePair(T a1, byte a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> MutCompObjBytePair<T> of(T a1, byte a2) {
			return new MutCompObjBytePair(a1, a2);
		}

		public static <T extends Comparable<T>> MutCompObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public MutCompObjBytePair<T> first(T first) {
			this.first = first;
			return this;
		}

		public byte second() {
			return second;
		}

		public MutCompObjBytePair<T> second(byte second) {
			this.second = second;
			return this;
		}

		public MutCompObjBytePair<T> setFirst(T first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjBytePair<T> setFirstIfArg(T first, LPredicate<T> predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjBytePair<T> setFirstIfArgNotNull(R arg, LFunction<R, T> func) {
			if (arg != null) {
				this.first = func.doApply(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjBytePair<T> setFirstIf(LPredicate<T> predicate, T first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjBytePair<T> setFirstIf(T first, LBiPredicate<T, T> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjBytePair<T> setFirstIf(LBiPredicate<T, T> predicate, T first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompObjBytePair<T> setSecond(byte second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompObjBytePair<T> setSecondIfArg(byte second, LBytePredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompObjBytePair<T> setSecondIfArgNotNull(R arg, LToByteFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsByte(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompObjBytePair<T> setSecondIf(LBytePredicate predicate, byte second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompObjBytePair<T> setSecondIf(byte second, LBiBytePredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompObjBytePair<T> setSecondIf(LBiBytePredicate predicate, byte second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = null;
			second = (byte) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmObjBytePair<T> extends AbstractObjBytePair<T> {

		private final T first;
		private final byte second;

		public ImmObjBytePair(T a1, byte a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T> ImmObjBytePair<T> of(T a1, byte a2) {
			return new ImmObjBytePair(a1, a2);
		}

		public static <T> ImmObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public byte second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompObjBytePair<T extends Comparable<T>> extends AbstractObjBytePair<T> implements ComparableObjBytePair<T> {

		private final T first;
		private final byte second;

		public ImmCompObjBytePair(T a1, byte a2) {
			this.first = a1;
			this.second = a2;
		}

		public static <T extends Comparable<T>> ImmCompObjBytePair<T> of(T a1, byte a2) {
			return new ImmCompObjBytePair(a1, a2);
		}

		public static <T extends Comparable<T>> ImmCompObjBytePair<T> copyOf(LObjBytePair<T> tuple) {
			return of(tuple.first(), tuple.second());
		}

		public T first() {
			return first;
		}

		public byte second() {
			return second;
		}

	}

}
