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
 * Non-throwing functional interface (lambda) LCharUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): char a
 *
 * Co-domain: char
 *
 * @see LCharUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharUnaryOperator extends LCharUnaryOperatorX<RuntimeException>, MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LCharUnaryOperator: char doApplyAsChar(char a)";

	char doApplyAsChar(char a);

	default char tupleApplyAsChar(LCharSingle args) {
		return doApplyAsChar(args.value());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingDoApplyAsChar(char a) {
		return this.doApplyAsChar(a);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default char shovingDoApplyAsChar(char a) {
		return this.doApplyAsChar(a);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(char a) {
		return doApplyAsChar(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier captureCharUnaryOp(char a) {
		return () -> this.doApplyAsChar(a);
	}

	/** Creates function that always returns the same value. */
	static LCharUnaryOperator constant(char r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharUnaryOperator l(final @Nonnull LCharUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static char call(char a, final @Nonnull LCharUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsChar(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharUnaryOperator wrap(final @Nonnull LCharUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceChar). */
	@Nonnull
	static LCharUnaryOperator safe() {
		return Function4U::produceChar;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharUnaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LCharUnaryOperator safe(final @Nullable LCharUnaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharUnaryOperator> safeSupplier(final @Nullable LSupplier<LCharUnaryOperator> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LCharUnaryOperator charUnaryOpComposeChar(@Nonnull final LCharUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsChar(before.doApplyAsChar(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToCharFunction<V> charUnaryOpCompose(@Nonnull final LToCharFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsChar(before.doApplyAsChar(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunction<V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunction thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToShortFunction thenToShort(@Nonnull LCharToShortFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsShort(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunction thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunction thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFloatFunction thenToFloat(@Nonnull LCharToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFloat(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDoubleFunction thenToDouble(@Nonnull LCharToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDouble(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperator thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicate thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsChar(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LCharUnaryOperator identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharUnaryOperator nestingCharUnaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharUnaryOperatorX<RuntimeException> nestingCharUnaryOpX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharUnaryOperator shovingCharUnaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharUnaryOperatorX<RuntimeException> shovingCharUnaryOpX() {
		return this;
	}

	// </editor-fold>

}
