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
 * Non-throwing functional interface (lambda) LTriSrtConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): short a1,short a2,short a3
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriSrtConsumer extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain3<aShort, aShort, aShort> {

	String DESCRIPTION = "LTriSrtConsumer: void accept(short a1,short a2,short a3)";

	// void accept(short a1,short a2,short a3) ;
	default void accept(short a1, short a2, short a3) {
		// nestingAccept(a1,a2,a3);
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(short a1,short a2,short a3)
	 */
	void acceptX(short a1, short a2, short a3) throws Throwable;

	default LTuple.Void tupleAccept(LSrtTriple args) {
		accept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(short a1, short a2, short a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriSrtConsumer handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingAccept(a1, a2, a3, handling);
	}

	default void accept(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LTriSrtConsumer trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF, newMessage, messageParams);
	}

	default void accept(short a1, short a2, short a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LTriSrtConsumer trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> accept(a1, a2, a3, exF);
	}

	default void acceptThen(short a1, short a2, short a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LTriSrtConsumer tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3) -> acceptThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(short a1, short a2, short a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(short a1, short a2, short a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingAccept(short a1, short a2, short a3, LTriSrtConsumer func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, handling);
	}

	static void tryAccept(short a1, short a2, short a3, LTriSrtConsumer func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3);
	}

	static void tryAccept(short a1, short a2, short a3, LTriSrtConsumer func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF, newMessage, messageParams);
	}

	static void tryAccept(short a1, short a2, short a3, LTriSrtConsumer func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, exF);
	}

	static void tryAcceptThen(short a1, short a2, short a3, LTriSrtConsumer func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, handler);
	}

	default void failSafeAccept(short a1, short a2, short a3, @Nonnull LTriSrtConsumer failSafe) {
		try {
			accept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3);
		}
	}

	static void failSafeAccept(short a1, short a2, short a3, LTriSrtConsumer func, @Nonnull LTriSrtConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3);
		} else {
			func.failSafeAccept(a1, a2, a3, failSafe);
		}
	}

	static LTriSrtConsumer failSafe(LTriSrtConsumer func, @Nonnull LTriSrtConsumer failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriSrtConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a1, short a2, short a3, @Nonnull LTriSrtConsumer func) {
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
	public static void fromTill(int min_i, int max_i, short a1, short a2, short a3, @Nonnull LTriSrtConsumer func) {
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
	public static void times(int max_i, short a1, short a2, short a3, @Nonnull LTriSrtConsumer func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LBiSrtConsumer lShrink(@Nonnull LSrtBinaryOperator left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> accept(left.applyAsSrt(a2, a3), a2, a3);
	}

	public default LBiSrtConsumer lShrink_(short a1) {
		return (a2, a3) -> accept(a1, a2, a3);
	}

	public static LBiSrtConsumer lShrunken(@Nonnull LSrtBinaryOperator left, @Nonnull LTriSrtConsumer func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LBiSrtConsumer lShrunken_(short a1, @Nonnull LTriSrtConsumer func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LBiSrtConsumer rShrink(@Nonnull LSrtBinaryOperator right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> accept(a1, a2, right.applyAsSrt(a1, a2));
	}

	public default LBiSrtConsumer rShrink_(short a3) {
		return (a1, a2) -> accept(a1, a2, a3);
	}

	public static LBiSrtConsumer rShrunken(@Nonnull LSrtBinaryOperator right, @Nonnull LTriSrtConsumer func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LBiSrtConsumer rShrunken_(short a3, @Nonnull LTriSrtConsumer func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static LTriSrtConsumer uncurry(@Nonnull LSrtFunction<LSrtFunction<LSrtConsumer>> func) {
		Null.nonNullArg(func, "func");
		return (short a1, short a2, short a3) -> func.apply(a1).apply(a2).accept(a3);
	}

	/** Calls domain consumer before main function. */
	public default LTriSrtConsumer beforeDo(@Nonnull LTriSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a1, short a2, short a3) -> {
			before.accept(a1, a2, a3);
			accept(a1, a2, a3);
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LAction capture(short a1, short a2, short a3) {
		return () -> this.accept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LTriSrtConsumer accept1st(@Nonnull LSrtConsumer func) {
		return (a1, a2, a3) -> func.accept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LTriSrtConsumer accept2nd(@Nonnull LSrtConsumer func) {
		return (a1, a2, a3) -> func.accept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static LTriSrtConsumer accept3rd(@Nonnull LSrtConsumer func) {
		return (a1, a2, a3) -> func.accept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LTriSrtConsumer triSrtCons(final @Nonnull LTriSrtConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LTriSrtConsumer recursive(final @Nonnull LFunction<LTriSrtConsumer, LTriSrtConsumer> selfLambda) {
		final LTriSrtConsumerSingle single = new LTriSrtConsumerSingle();
		LTriSrtConsumer func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LTriSrtConsumerSingle implements LSingle<LTriSrtConsumer>, LTriSrtConsumer {
		private LTriSrtConsumer target = null;

		@Override
		public void acceptX(short a1, short a2, short a3) throws Throwable {
			target.acceptX(a1, a2, a3);
		}

		@Override
		public LTriSrtConsumer value() {
			return target;
		}
	}

	@Nonnull
	static LTriSrtConsumer triSrtConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LTriSrtConsumer triSrtConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static void call(short a1, short a2, short a3, final @Nonnull LTriSrtConsumer lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LTriSrtConsumer safe() {
		return LTriSrtConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LTriSrtConsumer> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LTriSrtConsumer safe(final @Nullable LTriSrtConsumer other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LTriSrtConsumer> safeSupplier(final @Nullable LSupplier<LTriSrtConsumer> supplier) {
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
	default LTriSrtConsumer compose(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, @Nonnull final LSrtUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.applyAsSrt(v1), before2.applyAsSrt(v2), before3.applyAsSrt(v3));
	}

	public static LTriSrtConsumer composed(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, @Nonnull final LSrtUnaryOperator before3, LTriSrtConsumer after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> triSrtConsCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2, @Nonnull final LToSrtFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.applyAsSrt(v1), before2.applyAsSrt(v2), before3.applyAsSrt(v3));
	}

	public static <V1, V2, V3> LTriConsumer<V1, V2, V3> composed(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2, @Nonnull final LToSrtFunction<? super V3> before3, LTriSrtConsumer after) {
		return after.triSrtConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTriSrtConsumer together in a order. */
	@Nonnull
	default LTriSrtConsumer andThen(@Nonnull LTriSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			this.accept(a1, a2, a3);
			after.accept(a1, a2, a3);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LTriSrtConsumer) */
	public static void doNothing(short a1, short a2, short a3) {
		// NOSONAR
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, C2, C3> int forEach(IndexedRead<C1, aShort> ia1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LTriSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			short a1 = oiFunc1.applyAsSrt(source1, i);
			short a2 = oiFunc2.applyAsSrt(source2, i);
			short a3 = oiFunc3.applyAsSrt(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return i;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns iterations count
	*/
	public static <C1, I1, C2, C3> int iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LTriSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			short a1 = nextFunc1.applyAsSrt(iterator1);
			short a2 = oiFunc2.applyAsSrt(source2, i);
			short a3 = oiFunc3.applyAsSrt(source3, i);
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
	public static <C1, C2, I2, C3> int iterate(IndexedRead<C1, aShort> ia1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LTriSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			short a1 = oiFunc1.applyAsSrt(source1, i);
			short a2 = nextFunc2.applyAsSrt(iterator2);
			short a3 = oiFunc3.applyAsSrt(source3, i);
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
	public static <C1, I1, C2, I2, C3> int iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LTriSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			short a1 = nextFunc1.applyAsSrt(iterator1);
			short a2 = nextFunc2.applyAsSrt(iterator2);
			short a3 = oiFunc3.applyAsSrt(source3, i);
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
	public static <C1, C2, C3, I3> int iterate(IndexedRead<C1, aShort> ia1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTriSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			short a1 = oiFunc1.applyAsSrt(source1, i);
			short a2 = oiFunc2.applyAsSrt(source2, i);
			short a3 = nextFunc3.applyAsSrt(iterator3);
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
	public static <C1, I1, C2, C3, I3> int iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTriSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			short a1 = nextFunc1.applyAsSrt(iterator1);
			short a2 = oiFunc2.applyAsSrt(source2, i);
			short a3 = nextFunc3.applyAsSrt(iterator3);
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
	public static <C1, C2, I2, C3, I3> int iterate(IndexedRead<C1, aShort> ia1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTriSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			short a1 = oiFunc1.applyAsSrt(source1, i);
			short a2 = nextFunc2.applyAsSrt(iterator2);
			short a3 = nextFunc3.applyAsSrt(iterator3);
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
	public static <C1, I1, C2, I2, C3, I3> int iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTriSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			short a1 = nextFunc1.applyAsSrt(iterator1);
			short a2 = nextFunc2.applyAsSrt(iterator2);
			short a3 = nextFunc3.applyAsSrt(iterator3);
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
	public static <C2, C3> short targetedForEach(short a1, IndexedRead<C2, aShort> ia2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LTriSrtConsumer consumer) {
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			short a2 = oiFunc2.applyAsSrt(source2, i);
			short a3 = oiFunc3.applyAsSrt(source3, i);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns 'target' object
	*/
	public static <C2, I2, C3> short targetedIterate(short a1, SequentialRead<C2, I2, aShort> sa2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LTriSrtConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		while (testFunc2.test(iterator2) && i < size) {
			short a2 = nextFunc2.applyAsSrt(iterator2);
			short a3 = oiFunc3.applyAsSrt(source3, i);
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
	public static <C2, C3, I3> short targetedIterate(short a1, IndexedRead<C2, aShort> ia2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTriSrtConsumer consumer) {
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			short a2 = oiFunc2.applyAsSrt(source2, i);
			short a3 = nextFunc3.applyAsSrt(iterator3);
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
	public static <C2, I2, C3, I3> short targetedIterate(short a1, SequentialRead<C2, I2, aShort> sa2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTriSrtConsumer consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		while (testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			short a2 = nextFunc2.applyAsSrt(iterator2);
			short a3 = nextFunc3.applyAsSrt(iterator3);
			consumer.accept(a1, a2, a3);
		}
		return a1;

	}

}
