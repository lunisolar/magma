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

package eu.lunisolar.magma.func.function.conversion;
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
 * Throwing functional interface (lambda) LIntToLongFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntToLongFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToLongFunctionX<X extends Exception> extends java.util.function.IntToLongFunction, MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LIntToLongFunctionX: long doApplyAsLong(int i) throws X";

	@Override
	@Deprecated
	// calling this method via LIntToLongFunctionX interface should be discouraged.
	default long applyAsLong(int i) {
		return this.nestingDoApplyAsLong(i);
	}

	public long doApplyAsLong(int i) throws X;

	default long nestingDoApplyAsLong(int i) {
		try {
			return this.doApplyAsLong(i);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default long shovingDoApplyAsLong(int i) {
		return ((LIntToLongFunctionX<RuntimeException>) this).doApplyAsLong(i);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(int i) throws X {
		return doApplyAsLong(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToLongFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplierX<X> capture(int i) {
		return () -> this.doApplyAsLong(i);
	}

	public static <X extends Exception> LIntToLongFunctionX<X> constant(long r) {
		return (i) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LIntToLongFunctionX<X> lX(final @Nonnull LIntToLongFunctionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> LIntToLongFunctionX<X> wrap(final java.util.function.IntToLongFunction other) {
		return other::applyAsLong;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LIntToLongFunctionX<X> wrapX(final @Nonnull LIntToLongFunction other) {
		return (LIntToLongFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntToLongFunctionX<X> fromInt(@Nonnull final LIntUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final int v1) -> this.doApplyAsLong(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToLongFunctionX<V1, X> from(@Nonnull final LToIntFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsLong(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunctionX<V, X> then(@Nonnull LLongFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApply(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunctionX<X> thenToByte(@Nonnull LLongToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsByte(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToShortFunctionX<X> thenToShort(@Nonnull LLongToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsShort(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperatorX<X> thenToInt(@Nonnull LLongToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsInt(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunctionX<X> thenToLong(@Nonnull LLongUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsLong(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFloatFunctionX<X> thenToFloat(@Nonnull LLongToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsFloat(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDoubleFunctionX<X> thenToDouble(@Nonnull LLongToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsDouble(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunctionX<X> thenToChar(@Nonnull LLongToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsChar(this.doApplyAsLong(i));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicateX<X> thenToBoolean(@Nonnull LLongPredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doTest(this.doApplyAsLong(i));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToLongFunction nest() {
		return this::nestingDoApplyAsLong;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntToLongFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToLongFunction shove() {
		return this::shovingDoApplyAsLong;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToLongFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsLong;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LIntToLongFunctionX<Y> wrapException(@Nonnull final LIntToLongFunctionX<X> other, Class<E> exception, LLongSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (int i) -> {
			try {
				return other.doApplyAsLong(i);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsLong();
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
	default <E extends Exception, Y extends Exception> LIntToLongFunctionX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntToLongFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LIntToLongFunctionX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntToLongFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LIntToLongFunctionX<Y> handleX(Class<E> exception, LLongSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntToLongFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LIntToLongFunctionX<Y> handleX(LLongSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntToLongFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
