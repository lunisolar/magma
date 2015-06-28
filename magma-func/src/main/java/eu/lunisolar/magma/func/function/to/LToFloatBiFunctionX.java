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

package eu.lunisolar.magma.func.function.to;
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
 * Throwing functional interface (lambda) LToFloatBiFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 t1,T2 t2
 *
 * Co-domain: none
 *
 * @see LToFloatBiFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToFloatBiFunctionX<T1, T2, X extends Throwable> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	static final String DESCRIPTION = "LToFloatBiFunctionX: float doApplyAsFloat(T1 t1,T2 t2) throws X";

	float doApplyAsFloat(T1 t1, T2 t2) throws X;

	default float nestingDoApplyAsFloat(T1 t1, T2 t2) {
		try {
			return this.doApplyAsFloat(t1, t2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	default float shovingDoApplyAsFloat(T1 t1, T2 t2) {
		return ((LToFloatBiFunctionX<T1, T2, RuntimeException>) this).doApplyAsFloat(t1, t2);
	}

	default <Y extends Throwable> float handlingDoApplyAsFloat(T1 t1, T2 t2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsFloat(t1, t2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(T1 t1, T2 t2) throws X {
		return doApplyAsFloat(t1, t2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToFloatBiFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplierX<X> capture(T1 t1, T2 t2) {
		return () -> this.doApplyAsFloat(t1, t2);
	}

	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> constant(float r) {
		return (t1, t2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> lX(final @Nonnull LToFloatBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LToFloatBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> wrapX(final @Nonnull LToFloatBiFunction<T1, T2> other) {
		return (LToFloatBiFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LToFloatBiFunctionX<V1, V2, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final V2 v2) -> this.doApplyAsFloat(before1.doApply(v1), before2.doApply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunctionX<T1, T2, V, X> then(@Nonnull LFloatFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2) -> after.doApply(this.doApplyAsFloat(t1, t2));
	}

	// </editor-fold>
	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToFloatBiFunction<T1, T2> nest() {
		return this::nestingDoApplyAsFloat;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToFloatBiFunctionX<T1, T2, RuntimeException> nestX() {
		return this::nestingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToFloatBiFunction<T1, T2> shove() {
		return this::shovingDoApplyAsFloat;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToFloatBiFunctionX<T1, T2, RuntimeException> shoveX() {
		return this::shovingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	@Nonnull
	default LToFloatBiFunction<T1, T2> handle(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 t1, T2 t2) -> this.handlingDoApplyAsFloat(t1, t2, handling);
	}

	@Nonnull
	default <Y extends Throwable> LToFloatBiFunctionX<T1, T2, Y> handleX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 t1, T2 t2) -> this.handlingDoApplyAsFloat(t1, t2, handling);
	}

	// </editor-fold>

}
