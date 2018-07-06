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
public interface AccessSrtIntPair {

	static AccessSrtIntPair wrap(AccessSrtIntPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LSrtIntPair accessSrtIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseSrtIntPair(LSrtIntPair a) {
		// NOOP
	}

	default void useWith(LSrtIntConsumer accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LSrtIntConsumer.LIntSrtCons accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		accessFunction.doAcceptIntSrt(tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieSrtConsumer<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		accessFunction.doAccept(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		accessFunction.doAcceptObjSrtInt(a1, tuple.first(), tuple.second());
	}

	default <T> void useWith(T a1, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		accessFunction.doAcceptIntObjSrt(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		accessFunction.doAcceptIntSrtObj(tuple.second(), tuple.first(), a1);
	}

	default <T> void useWith(T a1, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		accessFunction.doAcceptSrtObjInt(tuple.first(), a1, tuple.second());
	}

	default <T> void useWith(T a1, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		accessFunction.doAcceptSrtIntObj(tuple.first(), tuple.second(), a1);
	}

	default <R, T> R useWith(T a1, LObjIntSrtFunction<T, R> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		R retval = accessFunction.doApply(a1, tuple.second(), tuple.first());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		R retval = accessFunction.doApplyObjSrtInt(a1, tuple.first(), tuple.second());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		R retval = accessFunction.doApplyIntObjSrt(tuple.second(), a1, tuple.first());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		R retval = accessFunction.doApplyIntSrtObj(tuple.second(), tuple.first(), a1);
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		R retval = accessFunction.doApplySrtObjInt(tuple.first(), a1, tuple.second());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		R retval = accessFunction.doApplySrtIntObj(tuple.first(), tuple.second(), a1);
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieSrtFunction<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		int retval = accessFunction.doApplyAsInt(a1, tuple.second(), tuple.first());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		int retval = accessFunction.doApplyAsIntObjSrtInt(a1, tuple.first(), tuple.second());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		int retval = accessFunction.doApplyAsIntIntObjSrt(tuple.second(), a1, tuple.first());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		int retval = accessFunction.doApplyAsIntIntSrtObj(tuple.second(), tuple.first(), a1);
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		int retval = accessFunction.doApplyAsIntSrtObjInt(tuple.first(), a1, tuple.second());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		int retval = accessFunction.doApplyAsIntSrtIntObj(tuple.first(), tuple.second(), a1);
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntSrtPredicate<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		boolean retval = accessFunction.doTest(a1, tuple.second(), tuple.first());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		boolean retval = accessFunction.doTestObjSrtInt(a1, tuple.first(), tuple.second());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		boolean retval = accessFunction.doTestIntObjSrt(tuple.second(), a1, tuple.first());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		boolean retval = accessFunction.doTestIntSrtObj(tuple.second(), tuple.first(), a1);
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		boolean retval = accessFunction.doTestSrtObjInt(tuple.first(), a1, tuple.second());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		boolean retval = accessFunction.doTestSrtIntObj(tuple.first(), tuple.second(), a1);
		releaseSrtIntPair(tuple);
		return retval;
	}

	default boolean useWith(LSrtIntPredicate accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseSrtIntPair(tuple);
		return retval;
	}

	default boolean useWith(LSrtIntPredicate.LIntSrtPred accessFunction) {
		LSrtIntPair tuple = accessSrtIntPair();
		boolean retval = accessFunction.doTestIntSrt(tuple.second(), tuple.first());
		releaseSrtIntPair(tuple);
		return retval;
	}

}
