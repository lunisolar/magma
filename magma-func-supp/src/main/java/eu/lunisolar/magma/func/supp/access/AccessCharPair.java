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
public interface AccessCharPair {

	static AccessCharPair wrap(AccessCharPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LCharPair accessCharPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseCharPair(LCharPair a) {
		// NOOP
	}

	default void useWith(LBiCharConsumer accessFunction) {
		LCharPair tuple = accessCharPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiCharConsumer accessFunction) {
		LCharPair tuple = accessCharPair();
		accessFunction.accept(tuple.second(), tuple.first());
	}

	default void useWith(LBiCharConsumer.LChar1Char0Cons accessFunction) {
		LCharPair tuple = accessCharPair();
		accessFunction.acceptChar1Char0(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiCharConsumer.LChar1Char0Cons accessFunction) {
		LCharPair tuple = accessCharPair();
		accessFunction.acceptChar1Char0(tuple.second(), tuple.first());
	}

	default char useWith(LCharBinaryOperator accessFunction) {
		LCharPair tuple = accessCharPair();
		char retval = accessFunction.applyAsChar(tuple.first(), tuple.second());
		releaseCharPair(tuple);
		return retval;
	}

	default char useWithO1(LCharBinaryOperator accessFunction) {
		LCharPair tuple = accessCharPair();
		char retval = accessFunction.applyAsChar(tuple.second(), tuple.first());
		releaseCharPair(tuple);
		return retval;
	}

	default <R> R useWith(LBiCharFunction<R> accessFunction) {
		LCharPair tuple = accessCharPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseCharPair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiCharFunction<R> accessFunction) {
		LCharPair tuple = accessCharPair();
		R retval = accessFunction.apply(tuple.second(), tuple.first());
		releaseCharPair(tuple);
		return retval;
	}

	default <R> R useWith(LBiCharFunction.LChar1Char0Func<R> accessFunction) {
		LCharPair tuple = accessCharPair();
		R retval = accessFunction.applyChar1Char0(tuple.first(), tuple.second());
		releaseCharPair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiCharFunction.LChar1Char0Func<R> accessFunction) {
		LCharPair tuple = accessCharPair();
		R retval = accessFunction.applyChar1Char0(tuple.second(), tuple.first());
		releaseCharPair(tuple);
		return retval;
	}

	default boolean useWith(LBiCharPredicate accessFunction) {
		LCharPair tuple = accessCharPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseCharPair(tuple);
		return retval;
	}

	default boolean useWithO1(LBiCharPredicate accessFunction) {
		LCharPair tuple = accessCharPair();
		boolean retval = accessFunction.test(tuple.second(), tuple.first());
		releaseCharPair(tuple);
		return retval;
	}

	default boolean useWith(LBiCharPredicate.LChar1Char0Pred accessFunction) {
		LCharPair tuple = accessCharPair();
		boolean retval = accessFunction.testChar1Char0(tuple.first(), tuple.second());
		releaseCharPair(tuple);
		return retval;
	}

	default boolean useWithO1(LBiCharPredicate.LChar1Char0Pred accessFunction) {
		LCharPair tuple = accessCharPair();
		boolean retval = accessFunction.testChar1Char0(tuple.second(), tuple.first());
		releaseCharPair(tuple);
		return retval;
	}

}
