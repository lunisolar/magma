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
 * Non-throwing functional interface (lambda) LSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: R
 *
 * @see LSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSupplier<R> extends LSupplierX<R, RuntimeException>, MetaSupplier, MetaInterface.NonThrowing {

	static final String DESCRIPTION = "LSupplier: R doGet()";

	@Override
	@Deprecated
	// calling this method via LSupplier interface should be discouraged.
	default R get() {
		return this.nestingDoGet();
	}

	@Nullable
	R doGet();

	default R nestingDoGet() {
		return this.doGet();
	}

	default R shovingDoGet() {
		return this.doGet();
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoGet() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoGet() {
		return Null.requireNonNull(doGet(), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSupplier.DESCRIPTION;
	}

	static <R> LSupplier<R> of(R r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LSupplier<R> l(final @Nonnull LSupplier<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <R> LSupplier<R> wrap(final java.util.function.Supplier<R> other) {
		return other::get;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LSupplier<R> wrap(final @Nonnull LSupplierX<R, X> other) {
		return other::nestingDoGet;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSupplier(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LAction toAction(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doAccept(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplier toByteSupplier(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplier toShortSupplier(@Nonnull LToShortFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplier toIntSupplier(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplier toLongSupplier(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplier toFloatSupplier(@Nonnull LToFloatFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplier toDoubleSupplier(@Nonnull LToDoubleFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplier toCharSupplier(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGet());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBooleanSupplier toBooleanSupplier(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGet());
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LSupplier<R> nestingSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LSupplierX<R, RuntimeException> nestingSupX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplier<R> shovingSup() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSupplierX<R, RuntimeException> shovingSupX() {
		return this;
	}

	// </editor-fold>

	@Nonnull
	default LSupplier<R> nonNullSup() {
		return this::nonNullDoGet;
	}

}
