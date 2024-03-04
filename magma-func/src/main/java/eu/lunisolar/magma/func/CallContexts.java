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

	public static CallContext lockContext(@Nonnull Lock lock, long time, TimeUnit unit) {
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

	public static CallContext semaphoreContext(int permits, @Nonnull Semaphore semaphore) {
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
