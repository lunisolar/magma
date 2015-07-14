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
 * Non-throwing functional interface (lambda) LIntSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 * @see LIntSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntSupplier extends LIntSupplierX<RuntimeException>, MetaSupplier, PrimitiveCodomain<Object>, MetaInterface.NonThrowing {

	static final String DESCRIPTION = "LIntSupplier: int doGetAsInt()";

	@Override
	@Deprecated
	// calling this method via LIntSupplier interface should be discouraged.
	default int getAsInt() {
		return this.nestingDoGetAsInt();
	}

	int doGetAsInt();

	default int nestingDoGetAsInt() {
		return this.doGetAsInt();
	}

	default int shovingDoGetAsInt() {
		return this.doGetAsInt();
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoGetAsInt() {
		return doGetAsInt();
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntSupplier.DESCRIPTION;
	}

	static LIntSupplier of(int r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntSupplier l(final @Nonnull LIntSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntSupplier wrap(final java.util.function.IntSupplier other) {
		return other::getAsInt;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntSupplier wrap(final @Nonnull LIntSupplierX<X> other) {
		return other::nestingDoGetAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSupplier(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplier toByteSupplier(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplier toShortSupplier(@Nonnull LIntToShortFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplier toIntSupplier(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplier toLongSupplier(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplier toFloatSupplier(@Nonnull LIntToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplier toDoubleSupplier(@Nonnull LIntToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplier toCharSupplier(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsInt());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBooleanSupplier toBooleanSupplier(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsInt());
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntSupplier nestingISup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntSupplierX<RuntimeException> nestingISupX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntSupplier shovingISup() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntSupplierX<RuntimeException> shovingISupX() {
		return this;
	}

	// </editor-fold>

}
