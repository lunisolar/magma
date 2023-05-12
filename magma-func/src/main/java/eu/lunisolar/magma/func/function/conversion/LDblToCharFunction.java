/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.function.conversion;

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

import java.util.concurrent.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*; // NOSONAR

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
 * Non-throwing functional interface (lambda) LDblToCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblToCharFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aChar>, Domain1<aDouble> { // NOSONAR

	String DESCRIPTION = "LDblToCharFunction: char applyAsChar(double a)";

	// char applyAsChar(double a) ;
	default char applyAsChar(double a) {
		// return nestingApplyAsChar(a);
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsChar(double a)
	 */
	char applyAsCharX(double a) throws Throwable;

	default char tupleApplyAsChar(LDblSingle args) {
		return applyAsChar(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingApplyAsChar(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblToCharFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsChar(a, handling);
	}

	default char applyAsChar(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default char applyAsChar(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default char applyAsChar(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default char applyAsChar(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LDblToCharFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsChar(a, factory, newMessage);
	}

	default LDblToCharFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsChar(a, factory, newMessage, param1);
	}

	default LDblToCharFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsChar(a, factory, newMessage, param1, param1);
	}

	default LDblToCharFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsChar(a, factory, newMessage, param1, param2, param3);
	}

	default char applyAsChar(double a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LDblToCharFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsChar(a, factory);
	}

	default char applyAsCharThen(double a, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsChar(e);
		}
	}

	default LDblToCharFunction tryingThen(@Nonnull LToCharFunction<Throwable> handler) {
		return a -> applyAsCharThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingApplyAsChar(double a) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingApplyAsChar(double a) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static char shovingApplyAsChar(double a, LDblToCharFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsChar(a);
	}

	static char handlingApplyAsChar(double a, LDblToCharFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsChar(a, handling);
	}

	static char tryApplyAsChar(double a, LDblToCharFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsChar(a);
	}

	static char tryApplyAsChar(double a, LDblToCharFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory, newMessage);
	}

	static char tryApplyAsChar(double a, LDblToCharFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory, newMessage, param1);
	}

	static char tryApplyAsChar(double a, LDblToCharFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory, newMessage, param1, param2);
	}

	static char tryApplyAsChar(double a, LDblToCharFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory, newMessage, param1, param2, param3);
	}

	static char tryApplyAsChar(double a, LDblToCharFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory);
	}

	static char tryApplyAsCharThen(double a, LDblToCharFunction func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsCharThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullApplyAsChar(double a) {
		return applyAsChar(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblToCharFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, @Nonnull LDblToCharFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsChar(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsChar(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a, @Nonnull LDblToCharFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsChar(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsChar(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a, @Nonnull LDblToCharFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	default LDblConsumer toConsumer() {
		return this::applyAsChar;
	}

	/** Calls domain consumer before main function. */
	default LDblToCharFunction beforeDo(@Nonnull LDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a) -> {
			before.accept(a);
			return applyAsChar(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LDblToCharFunction afterDo(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a) -> {
			final char retval = applyAsChar(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LDblToCharFunction constant(char r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblToCharFunction dblToCharFunc(final @Nonnull LDblToCharFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LDblToCharFunction {
		private LDblToCharFunction target = null;
		@Override
		public char applyAsCharX(double a) throws Throwable {
			return target.applyAsCharX(a);
		}
	}

	@Nonnull
	static LDblToCharFunction recursive(final @Nonnull LFunction<LDblToCharFunction, LDblToCharFunction> selfLambda) {
		final S single = new S();
		LDblToCharFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(double a, LDblToCharFunction function) {
		var initialValue = function.applyAsChar(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(char initialValue, LDblToCharFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(double a, LDblToCharFunction function, LCharBinaryOperator deltaFunction) {
		var initialValue = function.applyAsChar(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(double a, LDblToCharFunction function) {
		var initialValue = function.applyAsChar(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (char) (x2 - x1));
	}

	public static M initializedDeltaOf(char initialValue, LDblToCharFunction function, LCharBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsChar(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsChar(x1, x2));
	}

	public static M memento(char initialBaseValue, char initialValue, LDblToCharFunction baseFunction, LCharTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LDblToCharFunction.M)
	 */
	@NotThreadSafe
	final class M implements LDblToCharFunction {

		private final LDblToCharFunction baseFunction;
		private char lastBaseValue;
		private char lastValue;
		private final LCharTernaryOperator mementoFunction;

		private M(char lastBaseValue, char lastValue, LDblToCharFunction baseFunction, LCharTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public char applyAsCharX(double a) throws Throwable {
			char x1 = lastBaseValue;
			char x2 = lastBaseValue = baseFunction.applyAsCharX(a);

			return lastValue = mementoFunction.applyAsCharX(lastValue, x1, x2);
		}

		public char currentApplyAsChar(double a) {
			char x1 = lastBaseValue;
			char x2 = baseFunction.applyAsChar(a);

			return mementoFunction.applyAsChar(lastValue, x1, x2);
		}

		public char lastValue() {
			return lastValue;
		};

		public char lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LDblToCharFunction dblToCharFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblToCharFunction dblToCharFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static char call(double a, final @Nonnull LDblToCharFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsChar(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LDblToCharFunction compose(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsChar(before.applyAsDbl(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToCharFunction<V> unboxingCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsChar(before.applyAsDbl(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction thenToSrt(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction thenToFlt(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator thenToDbl(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsChar(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblToCharFunction) Function */
	public static char doNothing(double a) {
		return Function4U.defaultCharacter;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LCharConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.applyAsDbl(source, i);
			consumer.accept(this.applyAsChar(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LCharConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			double a = nextFunc0.applyAsDbl(iterator0);
			consumer.accept(this.applyAsChar(a));
		}
	}

}
