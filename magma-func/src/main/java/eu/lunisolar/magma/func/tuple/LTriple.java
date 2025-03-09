/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.*;
import eu.lunisolar.magma.basics.meta.functional.*;
import eu.lunisolar.magma.func.*;
import eu.lunisolar.magma.func.consumer.*;
import eu.lunisolar.magma.func.consumer.primitives.bi.*;
import eu.lunisolar.magma.func.consumer.primitives.tri.*;
import eu.lunisolar.magma.func.function.*;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.function.from.*;
import eu.lunisolar.magma.func.operator.unary.*;
import eu.lunisolar.magma.func.operator.binary.*;
import eu.lunisolar.magma.func.predicate.*;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.*;
import java.util.stream.*;

/**
 * Exact equivalent of input parameters used in LTriConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LTriple<T1, T2, T3> extends LTuple<Object>, Map.Entry<T2, T3> {

	int SIZE = 3;

	T1 first();

	T2 second();

	T3 third();

	default T3 value() {
		return third();
	}

	@Override
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
	@Override
	default int tupleSize() {
		return SIZE;
	}

	/** Returns value as Entry.getValue(). */
	@Override
	default T3 getValue() {
		return third();
	}

	//<editor-fold desc="Map.Entry">

	/** Returns key as Entry.getKey() */
	@Override
	default T2 getKey() {
		return second();
	}

	@Override
	default T3 setValue(T3 value) {
		throw new UnsupportedOperationException();
	}

	//</editor-fold>

	/** Static hashCode() implementation method that takes same arguments as fields of the LTriple and calculates hash from it. */
	static <T1, T2, T3> int argHashCode(T1 a1, T2 a2, T3 a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a1 == null) ? 0 : a1.hashCode());
		result = prime * result + ((a2 == null) ? 0 : a2.hashCode());
		result = prime * result + ((a3 == null) ? 0 : a3.hashCode());
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LTriple and checks if all values are equal. */
	static <T1, T2, T3> boolean argEquals(T1 a1, T2 a2, T3 a3, T1 b1, T2 b2, T3 b3) {
		return Null.equals(a1, b1) && //
				Null.equals(a2, b2) && //
				Null.equals(a3, b3); //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LTriple interface (among others) and their LTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LTriple are allowed.
			if (!(two instanceof LTriple)) {
				return false;
			}

			LTriple other = (LTriple) two;

			return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LTriple are allowed.
			if (!(two instanceof LTriple)) {
				return false;
			}

			LTriple other = (LTriple) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
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

	interface ComparableTriple<T1 extends Comparable<? super T1>, T2 extends Comparable<? super T2>, T3 extends Comparable<? super T3>> extends LTriple<T1, T2, T3>, Comparable<LTriple<T1, T2, T3>> {
		@Override
		default int compareTo(LTriple<T1, T2, T3> that) {
			return Null.compare(this, that, (one, two) -> {
				int retval = 0;

				return (retval = Null.compare(one.first(), two.first())) != 0 ? retval : //
						(retval = Null.compare(one.second(), two.second())) != 0 ? retval : //
								(retval = Null.compare(one.third(), two.third())) != 0 ? retval : 0; //
			});
		}

	}

	abstract class AbstractTriple<T1, T2, T3> implements LTriple<T1, T2, T3> {

		@Override
		public boolean equals(Object that) {
			return LTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LTriple.argHashCode(first(), second(), third());
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
	interface Mut<T1, T2, T3, SELF extends Mut<T1, T2, T3, SELF>> extends LTriple<T1, T2, T3> {

		SELF first(T1 first);

		SELF second(T2 second);

		SELF third(T3 third);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIf(T1 first, LPredicate<T1> predicate) {
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(T1 first, LBiPredicate<T1, T1> predicate) {
			if (predicate.test(first, this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) is true. */
		default SELF setFirstIf(LBiPredicate<T1, T1> predicate, T1 first) {
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setSecondIf(T2 second, LPredicate<T2> predicate) {
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(T2 second, LBiPredicate<T2, T2> predicate) {
			if (predicate.test(second, this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) is true. */
		default SELF setSecondIf(LBiPredicate<T2, T2> predicate, T2 second) {
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default LTriple<T1, T2, T3> value(T3 value) {
			third(value);
			return this;
		}

		default T3 setValue(T3 value) {
			var old = third();
			third(value);
			return old;
		}

		/** Sets value if predicate(current) is true */
		default SELF setThirdIf(T3 third, LPredicate<T3> predicate) {
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setThirdIf(T3 third, LBiPredicate<T3, T3> predicate) {
			if (predicate.test(third, this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(current, newValue) is true. */
		default SELF setThirdIf(LBiPredicate<T3, T3> predicate, T3 third) {
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(null);
			this.second(null);
			this.third(null);
			return (SELF) this;
		}
	}

	public static <T1, T2, T3> MutTriple<T1, T2, T3> of() {
		return of(null, null, null);
	}

	public static <T1, T2, T3> MutTriple<T1, T2, T3> of(T1 a1, T2 a2, T3 a3) {
		return new MutTriple(a1, a2, a3);
	}

	public static <T1, T2, T3> MutTriple<T1, T2, T3> copyOf(LTriple<T1, T2, T3> tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutTriple<T1, T2, T3> extends AbstractTriple<T1, T2, T3> implements Mut<T1, T2, T3, MutTriple<T1, T2, T3>> {

		private T1 first;
		private T2 second;
		private T3 third;

		public MutTriple(T1 a1, T2 a2, T3 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override T1 first() {
			return first;
		}

		public @Override MutTriple<T1, T2, T3> first(T1 first) {
			this.first = first;
			return this;
		}

		public @Override T2 second() {
			return second;
		}

		public @Override MutTriple<T1, T2, T3> second(T2 second) {
			this.second = second;
			return this;
		}

		public @Override T3 third() {
			return third;
		}

		public @Override MutTriple<T1, T2, T3> third(T3 third) {
			this.third = third;
			return this;
		}

	}

	public static <T1, T2, T3> LTriple<T1, T2, T3> immutableOf(T1 a1, T2 a2, T3 a3) {
		return new ImmTriple(a1, a2, a3);
	}

	public static <T1, T2, T3> LTriple<T1, T2, T3> immutableCopyOf(LTriple<T1, T2, T3> tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmTriple<T1, T2, T3> extends AbstractTriple<T1, T2, T3> {

		private final T1 first;
		private final T2 second;
		private final T3 third;

		public ImmTriple(T1 a1, T2 a2, T3 a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
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
	}

	public static <T> Iterator<LTriple.MutTriple> mutIterator(Iterator<? extends T> items) {
		return iterator(items, LTriple::of);
	}

	public static <T> Iterator<LTriple> immIterator(Iterator<? extends T> items) {
		return iterator(items, LTriple::immutableOf);
	}

	public static <T, R> Iterator<R> iterator(Iterator<? extends T> items, LTriFunction<T, T, T, R> factory) {
		return iterator(SA.sa(items), items, factory);
	}

	public static <T> Stream<LTriple.MutTriple> mutStream(Stream<? extends T> items) {
		return stream(items, LTriple::of);
	}

	public static <T> Stream<LTriple> immStream(Stream<? extends T> items) {
		return stream(items, LTriple::immutableOf);
	}

	public static <T, R> Stream<R> stream(Stream<? extends T> items, LTriFunction<T, T, T, R> factory) {
		var pairs = iterator(items.iterator(), factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <T, C, R> Stream<R> stream(SequentialRead<C, ?, a<T>> sa, C source, LTriFunction<T, T, T, R> factory) {
		var pairs = iterator(sa, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <T, C, R> Stream<R> stream(IndexedRead<C, a<T>> ia, C source, LTriFunction<T, T, T, R> factory) {
		var pairs = iterator(ia, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <T, C, R> Iterator<R> iterator(SequentialRead<C, ?, a<T>> sa_, C source, LTriFunction<T, T, T, R> factory) {

		var sa = (SequentialRead<C, Object, a<T>>) sa_;
		var iterator = SA.adapter(sa).apply(source);
		var testFunc = SA.tester(sa);
		var nextFunc = SA.supplier(sa);

		return new Iterator<R>() {

			@Override
			public boolean hasNext() {
				return testFunc.doApplyAsBoolean(iterator);
			}

			@Override
			public R next() {
				var a1 = nextFunc.apply(iterator);
				var a2 = nextFunc.apply(iterator);
				var a3 = nextFunc.apply(iterator);
				return factory.apply(a1, a2, a3);
			}
		};
	}

	public static <T, C, R> Iterator<R> iterator(IndexedRead<C, a<T>> ia, C source, LTriFunction<T, T, T, R> factory) {

		int size = ia.size(source);
		var oiFunc = IA.getter(ia);

		return new Iterator<R>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public R next() {
				var a1 = oiFunc.apply(source, index++);
				var a2 = oiFunc.apply(source, index++);
				var a3 = oiFunc.apply(source, index++);
				return factory.apply(a1, a2, a3);
			}
		};
	}

	public static <T> void forEach(Stream<? extends T> items, LTriConsumer<T, T, T> consumer) {
		forEach(items.iterator(), consumer);
	}

	public static <T> void forEach(Iterator<? extends T> items, LTriConsumer<T, T, T> consumer) {
		var emptyTuples = iterator(items, (a1, a2, a3) -> {
			consumer.accept(a1, a2, a3);
			return null;
		});

		while (emptyTuples.hasNext()) {
			emptyTuples.next();
		}
	}

}
