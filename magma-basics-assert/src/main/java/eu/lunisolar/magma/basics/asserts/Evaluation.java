/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.basics.Null;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Condition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.*;
import java.util.function.*;

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

    private R stillActualResult() {
        final AtomicReference<R> reference = new AtomicReference<>();

        to(rs -> rs.satisfies(new Condition<R>() {
            @Override public boolean matches(R value) {
                reference.set(value);
                return true;
            }
        }));

        return reference.get();
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

    /** Ads possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
    public CTX that(@Nonnull Consumer<R> customCheckBlock) {
        Null.nonNullArg(customCheckBlock, "customCheckBlock");
        R actualResult = stillActualResult();
        customCheckBlock.accept(actualResult);
        return context.self();
    }

    /** Ads possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
    public <AA> CTX that(@Nonnull Function<R, AA> adapter, @Nonnull Consumer<AA> customCheckBlock) {
        Null.nonNullArg(adapter, "adapter");
        Null.nonNullArg(customCheckBlock, "customCheckBlock");

        R actualResult = stillActualResult();

        AA wrapper = adapter.apply(actualResult);
        Null.nonNull(wrapper, () -> "Adapter function must produce non-null result!");

        customCheckBlock.accept(wrapper);
        return context.self();
    }

}