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
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

/**
 * Non-throwing functional interface (lambda) LCharToShortFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): char c
 *
 * Co-domain: none
 *
 * @see LCharToShortFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharToShortFunction extends LCharToShortFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LCharToShortFunction: short doApplyAsShort(char c)";

	short doApplyAsShort(char c);

	default short nestingDoApplyAsShort(char c) {
		return this.doApplyAsShort(c);
	}

	default short shovingDoApplyAsShort(char c) {
		return this.doApplyAsShort(c);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(char c) {
		return doApplyAsShort(c);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharToShortFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplier captureCToSFunc(char c) {
		return () -> this.doApplyAsShort(c);
	}

	static LCharToShortFunction constant(short r) {
		return c -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharToShortFunction l(final @Nonnull LCharToShortFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharToShortFunction wrap(final @Nonnull LCharToShortFunctionX<X> other) {
		return other::nestingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LCharToShortFunction cToSFuncFromChar(@Nonnull final LCharUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsShort(before1.doApplyAsChar(v1));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1> LToShortFunction<V1> cToSFuncFrom(@Nonnull final LToCharFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsShort(before1.doApplyAsChar(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunction<V> then(@Nonnull LShortFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApply(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunction thenToByte(@Nonnull LShortToByteFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsByte(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToShortFunction thenToShort(@Nonnull LShortUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsShort(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunction thenToInt(@Nonnull LShortToIntFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsInt(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunction thenToLong(@Nonnull LShortToLongFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsLong(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFloatFunction thenToFloat(@Nonnull LShortToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsFloat(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDoubleFunction thenToDouble(@Nonnull LShortToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsDouble(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperator thenToChar(@Nonnull LShortToCharFunction after) {
		Null.nonNullArg(after, "after");
		return c -> after.doApplyAsChar(this.doApplyAsShort(c));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicate thenToBoolean(@Nonnull LShortPredicate after) {
		Null.nonNullArg(after, "after");
		return c -> after.doTest(this.doApplyAsShort(c));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharToShortFunction nestingCToSFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharToShortFunctionX<RuntimeException> nestingCToSFuncX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToShortFunction shovingCToSFunc() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToShortFunctionX<RuntimeException> shovingCToSFuncX() {
		return this;
	}

	// </editor-fold>

}
