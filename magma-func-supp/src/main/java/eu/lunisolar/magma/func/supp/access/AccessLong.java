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
		accessFunction.accept(tuple);
	}

	default void useWith(long a2, LBiLongConsumer accessFunction) {
		long tuple = accessLong();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(long a1, LBiLongConsumer.LLong1Long0Cons accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptLong1Long0(tuple, a1);
	}

	default void useWith(int a2, LLongIntConsumer accessFunction) {
		long tuple = accessLong();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(int a2, LLongIntConsumer.LIntLongCons accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptIntLong(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumer<T1, T2> accessFunction) {
		long tuple = accessLong();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2> accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptObj0Long2Obj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2> accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptLong2Obj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjLongConsumer<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.accept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjLongConsumer.LLongObjCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptLongObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieLongConsumer<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieLongConsumer.LObjLongIntCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptObjLongInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieLongConsumer.LIntObjLongCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptIntObjLong(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieLongConsumer.LIntLongObjCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptIntLongObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieLongConsumer.LLongObjIntCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptLongObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieLongConsumer.LLongIntObjCons<T> accessFunction) {
		long tuple = accessLong();
		accessFunction.acceptLongIntObj(tuple, a2, a1);
	}

	default long useWith(long a2, LLongBinaryOperator accessFunction) {
		long tuple = accessLong();
		long retval = accessFunction.applyAsLong(tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default long useWith(LLongUnaryOperator accessFunction) {
		long tuple = accessLong();
		long retval = accessFunction.applyAsLong(tuple);
		releaseLong(tuple);
		return retval;
	}

	default byte useWith(LLongToByteFunction accessFunction) {
		long tuple = accessLong();
		byte retval = accessFunction.applyAsByte(tuple);
		releaseLong(tuple);
		return retval;
	}

	default char useWith(LLongToCharFunction accessFunction) {
		long tuple = accessLong();
		char retval = accessFunction.applyAsChar(tuple);
		releaseLong(tuple);
		return retval;
	}

	default double useWith(LLongToDblFunction accessFunction) {
		long tuple = accessLong();
		double retval = accessFunction.applyAsDbl(tuple);
		releaseLong(tuple);
		return retval;
	}

	default float useWith(LLongToFltFunction accessFunction) {
		long tuple = accessLong();
		float retval = accessFunction.applyAsFlt(tuple);
		releaseLong(tuple);
		return retval;
	}

	default int useWith(LLongToIntFunction accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.applyAsInt(tuple);
		releaseLong(tuple);
		return retval;
	}

	default short useWith(LLongToSrtFunction accessFunction) {
		long tuple = accessLong();
		short retval = accessFunction.applyAsSrt(tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R> R useWith(long a2, LBiLongFunction<R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.apply(tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R> R useWith(long a1, LBiLongFunction.LLong1Long0Func<R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyLong1Long0(tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunction<T1, T2, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyObj0Long2Obj1(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyLong2Obj0Obj1(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R> R useWith(LLongFunction<R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.apply(tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntLongFunction<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntLongFunction.LObjLongIntFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyObjLongInt(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntLongFunction.LIntObjLongFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyIntObjLong(a2, a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntLongFunction.LIntLongObjFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyIntLongObj(a2, tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntLongFunction.LLongObjIntFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyLongObjInt(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntLongFunction.LLongIntObjFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyLongIntObj(tuple, a2, a1);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjLongFunction<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.apply(a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjLongFunction.LLongObjFunc<T, R> accessFunction) {
		long tuple = accessLong();
		R retval = accessFunction.applyLongObj(tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieLongFunction<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.applyAsInt(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieLongFunction.LObjLongIntToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.applyAsIntObjLongInt(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieLongFunction.LIntObjLongToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.applyAsIntIntObjLong(a2, a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieLongFunction.LIntLongObjToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.applyAsIntIntLongObj(a2, tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieLongFunction.LLongObjIntToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.applyAsIntLongObjInt(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieLongFunction.LLongIntObjToIntFunc<T> accessFunction) {
		long tuple = accessLong();
		int retval = accessFunction.applyAsIntLongIntObj(tuple, a2, a1);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(long a2, LBiLongPredicate accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.test(tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(long a1, LBiLongPredicate.LLong1Long0Pred accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testLong1Long0(tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicate<T1, T2> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testObj0Long2Obj1(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testLong2Obj0Obj1(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(int a2, LLongIntPredicate accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.test(tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(int a2, LLongIntPredicate.LIntLongPred accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testIntLong(a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default boolean useWith(LLongPredicate accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.test(tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntLongPredicate<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntLongPredicate.LObjLongIntPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testObjLongInt(a1, tuple, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntLongPredicate.LIntObjLongPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testIntObjLong(a2, a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntLongPredicate.LIntLongObjPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testIntLongObj(a2, tuple, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntLongPredicate.LLongObjIntPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testLongObjInt(tuple, a1, a2);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntLongPredicate.LLongIntObjPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testLongIntObj(tuple, a2, a1);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjLongPredicate<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.test(a1, tuple);
		releaseLong(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjLongPredicate.LLongObjPred<T> accessFunction) {
		long tuple = accessLong();
		boolean retval = accessFunction.testLongObj(tuple, a1);
		releaseLong(tuple);
		return retval;
	}

}
