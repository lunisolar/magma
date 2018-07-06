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
 * Non-throwing functional interface (lambda) LObjIntPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: boolean
 *
 * Special case of function that corresponds to expressions like (list, index) -> List::get
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntPredicate<T> extends MetaPredicate, MetaInterface.NonThrowing, OiFunction<T, aBool> { // NOSONAR

	String DESCRIPTION = "LObjIntPredicate: boolean doTest(T a1,int a2)";

	// boolean doTest(T a1,int a2) ;
	default boolean doTest(T a1, int a2) {
		// return nestingDoTest(a1,a2);
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(T a1,int a2)
	 */
	boolean doTestX(T a1, int a2) throws Throwable;

	default boolean tupleTest(LObjIntPair<T> args) {
		return doTest(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(T a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(T a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(T a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(T a1, int a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T a1, int a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(T a1, int a2) {
		try {
			return this.doTestX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingDoTest(T a1, int a2, LObjIntPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a1, a2, handling);
	}

	static <T> boolean tryDoTest(T a1, int a2, LObjIntPredicate<T> func) {
		return tryDoTest(a1, a2, func, null);
	}

	static <T> boolean tryDoTest(T a1, int a2, LObjIntPredicate<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <T> boolean tryDoTest(T a1, int a2, LObjIntPredicate<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, exceptionFactory);
	}

	static <T> boolean tryDoTestThen(T a1, int a2, LObjIntPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a1, a2, handler);
	}

	default boolean failSafeDoTest(T a1, int a2, @Nonnull LObjIntPredicate<T> failSafe) {
		try {
			return doTest(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a1, a2);
		}
	}

	static <T> boolean failSafeDoTest(T a1, int a2, LObjIntPredicate<T> func, @Nonnull LObjIntPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a1, a2);
		} else {
			return func.failSafeDoTest(a1, a2, failSafe);
		}
	}

	static <T> LObjIntPredicate<T> failSafeObjIntPred(LObjIntPredicate<T> func, @Nonnull LObjIntPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoTest(a1, a2, func, failSafe);
	}

	default boolean doIf(T a1, int a2, LAction action) {
		if (doTest(a1, a2)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(T a1, int a2, LObjIntConsumer<? super T> consumer) {
		if (doTest(a1, a2)) {
			consumer.doAccept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	static <T> void throwIf(T a1, int a2, LObjIntPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static <T> void throwIfNot(T a1, int a2, LObjIntPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T a1, int a2) {
		return doTest(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a1, int a2) {
		return doTest(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, T a2, int a3, LBiObjIntConsumer<V, ? super T> consumer) {
		if (doTest(a2, a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIf(V a1, int a2, T a3, LTieConsumer<? super V, ? super T> consumer) {
		if (doTest(a3, a2)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, T a3, LTieFunction<? super V, ? super T> consumer) {
		if (doTest(a3, a2)) {
			return consumer.doApplyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, LObjIntPredicate<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.doTest(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.doTest(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, LObjIntPredicate<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.doTest(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.doTest(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, LObjIntPredicate<T> func) {
		fromTill(0, max_a2, a1, func);
	}

	public default LIntPredicate lShrink(LIntFunction<T> left) {
		return a2 -> doTest(left.doApply(a2), a2);
	}

	public default LIntPredicate lShrinkc(T a1) {
		return a2 -> doTest(a1, a2);
	}

	public static <T> LIntPredicate lShrinked(LIntFunction<T> left, LObjIntPredicate<T> func) {
		return func.lShrink(left);
	}

	public static <T> LIntPredicate lShrinkedc(T a1, LObjIntPredicate<T> func) {
		return func.lShrinkc(a1);
	}

	public default LPredicate<T> rShrink(LToIntFunction<T> right) {
		return a1 -> doTest(a1, right.doApplyAsInt(a1));
	}

	public default LPredicate<T> rShrinkc(int a2) {
		return a1 -> doTest(a1, a2);
	}

	public static <T> LPredicate<T> rShrinked(LToIntFunction<T> right, LObjIntPredicate<T> func) {
		return func.rShrink(right);
	}

	public static <T> LPredicate<T> rShrinkedc(int a2, LObjIntPredicate<T> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T> LObjIntPredicate<T> uncurryObjIntPred(LFunction<T, LIntPredicate> func) {
		return (T a1, int a2) -> func.doApply(a1).doTest(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureObjIntPred(T a1, int a2) {
		return () -> this.doTest(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjIntPredicate<T> constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjIntPredicate<T> test1st(@Nonnull LPredicate<T> func) {
		return (a1, a2) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjIntPredicate<T> test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2) -> func.doTest(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntPredicate<T> objIntPred(final @Nonnull LObjIntPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LObjIntPredicate<T> recursive(final @Nonnull LFunction<LObjIntPredicate<T>, LObjIntPredicate<T>> selfLambda) {
		final LObjIntPredicateSingle<T> single = new LObjIntPredicateSingle();
		LObjIntPredicate<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LObjIntPredicateSingle<T> implements LSingle<LObjIntPredicate<T>>, LObjIntPredicate<T> {
		private LObjIntPredicate<T> target = null;

		@Override
		public boolean doTestX(T a1, int a2) throws Throwable {
			return target.doTestX(a1, a2);
		}

		@Override
		public LObjIntPredicate<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LObjIntPredicate<T> objIntPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LObjIntPredicate<T> objIntPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjPred<T> intObjPred(final @Nonnull LIntObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> boolean call(T a1, int a2, final @Nonnull LObjIntPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LObjIntPredicate<T> safe() {
		return LObjIntPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjIntPredicate<T> safe(final @Nullable LObjIntPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntPredicate<T>> safeSupplier(final @Nullable LSupplier<LObjIntPredicate<T>> supplier) {
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
	default LObjIntPredicate<T> negate() {
		return (a1, a2) -> !doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjIntPredicate<T> and(@Nonnull LObjIntPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) && other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntPredicate<T> or(@Nonnull LObjIntPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) || other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntPredicate<T> xor(@Nonnull LObjIntPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) ^ other.doTest(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LObjIntPredicate<T> isEqual(T v1, int v2) {
		return (a1, a2) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjIntPredicate<V1> objIntPredComposeInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, T> LObjIntPredicate<V1> composedInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, LObjIntPredicate<T> after) {
		return after.objIntPredComposeInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> objIntPredCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2, T> LBiPredicate<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, LObjIntPredicate<T> after) {
		return after.objIntPredCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LOiFunction<T, V> boolToOiFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToByteFunction<T> boolToOiToByteFunc(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsByte(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToSrtFunction<T> boolToOiToSrtFunc(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsSrt(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToIntFunction<T> boolToOiToIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsInt(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToLongFunction<T> boolToOiToLongFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsLong(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToFltFunction<T> boolToOiToFltFunc(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsFlt(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToDblFunction<T> boolToOiToDblFunc(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsDbl(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToCharFunction<T> boolToOiToCharFunc(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsChar(this.doTest(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntPredicate<T> boolToObjIntPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjIntPredicate<T> nestingObjIntPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjIntPredicate<T> shovingObjIntPred() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntPredicate for method references. */
	@FunctionalInterface
	interface LIntObjPred<T> extends LObjIntPredicate<T> {

		boolean doTestIntObj(int a2, T a1);

		@Override
		default boolean doTestX(T a1, int a2) {
			return this.doTestIntObj(a2, a1);
		}
	}

	// </editor-fold>

	// >>> LObjIntPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, int a2) {
		return false;
	}

	// >>> LIntObjPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(int a2, T a1) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(int a2, T a1) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LObjIntConsumer<? super T> consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LObjIntConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LObjIntConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LObjIntConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LObjIntConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LObjIntConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LObjIntConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LObjIntConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
