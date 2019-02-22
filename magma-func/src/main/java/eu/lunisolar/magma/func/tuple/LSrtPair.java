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
 * Exact equivalent of input parameters used in LBiSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtPair extends LTuple<Object>, LSrtSingle {

	int SIZE = 2;

	short first();

	default short value() {
		return first();
	}

	short second();

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

	/** Static hashCode() implementation method that takes same arguments as fields of the LSrtPair and calculates hash from it. */
	static int argHashCode(short a1, short a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Short.hashCode(a1);
		result = prime * result + Short.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LSrtPair and checks if all values are equal. */
	static boolean argEquals(short a1, short a2, short b1, short b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples asnd checks if they are equal.
	 *
	 * Tuples are considered equal if are implementing same interface and their tuple values are equal regardless of the implementing class.
	 */
	static boolean argEquals(LSrtPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtPair are allowed.
				if (!(two instanceof LSrtPair)) {
					return false;
				}

				LSrtPair other = (LSrtPair) two;

				return argEquals(one.first(), one.second(), other.first(), other.second());
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

	interface ComparableSrtPair extends LSrtPair, Comparable<LSrtPair> {

		@Override
		default int compareTo(LSrtPair that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Short.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Short.compare(one.second(), two.second())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractSrtPair implements LSrtPair {

		@Override
		public boolean equals(Object that) {
			return LSrtPair.argEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LSrtPair.argHashCode(first(), second());
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			sb.append(first());
			sb.append(',');
			sb.append(second());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	final class MutSrtPair extends AbstractSrtPair {

		private short first;
		private short second;

		public MutSrtPair(short a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutSrtPair of(short a1, short a2) {
			return new MutSrtPair(a1, a2);
		}

		public static MutSrtPair copyOf(LSrtPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public MutSrtPair first(short first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutSrtPair second(short second) {
			this.second = second;
			return this;
		}

		public MutSrtPair setFirst(short first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSrtPair setFirstIfArg(short first, LSrtPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSrtPair setFirstIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSrtPair setFirstIf(LSrtPredicate predicate, short first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSrtPair setFirstIf(short first, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSrtPair setFirstIf(LBiSrtPredicate predicate, short first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutSrtPair setSecond(short second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutSrtPair setSecondIfArg(short second, LSrtPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutSrtPair setSecondIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutSrtPair setSecondIf(LSrtPredicate predicate, short second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutSrtPair setSecondIf(short second, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutSrtPair setSecondIf(LBiSrtPredicate predicate, short second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = (short) 0;
			second = (short) 0;
		}
	}

	/**
	 * Mutable, comparable tuple.
	 */
	final class MutCompSrtPair extends AbstractSrtPair implements ComparableSrtPair {

		private short first;
		private short second;

		public MutCompSrtPair(short a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static MutCompSrtPair of(short a1, short a2) {
			return new MutCompSrtPair(a1, a2);
		}

		public static MutCompSrtPair copyOf(LSrtPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public MutCompSrtPair first(short first) {
			this.first = first;
			return this;
		}

		public short second() {
			return second;
		}

		public MutCompSrtPair second(short second) {
			this.second = second;
			return this;
		}

		public MutCompSrtPair setFirst(short first) {
			this.first = first;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSrtPair setFirstIfArg(short first, LSrtPredicate predicate) {
			if (predicate.test(first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSrtPair setFirstIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.first = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSrtPair setFirstIf(LSrtPredicate predicate, short first) {
			if (predicate.test(this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSrtPair setFirstIf(short first, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first)) {
				this.first = first;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSrtPair setFirstIf(LBiSrtPredicate predicate, short first) {

			if (predicate.test(this.first, first)) {
				this.first = first;
			}
			return this;
		}

		public MutCompSrtPair setSecond(short second) {
			this.second = second;
			return this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		public MutCompSrtPair setSecondIfArg(short second, LSrtPredicate predicate) {
			if (predicate.test(second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		public <R> MutCompSrtPair setSecondIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				this.second = func.applyAsSrt(arg);
			}
			return this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		public MutCompSrtPair setSecondIf(LSrtPredicate predicate, short second) {
			if (predicate.test(this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		public MutCompSrtPair setSecondIf(short second, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second)) {
				this.second = second;
			}
			return this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		public MutCompSrtPair setSecondIf(LBiSrtPredicate predicate, short second) {

			if (predicate.test(this.second, second)) {
				this.second = second;
			}
			return this;
		}

		public void reset() {
			first = (short) 0;
			second = (short) 0;
		}
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmSrtPair extends AbstractSrtPair {

		private final short first;
		private final short second;

		public ImmSrtPair(short a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmSrtPair of(short a1, short a2) {
			return new ImmSrtPair(a1, a2);
		}

		public static ImmSrtPair copyOf(LSrtPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public short second() {
			return second;
		}

	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompSrtPair extends AbstractSrtPair implements ComparableSrtPair {

		private final short first;
		private final short second;

		public ImmCompSrtPair(short a1, short a2) {
			this.first = a1;
			this.second = a2;
		}

		public static ImmCompSrtPair of(short a1, short a2) {
			return new ImmCompSrtPair(a1, a2);
		}

		public static ImmCompSrtPair copyOf(LSrtPair tuple) {
			return of(tuple.first(), tuple.second());
		}

		public short first() {
			return first;
		}

		public short second() {
			return second;
		}

	}

}
