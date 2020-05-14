/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
public interface LDblSupplier extends DoubleSupplier, MetaSupplier, MetaInterface.NonThrowing, Codomain<aDouble>, Domain0 { // NOSONAR

	String DESCRIPTION = "LDblSupplier: double getAsDbl()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double getAsDouble() {
		return this.getAsDbl();
	}

	// double getAsDbl() ;
	default double getAsDbl() {
		// return nestingGetAsDbl();
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsDbl()
	 */
	double getAsDblX() throws Throwable;

	default double tupleGetAsDbl(LTuple.Void args) {
		return getAsDbl();
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingGetAsDbl(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsDbl(handling);
	}

	default double getAsDbl(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default double getAsDbl(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default double getAsDbl(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default double getAsDbl(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LDblSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return () -> getAsDbl(exF, newMessage);
	}

	default LDblSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> getAsDbl(exF, newMessage, param1);
	}

	default LDblSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> getAsDbl(exF, newMessage, param1, param1);
	}

	default LDblSupplier trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> getAsDbl(exF, newMessage, param1, param2, param3);
	}

	default double getAsDbl(@Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LDblSupplier trying(@Nonnull ExWF<RuntimeException> exF) {
		return () -> getAsDbl(exF);
	}

	default double getAsDblThen(@Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LDblSupplier tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return () -> getAsDblThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingGetAsDbl() {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingGetAsDbl() {
		try {
			return this.getAsDblX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double handlingGetAsDbl(LDblSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsDbl(handling);
	}

	static double tryGetAsDbl(LDblSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsDbl();
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(exF, newMessage);
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(exF, newMessage, param1);
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(exF, newMessage, param1, param2);
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(exF, newMessage, param1, param2, param3);
	}

	static double tryGetAsDbl(LDblSupplier func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.getAsDbl(exF);
	}

	static double tryGetAsDblThen(LDblSupplier func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsDblThen(handler);
	}

	default double failSafeGetAsDbl(@Nonnull LDblSupplier failSafe) {
		try {
			return getAsDbl();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.getAsDbl();
		}
	}

	static double failSafeGetAsDbl(LDblSupplier func, @Nonnull LDblSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.getAsDbl();
		} else {
			return func.failSafeGetAsDbl(failSafe);
		}
	}

	static LDblSupplier failSafe(LDblSupplier func, @Nonnull LDblSupplier failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeGetAsDbl(func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullGetAsDbl() {
		return getAsDbl();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LDblSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsDbl();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsDbl();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LDblSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsDbl();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsDbl();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LDblSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to consumer that ignores output. */
	default LAction toConsumer() {
		return this::getAsDbl;
	}

	/** Calls codomain consumer after main function. */
	default LDblSupplier afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return () -> {
			final double retval = getAsDbl();
			after.accept(retval);
			return retval;
		};
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

	final class S implements LDblSupplier {
		private LDblSupplier target = null;
		@Override
		public double getAsDblX() throws Throwable {
			return target.getAsDblX();
		}
	}

	@Nonnull
	static LDblSupplier recursive(final @Nonnull LFunction<LDblSupplier, LDblSupplier> selfLambda) {
		final S single = new S();
		LDblSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	/**
	 * Memento of a function, initialized with value from it.
	 */
	public static M mementoOf(LDblSupplier function) {
		var initialValue = function.getAsDbl();
		return initializedMementoOf(initialValue, function);
	}

	/**
	 * Memento of a function, initialized with argument value.
	 */
	public static M initializedMementoOf(double initialValue, LDblSupplier function) {
		return memento(initialValue, function, (x1, x2) -> x2);
	}

	public static M deltaOf(LDblSupplier function, LDblBinaryOperator deltaFunction) {
		var initialValue = function.getAsDbl();
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(LDblSupplier function) {
		var initialValue = function.getAsDbl();
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	/**
	 * Delta of a function result, initialized with argument value.
	 */
	public static M initializedDeltaOf(double initialValue, LDblSupplier function, LDblBinaryOperator deltaFunction) {
		return memento(initialValue, function, deltaFunction);
	}

	/**
	 * Creates function that remembers previous result of itself and applies a memento-function on it an current result of base function.
	 * Basically, provided that calls and arguments (if applicable) represents progression of some sort, makes possible to apply functions like MAX. MIN, DELTA on the result of the base function.
	 */
	public static M memento(double initialValue, LDblSupplier baseFunction, LDblBinaryOperator mementoFunction) {
		return new M(initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not ). Very short name is intended to be used with parent (LDblSupplier.D)
	 */
	final class M implements LDblSupplier {

		private double lastValue;
		private final LDblBinaryOperator mementoFunction;
		private final LDblSupplier baseFunction;

		private M(double lastValue, LDblSupplier baseFunction, LDblBinaryOperator mementoFunction) {
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
			this.baseFunction = baseFunction;
		}

		@Override
		public double getAsDblX() throws Throwable {
			double x2 = baseFunction.getAsDblX();
			double x1 = lastValue;
			return lastValue = mementoFunction.applyAsDbl(x1, x2);
		}

		public double lastValue() {
			return lastValue;
		};
	}

	@Nonnull
	static LDblSupplier dblSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblSupplier dblSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static double call(final @Nonnull LDblSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsDbl();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDblSupplier wrap(final DoubleSupplier other) {
		return other::getAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LDblSupplier safe() {
		return LDblSupplier::doNothing;
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
		return () -> after.apply(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsDbl());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return () -> after.test(this.getAsDbl());
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblSupplier) Supplier */
	public static double doNothing() {
		return Function4U.defaultDouble;
	}

}
