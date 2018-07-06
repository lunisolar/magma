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
 * SrtTunnel is a tunnel that encloses set of operations that when executed sequentially on some set of values of type [E]
 * (entry values) will produce some set (can be empty) of values of type [short]. SrtTunnel contains methods that can add to the
 * sequence of operations in order to further process of convert the final values while keeping the same type of entry values.
 *
 * SrtTunnel abstracts completely from where the values will came from (e.g. array, collection) and where the values will be put in. And concentrates
 * on sequence of operations that is going to be done to the values.
 *
 * In order to make SrtTunnel really reusable an termination of operation chain is required in form of SrtFunnel<C,E>.
 */
@SuppressWarnings("UnusedDeclaration")
public interface SrtTunnel<E extends aType> extends LFunction<LObjSrtConsumer<Object>, TeConsumer<Object, E>> {

	public static <E extends aType> SrtTunnel<E> srtTunnel(SrtTunnel<E> tunnel) {
		return tunnel;
	}

	public static <E extends aType> SrtTunnel<E> srtTunnel(LFunction<LObjSrtConsumer<Object>, TeConsumer<Object, E>> tunnelFunction) {
		return tunnelFunction::doApply;
	}

	public static SrtTunnel<aShort> srtTunnel() {
		return (SrtTunnel) down -> down;
	}

	public default SrtTunnel<E> filter(LSrtPredicate predicate) {
		return SrtTunnel.srtTunnel(this.funcCompose((LObjSrtConsumer down) -> (t, e) -> predicate.doIf(t, e, down)));
	}

	public default <R> Tunnel<R, E> mapToObj(LSrtFunction<R> mapper) {
		return Tunnel.tunnel(this.funcCompose((LBiConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApply(e))));
	}

	public default BoolTunnel<E> mapToBool(LSrtPredicate mapper) {
		return BoolTunnel.boolTunnel(this.funcCompose((LObjBoolConsumer down) -> (t, e) -> down.doAccept(t, mapper.doTest(e))));
	}

	public default ByteTunnel<E> mapToByte(LSrtToByteFunction mapper) {
		return ByteTunnel.byteTunnel(this.funcCompose((LObjByteConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsByte(e))));
	}

	public default DblTunnel<E> mapToDbl(LSrtToDblFunction mapper) {
		return DblTunnel.dblTunnel(this.funcCompose((LObjDblConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsDbl(e))));
	}

	public default CharTunnel<E> mapToChar(LSrtToCharFunction mapper) {
		return CharTunnel.charTunnel(this.funcCompose((LObjCharConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsChar(e))));
	}

	public default SrtTunnel<E> map(LSrtUnaryOperator mapper) {
		return SrtTunnel.srtTunnel(this.funcCompose((LObjSrtConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsSrt(e))));
	}

	public default FltTunnel<E> mapToFlt(LSrtToFltFunction mapper) {
		return FltTunnel.fltTunnel(this.funcCompose((LObjFltConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsFlt(e))));
	}

	public default IntTunnel<E> mapToInt(LSrtToIntFunction mapper) {
		return IntTunnel.intTunnel(this.funcCompose((LObjIntConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsInt(e))));
	}

	public default LongTunnel<E> mapToLong(LSrtToLongFunction mapper) {
		return LongTunnel.longTunnel(this.funcCompose((LObjLongConsumer down) -> (t, e) -> down.doAccept(t, mapper.doApplyAsLong(e))));
	}

	/** Narrows end of the tunnel to specific type of container supported by the TIE function (but not specific instance) */
	public default <C> SrtFunnel<C, E> funnelTo(LObjSrtConsumer<C> consumer) {
		return SrtFunnel.srtFunnel(this.doApply((LObjSrtConsumer) consumer));
	}

	/** Narrows end of the tunnel to specific type of container (but not specific instance) */
	public default <C> SrtFunnel<C, E> funnelTo(SequentialWrite<C, aShort> sa) {
		return funnelTo((LObjSrtConsumer) sa.adder());
	}

}
