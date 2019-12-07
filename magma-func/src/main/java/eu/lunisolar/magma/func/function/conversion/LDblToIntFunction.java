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
 * Non-throwing functional interface (lambda) LDblToIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblToIntFunction extends DoubleToIntFunction, MetaFunction, MetaInterface.NonThrowing, Codomain<aInt>, Domain1<aDouble> { // NOSONAR

	String DESCRIPTION = "LDblToIntFunction: int applyAsInt(double a)";

	// int applyAsInt(double a) ;
	default int applyAsInt(double a) {
		// return nestingApplyAsInt(a);
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(double a)
	 */
	int applyAsIntX(double a) throws Throwable;

	default int tupleApplyAsInt(LDblSingle args) {
		return applyAsInt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblToIntFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsInt(a, handling);
	}

	default int applyAsInt(double a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LDblToIntFunction trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsInt(a, exF, newMessage, messageParams);
	}

	default int applyAsInt(double a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LDblToIntFunction trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsInt(a, exF);
	}

	default int applyAsIntThen(double a, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LDblToIntFunction tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return a -> applyAsIntThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(double a) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(double a) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static int handlingApplyAsInt(double a, LDblToIntFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a, handling);
	}

	static int tryApplyAsInt(double a, LDblToIntFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a);
	}

	static int tryApplyAsInt(double a, LDblToIntFunction func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a, exF, newMessage, messageParams);
	}

	static int tryApplyAsInt(double a, LDblToIntFunction func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a, exF);
	}

	static int tryApplyAsIntThen(double a, LDblToIntFunction func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a, handler);
	}

	default int failSafeApplyAsInt(double a, @Nonnull LDblToIntFunction failSafe) {
		try {
			return applyAsInt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsInt(a);
		}
	}

	static int failSafeApplyAsInt(double a, LDblToIntFunction func, @Nonnull LDblToIntFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsInt(a);
		} else {
			return func.failSafeApplyAsInt(a, failSafe);
		}
	}

	static LDblToIntFunction failSafe(LDblToIntFunction func, @Nonnull LDblToIntFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsInt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(double a) {
		return applyAsInt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblToIntFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, @Nonnull LDblToIntFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsInt(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsInt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a, @Nonnull LDblToIntFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsInt(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsInt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a, @Nonnull LDblToIntFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LDblConsumer toConsumer() {
		return this::applyAsInt;
	}

	/** Calls domain consumer before main function. */
	public default LDblToIntFunction beforeDo(@Nonnull LDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a) -> {
			before.accept(a);
			return applyAsInt(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LDblToIntFunction afterDo(@Nonnull LIntConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a) -> {
			final int retval = applyAsInt(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier capture(double a) {
		return () -> this.applyAsInt(a);
	}

	/** Creates function that always returns the same value. */
	static LDblToIntFunction constant(int r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblToIntFunction dblToIntFunc(final @Nonnull LDblToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LDblToIntFunction recursive(final @Nonnull LFunction<LDblToIntFunction, LDblToIntFunction> selfLambda) {
		final LDblToIntFunctionSingle single = new LDblToIntFunctionSingle();
		LDblToIntFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LDblToIntFunctionSingle implements LDblToIntFunction {
		private LDblToIntFunction target = null;

		@Override
		public int applyAsIntX(double a) throws Throwable {
			return target.applyAsIntX(a);
		}

	}

	@Nonnull
	static LDblToIntFunction dblToIntFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblToIntFunction dblToIntFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static int call(double a, final @Nonnull LDblToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDblToIntFunction wrap(final DoubleToIntFunction other) {
		return other::applyAsInt;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LDblToIntFunction safe() {
		return LDblToIntFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblToIntFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDblToIntFunction safe(final @Nullable LDblToIntFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblToIntFunction> safeSupplier(final @Nullable LSupplier<LDblToIntFunction> supplier) {
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
	default LDblToIntFunction compose(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsInt(before.applyAsDbl(v));
	}

	public static LDblToIntFunction composed(@Nonnull final LDblUnaryOperator before, LDblToIntFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToIntFunction<V> dblToIntFuncCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsInt(before.applyAsDbl(v));
	}

	public static <V> LToIntFunction<V> composed(@Nonnull final LToDblFunction<? super V> before, LDblToIntFunction after) {
		return after.dblToIntFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction thenToSrt(@Nonnull LIntToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction thenToFlt(@Nonnull LIntToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator thenToDbl(@Nonnull LIntToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsInt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblToIntFunction) Function */
	public static int doNothing(double a) {
		return Function4U.defaultInteger;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LIntConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.applyAsDbl(source, i);
			consumer.accept(this.applyAsInt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LIntConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			double a = nextFunc0.applyAsDbl(iterator0);
			consumer.accept(this.applyAsInt(a));
		}
	}

}
