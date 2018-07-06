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
 * ByteFunnel is a finalized and reusable for of tunnel. In order to reduce GC one side (target/output) was reduced to some type of container
 * (not specific instance, but specific type).
 *
 * ByteFunnel contains method that allow to push and/or transform some set of values of type [E] (entry values) to a container of
 * values of type [byte].
 * 
 * ByteFunnel the operations are organized in a way that those operations do not need to maintain a state while traversing the values. The main purpose
 * of this is to reduce the GC and make once defined set of operations a reusable structure (object instances). The downside of this is obvious heavy reduction
 * in possible operations compared to Java Streams. And while GC is being reduced (or totally zeroed when used properly) the performance is not so obvious.
 * For one, Tunnels are faster than Streams in micro loops (even if completely ignoring reusability). Beyond that, performance gain is kept with growing number
 * of elements, but not with growing number of operations in chain (or more precisely not with growing number of effective operations per element).
 * In some cases performance might be actually lower than Java Streams.
 */
@SuppressWarnings("UnusedDeclaration")
public class ByteFunnel<C, E extends aType> {

	private final TeConsumer<C, E> tunnel;

	private ByteFunnel(TeConsumer<C, E> tunnel) {
		this.tunnel = tunnel;
	}

	public static <C, E extends aType> ByteFunnel<C, E> byteFunnel(TeConsumer<C, E> tieFunction) {
		return new ByteFunnel(tieFunction);
	}

	public <S> ByteFunnel<C, E> push(C target, IndexedRead<S, E> ia, S source) {
		tunnel.genericForEach(target, ia, source);
		return this;
	}

	public <S, I> ByteFunnel<C, E> push(C target, SequentialRead<S, I, E> sa, S source) {
		tunnel.genericIterate(target, sa, source);
		return this;
	}

}
