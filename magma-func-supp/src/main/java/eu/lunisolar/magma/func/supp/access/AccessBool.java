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
public interface AccessBool {

	static AccessBool wrap(AccessBool lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	boolean accessBool();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBool(boolean a) {
		// NOOP
	}

	default void useWith(LBoolConsumer accessFunction) {
		boolean tuple = accessBool();
		accessFunction.accept(tuple);
	}

	default void useWith(boolean a2, LBiBoolConsumer accessFunction) {
		boolean tuple = accessBool();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(boolean a1, LBiBoolConsumer.LBool1Bool0Cons accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptBool1Bool0(tuple, a1);
	}

	default void useWith(int a2, LBoolIntConsumer accessFunction) {
		boolean tuple = accessBool();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(int a2, LBoolIntConsumer.LIntBoolCons accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptIntBool(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumer<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptObj0Bool2Obj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptBool2Obj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjBoolConsumer<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.accept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjBoolConsumer.LBoolObjCons<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptBoolObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieBoolConsumer<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieBoolConsumer.LObjBoolIntCons<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptObjBoolInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieBoolConsumer.LIntObjBoolCons<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptIntObjBool(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieBoolConsumer.LIntBoolObjCons<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptIntBoolObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieBoolConsumer.LBoolObjIntCons<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptBoolObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieBoolConsumer.LBoolIntObjCons<T> accessFunction) {
		boolean tuple = accessBool();
		accessFunction.acceptBoolIntObj(tuple, a2, a1);
	}

	default void useWith(boolean a2, boolean a3, LTriBoolConsumer accessFunction) {
		boolean tuple = accessBool();
		accessFunction.accept(tuple, a2, a3);
	}

	default boolean useWith(boolean a2, LLogicalBinaryOperator accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.apply(tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default boolean useWith(boolean a2, boolean a3, LLogicalTernaryOperator accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.apply(tuple, a2, a3);
		releaseBool(tuple);
		return retval;
	}

	default boolean useWith(LLogicalOperator accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.apply(tuple);
		releaseBool(tuple);
		return retval;
	}

	default byte useWith(LBoolToByteFunction accessFunction) {
		boolean tuple = accessBool();
		byte retval = accessFunction.applyAsByte(tuple);
		releaseBool(tuple);
		return retval;
	}

	default char useWith(LBoolToCharFunction accessFunction) {
		boolean tuple = accessBool();
		char retval = accessFunction.applyAsChar(tuple);
		releaseBool(tuple);
		return retval;
	}

	default double useWith(LBoolToDblFunction accessFunction) {
		boolean tuple = accessBool();
		double retval = accessFunction.applyAsDbl(tuple);
		releaseBool(tuple);
		return retval;
	}

	default float useWith(LBoolToFltFunction accessFunction) {
		boolean tuple = accessBool();
		float retval = accessFunction.applyAsFlt(tuple);
		releaseBool(tuple);
		return retval;
	}

	default int useWith(LBoolToIntFunction accessFunction) {
		boolean tuple = accessBool();
		int retval = accessFunction.applyAsInt(tuple);
		releaseBool(tuple);
		return retval;
	}

	default long useWith(LBoolToLongFunction accessFunction) {
		boolean tuple = accessBool();
		long retval = accessFunction.applyAsLong(tuple);
		releaseBool(tuple);
		return retval;
	}

	default short useWith(LBoolToSrtFunction accessFunction) {
		boolean tuple = accessBool();
		short retval = accessFunction.applyAsSrt(tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, LBiBoolFunction<R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.apply(tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <R> R useWith(boolean a1, LBiBoolFunction.LBool1Bool0Func<R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyBool1Bool0(tuple, a1);
		releaseBool(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunction<T1, T2, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyObj0Bool2Obj1(a1, tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyBool2Obj0Obj1(tuple, a1, a2);
		releaseBool(tuple);
		return retval;
	}

	default <R> R useWith(LBoolFunction<R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.apply(tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBoolFunction<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.apply(a1, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjBoolFunction.LBoolObjFunc<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyBoolObj(tuple, a1);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntBoolFunction<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntBoolFunction.LObjBoolIntFunc<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyObjBoolInt(a1, tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntBoolFunction.LIntObjBoolFunc<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyIntObjBool(a2, a1, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntBoolFunction.LIntBoolObjFunc<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyIntBoolObj(a2, tuple, a1);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntBoolFunction.LBoolObjIntFunc<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyBoolObjInt(tuple, a1, a2);
		releaseBool(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntBoolFunction.LBoolIntObjFunc<T, R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.applyBoolIntObj(tuple, a2, a1);
		releaseBool(tuple);
		return retval;
	}

	default <R> R useWith(boolean a2, boolean a3, LTriBoolFunction<R> accessFunction) {
		boolean tuple = accessBool();
		R retval = accessFunction.apply(tuple, a2, a3);
		releaseBool(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieBoolFunction<T> accessFunction) {
		boolean tuple = accessBool();
		int retval = accessFunction.applyAsInt(a1, a2, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieBoolFunction.LObjBoolIntToIntFunc<T> accessFunction) {
		boolean tuple = accessBool();
		int retval = accessFunction.applyAsIntObjBoolInt(a1, tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieBoolFunction.LIntObjBoolToIntFunc<T> accessFunction) {
		boolean tuple = accessBool();
		int retval = accessFunction.applyAsIntIntObjBool(a2, a1, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieBoolFunction.LIntBoolObjToIntFunc<T> accessFunction) {
		boolean tuple = accessBool();
		int retval = accessFunction.applyAsIntIntBoolObj(a2, tuple, a1);
		releaseBool(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieBoolFunction.LBoolObjIntToIntFunc<T> accessFunction) {
		boolean tuple = accessBool();
		int retval = accessFunction.applyAsIntBoolObjInt(tuple, a1, a2);
		releaseBool(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieBoolFunction.LBoolIntObjToIntFunc<T> accessFunction) {
		boolean tuple = accessBool();
		int retval = accessFunction.applyAsIntBoolIntObj(tuple, a2, a1);
		releaseBool(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicate<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testObj0Bool2Obj1(a1, tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testBool2Obj0Obj1(tuple, a1, a2);
		releaseBool(tuple);
		return retval;
	}

	default boolean useWith(int a2, LBoolIntPredicate accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.test(tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default boolean useWith(int a2, LBoolIntPredicate.LIntBoolPred accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testIntBool(a2, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBoolPredicate<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.test(a1, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBoolPredicate.LBoolObjPred<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testBoolObj(tuple, a1);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntBoolPredicate<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntBoolPredicate.LObjBoolIntPred<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testObjBoolInt(a1, tuple, a2);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntBoolPredicate.LIntObjBoolPred<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testIntObjBool(a2, a1, tuple);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntBoolPredicate.LIntBoolObjPred<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testIntBoolObj(a2, tuple, a1);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntBoolPredicate.LBoolObjIntPred<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testBoolObjInt(tuple, a1, a2);
		releaseBool(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntBoolPredicate.LBoolIntObjPred<T> accessFunction) {
		boolean tuple = accessBool();
		boolean retval = accessFunction.testBoolIntObj(tuple, a2, a1);
		releaseBool(tuple);
		return retval;
	}

}
