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

package eu.lunisolar.magma.basics.asserts;

import org.assertj.core.api.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Fluent sub-context for functional interface assertions.
 */
@Immutable
@ThreadSafe
@SuppressWarnings("unchecked")
public final class Evaluation<CTX extends FullFunctionalAssert<CTX, A, RS, R, X>, A, RS extends Assert<RS, R>, R, X extends Exception>
        extends AbstractEvaluation<Evaluation<CTX, A, RS, R, X>, CTX, RS, X> {

    public Evaluation(
            @Nonnull CTX context,
            @Nullable java.util.function.Consumer<RS> assertPreConsumer,
            @Nonnull AssertionSupplier<RS> assertSupplier) {
        super(context, assertPreConsumer, assertSupplier);
    }

    /** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
    public CTX to(@Nonnull java.util.function.Consumer<RS> assertions) {
        normalCheck(assertSupplier, assertPreConsumer, assertions);
        return context.self();
    }

    /** Convenient method to just check equality */
    public CTX toEqualTo(R equalsTo) {
        to(rs -> rs.isEqualTo(equalsTo));
        return context.self();
    }

    /** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
    public CTX as(@Nonnull java.util.function.Consumer<RS> assertions) {
        return to(assertions);
    }

    /** Convenient method to just check equality */
    public CTX asEqualTo(R equalsTo) {
        to(rs -> rs.isEqualTo(equalsTo));
        return context.self();
    }

}