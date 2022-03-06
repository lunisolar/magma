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

public class P_False_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    @Test void true_P() {
        assertFalse(P.False(true));
        assertTrue(P.False(false));
    }
    
    @Test void true_Be() {
        assertFalse(Be.False(true));
        assertTrue(Be.False(false));
    }

    @Test void true$_P() {
        assertEquals(P.False$(true), "<true> must be false.");
        assertNull(P.False$(false));
    }

    @Test void true$_Be() {
        assertEquals(Be.False$(true), "<true> must be false.");
        assertNull(Be.False$(false));
    }
    
}
