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
import java.util.stream.Stream; // NOSONAR

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
 * Non-throwing functional interface (lambda) LCharToDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): char a
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharToDblFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aDouble>, Domain1<aChar> { //NOSONAR

	String DESCRIPTION = "LCharToDblFunction: double applyAsDbl(char a)";

	default double applyAsDbl(char a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(char a)
	 */
	double applyAsDblX(char a) throws Throwable;

	default double tupleApplyAsDbl(LCharSingle args) {
		return applyAsDbl(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(char a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LCharToDblFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsDbl(a, handling);
	}

	default double applyAsDbl(char a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default double applyAsDbl(char a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default double applyAsDbl(char a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default double applyAsDbl(char a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LCharToDblFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsDbl(a, factory, newMessage);
	}

	default LCharToDblFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsDbl(a, factory, newMessage, param1);
	}

	default LCharToDblFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsDbl(a, factory, newMessage, param1, param1);
	}

	default LCharToDblFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsDbl(a, factory, newMessage, param1, param2, param3);
	}

	default double applyAsDbl(char a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LCharToDblFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsDbl(a, factory);
	}

	default double applyAsDblThen(char a, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LCharToDblFunction tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return a -> applyAsDblThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(char a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(char a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double shovingApplyAsDbl(char a, LCharToDblFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsDbl(a);
	}

	static double handlingApplyAsDbl(char a, LCharToDblFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a, handling);
	}

	static double tryApplyAsDbl(char a, LCharToDblFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a);
	}

	static double tryApplyAsDbl(char a, LCharToDblFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage);
	}

	static double tryApplyAsDbl(char a, LCharToDblFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1);
	}

	static double tryApplyAsDbl(char a, LCharToDblFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1, param2);
	}

	static double tryApplyAsDbl(char a, LCharToDblFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1, param2, param3);
	}

	static double tryApplyAsDbl(char a, LCharToDblFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory);
	}

	static double tryApplyAsDblThen(char a, LCharToDblFunction func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(char a) {
		return applyAsDbl(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharToDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a, @Nonnull LCharToDblFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsDbl(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, char a, @Nonnull LCharToDblFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsDbl(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, char a, @Nonnull LCharToDblFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to one that ignores output. */
	default LCharConsumer toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	default LCharToDblFunction beforeDo(@Nonnull LCharConsumer before) {
		Null.nonNullArg(before, "before");
		return (char a) -> {
			before.accept(a);
			return applyAsDbl(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LCharToDblFunction afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (char a) -> {
			final double retval = applyAsDbl(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LCharToDblFunction constant(double r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharToDblFunction charToDblFunc(final @Nonnull LCharToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LCharToDblFunction {
		private LCharToDblFunction target = null;
		@Override
		public double applyAsDblX(char a) throws Throwable {
			return target.applyAsDblX(a);
		}
	}

	@Nonnull
	static LCharToDblFunction recursive(final @Nonnull LFunction<LCharToDblFunction, LCharToDblFunction> selfLambda) {
		final S single = new S();
		LCharToDblFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(char a, LCharToDblFunction function) {
		var initialValue = function.applyAsDbl(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(double initialValue, LCharToDblFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(char a, LCharToDblFunction function, LDblBinaryOperator deltaFunction) {
		var initialValue = function.applyAsDbl(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(char a, LCharToDblFunction function) {
		var initialValue = function.applyAsDbl(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(double initialValue, LCharToDblFunction function, LDblBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsDbl(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsDbl(x1, x2));
	}

	public static M memento(double initialBaseValue, double initialValue, LCharToDblFunction baseFunction, LDblTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LCharToDblFunction.M)
	 */
	@NotThreadSafe
	final class M implements LCharToDblFunction {

		private final LCharToDblFunction baseFunction;
		private double lastBaseValue;
		private double lastValue;
		private final LDblTernaryOperator mementoFunction;

		private M(double lastBaseValue, double lastValue, LCharToDblFunction baseFunction, LDblTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public double applyAsDblX(char a) throws Throwable {
			double x1 = lastBaseValue;
			double x2 = lastBaseValue = baseFunction.applyAsDblX(a);

			return lastValue = mementoFunction.applyAsDblX(lastValue, x1, x2);
		}

		public double currentApplyAsDbl(char a) {
			double x1 = lastBaseValue;
			double x2 = baseFunction.applyAsDbl(a);

			return mementoFunction.applyAsDbl(lastValue, x1, x2);
		}

		public double lastValue() {
			return lastValue;
		}

		public double lastBaseValue() {
			return lastBaseValue;
		}

		public double currentBaseValue(char a) {
			return baseFunction.applyAsDbl(a);
		}
	}

	// </editor-fold>

	@Nonnull
	static LCharToDblFunction charToDblFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LCharToDblFunction charToDblFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static double call(char a, final @Nonnull LCharToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LCharToDblFunction compose(@Nonnull final LCharUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.applyAsChar(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToDblFunction<V> unboxingCompose(@Nonnull final LToCharFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.applyAsChar(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunction<V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunction thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToSrtFunction thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunction thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunction thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFltFunction thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDblFunction thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperator thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsDbl(a));
	}

	// </editor-fold>

	default LCharToDblFunction shoving() {

		return new LCharToDblFunction() {

			public double applyAsDbl(char a) {
				try {
					return this.applyAsDblX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public double applyAsDblX(char a) throws Throwable {
				return LCharToDblFunction.this.applyAsDblX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LCharToDblFunction) Function */
	public static double doNothing(char a) {
		return Function4U.defaultDouble;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aChar> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		var oiFunc0 = IA.charGetter(ia);
		int i = 0;
		for (; i < size; i++) {
			char a = oiFunc0.applyAsChar(source, i);
			consumer.accept(this.applyAsDbl(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aChar> sa, C0 source, LDblConsumer consumer) {
		var iterator0 = SA.adapter(sa).apply(source);
		var testFunc0 = SA.tester(sa);
		var nextFunc0 = SA.charSupplier(sa);
		while (testFunc0.test(iterator0)) {
			char a = nextFunc0.applyAsChar(iterator0);
			consumer.accept(this.applyAsDbl(a));
		}
	}

}
