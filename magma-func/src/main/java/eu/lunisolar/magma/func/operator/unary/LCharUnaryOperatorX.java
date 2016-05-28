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
 * Throwing functional interface (lambda) LCharUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): char a
 *
 * Co-domain: char
 *
 * @see LCharUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharUnaryOperatorX<X extends Throwable> extends MetaOperator, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LCharUnaryOperatorX: char doApplyAsChar(char a) throws X";

	char doApplyAsChar(char a) throws X;

	default char tupleApplyAsChar(LCharSingle args) throws X {
		return doApplyAsChar(args.value());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingDoApplyAsChar(char a) {
		try {
			return this.doApplyAsChar(a);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default char shovingDoApplyAsChar(char a) {
		return ((LCharUnaryOperatorX<RuntimeException>) this).doApplyAsChar(a);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> char handlingDoApplyAsChar(char a, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsChar(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(char a) throws X {
		return doApplyAsChar(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplierX<X> captureCharUnaryOp(char a) {
		return () -> this.doApplyAsChar(a);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LCharUnaryOperatorX<X> constant(char r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LCharUnaryOperatorX<X> lX(final @Nonnull LCharUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LCharUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LCharUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> char call(char a, final @Nonnull LCharUnaryOperatorX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsChar(a);
	}

	static <X extends Throwable> char shoving(char a, final @Nonnull LCharUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApplyAsChar(a);
	}

	static <X extends Throwable> char nesting(char a, final @Nonnull LCharUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApplyAsChar(a);
	}

	static <X extends Throwable, Y extends Throwable> char handling(char a, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LCharUnaryOperatorX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApplyAsChar(a, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharUnaryOperatorX<X> wrapX(final @Nonnull LCharUnaryOperator other) {
		return (LCharUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceChar). */
	@Nonnull
	static <X extends Throwable> LCharUnaryOperatorX<X> safe() {
		return Function4U::produceChar;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LCharUnaryOperatorX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LCharUnaryOperatorX<X> safe(final @Nullable LCharUnaryOperatorX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LCharUnaryOperatorX<X>, Y> safeSupplier(final @Nullable LSupplierX<LCharUnaryOperatorX<X>, Y> supplier) {
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
	default LCharUnaryOperatorX<X> charUnaryOpComposeChar(@Nonnull final LCharUnaryOperatorX<X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsChar(before.doApplyAsChar(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToCharFunctionX<V, X> charUnaryOpCompose(@Nonnull final LToCharFunctionX<? super V, X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsChar(before.doApplyAsChar(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunctionX<V, X> then(@Nonnull LCharFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunctionX<X> thenToByte(@Nonnull LCharToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToShortFunctionX<X> thenToShort(@Nonnull LCharToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsShort(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunctionX<X> thenToInt(@Nonnull LCharToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunctionX<X> thenToLong(@Nonnull LCharToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFloatFunctionX<X> thenToFloat(@Nonnull LCharToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFloat(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDoubleFunctionX<X> thenToDouble(@Nonnull LCharToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDouble(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperatorX<X> thenToChar(@Nonnull LCharUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicateX<X> thenToBool(@Nonnull LCharPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsChar(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <X extends Throwable> LCharUnaryOperatorX<X> identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharUnaryOperator nestingCharUnaryOp() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharUnaryOperatorX<RuntimeException> nestingCharUnaryOpX() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharUnaryOperator shovingCharUnaryOp() {
		return this::shovingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharUnaryOperatorX<RuntimeException> shovingCharUnaryOpX() {
		return this::shovingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LCharUnaryOperator handleCharUnaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> this.handlingDoApplyAsChar(a, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LCharUnaryOperatorX<Y> handleCharUnaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a -> this.handlingDoApplyAsChar(a, handling);
	}

	// </editor-fold>

}
