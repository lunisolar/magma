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

import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Has;
import eu.lunisolar.magma.func.supp.Is;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.opt.Opt;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.asserts.Attests.attestThat;
import static java.util.Arrays.*;

public class PTest {

    @Test void containExactlyTest() {

        attestThat(P.containExactly(asList(1, 2, 3), 1, 2, 3)).mustEx(Be::TrueEx);

        attestThat(P.containExactly(asList(1, 2, 3), 1, 2)).mustEx(Be::FalseEx);
        attestThat(P.containExactly(asList(1, 2, 3), 2, 3)).mustEx(Be::FalseEx);
        attestThat(P.containExactly(asList(1, 2, 3), 3, 2, 1)).mustEx(Be::FalseEx);

        attestThat(P.containExactly(asList(1, 2, 3))).mustEx(Be::FalseEx);

        attestThat(P.containExactly(asList())).mustEx(Be::TrueEx);

        Opt.obj(asList(1, 2)).mustAEx(P::containExactlyEx, 1, 2);

    }

    @Test void array_in_messages() {

        var array = new Integer[]{1, 2, 3};

        attestThat(Has.lengthEx(array, 5)).mustEx(Be::equalEx, "Array <[1, 2, 3]> must be of size 5.");
        attestThat(Is.ofLengthEx(array, 5)).mustEx(Be::equalEx, "Array <[1, 2, 3]> must be of size 5.");

        attestThat(P.containExactlyEx(asList(array), 5, 6, 7)).mustEx(Be::equalEx, "Collection <[1, 2, 3]> must contain exactly elements in order: <[5, 6, 7]>.");

    }
}
