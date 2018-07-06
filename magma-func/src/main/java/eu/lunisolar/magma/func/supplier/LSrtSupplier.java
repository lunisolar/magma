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
 * Non-throwing functional interface (lambda) LSrtSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtSupplier extends MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LSrtSupplier: short doGetAsSrt()";

	// short doGetAsSrt() ;
	default short doGetAsSrt() {
		// return nestingDoGetAsSrt();
		try {
			return this.doGetAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doGetAsSrt()
	 */
	short doGetAsSrtX() throws Throwable;

	default short tupleGetAsSrt(LTuple.Void args) {
		return doGetAsSrt();
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingDoGetAsSrt(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doGetAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default short tryDoGetAsSrt(@Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doGetAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default short tryDoGetAsSrt(@Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doGetAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default short tryDoGetAsSrtThen(@Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.doGetAsSrtX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsSrt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingDoGetAsSrt() {
		try {
			return this.doGetAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingDoGetAsSrt() {
		try {
			return this.doGetAsSrtX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short handlingDoGetAsSrt(LSrtSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoGetAsSrt(handling);
	}

	static short tryDoGetAsSrt(LSrtSupplier func) {
		return tryDoGetAsSrt(func, null);
	}

	static short tryDoGetAsSrt(LSrtSupplier func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsSrt(exceptionFactory, newMessage, messageParams);
	}

	static short tryDoGetAsSrt(LSrtSupplier func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsSrt(exceptionFactory);
	}

	static short tryDoGetAsSrtThen(LSrtSupplier func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsSrtThen(handler);
	}

	default short failSafeDoGetAsSrt(@Nonnull LSrtSupplier failSafe) {
		try {
			return doGetAsSrt();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doGetAsSrt();
		}
	}

	static short failSafeDoGetAsSrt(LSrtSupplier func, @Nonnull LSrtSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doGetAsSrt();
		} else {
			return func.failSafeDoGetAsSrt(failSafe);
		}
	}

	static LSrtSupplier failSafeSrtSup(LSrtSupplier func, @Nonnull LSrtSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeDoGetAsSrt(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoGetAsSrt() {
		return doGetAsSrt();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, LSrtSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doGetAsSrt();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doGetAsSrt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, LSrtSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doGetAsSrt();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doGetAsSrt();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, LSrtSupplier func) {
		fromTill(0, max_i, func);
	}

	/** Creates function that always returns the same value. */
	static LSrtSupplier of(short r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtSupplier srtSup(final @Nonnull LSrtSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtSupplier recursive(final @Nonnull LFunction<LSrtSupplier, LSrtSupplier> selfLambda) {
		final LSrtSupplierSingle single = new LSrtSupplierSingle();
		LSrtSupplier func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LSrtSupplierSingle implements LSingle<LSrtSupplier>, LSrtSupplier {
		private LSrtSupplier target = null;

		@Override
		public short doGetAsSrtX() throws Throwable {
			return target.doGetAsSrtX();
		}

		@Override
		public LSrtSupplier value() {
			return target;
		}
	}

	@Nonnull
	static LSrtSupplier srtSupThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LSrtSupplier srtSupThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce(message);
		};
	}

	static short call(final @Nonnull LSrtSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsSrt();
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceShort). */
	@Nonnull
	static LSrtSupplier safe() {
		return LSrtSupplier::produceShort;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtSupplier safe(final @Nullable LSrtSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtSupplier> safeSupplier(final @Nullable LSupplier<LSrtSupplier> supplier) {
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
	default <V> LSupplier<V> toSup(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LSrtToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsSrt(this.doGetAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LSrtToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LSrtToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LSrtToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFlt(this.doGetAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LSrtToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDbl(this.doGetAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LSrtToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsSrt());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsSrt());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LSrtSupplier nestingSrtSup() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSrtSupplier shovingSrtSup() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LSrtSupplier) Supplier */
	public static short produceShort() {
		return Function4U.defaultShort;
	}

}
