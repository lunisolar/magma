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
 * Throwing functional interface (lambda) LFloatToByteFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float a1
 *
 * Co-domain: byte
 *
 * @see LFloatToByteFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatToByteFunctionX<X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LFloatToByteFunctionX: byte doApplyAsByte(float a1) throws X";

	byte doApplyAsByte(float a1) throws X;

	default Byte tupleApplyAsByte(LFloatSingle args) throws X {
		return doApplyAsByte(args.first());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingDoApplyAsByte(float a1) {
		try {
			return this.doApplyAsByte(a1);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default byte shovingDoApplyAsByte(float a1) {
		return ((LFloatToByteFunctionX<RuntimeException>) this).doApplyAsByte(a1);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> byte handlingDoApplyAsByte(float a1, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsByte(a1);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(float a1) throws X {
		return doApplyAsByte(a1);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatToByteFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplierX<X> captureFloatToByteFunc(float a1) {
		return () -> this.doApplyAsByte(a1);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LFloatToByteFunctionX<X> constant(byte r) {
		return a1 -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatToByteFunctionX<X> lX(final @Nonnull LFloatToByteFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatToByteFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LFloatToByteFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> byte call(float a1, final @Nonnull LFloatToByteFunctionX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsByte(a1);
	}

	static <X extends Throwable> byte shoving(float a1, final @Nonnull LFloatToByteFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApplyAsByte(a1);
	}

	static <X extends Throwable> byte nesting(float a1, final @Nonnull LFloatToByteFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApplyAsByte(a1);
	}

	static <X extends Throwable, Y extends Throwable> byte handling(float a1, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LFloatToByteFunctionX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApplyAsByte(a1, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LFloatToByteFunctionX<X> wrapX(final @Nonnull LFloatToByteFunction other) {
		return (LFloatToByteFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static <X extends Throwable> LFloatToByteFunctionX<X> safe() {
		return Function4U::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LFloatToByteFunctionX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LFloatToByteFunctionX<X> safe(final @Nullable LFloatToByteFunctionX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LFloatToByteFunctionX<X>, Y> safeSupplier(final @Nullable LSupplierX<LFloatToByteFunctionX<X>, Y> supplier) {
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
	default LFloatToByteFunctionX<X> floatToByteFuncComposeFloat(@Nonnull final LFloatUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsByte(before1.doApplyAsFloat(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToByteFunctionX<V1, X> floatToByteFuncCompose(@Nonnull final LToFloatFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsByte(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFloatFunctionX<V, X> then(@Nonnull LByteFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToByteFunctionX<X> thenToByte(@Nonnull LByteUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsByte(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToShortFunctionX<X> thenToShort(@Nonnull LByteToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsShort(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToIntFunctionX<X> thenToInt(@Nonnull LByteToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsInt(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToLongFunctionX<X> thenToLong(@Nonnull LByteToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsLong(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatUnaryOperatorX<X> thenToFloat(@Nonnull LByteToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsFloat(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToDoubleFunctionX<X> thenToDouble(@Nonnull LByteToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsDouble(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToCharFunctionX<X> thenToChar(@Nonnull LByteToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsChar(this.doApplyAsByte(a1));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatPredicateX<X> thenToBool(@Nonnull LBytePredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doTest(this.doApplyAsByte(a1));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatToByteFunction nestingFloatToByteFunc() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatToByteFunctionX<RuntimeException> nestingFloatToByteFuncX() {
		return this::nestingDoApplyAsByte;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToByteFunction shovingFloatToByteFunc() {
		return this::shovingDoApplyAsByte;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToByteFunctionX<RuntimeException> shovingFloatToByteFuncX() {
		return this::shovingDoApplyAsByte;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LFloatToByteFunction handleFloatToByteFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a1 -> this.handlingDoApplyAsByte(a1, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LFloatToByteFunctionX<Y> handleFloatToByteFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a1 -> this.handlingDoApplyAsByte(a1, handling);
	}

	// </editor-fold>

}
