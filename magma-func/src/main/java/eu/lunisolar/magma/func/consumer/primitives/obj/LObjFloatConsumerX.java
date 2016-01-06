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
 * Throwing functional interface (lambda) LObjFloatConsumerX for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 2): T a1,float a2
 *
 * Co-domain: none
 *
 * @see LObjFloatConsumer
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjFloatConsumerX<T, X extends Throwable> extends MetaConsumer, MetaInterface.Throwing<X> {

	String DESCRIPTION = "LObjFloatConsumerX: void doAccept(T a1,float a2) throws X";

	void doAccept(T a1, float a2) throws X;

	default LTuple.Void tupleAccept(LObjFloatPair<T> args) throws X {
		doAccept(args.first(), args.second());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoAccept(T a1, float a2) {
		try {
			this.doAccept(a1, a2);
		} catch (RuntimeException | Error e) { // NOSONAR
			throw e;
		} catch (Throwable e) { // NOSONAR
			throw new NestedException(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is even when they are undeclared checked ones. */
	default void shovingDoAccept(T a1, float a2) {
		((LObjFloatConsumerX<T, RuntimeException>) this).doAccept(a1, a2);
	}

	/** Function call that handles exceptions according to the instructions. */
	default <Y extends Throwable> void handlingDoAccept(T a1, float a2, HandlingInstructions<Throwable, Y> handling) throws Y {

		try {
			this.doAccept(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjFloatConsumerX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LActionX<X> captureObjFloatCons(T a1, float a2) {
		return () -> this.doAccept(a1, a2);
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LObjFloatConsumerX<T, X> accept1st(@Nonnull LConsumerX<T, X> func) {
		return (a1, a2) -> func.doAccept(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T, X extends Throwable> LObjFloatConsumerX<T, X> accept2nd(@Nonnull LFloatConsumerX<X> func) {
		return (a1, a2) -> func.doAccept(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LObjFloatConsumerX<T, X> lX(final @Nonnull LObjFloatConsumerX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> LObjFloatConsumerX<T, X> lX(@Nonnull Class<X> xClass, final @Nonnull LObjFloatConsumerX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> V1<T, X> lX1(final @Nonnull V1<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, X extends Throwable> V1<T, X> lX1(@Nonnull Class<X> xClass, final @Nonnull V1<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T, X extends Throwable> void call(T a1, float a2, final @Nonnull LObjFloatConsumerX<T, X> lambda) throws X {
		Null.nonNullArg(lambda, "lambda");
		lambda.doAccept(a1, a2);
	}

	static <T, X extends Throwable> void shoving(T a1, float a2, final @Nonnull LObjFloatConsumerX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.shovingDoAccept(a1, a2);
	}

	static <T, X extends Throwable> void nesting(T a1, float a2, final @Nonnull LObjFloatConsumerX<T, X> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.nestingDoAccept(a1, a2);
	}

	static <T, X extends Throwable, Y extends Throwable> void handling(T a1, float a2, final HandlingInstructions<Throwable, Y> handling, final @Nonnull LObjFloatConsumerX<T, X> lambda) throws Y {
		Null.nonNullArg(lambda, "lambda");
		lambda.handlingDoAccept(a1, a2, handling);
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing vs non-throwing) instance. */
	@Nonnull
	static <T, X extends Throwable> LObjFloatConsumerX<T, X> wrapX(final @Nonnull LObjFloatConsumer<T> other) {
		return (LObjFloatConsumerX) other;
	}

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static <T, X extends Throwable> LObjFloatConsumerX<T, X> safe() {
		return Function4U::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T, X extends Throwable, Y extends Throwable> LSupplierX<LObjFloatConsumerX<T, X>, Y> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T, X extends Throwable> LObjFloatConsumerX<T, X> safe(final @Nullable LObjFloatConsumerX<T, X> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T, X extends Throwable, Y extends Throwable> LSupplierX<LObjFloatConsumerX<T, X>, Y> safeSupplier(final @Nullable LSupplierX<LObjFloatConsumerX<T, X>, Y> supplier) {
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
	default <V1> LObjFloatConsumerX<V1, X> objFloatConsComposeFloat(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LFloatUnaryOperatorX<X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (final V1 v1, final float v2) -> this.doAccept(before1.doApply(v1), before2.doApplyAsFloat(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiConsumerX<V1, V2, X> objFloatConsCompose(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1, @Nonnull final LToFloatFunctionX<? super V2, X> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (V1 v1, V2 v2) -> this.doAccept(before1.doApply(v1), before2.doApplyAsFloat(v2));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two consumers together in a order. */
	@Nonnull
	default LObjFloatConsumerX<T, X> andThen(@Nonnull LObjFloatConsumerX<? super T, X> after) {
		Null.nonNullArg(after, "after");
		return (T a1, float a2) -> {
			this.doAccept(a1, a2);
			after.doAccept(a1, a2);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjFloatConsumer<T> nestingObjFloatCons() {
		return this::nestingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjFloatConsumerX<T, RuntimeException> nestingObjFloatConsX() {
		return this::nestingDoAccept;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjFloatConsumer<T> shovingObjFloatCons() {
		return this::shovingDoAccept;
	}

	/** Converts to throwing variant (RuntimeException) that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjFloatConsumerX<T, RuntimeException> shovingObjFloatConsX() {
		return this::shovingDoAccept;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default LObjFloatConsumer<T> handleObjFloatCons(@Nonnull HandlingInstructions<Throwable, RuntimeException> handling) {
		return (T a1, float a2) -> this.handlingDoAccept(a1, a2, handling);
	}

	/** Converts to function that handles exceptions according to the instructions. */
	@Nonnull
	default <Y extends Throwable> LObjFloatConsumerX<T, Y> handleObjFloatConsX(@Nonnull HandlingInstructions<Throwable, Y> handling) {
		return (T a1, float a2) -> this.handlingDoAccept(a1, a2, handling);
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjFloatConsumerX for method references. */
	@FunctionalInterface
	interface V1<T, X extends Throwable> extends LObjFloatConsumerX<T, X> {

		void apply(float a2, T a1) throws X;

		@Override
		default void doAccept(T a1, float a2) throws X {
			this.apply(a2, a1);
		}
	}

	// </editor-fold>

}
