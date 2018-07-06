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
public interface AccessLong {

	static AccessLong wrap(AccessLong lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	long accessLong();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseLong(long a) {
		// NOOP
	}

	default void useWith(LLongConsumer accessFunction) {
		long tuple = accessLong();
		accessFunction.doAccept(tuple);
	}

	default void useWith(long a2, LBiLongConsumer accessFunction) {
		long tuple = accessLong();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(long a1, LBiLongConsumer.LLong1Long0Cons accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptLong1Long0(tuple, a1);
	}

	default void useWith(int a2, LLongIntConsumer accessFunction) {
		long tuple = accessLong();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(int a2, LLongIntConsumer.LIntLongCons accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptIntLong(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumer<T1, T2> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumer.LObjLongObj1Cons<T1, T2> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptObjLongObj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumer.LLongObj0Obj1Cons<T1, T2> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptLongObj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjLongConsumer<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjLongConsumer.LLongObjCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptLongObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieLongConsumer<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptObjLongInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptIntObjLong(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptIntLongObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptLongObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.doAcceptLongIntObj(tuple, a2, a1);
	}

	default long useWith(long a2, LLongBinaryOperator accessFunction) {
		long tuple = accessLong();
		long retval = accessFunction.doApplyAsLong(tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default long useWith(LLongUnaryOperator accessFunction) {
		long tuple = accessLong();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseLong(tuple);
		return retval;
	}

	default byte useWith(LLongToByteFunction accessFunction) {
		long tuple = accessLong();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseLong(tuple);
		return retval;
	}

	default char useWith(LLongToCharFunction accessFunction) {
		long tuple = accessLong();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseLong(tuple);
		return retval;
	}

	default double useWith(LLongToDblFunction accessFunction) {
		long tuple = accessLong();
		double retval = accessFunction.doApplyAsDbl(tuple);
		releaseLong(tuple);
		return retval;
	}

	default float useWith(LLongToFltFunction accessFunction) {
		long tuple = accessLong();
		float retval = accessFunction.doApplyAsFlt(tuple);
		releaseLong(tuple);
		return retval;
	}

	default int useWith(LLongToIntFunction accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseLong(tuple);
		return retval;
	}

	default short useWith(LLongToSrtFunction accessFunction) {
		long tuple = accessLong();
		short retval = accessFunction.doApplyAsSrt(tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LBiLongFunction<R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApply(tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R> R useWith(long a1, LBiLongFunction.LLong1Long0Func<R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyLong1Long0(tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunction<T1, T2, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunction.LObjLongObj1Func<T1, T2, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyObjLongObj1(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunction.LLongObj0Obj1Func<T1, T2, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyLongObj0Obj1(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R> R useWith(LLongFunction<R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApply(tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntLongFunction<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyObjLongInt(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyIntObjLong(a2, a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyIntLongObj(a2, tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyLongObjInt(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyLongIntObj(tuple, a2, a1);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjLongFunction<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApply(a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjLongFunction.LLongObjFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.doApplyLongObj(tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieLongFunction<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.doApplyAsInt(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.doApplyAsIntObjLongInt(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.doApplyAsIntIntObjLong(a2, a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.doApplyAsIntIntLongObj(a2, tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.doApplyAsIntLongObjInt(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.doApplyAsIntLongIntObj(tuple, a2, a1);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(long a2, LBiLongPredicate accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(long a1, LBiLongPredicate.LLong1Long0Pred accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestLong1Long0(tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicate<T1, T2> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicate.LObjLongObj1Pred<T1, T2> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestObjLongObj1(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicate.LLongObj0Obj1Pred<T1, T2> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestLongObj0Obj1(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(int a2, LLongIntPredicate accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(int a2, LLongIntPredicate.LIntLongPred accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestIntLong(a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(LLongPredicate accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTest(tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntLongPredicate<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestObjLongInt(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestIntObjLong(a2, a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestIntLongObj(a2, tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestLongObjInt(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestLongIntObj(tuple, a2, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjLongPredicate<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjLongPredicate.LLongObjPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.doTestLongObj(tuple, a1);
		releaseLong(tuple);
		return retval;
	}

}
