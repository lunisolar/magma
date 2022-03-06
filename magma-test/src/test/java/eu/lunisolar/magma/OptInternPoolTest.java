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

package eu.lunisolar.magma;

import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.opt.OptBool;
import eu.lunisolar.magma.func.supp.opt.OptByte;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class OptInternPoolTest {

    @Test
    public void testBool() {

        OptBool t1 = OptBool.of(true);
        OptBool f1 = OptBool.of(false);
        OptBool t2 = OptBool.of(true);
        OptBool f2 = OptBool.of(false);

        attest(t1).must$(Be::same$, t2);
        attest(f1).must$(Be::same$, f2);

        attest(t1).must$(Have::valueEqual$, true);
        attest(f1).must$(Have::valueEqual$, false);
    }

    @Test
    public void testByte() {

        for( int i= Byte.MIN_VALUE;i<= Byte.MAX_VALUE; i++) {
            OptByte b1 = OptByte.of((byte)i);
            OptByte b2 = OptByte.of((byte)i);

            attest(b1).must$(Be::same$, b2);
            attest(b1).must$(Have::valueEqual$, (byte)i);
        }
    }
}
