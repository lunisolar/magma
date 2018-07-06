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
 * Non-throwing functional interface (lambda) LBiBoolFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): boolean a1,boolean a2
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiBoolFunction<R> extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiBoolFunction: R doApply(boolean a1,boolean a2)";

	@Nullable
	// R doApply(boolean a1,boolean a2) ;
	default R doApply(boolean a1, boolean a2) {
		// return nestingDoApply(a1,a2);
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(boolean a1,boolean a2)
	 */
	R doApplyX(boolean a1, boolean a2) throws Throwable;

	default R tupleApply(LBoolPair args) {
		return doApply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(boolean a1, boolean a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(boolean a1, boolean a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(boolean a1, boolean a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(boolean a1, boolean a2, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(boolean a1, boolean a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(boolean a1, boolean a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingDoApply(boolean a1, boolean a2, LBiBoolFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, handling);
	}

	static <R> R tryDoApply(boolean a1, boolean a2, LBiBoolFunction<R> func) {
		return tryDoApply(a1, a2, func, null);
	}

	static <R> R tryDoApply(boolean a1, boolean a2, LBiBoolFunction<R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <R> R tryDoApply(boolean a1, boolean a2, LBiBoolFunction<R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory);
	}

	static <R> R tryDoApplyThen(boolean a1, boolean a2, LBiBoolFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, handler);
	}

	default R failSafeDoApply(boolean a1, boolean a2, @Nonnull LBiBoolFunction<R> failSafe) {
		try {
			return doApply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2);
		}
	}

	static <R> R failSafeDoApply(boolean a1, boolean a2, LBiBoolFunction<R> func, @Nonnull LBiBoolFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2);
		} else {
			return func.failSafeDoApply(a1, a2, failSafe);
		}
	}

	static <R> LBiBoolFunction<R> failSafeBiBoolFunc(LBiBoolFunction<R> func, @Nonnull LBiBoolFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApply(a1, a2, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(boolean a1, boolean a2) {
		return Null.requireNonNull(doApply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiBoolFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, boolean a1, boolean a2, LBiBoolFunction<R> func) {
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
	public static <R> void fromTill(int min_i, int max_i, boolean a1, boolean a2, LBiBoolFunction<R> func) {
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
	public static <R> void times(int max_i, boolean a1, boolean a2, LBiBoolFunction<R> func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LBoolFunction<R> lShrink(LLogicalOperator left) {
		return a2 -> doApply(left.doApply(a2), a2);
	}

	public default LBoolFunction<R> lShrinkc(boolean a1) {
		return a2 -> doApply(a1, a2);
	}

	public static <R> LBoolFunction<R> lShrinked(LLogicalOperator left, LBiBoolFunction<R> func) {
		return func.lShrink(left);
	}

	public static <R> LBoolFunction<R> lShrinkedc(boolean a1, LBiBoolFunction<R> func) {
		return func.lShrinkc(a1);
	}

	public default LBoolFunction<R> rShrink(LLogicalOperator right) {
		return a1 -> doApply(a1, right.doApply(a1));
	}

	public default LBoolFunction<R> rShrinkc(boolean a2) {
		return a1 -> doApply(a1, a2);
	}

	public static <R> LBoolFunction<R> rShrinked(LLogicalOperator right, LBiBoolFunction<R> func) {
		return func.rShrink(right);
	}

	public static <R> LBoolFunction<R> rShrinkedc(boolean a2, LBiBoolFunction<R> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <R> LBiBoolFunction<R> uncurryBiBoolFunc(LBoolFunction<LBoolFunction<R>> func) {
		return (boolean a1, boolean a2) -> func.doApply(a1).doApply(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBiBoolFunc(boolean a1, boolean a2) {
		return () -> this.doApply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <R> LBiBoolFunction<R> constant(R r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <R> LBiBoolFunction<R> apply1st(@Nonnull LBoolFunction<R> func) {
		return (a1, a2) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <R> LBiBoolFunction<R> apply2nd(@Nonnull LBoolFunction<R> func) {
		return (a1, a2) -> func.doApply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBiBoolFunction<R> biBoolFunc(final @Nonnull LBiBoolFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <R> LBiBoolFunction<R> recursive(final @Nonnull LFunction<LBiBoolFunction<R>, LBiBoolFunction<R>> selfLambda) {
		final LBiBoolFunctionSingle<R> single = new LBiBoolFunctionSingle();
		LBiBoolFunction<R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiBoolFunctionSingle<R> implements LSingle<LBiBoolFunction<R>>, LBiBoolFunction<R> {
		private LBiBoolFunction<R> target = null;

		@Override
		public R doApplyX(boolean a1, boolean a2) throws Throwable {
			return target.doApplyX(a1, a2);
		}

		@Override
		public LBiBoolFunction<R> value() {
			return target;
		}
	}

	@Nonnull
	static <R> LBiBoolFunction<R> biBoolFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <R> LBiBoolFunction<R> biBoolFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBool1Bool0Func<R> bool1Bool0Func(final @Nonnull LBool1Bool0Func<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <R> R call(boolean a1, boolean a2, final @Nonnull LBiBoolFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <R> LBiBoolFunction<R> safe() {
		return LBiBoolFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBiBoolFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LBiBoolFunction<R> safe(final @Nullable LBiBoolFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBiBoolFunction<R>> safeSupplier(final @Nullable LSupplier<LBiBoolFunction<R>> supplier) {
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
	default LBiBoolFunction<R> biBoolFuncComposeBool(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doApply(v1), before2.doApply(v2));
	}

	public static <R> LBiBoolFunction<R> composedBool(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2, LBiBoolFunction<R> after) {
		return after.biBoolFuncComposeBool(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiFunction<V1, V2, R> biBoolFuncCompose(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doTest(v1), before2.doTest(v2));
	}

	public static <V1, V2, R> LBiFunction<V1, V2, R> composed(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2, LBiBoolFunction<R> after) {
		return after.biBoolFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiBoolFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiBoolConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doAccept(this.doApply(a1, a2));
	}

	@Nonnull
	default LBiBoolFunction<R> before(@Nonnull LBiBoolConsumer before) {
		Null.nonNullArg(before, "before");
		return (a1, a2) -> {
			before.doAccept(a1, a2);
			return this.doApply(a1, a2);
		};
	}

	@Nonnull
	default LBiBoolFunction<R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			R result = this.doApply(a1, a2);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalBinaryOperator thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiBoolFunction<R> nestingBiBoolFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiBoolFunction<R> shovingBiBoolFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiBoolFunction<R> nonNullBiBoolFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiBoolFunction for method references. */
	@FunctionalInterface
	interface LBool1Bool0Func<R> extends LBiBoolFunction<R> {
		@Nullable
		R doApplyBool1Bool0(boolean a2, boolean a1);

		@Override
		default R doApplyX(boolean a1, boolean a2) {
			return this.doApplyBool1Bool0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiBoolFunction) Function */
	public static <R> R produce(boolean a1, boolean a2) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aBool> ia1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = oiFunc2.doTest(source2, i);
			consumer.doAccept(this.doApply(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer,
	// type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = oiFunc2.doTest(source2, i);
			consumer.doAccept(this.doApply(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer,
	// type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aBool> ia1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = nextFunc2.doTest(iterator2);
			consumer.doAccept(this.doApply(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer,
	// type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = nextFunc2.doTest(iterator2);
			consumer.doAccept(this.doApply(a1, a2));
		}
	}

}
