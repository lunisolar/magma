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
 * Non-throwing functional interface (lambda) LIntPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): int i
 *
 * Co-domain: none
 *
 * @see LIntPredicateX
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntPredicate extends LIntPredicateX<RuntimeException>, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.NonThrowing { // NOSONAR

	public static final String DESCRIPTION = "LIntPredicate: boolean doTest(int i)";

	@Override
	@Deprecated
	// calling this method via LIntPredicate interface should be discouraged.
	default boolean test(int i) {
		return this.nestingDoTest(i);
	}

	public boolean doTest(int i);

	default boolean nestingDoTest(int i) {
		return this.doTest(i);
	}

	default boolean shovingDoTest(int i) {
		return this.doTest(i);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(int i) {
		return doTest(i);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(int i) {
		return doTest(i);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntPredicate.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplier capture(int i) {
		return () -> this.doTest(i);
	}

	public static LIntPredicate constant(boolean r) {
		return (i) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static LIntPredicate l(final @Nonnull LIntPredicate lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static LIntPredicate wrap(final java.util.function.IntPredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LIntPredicate wrap(final @Nonnull LIntPredicateX<X> other) {
		return other::nestingDoTest;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LIntPredicate negate() {
		return (int i) -> !doTest(i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LIntPredicate and(@Nonnull LIntPredicate other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (int i) -> doTest(i) && other.doTest(i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LIntPredicate or(@Nonnull LIntPredicate other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (int i) -> doTest(i) || other.doTest(i);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LIntPredicate xor(@Nonnull LIntPredicate other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (int i) -> doTest(i) ^ other.doTest(i);
	}

	@Nonnull
	public static LIntPredicate isEqual(int target) {
		return i -> i == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LIntPredicate fromInt(@Nonnull final LIntUnaryOperator before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final int v1) -> this.doTest(before1.doApplyAsInt(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicate<V1> from(@Nonnull final LToIntFunction<? super V1> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doTest(before1.doApplyAsInt(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LBooleanFunction<? extends V> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApply(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LBooleanToByteFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsByte(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToShortFunction thenToShort(@Nonnull LBooleanToShortFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsShort(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LBooleanToIntFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsInt(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LBooleanToLongFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsLong(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToFloatFunction thenToFloat(@Nonnull LBooleanToFloatFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsFloat(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToDoubleFunction thenToDouble(@Nonnull LBooleanToDoubleFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsDouble(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LBooleanToCharFunction after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsChar(this.doTest(i));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LIntPredicate thenToBoolean(@Nonnull LBooleanUnaryOperator after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (int i) -> after.doApplyAsBoolean(this.doTest(i));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntPredicate nest() {
		return this;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LIntPredicateX<RuntimeException> nestX() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntPredicate shove() {
		return this;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntPredicateX<RuntimeException> shoveX() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends RuntimeException> LIntPredicate wrapException(@Nonnull final LIntPredicate other, Class<E> exception, LBooleanSupplier supplier, ExceptionHandler<E, Y> handler) {
		return (int i) -> {
			try {
				return other.doTest(i);
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
	default <E extends Exception, Y extends RuntimeException> LIntPredicate handle(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntPredicate.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends RuntimeException> LIntPredicate handle(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntPredicate.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends RuntimeException> LIntPredicate handle(Class<E> exception, LBooleanSupplier supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntPredicate.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends RuntimeException> LIntPredicate handle(LBooleanSupplier supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LIntPredicate.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
