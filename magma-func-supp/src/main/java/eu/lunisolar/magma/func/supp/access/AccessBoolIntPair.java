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
public interface AccessBoolIntPair {

	static AccessBoolIntPair wrap(AccessBoolIntPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBoolIntPair accessBoolIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBoolIntPair(LBoolIntPair a) {
		// NOOP
	}

	default void useWith(LBoolIntConsumer accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LBoolIntConsumer.LIntBoolCons accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		accessFunction.doAcceptIntBool(tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieBoolConsumer<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		accessFunction.doAccept(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		accessFunction.doAcceptObjBoolInt(a1, tuple.first(), tuple.second());
	}

	default <T> void useWith(T a1, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		accessFunction.doAcceptIntObjBool(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		accessFunction.doAcceptIntBoolObj(tuple.second(), tuple.first(), a1);
	}

	default <T> void useWith(T a1, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		accessFunction.doAcceptBoolObjInt(tuple.first(), a1, tuple.second());
	}

	default <T> void useWith(T a1, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		accessFunction.doAcceptBoolIntObj(tuple.first(), tuple.second(), a1);
	}

	default <R, T> R useWith(T a1, LObjIntBoolFunction<T, R> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		R retval = accessFunction.doApply(a1, tuple.second(), tuple.first());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		R retval = accessFunction.doApplyObjBoolInt(a1, tuple.first(), tuple.second());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		R retval = accessFunction.doApplyIntObjBool(tuple.second(), a1, tuple.first());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		R retval = accessFunction.doApplyIntBoolObj(tuple.second(), tuple.first(), a1);
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		R retval = accessFunction.doApplyBoolObjInt(tuple.first(), a1, tuple.second());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		R retval = accessFunction.doApplyBoolIntObj(tuple.first(), tuple.second(), a1);
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieBoolFunction<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		int retval = accessFunction.doApplyAsInt(a1, tuple.second(), tuple.first());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		int retval = accessFunction.doApplyAsIntObjBoolInt(a1, tuple.first(), tuple.second());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		int retval = accessFunction.doApplyAsIntIntObjBool(tuple.second(), a1, tuple.first());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		int retval = accessFunction.doApplyAsIntIntBoolObj(tuple.second(), tuple.first(), a1);
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		int retval = accessFunction.doApplyAsIntBoolObjInt(tuple.first(), a1, tuple.second());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		int retval = accessFunction.doApplyAsIntBoolIntObj(tuple.first(), tuple.second(), a1);
		releaseBoolIntPair(tuple);
		return retval;
	}

	default boolean useWith(LBoolIntPredicate accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default boolean useWith(LBoolIntPredicate.LIntBoolPred accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		boolean retval = accessFunction.doTestIntBool(tuple.second(), tuple.first());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBoolPredicate<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		boolean retval = accessFunction.doTest(a1, tuple.second(), tuple.first());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		boolean retval = accessFunction.doTestObjBoolInt(a1, tuple.first(), tuple.second());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		boolean retval = accessFunction.doTestIntObjBool(tuple.second(), a1, tuple.first());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		boolean retval = accessFunction.doTestIntBoolObj(tuple.second(), tuple.first(), a1);
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		boolean retval = accessFunction.doTestBoolObjInt(tuple.first(), a1, tuple.second());
		releaseBoolIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		LBoolIntPair tuple = accessBoolIntPair();
		boolean retval = accessFunction.doTestBoolIntObj(tuple.first(), tuple.second(), a1);
		releaseBoolIntPair(tuple);
		return retval;
	}

}
