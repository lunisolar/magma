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
 * Throwing functional interface (lambda) LToIntBiFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: int
 *
 * @see LToIntBiFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToIntBiFunctionX<T1, T2, X extends Throwable> extends ToIntBiFunction<T1, T2>, MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LToIntBiFunctionX: int doApplyAsInt(T1 a1,T2 a2) throws X";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LToIntBiFunctionX interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(T1 a1, T2 a2) {
		return this.nestingDoApplyAsInt(a1, a2);
	}

	int doApplyAsInt(T1 a1, T2 a2) throws X;

	default Integer tupleApplyAsInt(LPair<T1, T2> args) throws X {
		return doApplyAsInt(args.first(), args.second());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(T1 a1, T2 a2) {
		try {
			return this.doApplyAsInt(a1, a2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(T1 a1, T2 a2) {
		return ((LToIntBiFunctionX<T1, T2, RuntimeException>) this).doApplyAsInt(a1, a2);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> int handlingDoApplyAsInt(T1 a1, T2 a2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsInt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(T1 a1, T2 a2) throws X {
		return doApplyAsInt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToIntBiFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplierX<X> captureToIntBiFunc(T1 a1, T2 a2) {
		return () -> this.doApplyAsInt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> constant(int r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> apply1stAsInt(@Nonnull LToIntFunctionX<T1, X> func) {
		return (a1, a2) -> func.doApplyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> apply2ndAsInt(@Nonnull LToIntFunctionX<T2, X> func) {
		return (a1, a2) -> func.doApplyAsInt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> lX(final @Nonnull LToIntBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LToIntBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V1<T2, T1, X> lX1(final @Nonnull V1<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V1<T2, T1, X> lX1(@Nonnull Class<X> xClass, final @Nonnull V1<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, X extends Throwable> int call(T1 a1, T2 a2, final @Nonnull LToIntBiFunctionX<T1, T2, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a1, a2);
	}

	static <T1, T2, X extends Throwable> int shoving(T1 a1, T2 a2, final @Nonnull LToIntBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApplyAsInt(a1, a2);
	}

	static <T1, T2, X extends Throwable> int nesting(T1 a1, T2 a2, final @Nonnull LToIntBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApplyAsInt(a1, a2);
	}

	static <T1, T2, X extends Throwable, Y extends Throwable> int handling(T1 a1, T2 a2, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LToIntBiFunctionX<T1, T2, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApplyAsInt(a1, a2, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> wrap(final ToIntBiFunction<T1, T2> other) {
		return other::applyAsInt;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> wrapX(final @Nonnull LToIntBiFunction<T1, T2> other) {
		return (LToIntBiFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> safe() {
		return Function4U::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LToIntBiFunctionX<T1, T2, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToIntBiFunctionX<T1, T2, X> safe(final @Nullable LToIntBiFunctionX<T1, T2, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LToIntBiFunctionX<T1, T2, X>, Y> safeSupplier(final @Nullable LSupplierX<LToIntBiFunctionX<T1, T2, X>, Y> supplier) {
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
	default <V1, V2> LToIntBiFunctionX<V1, V2, X> toIntBiFuncCompose(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final V2 v2) -> this.doApplyAsInt(before1.doApply(v1), before2.doApply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunctionX<T1, T2, V, X> then(@Nonnull LIntFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2) -> after.doApply(this.doApplyAsInt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToIntBiFunction<T1, T2> nestingToIntBiFunc() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToIntBiFunctionX<T1, T2, RuntimeException> nestingToIntBiFuncX() {
		return this::nestingDoApplyAsInt;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToIntBiFunction<T1, T2> shovingToIntBiFunc() {
		return this::shovingDoApplyAsInt;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToIntBiFunctionX<T1, T2, RuntimeException> shovingToIntBiFuncX() {
		return this::shovingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LToIntBiFunction<T1, T2> handleToIntBiFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 a1, T2 a2) -> this.handlingDoApplyAsInt(a1, a2, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LToIntBiFunctionX<T1, T2, Y> handleToIntBiFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 a1, T2 a2) -> this.handlingDoApplyAsInt(a1, a2, handling);
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToIntBiFunctionX for method references. */
	@FunctionalInterface
	interface V1<T1, T2, X extends Throwable> extends LToIntBiFunctionX<T1, T2, X> {

		int apply1(T2 a2, T1 a1) throws X;

		@Override
		default int doApplyAsInt(T1 a1, T2 a2) throws X {
			return this.apply1(a2, a1);
		}
	}

	// </editor-fold>

}
