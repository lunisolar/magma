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
public interface AccessObjIntBoolTriple<T> {

	static <T> AccessObjIntBoolTriple<T> wrap(AccessObjIntBoolTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntBoolTriple<T> accessObjIntBoolTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntBoolTriple(LObjIntBoolTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieBoolConsumer<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		accessFunction.acceptObjBoolInt(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		accessFunction.acceptIntObjBool(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		accessFunction.acceptIntBoolObj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		accessFunction.acceptBoolObjInt(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		accessFunction.acceptBoolIntObj(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjIntBoolFunction<T, R> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		R retval = accessFunction.applyObjBoolInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		R retval = accessFunction.applyIntObjBool(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		R retval = accessFunction.applyIntBoolObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		R retval = accessFunction.applyBoolObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		R retval = accessFunction.applyBoolIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default int useWith(LTieBoolFunction<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default int useWith(LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		int retval = accessFunction.applyAsIntObjBoolInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default int useWith(LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		int retval = accessFunction.applyAsIntIntObjBool(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default int useWith(LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		int retval = accessFunction.applyAsIntIntBoolObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default int useWith(LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		int retval = accessFunction.applyAsIntBoolObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default int useWith(LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		int retval = accessFunction.applyAsIntBoolIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBoolPredicate<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		boolean retval = accessFunction.testObjBoolInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		boolean retval = accessFunction.testIntObjBool(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		boolean retval = accessFunction.testIntBoolObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		boolean retval = accessFunction.testBoolObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		LObjIntBoolTriple<T> tuple = accessObjIntBoolTriple();
		boolean retval = accessFunction.testBoolIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntBoolTriple(tuple);
		return retval;
	}

}
