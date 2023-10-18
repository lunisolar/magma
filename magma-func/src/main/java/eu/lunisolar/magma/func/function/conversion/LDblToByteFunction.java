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
 * Non-throwing functional interface (lambda) LDblToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: byte
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblToByteFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aByte>, Domain1<aDouble> { // NOSONAR

	String DESCRIPTION = "LDblToByteFunction: byte applyAsByte(double a)";

	// byte applyAsByte(double a) ;
	default byte applyAsByte(double a) {
		// return nestingApplyAsByte(a);
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsByte(double a)
	 */
	byte applyAsByteX(double a) throws Throwable;

	default byte tupleApplyAsByte(LDblSingle args) {
		return applyAsByte(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingApplyAsByte(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblToByteFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsByte(a, handling);
	}

	default byte applyAsByte(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default byte applyAsByte(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default byte applyAsByte(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default byte applyAsByte(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LDblToByteFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsByte(a, factory, newMessage);
	}

	default LDblToByteFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsByte(a, factory, newMessage, param1);
	}

	default LDblToByteFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsByte(a, factory, newMessage, param1, param1);
	}

	default LDblToByteFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsByte(a, factory, newMessage, param1, param2, param3);
	}

	default byte applyAsByte(double a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LDblToByteFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsByte(a, factory);
	}

	default byte applyAsByteThen(double a, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsByte(e);
		}
	}

	default LDblToByteFunction tryingThen(@Nonnull LToByteFunction<Throwable> handler) {
		return a -> applyAsByteThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingApplyAsByte(double a) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingApplyAsByte(double a) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte shovingApplyAsByte(double a, LDblToByteFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsByte(a);
	}

	static byte handlingApplyAsByte(double a, LDblToByteFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsByte(a, handling);
	}

	static byte tryApplyAsByte(double a, LDblToByteFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsByte(a);
	}

	static byte tryApplyAsByte(double a, LDblToByteFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, factory, newMessage);
	}

	static byte tryApplyAsByte(double a, LDblToByteFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, factory, newMessage, param1);
	}

	static byte tryApplyAsByte(double a, LDblToByteFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, factory, newMessage, param1, param2);
	}

	static byte tryApplyAsByte(double a, LDblToByteFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, factory, newMessage, param1, param2, param3);
	}

	static byte tryApplyAsByte(double a, LDblToByteFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, factory);
	}

	static byte tryApplyAsByteThen(double a, LDblToByteFunction func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsByteThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullApplyAsByte(double a) {
		return applyAsByte(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblToByteFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, @Nonnull LDblToByteFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsByte(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsByte(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a, @Nonnull LDblToByteFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsByte(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsByte(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a, @Nonnull LDblToByteFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to one that ignores output. */
	default LDblConsumer toConsumer() {
		return this::applyAsByte;
	}

	/** Calls domain consumer before main function. */
	default LDblToByteFunction beforeDo(@Nonnull LDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a) -> {
			before.accept(a);
			return applyAsByte(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LDblToByteFunction afterDo(@Nonnull LByteConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a) -> {
			final byte retval = applyAsByte(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LDblToByteFunction constant(byte r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblToByteFunction dblToByteFunc(final @Nonnull LDblToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LDblToByteFunction {
		private LDblToByteFunction target = null;
		@Override
		public byte applyAsByteX(double a) throws Throwable {
			return target.applyAsByteX(a);
		}
	}

	@Nonnull
	static LDblToByteFunction recursive(final @Nonnull LFunction<LDblToByteFunction, LDblToByteFunction> selfLambda) {
		final S single = new S();
		LDblToByteFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(double a, LDblToByteFunction function) {
		var initialValue = function.applyAsByte(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(byte initialValue, LDblToByteFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(double a, LDblToByteFunction function, LByteBinaryOperator deltaFunction) {
		var initialValue = function.applyAsByte(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(double a, LDblToByteFunction function) {
		var initialValue = function.applyAsByte(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (byte) (x2 - x1));
	}

	public static M initializedDeltaOf(byte initialValue, LDblToByteFunction function, LByteBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsByte(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsByte(x1, x2));
	}

	public static M memento(byte initialBaseValue, byte initialValue, LDblToByteFunction baseFunction, LByteTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LDblToByteFunction.M)
	 */
	@NotThreadSafe
	final class M implements LDblToByteFunction {

		private final LDblToByteFunction baseFunction;
		private byte lastBaseValue;
		private byte lastValue;
		private final LByteTernaryOperator mementoFunction;

		private M(byte lastBaseValue, byte lastValue, LDblToByteFunction baseFunction, LByteTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public byte applyAsByteX(double a) throws Throwable {
			byte x1 = lastBaseValue;
			byte x2 = lastBaseValue = baseFunction.applyAsByteX(a);

			return lastValue = mementoFunction.applyAsByteX(lastValue, x1, x2);
		}

		public byte currentApplyAsByte(double a) {
			byte x1 = lastBaseValue;
			byte x2 = baseFunction.applyAsByte(a);

			return mementoFunction.applyAsByte(lastValue, x1, x2);
		}

		public byte lastValue() {
			return lastValue;
		};

		public byte lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LDblToByteFunction dblToByteFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblToByteFunction dblToByteFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static byte call(double a, final @Nonnull LDblToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsByte(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LDblToByteFunction compose(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsByte(before.applyAsDbl(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToByteFunction<V> unboxingCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsByte(before.applyAsDbl(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction thenToSrt(@Nonnull LByteToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction thenToInt(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction thenToLong(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction thenToFlt(@Nonnull LByteToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator thenToDbl(@Nonnull LByteToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction thenToChar(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsByte(a));
	}

	// </editor-fold>

	default LDblToByteFunction shoving() {

		return new LDblToByteFunction() {

			public byte applyAsByte(double a) {
				try {
					return this.applyAsByteX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public byte applyAsByteX(double a) throws Throwable {
				return LDblToByteFunction.this.applyAsByteX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblToByteFunction) Function */
	public static byte doNothing(double a) {
		return Function4U.defaultByte;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.applyAsDbl(source, i);
			consumer.accept(this.applyAsByte(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			double a = nextFunc0.applyAsDbl(iterator0);
			consumer.accept(this.applyAsByte(a));
		}
	}

}
