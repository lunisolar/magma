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

package eu.lunisolar.magma.func.function.from;
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
 * Throwing functional interface (lambda) LBiObjBooleanFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 t1,T2 t2, boolean b
 *
 * Co-domain: R
 *
 * @see LBiObjBooleanFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjBooleanFunctionX<T1, T2, R, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LBiObjBooleanFunctionX: R doApply(T1 t1,T2 t2, boolean b) throws X";

	@Nullable
	public R doApply(T1 t1, T2 t2, boolean b) throws X;

	default R nestingDoApply(T1 t1, T2 t2, boolean b) {
		try {
			return this.doApply(t1, t2, b);
		} catch (RuntimeException | Error e) {
			throw e;
		} catch (Throwable e) {
			throw new NestedException(e);
		}
	}

	default R shovingDoApply(T1 t1, T2 t2, boolean b) {
		return ((LBiObjBooleanFunctionX<T1, T2, R, RuntimeException>) this).doApply(t1, t2, b);
	}

	default <Y extends Throwable> R handlingDoApply(T1 t1, T2 t2, boolean b, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(t1, t2, b);
		} catch (Throwable e) {
			throw Handler.handleOrNest(e, handling);
		}
	}

	public static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T1 t1, T2 t2, boolean b) throws X {
		return Null.requireNonNull(doApply(t1, t2, b), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjBooleanFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> capture(T1 t1, T2 t2, boolean b) {
		return () -> this.doApply(t1, t2, b);
	}

	public static <T1, T2, R, X extends Throwable> LBiObjBooleanFunctionX<T1, T2, R, X> constant(R r) {
		return (t1, t2, b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, R, X extends Throwable> LBiObjBooleanFunctionX<T1, T2, R, X> lX(final @Nonnull LBiObjBooleanFunctionX<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, R, X extends Throwable> LBiObjBooleanFunctionX<T1, T2, R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LBiObjBooleanFunctionX<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, R, X extends Throwable> LBiObjBooleanFunctionX<T1, T2, R, X> wrapX(final @Nonnull LBiObjBooleanFunction<T1, T2, R> other) {
		return (LBiObjBooleanFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiObjBooleanFunctionX<V1, V2, R, X> fromBoolean(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LBooleanUnaryOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final V1 v1, final V2 v2, final boolean v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsBoolean(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriFunctionX<V1, V2, V3, R, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LPredicateX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsBoolean(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjBooleanFunctionX<T1, T2, V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2, boolean b) -> after.doApply(this.doApply(t1, t2, b));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjBooleanConsumerX<T1, T2, X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2, boolean b) -> after.doAccept(this.doApply(t1, t2, b));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjBooleanFunction<T1, T2, R> nest() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjBooleanFunctionX<T1, T2, R, RuntimeException> nestX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjBooleanFunction<T1, T2, R> shove() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjBooleanFunctionX<T1, T2, R, RuntimeException> shoveX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LBiObjBooleanFunctionX<T1, T2, R, X> nonNullableX() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	@Nonnull
	default LBiObjBooleanFunction<T1, T2, R> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 t1, T2 t2, boolean b) -> this.handlingDoApply(t1, t2, b, handling);
	}

	@Nonnull
	default <Y extends Throwable> LBiObjBooleanFunctionX<T1, T2, R, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 t1, T2 t2, boolean b) -> this.handlingDoApply(t1, t2, b, handling);
	}

	// </editor-fold>

}
