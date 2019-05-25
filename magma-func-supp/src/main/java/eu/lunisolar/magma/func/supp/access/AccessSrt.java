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
		accessFunction.accept(tuple);
	}

	default void useWith(short a2, LBiSrtConsumer accessFunction) {
		short tuple = accessSrt();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(short a1, LBiSrtConsumer.LSrt1Srt0Cons accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptSrt1Srt0(tuple, a1);
	}

	default void useWith(int a2, LSrtIntConsumer accessFunction) {
		short tuple = accessSrt();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(int a2, LSrtIntConsumer.LIntSrtCons accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptIntSrt(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjSrtConsumer<T1, T2> accessFunction) {
		short tuple = accessSrt();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptObj0Srt2Obj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptSrt2Obj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjSrtConsumer<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.accept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjSrtConsumer.LSrtObjCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptSrtObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieSrtConsumer<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieSrtConsumer.LObjSrtIntCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptObjSrtInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieSrtConsumer.LIntObjSrtCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptIntObjSrt(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieSrtConsumer.LIntSrtObjCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptIntSrtObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieSrtConsumer.LSrtObjIntCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptSrtObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieSrtConsumer.LSrtIntObjCons<T> accessFunction) {
		short tuple = accessSrt();
		accessFunction.acceptSrtIntObj(tuple, a2, a1);
	}

	default short useWith(short a2, LSrtBinaryOperator accessFunction) {
		short tuple = accessSrt();
		short retval = accessFunction.applyAsSrt(tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default short useWith(LSrtUnaryOperator accessFunction) {
		short tuple = accessSrt();
		short retval = accessFunction.applyAsSrt(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default byte useWith(LSrtToByteFunction accessFunction) {
		short tuple = accessSrt();
		byte retval = accessFunction.applyAsByte(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default char useWith(LSrtToCharFunction accessFunction) {
		short tuple = accessSrt();
		char retval = accessFunction.applyAsChar(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default double useWith(LSrtToDblFunction accessFunction) {
		short tuple = accessSrt();
		double retval = accessFunction.applyAsDbl(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default float useWith(LSrtToFltFunction accessFunction) {
		short tuple = accessSrt();
		float retval = accessFunction.applyAsFlt(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default int useWith(LSrtToIntFunction accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.applyAsInt(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default long useWith(LSrtToLongFunction accessFunction) {
		short tuple = accessSrt();
		long retval = accessFunction.applyAsLong(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjSrtFunction<T1, T2, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applyObj0Srt2Obj1(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applySrt2Obj0Obj1(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R> R useWith(short a2, LBiSrtFunction<R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.apply(tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R> R useWith(short a1, LBiSrtFunction.LSrt1Srt0Func<R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applySrt1Srt0(tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntSrtFunction<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntSrtFunction.LObjSrtIntFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applyObjSrtInt(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntSrtFunction.LIntObjSrtFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applyIntObjSrt(a2, a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntSrtFunction.LIntSrtObjFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applyIntSrtObj(a2, tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntSrtFunction.LSrtObjIntFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applySrtObjInt(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntSrtFunction.LSrtIntObjFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applySrtIntObj(tuple, a2, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjSrtFunction<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.apply(a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjSrtFunction.LSrtObjFunc<T, R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.applySrtObj(tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <R> R useWith(LSrtFunction<R> accessFunction) {
		short tuple = accessSrt();
		R retval = accessFunction.apply(tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieSrtFunction<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.applyAsInt(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieSrtFunction.LObjSrtIntToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.applyAsIntObjSrtInt(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieSrtFunction.LIntObjSrtToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.applyAsIntIntObjSrt(a2, a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieSrtFunction.LIntSrtObjToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.applyAsIntIntSrtObj(a2, tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieSrtFunction.LSrtObjIntToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.applyAsIntSrtObjInt(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieSrtFunction.LSrtIntObjToIntFunc<T> accessFunction) {
		short tuple = accessSrt();
		int retval = accessFunction.applyAsIntSrtIntObj(tuple, a2, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjSrtPredicate<T1, T2> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testObj0Srt2Obj1(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testSrt2Obj0Obj1(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(short a2, LBiSrtPredicate accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.test(tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(short a1, LBiSrtPredicate.LSrt1Srt0Pred accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testSrt1Srt0(tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntSrtPredicate<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntSrtPredicate.LObjSrtIntPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testObjSrtInt(a1, tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntSrtPredicate.LIntObjSrtPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testIntObjSrt(a2, a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntSrtPredicate.LIntSrtObjPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testIntSrtObj(a2, tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntSrtPredicate.LSrtObjIntPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testSrtObjInt(tuple, a1, a2);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntSrtPredicate.LSrtIntObjPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testSrtIntObj(tuple, a2, a1);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjSrtPredicate<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjSrtPredicate.LSrtObjPred<T> accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testSrtObj(tuple, a1);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LSrtIntPredicate accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.test(tuple, a2);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LSrtIntPredicate.LIntSrtPred accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.testIntSrt(a2, tuple);
		releaseSrt(tuple);
		return retval;
	}

	default boolean useWith(LSrtPredicate accessFunction) {
		short tuple = accessSrt();
		boolean retval = accessFunction.test(tuple);
		releaseSrt(tuple);
		return retval;
	}

}
