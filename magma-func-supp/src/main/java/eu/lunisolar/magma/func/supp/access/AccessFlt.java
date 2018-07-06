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
public interface AccessFlt {

	static AccessFlt wrap(AccessFlt lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	float accessFlt();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseFlt(float a) {
		// NOOP
	}

	default void useWith(LFltConsumer accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAccept(tuple);
	}

	default void useWith(float a2, LBiFltConsumer accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(float a1, LBiFltConsumer.LFlt1Flt0Cons accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptFlt1Flt0(tuple, a1);
	}

	default void useWith(int a2, LFltIntConsumer accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a2, LFltIntConsumer.LIntFltCons accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptIntFlt(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFltConsumer<T1, T2> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFltConsumer.LObjFltObj1Cons<T1, T2> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptObjFltObj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFltConsumer.LFltObj0Obj1Cons<T1, T2> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptFltObj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjFltConsumer<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjFltConsumer.LFltObjCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptFltObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieFltConsumer<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptObjFltInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptIntObjFlt(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptIntFltObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptFltObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.doAcceptFltIntObj(tuple, a2, a1);
	}

	default float useWith(float a2, LFltBinaryOperator accessFunction) {
		float tuple = accessFlt();
		float retval = accessFunction.doApplyAsFlt(tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default float useWith(LFltUnaryOperator accessFunction) {
		float tuple = accessFlt();
		float retval = accessFunction.doApplyAsFlt(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default byte useWith(LFltToByteFunction accessFunction) {
		float tuple = accessFlt();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default char useWith(LFltToCharFunction accessFunction) {
		float tuple = accessFlt();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default double useWith(LFltToDblFunction accessFunction) {
		float tuple = accessFlt();
		double retval = accessFunction.doApplyAsDbl(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default int useWith(LFltToIntFunction accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default long useWith(LFltToLongFunction accessFunction) {
		float tuple = accessFlt();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default short useWith(LFltToSrtFunction accessFunction) {
		float tuple = accessFlt();
		short retval = accessFunction.doApplyAsSrt(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LBiFltFunction<R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApply(tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R> R useWith(float a1, LBiFltFunction.LFlt1Flt0Func<R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyFlt1Flt0(tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFltFunction<T1, T2, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFltFunction.LObjFltObj1Func<T1, T2, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyObjFltObj1(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFltFunction.LFltObj0Obj1Func<T1, T2, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyFltObj0Obj1(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R> R useWith(LFltFunction<R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApply(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjFltFunction<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApply(a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjFltFunction.LFltObjFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyFltObj(tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntFltFunction<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyObjFltInt(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyIntObjFlt(a2, a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyIntFltObj(a2, tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyFltObjInt(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.doApplyFltIntObj(tuple, a2, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieFltFunction<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.doApplyAsInt(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.doApplyAsIntObjFltInt(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.doApplyAsIntIntObjFlt(a2, a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.doApplyAsIntIntFltObj(a2, tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.doApplyAsIntFltObjInt(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.doApplyAsIntFltIntObj(tuple, a2, a1);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(float a2, LBiFltPredicate accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(float a1, LBiFltPredicate.LFlt1Flt0Pred accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestFlt1Flt0(tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFltPredicate<T1, T2> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFltPredicate.LObjFltObj1Pred<T1, T2> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestObjFltObj1(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFltPredicate.LFltObj0Obj1Pred<T1, T2> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestFltObj0Obj1(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LFltIntPredicate accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LFltIntPredicate.LIntFltPred accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestIntFlt(a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(LFltPredicate accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTest(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjFltPredicate<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjFltPredicate.LFltObjPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestFltObj(tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntFltPredicate<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestObjFltInt(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestIntObjFlt(a2, a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestIntFltObj(a2, tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestFltObjInt(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.doTestFltIntObj(tuple, a2, a1);
		releaseFlt(tuple);
		return retval;
	}

}
