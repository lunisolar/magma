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

package eu.lunisolar.magma.func.function.from;
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
 * Throwing functional interface (lambda) LFloatFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: R
 *
 * @see LFloatFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatFunctionX<R, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LFloatFunctionX: R doApply(float f) throws X";

	@Nullable
	R doApply(float f) throws X;

	default R nestingDoApply(float f) {
		try {
			return this.doApply(f);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default R shovingDoApply(float f) {
		return ((LFloatFunctionX<R, RuntimeException>) this).doApply(f);
	}

	default <Y extends Throwable> R handlingDoApply(float f, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(f);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(float f) throws X {
		return Null.requireNonNull(doApply(f), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> capture(float f) {
		return () -> this.doApply(f);
	}

	static <R, X extends Throwable> LFloatFunctionX<R, X> constant(R r) {
		return f -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LFloatFunctionX<R, X> lX(final @Nonnull LFloatFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LFloatFunctionX<R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LFloatFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LFloatFunctionX<R, X> wrapX(final @Nonnull LFloatFunction<R> other) {
		return (LFloatFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LFloatFunctionX<R, X> fromFloat(@Nonnull final LFloatUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsFloat(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LFunctionX<V1, R, X> from(@Nonnull final LToFloatFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFloatFunctionX<V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApply(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatConsumerX<X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doAccept(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToByteFunctionX<X> thenToByte(@Nonnull LToByteFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsByte(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToShortFunctionX<X> thenToShort(@Nonnull LToShortFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsShort(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToIntFunctionX<X> thenToInt(@Nonnull LToIntFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsInt(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToLongFunctionX<X> thenToLong(@Nonnull LToLongFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsLong(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatUnaryOperatorX<X> thenToFloat(@Nonnull LToFloatFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsFloat(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToDoubleFunctionX<X> thenToDouble(@Nonnull LToDoubleFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsDouble(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToCharFunctionX<X> thenToChar(@Nonnull LToCharFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsChar(this.doApply(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatPredicateX<X> thenToBoolean(@Nonnull LPredicateX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doTest(this.doApply(f));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatFunction<R> nest() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatFunctionX<R, RuntimeException> nestX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatFunction<R> shove() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatFunctionX<R, RuntimeException> shoveX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LFloatFunctionX<R, X> nonNullableX() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	@Nonnull
	default LFloatFunction<R> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return f -> this.handlingDoApply(f, handling);
	}

	@Nonnull
	default <Y extends Throwable> LFloatFunctionX<R, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return f -> this.handlingDoApply(f, handling);
	}

	// </editor-fold>

}
