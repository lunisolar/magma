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

package eu.lunisolar.magma.func.function.from;

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
 * Non-throwing functional interface (lambda) LBiSrtFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): short a1,short a2
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiSrtFunction<R> extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiSrtFunction: R doApply(short a1,short a2)";

	@Nullable
	// R doApply(short a1,short a2) ;
	default R doApply(short a1, short a2) {
		// return nestingDoApply(a1,a2);
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(short a1,short a2)
	 */
	R doApplyX(short a1, short a2) throws Throwable;

	default R tupleApply(LSrtPair args) {
		return doApply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(short a1, short a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(short a1, short a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(short a1, short a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(short a1, short a2, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(short a1, short a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(short a1, short a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingDoApply(short a1, short a2, LBiSrtFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, handling);
	}

	static <R> R tryDoApply(short a1, short a2, LBiSrtFunction<R> func) {
		return tryDoApply(a1, a2, func, null);
	}

	static <R> R tryDoApply(short a1, short a2, LBiSrtFunction<R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <R> R tryDoApply(short a1, short a2, LBiSrtFunction<R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory);
	}

	static <R> R tryDoApplyThen(short a1, short a2, LBiSrtFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, handler);
	}

	default R failSafeDoApply(short a1, short a2, @Nonnull LBiSrtFunction<R> failSafe) {
		try {
			return doApply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2);
		}
	}

	static <R> R failSafeDoApply(short a1, short a2, LBiSrtFunction<R> func, @Nonnull LBiSrtFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2);
		} else {
			return func.failSafeDoApply(a1, a2, failSafe);
		}
	}

	static <R> LBiSrtFunction<R> failSafeBiSrtFunc(LBiSrtFunction<R> func, @Nonnull LBiSrtFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApply(a1, a2, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(short a1, short a2) {
		return Null.requireNonNull(doApply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiSrtFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, short a1, short a2, LBiSrtFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApply(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTill(int min_i, int max_i, short a1, short a2, LBiSrtFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApply(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void times(int max_i, short a1, short a2, LBiSrtFunction<R> func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LSrtFunction<R> lShrink(LSrtUnaryOperator left) {
		return a2 -> doApply(left.doApplyAsSrt(a2), a2);
	}

	public default LSrtFunction<R> lShrinkc(short a1) {
		return a2 -> doApply(a1, a2);
	}

	public static <R> LSrtFunction<R> lShrinked(LSrtUnaryOperator left, LBiSrtFunction<R> func) {
		return func.lShrink(left);
	}

	public static <R> LSrtFunction<R> lShrinkedc(short a1, LBiSrtFunction<R> func) {
		return func.lShrinkc(a1);
	}

	public default LSrtFunction<R> rShrink(LSrtUnaryOperator right) {
		return a1 -> doApply(a1, right.doApplyAsSrt(a1));
	}

	public default LSrtFunction<R> rShrinkc(short a2) {
		return a1 -> doApply(a1, a2);
	}

	public static <R> LSrtFunction<R> rShrinked(LSrtUnaryOperator right, LBiSrtFunction<R> func) {
		return func.rShrink(right);
	}

	public static <R> LSrtFunction<R> rShrinkedc(short a2, LBiSrtFunction<R> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <R> LBiSrtFunction<R> uncurryBiSrtFunc(LSrtFunction<LSrtFunction<R>> func) {
		return (short a1, short a2) -> func.doApply(a1).doApply(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBiSrtFunc(short a1, short a2) {
		return () -> this.doApply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <R> LBiSrtFunction<R> constant(R r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <R> LBiSrtFunction<R> apply1st(@Nonnull LSrtFunction<R> func) {
		return (a1, a2) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <R> LBiSrtFunction<R> apply2nd(@Nonnull LSrtFunction<R> func) {
		return (a1, a2) -> func.doApply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBiSrtFunction<R> biSrtFunc(final @Nonnull LBiSrtFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <R> LBiSrtFunction<R> recursive(final @Nonnull LFunction<LBiSrtFunction<R>, LBiSrtFunction<R>> selfLambda) {
		final LBiSrtFunctionSingle<R> single = new LBiSrtFunctionSingle();
		LBiSrtFunction<R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiSrtFunctionSingle<R> implements LSingle<LBiSrtFunction<R>>, LBiSrtFunction<R> {
		private LBiSrtFunction<R> target = null;

		@Override
		public R doApplyX(short a1, short a2) throws Throwable {
			return target.doApplyX(a1, a2);
		}

		@Override
		public LBiSrtFunction<R> value() {
			return target;
		}
	}

	@Nonnull
	static <R> LBiSrtFunction<R> biSrtFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <R> LBiSrtFunction<R> biSrtFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LSrt1Srt0Func<R> srt1Srt0Func(final @Nonnull LSrt1Srt0Func<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <R> R call(short a1, short a2, final @Nonnull LBiSrtFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <R> LBiSrtFunction<R> safe() {
		return LBiSrtFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBiSrtFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LBiSrtFunction<R> safe(final @Nullable LBiSrtFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBiSrtFunction<R>> safeSupplier(final @Nullable LSupplier<LBiSrtFunction<R>> supplier) {
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
	default LBiSrtFunction<R> biSrtFuncComposeSrt(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doApplyAsSrt(v1), before2.doApplyAsSrt(v2));
	}

	public static <R> LBiSrtFunction<R> composedSrt(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, LBiSrtFunction<R> after) {
		return after.biSrtFuncComposeSrt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiFunction<V1, V2, R> biSrtFuncCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doApplyAsSrt(v1), before2.doApplyAsSrt(v2));
	}

	public static <V1, V2, R> LBiFunction<V1, V2, R> composed(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2, LBiSrtFunction<R> after) {
		return after.biSrtFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiSrtFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiSrtConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doAccept(this.doApply(a1, a2));
	}

	@Nonnull
	default LBiSrtFunction<R> before(@Nonnull LBiSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (a1, a2) -> {
			before.doAccept(a1, a2);
			return this.doApply(a1, a2);
		};
	}

	@Nonnull
	default LBiSrtFunction<R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			R result = this.doApply(a1, a2);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtBinaryOperator thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsSrt(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiSrtPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiSrtFunction<R> nestingBiSrtFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiSrtFunction<R> shovingBiSrtFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiSrtFunction<R> nonNullBiSrtFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiSrtFunction for method references. */
	@FunctionalInterface
	interface LSrt1Srt0Func<R> extends LBiSrtFunction<R> {
		@Nullable
		R doApplySrt1Srt0(short a2, short a1);

		@Override
		default R doApplyX(short a1, short a2) {
			return this.doApplySrt1Srt0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiSrtFunction) Function */
	public static <R> R produce(short a1, short a2) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=short a1, type=IA}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aShort> ia1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			short a1 = oiFunc1.doApplyAsSrt(source1, i);
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			consumer.doAccept(this.doApply(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=short a1, type=SA}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			short a1 = nextFunc1.doApplyAsSrt(iterator1);
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			consumer.doAccept(this.doApply(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=short a1, type=IA}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aShort> ia1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			short a1 = oiFunc1.doApplyAsSrt(source1, i);
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			consumer.doAccept(this.doApply(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=short a1, type=SA}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			short a1 = nextFunc1.doApplyAsSrt(iterator1);
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			consumer.doAccept(this.doApply(a1, a2));
		}
	}

}
