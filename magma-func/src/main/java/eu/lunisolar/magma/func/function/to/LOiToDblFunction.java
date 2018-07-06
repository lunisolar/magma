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
 * Non-throwing functional interface (lambda) LOiToDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: double
 *
 * Special case of function that corresponds to expressions like (list, index) -> List::get
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LOiToDblFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OiFunction<T, aDouble> { // NOSONAR

	String DESCRIPTION = "LOiToDblFunction: double doApplyAsDbl(T a1,int a2)";

	// double doApplyAsDbl(T a1,int a2) ;
	default double doApplyAsDbl(T a1, int a2) {
		// return nestingDoApplyAsDbl(a1,a2);
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsDbl(T a1,int a2)
	 */
	double doApplyAsDblX(T a1, int a2) throws Throwable;

	default double tupleApplyAsDbl(LObjIntPair<T> args) {
		return doApplyAsDbl(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingDoApplyAsDbl(T a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default double tryDoApplyAsDbl(T a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default double tryDoApplyAsDbl(T a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default double tryDoApplyAsDblThen(T a1, int a2, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsDbl(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingDoApplyAsDbl(T a1, int a2) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingDoApplyAsDbl(T a1, int a2) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> double handlingDoApplyAsDbl(T a1, int a2, LOiToDblFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsDbl(a1, a2, handling);
	}

	static <T> double tryDoApplyAsDbl(T a1, int a2, LOiToDblFunction<T> func) {
		return tryDoApplyAsDbl(a1, a2, func, null);
	}

	static <T> double tryDoApplyAsDbl(T a1, int a2, LOiToDblFunction<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <T> double tryDoApplyAsDbl(T a1, int a2, LOiToDblFunction<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a1, a2, exceptionFactory);
	}

	static <T> double tryDoApplyAsDblThen(T a1, int a2, LOiToDblFunction<T> func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDblThen(a1, a2, handler);
	}

	default double failSafeDoApplyAsDbl(T a1, int a2, @Nonnull LOiToDblFunction<T> failSafe) {
		try {
			return doApplyAsDbl(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsDbl(a1, a2);
		}
	}

	static <T> double failSafeDoApplyAsDbl(T a1, int a2, LOiToDblFunction<T> func, @Nonnull LOiToDblFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsDbl(a1, a2);
		} else {
			return func.failSafeDoApplyAsDbl(a1, a2, failSafe);
		}
	}

	static <T> LOiToDblFunction<T> failSafeOiToDblFunc(LOiToDblFunction<T> func, @Nonnull LOiToDblFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsDbl(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDbl(T a1, int a2) {
		return doApplyAsDbl(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LOiToDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, LOiToDblFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.doApplyAsDbl(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.doApplyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, LOiToDblFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.doApplyAsDbl(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.doApplyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, LOiToDblFunction<T> func) {
		fromTill(0, max_a2, a1, func);
	}

	public default LIntToDblFunction lShrink(LIntFunction<T> left) {
		return a2 -> doApplyAsDbl(left.doApply(a2), a2);
	}

	public default LIntToDblFunction lShrinkc(T a1) {
		return a2 -> doApplyAsDbl(a1, a2);
	}

	public static <T> LIntToDblFunction lShrinked(LIntFunction<T> left, LOiToDblFunction<T> func) {
		return func.lShrink(left);
	}

	public static <T> LIntToDblFunction lShrinkedc(T a1, LOiToDblFunction<T> func) {
		return func.lShrinkc(a1);
	}

	public default LToDblFunction<T> rShrink(LToIntFunction<T> right) {
		return a1 -> doApplyAsDbl(a1, right.doApplyAsInt(a1));
	}

	public default LToDblFunction<T> rShrinkc(int a2) {
		return a1 -> doApplyAsDbl(a1, a2);
	}

	public static <T> LToDblFunction<T> rShrinked(LToIntFunction<T> right, LOiToDblFunction<T> func) {
		return func.rShrink(right);
	}

	public static <T> LToDblFunction<T> rShrinkedc(int a2, LOiToDblFunction<T> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T> LOiToDblFunction<T> uncurryOiToDblFunc(LFunction<T, LIntToDblFunction> func) {
		return (T a1, int a2) -> func.doApply(a1).doApplyAsDbl(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier captureOiToDblFunc(T a1, int a2) {
		return () -> this.doApplyAsDbl(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T> LOiToDblFunction<T> constant(double r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LOiToDblFunction<T> apply1stAsDbl(@Nonnull LToDblFunction<T> func) {
		return (a1, a2) -> func.doApplyAsDbl(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LOiToDblFunction<T> apply2ndAsDbl(@Nonnull LIntToDblFunction func) {
		return (a1, a2) -> func.doApplyAsDbl(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LOiToDblFunction<T> oiToDblFunc(final @Nonnull LOiToDblFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LOiToDblFunction<T> recursive(final @Nonnull LFunction<LOiToDblFunction<T>, LOiToDblFunction<T>> selfLambda) {
		final LOiToDblFunctionSingle<T> single = new LOiToDblFunctionSingle();
		LOiToDblFunction<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LOiToDblFunctionSingle<T> implements LSingle<LOiToDblFunction<T>>, LOiToDblFunction<T> {
		private LOiToDblFunction<T> target = null;

		@Override
		public double doApplyAsDblX(T a1, int a2) throws Throwable {
			return target.doApplyAsDblX(a1, a2);
		}

		@Override
		public LOiToDblFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LOiToDblFunction<T> oiToDblFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LOiToDblFunction<T> oiToDblFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjToDblFunc<T> intObjToDblFunc(final @Nonnull LIntObjToDblFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> double call(T a1, int a2, final @Nonnull LOiToDblFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsDbl(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceDouble). */
	@Nonnull
	static <T> LOiToDblFunction<T> safe() {
		return LOiToDblFunction::produceDouble;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LOiToDblFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LOiToDblFunction<T> safe(final @Nullable LOiToDblFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LOiToDblFunction<T>> safeSupplier(final @Nullable LSupplier<LOiToDblFunction<T>> supplier) {
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
	default <V1> LOiToDblFunction<V1> oiToDblFuncComposeInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsDbl(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, T> LOiToDblFunction<V1> composedInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, LOiToDblFunction<T> after) {
		return after.oiToDblFuncComposeInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToDblBiFunction<V1, V2> oiToDblFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsDbl(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2, T> LToDblBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, LOiToDblFunction<T> after) {
		return after.oiToDblFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LOiFunction<T, V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToByteFunction<T> thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsByte(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToSrtFunction<T> thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsSrt(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToIntFunction<T> thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsInt(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToLongFunction<T> thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsLong(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToFltFunction<T> thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsFlt(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToDblFunction<T> thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsDbl(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToCharFunction<T> thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsChar(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntPredicate<T> thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsDbl(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LOiToDblFunction<T> nestingOiToDblFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LOiToDblFunction<T> shovingOiToDblFunc() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LOiToDblFunction for method references. */
	@FunctionalInterface
	interface LIntObjToDblFunc<T> extends LOiToDblFunction<T> {

		double doApplyAsDblIntObj(int a2, T a1);

		@Override
		default double doApplyAsDblX(T a1, int a2) {
			return this.doApplyAsDblIntObj(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LOiToDblFunction) Function */
	public static <T> double produceDouble(T a1, int a2) {
		return Function4U.defaultDouble;
	}

	/** Does nothing (LOiToDblFunction.LIntObjToDblFunc) Function */
	public static <T> double produceDouble(int a2, T a1) {
		return Function4U.defaultDouble;
	}

	// MAP: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(this.doApplyAsDbl(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(this.doApplyAsDbl(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(this.doApplyAsDbl(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(this.doApplyAsDbl(a1, a2));
		}
	}

}
