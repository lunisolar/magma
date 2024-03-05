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

import eu.lunisolar.magma.basics.exceptions.Handling;
import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.supplier.LSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static eu.lunisolar.magma.basics.Null.nonNullArg;

public interface CallContext {

	/**
	 * Starting boundary of the context for the call. Returned object is hold for the duration of the call and then passed to the {@link
	 * CallContext#end(Object, Throwable)}. If {@link CallContext#start()} will throw OR return exception the context is considered as not started and no call to the {@link
	 * CallContext#end(Object, Throwable)}
	 * is made.
	 */
	@Nullable
	Object start() throws Throwable;

	/**
	 * End boundary of the context for the call.
	 *
	 * @param obj     Object returned by {@link CallContext#start()}
	 * @param primary Exception created either during the call or initialization of another (next in line) CallContext. Rethrow of this exception is
	 *                carried elsewhere. It should be used only for information, e.g. things like: TX commit/rollback decision.
	 *                {@link CallContext#end(Object, Throwable)} is allowed to throw its own exception.
	 */
	void end(@Nullable Object obj, @Nullable Throwable primary) throws Throwable;

}
