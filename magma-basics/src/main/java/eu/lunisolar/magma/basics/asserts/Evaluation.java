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
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.basics.asserts;

import eu.lunisolar.magma.basics.fluent.FluentSubcontext;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.function.Consumer;

import static org.assertj.core.api.Fail.fail;

/**
 * Fluent sub-context for functional interface assertions.
 */
@Immutable
@ThreadSafe
@SuppressWarnings("unchecked")
public final class Evaluation<CTX extends FunctionalAssert<CTX, A, RS, R, X>, A, RS extends Assert<RS, R>, R, X extends Exception> implements FluentSubcontext<Evaluation<CTX, A, RS, R, X>, CTX> {

    private final @Nonnull  AssertionSupplier<RS, X> assertSupplier;
    private final @Nullable Consumer<RS>             assertPreConsumer;

    private final @Nonnull FunctionalAssert<? extends FunctionalAssert, A, RS, R, X> context;

    public Evaluation(
            @Nonnull FunctionalAssert<? extends FunctionalAssert, A, RS, R, X> context,
            @Nullable java.util.function.Consumer<RS> assertPreConsumer,
            @Nonnull AssertionSupplier<RS, X> assertSupplier) {
        this.assertPreConsumer = assertPreConsumer;
        this.assertSupplier = assertSupplier;
        this.context = context;
    }

    /** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
    public CTX to(@Nonnull java.util.function.Consumer<RS> assertions) {
        normalCheck(assertSupplier, assertPreConsumer, assertions);
        return (CTX) context.self();
    }

    /** Convenient method to just check equality */
    public CTX toEqualTo(R equalsTo ) {
        to(rs->rs.isEqualTo(equalsTo));
        return (CTX) context.self();
    }

    /** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
    public CTX as(@Nonnull java.util.function.Consumer<RS> assertions) {
        return to(assertions);
    }

    /** Convenient method to just check equality */
    public CTX asEqualTo(R equalsTo ) {
        to(rs->rs.isEqualTo(equalsTo));
        return (CTX) context.self();
    }

    public CTX withoutException() {
        exceptionCheck(assertSupplier, a -> {
        });
        return (CTX) context.self();
    }

    /** Assertion for the failure of the method unther test. */
    public CTX withException(@Nonnull java.util.function.Consumer<AbstractThrowableAssert<?, ? extends Throwable>> assertions) {
        exceptionCheck(assertSupplier, assertions);
        return (CTX) context.self();
    }

    protected static <RS extends Assert<RS, R>, R, X extends Exception> void normalCheck(
            @Nonnull AssertionSupplier<RS, X> assertSupplier,
            @Nullable java.util.function.Consumer<RS> assertPreConsumer,
            @Nonnull java.util.function.Consumer<RS> assertConsumer
    ) {
        try {
            RS resultAssert = assertSupplier.get();

            try {
                if (assertPreConsumer != null) {
                    assertPreConsumer.accept(resultAssert);
                }
            } catch (AssertionError e) {
                throw new AssertionError("Recurring assertion failed." + e.getMessage() , e);
            }

            assertConsumer.accept(resultAssert);
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            fail("Should evaluate without exception.", e);
        }
    }

    protected static <RS extends Assert<RS, R>, R, X extends Exception> void exceptionCheck(
            @Nonnull AssertionSupplier<RS, X> assertSupplier,
            @Nonnull java.util.function.Consumer<AbstractThrowableAssert<?, ? extends Throwable>> assertConsumer
    ) {
        try {
            // supplier will fail with the
            assertSupplier.get();
            fail("Should evaluate with exception.");
        } catch (Exception e) {
            assertConsumer.accept(Assertions.assertThat(e));
        }
    }

}