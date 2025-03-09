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
 * Exact equivalent of input parameters used in LBiCharConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LCharPair extends LTuple<Character>, Comparable<LCharPair> {

	int SIZE = 2;

	char first();

	char second();

	@Override
	default Character get(int index) {
		switch (index) {
			case 1 :
				return first();
			case 2 :
				return second();
			default :
				throw new NoSuchElementException();
		}
	}

	default char getChar(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LCharPair and calculates hash from it. */
	static int argHashCode(char a1, char a2) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Character.hashCode(a1);
		result = prime * result + Character.hashCode(a2);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LCharPair and checks if all values are equal. */
	static boolean argEquals(char a1, char a2, char b1, char b2) {
		return a1 == b1 && //
				a2 == b2; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LCharPair interface (among others) and their LCharPair values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LCharPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharPair are allowed.
			if (!(two instanceof LCharPair)) {
				return false;
			}

			LCharPair other = (LCharPair) two;

			return argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LCharPair the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LCharPair are allowed.
			if (!(two instanceof LCharPair)) {
				return false;
			}

			LCharPair other = (LCharPair) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), other.first(), other.second());
		});
	}

	@Override
	default Iterator<Character> iterator() {
		return new Iterator<Character>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Character next() {
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
				return getChar(index);
			}
		};
	}

	@Override
	default int compareTo(LCharPair that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Character.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Character.compare(one.second(), two.second())) != 0 ? retval : 0; //
		});
	}

	abstract class AbstractCharPair implements LCharPair {

		@Override
		public boolean equals(Object that) {
			return LCharPair.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LCharPair.argHashCode(first(), second());
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
	interface Mut<SELF extends Mut<SELF>> extends LCharPair {

		SELF first(char first);

		SELF second(char second);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIfCurrent(char first, LCharPredicate predicate) {//1
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setFirstIfNew(char first, LCharPredicate predicate) {//1
			if (predicate.test(first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(char first, LBiCharPredicate predicate) {//2
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setSecondIfCurrent(char second, LCharPredicate predicate) {//1
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setSecondIfNew(char second, LCharPredicate predicate) {//1
			if (predicate.test(second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(char second, LBiCharPredicate predicate) {//2
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first('\u0000');
			this.second('\u0000');
			return (SELF) this;
		}
	}

	public static MutCharPair of() {
		return of('\u0000', '\u0000');
	}

	public static MutCharPair of(char a1, char a2) {
		return new MutCharPair(a1, a2);
	}

	public static MutCharPair copyOf(LCharPair tuple) {
		return of(tuple.first(), tuple.second());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutCharPair extends AbstractCharPair implements Mut<MutCharPair> {

		private char first;
		private char second;

		public MutCharPair(char a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override char first() {
			return first;
		}

		public @Override MutCharPair first(char first) {
			this.first = first;
			return this;
		}

		public @Override char second() {
			return second;
		}

		public @Override MutCharPair second(char second) {
			this.second = second;
			return this;
		}

	}

	public static LCharPair immutableOf(char a1, char a2) {
		return new ImmCharPair(a1, a2);
	}

	public static LCharPair immutableCopyOf(LCharPair tuple) {
		return immutableOf(tuple.first(), tuple.second());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmCharPair extends AbstractCharPair {

		private final char first;
		private final char second;

		public ImmCharPair(char a1, char a2) {
			this.first = a1;
			this.second = a2;
		}

		public @Override char first() {
			return first;
		}

		public @Override char second() {
			return second;
		}
	}

	public static Iterator<LCharPair.MutCharPair> mutIterator(PrimitiveIterator.OfInt items) {
		return iterator(items, LCharPair::of);
	}

	public static Iterator<LCharPair> immIterator(PrimitiveIterator.OfInt items) {
		return iterator(items, LCharPair::immutableOf);
	}

	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LBiCharFunction<R> factory) {
		return iterator(SA.charIterator(), items, factory);
	}

	public static Stream<LCharPair.MutCharPair> mutStream(IntStream items) {
		return stream(items, LCharPair::of);
	}

	public static Stream<LCharPair> immStream(IntStream items) {
		return stream(items, LCharPair::immutableOf);
	}

	public static <R> Stream<R> stream(IntStream items, LBiCharFunction<R> factory) {
		var pairs = iterator(items.iterator(), factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(SequentialRead<C, ?, aChar> sa, C source, LBiCharFunction<R> factory) {
		var pairs = iterator(sa, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(IndexedRead<C, aChar> ia, C source, LBiCharFunction<R> factory) {
		var pairs = iterator(ia, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Iterator<R> iterator(SequentialRead<C, ?, aChar> sa_, C source, LBiCharFunction<R> factory) {

		var sa = (SequentialRead<C, Object, aChar>) sa_;
		var iterator = SA.adapter(sa).apply(source);
		var testFunc = SA.tester(sa);
		var nextFunc = SA.charSupplier(sa);

		return new Iterator<R>() {

			@Override
			public boolean hasNext() {
				return testFunc.doApplyAsBoolean(iterator);
			}

			@Override
			public R next() {
				var a1 = nextFunc.applyAsChar(iterator);
				var a2 = nextFunc.applyAsChar(iterator);
				return factory.apply(a1, a2);
			}
		};
	}

	public static <C, R> Iterator<R> iterator(IndexedRead<C, aChar> ia, C source, LBiCharFunction<R> factory) {

		int size = ia.size(source);
		var oiFunc = IA.charGetter(ia);

		return new Iterator<R>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public R next() {
				var a1 = oiFunc.applyAsChar(source, index++);
				var a2 = oiFunc.applyAsChar(source, index++);
				return factory.apply(a1, a2);
			}
		};
	}

	public static void forEach(IntStream items, LBiCharConsumer consumer) {
		forEach(items.iterator(), consumer);
	}

	public static void forEach(PrimitiveIterator.OfInt items, LBiCharConsumer consumer) {
		var emptyTuples = iterator(items, (a1, a2) -> {
			consumer.accept(a1, a2);
			return null;
		});

		while (emptyTuples.hasNext()) {
			emptyTuples.next();
		}
	}

}
