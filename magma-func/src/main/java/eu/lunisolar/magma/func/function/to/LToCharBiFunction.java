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

package eu.lunisolar.magma.func.function.to;

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
 * Non-throwing functional interface (lambda) LToCharBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToCharBiFunction<T1, T2> extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LToCharBiFunction: char doApplyAsChar(T1 a1,T2 a2)";

	// char doApplyAsChar(T1 a1,T2 a2) ;
	default char doApplyAsChar(T1 a1, T2 a2) {
		// return nestingDoApplyAsChar(a1,a2);
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsChar(T1 a1,T2 a2)
	 */
	char doApplyAsCharX(T1 a1, T2 a2) throws Throwable;

	default char tupleApplyAsChar(LPair<T1, T2> args) {
		return doApplyAsChar(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingDoApplyAsChar(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default char tryDoApplyAsChar(T1 a1, T2 a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default char tryDoApplyAsChar(T1 a1, T2 a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default char tryDoApplyAsCharThen(T1 a1, T2 a2, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsChar(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingDoApplyAsChar(T1 a1, T2 a2) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingDoApplyAsChar(T1 a1, T2 a2) {
		try {
			return this.doApplyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> char handlingDoApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsChar(a1, a2, handling);
	}

	static <T1, T2> char tryDoApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func) {
		return tryDoApplyAsChar(a1, a2, func, null);
	}

	static <T1, T2> char tryDoApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsChar(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2> char tryDoApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsChar(a1, a2, exceptionFactory);
	}

	static <T1, T2> char tryDoApplyAsCharThen(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsCharThen(a1, a2, handler);
	}

	default char failSafeDoApplyAsChar(T1 a1, T2 a2, @Nonnull LToCharBiFunction<T1, T2> failSafe) {
		try {
			return doApplyAsChar(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsChar(a1, a2);
		}
	}

	static <T1, T2> char failSafeDoApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull LToCharBiFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsChar(a1, a2);
		} else {
			return func.failSafeDoApplyAsChar(a1, a2, failSafe);
		}
	}

	static <T1, T2> LToCharBiFunction<T1, T2> failSafeToCharBiFunc(LToCharBiFunction<T1, T2> func, @Nonnull LToCharBiFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsChar(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(T1 a1, T2 a2) {
		return doApplyAsChar(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToCharBiFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, LToCharBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsChar(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, LToCharBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsChar(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, LToCharBiFunction<T1, T2> func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LToCharFunction<T2> lShrink(LFunction<T2, T1> left) {
		return a2 -> doApplyAsChar(left.doApply(a2), a2);
	}

	public default LToCharFunction<T2> lShrinkc(T1 a1) {
		return a2 -> doApplyAsChar(a1, a2);
	}

	public static <T2, T1> LToCharFunction<T2> lShrinked(LFunction<T2, T1> left, LToCharBiFunction<T1, T2> func) {
		return func.lShrink(left);
	}

	public static <T2, T1> LToCharFunction<T2> lShrinkedc(T1 a1, LToCharBiFunction<T1, T2> func) {
		return func.lShrinkc(a1);
	}

	public default LToCharFunction<T1> rShrink(LFunction<T1, T2> right) {
		return a1 -> doApplyAsChar(a1, right.doApply(a1));
	}

	public default LToCharFunction<T1> rShrinkc(T2 a2) {
		return a1 -> doApplyAsChar(a1, a2);
	}

	public static <T1, T2> LToCharFunction<T1> rShrinked(LFunction<T1, T2> right, LToCharBiFunction<T1, T2> func) {
		return func.rShrink(right);
	}

	public static <T1, T2> LToCharFunction<T1> rShrinkedc(T2 a2, LToCharBiFunction<T1, T2> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T1, T2> LToCharBiFunction<T1, T2> uncurryToCharBiFunc(LFunction<T1, LToCharFunction<T2>> func) {
		return (T1 a1, T2 a2) -> func.doApply(a1).doApplyAsChar(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier captureToCharBiFunc(T1 a1, T2 a2) {
		return () -> this.doApplyAsChar(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LToCharBiFunction<T1, T2> constant(char r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> apply1stAsChar(@Nonnull LToCharFunction<T1> func) {
		return (a1, a2) -> func.doApplyAsChar(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> apply2ndAsChar(@Nonnull LToCharFunction<T2> func) {
		return (a1, a2) -> func.doApplyAsChar(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> toCharBiFunc(final @Nonnull LToCharBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> recursive(final @Nonnull LFunction<LToCharBiFunction<T1, T2>, LToCharBiFunction<T1, T2>> selfLambda) {
		final LToCharBiFunctionSingle<T1, T2> single = new LToCharBiFunctionSingle();
		LToCharBiFunction<T1, T2> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LToCharBiFunctionSingle<T1, T2> implements LSingle<LToCharBiFunction<T1, T2>>, LToCharBiFunction<T1, T2> {
		private LToCharBiFunction<T1, T2> target = null;

		@Override
		public char doApplyAsCharX(T1 a1, T2 a2) throws Throwable {
			return target.doApplyAsCharX(a1, a2);
		}

		@Override
		public LToCharBiFunction<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> toCharBiFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> toCharBiFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LToCharObj1Obj0Func<T2, T1> toCharObj1Obj0Func(final @Nonnull LToCharObj1Obj0Func<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> char call(T1 a1, T2 a2, final @Nonnull LToCharBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsChar(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceChar). */
	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> safe() {
		return LToCharBiFunction::produceChar;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LToCharBiFunction<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> safe(final @Nullable LToCharBiFunction<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LToCharBiFunction<T1, T2>> safeSupplier(final @Nullable LSupplier<LToCharBiFunction<T1, T2>> supplier) {
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
	default <V1, V2> LToCharBiFunction<V1, V2> toCharBiFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsChar(before1.doApply(v1), before2.doApply(v2));
	}

	public static <V1, V2, T1, T2> LToCharBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, LToCharBiFunction<T1, T2> after) {
		return after.toCharBiFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T1, T2> thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsByte(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T1, T2> thenToSrt(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsSrt(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T1, T2> thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsInt(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T1, T2> thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsLong(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T1, T2> thenToFlt(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsFlt(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T1, T2> thenToDbl(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsDbl(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T1, T2> thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsChar(this.doApplyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T1, T2> thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsChar(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToCharBiFunction<T1, T2> nestingToCharBiFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToCharBiFunction<T1, T2> shovingToCharBiFunc() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToCharBiFunction for method references. */
	@FunctionalInterface
	interface LToCharObj1Obj0Func<T2, T1> extends LToCharBiFunction<T1, T2> {

		char doApplyAsCharObj1Obj0(T2 a2, T1 a1);

		@Override
		default char doApplyAsCharX(T1 a1, T2 a2) {
			return this.doApplyAsCharObj1Obj0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LToCharBiFunction) Function */
	public static <T1, T2> char produceChar(T1 a1, T2 a2) {
		return Function4U.defaultCharacter;
	}

	// MAP: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			consumer.doAccept(this.doApplyAsChar(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			consumer.doAccept(this.doApplyAsChar(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(this.doApplyAsChar(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(this.doApplyAsChar(a1, a2));
		}
	}

}
