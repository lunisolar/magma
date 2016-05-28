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
 * Non-throwing functional interface (lambda) LToFloatBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: float
 *
 * @see LToFloatBiFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToFloatBiFunction<T1, T2> extends LToFloatBiFunctionX<T1, T2, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LToFloatBiFunction: float doApplyAsFloat(T1 a1,T2 a2)";

	float doApplyAsFloat(T1 a1, T2 a2);

	default float tupleApplyAsFloat(LPair<T1, T2> args) {
		return doApplyAsFloat(args.first(), args.second());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoApplyAsFloat(T1 a1, T2 a2) {
		return this.doApplyAsFloat(a1, a2);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default float shovingDoApplyAsFloat(T1 a1, T2 a2) {
		return this.doApplyAsFloat(a1, a2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFloat(T1 a1, T2 a2) {
		return doApplyAsFloat(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToFloatBiFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LFloatSupplier captureToFloatBiFunc(T1 a1, T2 a2) {
		return () -> this.doApplyAsFloat(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LToFloatBiFunction<T1, T2> constant(float r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LToFloatBiFunction<T1, T2> apply1stAsFloat(@Nonnull LToFloatFunction<T1> func) {
		return (a1, a2) -> func.doApplyAsFloat(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LToFloatBiFunction<T1, T2> apply2ndAsFloat(@Nonnull LToFloatFunction<T2> func) {
		return (a1, a2) -> func.doApplyAsFloat(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LToFloatBiFunction<T1, T2> l(final @Nonnull LToFloatBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> V1<T2, T1> l1(final @Nonnull V1<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> float call(T1 a1, T2 a2, final @Nonnull LToFloatBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsFloat(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LToFloatBiFunction<T1, T2> wrap(final @Nonnull LToFloatBiFunctionX<T1, T2, X> other) {
		return other::nestingDoApplyAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceFloat). */
	@Nonnull
	static <T1, T2> LToFloatBiFunction<T1, T2> safe() {
		return Function4U::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LToFloatBiFunction<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LToFloatBiFunction<T1, T2> safe(final @Nullable LToFloatBiFunction<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LToFloatBiFunction<T1, T2>> safeSupplier(final @Nullable LSupplier<LToFloatBiFunction<T1, T2>> supplier) {
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
	default <V1, V2> LToFloatBiFunction<V1, V2> toFloatBiFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsFloat(before1.doApply(v1), before2.doApply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LFloatFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsFloat(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToFloatBiFunction<T1, T2> nestingToFloatBiFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LToFloatBiFunctionX<T1, T2, RuntimeException> nestingToFloatBiFuncX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToFloatBiFunction<T1, T2> shovingToFloatBiFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToFloatBiFunctionX<T1, T2, RuntimeException> shovingToFloatBiFuncX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToFloatBiFunction for method references. */
	@FunctionalInterface
	interface V1<T2, T1> extends LToFloatBiFunction<T1, T2> {

		float doApplyAsFloatV1(T2 a2, T1 a1);

		@Override
		default float doApplyAsFloat(T1 a1, T2 a2) {
			return this.doApplyAsFloatV1(a2, a1);
		}
	}

	// </editor-fold>

}
