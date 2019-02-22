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
public interface AccessObjSrtPair<T> {

	static <T> AccessObjSrtPair<T> wrap(AccessObjSrtPair<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjSrtPair<T> accessObjSrtPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjSrtPair(LObjSrtPair<T> a) {
		// NOOP
	}

	default void useWith(LObjSrtConsumer<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LObjSrtConsumer.LSrtObjCons<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		accessFunction.acceptSrtObj(tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieSrtConsumer<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		accessFunction.accept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		accessFunction.acceptObjSrtInt(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		accessFunction.acceptIntObjSrt(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		accessFunction.acceptIntSrtObj(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		accessFunction.acceptSrtObjInt(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		accessFunction.acceptSrtIntObj(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(int a2, LObjIntSrtFunction<T, R> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		R retval = accessFunction.apply(tuple.first(), a2, tuple.second());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		R retval = accessFunction.applyObjSrtInt(tuple.first(), tuple.second(), a2);
		releaseObjSrtPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		R retval = accessFunction.applyIntObjSrt(a2, tuple.first(), tuple.second());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		R retval = accessFunction.applyIntSrtObj(a2, tuple.second(), tuple.first());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		R retval = accessFunction.applySrtObjInt(tuple.second(), tuple.first(), a2);
		releaseObjSrtPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		R retval = accessFunction.applySrtIntObj(tuple.second(), a2, tuple.first());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjSrtFunction<T, R> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjSrtFunction.LSrtObjFunc<T, R> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		R retval = accessFunction.applySrtObj(tuple.second(), tuple.first());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieSrtFunction<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		int retval = accessFunction.applyAsInt(tuple.first(), a2, tuple.second());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		int retval = accessFunction.applyAsIntObjSrtInt(tuple.first(), tuple.second(), a2);
		releaseObjSrtPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		int retval = accessFunction.applyAsIntIntObjSrt(a2, tuple.first(), tuple.second());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		int retval = accessFunction.applyAsIntIntSrtObj(a2, tuple.second(), tuple.first());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		int retval = accessFunction.applyAsIntSrtObjInt(tuple.second(), tuple.first(), a2);
		releaseObjSrtPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		int retval = accessFunction.applyAsIntSrtIntObj(tuple.second(), a2, tuple.first());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntSrtPredicate<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		boolean retval = accessFunction.test(tuple.first(), a2, tuple.second());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		boolean retval = accessFunction.testObjSrtInt(tuple.first(), tuple.second(), a2);
		releaseObjSrtPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		boolean retval = accessFunction.testIntObjSrt(a2, tuple.first(), tuple.second());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		boolean retval = accessFunction.testIntSrtObj(a2, tuple.second(), tuple.first());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		boolean retval = accessFunction.testSrtObjInt(tuple.second(), tuple.first(), a2);
		releaseObjSrtPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		boolean retval = accessFunction.testSrtIntObj(tuple.second(), a2, tuple.first());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default boolean useWith(LObjSrtPredicate<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseObjSrtPair(tuple);
		return retval;
	}

	default boolean useWith(LObjSrtPredicate.LSrtObjPred<T> accessFunction) {
		LObjSrtPair<T> tuple = accessObjSrtPair();
		boolean retval = accessFunction.testSrtObj(tuple.second(), tuple.first());
		releaseObjSrtPair(tuple);
		return retval;
	}

}
