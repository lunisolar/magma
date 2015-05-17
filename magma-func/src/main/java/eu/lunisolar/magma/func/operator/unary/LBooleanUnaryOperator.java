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

package eu.lunisolar.magma.func.operator.unary;
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
 * Non-throwing functional interface (lambda) LBooleanUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): boolean b
 *
 * Co-domain: none
 *
 * @see LBooleanUnaryOperatorX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanUnaryOperator extends LBooleanUnaryOperatorX<RuntimeException>, MetaLogicalOperator, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LBooleanUnaryOperator: boolean doApplyAsBoolean(boolean b)";

	public boolean doApplyAsBoolean(boolean b);

	default boolean nestingDoApplyAsBoolean(boolean b) {
		return this.doApplyAsBoolean(b);
	}

	default boolean shovingDoApplyAsBoolean(boolean b) {
		return this.doApplyAsBoolean(b);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApplyAsBoolean(boolean b) {
		return doApplyAsBoolean(b);
	}

	/** For convinience boolean operator is also special case of predicate. */
	default boolean doTest(boolean b) {
		return doApplyAsBoolean(b);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanUnaryOperator.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(boolean b) {
		return () -> this.doApplyAsBoolean(b);
	}

	public static LBooleanUnaryOperator constant(boolean r) {
		return (b) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LBooleanUnaryOperator l(final @Nonnull LBooleanUnaryOperator lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LBooleanUnaryOperator wrap(final @Nonnull LBooleanUnaryOperatorX<X> other) {
		return other::nestingDoApplyAsBoolean;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBooleanUnaryOperator negate() {
		return (boolean b) -> !doApplyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBooleanUnaryOperator and(@Nonnull LBooleanUnaryOperator other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> doApplyAsBoolean(b) && other.doApplyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanUnaryOperator or(@Nonnull LBooleanUnaryOperator other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> doApplyAsBoolean(b) || other.doApplyAsBoolean(b);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanUnaryOperator xor(@Nonnull LBooleanUnaryOperator other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b) -> doApplyAsBoolean(b) ^ other.doApplyAsBoolean(b);
	}

	@Nonnull
	public static LBooleanUnaryOperator isEqual(boolean target) {
		return b -> b == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanUnaryOperator fromBoolean(@Nonnull final LBooleanUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final boolean v1) -> this.doApplyAsBoolean(before1.doApplyAsBoolean(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicate<V1> from(@Nonnull final LPredicate<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsBoolean(before1.doApplyAsBoolean(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBooleanFunction<V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApply(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToByteFunction thenToByte(@Nonnull LBooleanToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsByte(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToShortFunction thenToShort(@Nonnull LBooleanToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsShort(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToIntFunction thenToInt(@Nonnull LBooleanToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsInt(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToLongFunction thenToLong(@Nonnull LBooleanToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsLong(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToFloatFunction thenToFloat(@Nonnull LBooleanToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsFloat(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToDoubleFunction thenToDouble(@Nonnull LBooleanToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsDouble(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanToCharFunction thenToChar(@Nonnull LBooleanToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsChar(this.doApplyAsBoolean(b));
	}

	/** Combines two operators together in a order. */
	@Nonnull
	default LBooleanUnaryOperator thenToBoolean(@Nonnull LBooleanUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b) -> after.doApplyAsBoolean(this.doApplyAsBoolean(b));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	public static LBooleanUnaryOperator identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanUnaryOperator nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanUnaryOperatorX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanUnaryOperator shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanUnaryOperatorX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LBooleanUnaryOperator wrapException(@Nonnull final LBooleanUnaryOperator other, Class<E> exception, LBooleanSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (boolean b) -> {
			try {
				return other.doApplyAsBoolean(b);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsBoolean();
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
	default <E extends Exception, Y extends RuntimeException> LBooleanUnaryOperator handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanUnaryOperator.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LBooleanUnaryOperator handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanUnaryOperator.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LBooleanUnaryOperator handle(Class<E> exception, LBooleanSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanUnaryOperator.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LBooleanUnaryOperator handle(LBooleanSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanUnaryOperator.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
