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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.func.supp.Opt;
import eu.lunisolar.magma.func.supp.OptInt;

import java.util.*;

public class OptTest {

    static {
        Opt<Integer> ooo = Opt.obj(5);
        ooo.is(5, Objects::equals);
        ooo.is(Objects::equals, 5);
    }

    static {
        OptInt ooo = Opt.of(5);
        ooo.is(5, Objects::equals);
        ooo.is(Objects::equals, 5);
    }

    public static interface S {
        S add(D all);
        Opt<S> addO(D all);
    }

    public static interface D {
        D add(S all);
        Opt<D> addO(S all);
    }

    static {
        Opt<S> mmm = Opt.of(null);
        mmm.map((D) null, S::add);
        mmm.mapWith((D) null, D::add);

        mmm.flatMap((D) null, S::addO);
        mmm.flatMapWith((D) null, D::addO);
    }

}
