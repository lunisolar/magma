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
 * Throwing functional interface (lambda) LToFloatBiFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: float
 *
 * @see LToFloatBiFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToFloatBiFunctionX<T1, T2, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LToFloatBiFunctionX: float doApplyAsFloat(T1 a1,T2 a2) throws X";

	float doApplyAsFloat(T1 a1, T2 a2) throws X;

	default Float tupleApplyAsFloat(LPair<T1, T2> args) throws X {
		return doApplyAsFloat(args.first(), args.second());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoApplyAsFloat(T1 a1, T2 a2) {
		try {
			return this.doApplyAsFloat(a1, a2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default float shovingDoApplyAsFloat(T1 a1, T2 a2) {
		return ((LToFloatBiFunctionX<T1, T2, RuntimeException>) this).doApplyAsFloat(a1, a2);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> float handlingDoApplyAsFloat(T1 a1, T2 a2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsFloat(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(T1 a1, T2 a2) throws X {
		return doApplyAsFloat(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToFloatBiFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplierX<X> captureToFloatBiFunc(T1 a1, T2 a2) {
		return () -> this.doApplyAsFloat(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> constant(float r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> apply1stAsFloat(@Nonnull LToFloatFunctionX<T1, X> func) {
		return (a1, a2) -> func.doApplyAsFloat(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> apply2ndAsFloat(@Nonnull LToFloatFunctionX<T2, X> func) {
		return (a1, a2) -> func.doApplyAsFloat(a2);
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

	static <T1, T2, X extends Throwable> float call(T1 a1, T2 a2, final @Nonnull LToFloatBiFunctionX<T1, T2, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsFloat(a1, a2);
	}

	static <T1, T2, X extends Throwable> float shoving(T1 a1, T2 a2, final @Nonnull LToFloatBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApplyAsFloat(a1, a2);
	}

	static <T1, T2, X extends Throwable> float nesting(T1 a1, T2 a2, final @Nonnull LToFloatBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApplyAsFloat(a1, a2);
	}

	static <T1, T2, X extends Throwable, Y extends Throwable> float handling(T1 a1, T2 a2, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LToFloatBiFunctionX<T1, T2, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApplyAsFloat(a1, a2, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> wrapX(final @Nonnull LToFloatBiFunction<T1, T2> other) {
		return (LToFloatBiFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> safe() {
		return Function4U::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LToFloatBiFunctionX<T1, T2, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunctionX<T1, T2, X> safe(final @Nullable LToFloatBiFunctionX<T1, T2, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LToFloatBiFunctionX<T1, T2, X>, Y> safeSupplier(final @Nullable LSupplierX<LToFloatBiFunctionX<T1, T2, X>, Y> supplier) {
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
	default <V1, V2> LToFloatBiFunctionX<V1, V2, X> toFloatBiFuncCompose(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2) {
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
		return (T1 a1, T2 a2) -> after.doApply(this.doApplyAsFloat(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToFloatBiFunction<T1, T2> nestingToFloatBiFunc() {
		return this::nestingDoApplyAsFloat;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToFloatBiFunctionX<T1, T2, RuntimeException> nestingToFloatBiFuncX() {
		return this::nestingDoApplyAsFloat;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToFloatBiFunction<T1, T2> shovingToFloatBiFunc() {
		return this::shovingDoApplyAsFloat;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToFloatBiFunctionX<T1, T2, RuntimeException> shovingToFloatBiFuncX() {
		return this::shovingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LToFloatBiFunction<T1, T2> handleToFloatBiFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 a1, T2 a2) -> this.handlingDoApplyAsFloat(a1, a2, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LToFloatBiFunctionX<T1, T2, Y> handleToFloatBiFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 a1, T2 a2) -> this.handlingDoApplyAsFloat(a1, a2, handling);
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToFloatBiFunctionX for method references. */
	@FunctionalInterface
	interface V1<T1, T2, X extends Throwable> extends LToFloatBiFunctionX<T1, T2, X> {

		float apply1(T2 a2, T1 a1) throws X;

		@Override
		default float doApplyAsFloat(T1 a1, T2 a2) throws X {
			return this.apply1(a2, a1);
		}
	}

	// </editor-fold>

}
