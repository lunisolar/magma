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
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LObjLongConsumer.LLongObjCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.acceptLongObj(tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieLongConsumer<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.accept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.acceptObjLongInt(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.acceptIntObjLong(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.acceptIntLongObj(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.acceptLongObjInt(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		accessFunction.acceptLongIntObj(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(int a2, LObjIntLongFunction<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.apply(tuple.first(), a2, tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.applyObjLongInt(tuple.first(), tuple.second(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.applyIntObjLong(a2, tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.applyIntLongObj(a2, tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.applyLongObjInt(tuple.second(), tuple.first(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.applyLongIntObj(tuple.second(), a2, tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjLongFunction<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjLongFunction.LLongObjFunc<T, R> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		R retval = accessFunction.applyLongObj(tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.applyAsInt(tuple.first(), a2, tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.applyAsIntObjLongInt(tuple.first(), tuple.second(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.applyAsIntIntObjLong(a2, tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.applyAsIntIntLongObj(a2, tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.applyAsIntLongObjInt(tuple.second(), tuple.first(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		int retval = accessFunction.applyAsIntLongIntObj(tuple.second(), a2, tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.test(tuple.first(), a2, tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.testObjLongInt(tuple.first(), tuple.second(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.testIntObjLong(a2, tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.testIntLongObj(a2, tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.testLongObjInt(tuple.second(), tuple.first(), a2);
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.testLongIntObj(tuple.second(), a2, tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(LObjLongPredicate<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseObjLongPair(tuple);
		return retval;
	}

	default boolean useWith(LObjLongPredicate.LLongObjPred<T> accessFunction) {
		LObjLongPair<T> tuple = accessObjLongPair();
		boolean retval = accessFunction.testLongObj(tuple.second(), tuple.first());
		releaseObjLongPair(tuple);
		return retval;
	}

}
