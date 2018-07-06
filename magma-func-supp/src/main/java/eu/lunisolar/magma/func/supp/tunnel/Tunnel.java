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

package eu.lunisolar.magma.func.supp.tunnel;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.tuple.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Tunnel is a tunnel that encloses set of operations that when executed sequentially on some set of values of type [E]
 * (entry values) will produce some set (can be empty) of values of type [T]. Tunnel contains methods that can add to the
 * sequence of operations in order to further process of convert the final values while keeping the same type of entry values.
 *
 * Tunnel abstracts completely from where the values will came from (e.g. array, collection) and where the values will be put in. And concentrates
 * on sequence of operations that is going to be done to the values.
 *
 * In order to make Tunnel really reusable an termination of operation chain is required in form of Funnel<C,E>.
 */
@SuppressWarnings("UnusedDeclaration")
public interface Tunnel<T, E extends aType> extends LFunction<LBiConsumer<Object, ? super T>, TeConsumer<Object, E>> {

	public static <T, E extends aType> Tunnel<T, E> tunnel(Tunnel<T, E> tunnel) {
		return tunnel;
	}

	public static <T, E extends aType> Tunnel<T, E> tunnel(LFunction<LBiConsumer<Object, ? super T>, TeConsumer<Object, E>> tunnelFunction) {
		return tunnelFunction::doApply;
	}

	public static <T> Tunnel<T, a<T>> tunnel() {
		return (Tunnel) down -> down;
	}

	public default Tunnel<T, E> filter(LPredicate<T> predicate) {
		return Tunnel.tunnel(this.funcCompose((LBiConsumer down) -> (t, e) -> predicate.doIf(t, e, down)));
	}

	public default <R> Tunnel<R, E> map(LFunction<T, R> mapper) {
		return Tunnel.tunnel(this.funcCompose((LBiConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApply(e))));
	}

	public default BoolTunnel<E> mapToBool(LPredicate<T> mapper) {
		return BoolTunnel.boolTunnel(this.funcCompose((LObjBoolConsumer down) -> (t, e) -> down.doAccept(t, mapper.doTest(e))));
	}

	public default ByteTunnel<E> mapToByte(LToByteFunction<T> mapper) {
		return ByteTunnel.byteTunnel(this.funcCompose((LObjByteConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsByte(e))));
	}

	public default DblTunnel<E> mapToDbl(LToDblFunction<T> mapper) {
		return DblTunnel.dblTunnel(this.funcCompose((LObjDblConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsDbl(e))));
	}

	public default CharTunnel<E> mapToChar(LToCharFunction<T> mapper) {
		return CharTunnel.charTunnel(this.funcCompose((LObjCharConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsChar(e))));
	}

	public default SrtTunnel<E> mapToSrt(LToSrtFunction<T> mapper) {
		return SrtTunnel.srtTunnel(this.funcCompose((LObjSrtConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsSrt(e))));
	}

	public default FltTunnel<E> mapToFlt(LToFltFunction<T> mapper) {
		return FltTunnel.fltTunnel(this.funcCompose((LObjFltConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsFlt(e))));
	}

	public default IntTunnel<E> mapToInt(LToIntFunction<T> mapper) {
		return IntTunnel.intTunnel(this.funcCompose((LObjIntConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsInt(e))));
	}

	public default LongTunnel<E> mapToLong(LToLongFunction<T> mapper) {
		return LongTunnel.longTunnel(this.funcCompose((LObjLongConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsLong(e))));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default <R> Tunnel<R, E> flatMap(IndexedRead<T, a<R>> ia) {
		return Tunnel.tunnel(this.funcCompose((LBiConsumer down) -> (t, e) -> LBiConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <R, I> Tunnel<R, E> flatMap(SequentialRead<T, I, a<R>> sa) {
		return Tunnel.tunnel(this.funcCompose((LBiConsumer down) -> (t, e) -> LBiConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default BoolTunnel<E> flatMapToBool(IndexedRead<T, aBool> ia) {
		return BoolTunnel.boolTunnel(this.funcCompose((LObjBoolConsumer down) -> (t, e) -> LObjBoolConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <I> BoolTunnel<E> flatMapToBool(SequentialRead<T, I, aBool> sa) {
		return BoolTunnel.boolTunnel(this.funcCompose((LObjBoolConsumer down) -> (t, e) -> LObjBoolConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default ByteTunnel<E> flatMapToByte(IndexedRead<T, aByte> ia) {
		return ByteTunnel.byteTunnel(this.funcCompose((LObjByteConsumer down) -> (t, e) -> LObjByteConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <I> ByteTunnel<E> flatMapToByte(SequentialRead<T, I, aByte> sa) {
		return ByteTunnel.byteTunnel(this.funcCompose((LObjByteConsumer down) -> (t, e) -> LObjByteConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default DblTunnel<E> flatMapToDbl(IndexedRead<T, aDouble> ia) {
		return DblTunnel.dblTunnel(this.funcCompose((LObjDblConsumer down) -> (t, e) -> LObjDblConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <I> DblTunnel<E> flatMapToDbl(SequentialRead<T, I, aDouble> sa) {
		return DblTunnel.dblTunnel(this.funcCompose((LObjDblConsumer down) -> (t, e) -> LObjDblConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default CharTunnel<E> flatMapToChar(IndexedRead<T, aChar> ia) {
		return CharTunnel.charTunnel(this.funcCompose((LObjCharConsumer down) -> (t, e) -> LObjCharConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <I> CharTunnel<E> flatMapToChar(SequentialRead<T, I, aChar> sa) {
		return CharTunnel.charTunnel(this.funcCompose((LObjCharConsumer down) -> (t, e) -> LObjCharConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default SrtTunnel<E> flatMapToSrt(IndexedRead<T, aShort> ia) {
		return SrtTunnel.srtTunnel(this.funcCompose((LObjSrtConsumer down) -> (t, e) -> LObjSrtConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <I> SrtTunnel<E> flatMapToSrt(SequentialRead<T, I, aShort> sa) {
		return SrtTunnel.srtTunnel(this.funcCompose((LObjSrtConsumer down) -> (t, e) -> LObjSrtConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default FltTunnel<E> flatMapToFlt(IndexedRead<T, aFloat> ia) {
		return FltTunnel.fltTunnel(this.funcCompose((LObjFltConsumer down) -> (t, e) -> LObjFltConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <I> FltTunnel<E> flatMapToFlt(SequentialRead<T, I, aFloat> sa) {
		return FltTunnel.fltTunnel(this.funcCompose((LObjFltConsumer down) -> (t, e) -> LObjFltConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default IntTunnel<E> flatMapToInt(IndexedRead<T, aInt> ia) {
		return IntTunnel.intTunnel(this.funcCompose((LObjIntConsumer down) -> (t, e) -> LObjIntConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <I> IntTunnel<E> flatMapToInt(SequentialRead<T, I, aInt> sa) {
		return IntTunnel.intTunnel(this.funcCompose((LObjIntConsumer down) -> (t, e) -> LObjIntConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Flat-map any collection accessible with index (e.g. list) by OI (object-index function). */
	public default LongTunnel<E> flatMapToLong(IndexedRead<T, aLong> ia) {
		return LongTunnel.longTunnel(this.funcCompose((LObjLongConsumer down) -> (t, e) -> LObjLongConsumer.targetedForEach(t, ia, e, down)));
	}

	/** Flat-map any collection accessible via iteration. */
	public default <I> LongTunnel<E> flatMapToLong(SequentialRead<T, I, aLong> sa) {
		return LongTunnel.longTunnel(this.funcCompose((LObjLongConsumer down) -> (t, e) -> LObjLongConsumer.targetedIterate(t, sa, e, down)));
	}

	/** Narrows end of the tunnel to specific type of container supported by the TIE function (but not specific instance) */
	public default <C> Funnel<C, E> funnelTo(LBiConsumer<C, ? super T> consumer) {
		return Funnel.funnel(this.doApply((LBiConsumer) consumer));
	}

	/** Narrows end of the tunnel to specific type of container (but not specific instance) */
	public default <C> Funnel<C, E> funnelTo(SequentialWrite<C, a<T>> sa) {
		return funnelTo((LBiConsumer) sa.adder());
	}

}
