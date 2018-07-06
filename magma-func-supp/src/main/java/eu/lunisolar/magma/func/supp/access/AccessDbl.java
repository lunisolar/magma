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
public interface AccessDbl {

	static AccessDbl wrap(AccessDbl lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	double accessDbl();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseDbl(double a) {
		// NOOP
	}

	default void useWith(LDblConsumer accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAccept(tuple);
	}

	default void useWith(double a2, LBiDblConsumer accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(double a1, LBiDblConsumer.LDbl1Dbl0Cons accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptDbl1Dbl0(tuple, a1);
	}

	default void useWith(int a2, LDblIntConsumer accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a2, LDblIntConsumer.LIntDblCons accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptIntDbl(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjDblConsumer<T1, T2> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjDblConsumer.LObjDblObj1Cons<T1, T2> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptObjDblObj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjDblConsumer.LDblObj0Obj1Cons<T1, T2> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptDblObj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjDblConsumer<T> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjDblConsumer.LDblObjCons<T> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptDblObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieDblConsumer<T> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieDblConsumer.LObjDblIntCons<T> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptObjDblInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieDblConsumer.LIntObjDblCons<T> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptIntObjDbl(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieDblConsumer.LIntDblObjCons<T> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptIntDblObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieDblConsumer.LDblObjIntCons<T> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptDblObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieDblConsumer.LDblIntObjCons<T> accessFunction) {
		double tuple = accessDbl();
		accessFunction.doAcceptDblIntObj(tuple, a2, a1);
	}

	default double useWith(double a2, LDblBinaryOperator accessFunction) {
		double tuple = accessDbl();
		double retval = accessFunction.doApplyAsDbl(tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default double useWith(LDblUnaryOperator accessFunction) {
		double tuple = accessDbl();
		double retval = accessFunction.doApplyAsDbl(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default byte useWith(LDblToByteFunction accessFunction) {
		double tuple = accessDbl();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default char useWith(LDblToCharFunction accessFunction) {
		double tuple = accessDbl();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default float useWith(LDblToFltFunction accessFunction) {
		double tuple = accessDbl();
		float retval = accessFunction.doApplyAsFlt(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default int useWith(LDblToIntFunction accessFunction) {
		double tuple = accessDbl();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default long useWith(LDblToLongFunction accessFunction) {
		double tuple = accessDbl();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default short useWith(LDblToSrtFunction accessFunction) {
		double tuple = accessDbl();
		short retval = accessFunction.doApplyAsSrt(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LBiDblFunction<R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApply(tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <R> R useWith(double a1, LBiDblFunction.LDbl1Dbl0Func<R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyDbl1Dbl0(tuple, a1);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjDblFunction<T1, T2, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjDblFunction.LObjDblObj1Func<T1, T2, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyObjDblObj1(a1, tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjDblFunction.LDblObj0Obj1Func<T1, T2, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyDblObj0Obj1(tuple, a1, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <R> R useWith(LDblFunction<R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApply(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjDblFunction<T, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApply(a1, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjDblFunction.LDblObjFunc<T, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyDblObj(tuple, a1);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntDblFunction<T, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntDblFunction.LObjDblIntFunc<T, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyObjDblInt(a1, tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntDblFunction.LIntObjDblFunc<T, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyIntObjDbl(a2, a1, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntDblFunction.LIntDblObjFunc<T, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyIntDblObj(a2, tuple, a1);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntDblFunction.LDblObjIntFunc<T, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyDblObjInt(tuple, a1, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntDblFunction.LDblIntObjFunc<T, R> accessFunction) {
		double tuple = accessDbl();
		R retval = accessFunction.doApplyDblIntObj(tuple, a2, a1);
		releaseDbl(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieDblFunction<T> accessFunction) {
		double tuple = accessDbl();
		int retval = accessFunction.doApplyAsInt(a1, a2, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieDblFunction.LObjDblIntToIntFunc<T> accessFunction) {
		double tuple = accessDbl();
		int retval = accessFunction.doApplyAsIntObjDblInt(a1, tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieDblFunction.LIntObjDblToIntFunc<T> accessFunction) {
		double tuple = accessDbl();
		int retval = accessFunction.doApplyAsIntIntObjDbl(a2, a1, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieDblFunction.LIntDblObjToIntFunc<T> accessFunction) {
		double tuple = accessDbl();
		int retval = accessFunction.doApplyAsIntIntDblObj(a2, tuple, a1);
		releaseDbl(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieDblFunction.LDblObjIntToIntFunc<T> accessFunction) {
		double tuple = accessDbl();
		int retval = accessFunction.doApplyAsIntDblObjInt(tuple, a1, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieDblFunction.LDblIntObjToIntFunc<T> accessFunction) {
		double tuple = accessDbl();
		int retval = accessFunction.doApplyAsIntDblIntObj(tuple, a2, a1);
		releaseDbl(tuple);
		return retval;
	}

	default boolean useWith(double a2, LBiDblPredicate accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default boolean useWith(double a1, LBiDblPredicate.LDbl1Dbl0Pred accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestDbl1Dbl0(tuple, a1);
		releaseDbl(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjDblPredicate<T1, T2> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjDblPredicate.LObjDblObj1Pred<T1, T2> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestObjDblObj1(a1, tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjDblPredicate.LDblObj0Obj1Pred<T1, T2> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestDblObj0Obj1(tuple, a1, a2);
		releaseDbl(tuple);
		return retval;
	}

	default boolean useWith(int a2, LDblIntPredicate accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default boolean useWith(int a2, LDblIntPredicate.LIntDblPred accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestIntDbl(a2, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default boolean useWith(LDblPredicate accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTest(tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjDblPredicate<T> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjDblPredicate.LDblObjPred<T> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestDblObj(tuple, a1);
		releaseDbl(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntDblPredicate<T> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntDblPredicate.LObjDblIntPred<T> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestObjDblInt(a1, tuple, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntDblPredicate.LIntObjDblPred<T> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestIntObjDbl(a2, a1, tuple);
		releaseDbl(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntDblPredicate.LIntDblObjPred<T> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestIntDblObj(a2, tuple, a1);
		releaseDbl(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntDblPredicate.LDblObjIntPred<T> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestDblObjInt(tuple, a1, a2);
		releaseDbl(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntDblPredicate.LDblIntObjPred<T> accessFunction) {
		double tuple = accessDbl();
		boolean retval = accessFunction.doTestDblIntObj(tuple, a2, a1);
		releaseDbl(tuple);
		return retval;
	}

}
