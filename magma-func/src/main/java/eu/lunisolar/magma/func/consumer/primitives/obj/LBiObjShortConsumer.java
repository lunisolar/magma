/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

/**
 * Non-throwing functional interface (lambda) LBiObjShortConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 t1,T2 t2, short s
 *
 * Co-domain: none
 *
 * @see LBiObjShortConsumerX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjShortConsumer<T1, T2> extends LBiObjShortConsumerX<T1, T2, RuntimeException>, MetaConsumer, MetaInterface.NonThrowing {

	static final String DESCRIPTION = "LBiObjShortConsumer: void doAccept(T1 t1,T2 t2, short s)";

	void doAccept(T1 t1, T2 t2, short s);

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the otheres as is. */
	default void nestingDoAccept(T1 t1, T2 t2, short s) {
		this.doAccept(t1, t2, s);
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoAccept(T1 t1, T2 t2, short s) {
		this.doAccept(t1, t2, s);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjShortConsumer.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LAction captureBiObjShortCons(T1 t1, T2 t2, short s) {
		return () -> this.doAccept(t1, t2, s);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjShortConsumer<T1, T2> accept1st(@Nonnull LConsumer<T1> func) {
		return (t1, t2, s) -> func.doAccept(t1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjShortConsumer<T1, T2> accept2nd(@Nonnull LConsumer<T2> func) {
		return (t1, t2, s) -> func.doAccept(t2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjShortConsumer<T1, T2> accept3rd(@Nonnull LShortConsumer func) {
		return (t1, t2, s) -> func.doAccept(s);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjShortConsumer<T1, T2> l(final @Nonnull LBiObjShortConsumer<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, X extends Throwable> LBiObjShortConsumer<T1, T2> wrap(final @Nonnull LBiObjShortConsumerX<T1, T2, X> other) {
		return other::nestingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiObjShortConsumer<V1, V2> biObjShortConsComposeShort(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LShortUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final V1 v1, final V2 v2, final short v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsShort(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> biObjShortConsCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToShortFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (V1 v1, V2 v2, V3 v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsShort(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LBiObjShortConsumer<T1, T2> andThen(@Nonnull LBiObjShortConsumer<? super T1, ? super T2> after) {
		Null.nonNullArg(after, "after");
		return (T1 t1, T2 t2, short s) -> {
			this.doAccept(t1, t2, s);
			after.doAccept(t1, t2, s);
		};
	}

	// </editor-fold> // <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjShortConsumer<T1, T2> nestingBiObjShortCons() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjShortConsumerX<T1, T2, RuntimeException> nestingBiObjShortConsX() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjShortConsumer<T1, T2> shovingBiObjShortCons() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjShortConsumerX<T1, T2, RuntimeException> shovingBiObjShortConsX() {
		return this;
	}

	// </editor-fold>

}
