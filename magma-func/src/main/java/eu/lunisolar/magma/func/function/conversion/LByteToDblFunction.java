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
 * Non-throwing functional interface (lambda) LByteToDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte a
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteToDblFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aDouble>, Domain1<aByte> { // NOSONAR

	String DESCRIPTION = "LByteToDblFunction: double applyAsDbl(byte a)";

	// double applyAsDbl(byte a) ;
	default double applyAsDbl(byte a) {
		// return nestingApplyAsDbl(a);
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(byte a)
	 */
	double applyAsDblX(byte a) throws Throwable;

	default double tupleApplyAsDbl(LByteSingle args) {
		return applyAsDbl(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(byte a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LByteToDblFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsDbl(a, handling);
	}

	default double applyAsDbl(byte a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LByteToDblFunction trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsDbl(a, exF, newMessage, messageParams);
	}

	default double applyAsDbl(byte a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LByteToDblFunction trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsDbl(a, exF);
	}

	default double applyAsDblThen(byte a, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LByteToDblFunction tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return a -> applyAsDblThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(byte a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(byte a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double handlingApplyAsDbl(byte a, LByteToDblFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a, handling);
	}

	static double tryApplyAsDbl(byte a, LByteToDblFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a);
	}

	static double tryApplyAsDbl(byte a, LByteToDblFunction func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, exF, newMessage, messageParams);
	}

	static double tryApplyAsDbl(byte a, LByteToDblFunction func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, exF);
	}

	static double tryApplyAsDblThen(byte a, LByteToDblFunction func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a, handler);
	}

	default double failSafeApplyAsDbl(byte a, @Nonnull LByteToDblFunction failSafe) {
		try {
			return applyAsDbl(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsDbl(a);
		}
	}

	static double failSafeApplyAsDbl(byte a, LByteToDblFunction func, @Nonnull LByteToDblFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsDbl(a);
		} else {
			return func.failSafeApplyAsDbl(a, failSafe);
		}
	}

	static LByteToDblFunction failSafe(LByteToDblFunction func, @Nonnull LByteToDblFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsDbl(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(byte a) {
		return applyAsDbl(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteToDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a, @Nonnull LByteToDblFunction func) {
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
	public static void fromTill(int min_i, int max_i, byte a, @Nonnull LByteToDblFunction func) {
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
	public static void times(int max_i, byte a, @Nonnull LByteToDblFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LByteConsumer toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	public default LByteToDblFunction beforeDo(@Nonnull LByteConsumer before) {
		Null.nonNullArg(before, "before");
		return (byte a) -> {
			before.accept(a);
			return applyAsDbl(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LByteToDblFunction afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (byte a) -> {
			final double retval = applyAsDbl(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier capture(byte a) {
		return () -> this.applyAsDbl(a);
	}

	/** Creates function that always returns the same value. */
	static LByteToDblFunction constant(double r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteToDblFunction byteToDblFunc(final @Nonnull LByteToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LByteToDblFunction recursive(final @Nonnull LFunction<LByteToDblFunction, LByteToDblFunction> selfLambda) {
		final LByteToDblFunctionSingle single = new LByteToDblFunctionSingle();
		LByteToDblFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LByteToDblFunctionSingle implements LSingle<LByteToDblFunction>, LByteToDblFunction {
		private LByteToDblFunction target = null;

		@Override
		public double applyAsDblX(byte a) throws Throwable {
			return target.applyAsDblX(a);
		}

		@Override
		public LByteToDblFunction value() {
			return target;
		}
	}

	@Nonnull
	static LByteToDblFunction byteToDblFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LByteToDblFunction byteToDblFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static double call(byte a, final @Nonnull LByteToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceDouble). */
	@Nonnull
	static LByteToDblFunction safe() {
		return LByteToDblFunction::produceDouble;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteToDblFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LByteToDblFunction safe(final @Nullable LByteToDblFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteToDblFunction> safeSupplier(final @Nullable LSupplier<LByteToDblFunction> supplier) {
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
	default LByteToDblFunction compose(@Nonnull final LByteUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.applyAsByte(v));
	}

	public static LByteToDblFunction composed(@Nonnull final LByteUnaryOperator before, LByteToDblFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToDblFunction<V> byteToDblFuncCompose(@Nonnull final LToByteFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.applyAsByte(v));
	}

	public static <V> LToDblFunction<V> composed(@Nonnull final LToByteFunction<? super V> before, LByteToDblFunction after) {
		return after.byteToDblFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToSrtFunction thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFltFunction thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDblFunction thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsDbl(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LByteToDblFunction) Function */
	public static double produceDouble(byte a) {
		return Function4U.defaultDouble;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aByte> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiToByteFunction<Object> oiFunc0 = (LOiToByteFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a = oiFunc0.applyAsByte(source, i);
			consumer.accept(this.applyAsDbl(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aByte> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToByteFunction<Object> nextFunc0 = (LToByteFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			byte a = nextFunc0.applyAsByte(iterator0);
			consumer.accept(this.applyAsDbl(a));
		}
	}

}
