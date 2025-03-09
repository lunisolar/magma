/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2025 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.tuple;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class TupleTest {

    public static final String V1 = "1";
    public static final String V2 = "2";
    public static final String V3 = "3";
    public static final String V4 = "4";

    @DataProvider public static Object[][] singles() {
        return new Object[][] {
                new Object[] { LSingle.of(V1) },
                new Object[] { LSingle.immutableOf(V1) },
                new Object[] { LSingle.immutableCopyOf(LSingle.of(V1)) }
        };
    }

    @Test(dataProvider = "singles" )
    void single(LSingle<Object> tuple) {

        attest(tuple.value()).mustBeEqual(V1);

        if ( tuple instanceof LSingle.Mut mut) {
            attest(mut.setValue("setValue")).mustBeEqual(V1);
            attest(mut.getValue()).mustBeEqual("setValue");

            attest(mut.value("setValue2")).mustBeExactlyInstanceOf(tuple.getClass());
            attest(mut.value()).mustBeEqual("setValue2");
        }
    }

    @DataProvider public static Object[][] pairs() {
        return new Object[][] {
                new Object[] { LPair.of(V1, V2) },
                new Object[] { LPair.immutableOf(V1, V2) },
                new Object[] { LPair.immutableCopyOf(LPair.of(V1, V2)) }
        };
    }

    @Test(dataProvider = "pairs" )
    void pair(LPair<Object, Object> tuple) {

        attest(tuple.first()).mustBeEqual(V1);
        attest(tuple.second()).mustBeEqual(V2);

        attest(tuple.value()).mustBeEqual(V2);
        attest(tuple.getValue()).mustBeEqual(V2);
        attest(tuple.getKey()).mustBeEqual(V1);

        if ( tuple instanceof LPair.Mut mut) {
            attest(mut.setValue("setValue")).mustBeEqual(V2);
            attest(mut.getValue()).mustBeEqual("setValue");

            attest(mut.value("setValue2")).mustBeExactlyInstanceOf(tuple.getClass());
            attest(mut.value()).mustBeEqual("setValue2");
        }
    }

    @DataProvider public static Object[][] triples() {
        return new Object[][] {
                new Object[] { LTriple.of(V1, V2, V3) },
                new Object[] { LTriple.immutableOf(V1, V2, V3) },
                new Object[] { LTriple.immutableCopyOf(LTriple.of(V1, V2, V3)) }
        };
    }

    @Test(dataProvider = "triples")
    void triple(LTriple<Object, Object, Object> tuple) {

        attest(tuple.first()).mustBeEqual(V1);
        attest(tuple.second()).mustBeEqual(V2);
        attest(tuple.third()).mustBeEqual(V3);

        attest(tuple.value()).mustBeEqual(V3);
        attest(tuple.getValue()).mustBeEqual(V3);
        attest(tuple.getKey()).mustBeEqual(V2);

        if ( tuple instanceof LTriple.Mut mut) {
            attest(mut.setValue("setValue")).mustBeEqual(V3);
            attest(mut.getValue()).mustBeEqual("setValue");

            attest(mut.value("setValue2")).mustBeExactlyInstanceOf(tuple.getClass());
            attest(mut.value()).mustBeEqual("setValue2");
        }
    }

    @DataProvider public static Object[][] quads() {
        return new Object[][] {
                new Object[] { LQuad.of(V1, V2, V3, V4) },
                new Object[] { LQuad.immutableOf(V1, V2, V3, V4) },
                new Object[] { LQuad.immutableCopyOf(LQuad.of(V1, V2, V3, V4)) }
        };
    }

    @Test(dataProvider = "quads")
    void quad(LQuad<Object, Object, Object, Object> tuple) {

        attest(tuple.first()).mustBeEqual(V1);
        attest(tuple.second()).mustBeEqual(V2);
        attest(tuple.third()).mustBeEqual(V3);
        attest(tuple.fourth()).mustBeEqual(V4);

        attest(tuple.value()).mustBeEqual(V4);
        attest(tuple.getValue()).mustBeEqual(V4);
        attest(tuple.getKey()).mustBeEqual(V3);

        if ( tuple instanceof LQuad.Mut mut) {
            attest(mut.setValue("setValue")).mustBeEqual(V4);
            attest(mut.getValue()).mustBeEqual("setValue");

            attest(mut.value("setValue2")).mustBeExactlyInstanceOf(tuple.getClass());
            attest(mut.value()).mustBeEqual("setValue2");
        }
    }

}
