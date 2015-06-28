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

package eu.lunisolar.magma.func.predicate;
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
 * Throwing functional interface (lambda) LBiObjBytePredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 t1,T2 t2, byte b
 *
 * Co-domain: none
 *
 * @see LBiObjBytePredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjBytePredicateX<T1, T2, X extends Throwable> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LBiObjBytePredicateX: boolean doTest(T1 t1,T2 t2, byte b) throws X";

	boolean doTest(T1 t1, T2 t2, byte b) throws X;

	default boolean nestingDoTest(T1 t1, T2 t2, byte b) {
		try {
			return this.doTest(t1, t2, b);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(T1 t1, T2 t2, byte b) {
		return ((LBiObjBytePredicateX<T1, T2, RuntimeException>) this).doTest(t1, t2, b);
	}

	default <Y extends Throwable> boolean handlingDoTest(T1 t1, T2 t2, byte b, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(t1, t2, b);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T1 t1, T2 t2, byte b) throws X {
		return doTest(t1, t2, b);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 t1, T2 t2, byte b) throws X {
		return doTest(t1, t2, b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjBytePredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(T1 t1, T2 t2, byte b) {
		return () -> this.doTest(t1, t2, b);
	}

	static <T1, T2, X extends Throwable> LBiObjBytePredicateX<T1, T2, X> constant(boolean r) {
		return (t1, t2, b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjBytePredicateX<T1, T2, X> lX(final @Nonnull LBiObjBytePredicateX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjBytePredicateX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LBiObjBytePredicateX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjBytePredicateX<T1, T2, X> wrapX(final @Nonnull LBiObjBytePredicate<T1, T2> other) {
		return (LBiObjBytePredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBiObjBytePredicateX<T1, T2, X> negate() {
		return (T1 t1, T2 t2, byte b) -> !doTest(t1, t2, b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiObjBytePredicateX<T1, T2, X> and(@Nonnull LBiObjBytePredicateX<? super T1, ? super T2, X> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, byte b) -> doTest(t1, t2, b) && other.doTest(t1, t2, b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiObjBytePredicateX<T1, T2, X> or(@Nonnull LBiObjBytePredicateX<? super T1, ? super T2, X> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, byte b) -> doTest(t1, t2, b) || other.doTest(t1, t2, b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiObjBytePredicateX<T1, T2, X> xor(@Nonnull LBiObjBytePredicateX<? super T1, ? super T2, X> other) {
		Null.nonNullArg(other, "other");
		return (T1 t1, T2 t2, byte b) -> doTest(t1, t2, b) ^ other.doTest(t1, t2, b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjBytePredicateX<T1, T2, X> isEqual(final T1 v1, final T2 v2, final byte v3) {
		return (t1, t2, b) -> (t1 == null ? v1 == null : t1.equals(v1)) && (t2 == null ? v2 == null : t2.equals(v2)) && (b == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiObjBytePredicateX<V1, V2, X> fromByte(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LByteUnaryOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final V1 v1, final V2 v2, final byte v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsByte(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriPredicateX<V1, V2, V3, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LToByteFunctionX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsByte(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LBiObjByteFunctionX<T1, T2, V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2, byte b) -> after.doApply(this.doTest(t1, t2, b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjBytePredicate<T1, T2> nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjBytePredicateX<T1, T2, RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjBytePredicate<T1, T2> shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjBytePredicateX<T1, T2, RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LBiObjBytePredicate<T1, T2> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 t1, T2 t2, byte b) -> this.handlingDoTest(t1, t2, b, handling);
	}

	@Nonnull
	default <Y extends Throwable> LBiObjBytePredicateX<T1, T2, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 t1, T2 t2, byte b) -> this.handlingDoTest(t1, t2, b, handling);
	}

	// </editor-fold>

}
