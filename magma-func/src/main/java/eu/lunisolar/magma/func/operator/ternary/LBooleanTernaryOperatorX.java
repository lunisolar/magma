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

package eu.lunisolar.magma.func.operator.ternary;
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
 * Throwing functional interface (lambda) LBooleanTernaryOperatorX for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): boolean b1,boolean b2,boolean b3
 *
 * Co-domain: none
 *
 * @see LBooleanTernaryOperator
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBooleanTernaryOperatorX<X extends Exception> extends MetaLogicalOperator, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LBooleanTernaryOperatorX: boolean doApply(boolean b1,boolean b2,boolean b3) throws X";

	public boolean doApply(boolean b1, boolean b2, boolean b3) throws X;

	default boolean nestingDoApply(boolean b1, boolean b2, boolean b3) {
		try {
			return this.doApply(b1, b2, b3);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default boolean shovingDoApply(boolean b1, boolean b2, boolean b3) {
		return ((LBooleanTernaryOperatorX<RuntimeException>) this).doApply(b1, b2, b3);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoApply(boolean b1, boolean b2, boolean b3) throws X {
		return doApply(b1, b2, b3);
	}

	/** For convinience boolean operator is also special case of predicate. */
	default boolean doTest(boolean b1, boolean b2, boolean b3) throws X {
		return doApply(b1, b2, b3);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBooleanTernaryOperatorX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(boolean b1, boolean b2, boolean b3) {
		return () -> this.doApply(b1, b2, b3);
	}

	public static <X extends Exception> LBooleanTernaryOperatorX<X> constant(boolean r) {
		return (b1, b2, b3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LBooleanTernaryOperatorX<X> lX(final @Nonnull LBooleanTernaryOperatorX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LBooleanTernaryOperatorX<X> wrapX(final @Nonnull LBooleanTernaryOperator other) {
		return (LBooleanTernaryOperatorX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBooleanTernaryOperatorX<X> negate() {
		return (boolean b1, boolean b2, boolean b3) -> !doApply(b1, b2, b3);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBooleanTernaryOperatorX<X> and(@Nonnull LBooleanTernaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b1, boolean b2, boolean b3) -> doApply(b1, b2, b3) && other.doApply(b1, b2, b3);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanTernaryOperatorX<X> or(@Nonnull LBooleanTernaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b1, boolean b2, boolean b3) -> doApply(b1, b2, b3) || other.doApply(b1, b2, b3);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBooleanTernaryOperatorX<X> xor(@Nonnull LBooleanTernaryOperatorX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (boolean b1, boolean b2, boolean b3) -> doApply(b1, b2, b3) ^ other.doApply(b1, b2, b3);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <X extends Exception> LBooleanTernaryOperatorX<X> isEqual(final boolean v1, final boolean v2, final boolean v3) {
		return (b1, b2, b3) -> (b1 == v1) && (b2 == v2) && (b3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LBooleanTernaryOperatorX<X> fromBoolean(@Nonnull final LBooleanUnaryOperatorX<X> before1, @Nonnull final LBooleanUnaryOperatorX<X> before2, @Nonnull final LBooleanUnaryOperatorX<X> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (final boolean v1, final boolean v2, final boolean v3) -> this.doApply(before1.doApplyAsBoolean(v1), before2.doApplyAsBoolean(v2), before3.doApplyAsBoolean(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriPredicateX<V1, V2, V3, X> from(@Nonnull final LPredicateX<? super V1, X> before1, @Nonnull final LPredicateX<? super V2, X> before2, @Nonnull final LPredicateX<? super V3, X> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (V1 v1, V2 v2, V3 v3) -> this.doApply(before1.doApplyAsBoolean(v1), before2.doApplyAsBoolean(v2), before3.doApplyAsBoolean(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two operators together in a order. */
	@Nonnull
	default <V> LBooleanTriFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (boolean b1, boolean b2, boolean b3) -> after.doApply(this.doApply(b1, b2, b3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBooleanTernaryOperator nest() {
		return this::nestingDoApply;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBooleanTernaryOperatorX<RuntimeException> nestX() {
		return this::nestingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanTernaryOperator shove() {
		return this::shovingDoApply;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBooleanTernaryOperatorX<RuntimeException> shoveX() {
		return this::shovingDoApply;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LBooleanTernaryOperatorX<Y> wrapException(@Nonnull final LBooleanTernaryOperatorX<X> other, Class<E> exception, LBooleanSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (boolean b1, boolean b2, boolean b3) -> {
			try {
				return other.doApply(b1, b2, b3);
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
	default <E extends Exception, Y extends Exception> LBooleanTernaryOperatorX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanTernaryOperatorX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LBooleanTernaryOperatorX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanTernaryOperatorX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LBooleanTernaryOperatorX<Y> handleX(Class<E> exception, LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanTernaryOperatorX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LBooleanTernaryOperatorX<Y> handleX(LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBooleanTernaryOperatorX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
