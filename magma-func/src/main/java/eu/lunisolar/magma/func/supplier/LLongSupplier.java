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
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR

import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/**
 * Non-throwing functional interface (lambda) LLongSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: long
 *
 * @see LLongSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongSupplier extends LLongSupplierX<RuntimeException>, MetaSupplier, MetaInterface.NonThrowing {

	String DESCRIPTION = "LLongSupplier: long doGetAsLong()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long getAsLong() {
		return this.nestingDoGetAsLong();
	}

	long doGetAsLong();

	default Long tupleGetAsLong(LTuple.Void args) {
		return doGetAsLong();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default long nestingDoGetAsLong() {
		return this.doGetAsLong();
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default long shovingDoGetAsLong() {
		return this.doGetAsLong();
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoGetAsLong() {
		return doGetAsLong();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongSupplier.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static LLongSupplier of(long r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongSupplier l(final @Nonnull LLongSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongSupplier wrap(final LongSupplier other) {
		return other::getAsLong;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongSupplier wrap(final @Nonnull LLongSupplierX<X> other) {
		return other::nestingDoGetAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSupplier(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplier toByteSupplier(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplier toShortSupplier(@Nonnull LLongToShortFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplier toIntSupplier(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplier toLongSupplier(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplier toFloatSupplier(@Nonnull LLongToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplier toDoubleSupplier(@Nonnull LLongToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplier toCharSupplier(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsLong());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSupplier(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsLong());
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongSupplier nestingLongSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongSupplierX<RuntimeException> nestingLongSupX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongSupplier shovingLongSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongSupplierX<RuntimeException> shovingLongSupX() {
		return this;
	}

	// </editor-fold>

}
