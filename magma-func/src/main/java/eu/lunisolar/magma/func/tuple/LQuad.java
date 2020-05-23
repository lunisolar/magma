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
 * Exact equivalent of input parameters used in LQuadConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LQuad<T1, T2, T3, T4> extends LTuple<Object>, Map.Entry<T2, T1> {

	int SIZE = 4;

	T1 first();

	default T1 value() {
		return first();
	}

	T2 second();

	T3 third();

	T4 fourth();

	@Override
	default Object get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			case 3 :
				return third();
			case 4 :
				return fourth();
			default :
				throw new NoSuchElementException();
		}
	}

	/** Tuple size */
	@Override
	default int tupleSize() {
		return SIZE;
	}

	// <editor-fold desc="Map.Entry">

	/** Returns key as Entry.key() */
	@Override
	default T2 getKey() {
		return second();
	}

	/** Returns value as Entry.value(). 'Value' is assigned to first tuple element. */
	@Override
	default T1 getValue() {
		return first();
	}

	@Override
	default T1 setValue(T1 value) {
		throw new UnsupportedOperationException();
	}

	// </editor-fold>

	/** Static hashCode() implementation method that takes same arguments as fields of the LQuad and calculates hash from it. */
	static <T1, T2, T3, T4> int argHashCode(T1 a1, T2 a2, T3 a3, T4 a4) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + ((a3 == null) ? 0 : a3.hashCode());
		result = prime * result + ((a4 == null) ? 0 : a4.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LQuad and checks if all values are equal. */
	static <T1, T2, T3, T4> boolean argEquals(T1 a1, T2 a2, T3 a3, T4 a4, T1 b1, T2 b2, T3 b3, T4 b4) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2) && //
				Null.equals(a3, b3) && //
				Null.equals(a4, b4); //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LQuad interface (among others) and their LQuad values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LQuad the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LQuad are allowed.
				if (!(two instanceof LQuad)) {
					return false;
				}

				LQuad other = (LQuad) two;

				return argEquals(one.first(), one.second(), one.third(), one.fourth(), other.first(), other.second(), other.third(), other.fourth());
			});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LQuad the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LQuad are allowed.
				if (!(two instanceof LQuad)) {
					return false;
				}

				LQuad other = (LQuad) two;

				return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), one.fourth(), other.first(), other.second(), other.third(), other.fourth());
			});
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

	interface ComparableQuad<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> extends LQuad<T1, T2, T3, T4>, Comparable<LQuad<T1, T2, T3, T4>> {
		@Override
		default int compareTo(LQuad<T1, T2, T3, T4> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Null.compare(one.third(), two.third())) != 0 ? retval : //
										(retval = Null.compare(one.fourth(), two.fourth())) != 0 ? retval : 0; //
				});
		}

	}

	abstract class AbstractQuad<T1, T2, T3, T4> implements LQuad<T1, T2, T3, T4> {

		@Override
		public boolean equals(Object that) {
			return LQuad.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LQuad.argHashCode(first(), second(), third(), fourth());
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
			sb.append(',');
			sb.append(fourth());
			sb.append(')');
			return sb.toString();
		}

	}

	/**
	 * Mutable tuple.
	 */

	interface Mut<T1, T2, T3, T4, SELF extends Mut<T1, T2, T3, T4, SELF>> extends LQuad<T1, T2, T3, T4> {

		SELF first(T1 first);
		SELF second(T2 second);
		SELF third(T3 third);
		SELF fourth(T4 fourth);

		default SELF setFirst(T1 first) {
			this.first(first);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFirstIfArg(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFirstIfArgNotNull(R arg, LFunction<R, T1> func) {
			if (arg != null) {
				return this.first(func.apply(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFirstIf(LPredicate<T1> predicate, T1 first) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		default SELF setSecond(T2 second) {
			this.second(second);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setSecondIfArg(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setSecondIfArgNotNull(R arg, LFunction<R, T2> func) {
			if (arg != null) {
				return this.second(func.apply(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setSecondIf(LPredicate<T2> predicate, T2 second) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF setThird(T3 third) {
			this.third(third);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setThirdIfArg(T3 third, LPredicate<T3> predicate) {
			if (predicate.test(third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setThirdIfArgNotNull(R arg, LFunction<R, T3> func) {
			if (arg != null) {
				return this.third(func.apply(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setThirdIf(LPredicate<T3> predicate, T3 third) {
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setThirdIf(T3 third, LBiPredicate<T3, T3> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(third, this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setThirdIf(LBiPredicate<T3, T3> predicate, T3 third) {
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF setFourth(T4 fourth) {
			this.fourth(fourth);
			return (SELF) this;
		}

		/** Sets value if predicate(newValue) OR newValue::predicate is true */
		default SELF setFourthIfArg(T4 fourth, LPredicate<T4> predicate) {
			if (predicate.test(fourth())) {
				return this.fourth(fourth);
			}
			return (SELF) this;
		}

		/** Sets value derived from non-null argument, only if argument is not null. */
		default <R> SELF setFourthIfArgNotNull(R arg, LFunction<R, T4> func) {
			if (arg != null) {
				return this.fourth(func.apply(arg));
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) OR current::predicate is true */
		default SELF setFourthIf(LPredicate<T4> predicate, T4 fourth) {
			if (predicate.test(this.fourth())) {
				return this.fourth(fourth);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) OR newValue::something(current) is true. */
		default SELF setFourthIf(T4 fourth, LBiPredicate<T4, T4> predicate) {
			// the order of arguments is intentional, to allow predicate:
			if (predicate.test(fourth, this.fourth())) {
				return this.fourth(fourth);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) OR current::something(newValue) is true. */
		default SELF setFourthIf(LBiPredicate<T4, T4> predicate, T4 fourth) {
			if (predicate.test(this.fourth(), fourth)) {
				return this.fourth(fourth);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second(null);
			this.third(null);
			this.fourth(null);
			return (SELF) this;
		}
	}

	public static <T1, T2, T3, T4> MutQuad<T1, T2, T3, T4> of() {
		return of(null, null, null, null);
	}

	public static <T1, T2, T3, T4> MutQuad<T1, T2, T3, T4> of(T1 a1, T2 a2, T3 a3, T4 a4) {
		return new MutQuad(a1, a2, a3, a4);
	}

	public static <T1, T2, T3, T4> MutQuad<T1, T2, T3, T4> copyOf(LQuad<T1, T2, T3, T4> tuple) {
		return of(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */

	class MutQuad<T1, T2, T3, T4> extends AbstractQuad<T1, T2, T3, T4> implements Mut<T1, T2, T3, T4, MutQuad<T1, T2, T3, T4>> {

		private T1 first;
		private T2 second;
		private T3 third;
		private T4 fourth;

		public MutQuad(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override MutQuad<T1, T2, T3, T4> first(T1 first) {
			this.first = first;
			return this;
		}

		public @Override T2 second() {
			return second;
		}

		public @Override MutQuad<T1, T2, T3, T4> second(T2 second) {
			this.second = second;
			return this;
		}

		public @Override T3 third() {
			return third;
		}

		public @Override MutQuad<T1, T2, T3, T4> third(T3 third) {
			this.third = third;
			return this;
		}

		public @Override T4 fourth() {
			return fourth;
		}

		public @Override MutQuad<T1, T2, T3, T4> fourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> MutCompQuad<T1, T2, T3, T4> comparableOf() {
		return comparableOf(null, null, null, null);
	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> MutCompQuad<T1, T2, T3, T4> comparableOf(T1 a1, T2 a2, T3 a3, T4 a4) {
		return new MutCompQuad(a1, a2, a3, a4);
	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> MutCompQuad<T1, T2, T3, T4> comparableCopyOf(LQuad<T1, T2, T3, T4> tuple) {
		return comparableOf(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
	}

	/**
	 * Mutable, comparable tuple.
	 */

	final class MutCompQuad<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> extends AbstractQuad<T1, T2, T3, T4>
			implements
				ComparableQuad<T1, T2, T3, T4>,
				Mut<T1, T2, T3, T4, MutCompQuad<T1, T2, T3, T4>> {

		private T1 first;
		private T2 second;
		private T3 third;
		private T4 fourth;

		public MutCompQuad(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override MutCompQuad<T1, T2, T3, T4> first(T1 first) {
			this.first = first;
			return this;
		}

		public @Override T2 second() {
			return second;
		}

		public @Override MutCompQuad<T1, T2, T3, T4> second(T2 second) {
			this.second = second;
			return this;
		}

		public @Override T3 third() {
			return third;
		}

		public @Override MutCompQuad<T1, T2, T3, T4> third(T3 third) {
			this.third = third;
			return this;
		}

		public @Override T4 fourth() {
			return fourth;
		}

		public @Override MutCompQuad<T1, T2, T3, T4> fourth(T4 fourth) {
			this.fourth = fourth;
			return this;
		}

	}

	public static <T1, T2, T3, T4> ImmQuad<T1, T2, T3, T4> immutableOf(T1 a1, T2 a2, T3 a3, T4 a4) {
		return new ImmQuad(a1, a2, a3, a4);
	}

	public static <T1, T2, T3, T4> ImmQuad<T1, T2, T3, T4> immutableCopyOf(LQuad<T1, T2, T3, T4> tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmQuad<T1, T2, T3, T4> extends AbstractQuad<T1, T2, T3, T4> {

		private final T1 first;
		private final T2 second;
		private final T3 third;
		private final T4 fourth;

		public ImmQuad(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override T2 second() {
			return second;
		}

		public @Override T3 third() {
			return third;
		}

		public @Override T4 fourth() {
			return fourth;
		}

	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> ImmCompQuad<T1, T2, T3, T4> immutableComparableOf(T1 a1, T2 a2, T3 a3, T4 a4) {
		return new ImmCompQuad(a1, a2, a3, a4);
	}

	public static <T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> ImmCompQuad<T1, T2, T3, T4> immutableComparableCopyOf(LQuad<T1, T2, T3, T4> tuple) {
		return immutableComparableOf(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
	}

	/**
	 * Immutable, comparable tuple.
	 */
	@Immutable
	final class ImmCompQuad<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>, T4 extends Comparable<? super T4>> extends AbstractQuad<T1, T2, T3, T4> implements ComparableQuad<T1, T2, T3, T4> {

		private final T1 first;
		private final T2 second;
		private final T3 third;
		private final T4 fourth;

		public ImmCompQuad(T1 a1, T2 a2, T3 a3, T4 a4) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
			this.fourth = a4;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override T2 second() {
			return second;
		}

		public @Override T3 third() {
			return third;
		}

		public @Override T4 fourth() {
			return fourth;
		}

	}

}
