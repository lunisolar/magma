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
 * Throwing functional interface (lambda) LLongUnaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongUnaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongUnaryOperatorX<X extends Throwable> extends java.util.function.LongUnaryOperator, MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LLongUnaryOperatorX: long doApplyAsLong(long l) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongUnaryOperatorX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(long l) {
		return this.nestingDoApplyAsLong(l);
	}

	long doApplyAsLong(long l) throws X;

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default long nestingDoApplyAsLong(long l) {
		try {
			return this.doApplyAsLong(l);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default long shovingDoApplyAsLong(long l) {
		return ((LLongUnaryOperatorX<RuntimeException>) this).doApplyAsLong(l);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> long handlingDoApplyAsLong(long l, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsLong(l);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(long l) throws X {
		return doApplyAsLong(l);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongUnaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplierX<X> captureLongUnaryOp(long l) {
		return () -> this.doApplyAsLong(l);
	}

	/** Creates function that always returns the same value. */
	static <X extends Throwable> LLongUnaryOperatorX<X> constant(long r) {
		return l -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongUnaryOperatorX<X> lX(final @Nonnull LLongUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLongUnaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLongUnaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <X extends Throwable> LLongUnaryOperatorX<X> wrap(final java.util.function.LongUnaryOperator other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLongUnaryOperatorX<X> wrapX(final @Nonnull LLongUnaryOperator other) {
		return (LLongUnaryOperatorX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongUnaryOperatorX<X> longUnaryOpComposeLong(@Nonnull final LLongUnaryOperatorX<X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApplyAsLong(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToLongFunctionX<V1, X> longUnaryOpCompose(@Nonnull final LToLongFunctionX<? super V1, X> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsLong(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LLongFunctionX<V, X> then(@Nonnull LLongFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApply(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToByteFunctionX<X> thenToByte(@Nonnull LLongToByteFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsByte(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToShortFunctionX<X> thenToShort(@Nonnull LLongToShortFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsShort(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToIntFunctionX<X> thenToInt(@Nonnull LLongToIntFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsInt(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongUnaryOperatorX<X> thenToLong(@Nonnull LLongUnaryOperatorX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsLong(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToFloatFunctionX<X> thenToFloat(@Nonnull LLongToFloatFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsFloat(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToDoubleFunctionX<X> thenToDouble(@Nonnull LLongToDoubleFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsDouble(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongToCharFunctionX<X> thenToChar(@Nonnull LLongToCharFunctionX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doApplyAsChar(this.doApplyAsLong(l));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LLongPredicateX<X> thenToBoolean(@Nonnull LLongPredicateX<X> after) {
		Null.nonNullArg(after, "after");
		return l -> after.doTest(this.doApplyAsLong(l));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <X extends Throwable> LLongUnaryOperatorX<X> identity() {
		return t -> t;
	}
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongUnaryOperator nestingLongUnaryOp() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongUnaryOperatorX<RuntimeException> nestingLongUnaryOpX() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongUnaryOperator shovingLongUnaryOp() {
		return this::shovingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongUnaryOperatorX<RuntimeException> shovingLongUnaryOpX() {
		return this::shovingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LLongUnaryOperator handleLongUnaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return l -> this.handlingDoApplyAsLong(l, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LLongUnaryOperatorX<Y> handleLongUnaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return l -> this.handlingDoApplyAsLong(l, handling);
	}

	// </editor-fold>

}
