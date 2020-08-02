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

package eu.lunisolar.magma.basics.asserts;

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assert;

import javax.annotation.Nonnull;
import java.util.*;

public interface RecurringAsserts<S extends Assert<S, A>, A, RA> {

    /**
     * In case of some assertion that could be applied each time a method call result is tested, the argument assertion will be checked before the assertion
     * for specific case.
     */
    @Nonnull S inAllFollowingCases(@Nonnull RA recurringAssert);

    abstract class Base<S extends Base<S, A, RA>, A, RA> extends AbstractObjectAssert<S, A> implements RecurringAsserts<S, A, RA> {

        protected List<RA> recurringAssert = new ArrayList<>();

        public Base(A actual, Class<?> selfType) {
            super(actual, selfType);
        }

        @Override
        @Nonnull
        public S inAllFollowingCases(@Nonnull RA recurringAssert) {
            this.recurringAssert.add(recurringAssert);
            return myself;
        }

        @Nonnull
        protected List<RA> recurringAssert() {
            return recurringAssert;
        }

    }

}
