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
 * Non-throwing functional interface (lambda) LDoubleToShortFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoubleToShortFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleToShortFunction extends LDoubleToShortFunctionX<RuntimeException>, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	static final String DESCRIPTION = "LDoubleToShortFunction: short doApplyAsShort(double d)";

	short doApplyAsShort(double d);

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default short nestingDoApplyAsShort(double d) {
		return this.doApplyAsShort(d);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default short shovingDoApplyAsShort(double d) {
		return this.doApplyAsShort(d);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(double d) {
		return doApplyAsShort(d);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleToShortFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplier captureDoubleToShortFunc(double d) {
		return () -> this.doApplyAsShort(d);
	}

	/** Creates function that always returns the same value. */
	static LDoubleToShortFunction constant(short r) {
		return d -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDoubleToShortFunction l(final @Nonnull LDoubleToShortFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LDoubleToShortFunction wrap(final @Nonnull LDoubleToShortFunctionX<X> other) {
		return other::nestingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LDoubleToShortFunction doubleToShortFuncComposeDouble(@Nonnull final LDoubleUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsShort(before1.doApplyAsDouble(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToShortFunction<V1> doubleToShortFuncCompose(@Nonnull final LToDoubleFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsShort(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDoubleFunction<V> then(@Nonnull LShortFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApply(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToByteFunction thenToByte(@Nonnull LShortToByteFunction after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsByte(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToShortFunction thenToShort(@Nonnull LShortUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsShort(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToIntFunction thenToInt(@Nonnull LShortToIntFunction after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsInt(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToLongFunction thenToLong(@Nonnull LShortToLongFunction after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsLong(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToFloatFunction thenToFloat(@Nonnull LShortToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsFloat(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleUnaryOperator thenToDouble(@Nonnull LShortToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsDouble(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleToCharFunction thenToChar(@Nonnull LShortToCharFunction after) {
		Null.nonNullArg(after, "after");
		return d -> after.doApplyAsChar(this.doApplyAsShort(d));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoublePredicate thenToBoolean(@Nonnull LShortPredicate after) {
		Null.nonNullArg(after, "after");
		return d -> after.doTest(this.doApplyAsShort(d));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleToShortFunction nestingDoubleToShortFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleToShortFunctionX<RuntimeException> nestingDoubleToShortFuncX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToShortFunction shovingDoubleToShortFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleToShortFunctionX<RuntimeException> shovingDoubleToShortFuncX() {
		return this;
	}

	// </editor-fold>

}
