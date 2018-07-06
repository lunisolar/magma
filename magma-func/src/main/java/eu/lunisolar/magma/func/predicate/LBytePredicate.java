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

package eu.lunisolar.magma.func.predicate;

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
 * Non-throwing functional interface (lambda) LBytePredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): byte a
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBytePredicate extends MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBytePredicate: boolean doTest(byte a)";

	// boolean doTest(byte a) ;
	default boolean doTest(byte a) {
		// return nestingDoTest(a);
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(byte a)
	 */
	boolean doTestX(byte a) throws Throwable;

	default boolean tupleTest(LByteSingle args) {
		return doTest(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(byte a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(byte a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(byte a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(byte a, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(byte a) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(byte a) {
		try {
			return this.doTestX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingDoTest(byte a, LBytePredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a, handling);
	}

	static boolean tryDoTest(byte a, LBytePredicate func) {
		return tryDoTest(a, func, null);
	}

	static boolean tryDoTest(byte a, LBytePredicate func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a, exceptionFactory, newMessage, messageParams);
	}

	static boolean tryDoTest(byte a, LBytePredicate func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a, exceptionFactory);
	}

	static boolean tryDoTestThen(byte a, LBytePredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a, handler);
	}

	default boolean failSafeDoTest(byte a, @Nonnull LBytePredicate failSafe) {
		try {
			return doTest(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a);
		}
	}

	static boolean failSafeDoTest(byte a, LBytePredicate func, @Nonnull LBytePredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a);
		} else {
			return func.failSafeDoTest(a, failSafe);
		}
	}

	static LBytePredicate failSafeBytePred(LBytePredicate func, @Nonnull LBytePredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoTest(a, func, failSafe);
	}

	default boolean doIf(byte a, LAction action) {
		if (doTest(a)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(byte a, LByteConsumer consumer) {
		if (doTest(a)) {
			consumer.doAccept(a);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(byte a, LBytePredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(byte a, LBytePredicate pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(byte a) {
		return doTest(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(byte a) {
		return doTest(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBytePredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, byte a2, LObjByteConsumer<V> consumer) {
		if (doTest(a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIf(V a1, int a2, byte a3, LTieByteConsumer<? super V> consumer) {
		if (doTest(a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, byte a3, LTieByteFunction<? super V> consumer) {
		if (doTest(a3)) {
			return consumer.doApplyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a, LBytePredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doTest(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doTest(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, byte a, LBytePredicate func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doTest(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doTest(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, byte a, LBytePredicate func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureBytePred(byte a) {
		return () -> this.doTest(a);
	}

	/** Creates function that always returns the same value. */
	static LBytePredicate constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBytePredicate bytePred(final @Nonnull LBytePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBytePredicate recursive(final @Nonnull LFunction<LBytePredicate, LBytePredicate> selfLambda) {
		final LBytePredicateSingle single = new LBytePredicateSingle();
		LBytePredicate func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBytePredicateSingle implements LSingle<LBytePredicate>, LBytePredicate {
		private LBytePredicate target = null;

		@Override
		public boolean doTestX(byte a) throws Throwable {
			return target.doTestX(a);
		}

		@Override
		public LBytePredicate value() {
			return target;
		}
	}

	@Nonnull
	static LBytePredicate bytePredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LBytePredicate bytePredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static boolean call(byte a, final @Nonnull LBytePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LBytePredicate safe() {
		return LBytePredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBytePredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBytePredicate safe(final @Nullable LBytePredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBytePredicate> safeSupplier(final @Nullable LSupplier<LBytePredicate> supplier) {
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
	default LBytePredicate negate() {
		return a -> !doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBytePredicate and(@Nonnull LBytePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) && other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBytePredicate or(@Nonnull LBytePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) || other.doTest(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBytePredicate xor(@Nonnull LBytePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> doTest(a) ^ other.doTest(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LBytePredicate isEqual(byte target) {
		return a -> a == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBytePredicate bytePredComposeByte(@Nonnull final LByteUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApplyAsByte(v));
	}

	public static LBytePredicate composedByte(@Nonnull final LByteUnaryOperator before, LBytePredicate after) {
		return after.bytePredComposeByte(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> bytePredCompose(@Nonnull final LToByteFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doTest(before.doApplyAsByte(v));
	}

	public static <V> LPredicate<V> composed(@Nonnull final LToByteFunction<? super V> before, LBytePredicate after) {
		return after.bytePredCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> boolToByteFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator boolToByteUnaryOp(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToSrtFunction boolToByteToSrtFunc(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction boolToByteToIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction boolToByteToLongFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFltFunction boolToByteToFltFunc(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDblFunction boolToByteToDblFunc(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction boolToByteToCharFunc(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doTest(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate boolToBytePred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doTest(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBytePredicate nestingBytePred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBytePredicate shovingBytePred() {
		return this;
	}

	// </editor-fold>

	// >>> LBytePredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte a) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte a) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=byte a, type=IA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aByte> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiToByteFunction<Object> oiFunc0 = (LOiToByteFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a = oiFunc0.doApplyAsByte(source, i);
			doIf(a, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=byte a, type=SA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aByte> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToByteFunction<Object> nextFunc0 = (LToByteFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			byte a = nextFunc0.doApplyAsByte(iterator0);
			doIf(a, consumer);
		}
	}

	// FILTER_WITH_TARGET_AND_INDEX: FOR, [SourcePurpose{arg=V v, type=CONST}, SourcePurpose{arg=byte a, type=IA}, SourcePurpose{arg=LTieByteConsumer<V>
	// consumer, type=CONST}]
	default <V, C0> int tieForEach(V v, IndexedRead<C0, aByte> ia, C0 source, LTieByteConsumer<V> consumer) {
		int size = ia.size(source);
		LOiToByteFunction<Object> oiFunc0 = (LOiToByteFunction) ia.getter();
		int acceptedIndex = 0;
		int i = 0;
		for (; i < size; i++) {
			byte a = oiFunc0.doApplyAsByte(source, i);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
		}
		return acceptedIndex;

	}

	// FILTER_WITH_TARGET_AND_INDEX: WHILE, [SourcePurpose{arg=V v, type=CONST}, SourcePurpose{arg=byte a, type=SA}, SourcePurpose{arg=LTieByteConsumer<V>
	// consumer, type=CONST}]
	default <V, C0, I0> int tieIterate(V v, SequentialRead<C0, I0, aByte> sa, C0 source, LTieByteConsumer<V> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToByteFunction<Object> nextFunc0 = (LToByteFunction) sa.getter();
		int acceptedIndex = 0;
		int i = 0;
		while (testFunc0.doTest(iterator0)) {
			byte a = nextFunc0.doApplyAsByte(iterator0);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
			i++;
		}
		return acceptedIndex;

	}

}
