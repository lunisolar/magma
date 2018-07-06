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
public interface AccessObjLongPair<T> {

	static <T> AccessObjLongPair<T> wrap(AccessObjLongPair<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjLongPair<T> accessObjLongPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjLongPair(LObjLongPair<T> a) {
		// NOOP
	}

	default void useWith(LObjLongConsumer<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LObjLongConsumer.LLongObjCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.doAcceptLongObj(tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieLongConsumer<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.doAccept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.doAcceptObjLongInt(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.doAcceptIntObjLong(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.doAcceptIntLongObj(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.doAcceptLongObjInt(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.doAcceptLongIntObj(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(int a2, LObjIntLongFunction<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.doApply(tuple.first(), a2, tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.doApplyObjLongInt(tuple.first(), tuple.second(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.doApplyIntObjLong(a2, tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.doApplyIntLongObj(a2, tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.doApplyLongObjInt(tuple.second(), tuple.first(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.doApplyLongIntObj(tuple.second(), a2, tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjLongFunction<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjLongFunction.LLongObjFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.doApplyLongObj(tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), a2, tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.doApplyAsIntObjLongInt(tuple.first(), tuple.second(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.doApplyAsIntIntObjLong(a2, tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.doApplyAsIntIntLongObj(a2, tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.doApplyAsIntLongObjInt(tuple.second(), tuple.first(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.doApplyAsIntLongIntObj(tuple.second(), a2, tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.doTest(tuple.first(), a2, tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.doTestObjLongInt(tuple.first(), tuple.second(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.doTestIntObjLong(a2, tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.doTestIntLongObj(a2, tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.doTestLongObjInt(tuple.second(), tuple.first(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.doTestLongIntObj(tuple.second(), a2, tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(LObjLongPredicate<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(LObjLongPredicate.LLongObjPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.doTestLongObj(tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

}
