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
 * Non-throwing functional interface (lambda) LBiIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): int a1,int a2
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiIntFunction<R> extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiIntFunction: R doApply(int a1,int a2)";

	@Nullable
	// R doApply(int a1,int a2) ;
	default R doApply(int a1, int a2) {
		// return nestingDoApply(a1,a2);
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(int a1,int a2)
	 */
	R doApplyX(int a1, int a2) throws Throwable;

	default R tupleApply(LIntPair args) {
		return doApply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(int a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(int a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(int a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(int a1, int a2, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(int a1, int a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(int a1, int a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingDoApply(int a1, int a2, LBiIntFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, handling);
	}

	static <R> R tryDoApply(int a1, int a2, LBiIntFunction<R> func) {
		return tryDoApply(a1, a2, func, null);
	}

	static <R> R tryDoApply(int a1, int a2, LBiIntFunction<R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <R> R tryDoApply(int a1, int a2, LBiIntFunction<R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory);
	}

	static <R> R tryDoApplyThen(int a1, int a2, LBiIntFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, handler);
	}

	default R failSafeDoApply(int a1, int a2, @Nonnull LBiIntFunction<R> failSafe) {
		try {
			return doApply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2);
		}
	}

	static <R> R failSafeDoApply(int a1, int a2, LBiIntFunction<R> func, @Nonnull LBiIntFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2);
		} else {
			return func.failSafeDoApply(a1, a2, failSafe);
		}
	}

	static <R> LBiIntFunction<R> failSafeBiIntFunc(LBiIntFunction<R> func, @Nonnull LBiIntFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApply(a1, a2, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(int a1, int a2) {
		return Null.requireNonNull(doApply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiIntFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, int a1, int a2, LBiIntFunction<R> func) {
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
	public static <R> void fromTill(int min_i, int max_i, int a1, int a2, LBiIntFunction<R> func) {
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
	public static <R> void times(int max_i, int a1, int a2, LBiIntFunction<R> func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LIntFunction<R> lShrink(LIntUnaryOperator left) {
		return a2 -> doApply(left.doApplyAsInt(a2), a2);
	}

	public default LIntFunction<R> lShrinkc(int a1) {
		return a2 -> doApply(a1, a2);
	}

	public static <R> LIntFunction<R> lShrinked(LIntUnaryOperator left, LBiIntFunction<R> func) {
		return func.lShrink(left);
	}

	public static <R> LIntFunction<R> lShrinkedc(int a1, LBiIntFunction<R> func) {
		return func.lShrinkc(a1);
	}

	public default LIntFunction<R> rShrink(LIntUnaryOperator right) {
		return a1 -> doApply(a1, right.doApplyAsInt(a1));
	}

	public default LIntFunction<R> rShrinkc(int a2) {
		return a1 -> doApply(a1, a2);
	}

	public static <R> LIntFunction<R> rShrinked(LIntUnaryOperator right, LBiIntFunction<R> func) {
		return func.rShrink(right);
	}

	public static <R> LIntFunction<R> rShrinkedc(int a2, LBiIntFunction<R> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <R> LBiIntFunction<R> uncurryBiIntFunc(LIntFunction<LIntFunction<R>> func) {
		return (int a1, int a2) -> func.doApply(a1).doApply(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBiIntFunc(int a1, int a2) {
		return () -> this.doApply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <R> LBiIntFunction<R> constant(R r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <R> LBiIntFunction<R> apply1st(@Nonnull LIntFunction<R> func) {
		return (a1, a2) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <R> LBiIntFunction<R> apply2nd(@Nonnull LIntFunction<R> func) {
		return (a1, a2) -> func.doApply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBiIntFunction<R> biIntFunc(final @Nonnull LBiIntFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <R> LBiIntFunction<R> recursive(final @Nonnull LFunction<LBiIntFunction<R>, LBiIntFunction<R>> selfLambda) {
		final LBiIntFunctionSingle<R> single = new LBiIntFunctionSingle();
		LBiIntFunction<R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiIntFunctionSingle<R> implements LSingle<LBiIntFunction<R>>, LBiIntFunction<R> {
		private LBiIntFunction<R> target = null;

		@Override
		public R doApplyX(int a1, int a2) throws Throwable {
			return target.doApplyX(a1, a2);
		}

		@Override
		public LBiIntFunction<R> value() {
			return target;
		}
	}

	@Nonnull
	static <R> LBiIntFunction<R> biIntFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <R> LBiIntFunction<R> biIntFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LInt1Int0Func<R> int1Int0Func(final @Nonnull LInt1Int0Func<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <R> R call(int a1, int a2, final @Nonnull LBiIntFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <R> LBiIntFunction<R> safe() {
		return LBiIntFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBiIntFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LBiIntFunction<R> safe(final @Nullable LBiIntFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBiIntFunction<R>> safeSupplier(final @Nullable LSupplier<LBiIntFunction<R>> supplier) {
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
	default LBiIntFunction<R> biIntFuncComposeInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	public static <R> LBiIntFunction<R> composedInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LBiIntFunction<R> after) {
		return after.biIntFuncComposeInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiFunction<V1, V2, R> biIntFuncCompose(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2, R> LBiFunction<V1, V2, R> composed(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2, LBiIntFunction<R> after) {
		return after.biIntFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiIntFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiIntConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doAccept(this.doApply(a1, a2));
	}

	@Nonnull
	default LBiIntFunction<R> before(@Nonnull LBiIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (a1, a2) -> {
			before.doAccept(a1, a2);
			return this.doApply(a1, a2);
		};
	}

	@Nonnull
	default LBiIntFunction<R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			R result = this.doApply(a1, a2);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntBinaryOperator thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsInt(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiIntPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiIntFunction<R> nestingBiIntFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiIntFunction<R> shovingBiIntFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiIntFunction<R> nonNullBiIntFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiIntFunction for method references. */
	@FunctionalInterface
	interface LInt1Int0Func<R> extends LBiIntFunction<R> {
		@Nullable
		R doApplyInt1Int0(int a2, int a1);

		@Override
		default R doApplyX(int a1, int a2) {
			return this.doApplyInt1Int0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiIntFunction) Function */
	public static <R> R produce(int a1, int a2) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aInt> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			int a1 = oiFunc1.doApplyAsInt(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(this.doApply(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(this.doApply(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aInt> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			int a1 = oiFunc1.doApplyAsInt(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(this.doApply(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(this.doApply(a1, a2));
		}
	}

}
