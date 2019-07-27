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
public interface AccessObjIntSrtTriple<T> {

	static <T> AccessObjIntSrtTriple<T> wrap(AccessObjIntSrtTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntSrtTriple<T> accessObjIntSrtTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntSrtTriple(LObjIntSrtTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieSrtConsumer<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		accessFunction.acceptObjSrtInt(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		accessFunction.acceptIntObjSrt(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		accessFunction.acceptIntSrtObj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		accessFunction.acceptSrtObjInt(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		accessFunction.acceptSrtIntObj(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjIntSrtFunction<T, R> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		R retval = accessFunction.applyObjSrtInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		R retval = accessFunction.applyIntObjSrt(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		R retval = accessFunction.applyIntSrtObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		R retval = accessFunction.applySrtObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		R retval = accessFunction.applySrtIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default int useWith(LTieSrtFunction<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default int useWith(LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		int retval = accessFunction.applyAsIntObjSrtInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default int useWith(LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		int retval = accessFunction.applyAsIntIntObjSrt(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default int useWith(LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		int retval = accessFunction.applyAsIntIntSrtObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default int useWith(LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		int retval = accessFunction.applyAsIntSrtObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default int useWith(LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		int retval = accessFunction.applyAsIntSrtIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntSrtPredicate<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		boolean retval = accessFunction.testObjSrtInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		boolean retval = accessFunction.testIntObjSrt(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		boolean retval = accessFunction.testIntSrtObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		boolean retval = accessFunction.testSrtObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		LObjIntSrtTriple<T> tuple = accessObjIntSrtTriple();
		boolean retval = accessFunction.testSrtIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntSrtTriple(tuple);
		return retval;
	}

}