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

package eu.lunisolar.magma.func.function.conversion;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
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
 * Non-throwing functional interface (lambda) LFltToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float a
 *
 * Co-domain: byte
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltToByteFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aByte>, Domain1<aFloat> { // NOSONAR

	String DESCRIPTION = "LFltToByteFunction: byte applyAsByte(float a)";

	// byte applyAsByte(float a) ;
	default byte applyAsByte(float a) {
		// return nestingApplyAsByte(a);
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsByte(float a)
	 */
	byte applyAsByteX(float a) throws Throwable;

	default byte tupleApplyAsByte(LFltSingle args) {
		return applyAsByte(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingApplyAsByte(float a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LFltToByteFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsByte(a, handling);
	}

	default byte applyAsByte(float a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LFltToByteFunction trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsByte(a, exF, newMessage, messageParams);
	}

	default byte applyAsByte(float a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LFltToByteFunction trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsByte(a, exF);
	}

	default byte applyAsByteThen(float a, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsByte(e);
		}
	}

	default LFltToByteFunction tryingThen(@Nonnull LToByteFunction<Throwable> handler) {
		return a -> applyAsByteThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingApplyAsByte(float a) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingApplyAsByte(float a) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte handlingApplyAsByte(float a, LFltToByteFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsByte(a, handling);
	}

	static byte tryApplyAsByte(float a, LFltToByteFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsByte(a);
	}

	static byte tryApplyAsByte(float a, LFltToByteFunction func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, exF, newMessage, messageParams);
	}

	static byte tryApplyAsByte(float a, LFltToByteFunction func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, exF);
	}

	static byte tryApplyAsByteThen(float a, LFltToByteFunction func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsByteThen(a, handler);
	}

	default byte failSafeApplyAsByte(float a, @Nonnull LFltToByteFunction failSafe) {
		try {
			return applyAsByte(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsByte(a);
		}
	}

	static byte failSafeApplyAsByte(float a, LFltToByteFunction func, @Nonnull LFltToByteFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsByte(a);
		} else {
			return func.failSafeApplyAsByte(a, failSafe);
		}
	}

	static LFltToByteFunction failSafe(LFltToByteFunction func, @Nonnull LFltToByteFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsByte(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullApplyAsByte(float a) {
		return applyAsByte(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltToByteFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a, LFltToByteFunction func) {
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
	public static void fromTill(int min_i, int max_i, float a, LFltToByteFunction func) {
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
	public static void times(int max_i, float a, LFltToByteFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LFltConsumer toConsumer() {
		return this::applyAsByte;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier capture(float a) {
		return () -> this.applyAsByte(a);
	}

	/** Creates function that always returns the same value. */
	static LFltToByteFunction constant(byte r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltToByteFunction fltToByteFunc(final @Nonnull LFltToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LFltToByteFunction recursive(final @Nonnull LFunction<LFltToByteFunction, LFltToByteFunction> selfLambda) {
		final LFltToByteFunctionSingle single = new LFltToByteFunctionSingle();
		LFltToByteFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LFltToByteFunctionSingle implements LSingle<LFltToByteFunction>, LFltToByteFunction {
		private LFltToByteFunction target = null;

		@Override
		public byte applyAsByteX(float a) throws Throwable {
			return target.applyAsByteX(a);
		}

		@Override
		public LFltToByteFunction value() {
			return target;
		}
	}

	@Nonnull
	static LFltToByteFunction fltToByteFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LFltToByteFunction fltToByteFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static byte call(float a, final @Nonnull LFltToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsByte(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceByte). */
	@Nonnull
	static LFltToByteFunction safe() {
		return LFltToByteFunction::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltToByteFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFltToByteFunction safe(final @Nullable LFltToByteFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltToByteFunction> safeSupplier(final @Nullable LSupplier<LFltToByteFunction> supplier) {
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
	default LFltToByteFunction compose(@Nonnull final LFltUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsByte(before.applyAsFlt(v));
	}

	public static LFltToByteFunction composed(@Nonnull final LFltUnaryOperator before, LFltToByteFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToByteFunction<V> fltToByteFuncCompose(@Nonnull final LToFltFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsByte(before.applyAsFlt(v));
	}

	public static <V> LToByteFunction<V> composed(@Nonnull final LToFltFunction<? super V> before, LFltToByteFunction after) {
		return after.fltToByteFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFltFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToByteFunction thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToSrtFunction thenToSrt(@Nonnull LByteToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToIntFunction thenToInt(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToLongFunction thenToLong(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltUnaryOperator thenToFlt(@Nonnull LByteToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToDblFunction thenToDbl(@Nonnull LByteToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToCharFunction thenToChar(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltPredicate thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsByte(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LFltToByteFunction) Function */
	public static byte produceByte(float a) {
		return Function4U.defaultByte;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aFloat> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiToFltFunction<Object> oiFunc0 = (LOiToFltFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			float a = oiFunc0.applyAsFlt(source, i);
			consumer.accept(this.applyAsByte(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aFloat> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToFltFunction<Object> nextFunc0 = (LToFltFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			float a = nextFunc0.applyAsFlt(iterator0);
			consumer.accept(this.applyAsByte(a));
		}
	}

}