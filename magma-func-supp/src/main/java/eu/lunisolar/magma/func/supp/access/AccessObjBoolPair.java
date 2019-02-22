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
public interface AccessObjBoolPair<T> {

	static <T> AccessObjBoolPair<T> wrap(AccessObjBoolPair<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjBoolPair<T> accessObjBoolPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjBoolPair(LObjBoolPair<T> a) {
		// NOOP
	}

	default void useWith(LObjBoolConsumer<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LObjBoolConsumer.LBoolObjCons<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		accessFunction.acceptBoolObj(tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieBoolConsumer<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		accessFunction.accept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		accessFunction.acceptObjBoolInt(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		accessFunction.acceptIntObjBool(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		accessFunction.acceptIntBoolObj(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		accessFunction.acceptBoolObjInt(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		accessFunction.acceptBoolIntObj(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(LObjBoolFunction<T, R> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjBoolFunction.LBoolObjFunc<T, R> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		R retval = accessFunction.applyBoolObj(tuple.second(), tuple.first());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntBoolFunction<T, R> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		R retval = accessFunction.apply(tuple.first(), a2, tuple.second());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		R retval = accessFunction.applyObjBoolInt(tuple.first(), tuple.second(), a2);
		releaseObjBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		R retval = accessFunction.applyIntObjBool(a2, tuple.first(), tuple.second());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		R retval = accessFunction.applyIntBoolObj(a2, tuple.second(), tuple.first());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		R retval = accessFunction.applyBoolObjInt(tuple.second(), tuple.first(), a2);
		releaseObjBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		R retval = accessFunction.applyBoolIntObj(tuple.second(), a2, tuple.first());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieBoolFunction<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		int retval = accessFunction.applyAsInt(tuple.first(), a2, tuple.second());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		int retval = accessFunction.applyAsIntObjBoolInt(tuple.first(), tuple.second(), a2);
		releaseObjBoolPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		int retval = accessFunction.applyAsIntIntObjBool(a2, tuple.first(), tuple.second());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		int retval = accessFunction.applyAsIntIntBoolObj(a2, tuple.second(), tuple.first());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		int retval = accessFunction.applyAsIntBoolObjInt(tuple.second(), tuple.first(), a2);
		releaseObjBoolPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		int retval = accessFunction.applyAsIntBoolIntObj(tuple.second(), a2, tuple.first());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default boolean useWith(LObjBoolPredicate<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default boolean useWith(LObjBoolPredicate.LBoolObjPred<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		boolean retval = accessFunction.testBoolObj(tuple.second(), tuple.first());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBoolPredicate<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		boolean retval = accessFunction.test(tuple.first(), a2, tuple.second());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		boolean retval = accessFunction.testObjBoolInt(tuple.first(), tuple.second(), a2);
		releaseObjBoolPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		boolean retval = accessFunction.testIntObjBool(a2, tuple.first(), tuple.second());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		boolean retval = accessFunction.testIntBoolObj(a2, tuple.second(), tuple.first());
		releaseObjBoolPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		boolean retval = accessFunction.testBoolObjInt(tuple.second(), tuple.first(), a2);
		releaseObjBoolPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		LObjBoolPair<T> tuple = accessObjBoolPair();
		boolean retval = accessFunction.testBoolIntObj(tuple.second(), a2, tuple.first());
		releaseObjBoolPair(tuple);
		return retval;
	}

}
