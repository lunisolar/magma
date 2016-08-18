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
public interface AccessLongPairX<X extends Throwable> {

	static <X extends Throwable> AccessLongPairX<X> wrap(AccessLongPairX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LLongPair accessLongPairX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseLongPairX(LLongPair a) throws X {
		// NOOP
	}

	default void useWith(LBiLongConsumerX<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiLongConsumerX<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		accessFunction.doAccept(tuple.second(), tuple.first());
	}

	default void useWith(LBiLongConsumerX.V1<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		accessFunction.doAcceptV1(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiLongConsumerX.V1<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		accessFunction.doAcceptV1(tuple.second(), tuple.first());
	}

	default long useWith(LLongBinaryOperatorX<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		long retval = accessFunction.doApplyAsLong(tuple.first(), tuple.second());
		releaseLongPairX(tuple);
		return retval;
	}

	default long useWithO1(LLongBinaryOperatorX<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		long retval = accessFunction.doApplyAsLong(tuple.second(), tuple.first());
		releaseLongPairX(tuple);
		return retval;
	}

	default <R> R useWith(LBiLongFunctionX<R, X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releaseLongPairX(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiLongFunctionX<R, X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		R retval = accessFunction.doApply(tuple.second(), tuple.first());
		releaseLongPairX(tuple);
		return retval;
	}

	default <R> R useWith(LBiLongFunctionX.V1<R, X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		R retval = accessFunction.doApplyV1(tuple.first(), tuple.second());
		releaseLongPairX(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiLongFunctionX.V1<R, X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		R retval = accessFunction.doApplyV1(tuple.second(), tuple.first());
		releaseLongPairX(tuple);
		return retval;
	}

	default boolean useWith(LBiLongPredicateX<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseLongPairX(tuple);
		return retval;
	}

	default boolean useWithO1(LBiLongPredicateX<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		boolean retval = accessFunction.doTest(tuple.second(), tuple.first());
		releaseLongPairX(tuple);
		return retval;
	}

	default boolean useWith(LBiLongPredicateX.V1<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		boolean retval = accessFunction.doTestV1(tuple.first(), tuple.second());
		releaseLongPairX(tuple);
		return retval;
	}

	default boolean useWithO1(LBiLongPredicateX.V1<X> accessFunction) throws X {
		LLongPair tuple = accessLongPairX();
		boolean retval = accessFunction.doTestV1(tuple.second(), tuple.first());
		releaseLongPairX(tuple);
		return retval;
	}

}
