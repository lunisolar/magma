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
 * Non-throwing functional interface (lambda) LShortToFloatFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short s
 *
 * Co-domain: none
 *
 * @see LShortToFloatFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortToFloatFunction extends LShortToFloatFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LShortToFloatFunction: float doApplyAsFloat(short s)";

	float doApplyAsFloat(short s);

	default float nestingDoApplyAsFloat(short s) {
		return this.doApplyAsFloat(s);
	}

	default float shovingDoApplyAsFloat(short s) {
		return this.doApplyAsFloat(s);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(short s) {
		return doApplyAsFloat(s);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortToFloatFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplier capture(short s) {
		return () -> this.doApplyAsFloat(s);
	}

	static LShortToFloatFunction constant(float r) {
		return s -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LShortToFloatFunction l(final @Nonnull LShortToFloatFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LShortToFloatFunction wrap(final @Nonnull LShortToFloatFunctionX<X> other) {
		return other::nestingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LShortToFloatFunction fromShort(@Nonnull final LShortUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsFloat(before1.doApplyAsShort(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToFloatFunction<V1> from(@Nonnull final LToShortFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsFloat(before1.doApplyAsShort(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LShortFunction<V> then(@Nonnull LFloatFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApply(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToByteFunction thenToByte(@Nonnull LFloatToByteFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsByte(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortUnaryOperator thenToShort(@Nonnull LFloatToShortFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsShort(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToIntFunction thenToInt(@Nonnull LFloatToIntFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsInt(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToLongFunction thenToLong(@Nonnull LFloatToLongFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsLong(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToFloatFunction thenToFloat(@Nonnull LFloatUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsFloat(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToDoubleFunction thenToDouble(@Nonnull LFloatToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsDouble(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToCharFunction thenToChar(@Nonnull LFloatToCharFunction after) {
		Null.nonNullArg(after, "after");
		return s -> after.doApplyAsChar(this.doApplyAsFloat(s));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortPredicate thenToBoolean(@Nonnull LFloatPredicate after) {
		Null.nonNullArg(after, "after");
		return s -> after.doTest(this.doApplyAsFloat(s));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortToFloatFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortToFloatFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortToFloatFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortToFloatFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
