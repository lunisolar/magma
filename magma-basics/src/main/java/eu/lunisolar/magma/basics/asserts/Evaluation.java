/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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