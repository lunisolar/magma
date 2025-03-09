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
 * Exact equivalent of input parameters used in LTriByteConsumer.
 */
@SuppressWarnings("UnusedDeclaration")
public interface LByteTriple extends LTuple<Byte>, Comparable<LByteTriple> {

	int SIZE = 3;

	byte first();

	byte second();

	byte third();

	@Override
	default Byte get(int index) {
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

	default byte getByte(int index) {
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

	/** Static hashCode() implementation method that takes same arguments as fields of the LByteTriple and calculates hash from it. */
	static int argHashCode(byte a1, byte a2, byte a3) {
		final int prime = 31;
		int result = 1;
		result = prime * result + Byte.hashCode(a1);
		result = prime * result + Byte.hashCode(a2);
		result = prime * result + Byte.hashCode(a3);
		return result;
	}

	/** Static equals() implementation that takes same arguments (doubled) as fields of the LByteTriple and checks if all values are equal. */
	static boolean argEquals(byte a1, byte a2, byte a3, byte b1, byte b2, byte b3) {
		return a1 == b1 && //
				a2 == b2 && //
				a3 == b3; //
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 * Tuples are considered equal if are implementing LByteTriple interface (among others) and their LByteTriple values are equal regardless of the implementing class
	 * and how many more values there are.
	 */
	static boolean argEquals(LByteTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LByteTriple are allowed.
			if (!(two instanceof LByteTriple)) {
				return false;
			}

			LByteTriple other = (LByteTriple) two;

			return argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
		});
	}

	/**
	 * Static equals() implementation that takes two tuples and checks if they are equal.
	 */
	public static boolean tupleEquals(LByteTriple the, Object that) {
		return Null.equals(the, that, (one, two) -> {
			// Intentionally all implementations of LByteTriple are allowed.
			if (!(two instanceof LByteTriple)) {
				return false;
			}

			LByteTriple other = (LByteTriple) two;

			return one.tupleSize() == other.tupleSize() && argEquals(one.first(), one.second(), one.third(), other.first(), other.second(), other.third());
		});
	}

	@Override
	default Iterator<Byte> iterator() {
		return new Iterator<Byte>() {

			private int index;

			@Override
			public boolean hasNext() {
				return index < SIZE;
			}

			@Override
			public Byte next() {
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
				return getByte(index);
			}
		};
	}

	@Override
	default int compareTo(LByteTriple that) {
		return Null.compare(this, that, (one, two) -> {
			int retval = 0;

			return (retval = Byte.compare(one.first(), two.first())) != 0 ? retval : //
					(retval = Byte.compare(one.second(), two.second())) != 0 ? retval : //
							(retval = Byte.compare(one.third(), two.third())) != 0 ? retval : 0; //
		});
	}

	abstract class AbstractByteTriple implements LByteTriple {

		@Override
		public boolean equals(Object that) {
			return LByteTriple.tupleEquals(this, that);
		}

		@Override
		public int hashCode() {
			return LByteTriple.argHashCode(first(), second(), third());
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
	interface Mut<SELF extends Mut<SELF>> extends LByteTriple {

		SELF first(byte first);

		SELF second(byte second);

		SELF third(byte third);

		/** Sets value if predicate(current) is true */
		default SELF setFirstIfCurrent(byte first, LBytePredicate predicate) {//1
			if (predicate.test(this.first())) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setFirstIfNew(byte first, LBytePredicate predicate) {//1
			if (predicate.test(first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setFirstIf(byte first, LBiBytePredicate predicate) {//2
			if (predicate.test(this.first(), first)) {
				return this.first(first);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setSecondIfCurrent(byte second, LBytePredicate predicate) {//1
			if (predicate.test(this.second())) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setSecondIfNew(byte second, LBytePredicate predicate) {//1
			if (predicate.test(second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setSecondIf(byte second, LBiBytePredicate predicate) {//2
			if (predicate.test(this.second(), second)) {
				return this.second(second);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(current) is true */
		default SELF setThirdIfCurrent(byte third, LBytePredicate predicate) {//1
			if (predicate.test(this.third())) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets value if predicate(new) is true */
		default SELF setThirdIfNew(byte third, LBytePredicate predicate) {//1
			if (predicate.test(third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		/** Sets new value if predicate predicate(newValue, current) is true. */
		default SELF setThirdIf(byte third, LBiBytePredicate predicate) {//2
			if (predicate.test(this.third(), third)) {
				return this.third(third);
			}
			return (SELF) this;
		}

		default SELF reset() {
			this.first((byte) 0);
			this.second((byte) 0);
			this.third((byte) 0);
			return (SELF) this;
		}
	}

	public static MutByteTriple of() {
		return of((byte) 0, (byte) 0, (byte) 0);
	}

	public static MutByteTriple of(byte a1, byte a2, byte a3) {
		return new MutByteTriple(a1, a2, a3);
	}

	public static MutByteTriple copyOf(LByteTriple tuple) {
		return of(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Mutable, non-comparable tuple.
	 */
	class MutByteTriple extends AbstractByteTriple implements Mut<MutByteTriple> {

		private byte first;
		private byte second;
		private byte third;

		public MutByteTriple(byte a1, byte a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override byte first() {
			return first;
		}

		public @Override MutByteTriple first(byte first) {
			this.first = first;
			return this;
		}

		public @Override byte second() {
			return second;
		}

		public @Override MutByteTriple second(byte second) {
			this.second = second;
			return this;
		}

		public @Override byte third() {
			return third;
		}

		public @Override MutByteTriple third(byte third) {
			this.third = third;
			return this;
		}

	}

	public static LByteTriple immutableOf(byte a1, byte a2, byte a3) {
		return new ImmByteTriple(a1, a2, a3);
	}

	public static LByteTriple immutableCopyOf(LByteTriple tuple) {
		return immutableOf(tuple.first(), tuple.second(), tuple.third());
	}

	/**
	 * Immutable, non-comparable tuple.
	 */
	@Immutable
	final class ImmByteTriple extends AbstractByteTriple {

		private final byte first;
		private final byte second;
		private final byte third;

		public ImmByteTriple(byte a1, byte a2, byte a3) {
			this.first = a1;
			this.second = a2;
			this.third = a3;
		}

		public @Override byte first() {
			return first;
		}

		public @Override byte second() {
			return second;
		}

		public @Override byte third() {
			return third;
		}
	}

	public static Iterator<LByteTriple.MutByteTriple> mutIterator(PrimitiveIterator.OfInt items) {
		return iterator(items, LByteTriple::of);
	}

	public static Iterator<LByteTriple> immIterator(PrimitiveIterator.OfInt items) {
		return iterator(items, LByteTriple::immutableOf);
	}

	public static <R> Iterator<R> iterator(PrimitiveIterator.OfInt items, LTriByteFunction<R> factory) {
		return iterator(SA.byteIterator(), items, factory);
	}

	public static Stream<LByteTriple.MutByteTriple> mutStream(IntStream items) {
		return stream(items, LByteTriple::of);
	}

	public static Stream<LByteTriple> immStream(IntStream items) {
		return stream(items, LByteTriple::immutableOf);
	}

	public static <R> Stream<R> stream(IntStream items, LTriByteFunction<R> factory) {
		var pairs = iterator(items.iterator(), factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(SequentialRead<C, ?, aByte> sa, C source, LTriByteFunction<R> factory) {
		var pairs = iterator(sa, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Stream<R> stream(IndexedRead<C, aByte> ia, C source, LTriByteFunction<R> factory) {
		var pairs = iterator(ia, source, factory);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(pairs, Spliterator.ORDERED), false);
	}

	public static <C, R> Iterator<R> iterator(SequentialRead<C, ?, aByte> sa_, C source, LTriByteFunction<R> factory) {

		var sa = (SequentialRead<C, Object, aByte>) sa_;
		var iterator = SA.adapter(sa).apply(source);
		var testFunc = SA.tester(sa);
		var nextFunc = SA.byteSupplier(sa);

		return new Iterator<R>() {

			@Override
			public boolean hasNext() {
				return testFunc.doApplyAsBoolean(iterator);
			}

			@Override
			public R next() {
				var a1 = nextFunc.applyAsByte(iterator);
				var a2 = nextFunc.applyAsByte(iterator);
				var a3 = nextFunc.applyAsByte(iterator);
				return factory.apply(a1, a2, a3);
			}
		};
	}

	public static <C, R> Iterator<R> iterator(IndexedRead<C, aByte> ia, C source, LTriByteFunction<R> factory) {

		int size = ia.size(source);
		var oiFunc = IA.byteGetter(ia);

		return new Iterator<R>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < size;
			}

			@Override
			public R next() {
				var a1 = oiFunc.applyAsByte(source, index++);
				var a2 = oiFunc.applyAsByte(source, index++);
				var a3 = oiFunc.applyAsByte(source, index++);
				return factory.apply(a1, a2, a3);
			}
		};
	}

	public static void forEach(IntStream items, LTriByteConsumer consumer) {
		forEach(items.iterator(), consumer);
	}

	public static void forEach(PrimitiveIterator.OfInt items, LTriByteConsumer consumer) {
		var emptyTuples = iterator(items, (a1, a2, a3) -> {
			consumer.accept(a1, a2, a3);
			return null;
		});

		while (emptyTuples.hasNext()) {
			emptyTuples.next();
		}
	}

}
