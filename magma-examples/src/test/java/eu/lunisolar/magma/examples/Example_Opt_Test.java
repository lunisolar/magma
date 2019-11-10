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

import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.opt.Opt;
import eu.lunisolar.magma.func.supp.opt.OptInt;

import java.util.*;

//>transform-to-MD<
/**
 * Basic introduction (by example) to Opt classes.
 *
 * Opt(ional)
 * ==========================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to Opt classes.
 * Available since 2.1.0.
 *
 * ### Description
 *
 *
 * ### Examples
 */
//>inject<:readmore

//>inject<:generated

/**
 * Opt(ional)
 * ==========================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to Opt classes.
 * Available since 2.1.0.
 *
 * ### Description
 *
 *
 * ### Examples
 *
 */

/**
 * ...
 */
public class Example_Opt_Test {

    /**
     * ...
     */
    //>example<

    public static interface S {
        S add(D all);
        Opt<S> addO(D all);
    }

    public static interface D {
        D add(S all);
        Opt<D> addO(S all);
    }

    //  @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument \\[\\?\\]: cannot be greater or equal 50.")
    public void test1() {

        Opt<S> optS = Opt.of(null);
        Opt<S> v1 = optS.map((D) null, S::add);
        Opt<D> v2 = optS.mapWith((D) null, D::add);

        Opt<S> v3 = optS.flatMap((D) null, S::addO);
        Opt<D> v4 = optS.flatMapWith((D) null, D::addO);
    }

    //>example<

    static {
        Opt<Integer> ooo = Opt.obj(5);
        ooo.is(5, Objects::equals);
        ooo.must(Be::equal, 5, "must be 5");
        ooo.is(Objects::equals, 5);
    }

    static {
        OptInt ooo = Opt.of(5);
        ooo.is(5, Objects::equals);
        ooo.is(Objects::equals, 5);
    }

    static {
        Opt<S> optS = Opt.of(null);
        Opt<S> v1 = optS.map((D) null, S::add);
        Opt<D> v2 = optS.mapWith((D) null, D::add);

        Opt<S> v3 = optS.flatMap((D) null, S::addO);
        Opt<D> v4 = optS.flatMapWith((D) null, D::addO);
    }

    static {
        Opt<S> optS = Opt.of(null);
        Opt<S> v1 = optS.map((D) null, S::add);
        Opt<D> v2 = optS.mapWith((D) null, D::add);

        Opt<S> v3 = optS.flatMap((D) null, S::addO);
        Opt<D> v4 = optS.flatMapWith((D) null, D::addO);
    }

    private int arg45 = 45;

    //>inject<:generated

}
