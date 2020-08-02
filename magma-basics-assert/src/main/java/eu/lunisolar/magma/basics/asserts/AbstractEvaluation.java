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

import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.fluent.FluentSubcontext;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.function.*;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Fail.fail;

/**
 * Fluent sub-context for functional interface assertions.
 *
 * @param <PC> Preconditioner can establish external conditions that are required in order for evaluation case to be completed.
 */
@Immutable
@ThreadSafe
@SuppressWarnings("unchecked")
public abstract class AbstractEvaluation<SELF extends AbstractEvaluation<SELF, CTX, PC, A>, CTX extends Fluent<CTX>, PC, A>
        implements FluentSubcontext<SELF, CTX> {

    protected @Nonnull        Supplier<String>         description;
    protected @Nonnull        Supplier<String>         caseDescription;
    protected @Nullable       PC                       preconditioner;
    protected final @Nonnull  AssertionFunction<PC, A> assertFunction;
    protected final @Nullable List<Consumer<A>>        assertPreConsumer;

    protected final @Nonnull CTX context;

    protected AbstractEvaluation(
            @Nonnull CTX context,
            @Nonnull Supplier<String> description,
            @Nonnull Supplier<String> caseDescription,
            @Nonnull AssertionFunction<PC, A> assertFunction,
            @Nullable List<Consumer<A>> assertPreConsumer) {
        this.description = description;
        this.caseDescription = caseDescription;
        this.assertPreConsumer = assertPreConsumer;
        this.assertFunction = Objects.requireNonNull(assertFunction);
        this.context = context;

    }

    protected AbstractEvaluation(
            @Nonnull CTX context,
            @Nonnull Supplier<String> description,
            @Nonnull Supplier<String> caseDescription,
            @Nullable List<AssertionsCheck> assertPreConsumer, AssertionFunction<PC, A> assertFunction) {
        this(context, description, caseDescription, assertFunction,
             assertPreConsumer == null ? null : assertPreConsumer.stream().map(a -> (Consumer<A>) __ -> a.assertionsCheck()).collect(toList()));
    }

    public SELF when(PC preconditioner) {
        this.preconditioner = preconditioner;
        return self();
    }

    /** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
    public CTX soThat(@Nonnull AssertionsCheck assertions) {
        normalCheck(description, caseDescription, preconditioner, assertFunction, assertPreConsumer, a -> assertions.assertionsCheck());
        return context.self();
    }

    public CTX withoutException() {
        exceptionCheck(description, caseDescription, preconditioner, assertFunction, a -> {
        });
        return context.self();
    }

    /** Assertion for the failure of the method under test. */
    public CTX withException(@Nonnull Consumer<AbstractThrowableAssert<?, ? extends Throwable>> assertions) {
        exceptionCheck(description, caseDescription, preconditioner, assertFunction, assertions);
        return context.self();
    }

    protected static <PC, A, X extends Throwable> void normalCheck(
            @Nonnull Supplier<String> description,
            @Nonnull Supplier<String> caseDescription,
            @Nullable PC preconditioner,
            @Nonnull AssertionFunction<PC, A> assertFunction,
            @Nullable List<Consumer<A>> assertPreConsumer,
            @Nonnull Consumer<A> assertConsumer
    ) {
        try {
            A resultAssert = assertFunction.applyAndCreateResultAssert(preconditioner);

            try {
                if (assertPreConsumer != null) {
                    assertPreConsumer.forEach(c -> c.accept(resultAssert));
                }
            } catch (AssertionError e) {
                throw new AssertionError(String.format("%sRecurring assertion failed.%s", mainDescription(description), e.getMessage()), e);
            }

            assertConsumer.accept(resultAssert);
        } catch (AssertionError e) {
            throw e;
        } catch (Throwable e) { // NOSONAR
            fail(String.format("%sCase %s should evaluate without problem.", mainDescription(description), caseDescription.get()), e);
        }
    }

    protected static <PC, A, X extends Throwable> void exceptionCheck(
            @Nonnull Supplier<String> description,
            @Nonnull Supplier<String> caseDescription,
            @Nullable PC preconditioner,
            @Nonnull AssertionFunction<PC, A> assertFunction,
            @Nonnull Consumer<AbstractThrowableAssert<?, ? extends Throwable>> assertConsumer
    ) {

        try {
            assertFunction.applyAndCreateResultAssert(preconditioner);
        } catch (Throwable e) {  // NOSONAR
            assertConsumer.accept(Assertions.assertThat(e).as(description.get()));
            return;
        }

        fail("%sCase %s should evaluate with exception.", mainDescription(description), caseDescription.get());
    }

    private static String mainDescription(@Nonnull Supplier<String> description) {
        String desc = description.get();
        desc = desc == null ? "" : desc;
        desc = desc.isEmpty() ? desc : "[" + desc + "] ";
        return desc;
    }

}