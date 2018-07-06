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
public interface AccessObjFltPair<T> {

	static <T> AccessObjFltPair<T> wrap(AccessObjFltPair<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjFltPair<T> accessObjFltPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjFltPair(LObjFltPair<T> a) {
		// NOOP
	}

	default void useWith(LObjFltConsumer<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LObjFltConsumer.LFltObjCons<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		accessFunction.doAcceptFltObj(tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieFltConsumer<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		accessFunction.doAccept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		accessFunction.doAcceptObjFltInt(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		accessFunction.doAcceptIntObjFlt(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		accessFunction.doAcceptIntFltObj(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		accessFunction.doAcceptFltObjInt(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		accessFunction.doAcceptFltIntObj(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(LObjFltFunction<T, R> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releaseObjFltPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjFltFunction.LFltObjFunc<T, R> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		R retval = accessFunction.doApplyFltObj(tuple.second(), tuple.first());
		releaseObjFltPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFltFunction<T, R> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		R retval = accessFunction.doApply(tuple.first(), a2, tuple.second());
		releaseObjFltPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		R retval = accessFunction.doApplyObjFltInt(tuple.first(), tuple.second(), a2);
		releaseObjFltPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		R retval = accessFunction.doApplyIntObjFlt(a2, tuple.first(), tuple.second());
		releaseObjFltPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		R retval = accessFunction.doApplyIntFltObj(a2, tuple.second(), tuple.first());
		releaseObjFltPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		R retval = accessFunction.doApplyFltObjInt(tuple.second(), tuple.first(), a2);
		releaseObjFltPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		R retval = accessFunction.doApplyFltIntObj(tuple.second(), a2, tuple.first());
		releaseObjFltPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFltFunction<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), a2, tuple.second());
		releaseObjFltPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		int retval = accessFunction.doApplyAsIntObjFltInt(tuple.first(), tuple.second(), a2);
		releaseObjFltPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		int retval = accessFunction.doApplyAsIntIntObjFlt(a2, tuple.first(), tuple.second());
		releaseObjFltPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		int retval = accessFunction.doApplyAsIntIntFltObj(a2, tuple.second(), tuple.first());
		releaseObjFltPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		int retval = accessFunction.doApplyAsIntFltObjInt(tuple.second(), tuple.first(), a2);
		releaseObjFltPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		int retval = accessFunction.doApplyAsIntFltIntObj(tuple.second(), a2, tuple.first());
		releaseObjFltPair(tuple);
		return retval;
	}

	default boolean useWith(LObjFltPredicate<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseObjFltPair(tuple);
		return retval;
	}

	default boolean useWith(LObjFltPredicate.LFltObjPred<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		boolean retval = accessFunction.doTestFltObj(tuple.second(), tuple.first());
		releaseObjFltPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntFltPredicate<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		boolean retval = accessFunction.doTest(tuple.first(), a2, tuple.second());
		releaseObjFltPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		boolean retval = accessFunction.doTestObjFltInt(tuple.first(), tuple.second(), a2);
		releaseObjFltPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		boolean retval = accessFunction.doTestIntObjFlt(a2, tuple.first(), tuple.second());
		releaseObjFltPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		boolean retval = accessFunction.doTestIntFltObj(a2, tuple.second(), tuple.first());
		releaseObjFltPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		boolean retval = accessFunction.doTestFltObjInt(tuple.second(), tuple.first(), a2);
		releaseObjFltPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		LObjFltPair<T> tuple = accessObjFltPair();
		boolean retval = accessFunction.doTestFltIntObj(tuple.second(), a2, tuple.first());
		releaseObjFltPair(tuple);
		return retval;
	}

}
