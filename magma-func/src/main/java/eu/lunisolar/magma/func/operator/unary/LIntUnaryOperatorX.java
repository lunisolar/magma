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
 * Throwing functional interface (lambda) LIntUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): int a1
 *
 * Co-domain: int
 *
 * @see LIntUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntUnaryOperatorX<X extends Throwable> extends IntUnaryOperator, MetaOperator, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LIntUnaryOperatorX: int doApplyAsInt(int a1) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntUnaryOperatorX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(int a1) {
		return this.nestingDoApplyAsInt(a1);
	}

	int doApplyAsInt(int a1) throws X;

	default Integer tupleApplyAsInt(LIntSingle args) throws X {
		return doApplyAsInt(args.first());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(int a1) {
		try {
			return this.doApplyAsInt(a1);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(int a1) {
		return ((LIntUnaryOperatorX<RuntimeException>) this).doApplyAsInt(a1);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> int handlingDoApplyAsInt(int a1, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsInt(a1);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(int a1) throws X {
		return doApplyAsInt(a1);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplierX<X> captureIntUnaryOp(int a1) {
		return () -> this.doApplyAsInt(a1);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LIntUnaryOperatorX<X> constant(int r) {
		return a1 -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntUnaryOperatorX<X> lX(final @Nonnull LIntUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> int call(int a1, final @Nonnull LIntUnaryOperatorX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a1);
	}

	static <X extends Throwable> int shoving(int a1, final @Nonnull LIntUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApplyAsInt(a1);
	}

	static <X extends Throwable> int nesting(int a1, final @Nonnull LIntUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApplyAsInt(a1);
	}

	static <X extends Throwable, Y extends Throwable> int handling(int a1, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LIntUnaryOperatorX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApplyAsInt(a1, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LIntUnaryOperatorX<X> wrap(final IntUnaryOperator other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntUnaryOperatorX<X> wrapX(final @Nonnull LIntUnaryOperator other) {
		return (LIntUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static <X extends Throwable> LIntUnaryOperatorX<X> safe() {
		return Function4U::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LIntUnaryOperatorX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LIntUnaryOperatorX<X> safe(final @Nullable LIntUnaryOperatorX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LIntUnaryOperatorX<X>, Y> safeSupplier(final @Nullable LSupplierX<LIntUnaryOperatorX<X>, Y> supplier) {
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
	default LIntUnaryOperatorX<X> intUnaryOpComposeInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApplyAsInt(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToIntFunctionX<V1, X> intUnaryOpCompose(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsInt(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> then(@Nonnull LIntFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doApplyAsInt(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> thenToByte(@Nonnull LIntToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsByte(this.doApplyAsInt(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> thenToShort(@Nonnull LIntToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsShort(this.doApplyAsInt(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> thenToInt(@Nonnull LIntUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsInt(this.doApplyAsInt(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> thenToLong(@Nonnull LIntToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsLong(this.doApplyAsInt(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> thenToFloat(@Nonnull LIntToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsFloat(this.doApplyAsInt(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> thenToDouble(@Nonnull LIntToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsDouble(this.doApplyAsInt(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> thenToChar(@Nonnull LIntToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsChar(this.doApplyAsInt(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LIntPredicateX<X> thenToBool(@Nonnull LIntPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doTest(this.doApplyAsInt(a1));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <X extends Throwable> LIntUnaryOperatorX<X> identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntUnaryOperator nestingIntUnaryOp() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntUnaryOperatorX<RuntimeException> nestingIntUnaryOpX() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntUnaryOperator shovingIntUnaryOp() {
		return this::shovingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntUnaryOperatorX<RuntimeException> shovingIntUnaryOpX() {
		return this::shovingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LIntUnaryOperator handleIntUnaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a1 -> this.handlingDoApplyAsInt(a1, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LIntUnaryOperatorX<Y> handleIntUnaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a1 -> this.handlingDoApplyAsInt(a1, handling);
	}

	// </editor-fold>

}
