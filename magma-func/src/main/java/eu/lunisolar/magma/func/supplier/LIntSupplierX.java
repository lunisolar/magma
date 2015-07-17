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
 * Throwing functional interface (lambda) LIntSupplierX for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 * @see LIntSupplier
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntSupplierX<X extends Throwable> extends java.util.function.IntSupplier, MetaSupplier, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> {

	static final String DESCRIPTION = "LIntSupplierX: int doGetAsInt() throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntSupplierX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int getAsInt() {
		return this.nestingDoGetAsInt();
	}

	int doGetAsInt() throws X;

	default int nestingDoGetAsInt() {
		try {
			return this.doGetAsInt();
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default int shovingDoGetAsInt() {
		return ((LIntSupplierX<RuntimeException>) this).doGetAsInt();
	}

	default <Y extends Throwable> int handlingDoGetAsInt(HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doGetAsInt();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoGetAsInt() throws X {
		return doGetAsInt();
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntSupplierX.DESCRIPTION;
	}

	static <X extends Throwable> LIntSupplierX<X> of(int r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntSupplierX<X> lX(final @Nonnull LIntSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntSupplierX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntSupplierX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LIntSupplierX<X> wrap(final java.util.function.IntSupplier other) {
		return other::getAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntSupplierX<X> wrapX(final @Nonnull LIntSupplier other) {
		return (LIntSupplierX) other;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplierX<V, X> toSupplier(@Nonnull LIntFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplierX<X> toByteSupplier(@Nonnull LIntToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplierX<X> toShortSupplier(@Nonnull LIntToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplierX<X> toIntSupplier(@Nonnull LIntUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplierX<X> toLongSupplier(@Nonnull LIntToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplierX<X> toFloatSupplier(@Nonnull LIntToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplierX<X> toDoubleSupplier(@Nonnull LIntToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplierX<X> toCharSupplier(@Nonnull LIntToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBooleanSupplierX<X> toBooleanSupplier(@Nonnull LIntPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsInt());
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntSupplier nestingISup() {
		return this::nestingDoGetAsInt;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntSupplierX<RuntimeException> nestingISupX() {
		return this::nestingDoGetAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntSupplier shovingISup() {
		return this::shovingDoGetAsInt;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntSupplierX<RuntimeException> shovingISupX() {
		return this::shovingDoGetAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LIntSupplier handleISup(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> this.handlingDoGetAsInt(handling);
	}

	@Nonnull
	default <Y extends Throwable> LIntSupplierX<Y> handleISupX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return () -> this.handlingDoGetAsInt(handling);
	}

	// </editor-fold>

}
