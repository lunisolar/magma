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

package eu.lunisolar.magma.func.operator.ternary;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
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
import java.util.*; // NOSONAR
import java.lang.reflect.*;

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
 * Non-throwing functional interface (lambda) LCharTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): char a1,char a2,char a3
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharTernaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aChar>, Domain3<aChar, aChar, aChar> { // NOSONAR

	String DESCRIPTION = "LCharTernaryOperator: char applyAsChar(char a1,char a2,char a3)";

	// char applyAsChar(char a1,char a2,char a3) ;
	default char applyAsChar(char a1, char a2, char a3) {
		// return nestingApplyAsChar(a1,a2,a3);
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsChar(char a1,char a2,char a3)
	 */
	char applyAsCharX(char a1, char a2, char a3) throws Throwable;

	default char tupleApplyAsChar(LCharTriple args) {
		return applyAsChar(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingApplyAsChar(char a1, char a2, char a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LCharTernaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsChar(a1, a2, a3, handling);
	}

	default char applyAsChar(char a1, char a2, char a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default char applyAsChar(char a1, char a2, char a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default char applyAsChar(char a1, char a2, char a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default char applyAsChar(char a1, char a2, char a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LCharTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> applyAsChar(a1, a2, a3, factory, newMessage);
	}

	default LCharTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> applyAsChar(a1, a2, a3, factory, newMessage, param1);
	}

	default LCharTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> applyAsChar(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LCharTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> applyAsChar(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default char applyAsChar(char a1, char a2, char a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LCharTernaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> applyAsChar(a1, a2, a3, factory);
	}

	default char applyAsCharThen(char a1, char a2, char a3, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsChar(e);
		}
	}

	default LCharTernaryOperator tryingThen(@Nonnull LToCharFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsCharThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingApplyAsChar(char a1, char a2, char a3) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingApplyAsChar(char a1, char a2, char a3) {
		try {
			return this.applyAsCharX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static char shovingApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsChar(a1, a2, a3);
	}

	static char handlingApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsChar(a1, a2, a3, handling);
	}

	static char tryApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsChar(a1, a2, a3);
	}

	static char tryApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, a3, factory, newMessage);
	}

	static char tryApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, a3, factory, newMessage, param1);
	}

	static char tryApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static char tryApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static char tryApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, a3, factory);
	}

	static char tryApplyAsCharThen(char a1, char a2, char a3, LCharTernaryOperator func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsCharThen(a1, a2, a3, handler);
	}

	default char failSafeApplyAsChar(char a1, char a2, char a3, @Nonnull LCharTernaryOperator failSafe) {
		try {
			return applyAsChar(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsChar(a1, a2, a3);
		}
	}

	static char failSafeApplyAsChar(char a1, char a2, char a3, LCharTernaryOperator func, @Nonnull LCharTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsChar(a1, a2, a3);
		} else {
			return func.failSafeApplyAsChar(a1, a2, a3, failSafe);
		}
	}

	static LCharTernaryOperator failSafe(LCharTernaryOperator func, @Nonnull LCharTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsChar(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullApplyAsChar(char a1, char a2, char a3) {
		return applyAsChar(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a1, char a2, char a3, @Nonnull LCharTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsChar(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsChar(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, char a1, char a2, char a3, @Nonnull LCharTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsChar(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsChar(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, char a1, char a2, char a3, @Nonnull LCharTernaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	default LCharBinaryOperator lShrink(@Nonnull LCharBinaryOperator left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> applyAsChar(left.applyAsChar(a2, a3), a2, a3);
	}

	default LCharBinaryOperator lShrink_(char a1) {
		return (a2, a3) -> applyAsChar(a1, a2, a3);
	}

	public static LCharBinaryOperator lShrunken(@Nonnull LCharBinaryOperator left, @Nonnull LCharTernaryOperator func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LCharBinaryOperator lShrunken_(char a1, @Nonnull LCharTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LCharBinaryOperator rShrink(@Nonnull LCharBinaryOperator right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> applyAsChar(a1, a2, right.applyAsChar(a1, a2));
	}

	default LCharBinaryOperator rShrink_(char a3) {
		return (a1, a2) -> applyAsChar(a1, a2, a3);
	}

	public static LCharBinaryOperator rShrunken(@Nonnull LCharBinaryOperator right, @Nonnull LCharTernaryOperator func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LCharBinaryOperator rShrunken_(char a3, @Nonnull LCharTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static LCharTernaryOperator uncurry(@Nonnull LCharFunction<LCharFunction<LCharUnaryOperator>> func) {
		Null.nonNullArg(func, "func");
		return (char a1, char a2, char a3) -> func.apply(a1).apply(a2).applyAsChar(a3);
	}

	/** Change function to consumer that ignores output. */
	default LTriCharConsumer toConsumer() {
		return this::applyAsChar;
	}

	/** Calls domain consumer before main function. */
	default LCharTernaryOperator beforeDo(@Nonnull LTriCharConsumer before) {
		Null.nonNullArg(before, "before");
		return (char a1, char a2, char a3) -> {
			before.accept(a1, a2, a3);
			return applyAsChar(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LCharTernaryOperator afterDo(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return (char a1, char a2, char a3) -> {
			final char retval = applyAsChar(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier capture(char a1, char a2, char a3) {
		return () -> this.applyAsChar(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static LCharTernaryOperator constant(char r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LCharTernaryOperator apply1stAsChar(@Nonnull LCharUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsChar(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LCharTernaryOperator apply2ndAsChar(@Nonnull LCharUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsChar(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static LCharTernaryOperator apply3rdAsChar(@Nonnull LCharUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsChar(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharTernaryOperator charTernaryOp(final @Nonnull LCharTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LCharTernaryOperator {
		private LCharTernaryOperator target = null;
		@Override
		public char applyAsCharX(char a1, char a2, char a3) throws Throwable {
			return target.applyAsCharX(a1, a2, a3);
		}
	}

	@Nonnull
	static LCharTernaryOperator recursive(final @Nonnull LFunction<LCharTernaryOperator, LCharTernaryOperator> selfLambda) {
		final S single = new S();
		LCharTernaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static M mementoOf(char a1, char a2, char a3, LCharTernaryOperator function) {
		var initialValue = function.applyAsChar(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(char initialValue, LCharTernaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(char a1, char a2, char a3, LCharTernaryOperator function, LCharBinaryOperator deltaFunction) {
		var initialValue = function.applyAsChar(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(char a1, char a2, char a3, LCharTernaryOperator function) {
		var initialValue = function.applyAsChar(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (char) (x2 - x1));
	}

	public static M initializedDeltaOf(char initialValue, LCharTernaryOperator function, LCharBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsChar(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsChar(x1, x2));
	}

	public static M memento(char initialBaseValue, char initialValue, LCharTernaryOperator baseFunction, LCharTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LCharTernaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LCharTernaryOperator {

		private final LCharTernaryOperator baseFunction;
		private char lastBaseValue;
		private char lastValue;
		private final LCharTernaryOperator mementoFunction;

		private M(char lastBaseValue, char lastValue, LCharTernaryOperator baseFunction, LCharTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public char applyAsCharX(char a1, char a2, char a3) throws Throwable {
			char x1 = lastBaseValue;
			char x2 = lastBaseValue = baseFunction.applyAsCharX(a1, a2, a3);

			return lastValue = mementoFunction.applyAsChar(lastValue, x1, x2);
		}

		public char lastValue() {
			return lastValue;
		};

		public char lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static LCharTernaryOperator charTernaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LCharTernaryOperator charTernaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static char call(char a1, char a2, char a3, final @Nonnull LCharTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsChar(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LCharTernaryOperator safe() {
		return LCharTernaryOperator::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharTernaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LCharTernaryOperator safe(final @Nullable LCharTernaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharTernaryOperator> safeSupplier(final @Nullable LSupplier<LCharTernaryOperator> supplier) {
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
	default LCharTernaryOperator compose(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, @Nonnull final LCharUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsChar(before1.applyAsChar(v1), before2.applyAsChar(v2), before3.applyAsChar(v3));
	}

	public static LCharTernaryOperator composed(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, @Nonnull final LCharUnaryOperator before3, LCharTernaryOperator after) {
		return after.compose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharTernaryOperator thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsChar(this.applyAsChar(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriCharPredicate thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsChar(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LCharTernaryOperator) Operator */
	public static char doNothing(char a1, char a2, char a3) {
		return Function4U.defaultCharacter;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = oiFunc3.applyAsChar(source3, i);
			consumer.accept(this.applyAsChar(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = oiFunc3.applyAsChar(source3, i);
			consumer.accept(this.applyAsChar(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = oiFunc3.applyAsChar(source3, i);
			consumer.accept(this.applyAsChar(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = oiFunc3.applyAsChar(source3, i);
			consumer.accept(this.applyAsChar(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = nextFunc3.applyAsChar(iterator3);
			consumer.accept(this.applyAsChar(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = nextFunc3.applyAsChar(iterator3);
			consumer.accept(this.applyAsChar(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = nextFunc3.applyAsChar(iterator3);
			consumer.accept(this.applyAsChar(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = nextFunc3.applyAsChar(iterator3);
			consumer.accept(this.applyAsChar(a1, a2, a3));
		}
	}

}
