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

package eu.lunisolar.magma.asserts.opt;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.*; // NOSONAR
import eu.lunisolar.magma.asserts.*; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.check.*; // NOSONAR
import eu.lunisolar.magma.func.supp.opt.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; // NOSONAR
import org.assertj.core.api.*; // NOSONAR

import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

import static eu.lunisolar.magma.func.supp.check.Checks.arg; // NOSONAR
import static org.assertj.core.error.OptionalShouldContain.shouldContain; // NOSONAR

public class OptLongTraitAssert extends AbstractObjectAssert<OptLongTraitAssert, OptLongTrait<?>> implements FluentTrait<OptLongTraitAssert>, Attest<OptLongTraitAssert, OptLongTrait<?>> {

	@Override
	public OptLongTrait<?> actual() {
		return super.actual;
	}

	public OptLongTraitAssert(OptLongTrait<?> actual) {
		super(actual, OptLongTraitAssert.class);
	}

	public OptLongTraitAssert isPresent() {
		isNotNull();
		must(OptLongTrait::isPresent, "<%s> is expected to have value, but is void.");
		return this;
	}

	public OptLongTraitAssert isNotVoid() {
		return isPresent();
	}

	public OptLongTraitAssert isVoid() {
		isNotNull();
		must(OptLongTrait::isVoid, "<%s> is expected to NOT have value, but it does.");
		return this;
	}

	public OptLongTraitAssert isNotPresent() {
		return isVoid();
	}

	public OptLongTraitAssert contains(long expectedValue) {
		isNotNull();

		must(OptLongTrait::isPresent, "<%s> is expected to have value <%s>, but is void.", actual(), expectedValue);
		must(P.haveToLong(OptLongTrait::value, P::equal, expectedValue), "Optional value <%s> should be equal to <%s>.", actual().nullable(), expectedValue);
		return this;
	}

	public OptLongTraitAssert hasValue(long expectedValue) {
		return contains(expectedValue);
	}

	public AbstractLongAssert<?> hasValueThat() {
		isPresent();
		return Assertions.assertThat(actual().value());
	}

}
