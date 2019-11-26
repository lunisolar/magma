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

package eu.lunisolar.magma.func;

import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.a;
import eu.lunisolar.magma.basics.meta.functional.SequentialRead;
import eu.lunisolar.magma.basics.meta.functional.SequentialWrite;
import eu.lunisolar.magma.basics.meta.functional.type.OFunction;
import eu.lunisolar.magma.basics.meta.functional.type.TeConsumer;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.predicate.LPredicate;

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

	public static <E, C extends Collection<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> collection() {
		return (SA) The.COLLECTION;
	}

	public static <E, C extends Collection<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> sa(Collection ignored) {
		return collection();
	}

	public static <E, C extends Iterable<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> iterable() {
		return (SA) The.ITERABLE;
	}

	public static <E, C extends Iterable<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> sa(Iterable ignored) {
		return iterable();
	}

	public static <E, C extends Stream<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> stream() {
		return (SA) The.STREAM;
	}

	public static <E, C extends Stream<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> sa(Stream ignored) {
		return stream();
	}

	public static <E, C extends Iterator<E>, A extends a<E>> SA<C, C, A> iterator() {
		return (SA) The.ITERATOR;
	}

	public static <E, C extends Iterator<E>, A extends a<E>> SA<C, C, A> sa(Iterator ignored) {
		return iterator();
	}

	public static <E, C extends Enumeration<E>, A extends a<E>> SA<C, C, A> enumeration() {
		return (SA) The.ENUMERATION;
	}

	public static <E, C extends Enumeration<E>, A extends a<E>> SA<C, C, A> sa(Enumeration ignored) {
		return enumeration();
	}

	public static <E, C extends ListIterator<E>, A extends a<E>> SA<C, C, A> listIterator() {
		return (SA) The.LIST_ITERATOR;
	}

	public static <E, C extends ListIterator<E>, A extends a<E>> SA<C, C, A> sa(ListIterator ignored) {
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

	/** Sequantial access to int[] (Element are boxed/unboxed) */
	public static <Integer, I extends Iterator<Integer>, A extends a<Integer>> SA<int[], I, A> intArrayObj() {
		return (SA) The.INT_ARRAY_OBJ;
	}

	public static <Integer, I extends Iterator<Integer>, A extends a<Integer>> SA<int[], I, A> sa(int[] ignored) {
		return intArrayObj();
	}

}