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
public interface AccessIntPair {

	static AccessIntPair wrap(AccessIntPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LIntPair accessIntPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseIntPair(LIntPair a) {
		// NOOP
	}

	default void useWith(LBiIntConsumer accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiIntConsumer accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAccept(tuple.second(), tuple.first());
	}

	default void useWith(LBiIntConsumer.LInt1Int0Cons accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptInt1Int0(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiIntConsumer.LInt1Int0Cons accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptInt1Int0(tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieIntConsumer<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAccept(a1, tuple.first(), tuple.second());
	}

	default <T> void useWithO1(T a1, LTieIntConsumer<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAccept(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieIntConsumer.LObjInt2Int1Cons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptObjInt2Int1(a1, tuple.first(), tuple.second());
	}

	default <T> void useWithO1(T a1, LTieIntConsumer.LObjInt2Int1Cons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptObjInt2Int1(a1, tuple.second(), tuple.first());
	}

	default <T> void useWith(T a1, LTieIntConsumer.LInt1ObjIntCons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptInt1ObjInt(tuple.first(), a1, tuple.second());
	}

	default <T> void useWithO1(T a1, LTieIntConsumer.LInt1ObjIntCons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptInt1ObjInt(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieIntConsumer.LInt1Int2ObjCons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptInt1Int2Obj(tuple.first(), tuple.second(), a1);
	}

	default <T> void useWithO1(T a1, LTieIntConsumer.LInt1Int2ObjCons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptInt1Int2Obj(tuple.second(), tuple.first(), a1);
	}

	default <T> void useWith(T a1, LTieIntConsumer.LInt2ObjInt1Cons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptInt2ObjInt1(tuple.first(), a1, tuple.second());
	}

	default <T> void useWithO1(T a1, LTieIntConsumer.LInt2ObjInt1Cons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptInt2ObjInt1(tuple.second(), a1, tuple.first());
	}

	default <T> void useWith(T a1, LTieIntConsumer.LBiIntObjCons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptBiIntObj(tuple.first(), tuple.second(), a1);
	}

	default <T> void useWithO1(T a1, LTieIntConsumer.LBiIntObjCons<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		accessFunction.doAcceptBiIntObj(tuple.second(), tuple.first(), a1);
	}

	default int useWith(LIntBinaryOperator accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default int useWithO1(LIntBinaryOperator accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsInt(tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <R> R useWith(LBiIntFunction<R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiIntFunction<R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApply(tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <R> R useWith(LBiIntFunction.LInt1Int0Func<R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyInt1Int0(tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiIntFunction.LInt1Int0Func<R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyInt1Int0(tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBiIntFunction<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApply(a1, tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWithO1(T a1, LObjBiIntFunction<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApply(a1, tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBiIntFunction.LObjInt2Int1Func<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyObjInt2Int1(a1, tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWithO1(T a1, LObjBiIntFunction.LObjInt2Int1Func<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyObjInt2Int1(a1, tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBiIntFunction.LInt1ObjIntFunc<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyInt1ObjInt(tuple.first(), a1, tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWithO1(T a1, LObjBiIntFunction.LInt1ObjIntFunc<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyInt1ObjInt(tuple.second(), a1, tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBiIntFunction.LInt1Int2ObjFunc<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyInt1Int2Obj(tuple.first(), tuple.second(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWithO1(T a1, LObjBiIntFunction.LInt1Int2ObjFunc<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyInt1Int2Obj(tuple.second(), tuple.first(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBiIntFunction.LInt2ObjInt1Func<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyInt2ObjInt1(tuple.first(), a1, tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWithO1(T a1, LObjBiIntFunction.LInt2ObjInt1Func<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyInt2ObjInt1(tuple.second(), a1, tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBiIntFunction.LBiIntObjFunc<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyBiIntObj(tuple.first(), tuple.second(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <R, T> R useWithO1(T a1, LObjBiIntFunction.LBiIntObjFunc<T, R> accessFunction) {
		LIntPair tuple = accessIntPair();
		R retval = accessFunction.doApplyBiIntObj(tuple.second(), tuple.first(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieIntFunction<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsInt(a1, tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWithO1(T a1, LTieIntFunction<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsInt(a1, tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieIntFunction.LObjInt2Int1ToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntObjInt2Int1(a1, tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWithO1(T a1, LTieIntFunction.LObjInt2Int1ToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntObjInt2Int1(a1, tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieIntFunction.LInt1ObjIntToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntInt1ObjInt(tuple.first(), a1, tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWithO1(T a1, LTieIntFunction.LInt1ObjIntToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntInt1ObjInt(tuple.second(), a1, tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieIntFunction.LInt1Int2ObjToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntInt1Int2Obj(tuple.first(), tuple.second(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWithO1(T a1, LTieIntFunction.LInt1Int2ObjToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntInt1Int2Obj(tuple.second(), tuple.first(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieIntFunction.LInt2ObjInt1ToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntInt2ObjInt1(tuple.first(), a1, tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWithO1(T a1, LTieIntFunction.LInt2ObjInt1ToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntInt2ObjInt1(tuple.second(), a1, tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWith(T a1, LTieIntFunction.LBiIntObjToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntBiIntObj(tuple.first(), tuple.second(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <T> int useWithO1(T a1, LTieIntFunction.LBiIntObjToIntFunc<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		int retval = accessFunction.doApplyAsIntBiIntObj(tuple.second(), tuple.first(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default boolean useWith(LBiIntPredicate accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default boolean useWithO1(LBiIntPredicate accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTest(tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default boolean useWith(LBiIntPredicate.LInt1Int0Pred accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestInt1Int0(tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default boolean useWithO1(LBiIntPredicate.LInt1Int0Pred accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestInt1Int0(tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBiIntPredicate<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTest(a1, tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWithO1(T a1, LObjBiIntPredicate<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTest(a1, tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBiIntPredicate.LObjInt2Int1Pred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestObjInt2Int1(a1, tuple.first(), tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWithO1(T a1, LObjBiIntPredicate.LObjInt2Int1Pred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestObjInt2Int1(a1, tuple.second(), tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBiIntPredicate.LInt1ObjIntPred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestInt1ObjInt(tuple.first(), a1, tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWithO1(T a1, LObjBiIntPredicate.LInt1ObjIntPred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestInt1ObjInt(tuple.second(), a1, tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBiIntPredicate.LInt1Int2ObjPred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestInt1Int2Obj(tuple.first(), tuple.second(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWithO1(T a1, LObjBiIntPredicate.LInt1Int2ObjPred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestInt1Int2Obj(tuple.second(), tuple.first(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBiIntPredicate.LInt2ObjInt1Pred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestInt2ObjInt1(tuple.first(), a1, tuple.second());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWithO1(T a1, LObjBiIntPredicate.LInt2ObjInt1Pred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestInt2ObjInt1(tuple.second(), a1, tuple.first());
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBiIntPredicate.LBiIntObjPred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestBiIntObj(tuple.first(), tuple.second(), a1);
		releaseIntPair(tuple);
		return retval;
	}

	default <T> boolean useWithO1(T a1, LObjBiIntPredicate.LBiIntObjPred<T> accessFunction) {
		LIntPair tuple = accessIntPair();
		boolean retval = accessFunction.doTestBiIntObj(tuple.second(), tuple.first(), a1);
		releaseIntPair(tuple);
		return retval;
	}

}
