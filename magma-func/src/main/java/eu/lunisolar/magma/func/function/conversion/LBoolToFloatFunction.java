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
 * Non-throwing functional interface (lambda) LBoolToFloatFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see LBoolToFloatFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolToFloatFunction extends LBoolToFloatFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LBoolToFloatFunction: float doApplyAsFloat(boolean b)";

	float doApplyAsFloat(boolean b);

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default float nestingDoApplyAsFloat(boolean b) {
		return this.doApplyAsFloat(b);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default float shovingDoApplyAsFloat(boolean b) {
		return this.doApplyAsFloat(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(boolean b) {
		return doApplyAsFloat(b);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolToFloatFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplier captureBoolToFloatFunc(boolean b) {
		return () -> this.doApplyAsFloat(b);
	}

	/** Creates function that always returns the same value. */
	static LBoolToFloatFunction constant(float r) {
		return b -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBoolToFloatFunction l(final @Nonnull LBoolToFloatFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LBoolToFloatFunction wrap(final @Nonnull LBoolToFloatFunctionX<X> other) {
		return other::nestingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBoolToFloatFunction boolToFloatFuncComposeBoolean(@Nonnull final LLogicalOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsFloat(before1.doApply(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToFloatFunction<V1> boolToFloatFuncCompose(@Nonnull final LPredicate<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsFloat(before1.doTest(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBoolFunction<V> then(@Nonnull LFloatFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApply(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToByteFunction thenToByte(@Nonnull LFloatToByteFunction after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsByte(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToShortFunction thenToShort(@Nonnull LFloatToShortFunction after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsShort(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToIntFunction thenToInt(@Nonnull LFloatToIntFunction after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsInt(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToLongFunction thenToLong(@Nonnull LFloatToLongFunction after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsLong(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToFloatFunction thenToFloat(@Nonnull LFloatUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsFloat(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToDoubleFunction thenToDouble(@Nonnull LFloatToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsDouble(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToCharFunction thenToChar(@Nonnull LFloatToCharFunction after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsChar(this.doApplyAsFloat(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperator thenToBoolean(@Nonnull LFloatPredicate after) {
		Null.nonNullArg(after, "after");
		return b -> after.doTest(this.doApplyAsFloat(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBoolToFloatFunction nestingBoolToFloatFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBoolToFloatFunctionX<RuntimeException> nestingBoolToFloatFuncX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolToFloatFunction shovingBoolToFloatFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolToFloatFunctionX<RuntimeException> shovingBoolToFloatFuncX() {
		return this;
	}

	// </editor-fold>

}