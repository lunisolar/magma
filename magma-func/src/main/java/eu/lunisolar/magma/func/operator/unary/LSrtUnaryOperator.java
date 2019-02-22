/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.operator.unary;

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
 * Non-throwing functional interface (lambda) LSrtUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): short a
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtUnaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aShort>, Domain1<aShort> { // NOSONAR

	String DESCRIPTION = "LSrtUnaryOperator: short applyAsSrt(short a)";

	// short applyAsSrt(short a) ;
	default short applyAsSrt(short a) {
		// return nestingApplyAsSrt(a);
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsSrt(short a)
	 */
	short applyAsSrtX(short a) throws Throwable;

	default short tupleApplyAsSrt(LSrtSingle args) {
		return applyAsSrt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingApplyAsSrt(short a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtUnaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsSrt(a, handling);
	}

	default short applyAsSrt(short a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LSrtUnaryOperator trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsSrt(a, exF, newMessage, messageParams);
	}

	default short applyAsSrt(short a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LSrtUnaryOperator trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsSrt(a, exF);
	}

	default short applyAsSrtThen(short a, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LSrtUnaryOperator tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return a -> applyAsSrtThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingApplyAsSrt(short a) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingApplyAsSrt(short a) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short handlingApplyAsSrt(short a, LSrtUnaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsSrt(a, handling);
	}

	static short tryApplyAsSrt(short a, LSrtUnaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsSrt(a);
	}

	static short tryApplyAsSrt(short a, LSrtUnaryOperator func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, exF, newMessage, messageParams);
	}

	static short tryApplyAsSrt(short a, LSrtUnaryOperator func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, exF);
	}

	static short tryApplyAsSrtThen(short a, LSrtUnaryOperator func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrtThen(a, handler);
	}

	default short failSafeApplyAsSrt(short a, @Nonnull LSrtUnaryOperator failSafe) {
		try {
			return applyAsSrt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsSrt(a);
		}
	}

	static short failSafeApplyAsSrt(short a, LSrtUnaryOperator func, @Nonnull LSrtUnaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsSrt(a);
		} else {
			return func.failSafeApplyAsSrt(a, failSafe);
		}
	}

	static LSrtUnaryOperator failSafe(LSrtUnaryOperator func, @Nonnull LSrtUnaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsSrt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullApplyAsSrt(short a) {
		return applyAsSrt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtUnaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a, LSrtUnaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsSrt(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, short a, LSrtUnaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsSrt(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, short a, LSrtUnaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LSrtSupplier capture(short a) {
		return () -> this.applyAsSrt(a);
	}

	/** Creates function that always returns the same value. */
	static LSrtUnaryOperator constant(short r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtUnaryOperator srtUnaryOp(final @Nonnull LSrtUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtUnaryOperator recursive(final @Nonnull LFunction<LSrtUnaryOperator, LSrtUnaryOperator> selfLambda) {
		final LSrtUnaryOperatorSingle single = new LSrtUnaryOperatorSingle();
		LSrtUnaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LSrtUnaryOperatorSingle implements LSingle<LSrtUnaryOperator>, LSrtUnaryOperator {
		private LSrtUnaryOperator target = null;

		@Override
		public short applyAsSrtX(short a) throws Throwable {
			return target.applyAsSrtX(a);
		}

		@Override
		public LSrtUnaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LSrtUnaryOperator srtUnaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtUnaryOperator srtUnaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static short call(short a, final @Nonnull LSrtUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsSrt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceShort). */
	@Nonnull
	static LSrtUnaryOperator safe() {
		return LSrtUnaryOperator::produceShort;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtUnaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtUnaryOperator safe(final @Nullable LSrtUnaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtUnaryOperator> safeSupplier(final @Nullable LSupplier<LSrtUnaryOperator> supplier) {
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
	default LSrtUnaryOperator compose(@Nonnull final LSrtUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsSrt(before.applyAsSrt(v));
	}

	public static LSrtUnaryOperator composed(@Nonnull final LSrtUnaryOperator before, LSrtUnaryOperator after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToSrtFunction<V> srtUnaryOpCompose(@Nonnull final LToSrtFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsSrt(before.applyAsSrt(v));
	}

	public static <V> LToSrtFunction<V> composed(@Nonnull final LToSrtFunction<? super V> before, LSrtUnaryOperator after) {
		return after.srtUnaryOpCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSrtFunction<V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToByteFunction thenToByte(@Nonnull LSrtToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtUnaryOperator thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToIntFunction thenToInt(@Nonnull LSrtToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToLongFunction thenToLong(@Nonnull LSrtToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToFltFunction thenToFlt(@Nonnull LSrtToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToDblFunction thenToDbl(@Nonnull LSrtToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToCharFunction thenToChar(@Nonnull LSrtToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtPredicate thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsSrt(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LSrtUnaryOperator identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtUnaryOperator) Operator */
	public static short produceShort(short a) {
		return Function4U.defaultShort;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aShort> ia, C0 source, LSrtConsumer consumer) {
		int size = ia.size(source);
		LOiToSrtFunction<Object> oiFunc0 = (LOiToSrtFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			short a = oiFunc0.applyAsSrt(source, i);
			consumer.accept(this.applyAsSrt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aShort> sa, C0 source, LSrtConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToSrtFunction<Object> nextFunc0 = (LToSrtFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			short a = nextFunc0.applyAsSrt(iterator0);
			consumer.accept(this.applyAsSrt(a));
		}
	}

}
