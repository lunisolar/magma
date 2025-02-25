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
import eu.lunisolar.magma.func.function.LBiFunction;
import eu.lunisolar.magma.func.supplier.LSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

import static eu.lunisolar.magma.basics.Null.nonNullArg;
import static java.util.logging.Level.FINEST;

public final class CallContexts {

	private final static Logger log = Logger.getLogger(CallContexts.class.getName());

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

		@Nullable
		Throwable doEnd(@Nullable C obj, @Nullable Throwable e) throws Throwable;

		@Override
		default @Nullable Object start() throws Throwable {
			return doStart();
		}

		@Override
		default @Nullable Throwable end(@Nullable Object obj, @Nullable Throwable e) throws Throwable {
			return doEnd((C) obj, e);
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <C> @Nonnull CallContext ctxHandling(@Nonnull LSupplier</* ? extends */C> starter, @Nonnull LBiFunction</* ? super */C, Throwable, Throwable> finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return new CallContexts.Easy<C>() {
			@Override
			public @Nullable C doStart() {
				return starter.shovingGet();
			}

			@Override
			public @Nullable Throwable doEnd(@Nullable C obj, @Nullable Throwable e) {
				return finisher.shovingApply(obj, e);
			}
		};
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <C> @Nonnull CallContext ctx(@Nonnull LSupplier</* ? extends */C> starter, @Nonnull LBiConsumer</* ? super */C, Throwable> finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return new CallContexts.Easy<C>() {
			@Override
			public @Nullable C doStart() {
				return starter.shovingGet();
			}

			@Override
			public @Nullable Throwable doEnd(@Nullable C obj, @Nullable Throwable e) {
				finisher.shovingAccept(obj, e);
				return null;
			}
		};
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static <C> @Nonnull CallContext ctx(@Nonnull LSupplier<? extends C> starter, @Nonnull LConsumer<? super C> finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return new CallContexts.Easy<C>() {
			@Override
			public @Nullable C doStart() {
				return starter.shovingGet();
			}

			@Override
			public @Nullable Throwable doEnd(@Nullable C obj, @Nullable Throwable e) {
				finisher.shovingAccept(obj);
				return null;
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
			public @Nullable Throwable doEnd(@Nullable Void obj, @Nullable Throwable e) {
				finisher.shovingExecute();
				return null;
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
				var newPrimary = alreadyInitialized.end(state, primary);
				if (newPrimary != null) {
					primary = newPrimary;
				}
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

	public static @Nonnull CallContext merge(@Nonnull CallContext... contexts) {
		return new MultiCallContext(contexts);
	}

	public static @Nonnull CallContext merge(@Nonnull Collection<CallContext> contexts) {
		return new MultiCallContext(contexts);
	}

	/**
	 * Reproduces logic (call sequence and exception handling) normally carried by call site (e.g. {@link LAction#nestingExecute(CallContext, LAction)},
	 * {@link CallContexts#tryInit(Object, CallContext)} and {@link CallContexts#tryFinish(Throwable, CallContext, Object)}),
	 * to merge two or more CallContext instances. Compared to using call site variant with two or more arguments for CallContext is
	 * that keeping the state together (between start and end) requires additional object allocation (array storing state objects for each context).
	 */
	private static final class MultiCallContext implements CallContext {

		private final CallContext[] contexts;

		private MultiCallContext(@Nonnull CallContext[] contexts) {
			nonNullArg(contexts, "contexts");
			this.contexts = Arrays.copyOf(contexts, contexts.length);
		}

		private MultiCallContext(@Nonnull Collection<CallContext> contexts) {
			nonNullArg(contexts, "contexts");
			this.contexts = contexts.toArray(CallContext[]::new);
		}

		@Nullable
		@Override
		public Object start() throws Throwable {
			Object[] state = null; // lazy allocation

			Object last = null;
			for (int i = 0; i < contexts.length; i++) {
				CallContext c = contexts[i];

				last = CallContexts.tryInit(last, c);
				if (last != null) {
					if (state == null) {
						state = new Object[contexts.length]; // lazy allocation
					}
					state[i] = last;
				}

				Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

				if (primary != null) {
					return tryFinish(i, primary, state);
				}
			}

			return state;
		}

		@Nullable
		private Throwable tryFinish(int i, Throwable primary, Object[] state) {
			if (i > 0) {
				// The outside logic does not know we have here multiple contexts that might be already initialized.
				if (state != null) {
					for (int rollbackIndex = i - 1; rollbackIndex >= 0; rollbackIndex--) {
						primary = CallContexts.tryFinish(primary, contexts[rollbackIndex], state[rollbackIndex]);
					}
				} else {
					for (int rollbackIndex = i - 1; rollbackIndex >= 0; rollbackIndex--) {
						primary = CallContexts.tryFinish(primary, contexts[rollbackIndex], null);
					}
				}
			}
			return primary;
		}

		@Override
		public @Nullable Throwable end(@Nullable Object obj, @Nullable Throwable argPrimary) throws Throwable {
			Object[] state = (Object[]) obj;

			var primary = tryFinish(contexts.length, argPrimary, state);

			if (primary != null && primary != argPrimary) {
				throw primary;
			} // else - "primary" must be already being handled.
			return null;
		}
	}

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
		public @Nullable Throwable end(@Nullable Object obj, @Nullable Throwable primary) throws Throwable {
			// NO-OP
			return null;
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
		public @Nullable Throwable end(@Nullable Object obj, @Nullable Throwable primary) {
			lock.unlock();
			return null;
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
		public @Nullable Throwable end(@Nullable Object obj, @Nullable Throwable primary) {
			semaphore.release(1);
			return null;
		}
	}

	// </editor-fold>

	//<editor-fold desc="Async">

	public static final @Nonnull AsyncCallContext COMMON_POOL = asyncCtx(ForkJoinPool.commonPool());
	public static final @Nonnull AsyncCallContext VIRTUAL = asyncCtx(Executors.newVirtualThreadPerTaskExecutor());

	public static AsyncCallContext asyncCtx(@Nonnull AsyncCallContext lambdaCapture) {
		return lambdaCapture;
	}

	public static @Nonnull AsyncCallContext asyncCtx(@Nonnull ExecutorService service) {
		return service::execute;
	}

	public static @Nonnull AsyncCallContext commonPool() {
		return COMMON_POOL;
	}

	public static @Nonnull AsyncCallContext virtual() {
		return VIRTUAL;
	}

	//</editor-fold>

	//<editor-fold desc="timeout">

	protected static class TimeoutObservation implements Runnable {

		private static final Thread.Builder OBSERVER_FACTORY = Thread.ofVirtual().name("CallContext.timeout", 0);

		private final TimeUnit unit;
		private final long timeout;
		private final boolean diligent;
		private final WeakReference<Thread> observed;
		private final WeakReference<Thread> observer;
		private final long start;
		private volatile long end = 0;
		private volatile boolean timedOut = false;

		protected TimeoutObservation(Thread observed, long timeout, TimeUnit unit, boolean diligent) {
			this.unit = unit;
			this.timeout = timeout;
			this.diligent = diligent;
			var observer = OBSERVER_FACTORY.unstarted(this);
			this.observer = new WeakReference<>(observer);
			this.observed = new WeakReference<>(observed);
			observer.setDaemon(true);
			this.start = System.nanoTime();
			observer.start();
		}

		@Override
		public void run() {
			try {
				if (log.isLoggable(FINEST)) {
					log.log(FINEST, "Observer: going to sleep for: {0} {1}.", new Object[]{timeout, unit});
				}
				unit.sleep(timeout);
				if (log.isLoggable(FINEST)) {
					log.log(FINEST, "Observer: sleep ended.");
				}
				synchronized (this) {
					if (end != 0) {
						if (log.isLoggable(FINEST)) {
							log.log(FINEST, "Observer: Seems observation ended already. Ignoring timeout.");
						}
						return;
					} else {
						if (log.isLoggable(FINEST)) {
							log.log(FINEST, "Observer: Timeout elapsed.");
						}
						timedOut = true;
					}
				}
				if (timedOut) {
					interruptObserved(diligent);
				}
			} catch (InterruptedException e) {
				Handling.ignore(e);
				if (log.isLoggable(FINEST)) {
					log.log(FINEST, "Observer: Interrupted.");
				}
			}
		}

		protected long elapsedTimeNanos() {
			return end - start;
		}

		protected boolean end() {
			if (log.isLoggable(FINEST)) {
				log.log(FINEST, "Ending timeout observation.");
			}
			synchronized (this) {
				if (end == 0) {
					end = System.nanoTime();
					interruptThread(observer);
				}
				if (timedOut) {
					//noinspection ResultOfMethodCallIgnored
					Thread.interrupted(); // Clears interrupted flag - exception should be already thrown (if not, then it is no longer needed).
				}
				return timedOut;
			}
		}

		private void interruptObserved(boolean diligent) {
			if (log.isLoggable(FINEST)) {
				log.log(FINEST, "Starting to interrupting observed thread: {0}", observed.get());
			}

			interruptObserved();

			if (diligent) {
				while (end == 0 && observed.get() != null) {
					Thread.onSpinWait();
					interruptObserved();
				}
			}
		}

		private void interruptObserved() {
			synchronized (this) {
				if (end == 0) {
					interruptThread(observed);
				}
			}
		}

		private static void interruptThread(WeakReference<Thread> threadRef) {
			Thread thread = threadRef.get();
			if (thread != null) {
				if (log.isLoggable(FINEST)) {
					log.log(FINEST, "Interrupting thread: {0}", thread);
				}
				thread.interrupt();
			}
		}
	}

	public static void checkInterrupted() {
		if (Thread.interrupted()) {
			Handling.shoveIt(new InterruptedException());
		}
	}

	/**
	 * Created {@link CallContext} that will observe called function execution and will try to interrupt the thread after a certain amount of time (timeout threshold).
	 *
	 * @param timeout Timeout threshold.
	 * @param diligent False - timeout would interrupt observed thread only once;
	 *                  True - observing thread will try to interrupt multiple times as long as the call context will not end
	 *                  (e.g. tu interrupt multiple independent sleep calls).
	 *
	 * @apiNote This context follows logic implemented in {@link Thread#sleep(long)} {@link Thread#isInterrupted()}. If the code of block that is called does not
	 * ever check interruption timeout will never happen. Likewise, all parts of code must react to {@link InterruptedException} and {@link TimeoutException} appropriately.
	 */
	public static CallContext timeout(long timeout, @Nonnull TimeUnit unit, boolean diligent) {
		nonNullArg(unit, "unit");
		return CallContexts.ctxHandling(() -> new TimeoutObservation(Thread.currentThread(), timeout, unit, diligent), (observation, e) -> {
			var timedOut = observation.end();
			if (timedOut && e instanceof InterruptedException interrupted) {
				// We are claiming it is our InterruptedException.
				TimeoutException timeoutEx = new TimeoutException("Timeout after %dns (threshold: %d %s).".formatted(observation.elapsedTimeNanos(), timeout, unit.name().toLowerCase()));
				timeoutEx.addSuppressed(interrupted);
				return timeoutEx;
			}
			return null;
		});
	}

	//</editor-fold>

	public static CallContext logThrowable(LBiConsumer<String, Throwable> logger) {
		nonNullArg(logger, "logger");
		return CallContexts.ctxHandling(() -> null, (__, e) -> {
			logger.accept(e.getMessage(), e);
			return null;
		});
	}

	public static CallContext logThrowable(String message, LBiConsumer<String, Throwable> logger) {
		nonNullArg(message, "message");
		nonNullArg(logger, "logger");
		return CallContexts.ctxHandling(() -> null, (__, e) -> {
			logger.accept(message.formatted(e.getMessage()), e);
			return null;
		});
	}

	public static CallContext logBoundary(String name, LConsumer<String> logger) {
		nonNullArg(name, "name");
		nonNullArg(logger, "logger");
		return CallContexts.ctxHandling(() -> {
			logger.accept("%s - start".formatted(name));
			return null;
		}, (__, e) -> {
			if (e != null) {
				logger.accept("%s - end with exception %s: %s".formatted(name, e.getClass().getName(), e.getMessage()));
			} else {
				logger.accept("%s - end".formatted(name));
			}
			return null;
		});
	}

}