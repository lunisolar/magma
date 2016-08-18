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
public interface AccessDouble {

	static AccessDouble wrap(AccessDouble lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	double accessDouble();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseDouble(double a) {
		// NOOP
	}

	default void useWith(LDoubleConsumer accessFunction) {
		double tuple = accessDouble();
		accessFunction.doAccept(tuple);
	}

	default void useWith(double a2, LBiDoubleConsumer accessFunction) {
		double tuple = accessDouble();
		accessFunction.doAccept(tuple, a2);
	}

	default void useWith(double a1, LBiDoubleConsumer.V1 accessFunction) {
		double tuple = accessDouble();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjDoubleConsumer<T1, T2> accessFunction) {
		double tuple = accessDouble();
		accessFunction.doAccept(a1, a2, tuple);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjDoubleConsumer.V1<T1, T2> accessFunction) {
		double tuple = accessDouble();
		accessFunction.doAcceptV1(a1, tuple, a2);
	}

	default <T1, T2> void useWith(T1 a1, T2 a2, LBiObjDoubleConsumer.V4<T1, T2> accessFunction) {
		double tuple = accessDouble();
		accessFunction.doAcceptV4(tuple, a1, a2);
	}

	default <T> void useWith(T a1, LObjDoubleConsumer<T> accessFunction) {
		double tuple = accessDouble();
		accessFunction.doAccept(a1, tuple);
	}

	default <T> void useWith(T a1, LObjDoubleConsumer.V1<T> accessFunction) {
		double tuple = accessDouble();
		accessFunction.doAcceptV1(tuple, a1);
	}

	default double useWith(double a2, LDoubleBinaryOperator accessFunction) {
		double tuple = accessDouble();
		double retval = accessFunction.doApplyAsDouble(tuple, a2);
		releaseDouble(tuple);
		return retval;
	}

	default double useWith(LDoubleUnaryOperator accessFunction) {
		double tuple = accessDouble();
		double retval = accessFunction.doApplyAsDouble(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default byte useWith(LDoubleToByteFunction accessFunction) {
		double tuple = accessDouble();
		byte retval = accessFunction.doApplyAsByte(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default char useWith(LDoubleToCharFunction accessFunction) {
		double tuple = accessDouble();
		char retval = accessFunction.doApplyAsChar(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default float useWith(LDoubleToFloatFunction accessFunction) {
		double tuple = accessDouble();
		float retval = accessFunction.doApplyAsFloat(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default int useWith(LDoubleToIntFunction accessFunction) {
		double tuple = accessDouble();
		int retval = accessFunction.doApplyAsInt(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default long useWith(LDoubleToLongFunction accessFunction) {
		double tuple = accessDouble();
		long retval = accessFunction.doApplyAsLong(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default short useWith(LDoubleToShortFunction accessFunction) {
		double tuple = accessDouble();
		short retval = accessFunction.doApplyAsShort(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default <R> R useWith(double a2, LBiDoubleFunction<R> accessFunction) {
		double tuple = accessDouble();
		R retval = accessFunction.doApply(tuple, a2);
		releaseDouble(tuple);
		return retval;
	}

	default <R> R useWith(double a1, LBiDoubleFunction.V1<R> accessFunction) {
		double tuple = accessDouble();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseDouble(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjDoubleFunction<T1, T2, R> accessFunction) {
		double tuple = accessDouble();
		R retval = accessFunction.doApply(a1, a2, tuple);
		releaseDouble(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjDoubleFunction.V1<T1, T2, R> accessFunction) {
		double tuple = accessDouble();
		R retval = accessFunction.doApplyV1(a1, tuple, a2);
		releaseDouble(tuple);
		return retval;
	}

	default <R, T1, T2> R useWith(T1 a1, T2 a2, LBiObjDoubleFunction.V4<T1, T2, R> accessFunction) {
		double tuple = accessDouble();
		R retval = accessFunction.doApplyV4(tuple, a1, a2);
		releaseDouble(tuple);
		return retval;
	}

	default <R> R useWith(LDoubleFunction<R> accessFunction) {
		double tuple = accessDouble();
		R retval = accessFunction.doApply(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjDoubleFunction<T, R> accessFunction) {
		double tuple = accessDouble();
		R retval = accessFunction.doApply(a1, tuple);
		releaseDouble(tuple);
		return retval;
	}

	default <R, T> R useWith(T a1, LObjDoubleFunction.V1<T, R> accessFunction) {
		double tuple = accessDouble();
		R retval = accessFunction.doApplyV1(tuple, a1);
		releaseDouble(tuple);
		return retval;
	}

	default boolean useWith(double a2, LBiDoublePredicate accessFunction) {
		double tuple = accessDouble();
		boolean retval = accessFunction.doTest(tuple, a2);
		releaseDouble(tuple);
		return retval;
	}

	default boolean useWith(double a1, LBiDoublePredicate.V1 accessFunction) {
		double tuple = accessDouble();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseDouble(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjDoublePredicate<T1, T2> accessFunction) {
		double tuple = accessDouble();
		boolean retval = accessFunction.doTest(a1, a2, tuple);
		releaseDouble(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjDoublePredicate.V1<T1, T2> accessFunction) {
		double tuple = accessDouble();
		boolean retval = accessFunction.doTestV1(a1, tuple, a2);
		releaseDouble(tuple);
		return retval;
	}

	default <T1, T2> boolean useWith(T1 a1, T2 a2, LBiObjDoublePredicate.V4<T1, T2> accessFunction) {
		double tuple = accessDouble();
		boolean retval = accessFunction.doTestV4(tuple, a1, a2);
		releaseDouble(tuple);
		return retval;
	}

	default boolean useWith(LDoublePredicate accessFunction) {
		double tuple = accessDouble();
		boolean retval = accessFunction.doTest(tuple);
		releaseDouble(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjDoublePredicate<T> accessFunction) {
		double tuple = accessDouble();
		boolean retval = accessFunction.doTest(a1, tuple);
		releaseDouble(tuple);
		return retval;
	}

	default <T> boolean useWith(T a1, LObjDoublePredicate.V1<T> accessFunction) {
		double tuple = accessDouble();
		boolean retval = accessFunction.doTestV1(tuple, a1);
		releaseDouble(tuple);
		return retval;
	}

}
