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
 * Throwing functional interface (lambda) LBiObjCharPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 a1,T2 a2,char a3
 *
 * Co-domain: boolean
 *
 * @see LBiObjCharPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjCharPredicateX<T1, T2, X extends Throwable> extends MetaPredicate, MetaInterface.Throwing<X> { // NOSONAR

	String DESCRIPTION = "LBiObjCharPredicateX: boolean doTest(T1 a1,T2 a2,char a3) throws X";

	boolean doTest(T1 a1, T2 a2, char a3) throws X;

	default boolean tupleTest(LBiObjCharTriple<T1, T2> args) throws X {
		return doTest(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T1 a1, T2 a2, char a3) {
		try {
			return this.doTest(a1, a2, a3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(T1 a1, T2 a2, char a3) {
		return ((LBiObjCharPredicateX<T1, T2, RuntimeException>) this).doTest(a1, a2, a3);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> boolean handlingDoTest(T1 a1, T2 a2, char a3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			return this.doTest(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T1 a1, T2 a2, char a3) throws X {
		return doTest(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, T2 a2, char a3) throws X {
		return doTest(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjCharPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplierX<X> captureBiObjCharPred(T1 a1, T2 a2, char a3) {
		return () -> this.doTest(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> test1st(@Nonnull LPredicateX<T1, X> func) {
		return (a1, a2, a3) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> test2nd(@Nonnull LPredicateX<T2, X> func) {
		return (a1, a2, a3) -> func.doTest(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> test3rd(@Nonnull LCharPredicateX<X> func) {
		return (a1, a2, a3) -> func.doTest(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> lX(final @Nonnull LBiObjCharPredicateX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LBiObjCharPredicateX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> V1<T1, T2, X> lX1(final @Nonnull V1<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> V1<T1, T2, X> lX1(@Nonnull Class<X> xClass, final @Nonnull V1<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V2<T2, T1, X> lX2(final @Nonnull V2<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V2<T2, T1, X> lX2(@Nonnull Class<X> xClass, final @Nonnull V2<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V3<T2, T1, X> lX3(final @Nonnull V3<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V3<T2, T1, X> lX3(@Nonnull Class<X> xClass, final @Nonnull V3<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> V4<T1, T2, X> lX4(final @Nonnull V4<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> V4<T1, T2, X> lX4(@Nonnull Class<X> xClass, final @Nonnull V4<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V5<T2, T1, X> lX5(final @Nonnull V5<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V5<T2, T1, X> lX5(@Nonnull Class<X> xClass, final @Nonnull V5<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, X extends Throwable> boolean call(T1 a1, T2 a2, char a3, final @Nonnull LBiObjCharPredicateX<T1, T2, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2, a3);
	}

	static <T1, T2, X extends Throwable> boolean shoving(T1 a1, T2 a2, char a3, final @Nonnull LBiObjCharPredicateX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.shovingDoTest(a1, a2, a3);
	}

	static <T1, T2, X extends Throwable> boolean nesting(T1 a1, T2 a2, char a3, final @Nonnull LBiObjCharPredicateX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.nestingDoTest(a1, a2, a3);
	}

	static <T1, T2, X extends Throwable, Y extends Throwable> boolean handling(T1 a1, T2 a2, char a3, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LBiObjCharPredicateX<T1, T2, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		return lambda.handlingDoTest(a1, a2, a3, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> wrapX(final @Nonnull LBiObjCharPredicate<T1, T2> other) {
		return (LBiObjCharPredicateX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::alwaysFalse). */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> safe() {
		return Function4U::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LBiObjCharPredicateX<T1, T2, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> safe(final @Nullable LBiObjCharPredicateX<T1, T2, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LBiObjCharPredicateX<T1, T2, X>, Y> safeSupplier(final @Nullable LSupplierX<LBiObjCharPredicateX<T1, T2, X>, Y> supplier) {
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
	default LBiObjCharPredicateX<T1, T2, X> negate() {
		return (a1, a2, a3) -> !doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiObjCharPredicateX<T1, T2, X> and(@Nonnull LBiObjCharPredicateX<? super T1, ? super T2, X> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) && other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiObjCharPredicateX<T1, T2, X> or(@Nonnull LBiObjCharPredicateX<? super T1, ? super T2, X> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) || other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiObjCharPredicateX<T1, T2, X> xor(@Nonnull LBiObjCharPredicateX<? super T1, ? super T2, X> other) {
		Null.nonNullArg(other, "other");
		return (T1 a1, T2 a2, char a3) -> doTest(a1, a2, a3) ^ other.doTest(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicateX<T1, T2, X> isEqual(T1 v1, T2 v2, char v3) {
		return (T1 a1, T2 a2, char a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == null ? v2 == null : a2.equals(v2)) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiObjCharPredicateX<V1, V2, X> biObjCharPredComposeChar(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LCharUnaryOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsChar(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicateX<V1, V2, V3, X> biObjCharPredCompose(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2,
			@Nonnull final LToCharFunctionX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsChar(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjCharFunctionX<T1, T2, V, X> boolToBiObjCharFunction(@Nonnull LBoolFunctionX<? extends V, X> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doTest(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjCharPredicate<T1, T2> nestingBiObjCharPred() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjCharPredicateX<T1, T2, RuntimeException> nestingBiObjCharPredX() {
		return this::nestingDoTest;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjCharPredicate<T1, T2> shovingBiObjCharPred() {
		return this::shovingDoTest;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjCharPredicateX<T1, T2, RuntimeException> shovingBiObjCharPredX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LBiObjCharPredicate<T1, T2> handleBiObjCharPred(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> this.handlingDoTest(a1, a2, a3, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LBiObjCharPredicateX<T1, T2, Y> handleBiObjCharPredX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (a1, a2, a3) -> this.handlingDoTest(a1, a2, a3, handling);
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjCharPredicateX for method references. */
	@FunctionalInterface
	interface V1<T1, T2, X extends Throwable> extends LBiObjCharPredicateX<T1, T2, X> {

		boolean doTestV1(T1 a1, char a3, T2 a2) throws X;

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) throws X {
			return this.doTestV1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjCharPredicateX for method references. */
	@FunctionalInterface
	interface V2<T2, T1, X extends Throwable> extends LBiObjCharPredicateX<T1, T2, X> {

		boolean doTestV2(T2 a2, T1 a1, char a3) throws X;

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) throws X {
			return this.doTestV2(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjCharPredicateX for method references. */
	@FunctionalInterface
	interface V3<T2, T1, X extends Throwable> extends LBiObjCharPredicateX<T1, T2, X> {

		boolean doTestV3(T2 a2, char a3, T1 a1) throws X;

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) throws X {
			return this.doTestV3(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjCharPredicateX for method references. */
	@FunctionalInterface
	interface V4<T1, T2, X extends Throwable> extends LBiObjCharPredicateX<T1, T2, X> {

		boolean doTestV4(char a3, T1 a1, T2 a2) throws X;

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) throws X {
			return this.doTestV4(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjCharPredicateX for method references. */
	@FunctionalInterface
	interface V5<T2, T1, X extends Throwable> extends LBiObjCharPredicateX<T1, T2, X> {

		boolean doTestV5(char a3, T2 a2, T1 a1) throws X;

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) throws X {
			return this.doTestV5(a3, a2, a1);
		}
	}

	// </editor-fold>

}
