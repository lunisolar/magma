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

import eu.lunisolar.magma.basics.meta.SelfReferencing;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.supplier.LSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static eu.lunisolar.magma.asserts.TestFlow.DEFAULT_DESCRIPTION;

public final class TestFlowTraits {

	private TestFlowTraits() {
		throw new RuntimeException("No instance.");
	}

	public interface Step<STAGE, SELF extends Step<STAGE, SELF>> extends SelfReferencing<SELF> {

		STAGE stage();

		default @Nonnull SELF and(@Nullable String description) {
			return activity("And", description, sut -> {
			});
		}

		default @Nonnull SELF and(@Nonnull LConsumer<STAGE> block) {
			return activity("And", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF and(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("And", description, block);
		}

		default @Nonnull SELF precondition(@Nullable String description) {
			return activity("Precondition", description, sut -> {
			});
		}

		default @Nonnull SELF precondition(@Nonnull LConsumer<STAGE> block) {
			return activity("Precondition", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF precondition(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Precondition", description, block);
		}

		default @Nonnull SELF when(@Nullable String description) {
			return activity("When", description, sut -> {
			});
		}

		default @Nonnull SELF when(@Nonnull LConsumer<STAGE> block) {
			return activity("When", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF when(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("When", description, block);
		}

		default @Nonnull SELF then(@Nullable String description) {
			return activity("Then", description, sut -> {
			});
		}

		default @Nonnull SELF then(@Nonnull LConsumer<STAGE> block) {
			return activity("Then", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF then(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Then", description, block);
		}

		default @Nonnull SELF expect(@Nullable String description) {
			return activity("Expect", description, sut -> {
			});
		}

		default @Nonnull SELF expect(@Nonnull LConsumer<STAGE> block) {
			return activity("Expect", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF expect(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Expect", description, block);
		}

		default @Nonnull SELF continuing(@Nullable String description) {
			return activity("Continuing", description, sut -> {
			});
		}

		default @Nonnull SELF continuing(@Nonnull LConsumer<STAGE> block) {
			return activity("Continuing", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF continuing(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Continuing", description, block);
		}

		default @Nonnull SELF aftermath(@Nullable String description) {
			return activity("Aftermath", description, sut -> {
			});
		}

		default @Nonnull SELF aftermath(@Nonnull LConsumer<STAGE> block) {
			return activity("Aftermath", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF aftermath(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Aftermath", description, block);
		}

		default @Nonnull SELF sanityCheck(@Nullable String description) {
			return activity("Sanity Check", description, sut -> {
			});
		}

		default @Nonnull SELF sanityCheck(@Nonnull LConsumer<STAGE> block) {
			return activity("Sanity Check", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF sanityCheck(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Sanity Check", description, block);
		}

		default @Nonnull SELF setup(@Nullable String description) {
			return activity("Setup", description, sut -> {
			});
		}

		default @Nonnull SELF setup(@Nonnull LConsumer<STAGE> block) {
			return activity("Setup", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF setup(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Setup", description, block);
		}

		default @Nonnull SELF cleanup(@Nullable String description) {
			return activity("Cleanup", description, sut -> {
			});
		}

		default @Nonnull SELF cleanup(@Nonnull LConsumer<STAGE> block) {
			return activity("Cleanup", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF cleanup(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Cleanup", description, block);
		}

		default @Nonnull SELF await(@Nullable String description) {
			return activity("Await", description, sut -> {
			});
		}

		default @Nonnull SELF await(@Nonnull LConsumer<STAGE> block) {
			return activity("Await", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF await(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Await", description, block);
		}

		default @Nonnull SELF meantime(@Nullable String description) {
			return activity("Meantime", description, sut -> {
			});
		}

		default @Nonnull SELF meantime(@Nonnull LConsumer<STAGE> block) {
			return activity("Meantime", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF meantime(@Nullable String description, @Nonnull LConsumer<STAGE> block) {
			return activity("Meantime", description, block);
		}

		default @Nonnull SELF continuing() {
			return continuing(DEFAULT_DESCRIPTION, sut -> {
			});
		}

		@Nonnull
		SELF activity(@Nonnull String activity, @Nullable String description, @Nonnull LConsumer<STAGE> block);
	}

	public interface Step2<STAGE, STEP_STAGE, SELF extends Step2<STAGE, STEP_STAGE, SELF>> extends Step<STAGE, SELF>, SelfReferencing<SELF> {

		STEP_STAGE stepStage();

		default @Nonnull SELF and(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("And", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF and(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("And", description, block);
		}

		default @Nonnull SELF precondition(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Precondition", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF precondition(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Precondition", description, block);
		}

		default @Nonnull SELF when(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("When", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF when(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("When", description, block);
		}

		default @Nonnull SELF then(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Then", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF then(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Then", description, block);
		}

		default @Nonnull SELF expect(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Expect", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF expect(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Expect", description, block);
		}

		default @Nonnull SELF continuing(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Continuing", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF continuing(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Continuing", description, block);
		}

		default @Nonnull SELF aftermath(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Aftermath", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF aftermath(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Aftermath", description, block);
		}

		default @Nonnull SELF sanityCheck(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Sanity Check", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF sanityCheck(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Sanity Check", description, block);
		}

		default @Nonnull SELF setup(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Setup", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF setup(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Setup", description, block);
		}

		default @Nonnull SELF cleanup(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Cleanup", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF cleanup(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Cleanup", description, block);
		}

		default @Nonnull SELF await(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Await", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF await(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Await", description, block);
		}

		default @Nonnull SELF meantime(@Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Meantime", DEFAULT_DESCRIPTION, block);
		}

		default @Nonnull SELF meantime(@Nullable String description, @Nonnull LBiConsumer<STAGE, STEP_STAGE> block) {
			return activity("Meantime", description, block);
		}

		@Nonnull
		SELF activity(@Nonnull String activity, @Nullable String description, LBiConsumer<STAGE, STEP_STAGE> block);
	}

	public interface Junction<STAGE, SELF extends Junction<STAGE, SELF>> extends SelfReferencing<SELF> {

		default @Nonnull SELF step(@Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("STEP", DEFAULT_DESCRIPTION, consumer);
		}

		default @Nonnull SELF step(@Nullable String description, @Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("STEP", description, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF step(@Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("STEP", DEFAULT_DESCRIPTION, stepStage, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF step(@Nullable String description, @Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("STEP", description, stepStage, consumer);
		}

		default @Nonnull SELF phase(@Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("PHASE", DEFAULT_DESCRIPTION, consumer);
		}

		default @Nonnull SELF phase(@Nullable String description, @Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("PHASE", description, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF phase(@Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("PHASE", DEFAULT_DESCRIPTION, stepStage, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF phase(@Nullable String description, @Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("PHASE", description, stepStage, consumer);
		}

		default @Nonnull SELF focus(@Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("FOCUS", DEFAULT_DESCRIPTION, consumer);
		}

		default @Nonnull SELF focus(@Nullable String description, @Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("FOCUS", description, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF focus(@Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("FOCUS", DEFAULT_DESCRIPTION, stepStage, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF focus(@Nullable String description, @Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("FOCUS", description, stepStage, consumer);
		}

		default @Nonnull SELF aspect(@Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("ASPECT", DEFAULT_DESCRIPTION, consumer);
		}

		default @Nonnull SELF aspect(@Nullable String description, @Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("ASPECT", description, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF aspect(@Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("ASPECT", DEFAULT_DESCRIPTION, stepStage, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF aspect(@Nullable String description, @Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("ASPECT", description, stepStage, consumer);
		}

		default @Nonnull SELF junction(@Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("JUNCTION", DEFAULT_DESCRIPTION, consumer);
		}

		default @Nonnull SELF junction(@Nullable String description, @Nonnull LConsumer<Step<STAGE, ?>> consumer) {
			return testFlow("JUNCTION", description, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF junction(@Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("JUNCTION", DEFAULT_DESCRIPTION, stepStage, consumer);
		}

		default <STEP_STAGE> @Nonnull SELF junction(@Nullable String description, @Nonnull LSupplier<STEP_STAGE> stepStage, @Nonnull LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer) {
			return testFlowWith("JUNCTION", description, stepStage, consumer);
		}

		@Nonnull
		SELF testFlow(@Nonnull String phase, @Nullable String description, LConsumer<Step<STAGE, ?>> consumer);
		<STEP_STAGE> @Nonnull SELF testFlowWith(@Nonnull String phase, @Nullable String description, @Nonnull LSupplier<STEP_STAGE> stepStage, LConsumer<Step2<STAGE, STEP_STAGE, ?>> consumer);

	}

}