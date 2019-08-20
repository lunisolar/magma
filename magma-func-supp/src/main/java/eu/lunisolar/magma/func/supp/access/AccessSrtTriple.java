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
public interface AccessSrtTriple {

	static AccessSrtTriple wrap(AccessSrtTriple lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LSrtTriple accessSrtTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseSrtTriple(LSrtTriple a) {
		// NOOP
	}

	default void useWith(LTriSrtConsumer accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWithO1(LTriSrtConsumer accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		accessFunction.accept(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWithO2(LTriSrtConsumer accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		accessFunction.accept(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWithO3(LTriSrtConsumer accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		accessFunction.accept(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWithO4(LTriSrtConsumer accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		accessFunction.accept(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWithO5(LTriSrtConsumer accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		accessFunction.accept(tuple.third(), tuple.second(), tuple.first());
	}

	default boolean useWith(LTriSrtPredicate accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseSrtTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LTriSrtPredicate accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		boolean retval = accessFunction.test(tuple.second(), tuple.first(), tuple.third());
		releaseSrtTriple(tuple);
		return retval;
	}

	default boolean useWithO2(LTriSrtPredicate accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.third(), tuple.second());
		releaseSrtTriple(tuple);
		return retval;
	}

	default boolean useWithO3(LTriSrtPredicate accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		boolean retval = accessFunction.test(tuple.third(), tuple.first(), tuple.second());
		releaseSrtTriple(tuple);
		return retval;
	}

	default boolean useWithO4(LTriSrtPredicate accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		boolean retval = accessFunction.test(tuple.second(), tuple.third(), tuple.first());
		releaseSrtTriple(tuple);
		return retval;
	}

	default boolean useWithO5(LTriSrtPredicate accessFunction) {
		LSrtTriple tuple = accessSrtTriple();
		boolean retval = accessFunction.test(tuple.third(), tuple.second(), tuple.first());
		releaseSrtTriple(tuple);
		return retval;
	}

}
