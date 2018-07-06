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
 * Non-throwing functional interface (lambda) LObjIntBoolPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T a1,int a2,boolean a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntBoolPredicate<T> extends MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LObjIntBoolPredicate: boolean doTest(T a1,int a2,boolean a3)";

	// boolean doTest(T a1,int a2,boolean a3) ;
	default boolean doTest(T a1, int a2, boolean a3) {
		// return nestingDoTest(a1,a2,a3);
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doTest(T a1,int a2,boolean a3)
	 */
	boolean doTestX(T a1, int a2, boolean a3) throws Throwable;

	default boolean tupleTest(LObjIntBoolTriple<T> args) {
		return doTest(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingDoTest(T a1, int a2, boolean a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default boolean tryDoTest(T a1, int a2, boolean a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default boolean tryDoTest(T a1, int a2, boolean a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default boolean tryDoTestThen(T a1, int a2, boolean a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doTest(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T a1, int a2, boolean a3) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingDoTest(T a1, int a2, boolean a3) {
		try {
			return this.doTestX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingDoTest(T a1, int a2, boolean a3, LObjIntBoolPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoTest(a1, a2, a3, handling);
	}

	static <T> boolean tryDoTest(T a1, int a2, boolean a3, LObjIntBoolPredicate<T> func) {
		return tryDoTest(a1, a2, a3, func, null);
	}

	static <T> boolean tryDoTest(T a1, int a2, boolean a3, LObjIntBoolPredicate<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T> boolean tryDoTest(T a1, int a2, boolean a3, LObjIntBoolPredicate<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoTest(a1, a2, a3, exceptionFactory);
	}

	static <T> boolean tryDoTestThen(T a1, int a2, boolean a3, LObjIntBoolPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoTestThen(a1, a2, a3, handler);
	}

	default boolean failSafeDoTest(T a1, int a2, boolean a3, @Nonnull LObjIntBoolPredicate<T> failSafe) {
		try {
			return doTest(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doTest(a1, a2, a3);
		}
	}

	static <T> boolean failSafeDoTest(T a1, int a2, boolean a3, LObjIntBoolPredicate<T> func, @Nonnull LObjIntBoolPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doTest(a1, a2, a3);
		} else {
			return func.failSafeDoTest(a1, a2, a3, failSafe);
		}
	}

	static <T> LObjIntBoolPredicate<T> failSafeObjIntBoolPred(LObjIntBoolPredicate<T> func, @Nonnull LObjIntBoolPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(T a1, int a2, boolean a3, LAction action) {
		if (doTest(a1, a2, a3)) {
			action.doExecute();
			return true;
		} else {
			return false;
		}
	}

	default boolean doIf(T a1, int a2, boolean a3, LTieBoolConsumer<? super T> consumer) {
		if (doTest(a1, a2, a3)) {
			consumer.doAccept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	static <T> void throwIf(T a1, int a2, boolean a3, LObjIntBoolPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.doTest(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static <T> void throwIfNot(T a1, int a2, boolean a3, LObjIntBoolPredicate<T> pred, ExceptionWithMessageFactory<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.doTest(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T a1, int a2, boolean a3) {
		return doTest(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a1, int a2, boolean a3) {
		return doTest(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntBoolPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, boolean a3, LObjIntBoolPredicate<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.doTest(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.doTest(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, boolean a3, LObjIntBoolPredicate<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.doTest(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.doTest(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, boolean a3, LObjIntBoolPredicate<T> func) {
		fromTill(0, max_a2, a1, a3, func);
	}

	/**  */
	public static <T> LObjIntBoolPredicate<T> uncurryObjIntBoolPred(LFunction<T, LIntFunction<LLogicalOperator>> func) {
		return (T a1, int a2, boolean a3) -> func.doApply(a1).doApply(a2).doApply(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureObjIntBoolPred(T a1, int a2, boolean a3) {
		return () -> this.doTest(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjIntBoolPredicate<T> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjIntBoolPredicate<T> test1st(@Nonnull LPredicate<T> func) {
		return (a1, a2, a3) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjIntBoolPredicate<T> test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2, a3) -> func.doTest(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LObjIntBoolPredicate<T> test3rd(@Nonnull LLogicalOperator func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntBoolPredicate<T> objIntBoolPred(final @Nonnull LObjIntBoolPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LObjIntBoolPredicate<T> recursive(final @Nonnull LFunction<LObjIntBoolPredicate<T>, LObjIntBoolPredicate<T>> selfLambda) {
		final LObjIntBoolPredicateSingle<T> single = new LObjIntBoolPredicateSingle();
		LObjIntBoolPredicate<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LObjIntBoolPredicateSingle<T> implements LSingle<LObjIntBoolPredicate<T>>, LObjIntBoolPredicate<T> {
		private LObjIntBoolPredicate<T> target = null;

		@Override
		public boolean doTestX(T a1, int a2, boolean a3) throws Throwable {
			return target.doTestX(a1, a2, a3);
		}

		@Override
		public LObjIntBoolPredicate<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LObjIntBoolPredicate<T> objIntBoolPredThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LObjIntBoolPredicate<T> objIntBoolPredThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBoolIntPred<T> objBoolIntPred(final @Nonnull LObjBoolIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjBoolPred<T> intObjBoolPred(final @Nonnull LIntObjBoolPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntBoolObjPred<T> intBoolObjPred(final @Nonnull LIntBoolObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBoolObjIntPred<T> boolObjIntPred(final @Nonnull LBoolObjIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBoolIntObjPred<T> boolIntObjPred(final @Nonnull LBoolIntObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> boolean call(T a1, int a2, boolean a3, final @Nonnull LObjIntBoolPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LObjIntBoolPredicate<T> safe() {
		return LObjIntBoolPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntBoolPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjIntBoolPredicate<T> safe(final @Nullable LObjIntBoolPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntBoolPredicate<T>> safeSupplier(final @Nullable LSupplier<LObjIntBoolPredicate<T>> supplier) {
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
	default LObjIntBoolPredicate<T> negate() {
		return (a1, a2, a3) -> !doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjIntBoolPredicate<T> and(@Nonnull LObjIntBoolPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) && other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntBoolPredicate<T> or(@Nonnull LObjIntBoolPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) || other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntBoolPredicate<T> xor(@Nonnull LObjIntBoolPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) ^ other.doTest(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LObjIntBoolPredicate<T> isEqual(T v1, int v2, boolean v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjIntBoolPredicate<V1> objIntBoolPredComposeIntBool(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLogicalOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApply(v3));
	}

	public static <V1, T> LObjIntBoolPredicate<V1> composedIntBool(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLogicalOperator before3, LObjIntBoolPredicate<T> after) {
		return after.objIntBoolPredComposeIntBool(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> objIntBoolPredCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LPredicate<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doTest(v3));
	}

	public static <V1, V2, V3, T> LTriPredicate<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LPredicate<? super V3> before3, LObjIntBoolPredicate<T> after) {
		return after.objIntBoolPredCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntBoolFunction<T, V> boolToObjIntBoolFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doTest(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieBoolFunction<T> boolToTieBoolFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApplyAsInt(this.doTest(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntBoolPredicate<T> boolToObjIntBoolPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doTest(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjIntBoolPredicate<T> nestingObjIntBoolPred() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjIntBoolPredicate<T> shovingObjIntBoolPred() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntBoolPredicate for method references. */
	@FunctionalInterface
	interface LObjBoolIntPred<T> extends LObjIntBoolPredicate<T> {

		boolean doTestObjBoolInt(T a1, boolean a3, int a2);

		@Override
		default boolean doTestX(T a1, int a2, boolean a3) {
			return this.doTestObjBoolInt(a1, a3, a2);
		}
	}

	/** Permutation of LObjIntBoolPredicate for method references. */
	@FunctionalInterface
	interface LIntObjBoolPred<T> extends LObjIntBoolPredicate<T> {

		boolean doTestIntObjBool(int a2, T a1, boolean a3);

		@Override
		default boolean doTestX(T a1, int a2, boolean a3) {
			return this.doTestIntObjBool(a2, a1, a3);
		}
	}

	/** Permutation of LObjIntBoolPredicate for method references. */
	@FunctionalInterface
	interface LIntBoolObjPred<T> extends LObjIntBoolPredicate<T> {

		boolean doTestIntBoolObj(int a2, boolean a3, T a1);

		@Override
		default boolean doTestX(T a1, int a2, boolean a3) {
			return this.doTestIntBoolObj(a2, a3, a1);
		}
	}

	/** Permutation of LObjIntBoolPredicate for method references. */
	@FunctionalInterface
	interface LBoolObjIntPred<T> extends LObjIntBoolPredicate<T> {

		boolean doTestBoolObjInt(boolean a3, T a1, int a2);

		@Override
		default boolean doTestX(T a1, int a2, boolean a3) {
			return this.doTestBoolObjInt(a3, a1, a2);
		}
	}

	/** Permutation of LObjIntBoolPredicate for method references. */
	@FunctionalInterface
	interface LBoolIntObjPred<T> extends LObjIntBoolPredicate<T> {

		boolean doTestBoolIntObj(boolean a3, int a2, T a1);

		@Override
		default boolean doTestX(T a1, int a2, boolean a3) {
			return this.doTestBoolIntObj(a3, a2, a1);
		}
	}

	// </editor-fold>

	// >>> LObjIntBoolPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, int a2, boolean a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, int a2, boolean a3) {
		return false;
	}

	// >>> LObjBoolIntPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, boolean a3, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, boolean a3, int a2) {
		return false;
	}

	// >>> LIntObjBoolPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(int a2, T a1, boolean a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(int a2, T a1, boolean a3) {
		return false;
	}

	// >>> LIntBoolObjPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(int a2, boolean a3, T a1) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(int a2, boolean a3, T a1) {
		return false;
	}

	// >>> LBoolObjIntPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(boolean a3, T a1, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(boolean a3, T a1, int a2) {
		return false;
	}

	// >>> LBoolIntObjPred<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(boolean a3, int a2, T a1) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(boolean a3, int a2, T a1) {
		return false;
	}

	// FILTER: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTieBoolConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTieBoolConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			boolean a3 = oiFunc3.doTest(source3, i);
			doIf(a1, a2, a3, consumer);
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTieBoolConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTieBoolConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			boolean a3 = oiFunc3.doTest(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTieBoolConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTieBoolConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			boolean a3 = oiFunc3.doTest(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=boolean a3, type=IA},
	// SourcePurpose{arg=LTieBoolConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LTieBoolConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int size = ia3.size(source3);
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			boolean a3 = oiFunc3.doTest(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTieBoolConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTieBoolConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			boolean a3 = nextFunc3.doTest(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTieBoolConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTieBoolConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			boolean a3 = nextFunc3.doTest(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTieBoolConsumer<? super T> consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTieBoolConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			boolean a3 = nextFunc3.doTest(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	// FILTER: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=boolean a3, type=SA},
	// SourcePurpose{arg=LTieBoolConsumer<? super T> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTieBoolConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			boolean a3 = nextFunc3.doTest(iterator3);
			doIf(a1, a2, a3, consumer);
		}
	}

}
