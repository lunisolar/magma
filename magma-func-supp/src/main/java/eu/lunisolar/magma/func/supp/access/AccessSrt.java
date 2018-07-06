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
public interface AccessSrt {

	static AccessSrt wrap(AccessSrt lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	short accessSrt();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseSrt(short a) {
		// NOOP
	}

	default void useWith(LSrtConsumer accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAccept(tuple);
	}

	default void useWith(short a2, LBiSrtConsumer accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(short a1, LBiSrtConsumer.LSrt1Srt0Cons accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptSrt1Srt0(tuple, a1);
	}

	default void useWith(int a2, LSrtIntConsumer accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a2, LSrtIntConsumer.LIntSrtCons accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptIntSrt(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjSrtConsumer<T1, T2> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjSrtConsumer.LObjSrtObj1Cons<T1, T2> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptObjSrtObj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjSrtConsumer.LSrtObj0Obj1Cons<T1, T2> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptSrtObj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjSrtConsumer<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjSrtConsumer.LSrtObjCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptSrtObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieSrtConsumer<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptObjSrtInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptIntObjSrt(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptIntSrtObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptSrtObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.doAcceptSrtIntObj(tuple, a2, a1);
	}

	default short useWith(short a2, LSrtBinaryOperator accessFunction) {
		short tuple = accessSrt();
		short retval = accessFunction.doApplyAsSrt(tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default short useWith(LSrtUnaryOperator accessFunction) {
		short tuple = accessSrt();
		short retval = accessFunction.doApplyAsSrt(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default byte useWith(LSrtToByteFunction accessFunction) {
		short tuple = accessSrt();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default char useWith(LSrtToCharFunction accessFunction) {
		short tuple = accessSrt();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default double useWith(LSrtToDblFunction accessFunction) {
		short tuple = accessSrt();
		double retval = accessFunction.doApplyAsDbl(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default float useWith(LSrtToFltFunction accessFunction) {
		short tuple = accessSrt();
		float retval = accessFunction.doApplyAsFlt(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default int useWith(LSrtToIntFunction accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default long useWith(LSrtToLongFunction accessFunction) {
		short tuple = accessSrt();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjSrtFunction<T1, T2, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjSrtFunction.LObjSrtObj1Func<T1, T2, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplyObjSrtObj1(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjSrtFunction.LSrtObj0Obj1Func<T1, T2, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplySrtObj0Obj1(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LBiSrtFunction<R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApply(tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R> R useWith(short a1, LBiSrtFunction.LSrt1Srt0Func<R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplySrt1Srt0(tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntSrtFunction<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplyObjSrtInt(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplyIntObjSrt(a2, a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplyIntSrtObj(a2, tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplySrtObjInt(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplySrtIntObj(tuple, a2, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjSrtFunction<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApply(a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjSrtFunction.LSrtObjFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApplySrtObj(tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <R> R useWith(LSrtFunction<R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.doApply(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieSrtFunction<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.doApplyAsInt(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.doApplyAsIntObjSrtInt(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.doApplyAsIntIntObjSrt(a2, a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.doApplyAsIntIntSrtObj(a2, tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.doApplyAsIntSrtObjInt(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.doApplyAsIntSrtIntObj(tuple, a2, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjSrtPredicate<T1, T2> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjSrtPredicate.LObjSrtObj1Pred<T1, T2> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestObjSrtObj1(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjSrtPredicate.LSrtObj0Obj1Pred<T1, T2> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestSrtObj0Obj1(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(short a2, LBiSrtPredicate accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(short a1, LBiSrtPredicate.LSrt1Srt0Pred accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestSrt1Srt0(tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntSrtPredicate<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestObjSrtInt(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestIntObjSrt(a2, a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestIntSrtObj(a2, tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestSrtObjInt(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestSrtIntObj(tuple, a2, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjSrtPredicate<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjSrtPredicate.LSrtObjPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestSrtObj(tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LSrtIntPredicate accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LSrtIntPredicate.LIntSrtPred accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTestIntSrt(a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(LSrtPredicate accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.doTest(tuple);
		releaseSrt(tuple);
		return retval;
	}

}
