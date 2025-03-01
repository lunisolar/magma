/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
 * Non-throwing functional interface (lambda) LTieLongConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T a1,int a2,long a3
 *
 * Co-domain: none
 *
 * Special case of consumer that corresponds to expressions like     (list, index, element) -> List::set    or  (list, index, element) -> Array::set
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieLongConsumer<T> extends MetaConsumer, MetaInterface.NonThrowing, TieConsumer<T, aLong>, Codomain<aVoid>, Domain3<a<T>, aInt, aLong> {

	String DESCRIPTION = "LTieLongConsumer: void accept(T a1,int a2,long a3)";

	default void accept(T a1, int a2, long a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T a1,int a2,long a3)
	 */
	void acceptX(T a1, int a2, long a3) throws Throwable;

	default LTuple.Void tupleAccept(LObjIntLongTriple<T> args) {
		accept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T a1, int a2, long a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTieLongConsumer<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingAccept(a1, a2, a3, handling);
	}

	default void accept(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTieLongConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage);
	}

	default LTieLongConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1);
	}

	default LTieLongConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTieLongConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default void accept(T a1, int a2, long a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTieLongConsumer<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory);
	}

	default void acceptThen(T a1, int a2, long a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LTieLongConsumer<T> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3) -> acceptThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T a1, int a2, long a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T a1, int a2, long a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> void shovingAccept(T a1, int a2, long a3, LTieLongConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2, a3);
	}

	static <T> void handlingAccept(T a1, int a2, long a3, LTieLongConsumer<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, handling);
	}

	static <T> void tryAccept(T a1, int a2, long a3, LTieLongConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3);
	}

	static <T> void tryAccept(T a1, int a2, long a3, LTieLongConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage);
	}

	static <T> void tryAccept(T a1, int a2, long a3, LTieLongConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1);
	}

	static <T> void tryAccept(T a1, int a2, long a3, LTieLongConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T> void tryAccept(T a1, int a2, long a3, LTieLongConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T> void tryAccept(T a1, int a2, long a3, LTieLongConsumer<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory);
	}

	static <T> void tryAcceptThen(T a1, int a2, long a3, LTieLongConsumer<T> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, handler);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieLongConsumer.DESCRIPTION;
	}

	default LTieLongFunction<T> toTieFunction() {
		return (t, i, e) -> {
			this.accept(t, i, e);
			return 1;
		};
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, int a2, long a3, @Nonnull LTieLongConsumer<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a1, int a2, long a3, @Nonnull LTieLongConsumer<T> func) {
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
	public static <T> void times(int max_i, T a1, int a2, long a3, @Nonnull LTieLongConsumer<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	default LObjIntConsumer<T> with(long a3) {
		return (a1, a2) -> accept(a1, a2, a3);
	}

	default LLongConsumer _with(T a1, int a2) {
		return a3 -> accept(a1, a2, a3);
	}

	default LConsumer<T> with(int a2, long a3) {
		return a1 -> accept(a1, a2, a3);
	}

	/**  */
	public static <T> LTieLongConsumer<T> uncurry(@Nonnull LFunction<T, LIntFunction<LLongConsumer>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2, long a3) -> func.apply(a1).apply(a2).accept(a3);
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default LObjIntLongFunction<T, T> returning(T value) {
		return (a1, a2, a3) -> {
			LTieLongConsumer.this.accept(a1, a2, a3);
			return value;
		};
	}

	/** Calls domain consumer before main function. */
	default LTieLongConsumer<T> beforeDo(@Nonnull LTieLongConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2, long a3) -> {
			before.accept(a1, a2, a3);
			accept(a1, a2, a3);
		};
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieLongConsumer<T> tieLongCons(final @Nonnull LTieLongConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T> implements LTieLongConsumer<T> {
		private LTieLongConsumer<T> target = null;
		@Override
		public void acceptX(T a1, int a2, long a3) throws Throwable {
			target.acceptX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T> LTieLongConsumer<T> recursive(final @Nonnull LFunction<LTieLongConsumer<T>, LTieLongConsumer<T>> selfLambda) {
		final S<T> single = new S();
		LTieLongConsumer<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	@Nonnull
	static <T> LTieLongConsumer<T> tieLongConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LTieLongConsumer<T> tieLongConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static <T> void call(T a1, int a2, long a3, final @Nonnull LTieLongConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LTieLongConsumer<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLongUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsLong(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> unboxingCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsLong(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTieLongConsumer<T> together in a order. */
	@Nonnull
	default LTieLongConsumer<T> andThen(@Nonnull LTieLongConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			this.accept(a1, a2, a3);
			after.accept(a1, a2, a3);
		};
	}

	// </editor-fold>

	default LTieLongConsumer<T> shoving() {

		return new LTieLongConsumer<T>() {

			public void accept(T a1, int a2, long a3) {
				try {
					this.acceptX(a1, a2, a3);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public void acceptX(T a1, int a2, long a3) throws Throwable {
				LTieLongConsumer.this.acceptX(a1, a2, a3);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LTieLongConsumer) */
	public static <T> void doNothing(T a1, int a2, long a3) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, T> int forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		size = Integer.min(size, ia2.size(source2));
		var oiFunc2 = IA.intGetter(ia2);
		size = Integer.min(size, ia3.size(source3));
		var oiFunc3 = IA.longGetter(ia3);
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		int size = ia2.size(source2);
		var oiFunc2 = IA.intGetter(ia2);
		size = Integer.min(size, ia3.size(source3));
		var oiFunc3 = IA.longGetter(ia3);
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		size = Integer.min(size, ia3.size(source3));
		var oiFunc3 = IA.longGetter(ia3);
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		int size = ia3.size(source3);
		var oiFunc3 = IA.longGetter(ia3);
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3, I3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		size = Integer.min(size, ia2.size(source2));
		var oiFunc2 = IA.intGetter(ia2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.longSupplier(sa3);
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3, I3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		int size = ia2.size(source2);
		var oiFunc2 = IA.intGetter(ia2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.longSupplier(sa3);
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, C3, I3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.longSupplier(sa3);
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, C3, I3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.longSupplier(sa3);
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C3, T> int indexed2ndForEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		size = Integer.min(size, ia3.size(source3));
		var oiFunc3 = IA.longGetter(ia3);
		int a2 = 0;
		for (; a2 < size; a2++) {
			T a1 = oiFunc1.apply(source1, a2);
			long a3 = oiFunc3.applyAsLong(source3, a2);
			consumer.accept(a1, a2, a3);
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C3, T> int indexed2ndIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		int size = ia3.size(source3);
		var oiFunc3 = IA.longGetter(ia3);
		int a2 = 0;
		while (testFunc1.test(iterator1) && a2 < size) {
			T a1 = nextFunc1.apply(iterator1);
			long a3 = oiFunc3.applyAsLong(source3, a2);
			consumer.accept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C3, I3, T> int indexed2ndIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.longSupplier(sa3);
		int a2 = 0;
		while (a2 < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, a2);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C3, I3, T> int indexed2ndIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.longSupplier(sa3);
		int a2 = 0;
		while (testFunc1.test(iterator1) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, T> long indexed3rdForEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		size = Integer.min(size, ia2.size(source2));
		var oiFunc2 = IA.intGetter(ia2);
		long a3 = 0;
		for (; a3 < size; a3++) {
			T a1 = oiFunc1.apply(source1, (int) a3);
			int a2 = oiFunc2.applyAsInt(source2, (int) a3);
			consumer.accept(a1, a2, a3);
		}
		return a3;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, T> long indexed3rdIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LTieLongConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		int size = ia2.size(source2);
		var oiFunc2 = IA.intGetter(ia2);
		long a3 = 0;
		while (testFunc1.test(iterator1) && a3 < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, (int) a3);
			consumer.accept(a1, a2, a3);
			a3++;
		}
		return a3;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, I2, T> long indexed3rdIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		long a3 = 0;
		while (a3 < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, (int) a3);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(a1, a2, a3);
			a3++;
		}
		return a3;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index).
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <C1, I1, C2, I2, T> long indexed3rdIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LTieLongConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		long a3 = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(a1, a2, a3);
			a3++;
		}
		return a3;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T, C2, C3> T targetedForEach(T a1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		var oiFunc2 = IA.intGetter(ia2);
		size = Integer.min(size, ia3.size(source3));
		var oiFunc3 = IA.longGetter(ia3);
		int i = 0;
		for (; i < size; i++) {
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T, C2, I2, C3> T targetedIterate(T a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		int size = ia3.size(source3);
		var oiFunc3 = IA.longGetter(ia3);
		int i = 0;
		while (testFunc2.test(iterator2) && i < size) {
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <T, C2, C3, I3> T targetedIterate(T a1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		var oiFunc2 = IA.intGetter(ia2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.longSupplier(sa3);
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T, C2, I2, C3, I3> T targetedIterate(T a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.longSupplier(sa3);
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

	/** ***ITERATION:    TIE_CONSUMER_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=long a3, type=TIE_SOURCE}, SourcePurpose{arg=long a3, type=TIE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, aLong> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiToLongFunction<SRC>) srcAcc3, this);

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=long a3, type=IA}, SourcePurpose{arg=LTieLongConsumer<? super T> consumer, type=CONST}] */
	public static <T, C3> T tiForEach(T trg1, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH_NEW:  FOR, [SourcePurpose{arg=T trg1, type=SIZE_FACTORY}, SourcePurpose{arg=long a3, type=IA}, SourcePurpose{arg=LTieLongConsumer<? super T> consumer, type=CONST}] */
	public static <T, C3> T ntiForEach(LIntFunction<T> trgFactory1, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia3.size(source3);
		T trg1 = trgFactory1.apply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	/** ***ITERATION:    TIE_CONSUMER_SHORT:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=long a3, type=IA}, SourcePurpose{arg=LTieLongConsumer<? super T> consumer, type=CONST}] */
	public static <T, C3> int tieForEach(T trg1, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	/** ***ITERATION:    TIE_CONSUMER:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=long a3, type=TIE_SOURCE}, SourcePurpose{arg=long a3, type=TIE_SUPPLIER}, SourcePurpose{arg=LTieLongConsumer<? super T> consumer, type=CONST}] */
	public static <T, SRC> int tieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, LOiToLongFunction<SRC> srcAcc3, LTieLongConsumer<? super T> consumer) {
		for (int sIndex = sStart, tIndex = tStart; sIndex < sEnd; sIndex++, tIndex++) {
			long a3 = srcAcc3.applyAsLong(src3, sIndex);
			consumer.accept(trg1, tIndex, a3);
		}
		return sEnd - sStart;

	}

	/** ***ITERATION:    TIE_CONSUMER2_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=long a3, type=TIE_SOURCE}, SourcePurpose{arg=long a3, type=TE_GEN_PREDICATE}, SourcePurpose{arg=long a3, type=TE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int tStart, T trg1, SRC src3, OFunction<SRC, aBool> srcTest3, OFunction<SRC, aLong> srcAcc3) {
		return tieForEach(sStart, tStart, trg1, src3, (LPredicate<SRC>) srcTest3, (LToLongFunction<SRC>) srcAcc3, this);

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <T, SRC> int tieForEach(int sStart, int tStart, T trg1, SRC src3, LPredicate<SRC> srcTest3, LToLongFunction<SRC> srcAcc3, LTieLongConsumer<? super T> consumer) {
		int tIndex = tStart;
		for (; srcTest3.test(src3); tIndex++) {
			long a3 = srcAcc3.applyAsLong(src3);
			consumer.accept(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns iterations count
	*/
	public static <T, C3, I3> int tieIterate(T trg1, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.apply(source3), sa3.tester(), sa3.supplier(), consumer);
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T, C3, I3> T tiIterate(T trg1, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_ITERATE_NEW:  WHILE, [SourcePurpose{arg=T trg1, type=SUPPLIER}, SourcePurpose{arg=long a3, type=SA}, SourcePurpose{arg=LTieLongConsumer<? super T> consumer, type=CONST}] */
	public static <T, C3, I3> T ntiIterate(LSupplier<T> source1, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		T trg1 = source1.get();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	// <editor-fold desc="fluentUse">

	public static <T, R> R inlineAcceptR(R retval, T a1, int a2, long a3, LTieLongConsumer<T> consumer) {
		consumer.accept(a1, a2, a3);
		return retval;
	}

	public static <T> T inlineAccept(T a1, int a2, long a3, LTieLongConsumer<T> consumer) {
		consumer.accept(a1, a2, a3);
		return a1;
	}

	// </editor-fold>

}
