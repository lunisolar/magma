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

package eu.lunisolar.magma.func.operator.ternary;
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
 * Throwing functional interface (lambda) LLogicalTernaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): boolean b1,boolean b2,boolean b3
 *
 * Co-domain: none
 *
 * @see LLogicalTernaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLogicalTernaryOperatorX<X extends Throwable> extends MetaOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LLogicalTernaryOperatorX: boolean doApply(boolean b1,boolean b2,boolean b3) throws X";

	boolean doApply(boolean b1, boolean b2, boolean b3) throws X;

	default boolean nestingDoApply(boolean b1, boolean b2, boolean b3) {
		try {
			return this.doApply(b1, b2, b3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoApply(boolean b1, boolean b2, boolean b3) {
		return ((LLogicalTernaryOperatorX<RuntimeException>) this).doApply(b1, b2, b3);
	}

	default <Y extends Throwable> boolean handlingDoApply(boolean b1, boolean b2, boolean b3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(b1, b2, b3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApply(boolean b1, boolean b2, boolean b3) throws X {
		return doApply(b1, b2, b3);
	}

	/** For convinience boolean operator is also special case of predicate. */
	default boolean doTest(boolean b1, boolean b2, boolean b3) throws X {
		return doApply(b1, b2, b3);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLogicalTernaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> captureLogicalTernaryOp(boolean b1, boolean b2, boolean b3) {
		return () -> this.doApply(b1, b2, b3);
	}

	static <X extends Throwable> LLogicalTernaryOperatorX<X> constant(boolean r) {
		return (b1, b2, b3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLogicalTernaryOperatorX<X> lX(final @Nonnull LLogicalTernaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LLogicalTernaryOperatorX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LLogicalTernaryOperatorX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LLogicalTernaryOperatorX<X> wrapX(final @Nonnull LLogicalTernaryOperator other) {
		return (LLogicalTernaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LLogicalTernaryOperatorX<X> negate() {
		return (boolean b1, boolean b2, boolean b3) -> !doApply(b1, b2, b3);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLogicalTernaryOperatorX<X> and(@Nonnull LLogicalTernaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2, boolean b3) -> doApply(b1, b2, b3) && other.doApply(b1, b2, b3);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LLogicalTernaryOperatorX<X> or(@Nonnull LLogicalTernaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2, boolean b3) -> doApply(b1, b2, b3) || other.doApply(b1, b2, b3);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LLogicalTernaryOperatorX<X> xor(@Nonnull LLogicalTernaryOperatorX<X> other) {
		Null.nonNullArg(other, "other");
		return (boolean b1, boolean b2, boolean b3) -> doApply(b1, b2, b3) ^ other.doApply(b1, b2, b3);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	static <X extends Throwable> LLogicalTernaryOperatorX<X> isEqual(final boolean v1, final boolean v2, final boolean v3) {
		return (b1, b2, b3) -> (b1 == v1) && (b2 == v2) && (b3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default LLogicalTernaryOperatorX<X> logicalTernaryOpFromBoolean(@Nonnull final LLogicalOperatorX<X> before1, @Nonnull final LLogicalOperatorX<X> before2, @Nonnull final LLogicalOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final boolean v1, final boolean v2, final boolean v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	/**
	 * Allows to manipulate the domain of the function.
	 */
	@Nonnull
	default <V1, V2, V3> LTriPredicateX<V1, V2, V3, X> logicalTernaryOpFrom(@Nonnull final LPredicateX<? super V1, X> before1, @Nonnull final LPredicateX<? super V2, X> before2, @Nonnull final LPredicateX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doApply(before1.doTest(v1), before2.doTest(v2), before3.doTest(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBooleanTriFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (boolean b1, boolean b2, boolean b3) -> after.doApply(this.doApply(b1, b2, b3));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLogicalTernaryOperator nestingLogicalTernaryOp() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLogicalTernaryOperatorX<RuntimeException> nestingLogicalTernaryOpX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLogicalTernaryOperator shovingLogicalTernaryOp() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLogicalTernaryOperatorX<RuntimeException> shovingLogicalTernaryOpX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LLogicalTernaryOperator handleLogicalTernaryOp(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (boolean b1, boolean b2, boolean b3) -> this.handlingDoApply(b1, b2, b3, handling);
	}

	@Nonnull
	default <Y extends Throwable> LLogicalTernaryOperatorX<Y> handleLogicalTernaryOpX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (boolean b1, boolean b2, boolean b3) -> this.handlingDoApply(b1, b2, b3, handling);
	}

	// </editor-fold>

}
