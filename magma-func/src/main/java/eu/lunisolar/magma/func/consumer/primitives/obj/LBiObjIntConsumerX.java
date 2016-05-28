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
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.struct.tuple.*; // NOSONAR
import java.util.function.*; // NOSONAR

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
 * Throwing functional interface (lambda) LBiObjIntConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 a1,T2 a2,int a3
 *
 * Co-domain: none
 *
 * @see LBiObjIntConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjIntConsumerX<T1, T2, X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	String DESCRIPTION = "LBiObjIntConsumerX: void doAccept(T1 a1,T2 a2,int a3) throws X";

	void doAccept(T1 a1, T2 a2, int a3) throws X;

	default LTuple.Void tupleAccept(LBiObjIntTriple<T1, T2> args) throws X {
		doAccept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T1 a1, T2 a2, int a3) {
		try {
			this.doAccept(a1, a2, a3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoAccept(T1 a1, T2 a2, int a3) {
		((LBiObjIntConsumerX<T1, T2, RuntimeException>) this).doAccept(a1, a2, a3);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> void handlingDoAccept(T1 a1, T2 a2, int a3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjIntConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> captureBiObjIntCons(T1 a1, T2 a2, int a3) {
		return () -> this.doAccept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjIntConsumerX<T1, T2, X> accept1st(@Nonnull LConsumerX<T1, X> func) {
		return (a1, a2, a3) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjIntConsumerX<T1, T2, X> accept2nd(@Nonnull LConsumerX<T2, X> func) {
		return (a1, a2, a3) -> func.doAccept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjIntConsumerX<T1, T2, X> accept3rd(@Nonnull LIntConsumerX<X> func) {
		return (a1, a2, a3) -> func.doAccept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjIntConsumerX<T1, T2, X> lX(final @Nonnull LBiObjIntConsumerX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjIntConsumerX<T1, T2, X> lX(@Nonnull Class<X> xClass, final @Nonnull LBiObjIntConsumerX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> V1<T1, T2, X> lX1(final @Nonnull V1<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> V1<T1, T2, X> lX1(@Nonnull Class<X> xClass, final @Nonnull V1<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V2<T2, T1, X> lX2(final @Nonnull V2<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V2<T2, T1, X> lX2(@Nonnull Class<X> xClass, final @Nonnull V2<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V3<T2, T1, X> lX3(final @Nonnull V3<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V3<T2, T1, X> lX3(@Nonnull Class<X> xClass, final @Nonnull V3<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> V4<T1, T2, X> lX4(final @Nonnull V4<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, X extends Throwable> V4<T1, T2, X> lX4(@Nonnull Class<X> xClass, final @Nonnull V4<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V5<T2, T1, X> lX5(final @Nonnull V5<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, X extends Throwable> V5<T2, T1, X> lX5(@Nonnull Class<X> xClass, final @Nonnull V5<T2, T1, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, X extends Throwable> void call(T1 a1, T2 a2, int a3, final @Nonnull LBiObjIntConsumerX<T1, T2, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2, a3);
	}

	static <T1, T2, X extends Throwable> void shoving(T1 a1, T2 a2, int a3, final @Nonnull LBiObjIntConsumerX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.shovingDoAccept(a1, a2, a3);
	}

	static <T1, T2, X extends Throwable> void nesting(T1 a1, T2 a2, int a3, final @Nonnull LBiObjIntConsumerX<T1, T2, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.nestingDoAccept(a1, a2, a3);
	}

	static <T1, T2, X extends Throwable, Y extends Throwable> void handling(T1 a1, T2 a2, int a3, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LBiObjIntConsumerX<T1, T2, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		lambda.handlingDoAccept(a1, a2, a3, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjIntConsumerX<T1, T2, X> wrapX(final @Nonnull LBiObjIntConsumer<T1, T2> other) {
		return (LBiObjIntConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjIntConsumerX<T1, T2, X> safe() {
		return Function4U::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LBiObjIntConsumerX<T1, T2, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjIntConsumerX<T1, T2, X> safe(final @Nullable LBiObjIntConsumerX<T1, T2, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, X extends Throwable, Y extends Throwable> LSupplierX<LBiObjIntConsumerX<T1, T2, X>, Y> safeSupplier(final @Nullable LSupplierX<LBiObjIntConsumerX<T1, T2, X>, Y> supplier) {
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
	default <V1, V2> LBiObjIntConsumerX<V1, V2, X> biObjIntConsComposeInt(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LIntUnaryOperatorX<X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsInt(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumerX<V1, V2, V3, X> biObjIntConsCompose(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LToIntFunctionX<? super V3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsInt(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiObjIntConsumerX<T1,T2,X> together in a order. */
	@Nonnull
	default LBiObjIntConsumerX<T1, T2, X> andThen(@Nonnull LBiObjIntConsumerX<? super T1, ? super T2, X> after) {
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
	default LBiObjIntConsumer<T1, T2> nestingBiObjIntCons() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjIntConsumerX<T1, T2, RuntimeException> nestingBiObjIntConsX() {
		return this::nestingDoAccept;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjIntConsumer<T1, T2> shovingBiObjIntCons() {
		return this::shovingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjIntConsumerX<T1, T2, RuntimeException> shovingBiObjIntConsX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LBiObjIntConsumer<T1, T2> handleBiObjIntCons(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> this.handlingDoAccept(a1, a2, a3, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LBiObjIntConsumerX<T1, T2, Y> handleBiObjIntConsX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (a1, a2, a3) -> this.handlingDoAccept(a1, a2, a3, handling);
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjIntConsumerX for method references. */
	@FunctionalInterface
	interface V1<T1, T2, X extends Throwable> extends LBiObjIntConsumerX<T1, T2, X> {

		void doAcceptV1(T1 a1, int a3, T2 a2) throws X;

		@Override
		default void doAccept(T1 a1, T2 a2, int a3) throws X {
			this.doAcceptV1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjIntConsumerX for method references. */
	@FunctionalInterface
	interface V2<T2, T1, X extends Throwable> extends LBiObjIntConsumerX<T1, T2, X> {

		void doAcceptV2(T2 a2, T1 a1, int a3) throws X;

		@Override
		default void doAccept(T1 a1, T2 a2, int a3) throws X {
			this.doAcceptV2(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjIntConsumerX for method references. */
	@FunctionalInterface
	interface V3<T2, T1, X extends Throwable> extends LBiObjIntConsumerX<T1, T2, X> {

		void doAcceptV3(T2 a2, int a3, T1 a1) throws X;

		@Override
		default void doAccept(T1 a1, T2 a2, int a3) throws X {
			this.doAcceptV3(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjIntConsumerX for method references. */
	@FunctionalInterface
	interface V4<T1, T2, X extends Throwable> extends LBiObjIntConsumerX<T1, T2, X> {

		void doAcceptV4(int a3, T1 a1, T2 a2) throws X;

		@Override
		default void doAccept(T1 a1, T2 a2, int a3) throws X {
			this.doAcceptV4(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjIntConsumerX for method references. */
	@FunctionalInterface
	interface V5<T2, T1, X extends Throwable> extends LBiObjIntConsumerX<T1, T2, X> {

		void doAcceptV5(int a3, T2 a2, T1 a1) throws X;

		@Override
		default void doAccept(T1 a1, T2 a2, int a3) throws X {
			this.doAcceptV5(a3, a2, a1);
		}
	}

	// </editor-fold>

}
