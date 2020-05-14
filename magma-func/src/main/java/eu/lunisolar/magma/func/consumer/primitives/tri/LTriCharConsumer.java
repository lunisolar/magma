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

package eu.lunisolar.magma.func.consumer.primitives.tri;

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
 * Non-throwing functional interface (lambda) LTriCharConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): char a1,char a2,char a3
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriCharConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain3<aChar, aChar, aChar> {

	String DESCRIPTION = "LTriCharConsumer: void accept(char a1,char a2,char a3)";

	// void accept(char a1,char a2,char a3) ;
	default void accept(char a1, char a2, char a3) {
		// nestingAccept(a1,a2,a3);
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(char a1,char a2,char a3)
	 */
	void acceptX(char a1, char a2, char a3) throws Throwable;

	default LTuple.Void tupleAccept(LCharTriple args) {
		accept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(char a1, char a2, char a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriCharConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingAccept(a1, a2, a3, handling);
	}

	default void accept(char a1, char a2, char a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default void accept(char a1, char a2, char a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default void accept(char a1, char a2, char a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default void accept(char a1, char a2, char a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LTriCharConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF, newMessage);
	}

	default LTriCharConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF, newMessage, param1);
	}

	default LTriCharConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF, newMessage, param1, param1);
	}

	default LTriCharConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF, newMessage, param1, param2, param3);
	}

	default void accept(char a1, char a2, char a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LTriCharConsumer trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF);
	}

	default void acceptThen(char a1, char a2, char a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LTriCharConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3) -> acceptThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(char a1, char a2, char a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(char a1, char a2, char a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingAccept(char a1, char a2, char a3, LTriCharConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, handling);
	}

	static void tryAccept(char a1, char a2, char a3, LTriCharConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3);
	}

	static void tryAccept(char a1, char a2, char a3, LTriCharConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF, newMessage);
	}

	static void tryAccept(char a1, char a2, char a3, LTriCharConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF, newMessage, param1);
	}

	static void tryAccept(char a1, char a2, char a3, LTriCharConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF, newMessage, param1, param2);
	}

	static void tryAccept(char a1, char a2, char a3, LTriCharConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF, newMessage, param1, param2, param3);
	}

	static void tryAccept(char a1, char a2, char a3, LTriCharConsumer func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF);
	}

	static void tryAcceptThen(char a1, char a2, char a3, LTriCharConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, handler);
	}

	default void failSafeAccept(char a1, char a2, char a3, @Nonnull LTriCharConsumer failSafe) {
		try {
			accept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3);
		}
	}

	static void failSafeAccept(char a1, char a2, char a3, LTriCharConsumer func, @Nonnull LTriCharConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3);
		} else {
			func.failSafeAccept(a1, a2, a3, failSafe);
		}
	}

	static LTriCharConsumer failSafe(LTriCharConsumer func, @Nonnull LTriCharConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriCharConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a1, char a2, char a3, @Nonnull LTriCharConsumer func) {
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
	public static void fromTill(int min_i, int max_i, char a1, char a2, char a3, @Nonnull LTriCharConsumer func) {
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
	public static void times(int max_i, char a1, char a2, char a3, @Nonnull LTriCharConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	default LBiCharConsumer lShrink(@Nonnull LCharBinaryOperator left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> accept(left.applyAsChar(a2, a3), a2, a3);
	}

	default LBiCharConsumer lShrink_(char a1) {
		return (a2, a3) -> accept(a1, a2, a3);
	}

	public static LBiCharConsumer lShrunken(@Nonnull LCharBinaryOperator left, @Nonnull LTriCharConsumer func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LBiCharConsumer lShrunken_(char a1, @Nonnull LTriCharConsumer func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LBiCharConsumer rShrink(@Nonnull LCharBinaryOperator right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> accept(a1, a2, right.applyAsChar(a1, a2));
	}

	default LBiCharConsumer rShrink_(char a3) {
		return (a1, a2) -> accept(a1, a2, a3);
	}

	public static LBiCharConsumer rShrunken(@Nonnull LCharBinaryOperator right, @Nonnull LTriCharConsumer func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LBiCharConsumer rShrunken_(char a3, @Nonnull LTriCharConsumer func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static LTriCharConsumer uncurry(@Nonnull LCharFunction<LCharFunction<LCharConsumer>> func) {
		Null.nonNullArg(func, "func");
		return (char a1, char a2, char a3) -> func.apply(a1).apply(a2).accept(a3);
	}

	/** Calls domain consumer before main function. */
	default LTriCharConsumer beforeDo(@Nonnull LTriCharConsumer before) {
		Null.nonNullArg(before, "before");
		return (char a1, char a2, char a3) -> {
			before.accept(a1, a2, a3);
			accept(a1, a2, a3);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(char a1, char a2, char a3) {
		return () -> this.accept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LTriCharConsumer accept1st(@Nonnull LCharConsumer func) {
		return (a1, a2, a3) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LTriCharConsumer accept2nd(@Nonnull LCharConsumer func) {
		return (a1, a2, a3) -> func.accept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static LTriCharConsumer accept3rd(@Nonnull LCharConsumer func) {
		return (a1, a2, a3) -> func.accept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LTriCharConsumer triCharCons(final @Nonnull LTriCharConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LTriCharConsumer {
		private LTriCharConsumer target = null;
		@Override
		public void acceptX(char a1, char a2, char a3) throws Throwable {
			target.acceptX(a1, a2, a3);
		}
	}

	@Nonnull
	static LTriCharConsumer recursive(final @Nonnull LFunction<LTriCharConsumer, LTriCharConsumer> selfLambda) {
		final S single = new S();
		LTriCharConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	@Nonnull
	static LTriCharConsumer triCharConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LTriCharConsumer triCharConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static void call(char a1, char a2, char a3, final @Nonnull LTriCharConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LTriCharConsumer safe() {
		return LTriCharConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LTriCharConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LTriCharConsumer safe(final @Nullable LTriCharConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LTriCharConsumer> safeSupplier(final @Nullable LSupplier<LTriCharConsumer> supplier) {
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
	default LTriCharConsumer compose(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, @Nonnull final LCharUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.applyAsChar(v1), before2.applyAsChar(v2), before3.applyAsChar(v3));
	}

	public static LTriCharConsumer composed(@Nonnull final LCharUnaryOperator before1, @Nonnull final LCharUnaryOperator before2, @Nonnull final LCharUnaryOperator before3, LTriCharConsumer after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> triCharConsCompose(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2, @Nonnull final LToCharFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.applyAsChar(v1), before2.applyAsChar(v2), before3.applyAsChar(v3));
	}

	public static <V1, V2, V3> LTriConsumer<V1, V2, V3> composed(@Nonnull final LToCharFunction<? super V1> before1, @Nonnull final LToCharFunction<? super V2> before2, @Nonnull final LToCharFunction<? super V3> before3, LTriCharConsumer after) {
		return after.triCharConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTriCharConsumer together in a order. */
	@Nonnull
	default LTriCharConsumer andThen(@Nonnull LTriCharConsumer after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			this.accept(a1, a2, a3);
			after.accept(a1, a2, a3);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LTriCharConsumer) */
	public static void doNothing(char a1, char a2, char a3) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3> int forEach(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTriCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = oiFunc3.applyAsChar(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3> int iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTriCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = oiFunc3.applyAsChar(source3, i);
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
	public static <C1, C2, I2, C3> int iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTriCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = oiFunc3.applyAsChar(source3, i);
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
	public static <C1, I1, C2, I2, C3> int iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTriCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = oiFunc3.applyAsChar(source3, i);
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
	public static <C1, C2, C3, I3> int iterate(IndexedRead<C1, aChar> ia1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTriCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = nextFunc3.applyAsChar(iterator3);
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
	public static <C1, I1, C2, C3, I3> int iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, IndexedRead<C2, aChar> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTriCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = nextFunc3.applyAsChar(iterator3);
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
	public static <C1, C2, I2, C3, I3> int iterate(IndexedRead<C1, aChar> ia1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTriCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiToCharFunction<Object> oiFunc1 = (LOiToCharFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			char a1 = oiFunc1.applyAsChar(source1, i);
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = nextFunc3.applyAsChar(iterator3);
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
	public static <C1, I1, C2, I2, C3, I3> int iterate(SequentialRead<C1, I1, aChar> sa1, C1 source1, SequentialRead<C2, I2, aChar> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTriCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToCharFunction<Object> nextFunc1 = (LToCharFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			char a1 = nextFunc1.applyAsChar(iterator1);
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = nextFunc3.applyAsChar(iterator3);
			consumer.accept(a1, a2, a3);
			i++;
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <C2, C3> char targetedForEach(char a1, IndexedRead<C2, aChar> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTriCharConsumer consumer) {
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = oiFunc3.applyAsChar(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <C2, I2, C3> char targetedIterate(char a1, SequentialRead<C2, I2, aChar> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTriCharConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size) {
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = oiFunc3.applyAsChar(source3, i);
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
	public static <C2, C3, I3> char targetedIterate(char a1, IndexedRead<C2, aChar> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTriCharConsumer consumer) {
		int size = ia2.size(source2);
		LOiToCharFunction<Object> oiFunc2 = (LOiToCharFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			char a2 = oiFunc2.applyAsChar(source2, i);
			char a3 = nextFunc3.applyAsChar(iterator3);
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
	public static <C2, I2, C3, I3> char targetedIterate(char a1, SequentialRead<C2, I2, aChar> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTriCharConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToCharFunction<Object> nextFunc2 = (LToCharFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			char a2 = nextFunc2.applyAsChar(iterator2);
			char a3 = nextFunc3.applyAsChar(iterator3);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

}
