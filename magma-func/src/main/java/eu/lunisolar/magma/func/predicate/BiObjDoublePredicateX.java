/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.predicate;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.domains.*; // NOSONAR
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
 * Throwing functional interface (lambda) BiObjDoublePredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 t1,T2 t2, double d
 *
 * Co-domain: none
 *
 * @see BiObjDoublePredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface BiObjDoublePredicateX<T1, T2, X extends Exception> extends MetaPredicate, PrimitiveCodomain<BiObjDoublePredicateX<T1, T2, X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "BiObjDoublePredicateX: boolean test(T1 t1,T2 t2, double d) throws X";

	public boolean test(T1 t1, T2 t2, double d) throws X;

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean applyAsBoolean(T1 t1, T2 t2, double d) throws X {
		return test(t1, t2, d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return BiObjDoublePredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default BooleanSupplierX<X> capture(T1 t1, T2 t2, double d) {
		return () -> this.test(t1, t2, d);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNull(T1 t1, T2 t2, double d) throws X {
		return test(t1, t2, d);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Exception> BiObjDoublePredicateX<T1, T2, X> lX(final @Nonnull BiObjDoublePredicateX<T1, T2, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Exception> BiObjDoublePredicateX<T1, T2, X> wrapX(final @Nonnull BiObjDoublePredicate<T1, T2> other) {
		return other::test;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default BiObjDoublePredicateX<T1, T2, X> negate() {
		return (T1 t1, T2 t2, double d) -> !test(t1, t2, d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default BiObjDoublePredicateX<T1, T2, X> and(@Nonnull BiObjDoublePredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2, double d) -> test(t1, t2, d) && other.test(t1, t2, d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default BiObjDoublePredicateX<T1, T2, X> or(@Nonnull BiObjDoublePredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2, double d) -> test(t1, t2, d) || other.test(t1, t2, d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default BiObjDoublePredicateX<T1, T2, X> xor(@Nonnull BiObjDoublePredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2, double d) -> test(t1, t2, d) ^ other.test(t1, t2, d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <T1, T2, X extends Exception> BiObjDoublePredicateX<T1, T2, X> isEqual(final T1 v1, final T2 v2, final double v3) {
		return (t1, t2, d) -> (t1 == null ? v1 == null : t1.equals(v1)) && (t2 == null ? v2 == null : t2.equals(v2)) && (d == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> BiObjDoublePredicateX<V1, V2, X> fromDouble(@Nonnull final FunctionX<? super V1, ? extends T1, X> before1, @Nonnull final FunctionX<? super V2, ? extends T2, X> before2, @Nonnull final DoubleUnaryOperatorX<X> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (final V1 v1, final V2 v2, final double v3) -> this.test(before1.apply(v1), before2.apply(v2), before3.applyAsDouble(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> TriPredicateX<V1, V2, V3, X> from(@Nonnull final FunctionX<? super V1, ? extends T1, X> before1, @Nonnull final FunctionX<? super V2, ? extends T2, X> before2, @Nonnull final ToDoubleFunctionX<? super V3, X> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (V1 v1, V2 v2, V3 v3) -> this.test(before1.apply(v1), before2.apply(v2), before3.applyAsDouble(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> BiObjDoubleFunctionX<T1, T2, V, X> then(@Nonnull BooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2, double d) -> after.apply(this.test(t1, t2, d));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default BiObjDoublePredicate<T1, T2> nonThrowing() {
		return BiObjDoublePredicate.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default BiObjDoublePredicateX<T1, T2, RuntimeException> uncheck() {
		return nonThrowing()::test;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default BiObjDoublePredicate<T1, T2> shove() {
		BiObjDoublePredicateX<T1, T2, RuntimeException> exceptionCast = (BiObjDoublePredicateX<T1, T2, RuntimeException>) this;
		return exceptionCast::test;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T1, T2, X extends Exception, E extends Exception, Y extends Exception> BiObjDoublePredicateX<T1, T2, Y> wrapException(@Nonnull final BiObjDoublePredicateX<T1, T2, X> other, Class<E> exception, BooleanSupplierX<X> supplier,
			ExceptionHandler<E, Y> handler) {
		return (T1 t1, T2 t2, double d) -> {
			try {
				return other.test(t1, t2, d);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.getAsBoolean();
					}
				} catch (Exception supplierException) {
					throw new ExceptionNotHandled("Provided supplier (as a default value supplier/exception handler) failed on its own.", supplierException);
				}
				throw ExceptionHandler.handle(exception, Objects.requireNonNull(handler), (E) e);
			}
		};
	}

	/** Wraps with exception handling that for argument exception class will call function to determine the final exception. */
	@Nonnull
	default <E extends Exception, Y extends Exception> BiObjDoublePredicateX<T1, T2, Y> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiObjDoublePredicateX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> BiObjDoublePredicateX<T1, T2, Y> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiObjDoublePredicateX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> BiObjDoublePredicateX<T1, T2, Y> handle(Class<E> exception, BooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiObjDoublePredicateX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> BiObjDoublePredicateX<T1, T2, Y> handle(BooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiObjDoublePredicateX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
