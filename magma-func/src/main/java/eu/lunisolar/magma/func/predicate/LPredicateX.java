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
 * Throwing functional interface (lambda) LPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): T t
 *
 * Co-domain: none
 *
 * @see LPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LPredicateX<T, X extends Exception> extends java.util.function.Predicate<T>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LPredicateX: boolean doTest(T t) throws X";

	@Override
	@Deprecated
	// calling this method via LPredicateX interface should be discouraged.
	default boolean test(T t) {
		return this.nestingDoTest(t);
	}

	public boolean doTest(T t) throws X;

	default boolean nestingDoTest(T t) {
		try {
			return this.doTest(t);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(T t) {
		return ((LPredicateX<T, RuntimeException>) this).doTest(t);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t) throws X {
		return doTest(t);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t) throws X {
		return doTest(t);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(T t) {
		return () -> this.doTest(t);
	}

	public static <T, X extends Exception> LPredicateX<T, X> constant(boolean r) {
		return (t) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T, X extends Exception> LPredicateX<T, X> lX(final @Nonnull LPredicateX<T, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T, X extends Exception> LPredicateX<T, X> wrap(final java.util.function.Predicate<T> other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> LPredicateX<T, X> wrapX(final @Nonnull LPredicate<T> other) {
		return (LPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LPredicateX<T, X> negate() {
		return (T t) -> !doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LPredicateX<T, X> and(@Nonnull LPredicateX<? super T, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T t) -> doTest(t) && other.doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LPredicateX<T, X> or(@Nonnull LPredicateX<? super T, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T t) -> doTest(t) || other.doTest(t);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LPredicateX<T, X> xor(@Nonnull LPredicateX<? super T, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T t) -> doTest(t) ^ other.doTest(t);
	}

	@Nonnull
	public static <T, X extends Exception> LPredicateX<T, X> isEqual(T target) {
		return (null == target) ? Objects::isNull : object -> object.equals(target);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> from(@Nonnull final LFunctionX<? super V1, ? extends T, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final V1 v1) -> this.doTest(before1.doApply(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LFunctionX<T, V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApply(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToByteFunctionX<T, X> thenToByte(@Nonnull LBooleanToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApplyAsByte(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToShortFunctionX<T, X> thenToShort(@Nonnull LBooleanToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApplyAsShort(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToIntFunctionX<T, X> thenToInt(@Nonnull LBooleanToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApplyAsInt(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToLongFunctionX<T, X> thenToLong(@Nonnull LBooleanToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApplyAsLong(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToFloatFunctionX<T, X> thenToFloat(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApplyAsFloat(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToDoubleFunctionX<T, X> thenToDouble(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApplyAsDouble(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LToCharFunctionX<T, X> thenToChar(@Nonnull LBooleanToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApplyAsChar(this.doTest(t));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LPredicateX<T, X> thenToBoolean(@Nonnull LBooleanUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t) -> after.doApplyAsBoolean(this.doTest(t));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LPredicate<T> nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LPredicateX<T, RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicate<T> shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LPredicateX<T, RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, E extends Exception, Y extends Exception> LPredicateX<T, Y> wrapException(@Nonnull final LPredicateX<T, X> other, Class<E> exception, LBooleanSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (T t) -> {
			try {
				return other.doTest(t);
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
	default <E extends Exception, Y extends Exception> LPredicateX<T, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LPredicateX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LPredicateX<T, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LPredicateX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LPredicateX<T, Y> handleX(Class<E> exception, LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LPredicateX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LPredicateX<T, Y> handleX(LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LPredicateX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
