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
public interface AccessByteIntPair {

	static AccessByteIntPair wrap(AccessByteIntPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LByteIntPair accessByteIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseByteIntPair(LByteIntPair a) {
		// NOOP
	}

	default void useWith(LByteIntConsumer accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LByteIntConsumer.LIntByteCons accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		accessFunction.acceptIntByte(tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieByteConsumer<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		accessFunction.accept(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		accessFunction.acceptObjByteInt(a1, tuple.first(), tuple.second());
	}

	default <T> void useWith(T a1, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		accessFunction.acceptIntObjByte(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		accessFunction.acceptIntByteObj(tuple.second(), tuple.first(), a1);
	}

	default <T> void useWith(T a1, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		accessFunction.acceptByteObjInt(tuple.first(), a1, tuple.second());
	}

	default <T> void useWith(T a1, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		accessFunction.acceptByteIntObj(tuple.first(), tuple.second(), a1);
	}

	default <R, T> R useWith(T a1, LObjIntByteFunction<T, R> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		R retval = accessFunction.apply(a1, tuple.second(), tuple.first());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		R retval = accessFunction.applyObjByteInt(a1, tuple.first(), tuple.second());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		R retval = accessFunction.applyIntObjByte(tuple.second(), a1, tuple.first());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		R retval = accessFunction.applyIntByteObj(tuple.second(), tuple.first(), a1);
		releaseByteIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		R retval = accessFunction.applyByteObjInt(tuple.first(), a1, tuple.second());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		R retval = accessFunction.applyByteIntObj(tuple.first(), tuple.second(), a1);
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieByteFunction<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		int retval = accessFunction.applyAsInt(a1, tuple.second(), tuple.first());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		int retval = accessFunction.applyAsIntObjByteInt(a1, tuple.first(), tuple.second());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		int retval = accessFunction.applyAsIntIntObjByte(tuple.second(), a1, tuple.first());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		int retval = accessFunction.applyAsIntIntByteObj(tuple.second(), tuple.first(), a1);
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		int retval = accessFunction.applyAsIntByteObjInt(tuple.first(), a1, tuple.second());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		int retval = accessFunction.applyAsIntByteIntObj(tuple.first(), tuple.second(), a1);
		releaseByteIntPair(tuple);
		return retval;
	}

	default boolean useWith(LByteIntPredicate accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseByteIntPair(tuple);
		return retval;
	}

	default boolean useWith(LByteIntPredicate.LIntBytePred accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		boolean retval = accessFunction.testIntByte(tuple.second(), tuple.first());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBytePredicate<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		boolean retval = accessFunction.test(a1, tuple.second(), tuple.first());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		boolean retval = accessFunction.testObjByteInt(a1, tuple.first(), tuple.second());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		boolean retval = accessFunction.testIntObjByte(tuple.second(), a1, tuple.first());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		boolean retval = accessFunction.testIntByteObj(tuple.second(), tuple.first(), a1);
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		boolean retval = accessFunction.testByteObjInt(tuple.first(), a1, tuple.second());
		releaseByteIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		LByteIntPair tuple = accessByteIntPair();
		boolean retval = accessFunction.testByteIntObj(tuple.first(), tuple.second(), a1);
		releaseByteIntPair(tuple);
		return retval;
	}

}
