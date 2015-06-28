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

package eu.lunisolar.magma.func.operator.unary;
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
 * Throwing functional interface (lambda) LFloatUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: none
 *
 * @see LFloatUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatUnaryOperatorX<X extends Throwable> extends MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LFloatUnaryOperatorX: float doApplyAsFloat(float f) throws X";

	float doApplyAsFloat(float f) throws X;

	default float nestingDoApplyAsFloat(float f) {
		try {
			return this.doApplyAsFloat(f);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default float shovingDoApplyAsFloat(float f) {
		return ((LFloatUnaryOperatorX<RuntimeException>) this).doApplyAsFloat(f);
	}

	default <Y extends Throwable> float handlingDoApplyAsFloat(float f, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsFloat(f);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(float f) throws X {
		return doApplyAsFloat(f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplierX<X> capture(float f) {
		return () -> this.doApplyAsFloat(f);
	}

	static <X extends Throwable> LFloatUnaryOperatorX<X> constant(float r) {
		return f -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatUnaryOperatorX<X> lX(final @Nonnull LFloatUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LFloatUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LFloatUnaryOperatorX<X> wrapX(final @Nonnull LFloatUnaryOperator other) {
		return (LFloatUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatUnaryOperatorX<X> fromFloat(@Nonnull final LFloatUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsFloat(before1.doApplyAsFloat(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToFloatFunctionX<V1, X> from(@Nonnull final LToFloatFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsFloat(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LFloatFunctionX<V, X> then(@Nonnull LFloatFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApply(this.doApplyAsFloat(f));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LFloatToByteFunctionX<X> thenToByte(@Nonnull LFloatToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsByte(this.doApplyAsFloat(f));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LFloatToShortFunctionX<X> thenToShort(@Nonnull LFloatToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsShort(this.doApplyAsFloat(f));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LFloatToIntFunctionX<X> thenToInt(@Nonnull LFloatToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsInt(this.doApplyAsFloat(f));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LFloatToLongFunctionX<X> thenToLong(@Nonnull LFloatToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsLong(this.doApplyAsFloat(f));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LFloatUnaryOperatorX<X> thenToFloat(@Nonnull LFloatUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsFloat(this.doApplyAsFloat(f));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LFloatToDoubleFunctionX<X> thenToDouble(@Nonnull LFloatToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsDouble(this.doApplyAsFloat(f));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LFloatToCharFunctionX<X> thenToChar(@Nonnull LFloatToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsChar(this.doApplyAsFloat(f));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LFloatPredicateX<X> thenToBoolean(@Nonnull LFloatPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doTest(this.doApplyAsFloat(f));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <X extends Throwable> LFloatUnaryOperatorX<X> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatUnaryOperator nest() {
		return this::nestingDoApplyAsFloat;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatUnaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatUnaryOperator shove() {
		return this::shovingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatUnaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LFloatUnaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return f -> this.handlingDoApplyAsFloat(f, handling);
	}

	@Nonnull
	default <Y extends Throwable> LFloatUnaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return f -> this.handlingDoApplyAsFloat(f, handling);
	}

	// </editor-fold>

}
