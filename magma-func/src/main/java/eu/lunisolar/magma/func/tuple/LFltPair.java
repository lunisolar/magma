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
 * Exact equivalent of input parameters used in LBiFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFltPair extends LTuple<Float> {

	int SIZE = 2;

	float first();

	float second();

	default float getFirst() {
		return first();
	}

	default float getSecond() {
		return second();
	}

	default Float get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default float getFloat(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LFltPair and calculates hash from it. */
	static int argHashCode(float a1, float a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(a1);
		result = prime * result + Float.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LFltPair and checks if all values are equal. */
	static boolean argEquals(float a1, float a2, float b1, float b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LFltPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFltPair are allowed.
				if (!(two instanceof LFltPair)) {
					return false;
				}

				LFltPair other = (LFltPair) two;

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

	default Float[] toVoArray(Float[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default Float[] toVoArray(Float[] array) {
		return toVoArray(array, 0);
	}

	default Float[] toVoArray() {
		Float[] array = new Float[size()];

		return toVoArray(array);
	}

	default float[] toFltArray(float[] array, int startingIndex) {
		int i = startingIndex;

		array[i] = first();
		i++;
		array[i] = second();

		return array;
	}

	default float[] toFltArray(float[] array) {
		return toFltArray(array, 0);
	}

	default float[] toFltArray() {
		float[] array = new float[size()];

		return toFltArray(array);
	}

	@Override
	default Iterator<Float> iterator() {
		return new Iterator<Float>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Float next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfDouble doubleIterator() {
		return new PrimitiveIterator.OfDouble() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public double nextDouble() {
				index++;
				return getFloat(index);
			}
		};
	}

	interface ComparableFltPair extends LFltPair, Comparable<LFltPair> {

		@Override
		default int compareTo(LFltPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Float.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Float.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractFltPair implements LFltPair {

		@Override
		public boolean equals(Object that) {
			return LFltPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LFltPair.argHashCode(first(), second());
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
	final class MutFltPair extends AbstractFltPair {

		private float first;
		private float second;

		public MutFltPair(float a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutFltPair of(float a1, float a2) {
			return new MutFltPair(a1, a2);
		}

		public static MutFltPair copyOf(LFltPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public MutFltPair first(float first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutFltPair second(float second) {
			this.second = second;
			return this;
		}

		public MutFltPair setFirst(float first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutFltPair setFirstIfArg(float first, LFltPredicate predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutFltPair setFirstIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.first = func.doApplyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutFltPair setFirstIf(LFltPredicate predicate, float first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutFltPair setFirstIf(float first, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutFltPair setFirstIf(LBiFltPredicate predicate, float first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutFltPair setSecond(float second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutFltPair setSecondIfArg(float second, LFltPredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutFltPair setSecondIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutFltPair setSecondIf(LFltPredicate predicate, float second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutFltPair setSecondIf(float second, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutFltPair setSecondIf(LBiFltPredicate predicate, float second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0f;
			second = 0f;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompFltPair extends AbstractFltPair implements ComparableFltPair {

		private float first;
		private float second;

		public MutCompFltPair(float a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompFltPair of(float a1, float a2) {
			return new MutCompFltPair(a1, a2);
		}

		public static MutCompFltPair copyOf(LFltPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public MutCompFltPair first(float first) {
			this.first = first;
			return this;
		}

		public float second() {
			return second;
		}

		public MutCompFltPair second(float second) {
			this.second = second;
			return this;
		}

		public MutCompFltPair setFirst(float first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompFltPair setFirstIfArg(float first, LFltPredicate predicate) {
			if (predicate.doTest(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompFltPair setFirstIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.first = func.doApplyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompFltPair setFirstIf(LFltPredicate predicate, float first) {
			if (predicate.doTest(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompFltPair setFirstIf(float first, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompFltPair setFirstIf(LBiFltPredicate predicate, float first) {

			if (predicate.doTest(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompFltPair setSecond(float second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompFltPair setSecondIfArg(float second, LFltPredicate predicate) {
			if (predicate.doTest(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompFltPair setSecondIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				this.second = func.doApplyAsFlt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompFltPair setSecondIf(LFltPredicate predicate, float second) {
			if (predicate.doTest(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompFltPair setSecondIf(float second, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.doTest(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompFltPair setSecondIf(LBiFltPredicate predicate, float second) {

			if (predicate.doTest(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = 0f;
			second = 0f;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmFltPair extends AbstractFltPair {

		private final float first;
		private final float second;

		public ImmFltPair(float a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmFltPair of(float a1, float a2) {
			return new ImmFltPair(a1, a2);
		}

		public static ImmFltPair copyOf(LFltPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public float second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompFltPair extends AbstractFltPair implements ComparableFltPair {

		private final float first;
		private final float second;

		public ImmCompFltPair(float a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompFltPair of(float a1, float a2) {
			return new ImmCompFltPair(a1, a2);
		}

		public static ImmCompFltPair copyOf(LFltPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public float first() {
			return first;
		}

		public float second() {
			return second;
		}

	}

}
