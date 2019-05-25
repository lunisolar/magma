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
public interface AccessObjIntFltTriple<T> {

	static <T> AccessObjIntFltTriple<T> wrap(AccessObjIntFltTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntFltTriple<T> accessObjIntFltTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntFltTriple(LObjIntFltTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieFltConsumer<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		accessFunction.acceptObjFltInt(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		accessFunction.acceptIntObjFlt(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		accessFunction.acceptIntFltObj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		accessFunction.acceptFltObjInt(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		accessFunction.acceptFltIntObj(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjIntFltFunction<T, R> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		R retval = accessFunction.applyObjFltInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		R retval = accessFunction.applyIntObjFlt(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		R retval = accessFunction.applyIntFltObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		R retval = accessFunction.applyFltObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		R retval = accessFunction.applyFltIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default int useWith(LTieFltFunction<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default int useWith(LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		int retval = accessFunction.applyAsIntObjFltInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default int useWith(LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		int retval = accessFunction.applyAsIntIntObjFlt(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default int useWith(LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		int retval = accessFunction.applyAsIntIntFltObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default int useWith(LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		int retval = accessFunction.applyAsIntFltObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default int useWith(LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		int retval = accessFunction.applyAsIntFltIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntFltPredicate<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		boolean retval = accessFunction.testObjFltInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		boolean retval = accessFunction.testIntObjFlt(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		boolean retval = accessFunction.testIntFltObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		boolean retval = accessFunction.testFltObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		LObjIntFltTriple<T> tuple = accessObjIntFltTriple();
		boolean retval = accessFunction.testFltIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntFltTriple(tuple);
		return retval;
	}

}
