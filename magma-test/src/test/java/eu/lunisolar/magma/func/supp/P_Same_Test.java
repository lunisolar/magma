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

import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Has;
import eu.lunisolar.magma.func.supp.Is;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.opt.Opt;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.asserts.Attests.attestThat;
import static java.util.Arrays.*;

public class P_Same_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    @Test void same_P() {
        attestThat(P.same(I1, I1)).must$(Be::True$);
        attestThat(P.notSame(I1, I1)).must$(Be::False$);

        attestThat(P.same(I1, I2)).must$(Be::False$);
        attestThat(P.notSame(I1, I2)).must$(Be::True$);
    }

    @Test void same_Be() {
        attestThat(Be.same(I1, I1)).must$(Be::True$);
        attestThat(Be.notSame(I1, I1)).must$(Be::False$);

        attestThat(Be.same(I1, I2)).must$(Be::False$);
        attestThat(Be.notSame(I1, I2)).must$(Be::True$);
    }

    @Test void same$() {
        attestThat(P.same$(I1, I1)).must$(Be::Null$);
        attestThat(P.notSame$(I1, I1)).must$(Be::equal$, "Object <1> must NOT be the same as <1>.");

        attestThat(Be.same$(I1, I2)).must$(Be::equal$, "Object <1> must be the same as <2>.");
        attestThat(Be.notSame$(I1, I2)).must$(Be::Null$);
    }

}
