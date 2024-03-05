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

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;

import static eu.lunisolar.magma.basics.Null.nonNullArg;

public final class CallContexts {

	// <editor-fold desc="no-instance">
	private CallContexts() {
		throw new RuntimeException();
	}
	// </editor-fold>

	// <editor-fold desc="ctx">

	/** {@link CallContext} variant that is slightly easier to implement. */
	@SuppressWarnings("unchecked")
	public interface Easy<C> extends CallContext {

		@Nullable
		C doStart() throws Throwable;
		void doEnd(@Nullable C obj, @Nullable Throwable e) throws Throwable;

		@Override
		default @Nullable Object start() throws Throwable {
			return doStart();
		}
		@Override
		default void end(@Nullable Object obj, @Nullable Throwable e) throws Throwable {
			doEnd((C) obj, e);
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <C> @Nonnull CallContext ctx(@Nonnull LSupplier<C> starter, @Nonnull LBiConsumer<C, Throwable> finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return new CallContexts.Easy<C>() {
			@Override
			public @Nullable C doStart() {
				return starter.shovingGet();
			}
			@Override
			public void doEnd(@Nullable C obj, @Nullable Throwable e) {
				finisher.shovingAccept(obj, e);
			}
		};
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <C> @Nonnull CallContext ctx(@Nonnull LSupplier<C> starter, @Nonnull LConsumer<C> finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return new CallContexts.Easy<C>() {
			@Override
			public @Nullable C doStart() {
				return starter.shovingGet();
			}
			@Override
			public void doEnd(@Nullable C obj, @Nullable Throwable e) {
				finisher.shovingAccept(obj);
			}
		};
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static @Nonnull CallContext ctx(@Nonnull LAction starter, @Nonnull LAction finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return new CallContexts.Easy<Void>() {
			@Override
			public @Nullable Void doStart() {
				starter.shovingExecute();
				return null;
			}
			@Override
			public void doEnd(@Nullable Void obj, @Nullable Throwable e) {
				finisher.shovingExecute();
			}
		};
	}

	/**
	 * Subsequent calls to this method are working like start of  <code>try {} finally { }</code> block.
	 * Works with {@link CallContexts#tryFinish(Throwable, CallContext, Object)} as counterpart.
	 * Requires to obey special contract considerations when calling (e.g. {@link LAction#executeX(CallContext, LAction)} X()}),
	 * especially in regard to exception handling and propagation that is distributed between those two methods and a calling site.
	 */
	public static @Nullable Object tryInit(Object potentialPrimaryException, CallContext notInitialized) {
		if (potentialPrimaryException instanceof Throwable) {
			return potentialPrimaryException;
		}

		if (notInitialized != null) {
			try {
				return notInitialized.start();
			} catch (Throwable e) {
				return e; // new primary
			}
		} else {
			return null;
		}
	}

	/**
	 * Subsequent calls to this method are working like <code>finally { }</code> block.
	 * Works with {@link CallContexts#tryInit(Object, CallContext)} as counterpart.
	 * Requires to obey special contract considerations when calling (e.g. {@link LAction#executeX(CallContext, LAction)} X()}),
	 * especially in regard to exception handling and propagation that is distributed between those two methods and a calling site.
	 */
	public static @Nullable Throwable tryFinish(Throwable primary, CallContext alreadyInitialized, Object state) {
		if (state != null && state == primary) {
			return primary; // Context failed to initialize.
		}

		if (alreadyInitialized != null) {
			try {
				alreadyInitialized.end(state, primary);
			} catch (Throwable e) {
				// 'primary' is expected to be thrown by a call site.
				if (primary != null) {
					if (e instanceof Error && !(primary instanceof Error)) {
						// Error has precedence.
						e.addSuppressed(primary);
						return e;
					}
					primary.addSuppressed(e);
				} else {
					return e;
				}
			}
		}
		return primary;
	}

	// </editor-fold>

	// <editor-fold desc="No-Op">

	private static final @Nonnull NoOpCallContext NO_OP_CALL_CONTEXT = new NoOpCallContext();

	public static @Nonnull CallContext noOp() {
		return NO_OP_CALL_CONTEXT;
	}

	private final static class NoOpCallContext implements CallContext {

		@Nullable
		@Override
		public Object start() throws Throwable {
			// NO-OP
			return null;
		}

		@Override
		public void end(@Nullable Object obj, @Nullable Throwable primary) throws Throwable {
			// NO-OP
		}
	}

	// </editor-fold>

	// <editor-fold desc="lock context">

	public static CallContext lockContext(@Nonnull Lock lock) {
		nonNullArg(lock, "lock");
		return new LockContext(lock) {
			@Nullable
			@Override
			public Object start() throws Throwable {
				lock.lock();
				return null;
			}

		};
	}

	public static @Nonnull CallContext lockContext(@Nonnull Lock lock, long time, TimeUnit unit) {
		nonNullArg(lock, "lock");
		nonNullArg(unit, "unit");
		var timeoutNs = unit.toNanos(time);
		return new LockContext(lock) {
			@Nullable
			@Override
			public Object start() {
				try {
					var hasLock = lock.tryLock(timeoutNs, TimeUnit.NANOSECONDS);
					if (!hasLock) {
						throw Handling.throwIt(Handling.create(TimeoutException::new, "Lock acquisition timeout: %s", lock));
					}
				} catch (InterruptedException e) {
					throw Handling.throwIt(e);
				}
				return null;
			}
		};
	}

	private static abstract class LockContext implements CallContext {

		protected final Lock lock;

		public LockContext(@Nonnull Lock lock) {
			nonNullArg(lock, "lock");
			this.lock = lock;
		}

		@Override
		public void end(@Nullable Object obj, @Nullable Throwable primary) {
			lock.unlock();
		}
	}

	// </editor-fold>

	// <editor-fold desc="Semaphore">

	public static @Nonnull CallContext semaphoreContext(int permits, @Nonnull Semaphore semaphore) {
		nonNullArg(semaphore, "semaphore");
		return new SemaphoreContext(semaphore, permits) {
			@Nullable
			@Override
			public Object start() throws Throwable {
				semaphore.acquire(this.permits);
				return null;
			}

		};
	}

	public static CallContext semaphoreContext(int permits, @Nonnull Semaphore semaphore, long time, TimeUnit unit) {
		nonNullArg(semaphore, "semaphore");
		nonNullArg(unit, "unit");
		var timeoutNs = unit.toNanos(time);
		return new SemaphoreContext(semaphore, permits) {
			@Nullable
			@Override
			public Object start() {
				try {
					var hasLock = semaphore.tryAcquire(this.permits, timeoutNs, TimeUnit.NANOSECONDS);
					if (!hasLock) {
						throw Handling.throwIt(Handling.create(TimeoutException::new, "Semaphore acquisition timeout: %s", semaphore));
					}
				} catch (InterruptedException e) {
					throw Handling.throwIt(e);
				}
				return null;
			}
		};
	}

	private static abstract class SemaphoreContext implements CallContext {

		protected final Semaphore semaphore;
		protected final int permits;

		public SemaphoreContext(@Nonnull Semaphore semaphore, int permits) {
			nonNullArg(semaphore, "semaphore");
			this.semaphore = semaphore;
			this.permits = permits;
		}

		@Override
		public void end(@Nullable Object obj, @Nullable Throwable primary) {
			semaphore.release(1);
		}
	}

	// </editor-fold>

}
