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
 * Non-throwing functional interface (lambda) LBiObjBoolFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,T2 a2,boolean a3
 *
 * Co-domain: R
 *
 * @see LBiObjBoolFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjBoolFunction<T1, T2, R> extends LBiObjBoolFunctionX<T1, T2, R, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiObjBoolFunction: R doApply(T1 a1,T2 a2,boolean a3)";

	@Nullable
	R doApply(T1 a1, T2 a2, boolean a3);

	default R tupleApply(LBiObjBoolTriple<T1, T2> args) {
		return doApply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(T1 a1, T2 a2, boolean a3) {
		return this.doApply(a1, a2, a3);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default R shovingDoApply(T1 a1, T2 a2, boolean a3) {
		return this.doApply(a1, a2, a3);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T1 a1, T2 a2, boolean a3) {
		return Null.requireNonNull(doApply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjBoolFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBiObjBoolFunc(T1 a1, T2 a2, boolean a3) {
		return () -> this.doApply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, R> LBiObjBoolFunction<T1, T2, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjBoolFunction<T1, T2, R> apply1st(@Nonnull LFunction<T1, R> func) {
		return (a1, a2, a3) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjBoolFunction<T1, T2, R> apply2nd(@Nonnull LFunction<T2, R> func) {
		return (a1, a2, a3) -> func.doApply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjBoolFunction<T1, T2, R> apply3rd(@Nonnull LBoolFunction<R> func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjBoolFunction<T1, T2, R> l(final @Nonnull LBiObjBoolFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> V1<T1, T2, R> l1(final @Nonnull V1<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> V2<T2, T1, R> l2(final @Nonnull V2<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> V3<T2, T1, R> l3(final @Nonnull V3<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> V4<T1, T2, R> l4(final @Nonnull V4<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> V5<T2, T1, R> l5(final @Nonnull V5<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, R> R call(T1 a1, T2 a2, boolean a3, final @Nonnull LBiObjBoolFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, R, X extends Throwable> LBiObjBoolFunction<T1, T2, R> wrap(final @Nonnull LBiObjBoolFunctionX<T1, T2, R, X> other) {
		return other::nestingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produce). */
	@Nonnull
	static <T1, T2, R> LBiObjBoolFunction<T1, T2, R> safe() {
		return Function4U::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R> LSupplier<LBiObjBoolFunction<T1, T2, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, R> LBiObjBoolFunction<T1, T2, R> safe(final @Nullable LBiObjBoolFunction<T1, T2, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R> LSupplier<LBiObjBoolFunction<T1, T2, R>> safeSupplier(final @Nullable LSupplier<LBiObjBoolFunction<T1, T2, R>> supplier) {
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
	default <V1, V2> LBiObjBoolFunction<V1, V2, R> biObjBoolFuncComposeBool(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LLogicalOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> biObjBoolFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LPredicate<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doTest(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjBoolFunction<T1, T2, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjBoolConsumer<T1, T2> then(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doAccept(this.doApply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjBoolFunction<T1, T2, R> nestingBiObjBoolFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjBoolFunctionX<T1, T2, R, RuntimeException> nestingBiObjBoolFuncX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjBoolFunction<T1, T2, R> shovingBiObjBoolFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjBoolFunctionX<T1, T2, R, RuntimeException> shovingBiObjBoolFuncX() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiObjBoolFunction<T1, T2, R> nonNullBiObjBoolFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjBoolFunction for method references. */
	@FunctionalInterface
	interface V1<T1, T2, R> extends LBiObjBoolFunction<T1, T2, R> {
		@Nullable
		R doApplyV1(T1 a1, boolean a3, T2 a2);

		@Override
		default R doApply(T1 a1, T2 a2, boolean a3) {
			return this.doApplyV1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjBoolFunction for method references. */
	@FunctionalInterface
	interface V2<T2, T1, R> extends LBiObjBoolFunction<T1, T2, R> {
		@Nullable
		R doApplyV2(T2 a2, T1 a1, boolean a3);

		@Override
		default R doApply(T1 a1, T2 a2, boolean a3) {
			return this.doApplyV2(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjBoolFunction for method references. */
	@FunctionalInterface
	interface V3<T2, T1, R> extends LBiObjBoolFunction<T1, T2, R> {
		@Nullable
		R doApplyV3(T2 a2, boolean a3, T1 a1);

		@Override
		default R doApply(T1 a1, T2 a2, boolean a3) {
			return this.doApplyV3(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjBoolFunction for method references. */
	@FunctionalInterface
	interface V4<T1, T2, R> extends LBiObjBoolFunction<T1, T2, R> {
		@Nullable
		R doApplyV4(boolean a3, T1 a1, T2 a2);

		@Override
		default R doApply(T1 a1, T2 a2, boolean a3) {
			return this.doApplyV4(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjBoolFunction for method references. */
	@FunctionalInterface
	interface V5<T2, T1, R> extends LBiObjBoolFunction<T1, T2, R> {
		@Nullable
		R doApplyV5(boolean a3, T2 a2, T1 a1);

		@Override
		default R doApply(T1 a1, T2 a2, boolean a3) {
			return this.doApplyV5(a3, a2, a1);
		}
	}

	// </editor-fold>

}
