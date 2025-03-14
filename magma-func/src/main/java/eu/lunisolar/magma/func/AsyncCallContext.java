/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func;

import eu.lunisolar.magma.func.supplier.LSupplier;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public interface AsyncCallContext {

	@Nonnull
	CompletableFuture<?> call_(@Nonnull LSupplier<?> functionCall);

	default <T> @Nonnull CompletableFuture<T> call(@Nonnull LSupplier<T> functionCall) {
		return (CompletableFuture<T>) call_(functionCall);
	}

}