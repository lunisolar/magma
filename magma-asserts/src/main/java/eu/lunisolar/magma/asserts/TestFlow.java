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

package eu.lunisolar.magma.asserts;

import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supplier.LSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static eu.lunisolar.magma.func.supp.check.Checks.arg;

/**
 * This class is intended to be used exclusively in a fluent way.
 *
 * @param <STAGE> Object representing the test stage. Could be just directly tested unit or more complex stage, with multiple tracked references and states.
 */
public final class TestFlow<STAGE> implements TestFlowTraits.Junction<STAGE, TestFlow<STAGE>>, TestFlowTraits.Step<STAGE, TestFlow<STAGE>> {

	public static final String DEFAULT_DESCRIPTION = "...";

	/**
	 * Optional marker for a SUT/State of Unit Test objects.
	 */
	public static class State {

	}

	/**
	 * Optional marker for a SUT/State of Unit Test objects.
	 */
	public static class Stage extends State {

	}

	private final STAGE stage;
	private final LConsumer<String> logger;

	private TestFlow(STAGE stage, @Nonnull LConsumer<String> logger) {
		arg(logger, "logger").mustEx(Be::notNullEx);
		this.stage = stage;
		this.logger = logger;
	}

	private static @Nonnull TestFlow<?> testFlow(@Nonnull LConsumer<String> logger) {
		arg(logger, "logger").mustEx(Be::notNullEx);
		return new TestFlow<>(null, logger);
	}

	public static @Nonnull TestFlow<?> test(@Nonnull LConsumer<String> logger) {
		return testFlow(logger);
	}

	public static @Nonnull TestFlow<?> test(String description, @Nonnull LConsumer<String> logger) {
		return testFlow(logger).log("Test: " + description);
	}
	public static @Nonnull TestFlow<?> test(String description) {
		return test(description, System.out::println);
	}
	public static @Nonnull TestFlow<?> test() {
		return test(DEFAULT_DESCRIPTION, System.out::println);
	}

	public static @Nonnull TestFlow<?> scenario(@Nonnull LConsumer<String> logger) {
		return testFlow(logger);
	}
	public static @Nonnull TestFlow<?> scenario(String description, @Nonnull LConsumer<String> logger) {
		return testFlow(logger).log("Test: " + description);
	}
	public static @Nonnull TestFlow<?> scenario(String description) {
		return scenario(description, System.out::println);
	}
	public static @Nonnull TestFlow<?> scenario() {
		return scenario(DEFAULT_DESCRIPTION, System.out::println);
	}

	@Override
	public STAGE stage() {
		return null;
	}

	public @Nonnull TestFlow<STAGE> log(String message) {
		arg(message, "message").mustEx(Be::notNullEx);
		logger.accept(message);
		return this;
	}

	// <editor-fold desc="Given">

	public <STAGE> @Nonnull TestFlow<STAGE> given(@Nonnull LSupplier<STAGE> givenBlock) {
		return given(DEFAULT_DESCRIPTION, givenBlock);
	}

	public <SUT> @Nonnull TestFlow<SUT> given(@Nullable String description, @Nonnull LSupplier<SUT> givenBlock) {
		log("Given: " + description);
		arg(givenBlock).mustEx(Be::notNullEx);
		var sut = givenBlock.shovingGet();
		return new TestFlow<>(sut, logger);
	}

	// </editor-fold>

	public @Nonnull TestFlow<STAGE> testFlow(@Nonnull String phase, @Nullable String description, LConsumer<TestFlowTraits.Step<STAGE, ?>> consumer) {
		arg(phase).mustEx(Be::notNullEx);
		log(phase + ": " + description);
		consumer.shovingAccept(this);
		return this;
	}

	public <STEP_STAGE> @Nonnull TestFlow<STAGE> testFlowWith(@Nonnull String phase, @Nullable String description, @Nonnull LSupplier<STEP_STAGE> stepStageBlock, LConsumer<TestFlowTraits.Step2<STAGE, STEP_STAGE, ?>> consumer) {
		arg(phase).mustEx(Be::notNullEx);
		log(phase + ": " + description);
		var stepStage = stepStageBlock.shovingGet();
		consumer.shovingAccept(new TS2<>() {

			@Override
			public STAGE stage() {
				return TestFlow.this.stage();
			}

			@Override
			public STEP_STAGE stepStage() {
				return stepStage;
			}

			@Nonnull
			public TS2<STAGE, STEP_STAGE> activity(@Nonnull String activity, @Nullable String description, @Nonnull LConsumer<STAGE> block) {
				TestFlow.this.activity(activity, description, block);
				return this;
			}

			@Nonnull
			@Override
			public TS2<STAGE, STEP_STAGE> activity(@Nonnull String activity, @Nullable String description, LBiConsumer<STAGE, STEP_STAGE> block) {
				return activity(activity, description, stage -> block.accept(stage, stepStage));
			}

		});
		return this;
	}

	public @Nonnull TestFlow<STAGE> activity(@Nonnull String activity, @Nullable String description, @Nonnull LConsumer<STAGE> block) {
		arg(activity).mustEx(Be::notNullEx);
		log(activity + ": " + description);
		arg(block).mustEx(Be::notNullEx);
		block.shovingAccept(stage);
		return this;
	}

	private static abstract class TS2<STAGE, STEP_STAGE> implements TestFlowTraits.Step2<STAGE, STEP_STAGE, TS2<STAGE, STEP_STAGE>> {

	}
}