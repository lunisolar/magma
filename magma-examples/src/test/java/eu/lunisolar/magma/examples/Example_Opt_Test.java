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

import eu.lunisolar.magma.basics.exceptions.IllegalValueException;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Opt;
import eu.lunisolar.magma.func.supp.OptInt;
import eu.lunisolar.magma.func.supp.Validations;
import org.testng.annotations.Test;

import java.util.*;

import static eu.lunisolar.magma.func.supp.Validations.*;

//>transform-to-MD<
/**
 * Basic introduction (by example) to Opt classes.
 */
//>inject<:readmore

//>inject<:generated

///
///Fluent Validations
///==========================
///
///### Abstract
///
///Basic introduction (by example) to Opt classes.
///Available since 2.1.0.
///
///### Examples
///
///

/**
 * ...
 */
public class Example_Opt_Test {

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

    private int arg45 = 45;

//    /**
//     * Lets consider very simple validation that need to be done.
//     */
//    //>example<
//    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument \\[\\?\\]: cannot be greater or equal 50.")
//    public void test1() {
//
//        Validations.arg(arg45)
//                   .mustNot(Be::gtEq, 50, "cannot be greater or equal 50") //passes
//                   .must(Be::lt, 50, "cannot be greater or equal 50"); //passes
//
//        var i = arg(60)
//                .mustNot(Be::gtEq, 50, "cannot be greater or equal 50") //fails
//                .get();
//    }
//    //>example<


    //>inject<:generated

}
