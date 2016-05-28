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
 * Throwing functional interface (lambda) LBiObjByteFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,T2 a2,byte a3
 *
 * Co-domain: R
 *
 * @see LBiObjByteFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjByteFunctionX<T1, T2, R, X extends Throwable> extends MetaFunction, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LBiObjByteFunctionX: R doApply(T1 a1,T2 a2,byte a3) throws X";

	@Nullable
	R doApply(T1 a1, T2 a2, byte a3) throws X;

	default R tupleApply(LBiObjByteTriple<T1, T2> args) throws X {
		return doApply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(T1 a1, T2 a2, byte a3) {
		try {
			return this.doApply(a1, a2, a3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default R shovingDoApply(T1 a1, T2 a2, byte a3) {
		return ((LBiObjByteFunctionX<T1, T2, R, RuntimeException>) this).doApply(a1, a2, a3);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> R handlingDoApply(T1 a1, T2 a2, byte a3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doApply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T1 a1, T2 a2, byte a3) throws X {
		return Null.requireNonNull(doApply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjByteFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplierX<R, X> captureBiObjByteFunc(T1 a1, T2 a2, byte a3) {
		return () -> this.doApply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> apply1st(@Nonnull LFunctionX<T1, R, X> func) {
		return (a1, a2, a3) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> apply2nd(@Nonnull LFunctionX<T2, R, X> func) {
		return (a1, a2, a3) -> func.doApply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> apply3rd(@Nonnull LByteFunctionX<R, X> func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> lX(final @Nonnull LBiObjByteFunctionX<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> lX(@Nonnull Class<X> xClass, final @Nonnull LBiObjByteFunctionX<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R, X extends Throwable> V1<T1, T2, R, X> lX1(final @Nonnull V1<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R, X extends Throwable> V1<T1, T2, R, X> lX1(@Nonnull Class<X> xClass, final @Nonnull V1<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R, X extends Throwable> V2<T2, T1, R, X> lX2(final @Nonnull V2<T2, T1, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R, X extends Throwable> V2<T2, T1, R, X> lX2(@Nonnull Class<X> xClass, final @Nonnull V2<T2, T1, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R, X extends Throwable> V3<T2, T1, R, X> lX3(final @Nonnull V3<T2, T1, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R, X extends Throwable> V3<T2, T1, R, X> lX3(@Nonnull Class<X> xClass, final @Nonnull V3<T2, T1, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R, X extends Throwable> V4<T1, T2, R, X> lX4(final @Nonnull V4<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R, X extends Throwable> V4<T1, T2, R, X> lX4(@Nonnull Class<X> xClass, final @Nonnull V4<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R, X extends Throwable> V5<T2, T1, R, X> lX5(final @Nonnull V5<T2, T1, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R, X extends Throwable> V5<T2, T1, R, X> lX5(@Nonnull Class<X> xClass, final @Nonnull V5<T2, T1, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, R, X extends Throwable> R call(T1 a1, T2 a2, byte a3, final @Nonnull LBiObjByteFunctionX<T1, T2, R, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2, a3);
	}

	static <T1, T2, R, X extends Throwable> R shoving(T1 a1, T2 a2, byte a3, final @Nonnull LBiObjByteFunctionX<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoApply(a1, a2, a3);
	}

	static <T1, T2, R, X extends Throwable> R nesting(T1 a1, T2 a2, byte a3, final @Nonnull LBiObjByteFunctionX<T1, T2, R, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoApply(a1, a2, a3);
	}

	static <T1, T2, R, X extends Throwable, Y extends Throwable> R handling(T1 a1, T2 a2, byte a3, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LBiObjByteFunctionX<T1, T2, R, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoApply(a1, a2, a3, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> wrapX(final @Nonnull LBiObjByteFunction<T1, T2, R> other) {
		return (LBiObjByteFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produce). */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> safe() {
		return Function4U::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R, X extends Throwable, Y extends Throwable> LSupplierX<LBiObjByteFunctionX<T1, T2, R, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjByteFunctionX<T1, T2, R, X> safe(final @Nullable LBiObjByteFunctionX<T1, T2, R, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R, X extends Throwable, Y extends Throwable> LSupplierX<LBiObjByteFunctionX<T1, T2, R, X>, Y> safeSupplier(final @Nullable LSupplierX<LBiObjByteFunctionX<T1, T2, R, X>, Y> supplier) {
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
	default <V1, V2> LBiObjByteFunctionX<V1, V2, R, X> biObjByteFuncComposeByte(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LByteUnaryOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsByte(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunctionX<V1, V2, V3, R, X> biObjByteFuncCompose(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2,
			@Nonnull final LToByteFunctionX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsByte(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjByteFunctionX<T1, T2, V, X> then(@Nonnull LFunctionX<? super R, ? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjByteConsumerX<T1, T2, X> then(@Nonnull LConsumerX<? super R, X> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doAccept(this.doApply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjByteFunction<T1, T2, R> nestingBiObjByteFunc() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjByteFunctionX<T1, T2, R, RuntimeException> nestingBiObjByteFuncX() {
		return this::nestingDoApply;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjByteFunction<T1, T2, R> shovingBiObjByteFunc() {
		return this::shovingDoApply;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjByteFunctionX<T1, T2, R, RuntimeException> shovingBiObjByteFuncX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiObjByteFunctionX<T1, T2, R, X> nonNullBiObjByteFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LBiObjByteFunction<T1, T2, R> handleBiObjByteFunc(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> this.handlingDoApply(a1, a2, a3, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LBiObjByteFunctionX<T1, T2, R, Y> handleBiObjByteFuncX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (a1, a2, a3) -> this.handlingDoApply(a1, a2, a3, handling);
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjByteFunctionX for method references. */
	@FunctionalInterface
	interface V1<T1, T2, R, X extends Throwable> extends LBiObjByteFunctionX<T1, T2, R, X> {
		@Nullable
		R doApplyV1(T1 a1, byte a3, T2 a2) throws X;

		@Override
		default R doApply(T1 a1, T2 a2, byte a3) throws X {
			return this.doApplyV1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjByteFunctionX for method references. */
	@FunctionalInterface
	interface V2<T2, T1, R, X extends Throwable> extends LBiObjByteFunctionX<T1, T2, R, X> {
		@Nullable
		R doApplyV2(T2 a2, T1 a1, byte a3) throws X;

		@Override
		default R doApply(T1 a1, T2 a2, byte a3) throws X {
			return this.doApplyV2(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjByteFunctionX for method references. */
	@FunctionalInterface
	interface V3<T2, T1, R, X extends Throwable> extends LBiObjByteFunctionX<T1, T2, R, X> {
		@Nullable
		R doApplyV3(T2 a2, byte a3, T1 a1) throws X;

		@Override
		default R doApply(T1 a1, T2 a2, byte a3) throws X {
			return this.doApplyV3(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjByteFunctionX for method references. */
	@FunctionalInterface
	interface V4<T1, T2, R, X extends Throwable> extends LBiObjByteFunctionX<T1, T2, R, X> {
		@Nullable
		R doApplyV4(byte a3, T1 a1, T2 a2) throws X;

		@Override
		default R doApply(T1 a1, T2 a2, byte a3) throws X {
			return this.doApplyV4(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjByteFunctionX for method references. */
	@FunctionalInterface
	interface V5<T2, T1, R, X extends Throwable> extends LBiObjByteFunctionX<T1, T2, R, X> {
		@Nullable
		R doApplyV5(byte a3, T2 a2, T1 a1) throws X;

		@Override
		default R doApply(T1 a1, T2 a2, byte a3) throws X {
			return this.doApplyV5(a3, a2, a1);
		}
	}

	// </editor-fold>

}
