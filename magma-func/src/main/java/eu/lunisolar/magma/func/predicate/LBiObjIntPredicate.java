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
 * Non-throwing functional interface (lambda) LBiObjIntPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 a1,T2 a2,int a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjIntPredicate<T1, T2> extends MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiObjIntPredicate: boolean doTest(T1 a1,T2 a2,int a3)";

	// boolean doTest(T1 a1,T2 a2,int a3) ;
	default boolean doTest(T1 a1, T2 a2, int a3) {
		// return nestingDoTest(a1,a2,a3);
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(T1 a1,T2 a2,int a3)
	 */
	boolean doTestX(T1 a1, T2 a2, int a3) throws Throwable;

	default boolean tupleTest(LBiObjIntTriple<T1, T2> args) {
		return doTest(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(T1 a1, T2 a2, int a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(T1 a1, T2 a2, int a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(T1 a1, T2 a2, int a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(T1 a1, T2 a2, int a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T1 a1, T2 a2, int a3) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(T1 a1, T2 a2, int a3) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> boolean handlingDoTest(T1 a1, T2 a2, int a3, LBiObjIntPredicate<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a1, a2, a3, handling);
	}

	static <T1, T2> boolean tryDoTest(T1 a1, T2 a2, int a3, LBiObjIntPredicate<T1, T2> func) {
		return tryDoTest(a1, a2, a3, func, null);
	}

	static <T1, T2> boolean tryDoTest(T1 a1, T2 a2, int a3, LBiObjIntPredicate<T1, T2> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2> boolean tryDoTest(T1 a1, T2 a2, int a3, LBiObjIntPredicate<T1, T2> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, a3, exceptionFactory);
	}

	static <T1, T2> boolean tryDoTestThen(T1 a1, T2 a2, int a3, LBiObjIntPredicate<T1, T2> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a1, a2, a3, handler);
	}

	default boolean failSafeDoTest(T1 a1, T2 a2, int a3, @Nonnull LBiObjIntPredicate<T1, T2> failSafe) {
		try {
			return doTest(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a1, a2, a3);
		}
	}

	static <T1, T2> boolean failSafeDoTest(T1 a1, T2 a2, int a3, LBiObjIntPredicate<T1, T2> func, @Nonnull LBiObjIntPredicate<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a1, a2, a3);
		} else {
			return func.failSafeDoTest(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2> LBiObjIntPredicate<T1, T2> failSafeBiObjIntPred(LBiObjIntPredicate<T1, T2> func, @Nonnull LBiObjIntPredicate<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(T1 a1, T2 a2, int a3, LAction action) {
		if (doTest(a1, a2, a3)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(T1 a1, T2 a2, int a3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		if (doTest(a1, a2, a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	static <T1, T2> void throwIf(T1 a1, T2 a2, int a3, LBiObjIntPredicate<T1, T2> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static <T1, T2> void throwIfNot(T1 a1, T2 a2, int a3, LBiObjIntPredicate<T1, T2> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T1 a1, T2 a2, int a3) {
		return doTest(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, T2 a2, int a3) {
		return doTest(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjIntPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_a3, int max_a3, T1 a1, T2 a2, LBiObjIntPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a3 <= min_a3) {
			for (int a3 = min_a3; a3 <= max_a3; a3++) {
				func.doTest(a1, a2, a3);
			}
		} else {
			for (int a3 = min_a3; a3 >= max_a3; a3--) {
				func.doTest(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_a3, int max_a3, T1 a1, T2 a2, LBiObjIntPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a3 <= min_a3) {
			for (int a3 = min_a3; a3 < max_a3; a3++) {
				func.doTest(a1, a2, a3);
			}
		} else {
			for (int a3 = min_a3; a3 > max_a3; a3--) {
				func.doTest(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_a3, T1 a1, T2 a2, LBiObjIntPredicate<T1, T2> func) {
		fromTill(0, max_a3, a1, a2, func);
	}

	public default LObjIntPredicate<T2> lShrink(LOiFunction<T2, T1> left) {
		return (a2, a3) -> doTest(left.doApply(a2, a3), a2, a3);
	}

	public default LObjIntPredicate<T2> lShrinkc(T1 a1) {
		return (a2, a3) -> doTest(a1, a2, a3);
	}

	public static <T2, T1> LObjIntPredicate<T2> lShrinked(LOiFunction<T2, T1> left, LBiObjIntPredicate<T1, T2> func) {
		return func.lShrink(left);
	}

	public static <T2, T1> LObjIntPredicate<T2> lShrinkedc(T1 a1, LBiObjIntPredicate<T1, T2> func) {
		return func.lShrinkc(a1);
	}

	public default LBiPredicate<T1, T2> rShrink(LToIntBiFunction<T1, T2> right) {
		return (a1, a2) -> doTest(a1, a2, right.doApplyAsInt(a1, a2));
	}

	public default LBiPredicate<T1, T2> rShrinkc(int a3) {
		return (a1, a2) -> doTest(a1, a2, a3);
	}

	public static <T1, T2> LBiPredicate<T1, T2> rShrinked(LToIntBiFunction<T1, T2> right, LBiObjIntPredicate<T1, T2> func) {
		return func.rShrink(right);
	}

	public static <T1, T2> LBiPredicate<T1, T2> rShrinkedc(int a3, LBiObjIntPredicate<T1, T2> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T1, T2> LBiObjIntPredicate<T1, T2> uncurryBiObjIntPred(LFunction<T1, LFunction<T2, LIntPredicate>> func) {
		return (T1 a1, T2 a2, int a3) -> func.doApply(a1).doApply(a2).doTest(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureBiObjIntPred(T1 a1, T2 a2, int a3) {
		return () -> this.doTest(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LBiObjIntPredicate<T1, T2> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> test1st(@Nonnull LPredicate<T1> func) {
		return (a1, a2, a3) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> test2nd(@Nonnull LPredicate<T2> func) {
		return (a1, a2, a3) -> func.doTest(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> test3rd(@Nonnull LIntPredicate func) {
		return (a1, a2, a3) -> func.doTest(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> biObjIntPred(final @Nonnull LBiObjIntPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> recursive(final @Nonnull LFunction<LBiObjIntPredicate<T1, T2>, LBiObjIntPredicate<T1, T2>> selfLambda) {
		final LBiObjIntPredicateSingle<T1, T2> single = new LBiObjIntPredicateSingle();
		LBiObjIntPredicate<T1, T2> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiObjIntPredicateSingle<T1, T2> implements LSingle<LBiObjIntPredicate<T1, T2>>, LBiObjIntPredicate<T1, T2> {
		private LBiObjIntPredicate<T1, T2> target = null;

		@Override
		public boolean doTestX(T1 a1, T2 a2, int a3) throws Throwable {
			return target.doTestX(a1, a2, a3);
		}

		@Override
		public LBiObjIntPredicate<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> biObjIntPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> biObjIntPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LObjIntObj1Pred<T1, T2> objIntObj1Pred(final @Nonnull LObjIntObj1Pred<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj1Obj0IntPred<T2, T1> obj1Obj0IntPred(final @Nonnull LObj1Obj0IntPred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj1IntObj0Pred<T2, T1> obj1IntObj0Pred(final @Nonnull LObj1IntObj0Pred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LIntObj0Obj1Pred<T1, T2> intObj0Obj1Pred(final @Nonnull LIntObj0Obj1Pred<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LIntObjObj0Pred<T2, T1> intObjObj0Pred(final @Nonnull LIntObjObj0Pred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> boolean call(T1 a1, T2 a2, int a3, final @Nonnull LBiObjIntPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> safe() {
		return LBiObjIntPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjIntPredicate<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> safe(final @Nullable LBiObjIntPredicate<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjIntPredicate<T1, T2>> safeSupplier(final @Nullable LSupplier<LBiObjIntPredicate<T1, T2>> supplier) {
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
	default LBiObjIntPredicate<T1, T2> negate() {
		return (a1, a2, a3) -> !doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiObjIntPredicate<T1, T2> and(@Nonnull LBiObjIntPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) && other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiObjIntPredicate<T1, T2> or(@Nonnull LBiObjIntPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) || other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiObjIntPredicate<T1, T2> xor(@Nonnull LBiObjIntPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) ^ other.doTest(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2> LBiObjIntPredicate<T1, T2> isEqual(T1 v1, T2 v2, int v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == null ? v2 == null : a2.equals(v2)) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiObjIntPredicate<V1, V2> biObjIntPredComposeInt(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LIntUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsInt(v3));
	}

	public static <V1, V2, T1, T2> LBiObjIntPredicate<V1, V2> composedInt(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LIntUnaryOperator before3,
			LBiObjIntPredicate<T1, T2> after) {
		return after.biObjIntPredComposeInt(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> biObjIntPredCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToIntFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsInt(v3));
	}

	public static <V1, V2, V3, T1, T2> LTriPredicate<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToIntFunction<? super V3> before3,
			LBiObjIntPredicate<T1, T2> after) {
		return after.biObjIntPredCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjIntFunction<T1, T2, V> boolToBiObjIntFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doTest(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjIntPredicate<T1, T2> boolToBiObjIntPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doTest(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjIntPredicate<T1, T2> nestingBiObjIntPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjIntPredicate<T1, T2> shovingBiObjIntPred() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjIntPredicate for method references. */
	@FunctionalInterface
	interface LObjIntObj1Pred<T1, T2> extends LBiObjIntPredicate<T1, T2> {

		boolean doTestObjIntObj1(T1 a1, int a3, T2 a2);

		@Override
		default boolean doTestX(T1 a1, T2 a2, int a3) {
			return this.doTestObjIntObj1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjIntPredicate for method references. */
	@FunctionalInterface
	interface LObj1Obj0IntPred<T2, T1> extends LBiObjIntPredicate<T1, T2> {

		boolean doTestObj1Obj0Int(T2 a2, T1 a1, int a3);

		@Override
		default boolean doTestX(T1 a1, T2 a2, int a3) {
			return this.doTestObj1Obj0Int(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjIntPredicate for method references. */
	@FunctionalInterface
	interface LObj1IntObj0Pred<T2, T1> extends LBiObjIntPredicate<T1, T2> {

		boolean doTestObj1IntObj0(T2 a2, int a3, T1 a1);

		@Override
		default boolean doTestX(T1 a1, T2 a2, int a3) {
			return this.doTestObj1IntObj0(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjIntPredicate for method references. */
	@FunctionalInterface
	interface LIntObj0Obj1Pred<T1, T2> extends LBiObjIntPredicate<T1, T2> {

		boolean doTestIntObj0Obj1(int a3, T1 a1, T2 a2);

		@Override
		default boolean doTestX(T1 a1, T2 a2, int a3) {
			return this.doTestIntObj0Obj1(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjIntPredicate for method references. */
	@FunctionalInterface
	interface LIntObjObj0Pred<T2, T1> extends LBiObjIntPredicate<T1, T2> {

		boolean doTestIntObjObj0(int a3, T2 a2, T1 a1);

		@Override
		default boolean doTestX(T1 a1, T2 a2, int a3) {
			return this.doTestIntObjObj0(a3, a2, a1);
		}
	}

	// </editor-fold>

	// >>> LBiObjIntPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, int a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, int a3) {
		return false;
	}

	// >>> LObjIntObj1Pred<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, int a3, T2 a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, int a3, T2 a2) {
		return false;
	}

	// >>> LIntObj0Obj1Pred<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(int a3, T1 a1, T2 a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(int a3, T1 a1, T2 a2) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			doIf(a1, a2, a3, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=int a3, type=SA},
	// SourcePurpose{arg=LBiObjIntConsumer<? super T1,? super T2> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LBiObjIntConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			doIf(a1, a2, a3, consumer);
		}
	}

}
