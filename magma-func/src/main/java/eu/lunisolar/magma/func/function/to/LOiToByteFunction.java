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
 * Non-throwing functional interface (lambda) LOiToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: byte
 *
 * Special case of function that corresponds to expressions like (list, index) -> List::get
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LOiToByteFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OiFunction<T, aByte>, Codomain<aByte>, Domain2<a<T>, aInt> { // NOSONAR

	String DESCRIPTION = "LOiToByteFunction: byte applyAsByte(T a1,int a2)";

	// byte applyAsByte(T a1,int a2) ;
	default byte applyAsByte(T a1, int a2) {
		// return nestingApplyAsByte(a1,a2);
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsByte(T a1,int a2)
	 */
	byte applyAsByteX(T a1, int a2) throws Throwable;

	default byte tupleApplyAsByte(LObjIntPair<T> args) {
		return applyAsByte(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingApplyAsByte(T a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LOiToByteFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsByte(a1, a2, handling);
	}

	default byte applyAsByte(T a1, int a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LOiToByteFunction<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> applyAsByte(a1, a2, exF, newMessage, messageParams);
	}

	default byte applyAsByte(T a1, int a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LOiToByteFunction<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> applyAsByte(a1, a2, exF);
	}

	default byte applyAsByteThen(T a1, int a2, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsByte(e);
		}
	}

	default LOiToByteFunction<T> tryingThen(@Nonnull LToByteFunction<Throwable> handler) {
		return (a1, a2) -> applyAsByteThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingApplyAsByte(T a1, int a2) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingApplyAsByte(T a1, int a2) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> byte handlingApplyAsByte(T a1, int a2, LOiToByteFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsByte(a1, a2, handling);
	}

	static <T> byte tryApplyAsByte(T a1, int a2, LOiToByteFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsByte(a1, a2);
	}

	static <T> byte tryApplyAsByte(T a1, int a2, LOiToByteFunction<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, exF, newMessage, messageParams);
	}

	static <T> byte tryApplyAsByte(T a1, int a2, LOiToByteFunction<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, exF);
	}

	static <T> byte tryApplyAsByteThen(T a1, int a2, LOiToByteFunction<T> func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsByteThen(a1, a2, handler);
	}

	default byte failSafeApplyAsByte(T a1, int a2, @Nonnull LOiToByteFunction<T> failSafe) {
		try {
			return applyAsByte(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsByte(a1, a2);
		}
	}

	static <T> byte failSafeApplyAsByte(T a1, int a2, LOiToByteFunction<T> func, @Nonnull LOiToByteFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsByte(a1, a2);
		} else {
			return func.failSafeApplyAsByte(a1, a2, failSafe);
		}
	}

	static <T> LOiToByteFunction<T> failSafe(LOiToByteFunction<T> func, @Nonnull LOiToByteFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsByte(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullApplyAsByte(T a1, int a2) {
		return applyAsByte(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LOiToByteFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, LOiToByteFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.applyAsByte(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.applyAsByte(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, LOiToByteFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.applyAsByte(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.applyAsByte(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, LOiToByteFunction<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> byte from(M container, LBiFunction<M, K, V> extractor, K key, int a2, LOiToByteFunction<V> function, byte orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsByte(value, a2);
		}

		return orElse;
	}

	public default LIntToByteFunction lShrink(LIntFunction<T> left) {
		return a2 -> applyAsByte(left.apply(a2), a2);
	}

	public default LIntToByteFunction lShrinkc(T a1) {
		return a2 -> applyAsByte(a1, a2);
	}

	public static <T> LIntToByteFunction lShrinked(LIntFunction<T> left, LOiToByteFunction<T> func) {
		return func.lShrink(left);
	}

	public static <T> LIntToByteFunction lShrinkedc(T a1, LOiToByteFunction<T> func) {
		return func.lShrinkc(a1);
	}

	public default LToByteFunction<T> rShrink(LToIntFunction<T> right) {
		return a1 -> applyAsByte(a1, right.applyAsInt(a1));
	}

	public default LToByteFunction<T> rShrinkc(int a2) {
		return a1 -> applyAsByte(a1, a2);
	}

	public static <T> LToByteFunction<T> rShrinked(LToIntFunction<T> right, LOiToByteFunction<T> func) {
		return func.rShrink(right);
	}

	public static <T> LToByteFunction<T> rShrinkedc(int a2, LOiToByteFunction<T> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T> LOiToByteFunction<T> uncurry(LFunction<T, LIntToByteFunction> func) {
		return (T a1, int a2) -> func.apply(a1).applyAsByte(a2);
	}

	/** Cast that removes generics. */
	public default LOiToByteFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LOiToByteFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LOiToByteFunction<V2> cast(LOiToByteFunction<T> function) {
		return (LOiToByteFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LObjIntConsumer<T> toConsumer() {
		return this::applyAsByte;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier capture(T a1, int a2) {
		return () -> this.applyAsByte(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T> LOiToByteFunction<T> constant(byte r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LOiToByteFunction<T> apply1stAsByte(@Nonnull LToByteFunction<T> func) {
		return (a1, a2) -> func.applyAsByte(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LOiToByteFunction<T> apply2ndAsByte(@Nonnull LIntToByteFunction func) {
		return (a1, a2) -> func.applyAsByte(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LOiToByteFunction<T> oiToByteFunc(final @Nonnull LOiToByteFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LOiToByteFunction<T> recursive(final @Nonnull LFunction<LOiToByteFunction<T>, LOiToByteFunction<T>> selfLambda) {
		final LOiToByteFunctionSingle<T> single = new LOiToByteFunctionSingle();
		LOiToByteFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LOiToByteFunctionSingle<T> implements LSingle<LOiToByteFunction<T>>, LOiToByteFunction<T> {
		private LOiToByteFunction<T> target = null;

		@Override
		public byte applyAsByteX(T a1, int a2) throws Throwable {
			return target.applyAsByteX(a1, a2);
		}

		@Override
		public LOiToByteFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LOiToByteFunction<T> oiToByteFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LOiToByteFunction<T> oiToByteFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjToByteFunc<T> intObjToByteFunc(final @Nonnull LIntObjToByteFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> byte call(T a1, int a2, final @Nonnull LOiToByteFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsByte(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceByte). */
	@Nonnull
	static <T> LOiToByteFunction<T> safe() {
		return LOiToByteFunction::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LOiToByteFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LOiToByteFunction<T> safe(final @Nullable LOiToByteFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LOiToByteFunction<T>> safeSupplier(final @Nullable LSupplier<LOiToByteFunction<T>> supplier) {
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
	default <V1> LOiToByteFunction<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsByte(before1.apply(v1), before2.applyAsInt(v2));
	}

	public static <V1, T> LOiToByteFunction<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, LOiToByteFunction<T> after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToByteBiFunction<V1, V2> oiToByteFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsByte(before1.apply(v1), before2.applyAsInt(v2));
	}

	public static <V1, V2, T> LToByteBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, LOiToByteFunction<T> after) {
		return after.oiToByteFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LOiFunction<T, V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToByteFunction<T> thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToSrtFunction<T> thenToSrt(@Nonnull LByteToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToIntFunction<T> thenToInt(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToLongFunction<T> thenToLong(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToFltFunction<T> thenToFlt(@Nonnull LByteToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToDblFunction<T> thenToDbl(@Nonnull LByteToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToCharFunction<T> thenToChar(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntPredicate<T> thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsByte(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LOiToByteFunction for method references. */
	@FunctionalInterface
	interface LIntObjToByteFunc<T> extends LOiToByteFunction<T> {

		byte applyAsByteIntObj(int a2, T a1);

		@Override
		default byte applyAsByteX(T a1, int a2) {
			return this.applyAsByteIntObj(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LOiToByteFunction) Function */
	public static <T> byte produceByte(T a1, int a2) {
		return Function4U.defaultByte;
	}

	/** Does nothing (LOiToByteFunction.LIntObjToByteFunc) Function */
	public static <T> byte produceByte(int a2, T a1) {
		return Function4U.defaultByte;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LByteConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(this.applyAsByte(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LByteConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(this.applyAsByte(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LByteConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(this.applyAsByte(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LByteConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(this.applyAsByte(a1, a2));
		}
	}

}