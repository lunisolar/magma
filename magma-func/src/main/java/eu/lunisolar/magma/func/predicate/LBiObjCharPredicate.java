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
 * Non-throwing functional interface (lambda) LBiObjCharPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 a1,T2 a2,char a3
 *
 * Co-domain: boolean
 *
 * @see LBiObjCharPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjCharPredicate<T1, T2> extends LBiObjCharPredicateX<T1, T2, RuntimeException>, MetaPredicate, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiObjCharPredicate: boolean doTest(T1 a1,T2 a2,char a3)";

	boolean doTest(T1 a1, T2 a2, char a3);

	default boolean tupleTest(LBiObjCharTriple<T1, T2> args) {
		return doTest(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingDoTest(T1 a1, T2 a2, char a3) {
		return this.doTest(a1, a2, a3);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default boolean shovingDoTest(T1 a1, T2 a2, char a3) {
		return this.doTest(a1, a2, a3);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T1 a1, T2 a2, char a3) {
		return doTest(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, T2 a2, char a3) {
		return doTest(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjCharPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier captureBiObjCharPred(T1 a1, T2 a2, char a3) {
		return () -> this.doTest(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LBiObjCharPredicate<T1, T2> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjCharPredicate<T1, T2> test1st(@Nonnull LPredicate<T1> func) {
		return (a1, a2, a3) -> func.doTest(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjCharPredicate<T1, T2> test2nd(@Nonnull LPredicate<T2> func) {
		return (a1, a2, a3) -> func.doTest(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjCharPredicate<T1, T2> test3rd(@Nonnull LCharPredicate func) {
		return (a1, a2, a3) -> func.doTest(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjCharPredicate<T1, T2> l(final @Nonnull LBiObjCharPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> V1<T1, T2> l1(final @Nonnull V1<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> V2<T2, T1> l2(final @Nonnull V2<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> V3<T2, T1> l3(final @Nonnull V3<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> V4<T1, T2> l4(final @Nonnull V4<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> V5<T2, T1> l5(final @Nonnull V5<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> boolean call(T1 a1, T2 a2, char a3, final @Nonnull LBiObjCharPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doTest(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjCharPredicate<T1, T2> wrap(final @Nonnull LBiObjCharPredicateX<T1, T2, X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as Function4U::alwaysFalse). */
	@Nonnull
	static <T1, T2> LBiObjCharPredicate<T1, T2> safe() {
		return Function4U::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjCharPredicate<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LBiObjCharPredicate<T1, T2> safe(final @Nullable LBiObjCharPredicate<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjCharPredicate<T1, T2>> safeSupplier(final @Nullable LSupplier<LBiObjCharPredicate<T1, T2>> supplier) {
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
	default LBiObjCharPredicate<T1, T2> negate() {
		return (a1, a2, a3) -> !doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiObjCharPredicate<T1, T2> and(@Nonnull LBiObjCharPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) && other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiObjCharPredicate<T1, T2> or(@Nonnull LBiObjCharPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> doTest(a1, a2, a3) || other.doTest(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiObjCharPredicate<T1, T2> xor(@Nonnull LBiObjCharPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (T1 a1, T2 a2, char a3) -> doTest(a1, a2, a3) ^ other.doTest(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2> LBiObjCharPredicate<T1, T2> isEqual(T1 v1, T2 v2, char v3) {
		return (T1 a1, T2 a2, char a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == null ? v2 == null : a2.equals(v2)) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiObjCharPredicate<V1, V2> biObjCharPredComposeChar(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LCharUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsChar(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> biObjCharPredCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToCharFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsChar(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjCharFunction<T1, T2, V> boolToBiObjCharFunction(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doTest(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjCharPredicate<T1, T2> nestingBiObjCharPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjCharPredicateX<T1, T2, RuntimeException> nestingBiObjCharPredX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjCharPredicate<T1, T2> shovingBiObjCharPred() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjCharPredicateX<T1, T2, RuntimeException> shovingBiObjCharPredX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjCharPredicate for method references. */
	@FunctionalInterface
	interface V1<T1, T2> extends LBiObjCharPredicate<T1, T2> {

		boolean doTestV1(T1 a1, char a3, T2 a2);

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) {
			return this.doTestV1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjCharPredicate for method references. */
	@FunctionalInterface
	interface V2<T2, T1> extends LBiObjCharPredicate<T1, T2> {

		boolean doTestV2(T2 a2, T1 a1, char a3);

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) {
			return this.doTestV2(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjCharPredicate for method references. */
	@FunctionalInterface
	interface V3<T2, T1> extends LBiObjCharPredicate<T1, T2> {

		boolean doTestV3(T2 a2, char a3, T1 a1);

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) {
			return this.doTestV3(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjCharPredicate for method references. */
	@FunctionalInterface
	interface V4<T1, T2> extends LBiObjCharPredicate<T1, T2> {

		boolean doTestV4(char a3, T1 a1, T2 a2);

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) {
			return this.doTestV4(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjCharPredicate for method references. */
	@FunctionalInterface
	interface V5<T2, T1> extends LBiObjCharPredicate<T1, T2> {

		boolean doTestV5(char a3, T2 a2, T1 a1);

		@Override
		default boolean doTest(T1 a1, T2 a2, char a3) {
			return this.doTestV5(a3, a2, a1);
		}
	}

	// </editor-fold>

}
