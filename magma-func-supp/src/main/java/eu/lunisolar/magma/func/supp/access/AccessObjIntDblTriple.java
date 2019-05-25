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
public interface AccessObjIntDblTriple<T> {

	static <T> AccessObjIntDblTriple<T> wrap(AccessObjIntDblTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntDblTriple<T> accessObjIntDblTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntDblTriple(LObjIntDblTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieDblConsumer<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		accessFunction.acceptObjDblInt(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		accessFunction.acceptIntObjDbl(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		accessFunction.acceptIntDblObj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		accessFunction.acceptDblObjInt(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		accessFunction.acceptDblIntObj(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjIntDblFunction<T, R> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		R retval = accessFunction.applyObjDblInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		R retval = accessFunction.applyIntObjDbl(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		R retval = accessFunction.applyIntDblObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		R retval = accessFunction.applyDblObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		R retval = accessFunction.applyDblIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default int useWith(LTieDblFunction<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default int useWith(LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		int retval = accessFunction.applyAsIntObjDblInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default int useWith(LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		int retval = accessFunction.applyAsIntIntObjDbl(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default int useWith(LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		int retval = accessFunction.applyAsIntIntDblObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default int useWith(LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		int retval = accessFunction.applyAsIntDblObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default int useWith(LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		int retval = accessFunction.applyAsIntDblIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntDblPredicate<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		boolean retval = accessFunction.testObjDblInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		boolean retval = accessFunction.testIntObjDbl(tuple.second(), tuple.first(), tuple.third());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		boolean retval = accessFunction.testIntDblObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		boolean retval = accessFunction.testDblObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		LObjIntDblTriple<T> tuple = accessObjIntDblTriple();
		boolean retval = accessFunction.testDblIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjIntDblTriple(tuple);
		return retval;
	}

}
