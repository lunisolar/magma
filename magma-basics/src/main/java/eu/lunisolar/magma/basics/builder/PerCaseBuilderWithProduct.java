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

package eu.lunisolar.magma.basics.builder;

import eu.lunisolar.magma.basics.meta.functional.MetaFunctionalInterface;

import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Supplier;

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
public abstract class PerCaseBuilderWithProduct<PCB extends PerCaseBuilderWithProduct<PCB, P, F, R, PC>, P extends MetaFunctionalInterface, F extends MetaFunctionalInterface, R, PC extends PartialCaseWithProduct<PC, PCB, P, F, R>> extends PerCaseBuilder<PCB, P, F, PC> {

    protected @Nonnull final Function<R, F> directToFunction;

    protected PerCaseBuilderWithProduct(@Nonnull F eventually, @Nonnull Function<R, F> directToFunction, @Nonnull Supplier<PCB> subCasesFactory) {
        super(eventually, subCasesFactory);
        this.directToFunction = directToFunction;
    }

    // <editor-fold desc="case">

    /** Sets the function to evaluate _eventually_ when input data do not match any case. */
    public final PCB otherwiseProduce(@Nonnull R directValue) {
        otherwise = directToFunction.apply(directValue);
        return fluentCtx();
    }

    // </editor-fold>

    protected PC partialCaseFactoryMethod(P casePredicate) {
        return (PC) new PartialCaseWithProduct(PerCaseBuilderWithProduct.this, casePredicate, subCasesFactory);
    }

    public static abstract class Base<SELF extends Base<SELF, P, F, R>, P extends MetaFunctionalInterface, F extends MetaFunctionalInterface, R> extends PerCaseBuilderWithProduct<SELF, P, F, R, PartialCaseWithProduct.The<SELF, P, F, R>> {
        protected Base(@Nonnull F eventually, @Nonnull Function<R, F> directToFunction, @Nonnull Supplier<SELF> subCasesFactory) {
            super(eventually, directToFunction, subCasesFactory);
        }

        @Override
        protected PartialCaseWithProduct.The<SELF, P, F, R> partialCaseFactoryMethod(P casePredicate) {
            return new PartialCaseWithProduct.The(fluentCtx(), casePredicate, subCasesFactory);
        }
    }

}