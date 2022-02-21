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

public class P_ContainsExactly_Test {

    @Test void containExactlyTest() {
        attestThat(P.containExactly(asList(1, 2, 3), 1, 2, 3)).must$(Be::True$);
        attestThat(P.containExactly(asList(1, 2, 3), 1, 2)).must$(Be::False$);
        attestThat(P.containExactly(asList(1, 2, 3), 2, 3)).must$(Be::False$);
        attestThat(P.containExactly(asList(1, 2, 3), 3, 2, 1)).must$(Be::False$);
        attestThat(P.containExactly(asList(1, 2, 3))).must$(Be::False$);
        attestThat(P.containExactly(asList())).must$(Be::True$);

        attestThat(Does.containExactly(asList(1, 2, 3), 1, 2, 3)).must$(Be::True$);
        attestThat(Does.containExactly(asList(1, 2, 3), 1, 2)).must$(Be::False$);
        attestThat(Does.containExactly(asList(1, 2, 3), 2, 3)).must$(Be::False$);
        attestThat(Does.containExactly(asList(1, 2, 3), 3, 2, 1)).must$(Be::False$);
        attestThat(Does.containExactly(asList(1, 2, 3))).must$(Be::False$);
        attestThat(Does.containExactly(asList())).must$(Be::True$);
    }

    @Test void notContainExactlyTest() {
        attestThat(P.notContainExactly(asList(1, 2, 3), 1, 2, 3)).must$(Be::False$);
        attestThat(P.notContainExactly(asList(1, 2, 3), 1, 3, 4)).must$(Be::True$);

        attestThat(Does.notContainExactly(asList(1, 2, 3), 1, 2, 3)).must$(Be::False$);
        attestThat(Does.notContainExactly(asList(1, 2, 3), 1, 3, 4)).must$(Be::True$);
    }

    @Test void array_in_messages() {

        var array = new Integer[]{1, 2, 3};

        attestThat(Has.length$(array, 5)).must$(Be::equal$, "Array <[1, 2, 3]> must be of size 5.");
        attestThat(Is.ofLength$(array, 5)).must$(Be::equal$, "Array <[1, 2, 3]> must be of size 5.");

        attestThat(P.containExactly$(asList(array), 5, 6, 7)).must$(Be::equal$, "Collection <[1, 2, 3]> must contain exactly elements in order: <[5, 6, 7]>.");

    }
}
