/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.function.to;

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
 * Non-throwing functional interface (lambda) LToFltBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToFltBiFunction<T1, T2> extends MetaFunction, MetaInterface.NonThrowing, Codomain<aFloat>, Domain2<a<T1>, a<T2>> { // NOSONAR

	String DESCRIPTION = "LToFltBiFunction: float applyAsFlt(T1 a1,T2 a2)";

	// float applyAsFlt(T1 a1,T2 a2) ;
	default float applyAsFlt(T1 a1, T2 a2) {
		// return nestingApplyAsFlt(a1,a2);
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsFlt(T1 a1,T2 a2)
	 */
	float applyAsFltX(T1 a1, T2 a2) throws Throwable;

	default float tupleApplyAsFlt(LPair<T1, T2> args) {
		return applyAsFlt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingApplyAsFlt(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToFltBiFunction<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsFlt(a1, a2, handling);
	}

	default float applyAsFlt(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LToFltBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> applyAsFlt(a1, a2, exF, newMessage, messageParams);
	}

	default float applyAsFlt(T1 a1, T2 a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LToFltBiFunction<T1, T2> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> applyAsFlt(a1, a2, exF);
	}

	default float applyAsFltThen(T1 a1, T2 a2, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LToFltBiFunction<T1, T2> tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return (a1, a2) -> applyAsFltThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingApplyAsFlt(T1 a1, T2 a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingApplyAsFlt(T1 a1, T2 a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> float handlingApplyAsFlt(T1 a1, T2 a2, LToFltBiFunction<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsFlt(a1, a2, handling);
	}

	static <T1, T2> float tryApplyAsFlt(T1 a1, T2 a2, LToFltBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsFlt(a1, a2);
	}

	static <T1, T2> float tryApplyAsFlt(T1 a1, T2 a2, LToFltBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, exF, newMessage, messageParams);
	}

	static <T1, T2> float tryApplyAsFlt(T1 a1, T2 a2, LToFltBiFunction<T1, T2> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, exF);
	}

	static <T1, T2> float tryApplyAsFltThen(T1 a1, T2 a2, LToFltBiFunction<T1, T2> func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsFltThen(a1, a2, handler);
	}

	default float failSafeApplyAsFlt(T1 a1, T2 a2, @Nonnull LToFltBiFunction<T1, T2> failSafe) {
		try {
			return applyAsFlt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsFlt(a1, a2);
		}
	}

	static <T1, T2> float failSafeApplyAsFlt(T1 a1, T2 a2, LToFltBiFunction<T1, T2> func, @Nonnull LToFltBiFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsFlt(a1, a2);
		} else {
			return func.failSafeApplyAsFlt(a1, a2, failSafe);
		}
	}

	static <T1, T2> LToFltBiFunction<T1, T2> failSafe(LToFltBiFunction<T1, T2> func, @Nonnull LToFltBiFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsFlt(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullApplyAsFlt(T1 a1, T2 a2) {
		return applyAsFlt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToFltBiFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, LToFltBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsFlt(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, LToFltBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsFlt(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, LToFltBiFunction<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> float from(M container, LBiFunction<M, K, V> extractor, K key, T2 a2, LToFltBiFunction<V, T2> function, float orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsFlt(value, a2);
		}

		return orElse;
	}

	public default LToFltFunction<T2> lShrink(LFunction<T2, T1> left) {
		return a2 -> applyAsFlt(left.apply(a2), a2);
	}

	public default LToFltFunction<T2> lShrinkc(T1 a1) {
		return a2 -> applyAsFlt(a1, a2);
	}

	public static <T2, T1> LToFltFunction<T2> lShrinked(LFunction<T2, T1> left, LToFltBiFunction<T1, T2> func) {
		return func.lShrink(left);
	}

	public static <T2, T1> LToFltFunction<T2> lShrinkedc(T1 a1, LToFltBiFunction<T1, T2> func) {
		return func.lShrinkc(a1);
	}

	public default LToFltFunction<T1> rShrink(LFunction<T1, T2> right) {
		return a1 -> applyAsFlt(a1, right.apply(a1));
	}

	public default LToFltFunction<T1> rShrinkc(T2 a2) {
		return a1 -> applyAsFlt(a1, a2);
	}

	public static <T1, T2> LToFltFunction<T1> rShrinked(LFunction<T1, T2> right, LToFltBiFunction<T1, T2> func) {
		return func.rShrink(right);
	}

	public static <T1, T2> LToFltFunction<T1> rShrinkedc(T2 a2, LToFltBiFunction<T1, T2> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T1, T2> LToFltBiFunction<T1, T2> uncurry(LFunction<T1, LToFltFunction<T2>> func) {
		return (T1 a1, T2 a2) -> func.apply(a1).applyAsFlt(a2);
	}

	/** Cast that removes generics. */
	public default LToFltBiFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2, V3> LToFltBiFunction<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, T1, T2> LToFltBiFunction<V2, V3> cast(LToFltBiFunction<T1, T2> function) {
		return (LToFltBiFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LBiConsumer<T1, T2> toConsumer() {
		return this::applyAsFlt;
	}

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier capture(T1 a1, T2 a2) {
		return () -> this.applyAsFlt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LToFltBiFunction<T1, T2> constant(float r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LToFltBiFunction<T1, T2> apply1stAsFlt(@Nonnull LToFltFunction<T1> func) {
		return (a1, a2) -> func.applyAsFlt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LToFltBiFunction<T1, T2> apply2ndAsFlt(@Nonnull LToFltFunction<T2> func) {
		return (a1, a2) -> func.applyAsFlt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LToFltBiFunction<T1, T2> toFltBiFunc(final @Nonnull LToFltBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LToFltBiFunction<T1, T2> recursive(final @Nonnull LFunction<LToFltBiFunction<T1, T2>, LToFltBiFunction<T1, T2>> selfLambda) {
		final LToFltBiFunctionSingle<T1, T2> single = new LToFltBiFunctionSingle();
		LToFltBiFunction<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LToFltBiFunctionSingle<T1, T2> implements LSingle<LToFltBiFunction<T1, T2>>, LToFltBiFunction<T1, T2> {
		private LToFltBiFunction<T1, T2> target = null;

		@Override
		public float applyAsFltX(T1 a1, T2 a2) throws Throwable {
			return target.applyAsFltX(a1, a2);
		}

		@Override
		public LToFltBiFunction<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LToFltBiFunction<T1, T2> toFltBiFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LToFltBiFunction<T1, T2> toFltBiFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LToFltObj1Obj0Func<T2, T1> toFltObj1Obj0Func(final @Nonnull LToFltObj1Obj0Func<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> float call(T1 a1, T2 a2, final @Nonnull LToFltBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsFlt(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceFloat). */
	@Nonnull
	static <T1, T2> LToFltBiFunction<T1, T2> safe() {
		return LToFltBiFunction::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LToFltBiFunction<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LToFltBiFunction<T1, T2> safe(final @Nullable LToFltBiFunction<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LToFltBiFunction<T1, T2>> safeSupplier(final @Nullable LSupplier<LToFltBiFunction<T1, T2>> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToFltBiFunction<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsFlt(before1.apply(v1), before2.apply(v2));
	}

	public static <V1, V2, T1, T2> LToFltBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, LToFltBiFunction<T1, T2> after) {
		return after.compose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T1, T2> thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T1, T2> thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T1, T2> thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T1, T2> thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T1, T2> thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T1, T2> thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T1, T2> thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T1, T2> thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsFlt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToFltBiFunction for method references. */
	@FunctionalInterface
	interface LToFltObj1Obj0Func<T2, T1> extends LToFltBiFunction<T1, T2> {

		float applyAsFltObj1Obj0(T2 a2, T1 a1);

		@Override
		default float applyAsFltX(T1 a1, T2 a2) {
			return this.applyAsFltObj1Obj0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LToFltBiFunction) Function */
	public static <T1, T2> float produceFloat(T1 a1, T2 a2) {
		return Function4U.defaultFloat;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			consumer.accept(this.applyAsFlt(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			consumer.accept(this.applyAsFlt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			consumer.accept(this.applyAsFlt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			consumer.accept(this.applyAsFlt(a1, a2));
		}
	}

}