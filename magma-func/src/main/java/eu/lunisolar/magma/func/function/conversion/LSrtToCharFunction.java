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
 * Non-throwing functional interface (lambda) LSrtToCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short a
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtToCharFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aChar>, Domain1<aShort> { // NOSONAR

	String DESCRIPTION = "LSrtToCharFunction: char applyAsChar(short a)";

	// char applyAsChar(short a) ;
	default char applyAsChar(short a) {
		// return nestingApplyAsChar(a);
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsChar(short a)
	 */
	char applyAsCharX(short a) throws Throwable;

	default char tupleApplyAsChar(LSrtSingle args) {
		return applyAsChar(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingApplyAsChar(short a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtToCharFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsChar(a, handling);
	}

	default char applyAsChar(short a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LSrtToCharFunction trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsChar(a, exF, newMessage, messageParams);
	}

	default char applyAsChar(short a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LSrtToCharFunction trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsChar(a, exF);
	}

	default char applyAsCharThen(short a, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsChar(e);
		}
	}

	default LSrtToCharFunction tryingThen(@Nonnull LToCharFunction<Throwable> handler) {
		return a -> applyAsCharThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingApplyAsChar(short a) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingApplyAsChar(short a) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static char handlingApplyAsChar(short a, LSrtToCharFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsChar(a, handling);
	}

	static char tryApplyAsChar(short a, LSrtToCharFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsChar(a);
	}

	static char tryApplyAsChar(short a, LSrtToCharFunction func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, exF, newMessage, messageParams);
	}

	static char tryApplyAsChar(short a, LSrtToCharFunction func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, exF);
	}

	static char tryApplyAsCharThen(short a, LSrtToCharFunction func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsCharThen(a, handler);
	}

	default char failSafeApplyAsChar(short a, @Nonnull LSrtToCharFunction failSafe) {
		try {
			return applyAsChar(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsChar(a);
		}
	}

	static char failSafeApplyAsChar(short a, LSrtToCharFunction func, @Nonnull LSrtToCharFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsChar(a);
		} else {
			return func.failSafeApplyAsChar(a, failSafe);
		}
	}

	static LSrtToCharFunction failSafe(LSrtToCharFunction func, @Nonnull LSrtToCharFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsChar(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullApplyAsChar(short a) {
		return applyAsChar(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtToCharFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a, @Nonnull LSrtToCharFunction func) {
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
	public static void fromTill(int min_i, int max_i, short a, @Nonnull LSrtToCharFunction func) {
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
	public static void times(int max_i, short a, @Nonnull LSrtToCharFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LSrtConsumer toConsumer() {
		return this::applyAsChar;
	}

	/** Calls domain consumer before main function. */
	public default LSrtToCharFunction beforeDo(@Nonnull LSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a) -> {
			before.accept(a);
			return applyAsChar(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LSrtToCharFunction afterDo(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return (short a) -> {
			final char retval = applyAsChar(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier capture(short a) {
		return () -> this.applyAsChar(a);
	}

	/** Creates function that always returns the same value. */
	static LSrtToCharFunction constant(char r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtToCharFunction srtToCharFunc(final @Nonnull LSrtToCharFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtToCharFunction recursive(final @Nonnull LFunction<LSrtToCharFunction, LSrtToCharFunction> selfLambda) {
		final LSrtToCharFunctionSingle single = new LSrtToCharFunctionSingle();
		LSrtToCharFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LSrtToCharFunctionSingle implements LSingle<LSrtToCharFunction>, LSrtToCharFunction {
		private LSrtToCharFunction target = null;

		@Override
		public char applyAsCharX(short a) throws Throwable {
			return target.applyAsCharX(a);
		}

		@Override
		public LSrtToCharFunction value() {
			return target;
		}
	}

	@Nonnull
	static LSrtToCharFunction srtToCharFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtToCharFunction srtToCharFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static char call(short a, final @Nonnull LSrtToCharFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsChar(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceChar). */
	@Nonnull
	static LSrtToCharFunction safe() {
		return LSrtToCharFunction::produceChar;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToCharFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtToCharFunction safe(final @Nullable LSrtToCharFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToCharFunction> safeSupplier(final @Nullable LSupplier<LSrtToCharFunction> supplier) {
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
	default LSrtToCharFunction compose(@Nonnull final LSrtUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsChar(before.applyAsSrt(v));
	}

	public static LSrtToCharFunction composed(@Nonnull final LSrtUnaryOperator before, LSrtToCharFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToCharFunction<V> srtToCharFuncCompose(@Nonnull final LToSrtFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsChar(before.applyAsSrt(v));
	}

	public static <V> LToCharFunction<V> composed(@Nonnull final LToSrtFunction<? super V> before, LSrtToCharFunction after) {
		return after.srtToCharFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSrtFunction<V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToByteFunction thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtUnaryOperator thenToSrt(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToIntFunction thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToLongFunction thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToFltFunction thenToFlt(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToDblFunction thenToDbl(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToCharFunction thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtPredicate thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsChar(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtToCharFunction) Function */
	public static char produceChar(short a) {
		return Function4U.defaultCharacter;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aShort> ia, C0 source, LCharConsumer consumer) {
		int size = ia.size(source);
		LOiToSrtFunction<Object> oiFunc0 = (LOiToSrtFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			short a = oiFunc0.applyAsSrt(source, i);
			consumer.accept(this.applyAsChar(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aShort> sa, C0 source, LCharConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToSrtFunction<Object> nextFunc0 = (LToSrtFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			short a = nextFunc0.applyAsSrt(iterator0);
			consumer.accept(this.applyAsChar(a));
		}
	}

}
