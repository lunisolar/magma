/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
import java.util.function.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Non-throwing functional interface (lambda) LCharSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: char
 *
 * @see LCharSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharSupplier extends LCharSupplierX<RuntimeException>, MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LCharSupplier: char doGetAsChar()";

	char doGetAsChar();

	default char tupleGetAsChar(LTuple.Void args) {
		return doGetAsChar();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingDoGetAsChar() {
		return this.doGetAsChar();
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default char shovingDoGetAsChar() {
		return this.doGetAsChar();
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoGetAsChar() {
		return doGetAsChar();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharSupplier.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static LCharSupplier of(char r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharSupplier l(final @Nonnull LCharSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static char call(final @Nonnull LCharSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsChar();
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharSupplier wrap(final @Nonnull LCharSupplierX<X> other) {
		return other::nestingDoGetAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceChar). */
	@Nonnull
	static LCharSupplier safe() {
		return Function4U::produceChar;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LCharSupplier safe(final @Nullable LCharSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharSupplier> safeSupplier(final @Nullable LSupplier<LCharSupplier> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSupplier(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSupplier(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortSupplier toShortSupplier(@Nonnull LCharToShortFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSupplier(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSupplier(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatSupplier toFloatSupplier(@Nonnull LCharToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleSupplier toDoubleSupplier(@Nonnull LCharToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSupplier(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSupplier(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsChar());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharSupplier nestingCharSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharSupplierX<RuntimeException> nestingCharSupX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharSupplier shovingCharSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharSupplierX<RuntimeException> shovingCharSupX() {
		return this;
	}

	// </editor-fold>

}
