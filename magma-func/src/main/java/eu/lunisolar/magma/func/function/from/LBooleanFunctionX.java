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
 * Throwing functional interface (lambda) LBooleanFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: R
 *
 * @see LBooleanFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanFunctionX<R, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LBooleanFunctionX: R doApply(boolean b) throws X";

	@Nullable
	R doApply(boolean b) throws X;

	default R nestingDoApply(boolean b) {
		try {
			return this.doApply(b);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default R shovingDoApply(boolean b) {
		return ((LBooleanFunctionX<R, RuntimeException>) this).doApply(b);
	}

	default <Y extends Throwable> R handlingDoApply(boolean b, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(b);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(boolean b) throws X {
		return Null.requireNonNull(doApply(b), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> capture(boolean b) {
		return () -> this.doApply(b);
	}

	static <R, X extends Throwable> LBooleanFunctionX<R, X> constant(R r) {
		return b -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LBooleanFunctionX<R, X> lX(final @Nonnull LBooleanFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LBooleanFunctionX<R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LBooleanFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LBooleanFunctionX<R, X> wrapX(final @Nonnull LBooleanFunction<R> other) {
		return (LBooleanFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanFunctionX<R, X> fromBoolean(@Nonnull final LBooleanUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsBoolean(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LFunctionX<V1, R, X> from(@Nonnull final LPredicateX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApply(before1.doApplyAsBoolean(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBooleanFunctionX<V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApply(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanConsumerX<X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doAccept(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToByteFunctionX<X> thenToByte(@Nonnull LToByteFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsByte(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToShortFunctionX<X> thenToShort(@Nonnull LToShortFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsShort(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToIntFunctionX<X> thenToInt(@Nonnull LToIntFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsInt(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToLongFunctionX<X> thenToLong(@Nonnull LToLongFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsLong(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToFloatFunctionX<X> thenToFloat(@Nonnull LToFloatFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsFloat(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToDoubleFunctionX<X> thenToDouble(@Nonnull LToDoubleFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsDouble(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanToCharFunctionX<X> thenToChar(@Nonnull LToCharFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsChar(this.doApply(b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBooleanUnaryOperatorX<X> thenToBoolean(@Nonnull LPredicateX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doTest(this.doApply(b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanFunction<R> nest() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanFunctionX<R, RuntimeException> nestX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanFunction<R> shove() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanFunctionX<R, RuntimeException> shoveX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LBooleanFunctionX<R, X> nonNullableX() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	@Nonnull
	default LBooleanFunction<R> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return b -> this.handlingDoApply(b, handling);
	}

	@Nonnull
	default <Y extends Throwable> LBooleanFunctionX<R, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return b -> this.handlingDoApply(b, handling);
	}

	// </editor-fold>

}
