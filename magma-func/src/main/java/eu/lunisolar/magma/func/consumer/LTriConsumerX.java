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

package eu.lunisolar.magma.func.consumer;

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

import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/**
 * Throwing functional interface (lambda) LTriConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 a1,T2 a2,T3 a3
 *
 * Co-domain: none
 *
 * @see LTriConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriConsumerX<T1, T2, T3, X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	String DESCRIPTION = "LTriConsumerX: void doAccept(T1 a1,T2 a2,T3 a3) throws X";

	void doAccept(T1 a1, T2 a2, T3 a3) throws X;

	default LTuple.Void tupleAccept(LTriple<T1, T2, T3> args) throws X {
		doAccept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T1 a1, T2 a2, T3 a3) {
		try {
			this.doAccept(a1, a2, a3);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoAccept(T1 a1, T2 a2, T3 a3) {
		((LTriConsumerX<T1, T2, T3, RuntimeException>) this).doAccept(a1, a2, a3);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> void handlingDoAccept(T1 a1, T2 a2, T3 a3, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> captureTriCons(T1 a1, T2 a2, T3 a3) {
		return () -> this.doAccept(a1, a2, a3);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> accept1st(@Nonnull LConsumerX<T1, X> func) {
		return (a1, a2, a3) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> accept2nd(@Nonnull LConsumerX<T2, X> func) {
		return (a1, a2, a3) -> func.doAccept(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> accept3rd(@Nonnull LConsumerX<T3, X> func) {
		return (a1, a2, a3) -> func.doAccept(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> lX(final @Nonnull LTriConsumerX<T1, T2, T3, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> lX(@Nonnull Class<X> xClass, final @Nonnull LTriConsumerX<T1, T2, T3, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <T1, T2, T3, X extends Throwable> void call(T1 a1, T2 a2, T3 a3, final @Nonnull LTriConsumerX<T1, T2, T3, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2, a3);
	}

	static <T1, T2, T3, X extends Throwable> void shoving(T1 a1, T2 a2, T3 a3, final @Nonnull LTriConsumerX<T1, T2, T3, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.shovingDoAccept(a1, a2, a3);
	}

	static <T1, T2, T3, X extends Throwable> void nesting(T1 a1, T2 a2, T3 a3, final @Nonnull LTriConsumerX<T1, T2, T3, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.nestingDoAccept(a1, a2, a3);
	}

	static <T1, T2, T3, X extends Throwable, Y extends Throwable> void handling(T1 a1, T2 a2, T3 a3, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LTriConsumerX<T1, T2, T3, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		lambda.handlingDoAccept(a1, a2, a3, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> wrapX(final @Nonnull LTriConsumer<T1, T2, T3> other) {
		return (LTriConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> safe() {
		return Function4U::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable, Y extends Throwable> LSupplierX<LTriConsumerX<T1, T2, T3, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable> LTriConsumerX<T1, T2, T3, X> safe(final @Nullable LTriConsumerX<T1, T2, T3, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, X extends Throwable, Y extends Throwable> LSupplierX<LTriConsumerX<T1, T2, T3, X>, Y> safeSupplier(final @Nullable LSupplierX<LTriConsumerX<T1, T2, T3, X>, Y> supplier) {
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
	default <V1, V2, V3> LTriConsumerX<V1, V2, V3, X> triConsCompose(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2,
			@Nonnull final LFunctionX<? super V3, ? extends T3, X> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (final V1 v1, final V2 v2, final V3 v3) -> this.doAccept(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LTriConsumerX<T1, T2, T3, X> andThen(@Nonnull LTriConsumerX<? super T1, ? super T2, ? super T3, X> after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, T3 a3) -> {
			this.doAccept(a1, a2, a3);
			after.doAccept(a1, a2, a3);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTriConsumer<T1, T2, T3> nestingTriCons() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LTriConsumerX<T1, T2, T3, RuntimeException> nestingTriConsX() {
		return this::nestingDoAccept;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriConsumer<T1, T2, T3> shovingTriCons() {
		return this::shovingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriConsumerX<T1, T2, T3, RuntimeException> shovingTriConsX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LTriConsumer<T1, T2, T3> handleTriCons(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T1 a1, T2 a2, T3 a3) -> this.handlingDoAccept(a1, a2, a3, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LTriConsumerX<T1, T2, T3, Y> handleTriConsX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T1 a1, T2 a2, T3 a3) -> this.handlingDoAccept(a1, a2, a3, handling);
	}

	// </editor-fold>

}
