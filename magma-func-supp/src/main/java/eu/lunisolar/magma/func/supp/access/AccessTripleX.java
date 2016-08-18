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
public interface AccessTripleX<T1, T2, T3, X extends Throwable> {

	static <T1, T2, T3, X extends Throwable> AccessTripleX<T1, T2, T3, X> wrap(AccessTripleX<T1, T2, T3, X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LTriple<T1, T2, T3> accessTripleX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseTripleX(LTriple<T1, T2, T3> a) throws X {
		// NOOP
	}

	default void useWith(LTriConsumerX<T1, T2, T3, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTriConsumerX.V1<T1, T3, T2, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		accessFunction.doAcceptV1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTriConsumerX.V2<T2, T1, T3, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTriConsumerX.V3<T2, T3, T1, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		accessFunction.doAcceptV3(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTriConsumerX.V4<T3, T1, T2, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		accessFunction.doAcceptV4(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTriConsumerX.V5<T3, T2, T1, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		accessFunction.doAcceptV5(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LTriFunctionX<T1, T2, T3, R, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunctionX.V1<T1, T3, T2, R, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		R retval = accessFunction.doApplyV1(tuple.first(), tuple.third(), tuple.second());
		releaseTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunctionX.V2<T2, T1, T3, R, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), tuple.third());
		releaseTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunctionX.V3<T2, T3, T1, R, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		R retval = accessFunction.doApplyV3(tuple.second(), tuple.third(), tuple.first());
		releaseTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunctionX.V4<T3, T1, T2, R, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		R retval = accessFunction.doApplyV4(tuple.third(), tuple.first(), tuple.second());
		releaseTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunctionX.V5<T3, T2, T1, R, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		R retval = accessFunction.doApplyV5(tuple.third(), tuple.second(), tuple.first());
		releaseTripleX(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicateX<T1, T2, T3, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseTripleX(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicateX.V1<T1, T3, T2, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		boolean retval = accessFunction.doTestV1(tuple.first(), tuple.third(), tuple.second());
		releaseTripleX(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicateX.V2<T2, T1, T3, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), tuple.third());
		releaseTripleX(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicateX.V3<T2, T3, T1, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		boolean retval = accessFunction.doTestV3(tuple.second(), tuple.third(), tuple.first());
		releaseTripleX(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicateX.V4<T3, T1, T2, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		boolean retval = accessFunction.doTestV4(tuple.third(), tuple.first(), tuple.second());
		releaseTripleX(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicateX.V5<T3, T2, T1, X> accessFunction) throws X {
		LTriple<T1, T2, T3> tuple = accessTripleX();
		boolean retval = accessFunction.doTestV5(tuple.third(), tuple.second(), tuple.first());
		releaseTripleX(tuple);
		return retval;
	}

}
