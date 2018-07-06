/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.predicate.LPredicate;

import java.util.*;
import java.util.stream.*;

import static eu.lunisolar.magma.func.consumer.LBiConsumer.biCons;
import static eu.lunisolar.magma.func.function.LFunction.func;
import static eu.lunisolar.magma.func.function.LFunction.identity;

/**
 *    
 */
@SuppressWarnings("unchecked")
public class SA<C, I, E extends aType> implements SequentialRead<C, I, E>, SequentialWrite<C, E> {

	public static final LToIntFunction UNKNOWN_SIZE = c -> -1;

	private static final SA<Collection, Iterator, a<Object>> COLLECTION = sA(Collection::size, Collection::iterator, Iterator::hasNext, func(Iterator::next), biCons(Collection::add));

	private static final SA<Iterable, Iterator, a<Object>> ITERABLE = sA(UNKNOWN_SIZE, Iterable::iterator, Iterator::hasNext, func(Iterator::next), biCons((t, e) -> {
		throw new UnsupportedOperationException();
	}));

	private static final SA<Stream, Iterator, a<Object>> STREAM = sA(UNKNOWN_SIZE, Stream::iterator, Iterator::hasNext, func(Iterator::next), biCons((t, e) -> {
		throw new UnsupportedOperationException();
	}));

	private static final SA<Iterator, Iterator, a<Object>> ITERATOR = sA(UNKNOWN_SIZE, identity(), Iterator::hasNext, func(Iterator::next), biCons((t, e) -> {
		throw new UnsupportedOperationException();
	}));

	private static final SA<Enumeration, Enumeration, a<Object>> ENUMERATION = sA(UNKNOWN_SIZE, identity(), Enumeration::hasMoreElements, func(Enumeration::nextElement), biCons((t, e) -> {
		throw new UnsupportedOperationException();
	}));

	/* adds depending on position */
	private static final SA<ListIterator, ListIterator, a<Object>> LIST_ITERATOR = sA(UNKNOWN_SIZE, identity(), ListIterator::hasNext, func(ListIterator::next), biCons(ListIterator::add));

	private static final SA<Map, Iterator<Map.Entry>, a<Map.Entry>> MAP = sA(UNKNOWN_SIZE, map -> map.entrySet().iterator(), Iterator::hasNext, func(Iterator::next), biCons((Map t, Map.Entry e) -> {
		t.put(e.getKey(), e.getValue());
	}));

	private final LToIntFunction<C> sizeFunc;

	private final LFunction<C, I> adapter;

	private final LPredicate<I> tester;
	private final OFunction<I, E> getter;

	private final TeConsumer<C, E> adder;

	public SA(LToIntFunction<C> sizeFunc, LFunction<C, I> adapter, LPredicate<I> tester, OFunction<I, E> getter, TeConsumer<C, E> adder) {
		this.sizeFunc = sizeFunc == null ? UNKNOWN_SIZE : sizeFunc;
		this.adapter = adapter;
		this.tester = tester;
		this.getter = getter;
		this.adder = adder;
	}

	public static <C, I, E extends aType> SA<C, I, E> sA(LToIntFunction<C> sizeFunc, LFunction<C, I> adapter, LPredicate<I> tester, OFunction<I, E> getter, TeConsumer<C, E> adder) {
		return new SA<>(sizeFunc, adapter, tester, getter, adder);
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

	public OFunction<I, E> genericGetter() {
		return getter;
	}

	public TeConsumer<C, E> genericAdder() {
		return adder;
	}

	private static void unsupported() {
		throw new UnsupportedOperationException();
	}

	public static <E, C extends Collection<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> collection() {
		return (SA) COLLECTION;
	}

	public static <E, C extends Iterable<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> iterable() {
		return (SA) ITERABLE;
	}

	public static <E, C extends Stream<E>, I extends Iterator<E>, A extends a<E>> SA<C, I, A> stream() {
		return (SA) STREAM;
	}

	public static <E, C extends Iterator<E>, A extends a<E>> SA<C, C, A> iterator() {
		return (SA) ITERATOR;
	}

	public static <E, C extends Enumeration<E>, A extends a<E>> SA<C, C, A> enumeration() {
		return (SA) ENUMERATION;
	}

	public static <E, C extends ListIterator<E>, A extends a<E>> SA<C, C, A> listIterator() {
		return (SA) LIST_ITERATOR;
	}

	public static <K, V, C extends Map<K, V>, I extends Iterator<Map.Entry<K, V>>> SA<C, I, a<Map.Entry<K, V>>> map() {
		return (SA) MAP;
	}

}