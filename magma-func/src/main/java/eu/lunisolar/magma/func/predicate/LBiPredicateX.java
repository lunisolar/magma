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
 * Throwing functional interface (lambda) LBiPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T1 t1,T2 t2
 *
 * Co-domain: none
 *
 * @see LBiPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiPredicateX<T1, T2, X extends Exception> extends java.util.function.BiPredicate<T1, T2>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LBiPredicateX: boolean doTest(T1 t1,T2 t2) throws X";

	@Override
	@Deprecated
	// calling this method via LBiPredicateX interface should be discouraged.
	default boolean test(T1 t1, T2 t2) {
		return this.nestingDoTest(t1, t2);
	}

	public boolean doTest(T1 t1, T2 t2) throws X;

	default boolean nestingDoTest(T1 t1, T2 t2) {
		try {
			return this.doTest(t1, t2);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(T1 t1, T2 t2) {
		return ((LBiPredicateX<T1, T2, RuntimeException>) this).doTest(t1, t2);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T1 t1, T2 t2) throws X {
		return doTest(t1, t2);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 t1, T2 t2) throws X {
		return doTest(t1, t2);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(T1 t1, T2 t2) {
		return () -> this.doTest(t1, t2);
	}

	public static <T1, T2, X extends Exception> LBiPredicateX<T1, T2, X> constant(boolean r) {
		return (t1, t2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiPredicateX<T1, T2, X> lX(final @Nonnull LBiPredicateX<T1, T2, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiPredicateX<T1, T2, X> wrap(final java.util.function.BiPredicate<T1, T2> other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiPredicateX<T1, T2, X> wrapX(final @Nonnull LBiPredicate<T1, T2> other) {
		return (LBiPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBiPredicateX<T1, T2, X> negate() {
		return (T1 t1, T2 t2) -> !doTest(t1, t2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiPredicateX<T1, T2, X> and(@Nonnull LBiPredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2) -> doTest(t1, t2) && other.doTest(t1, t2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiPredicateX<T1, T2, X> or(@Nonnull LBiPredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2) -> doTest(t1, t2) || other.doTest(t1, t2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiPredicateX<T1, T2, X> xor(@Nonnull LBiPredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2) -> doTest(t1, t2) ^ other.doTest(t1, t2);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiPredicateX<T1, T2, X> isEqual(final T1 v1, final T2 v2) {
		return (t1, t2) -> (t1 == null ? v1 == null : t1.equals(v1)) && (t2 == null ? v2 == null : t2.equals(v2));
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiPredicateX<V1, V2, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final V1 v1, final V2 v2) -> this.doTest(before1.doApply(v1), before2.doApply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LBiFunctionX<T1, T2, V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2) -> after.doApply(this.doTest(t1, t2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiPredicate<T1, T2> nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiPredicateX<T1, T2, RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiPredicate<T1, T2> shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiPredicateX<T1, T2, RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T1, T2, X extends Exception, E extends Exception, Y extends Exception> LBiPredicateX<T1, T2, Y> wrapException(@Nonnull final LBiPredicateX<T1, T2, X> other, Class<E> exception, LBooleanSupplierX<X> supplier,
			ExceptionHandler<E, Y> handler) {
		return (T1 t1, T2 t2) -> {
			try {
				return other.doTest(t1, t2);
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
	default <E extends Exception, Y extends Exception> LBiPredicateX<T1, T2, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiPredicateX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LBiPredicateX<T1, T2, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiPredicateX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LBiPredicateX<T1, T2, Y> handleX(Class<E> exception, LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiPredicateX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LBiPredicateX<T1, T2, Y> handleX(LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiPredicateX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
