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
 * Throwing functional interface (lambda) LShortUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): short a
 *
 * Co-domain: short
 *
 * @see LShortUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LShortUnaryOperatorX<X extends Throwable> extends MetaOperator, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LShortUnaryOperatorX: short doApplyAsShort(short a) throws X";

	short doApplyAsShort(short a) throws X;

	default short tupleApplyAsShort(LShortSingle args) throws X {
		return doApplyAsShort(args.value());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingDoApplyAsShort(short a) {
		try {
			return this.doApplyAsShort(a);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default short shovingDoApplyAsShort(short a) {
		return ((LShortUnaryOperatorX<RuntimeException>) this).doApplyAsShort(a);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> short handlingDoApplyAsShort(short a, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsShort(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsShort(short a) throws X {
		return doApplyAsShort(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LShortUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LShortSupplierX<X> captureShortUnaryOp(short a) {
		return () -> this.doApplyAsShort(a);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LShortUnaryOperatorX<X> constant(short r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LShortUnaryOperatorX<X> lX(final @Nonnull LShortUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LShortUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LShortUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> short call(short a, final @Nonnull LShortUnaryOperatorX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsShort(a);
	}

	static <X extends Throwable> short shoving(short a, final @Nonnull LShortUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApplyAsShort(a);
	}

	static <X extends Throwable> short nesting(short a, final @Nonnull LShortUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApplyAsShort(a);
	}

	static <X extends Throwable, Y extends Throwable> short handling(short a, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LShortUnaryOperatorX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApplyAsShort(a, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LShortUnaryOperatorX<X> wrapX(final @Nonnull LShortUnaryOperator other) {
		return (LShortUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceShort). */
	@Nonnull
	static <X extends Throwable> LShortUnaryOperatorX<X> safe() {
		return Function4U::produceShort;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LShortUnaryOperatorX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LShortUnaryOperatorX<X> safe(final @Nullable LShortUnaryOperatorX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LShortUnaryOperatorX<X>, Y> safeSupplier(final @Nullable LSupplierX<LShortUnaryOperatorX<X>, Y> supplier) {
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
	default LShortUnaryOperatorX<X> shortUnaryOpComposeShort(@Nonnull final LShortUnaryOperatorX<X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsShort(before.doApplyAsShort(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToShortFunctionX<V, X> shortUnaryOpCompose(@Nonnull final LToShortFunctionX<? super V, X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsShort(before.doApplyAsShort(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LShortFunctionX<V, X> then(@Nonnull LShortFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsShort(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToByteFunctionX<X> thenToByte(@Nonnull LShortToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsShort(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortUnaryOperatorX<X> thenToShort(@Nonnull LShortUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsShort(this.doApplyAsShort(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToIntFunctionX<X> thenToInt(@Nonnull LShortToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsShort(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToLongFunctionX<X> thenToLong(@Nonnull LShortToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsShort(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToFloatFunctionX<X> thenToFloat(@Nonnull LShortToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFloat(this.doApplyAsShort(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToDoubleFunctionX<X> thenToDouble(@Nonnull LShortToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDouble(this.doApplyAsShort(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortToCharFunctionX<X> thenToChar(@Nonnull LShortToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsShort(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortPredicateX<X> thenToBool(@Nonnull LShortPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsShort(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <X extends Throwable> LShortUnaryOperatorX<X> identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LShortUnaryOperator nestingShortUnaryOp() {
		return this::nestingDoApplyAsShort;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LShortUnaryOperatorX<RuntimeException> nestingShortUnaryOpX() {
		return this::nestingDoApplyAsShort;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortUnaryOperator shovingShortUnaryOp() {
		return this::shovingDoApplyAsShort;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LShortUnaryOperatorX<RuntimeException> shovingShortUnaryOpX() {
		return this::shovingDoApplyAsShort;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LShortUnaryOperator handleShortUnaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> this.handlingDoApplyAsShort(a, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LShortUnaryOperatorX<Y> handleShortUnaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a -> this.handlingDoApplyAsShort(a, handling);
	}

	// </editor-fold>

}
