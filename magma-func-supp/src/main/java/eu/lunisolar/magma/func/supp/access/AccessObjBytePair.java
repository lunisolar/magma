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
public interface AccessObjBytePair<T> {

	static <T> AccessObjBytePair<T> wrap(AccessObjBytePair<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjBytePair<T> accessObjBytePair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjBytePair(LObjBytePair<T> a) {
		// NOOP
	}

	default void useWith(LObjByteConsumer<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LObjByteConsumer.LByteObjCons<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		accessFunction.acceptByteObj(tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieByteConsumer<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		accessFunction.accept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		accessFunction.acceptObjByteInt(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		accessFunction.acceptIntObjByte(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		accessFunction.acceptIntByteObj(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		accessFunction.acceptByteObjInt(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		accessFunction.acceptByteIntObj(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(LObjByteFunction<T, R> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseObjBytePair(tuple);
		return retval;
	}

	default <R> R useWith(LObjByteFunction.LByteObjFunc<T, R> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		R retval = accessFunction.applyByteObj(tuple.second(), tuple.first());
		releaseObjBytePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntByteFunction<T, R> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		R retval = accessFunction.apply(tuple.first(), a2, tuple.second());
		releaseObjBytePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		R retval = accessFunction.applyObjByteInt(tuple.first(), tuple.second(), a2);
		releaseObjBytePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		R retval = accessFunction.applyIntObjByte(a2, tuple.first(), tuple.second());
		releaseObjBytePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		R retval = accessFunction.applyIntByteObj(a2, tuple.second(), tuple.first());
		releaseObjBytePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		R retval = accessFunction.applyByteObjInt(tuple.second(), tuple.first(), a2);
		releaseObjBytePair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		R retval = accessFunction.applyByteIntObj(tuple.second(), a2, tuple.first());
		releaseObjBytePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieByteFunction<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		int retval = accessFunction.applyAsInt(tuple.first(), a2, tuple.second());
		releaseObjBytePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		int retval = accessFunction.applyAsIntObjByteInt(tuple.first(), tuple.second(), a2);
		releaseObjBytePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		int retval = accessFunction.applyAsIntIntObjByte(a2, tuple.first(), tuple.second());
		releaseObjBytePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		int retval = accessFunction.applyAsIntIntByteObj(a2, tuple.second(), tuple.first());
		releaseObjBytePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		int retval = accessFunction.applyAsIntByteObjInt(tuple.second(), tuple.first(), a2);
		releaseObjBytePair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		int retval = accessFunction.applyAsIntByteIntObj(tuple.second(), a2, tuple.first());
		releaseObjBytePair(tuple);
		return retval;
	}

	default boolean useWith(LObjBytePredicate<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseObjBytePair(tuple);
		return retval;
	}

	default boolean useWith(LObjBytePredicate.LByteObjPred<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		boolean retval = accessFunction.testByteObj(tuple.second(), tuple.first());
		releaseObjBytePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBytePredicate<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		boolean retval = accessFunction.test(tuple.first(), a2, tuple.second());
		releaseObjBytePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		boolean retval = accessFunction.testObjByteInt(tuple.first(), tuple.second(), a2);
		releaseObjBytePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		boolean retval = accessFunction.testIntObjByte(a2, tuple.first(), tuple.second());
		releaseObjBytePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		boolean retval = accessFunction.testIntByteObj(a2, tuple.second(), tuple.first());
		releaseObjBytePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		boolean retval = accessFunction.testByteObjInt(tuple.second(), tuple.first(), a2);
		releaseObjBytePair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		LObjBytePair<T> tuple = accessObjBytePair();
		boolean retval = accessFunction.testByteIntObj(tuple.second(), a2, tuple.first());
		releaseObjBytePair(tuple);
		return retval;
	}

}
