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

package eu.lunisolar.magma.func.build;

import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.fluent.FluentSubcontext;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.function.*;

import eu.lunisolar.magma.basics.builder.*;

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

@Immutable
@ThreadSafe
public class PartialCaseWithIntProduct<SELF extends PartialCaseWithIntProduct<SELF, PCB, P, F>, PCB extends PerCaseBuilderWithIntProduct<PCB, P, F, SELF>, P, F> extends PartialCase<SELF, PCB, P, F> {

	public PartialCaseWithIntProduct(@Nonnull PCB superContext, @Nonnull P casePredicate, @Nonnull Supplier<PCB> subCasesFactory) {
		super(superContext, casePredicate, subCasesFactory);
	}

	/** Finalize the case build by providing second required value for the Case. */
	public final PCB produce(int directValue) {
		return this.evaluate(superContext().directToFunction.apply(directValue));
	}

	public static final class The<PCB extends PerCaseBuilderWithIntProduct<PCB, P, F, The<PCB, P, F>>, P, F> extends PartialCaseWithIntProduct<The<PCB, P, F>, PCB, P, F> {
		public The(@Nonnull PCB superContext, @Nonnull P casePredicate, @Nonnull Supplier<PCB> subCasesFactory) {
			super(superContext, casePredicate, subCasesFactory);
		}
	}
}
