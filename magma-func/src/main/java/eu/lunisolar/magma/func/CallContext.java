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

import javax.annotation.Nullable;

/**
 * Instance of CallContext is called before ({@link CallContext#start()}) and after ({@link CallContext#end(Object, Throwable)}) the call to the function.
 * Instance(s) of the CallContext are on the call sites (e.g. calls to {@link LAction#nestingExecute(CallContext, LAction)}.
 * {@link CallContext} implementation might provide, for example, logging, transactions, time measurements.
 *
 * Implementation of a call site ({@link LAction#nestingExecute(CallContext, LAction)}) allows potentially
 * to reuse decoupled logic blocks and keep minimum object allocation while doing it (depends on CallContext implementation really).
 * Logic blocks being: try-catch-finally and function implementation.
 */
public interface CallContext {

	/**
	 * Starting boundary of the context for the call. Returned object is hold for the duration of the call and then passed to the
	 * {@link CallContext#end(Object, Throwable)}.
	 * If {@link CallContext#start()} will throw OR return exception the context is considered as not started and no call to the
	 * {@link CallContext#end(Object, Throwable)} is made.
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
	 *                Propagation and suppression of exceptions is also carried outside of the {@link CallContext}
	 *                (see {@link CallContexts#tryInit(Object, CallContext)} and {@link CallContexts#tryFinish(Throwable, CallContext, Object)}})
	 */
	void end(@Nullable Object obj, @Nullable Throwable primary) throws Throwable;

}
