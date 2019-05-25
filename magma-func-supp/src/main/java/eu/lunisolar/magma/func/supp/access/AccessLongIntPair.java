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
public interface AccessLongIntPair {

	static AccessLongIntPair wrap(AccessLongIntPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LLongIntPair accessLongIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseLongIntPair(LLongIntPair a) {
		// NOOP
	}

	default void useWith(LLongIntConsumer accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LLongIntConsumer.LIntLongCons accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		accessFunction.acceptIntLong(tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieLongConsumer<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		accessFunction.accept(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		accessFunction.acceptObjLongInt(a1, tuple.first(), tuple.second());
	}

	default <T> void useWith(T a1, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		accessFunction.acceptIntObjLong(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		accessFunction.acceptIntLongObj(tuple.second(), tuple.first(), a1);
	}

	default <T> void useWith(T a1, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		accessFunction.acceptLongObjInt(tuple.first(), a1, tuple.second());
	}

	default <T> void useWith(T a1, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		accessFunction.acceptLongIntObj(tuple.first(), tuple.second(), a1);
	}

	default <R, T> R useWith(T a1, LObjIntLongFunction<T, R> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		R retval = accessFunction.apply(a1, tuple.second(), tuple.first());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		R retval = accessFunction.applyObjLongInt(a1, tuple.first(), tuple.second());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		R retval = accessFunction.applyIntObjLong(tuple.second(), a1, tuple.first());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		R retval = accessFunction.applyIntLongObj(tuple.second(), tuple.first(), a1);
		releaseLongIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		R retval = accessFunction.applyLongObjInt(tuple.first(), a1, tuple.second());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		R retval = accessFunction.applyLongIntObj(tuple.first(), tuple.second(), a1);
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieLongFunction<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		int retval = accessFunction.applyAsInt(a1, tuple.second(), tuple.first());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		int retval = accessFunction.applyAsIntObjLongInt(a1, tuple.first(), tuple.second());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		int retval = accessFunction.applyAsIntIntObjLong(tuple.second(), a1, tuple.first());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		int retval = accessFunction.applyAsIntIntLongObj(tuple.second(), tuple.first(), a1);
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		int retval = accessFunction.applyAsIntLongObjInt(tuple.first(), a1, tuple.second());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		int retval = accessFunction.applyAsIntLongIntObj(tuple.first(), tuple.second(), a1);
		releaseLongIntPair(tuple);
		return retval;
	}

	default boolean useWith(LLongIntPredicate accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseLongIntPair(tuple);
		return retval;
	}

	default boolean useWith(LLongIntPredicate.LIntLongPred accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		boolean retval = accessFunction.testIntLong(tuple.second(), tuple.first());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntLongPredicate<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		boolean retval = accessFunction.test(a1, tuple.second(), tuple.first());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		boolean retval = accessFunction.testObjLongInt(a1, tuple.first(), tuple.second());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		boolean retval = accessFunction.testIntObjLong(tuple.second(), a1, tuple.first());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		boolean retval = accessFunction.testIntLongObj(tuple.second(), tuple.first(), a1);
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		boolean retval = accessFunction.testLongObjInt(tuple.first(), a1, tuple.second());
		releaseLongIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		LLongIntPair tuple = accessLongIntPair();
		boolean retval = accessFunction.testLongIntObj(tuple.first(), tuple.second(), a1);
		releaseLongIntPair(tuple);
		return retval;
	}

}
