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
public interface UseIntTrait<SELF extends UseIntTrait<SELF>> extends FluentTrait<SELF> {

	// <editor-fold desc="doIf">

	public @Nonnull SELF use(@Nonnull LIntConsumer consumer);

	default @Nonnull SELF use(int a2, @Nonnull LBiIntConsumer consumer) {
		return use(a -> consumer.accept(a, a2));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LBiIntConsumer consumer, int a2) {
		return use(a2, consumer);
	}

	default @Nonnull SELF use(int a2, int a3, @Nonnull LTriIntConsumer consumer) {
		return use(a -> consumer.accept(a, a2, a3));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use(@Nonnull LTriIntConsumer consumer, int a2, int a3) {
		return use(a2, a3, consumer);
	}

	default @Nonnull SELF use_Bool(boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred consumer) {
		return use(a -> consumer.testIntBool(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use_Bool(@Nonnull LBoolIntPredicate.LIntBoolPred consumer, boolean v) {
		return use_Bool(v, consumer);
	}

	default @Nonnull SELF use_Byte(byte v, @Nonnull LByteIntPredicate.LIntBytePred consumer) {
		return use(a -> consumer.testIntByte(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use_Byte(@Nonnull LByteIntPredicate.LIntBytePred consumer, byte v) {
		return use_Byte(v, consumer);
	}

	default @Nonnull SELF use_Dbl(double v, @Nonnull LDblIntPredicate.LIntDblPred consumer) {
		return use(a -> consumer.testIntDbl(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use_Dbl(@Nonnull LDblIntPredicate.LIntDblPred consumer, double v) {
		return use_Dbl(v, consumer);
	}

	default @Nonnull SELF use_Char(char v, @Nonnull LCharIntPredicate.LIntCharPred consumer) {
		return use(a -> consumer.testIntChar(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use_Char(@Nonnull LCharIntPredicate.LIntCharPred consumer, char v) {
		return use_Char(v, consumer);
	}

	default @Nonnull SELF use_Srt(short v, @Nonnull LSrtIntPredicate.LIntSrtPred consumer) {
		return use(a -> consumer.testIntSrt(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use_Srt(@Nonnull LSrtIntPredicate.LIntSrtPred consumer, short v) {
		return use_Srt(v, consumer);
	}

	default @Nonnull SELF use_Flt(float v, @Nonnull LFltIntPredicate.LIntFltPred consumer) {
		return use(a -> consumer.testIntFlt(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use_Flt(@Nonnull LFltIntPredicate.LIntFltPred consumer, float v) {
		return use_Flt(v, consumer);
	}

	default @Nonnull SELF use_Long(long v, @Nonnull LLongIntPredicate.LIntLongPred consumer) {
		return use(a -> consumer.testIntLong(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull SELF use_Long(@Nonnull LLongIntPredicate.LIntLongPred consumer, long v) {
		return use_Long(v, consumer);
	}

	default @Nonnull <V> SELF use_(V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> consumer) {
		return use(a -> consumer.testIntObj(a, v));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V> SELF use_(@Nonnull LObjIntPredicate.LIntObjPred<? super V> consumer, V v) {
		return use_(v, consumer);
	}

	default @Nonnull <V1> SELF useWithInt(V1 with, @Nonnull LObjIntPredicate<? super V1> consumer) {
		return use(a -> consumer.test(with, a));
	}

	/** Variant with reverse predicate-vs-arg order. */
	default @Nonnull <V1> SELF useWithInt(@Nonnull LObjIntPredicate<? super V1> consumer, V1 with) {
		return useWithInt(with, consumer);
	}

	// </editor-fold>
}
