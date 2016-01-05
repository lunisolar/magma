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
 * Non-throwing functional interface (lambda) LByteSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: byte
 *
 * @see LByteSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteSupplier extends LByteSupplierX<RuntimeException>, MetaSupplier, MetaInterface.NonThrowing {

	String DESCRIPTION = "LByteSupplier: byte doGetAsByte()";

	byte doGetAsByte();

	default Byte tupleGetAsByte(LTuple.Void args) {
		return doGetAsByte();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingDoGetAsByte() {
		return this.doGetAsByte();
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default byte shovingDoGetAsByte() {
		return this.doGetAsByte();
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoGetAsByte() {
		return doGetAsByte();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteSupplier.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static LByteSupplier of(byte r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteSupplier l(final @Nonnull LByteSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static byte call(final @Nonnull LByteSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsByte();
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LByteSupplier wrap(final @Nonnull LByteSupplierX<X> other) {
		return other::nestingDoGetAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static LByteSupplier safe() {
		return Function4U::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LByteSupplier safe(final @Nullable LByteSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteSupplier> safeSupplier(final @Nullable LSupplier<LByteSupplier> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two suppliers together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSupplier(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LByteSupplier toByteSupplier(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LShortSupplier toShortSupplier(@Nonnull LByteToShortFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LIntSupplier toIntSupplier(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LLongSupplier toLongSupplier(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LFloatSupplier toFloatSupplier(@Nonnull LByteToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LDoubleSupplier toDoubleSupplier(@Nonnull LByteToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LCharSupplier toCharSupplier(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsByte());
	}

	/** Combines two suppliers together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSupplier(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsByte());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteSupplier nestingByteSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LByteSupplierX<RuntimeException> nestingByteSupX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteSupplier shovingByteSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteSupplierX<RuntimeException> shovingByteSupX() {
		return this;
	}

	// </editor-fold>

}
