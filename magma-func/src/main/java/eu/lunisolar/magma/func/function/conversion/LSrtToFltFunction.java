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
 * Non-throwing functional interface (lambda) LSrtToFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short a
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtToFltFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aFloat>, Domain1<aShort> { // NOSONAR

	String DESCRIPTION = "LSrtToFltFunction: float applyAsFlt(short a)";

	// float applyAsFlt(short a) ;
	default float applyAsFlt(short a) {
		// return nestingApplyAsFlt(a);
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsFlt(short a)
	 */
	float applyAsFltX(short a) throws Throwable;

	default float tupleApplyAsFlt(LSrtSingle args) {
		return applyAsFlt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingApplyAsFlt(short a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtToFltFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsFlt(a, handling);
	}

	default float applyAsFlt(short a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LSrtToFltFunction trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsFlt(a, exF, newMessage, messageParams);
	}

	default float applyAsFlt(short a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LSrtToFltFunction trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsFlt(a, exF);
	}

	default float applyAsFltThen(short a, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LSrtToFltFunction tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return a -> applyAsFltThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingApplyAsFlt(short a) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingApplyAsFlt(short a) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float handlingApplyAsFlt(short a, LSrtToFltFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsFlt(a, handling);
	}

	static float tryApplyAsFlt(short a, LSrtToFltFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsFlt(a);
	}

	static float tryApplyAsFlt(short a, LSrtToFltFunction func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, exF, newMessage, messageParams);
	}

	static float tryApplyAsFlt(short a, LSrtToFltFunction func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, exF);
	}

	static float tryApplyAsFltThen(short a, LSrtToFltFunction func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsFltThen(a, handler);
	}

	default float failSafeApplyAsFlt(short a, @Nonnull LSrtToFltFunction failSafe) {
		try {
			return applyAsFlt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsFlt(a);
		}
	}

	static float failSafeApplyAsFlt(short a, LSrtToFltFunction func, @Nonnull LSrtToFltFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsFlt(a);
		} else {
			return func.failSafeApplyAsFlt(a, failSafe);
		}
	}

	static LSrtToFltFunction failSafe(LSrtToFltFunction func, @Nonnull LSrtToFltFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsFlt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullApplyAsFlt(short a) {
		return applyAsFlt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtToFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a, @Nonnull LSrtToFltFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsFlt(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsFlt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, short a, @Nonnull LSrtToFltFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsFlt(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsFlt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, short a, @Nonnull LSrtToFltFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LSrtConsumer toConsumer() {
		return this::applyAsFlt;
	}

	/** Calls domain consumer before main function. */
	public default LSrtToFltFunction beforeDo(@Nonnull LSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a) -> {
			before.accept(a);
			return applyAsFlt(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LSrtToFltFunction afterDo(@Nonnull LFltConsumer after) {
		Null.nonNullArg(after, "after");
		return (short a) -> {
			final float retval = applyAsFlt(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier capture(short a) {
		return () -> this.applyAsFlt(a);
	}

	/** Creates function that always returns the same value. */
	static LSrtToFltFunction constant(float r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtToFltFunction srtToFltFunc(final @Nonnull LSrtToFltFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtToFltFunction recursive(final @Nonnull LFunction<LSrtToFltFunction, LSrtToFltFunction> selfLambda) {
		final LSrtToFltFunctionSingle single = new LSrtToFltFunctionSingle();
		LSrtToFltFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LSrtToFltFunctionSingle implements LSrtToFltFunction {
		private LSrtToFltFunction target = null;

		@Override
		public float applyAsFltX(short a) throws Throwable {
			return target.applyAsFltX(a);
		}

	}

	@Nonnull
	static LSrtToFltFunction srtToFltFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtToFltFunction srtToFltFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static float call(short a, final @Nonnull LSrtToFltFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsFlt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LSrtToFltFunction safe() {
		return LSrtToFltFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToFltFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtToFltFunction safe(final @Nullable LSrtToFltFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToFltFunction> safeSupplier(final @Nullable LSupplier<LSrtToFltFunction> supplier) {
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
	default LSrtToFltFunction compose(@Nonnull final LSrtUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsFlt(before.applyAsSrt(v));
	}

	public static LSrtToFltFunction composed(@Nonnull final LSrtUnaryOperator before, LSrtToFltFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToFltFunction<V> srtToFltFuncCompose(@Nonnull final LToSrtFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsFlt(before.applyAsSrt(v));
	}

	public static <V> LToFltFunction<V> composed(@Nonnull final LToSrtFunction<? super V> before, LSrtToFltFunction after) {
		return after.srtToFltFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSrtFunction<V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToByteFunction thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtUnaryOperator thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToIntFunction thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToLongFunction thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToFltFunction thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToDblFunction thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToCharFunction thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtPredicate thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsFlt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtToFltFunction) Function */
	public static float doNothing(short a) {
		return Function4U.defaultFloat;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aShort> ia, C0 source, LFltConsumer consumer) {
		int size = ia.size(source);
		LOiToSrtFunction<Object> oiFunc0 = (LOiToSrtFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			short a = oiFunc0.applyAsSrt(source, i);
			consumer.accept(this.applyAsFlt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aShort> sa, C0 source, LFltConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToSrtFunction<Object> nextFunc0 = (LToSrtFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			short a = nextFunc0.applyAsSrt(iterator0);
			consumer.accept(this.applyAsFlt(a));
		}
	}

}
