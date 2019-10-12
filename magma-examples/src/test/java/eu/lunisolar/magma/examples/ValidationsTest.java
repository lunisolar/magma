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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Validations;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ValidationsTest {

    static {
        Object str = "expectecStr";
        Validations.value(str, "someValue").must(Be::equal, "expectecStr", "Must be equal 'aaa'");
    }

    @Test void must$() {
        Object str = null;

        Assertions.assertThatThrownBy(() -> {
            Validations.value(str, "some-value-name").must(Be::notNull, "Must be not null");
        })
                  .isInstanceOf(IllegalArgumentException.class)
                  .hasMessage("Value [some-value-name]: Must be not null.");

        Assertions.assertThatThrownBy(() -> {
            Validations.value(str, "some-value-name").must$(Be::notNull, "Must be not null");
        })
                  .isInstanceOf(IllegalArgumentException.class)
                  .hasMessage("Value [some-value-name]: Must be not null. Value: null");

    }

}
