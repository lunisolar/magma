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
public interface AccessObjIntPairX<T, X extends Throwable> {

	static <T, X extends Throwable> AccessObjIntPairX<T, X> wrap(AccessObjIntPairX<T, X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjIntPair<T> accessObjIntPairX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjIntPairX(LObjIntPair<T> a) throws X {
		// NOOP
	}

	default void useWith(LObjIntConsumerX<T, X> accessFunction) throws X {
		LObjIntPair<T> tuple = accessObjIntPairX();
		accessFunction.doAccept(tuple.first(), tuple.second());
	}

	default void useWith(LObjIntConsumerX.V1<T, X> accessFunction) throws X {
		LObjIntPair<T> tuple = accessObjIntPairX();
		accessFunction.doAcceptV1(tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjIntFunctionX<T, R, X> accessFunction) throws X {
		LObjIntPair<T> tuple = accessObjIntPairX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second());
		releaseObjIntPairX(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntFunctionX.V1<T, R, X> accessFunction) throws X {
		LObjIntPair<T> tuple = accessObjIntPairX();
		R retval = accessFunction.doApplyV1(tuple.second(), tuple.first());
		releaseObjIntPairX(tuple);
		return retval;
	}

	default int useWith(LObjIntToIntFunctionX<T, X> accessFunction) throws X {
		LObjIntPair<T> tuple = accessObjIntPairX();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second());
		releaseObjIntPairX(tuple);
		return retval;
	}

	default int useWith(LObjIntToIntFunctionX.V1<T, X> accessFunction) throws X {
		LObjIntPair<T> tuple = accessObjIntPairX();
		int retval = accessFunction.doApplyAsIntV1(tuple.second(), tuple.first());
		releaseObjIntPairX(tuple);
		return retval;
	}

	default boolean useWith(LObjIntPredicateX<T, X> accessFunction) throws X {
		LObjIntPair<T> tuple = accessObjIntPairX();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second());
		releaseObjIntPairX(tuple);
		return retval;
	}

	default boolean useWith(LObjIntPredicateX.V1<T, X> accessFunction) throws X {
		LObjIntPair<T> tuple = accessObjIntPairX();
		boolean retval = accessFunction.doTestV1(tuple.second(), tuple.first());
		releaseObjIntPairX(tuple);
		return retval;
	}

}
