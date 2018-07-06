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

package eu.lunisolar.magma.func.operator.binary;

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
 * Non-throwing functional interface (lambda) LLogicalBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): boolean a1,boolean a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLogicalBinaryOperator extends MetaInterface.NonThrowing, MetaLogicalOperator { // NOSONAR

	String DESCRIPTION = "LLogicalBinaryOperator: boolean doApply(boolean a1,boolean a2)";

	// boolean doApply(boolean a1,boolean a2) ;
	default boolean doApply(boolean a1, boolean a2) {
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
	boolean doApplyX(boolean a1, boolean a2) throws Throwable;

	default boolean tupleApply(LBoolPair args) {
		return doApply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoApply(boolean a1, boolean a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoApply(boolean a1, boolean a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoApply(boolean a1, boolean a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoApplyThen(boolean a1, boolean a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoApply(boolean a1, boolean a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoApply(boolean a1, boolean a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingDoApply(boolean a1, boolean a2, LLogicalBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, handling);
	}

	static boolean tryDoApply(boolean a1, boolean a2, LLogicalBinaryOperator func) {
		return tryDoApply(a1, a2, func, null);
	}

	static boolean tryDoApply(boolean a1, boolean a2, LLogicalBinaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static boolean tryDoApply(boolean a1, boolean a2, LLogicalBinaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory);
	}

	static boolean tryDoApplyThen(boolean a1, boolean a2, LLogicalBinaryOperator func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, handler);
	}

	default boolean failSafeDoApply(boolean a1, boolean a2, @Nonnull LLogicalBinaryOperator failSafe) {
		try {
			return doApply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2);
		}
	}

	static boolean failSafeDoApply(boolean a1, boolean a2, LLogicalBinaryOperator func, @Nonnull LLogicalBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2);
		} else {
			return func.failSafeDoApply(a1, a2, failSafe);
		}
	}

	static LLogicalBinaryOperator failSafeLogicalBinaryOp(LLogicalBinaryOperator func, @Nonnull LLogicalBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApply(a1, a2, func, failSafe);
	}

	default boolean doIf(boolean a1, boolean a2, LAction action) {
		if (doApply(a1, a2)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(boolean a1, boolean a2, LBiBoolConsumer consumer) {
		if (doApply(a1, a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(boolean a1, boolean a2, LLogicalBinaryOperator pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(boolean a1, boolean a2, LLogicalBinaryOperator pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApply(boolean a1, boolean a2) {
		return doApply(a1, a2);
	}

	/** For convenience, boolean operator is also special case of predicate. */
	default boolean doTest(boolean a1, boolean a2) {
		return doApply(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLogicalBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a1, boolean a2, LLogicalBinaryOperator func) {
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
	public static void fromTill(int min_i, int max_i, boolean a1, boolean a2, LLogicalBinaryOperator func) {
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
	public static void times(int max_i, boolean a1, boolean a2, LLogicalBinaryOperator func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LLogicalOperator lShrink(LLogicalOperator left) {
		return a2 -> doApply(left.doApply(a2), a2);
	}

	public default LLogicalOperator lShrinkc(boolean a1) {
		return a2 -> doApply(a1, a2);
	}

	public static LLogicalOperator lShrinked(LLogicalOperator left, LLogicalBinaryOperator func) {
		return func.lShrink(left);
	}

	public static LLogicalOperator lShrinkedc(boolean a1, LLogicalBinaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LLogicalOperator rShrink(LLogicalOperator right) {
		return a1 -> doApply(a1, right.doApply(a1));
	}

	public default LLogicalOperator rShrinkc(boolean a2) {
		return a1 -> doApply(a1, a2);
	}

	public static LLogicalOperator rShrinked(LLogicalOperator right, LLogicalBinaryOperator func) {
		return func.rShrink(right);
	}

	public static LLogicalOperator rShrinkedc(boolean a2, LLogicalBinaryOperator func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LLogicalBinaryOperator uncurryLogicalBinaryOp(LBoolFunction<LLogicalOperator> func) {
		return (boolean a1, boolean a2) -> func.doApply(a1).doApply(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureLogicalBinaryOp(boolean a1, boolean a2) {
		return () -> this.doApply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LLogicalBinaryOperator constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LLogicalBinaryOperator apply1st(@Nonnull LLogicalOperator func) {
		return (a1, a2) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LLogicalBinaryOperator apply2nd(@Nonnull LLogicalOperator func) {
		return (a1, a2) -> func.doApply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLogicalBinaryOperator logicalBinaryOp(final @Nonnull LLogicalBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LLogicalBinaryOperator recursive(final @Nonnull LFunction<LLogicalBinaryOperator, LLogicalBinaryOperator> selfLambda) {
		final LLogicalBinaryOperatorSingle single = new LLogicalBinaryOperatorSingle();
		LLogicalBinaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LLogicalBinaryOperatorSingle implements LSingle<LLogicalBinaryOperator>, LLogicalBinaryOperator {
		private LLogicalBinaryOperator target = null;

		@Override
		public boolean doApplyX(boolean a1, boolean a2) throws Throwable {
			return target.doApplyX(a1, a2);
		}

		@Override
		public LLogicalBinaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LLogicalBinaryOperator logicalBinaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LLogicalBinaryOperator logicalBinaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static boolean call(boolean a1, boolean a2, final @Nonnull LLogicalBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceBoolean). */
	@Nonnull
	static LLogicalBinaryOperator safe() {
		return LLogicalBinaryOperator::produceBoolean;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLogicalBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLogicalBinaryOperator safe(final @Nullable LLogicalBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLogicalBinaryOperator> safeSupplier(final @Nullable LSupplier<LLogicalBinaryOperator> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LLogicalBinaryOperator negate() {
		return (a1, a2) -> !doApply(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLogicalBinaryOperator and(@Nonnull LLogicalBinaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doApply(a1, a2) && other.doApply(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalBinaryOperator or(@Nonnull LLogicalBinaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doApply(a1, a2) || other.doApply(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalBinaryOperator xor(@Nonnull LLogicalBinaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doApply(a1, a2) ^ other.doApply(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LLogicalBinaryOperator isEqual(boolean v1, boolean v2) {
		return (a1, a2) -> (a1 == v1) && (a2 == v2);
	}

	// </editor-fold>

	/**
	 * Returns function that applies logical AND operator.
	 */
	@Nonnull
	static LLogicalBinaryOperator and() {
		return Boolean::logicalAnd;
	}

	/**
	 * Returns function that applies logical OR operator.
	 */
	@Nonnull
	static LLogicalBinaryOperator or() {
		return Boolean::logicalOr;
	}

	/**
	 * Returns function that applies logical XOR operator.
	 */
	@Nonnull
	static LLogicalBinaryOperator xor() {
		return Boolean::logicalXor;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLogicalBinaryOperator logicalBinaryOpComposeBool(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doApply(v1), before2.doApply(v2));
	}

	public static LLogicalBinaryOperator composedBool(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2, LLogicalBinaryOperator after) {
		return after.logicalBinaryOpComposeBool(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> logicalBinaryOpCompose(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doTest(v1), before2.doTest(v2));
	}

	public static <V1, V2> LBiPredicate<V1, V2> composed(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2, LLogicalBinaryOperator after) {
		return after.logicalBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiBoolFunction<V> then(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalBinaryOperator thenToBool(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLogicalBinaryOperator nestingLogicalBinaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLogicalBinaryOperator shovingLogicalBinaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LLogicalBinaryOperator) Operator */
	public static boolean produceBoolean(boolean a1, boolean a2) {
		return Function4U.defaultBoolean;
	}

	// MAP: FOR, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aBool> ia1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, LBoolConsumer consumer) {
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

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, LBoolConsumer consumer) {
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

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aBool> ia1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LBoolConsumer consumer) {
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

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, LBoolConsumer consumer) {
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
