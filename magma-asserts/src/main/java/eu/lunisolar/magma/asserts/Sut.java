/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.asserts;

import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.supp.Be;

import javax.annotation.Nonnull;

import static eu.lunisolar.magma.func.supp.check.Checks.arg;

public interface Sut<SUT> {

	SUT sut();

	LConsumer<String> logger();

	default void log(String message) {
		arg(message, "message").mustEx(Be::notNullEx);
		logger().accept(message);
	}

	public static class Base<SUT> implements Sut<SUT> {

		private final SUT sut;
		private final LConsumer<String> logger;

		public Base(SUT sut, @Nonnull LConsumer<String> logger) {
			arg(logger, "logger").mustEx(Be::notNullEx);
			this.sut = sut;
			this.logger = logger;
		}

		@Override
		public SUT sut() {
			return sut;
		}

		@Override
		public LConsumer<String> logger() {
			return logger;
		}
	}

}