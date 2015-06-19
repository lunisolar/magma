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

import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.fluent.FluentSubcontext;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.function.*;

import static org.assertj.core.api.Fail.fail;

/**
 * Fluent sub-context for functional interface assertions.
 */
@Immutable
@ThreadSafe
@SuppressWarnings("unchecked")
public abstract class AbstractEvaluation<SELF extends AbstractEvaluation<SELF, CTX, C, X>, CTX extends Fluent<CTX>, C, X extends Exception>
        implements FluentSubcontext<SELF, CTX> {

    protected final @Nonnull  AssertionSupplier<C> assertSupplier;
    protected final @Nullable Consumer<C>             assertPreConsumer;

    protected final @Nonnull CTX context;

    protected AbstractEvaluation(
            @Nonnull CTX context,
            @Nullable Consumer<C> assertPreConsumer,
            @Nonnull AssertionSupplier<C> assertSupplier) {
        this.assertPreConsumer = assertPreConsumer;
        this.assertSupplier = assertSupplier;
        this.context = context;
    }

    protected AbstractEvaluation(@Nonnull CTX context, @Nullable AssertionsCheck assertPreConsumer, AssertionSupplier<C> assertSupplier) {
        this(context, assertPreConsumer == null ? null : (a) -> assertPreConsumer.assertionsCheck(), assertSupplier);
    }

    /** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
    public CTX soThat(@Nonnull AssertionsCheck assertions) {
        normalCheck(assertSupplier, assertPreConsumer, (a) -> assertions.assertionsCheck());
        return context.self();
    }

    public CTX withoutException() {
        exceptionCheck(assertSupplier, a -> {
        });
        return context.self();
    }

    /** Assertion for the failure of the method unther test. */
    public CTX withException(@Nonnull Consumer<AbstractThrowableAssert<?, ? extends Throwable>> assertions) {
        exceptionCheck(assertSupplier, assertions);
        return context.self();
    }

    protected static <RS, X extends Exception> void normalCheck(
            @Nonnull AssertionSupplier<RS> assertSupplier,
            @Nullable Consumer<RS> assertPreConsumer,
            @Nonnull Consumer<RS> assertConsumer
    ) {
        try {
            RS resultAssert = assertSupplier.get();

            try {
                if (assertPreConsumer != null) {
                    assertPreConsumer.accept(resultAssert);
                }
            } catch (AssertionError e) {
                throw new AssertionError("Recurring assertion failed." + e.getMessage(), e);
            }

            assertConsumer.accept(resultAssert);
        } catch (AssertionError e) {
            throw e;
        } catch (Throwable e) { // NOSONAR
            fail("Should evaluate without problem.", e);
        }
    }

    protected static <RS, X extends Exception> void exceptionCheck(
            @Nonnull AssertionSupplier<RS> assertSupplier,
            @Nonnull Consumer<AbstractThrowableAssert<?, ? extends Throwable>> assertConsumer
    ) {
        try {
            // supplier will fail with the
            assertSupplier.get();
            fail("Should evaluate with exception.");
        } catch (Throwable e) {  // NOSONAR
            assertConsumer.accept(Assertions.assertThat(e));
        }
    }

}