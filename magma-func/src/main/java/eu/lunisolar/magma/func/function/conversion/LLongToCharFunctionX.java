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
 * Throwing functional interface (lambda) LLongToCharFunctionX for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long l
 *
 * Co-domain: none
 *
 * @see LLongToCharFunction
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongToCharFunctionX<X extends Exception> extends MetaFunction, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LLongToCharFunctionX: char doApplyAsChar(long l) throws X";

	public char doApplyAsChar(long l) throws X;

	default char nestingDoApplyAsChar(long l) {
		try {
			return this.doApplyAsChar(l);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default char shovingDoApplyAsChar(long l) {
		return ((LLongToCharFunctionX<RuntimeException>) this).doApplyAsChar(l);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(long l) throws X {
		return doApplyAsChar(l);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongToCharFunctionX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplierX<X> capture(long l) {
		return () -> this.doApplyAsChar(l);
	}

	public static <X extends Exception> LLongToCharFunctionX<X> constant(char r) {
		return (l) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LLongToCharFunctionX<X> lX(final @Nonnull LLongToCharFunctionX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LLongToCharFunctionX<X> wrapX(final @Nonnull LLongToCharFunction other) {
		return (LLongToCharFunctionX) other;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LLongToCharFunctionX<X> fromLong(@Nonnull final LLongUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final long v1) -> this.doApplyAsChar(before1.doApplyAsLong(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LToCharFunctionX<V1, X> from(@Nonnull final LToLongFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doApplyAsChar(before1.doApplyAsLong(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunctionX<V, X> then(@Nonnull LCharFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doApply(this.doApplyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunctionX<X> thenToByte(@Nonnull LCharToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doApplyAsByte(this.doApplyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToShortFunctionX<X> thenToShort(@Nonnull LCharToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doApplyAsShort(this.doApplyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunctionX<X> thenToInt(@Nonnull LCharToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doApplyAsInt(this.doApplyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperatorX<X> thenToLong(@Nonnull LCharToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doApplyAsLong(this.doApplyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFloatFunctionX<X> thenToFloat(@Nonnull LCharToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doApplyAsFloat(this.doApplyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDoubleFunctionX<X> thenToDouble(@Nonnull LCharToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doApplyAsDouble(this.doApplyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunctionX<X> thenToChar(@Nonnull LCharUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doApplyAsChar(this.doApplyAsChar(l));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicateX<X> thenToBoolean(@Nonnull LCharPredicateX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (long l) -> after.doTest(this.doApplyAsChar(l));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongToCharFunction nest() {
		return this::nestingDoApplyAsChar;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LLongToCharFunctionX<RuntimeException> nestX() {
		return this::nestingDoApplyAsChar;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToCharFunction shove() {
		return this::shovingDoApplyAsChar;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToCharFunctionX<RuntimeException> shoveX() {
		return this::shovingDoApplyAsChar;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LLongToCharFunctionX<Y> wrapException(@Nonnull final LLongToCharFunctionX<X> other, Class<E> exception, LCharSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (long l) -> {
			try {
				return other.doApplyAsChar(l);
			} catch (Exception e) {
				try {
					if (supplier != null) {
						return supplier.doGetAsChar();
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
	default <E extends Exception, Y extends Exception> LLongToCharFunctionX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongToCharFunctionX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LLongToCharFunctionX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongToCharFunctionX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LLongToCharFunctionX<Y> handleX(Class<E> exception, LCharSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongToCharFunctionX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LLongToCharFunctionX<Y> handleX(LCharSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LLongToCharFunctionX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
