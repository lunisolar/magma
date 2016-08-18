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
public interface AccessChar {

	static AccessChar wrap(AccessChar lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	char accessChar();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseChar(char a) {
		// NOOP
	}

	default void useWith(LCharConsumer accessFunction) {
		char tuple = accessChar();
		accessFunction.doAccept(tuple);
	}

	default void useWith(char a2, LBiCharConsumer accessFunction) {
		char tuple = accessChar();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(char a1, LBiCharConsumer.V1 accessFunction) {
		char tuple = accessChar();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumer<T1, T2> accessFunction) {
		char tuple = accessChar();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumer.V1<T1, T2> accessFunction) {
		char tuple = accessChar();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjCharConsumer.V4<T1, T2> accessFunction) {
		char tuple = accessChar();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjCharConsumer<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjCharConsumer.V1<T> accessFunction) {
		char tuple = accessChar();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default char useWith(char a2, LCharBinaryOperator accessFunction) {
		char tuple = accessChar();
		char retval = accessFunction.doApplyAsChar(tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default char useWith(LCharUnaryOperator accessFunction) {
		char tuple = accessChar();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseChar(tuple);
		return retval;
	}

	default byte useWith(LCharToByteFunction accessFunction) {
		char tuple = accessChar();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseChar(tuple);
		return retval;
	}

	default double useWith(LCharToDoubleFunction accessFunction) {
		char tuple = accessChar();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseChar(tuple);
		return retval;
	}

	default float useWith(LCharToFloatFunction accessFunction) {
		char tuple = accessChar();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseChar(tuple);
		return retval;
	}

	default int useWith(LCharToIntFunction accessFunction) {
		char tuple = accessChar();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseChar(tuple);
		return retval;
	}

	default long useWith(LCharToLongFunction accessFunction) {
		char tuple = accessChar();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseChar(tuple);
		return retval;
	}

	default short useWith(LCharToShortFunction accessFunction) {
		char tuple = accessChar();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R> R useWith(char a2, LBiCharFunction<R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.doApply(tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <R> R useWith(char a1, LBiCharFunction.V1<R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunction<T1, T2, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunction.V1<T1, T2, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjCharFunction.V4<T1, T2, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseChar(tuple);
		return retval;
	}

	default <R> R useWith(LCharFunction<R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.doApply(tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjCharFunction<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.doApply(a1, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjCharFunction.V1<T, R> accessFunction) {
		char tuple = accessChar();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default boolean useWith(char a2, LBiCharPredicate accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default boolean useWith(char a1, LBiCharPredicate.V1 accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseChar(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicate<T1, T2> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicate.V1<T1, T2> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseChar(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjCharPredicate.V4<T1, T2> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseChar(tuple);
		return retval;
	}

	default boolean useWith(LCharPredicate accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.doTest(tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjCharPredicate<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseChar(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjCharPredicate.V1<T> accessFunction) {
		char tuple = accessChar();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseChar(tuple);
		return retval;
	}

}
