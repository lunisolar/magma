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
 * Exact equivalent of input parameters used in LBiIntConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LIntPair extends LTuple<Integer>, Comparable<LIntPair> {

	int SIZE = 2;

	int first();

	int second();

	@Override
	default Integer get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default int getInt(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LIntPair and calculates hash from it. */
	static int argHashCode(int a1, int a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.hashCode(a1);
		result = prime * result + Integer.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LIntPair and checks if all values are equal. */
	static boolean argEquals(int a1, int a2, int b1, int b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LIntPair interface (among others) and their LIntPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LIntPair are allowed.
			if (!(two instanceof LIntPair)) {
				return false;
			}

			LIntPair other = (LIntPair) two;

			return argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LIntPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LIntPair are allowed.
			if (!(two instanceof LIntPair)) {
				return false;
			}

			LIntPair other = (LIntPair) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	@Override
	default Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Integer next() {
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
				return getInt(index);
			}
		};
	}

	@Override
	default int compareTo(LIntPair that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Integer.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Integer.compare(one.second(), two.second())) != 0 ? retval : 0; //
		});
	}

	abstract class AbstractIntPair implements LIntPair {

		@Override
		public boolean equals(Object that) {
			return LIntPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LIntPair.argHashCode(first(), second());
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
	interface Mut<SELF extends Mut<SELF>> extends LIntPair {

		SELF first(int first);

		SELF second(int second);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIfCurrent(int first, LIntPredicate predicate) {//1
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setFirstIfNew(int first, LIntPredicate predicate) {//1
			if (predicate.test(first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(int first, LBiIntPredicate predicate) {//2
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setSecondIfCurrent(int second, LIntPredicate predicate) {//1
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setSecondIfNew(int second, LIntPredicate predicate) {//1
			if (predicate.test(second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(int second, LBiIntPredicate predicate) {//2
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(0);
			this.second(0);
			return (SELF) this;
		}
	}

	public static MutIntPair of() {
		return of(0, 0);
	}

	public static MutIntPair of(int a1, int a2) {
		return new MutIntPair(a1, a2);
	}

	public static MutIntPair copyOf(LIntPair tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutIntPair extends AbstractIntPair implements Mut<MutIntPair> {

		private int first;
		private int second;

		public MutIntPair(int a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override int first() {
			return first;
		}

		public @Override MutIntPair first(int first) {
			this.first = first;
			return this;
		}

		public @Override int second() {
			return second;
		}

		public @Override MutIntPair second(int second) {
			this.second = second;
			return this;
		}

	}

	public static LIntPair immutableOf(int a1, int a2) {
		return new ImmIntPair(a1, a2);
	}

	public static LIntPair immutableCopyOf(LIntPair tuple) {
		return immutableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmIntPair extends AbstractIntPair {

		private final int first;
		private final int second;

		public ImmIntPair(int a1, int a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override int first() {
			return first;
		}

		public @Override int second() {
			return second;
		}
	}

	public static Iterator<LIntPair.MutIntPair> mutIterator(PrimitiveIterator.OfInt items) {
		return iterator(items, LIntPair::of);
	}

	public static Iterator<LIntPair> immIterator(PrimitiveIterator.OfInt items) {
		return iterator(items, LIntPair::immutableOf);
	}

	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LBiIntFunction<R> factory) {
		return iterator(SA.sa(items), items, factory);
	}

	public static Stream<LIntPair.MutIntPair> mutStream(IntStream items) {
		return stream(items, LIntPair::of);
	}

	public static Stream<LIntPair> immStream(IntStream items) {
		return stream(items, LIntPair::immutableOf);
	}

	public static <R> Stream<R> stream(IntStream items, LBiIntFunction<R> factory) {
		var pairs = iterator(items.iterator(), factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(SequentialRead<C, ?, aInt> sa, C source, LBiIntFunction<R> factory) {
		var pairs = iterator(sa, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(IndexedRead<C, aInt> ia, C source, LBiIntFunction<R> factory) {
		var pairs = iterator(ia, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Iterator<R> iterator(SequentialRead<C, ?, aInt> sa_, C source, LBiIntFunction<R> factory) {

		var sa = (SequentialRead<C, Object, aInt>) sa_;
		var iterator = SA.adapter(sa).apply(source);
		var testFunc = SA.tester(sa);
		var nextFunc = SA.intSupplier(sa);

		return new Iterator<R>() {

			@Override
			public boolean hasNext() {
				return testFunc.doApplyAsBoolean(iterator);
			}

			@Override
			public R next() {
				var a1 = nextFunc.applyAsInt(iterator);
				var a2 = nextFunc.applyAsInt(iterator);
				return factory.apply(a1, a2);
			}
		};
	}

	public static <C, R> Iterator<R> iterator(IndexedRead<C, aInt> ia, C source, LBiIntFunction<R> factory) {

		int size = ia.size(source);
		var oiFunc = IA.intGetter(ia);

		return new Iterator<R>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public R next() {
				var a1 = oiFunc.applyAsInt(source, index++);
				var a2 = oiFunc.applyAsInt(source, index++);
				return factory.apply(a1, a2);
			}
		};
	}

	public static void forEach(IntStream items, LBiIntConsumer consumer) {
		forEach(items.iterator(), consumer);
	}

	public static void forEach(PrimitiveIterator.OfInt items, LBiIntConsumer consumer) {
		var emptyTuples = iterator(items, (a1, a2) -> {
			consumer.accept(a1, a2);
			return null;
		});

		while (emptyTuples.hasNext()) {
			emptyTuples.next();
		}
	}

}
