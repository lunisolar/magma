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

package eu.lunisolar.magma.func;

import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.*;
import eu.lunisolar.magma.basics.meta.functional.SequentialRead;
import eu.lunisolar.magma.basics.meta.functional.SequentialWrite;
import eu.lunisolar.magma.basics.meta.functional.type.OFunction;
import eu.lunisolar.magma.basics.meta.functional.type.TeConsumer;
import eu.lunisolar.magma.func.consumer.*;
import eu.lunisolar.magma.func.consumer.primitives.obj.*;
import eu.lunisolar.magma.func.function.*;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.predicate.*;

import javax.annotation.Nonnull;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;

import static eu.lunisolar.magma.func.consumer.LBiConsumer.biCons;
import static eu.lunisolar.magma.func.consumer.LBiConsumer.biConsThrowing;
import static eu.lunisolar.magma.func.function.LFunction.func;
import static eu.lunisolar.magma.func.function.LFunction.identity;

/**
 *
 */
@SuppressWarnings("unchecked")
public interface SA<C, I, E extends aType> extends SequentialRead<C, I, E>, SequentialWrite<C, E> {

	public static @Nonnull <T> LToIntFunction<T> unknownSize() {
		return c -> -1;
	}

	public static class The<C, I, E extends aType> implements SA<C, I, E> {

		private static final SA<Collection, Iterator, a<Object>> COLLECTION = sA(Collection::size, Collection::iterator, Iterator::hasNext, func(Iterator::next), biCons(Collection::add));

		private static final SA<Iterable, Iterator, a<Object>> ITERABLE = sA(unknownSize(), Iterable::iterator, Iterator::hasNext, func(Iterator::next), biConsThrowing(UnsupportedOperationException::new));

		private static final SA<Stream, Iterator, a<Object>> STREAM = sA(unknownSize(), Stream::iterator, Iterator::hasNext, func(Iterator::next), biConsThrowing(UnsupportedOperationException::new));

		private static final SA<Iterator, Iterator, a<Object>> ITERATOR = sA(unknownSize(), identity(), Iterator::hasNext, func(Iterator::next), biConsThrowing(UnsupportedOperationException::new));

		private static final SA<Enumeration, Enumeration, a<Object>> ENUMERATION = sA(unknownSize(), identity(), Enumeration::hasMoreElements, func(Enumeration::nextElement), biConsThrowing(UnsupportedOperationException::new));

		/* adds depending on position */
		private static final SA<ListIterator, ListIterator, a<Object>> LIST_ITERATOR = sA(unknownSize(), identity(), ListIterator::hasNext, func(ListIterator::next), biCons(ListIterator::add));

		private static final SA<Map, Iterator<Map.Entry>, a<Map.Entry>> MAP = sA(unknownSize(), map -> map.entrySet().iterator(), Iterator::hasNext, func(Iterator::next), biCons((Map t, Map.Entry e) -> {
			t.put(e.getKey(), e.getValue());
		}));

		private static final SA<Object[], Iterator, a<Object>> ARRAY = sA(Array::getLength, (Object[] c) -> Arrays.stream(c).iterator(), Iterator::hasNext, func(Iterator::next), biConsThrowing(UnsupportedOperationException::new));

		private static final SA<int[], Iterator, a<Object>> INT_ARRAY_OBJ = sA(Array::getLength, (int[] c) -> Arrays.stream(c).iterator(), Iterator::hasNext, func(Iterator::next), biConsThrowing(UnsupportedOperationException::new));

		private static final SA<long[], Iterator, a<Object>> LONG_ARRAY_OBJ = sA(Array::getLength, (long[] c) -> Arrays.stream(c).iterator(), Iterator::hasNext, func(Iterator::next), biConsThrowing(UnsupportedOperationException::new));

		private static final SA<byte[], Iterator, a<Object>> BYTE_ARRAY_OBJ = sA(Array::getLength, (byte[] c) -> IntStream.range(0, c.length).map(idx -> c[idx]).iterator(), Iterator::hasNext, func(Iterator::next),
				biConsThrowing(UnsupportedOperationException::new));

		private static final SA<byte[], PrimitiveIterator.OfInt, aByte> BYTE_ARRAY = sA(Array::getLength, (byte[] c) -> (PrimitiveIterator.OfInt) IntStream.range(0, c.length).map(idx -> c[idx]).iterator(), PrimitiveIterator.OfInt::hasNext,
				LToByteFunction.toByteFunc(i -> (byte) i.nextInt()), LObjByteConsumer.objByteConsThrowing(UnsupportedOperationException::new));

		private static final SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aByte> BYTE_ITERATOR = sA(unknownSize(), identity(), PrimitiveIterator.OfInt::hasNext, LToByteFunction.toByteFunc(i -> (byte) i.nextInt()),
				LObjByteConsumer.objByteConsThrowing(UnsupportedOperationException::new));

		private static final SA<IntStream, PrimitiveIterator.OfInt, aByte> BYTE_STREAM = sA(unknownSize(), IntStream::iterator, PrimitiveIterator.OfInt::hasNext, LToByteFunction.toByteFunc(i -> (byte) i.nextInt()),
				LObjByteConsumer.objByteConsThrowing(UnsupportedOperationException::new));

		private static final SA<short[], PrimitiveIterator.OfInt, aShort> SHORT_ARRAY = sA(Array::getLength, (short[] c) -> (PrimitiveIterator.OfInt) IntStream.range(0, c.length).map(idx -> c[idx]).iterator(), PrimitiveIterator.OfInt::hasNext,
				LToSrtFunction.toSrtFunc(i -> (short) i.nextInt()), LObjSrtConsumer.objSrtConsThrowing(UnsupportedOperationException::new));

		private static final SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aShort> SHORT_ITERATOR = sA(unknownSize(), identity(), PrimitiveIterator.OfInt::hasNext, LToSrtFunction.toSrtFunc(i -> (short) i.nextInt()),
				LObjSrtConsumer.objSrtConsThrowing(UnsupportedOperationException::new));

		private static final SA<IntStream, PrimitiveIterator.OfInt, aShort> SHORT_STREAM = sA(unknownSize(), IntStream::iterator, PrimitiveIterator.OfInt::hasNext, LToSrtFunction.toSrtFunc(i -> (short) i.nextInt()),
				LObjSrtConsumer.objSrtConsThrowing(UnsupportedOperationException::new));

		private static final SA<int[], PrimitiveIterator.OfInt, aInt> INT_ARRAY = sA(Array::getLength, (int[] c) -> (PrimitiveIterator.OfInt) IntStream.range(0, c.length).map(idx -> c[idx]).iterator(), PrimitiveIterator.OfInt::hasNext,
				LToIntFunction.toIntFunc(i -> (int) i.nextInt()), LObjIntConsumer.objIntConsThrowing(UnsupportedOperationException::new));

		private static final SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aInt> INT_ITERATOR = sA(unknownSize(), identity(), PrimitiveIterator.OfInt::hasNext, LToIntFunction.toIntFunc(i -> (int) i.nextInt()),
				LObjIntConsumer.objIntConsThrowing(UnsupportedOperationException::new));

		private static final SA<IntStream, PrimitiveIterator.OfInt, aInt> INT_STREAM = sA(unknownSize(), IntStream::iterator, PrimitiveIterator.OfInt::hasNext, LToIntFunction.toIntFunc(i -> (int) i.nextInt()),
				LObjIntConsumer.objIntConsThrowing(UnsupportedOperationException::new));

		private static final SA<long[], PrimitiveIterator.OfLong, aLong> LONG_ARRAY = sA(Array::getLength, (long[] c) -> (PrimitiveIterator.OfLong) IntStream.range(0, c.length).mapToLong(idx -> c[idx]).iterator(), PrimitiveIterator.OfLong::hasNext,
				LToLongFunction.toLongFunc(i -> (long) i.nextLong()), LObjLongConsumer.objLongConsThrowing(UnsupportedOperationException::new));

		private static final SA<PrimitiveIterator.OfLong, PrimitiveIterator.OfLong, aLong> LONG_ITERATOR = sA(unknownSize(), identity(), PrimitiveIterator.OfLong::hasNext, LToLongFunction.toLongFunc(i -> (long) i.nextLong()),
				LObjLongConsumer.objLongConsThrowing(UnsupportedOperationException::new));

		private static final SA<LongStream, PrimitiveIterator.OfLong, aLong> LONG_STREAM = sA(unknownSize(), LongStream::iterator, PrimitiveIterator.OfLong::hasNext, LToLongFunction.toLongFunc(i -> (long) i.nextLong()),
				LObjLongConsumer.objLongConsThrowing(UnsupportedOperationException::new));

		private static final SA<float[], PrimitiveIterator.OfDouble, aFloat> FLOAT_ARRAY = sA(Array::getLength, (float[] c) -> (PrimitiveIterator.OfDouble) IntStream.range(0, c.length).mapToDouble(idx -> c[idx]).iterator(),
				PrimitiveIterator.OfDouble::hasNext, LToFltFunction.toFltFunc(i -> (float) i.nextDouble()), LObjFltConsumer.objFltConsThrowing(UnsupportedOperationException::new));

		private static final SA<PrimitiveIterator.OfDouble, PrimitiveIterator.OfDouble, aFloat> FLOAT_ITERATOR = sA(unknownSize(), identity(), PrimitiveIterator.OfDouble::hasNext, LToFltFunction.toFltFunc(i -> (float) i.nextDouble()),
				LObjFltConsumer.objFltConsThrowing(UnsupportedOperationException::new));

		private static final SA<DoubleStream, PrimitiveIterator.OfDouble, aFloat> FLOAT_STREAM = sA(unknownSize(), DoubleStream::iterator, PrimitiveIterator.OfDouble::hasNext, LToFltFunction.toFltFunc(i -> (float) i.nextDouble()),
				LObjFltConsumer.objFltConsThrowing(UnsupportedOperationException::new));

		private static final SA<double[], PrimitiveIterator.OfDouble, aDouble> DOUBLE_ARRAY = sA(Array::getLength, (double[] c) -> (PrimitiveIterator.OfDouble) IntStream.range(0, c.length).mapToDouble(idx -> c[idx]).iterator(),
				PrimitiveIterator.OfDouble::hasNext, LToDblFunction.toDblFunc(i -> (double) i.nextDouble()), LObjDblConsumer.objDblConsThrowing(UnsupportedOperationException::new));

		private static final SA<PrimitiveIterator.OfDouble, PrimitiveIterator.OfDouble, aDouble> DOUBLE_ITERATOR = sA(unknownSize(), identity(), PrimitiveIterator.OfDouble::hasNext, LToDblFunction.toDblFunc(i -> (double) i.nextDouble()),
				LObjDblConsumer.objDblConsThrowing(UnsupportedOperationException::new));

		private static final SA<DoubleStream, PrimitiveIterator.OfDouble, aDouble> DOUBLE_STREAM = sA(unknownSize(), DoubleStream::iterator, PrimitiveIterator.OfDouble::hasNext, LToDblFunction.toDblFunc(i -> (double) i.nextDouble()),
				LObjDblConsumer.objDblConsThrowing(UnsupportedOperationException::new));

		private static final SA<char[], PrimitiveIterator.OfInt, aChar> CHAR_ARRAY = sA(Array::getLength, (char[] c) -> (PrimitiveIterator.OfInt) IntStream.range(0, c.length).map(idx -> c[idx]).iterator(), PrimitiveIterator.OfInt::hasNext,
				LToCharFunction.toCharFunc(i -> (char) i.nextInt()), LObjCharConsumer.objCharConsThrowing(UnsupportedOperationException::new));

		private static final SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aChar> CHAR_ITERATOR = sA(unknownSize(), identity(), PrimitiveIterator.OfInt::hasNext, LToCharFunction.toCharFunc(i -> (char) i.nextInt()),
				LObjCharConsumer.objCharConsThrowing(UnsupportedOperationException::new));

		private static final SA<IntStream, PrimitiveIterator.OfInt, aChar> CHAR_STREAM = sA(unknownSize(), IntStream::iterator, PrimitiveIterator.OfInt::hasNext, LToCharFunction.toCharFunc(i -> (char) i.nextInt()),
				LObjCharConsumer.objCharConsThrowing(UnsupportedOperationException::new));

		private static final SA<boolean[], PrimitiveIterator.OfInt, aBool> BOOLEAN_ARRAY = sA(Array::getLength, (boolean[] c) -> (PrimitiveIterator.OfInt) IntStream.range(0, c.length).map(idx -> c[idx] ? 1 : 0).iterator(),
				PrimitiveIterator.OfInt::hasNext, LPredicate.pred(i -> (boolean) (i.nextInt() > 0)), LObjBoolConsumer.objBoolConsThrowing(UnsupportedOperationException::new));

		private static final SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aBool> BOOLEAN_ITERATOR = sA(unknownSize(), identity(), PrimitiveIterator.OfInt::hasNext, LPredicate.pred(i -> (boolean) (i.nextInt() > 0)),
				LObjBoolConsumer.objBoolConsThrowing(UnsupportedOperationException::new));

		private static final SA<IntStream, PrimitiveIterator.OfInt, aBool> BOOLEAN_STREAM = sA(unknownSize(), IntStream::iterator, PrimitiveIterator.OfInt::hasNext, LPredicate.pred(i -> (boolean) (i.nextInt() > 0)),
				LObjBoolConsumer.objBoolConsThrowing(UnsupportedOperationException::new));

		private final LToIntFunction<C> sizeFunc;

		private final LFunction<C, I> adapter;

		private final LPredicate<I> tester;
		private final OFunction<I, E> getter;

		private final TeConsumer<C, E> consumer;

		public The(LToIntFunction<C> sizeFunc, LFunction<C, I> adapter, LPredicate<I> tester, OFunction<I, E> getter, TeConsumer<C, E> consumer) {
			this.sizeFunc = sizeFunc == null ? unknownSize() : sizeFunc;
			this.adapter = adapter;
			this.tester = tester;
			this.getter = getter;
			this.consumer = consumer;
		}

		@Override
		public OFunction<C, aType.aInt> genericSizeFunc() {
			return sizeFunc;
		}

		public LFunction<C, I> genericAdapter() {
			return adapter;
		}

		public LPredicate<I> genericTester() {
			return tester;
		}

		public OFunction<I, E> genericSupplier() {
			return getter;
		}

		public TeConsumer<C, E> genericConsumer() {
			return consumer;
		}

	}

	public static <C, I, E extends aType> SA<C, I, E> sA(LToIntFunction<C> sizeFunc, LFunction<C, I> adapter, LPredicate<I> tester, OFunction<I, E> getter, TeConsumer<C, E> consumer) {
		return new The<>(sizeFunc, adapter, tester, getter, consumer);
	}

	public static <E, C extends Collection<? extends E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> collection() {
		return (SA) The.COLLECTION;
	}

	public static <E, C extends Collection<? extends E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> sa(Collection ignored) {
		return collection();
	}

	public static <E, C extends Iterable<? extends E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> iterable() {
		return (SA) The.ITERABLE;
	}

	public static <E, C extends Iterable<? extends E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> sa(Iterable ignored) {
		return iterable();
	}

	public static <E, C extends Stream<? extends E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> stream() {
		return (SA) The.STREAM;
	}

	public static <E, C extends Stream<? extends E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> sa(Stream ignored) {
		return stream();
	}

	public static <E, C extends Iterator<? extends E>, A extends a<E>> SA<C, C, A> iterator() {
		return (SA) The.ITERATOR;
	}

	public static <E, C extends Iterator<? extends E>, A extends a<E>> SA<C, C, A> sa(Iterator ignored) {
		return iterator();
	}

	public static <E, C extends Enumeration<? extends E>, A extends a<E>> SA<C, C, A> enumeration() {
		return (SA) The.ENUMERATION;
	}

	public static <E, C extends Enumeration<? extends E>, A extends a<E>> SA<C, C, A> sa(Enumeration ignored) {
		return enumeration();
	}

	public static <E, C extends ListIterator<? extends E>, A extends a<E>> SA<C, C, A> listIterator() {
		return (SA) The.LIST_ITERATOR;
	}

	public static <E, C extends ListIterator<? extends E>, A extends a<E>> SA<C, C, A> sa(ListIterator ignored) {
		return listIterator();
	}

	public static <K, V, C extends Map<K, V>, I extends Iterator<Map.Entry<K, V>>> SA<C, I, a<Map.Entry<K, V>>> map() {
		return (SA) The.MAP;
	}

	public static <K, V, C extends Map<K, V>, I extends Iterator<Map.Entry<K, V>>> SA<C, I, a<Map.Entry<K, V>>> sa(Map ignored) {
		return map();
	}

	public static <E, I extends Iterator<E>, A extends a<E>> SA<E[], I, A> array() {
		return (SA) The.ARRAY;
	}

	public static <E, I extends Iterator<E>, A extends a<E>> SA<E[], I, A> sa(E[] ignored) {
		return array();
	}

	public static SA<byte[], PrimitiveIterator.OfInt, aByte> byteArray() {
		return (SA) The.BYTE_ARRAY;
	}

	public static SA<byte[], PrimitiveIterator.OfInt, aByte> sa(byte[] ignored) {
		return byteArray();
	}

	public static SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aByte> byteIterator() {
		return (SA) The.BYTE_ITERATOR;
	}

	public static SA<IntStream, PrimitiveIterator.OfInt, aByte> byteStream() {
		return (SA) The.BYTE_STREAM;
	}

	public static SA<short[], PrimitiveIterator.OfInt, aShort> shortArray() {
		return (SA) The.SHORT_ARRAY;
	}

	public static SA<short[], PrimitiveIterator.OfInt, aShort> sa(short[] ignored) {
		return shortArray();
	}

	public static SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aShort> shortIterator() {
		return (SA) The.SHORT_ITERATOR;
	}

	public static SA<IntStream, PrimitiveIterator.OfInt, aShort> shortStream() {
		return (SA) The.SHORT_STREAM;
	}

	public static SA<int[], PrimitiveIterator.OfInt, aInt> intArray() {
		return (SA) The.INT_ARRAY;
	}

	public static SA<int[], PrimitiveIterator.OfInt, aInt> sa(int[] ignored) {
		return intArray();
	}

	public static SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aInt> intIterator() {
		return (SA) The.INT_ITERATOR;
	}

	public static SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aInt> sa(PrimitiveIterator.OfInt ignored) {
		return intIterator();
	}

	public static SA<IntStream, PrimitiveIterator.OfInt, aInt> intStream() {
		return (SA) The.INT_STREAM;
	}

	public static SA<IntStream, PrimitiveIterator.OfInt, aInt> sa(IntStream ignored) {
		return intStream();
	}

	public static SA<long[], PrimitiveIterator.OfLong, aLong> longArray() {
		return (SA) The.LONG_ARRAY;
	}

	public static SA<long[], PrimitiveIterator.OfLong, aLong> sa(long[] ignored) {
		return longArray();
	}

	public static SA<PrimitiveIterator.OfLong, PrimitiveIterator.OfLong, aLong> longIterator() {
		return (SA) The.LONG_ITERATOR;
	}

	public static SA<PrimitiveIterator.OfLong, PrimitiveIterator.OfLong, aLong> sa(PrimitiveIterator.OfLong ignored) {
		return longIterator();
	}

	public static SA<LongStream, PrimitiveIterator.OfLong, aLong> longStream() {
		return (SA) The.LONG_STREAM;
	}

	public static SA<LongStream, PrimitiveIterator.OfLong, aLong> sa(LongStream ignored) {
		return longStream();
	}

	public static SA<float[], PrimitiveIterator.OfDouble, aFloat> floatArray() {
		return (SA) The.FLOAT_ARRAY;
	}

	public static SA<float[], PrimitiveIterator.OfDouble, aFloat> sa(float[] ignored) {
		return floatArray();
	}

	public static SA<PrimitiveIterator.OfDouble, PrimitiveIterator.OfDouble, aFloat> floatIterator() {
		return (SA) The.FLOAT_ITERATOR;
	}

	public static SA<DoubleStream, PrimitiveIterator.OfDouble, aFloat> floatStream() {
		return (SA) The.FLOAT_STREAM;
	}

	public static SA<double[], PrimitiveIterator.OfDouble, aDouble> doubleArray() {
		return (SA) The.DOUBLE_ARRAY;
	}

	public static SA<double[], PrimitiveIterator.OfDouble, aDouble> sa(double[] ignored) {
		return doubleArray();
	}

	public static SA<PrimitiveIterator.OfDouble, PrimitiveIterator.OfDouble, aDouble> doubleIterator() {
		return (SA) The.DOUBLE_ITERATOR;
	}

	public static SA<PrimitiveIterator.OfDouble, PrimitiveIterator.OfDouble, aDouble> sa(PrimitiveIterator.OfDouble ignored) {
		return doubleIterator();
	}

	public static SA<DoubleStream, PrimitiveIterator.OfDouble, aDouble> doubleStream() {
		return (SA) The.DOUBLE_STREAM;
	}

	public static SA<DoubleStream, PrimitiveIterator.OfDouble, aDouble> sa(DoubleStream ignored) {
		return doubleStream();
	}

	public static SA<char[], PrimitiveIterator.OfInt, aChar> charArray() {
		return (SA) The.CHAR_ARRAY;
	}

	public static SA<char[], PrimitiveIterator.OfInt, aChar> sa(char[] ignored) {
		return charArray();
	}

	public static SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aChar> charIterator() {
		return (SA) The.CHAR_ITERATOR;
	}

	public static SA<IntStream, PrimitiveIterator.OfInt, aChar> charStream() {
		return (SA) The.CHAR_STREAM;
	}

	public static SA<boolean[], PrimitiveIterator.OfInt, aBool> booleanArray() {
		return (SA) The.BOOLEAN_ARRAY;
	}

	public static SA<boolean[], PrimitiveIterator.OfInt, aBool> sa(boolean[] ignored) {
		return booleanArray();
	}

	public static SA<PrimitiveIterator.OfInt, PrimitiveIterator.OfInt, aBool> booleanIterator() {
		return (SA) The.BOOLEAN_ITERATOR;
	}

	public static SA<IntStream, PrimitiveIterator.OfInt, aBool> booleanStream() {
		return (SA) The.BOOLEAN_STREAM;
	}

	/** Sequential access to int[] (Elements are boxed/unboxed) */
	public static <I extends Iterator<Integer>, A extends a<Integer>> SA<int[], I, A> intArrayObj() {
		return (SA) The.INT_ARRAY_OBJ;
	}

	/** Sequential access to long[] (Elements are boxed/unboxed) */
	public static <I extends Iterator<Long>, A extends a<Long>> SA<long[], I, A> longArrayObj() {
		return (SA) The.LONG_ARRAY_OBJ;
	}

	/** Sequential access to byte[] (Elements are boxed/unboxed) */
	public static <I extends Iterator<Byte>, A extends a<Byte>> SA<byte[], I, A> byteArrayObj() {
		return (SA) The.BYTE_ARRAY_OBJ;
	}

}