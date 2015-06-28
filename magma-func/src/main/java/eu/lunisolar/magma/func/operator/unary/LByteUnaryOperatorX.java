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
 * Throwing functional interface (lambda) LByteUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see LByteUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteUnaryOperatorX<X extends Throwable> extends MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LByteUnaryOperatorX: byte doApplyAsByte(byte b) throws X";

	byte doApplyAsByte(byte b) throws X;

	default byte nestingDoApplyAsByte(byte b) {
		try {
			return this.doApplyAsByte(b);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default byte shovingDoApplyAsByte(byte b) {
		return ((LByteUnaryOperatorX<RuntimeException>) this).doApplyAsByte(b);
	}

	default <Y extends Throwable> byte handlingDoApplyAsByte(byte b, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsByte(b);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(byte b) throws X {
		return doApplyAsByte(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplierX<X> capture(byte b) {
		return () -> this.doApplyAsByte(b);
	}

	static <X extends Throwable> LByteUnaryOperatorX<X> constant(byte r) {
		return b -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LByteUnaryOperatorX<X> lX(final @Nonnull LByteUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LByteUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LByteUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LByteUnaryOperatorX<X> wrapX(final @Nonnull LByteUnaryOperator other) {
		return (LByteUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LByteUnaryOperatorX<X> fromByte(@Nonnull final LByteUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsByte(before1.doApplyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToByteFunctionX<V1, X> from(@Nonnull final LToByteFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsByte(before1.doApplyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LByteFunctionX<V, X> then(@Nonnull LByteFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApply(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteUnaryOperatorX<X> thenToByte(@Nonnull LByteUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsByte(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToShortFunctionX<X> thenToShort(@Nonnull LByteToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsShort(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToIntFunctionX<X> thenToInt(@Nonnull LByteToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsInt(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToLongFunctionX<X> thenToLong(@Nonnull LByteToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsLong(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToFloatFunctionX<X> thenToFloat(@Nonnull LByteToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsFloat(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToDoubleFunctionX<X> thenToDouble(@Nonnull LByteToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsDouble(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LByteToCharFunctionX<X> thenToChar(@Nonnull LByteToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doApplyAsChar(this.doApplyAsByte(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBytePredicateX<X> thenToBoolean(@Nonnull LBytePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return b -> after.doTest(this.doApplyAsByte(b));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <X extends Throwable> LByteUnaryOperatorX<X> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteUnaryOperator nest() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteUnaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteUnaryOperator shove() {
		return this::shovingDoApplyAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteUnaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LByteUnaryOperator handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return b -> this.handlingDoApplyAsByte(b, handling);
	}

	@Nonnull
	default <Y extends Throwable> LByteUnaryOperatorX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return b -> this.handlingDoApplyAsByte(b, handling);
	}

	// </editor-fold>

}
