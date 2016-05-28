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
 * Non-throwing functional interface (lambda) LBiObjFloatConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 a1,T2 a2,float a3
 *
 * Co-domain: none
 *
 * @see LBiObjFloatConsumerX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjFloatConsumer<T1, T2> extends LBiObjFloatConsumerX<T1, T2, RuntimeException>, MetaConsumer, MetaInterface.NonThrowing {

	String DESCRIPTION = "LBiObjFloatConsumer: void doAccept(T1 a1,T2 a2,float a3)";

	void doAccept(T1 a1, T2 a2, float a3);

	default LTuple.Void tupleAccept(LBiObjFloatTriple<T1, T2> args) {
		doAccept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T1 a1, T2 a2, float a3) {
		this.doAccept(a1, a2, a3);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoAccept(T1 a1, T2 a2, float a3) {
		this.doAccept(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjFloatConsumer.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureBiObjFloatCons(T1 a1, T2 a2, float a3) {
		return () -> this.doAccept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjFloatConsumer<T1, T2> accept1st(@Nonnull LConsumer<T1> func) {
		return (a1, a2, a3) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjFloatConsumer<T1, T2> accept2nd(@Nonnull LConsumer<T2> func) {
		return (a1, a2, a3) -> func.doAccept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjFloatConsumer<T1, T2> accept3rd(@Nonnull LFloatConsumer func) {
		return (a1, a2, a3) -> func.doAccept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjFloatConsumer<T1, T2> l(final @Nonnull LBiObjFloatConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> V1<T1, T2> l1(final @Nonnull V1<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> V2<T2, T1> l2(final @Nonnull V2<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> V3<T2, T1> l3(final @Nonnull V3<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> V4<T1, T2> l4(final @Nonnull V4<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> V5<T2, T1> l5(final @Nonnull V5<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> void call(T1 a1, T2 a2, float a3, final @Nonnull LBiObjFloatConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjFloatConsumer<T1, T2> wrap(final @Nonnull LBiObjFloatConsumerX<T1, T2, X> other) {
		return other::nestingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2> LBiObjFloatConsumer<T1, T2> safe() {
		return Function4U::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjFloatConsumer<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LBiObjFloatConsumer<T1, T2> safe(final @Nullable LBiObjFloatConsumer<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjFloatConsumer<T1, T2>> safeSupplier(final @Nullable LSupplier<LBiObjFloatConsumer<T1, T2>> supplier) {
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
	default <V1, V2> LBiObjFloatConsumer<V1, V2> biObjFloatConsComposeFloat(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFloatUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsFloat(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> biObjFloatConsCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToFloatFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsFloat(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LBiObjFloatConsumer<T1,T2> together in a order. */
	@Nonnull
	default LBiObjFloatConsumer<T1, T2> andThen(@Nonnull LBiObjFloatConsumer<? super T1, ? super T2> after) {
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
	default LBiObjFloatConsumer<T1, T2> nestingBiObjFloatCons() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjFloatConsumerX<T1, T2, RuntimeException> nestingBiObjFloatConsX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjFloatConsumer<T1, T2> shovingBiObjFloatCons() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjFloatConsumerX<T1, T2, RuntimeException> shovingBiObjFloatConsX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjFloatConsumer for method references. */
	@FunctionalInterface
	interface V1<T1, T2> extends LBiObjFloatConsumer<T1, T2> {

		void doAcceptV1(T1 a1, float a3, T2 a2);

		@Override
		default void doAccept(T1 a1, T2 a2, float a3) {
			this.doAcceptV1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjFloatConsumer for method references. */
	@FunctionalInterface
	interface V2<T2, T1> extends LBiObjFloatConsumer<T1, T2> {

		void doAcceptV2(T2 a2, T1 a1, float a3);

		@Override
		default void doAccept(T1 a1, T2 a2, float a3) {
			this.doAcceptV2(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjFloatConsumer for method references. */
	@FunctionalInterface
	interface V3<T2, T1> extends LBiObjFloatConsumer<T1, T2> {

		void doAcceptV3(T2 a2, float a3, T1 a1);

		@Override
		default void doAccept(T1 a1, T2 a2, float a3) {
			this.doAcceptV3(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjFloatConsumer for method references. */
	@FunctionalInterface
	interface V4<T1, T2> extends LBiObjFloatConsumer<T1, T2> {

		void doAcceptV4(float a3, T1 a1, T2 a2);

		@Override
		default void doAccept(T1 a1, T2 a2, float a3) {
			this.doAcceptV4(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjFloatConsumer for method references. */
	@FunctionalInterface
	interface V5<T2, T1> extends LBiObjFloatConsumer<T1, T2> {

		void doAcceptV5(float a3, T2 a2, T1 a1);

		@Override
		default void doAccept(T1 a1, T2 a2, float a3) {
			this.doAcceptV5(a3, a2, a1);
		}
	}

	// </editor-fold>

}
