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
 * Non-throwing functional interface (lambda) LSrtToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short a
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtToLongFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aLong>, Domain1<aShort> { // NOSONAR

	String DESCRIPTION = "LSrtToLongFunction: long applyAsLong(short a)";

	// long applyAsLong(short a) ;
	default long applyAsLong(short a) {
		// return nestingApplyAsLong(a);
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsLong(short a)
	 */
	long applyAsLongX(short a) throws Throwable;

	default long tupleApplyAsLong(LSrtSingle args) {
		return applyAsLong(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingApplyAsLong(short a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtToLongFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsLong(a, handling);
	}

	default long applyAsLong(short a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LSrtToLongFunction trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsLong(a, exF, newMessage, messageParams);
	}

	default long applyAsLong(short a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LSrtToLongFunction trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsLong(a, exF);
	}

	default long applyAsLongThen(short a, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsLong(e);
		}
	}

	default LSrtToLongFunction tryingThen(@Nonnull LToLongFunction<Throwable> handler) {
		return a -> applyAsLongThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingApplyAsLong(short a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingApplyAsLong(short a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long handlingApplyAsLong(short a, LSrtToLongFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsLong(a, handling);
	}

	static long tryApplyAsLong(short a, LSrtToLongFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsLong(a);
	}

	static long tryApplyAsLong(short a, LSrtToLongFunction func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, exF, newMessage, messageParams);
	}

	static long tryApplyAsLong(short a, LSrtToLongFunction func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, exF);
	}

	static long tryApplyAsLongThen(short a, LSrtToLongFunction func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsLongThen(a, handler);
	}

	default long failSafeApplyAsLong(short a, @Nonnull LSrtToLongFunction failSafe) {
		try {
			return applyAsLong(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsLong(a);
		}
	}

	static long failSafeApplyAsLong(short a, LSrtToLongFunction func, @Nonnull LSrtToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsLong(a);
		} else {
			return func.failSafeApplyAsLong(a, failSafe);
		}
	}

	static LSrtToLongFunction failSafe(LSrtToLongFunction func, @Nonnull LSrtToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsLong(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullApplyAsLong(short a) {
		return applyAsLong(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtToLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a, @Nonnull LSrtToLongFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsLong(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, short a, @Nonnull LSrtToLongFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsLong(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, short a, @Nonnull LSrtToLongFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LSrtConsumer toConsumer() {
		return this::applyAsLong;
	}

	/** Calls domain consumer before main function. */
	public default LSrtToLongFunction beforeDo(@Nonnull LSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a) -> {
			before.accept(a);
			return applyAsLong(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LSrtToLongFunction afterDo(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return (short a) -> {
			final long retval = applyAsLong(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier capture(short a) {
		return () -> this.applyAsLong(a);
	}

	/** Creates function that always returns the same value. */
	static LSrtToLongFunction constant(long r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtToLongFunction srtToLongFunc(final @Nonnull LSrtToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtToLongFunction recursive(final @Nonnull LFunction<LSrtToLongFunction, LSrtToLongFunction> selfLambda) {
		final LSrtToLongFunctionSingle single = new LSrtToLongFunctionSingle();
		LSrtToLongFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LSrtToLongFunctionSingle implements LSrtToLongFunction {
		private LSrtToLongFunction target = null;

		@Override
		public long applyAsLongX(short a) throws Throwable {
			return target.applyAsLongX(a);
		}

	}

	@Nonnull
	static LSrtToLongFunction srtToLongFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtToLongFunction srtToLongFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static long call(short a, final @Nonnull LSrtToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsLong(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LSrtToLongFunction safe() {
		return LSrtToLongFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToLongFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtToLongFunction safe(final @Nullable LSrtToLongFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToLongFunction> safeSupplier(final @Nullable LSupplier<LSrtToLongFunction> supplier) {
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
	default LSrtToLongFunction compose(@Nonnull final LSrtUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsLong(before.applyAsSrt(v));
	}

	public static LSrtToLongFunction composed(@Nonnull final LSrtUnaryOperator before, LSrtToLongFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToLongFunction<V> srtToLongFuncCompose(@Nonnull final LToSrtFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsLong(before.applyAsSrt(v));
	}

	public static <V> LToLongFunction<V> composed(@Nonnull final LToSrtFunction<? super V> before, LSrtToLongFunction after) {
		return after.srtToLongFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSrtFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtUnaryOperator thenToSrt(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToIntFunction thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToFltFunction thenToFlt(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToDblFunction thenToDbl(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsLong(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtToLongFunction) Function */
	public static long doNothing(short a) {
		return Function4U.defaultLong;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aShort> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		LOiToSrtFunction<Object> oiFunc0 = (LOiToSrtFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			short a = oiFunc0.applyAsSrt(source, i);
			consumer.accept(this.applyAsLong(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aShort> sa, C0 source, LLongConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToSrtFunction<Object> nextFunc0 = (LToSrtFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			short a = nextFunc0.applyAsSrt(iterator0);
			consumer.accept(this.applyAsLong(a));
		}
	}

}
