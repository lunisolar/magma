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
 * Non-throwing functional interface (lambda) LObjFloatPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T t, float f
 *
 * Co-domain: none
 *
 * @see LObjFloatPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjFloatPredicate<T> extends LObjFloatPredicateX<T, RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LObjFloatPredicate: boolean doTest(T t, float f)";

	public boolean doTest(T t, float f);

	default boolean nestingDoTest(T t, float f) {
		return this.doTest(t, f);
	}

	default boolean shovingDoTest(T t, float f) {
		return this.doTest(t, f);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(T t, float f) {
		return doTest(t, f);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T t, float f) {
		return doTest(t, f);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjFloatPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(T t, float f) {
		return () -> this.doTest(t, f);
	}

	public static <T> LObjFloatPredicate<T> constant(boolean r) {
		return (t, f) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <T> LObjFloatPredicate<T> l(final @Nonnull LObjFloatPredicate<T> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <T, X extends Exception> LObjFloatPredicate<T> wrap(final @Nonnull LObjFloatPredicateX<T, X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LObjFloatPredicate<T> negate() {
		return (T t, float f) -> !doTest(t, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjFloatPredicate<T> and(@Nonnull LObjFloatPredicate<? super T> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T t, float f) -> doTest(t, f) && other.doTest(t, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LObjFloatPredicate<T> or(@Nonnull LObjFloatPredicate<? super T> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T t, float f) -> doTest(t, f) || other.doTest(t, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LObjFloatPredicate<T> xor(@Nonnull LObjFloatPredicate<? super T> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (T t, float f) -> doTest(t, f) ^ other.doTest(t, f);
	}

	/**
	 *  @see {@link java.util.function.Predicate#isEqual()}
	 */
	@Nonnull
	public static <T1> LObjFloatPredicate<T1> isEqual(final T1 v1, final float v2) {
		return (t, f) -> (t == null ? v1 == null : t.equals(v1)) && (f == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LObjFloatPredicate<V1> fromFloat(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LFloatUnaryOperator before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (final V1 v1, final float v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsFloat(v2));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> from(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToFloatFunction<? super V2> before2) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		Objects.requireNonNull(before2, Function4U.VALIDATION_MESSAGE_BEFORE2);
		return (V1 v1, V2 v2) -> this.doTest(before1.doApply(v1), before2.doApplyAsFloat(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LObjFloatFunction<T, V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (T t, float f) -> after.doApply(this.doTest(t, f));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LObjFloatPredicate<T> nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LObjFloatPredicateX<T, RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjFloatPredicate<T> shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LObjFloatPredicateX<T, RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <T, X extends Exception, E extends Exception, Y extends RuntimeException> LObjFloatPredicate<T> wrapException(@Nonnull final LObjFloatPredicate<T> other, Class<E> exception, LBooleanSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (T t, float f) -> {
			try {
				return other.doTest(t, f);
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
	default <E extends Exception, Y extends RuntimeException> LObjFloatPredicate<T> handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjFloatPredicate.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LObjFloatPredicate<T> handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjFloatPredicate.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LObjFloatPredicate<T> handle(Class<E> exception, LBooleanSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjFloatPredicate.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LObjFloatPredicate<T> handle(LBooleanSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LObjFloatPredicate.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
