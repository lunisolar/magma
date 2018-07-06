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
public interface AccessObjIntLongTriple<T> {

	static <T> AccessObjIntLongTriple<T> wrap(AccessObjIntLongTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntLongTriple<T> accessObjIntLongTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntLongTriple(LObjIntLongTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieLongConsumer<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		accessFunction.doAcceptObjLongInt(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		accessFunction.doAcceptIntObjLong(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		accessFunction.doAcceptIntLongObj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		accessFunction.doAcceptLongObjInt(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		accessFunction.doAcceptLongIntObj(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjIntLongFunction<T, R> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		R retval = accessFunction.doApplyObjLongInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		R retval = accessFunction.doApplyIntObjLong(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		R retval = accessFunction.doApplyIntLongObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		R retval = accessFunction.doApplyLongObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		R retval = accessFunction.doApplyLongIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default int useWith(LTieLongFunction<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default int useWith(LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		int retval = accessFunction.doApplyAsIntObjLongInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default int useWith(LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		int retval = accessFunction.doApplyAsIntIntObjLong(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default int useWith(LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		int retval = accessFunction.doApplyAsIntIntLongObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default int useWith(LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		int retval = accessFunction.doApplyAsIntLongObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default int useWith(LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		int retval = accessFunction.doApplyAsIntLongIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntLongPredicate<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		boolean retval = accessFunction.doTestObjLongInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		boolean retval = accessFunction.doTestIntObjLong(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		boolean retval = accessFunction.doTestIntLongObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		boolean retval = accessFunction.doTestLongObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		LObjIntLongTriple<T> tuple = accessObjIntLongTriple();
		boolean retval = accessFunction.doTestLongIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntLongTriple(tuple);
		return retval;
	}

}
