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
 * Non-throwing functional interface (lambda) LCharSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharSupplier extends MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LCharSupplier: char doGetAsChar()";

	// char doGetAsChar() ;
	default char doGetAsChar() {
		// return nestingDoGetAsChar();
		try {
			return this.doGetAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doGetAsChar()
	 */
	char doGetAsCharX() throws Throwable;

	default char tupleGetAsChar(LTuple.Void args) {
		return doGetAsChar();
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingDoGetAsChar(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doGetAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default char tryDoGetAsChar(@Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doGetAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default char tryDoGetAsChar(@Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doGetAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default char tryDoGetAsCharThen(@Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.doGetAsCharX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsChar(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingDoGetAsChar() {
		try {
			return this.doGetAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingDoGetAsChar() {
		try {
			return this.doGetAsCharX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static char handlingDoGetAsChar(LCharSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoGetAsChar(handling);
	}

	static char tryDoGetAsChar(LCharSupplier func) {
		return tryDoGetAsChar(func, null);
	}

	static char tryDoGetAsChar(LCharSupplier func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsChar(exceptionFactory, newMessage, messageParams);
	}

	static char tryDoGetAsChar(LCharSupplier func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsChar(exceptionFactory);
	}

	static char tryDoGetAsCharThen(LCharSupplier func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsCharThen(handler);
	}

	default char failSafeDoGetAsChar(@Nonnull LCharSupplier failSafe) {
		try {
			return doGetAsChar();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doGetAsChar();
		}
	}

	static char failSafeDoGetAsChar(LCharSupplier func, @Nonnull LCharSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doGetAsChar();
		} else {
			return func.failSafeDoGetAsChar(failSafe);
		}
	}

	static LCharSupplier failSafeCharSup(LCharSupplier func, @Nonnull LCharSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeDoGetAsChar(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoGetAsChar() {
		return doGetAsChar();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, LCharSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doGetAsChar();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doGetAsChar();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, LCharSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doGetAsChar();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doGetAsChar();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, LCharSupplier func) {
		fromTill(0, max_i, func);
	}

	/** Creates function that always returns the same value. */
	static LCharSupplier of(char r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharSupplier charSup(final @Nonnull LCharSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LCharSupplier recursive(final @Nonnull LFunction<LCharSupplier, LCharSupplier> selfLambda) {
		final LCharSupplierSingle single = new LCharSupplierSingle();
		LCharSupplier func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LCharSupplierSingle implements LSingle<LCharSupplier>, LCharSupplier {
		private LCharSupplier target = null;

		@Override
		public char doGetAsCharX() throws Throwable {
			return target.doGetAsCharX();
		}

		@Override
		public LCharSupplier value() {
			return target;
		}
	}

	@Nonnull
	static LCharSupplier charSupThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LCharSupplier charSupThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce(message);
		};
	}

	static char call(final @Nonnull LCharSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsChar();
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceChar). */
	@Nonnull
	static LCharSupplier safe() {
		return LCharSupplier::produceChar;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LCharSupplier safe(final @Nullable LCharSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharSupplier> safeSupplier(final @Nullable LSupplier<LCharSupplier> supplier) {
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
	default <V> LSupplier<V> toSup(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsSrt(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFlt(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDbl(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsChar());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsChar());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharSupplier nestingCharSup() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharSupplier shovingCharSup() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LCharSupplier) Supplier */
	public static char produceChar() {
		return Function4U.defaultCharacter;
	}

}
