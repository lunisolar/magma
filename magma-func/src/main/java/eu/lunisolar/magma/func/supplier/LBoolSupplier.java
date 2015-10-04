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
 * Non-throwing functional interface (lambda) LBoolSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: boolean
 *
 * @see LBoolSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolSupplier extends LBoolSupplierX<RuntimeException>, MetaSupplier, MetaInterface.NonThrowing {

	String DESCRIPTION = "LBoolSupplier: boolean doGetAsBool()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LBoolSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean getAsBoolean() {
		return this.nestingDoGetAsBool();
	}

	boolean doGetAsBool();

	default Boolean tupleGetAsBool(LTuple.Void args) {
		return doGetAsBool();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default boolean nestingDoGetAsBool() {
		return this.doGetAsBool();
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoGetAsBool() {
		return this.doGetAsBool();
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoGetAsBool() {
		return doGetAsBool();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolSupplier.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static LBoolSupplier of(boolean r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBoolSupplier l(final @Nonnull LBoolSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LBoolSupplier wrap(final BooleanSupplier other) {
		return other::getAsBoolean;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LBoolSupplier wrap(final @Nonnull LBoolSupplierX<X> other) {
		return other::nestingDoGetAsBool;
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSupplier(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsBool());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplier toByteSupplier(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsBool());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplier toShortSupplier(@Nonnull LBoolToShortFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsBool());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplier toIntSupplier(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsBool());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplier toLongSupplier(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsBool());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplier toFloatSupplier(@Nonnull LBoolToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsBool());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplier toDoubleSupplier(@Nonnull LBoolToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsBool());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplier toCharSupplier(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsBool());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSupplier(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsBool());
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBoolSupplier nestingBoolSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBoolSupplierX<RuntimeException> nestingBoolSupX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolSupplier shovingBoolSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolSupplierX<RuntimeException> shovingBoolSupX() {
		return this;
	}

	// </editor-fold>

}
