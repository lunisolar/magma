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

package eu.lunisolar.magma.func.supplier;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
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
 * Throwing functional interface (lambda) LByteSupplierX for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 * @see LByteSupplier
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteSupplierX<X extends Throwable> extends MetaSupplier, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> {

	public static final String DESCRIPTION = "LByteSupplierX: byte doGetAsByte() throws X";

	public byte doGetAsByte() throws X;

	default byte nestingDoGetAsByte() {
		try {
			return this.doGetAsByte();
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default byte shovingDoGetAsByte() {
		return ((LByteSupplierX<RuntimeException>) this).doGetAsByte();
	}

	default <Y extends Throwable> byte handlingDoGetAsByte(HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doGetAsByte();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoGetAsByte() throws X {
		return doGetAsByte();
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteSupplierX.DESCRIPTION;
	}

	public static <X extends Throwable> LByteSupplierX<X> of(byte r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LByteSupplierX<X> lX(final @Nonnull LByteSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Throwable> LByteSupplierX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LByteSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Throwable> LByteSupplierX<X> wrapX(final @Nonnull LByteSupplier other) {
		return (LByteSupplierX) other;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplierX<V, X> then(@Nonnull LByteFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplierX<X> thenToByte(@Nonnull LByteUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplierX<X> thenToShort(@Nonnull LByteToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplierX<X> thenToInt(@Nonnull LByteToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplierX<X> thenToLong(@Nonnull LByteToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplierX<X> thenToFloat(@Nonnull LByteToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplierX<X> thenToDouble(@Nonnull LByteToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplierX<X> thenToChar(@Nonnull LByteToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBooleanSupplierX<X> thenToBoolean(@Nonnull LBytePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsByte());
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteSupplier nest() {
		return this::nestingDoGetAsByte;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteSupplierX<RuntimeException> nestX() {
		return this::nestingDoGetAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteSupplier shove() {
		return this::shovingDoGetAsByte;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteSupplierX<RuntimeException> shoveX() {
		return this::shovingDoGetAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LByteSupplier handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> this.handlingDoGetAsByte(handling);
	}

	@Nonnull
	default <Y extends Throwable> LByteSupplierX<Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return () -> this.handlingDoGetAsByte(handling);
	}

	// </editor-fold>

}
