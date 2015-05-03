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
public final class SemiEvaluation<CTX extends FunctionalAssert.Simple<CTX, A, X>, A, X extends Exception>
        extends AbstractEvaluation<SemiEvaluation<CTX, A, X>, CTX, Object, X> {

    public SemiEvaluation(
            @Nonnull CTX context,
            @Nullable AssertionsCheck assertPreConsumer,
            @Nonnull ThrowableAssert.ThrowingCallable callWrapper) {
        super(context, assertPreConsumer, () -> {
            callWrapper.call();
            return null;
        });
    }
}