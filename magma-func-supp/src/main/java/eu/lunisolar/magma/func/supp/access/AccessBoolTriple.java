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
public interface AccessBoolTriple {

	static AccessBoolTriple wrap(AccessBoolTriple lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBoolTriple accessBoolTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBoolTriple(LBoolTriple a) {
		// NOOP
	}

	default void useWith(LTriBoolConsumer accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWithO1(LTriBoolConsumer accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		accessFunction.accept(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWithO2(LTriBoolConsumer accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		accessFunction.accept(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWithO3(LTriBoolConsumer accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		accessFunction.accept(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWithO4(LTriBoolConsumer accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		accessFunction.accept(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWithO5(LTriBoolConsumer accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		accessFunction.accept(tuple.third(), tuple.second(), tuple.first());
	}

	default boolean useWith(LLogicalTernaryOperator accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		boolean retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseBoolTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LLogicalTernaryOperator accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		boolean retval = accessFunction.apply(tuple.second(), tuple.first(), tuple.third());
		releaseBoolTriple(tuple);
		return retval;
	}

	default boolean useWithO2(LLogicalTernaryOperator accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		boolean retval = accessFunction.apply(tuple.first(), tuple.third(), tuple.second());
		releaseBoolTriple(tuple);
		return retval;
	}

	default boolean useWithO3(LLogicalTernaryOperator accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		boolean retval = accessFunction.apply(tuple.third(), tuple.first(), tuple.second());
		releaseBoolTriple(tuple);
		return retval;
	}

	default boolean useWithO4(LLogicalTernaryOperator accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		boolean retval = accessFunction.apply(tuple.second(), tuple.third(), tuple.first());
		releaseBoolTriple(tuple);
		return retval;
	}

	default boolean useWithO5(LLogicalTernaryOperator accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		boolean retval = accessFunction.apply(tuple.third(), tuple.second(), tuple.first());
		releaseBoolTriple(tuple);
		return retval;
	}

	default <R> R useWith(LTriBoolFunction<R> accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseBoolTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LTriBoolFunction<R> accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		R retval = accessFunction.apply(tuple.second(), tuple.first(), tuple.third());
		releaseBoolTriple(tuple);
		return retval;
	}

	default <R> R useWithO2(LTriBoolFunction<R> accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.third(), tuple.second());
		releaseBoolTriple(tuple);
		return retval;
	}

	default <R> R useWithO3(LTriBoolFunction<R> accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		R retval = accessFunction.apply(tuple.third(), tuple.first(), tuple.second());
		releaseBoolTriple(tuple);
		return retval;
	}

	default <R> R useWithO4(LTriBoolFunction<R> accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		R retval = accessFunction.apply(tuple.second(), tuple.third(), tuple.first());
		releaseBoolTriple(tuple);
		return retval;
	}

	default <R> R useWithO5(LTriBoolFunction<R> accessFunction) {
		LBoolTriple tuple = accessBoolTriple();
		R retval = accessFunction.apply(tuple.third(), tuple.second(), tuple.first());
		releaseBoolTriple(tuple);
		return retval;
	}

}
