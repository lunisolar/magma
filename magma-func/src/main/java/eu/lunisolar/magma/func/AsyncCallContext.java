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

import eu.lunisolar.magma.func.action.LAction;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public interface AsyncCallContext {

	public static final @Nonnull AsyncCallContext COMMON_POOL = ctx(ForkJoinPool.commonPool());

	void call(@Nonnull LAction functionCall);

	static AsyncCallContext ctx(@Nonnull AsyncCallContext lambdaCapture) {
		return lambdaCapture;
	}

	static @Nonnull AsyncCallContext ctx(@Nonnull ExecutorService service) {
		return service::execute;
	}

	static @Nonnull AsyncCallContext commonPool() {
		return COMMON_POOL;
	}

}
