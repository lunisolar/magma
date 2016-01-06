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
 * Throwing functional interface (lambda) LTriBoolFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): boolean a1,boolean a2,boolean a3
 *
 * Co-domain: R
 *
 * @see LTriBoolFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriBoolFunctionX<R, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LTriBoolFunctionX: R doApply(boolean a1,boolean a2,boolean a3) throws X";

	@Nullable
	R doApply(boolean a1, boolean a2, boolean a3) throws X;

	default R tupleApply(LBoolTriple args) throws X {
		return doApply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(boolean a1, boolean a2, boolean a3) {
		try {
			return this.doApply(a1, a2, a3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default R shovingDoApply(boolean a1, boolean a2, boolean a3) {
		return ((LTriBoolFunctionX<R, RuntimeException>) this).doApply(a1, a2, a3);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> R handlingDoApply(boolean a1, boolean a2, boolean a3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(boolean a1, boolean a2, boolean a3) throws X {
		return Null.requireNonNull(doApply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriBoolFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> captureTriBoolFunc(boolean a1, boolean a2, boolean a3) {
		return () -> this.doApply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> apply1st(@Nonnull LBoolFunctionX<R, X> func) {
		return (a1, a2, a3) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> apply2nd(@Nonnull LBoolFunctionX<R, X> func) {
		return (a1, a2, a3) -> func.doApply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> apply3rd(@Nonnull LBoolFunctionX<R, X> func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> lX(final @Nonnull LTriBoolFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LTriBoolFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <R, X extends Throwable> R call(boolean a1, boolean a2, boolean a3, final @Nonnull LTriBoolFunctionX<R, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2, a3);
	}

	static <R, X extends Throwable> R shoving(boolean a1, boolean a2, boolean a3, final @Nonnull LTriBoolFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApply(a1, a2, a3);
	}

	static <R, X extends Throwable> R nesting(boolean a1, boolean a2, boolean a3, final @Nonnull LTriBoolFunctionX<R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApply(a1, a2, a3);
	}

	static <R, X extends Throwable, Y extends Throwable> R handling(boolean a1, boolean a2, boolean a3, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LTriBoolFunctionX<R, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApply(a1, a2, a3, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> wrapX(final @Nonnull LTriBoolFunction<R> other) {
		return (LTriBoolFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> safe() {
		return Function4U::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R, X extends Throwable, Y extends Throwable> LSupplierX<LTriBoolFunctionX<R, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R, X extends Throwable> LTriBoolFunctionX<R, X> safe(final @Nullable LTriBoolFunctionX<R, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R, X extends Throwable, Y extends Throwable> LSupplierX<LTriBoolFunctionX<R, X>, Y> safeSupplier(final @Nullable LSupplierX<LTriBoolFunctionX<R, X>, Y> supplier) {
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
	default LTriBoolFunctionX<R, X> triBoolFuncComposeBoolean(@Nonnull final LLogicalOperatorX<X> before1, @Nonnull final LLogicalOperatorX<X> before2, @Nonnull final LLogicalOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final boolean v1, final boolean v2, final boolean v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunctionX<V1, V2, V3, R, X> triBoolFuncCompose(@Nonnull final LPredicateX<? super V1, X> before1, @Nonnull final LPredicateX<? super V2, X> before2, @Nonnull final LPredicateX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doApply(before1.doTest(v1), before2.doTest(v2), before3.doTest(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriBoolFunctionX<V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (boolean a1, boolean a2, boolean a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriBoolConsumerX<X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return (boolean a1, boolean a2, boolean a3) -> after.doAccept(this.doApply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTriBoolFunction<R> nestingTriBoolFunc() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LTriBoolFunctionX<R, RuntimeException> nestingTriBoolFuncX() {
		return this::nestingDoApply;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriBoolFunction<R> shovingTriBoolFunc() {
		return this::shovingDoApply;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriBoolFunctionX<R, RuntimeException> shovingTriBoolFuncX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LTriBoolFunctionX<R, X> nonNullTriBoolFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LTriBoolFunction<R> handleTriBoolFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (boolean a1, boolean a2, boolean a3) -> this.handlingDoApply(a1, a2, a3, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LTriBoolFunctionX<R, Y> handleTriBoolFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (boolean a1, boolean a2, boolean a3) -> this.handlingDoApply(a1, a2, a3, handling);
	}

	// </editor-fold>

}
