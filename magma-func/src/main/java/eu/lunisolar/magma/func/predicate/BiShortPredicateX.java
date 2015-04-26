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
 * Function category: predicate
 * Non-throwing interface/lambda variant: BiShortPredicate
 *
 * @see BiShortPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface BiShortPredicateX<X extends Exception> extends MetaPredicate, PrimitiveCodomain<BiShortPredicateX<X>>, MetaThrowingInterface<X> { // NOSONAR

	public static final String DESCRIPTION = "BiShortPredicateX: boolean test(short s1,short s2) throws X";

	public boolean test(short s1, short s2) throws X;

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean applyAsBoolean(short s1, short s2) throws X {
		return test(s1, s2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return BiShortPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default BooleanSupplierX<X> capture(short s1, short s2) {
		return () -> this.test(s1, s2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNull(short s1, short s2) throws X {
		return test(s1, s2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> BiShortPredicateX<X> lX(final @Nonnull BiShortPredicateX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> BiShortPredicateX<X> wrapX(final @Nonnull BiShortPredicate other) {
		return other::test;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default BiShortPredicateX<X> negate() {
		return (short s1, short s2) -> !test(s1, s2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default BiShortPredicateX<X> and(@Nonnull BiShortPredicateX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (short s1, short s2) -> test(s1, s2) && other.test(s1, s2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default BiShortPredicateX<X> or(@Nonnull BiShortPredicateX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (short s1, short s2) -> test(s1, s2) || other.test(s1, s2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default BiShortPredicateX<X> xor(@Nonnull BiShortPredicateX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (short s1, short s2) -> test(s1, s2) ^ other.test(s1, s2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <X extends Exception> BiShortPredicateX<X> isEqual(final short v1, final short v2) {
		return (s1, s2) -> (s1 == v1) && (s2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default BiShortPredicateX<X> fromShort(@Nonnull final ShortUnaryOperatorX<X> before1, @Nonnull final ShortUnaryOperatorX<X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final short v1, final short v2) -> this.test(before1.applyAsShort(v1), before2.applyAsShort(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> BiPredicateX<V1, V2, X> from(@Nonnull final ToShortFunctionX<? super V1, X> before1, @Nonnull final ToShortFunctionX<? super V2, X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (V1 v1, V2 v2) -> this.test(before1.applyAsShort(v1), before2.applyAsShort(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> ShortBiFunctionX<V, X> then(@Nonnull BooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (short s1, short s2) -> after.apply(this.test(s1, s2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default BiShortPredicate nonThrowing() {
		return BiShortPredicate.wrap(this);
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default BiShortPredicateX<RuntimeException> uncheck() {
		return nonThrowing()::test;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default BiShortPredicate shove() {
		BiShortPredicateX<RuntimeException> exceptionCast = (BiShortPredicateX<RuntimeException>) this;
		return exceptionCast::test;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> BiShortPredicateX<Y> wrapException(@Nonnull final BiShortPredicateX<X> other, Class<E> exception, BooleanSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (short s1, short s2) -> {
			try {
				return other.test(s1, s2);
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
	default <E extends Exception, Y extends Exception> BiShortPredicateX<Y> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiShortPredicateX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> BiShortPredicateX<Y> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiShortPredicateX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> BiShortPredicateX<Y> handle(Class<E> exception, BooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiShortPredicateX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> BiShortPredicateX<Y> handle(BooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return BiShortPredicateX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
