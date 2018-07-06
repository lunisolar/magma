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

package eu.lunisolar.magma.func.operator.ternary;

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
 * Non-throwing functional interface (lambda) LTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): T a1,T a2,T a3
 *
 * Co-domain: T
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTernaryOperator<T> extends MetaOperator, MetaInterface.NonThrowing, LTriFunction<T, T, T, T> { // NOSONAR

	String DESCRIPTION = "LTernaryOperator: T doApply(T a1,T a2,T a3)";

	default T tupleApply(LTriple<T, T, T> args) {
		return doApply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default T handlingDoApply(T a1, T a2, T a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default T tryDoApply(T a1, T a2, T a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default T tryDoApply(T a1, T a2, T a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default T tryDoApplyThen(T a1, T a2, T a3, @Nonnull LFunction<Throwable, T> handler) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingDoApply(T a1, T a2, T a3) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default T shovingDoApply(T a1, T a2, T a3) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> T handlingDoApply(T a1, T a2, T a3, LTernaryOperator<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, a3, handling);
	}

	static <T> T tryDoApply(T a1, T a2, T a3, LTernaryOperator<T> func) {
		return tryDoApply(a1, a2, a3, func, null);
	}

	static <T> T tryDoApply(T a1, T a2, T a3, LTernaryOperator<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T> T tryDoApply(T a1, T a2, T a3, LTernaryOperator<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, a3, exceptionFactory);
	}

	static <T> T tryDoApplyThen(T a1, T a2, T a3, LTernaryOperator<T> func, @Nonnull LFunction<Throwable, T> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, a3, handler);
	}

	default T failSafeDoApply(T a1, T a2, T a3, @Nonnull LTernaryOperator<T> failSafe) {
		try {
			return doApply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2, a3);
		}
	}

	static <T> T failSafeDoApply(T a1, T a2, T a3, LTernaryOperator<T> func, @Nonnull LTernaryOperator<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2, a3);
		} else {
			return func.failSafeDoApply(a1, a2, a3, failSafe);
		}
	}

	static <T> LTernaryOperator<T> failSafeTernaryOp(LTernaryOperator<T> func, @Nonnull LTernaryOperator<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullDoApply(T a1, T a2, T a3) {
		return Null.requireNonNull(doApply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, T a2, T a3, LTernaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a1, T a2, T a3, LTernaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a1, T a2, T a3, LTernaryOperator<T> func) {
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LBinaryOperator<T> lShrink(LBinaryOperator<T> left) {
		return (a2, a3) -> doApply(left.doApply(a2, a3), a2, a3);
	}

	public default LBinaryOperator<T> lShrinkc(T a1) {
		return (a2, a3) -> doApply(a1, a2, a3);
	}

	public static <T> LBinaryOperator<T> lShrinked(LBinaryOperator<T> left, LTernaryOperator<T> func) {
		return func.lShrink(left);
	}

	public static <T> LBinaryOperator<T> lShrinkedc(T a1, LTernaryOperator<T> func) {
		return func.lShrinkc(a1);
	}

	public default LBinaryOperator<T> rShrink(LBinaryOperator<T> right) {
		return (a1, a2) -> doApply(a1, a2, right.doApply(a1, a2));
	}

	public default LBinaryOperator<T> rShrinkc(T a3) {
		return (a1, a2) -> doApply(a1, a2, a3);
	}

	public static <T> LBinaryOperator<T> rShrinked(LBinaryOperator<T> right, LTernaryOperator<T> func) {
		return func.rShrink(right);
	}

	public static <T> LBinaryOperator<T> rShrinkedc(T a3, LTernaryOperator<T> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T> LTernaryOperator<T> uncurryTernaryOp(LFunction<T, LFunction<T, LUnaryOperator<T>>> func) {
		return (T a1, T a2, T a3) -> func.doApply(a1).doApply(a2).doApply(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<T> captureTernaryOp(T a1, T a2, T a3) {
		return () -> this.doApply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LTernaryOperator<T> constant(T r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTernaryOperator<T> apply1st(@Nonnull LUnaryOperator<T> func) {
		return (a1, a2, a3) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTernaryOperator<T> apply2nd(@Nonnull LUnaryOperator<T> func) {
		return (a1, a2, a3) -> func.doApply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTernaryOperator<T> apply3rd(@Nonnull LUnaryOperator<T> func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTernaryOperator<T> ternaryOp(final @Nonnull LTernaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LTernaryOperator<T> recursive(final @Nonnull LFunction<LTernaryOperator<T>, LTernaryOperator<T>> selfLambda) {
		final LTernaryOperatorSingle<T> single = new LTernaryOperatorSingle();
		LTernaryOperator<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LTernaryOperatorSingle<T> implements LSingle<LTernaryOperator<T>>, LTernaryOperator<T> {
		private LTernaryOperator<T> target = null;

		@Override
		public T doApplyX(T a1, T a2, T a3) throws Throwable {
			return target.doApplyX(a1, a2, a3);
		}

		@Override
		public LTernaryOperator<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LTernaryOperator<T> ternaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LTernaryOperator<T> ternaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <T> T call(T a1, T a2, T a3, final @Nonnull LTernaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <T> LTernaryOperator<T> safe() {
		return LTernaryOperator::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTernaryOperator<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LTernaryOperator<T> safe(final @Nullable LTernaryOperator<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTernaryOperator<T>> safeSupplier(final @Nullable LSupplier<LTernaryOperator<T>> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriFunction<T, T, T, V> then(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntTriFunction<T, T, T> thenToInt(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApplyAsInt(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriPredicate<T, T, T> thenToBool(@Nonnull LPredicate<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doTest(this.doApply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTernaryOperator<T> nestingTernaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTernaryOperator<T> shovingTernaryOp() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LTernaryOperator<T> nonNullTernaryOp() {
		return this::nonNullDoApply;
	}

	/** Does nothing (LTernaryOperator) Operator */
	public static <T> T produce(T a1, T a2, T a3) {
		return (T) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=T a2, type=IA}, SourcePurpose{arg=T a3, type=IA}, SourcePurpose{arg=LConsumer<? super T>
	// consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, a<T>> ia2, C2 source2, IndexedRead<C3, a<T>> ia3, C3 source3, LConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			T a2 = oiFunc2.doApply(source2, i);
			T a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=T a2, type=IA}, SourcePurpose{arg=T a3, type=IA}, SourcePurpose{arg=LConsumer<? super T>
	// consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, a<T>> ia2, C2 source2, IndexedRead<C3, a<T>> ia3, C3 source3, LConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			T a2 = oiFunc2.doApply(source2, i);
			T a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=T a2, type=SA}, SourcePurpose{arg=T a3, type=IA}, SourcePurpose{arg=LConsumer<? super T>
	// consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, a<T>> sa2, C2 source2, IndexedRead<C3, a<T>> ia3, C3 source3, LConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T> nextFunc2 = (LFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			T a2 = nextFunc2.doApply(iterator2);
			T a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=T a2, type=SA}, SourcePurpose{arg=T a3, type=IA}, SourcePurpose{arg=LConsumer<? super T>
	// consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, a<T>> sa2, C2 source2, IndexedRead<C3, a<T>> ia3, C3 source3, LConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T> nextFunc2 = (LFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiFunction<Object, T> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			T a2 = nextFunc2.doApply(iterator2);
			T a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=T a2, type=IA}, SourcePurpose{arg=T a3, type=SA}, SourcePurpose{arg=LConsumer<? super T>
	// consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, a<T>> ia2, C2 source2, SequentialRead<C3, I3, a<T>> sa3, C3 source3, LConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			T a2 = oiFunc2.doApply(source2, i);
			T a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=T a2, type=IA}, SourcePurpose{arg=T a3, type=SA}, SourcePurpose{arg=LConsumer<? super T>
	// consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, a<T>> ia2, C2 source2, SequentialRead<C3, I3, a<T>> sa3, C3 source3, LConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			T a2 = oiFunc2.doApply(source2, i);
			T a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=T a2, type=SA}, SourcePurpose{arg=T a3, type=SA}, SourcePurpose{arg=LConsumer<? super T>
	// consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, a<T>> sa2, C2 source2, SequentialRead<C3, I3, a<T>> sa3, C3 source3, LConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			T a2 = nextFunc2.doApply(iterator2);
			T a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=T a2, type=SA}, SourcePurpose{arg=T a3, type=SA}, SourcePurpose{arg=LConsumer<? super T>
	// consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, a<T>> sa2, C2 source2, SequentialRead<C3, I3, a<T>> sa3, C3 source3, LConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T> nextFunc3 = (LFunction) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			T a2 = nextFunc2.doApply(iterator2);
			T a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
		}
	}

}
