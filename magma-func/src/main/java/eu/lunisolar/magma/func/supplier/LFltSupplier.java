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
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*;

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
 * Non-throwing functional interface (lambda) LFltSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltSupplier extends MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LFltSupplier: float doGetAsFlt()";

	// float doGetAsFlt() ;
	default float doGetAsFlt() {
		// return nestingDoGetAsFlt();
		try {
			return this.doGetAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doGetAsFlt()
	 */
	float doGetAsFltX() throws Throwable;

	default float tupleGetAsFlt(LTuple.Void args) {
		return doGetAsFlt();
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingDoGetAsFlt(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doGetAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default float tryDoGetAsFlt(@Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doGetAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default float tryDoGetAsFlt(@Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doGetAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default float tryDoGetAsFltThen(@Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.doGetAsFltX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsFlt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoGetAsFlt() {
		try {
			return this.doGetAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingDoGetAsFlt() {
		try {
			return this.doGetAsFltX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float handlingDoGetAsFlt(LFltSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoGetAsFlt(handling);
	}

	static float tryDoGetAsFlt(LFltSupplier func) {
		return tryDoGetAsFlt(func, null);
	}

	static float tryDoGetAsFlt(LFltSupplier func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsFlt(exceptionFactory, newMessage, messageParams);
	}

	static float tryDoGetAsFlt(LFltSupplier func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsFlt(exceptionFactory);
	}

	static float tryDoGetAsFltThen(LFltSupplier func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsFltThen(handler);
	}

	default float failSafeDoGetAsFlt(@Nonnull LFltSupplier failSafe) {
		try {
			return doGetAsFlt();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doGetAsFlt();
		}
	}

	static float failSafeDoGetAsFlt(LFltSupplier func, @Nonnull LFltSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doGetAsFlt();
		} else {
			return func.failSafeDoGetAsFlt(failSafe);
		}
	}

	static LFltSupplier failSafeFltSup(LFltSupplier func, @Nonnull LFltSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeDoGetAsFlt(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoGetAsFlt() {
		return doGetAsFlt();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, LFltSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doGetAsFlt();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doGetAsFlt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, LFltSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doGetAsFlt();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doGetAsFlt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, LFltSupplier func) {
		fromTill(0, max_i, func);
	}

	/** Creates function that always returns the same value. */
	static LFltSupplier of(float r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltSupplier fltSup(final @Nonnull LFltSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LFltSupplier recursive(final @Nonnull LFunction<LFltSupplier, LFltSupplier> selfLambda) {
		final LFltSupplierSingle single = new LFltSupplierSingle();
		LFltSupplier func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LFltSupplierSingle implements LSingle<LFltSupplier>, LFltSupplier {
		private LFltSupplier target = null;

		@Override
		public float doGetAsFltX() throws Throwable {
			return target.doGetAsFltX();
		}

		@Override
		public LFltSupplier value() {
			return target;
		}
	}

	@Nonnull
	static LFltSupplier fltSupThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LFltSupplier fltSupThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce(message);
		};
	}

	static float call(final @Nonnull LFltSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsFlt();
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceFloat). */
	@Nonnull
	static LFltSupplier safe() {
		return LFltSupplier::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFltSupplier safe(final @Nullable LFltSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltSupplier> safeSupplier(final @Nullable LSupplier<LFltSupplier> supplier) {
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
	default <V> LSupplier<V> toSup(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsSrt(this.doGetAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFlt(this.doGetAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDbl(this.doGetAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsFlt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsFlt());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFltSupplier nestingFltSup() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFltSupplier shovingFltSup() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LFltSupplier) Supplier */
	public static float produceFloat() {
		return Function4U.defaultFloat;
	}

}
