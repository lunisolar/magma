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

package eu.lunisolar.magma.func.function.from;

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
 * Non-throwing functional interface (lambda) LBiCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): char a1,char a2
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiCharFunction<R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain2<aChar, aChar> { // NOSONAR

	String DESCRIPTION = "LBiCharFunction: R apply(char a1,char a2)";

	@Nullable
	// R apply(char a1,char a2) ;
	default R apply(char a1, char a2) {
		// return nestingApply(a1,a2);
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(char a1,char a2)
	 */
	R applyX(char a1, char a2) throws Throwable;

	default R tupleApply(LCharPair args) {
		return apply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(char a1, char a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiCharFunction<R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApply(a1, a2, handling);
	}

	default R apply(char a1, char a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LBiCharFunction<R> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> apply(a1, a2, exF, newMessage, messageParams);
	}

	default R apply(char a1, char a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBiCharFunction<R> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> apply(a1, a2, exF);
	}

	default R applyThen(char a1, char a2, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LBiCharFunction<R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2) -> applyThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(char a1, char a2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(char a1, char a2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingApply(char a1, char a2, LBiCharFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, handling);
	}

	static <R> R tryApply(char a1, char a2, LBiCharFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2);
	}

	static <R> R tryApply(char a1, char a2, LBiCharFunction<R> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, exF, newMessage, messageParams);
	}

	static <R> R tryApply(char a1, char a2, LBiCharFunction<R> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, exF);
	}

	static <R> R tryApplyThen(char a1, char a2, LBiCharFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, handler);
	}

	default R failSafeApply(char a1, char a2, @Nonnull LBiCharFunction<R> failSafe) {
		try {
			return apply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2);
		}
	}

	static <R> R failSafeApply(char a1, char a2, LBiCharFunction<R> func, @Nonnull LBiCharFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2);
		} else {
			return func.failSafeApply(a1, a2, failSafe);
		}
	}

	static <R> LBiCharFunction<R> failSafe(LBiCharFunction<R> func, @Nonnull LBiCharFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApply(a1, a2, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(char a1, char a2) {
		return Null.requireNonNull(apply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiCharFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, char a1, char a2, LBiCharFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTill(int min_i, int max_i, char a1, char a2, LBiCharFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void times(int max_i, char a1, char a2, LBiCharFunction<R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	public default LCharFunction<R> lShrink(LCharUnaryOperator left) {
		return a2 -> apply(left.applyAsChar(a2), a2);
	}

	public default LCharFunction<R> lShrinkc(char a1) {
		return a2 -> apply(a1, a2);
	}

	public static <R> LCharFunction<R> lShrinked(LCharUnaryOperator left, LBiCharFunction<R> func) {
		return func.lShrink(left);
	}

	public static <R> LCharFunction<R> lShrinkedc(char a1, LBiCharFunction<R> func) {
		return func.lShrinkc(a1);
	}

	public default LCharFunction<R> rShrink(LCharUnaryOperator right) {
		return a1 -> apply(a1, right.applyAsChar(a1));
	}

	public default LCharFunction<R> rShrinkc(char a2) {
		return a1 -> apply(a1, a2);
	}

	public static <R> LCharFunction<R> rShrinked(LCharUnaryOperator right, LBiCharFunction<R> func) {
		return func.rShrink(right);
	}

	public static <R> LCharFunction<R> rShrinkedc(char a2, LBiCharFunction<R> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <R> LBiCharFunction<R> uncurry(LCharFunction<LCharFunction<R>> func) {
		return (char a1, char a2) -> func.apply(a1).apply(a2);
	}

	/** Cast that removes generics. */
	public default LBiCharFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LBiCharFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, R> LBiCharFunction<V2> cast(LBiCharFunction<R> function) {
		return (LBiCharFunction) function;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(char a1, char a2) {
		return () -> this.apply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <R> LBiCharFunction<R> constant(R r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <R> LBiCharFunction<R> apply1st(@Nonnull LCharFunction<R> func) {
		return (a1, a2) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <R> LBiCharFunction<R> apply2nd(@Nonnull LCharFunction<R> func) {
		return (a1, a2) -> func.apply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBiCharFunction<R> biCharFunc(final @Nonnull LBiCharFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <R> LBiCharFunction<R> recursive(final @Nonnull LFunction<LBiCharFunction<R>, LBiCharFunction<R>> selfLambda) {
		final LBiCharFunctionSingle<R> single = new LBiCharFunctionSingle();
		LBiCharFunction<R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LBiCharFunctionSingle<R> implements LSingle<LBiCharFunction<R>>, LBiCharFunction<R> {
		private LBiCharFunction<R> target = null;

		@Override
		public R applyX(char a1, char a2) throws Throwable {
			return target.applyX(a1, a2);
		}

		@Override
		public LBiCharFunction<R> value() {
			return target;
		}
	}

	@Nonnull
	static <R> LBiCharFunction<R> biCharFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <R> LBiCharFunction<R> biCharFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LChar1Char0Func<R> char1Char0Func(final @Nonnull LChar1Char0Func<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <R> R call(char a1, char a2, final @Nonnull LBiCharFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <R> LBiCharFunction<R> safe() {
		return LBiCharFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBiCharFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LBiCharFunction<R> safe(final @Nullable LBiCharFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBiCharFunction<R>> safeSupplier(final @Nullable LSupplier<LBiCharFunction<R>> supplier) {
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
	default LBiCharFunction<R> compose(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.apply(before1.applyAsChar(v1), before2.applyAsChar(v2));
	}

	public static <R> LBiCharFunction<R> composed(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, LBiCharFunction<R> after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiFunction<V1, V2, R> biCharFuncCompose(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.apply(before1.applyAsChar(v1), before2.applyAsChar(v2));
	}

	public static <V1, V2, R> LBiFunction<V1, V2, R> composed(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2, LBiCharFunction<R> after) {
		return after.biCharFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiCharFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiCharConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.accept(this.apply(a1, a2));
	}

	@Nonnull
	default LBiCharFunction<R> before(@Nonnull LBiCharConsumer before) {
		Null.nonNullArg(before, "before");
		return (a1, a2) -> {
			before.accept(a1, a2);
			return this.apply(a1, a2);
		};
	}

	@Nonnull
	default LBiCharFunction<R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			R result = this.apply(a1, a2);
			after.accept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharBinaryOperator thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiCharPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.apply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiCharFunction<R> nonNullable() {
		return this::nonNullApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiCharFunction for method references. */
	@FunctionalInterface
	interface LChar1Char0Func<R> extends LBiCharFunction<R> {
		@Nullable
		R applyChar1Char0(char a2, char a1);

		@Override
		default R applyX(char a1, char a2) {
			return this.applyChar1Char0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiCharFunction) Function */
	public static <R> R produce(char a1, char a2) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = oiFunc2.applyAsChar(source2, i);
			consumer.accept(this.apply(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = oiFunc2.applyAsChar(source2, i);
			consumer.accept(this.apply(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = nextFunc2.applyAsChar(iterator2);
			consumer.accept(this.apply(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = nextFunc2.applyAsChar(iterator2);
			consumer.accept(this.apply(a1, a2));
		}
	}

}
