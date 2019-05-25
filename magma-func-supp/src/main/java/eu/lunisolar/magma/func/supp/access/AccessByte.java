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
public interface AccessByte {

	static AccessByte wrap(AccessByte lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	byte accessByte();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseByte(byte a) {
		// NOOP
	}

	default void useWith(LByteConsumer accessFunction) {
		byte tuple = accessByte();
		accessFunction.accept(tuple);
	}

	default void useWith(byte a2, LBiByteConsumer accessFunction) {
		byte tuple = accessByte();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(byte a1, LBiByteConsumer.LByte1Byte0Cons accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptByte1Byte0(tuple, a1);
	}

	default void useWith(int a2, LByteIntConsumer accessFunction) {
		byte tuple = accessByte();
		accessFunction.accept(tuple, a2);
	}

	default void useWith(int a2, LByteIntConsumer.LIntByteCons accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptIntByte(a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumer<T1, T2> accessFunction) {
		byte tuple = accessByte();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptObj0Byte2Obj1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptByte2Obj0Obj1(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjByteConsumer<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.accept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjByteConsumer.LByteObjCons<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptByteObj(tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieByteConsumer<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.accept(a1, a2, tuple);
	}

	default <T> void useWith(T a1, int a2, LTieByteConsumer.LObjByteIntCons<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptObjByteInt(a1, tuple, a2);
	}

	default <T> void useWith(int a2, T a1, LTieByteConsumer.LIntObjByteCons<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptIntObjByte(a2, a1, tuple);
	}

	default <T> void useWith(int a2, T a1, LTieByteConsumer.LIntByteObjCons<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptIntByteObj(a2, tuple, a1);
	}

	default <T> void useWith(T a1, int a2, LTieByteConsumer.LByteObjIntCons<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptByteObjInt(tuple, a1, a2);
	}

	default <T> void useWith(int a2, T a1, LTieByteConsumer.LByteIntObjCons<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.acceptByteIntObj(tuple, a2, a1);
	}

	default byte useWith(byte a2, LByteBinaryOperator accessFunction) {
		byte tuple = accessByte();
		byte retval = accessFunction.applyAsByte(tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default byte useWith(LByteUnaryOperator accessFunction) {
		byte tuple = accessByte();
		byte retval = accessFunction.applyAsByte(tuple);
		releaseByte(tuple);
		return retval;
	}

	default char useWith(LByteToCharFunction accessFunction) {
		byte tuple = accessByte();
		char retval = accessFunction.applyAsChar(tuple);
		releaseByte(tuple);
		return retval;
	}

	default double useWith(LByteToDblFunction accessFunction) {
		byte tuple = accessByte();
		double retval = accessFunction.applyAsDbl(tuple);
		releaseByte(tuple);
		return retval;
	}

	default float useWith(LByteToFltFunction accessFunction) {
		byte tuple = accessByte();
		float retval = accessFunction.applyAsFlt(tuple);
		releaseByte(tuple);
		return retval;
	}

	default int useWith(LByteToIntFunction accessFunction) {
		byte tuple = accessByte();
		int retval = accessFunction.applyAsInt(tuple);
		releaseByte(tuple);
		return retval;
	}

	default long useWith(LByteToLongFunction accessFunction) {
		byte tuple = accessByte();
		long retval = accessFunction.applyAsLong(tuple);
		releaseByte(tuple);
		return retval;
	}

	default short useWith(LByteToSrtFunction accessFunction) {
		byte tuple = accessByte();
		short retval = accessFunction.applyAsSrt(tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LBiByteFunction<R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.apply(tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <R> R useWith(byte a1, LBiByteFunction.LByte1Byte0Func<R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyByte1Byte0(tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunction<T1, T2, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyObj0Byte2Obj1(a1, tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyByte2Obj0Obj1(tuple, a1, a2);
		releaseByte(tuple);
		return retval;
	}

	default <R> R useWith(LByteFunction<R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.apply(tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjByteFunction<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.apply(a1, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjByteFunction.LByteObjFunc<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyByteObj(tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntByteFunction<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.apply(a1, a2, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntByteFunction.LObjByteIntFunc<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyObjByteInt(a1, tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntByteFunction.LIntObjByteFunc<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyIntObjByte(a2, a1, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntByteFunction.LIntByteObjFunc<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyIntByteObj(a2, tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, int a2, LObjIntByteFunction.LByteObjIntFunc<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyByteObjInt(tuple, a1, a2);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(int a2, T a1, LObjIntByteFunction.LByteIntObjFunc<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.applyByteIntObj(tuple, a2, a1);
		releaseByte(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieByteFunction<T> accessFunction) {
		byte tuple = accessByte();
		int retval = accessFunction.applyAsInt(a1, a2, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieByteFunction.LObjByteIntToIntFunc<T> accessFunction) {
		byte tuple = accessByte();
		int retval = accessFunction.applyAsIntObjByteInt(a1, tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieByteFunction.LIntObjByteToIntFunc<T> accessFunction) {
		byte tuple = accessByte();
		int retval = accessFunction.applyAsIntIntObjByte(a2, a1, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieByteFunction.LIntByteObjToIntFunc<T> accessFunction) {
		byte tuple = accessByte();
		int retval = accessFunction.applyAsIntIntByteObj(a2, tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <T> int useWith(T a1, int a2, LTieByteFunction.LByteObjIntToIntFunc<T> accessFunction) {
		byte tuple = accessByte();
		int retval = accessFunction.applyAsIntByteObjInt(tuple, a1, a2);
		releaseByte(tuple);
		return retval;
	}

	default <T> int useWith(int a2, T a1, LTieByteFunction.LByteIntObjToIntFunc<T> accessFunction) {
		byte tuple = accessByte();
		int retval = accessFunction.applyAsIntByteIntObj(tuple, a2, a1);
		releaseByte(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LBiBytePredicate accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.test(tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default boolean useWith(byte a1, LBiBytePredicate.LByte1Byte0Pred accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testByte1Byte0(tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicate<T1, T2> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testObj0Byte2Obj1(a1, tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testByte2Obj0Obj1(tuple, a1, a2);
		releaseByte(tuple);
		return retval;
	}

	default boolean useWith(int a2, LByteIntPredicate accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.test(tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default boolean useWith(int a2, LByteIntPredicate.LIntBytePred accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testIntByte(a2, tuple);
		releaseByte(tuple);
		return retval;
	}

	default boolean useWith(LBytePredicate accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.test(tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBytePredicate<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.test(a1, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBytePredicate.LByteObjPred<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testByteObj(tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntBytePredicate<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.test(a1, a2, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntBytePredicate.LObjByteIntPred<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testObjByteInt(a1, tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntBytePredicate.LIntObjBytePred<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testIntObjByte(a2, a1, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntBytePredicate.LIntByteObjPred<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testIntByteObj(a2, tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, int a2, LObjIntBytePredicate.LByteObjIntPred<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testByteObjInt(tuple, a1, a2);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(int a2, T a1, LObjIntBytePredicate.LByteIntObjPred<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.testByteIntObj(tuple, a2, a1);
		releaseByte(tuple);
		return retval;
	}

}
