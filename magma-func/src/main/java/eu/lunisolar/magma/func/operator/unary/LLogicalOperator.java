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
 * Non-throwing functional interface (lambda) LLogicalOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): boolean a
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLogicalOperator extends MetaInterface.NonThrowing, MetaLogicalOperator { // NOSONAR

	String DESCRIPTION = "LLogicalOperator: boolean doApply(boolean a)";

	// boolean doApply(boolean a) ;
	default boolean doApply(boolean a) {
		// return nestingDoApply(a);
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(boolean a)
	 */
	boolean doApplyX(boolean a) throws Throwable;

	default boolean tupleApply(LBoolSingle args) {
		return doApply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoApply(boolean a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoApply(boolean a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoApply(boolean a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoApplyThen(boolean a, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoApply(boolean a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoApply(boolean a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingDoApply(boolean a, LLogicalOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a, handling);
	}

	static boolean tryDoApply(boolean a, LLogicalOperator func) {
		return tryDoApply(a, func, null);
	}

	static boolean tryDoApply(boolean a, LLogicalOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory, newMessage, messageParams);
	}

	static boolean tryDoApply(boolean a, LLogicalOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory);
	}

	static boolean tryDoApplyThen(boolean a, LLogicalOperator func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a, handler);
	}

	default boolean failSafeDoApply(boolean a, @Nonnull LLogicalOperator failSafe) {
		try {
			return doApply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a);
		}
	}

	static boolean failSafeDoApply(boolean a, LLogicalOperator func, @Nonnull LLogicalOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a);
		} else {
			return func.failSafeDoApply(a, failSafe);
		}
	}

	static LLogicalOperator failSafeLogicalOp(LLogicalOperator func, @Nonnull LLogicalOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApply(a, func, failSafe);
	}

	default boolean doIf(boolean a, LAction action) {
		if (doApply(a)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(boolean a, LBoolConsumer consumer) {
		if (doApply(a)) {
			consumer.doAccept(a);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(boolean a, LLogicalOperator pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(boolean a, LLogicalOperator pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApply(boolean a) {
		return doApply(a);
	}

	/** For convenience, boolean operator is also special case of predicate. */
	default boolean doTest(boolean a) {
		return doApply(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLogicalOperator.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, boolean a2, LObjBoolConsumer<V> consumer) {
		if (doApply(a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIf(V a1, int a2, boolean a3, LTieBoolConsumer<? super V> consumer) {
		if (doApply(a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, boolean a3, LTieBoolFunction<? super V> consumer) {
		if (doApply(a3)) {
			return consumer.doApplyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a, LLogicalOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApply(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, boolean a, LLogicalOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApply(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, boolean a, LLogicalOperator func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureLogicalOp(boolean a) {
		return () -> this.doApply(a);
	}

	/** Creates function that always returns the same value. */
	static LLogicalOperator constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLogicalOperator logicalOp(final @Nonnull LLogicalOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LLogicalOperator recursive(final @Nonnull LFunction<LLogicalOperator, LLogicalOperator> selfLambda) {
		final LLogicalOperatorSingle single = new LLogicalOperatorSingle();
		LLogicalOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LLogicalOperatorSingle implements LSingle<LLogicalOperator>, LLogicalOperator {
		private LLogicalOperator target = null;

		@Override
		public boolean doApplyX(boolean a) throws Throwable {
			return target.doApplyX(a);
		}

		@Override
		public LLogicalOperator value() {
			return target;
		}
	}

	@Nonnull
	static LLogicalOperator logicalOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LLogicalOperator logicalOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static boolean call(boolean a, final @Nonnull LLogicalOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceBoolean). */
	@Nonnull
	static LLogicalOperator safe() {
		return LLogicalOperator::produceBoolean;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLogicalOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLogicalOperator safe(final @Nullable LLogicalOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLogicalOperator> safeSupplier(final @Nullable LSupplier<LLogicalOperator> supplier) {
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
	default LLogicalOperator negate() {
		return a -> !doApply(a);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LLogicalOperator and(@Nonnull LLogicalOperator other) {
		Null.nonNullArg(other, "other");
		return a -> doApply(a) && other.doApply(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalOperator or(@Nonnull LLogicalOperator other) {
		Null.nonNullArg(other, "other");
		return a -> doApply(a) || other.doApply(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LLogicalOperator xor(@Nonnull LLogicalOperator other) {
		Null.nonNullArg(other, "other");
		return a -> doApply(a) ^ other.doApply(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LLogicalOperator isEqual(boolean target) {
		return a -> a == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLogicalOperator logicalOpComposeBool(@Nonnull final LLogicalOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApply(before.doApply(v));
	}

	public static LLogicalOperator composedBool(@Nonnull final LLogicalOperator before, LLogicalOperator after) {
		return after.logicalOpComposeBool(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> logicalOpCompose(@Nonnull final LPredicate<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApply(before.doTest(v));
	}

	public static <V> LPredicate<V> composed(@Nonnull final LPredicate<? super V> before, LLogicalOperator after) {
		return after.logicalOpCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBoolFunction<V> then(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToByteFunction thenToByte(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToSrtFunction thenToSrt(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToIntFunction thenToInt(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToLongFunction thenToLong(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToFltFunction thenToFlt(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToDblFunction thenToDbl(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToCharFunction thenToChar(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperator thenToBool(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApply(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LLogicalOperator identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLogicalOperator nestingLogicalOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLogicalOperator shovingLogicalOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LLogicalOperator) Operator */
	public static boolean produceBoolean(boolean a) {
		return Function4U.defaultBoolean;
	}

	// MAP: FOR, [SourcePurpose{arg=boolean a, type=IA}, SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aBool> ia, C0 source, LBoolConsumer consumer) {
		int size = ia.size(source);
		LObjIntPredicate<Object> oiFunc0 = (LObjIntPredicate) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a = oiFunc0.doTest(source, i);
			consumer.doAccept(this.doApply(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a, type=SA}, SourcePurpose{arg=LBoolConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aBool> sa, C0 source, LBoolConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LPredicate<Object> nextFunc0 = (LPredicate) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			boolean a = nextFunc0.doTest(iterator0);
			consumer.doAccept(this.doApply(a));
		}
	}

}
