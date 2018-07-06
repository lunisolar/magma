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
 * Non-throwing functional interface (lambda) LLogicalTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): boolean a1,boolean a2,boolean a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLogicalTernaryOperator extends MetaInterface.NonThrowing, MetaLogicalOperator { // NOSONAR

	String DESCRIPTION = "LLogicalTernaryOperator: boolean doApply(boolean a1,boolean a2,boolean a3)";

	// boolean doApply(boolean a1,boolean a2,boolean a3) ;
	default boolean doApply(boolean a1, boolean a2, boolean a3) {
		// return nestingDoApply(a1,a2,a3);
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(boolean a1,boolean a2,boolean a3)
	 */
	boolean doApplyX(boolean a1, boolean a2, boolean a3) throws Throwable;

	default boolean tupleApply(LBoolTriple args) {
		return doApply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoApply(boolean a1, boolean a2, boolean a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoApply(boolean a1, boolean a2, boolean a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoApply(boolean a1, boolean a2, boolean a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoApplyThen(boolean a1, boolean a2, boolean a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoApply(boolean a1, boolean a2, boolean a3) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoApply(boolean a1, boolean a2, boolean a3) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingDoApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, a3, handling);
	}

	static boolean tryDoApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func) {
		return tryDoApply(a1, a2, a3, func, null);
	}

	static boolean tryDoApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static boolean tryDoApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, a3, exceptionFactory);
	}

	static boolean tryDoApplyThen(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, a3, handler);
	}

	default boolean failSafeDoApply(boolean a1, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator failSafe) {
		try {
			return doApply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2, a3);
		}
	}

	static boolean failSafeDoApply(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func, @Nonnull LLogicalTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2, a3);
		} else {
			return func.failSafeDoApply(a1, a2, a3, failSafe);
		}
	}

	static LLogicalTernaryOperator failSafeLogicalTernaryOp(LLogicalTernaryOperator func, @Nonnull LLogicalTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoApply(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(boolean a1, boolean a2, boolean a3, LAction action) {
		if (doApply(a1, a2, a3)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(boolean a1, boolean a2, boolean a3, LTriBoolConsumer consumer) {
		if (doApply(a1, a2, a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApply(boolean a1, boolean a2, boolean a3) {
		return doApply(a1, a2, a3);
	}

	/** For convenience, boolean operator is also special case of predicate. */
	default boolean doTest(boolean a1, boolean a2, boolean a3) {
		return doApply(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLogicalTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func) {
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
	public static void fromTill(int min_i, int max_i, boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func) {
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
	public static void times(int max_i, boolean a1, boolean a2, boolean a3, LLogicalTernaryOperator func) {
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LLogicalBinaryOperator lShrink(LLogicalBinaryOperator left) {
		return (a2, a3) -> doApply(left.doApply(a2, a3), a2, a3);
	}

	public default LLogicalBinaryOperator lShrinkc(boolean a1) {
		return (a2, a3) -> doApply(a1, a2, a3);
	}

	public static LLogicalBinaryOperator lShrinked(LLogicalBinaryOperator left, LLogicalTernaryOperator func) {
		return func.lShrink(left);
	}

	public static LLogicalBinaryOperator lShrinkedc(boolean a1, LLogicalTernaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LLogicalBinaryOperator rShrink(LLogicalBinaryOperator right) {
		return (a1, a2) -> doApply(a1, a2, right.doApply(a1, a2));
	}

	public default LLogicalBinaryOperator rShrinkc(boolean a3) {
		return (a1, a2) -> doApply(a1, a2, a3);
	}

	public static LLogicalBinaryOperator rShrinked(LLogicalBinaryOperator right, LLogicalTernaryOperator func) {
		return func.rShrink(right);
	}

	public static LLogicalBinaryOperator rShrinkedc(boolean a3, LLogicalTernaryOperator func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static LLogicalTernaryOperator uncurryLogicalTernaryOp(LBoolFunction<LBoolFunction<LLogicalOperator>> func) {
		return (boolean a1, boolean a2, boolean a3) -> func.doApply(a1).doApply(a2).doApply(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureLogicalTernaryOp(boolean a1, boolean a2, boolean a3) {
		return () -> this.doApply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static LLogicalTernaryOperator constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LLogicalTernaryOperator apply1st(@Nonnull LLogicalOperator func) {
		return (a1, a2, a3) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LLogicalTernaryOperator apply2nd(@Nonnull LLogicalOperator func) {
		return (a1, a2, a3) -> func.doApply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static LLogicalTernaryOperator apply3rd(@Nonnull LLogicalOperator func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLogicalTernaryOperator logicalTernaryOp(final @Nonnull LLogicalTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LLogicalTernaryOperator recursive(final @Nonnull LFunction<LLogicalTernaryOperator, LLogicalTernaryOperator> selfLambda) {
		final LLogicalTernaryOperatorSingle single = new LLogicalTernaryOperatorSingle();
		LLogicalTernaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LLogicalTernaryOperatorSingle implements LSingle<LLogicalTernaryOperator>, LLogicalTernaryOperator {
		private LLogicalTernaryOperator target = null;

		@Override
		public boolean doApplyX(boolean a1, boolean a2, boolean a3) throws Throwable {
			return target.doApplyX(a1, a2, a3);
		}

		@Override
		public LLogicalTernaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LLogicalTernaryOperator logicalTernaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LLogicalTernaryOperator logicalTernaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static boolean call(boolean a1, boolean a2, boolean a3, final @Nonnull LLogicalTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceBoolean). */
	@Nonnull
	static LLogicalTernaryOperator safe() {
		return LLogicalTernaryOperator::produceBoolean;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLogicalTernaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLogicalTernaryOperator safe(final @Nullable LLogicalTernaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLogicalTernaryOperator> safeSupplier(final @Nullable LSupplier<LLogicalTernaryOperator> supplier) {
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
	default LLogicalTernaryOperator negate() {
		return (a1, a2, a3) -> !doApply(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLogicalTernaryOperator and(@Nonnull LLogicalTernaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doApply(a1, a2, a3) && other.doApply(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalTernaryOperator or(@Nonnull LLogicalTernaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doApply(a1, a2, a3) || other.doApply(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalTernaryOperator xor(@Nonnull LLogicalTernaryOperator other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doApply(a1, a2, a3) ^ other.doApply(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LLogicalTernaryOperator isEqual(boolean v1, boolean v2, boolean v3) {
		return (a1, a2, a3) -> (a1 == v1) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	/**
	 * Returns function that applies logical AND operator.
	 */
	@Nonnull
	static LLogicalTernaryOperator and() {
		return (a1, a2, a3) -> a1 && a2 && a3;
	}

	/**
	 * Returns function that applies logical OR operator.
	 */
	@Nonnull
	static LLogicalTernaryOperator or() {
		return (a1, a2, a3) -> a1 || a2 || a3;
	}

	/**
	 * Returns function that applies logical XOR operator.
	 */
	@Nonnull
	static LLogicalTernaryOperator xor() {
		return (a1, a2, a3) -> a1 ^ a2 ^ a3;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLogicalTernaryOperator logicalTernaryOpComposeBool(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2, @Nonnull final LLogicalOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	public static LLogicalTernaryOperator composedBool(@Nonnull final LLogicalOperator before1, @Nonnull final LLogicalOperator before2, @Nonnull final LLogicalOperator before3, LLogicalTernaryOperator after) {
		return after.logicalTernaryOpComposeBool(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> logicalTernaryOpCompose(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2, @Nonnull final LPredicate<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doTest(v1), before2.doTest(v2), before3.doTest(v3));
	}

	public static <V1, V2, V3> LTriPredicate<V1, V2, V3> composed(@Nonnull final LPredicate<? super V1> before1, @Nonnull final LPredicate<? super V2> before2, @Nonnull final LPredicate<? super V3> before3, LLogicalTernaryOperator after) {
		return after.logicalTernaryOpCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriBoolFunction<V> then(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalTernaryOperator thenToBool(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLogicalTernaryOperator nestingLogicalTernaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLogicalTernaryOperator shovingLogicalTernaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LLogicalTernaryOperator) Operator */
	public static boolean produceBoolean(boolean a1, boolean a2, boolean a3) {
		return Function4U.defaultBoolean;
	}

	// MAP: FOR, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, aBool> ia1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, aBool> ia1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		int size = ia3.size(source3);
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = oiFunc3.doTest(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, aBool> ia1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=IA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, IndexedRead<C2, aBool> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		int size = ia2.size(source2);
		LObjIntPredicate<Object> oiFunc2 = (LObjIntPredicate) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = oiFunc2.doTest(source2, i);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=IA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, aBool> ia1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LBoolConsumer consumer) {
		int size = ia1.size(source1);
		LObjIntPredicate<Object> oiFunc1 = (LObjIntPredicate) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			boolean a1 = oiFunc1.doTest(source1, i);
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a1, type=SA}, SourcePurpose{arg=boolean a2, type=SA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, aBool> sa1, C1 source1, SequentialRead<C2, I2, aBool> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LBoolConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LPredicate<Object> nextFunc1 = (LPredicate) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LPredicate<Object> nextFunc2 = (LPredicate) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			boolean a1 = nextFunc1.doTest(iterator1);
			boolean a2 = nextFunc2.doTest(iterator2);
			boolean a3 = nextFunc3.doTest(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
		}
	}

}
