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
public interface AccessLongPair {

	static AccessLongPair wrap(AccessLongPair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LLongPair accessLongPair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseLongPair(LLongPair a) {
		// NOOP
	}

	default void useWith(LBiLongConsumer accessFunction) {
		LLongPair tuple = accessLongPair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiLongConsumer accessFunction) {
		LLongPair tuple = accessLongPair();
		accessFunction.accept(tuple.second(), tuple.first());
	}

	default void useWith(LBiLongConsumer.LLong1Long0Cons accessFunction) {
		LLongPair tuple = accessLongPair();
		accessFunction.acceptLong1Long0(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiLongConsumer.LLong1Long0Cons accessFunction) {
		LLongPair tuple = accessLongPair();
		accessFunction.acceptLong1Long0(tuple.second(), tuple.first());
	}

	default void useWith(long a3, LTriLongConsumer accessFunction) {
		LLongPair tuple = accessLongPair();
		accessFunction.accept(tuple.first(), tuple.second(), a3);
	}

	default void useWithO1(long a3, LTriLongConsumer accessFunction) {
		LLongPair tuple = accessLongPair();
		accessFunction.accept(tuple.second(), tuple.first(), a3);
	}

	default long useWith(LLongBinaryOperator accessFunction) {
		LLongPair tuple = accessLongPair();
		long retval = accessFunction.applyAsLong(tuple.first(), tuple.second());
		releaseLongPair(tuple);
		return retval;
	}

	default long useWithO1(LLongBinaryOperator accessFunction) {
		LLongPair tuple = accessLongPair();
		long retval = accessFunction.applyAsLong(tuple.second(), tuple.first());
		releaseLongPair(tuple);
		return retval;
	}

	default <R> R useWith(LBiLongFunction<R> accessFunction) {
		LLongPair tuple = accessLongPair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseLongPair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiLongFunction<R> accessFunction) {
		LLongPair tuple = accessLongPair();
		R retval = accessFunction.apply(tuple.second(), tuple.first());
		releaseLongPair(tuple);
		return retval;
	}

	default <R> R useWith(LBiLongFunction.LLong1Long0Func<R> accessFunction) {
		LLongPair tuple = accessLongPair();
		R retval = accessFunction.applyLong1Long0(tuple.first(), tuple.second());
		releaseLongPair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiLongFunction.LLong1Long0Func<R> accessFunction) {
		LLongPair tuple = accessLongPair();
		R retval = accessFunction.applyLong1Long0(tuple.second(), tuple.first());
		releaseLongPair(tuple);
		return retval;
	}

	default boolean useWith(LBiLongPredicate accessFunction) {
		LLongPair tuple = accessLongPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseLongPair(tuple);
		return retval;
	}

	default boolean useWithO1(LBiLongPredicate accessFunction) {
		LLongPair tuple = accessLongPair();
		boolean retval = accessFunction.test(tuple.second(), tuple.first());
		releaseLongPair(tuple);
		return retval;
	}

	default boolean useWith(LBiLongPredicate.LLong1Long0Pred accessFunction) {
		LLongPair tuple = accessLongPair();
		boolean retval = accessFunction.testLong1Long0(tuple.first(), tuple.second());
		releaseLongPair(tuple);
		return retval;
	}

	default boolean useWithO1(LBiLongPredicate.LLong1Long0Pred accessFunction) {
		LLongPair tuple = accessLongPair();
		boolean retval = accessFunction.testLong1Long0(tuple.second(), tuple.first());
		releaseLongPair(tuple);
		return retval;
	}

	default boolean useWith(long a3, LTriLongPredicate accessFunction) {
		LLongPair tuple = accessLongPair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), a3);
		releaseLongPair(tuple);
		return retval;
	}

	default boolean useWithO1(long a3, LTriLongPredicate accessFunction) {
		LLongPair tuple = accessLongPair();
		boolean retval = accessFunction.test(tuple.second(), tuple.first(), a3);
		releaseLongPair(tuple);
		return retval;
	}

}
