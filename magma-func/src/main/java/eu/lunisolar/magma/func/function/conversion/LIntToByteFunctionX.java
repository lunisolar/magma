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

package eu.lunisolar.magma.func.function.conversion;
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
 * Throwing functional interface (lambda) LIntToByteFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int a1
 *
 * Co-domain: byte
 *
 * @see LIntToByteFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToByteFunctionX<X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LIntToByteFunctionX: byte doApplyAsByte(int a1) throws X";

	byte doApplyAsByte(int a1) throws X;

	default Byte tupleApplyAsByte(LIntSingle args) throws X {
		return doApplyAsByte(args.first());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default byte nestingDoApplyAsByte(int a1) {
		try {
			return this.doApplyAsByte(a1);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default byte shovingDoApplyAsByte(int a1) {
		return ((LIntToByteFunctionX<RuntimeException>) this).doApplyAsByte(a1);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> byte handlingDoApplyAsByte(int a1, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsByte(a1);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(int a1) throws X {
		return doApplyAsByte(a1);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToByteFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplierX<X> captureIntToByteFunc(int a1) {
		return () -> this.doApplyAsByte(a1);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LIntToByteFunctionX<X> constant(byte r) {
		return a1 -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntToByteFunctionX<X> lX(final @Nonnull LIntToByteFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LIntToByteFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntToByteFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LIntToByteFunctionX<X> wrapX(final @Nonnull LIntToByteFunction other) {
		return (LIntToByteFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LIntToByteFunctionX<X> intToByteFuncComposeInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsByte(before1.doApplyAsInt(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToByteFunctionX<V1, X> intToByteFuncCompose(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsByte(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> then(@Nonnull LByteFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> thenToByte(@Nonnull LByteUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsByte(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> thenToShort(@Nonnull LByteToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsShort(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> thenToInt(@Nonnull LByteToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsInt(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> thenToLong(@Nonnull LByteToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsLong(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> thenToFloat(@Nonnull LByteToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsFloat(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> thenToDouble(@Nonnull LByteToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsDouble(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> thenToChar(@Nonnull LByteToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsChar(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicateX<X> thenToBool(@Nonnull LBytePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doTest(this.doApplyAsByte(a1));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToByteFunction nestingIntToByteFunc() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntToByteFunctionX<RuntimeException> nestingIntToByteFuncX() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToByteFunction shovingIntToByteFunc() {
		return this::shovingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToByteFunctionX<RuntimeException> shovingIntToByteFuncX() {
		return this::shovingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LIntToByteFunction handleIntToByteFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a1 -> this.handlingDoApplyAsByte(a1, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LIntToByteFunctionX<Y> handleIntToByteFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a1 -> this.handlingDoApplyAsByte(a1, handling);
	}

	// </editor-fold>

}
