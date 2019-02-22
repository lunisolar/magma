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
public interface AccessDblIntPair {

	static AccessDblIntPair wrap(AccessDblIntPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LDblIntPair accessDblIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseDblIntPair(LDblIntPair a) {
		// NOOP
	}

	default void useWith(LDblIntConsumer accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWith(LDblIntConsumer.LIntDblCons accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		accessFunction.acceptIntDbl(tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieDblConsumer<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		accessFunction.accept(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		accessFunction.acceptObjDblInt(a1, tuple.first(), tuple.second());
	}

	default <T> void useWith(T a1, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		accessFunction.acceptIntObjDbl(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		accessFunction.acceptIntDblObj(tuple.second(), tuple.first(), a1);
	}

	default <T> void useWith(T a1, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		accessFunction.acceptDblObjInt(tuple.first(), a1, tuple.second());
	}

	default <T> void useWith(T a1, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		accessFunction.acceptDblIntObj(tuple.first(), tuple.second(), a1);
	}

	default <R, T> R useWith(T a1, LObjIntDblFunction<T, R> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		R retval = accessFunction.apply(a1, tuple.second(), tuple.first());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		R retval = accessFunction.applyObjDblInt(a1, tuple.first(), tuple.second());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		R retval = accessFunction.applyIntObjDbl(tuple.second(), a1, tuple.first());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		R retval = accessFunction.applyIntDblObj(tuple.second(), tuple.first(), a1);
		releaseDblIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		R retval = accessFunction.applyDblObjInt(tuple.first(), a1, tuple.second());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		R retval = accessFunction.applyDblIntObj(tuple.first(), tuple.second(), a1);
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieDblFunction<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		int retval = accessFunction.applyAsInt(a1, tuple.second(), tuple.first());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		int retval = accessFunction.applyAsIntObjDblInt(a1, tuple.first(), tuple.second());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		int retval = accessFunction.applyAsIntIntObjDbl(tuple.second(), a1, tuple.first());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		int retval = accessFunction.applyAsIntIntDblObj(tuple.second(), tuple.first(), a1);
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		int retval = accessFunction.applyAsIntDblObjInt(tuple.first(), a1, tuple.second());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		int retval = accessFunction.applyAsIntDblIntObj(tuple.first(), tuple.second(), a1);
		releaseDblIntPair(tuple);
		return retval;
	}

	default boolean useWith(LDblIntPredicate accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseDblIntPair(tuple);
		return retval;
	}

	default boolean useWith(LDblIntPredicate.LIntDblPred accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		boolean retval = accessFunction.testIntDbl(tuple.second(), tuple.first());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntDblPredicate<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		boolean retval = accessFunction.test(a1, tuple.second(), tuple.first());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		boolean retval = accessFunction.testObjDblInt(a1, tuple.first(), tuple.second());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		boolean retval = accessFunction.testIntObjDbl(tuple.second(), a1, tuple.first());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		boolean retval = accessFunction.testIntDblObj(tuple.second(), tuple.first(), a1);
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		boolean retval = accessFunction.testDblObjInt(tuple.first(), a1, tuple.second());
		releaseDblIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		LDblIntPair tuple = accessDblIntPair();
		boolean retval = accessFunction.testDblIntObj(tuple.first(), tuple.second(), a1);
		releaseDblIntPair(tuple);
		return retval;
	}

}
