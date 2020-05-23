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
 * Exact equivalent of input parameters used in LBiFltConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LFltPair extends LTuple<Float>, Comparable<LFltPair> {

	int SIZE = 2;

	float first();

	default float value() {
		return first();
	}

	float second();

	@Override
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
	@Override
	default int tupleSize() {
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
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LFltPair interface (among others) and their LFltPair values are equal regardless of the implementing class
	 * and how many more values there are.
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

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LFltPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LFltPair are allowed.
				if (!(two instanceof LFltPair)) {
					return false;
				}

				LFltPair other = (LFltPair) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
			});
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
	@Override
	default int compareTo(LFltPair that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Float.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Float.compare(one.second(), two.second())) != 0 ? retval : 0; //
			});
	}

	abstract class AbstractFltPair implements LFltPair {

		@Override
		public boolean equals(Object that) {
			return LFltPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LFltPair.argHashCode(first(), second());
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
	 * Mutable tuple.
	 */

	interface Mut<SELF extends Mut<SELF>> extends LFltPair {

		SELF first(float first);
		SELF second(float second);

		default SELF setFirst(float first) {
			this.first(first);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFirstIfArg(float first, LFltPredicate predicate) {
			if (predicate.test(first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFirstIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				return this.first(func.applyAsFlt(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFirstIf(LFltPredicate predicate, float first) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFirstIf(float first, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFirstIf(LBiFltPredicate predicate, float first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		default SELF setSecond(float second) {
			this.second(second);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setSecondIfArg(float second, LFltPredicate predicate) {
			if (predicate.test(second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setSecondIfArgNotNull(R arg, LToFltFunction<R> func) {
			if (arg != null) {
				return this.second(func.applyAsFlt(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setSecondIf(LFltPredicate predicate, float second) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setSecondIf(float second, LBiFltPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setSecondIf(LBiFltPredicate predicate, float second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(0f);
			this.second(0f);
			return (SELF) this;
		}
	}

	public static MutFltPair of() {
		return of(0f, 0f);
	}

	public static MutFltPair of(float a1, float a2) {
		return new MutFltPair(a1, a2);
	}

	public static MutFltPair copyOf(LFltPair tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutFltPair extends AbstractFltPair implements Mut<MutFltPair> {

		private float first;
		private float second;

		public MutFltPair(float a1, float a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override float first() {
			return first;
		}

		public @Override MutFltPair first(float first) {
			this.first = first;
			return this;
		}

		public @Override float second() {
			return second;
		}

		public @Override MutFltPair second(float second) {
			this.second = second;
			return this;
		}

	}

	public static ImmFltPair immutableOf(float a1, float a2) {
		return new ImmFltPair(a1, a2);
	}

	public static ImmFltPair immutableCopyOf(LFltPair tuple) {
		return immutableOf(tuple.first(), tuple.second());
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

		public @Override float first() {
			return first;
		}

		public @Override float second() {
			return second;
		}

	}

}
