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
public interface AccessObjCharPair<T> {

	static <T> AccessObjCharPair<T> wrap(AccessObjCharPair<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjCharPair<T> accessObjCharPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjCharPair(LObjCharPair<T> a) {
		// NOOP
	}

	default void useWith(LObjCharConsumer<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LObjCharConsumer.LCharObjCons<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		accessFunction.acceptCharObj(tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieCharConsumer<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		accessFunction.accept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		accessFunction.acceptObjCharInt(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		accessFunction.acceptIntObjChar(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		accessFunction.acceptIntCharObj(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		accessFunction.acceptCharObjInt(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		accessFunction.acceptCharIntObj(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(LObjCharFunction<T, R> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseObjCharPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjCharFunction.LCharObjFunc<T, R> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		R retval = accessFunction.applyCharObj(tuple.second(), tuple.first());
		releaseObjCharPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntCharFunction<T, R> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		R retval = accessFunction.apply(tuple.first(), a2, tuple.second());
		releaseObjCharPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		R retval = accessFunction.applyObjCharInt(tuple.first(), tuple.second(), a2);
		releaseObjCharPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		R retval = accessFunction.applyIntObjChar(a2, tuple.first(), tuple.second());
		releaseObjCharPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		R retval = accessFunction.applyIntCharObj(a2, tuple.second(), tuple.first());
		releaseObjCharPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		R retval = accessFunction.applyCharObjInt(tuple.second(), tuple.first(), a2);
		releaseObjCharPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		R retval = accessFunction.applyCharIntObj(tuple.second(), a2, tuple.first());
		releaseObjCharPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieCharFunction<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		int retval = accessFunction.applyAsInt(tuple.first(), a2, tuple.second());
		releaseObjCharPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		int retval = accessFunction.applyAsIntObjCharInt(tuple.first(), tuple.second(), a2);
		releaseObjCharPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		int retval = accessFunction.applyAsIntIntObjChar(a2, tuple.first(), tuple.second());
		releaseObjCharPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		int retval = accessFunction.applyAsIntIntCharObj(a2, tuple.second(), tuple.first());
		releaseObjCharPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		int retval = accessFunction.applyAsIntCharObjInt(tuple.second(), tuple.first(), a2);
		releaseObjCharPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		int retval = accessFunction.applyAsIntCharIntObj(tuple.second(), a2, tuple.first());
		releaseObjCharPair(tuple);
		return retval;
	}

	default boolean useWith(LObjCharPredicate<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseObjCharPair(tuple);
		return retval;
	}

	default boolean useWith(LObjCharPredicate.LCharObjPred<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		boolean retval = accessFunction.testCharObj(tuple.second(), tuple.first());
		releaseObjCharPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntCharPredicate<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		boolean retval = accessFunction.test(tuple.first(), a2, tuple.second());
		releaseObjCharPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		boolean retval = accessFunction.testObjCharInt(tuple.first(), tuple.second(), a2);
		releaseObjCharPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		boolean retval = accessFunction.testIntObjChar(a2, tuple.first(), tuple.second());
		releaseObjCharPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		boolean retval = accessFunction.testIntCharObj(a2, tuple.second(), tuple.first());
		releaseObjCharPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		boolean retval = accessFunction.testCharObjInt(tuple.second(), tuple.first(), a2);
		releaseObjCharPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		LObjCharPair<T> tuple = accessObjCharPair();
		boolean retval = accessFunction.testCharIntObj(tuple.second(), a2, tuple.first());
		releaseObjCharPair(tuple);
		return retval;
	}

}
