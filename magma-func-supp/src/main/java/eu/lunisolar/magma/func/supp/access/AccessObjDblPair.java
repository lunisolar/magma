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
public interface AccessObjDblPair<T> {

	static <T> AccessObjDblPair<T> wrap(AccessObjDblPair<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjDblPair<T> accessObjDblPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjDblPair(LObjDblPair<T> a) {
		// NOOP
	}

	default void useWith(LObjDblConsumer<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LObjDblConsumer.LDblObjCons<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		accessFunction.doAcceptDblObj(tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieDblConsumer<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		accessFunction.doAccept(tuple.first(), a2, tuple.second());
	}

	default void useWith(int a2, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		accessFunction.doAcceptObjDblInt(tuple.first(), tuple.second(), a2);
	}

	default void useWith(int a2, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		accessFunction.doAcceptIntObjDbl(a2, tuple.first(), tuple.second());
	}

	default void useWith(int a2, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		accessFunction.doAcceptIntDblObj(a2, tuple.second(), tuple.first());
	}

	default void useWith(int a2, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		accessFunction.doAcceptDblObjInt(tuple.second(), tuple.first(), a2);
	}

	default void useWith(int a2, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		accessFunction.doAcceptDblIntObj(tuple.second(), a2, tuple.first());
	}

	default <R> R useWith(LObjDblFunction<T, R> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releaseObjDblPair(tuple);
		return retval;
	}

	default <R> R useWith(LObjDblFunction.LDblObjFunc<T, R> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		R retval = accessFunction.doApplyDblObj(tuple.second(), tuple.first());
		releaseObjDblPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntDblFunction<T, R> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		R retval = accessFunction.doApply(tuple.first(), a2, tuple.second());
		releaseObjDblPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		R retval = accessFunction.doApplyObjDblInt(tuple.first(), tuple.second(), a2);
		releaseObjDblPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		R retval = accessFunction.doApplyIntObjDbl(a2, tuple.first(), tuple.second());
		releaseObjDblPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		R retval = accessFunction.doApplyIntDblObj(a2, tuple.second(), tuple.first());
		releaseObjDblPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		R retval = accessFunction.doApplyDblObjInt(tuple.second(), tuple.first(), a2);
		releaseObjDblPair(tuple);
		return retval;
	}

	default <R> R useWith(int a2, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		R retval = accessFunction.doApplyDblIntObj(tuple.second(), a2, tuple.first());
		releaseObjDblPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieDblFunction<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), a2, tuple.second());
		releaseObjDblPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		int retval = accessFunction.doApplyAsIntObjDblInt(tuple.first(), tuple.second(), a2);
		releaseObjDblPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		int retval = accessFunction.doApplyAsIntIntObjDbl(a2, tuple.first(), tuple.second());
		releaseObjDblPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		int retval = accessFunction.doApplyAsIntIntDblObj(a2, tuple.second(), tuple.first());
		releaseObjDblPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		int retval = accessFunction.doApplyAsIntDblObjInt(tuple.second(), tuple.first(), a2);
		releaseObjDblPair(tuple);
		return retval;
	}

	default int useWith(int a2, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		int retval = accessFunction.doApplyAsIntDblIntObj(tuple.second(), a2, tuple.first());
		releaseObjDblPair(tuple);
		return retval;
	}

	default boolean useWith(LObjDblPredicate<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseObjDblPair(tuple);
		return retval;
	}

	default boolean useWith(LObjDblPredicate.LDblObjPred<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		boolean retval = accessFunction.doTestDblObj(tuple.second(), tuple.first());
		releaseObjDblPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntDblPredicate<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		boolean retval = accessFunction.doTest(tuple.first(), a2, tuple.second());
		releaseObjDblPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		boolean retval = accessFunction.doTestObjDblInt(tuple.first(), tuple.second(), a2);
		releaseObjDblPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		boolean retval = accessFunction.doTestIntObjDbl(a2, tuple.first(), tuple.second());
		releaseObjDblPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		boolean retval = accessFunction.doTestIntDblObj(a2, tuple.second(), tuple.first());
		releaseObjDblPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		boolean retval = accessFunction.doTestDblObjInt(tuple.second(), tuple.first(), a2);
		releaseObjDblPair(tuple);
		return retval;
	}

	default boolean useWith(int a2, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		LObjDblPair<T> tuple = accessObjDblPair();
		boolean retval = accessFunction.doTestDblIntObj(tuple.second(), a2, tuple.first());
		releaseObjDblPair(tuple);
		return retval;
	}

}
