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

package eu.lunisolar.magma.func.supp.access;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR

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
 * Interface representing a value(s) that can be optionally combined with additional arguments and function call that might produce some other value.
 * Interface implementation is not necessarily holding nor owning the value(s).
 */
@SuppressWarnings("UnusedDeclaration")
public interface AccessBiObjShortTriple<T1, T2> {

	static <T1, T2> AccessBiObjShortTriple<T1, T2> wrap(AccessBiObjShortTriple<T1, T2> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBiObjShortTriple<T1, T2> accessBiObjShortTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBiObjShortTriple(LBiObjShortTriple<T1, T2> a) {
		// NOOP
	}

	default void useWith(LBiObjShortConsumer<T1, T2> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjShortConsumer.V1<T1, T2> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		accessFunction.doAcceptV1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjShortConsumer.V2<T2, T1> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjShortConsumer.V3<T2, T1> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		accessFunction.doAcceptV3(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjShortConsumer.V4<T1, T2> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		accessFunction.doAcceptV4(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjShortConsumer.V5<T2, T1> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		accessFunction.doAcceptV5(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiObjShortFunction<T1, T2, R> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjShortFunction.V1<T1, T2, R> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		R retval = accessFunction.doApplyV1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjShortFunction.V2<T2, T1, R> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjShortFunction.V3<T2, T1, R> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		R retval = accessFunction.doApplyV3(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjShortFunction.V4<T1, T2, R> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		R retval = accessFunction.doApplyV4(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjShortFunction.V5<T2, T1, R> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		R retval = accessFunction.doApplyV5(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjShortPredicate<T1, T2> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjShortPredicate.V1<T1, T2> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		boolean retval = accessFunction.doTestV1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjShortPredicate.V2<T2, T1> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjShortPredicate.V3<T2, T1> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		boolean retval = accessFunction.doTestV3(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjShortPredicate.V4<T1, T2> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		boolean retval = accessFunction.doTestV4(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjShortPredicate.V5<T2, T1> accessFunction) {
		LBiObjShortTriple<T1, T2> tuple = accessBiObjShortTriple();
		boolean retval = accessFunction.doTestV5(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjShortTriple(tuple);
		return retval;
	}

}
