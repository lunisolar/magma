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

import org.assertj.core.api.ThrowableAssert;

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
public final class SemiEvaluation<CTX extends FunctionalAssert.Simple<CTX, PC, A, X>, PC, A, X extends Exception>
        extends AbstractEvaluation<SemiEvaluation<CTX, PC, A, X>, CTX, PC, A, X> {

    public SemiEvaluation(
            @Nonnull CTX context,
            @Nullable AssertionsCheck assertPreConsumer,
            @Nonnull AssertionFunction<PC, A> assertFunction) {
        super(context, assertPreConsumer, assertFunction);
    }
}