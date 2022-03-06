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

import static org.testng.Assert.*;

public class P_True_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    @Test void true_P() {
        assertTrue(P.True(true));
        assertFalse(P.True(false));
    }
    
    @Test void true_Be() {
        assertTrue(Be.True(true));
        assertFalse(Be.True(false));
    }

    @Test void true$_P() {
        assertNull(P.True$(true));
        assertEquals(P.True$(false), "<false> must be true.");
    }

    @Test void true$_Be() {
        assertNull(Be.True$(true));
        assertEquals(Be.True$(false), "<false> must be true.");
    }
    
}
