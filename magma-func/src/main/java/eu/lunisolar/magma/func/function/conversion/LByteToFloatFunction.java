/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.function.conversion;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR

import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/**
 * Non-throwing functional interface (lambda) LByteToFloatFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte a1
 *
 * Co-domain: float
 *
 * @see LByteToFloatFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteToFloatFunction extends LByteToFloatFunctionX<RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LByteToFloatFunction: float doApplyAsFloat(byte a1)";

	float doApplyAsFloat(byte a1);

	default Float tupleApplyAsFloat(LByteSingle args) {
		return doApplyAsFloat(args.first());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoApplyAsFloat(byte a1) {
		return this.doApplyAsFloat(a1);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default float shovingDoApplyAsFloat(byte a1) {
		return this.doApplyAsFloat(a1);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(byte a1) {
		return doApplyAsFloat(a1);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteToFloatFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplier captureByteToFloatFunc(byte a1) {
		return () -> this.doApplyAsFloat(a1);
	}

	/** Creates function that always returns the same value. */
	static LByteToFloatFunction constant(float r) {
		return a1 -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteToFloatFunction l(final @Nonnull LByteToFloatFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static float call(byte a1, final @Nonnull LByteToFloatFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsFloat(a1);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LByteToFloatFunction wrap(final @Nonnull LByteToFloatFunctionX<X> other) {
		return other::nestingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static LByteToFloatFunction safe() {
		return Function4U::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteToFloatFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LByteToFloatFunction safe(final @Nullable LByteToFloatFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteToFloatFunction> safeSupplier(final @Nullable LSupplier<LByteToFloatFunction> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LByteToFloatFunction byteToFloatFuncComposeByte(@Nonnull final LByteUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsFloat(before1.doApplyAsByte(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToFloatFunction<V1> byteToFloatFuncCompose(@Nonnull final LToByteFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsFloat(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LFloatFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doApplyAsFloat(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LFloatToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsByte(this.doApplyAsFloat(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToShortFunction thenToShort(@Nonnull LFloatToShortFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsShort(this.doApplyAsFloat(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LFloatToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsInt(this.doApplyAsFloat(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LFloatToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsLong(this.doApplyAsFloat(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFloatFunction thenToFloat(@Nonnull LFloatUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsFloat(this.doApplyAsFloat(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDoubleFunction thenToDouble(@Nonnull LFloatToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsDouble(this.doApplyAsFloat(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LFloatToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsChar(this.doApplyAsFloat(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate thenToBool(@Nonnull LFloatPredicate after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doTest(this.doApplyAsFloat(a1));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteToFloatFunction nestingByteToFloatFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteToFloatFunctionX<RuntimeException> nestingByteToFloatFuncX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToFloatFunction shovingByteToFloatFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToFloatFunctionX<RuntimeException> shovingByteToFloatFuncX() {
		return this;
	}

	// </editor-fold>

}
