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
 * Exact equivalent of input parameters used in LBiDblConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LDblPair extends LTuple<Double>, Comparable<LDblPair> {

	int SIZE = 2;

	double first();

	double second();

	@Override
	default Double get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default double getDouble(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LDblPair and calculates hash from it. */
	static int argHashCode(double a1, double a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(a1);
		result = prime * result + Double.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LDblPair and checks if all values are equal. */
	static boolean argEquals(double a1, double a2, double b1, double b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LDblPair interface (among others) and their LDblPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LDblPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblPair are allowed.
			if (!(two instanceof LDblPair)) {
				return false;
			}

			LDblPair other = (LDblPair) two;

			return argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LDblPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LDblPair are allowed.
			if (!(two instanceof LDblPair)) {
				return false;
			}

			LDblPair other = (LDblPair) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	@Override
	default Iterator<Double> iterator() {
		return new Iterator<Double>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Double next() {
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
				return getDouble(index);
			}
		};
	}

	@Override
	default int compareTo(LDblPair that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Double.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Double.compare(one.second(), two.second())) != 0 ? retval : 0; //
		});
	}

	abstract class AbstractDblPair implements LDblPair {

		@Override
		public boolean equals(Object that) {
			return LDblPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LDblPair.argHashCode(first(), second());
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
	interface Mut<SELF extends Mut<SELF>> extends LDblPair {

		SELF first(double first);

		SELF second(double second);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIfCurrent(double first, LDblPredicate predicate) {//1
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setFirstIfNew(double first, LDblPredicate predicate) {//1
			if (predicate.test(first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(double first, LBiDblPredicate predicate) {//2
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setSecondIfCurrent(double second, LDblPredicate predicate) {//1
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setSecondIfNew(double second, LDblPredicate predicate) {//1
			if (predicate.test(second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(double second, LBiDblPredicate predicate) {//2
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first(0d);
			this.second(0d);
			return (SELF) this;
		}
	}

	public static MutDblPair of() {
		return of(0d, 0d);
	}

	public static MutDblPair of(double a1, double a2) {
		return new MutDblPair(a1, a2);
	}

	public static MutDblPair copyOf(LDblPair tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutDblPair extends AbstractDblPair implements Mut<MutDblPair> {

		private double first;
		private double second;

		public MutDblPair(double a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override double first() {
			return first;
		}

		public @Override MutDblPair first(double first) {
			this.first = first;
			return this;
		}

		public @Override double second() {
			return second;
		}

		public @Override MutDblPair second(double second) {
			this.second = second;
			return this;
		}

	}

	public static LDblPair immutableOf(double a1, double a2) {
		return new ImmDblPair(a1, a2);
	}

	public static LDblPair immutableCopyOf(LDblPair tuple) {
		return immutableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmDblPair extends AbstractDblPair {

		private final double first;
		private final double second;

		public ImmDblPair(double a1, double a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override double first() {
			return first;
		}

		public @Override double second() {
			return second;
		}
	}

	public static Iterator<LDblPair.MutDblPair> mutIterator(PrimitiveIterator.OfDouble items) {
		return iterator(items, LDblPair::of);
	}

	public static Iterator<LDblPair> immIterator(PrimitiveIterator.OfDouble items) {
		return iterator(items, LDblPair::immutableOf);
	}

	public static <R> Iterator<R> iterator(PrimitiveIterator.OfDouble items, LBiDblFunction<R> factory) {
		return iterator(SA.sa(items), items, factory);
	}

	public static Stream<LDblPair.MutDblPair> mutStream(DoubleStream items) {
		return stream(items, LDblPair::of);
	}

	public static Stream<LDblPair> immStream(DoubleStream items) {
		return stream(items, LDblPair::immutableOf);
	}

	public static <R> Stream<R> stream(DoubleStream items, LBiDblFunction<R> factory) {
		var pairs = iterator(items.iterator(), factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(SequentialRead<C, ?, aDouble> sa, C source, LBiDblFunction<R> factory) {
		var pairs = iterator(sa, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(IndexedRead<C, aDouble> ia, C source, LBiDblFunction<R> factory) {
		var pairs = iterator(ia, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Iterator<R> iterator(SequentialRead<C, ?, aDouble> sa_, C source, LBiDblFunction<R> factory) {

		var sa = (SequentialRead<C, Object, aDouble>) sa_;
		var iterator = SA.adapter(sa).apply(source);
		var testFunc = SA.tester(sa);
		var nextFunc = SA.dblSupplier(sa);

		return new Iterator<R>() {

			@Override
			public boolean hasNext() {
				return testFunc.doApplyAsBoolean(iterator);
			}

			@Override
			public R next() {
				var a1 = nextFunc.applyAsDbl(iterator);
				var a2 = nextFunc.applyAsDbl(iterator);
				return factory.apply(a1, a2);
			}
		};
	}

	public static <C, R> Iterator<R> iterator(IndexedRead<C, aDouble> ia, C source, LBiDblFunction<R> factory) {

		int size = ia.size(source);
		var oiFunc = IA.dblGetter(ia);

		return new Iterator<R>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public R next() {
				var a1 = oiFunc.applyAsDbl(source, index++);
				var a2 = oiFunc.applyAsDbl(source, index++);
				return factory.apply(a1, a2);
			}
		};
	}

	public static void forEach(DoubleStream items, LBiDblConsumer consumer) {
		forEach(items.iterator(), consumer);
	}

	public static void forEach(PrimitiveIterator.OfDouble items, LBiDblConsumer consumer) {
		var emptyTuples = iterator(items, (a1, a2) -> {
			consumer.accept(a1, a2);
			return null;
		});

		while (emptyTuples.hasNext()) {
			emptyTuples.next();
		}
	}

}
