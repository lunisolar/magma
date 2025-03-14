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
 * Exact equivalent of input parameters used in LTriSrtConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LSrtTriple extends LTuple<Short>, Comparable<LSrtTriple> {

	int SIZE = 3;

	short first();

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

		/** Sets value if predicate(current) is true */
		default SELF setFirstIfCurrent(short first, LSrtPredicate predicate) {//1
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setFirstIfNew(short first, LSrtPredicate predicate) {//1
			if (predicate.test(first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(short first, LBiSrtPredicate predicate) {//2
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setSecondIfCurrent(short second, LSrtPredicate predicate) {//1
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setSecondIfNew(short second, LSrtPredicate predicate) {//1
			if (predicate.test(second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(short second, LBiSrtPredicate predicate) {//2
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setThirdIfCurrent(short third, LSrtPredicate predicate) {//1
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setThirdIfNew(short third, LSrtPredicate predicate) {//1
			if (predicate.test(third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setThirdIf(short third, LBiSrtPredicate predicate) {//2
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

	public static LSrtTriple immutableOf(short a1, short a2, short a3) {
		return new ImmSrtTriple(a1, a2, a3);
	}

	public static LSrtTriple immutableCopyOf(LSrtTriple tuple) {
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

	public static Iterator<LSrtTriple.MutSrtTriple> mutIterator(PrimitiveIterator.OfInt items) {
		return iterator(items, LSrtTriple::of);
	}

	public static Iterator<LSrtTriple> immIterator(PrimitiveIterator.OfInt items) {
		return iterator(items, LSrtTriple::immutableOf);
	}

	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LTriSrtFunction<R> factory) {
		return iterator(SA.shortIterator(), items, factory);
	}

	public static Stream<LSrtTriple.MutSrtTriple> mutStream(IntStream items) {
		return stream(items, LSrtTriple::of);
	}

	public static Stream<LSrtTriple> immStream(IntStream items) {
		return stream(items, LSrtTriple::immutableOf);
	}

	public static <R> Stream<R> stream(IntStream items, LTriSrtFunction<R> factory) {
		var pairs = iterator(items.iterator(), factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(SequentialRead<C, ?, aShort> sa, C source, LTriSrtFunction<R> factory) {
		var pairs = iterator(sa, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(IndexedRead<C, aShort> ia, C source, LTriSrtFunction<R> factory) {
		var pairs = iterator(ia, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Iterator<R> iterator(SequentialRead<C, ?, aShort> sa_, C source, LTriSrtFunction<R> factory) {

		var sa = (SequentialRead<C, Object, aShort>) sa_;
		var iterator = SA.adapter(sa).apply(source);
		var testFunc = SA.tester(sa);
		var nextFunc = SA.srtSupplier(sa);

		return new Iterator<R>() {

			@Override
			public boolean hasNext() {
				return testFunc.doApplyAsBoolean(iterator);
			}

			@Override
			public R next() {
				var a1 = nextFunc.applyAsSrt(iterator);
				var a2 = nextFunc.applyAsSrt(iterator);
				var a3 = nextFunc.applyAsSrt(iterator);
				return factory.apply(a1, a2, a3);
			}
		};
	}

	public static <C, R> Iterator<R> iterator(IndexedRead<C, aShort> ia, C source, LTriSrtFunction<R> factory) {

		int size = ia.size(source);
		var oiFunc = IA.srtGetter(ia);

		return new Iterator<R>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public R next() {
				var a1 = oiFunc.applyAsSrt(source, index++);
				var a2 = oiFunc.applyAsSrt(source, index++);
				var a3 = oiFunc.applyAsSrt(source, index++);
				return factory.apply(a1, a2, a3);
			}
		};
	}

	public static void forEach(IntStream items, LTriSrtConsumer consumer) {
		forEach(items.iterator(), consumer);
	}

	public static void forEach(PrimitiveIterator.OfInt items, LTriSrtConsumer consumer) {
		var emptyTuples = iterator(items, (a1, a2, a3) -> {
			consumer.accept(a1, a2, a3);
			return null;
		});

		while (emptyTuples.hasNext()) {
			emptyTuples.next();
		}
	}

}
