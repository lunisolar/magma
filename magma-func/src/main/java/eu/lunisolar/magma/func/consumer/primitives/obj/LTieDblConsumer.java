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
 * Non-throwing functional interface (lambda) LTieDblConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T a1,int a2,double a3
 *
 * Co-domain: none
 *
 * Special case of consumer that corresponds to expressions like     (list, index, element) -> List::set    or  (list, index, element) -> Array::set  
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieDblConsumer<T> extends MetaConsumer, MetaInterface.NonThrowing, TieConsumer<T, aDouble> {

	String DESCRIPTION = "LTieDblConsumer: void doAccept(T a1,int a2,double a3)";

	// void doAccept(T a1,int a2,double a3) ;
	default void doAccept(T a1, int a2, double a3) {
		// nestingDoAccept(a1,a2,a3);
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doAccept(T a1,int a2,double a3)
	 */
	void doAcceptX(T a1, int a2, double a3) throws Throwable;

	default LTuple.Void tupleAccept(LObjIntDblTriple<T> args) {
		doAccept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoAccept(T a1, int a2, double a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoAccept(T a1, int a2, double a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoAccept(T a1, int a2, double a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoAcceptThen(T a1, int a2, double a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T a1, int a2, double a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoAccept(T a1, int a2, double a3) {
		try {
			this.doAcceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> void handlingDoAccept(T a1, int a2, double a3, LTieDblConsumer<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoAccept(a1, a2, a3, handling);
	}

	static <T> void tryDoAccept(T a1, int a2, double a3, LTieDblConsumer<T> func) {
		tryDoAccept(a1, a2, a3, func, null);
	}

	static <T> void tryDoAccept(T a1, int a2, double a3, LTieDblConsumer<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T> void tryDoAccept(T a1, int a2, double a3, LTieDblConsumer<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoAccept(a1, a2, a3, exceptionFactory);
	}

	static <T> void tryDoAcceptThen(T a1, int a2, double a3, LTieDblConsumer<T> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoAcceptThen(a1, a2, a3, handler);
	}

	default void failSafeDoAccept(T a1, int a2, double a3, @Nonnull LTieDblConsumer<T> failSafe) {
		try {
			doAccept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doAccept(a1, a2, a3);
		}
	}

	static <T> void failSafeDoAccept(T a1, int a2, double a3, LTieDblConsumer<T> func, @Nonnull LTieDblConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doAccept(a1, a2, a3);
		} else {
			func.failSafeDoAccept(a1, a2, a3, failSafe);
		}
	}

	static <T> LTieDblConsumer<T> failSafeTieDblCons(LTieDblConsumer<T> func, @Nonnull LTieDblConsumer<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieDblConsumer.DESCRIPTION;
	}

	public default LTieDblFunction<T> toTieFunction() {
		return (t, i, e) -> {
			this.doAccept(t, i, e);
			return 1;
		};
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, double a3, LTieDblConsumer<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.doAccept(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.doAccept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, double a3, LTieDblConsumer<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.doAccept(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.doAccept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, double a3, LTieDblConsumer<T> func) {
		fromTill(0, max_a2, a1, a3, func);
	}

	/**  */
	public static <T> LTieDblConsumer<T> uncurryTieDblCons(LFunction<T, LIntFunction<LDblConsumer>> func) {
		return (T a1, int a2, double a3) -> func.doApply(a1).doApply(a2).doAccept(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureTieDblCons(T a1, int a2, double a3) {
		return () -> this.doAccept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTieDblConsumer<T> accept1st(@Nonnull LConsumer<T> func) {
		return (a1, a2, a3) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTieDblConsumer<T> accept2nd(@Nonnull LIntConsumer func) {
		return (a1, a2, a3) -> func.doAccept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTieDblConsumer<T> accept3rd(@Nonnull LDblConsumer func) {
		return (a1, a2, a3) -> func.doAccept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieDblConsumer<T> tieDblCons(final @Nonnull LTieDblConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LTieDblConsumer<T> recursive(final @Nonnull LFunction<LTieDblConsumer<T>, LTieDblConsumer<T>> selfLambda) {
		final LTieDblConsumerSingle<T> single = new LTieDblConsumerSingle();
		LTieDblConsumer<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LTieDblConsumerSingle<T> implements LSingle<LTieDblConsumer<T>>, LTieDblConsumer<T> {
		private LTieDblConsumer<T> target = null;

		@Override
		public void doAcceptX(T a1, int a2, double a3) throws Throwable {
			target.doAcceptX(a1, a2, a3);
		}

		@Override
		public LTieDblConsumer<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LTieDblConsumer<T> tieDblConsThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LTieDblConsumer<T> tieDblConsThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjDblIntCons<T> objDblIntCons(final @Nonnull LObjDblIntCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjDblCons<T> intObjDblCons(final @Nonnull LIntObjDblCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntDblObjCons<T> intDblObjCons(final @Nonnull LIntDblObjCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LDblObjIntCons<T> dblObjIntCons(final @Nonnull LDblObjIntCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LDblIntObjCons<T> dblIntObjCons(final @Nonnull LDblIntObjCons<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> void call(T a1, int a2, double a3, final @Nonnull LTieDblConsumer<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T> LTieDblConsumer<T> safe() {
		return LTieDblConsumer::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieDblConsumer<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LTieDblConsumer<T> safe(final @Nullable LTieDblConsumer<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieDblConsumer<T>> safeSupplier(final @Nullable LSupplier<LTieDblConsumer<T>> supplier) {
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
	default <V1> LTieDblConsumer<V1> tieDblConsComposeIntDbl(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LDblUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApplyAsDbl(v3));
	}

	public static <V1, T> LTieDblConsumer<V1> composedIntDbl(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LDblUnaryOperator before3, LTieDblConsumer<T> after) {
		return after.tieDblConsComposeIntDbl(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> tieDblConsCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToDblFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApplyAsDbl(v3));
	}

	public static <V1, V2, V3, T> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToDblFunction<? super V3> before3, LTieDblConsumer<T> after) {
		return after.tieDblConsCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTieDblConsumer<T> together in a order. */
	@Nonnull
	default LTieDblConsumer<T> andThen(@Nonnull LTieDblConsumer<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			this.doAccept(a1, a2, a3);
			after.doAccept(a1, a2, a3);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTieDblConsumer<T> nestingTieDblCons() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTieDblConsumer<T> shovingTieDblCons() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTieDblConsumer for method references. */
	@FunctionalInterface
	interface LObjDblIntCons<T> extends LTieDblConsumer<T> {

		void doAcceptObjDblInt(T a1, double a3, int a2);

		@Override
		default void doAcceptX(T a1, int a2, double a3) {
			this.doAcceptObjDblInt(a1, a3, a2);
		}
	}

	/** Permutation of LTieDblConsumer for method references. */
	@FunctionalInterface
	interface LIntObjDblCons<T> extends LTieDblConsumer<T> {

		void doAcceptIntObjDbl(int a2, T a1, double a3);

		@Override
		default void doAcceptX(T a1, int a2, double a3) {
			this.doAcceptIntObjDbl(a2, a1, a3);
		}
	}

	/** Permutation of LTieDblConsumer for method references. */
	@FunctionalInterface
	interface LIntDblObjCons<T> extends LTieDblConsumer<T> {

		void doAcceptIntDblObj(int a2, double a3, T a1);

		@Override
		default void doAcceptX(T a1, int a2, double a3) {
			this.doAcceptIntDblObj(a2, a3, a1);
		}
	}

	/** Permutation of LTieDblConsumer for method references. */
	@FunctionalInterface
	interface LDblObjIntCons<T> extends LTieDblConsumer<T> {

		void doAcceptDblObjInt(double a3, T a1, int a2);

		@Override
		default void doAcceptX(T a1, int a2, double a3) {
			this.doAcceptDblObjInt(a3, a1, a2);
		}
	}

	/** Permutation of LTieDblConsumer for method references. */
	@FunctionalInterface
	interface LDblIntObjCons<T> extends LTieDblConsumer<T> {

		void doAcceptDblIntObj(double a3, int a2, T a1);

		@Override
		default void doAcceptX(T a1, int a2, double a3) {
			this.doAcceptDblIntObj(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTieDblConsumer) */
	public static <T> void doNothing(T a1, int a2, double a3) {
		// NOSONAR
	}

	/** Does nothing (LTieDblConsumer.LObjDblIntCons) */
	public static <T> void doNothing(T a1, double a3, int a2) {
		// NOSONAR
	}

	/** Does nothing (LTieDblConsumer.LIntObjDblCons) */
	public static <T> void doNothing(int a2, T a1, double a3) {
		// NOSONAR
	}

	/** Does nothing (LTieDblConsumer.LIntDblObjCons) */
	public static <T> void doNothing(int a2, double a3, T a1) {
		// NOSONAR
	}

	/** Does nothing (LTieDblConsumer.LDblObjIntCons) */
	public static <T> void doNothing(double a3, T a1, int a2) {
		// NOSONAR
	}

	/** Does nothing (LTieDblConsumer.LDblIntObjCons) */
	public static <T> void doNothing(double a3, int a2, T a1) {
		// NOSONAR
	}

	// JUST_CONSUME: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=double a3, type=IA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <C1, C2, C3, T> int forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			double a3 = oiFunc3.doApplyAsDbl(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=double a3, type=IA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <C1, I1, C2, C3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			double a3 = oiFunc3.doApplyAsDbl(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=double a3, type=IA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <C1, C2, I2, C3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			double a3 = oiFunc3.doApplyAsDbl(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=double a3, type=IA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			double a3 = oiFunc3.doApplyAsDbl(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=double a3, type=SA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <C1, C2, C3, I3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			double a3 = nextFunc3.doApplyAsDbl(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=double a3, type=SA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <C1, I1, C2, C3, I3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			double a3 = nextFunc3.doApplyAsDbl(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=double a3, type=SA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <C1, C2, I2, C3, I3, T> int iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			double a3 = nextFunc3.doApplyAsDbl(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_CONSUME: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=double a3, type=SA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <C1, I1, C2, I2, C3, I3, T> int iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			double a3 = nextFunc3.doApplyAsDbl(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return i;

	}

	// JUST_WITH_INDEX: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=double a3, type=IA}, SourcePurpose{arg=LTieDblConsumer<? super T> consumer,
	// type=CONST}]
	public static <C1, C3, T> int indexedForEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int a2 = 0;
		for (; a2 < size; a2++) {
			T a1 = oiFunc1.doApply(source1, a2);
			double a3 = oiFunc3.doApplyAsDbl(source3, a2);
			consumer.doAccept(a1, a2, a3);
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=double a3, type=IA}, SourcePurpose{arg=LTieDblConsumer<? super T> consumer,
	// type=CONST}]
	public static <C1, I1, C3, T> int indexedIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia3.size(source3);
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int a2 = 0;
		while (testFunc1.doTest(iterator1) && a2 < size) {
			T a1 = nextFunc1.doApply(iterator1);
			double a3 = oiFunc3.doApplyAsDbl(source3, a2);
			consumer.doAccept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=double a3, type=SA}, SourcePurpose{arg=LTieDblConsumer<? super T> consumer,
	// type=CONST}]
	public static <C1, C3, I3, T> int indexedIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.getter();
		int a2 = 0;
		while (a2 < size && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, a2);
			double a3 = nextFunc3.doApplyAsDbl(iterator3);
			consumer.doAccept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	// JUST_WITH_INDEX: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=double a3, type=SA}, SourcePurpose{arg=LTieDblConsumer<? super T> consumer,
	// type=CONST}]
	public static <C1, I1, C3, I3, T> int indexedIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.getter();
		int a2 = 0;
		while (testFunc1.doTest(iterator1) && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			double a3 = nextFunc3.doApplyAsDbl(iterator3);
			consumer.doAccept(a1, a2, a3);
			a2++;
		}
		return a2;

	}

	// CONSUME_WITH_TARGET: FOR, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=double a3, type=IA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <T, C2, C3> T targetedForEach(T a1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			double a3 = oiFunc3.doApplyAsDbl(source3, i);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=double a3, type=IA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <T, C2, I2, C3> T targetedIterate(T a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		while (testFunc2.doTest(iterator2) && i < size) {
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			double a3 = oiFunc3.doApplyAsDbl(source3, i);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=double a3, type=SA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <T, C2, C3, I3> T targetedIterate(T a1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			double a3 = nextFunc3.doApplyAsDbl(iterator3);
			consumer.doAccept(a1, a2, a3);
			i++;
		}
		return a1;

	}

	// CONSUME_WITH_TARGET: WHILE, [SourcePurpose{arg=T a1, type=CONST}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=double a3, type=SA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <T, C2, I2, C3, I3> T targetedIterate(T a1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.getter();
		while (testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			double a3 = nextFunc3.doApplyAsDbl(iterator3);
			consumer.doAccept(a1, a2, a3);
		}
		return a1;

	}

	// TIE_CONSUMER_GEN: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST},
	// SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=TIE_SOURCE}, SourcePurpose{arg=double a3, type=TIE_GEN_SUPPLIER}]
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, aDouble> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiToDblFunction<SRC>) srcAcc3, this);

	}

	// TARGETED_INDEXED_FOR_EACH: FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=IA}, SourcePurpose{arg=LTieDblConsumer<? super
	// T> consumer, type=CONST}]
	public static <T, C3> T tiForEach(T trg1, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	// TARGETED_INDEXED_FOR_EACH_NEW: FOR, [SourcePurpose{arg=T trg1, type=SIZE_FACTORY}, SourcePurpose{arg=double a3, type=IA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <T, C3> T ntiForEach(LIntFunction<T> trgFactory1, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia3.size(source3);
		T trg1 = trgFactory1.doApply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	// TIE_CONSUMER_SHORT: FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=IA}, SourcePurpose{arg=LTieDblConsumer<? super T>
	// consumer, type=CONST}]
	public static <T, C3> int tieForEach(T trg1, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblConsumer<? super T> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	// TIE_CONSUMER: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST},
	// SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=TIE_SOURCE}, SourcePurpose{arg=double a3, type=TIE_SUPPLIER},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <T, SRC> int tieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, LOiToDblFunction<SRC> srcAcc3, LTieDblConsumer<? super T> consumer) {
		for (int sIndex = sStart, tIndex = tStart; sIndex < sEnd; sIndex++, tIndex++) {
			double a3 = srcAcc3.doApplyAsDbl(src3, sIndex);
			consumer.doAccept(trg1, tIndex, a3);
		}
		return sEnd - sStart;

	}

	// TIE_CONSUMER2: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST},
	// SourcePurpose{arg=double a3, type=TIE_SOURCE}, SourcePurpose{arg=double a3, type=TE_PREDICATE}, SourcePurpose{arg=double a3, type=TE_SUPPLIER},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <T, SRC> int tieForEach(int sStart, int tStart, T trg1, SRC src3, LPredicate<SRC> srcTest3, LToDblFunction<SRC> srcAcc3, LTieDblConsumer<? super T> consumer) {
		int tIndex = tStart;
		for (; srcTest3.doTest(src3); tIndex++) {
			double a3 = srcAcc3.doApplyAsDbl(src3);
			consumer.doAccept(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	// TIE_CONSUMER2_SHORT: WHILE, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=SA}, SourcePurpose{arg=LTieDblConsumer<? super T>
	// consumer, type=CONST}]
	public static <T, C3, I3> int tieIterate(T trg1, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.doApply(source3), sa3.tester(), sa3.getter(), consumer);
	}

	// TARGETED_INDEXED_ITERATE: WHILE, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=SA}, SourcePurpose{arg=LTieDblConsumer<? super
	// T> consumer, type=CONST}]
	public static <T, C3, I3> T tiIterate(T trg1, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	// TARGETED_INDEXED_ITERATE_NEW: WHILE, [SourcePurpose{arg=T trg1, type=SUPPLIER}, SourcePurpose{arg=double a3, type=SA},
	// SourcePurpose{arg=LTieDblConsumer<? super T> consumer, type=CONST}]
	public static <T, C3, I3> T ntiIterate(LSupplier<T> source1, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblConsumer<? super T> consumer) {
		T trg1 = source1.doGet();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

}
