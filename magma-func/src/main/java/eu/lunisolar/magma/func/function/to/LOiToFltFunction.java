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
 * Non-throwing functional interface (lambda) LOiToFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: float
 *
 * Special case of function that corresponds to expressions like (list, index) -> List::get
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LOiToFltFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OiFunction<T, aFloat> { // NOSONAR

	String DESCRIPTION = "LOiToFltFunction: float doApplyAsFlt(T a1,int a2)";

	// float doApplyAsFlt(T a1,int a2) ;
	default float doApplyAsFlt(T a1, int a2) {
		// return nestingDoApplyAsFlt(a1,a2);
		try {
			return this.doApplyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsFlt(T a1,int a2)
	 */
	float doApplyAsFltX(T a1, int a2) throws Throwable;

	default float tupleApplyAsFlt(LObjIntPair<T> args) {
		return doApplyAsFlt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingDoApplyAsFlt(T a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default float tryDoApplyAsFlt(T a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default float tryDoApplyAsFlt(T a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default float tryDoApplyAsFltThen(T a1, int a2, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.doApplyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsFlt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoApplyAsFlt(T a1, int a2) {
		try {
			return this.doApplyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingDoApplyAsFlt(T a1, int a2) {
		try {
			return this.doApplyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> float handlingDoApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsFlt(a1, a2, handling);
	}

	static <T> float tryDoApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func) {
		return tryDoApplyAsFlt(a1, a2, func, null);
	}

	static <T> float tryDoApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFlt(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <T> float tryDoApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFlt(a1, a2, exceptionFactory);
	}

	static <T> float tryDoApplyAsFltThen(T a1, int a2, LOiToFltFunction<T> func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFltThen(a1, a2, handler);
	}

	default float failSafeDoApplyAsFlt(T a1, int a2, @Nonnull LOiToFltFunction<T> failSafe) {
		try {
			return doApplyAsFlt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsFlt(a1, a2);
		}
	}

	static <T> float failSafeDoApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, @Nonnull LOiToFltFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsFlt(a1, a2);
		} else {
			return func.failSafeDoApplyAsFlt(a1, a2, failSafe);
		}
	}

	static <T> LOiToFltFunction<T> failSafeOiToFltFunc(LOiToFltFunction<T> func, @Nonnull LOiToFltFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsFlt(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFlt(T a1, int a2) {
		return doApplyAsFlt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LOiToFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, LOiToFltFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.doApplyAsFlt(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.doApplyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, LOiToFltFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.doApplyAsFlt(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.doApplyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, LOiToFltFunction<T> func) {
		fromTill(0, max_a2, a1, func);
	}

	public default LIntToFltFunction lShrink(LIntFunction<T> left) {
		return a2 -> doApplyAsFlt(left.doApply(a2), a2);
	}

	public default LIntToFltFunction lShrinkc(T a1) {
		return a2 -> doApplyAsFlt(a1, a2);
	}

	public static <T> LIntToFltFunction lShrinked(LIntFunction<T> left, LOiToFltFunction<T> func) {
		return func.lShrink(left);
	}

	public static <T> LIntToFltFunction lShrinkedc(T a1, LOiToFltFunction<T> func) {
		return func.lShrinkc(a1);
	}

	public default LToFltFunction<T> rShrink(LToIntFunction<T> right) {
		return a1 -> doApplyAsFlt(a1, right.doApplyAsInt(a1));
	}

	public default LToFltFunction<T> rShrinkc(int a2) {
		return a1 -> doApplyAsFlt(a1, a2);
	}

	public static <T> LToFltFunction<T> rShrinked(LToIntFunction<T> right, LOiToFltFunction<T> func) {
		return func.rShrink(right);
	}

	public static <T> LToFltFunction<T> rShrinkedc(int a2, LOiToFltFunction<T> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T> LOiToFltFunction<T> uncurryOiToFltFunc(LFunction<T, LIntToFltFunction> func) {
		return (T a1, int a2) -> func.doApply(a1).doApplyAsFlt(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier captureOiToFltFunc(T a1, int a2) {
		return () -> this.doApplyAsFlt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T> LOiToFltFunction<T> constant(float r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LOiToFltFunction<T> apply1stAsFlt(@Nonnull LToFltFunction<T> func) {
		return (a1, a2) -> func.doApplyAsFlt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LOiToFltFunction<T> apply2ndAsFlt(@Nonnull LIntToFltFunction func) {
		return (a1, a2) -> func.doApplyAsFlt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LOiToFltFunction<T> oiToFltFunc(final @Nonnull LOiToFltFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LOiToFltFunction<T> recursive(final @Nonnull LFunction<LOiToFltFunction<T>, LOiToFltFunction<T>> selfLambda) {
		final LOiToFltFunctionSingle<T> single = new LOiToFltFunctionSingle();
		LOiToFltFunction<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LOiToFltFunctionSingle<T> implements LSingle<LOiToFltFunction<T>>, LOiToFltFunction<T> {
		private LOiToFltFunction<T> target = null;

		@Override
		public float doApplyAsFltX(T a1, int a2) throws Throwable {
			return target.doApplyAsFltX(a1, a2);
		}

		@Override
		public LOiToFltFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LOiToFltFunction<T> oiToFltFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LOiToFltFunction<T> oiToFltFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjToFltFunc<T> intObjToFltFunc(final @Nonnull LIntObjToFltFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> float call(T a1, int a2, final @Nonnull LOiToFltFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsFlt(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceFloat). */
	@Nonnull
	static <T> LOiToFltFunction<T> safe() {
		return LOiToFltFunction::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LOiToFltFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LOiToFltFunction<T> safe(final @Nullable LOiToFltFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LOiToFltFunction<T>> safeSupplier(final @Nullable LSupplier<LOiToFltFunction<T>> supplier) {
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
	default <V1> LOiToFltFunction<V1> oiToFltFuncComposeInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsFlt(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, T> LOiToFltFunction<V1> composedInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, LOiToFltFunction<T> after) {
		return after.oiToFltFuncComposeInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToFltBiFunction<V1, V2> oiToFltFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsFlt(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2, T> LToFltBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, LOiToFltFunction<T> after) {
		return after.oiToFltFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LOiFunction<T, V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToByteFunction<T> thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsByte(this.doApplyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToSrtFunction<T> thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsSrt(this.doApplyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToIntFunction<T> thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsInt(this.doApplyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToLongFunction<T> thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsLong(this.doApplyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToFltFunction<T> thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsFlt(this.doApplyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToDblFunction<T> thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsDbl(this.doApplyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToCharFunction<T> thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsChar(this.doApplyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntPredicate<T> thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsFlt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LOiToFltFunction<T> nestingOiToFltFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LOiToFltFunction<T> shovingOiToFltFunc() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LOiToFltFunction for method references. */
	@FunctionalInterface
	interface LIntObjToFltFunc<T> extends LOiToFltFunction<T> {

		float doApplyAsFltIntObj(int a2, T a1);

		@Override
		default float doApplyAsFltX(T a1, int a2) {
			return this.doApplyAsFltIntObj(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LOiToFltFunction) Function */
	public static <T> float produceFloat(T a1, int a2) {
		return Function4U.defaultFloat;
	}

	/** Does nothing (LOiToFltFunction.LIntObjToFltFunc) Function */
	public static <T> float produceFloat(int a2, T a1) {
		return Function4U.defaultFloat;
	}

	// MAP: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(this.doApplyAsFlt(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(this.doApplyAsFlt(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(this.doApplyAsFlt(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(this.doApplyAsFlt(a1, a2));
		}
	}

}
