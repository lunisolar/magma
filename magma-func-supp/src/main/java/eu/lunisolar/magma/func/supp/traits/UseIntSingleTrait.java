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

package eu.lunisolar.magma.func.supp.traits;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.*; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.check.*; // NOSONAR
import eu.lunisolar.magma.func.supp.memento.*; // NOSONAR
import eu.lunisolar.magma.func.supp.value.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; // NOSONAR

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
 * Trait for any class that has fluent filter method.
 */
public interface UseIntSingleTrait<SELF extends UseIntSingleTrait<SELF>> extends LIntSingle, UseIntTrait<SELF>, IntValueTrait<SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF use(@Nonnull LIntConsumer consumer) {
		consumer.accept(value());
		return self();
	}

	default @Nonnull SELF use(int a2, @Nonnull LBiIntConsumer consumer) {
		consumer.accept(value(), a2);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LBiIntConsumer consumer, int a2) {
		return use(a2, consumer);
	}

	default @Nonnull SELF use(int a2, int a3, @Nonnull LTriIntConsumer consumer) {
		consumer.accept(value(), a2, a3);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LTriIntConsumer consumer, int a2, int a3) {
		return use(a2, a3, consumer);
	}

	default @Nonnull SELF useBool_(boolean v, @Nonnull LBoolIntConsumer.LIntBoolCons consumer) {
		consumer.acceptIntBool(value(), v);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useBool_(@Nonnull LBoolIntConsumer.LIntBoolCons consumer, boolean v) {
		return useBool_(v, consumer);
	}

	default @Nonnull SELF useByte_(byte v, @Nonnull LByteIntConsumer.LIntByteCons consumer) {
		consumer.acceptIntByte(value(), v);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useByte_(@Nonnull LByteIntConsumer.LIntByteCons consumer, byte v) {
		return useByte_(v, consumer);
	}

	default @Nonnull SELF useDbl_(double v, @Nonnull LDblIntConsumer.LIntDblCons consumer) {
		consumer.acceptIntDbl(value(), v);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useDbl_(@Nonnull LDblIntConsumer.LIntDblCons consumer, double v) {
		return useDbl_(v, consumer);
	}

	default @Nonnull SELF useChar_(char v, @Nonnull LCharIntConsumer.LIntCharCons consumer) {
		consumer.acceptIntChar(value(), v);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useChar_(@Nonnull LCharIntConsumer.LIntCharCons consumer, char v) {
		return useChar_(v, consumer);
	}

	default @Nonnull SELF useSrt_(short v, @Nonnull LSrtIntConsumer.LIntSrtCons consumer) {
		consumer.acceptIntSrt(value(), v);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useSrt_(@Nonnull LSrtIntConsumer.LIntSrtCons consumer, short v) {
		return useSrt_(v, consumer);
	}

	default @Nonnull SELF useFlt_(float v, @Nonnull LFltIntConsumer.LIntFltCons consumer) {
		consumer.acceptIntFlt(value(), v);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useFlt_(@Nonnull LFltIntConsumer.LIntFltCons consumer, float v) {
		return useFlt_(v, consumer);
	}

	default @Nonnull SELF useLong_(long v, @Nonnull LLongIntConsumer.LIntLongCons consumer) {
		consumer.acceptIntLong(value(), v);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull SELF useLong_(@Nonnull LLongIntConsumer.LIntLongCons consumer, long v) {
		return useLong_(v, consumer);
	}

	default @Nonnull <V> SELF use_(V v, @Nonnull LObjIntConsumer.LIntObjCons<? super V> consumer) {
		consumer.acceptIntObj(value(), v);
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V> SELF use_(@Nonnull LObjIntConsumer.LIntObjCons<? super V> consumer, V v) {
		return use_(v, consumer);
	}

	default @Nonnull <V1> SELF useWithInt(V1 with1, @Nonnull LObjIntConsumer<? super V1> consumer) {
		consumer.accept(with1, value());
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1> SELF useWithInt(@Nonnull LObjIntConsumer<? super V1> consumer, V1 with1) {
		return useWithInt(with1, consumer);
	}

	default @Nonnull <V1, V2> SELF useWith(V1 with1, V2 with2, @Nonnull LBiObjIntConsumer<? super V1, ? super V2> consumer) {
		consumer.accept(with1, with2, value());
		return self();
	}

	/** Variant with reverse function-vs-arg order. */
	default @Nonnull <V1, V2> SELF useWith(@Nonnull LBiObjIntConsumer<? super V1, ? super V2> consumer, V1 with1, V2 with2) {
		return useWith(with1, with2, consumer);
	}

	// </editor-fold>
}
