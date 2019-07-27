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
public interface AccessBytePair {

	static AccessBytePair wrap(AccessBytePair lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBytePair accessBytePair();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBytePair(LBytePair a) {
		// NOOP
	}

	default void useWith(LBiByteConsumer accessFunction) {
		LBytePair tuple = accessBytePair();
		accessFunction.accept(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiByteConsumer accessFunction) {
		LBytePair tuple = accessBytePair();
		accessFunction.accept(tuple.second(), tuple.first());
	}

	default void useWith(LBiByteConsumer.LByte1Byte0Cons accessFunction) {
		LBytePair tuple = accessBytePair();
		accessFunction.acceptByte1Byte0(tuple.first(), tuple.second());
	}

	default void useWithO1(LBiByteConsumer.LByte1Byte0Cons accessFunction) {
		LBytePair tuple = accessBytePair();
		accessFunction.acceptByte1Byte0(tuple.second(), tuple.first());
	}

	default byte useWith(LByteBinaryOperator accessFunction) {
		LBytePair tuple = accessBytePair();
		byte retval = accessFunction.applyAsByte(tuple.first(), tuple.second());
		releaseBytePair(tuple);
		return retval;
	}

	default byte useWithO1(LByteBinaryOperator accessFunction) {
		LBytePair tuple = accessBytePair();
		byte retval = accessFunction.applyAsByte(tuple.second(), tuple.first());
		releaseBytePair(tuple);
		return retval;
	}

	default <R> R useWith(LBiByteFunction<R> accessFunction) {
		LBytePair tuple = accessBytePair();
		R retval = accessFunction.apply(tuple.first(), tuple.second());
		releaseBytePair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiByteFunction<R> accessFunction) {
		LBytePair tuple = accessBytePair();
		R retval = accessFunction.apply(tuple.second(), tuple.first());
		releaseBytePair(tuple);
		return retval;
	}

	default <R> R useWith(LBiByteFunction.LByte1Byte0Func<R> accessFunction) {
		LBytePair tuple = accessBytePair();
		R retval = accessFunction.applyByte1Byte0(tuple.first(), tuple.second());
		releaseBytePair(tuple);
		return retval;
	}

	default <R> R useWithO1(LBiByteFunction.LByte1Byte0Func<R> accessFunction) {
		LBytePair tuple = accessBytePair();
		R retval = accessFunction.applyByte1Byte0(tuple.second(), tuple.first());
		releaseBytePair(tuple);
		return retval;
	}

	default boolean useWith(LBiBytePredicate accessFunction) {
		LBytePair tuple = accessBytePair();
		boolean retval = accessFunction.test(tuple.first(), tuple.second());
		releaseBytePair(tuple);
		return retval;
	}

	default boolean useWithO1(LBiBytePredicate accessFunction) {
		LBytePair tuple = accessBytePair();
		boolean retval = accessFunction.test(tuple.second(), tuple.first());
		releaseBytePair(tuple);
		return retval;
	}

	default boolean useWith(LBiBytePredicate.LByte1Byte0Pred accessFunction) {
		LBytePair tuple = accessBytePair();
		boolean retval = accessFunction.testByte1Byte0(tuple.first(), tuple.second());
		releaseBytePair(tuple);
		return retval;
	}

	default boolean useWithO1(LBiBytePredicate.LByte1Byte0Pred accessFunction) {
		LBytePair tuple = accessBytePair();
		boolean retval = accessFunction.testByte1Byte0(tuple.second(), tuple.first());
		releaseBytePair(tuple);
		return retval;
	}

}