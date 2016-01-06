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

package eu.lunisolar.magma.basics.builder;

import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.fluent.Fluent;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.*;

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
public abstract class PerCaseBuilder<PCB extends PerCaseBuilder<PCB, P, F, PC>, P, F, PC extends PartialCase<PC, PCB, P, F>> implements Fluent<PCB> {

    protected @Nonnull final List<Case<P, F>> cases = new ArrayList<>();
    protected @Nonnull final Supplier<PCB> subCasesFactory;

    protected @Nonnull F eventually;

    public PerCaseBuilder(@Nonnull F eventually, @Nonnull Supplier<PCB> subCasesFactory) {
        this.eventually = Null.nonNullArg(eventually, "eventually");
        this.subCasesFactory = subCasesFactory;
    }

    // <editor-fold desc="case">

    /** Adds full new case. */
    public final PCB aCase(@Nonnull Case<P, F> theCase) {
        cases.add(theCase);
        return self();
    }

    /** Builds full new case by lambda expression (presumably). */
    public final PCB aCase(Consumer<CaseBuilder<P, PC>> caseBuilderConsumer) {
        Null.nonNullArg(caseBuilderConsumer, "caseBuilderConsumer");
        caseBuilderConsumer.accept(new CaseBuilder<>(this::partialCaseFactoryMethod));
        return self();
    }

    /** Adds full new case by lambda expressions (presumably). */
    public final PCB aCase(@Nonnull P casePredicate, @Nonnull F caseFunction) {
        cases.add(new Case<>(casePredicate, caseFunction));
        return self();
    }

    /** Starts adding the case to the list. Changes also the fluent context. */
    public final PC inCase(@Nonnull P casePredicate) {
        Null.nonNullArg(casePredicate, "casePredicate");
        return partialCaseFactoryMethod(casePredicate);
    }

    /** Sets the function to evaluate _eventually_ when input data do not match any case. */
    public final PCB eventually(@Nonnull F caseFunction) {
        eventually = caseFunction;
        return self();
    }

    public abstract F build();

    // </editor-fold>

    protected PC partialCaseFactoryMethod(P casePredicate) {
        return (PC) new PartialCase(PerCaseBuilder.this, casePredicate, subCasesFactory);
    }

    public static abstract class Base<SELF extends Base<SELF, P, F>, P, F> extends PerCaseBuilder<SELF, P, F, PartialCase.The<SELF, P, F>> {
        public Base(@Nonnull F eventually, @Nonnull Supplier<SELF> subCasesFactory) {
            super(eventually, subCasesFactory);
        }

        @Override
        protected PartialCase.The<SELF, P, F> partialCaseFactoryMethod(P casePredicate) {
            return new PartialCase.The(self(), casePredicate, subCasesFactory);
        }
    }

}
