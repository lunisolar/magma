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
 * Throwing functional interface (lambda) LSupplierX for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: R
 *
 * @see LSupplier
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSupplierX<R, X extends Throwable> extends java.util.function.Supplier<R>, MetaSupplier, MetaInterface.Throwing<X> {

	static final String DESCRIPTION = "LSupplierX: R doGet() throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LSupplierX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R get() {
		return this.nestingDoGet();
	}

	@Nullable
	R doGet() throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default R nestingDoGet() {
		try {
			return this.doGet();
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default R shovingDoGet() {
		return ((LSupplierX<R, RuntimeException>) this).doGet();
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> R handlingDoGet(HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doGet();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoGet() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoGet() throws X {
		return Null.requireNonNull(doGet(), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSupplierX.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static <R, X extends Throwable> LSupplierX<R, X> of(R r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LSupplierX<R, X> lX(final @Nonnull LSupplierX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LSupplierX<R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LSupplierX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <R, X extends Throwable> LSupplierX<R, X> wrap(final java.util.function.Supplier<R> other) {
		return other::get;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LSupplierX<R, X> wrapX(final @Nonnull LSupplier<R> other) {
		return (LSupplierX) other;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplierX<V, X> toSupplier(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LActionX<X> toAction(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doAccept(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplierX<X> toByteSupplier(@Nonnull LToByteFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplierX<X> toShortSupplier(@Nonnull LToShortFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplierX<X> toIntSupplier(@Nonnull LToIntFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplierX<X> toLongSupplier(@Nonnull LToLongFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplierX<X> toFloatSupplier(@Nonnull LToFloatFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplierX<X> toDoubleSupplier(@Nonnull LToDoubleFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplierX<X> toCharSupplier(@Nonnull LToCharFunctionX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBooleanSupplierX<X> toBooleanSupplier(@Nonnull LPredicateX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGet());
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LSupplier<R> nestingSup() {
		return this::nestingDoGet;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LSupplierX<R, RuntimeException> nestingSupX() {
		return this::nestingDoGet;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplier<R> shovingSup() {
		return this::shovingDoGet;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplierX<R, RuntimeException> shovingSupX() {
		return this::shovingDoGet;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LSupplierX<R, X> nonNullSup() {
		return this::nonNullDoGet;
	}

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LSupplier<R> handleSup(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> this.handlingDoGet(handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LSupplierX<R, Y> handleSupX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return () -> this.handlingDoGet(handling);
	}

	// </editor-fold>

}
