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

package eu.lunisolar.magma.func.consumer.primitives;

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
 * Throwing functional interface (lambda) LCharConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 1): char a
 *
 * Co-domain: none
 *
 * @see LCharConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharConsumerX<X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	String DESCRIPTION = "LCharConsumerX: void doAccept(char a) throws X";

	void doAccept(char a) throws X;

	default LTuple.Void tupleAccept(LCharSingle args) throws X {
		doAccept(args.value());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(char a) {
		try {
			this.doAccept(a);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoAccept(char a) {
		((LCharConsumerX<RuntimeException>) this).doAccept(a);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> void handlingDoAccept(char a, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> captureCharCons(char a) {
		return () -> this.doAccept(a);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LCharConsumerX<X> lX(final @Nonnull LCharConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <X extends Throwable> LCharConsumerX<X> lX(@Nonnull Class<X> xClass, final @Nonnull LCharConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	static <X extends Throwable> void call(char a, final @Nonnull LCharConsumerX<X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a);
	}

	static <X extends Throwable> void shoving(char a, final @Nonnull LCharConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.shovingDoAccept(a);
	}

	static <X extends Throwable> void nesting(char a, final @Nonnull LCharConsumerX<X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.nestingDoAccept(a);
	}

	static <X extends Throwable, Y extends Throwable> void handling(char a, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LCharConsumerX<X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		lambda.handlingDoAccept(a, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <X extends Throwable> LCharConsumerX<X> wrapX(final @Nonnull LCharConsumer other) {
		return (LCharConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <X extends Throwable> LCharConsumerX<X> safe() {
		return Function4U::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LCharConsumerX<X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <X extends Throwable> LCharConsumerX<X> safe(final @Nullable LCharConsumerX<X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <X extends Throwable, Y extends Throwable> LSupplierX<LCharConsumerX<X>, Y> safeSupplier(final @Nullable LSupplierX<LCharConsumerX<X>, Y> supplier) {
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
	default LCharConsumerX<X> charConsComposeChar(@Nonnull final LCharUnaryOperatorX<X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doAccept(before.doApplyAsChar(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LConsumerX<V, X> charConsCompose(@Nonnull final LToCharFunctionX<? super V, X> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doAccept(before.doApplyAsChar(v));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LCharConsumerX<X> together in a order. */
	@Nonnull
	default LCharConsumerX<X> andThen(@Nonnull LCharConsumerX<X> after) {
		Null.nonNullArg(after, "after");
		return a -> {
			this.doAccept(a);
			after.doAccept(a);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharConsumer nestingCharCons() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LCharConsumerX<RuntimeException> nestingCharConsX() {
		return this::nestingDoAccept;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharConsumer shovingCharCons() {
		return this::shovingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharConsumerX<RuntimeException> shovingCharConsX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LCharConsumer handleCharCons(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> this.handlingDoAccept(a, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LCharConsumerX<Y> handleCharConsX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return a -> this.handlingDoAccept(a, handling);
	}

	// </editor-fold>

}
