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
public interface AccessBoolTripleX<X extends Throwable> {

	static <X extends Throwable> AccessBoolTripleX<X> wrap(AccessBoolTripleX<X> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBoolTriple accessBoolTripleX() throws X;

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBoolTripleX(LBoolTriple a) throws X {
		// NOOP
	}

	default void useWith(LTriBoolConsumerX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWithO1(LTriBoolConsumerX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		accessFunction.doAccept(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWithO2(LTriBoolConsumerX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		accessFunction.doAccept(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWithO3(LTriBoolConsumerX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		accessFunction.doAccept(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWithO4(LTriBoolConsumerX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		accessFunction.doAccept(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWithO5(LTriBoolConsumerX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		accessFunction.doAccept(tuple.third(), tuple.second(), tuple.first());
	}

	default boolean useWith(LLogicalTernaryOperatorX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		boolean retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default boolean useWithO1(LLogicalTernaryOperatorX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		boolean retval = accessFunction.doApply(tuple.second(), tuple.first(), tuple.third());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default boolean useWithO2(LLogicalTernaryOperatorX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		boolean retval = accessFunction.doApply(tuple.first(), tuple.third(), tuple.second());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default boolean useWithO3(LLogicalTernaryOperatorX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		boolean retval = accessFunction.doApply(tuple.third(), tuple.first(), tuple.second());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default boolean useWithO4(LLogicalTernaryOperatorX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		boolean retval = accessFunction.doApply(tuple.second(), tuple.third(), tuple.first());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default boolean useWithO5(LLogicalTernaryOperatorX<X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		boolean retval = accessFunction.doApply(tuple.third(), tuple.second(), tuple.first());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default <R> R useWith(LTriBoolFunctionX<R, X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default <R> R useWithO1(LTriBoolFunctionX<R, X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		R retval = accessFunction.doApply(tuple.second(), tuple.first(), tuple.third());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default <R> R useWithO2(LTriBoolFunctionX<R, X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		R retval = accessFunction.doApply(tuple.first(), tuple.third(), tuple.second());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default <R> R useWithO3(LTriBoolFunctionX<R, X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		R retval = accessFunction.doApply(tuple.third(), tuple.first(), tuple.second());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default <R> R useWithO4(LTriBoolFunctionX<R, X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		R retval = accessFunction.doApply(tuple.second(), tuple.third(), tuple.first());
		releaseBoolTripleX(tuple);
		return retval;
	}

	default <R> R useWithO5(LTriBoolFunctionX<R, X> accessFunction) throws X {
		LBoolTriple tuple = accessBoolTripleX();
		R retval = accessFunction.doApply(tuple.third(), tuple.second(), tuple.first());
		releaseBoolTripleX(tuple);
		return retval;
	}

}
