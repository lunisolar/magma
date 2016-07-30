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
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR

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
 * Throwing functional interface (lambda) LObjIntPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: boolean
 *
 * @see LObjIntPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntPredicateX<T, X extends Throwable> extends MetaPredicate, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LObjIntPredicateX: boolean doTest(T a1,int a2) throws X";

	boolean doTest(T a1, int a2) throws X;

	default boolean tupleTest(LObjIntPair<T> args) throws X {
		return doTest(args.first(), args.second());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T a1, int a2) {
		try {
			return this.doTest(a1, a2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(T a1, int a2) {
		return ((LObjIntPredicateX<T, RuntimeException>) this).doTest(a1, a2);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(T a1, int a2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T a1, int a2) throws X {
		return doTest(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a1, int a2) throws X {
		return doTest(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplierX<X> captureObjIntPred(T a1, int a2) {
		return () -> this.doTest(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T, X extends Throwable> LObjIntPredicateX<T, X> constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LObjIntPredicateX<T, X> test1st(@Nonnull LPredicateX<T, X> func) {
		return (a1, a2) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LObjIntPredicateX<T, X> test2nd(@Nonnull LIntPredicateX<X> func) {
		return (a1, a2) -> func.doTest(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LObjIntPredicateX<T, X> lX(final @Nonnull LObjIntPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LObjIntPredicateX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LObjIntPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> V1<T, X> lX1(final @Nonnull V1<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> V1<T, X> lX1(@Nonnull Class<X> xClass, final @Nonnull V1<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T, X extends Throwable> boolean call(T a1, int a2, final @Nonnull LObjIntPredicateX<T, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2);
	}

	static <T, X extends Throwable> boolean shoving(T a1, int a2, final @Nonnull LObjIntPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoTest(a1, a2);
	}

	static <T, X extends Throwable> boolean nesting(T a1, int a2, final @Nonnull LObjIntPredicateX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoTest(a1, a2);
	}

	static <T, X extends Throwable, Y extends Throwable> boolean handling(T a1, int a2, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LObjIntPredicateX<T, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoTest(a1, a2, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LObjIntPredicateX<T, X> wrapX(final @Nonnull LObjIntPredicate<T> other) {
		return (LObjIntPredicateX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::alwaysFalse). */
	@Nonnull
	static <T, X extends Throwable> LObjIntPredicateX<T, X> safe() {
		return Function4U::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T, X extends Throwable, Y extends Throwable> LSupplierX<LObjIntPredicateX<T, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T, X extends Throwable> LObjIntPredicateX<T, X> safe(final @Nullable LObjIntPredicateX<T, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T, X extends Throwable, Y extends Throwable> LSupplierX<LObjIntPredicateX<T, X>, Y> safeSupplier(final @Nullable LSupplierX<LObjIntPredicateX<T, X>, Y> supplier) {
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
	default LObjIntPredicateX<T, X> negate() {
		return (a1, a2) -> !doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjIntPredicateX<T, X> and(@Nonnull LObjIntPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) && other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntPredicateX<T, X> or(@Nonnull LObjIntPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) || other.doTest(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntPredicateX<T, X> xor(@Nonnull LObjIntPredicateX<? super T, X> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> doTest(a1, a2) ^ other.doTest(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T, X extends Throwable> LObjIntPredicateX<T, X> isEqual(T v1, int v2) {
		return (a1, a2) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjIntPredicateX<V1, X> objIntPredComposeInt(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LIntUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicateX<V1, V2, X> objIntPredCompose(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LToIntFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntFunctionX<T, V, X> boolToObjIntFunc(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doTest(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjIntPredicate<T> nestingObjIntPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjIntPredicateX<T, RuntimeException> nestingObjIntPredX() {
		return this::nestingDoTest;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjIntPredicate<T> shovingObjIntPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjIntPredicateX<T, RuntimeException> shovingObjIntPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LObjIntPredicate<T> handleObjIntPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> this.handlingDoTest(a1, a2, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LObjIntPredicateX<T, Y> handleObjIntPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (a1, a2) -> this.handlingDoTest(a1, a2, handling);
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntPredicateX for method references. */
	@FunctionalInterface
	interface V1<T, X extends Throwable> extends LObjIntPredicateX<T, X> {

		boolean doTestV1(int a2, T a1) throws X;

		@Override
		default boolean doTest(T a1, int a2) throws X {
			return this.doTestV1(a2, a1);
		}
	}

	// </editor-fold>

}
