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

package eu.lunisolar.magma.func.operator.unary;
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
 * Non-throwing functional interface (lambda) LDoubleUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): double a1
 *
 * Co-domain: double
 *
 * @see LDoubleUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoubleUnaryOperator extends LDoubleUnaryOperatorX<RuntimeException>, MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LDoubleUnaryOperator: double doApplyAsDouble(double a1)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDoubleUnaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(double a1) {
		return this.nestingDoApplyAsDouble(a1);
	}

	double doApplyAsDouble(double a1);

	default Double tupleApplyAsDouble(LDoubleSingle args) {
		return doApplyAsDouble(args.first());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingDoApplyAsDouble(double a1) {
		return this.doApplyAsDouble(a1);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default double shovingDoApplyAsDouble(double a1) {
		return this.doApplyAsDouble(a1);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDouble(double a1) {
		return doApplyAsDouble(a1);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoubleUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LDoubleSupplier captureDoubleUnaryOp(double a1) {
		return () -> this.doApplyAsDouble(a1);
	}

	/** Creates function that always returns the same value. */
	static LDoubleUnaryOperator constant(double r) {
		return a1 -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDoubleUnaryOperator l(final @Nonnull LDoubleUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static double call(double a1, final @Nonnull LDoubleUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsDouble(a1);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDoubleUnaryOperator wrap(final DoubleUnaryOperator other) {
		return other::applyAsDouble;
	}

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LDoubleUnaryOperator wrap(final @Nonnull LDoubleUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsDouble;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::static_doNothing_method_name). */
	@Nonnull
	static LDoubleUnaryOperator safe() {
		return Function4U::produceDouble;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDoubleUnaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDoubleUnaryOperator safe(final @Nullable LDoubleUnaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDoubleUnaryOperator> safeSupplier(final @Nullable LSupplier<LDoubleUnaryOperator> supplier) {
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
	default LDoubleUnaryOperator doubleUnaryOpComposeDouble(@Nonnull final LDoubleUnaryOperator before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsDouble(before1.doApplyAsDouble(v1));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LToDoubleFunction<V1> doubleUnaryOpCompose(@Nonnull final LToDoubleFunction<? super V1> before1) {
		Null.nonNullArg(before1, "before1");
		return v1 -> this.doApplyAsDouble(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LDoubleFunction<V> then(@Nonnull LDoubleFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApply(this.doApplyAsDouble(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToByteFunction thenToByte(@Nonnull LDoubleToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsByte(this.doApplyAsDouble(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToShortFunction thenToShort(@Nonnull LDoubleToShortFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsShort(this.doApplyAsDouble(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToIntFunction thenToInt(@Nonnull LDoubleToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsInt(this.doApplyAsDouble(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToLongFunction thenToLong(@Nonnull LDoubleToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsLong(this.doApplyAsDouble(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToFloatFunction thenToFloat(@Nonnull LDoubleToFloatFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsFloat(this.doApplyAsDouble(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleUnaryOperator thenToDouble(@Nonnull LDoubleUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsDouble(this.doApplyAsDouble(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoubleToCharFunction thenToChar(@Nonnull LDoubleToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doApplyAsChar(this.doApplyAsDouble(a1));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LDoublePredicate thenToBool(@Nonnull LDoublePredicate after) {
		Null.nonNullArg(after, "after");
		return a1 -> after.doTest(this.doApplyAsDouble(a1));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LDoubleUnaryOperator identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoubleUnaryOperator nestingDoubleUnaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoubleUnaryOperatorX<RuntimeException> nestingDoubleUnaryOpX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleUnaryOperator shovingDoubleUnaryOp() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoubleUnaryOperatorX<RuntimeException> shovingDoubleUnaryOpX() {
		return this;
	}

	// </editor-fold>

}
