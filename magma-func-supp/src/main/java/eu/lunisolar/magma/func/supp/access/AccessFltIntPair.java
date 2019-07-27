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
public interface AccessFltIntPair {

	static AccessFltIntPair wrap(AccessFltIntPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LFltIntPair accessFltIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseFltIntPair(LFltIntPair a) {
		// NOOP
	}

	default void useWith(LFltIntConsumer accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LFltIntConsumer.LIntFltCons accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		accessFunction.acceptIntFlt(tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieFltConsumer<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		accessFunction.accept(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		accessFunction.acceptObjFltInt(a1, tuple.first(), tuple.second());
	}

	default <T> void useWith(T a1, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		accessFunction.acceptIntObjFlt(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		accessFunction.acceptIntFltObj(tuple.second(), tuple.first(), a1);
	}

	default <T> void useWith(T a1, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		accessFunction.acceptFltObjInt(tuple.first(), a1, tuple.second());
	}

	default <T> void useWith(T a1, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		accessFunction.acceptFltIntObj(tuple.first(), tuple.second(), a1);
	}

	default <R, T> R useWith(T a1, LObjIntFltFunction<T, R> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		R retval = accessFunction.apply(a1, tuple.second(), tuple.first());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		R retval = accessFunction.applyObjFltInt(a1, tuple.first(), tuple.second());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		R retval = accessFunction.applyIntObjFlt(tuple.second(), a1, tuple.first());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		R retval = accessFunction.applyIntFltObj(tuple.second(), tuple.first(), a1);
		releaseFltIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		R retval = accessFunction.applyFltObjInt(tuple.first(), a1, tuple.second());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		R retval = accessFunction.applyFltIntObj(tuple.first(), tuple.second(), a1);
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieFltFunction<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		int retval = accessFunction.applyAsInt(a1, tuple.second(), tuple.first());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		int retval = accessFunction.applyAsIntObjFltInt(a1, tuple.first(), tuple.second());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		int retval = accessFunction.applyAsIntIntObjFlt(tuple.second(), a1, tuple.first());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		int retval = accessFunction.applyAsIntIntFltObj(tuple.second(), tuple.first(), a1);
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		int retval = accessFunction.applyAsIntFltObjInt(tuple.first(), a1, tuple.second());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		int retval = accessFunction.applyAsIntFltIntObj(tuple.first(), tuple.second(), a1);
		releaseFltIntPair(tuple);
		return retval;
	}

	default boolean useWith(LFltIntPredicate accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseFltIntPair(tuple);
		return retval;
	}

	default boolean useWith(LFltIntPredicate.LIntFltPred accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		boolean retval = accessFunction.testIntFlt(tuple.second(), tuple.first());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntFltPredicate<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		boolean retval = accessFunction.test(a1, tuple.second(), tuple.first());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		boolean retval = accessFunction.testObjFltInt(a1, tuple.first(), tuple.second());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		boolean retval = accessFunction.testIntObjFlt(tuple.second(), a1, tuple.first());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		boolean retval = accessFunction.testIntFltObj(tuple.second(), tuple.first(), a1);
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		boolean retval = accessFunction.testFltObjInt(tuple.first(), a1, tuple.second());
		releaseFltIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		LFltIntPair tuple = accessFltIntPair();
		boolean retval = accessFunction.testFltIntObj(tuple.first(), tuple.second(), a1);
		releaseFltIntPair(tuple);
		return retval;
	}

}