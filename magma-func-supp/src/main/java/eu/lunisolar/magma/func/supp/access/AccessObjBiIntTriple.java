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
public interface AccessObjBiIntTriple<T> {

	static <T> AccessObjBiIntTriple<T> wrap(AccessObjBiIntTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjBiIntTriple<T> accessObjBiIntTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjBiIntTriple(LObjBiIntTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieIntConsumer<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWithO1(LTieIntConsumer<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAccept(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieIntConsumer.LObjInt2Int1Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptObjInt2Int1(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWithO1(LTieIntConsumer.LObjInt2Int1Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptObjInt2Int1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieIntConsumer.LInt1ObjIntCons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptInt1ObjInt(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWithO1(LTieIntConsumer.LInt1ObjIntCons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptInt1ObjInt(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieIntConsumer.LInt1Int2ObjCons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptInt1Int2Obj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWithO1(LTieIntConsumer.LInt1Int2ObjCons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptInt1Int2Obj(tuple.third(), tuple.second(), tuple.first());
	}

	default void useWith(LTieIntConsumer.LInt2ObjInt1Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptInt2ObjInt1(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWithO1(LTieIntConsumer.LInt2ObjInt1Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptInt2ObjInt1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieIntConsumer.LBiIntObjCons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptBiIntObj(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWithO1(LTieIntConsumer.LBiIntObjCons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.doAcceptBiIntObj(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjBiIntFunction<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LObjInt2Int1Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyObjInt2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LObjInt2Int1Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyObjInt2Int1(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LInt1ObjIntFunc<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyInt1ObjInt(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LInt1ObjIntFunc<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyInt1ObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LInt1Int2ObjFunc<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyInt1Int2Obj(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LInt1Int2ObjFunc<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyInt1Int2Obj(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LInt2ObjInt1Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyInt2ObjInt1(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LInt2ObjInt1Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyInt2ObjInt1(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LBiIntObjFunc<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyBiIntObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LBiIntObjFunc<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.doApplyBiIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LObjInt2Int1ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntObjInt2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LObjInt2Int1ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntObjInt2Int1(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LInt1ObjIntToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntInt1ObjInt(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LInt1ObjIntToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntInt1ObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LInt1Int2ObjToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntInt1Int2Obj(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LInt1Int2ObjToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntInt1Int2Obj(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LInt2ObjInt1ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntInt2ObjInt1(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LInt2ObjInt1ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntInt2ObjInt1(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LBiIntObjToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntBiIntObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LBiIntObjToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.doApplyAsIntBiIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LObjInt2Int1Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestObjInt2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LObjInt2Int1Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestObjInt2Int1(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LInt1ObjIntPred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestInt1ObjInt(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LInt1ObjIntPred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestInt1ObjInt(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LInt1Int2ObjPred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestInt1Int2Obj(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LInt1Int2ObjPred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestInt1Int2Obj(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LInt2ObjInt1Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestInt2ObjInt1(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LInt2ObjInt1Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestInt2ObjInt1(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LBiIntObjPred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestBiIntObj(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LBiIntObjPred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.doTestBiIntObj(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

}
