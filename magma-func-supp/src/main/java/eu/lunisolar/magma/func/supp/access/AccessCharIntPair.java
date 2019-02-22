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
public interface AccessCharIntPair {

	static AccessCharIntPair wrap(AccessCharIntPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LCharIntPair accessCharIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseCharIntPair(LCharIntPair a) {
		// NOOP
	}

	default void useWith(LCharIntConsumer accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LCharIntConsumer.LIntCharCons accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		accessFunction.acceptIntChar(tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieCharConsumer<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		accessFunction.accept(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		accessFunction.acceptObjCharInt(a1, tuple.first(), tuple.second());
	}

	default <T> void useWith(T a1, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		accessFunction.acceptIntObjChar(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		accessFunction.acceptIntCharObj(tuple.second(), tuple.first(), a1);
	}

	default <T> void useWith(T a1, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		accessFunction.acceptCharObjInt(tuple.first(), a1, tuple.second());
	}

	default <T> void useWith(T a1, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		accessFunction.acceptCharIntObj(tuple.first(), tuple.second(), a1);
	}

	default <R, T> R useWith(T a1, LObjIntCharFunction<T, R> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		R retval = accessFunction.apply(a1, tuple.second(), tuple.first());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		R retval = accessFunction.applyObjCharInt(a1, tuple.first(), tuple.second());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		R retval = accessFunction.applyIntObjChar(tuple.second(), a1, tuple.first());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		R retval = accessFunction.applyIntCharObj(tuple.second(), tuple.first(), a1);
		releaseCharIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		R retval = accessFunction.applyCharObjInt(tuple.first(), a1, tuple.second());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		R retval = accessFunction.applyCharIntObj(tuple.first(), tuple.second(), a1);
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieCharFunction<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		int retval = accessFunction.applyAsInt(a1, tuple.second(), tuple.first());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		int retval = accessFunction.applyAsIntObjCharInt(a1, tuple.first(), tuple.second());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		int retval = accessFunction.applyAsIntIntObjChar(tuple.second(), a1, tuple.first());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		int retval = accessFunction.applyAsIntIntCharObj(tuple.second(), tuple.first(), a1);
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		int retval = accessFunction.applyAsIntCharObjInt(tuple.first(), a1, tuple.second());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		int retval = accessFunction.applyAsIntCharIntObj(tuple.first(), tuple.second(), a1);
		releaseCharIntPair(tuple);
		return retval;
	}

	default boolean useWith(LCharIntPredicate accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseCharIntPair(tuple);
		return retval;
	}

	default boolean useWith(LCharIntPredicate.LIntCharPred accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		boolean retval = accessFunction.testIntChar(tuple.second(), tuple.first());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntCharPredicate<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		boolean retval = accessFunction.test(a1, tuple.second(), tuple.first());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		boolean retval = accessFunction.testObjCharInt(a1, tuple.first(), tuple.second());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		boolean retval = accessFunction.testIntObjChar(tuple.second(), a1, tuple.first());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		boolean retval = accessFunction.testIntCharObj(tuple.second(), tuple.first(), a1);
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		boolean retval = accessFunction.testCharObjInt(tuple.first(), a1, tuple.second());
		releaseCharIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		LCharIntPair tuple = accessCharIntPair();
		boolean retval = accessFunction.testCharIntObj(tuple.first(), tuple.second(), a1);
		releaseCharIntPair(tuple);
		return retval;
	}

}
