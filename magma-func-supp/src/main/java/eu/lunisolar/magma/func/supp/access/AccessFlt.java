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
		accessFunction.accept(tuple);
	}

	default void useWith(float a2, LBiFltConsumer accessFunction) {
		float tuple = accessFlt();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(float a1, LBiFltConsumer.LFlt1Flt0Cons accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptFlt1Flt0(tuple, a1);
	}

	default void useWith(int a2, LFltIntConsumer accessFunction) {
		float tuple = accessFlt();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(int a2, LFltIntConsumer.LIntFltCons accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptIntFlt(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFltConsumer<T1, T2> accessFunction) {
		float tuple = accessFlt();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2> accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptObj0Flt2Obj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2> accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptFlt2Obj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjFltConsumer<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.accept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjFltConsumer.LFltObjCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptFltObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieFltConsumer<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieFltConsumer.LObjFltIntCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptObjFltInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieFltConsumer.LIntObjFltCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptIntObjFlt(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieFltConsumer.LIntFltObjCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptIntFltObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieFltConsumer.LFltObjIntCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptFltObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieFltConsumer.LFltIntObjCons<T> accessFunction) {
		float tuple = accessFlt();
		accessFunction.acceptFltIntObj(tuple, a2, a1);
	}

	default float useWith(float a2, LFltBinaryOperator accessFunction) {
		float tuple = accessFlt();
		float retval = accessFunction.applyAsFlt(tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default float useWith(LFltUnaryOperator accessFunction) {
		float tuple = accessFlt();
		float retval = accessFunction.applyAsFlt(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default byte useWith(LFltToByteFunction accessFunction) {
		float tuple = accessFlt();
		byte retval = accessFunction.applyAsByte(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default char useWith(LFltToCharFunction accessFunction) {
		float tuple = accessFlt();
		char retval = accessFunction.applyAsChar(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default double useWith(LFltToDblFunction accessFunction) {
		float tuple = accessFlt();
		double retval = accessFunction.applyAsDbl(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default int useWith(LFltToIntFunction accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.applyAsInt(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default long useWith(LFltToLongFunction accessFunction) {
		float tuple = accessFlt();
		long retval = accessFunction.applyAsLong(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default short useWith(LFltToSrtFunction accessFunction) {
		float tuple = accessFlt();
		short retval = accessFunction.applyAsSrt(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R> R useWith(float a2, LBiFltFunction<R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.apply(tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R> R useWith(float a1, LBiFltFunction.LFlt1Flt0Func<R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyFlt1Flt0(tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFltFunction<T1, T2, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyObj0Flt2Obj1(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyFlt2Obj0Obj1(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R> R useWith(LFltFunction<R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.apply(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjFltFunction<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.apply(a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjFltFunction.LFltObjFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyFltObj(tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntFltFunction<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntFltFunction.LObjFltIntFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyObjFltInt(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntFltFunction.LIntObjFltFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyIntObjFlt(a2, a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntFltFunction.LIntFltObjFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyIntFltObj(a2, tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntFltFunction.LFltObjIntFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyFltObjInt(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntFltFunction.LFltIntObjFunc<T, R> accessFunction) {
		float tuple = accessFlt();
		R retval = accessFunction.applyFltIntObj(tuple, a2, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieFltFunction<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.applyAsInt(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieFltFunction.LObjFltIntToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.applyAsIntObjFltInt(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieFltFunction.LIntObjFltToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.applyAsIntIntObjFlt(a2, a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieFltFunction.LIntFltObjToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.applyAsIntIntFltObj(a2, tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieFltFunction.LFltObjIntToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.applyAsIntFltObjInt(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieFltFunction.LFltIntObjToIntFunc<T> accessFunction) {
		float tuple = accessFlt();
		int retval = accessFunction.applyAsIntFltIntObj(tuple, a2, a1);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(float a2, LBiFltPredicate accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.test(tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(float a1, LBiFltPredicate.LFlt1Flt0Pred accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testFlt1Flt0(tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFltPredicate<T1, T2> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testObj0Flt2Obj1(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testFlt2Obj0Obj1(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LFltIntPredicate accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.test(tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(int a2, LFltIntPredicate.LIntFltPred accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testIntFlt(a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default boolean useWith(LFltPredicate accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.test(tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjFltPredicate<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.test(a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjFltPredicate.LFltObjPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testFltObj(tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntFltPredicate<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntFltPredicate.LObjFltIntPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testObjFltInt(a1, tuple, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntFltPredicate.LIntObjFltPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testIntObjFlt(a2, a1, tuple);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntFltPredicate.LIntFltObjPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testIntFltObj(a2, tuple, a1);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntFltPredicate.LFltObjIntPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testFltObjInt(tuple, a1, a2);
		releaseFlt(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntFltPredicate.LFltIntObjPred<T> accessFunction) {
		float tuple = accessFlt();
		boolean retval = accessFunction.testFltIntObj(tuple, a2, a1);
		releaseFlt(tuple);
		return retval;
	}

}
