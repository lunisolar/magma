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
import eu.lunisolar.magma.func.supplier.LSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static eu.lunisolar.magma.func.supp.check.Checks.arg;
import static eu.lunisolar.magma.func.supp.check.Checks.check;

/**
 * @param <SUT> SUT - System Under Test / State of Unit Test
 */
public final class TestFlow<SUT> extends Sut.Base<SUT> {

	public static final String DEFAULT_DESCRIPTION = "...";

	/**
	 * Optional marker for a SUT/State of Unit Test objects.
	 */
	public static class State {

	}

	public TestFlow(SUT sut, @Nonnull LConsumer<String> logger) {
		super(sut, logger);
	}
	public static TestFlow<?> test() {
		return new TestFlow<>(null, System.out::println);
	}

	public TestFlow<?> logWith(@Nonnull LConsumer<String> logger) {
		arg(logger, "logger").mustEx(Be::notNullEx);
		return new TestFlow<>(sut(), logger);
	}

	// <editor-fold desc="Given">

	public <SUT> TestFlow<SUT> given(LSupplier<SUT> givenBlock) {
		return given(DEFAULT_DESCRIPTION, givenBlock);
	}

	public <SUT> TestFlow<SUT> given(@Nullable String description, LSupplier<SUT> givenBlock) {
		log("Given: " + description);
		arg(givenBlock).mustEx(Be::notNullEx);
		var sut = givenBlock.shovingGet();
		return new TestFlow<>(sut, logger());
	}

	// </editor-fold>

	// <editor-fold desc="activity">

	private TestFlow<SUT> activity(@Nonnull String activity, @Nullable String description, LConsumer<SUT> block) {
		arg(activity).mustEx(Be::notNullEx);
		log(activity + ": " + description);
		arg(block).mustEx(Be::notNullEx);
		block.shovingAccept(sut());
		return this;
	}

	// </editor-fold>

	public TestFlow<SUT> and(@Nullable String description) {
		return and(description, sut -> {
		});
	}
	public TestFlow<SUT> and(@Nonnull LConsumer<SUT> block) {
		return and(DEFAULT_DESCRIPTION, block);
	}
	public TestFlow<SUT> and(@Nullable String description, @Nonnull LConsumer<SUT> block) {
		return activity("And", description, block);
	}

	public TestFlow<SUT> precondition(@Nullable String description) {
		return precondition(description, sut -> {
		});
	}
	public TestFlow<SUT> precondition(LConsumer<SUT> block) {
		return precondition(DEFAULT_DESCRIPTION, block);
	}
	public TestFlow<SUT> precondition(@Nullable String description, LConsumer<SUT> block) {
		return activity("Precondition", description, block);
	}

	public TestFlow<SUT> when(@Nullable String description) {
		return when(description, sut -> {
		});
	}
	public TestFlow<SUT> when(LConsumer<SUT> block) {
		return when(DEFAULT_DESCRIPTION, block);
	}
	public TestFlow<SUT> when(@Nullable String description, LConsumer<SUT> block) {
		return activity("When", description, block);
	}

	public TestFlow<SUT> then(@Nullable String description) {
		return then(description, sut -> {
		});
	}
	public TestFlow<SUT> then(LConsumer<SUT> block) {
		return then(DEFAULT_DESCRIPTION, block);
	}
	public TestFlow<SUT> then(@Nullable String description, LConsumer<SUT> block) {
		return activity("Then", description, block);
	}

	public TestFlow<SUT> continuing() {
		return continuing(DEFAULT_DESCRIPTION, sut -> {
		});
	}
	public TestFlow<SUT> continuing(@Nullable String description) {
		return continuing(description, sut -> {
		});
	}
	public TestFlow<SUT> continuing(@Nonnull LConsumer<SUT> block) {
		return continuing(DEFAULT_DESCRIPTION, block);
	}
	public TestFlow<SUT> continuing(@Nullable String description, @Nonnull LConsumer<SUT> block) {
		return activity("Continuing", description, block);
	}

	public TestFlow<SUT> aftermath(@Nullable String description) {
		return aftermath(description, sut -> {
		});
	}
	public TestFlow<SUT> aftermath(LConsumer<SUT> block) {
		return aftermath(DEFAULT_DESCRIPTION, block);
	}
	public TestFlow<SUT> aftermath(@Nullable String description, LConsumer<SUT> block) {
		return activity("Aftermath", description, block);
	}

	// <editor-fold desc="step">

	public TestFlow<SUT> step_(@Nonnull String step, @Nullable String description, LConsumer<TestFlow<SUT>> consumer) {
		arg(step).mustEx(Be::notNullEx);
		log(step + ": " + description);
		consumer.shovingAccept(this);
		return this;
	}
	// </editor-fold>

	public TestFlow<SUT> step(LConsumer<TestFlow<SUT>> consumer) {
		return step(DEFAULT_DESCRIPTION, consumer);
	}
	public TestFlow<SUT> step(@Nullable String description, LConsumer<TestFlow<SUT>> consumer) {
		return step_("STEP", description, consumer);
	}

}