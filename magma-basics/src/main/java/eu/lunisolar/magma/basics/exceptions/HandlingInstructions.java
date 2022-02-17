/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.basics.exceptions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@FunctionalInterface
public interface HandlingInstructions<X extends Throwable, Y extends Throwable> {

    void processWith(Handler<X> handler) throws Y;

    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrFail(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {
        return Handling.handleOrFail(throwable, instructions);
    }

    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrNest(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {
        return Handling.handleOrNest(throwable, instructions);
    }

    public static <X extends Throwable, Y extends Throwable> RuntimeException handleOrPropagate(
            @Nonnull X throwable, @Nullable HandlingInstructions<X, Y> instructions) throws Y {
        return Handling.handleOrPropagate(throwable, instructions);
    }

}
