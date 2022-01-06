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

import eu.lunisolar.magma.func.supp.value.LInteger;
import eu.lunisolar.magma.func.supp.value.LValue;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueTest {

    @Test
    void valueIsMutable() {

        LValue<Integer> v = LValue.objValue(1);

        var result = v
                .filter(i -> i == 1)
                .uniMap(i -> i + 1);

        assertThat(result).isSameAs(v);
        assertThat(v.value()).isEqualTo(2);
        assertThat(v.is(i-> i==2)).isTrue();
    }

    @Test
    void valueIsMutableAndVoidable() {

        LValue<Integer> v = LValue.objValue(1);

        var result = v
                .filter(i -> i != 1)     // v.value() == null
                .value(10)
                .uniMap(i -> i + 1);

        assertThat(result).isSameAs(v);
        assertThat(v.value()).isEqualTo(11);
        assertThat(v.is(i-> i==11)).isTrue();
    }

    @Test
    void intValueIsMutableAndVoidable() {

        LInteger v = LInteger.intValue(1);

        var result = v
                .filter(i -> i != 1)     // v.value() == 0
                .map(i -> i + 1);

        assertThat(result).isSameAs(v);
        assertThat(v.value()).isEqualTo(1);
        assertThat(v.is(i-> i==1)).isTrue();
    }

}
