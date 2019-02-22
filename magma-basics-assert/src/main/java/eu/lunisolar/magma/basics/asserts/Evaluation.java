/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
import org.assertj.core.description.Description;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import java.util.function.*;

import static javafx.scene.input.KeyCode.X;

/**
 * Fluent sub-context for functional interface assertions.
 */
@Immutable
@ThreadSafe
@SuppressWarnings("unchecked")
public final class Evaluation<CTX extends FullFunctionalAssert<CTX, PC, A, RS, R>, PC, A, RS extends Assert<RS, R>, R>
        extends AbstractEvaluation<Evaluation<CTX, PC, A, RS, R>, CTX, PC, RS> {

    public Evaluation(
            @Nonnull CTX context,
            @Nonnull Supplier<String> description,
            @Nonnull Supplier<String> caseDescription,
            @Nonnull AssertionFunction<PC, RS> assertFunction,
            @Nullable java.util.function.Consumer<RS> assertPreConsumer) {
        super(context, description, caseDescription, assertFunction, assertPreConsumer);
    }

    /** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
    public CTX to(@Nonnull java.util.function.Consumer<RS> assertions) {
        normalCheck(description, caseDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
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