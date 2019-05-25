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
public interface AccessObjIntByteTriple<T> {

	static <T> AccessObjIntByteTriple<T> wrap(AccessObjIntByteTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntByteTriple<T> accessObjIntByteTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntByteTriple(LObjIntByteTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieByteConsumer<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		accessFunction.acceptObjByteInt(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		accessFunction.acceptIntObjByte(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		accessFunction.acceptIntByteObj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		accessFunction.acceptByteObjInt(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		accessFunction.acceptByteIntObj(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjIntByteFunction<T, R> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		R retval = accessFunction.applyObjByteInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		R retval = accessFunction.applyIntObjByte(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		R retval = accessFunction.applyIntByteObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		R retval = accessFunction.applyByteObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		R retval = accessFunction.applyByteIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default int useWith(LTieByteFunction<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default int useWith(LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		int retval = accessFunction.applyAsIntObjByteInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default int useWith(LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		int retval = accessFunction.applyAsIntIntObjByte(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default int useWith(LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		int retval = accessFunction.applyAsIntIntByteObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default int useWith(LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		int retval = accessFunction.applyAsIntByteObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default int useWith(LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		int retval = accessFunction.applyAsIntByteIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBytePredicate<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		boolean retval = accessFunction.testObjByteInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		boolean retval = accessFunction.testIntObjByte(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		boolean retval = accessFunction.testIntByteObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		boolean retval = accessFunction.testByteObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		LObjIntByteTriple<T> tuple = accessObjIntByteTriple();
		boolean retval = accessFunction.testByteIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntByteTriple(tuple);
		return retval;
	}

}
