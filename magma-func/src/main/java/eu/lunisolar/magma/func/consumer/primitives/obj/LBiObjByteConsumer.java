/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
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
import java.util.concurrent.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*; // NOSONAR

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
 * Non-throwing functional interface (lambda) LBiObjByteConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 a1,T2 a2,byte a3
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjByteConsumer<T1, T2> extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain3<a<T1>, a<T2>, aByte> {

	String DESCRIPTION = "LBiObjByteConsumer: void accept(T1 a1,T2 a2,byte a3)";

	// void accept(T1 a1,T2 a2,byte a3) ;
	default void accept(T1 a1, T2 a2, byte a3) {
		// nestingAccept(a1,a2,a3);
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T1 a1,T2 a2,byte a3)
	 */
	void acceptX(T1 a1, T2 a2, byte a3) throws Throwable;

	default LTuple.Void tupleAccept(LBiObjByteTriple<T1, T2> args) {
		accept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T1 a1, T2 a2, byte a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiObjByteConsumer<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingAccept(a1, a2, a3, handling);
	}

	default void accept(T1 a1, T2 a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(T1 a1, T2 a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(T1 a1, T2 a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(T1 a1, T2 a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBiObjByteConsumer<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage);
	}

	default LBiObjByteConsumer<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1);
	}

	default LBiObjByteConsumer<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LBiObjByteConsumer<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default void accept(T1 a1, T2 a2, byte a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBiObjByteConsumer<T1, T2> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory);
	}

	default void acceptThen(T1 a1, T2 a2, byte a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LBiObjByteConsumer<T1, T2> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3) -> acceptThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T1 a1, T2 a2, byte a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T1 a1, T2 a2, byte a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> void shovingAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2, a3);
	}

	static <T1, T2> void handlingAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, handling);
	}

	static <T1, T2> void tryAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3);
	}

	static <T1, T2> void tryAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage);
	}

	static <T1, T2> void tryAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1);
	}

	static <T1, T2> void tryAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T1, T2> void tryAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2> void tryAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory);
	}

	static <T1, T2> void tryAcceptThen(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, handler);
	}

	default void failSafeAccept(T1 a1, T2 a2, byte a3, @Nonnull LBiObjByteConsumer<T1, T2> failSafe) {
		try {
			accept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3);
		}
	}

	static <T1, T2> void failSafeAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> func, @Nonnull LBiObjByteConsumer<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3);
		} else {
			func.failSafeAccept(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2> LBiObjByteConsumer<T1, T2> failSafe(LBiObjByteConsumer<T1, T2> func, @Nonnull LBiObjByteConsumer<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjByteConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, byte a3, @Nonnull LBiObjByteConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, byte a3, @Nonnull LBiObjByteConsumer<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, byte a3, @Nonnull LBiObjByteConsumer<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static <T1, T2> LBiObjByteConsumer<T1, T2> uncurry(@Nonnull LFunction<T1, LFunction<T2, LByteConsumer>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, byte a3) -> func.apply(a1).apply(a2).accept(a3);
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default <T> LBiObjByteFunction<T1, T2, T> returning(T value) {
		return (a1, a2, a3) -> {
			LBiObjByteConsumer.this.accept(a1, a2, a3);
			return value;
		};
	}

	/** Calls domain consumer before main function. */
	default LBiObjByteConsumer<T1, T2> beforeDo(@Nonnull LBiObjByteConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, byte a3) -> {
			before.accept(a1, a2, a3);
			accept(a1, a2, a3);
		};
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjByteConsumer<T1, T2> biObjByteCons(final @Nonnull LBiObjByteConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2> LBiObjByteConsumer<T1, T2> biObjByteCons(@Nullable Class<T1> c1, @Nullable Class<T2> c2, final @Nonnull LBiObjByteConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T1, T2> implements LBiObjByteConsumer<T1, T2> {
		private LBiObjByteConsumer<T1, T2> target = null;
		@Override
		public void acceptX(T1 a1, T2 a2, byte a3) throws Throwable {
			target.acceptX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T1, T2> LBiObjByteConsumer<T1, T2> recursive(final @Nonnull LFunction<LBiObjByteConsumer<T1, T2>, LBiObjByteConsumer<T1, T2>> selfLambda) {
		final S<T1, T2> single = new S();
		LBiObjByteConsumer<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static <T1, T2> LBiObjByteConsumer<T1, T2> biObjByteConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LBiObjByteConsumer<T1, T2> biObjByteConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> obj0Byte2Obj1Cons(final @Nonnull LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> obj1Obj0Byte2Cons(final @Nonnull LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> obj1Byte2Obj0Cons(final @Nonnull LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> byte2Obj0Obj1Cons(final @Nonnull LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> byte2Obj1Obj0Cons(final @Nonnull LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> void call(T1 a1, T2 a2, byte a3, final @Nonnull LBiObjByteConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiObjByteConsumer<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LByteUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.apply(v2), before3.applyAsByte(v3));
	}

	public static <V1, V2, T1, T2> LBiObjByteConsumer<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LByteUnaryOperator before3,
			LBiObjByteConsumer<T1, T2> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> biObjByteConsCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToByteFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.apply(v2), before3.applyAsByte(v3));
	}

	public static <V1, V2, V3, T1, T2> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToByteFunction<? super V3> before3,
			LBiObjByteConsumer<T1, T2> after) {
		return after.biObjByteConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiObjByteConsumer<T1,T2> together in a order. */
	@Nonnull
	default LBiObjByteConsumer<T1, T2> andThen(@Nonnull LBiObjByteConsumer<? super T1, ? super T2> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			this.accept(a1, a2, a3);
			after.accept(a1, a2, a3);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjByteConsumer for method references. */
	@FunctionalInterface
	interface LObj0Byte2Obj1Cons<T1, T2> extends LBiObjByteConsumer<T1, T2> {

		/**
		 * Implement this, but call accept(T1 a1,T2 a2,byte a3)
		 */
		default void acceptX(T1 a1, T2 a2, byte a3) {
			this.acceptObj0Byte2Obj1(a1, a3, a2);
		}

		// void acceptObj0Byte2Obj1(T1 a1,byte a3,T2 a2) ;
		default void acceptObj0Byte2Obj1(T1 a1, byte a3, T2 a2) {
			// nestingAcceptObj0Byte2Obj1(a1,a3,a2);
			try {
				this.acceptObj0Byte2Obj1X(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptObj0Byte2Obj1(T1 a1,byte a3,T2 a2)
		 */
		void acceptObj0Byte2Obj1X(T1 a1, byte a3, T2 a2) throws Throwable;
	}

	/** Permutation of LBiObjByteConsumer for method references. */
	@FunctionalInterface
	interface LObj1Obj0Byte2Cons<T2, T1> extends LBiObjByteConsumer<T1, T2> {

		/**
		 * Implement this, but call acceptObj0Byte2Obj1(T1 a1,byte a3,T2 a2)
		 */
		default void acceptX(T1 a1, T2 a2, byte a3) {
			this.acceptObj1Obj0Byte2(a2, a1, a3);
		}

		// void acceptObj1Obj0Byte2(T2 a2,T1 a1,byte a3) ;
		default void acceptObj1Obj0Byte2(T2 a2, T1 a1, byte a3) {
			// nestingAcceptObj1Obj0Byte2(a2,a1,a3);
			try {
				this.acceptObj1Obj0Byte2X(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptObj1Obj0Byte2(T2 a2,T1 a1,byte a3)
		 */
		void acceptObj1Obj0Byte2X(T2 a2, T1 a1, byte a3) throws Throwable;
	}

	/** Permutation of LBiObjByteConsumer for method references. */
	@FunctionalInterface
	interface LObj1Byte2Obj0Cons<T2, T1> extends LBiObjByteConsumer<T1, T2> {

		/**
		 * Implement this, but call acceptObj1Obj0Byte2(T2 a2,T1 a1,byte a3)
		 */
		default void acceptX(T1 a1, T2 a2, byte a3) {
			this.acceptObj1Byte2Obj0(a2, a3, a1);
		}

		// void acceptObj1Byte2Obj0(T2 a2,byte a3,T1 a1) ;
		default void acceptObj1Byte2Obj0(T2 a2, byte a3, T1 a1) {
			// nestingAcceptObj1Byte2Obj0(a2,a3,a1);
			try {
				this.acceptObj1Byte2Obj0X(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptObj1Byte2Obj0(T2 a2,byte a3,T1 a1)
		 */
		void acceptObj1Byte2Obj0X(T2 a2, byte a3, T1 a1) throws Throwable;
	}

	/** Permutation of LBiObjByteConsumer for method references. */
	@FunctionalInterface
	interface LByte2Obj0Obj1Cons<T1, T2> extends LBiObjByteConsumer<T1, T2> {

		/**
		 * Implement this, but call acceptObj1Byte2Obj0(T2 a2,byte a3,T1 a1)
		 */
		default void acceptX(T1 a1, T2 a2, byte a3) {
			this.acceptByte2Obj0Obj1(a3, a1, a2);
		}

		// void acceptByte2Obj0Obj1(byte a3,T1 a1,T2 a2) ;
		default void acceptByte2Obj0Obj1(byte a3, T1 a1, T2 a2) {
			// nestingAcceptByte2Obj0Obj1(a3,a1,a2);
			try {
				this.acceptByte2Obj0Obj1X(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptByte2Obj0Obj1(byte a3,T1 a1,T2 a2)
		 */
		void acceptByte2Obj0Obj1X(byte a3, T1 a1, T2 a2) throws Throwable;
	}

	/** Permutation of LBiObjByteConsumer for method references. */
	@FunctionalInterface
	interface LByte2Obj1Obj0Cons<T2, T1> extends LBiObjByteConsumer<T1, T2> {

		/**
		 * Implement this, but call acceptByte2Obj0Obj1(byte a3,T1 a1,T2 a2)
		 */
		default void acceptX(T1 a1, T2 a2, byte a3) {
			this.acceptByte2Obj1Obj0(a3, a2, a1);
		}

		// void acceptByte2Obj1Obj0(byte a3,T2 a2,T1 a1) ;
		default void acceptByte2Obj1Obj0(byte a3, T2 a2, T1 a1) {
			// nestingAcceptByte2Obj1Obj0(a3,a2,a1);
			try {
				this.acceptByte2Obj1Obj0X(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call acceptByte2Obj1Obj0(byte a3,T2 a2,T1 a1)
		 */
		void acceptByte2Obj1Obj0X(byte a3, T2 a2, T1 a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LBiObjByteConsumer) */
	public static <T1, T2> void doNothing(T1 a1, T2 a2, byte a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjByteConsumer.LObj0Byte2Obj1Cons) */
	public static <T1, T2> void doNothing(T1 a1, byte a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjByteConsumer.LByte2Obj0Obj1Cons) */
	public static <T1, T2> void doNothing(byte a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	// <editor-fold desc="fluentUse">

	public static <T1, T2, R> R inlineAcceptR(R retval, T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> consumer) {
		consumer.accept(a1, a2, a3);
		return retval;
	}

	public static <T1, T2> T1 inlineAccept(T1 a1, T2 a2, byte a3, LBiObjByteConsumer<T1, T2> consumer) {
		consumer.accept(a1, a2, a3);
		return a1;
	}

	// </editor-fold>

}
