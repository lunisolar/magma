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
 * Non-throwing functional interface (lambda) LBooleanToCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see LBooleanToCharFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanToCharFunction extends LBooleanToCharFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LBooleanToCharFunction: char doApplyAsChar(boolean b)";

	public char doApplyAsChar(boolean b);

	default char nestingDoApplyAsChar(boolean b) {
		return this.doApplyAsChar(b);
	}

	default char shovingDoApplyAsChar(boolean b) {
		return this.doApplyAsChar(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(boolean b) {
		return doApplyAsChar(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanToCharFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier capture(boolean b) {
		return () -> this.doApplyAsChar(b);
	}

	public static LBooleanToCharFunction constant(char r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LBooleanToCharFunction l(final @Nonnull LBooleanToCharFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LBooleanToCharFunction wrap(final @Nonnull LBooleanToCharFunctionX<X> other) {
		return other::nestingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanToCharFunction fromBoolean(@Nonnull final LBooleanUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return (final boolean v1) -> this.doApplyAsChar(before1.doApplyAsBoolean(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToCharFunction<V1> from(@Nonnull final LPredicate<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return (V1 v1) -> this.doApplyAsChar(before1.doApplyAsBoolean(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBooleanFunction<V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApply(this.doApplyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToByteFunction thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsByte(this.doApplyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToShortFunction thenToShort(@Nonnull LCharToShortFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsShort(this.doApplyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToIntFunction thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsInt(this.doApplyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToLongFunction thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsLong(this.doApplyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToFloatFunction thenToFloat(@Nonnull LCharToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsFloat(this.doApplyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToDoubleFunction thenToDouble(@Nonnull LCharToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsDouble(this.doApplyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToCharFunction thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doApplyAsChar(this.doApplyAsChar(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanUnaryOperator thenToBoolean(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return (boolean b) -> after.doTest(this.doApplyAsChar(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanToCharFunction nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanToCharFunctionX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanToCharFunction shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanToCharFunctionX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

}
