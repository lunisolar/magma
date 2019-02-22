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
public interface AccessBoolPair {

	static AccessBoolPair wrap(AccessBoolPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBoolPair accessBoolPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBoolPair(LBoolPair a) {
		// NOOP
	}

	default void useWith(LBiBoolConsumer accessFunction) {
		LBoolPair tuple = accessBoolPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiBoolConsumer accessFunction) {
		LBoolPair tuple = accessBoolPair();
		accessFunction.accept(tuple.second(), tuple.first());
	}

	default void useWith(LBiBoolConsumer.LBool1Bool0Cons accessFunction) {
		LBoolPair tuple = accessBoolPair();
		accessFunction.acceptBool1Bool0(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiBoolConsumer.LBool1Bool0Cons accessFunction) {
		LBoolPair tuple = accessBoolPair();
		accessFunction.acceptBool1Bool0(tuple.second(), tuple.first());
	}

	default void useWith(boolean a3, LTriBoolConsumer accessFunction) {
		LBoolPair tuple = accessBoolPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWithO1(boolean a3, LTriBoolConsumer accessFunction) {
		LBoolPair tuple = accessBoolPair();
		accessFunction.accept(tuple.second(), tuple.first(), a3);
	}

	default boolean useWith(LLogicalBinaryOperator accessFunction) {
		LBoolPair tuple = accessBoolPair();
		boolean retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseBoolPair(tuple);
		return retval;
	}

	default boolean useWithO1(LLogicalBinaryOperator accessFunction) {
		LBoolPair tuple = accessBoolPair();
		boolean retval = accessFunction.apply(tuple.second(), tuple.first());
		releaseBoolPair(tuple);
		return retval;
	}

	default boolean useWith(boolean a3, LLogicalTernaryOperator accessFunction) {
		LBoolPair tuple = accessBoolPair();
		boolean retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseBoolPair(tuple);
		return retval;
	}

	default boolean useWithO1(boolean a3, LLogicalTernaryOperator accessFunction) {
		LBoolPair tuple = accessBoolPair();
		boolean retval = accessFunction.apply(tuple.second(), tuple.first(), a3);
		releaseBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(LBiBoolFunction<R> accessFunction) {
		LBoolPair tuple = accessBoolPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseBoolPair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiBoolFunction<R> accessFunction) {
		LBoolPair tuple = accessBoolPair();
		R retval = accessFunction.apply(tuple.second(), tuple.first());
		releaseBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(LBiBoolFunction.LBool1Bool0Func<R> accessFunction) {
		LBoolPair tuple = accessBoolPair();
		R retval = accessFunction.applyBool1Bool0(tuple.first(), tuple.second());
		releaseBoolPair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiBoolFunction.LBool1Bool0Func<R> accessFunction) {
		LBoolPair tuple = accessBoolPair();
		R retval = accessFunction.applyBool1Bool0(tuple.second(), tuple.first());
		releaseBoolPair(tuple);
		return retval;
	}

	default <R> R useWith(boolean a3, LTriBoolFunction<R> accessFunction) {
		LBoolPair tuple = accessBoolPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), a3);
		releaseBoolPair(tuple);
		return retval;
	}

	default <R> R useWithO1(boolean a3, LTriBoolFunction<R> accessFunction) {
		LBoolPair tuple = accessBoolPair();
		R retval = accessFunction.apply(tuple.second(), tuple.first(), a3);
		releaseBoolPair(tuple);
		return retval;
	}

}
