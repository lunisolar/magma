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
 * Non-throwing functional interface (lambda) LIntSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntSupplier extends IntSupplier, MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LIntSupplier: int doGetAsInt()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int getAsInt() {
		return this.doGetAsInt();
	}

	// int doGetAsInt() ;
	default int doGetAsInt() {
		// return nestingDoGetAsInt();
		try {
			return this.doGetAsIntX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doGetAsInt()
	 */
	int doGetAsIntX() throws Throwable;

	default int tupleGetAsInt(LTuple.Void args) {
		return doGetAsInt();
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingDoGetAsInt(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doGetAsIntX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default int tryDoGetAsInt(@Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doGetAsIntX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default int tryDoGetAsInt(@Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doGetAsIntX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default int tryDoGetAsIntThen(@Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.doGetAsIntX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsInt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoGetAsInt() {
		try {
			return this.doGetAsIntX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingDoGetAsInt() {
		try {
			return this.doGetAsIntX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static int handlingDoGetAsInt(LIntSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoGetAsInt(handling);
	}

	static int tryDoGetAsInt(LIntSupplier func) {
		return tryDoGetAsInt(func, null);
	}

	static int tryDoGetAsInt(LIntSupplier func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsInt(exceptionFactory, newMessage, messageParams);
	}

	static int tryDoGetAsInt(LIntSupplier func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsInt(exceptionFactory);
	}

	static int tryDoGetAsIntThen(LIntSupplier func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsIntThen(handler);
	}

	default int failSafeDoGetAsInt(@Nonnull LIntSupplier failSafe) {
		try {
			return doGetAsInt();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doGetAsInt();
		}
	}

	static int failSafeDoGetAsInt(LIntSupplier func, @Nonnull LIntSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doGetAsInt();
		} else {
			return func.failSafeDoGetAsInt(failSafe);
		}
	}

	static LIntSupplier failSafeIntSup(LIntSupplier func, @Nonnull LIntSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeDoGetAsInt(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoGetAsInt() {
		return doGetAsInt();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, LIntSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doGetAsInt();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doGetAsInt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, LIntSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doGetAsInt();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doGetAsInt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, LIntSupplier func) {
		fromTill(0, max_i, func);
	}

	/** Creates function that always returns the same value. */
	static LIntSupplier of(int r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntSupplier intSup(final @Nonnull LIntSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LIntSupplier recursive(final @Nonnull LFunction<LIntSupplier, LIntSupplier> selfLambda) {
		final LIntSupplierSingle single = new LIntSupplierSingle();
		LIntSupplier func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LIntSupplierSingle implements LSingle<LIntSupplier>, LIntSupplier {
		private LIntSupplier target = null;

		@Override
		public int doGetAsIntX() throws Throwable {
			return target.doGetAsIntX();
		}

		@Override
		public LIntSupplier value() {
			return target;
		}
	}

	@Nonnull
	static LIntSupplier intSupThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LIntSupplier intSupThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce(message);
		};
	}

	static int call(final @Nonnull LIntSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsInt();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntSupplier wrap(final IntSupplier other) {
		return other::getAsInt;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static LIntSupplier safe() {
		return LIntSupplier::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LIntSupplier safe(final @Nullable LIntSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntSupplier> safeSupplier(final @Nullable LSupplier<LIntSupplier> supplier) {
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
	default <V> LSupplier<V> toSup(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsInt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsInt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LIntToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsSrt(this.doGetAsInt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsInt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsInt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LIntToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFlt(this.doGetAsInt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LIntToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDbl(this.doGetAsInt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsInt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsInt());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntSupplier nestingIntSup() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntSupplier shovingIntSup() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LIntSupplier) Supplier */
	public static int produceInt() {
		return Function4U.defaultInteger;
	}

}
