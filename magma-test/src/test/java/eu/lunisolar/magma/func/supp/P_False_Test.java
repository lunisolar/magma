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

package eu.lunisolar.magma.func.supp;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class P_False_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    @Test void true_P() {
        assertThat(P.False(true)).isFalse();
        assertThat(P.False(false)).isTrue();
    }
    
    @Test void true_Be() {
        assertThat(Be.False(true)).isFalse();
        assertThat(Be.False(false)).isTrue();
    }

    @Test void trueEx_P() {
        assertThat(P.FalseEx(true)).isEqualTo("<true> must be false.");
        assertThat(P.FalseEx(false)).isNull();
    }

    @Test void trueEx_Be() {
        assertThat(Be.FalseEx(true)).isEqualTo("<true> must be false.");
        assertThat(Be.FalseEx(false)).isNull();
    }
    
}
