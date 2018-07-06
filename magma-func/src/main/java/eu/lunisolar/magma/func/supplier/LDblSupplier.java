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
 * Non-throwing functional interface (lambda) LDblSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblSupplier extends DoubleSupplier, MetaSupplier, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LDblSupplier: double doGetAsDbl()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double getAsDouble() {
		return this.doGetAsDbl();
	}

	// double doGetAsDbl() ;
	default double doGetAsDbl() {
		// return nestingDoGetAsDbl();
		try {
			return this.doGetAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doGetAsDbl()
	 */
	double doGetAsDblX() throws Throwable;

	default double tupleGetAsDbl(LTuple.Void args) {
		return doGetAsDbl();
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingDoGetAsDbl(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doGetAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default double tryDoGetAsDbl(@Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doGetAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default double tryDoGetAsDbl(@Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doGetAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default double tryDoGetAsDblThen(@Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.doGetAsDblX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsDbl(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingDoGetAsDbl() {
		try {
			return this.doGetAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingDoGetAsDbl() {
		try {
			return this.doGetAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double handlingDoGetAsDbl(LDblSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoGetAsDbl(handling);
	}

	static double tryDoGetAsDbl(LDblSupplier func) {
		return tryDoGetAsDbl(func, null);
	}

	static double tryDoGetAsDbl(LDblSupplier func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsDbl(exceptionFactory, newMessage, messageParams);
	}

	static double tryDoGetAsDbl(LDblSupplier func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsDbl(exceptionFactory);
	}

	static double tryDoGetAsDblThen(LDblSupplier func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoGetAsDblThen(handler);
	}

	default double failSafeDoGetAsDbl(@Nonnull LDblSupplier failSafe) {
		try {
			return doGetAsDbl();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doGetAsDbl();
		}
	}

	static double failSafeDoGetAsDbl(LDblSupplier func, @Nonnull LDblSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doGetAsDbl();
		} else {
			return func.failSafeDoGetAsDbl(failSafe);
		}
	}

	static LDblSupplier failSafeDblSup(LDblSupplier func, @Nonnull LDblSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeDoGetAsDbl(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoGetAsDbl() {
		return doGetAsDbl();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, LDblSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doGetAsDbl();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doGetAsDbl();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, LDblSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doGetAsDbl();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doGetAsDbl();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, LDblSupplier func) {
		fromTill(0, max_i, func);
	}

	/** Creates function that always returns the same value. */
	static LDblSupplier of(double r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblSupplier dblSup(final @Nonnull LDblSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LDblSupplier recursive(final @Nonnull LFunction<LDblSupplier, LDblSupplier> selfLambda) {
		final LDblSupplierSingle single = new LDblSupplierSingle();
		LDblSupplier func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LDblSupplierSingle implements LSingle<LDblSupplier>, LDblSupplier {
		private LDblSupplier target = null;

		@Override
		public double doGetAsDblX() throws Throwable {
			return target.doGetAsDblX();
		}

		@Override
		public LDblSupplier value() {
			return target;
		}
	}

	@Nonnull
	static LDblSupplier dblSupThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LDblSupplier dblSupThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce(message);
		};
	}

	static double call(final @Nonnull LDblSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doGetAsDbl();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDblSupplier wrap(final DoubleSupplier other) {
		return other::getAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceDouble). */
	@Nonnull
	static LDblSupplier safe() {
		return LDblSupplier::produceDouble;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblSupplier> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDblSupplier safe(final @Nullable LDblSupplier other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblSupplier> safeSupplier(final @Nullable LSupplier<LDblSupplier> supplier) {
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
	default <V> LSupplier<V> toSup(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApply(this.doGetAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsByte(this.doGetAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsSrt(this.doGetAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsInt(this.doGetAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsLong(this.doGetAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsFlt(this.doGetAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsDbl(this.doGetAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.doApplyAsChar(this.doGetAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.doTest(this.doGetAsDbl());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDblSupplier nestingDblSup() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDblSupplier shovingDblSup() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LDblSupplier) Supplier */
	public static double produceDouble() {
		return Function4U.defaultDouble;
	}

}
