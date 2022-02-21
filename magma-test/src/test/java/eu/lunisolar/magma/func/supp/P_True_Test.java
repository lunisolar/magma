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

import eu.lunisolar.magma.func.supp.*;
import eu.lunisolar.magma.func.supp.check.Checks;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.asserts.Attests.attestThat;
import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class P_True_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    @Test void true_P() {
        assertThat(P.True(true)).isTrue();
        assertThat(P.True(false)).isFalse();
    }
    
    @Test void true_Be() {
        assertThat(Be.True(true)).isTrue();
        assertThat(Be.True(false)).isFalse();
    }

    @Test void true$_P() {
        assertThat(P.True$(true)).isNull();
        assertThat(P.True$(false)).isEqualTo("<false> must be true.");
    }

    @Test void true$_Be() {
        assertThat(Be.True$(true)).isNull();
        assertThat(Be.True$(false)).isEqualTo("<false> must be true.");
    }
    
}
