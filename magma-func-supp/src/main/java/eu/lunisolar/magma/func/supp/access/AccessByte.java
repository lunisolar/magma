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
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR

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
		accessFunction.doAccept(tuple);
	}

	default void useWith(byte a2, LBiByteConsumer accessFunction) {
		byte tuple = accessByte();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(byte a1, LBiByteConsumer.V1 accessFunction) {
		byte tuple = accessByte();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumer<T1, T2> accessFunction) {
		byte tuple = accessByte();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumer.V1<T1, T2> accessFunction) {
		byte tuple = accessByte();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjByteConsumer.V4<T1, T2> accessFunction) {
		byte tuple = accessByte();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjByteConsumer<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjByteConsumer.V1<T> accessFunction) {
		byte tuple = accessByte();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default byte useWith(byte a2, LByteBinaryOperator accessFunction) {
		byte tuple = accessByte();
		byte retval = accessFunction.doApplyAsByte(tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default byte useWith(LByteUnaryOperator accessFunction) {
		byte tuple = accessByte();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseByte(tuple);
		return retval;
	}

	default char useWith(LByteToCharFunction accessFunction) {
		byte tuple = accessByte();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseByte(tuple);
		return retval;
	}

	default double useWith(LByteToDoubleFunction accessFunction) {
		byte tuple = accessByte();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseByte(tuple);
		return retval;
	}

	default float useWith(LByteToFloatFunction accessFunction) {
		byte tuple = accessByte();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseByte(tuple);
		return retval;
	}

	default int useWith(LByteToIntFunction accessFunction) {
		byte tuple = accessByte();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseByte(tuple);
		return retval;
	}

	default long useWith(LByteToLongFunction accessFunction) {
		byte tuple = accessByte();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseByte(tuple);
		return retval;
	}

	default short useWith(LByteToShortFunction accessFunction) {
		byte tuple = accessByte();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R> R useWith(byte a2, LBiByteFunction<R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.doApply(tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <R> R useWith(byte a1, LBiByteFunction.V1<R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunction<T1, T2, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunction.V1<T1, T2, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjByteFunction.V4<T1, T2, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseByte(tuple);
		return retval;
	}

	default <R> R useWith(LByteFunction<R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.doApply(tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjByteFunction<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.doApply(a1, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjByteFunction.V1<T, R> accessFunction) {
		byte tuple = accessByte();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default boolean useWith(byte a2, LBiBytePredicate accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default boolean useWith(byte a1, LBiBytePredicate.V1 accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseByte(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicate<T1, T2> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicate.V1<T1, T2> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseByte(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjBytePredicate.V4<T1, T2> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseByte(tuple);
		return retval;
	}

	default boolean useWith(LBytePredicate accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.doTest(tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBytePredicate<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseByte(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjBytePredicate.V1<T> accessFunction) {
		byte tuple = accessByte();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseByte(tuple);
		return retval;
	}

}
