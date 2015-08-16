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
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LFloatToCharFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float f
 *
 * Co-domain: none
 *
 * @see LFloatToCharFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatToCharFunctionX<X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LFloatToCharFunctionX: char doApplyAsChar(float f) throws X";

	char doApplyAsChar(float f) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default char nestingDoApplyAsChar(float f) {
		try {
			return this.doApplyAsChar(f);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default char shovingDoApplyAsChar(float f) {
		return ((LFloatToCharFunctionX<RuntimeException>) this).doApplyAsChar(f);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> char handlingDoApplyAsChar(float f, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsChar(f);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(float f) throws X {
		return doApplyAsChar(f);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatToCharFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplierX<X> captureFloatToCharFunc(float f) {
		return () -> this.doApplyAsChar(f);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LFloatToCharFunctionX<X> constant(char r) {
		return f -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatToCharFunctionX<X> lX(final @Nonnull LFloatToCharFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LFloatToCharFunctionX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LFloatToCharFunctionX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LFloatToCharFunctionX<X> wrapX(final @Nonnull LFloatToCharFunction other) {
		return (LFloatToCharFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LFloatToCharFunctionX<X> floatToCharFuncComposeFloat(@Nonnull final LFloatUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsChar(before1.doApplyAsFloat(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToCharFunctionX<V1, X> floatToCharFuncCompose(@Nonnull final LToFloatFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsChar(before1.doApplyAsFloat(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFloatFunctionX<V, X> then(@Nonnull LCharFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApply(this.doApplyAsChar(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToByteFunctionX<X> thenToByte(@Nonnull LCharToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsByte(this.doApplyAsChar(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToShortFunctionX<X> thenToShort(@Nonnull LCharToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsShort(this.doApplyAsChar(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToIntFunctionX<X> thenToInt(@Nonnull LCharToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsInt(this.doApplyAsChar(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToLongFunctionX<X> thenToLong(@Nonnull LCharToLongFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsLong(this.doApplyAsChar(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatUnaryOperatorX<X> thenToFloat(@Nonnull LCharToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsFloat(this.doApplyAsChar(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToDoubleFunctionX<X> thenToDouble(@Nonnull LCharToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsDouble(this.doApplyAsChar(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatToCharFunctionX<X> thenToChar(@Nonnull LCharUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doApplyAsChar(this.doApplyAsChar(f));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatPredicateX<X> thenToBoolean(@Nonnull LCharPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return f -> after.doTest(this.doApplyAsChar(f));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatToCharFunction nestingFloatToCharFunc() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatToCharFunctionX<RuntimeException> nestingFloatToCharFuncX() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToCharFunction shovingFloatToCharFunc() {
		return this::shovingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatToCharFunctionX<RuntimeException> shovingFloatToCharFuncX() {
		return this::shovingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LFloatToCharFunction handleFloatToCharFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return f -> this.handlingDoApplyAsChar(f, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LFloatToCharFunctionX<Y> handleFloatToCharFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return f -> this.handlingDoApplyAsChar(f, handling);
	}

	// </editor-fold>

}
