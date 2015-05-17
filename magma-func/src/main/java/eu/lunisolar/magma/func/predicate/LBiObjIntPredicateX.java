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
 * Throwing functional interface (lambda) LBiObjIntPredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 t1,T2 t2, int i
 *
 * Co-domain: none
 *
 * @see LBiObjIntPredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjIntPredicateX<T1, T2, X extends Exception> extends MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LBiObjIntPredicateX: boolean doTest(T1 t1,T2 t2, int i) throws X";

	public boolean doTest(T1 t1, T2 t2, int i) throws X;

	default boolean nestingDoTest(T1 t1, T2 t2, int i) {
		try {
			return this.doTest(t1, t2, i);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(T1 t1, T2 t2, int i) {
		return ((LBiObjIntPredicateX<T1, T2, RuntimeException>) this).doTest(t1, t2, i);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T1 t1, T2 t2, int i) throws X {
		return doTest(t1, t2, i);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 t1, T2 t2, int i) throws X {
		return doTest(t1, t2, i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjIntPredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(T1 t1, T2 t2, int i) {
		return () -> this.doTest(t1, t2, i);
	}

	public static <T1, T2, X extends Exception> LBiObjIntPredicateX<T1, T2, X> constant(boolean r) {
		return (t1, t2, i) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiObjIntPredicateX<T1, T2, X> lX(final @Nonnull LBiObjIntPredicateX<T1, T2, X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiObjIntPredicateX<T1, T2, X> wrapX(final @Nonnull LBiObjIntPredicate<T1, T2> other) {
		return (LBiObjIntPredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LBiObjIntPredicateX<T1, T2, X> negate() {
		return (T1 t1, T2 t2, int i) -> !doTest(t1, t2, i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiObjIntPredicateX<T1, T2, X> and(@Nonnull LBiObjIntPredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2, int i) -> doTest(t1, t2, i) && other.doTest(t1, t2, i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiObjIntPredicateX<T1, T2, X> or(@Nonnull LBiObjIntPredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2, int i) -> doTest(t1, t2, i) || other.doTest(t1, t2, i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LBiObjIntPredicateX<T1, T2, X> xor(@Nonnull LBiObjIntPredicateX<? super T1, ? super T2, X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T1 t1, T2 t2, int i) -> doTest(t1, t2, i) ^ other.doTest(t1, t2, i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <T1, T2, X extends Exception> LBiObjIntPredicateX<T1, T2, X> isEqual(final T1 v1, final T2 v2, final int v3) {
		return (t1, t2, i) -> (t1 == null ? v1 == null : t1.equals(v1)) && (t2 == null ? v2 == null : t2.equals(v2)) && (i == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiObjIntPredicateX<V1, V2, X> fromInt(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LIntUnaryOperatorX<X> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (final V1 v1, final V2 v2, final int v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsInt(v3));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2, V3> LTriPredicateX<V1, V2, V3, X> from(@Nonnull final LFunctionX<? super V1, ? extends T1, X> before1, @Nonnull final LFunctionX<? super V2, ? extends T2, X> before2, @Nonnull final LToIntFunctionX<? super V3, X> before3) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		Objects.requireNonNull(before3, Function4U.VALIDATION_MESSAGE_BEFORE3);
		return (V1 v1, V2 v2, V3 v3) -> this.doTest(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsInt(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LBiObjIntFunctionX<T1, T2, V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T1 t1, T2 t2, int i) -> after.doApply(this.doTest(t1, t2, i));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjIntPredicate<T1, T2> nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LBiObjIntPredicateX<T1, T2, RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjIntPredicate<T1, T2> shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjIntPredicateX<T1, T2, RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T1, T2, X extends Exception, E extends Exception, Y extends Exception> LBiObjIntPredicateX<T1, T2, Y> wrapException(@Nonnull final LBiObjIntPredicateX<T1, T2, X> other, Class<E> exception, LBooleanSupplierX<X> supplier,
			ExceptionHandler<E, Y> handler) {
		return (T1 t1, T2 t2, int i) -> {
			try {
				return other.doTest(t1, t2, i);
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
	default <E extends Exception, Y extends Exception> LBiObjIntPredicateX<T1, T2, Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiObjIntPredicateX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LBiObjIntPredicateX<T1, T2, Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiObjIntPredicateX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LBiObjIntPredicateX<T1, T2, Y> handleX(Class<E> exception, LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiObjIntPredicateX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LBiObjIntPredicateX<T1, T2, Y> handleX(LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LBiObjIntPredicateX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
