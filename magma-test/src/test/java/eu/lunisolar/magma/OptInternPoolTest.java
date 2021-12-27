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

package eu.lunisolar.magma;

import eu.lunisolar.magma.func.supp.opt.OptBool;
import eu.lunisolar.magma.func.supp.opt.OptByte;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OptInternPoolTest {

    @Test
    public void testBool() {

        OptBool t1 = OptBool.of(true);
        OptBool f1 = OptBool.of(false);
        OptBool t2 = OptBool.of(true);
        OptBool f2 = OptBool.of(false);

        assertThat(t1).isSameAs(t2);
        assertThat(f1).isSameAs(f2);

        assertThat(t1.value()).isTrue();
        assertThat(f1.value()).isFalse();
    }

    @Test
    public void testByte() {

        for( int i= Byte.MIN_VALUE;i<= Byte.MAX_VALUE; i++) {
            OptByte b1 = OptByte.of((byte)i);
            OptByte b2 = OptByte.of((byte)i);

            assertThat(b1).isSameAs(b2);
            assertThat(b1.value()).isEqualTo((byte)i);
        }
    }
}
