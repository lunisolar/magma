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

package eu.lunisolar.magma.func.supplier;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
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
 * Non-throwing functional interface (lambda) LFloatSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: float
 *
 * @see LFloatSupplierX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFloatSupplier extends LFloatSupplierX<RuntimeException>, MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LFloatSupplier: float doGetAsFloat()";

	float doGetAsFloat();

	default float tupleGetAsFloat(LTuple.Void args) {
		return doGetAsFloat();
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoGetAsFloat() {
		return this.doGetAsFloat();
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default float shovingDoGetAsFloat() {
		return this.doGetAsFloat();
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoGetAsFloat() {
		return doGetAsFloat();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFloatSupplier.DESCRIPTION;
	}

	/** Creates function that always returns the same value. */
	static LFloatSupplier of(float r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFloatSupplier l(final @Nonnull LFloatSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static float call(final @Nonnull LFloatSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsFloat();
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LFloatSupplier wrap(final @Nonnull LFloatSupplierX<X> other) {
		return other::nestingDoGetAsFloat;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::produceFloat). */
	@Nonnull
	static LFloatSupplier safe() {
		return Function4U::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFloatSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFloatSupplier safe(final @Nullable LFloatSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFloatSupplier> safeSupplier(final @Nullable LSupplier<LFloatSupplier> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSup(@Nonnull LFloatFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsFloat());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LFloatToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsFloat());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LShortSupplier toShortSup(@Nonnull LFloatToShortFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsShort(this.doGetAsFloat());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LFloatToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsFloat());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LFloatToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsFloat());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFloatSupplier toFloatSup(@Nonnull LFloatUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFloat(this.doGetAsFloat());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDoubleSupplier toDoubleSup(@Nonnull LFloatToDoubleFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDouble(this.doGetAsFloat());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LFloatToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsFloat());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LFloatPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsFloat());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFloatSupplier nestingFloatSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LFloatSupplierX<RuntimeException> nestingFloatSupX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatSupplier shovingFloatSup() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFloatSupplierX<RuntimeException> shovingFloatSupX() {
		return this;
	}

	// </editor-fold>

}
