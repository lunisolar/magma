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
 * Non-throwing functional interface (lambda) LObjSrtConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): T a1,short a2
 *
 * Co-domain: none
 *
 * Special case of consumer that corresponds to expressions like     (list, element) -> List::add
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjSrtConsumer<T> extends MetaConsumer, MetaInterface.NonThrowing, TeConsumer<T, aShort>, Codomain<aVoid>, Domain2<a<T>, aShort> {

	String DESCRIPTION = "LObjSrtConsumer: void accept(T a1,short a2)";

	default void accept(T a1, short a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T a1,short a2)
	 */
	void acceptX(T a1, short a2) throws Throwable;

	default LTuple.Void tupleAccept(LObjSrtPair<T> args) {
		accept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T a1, short a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjSrtConsumer<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingAccept(a1, a2, handling);
	}

	default void accept(T a1, short a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(T a1, short a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(T a1, short a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(T a1, short a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LObjSrtConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage);
	}

	default LObjSrtConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1);
	}

	default LObjSrtConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1, param1);
	}

	default LObjSrtConsumer<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> accept(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default void accept(T a1, short a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LObjSrtConsumer<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> accept(a1, a2, factory);
	}

	default void acceptThen(T a1, short a2, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LObjSrtConsumer<T> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2) -> acceptThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T a1, short a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T a1, short a2) {
		try {
			this.acceptX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> void shovingAccept(T a1, short a2, LObjSrtConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2);
	}

	static <T> void handlingAccept(T a1, short a2, LObjSrtConsumer<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, handling);
	}

	static <T> void tryAccept(T a1, short a2, LObjSrtConsumer<T> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2);
	}

	static <T> void tryAccept(T a1, short a2, LObjSrtConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage);
	}

	static <T> void tryAccept(T a1, short a2, LObjSrtConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1);
	}

	static <T> void tryAccept(T a1, short a2, LObjSrtConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1, param2);
	}

	static <T> void tryAccept(T a1, short a2, LObjSrtConsumer<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T> void tryAccept(T a1, short a2, LObjSrtConsumer<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, factory);
	}

	static <T> void tryAcceptThen(T a1, short a2, LObjSrtConsumer<T> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, handler);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjSrtConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, short a2, @Nonnull LObjSrtConsumer<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a1, short a2, @Nonnull LObjSrtConsumer<T> func) {
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
	public static <T> void times(int max_i, T a1, short a2, @Nonnull LObjSrtConsumer<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LSrtConsumer _with(T a1) {
		return a2 -> accept(a1, a2);
	}

	default LConsumer<T> with(short a2) {
		return a1 -> accept(a1, a2);
	}

	/**  */
	public static <T> LObjSrtConsumer<T> uncurry(@Nonnull LFunction<T, LSrtConsumer> func) {
		Null.nonNullArg(func, "func");
		return (T a1, short a2) -> func.apply(a1).accept(a2);
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default LObjSrtFunction<T, T> returning(T value) {
		return (a1, a2) -> {
			LObjSrtConsumer.this.accept(a1, a2);
			return value;
		};
	}

	/** Calls domain consumer before main function. */
	default LObjSrtConsumer<T> beforeDo(@Nonnull LObjSrtConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, short a2) -> {
			before.accept(a1, a2);
			accept(a1, a2);
		};
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjSrtConsumer<T> objSrtCons(final @Nonnull LObjSrtConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T> implements LObjSrtConsumer<T> {
		private LObjSrtConsumer<T> target = null;
		@Override
		public void acceptX(T a1, short a2) throws Throwable {
			target.acceptX(a1, a2);
		}
	}

	@Nonnull
	static <T> LObjSrtConsumer<T> recursive(final @Nonnull LFunction<LObjSrtConsumer<T>, LObjSrtConsumer<T>> selfLambda) {
		final S<T> single = new S();
		LObjSrtConsumer<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	@Nonnull
	static <T> LObjSrtConsumer<T> objSrtConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LObjSrtConsumer<T> objSrtConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static <T> void call(T a1, short a2, final @Nonnull LObjSrtConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjSrtConsumer<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LSrtUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.apply(v1), before2.applyAsSrt(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumer<V1, V2> unboxingCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToSrtFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.accept(before1.apply(v1), before2.applyAsSrt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LObjSrtConsumer<T> together in a order. */
	@Nonnull
	default LObjSrtConsumer<T> andThen(@Nonnull LObjSrtConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			this.accept(a1, a2);
			after.accept(a1, a2);
		};
	}

	// </editor-fold>

	default LObjSrtConsumer<T> shoving() {

		return new LObjSrtConsumer<T>() {

			public void accept(T a1, short a2) {
				try {
					this.acceptX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public void acceptX(T a1, short a2) throws Throwable {
				LObjSrtConsumer.this.acceptX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LObjSrtConsumer) */
	public static <T> void doNothing(T a1, short a2) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, T> int forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LObjSrtConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		size = Integer.min(size, ia2.size(source2));
		var oiFunc2 = IA.srtGetter(ia2);
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			short a2 = oiFunc2.applyAsSrt(source2, i);
			consumer.accept(a1, a2);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LObjSrtConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		int size = ia2.size(source2);
		var oiFunc2 = IA.srtGetter(ia2);
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			short a2 = oiFunc2.applyAsSrt(source2, i);
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
	public static <C1, C2, I2, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LObjSrtConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.srtSupplier(sa2);
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			short a2 = nextFunc2.applyAsSrt(iterator2);
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
	public static <C1, I1, C2, I2, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LObjSrtConsumer<? super T> consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.srtSupplier(sa2);
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T a1 = nextFunc1.apply(iterator1);
			short a2 = nextFunc2.applyAsSrt(iterator2);
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
	public static <T, C2> T targetedForEach(T a1, IndexedRead<C2, aShort> ia2, C2 source2, LObjSrtConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		var oiFunc2 = IA.srtGetter(ia2);
		int i = 0;
		for (; i < size; i++) {
			short a2 = oiFunc2.applyAsSrt(source2, i);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T, C2, I2> T targetedIterate(T a1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LObjSrtConsumer<? super T> consumer) {
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.srtSupplier(sa2);
		while (testFunc2.test(iterator2)) {
			short a2 = nextFunc2.applyAsSrt(iterator2);
			consumer.accept(a1, a2);
		}
		return a1;

	}

	/** ***ITERATION:    TE_CONSUMER_GEN_IA:  FOR, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=short a2, type=IA}] */
	default <C2> T genericForEach(T a1, IndexedRead<C2, aShort> ia2, C2 source2) {
		return targetedForEach(a1, ia2, source2, (LObjSrtConsumer<T>) this);
	}

	/** ***ITERATION:    TE_CONSUMER_GEN_SA:  WHILE, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=short a2, type=SA}] */
	default <C2, I2> T genericIterate(T a1, SequentialRead<C2, I2, aShort> sa2, C2 source2) {
		return targetedIterate(a1, sa2, source2, (LObjSrtConsumer<T>) this);
	}

	// <editor-fold desc="fluentUse">

	public static <T, R> R inlineAcceptR(R retval, T a1, short a2, LObjSrtConsumer<T> consumer) {
		consumer.accept(a1, a2);
		return retval;
	}

	public static <T> T inlineAccept(T a1, short a2, LObjSrtConsumer<T> consumer) {
		consumer.accept(a1, a2);
		return a1;
	}

	// </editor-fold>

}
