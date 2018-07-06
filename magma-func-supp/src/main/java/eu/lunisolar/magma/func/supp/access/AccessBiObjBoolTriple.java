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
public interface AccessBiObjBoolTriple<T1, T2> {

	static <T1, T2> AccessBiObjBoolTriple<T1, T2> wrap(AccessBiObjBoolTriple<T1, T2> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBiObjBoolTriple<T1, T2> accessBiObjBoolTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBiObjBoolTriple(LBiObjBoolTriple<T1, T2> a) {
		// NOOP
	}

	default void useWith(LBiObjBoolConsumer<T1, T2> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjBoolConsumer.LObjBoolObj1Cons<T1, T2> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		accessFunction.doAcceptObjBoolObj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjBoolConsumer.LObj1Obj0BoolCons<T2, T1> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		accessFunction.doAcceptObj1Obj0Bool(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjBoolConsumer.LObj1BoolObj0Cons<T2, T1> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		accessFunction.doAcceptObj1BoolObj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjBoolConsumer.LBoolObj0Obj1Cons<T1, T2> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		accessFunction.doAcceptBoolObj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjBoolConsumer.LBoolObjObj0Cons<T2, T1> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		accessFunction.doAcceptBoolObjObj0(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiObjBoolFunction<T1, T2, R> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjBoolFunction.LObjBoolObj1Func<T1, T2, R> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		R retval = accessFunction.doApplyObjBoolObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjBoolFunction.LObj1Obj0BoolFunc<T2, T1, R> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		R retval = accessFunction.doApplyObj1Obj0Bool(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjBoolFunction.LObj1BoolObj0Func<T2, T1, R> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		R retval = accessFunction.doApplyObj1BoolObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjBoolFunction.LBoolObj0Obj1Func<T1, T2, R> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		R retval = accessFunction.doApplyBoolObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjBoolFunction.LBoolObjObj0Func<T2, T1, R> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		R retval = accessFunction.doApplyBoolObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBoolPredicate<T1, T2> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBoolPredicate.LObjBoolObj1Pred<T1, T2> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		boolean retval = accessFunction.doTestObjBoolObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBoolPredicate.LObj1Obj0BoolPred<T2, T1> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		boolean retval = accessFunction.doTestObj1Obj0Bool(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBoolPredicate.LObj1BoolObj0Pred<T2, T1> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		boolean retval = accessFunction.doTestObj1BoolObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBoolPredicate.LBoolObj0Obj1Pred<T1, T2> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		boolean retval = accessFunction.doTestBoolObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBoolPredicate.LBoolObjObj0Pred<T2, T1> accessFunction) {
		LBiObjBoolTriple<T1, T2> tuple = accessBiObjBoolTriple();
		boolean retval = accessFunction.doTestBoolObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjBoolTriple(tuple);
		return retval;
	}

}
