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
 * Interface representing a value(s) that can be optionally combined with additional arguments and function call that might produce some other value.
 * Interface implementation is not necessarily holding nor owning the value(s).
 */
@SuppressWarnings("UnusedDeclaration")
public interface AccessBiObjFltTriple<T1, T2> {

	static <T1, T2> AccessBiObjFltTriple<T1, T2> wrap(AccessBiObjFltTriple<T1, T2> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBiObjFltTriple<T1, T2> accessBiObjFltTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBiObjFltTriple(LBiObjFltTriple<T1, T2> a) {
		// NOOP
	}

	default void useWith(LBiObjFltConsumer<T1, T2> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjFltConsumer.LObjFltObj1Cons<T1, T2> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		accessFunction.doAcceptObjFltObj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjFltConsumer.LObj1Obj0FltCons<T2, T1> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		accessFunction.doAcceptObj1Obj0Flt(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjFltConsumer.LObj1FltObj0Cons<T2, T1> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		accessFunction.doAcceptObj1FltObj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjFltConsumer.LFltObj0Obj1Cons<T1, T2> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		accessFunction.doAcceptFltObj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjFltConsumer.LFltObjObj0Cons<T2, T1> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		accessFunction.doAcceptFltObjObj0(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiObjFltFunction<T1, T2, R> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjFltFunction.LObjFltObj1Func<T1, T2, R> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		R retval = accessFunction.doApplyObjFltObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjFltFunction.LObj1Obj0FltFunc<T2, T1, R> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		R retval = accessFunction.doApplyObj1Obj0Flt(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjFltFunction.LObj1FltObj0Func<T2, T1, R> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		R retval = accessFunction.doApplyObj1FltObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjFltFunction.LFltObj0Obj1Func<T1, T2, R> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		R retval = accessFunction.doApplyFltObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjFltFunction.LFltObjObj0Func<T2, T1, R> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		R retval = accessFunction.doApplyFltObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjFltPredicate<T1, T2> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjFltPredicate.LObjFltObj1Pred<T1, T2> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		boolean retval = accessFunction.doTestObjFltObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjFltPredicate.LObj1Obj0FltPred<T2, T1> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		boolean retval = accessFunction.doTestObj1Obj0Flt(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjFltPredicate.LObj1FltObj0Pred<T2, T1> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		boolean retval = accessFunction.doTestObj1FltObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjFltPredicate.LFltObj0Obj1Pred<T1, T2> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		boolean retval = accessFunction.doTestFltObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjFltPredicate.LFltObjObj0Pred<T2, T1> accessFunction) {
		LBiObjFltTriple<T1, T2> tuple = accessBiObjFltTriple();
		boolean retval = accessFunction.doTestFltObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjFltTriple(tuple);
		return retval;
	}

}
