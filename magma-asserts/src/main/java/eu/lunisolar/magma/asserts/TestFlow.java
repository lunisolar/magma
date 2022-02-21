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

public final class TestFlow<SUT> extends Sut.Base<SUT> {

	public static final String DEFAULT_DESCRIPTION = "...";

	public TestFlow(SUT sut, @Nonnull LConsumer<String> logger) {
		super(sut, logger);
	}

	public static TestFlow<?> test() {
		return new TestFlow<>(null, System.out::println);
	}

	public TestFlow<?> log(@Nonnull LConsumer<String> logger) {
		arg(logger, "logger").must$(Be::notNull$);
		return new TestFlow<>(null, logger);
	}

	public <SUT> TestFlow<SUT> given(LSupplier<SUT> givenBlock) {
		return given(DEFAULT_DESCRIPTION, givenBlock);
	}

	public <SUT> TestFlow<SUT> given(@Nullable String description, LSupplier<SUT> givenBlock) {
		log("Given: " + description);
		check(givenBlock).must$(Be::notNull$);
		var sut = givenBlock.shovingGet();
		return new TestFlow<>(sut, logger());
	}

	public TestFlow<SUT> precondition(LConsumer<SUT> preBlock) {
		return precondition(DEFAULT_DESCRIPTION, preBlock);
	}

	public TestFlow<SUT> precondition(@Nullable String description, LConsumer<SUT> preBlock) {
		log("Precondition: " + description);
		check(preBlock).must$(Be::notNull$);
		preBlock.shovingAccept(sut());
		return new TestFlow<>(sut(), logger());
	}

	public TestFlow<SUT> when(LConsumer<SUT> whenBlock) {
		return when(DEFAULT_DESCRIPTION, whenBlock);
	}

	public TestFlow<SUT> when(@Nullable String description, LConsumer<SUT> whenBlock) {
		log("When: " + description);
		check(whenBlock).must$(Be::notNull$);
		whenBlock.shovingAccept(sut());
		return new TestFlow<>(sut(), logger());
	}

	public TestFlow<SUT> then(LConsumer<SUT> thenBlock) {
		return then(DEFAULT_DESCRIPTION, thenBlock);
	}

	public TestFlow<SUT> then(@Nullable String description, LConsumer<SUT> thenBlock) {
		log("Then: " + description);
		check(thenBlock).must$(Be::notNull$);
		thenBlock.shovingAccept(sut());
		return new TestFlow<>(sut(), logger());
	}

	public TestFlow<SUT> continuing() {
		return continuing(DEFAULT_DESCRIPTION);
	}

	public TestFlow<SUT> continuing(@Nullable String description) {
		log("Continuing: " + description);
		return new TestFlow<>(sut(), logger());
	}

	public TestFlow<SUT> step(LConsumer<TestFlow<SUT>> consumer) {
		return step(DEFAULT_DESCRIPTION, consumer);
	}

	public TestFlow<SUT> step(@Nullable String description, LConsumer<TestFlow<SUT>> consumer) {
		log("STEP: " + description);
		consumer.shovingAccept(this);
		return this;
	}

	public TestFlow<SUT> aftermath(LConsumer<SUT> postBlock) {
		return precondition(DEFAULT_DESCRIPTION, postBlock);
	}

	public TestFlow<SUT> aftermath(@Nullable String description, LConsumer<SUT> postBlock) {
		log("Aftermath: " + description);
		check(postBlock).must$(Be::notNull$);
		postBlock.shovingAccept(sut());
		return new TestFlow<>(sut(), logger());
	}

}