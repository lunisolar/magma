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
 * Instance of CallContext is called before ({@link CallContext#start()}) and after ({@link CallContext#end(Object, Object)}) the call to the function.
 * Instance(s) of the CallContext are on the call sites (e.g. calls to {@link LAction#nestingExecute(CallContext, LAction)}.
 * {@link CallContext} implementation might provide, for example, logging, transactions, time measurements.
 *
 * Implementation of a call site ({@link LAction#nestingExecute(CallContext, LAction)}) allows potentially
 * to reuse decoupled logic blocks and keep minimum object allocation while doing it (depends on CallContext implementation really).
 * Logic blocks being: try-catch-finally and function implementation.
 */
public interface CallContext {

	/**
	* If this specific instance is returned by {@link CallContext#start()} then the call to function will be quietly skipped.
	* In terms of how other {@link CallContext} instances will be handled - it would work just similar to situation where {@link CallContext#start()} failed.
	* (since all started {@link CallContext} istances do expect call to {@link CallContext#end(Object, Object)}).
	* If nothing else happen (e.g., outer {@link CallContext#end(Object, Object)} fails) no exception will be thrown,
	* and if the function has codomain (expected result), then null will be returned.
	*/
	public static final ReasonToNotInvoke CONDITION_NOT_MET = new ReasonToNotInvoke("Condition was not met.");

	/**
	 * Starting boundary of the context for the call. Returned object is hold for the duration of the call and then passed to the
	 * {@link CallContext#end(Object, Object)}.
	 * If {@link CallContext#start()} will throw OR return exception (Or instance of {@link ReasonToNotInvoke), the context is considered as not started and no call to the
	 * {@link CallContext#end(Object, Object)} is made.
	 */
	@Nullable
	Object start() throws Throwable;

	/**
	 * End boundary of the context for the call.
	 *
	 * @param state   Object returned by {@link CallContext#start()}
	 * @param primary Exception (or ReasonToNotInvoke) created either during the invocation of function or initialization of another (next in line) CallContext.
	 *                Rethrow of this exception is
	 *                carried elsewhere. It should be used only for information, e.g. things like: TX commit/rollback decision.
	 *                {@link CallContext#end(Object, Object)} is allowed to throw its own exception.
	 *                Propagation and suppression of exceptions is also carried outside of the {@link CallContext}
	 *                (see {@link CallContexts#tryInit(Object, CallContext)} and {@link CallContexts#tryFinish(Object, CallContext, Object)}})
	 * @return Instance of exception, if the primary exception is to be overridden. For this specific instance,
	 *         it is the responsibility of the CallContext to keep the new exception connected with the cause
	 *         (or simply use {@link Exception#addSuppressed(Throwable)}.
	 */
	@Nullable
	Throwable end(@Nullable Object state, @Nullable Object primary) throws Throwable;

	/**
	 * User is free to use {@link CallContext#CONDITION_NOT_MET} as a reason to not invoke the function or to create own instances.
	 */
	public record ReasonToNotInvoke(String reason) {
	}

}
