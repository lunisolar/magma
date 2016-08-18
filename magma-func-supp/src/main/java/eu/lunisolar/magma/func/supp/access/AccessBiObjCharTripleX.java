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
public interface AccessBiObjCharTripleX<T1, T2, X extends Throwable> {

	static <T1, T2, X extends Throwable> AccessBiObjCharTripleX<T1, T2, X> wrap(AccessBiObjCharTripleX<T1, T2, X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBiObjCharTriple<T1, T2> accessBiObjCharTripleX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBiObjCharTripleX(LBiObjCharTriple<T1, T2> a) throws X {
		// NOOP
	}

	default void useWith(LBiObjCharConsumerX<T1, T2, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjCharConsumerX.V1<T1, T2, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		accessFunction.doAcceptV1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjCharConsumerX.V2<T2, T1, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		accessFunction.doAcceptV2(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjCharConsumerX.V3<T2, T1, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		accessFunction.doAcceptV3(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjCharConsumerX.V4<T1, T2, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		accessFunction.doAcceptV4(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjCharConsumerX.V5<T2, T1, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		accessFunction.doAcceptV5(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiObjCharFunctionX<T1, T2, R, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjCharFunctionX.V1<T1, T2, R, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		R retval = accessFunction.doApplyV1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjCharFunctionX.V2<T2, T1, R, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		R retval = accessFunction.doApplyV2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjCharFunctionX.V3<T2, T1, R, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		R retval = accessFunction.doApplyV3(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjCharFunctionX.V4<T1, T2, R, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		R retval = accessFunction.doApplyV4(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjCharFunctionX.V5<T2, T1, R, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		R retval = accessFunction.doApplyV5(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default boolean useWith(LBiObjCharPredicateX<T1, T2, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default boolean useWith(LBiObjCharPredicateX.V1<T1, T2, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		boolean retval = accessFunction.doTestV1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default boolean useWith(LBiObjCharPredicateX.V2<T2, T1, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		boolean retval = accessFunction.doTestV2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default boolean useWith(LBiObjCharPredicateX.V3<T2, T1, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		boolean retval = accessFunction.doTestV3(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default boolean useWith(LBiObjCharPredicateX.V4<T1, T2, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		boolean retval = accessFunction.doTestV4(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

	default boolean useWith(LBiObjCharPredicateX.V5<T2, T1, X> accessFunction) throws X {
		LBiObjCharTriple<T1, T2> tuple = accessBiObjCharTripleX();
		boolean retval = accessFunction.doTestV5(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjCharTripleX(tuple);
		return retval;
	}

}
