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
public interface UseByteSingleTrait<SELF extends UseByteSingleTrait<SELF>> extends LByteSingle, UseByteTrait<SELF>, ByteValueTrait<SELF> {

	// <editor-fold desc="doIf">

	default @Nonnull SELF use(@Nonnull LByteConsumer consumer) {
		consumer.accept(value());
		return self();
	}

	default @Nonnull SELF use(byte a2, @Nonnull LBiByteConsumer consumer) {
		consumer.accept(value(), a2);
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LBiByteConsumer consumer, byte a2) {
		return use(a2, consumer);
	}

	default @Nonnull SELF use(byte a2, byte a3, @Nonnull LTriByteConsumer consumer) {
		consumer.accept(value(), a2, a3);
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LTriByteConsumer consumer, byte a2, byte a3) {
		return use(a2, a3, consumer);
	}

	default @Nonnull SELF useInt(int v, @Nonnull LByteIntPredicate consumer) {
		consumer.test(value(), v);
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF useInt(@Nonnull LByteIntPredicate consumer, int v) {
		return useInt(v, consumer);
	}

	default @Nonnull <V> SELF use_(V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> consumer) {
		consumer.testByteObj(value(), v);
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF use_(@Nonnull LObjBytePredicate.LByteObjPred<? super V> consumer, V v) {
		return use_(v, consumer);
	}

	default @Nonnull <V1> SELF useWithByte(V1 with, @Nonnull LObjBytePredicate<? super V1> consumer) {
		consumer.test(with, value());
		return self();
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF useWithByte(@Nonnull LObjBytePredicate<? super V1> consumer, V1 with) {
		return useWithByte(with, consumer);
	}

	// </editor-fold>
}
