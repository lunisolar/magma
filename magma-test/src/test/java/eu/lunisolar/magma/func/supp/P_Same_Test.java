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
        attestThat(P.same(I1, I1)).mustEx(Be::TrueEx);
        attestThat(P.notSame(I1, I1)).mustEx(Be::FalseEx);

        attestThat(P.same(I1, I2)).mustEx(Be::FalseEx);
        attestThat(P.notSame(I1, I2)).mustEx(Be::TrueEx);
    }

    @Test void same_Be() {
        attestThat(Be.same(I1, I1)).mustEx(Be::TrueEx);
        attestThat(Be.notSame(I1, I1)).mustEx(Be::FalseEx);

        attestThat(Be.same(I1, I2)).mustEx(Be::FalseEx);
        attestThat(Be.notSame(I1, I2)).mustEx(Be::TrueEx);
    }

    @Test void sameEx() {
        attestThat(P.sameEx(I1, I1)).mustEx(Be::NullEx);
        attestThat(P.notSameEx(I1, I1)).mustEx(Be::equalEx, "Object <1> must NOT be the same as <1>.");

        attestThat(Be.sameEx(I1, I2)).mustEx(Be::equalEx, "Object <1> must be the same as <2>.");
        attestThat(Be.notSameEx(I1, I2)).mustEx(Be::NullEx);
    }

}
