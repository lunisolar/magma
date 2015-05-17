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
 * Throwing functional interface (lambda) LDoublePredicateX for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): double d
 *
 * Co-domain: none
 *
 * @see LDoublePredicate
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDoublePredicateX<X extends Exception> extends java.util.function.DoublePredicate, MetaPredicate, PrimitiveCodomain<Object>, MetaInterface.Throwing<X> { // NOSONAR

	public static final String DESCRIPTION = "LDoublePredicateX: boolean doTest(double d) throws X";

	@Override
	@Deprecated
	// calling this method via LDoublePredicateX interface should be discouraged.
	default boolean test(double d) {
		return this.nestingDoTest(d);
	}

	public boolean doTest(double d) throws X;

	default boolean nestingDoTest(double d) {
		try {
			return this.doTest(d);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NestedException(e);
		}
	}

	default boolean shovingDoTest(double d) {
		return ((LDoublePredicateX<RuntimeException>) this).doTest(d);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullDoTest(double d) throws X {
		return doTest(d);
	}

	/** For convinience where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(double d) throws X {
		return doTest(d);
	}

	/** Returns desxription of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDoublePredicateX.DESCRIPTION;
	}

	/** Captures arguments but delays the evaluation. */
	default LBooleanSupplierX<X> capture(double d) {
		return () -> this.doTest(d);
	}

	public static <X extends Exception> LDoublePredicateX<X> constant(boolean r) {
		return (d) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	public static <X extends Exception> LDoublePredicateX<X> lX(final @Nonnull LDoublePredicateX<X> lambda) {
		Objects.requireNonNull(lambda, "Argument [lambda] cannot be null.");
		return lambda;
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	public static <X extends Exception> LDoublePredicateX<X> wrap(final java.util.function.DoublePredicate other) {
		return other::test;
	}

	/** Wraps opposite (throwing/non-throwing) instance. */
	@Nonnull
	public static <X extends Exception> LDoublePredicateX<X> wrapX(final @Nonnull LDoublePredicate other) {
		return (LDoublePredicateX) other;
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 *  @see {@link java.util.function.Predicate#negate()}
	 */
	@Nonnull
	default LDoublePredicateX<X> negate() {
		return (double d) -> !doTest(d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LDoublePredicateX<X> and(@Nonnull LDoublePredicateX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (double d) -> doTest(d) && other.doTest(d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LDoublePredicateX<X> or(@Nonnull LDoublePredicateX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (double d) -> doTest(d) || other.doTest(d);
	}

	/**
	 *  @see {@link java.util.function.Predicate#or()}
	 */
	@Nonnull
	default LDoublePredicateX<X> xor(@Nonnull LDoublePredicateX<X> other) {
		Objects.requireNonNull(other, Function4U.VALIDATION_MESSAGE_OTHER);
		return (double d) -> doTest(d) ^ other.doTest(d);
	}

	@Nonnull
	public static <X extends Exception> LDoublePredicateX<X> isEqual(double target) {
		return d -> d == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default LDoublePredicateX<X> fromDouble(@Nonnull final LDoubleUnaryOperatorX<X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (final double v1) -> this.doTest(before1.doApplyAsDouble(v1));
	}

	/**
	 * Allows to manipulate the domain of the functyion.
	 */
	@Nonnull
	default <V1> LPredicateX<V1, X> from(@Nonnull final LToDoubleFunctionX<? super V1, X> before1) {
		Objects.requireNonNull(before1, Function4U.VALIDATION_MESSAGE_BEFORE1);
		return (V1 v1) -> this.doTest(before1.doApplyAsDouble(v1));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two predicates together in a order. */
	@Nonnull
	default <V> LDoubleFunctionX<V, X> then(@Nonnull LBooleanFunctionX<? extends V, X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApply(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToByteFunctionX<X> thenToByte(@Nonnull LBooleanToByteFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsByte(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToShortFunctionX<X> thenToShort(@Nonnull LBooleanToShortFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsShort(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToIntFunctionX<X> thenToInt(@Nonnull LBooleanToIntFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsInt(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToLongFunctionX<X> thenToLong(@Nonnull LBooleanToLongFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsLong(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToFloatFunctionX<X> thenToFloat(@Nonnull LBooleanToFloatFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsFloat(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleUnaryOperatorX<X> thenToDouble(@Nonnull LBooleanToDoubleFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsDouble(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoubleToCharFunctionX<X> thenToChar(@Nonnull LBooleanToCharFunctionX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsChar(this.doTest(d));
	}

	/** Combines two predicates together in a order. */
	@Nonnull
	default LDoublePredicateX<X> thenToBoolean(@Nonnull LBooleanUnaryOperatorX<X> after) {
		Objects.requireNonNull(after, Function4U.VALIDATION_MESSAGE_AFTER);
		return (double d) -> after.doApplyAsBoolean(this.doTest(d));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDoublePredicate nest() {
		return this::nestingDoTest;
	}

	/** Converts to throwing variant (RuntimeException). */
	@Nonnull
	default LDoublePredicateX<RuntimeException> nestX() {
		return this::nestingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoublePredicate shove() {
		return this::shovingDoTest;
	}

	/** Dirty way, checked exception will propagate as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDoublePredicateX<RuntimeException> shoveX() {
		return this::shovingDoTest;
	}

	// </editor-fold>

	// <editor-fold desc="exception handling">

	/** Wraps with additional exception handling. */
	@Nonnull
	public static <X extends Exception, E extends Exception, Y extends Exception> LDoublePredicateX<Y> wrapException(@Nonnull final LDoublePredicateX<X> other, Class<E> exception, LBooleanSupplierX<X> supplier, ExceptionHandler<E, Y> handler) {
		return (double d) -> {
			try {
				return other.doTest(d);
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
	default <E extends Exception, Y extends Exception> LDoublePredicateX<Y> handleX(Class<E> exception, ExceptionHandler<E, Y> handler) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoublePredicateX.wrapException(this, exception, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for any exception (including unchecked exception that might be different from X) will call handler function to determine the final exception. */
	@Nonnull
	default <Y extends Exception> LDoublePredicateX<Y> handleX(ExceptionHandler<Exception, Y> handler) {
		Objects.requireNonNull(handler, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoublePredicateX.wrapException(this, Exception.class, null, (ExceptionHandler) handler);
	}

	/** Wraps with exception handling that for argument exception class will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <E extends Exception, Y extends Exception> LDoublePredicateX<Y> handleX(Class<E> exception, LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(exception, Function4U.VALIDATION_MESSAGE_EXCEPTION);
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoublePredicateX.wrapException(this, exception, supplier, null);
	}

	/** Wraps with exception handling that for any exception will call supplier and return default value instead for propagating exception.  */
	@Nonnull
	default <Y extends Exception> LDoublePredicateX<Y> handleX(LBooleanSupplierX<X> supplier) {
		Objects.requireNonNull(supplier, Function4U.VALIDATION_MESSAGE_HANDLER);

		return LDoublePredicateX.wrapException(this, Exception.class, supplier, null);
	}

	// </editor-fold>

}
