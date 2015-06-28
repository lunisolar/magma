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
 * Throwing functional interface (lambda) LIntBiFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): int i1,int i2
 *
 * Co-domain: R
 *
 * @see LIntBiFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntBiFunctionX<R, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LIntBiFunctionX: R doApply(int i1,int i2) throws X";

	@Nullable
	R doApply(int i1, int i2) throws X;

	default R nestingDoApply(int i1, int i2) {
		try {
			return this.doApply(i1, i2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default R shovingDoApply(int i1, int i2) {
		return ((LIntBiFunctionX<R, RuntimeException>) this).doApply(i1, i2);
	}

	default <Y extends Throwable> R handlingDoApply(int i1, int i2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(i1, i2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	static final LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(int i1, int i2) throws X {
		return Null.requireNonNull(doApply(i1, i2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntBiFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> capture(int i1, int i2) {
		return () -> this.doApply(i1, i2);
	}

	static <R, X extends Throwable> LIntBiFunctionX<R, X> constant(R r) {
		return (i1, i2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LIntBiFunctionX<R, X> lX(final @Nonnull LIntBiFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LIntBiFunctionX<R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LIntBiFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LIntBiFunctionX<R, X> wrapX(final @Nonnull LIntBiFunction<R> other) {
		return (LIntBiFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntBiFunctionX<R, X> fromInt(@Nonnull final LIntUnaryOperatorX<X> before1, @Nonnull final LIntUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final int v1, final int v2) -> this.doApply(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiFunctionX<V1, V2, R, X> from(@Nonnull final LToIntFunctionX<? super V1, X> before1, @Nonnull final LToIntFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doApply(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntBiFunctionX<V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (int i1, int i2) -> after.doApply(this.doApply(i1, i2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntBiConsumerX<X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return (int i1, int i2) -> after.doAccept(this.doApply(i1, i2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntBiFunction<R> nest() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntBiFunctionX<R, RuntimeException> nestX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBiFunction<R> shove() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBiFunctionX<R, RuntimeException> shoveX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	@Nonnull
	default LIntBiFunctionX<R, X> nonNullableX() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	@Nonnull
	default LIntBiFunction<R> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (int i1, int i2) -> this.handlingDoApply(i1, i2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LIntBiFunctionX<R, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (int i1, int i2) -> this.handlingDoApply(i1, i2, handling);
	}

	// </editor-fold>

}
