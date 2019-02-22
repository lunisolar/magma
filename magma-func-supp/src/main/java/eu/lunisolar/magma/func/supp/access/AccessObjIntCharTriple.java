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
public interface AccessObjIntCharTriple<T> {

	static <T> AccessObjIntCharTriple<T> wrap(AccessObjIntCharTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntCharTriple<T> accessObjIntCharTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntCharTriple(LObjIntCharTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieCharConsumer<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieCharConsumer.LObjCharIntCons<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		accessFunction.acceptObjCharInt(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieCharConsumer.LIntObjCharCons<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		accessFunction.acceptIntObjChar(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieCharConsumer.LIntCharObjCons<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		accessFunction.acceptIntCharObj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTieCharConsumer.LCharObjIntCons<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		accessFunction.acceptCharObjInt(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieCharConsumer.LCharIntObjCons<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		accessFunction.acceptCharIntObj(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjIntCharFunction<T, R> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntCharFunction.LObjCharIntFunc<T, R> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		R retval = accessFunction.applyObjCharInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntCharFunction.LIntObjCharFunc<T, R> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		R retval = accessFunction.applyIntObjChar(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntCharFunction.LIntCharObjFunc<T, R> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		R retval = accessFunction.applyIntCharObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntCharFunction.LCharObjIntFunc<T, R> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		R retval = accessFunction.applyCharObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntCharFunction.LCharIntObjFunc<T, R> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		R retval = accessFunction.applyCharIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default int useWith(LTieCharFunction<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default int useWith(LTieCharFunction.LObjCharIntToIntFunc<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		int retval = accessFunction.applyAsIntObjCharInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default int useWith(LTieCharFunction.LIntObjCharToIntFunc<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		int retval = accessFunction.applyAsIntIntObjChar(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default int useWith(LTieCharFunction.LIntCharObjToIntFunc<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		int retval = accessFunction.applyAsIntIntCharObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default int useWith(LTieCharFunction.LCharObjIntToIntFunc<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		int retval = accessFunction.applyAsIntCharObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default int useWith(LTieCharFunction.LCharIntObjToIntFunc<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		int retval = accessFunction.applyAsIntCharIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntCharPredicate<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntCharPredicate.LObjCharIntPred<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		boolean retval = accessFunction.testObjCharInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntCharPredicate.LIntObjCharPred<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		boolean retval = accessFunction.testIntObjChar(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntCharPredicate.LIntCharObjPred<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		boolean retval = accessFunction.testIntCharObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntCharPredicate.LCharObjIntPred<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		boolean retval = accessFunction.testCharObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntCharPredicate.LCharIntObjPred<T> accessFunction) {
		LObjIntCharTriple<T> tuple = accessObjIntCharTriple();
		boolean retval = accessFunction.testCharIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntCharTriple(tuple);
		return retval;
	}

}
