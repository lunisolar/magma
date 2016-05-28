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
import java.util.function.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LToCharBiFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: char
 *
 * @see LToCharBiFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToCharBiFunctionX<T1, T2, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LToCharBiFunctionX: char doApplyAsChar(T1 a1,T2 a2) throws X";

	char doApplyAsChar(T1 a1, T2 a2) throws X;

	default char tupleApplyAsChar(LPair<T1, T2> args) throws X {
		return doApplyAsChar(args.first(), args.second());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingDoApplyAsChar(T1 a1, T2 a2) {
		try {
			return this.doApplyAsChar(a1, a2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default char shovingDoApplyAsChar(T1 a1, T2 a2) {
		return ((LToCharBiFunctionX<T1, T2, RuntimeException>) this).doApplyAsChar(a1, a2);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> char handlingDoApplyAsChar(T1 a1, T2 a2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApplyAsChar(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(T1 a1, T2 a2) throws X {
		return doApplyAsChar(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToCharBiFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplierX<X> captureToCharBiFunc(T1 a1, T2 a2) {
		return () -> this.doApplyAsChar(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, X extends Throwable> LToCharBiFunctionX<T1, T2, X> constant(char r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToCharBiFunctionX<T1, T2, X> apply1stAsCharacter(@Nonnull LToCharFunctionX<T1, X> func) {
		return (a1, a2) -> func.doApplyAsChar(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToCharBiFunctionX<T1, T2, X> apply2ndAsCharacter(@Nonnull LToCharFunctionX<T2, X> func) {
		return (a1, a2) -> func.doApplyAsChar(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToCharBiFunctionX<T1, T2, X> lX(final @Nonnull LToCharBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToCharBiFunctionX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LToCharBiFunctionX<T1, T2, X> lambda) {
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

	static <T1, T2, X extends Throwable> char call(T1 a1, T2 a2, final @Nonnull LToCharBiFunctionX<T1, T2, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsChar(a1, a2);
	}

	static <T1, T2, X extends Throwable> char shoving(T1 a1, T2 a2, final @Nonnull LToCharBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApplyAsChar(a1, a2);
	}

	static <T1, T2, X extends Throwable> char nesting(T1 a1, T2 a2, final @Nonnull LToCharBiFunctionX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApplyAsChar(a1, a2);
	}

	static <T1, T2, X extends Throwable, Y extends Throwable> char handling(T1 a1, T2 a2, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LToCharBiFunctionX<T1, T2, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApplyAsChar(a1, a2, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToCharBiFunctionX<T1, T2, X> wrapX(final @Nonnull LToCharBiFunction<T1, T2> other) {
		return (LToCharBiFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceChar). */
	@Nonnull
	static <T1, T2, X extends Throwable> LToCharBiFunctionX<T1, T2, X> safe() {
		return Function4U::produceChar;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LToCharBiFunctionX<T1, T2, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToCharBiFunctionX<T1, T2, X> safe(final @Nullable LToCharBiFunctionX<T1, T2, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LToCharBiFunctionX<T1, T2, X>, Y> safeSupplier(final @Nullable LSupplierX<LToCharBiFunctionX<T1, T2, X>, Y> supplier) {
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
	default <V1, V2> LToCharBiFunctionX<V1, V2, X> toCharBiFuncCompose(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsChar(before1.doApply(v1), before2.doApply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunctionX<T1, T2, V, X> then(@Nonnull LCharFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsChar(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToCharBiFunction<T1, T2> nestingToCharBiFunc() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToCharBiFunctionX<T1, T2, RuntimeException> nestingToCharBiFuncX() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToCharBiFunction<T1, T2> shovingToCharBiFunc() {
		return this::shovingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToCharBiFunctionX<T1, T2, RuntimeException> shovingToCharBiFuncX() {
		return this::shovingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LToCharBiFunction<T1, T2> handleToCharBiFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> this.handlingDoApplyAsChar(a1, a2, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LToCharBiFunctionX<T1, T2, Y> handleToCharBiFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (a1, a2) -> this.handlingDoApplyAsChar(a1, a2, handling);
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToCharBiFunctionX for method references. */
	@FunctionalInterface
	interface V1<T2, T1, X extends Throwable> extends LToCharBiFunctionX<T1, T2, X> {

		char doApplyAsCharV1(T2 a2, T1 a1) throws X;

		@Override
		default char doApplyAsChar(T1 a1, T2 a2) throws X {
			return this.doApplyAsCharV1(a2, a1);
		}
	}

	// </editor-fold>

}
