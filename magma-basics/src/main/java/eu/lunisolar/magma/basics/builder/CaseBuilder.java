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

package eu.lunisolar.magma.basics.builder;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.function.*;

@Immutable
@ThreadSafe
public final class CaseBuilder<P, PC extends PartialCase> {

    private final Function<P, PC> partialCaseFactory;

    public CaseBuilder(Function<P, PC> partialCaseFactory) {
        this.partialCaseFactory = partialCaseFactory;
    }

    @Nonnull
    public final PC of(@Nonnull P casePredicate) {
        return partialCaseFactory.apply(casePredicate);
    }
}