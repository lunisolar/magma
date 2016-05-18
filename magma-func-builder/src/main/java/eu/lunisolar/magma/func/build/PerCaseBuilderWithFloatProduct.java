/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

/**
 * Abstract implementation of a per case builder of a complex implementation of a functional interface.
 *
 * Each case consist of a appropriate condition - predicate, supplier, or operator depending on the functional interface type - and a evaluating function
 * (same type as overall function) that will be called when condition expression evaluates to **true**.
 *
 * Cases are evaluated in a order one by one. First condition that returns **true** will decide what function will be called. Eventually if no condition is
 * evaluating to **true** a last resort function _eventually_ is called. By default _eventually_ will throw an exception that there is no case that will cover
 * the input data. This default _eventually_ behavior can be overridden.
 */
@SuppressWarnings("unchecked")
public abstract class PerCaseBuilderWithFloatProduct<PCB extends PerCaseBuilderWithFloatProduct<PCB, P, F, PC>, P, F, PC extends PartialCaseWithFloatProduct<PC, PCB, P, F>> extends PerCaseBuilder<PCB, P, F, PC> {

	protected @Nonnull final LFloatFunction<F> directToFunction;

	protected PerCaseBuilderWithFloatProduct(@Nonnull F eventually, @Nonnull LFloatFunction<F> directToFunction, @Nonnull Supplier<PCB> subCasesFactory) {
		super(eventually, subCasesFactory);
		this.directToFunction = directToFunction;
	}

	// <editor-fold desc="case">

	/** Sets the function to evaluate _eventually_ when input data do not match any case. */
	public final PCB eventuallyProduce(float directValue) {
		eventually = directToFunction.doApply(directValue);
		return self();
	}

	// </editor-fold>

	protected PC partialCaseFactoryMethod(P casePredicate) {
		return (PC) new PartialCaseWithFloatProduct(self(), casePredicate, subCasesFactory);
	}

	public static abstract class Base<SELF extends Base<SELF, P, F>, P, F> extends PerCaseBuilderWithFloatProduct<SELF, P, F, PartialCaseWithFloatProduct.The<SELF, P, F>> {
		protected Base(@Nonnull F eventually, @Nonnull LFloatFunction<F> directToFunction, @Nonnull Supplier<SELF> subCasesFactory) {
			super(eventually, directToFunction, subCasesFactory);
		}

		@Override
		protected PartialCaseWithFloatProduct.The<SELF, P, F> partialCaseFactoryMethod(P casePredicate) {
			return new PartialCaseWithFloatProduct.The(this, casePredicate, subCasesFactory);
		}
	}

}
