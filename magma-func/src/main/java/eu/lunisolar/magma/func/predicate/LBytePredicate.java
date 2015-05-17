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
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.predicate;
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
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
 * Non-throwing functional interface (lambda) LBytePredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): byte b
 *
 * Co-domain: none
 *
 * @see LBytePredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBytePredicate extends LBytePredicateX<RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LBytePredicate: boolean test(byte b)";

	// Ovverriding methods can cause problems with inference.

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean applyAsBoolean(byte b) {
		return test(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBytePredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(byte b) {
		return () -> this.test(b);
	}

	public static LBytePredicate constant(boolean r) {
		return (b) -> r;
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNull(byte b) {
		return test(b);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LBytePredicate l(final @Nonnull LBytePredicate lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LBytePredicate wrap(final @Nonnull LBytePredicateX<X> other) {
		return (byte b) -> {
			try {
				return other.test(b);
			} catch (Exception e) {
				throw ExceptionHandler.handleWrapping(e);
			}
		};
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBytePredicate negate() {
		return (byte b) -> !test(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBytePredicate and(@Nonnull LBytePredicate other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (byte b) -> test(b) && other.test(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBytePredicate or(@Nonnull LBytePredicate other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (byte b) -> test(b) || other.test(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBytePredicate xor(@Nonnull LBytePredicate other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (byte b) -> test(b) ^ other.test(b);
	}

	@Nonnull
	public static LBytePredicate isEqual(byte target) {
		return b -> b == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBytePredicate fromByte(@Nonnull final LByteUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final byte v1) -> this.test(before1.applyAsByte(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicate<V1> from(@Nonnull final LToByteFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.test(before1.applyAsByte(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.apply(this.test(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LBooleanToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsByte(this.test(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToShortFunction thenToShort(@Nonnull LBooleanToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsShort(this.test(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LBooleanToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsInt(this.test(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LBooleanToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsLong(this.test(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToFloatFunction thenToFloat(@Nonnull LBooleanToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsFloat(this.test(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToDoubleFunction thenToDouble(@Nonnull LBooleanToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsDouble(this.test(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LBooleanToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsChar(this.test(b));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LBytePredicate thenToBoolean(@Nonnull LBooleanUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (byte b) -> after.applyAsBoolean(this.test(b));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBytePredicate nonThrowing() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBytePredicateX<RuntimeException> uncheck() {
		return (LBytePredicateX) this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBytePredicate shove() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LBytePredicate wrapException(@Nonnull final LBytePredicate other, Class<E> exception, LBooleanSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (byte b) -> {
			try {
				return other.test(b);
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
	default <E extends Exception, Y extends RuntimeException> LBytePredicate handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBytePredicate.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LBytePredicate handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBytePredicate.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LBytePredicate handle(Class<E> exception, LBooleanSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBytePredicate.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LBytePredicate handle(LBooleanSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBytePredicate.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
