/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
	CallContext NOOP = LSupplier::getX;

	/**
	 * Executes call within expected context (be it transaction, logging, time measuring), and propagates the answer.
	 * There is no access to function arguments.
	 * Generalized (Object, ?) so the CallContext could be implemented with lambda, without CallContext itself having generic argument.
	 * Unless it is intended for the user to receive exactly that exception, implementation must not use
	 * {@link eu.lunisolar.magma.basics.exceptions.NestedException} or method wrapping to it.
	 * Checked vs uncheck behaviour will be decided by user by calling either {@link eu.lunisolar.magma.func.function.LBiFunction#shovingApply(Object, Object)}
	 * or {@link eu.lunisolar.magma.func.function.LBiFunction#nestingApply(Object, Object)}.
	 */
	Object call(@Nonnull LSupplier<?> function) throws Throwable;

	default @Nonnull CallContext and(@Nonnull CallContext other) {
		return combine(other);
	}

	default @Nonnull CallContext combine(@Nonnull CallContext other) {
		nonNullArg(other, "[other] cannot be null.");
		return function -> this.call(() -> other.call(function));
	}

	static @Nonnull CallContext cc(@Nonnull CallContext... ccs) {
		return combine(ccs);
	}

	static @Nonnull CallContext combine(@Nonnull CallContext... ccs) {
		if (ccs == null || ccs.length == 0) {
			return NOOP;
		}

		CallContext result = null;
		for (CallContext cc : ccs) {
			if (result != null) {
				result.combine(cc);
			} else {
				result = cc;
			}
		}
		return result;
	}

	static CallContext ctx(@Nonnull CallContext lambdaCapture) {
		return lambdaCapture;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static <C> CallContext ctx(@Nonnull LSupplier<C> starter, @Nonnull LBiConsumer<C, Throwable> finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return call -> {
			var c = starter.getX();
			Object v = null;
			try {
				v = call.getX();
			} catch (Throwable primary) {
				try {
					finisher.acceptX(c, primary);
				} catch (Throwable secondary) {
					Handling.throwWithSuppression(primary, secondary);
				}

				Handling.throwIt(primary);
			}
			finisher.acceptX(c, null);
			return v;
		};
	}

	@Nullable
	static <C> Object shovingCallWithHandling(@Nonnull LSupplier<C> starter, @Nonnull LBiConsumer<C, Throwable> finisher, @Nonnull LSupplier<?> call) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		nonNullArg(call, "[call] cannot be null.");
		var c = starter.shovingGet();
		Object v = null;
		try {
			v = call.getX();
		} catch (Throwable primary) {
			try {
				finisher.acceptX(c, primary);
			} catch (Throwable secondary) {
				Handling.throwWithSuppression(primary, secondary);
			}

			Handling.throwIt(primary);
		}
		finisher.shovingAccept(c, null);
		return v;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static <C> CallContext ctx(@Nonnull LSupplier<C> starter, @Nonnull LConsumer<C> finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return call -> {
			var c = starter.getX();
			Object v = null;
			try {
				v = call.getX();
			} catch (Throwable primary) {
				try {
					finisher.acceptX(c);
				} catch (Throwable secondary) {
					Handling.throwWithSuppression(primary, secondary);
				}

				Handling.throwIt(primary);
			}
			finisher.acceptX(c);
			return v;
		};
	}

	@Nullable
	static <C> Object shovingCallWithHandling(@Nonnull LSupplier<C> starter, @Nonnull LConsumer<C> finisher, @Nonnull LSupplier<?> call) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		nonNullArg(call, "[call] cannot be null.");
		var c = starter.shovingGet();
		Object v = null;
		try {
			v = call.getX();
		} catch (Throwable primary) {
			try {
				finisher.acceptX(c);
			} catch (Throwable secondary) {
				Handling.throwWithSuppression(primary, secondary);
			}

			Handling.throwIt(primary);
		}
		finisher.shovingAccept(c);
		return v;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static CallContext ctx(@Nonnull LAction starter, @Nonnull LAction finisher) {
		nonNullArg(starter, "[starter] cannot be null.");
		nonNullArg(finisher, "[finisher] cannot be null.");
		return ctx(() -> {
			starter.executeX();
			return null;
		}, ignored -> finisher.executeX());
	}

}
