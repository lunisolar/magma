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

package eu.lunisolar.magma.basics.asserts;

import eu.lunisolar.magma.basics.fluent.Fluent;
import org.assertj.core.api.Assert;

import javax.annotation.Nonnull;
import java.util.function.*;

import static javafx.scene.input.KeyCode.X;

public interface FullFunctionalAssert<S extends FullFunctionalAssert<S, PC, A, RS, R>, PC, A, RS extends Assert<RS, R>, R>
        extends FunctionalAssert<S, PC, A, Consumer<RS>>, Assert<S, A>, Fluent<S> {

    @SuppressWarnings("unchecked")
    abstract class Base<S extends Base<S, PC, A, RS, R>, PC, A, RS extends Assert<RS, R>, R>
            extends RecurringAsserts.Base<S, A, Consumer<RS>>
            implements FullFunctionalAssert<S, PC, A, RS, R>, Fluent<S> {

        public Base(A actual, Class<?> selfType) {
            super(actual, selfType);
        }

        @Nonnull
        protected Evaluation<S, PC, A, RS, R> evaluation(Supplier<String> caseDescription, AssertionFunction<PC, RS> assertFunction) {
            return new Evaluation<>(self(), ()-> descriptionText(),  caseDescription, (PC pc) -> {
                isNotNull();
                return assertFunction.applyAndCreateResultAssert(pc);
            }, recurringAssert);
        }

    }
}
