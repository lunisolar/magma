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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.function.*;

/**
 * Fluent sub-context for functional interface assertions.
 */
@Immutable
@ThreadSafe
@SuppressWarnings("unchecked")
public final class SemiEvaluation<CTX extends FunctionalAssert.Simple<CTX, PC, A>, PC, A>
        extends AbstractEvaluation<SemiEvaluation<CTX, PC, A>, CTX, PC, A> {

    public SemiEvaluation(
            @Nonnull CTX context,
            @Nonnull Supplier<String> description,
            @Nonnull Supplier<String> caseDescription,
            @Nullable AssertionsCheck assertPreConsumer,
            @Nonnull AssertionFunction<PC, A> assertFunction) {
        super(context, description, caseDescription, assertPreConsumer, assertFunction);
    }
}