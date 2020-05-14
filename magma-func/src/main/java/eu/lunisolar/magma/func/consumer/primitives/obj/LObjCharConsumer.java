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

package eu.lunisolar.magma.func.consumer.primitives.obj;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*;
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
 * Non-throwing functional interface (lambda) LObjCharConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): T a1,char a2
 *
 * Co-domain: none
 *
 * Special case of consumer that corresponds to expressions like     (list, element) -> List::add
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjCharConsumer<T> extends MetaConsumer, MetaInterface.NonThrowing, TeConsumer<T, aChar>, Codomain<aVoid>, Domain2<a<T>, aChar> {

	String DESCRIPTION = "LObjCharConsumer: void accept(T a1,char a2)";

	// void accept(T a1,char a2) ;
	default void accept(T a1, char a2) {
		// nestingAccept(a1,a2);
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T a1,char a2)
	 */
	void acceptX(T a1, char a2) throws Throwable;

	default LTuple.Void tupleAccept(LObjCharPair<T> args) {
		accept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T a1, char a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjCharConsumer<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingAccept(a1, a2, handling);
	}

	default void accept(T a1, char a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default void accept(T a1, char a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default void accept(T a1, char a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default void accept(T a1, char a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LObjCharConsumer<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage);
	}

	default LObjCharConsumer<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage, param1);
	}

	default LObjCharConsumer<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage, param1, param1);
	}

	default LObjCharConsumer<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> accept(a1, a2, exF, newMessage, param1, param2, param3);
	}

	default void accept(T a1, char a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LObjCharConsumer<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> accept(a1, a2, exF);
	}

	default void acceptThen(T a1, char a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LObjCharConsumer<T> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2) -> acceptThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T a1, char a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T a1, char a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> void handlingAccept(T a1, char a2, LObjCharConsumer<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, handling);
	}

	static <T> void tryAccept(T a1, char a2, LObjCharConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2);
	}

	static <T> void tryAccept(T a1, char a2, LObjCharConsumer<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage);
	}

	static <T> void tryAccept(T a1, char a2, LObjCharConsumer<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage, param1);
	}

	static <T> void tryAccept(T a1, char a2, LObjCharConsumer<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage, param1, param2);
	}

	static <T> void tryAccept(T a1, char a2, LObjCharConsumer<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF, newMessage, param1, param2, param3);
	}

	static <T> void tryAccept(T a1, char a2, LObjCharConsumer<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, exF);
	}

	static <T> void tryAcceptThen(T a1, char a2, LObjCharConsumer<T> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, handler);
	}

	default void failSafeAccept(T a1, char a2, @Nonnull LObjCharConsumer<T> failSafe) {
		try {
			accept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2);
		}
	}

	static <T> void failSafeAccept(T a1, char a2, LObjCharConsumer<T> func, @Nonnull LObjCharConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2);
		} else {
			func.failSafeAccept(a1, a2, failSafe);
		}
	}

	static <T> LObjCharConsumer<T> failSafe(LObjCharConsumer<T> func, @Nonnull LObjCharConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeAccept(a1, a2, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjCharConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, char a2, @Nonnull LObjCharConsumer<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a1, char a2, @Nonnull LObjCharConsumer<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a1, char a2, @Nonnull LObjCharConsumer<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LCharConsumer lShrink(@Nonnull LCharFunction<T> left) {
		Null.nonNullArg(left, "left");
		return a2 -> accept(left.apply(a2), a2);
	}

	default LCharConsumer lShrink_(T a1) {
		return a2 -> accept(a1, a2);
	}

	public static <T> LCharConsumer lShrunken(@Nonnull LCharFunction<T> left, @Nonnull LObjCharConsumer<T> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T> LCharConsumer lShrunken_(T a1, @Nonnull LObjCharConsumer<T> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LConsumer<T> rShrink(@Nonnull LToCharFunction<T> right) {
		Null.nonNullArg(right, "right");
		return a1 -> accept(a1, right.applyAsChar(a1));
	}

	default LConsumer<T> rShrink_(char a2) {
		return a1 -> accept(a1, a2);
	}

	public static <T> LConsumer<T> rShrunken(@Nonnull LToCharFunction<T> right, @Nonnull LObjCharConsumer<T> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T> LConsumer<T> rShrunken_(char a2, @Nonnull LObjCharConsumer<T> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static <T> LObjCharConsumer<T> uncurry(@Nonnull LFunction<T, LCharConsumer> func) {
		Null.nonNullArg(func, "func");
		return (T a1, char a2) -> func.apply(a1).accept(a2);
	}

	/** Cast that removes generics. */
	default LObjCharConsumer untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2> LObjCharConsumer<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LObjCharConsumer<V2> cast(LObjCharConsumer<T> function) {
		return (LObjCharConsumer) function;
	}

	/** Calls domain consumer before main function. */
	default LObjCharConsumer<T> beforeDo(@Nonnull LObjCharConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, char a2) -> {
			before.accept(a1, a2);
			accept(a1, a2);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(T a1, char a2) {
		return () -> this.accept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjCharConsumer<T> accept1st(@Nonnull LConsumer<T> func) {
		return (a1, a2) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjCharConsumer<T> accept2nd(@Nonnull LCharConsumer func) {
		return (a1, a2) -> func.accept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjCharConsumer<T> objCharCons(final @Nonnull LObjCharConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LObjCharConsumer<T> objCharCons(@Nullable Class<T> c1, final @Nonnull LObjCharConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T> implements LObjCharConsumer<T> {
		private LObjCharConsumer<T> target = null;
		@Override
		public void acceptX(T a1, char a2) throws Throwable {
			target.acceptX(a1, a2);
		}
	}

	@Nonnull
	static <T> LObjCharConsumer<T> recursive(final @Nonnull LFunction<LObjCharConsumer<T>, LObjCharConsumer<T>> selfLambda) {
		final S<T> single = new S();
		LObjCharConsumer<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static <T> LObjCharConsumer<T> objCharConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LObjCharConsumer<T> objCharConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjCharConsumer.LCharObjCons<T> charObjCons(final @Nonnull LObjCharConsumer.LCharObjCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> void call(T a1, char a2, final @Nonnull LObjCharConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T> LObjCharConsumer<T> safe() {
		return LObjCharConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjCharConsumer<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjCharConsumer<T> safe(final @Nullable LObjCharConsumer<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjCharConsumer<T>> safeSupplier(final @Nullable LSupplier<LObjCharConsumer<T>> supplier) {
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
	default <V1> LObjCharConsumer<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LCharUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.apply(v1), before2.applyAsChar(v2));
	}

	public static <V1, T> LObjCharConsumer<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LCharUnaryOperator before2, LObjCharConsumer<T> after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> objCharConsCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToCharFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.apply(v1), before2.applyAsChar(v2));
	}

	public static <V1, V2, T> LBiConsumer<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToCharFunction<? super V2> before2, LObjCharConsumer<T> after) {
		return after.objCharConsCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LObjCharConsumer<T> together in a order. */
	@Nonnull
	default LObjCharConsumer<T> andThen(@Nonnull LObjCharConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			this.accept(a1, a2);
			after.accept(a1, a2);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjCharConsumer for method references. */
	@FunctionalInterface
	interface LCharObjCons<T> extends LObjCharConsumer<T> {

		/**
		 * Implement this, but call accept(T a1,char a2)
		 */
		default void acceptX(T a1, char a2) {
			this.acceptCharObj(a2, a1);
		}

		// void acceptCharObj(char a2,T a1) ;
		default void acceptCharObj(char a2, T a1) {
			// nestingAcceptCharObj(a2,a1);
			try {
				this.acceptCharObjX(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptCharObj(char a2,T a1)
		 */
		void acceptCharObjX(char a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LObjCharConsumer) */
	public static <T> void doNothing(T a1, char a2) {
		// NOSONAR
	}

	/** Does nothing (LObjCharConsumer.LCharObjCons) */
	public static <T> void doNothing(char a2, T a1) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, T> int forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LObjCharConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			char a2 = oiFunc2.applyAsChar(source2, i);
			consumer.accept(a1, a2);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, LObjCharConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			char a2 = oiFunc2.applyAsChar(source2, i);
			consumer.accept(a1, a2);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LObjCharConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			char a2 = nextFunc2.applyAsChar(iterator2);
			consumer.accept(a1, a2);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LObjCharConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T a1 = nextFunc1.apply(iterator1);
			char a2 = nextFunc2.applyAsChar(iterator2);
			consumer.accept(a1, a2);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T, C2> T targetedForEach(T a1, IndexedRead<C2, aChar> ia2, C2 source2, LObjCharConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			char a2 = oiFunc2.applyAsChar(source2, i);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T, C2, I2> T targetedIterate(T a1, SequentialRead<C2, I2, aChar> sa2, C2 source2, LObjCharConsumer<? super T> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		while (testFunc2.test(iterator2)) {
			char a2 = nextFunc2.applyAsChar(iterator2);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	/** ***ITERATION:    TE_CONSUMER_GEN_IA:  FOR, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=char a2, type=IA}] */
	default <C2> T genericForEach(T a1, IndexedRead<C2, aChar> ia2, C2 source2) {
		return targetedForEach(a1, ia2, source2, (LObjCharConsumer<T>) this);
	}

	/** ***ITERATION:    TE_CONSUMER_GEN_SA:  WHILE, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=char a2, type=SA}] */
	default <C2, I2> T genericIterate(T a1, SequentialRead<C2, I2, aChar> sa2, C2 source2) {
		return targetedIterate(a1, sa2, source2, (LObjCharConsumer<T>) this);
	}

}
