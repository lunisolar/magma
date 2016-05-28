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
 * Non-throwing functional interface (lambda) LObjIntToIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: int
 *
 * @see LObjIntToIntFunctionX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntToIntFunction<T> extends LObjIntToIntFunctionX<T, RuntimeException>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LObjIntToIntFunction: int doApplyAsInt(T a1,int a2)";

	int doApplyAsInt(T a1, int a2);

	default int tupleApplyAsInt(LObjIntPair<T> args) {
		return doApplyAsInt(args.first(), args.second());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(T a1, int a2) {
		return this.doApplyAsInt(a1, a2);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(T a1, int a2) {
		return this.doApplyAsInt(a1, a2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(T a1, int a2) {
		return doApplyAsInt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntToIntFunction.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureObjIntToIntFunc(T a1, int a2) {
		return () -> this.doApplyAsInt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjIntToIntFunction<T> constant(int r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjIntToIntFunction<T> apply1stAsInt(@Nonnull LToIntFunction<T> func) {
		return (a1, a2) -> func.doApplyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjIntToIntFunction<T> apply2ndAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsInt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntToIntFunction<T> l(final @Nonnull LObjIntToIntFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> V1<T> l1(final @Nonnull V1<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> int call(T a1, int a2, final @Nonnull LObjIntToIntFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LObjIntToIntFunction<T> wrap(final @Nonnull LObjIntToIntFunctionX<T, X> other) {
		return other::nestingDoApplyAsInt;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceInt). */
	@Nonnull
	static <T> LObjIntToIntFunction<T> safe() {
		return Function4U::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntToIntFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjIntToIntFunction<T> safe(final @Nullable LObjIntToIntFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntToIntFunction<T>> safeSupplier(final @Nullable LSupplier<LObjIntToIntFunction<T>> supplier) {
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
	default <V1> LObjIntToIntFunction<V1> objIntToIntFuncComposeInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsInt(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToIntBiFunction<V1, V2> objIntToIntFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsInt(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntFunction<T, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsInt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjIntToIntFunction<T> nestingObjIntToIntFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjIntToIntFunctionX<T, RuntimeException> nestingObjIntToIntFuncX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjIntToIntFunction<T> shovingObjIntToIntFunc() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjIntToIntFunctionX<T, RuntimeException> shovingObjIntToIntFuncX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntToIntFunction for method references. */
	@FunctionalInterface
	interface V1<T> extends LObjIntToIntFunction<T> {

		int doApplyAsIntV1(int a2, T a1);

		@Override
		default int doApplyAsInt(T a1, int a2) {
			return this.doApplyAsIntV1(a2, a1);
		}
	}

	// </editor-fold>

}
