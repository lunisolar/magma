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

package eu.lunisolar.magma.basics.builder;

import eu.lunisolar.magma.basics.fluent.AbstractFluentSubcontext;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.function.*;

@Immutable
@ThreadSafe
public class PartialCase<SELF extends PartialCase<SELF, PCB, P, F>, PCB extends PerCaseBuilder<PCB, P, F, SELF>, P, F> extends AbstractFluentSubcontext<SELF, PCB> {
    protected final P             casePredicate;
    protected final Supplier<PCB> subCasesBuilderFactory;

    public PartialCase(@Nonnull PCB superContext, @Nonnull P casePredicate, @Nonnull Supplier<PCB> subCasesBuilderFactory) {
        super(superContext);
        this.casePredicate = casePredicate;
        this.subCasesBuilderFactory = subCasesBuilderFactory;
    }

    public final PCB specifySubCases(Consumer<PCB> subCaseExpression) {

        PCB subCasesBuilder = subCasesBuilderFactory.get();
        subCaseExpression.accept(subCasesBuilder);

        F specializedFunction = subCasesBuilder.build();

        PCB superContext = superContext();
        superContext.aCase(Case.of(casePredicate, specializedFunction));
        return superContext();
    }

    /** Finalize the case build by providing second required value for the Case. */
    public final PCB evaluate(@Nonnull F caseFunction) {
        PCB superContext = superContext();
        superContext.aCase(Case.of(casePredicate, caseFunction));
        return superContext;
    }

    /** Finalize the case build by providing second required value for the Case. */
    public final PCB eval(@Nonnull F caseFunction) {
        return evaluate(caseFunction);
    }

    public static final class The<PCB extends PerCaseBuilder.Base<PCB, P, F>, P, F> extends PartialCase<The<PCB, P, F>, PCB, P, F> {

        public The(@Nonnull PCB superContext, @Nonnull P casePredicate, Supplier<PCB> subCasesFactory) {
            super(superContext, casePredicate, subCasesFactory);
        }
    }

}