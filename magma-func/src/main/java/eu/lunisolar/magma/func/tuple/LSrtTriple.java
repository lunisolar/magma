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
 * Exact equivalent of input parameters used in LTriSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtTriple extends LTuple<Short>, Comparable<LSrtTriple> {

	int SIZE = 3;

	short first();

	default short value() {
		return first();
	}

	short second();

	short third();

	@Override
	default Short get(int index) {
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

	default short getShort(int index) {
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
	@Override
	default int tupleSize() {
		return SIZE;
	}

	/** Static hashCode() implementation method that takes same arguments as fields of the LSrtTriple and calculates hash from it. */
	static int argHashCode(short a1, short a2, short a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Short.hashCode(a1);
		result = prime * result + Short.hashCode(a2);
		result = prime * result + Short.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LSrtTriple and checks if all values are equal. */
	static boolean argEquals(short a1, short a2, short a3, short b1, short b2, short b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LSrtTriple interface (among others) and their LSrtTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LSrtTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtTriple are allowed.
				if (!(two instanceof LSrtTriple)) {
					return false;
				}

				LSrtTriple other = (LSrtTriple) two;

				return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LSrtTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LSrtTriple are allowed.
				if (!(two instanceof LSrtTriple)) {
					return false;
				}

				LSrtTriple other = (LSrtTriple) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
			});
	}

	@Override
	default Iterator<Short> iterator() {
		return new Iterator<Short>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Short next() {
				index++;
				return get(index);
			}
		};
	}

	default PrimitiveIterator.OfInt intIterator() {
		return new PrimitiveIterator.OfInt() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public int nextInt() {
				index++;
				return getShort(index);
			}
		};
	}
	@Override
	default int compareTo(LSrtTriple that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Short.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Short.compare(one.second(), two.second())) != 0 ? retval : //
							(retval = Short.compare(one.third(), two.third())) != 0 ? retval : 0; //
			});
	}

	abstract class AbstractSrtTriple implements LSrtTriple {

		@Override
		public boolean equals(Object that) {
			return LSrtTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LSrtTriple.argHashCode(first(), second(), third());
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
	 * Mutable tuple.
	 */

	interface Mut<SELF extends Mut<SELF>> extends LSrtTriple {

		SELF first(short first);
		SELF second(short second);
		SELF third(short third);

		default SELF setFirst(short first) {
			this.first(first);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFirstIfArg(short first, LSrtPredicate predicate) {
			if (predicate.test(first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFirstIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				return this.first(func.applyAsSrt(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFirstIf(LSrtPredicate predicate, short first) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFirstIf(short first, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFirstIf(LBiSrtPredicate predicate, short first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		default SELF setSecond(short second) {
			this.second(second);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setSecondIfArg(short second, LSrtPredicate predicate) {
			if (predicate.test(second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setSecondIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				return this.second(func.applyAsSrt(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setSecondIf(LSrtPredicate predicate, short second) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setSecondIf(short second, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setSecondIf(LBiSrtPredicate predicate, short second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF setThird(short third) {
			this.third(third);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setThirdIfArg(short third, LSrtPredicate predicate) {
			if (predicate.test(third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setThirdIfArgNotNull(R arg, LToSrtFunction<R> func) {
			if (arg != null) {
				return this.third(func.applyAsSrt(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setThirdIf(LSrtPredicate predicate, short third) {
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setThirdIf(short third, LBiSrtPredicate predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setThirdIf(LBiSrtPredicate predicate, short third) {
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first((short) 0);
			this.second((short) 0);
			this.third((short) 0);
			return (SELF) this;
		}
	}

	public static MutSrtTriple of() {
		return of((short) 0, (short) 0, (short) 0);
	}

	public static MutSrtTriple of(short a1, short a2, short a3) {
		return new MutSrtTriple(a1, a2, a3);
	}

	public static MutSrtTriple copyOf(LSrtTriple tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutSrtTriple extends AbstractSrtTriple implements Mut<MutSrtTriple> {

		private short first;
		private short second;
		private short third;

		public MutSrtTriple(short a1, short a2, short a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override short first() {
			return first;
		}

		public @Override MutSrtTriple first(short first) {
			this.first = first;
			return this;
		}

		public @Override short second() {
			return second;
		}

		public @Override MutSrtTriple second(short second) {
			this.second = second;
			return this;
		}

		public @Override short third() {
			return third;
		}

		public @Override MutSrtTriple third(short third) {
			this.third = third;
			return this;
		}

	}

	public static ImmSrtTriple immutableOf(short a1, short a2, short a3) {
		return new ImmSrtTriple(a1, a2, a3);
	}

	public static ImmSrtTriple immutableCopyOf(LSrtTriple tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmSrtTriple extends AbstractSrtTriple {

		private final short first;
		private final short second;
		private final short third;

		public ImmSrtTriple(short a1, short a2, short a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override short first() {
			return first;
		}

		public @Override short second() {
			return second;
		}

		public @Override short third() {
			return third;
		}

	}

}
