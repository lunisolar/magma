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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.function.*;

@Immutable
@ThreadSafe
public class PartialCaseWithProduct<SELF extends PartialCaseWithProduct<SELF, PCB, P, F, R>, PCB extends PerCaseBuilderWithProduct<PCB, P, F, R, SELF>, P, F, R> extends PartialCase<SELF, PCB, P, F> {

    public PartialCaseWithProduct(@Nonnull PCB superContext, @Nonnull P casePredicate, @Nonnull Supplier<PCB> subCasesFactory) {
        super(superContext, casePredicate, subCasesFactory);
    }

    /** Finalize the case build by providing second required value for the Case. */
    public final PCB produce(@Nonnull R directValue) {
        return this.evaluate(superContext().directToFunction.apply(directValue));
    }

    public static final class The<PCB extends PerCaseBuilderWithProduct.Base<PCB, P, F, R>, P, F, R> extends PartialCaseWithProduct<The<PCB, P, F, R>, PCB, P, F, R> {
        public The(@Nonnull PCB superContext, @Nonnull P casePredicate, @Nonnull Supplier<PCB> subCasesFactory) {
            super(superContext, casePredicate, subCasesFactory);
        }
    }
}