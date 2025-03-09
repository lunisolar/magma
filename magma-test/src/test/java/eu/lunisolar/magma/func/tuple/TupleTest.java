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

import eu.lunisolar.magma.func.supp.Is;
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

            attest(mut.value("setValue2")).mustBeExactlyInstanceOf(tuple.getClass());
            attest(mut.value()).mustBeEqual("setValue2");

            mut.setValueIf("conditionalValue", (o, n)  -> n == null);
            attest(mut.value()).mustBeEqual("setValue2");
            mut.setValueIf("conditionalValue2", (o, n)  -> n != null);
            attest(mut.value()).mustBeEqual("conditionalValue2");

            mut.setValueIfNew(null, Is::notNull);
            attest(mut.value()).mustBeEqual("conditionalValue2");
            mut.setValueIfNew(null, Is::Null);
            attest(mut.value()).mustBeEqual(null);

            mut.setValueIfCurrent("conditionalValue3", Is::notNull);
            attest(mut.value()).mustBeEqual(null);
            mut.setValueIfCurrent("conditionalValue3", Is::Null);
            attest(mut.value()).mustBeEqual("conditionalValue3");
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

        attest(tuple.getValue()).mustBeEqual(V2);
        attest(tuple.getKey()).mustBeEqual(V1);

        if ( tuple instanceof LPair.Mut mut) {
            attest(mut.setValue("setValue")).mustBeEqual(V2);
            attest(mut.getValue()).mustBeEqual("setValue");

            mut.setSecondIf("conditionalValue", (o, n)  -> n == null);
            attest(mut.second()).mustBeEqual("setValue");
            mut.setSecondIf("conditionalValue2", (o, n)  -> n != null);
            attest(mut.second()).mustBeEqual("conditionalValue2");

            mut.setSecondIfNew(null, Is::notNull);
            attest(mut.second()).mustBeEqual("conditionalValue2");
            mut.setSecondIfNew(null, Is::Null);
            attest(mut.second()).mustBeEqual(null);

            mut.setSecondIfCurrent("conditionalValue3", Is::notNull);
            attest(mut.second()).mustBeEqual(null);
            mut.setSecondIfCurrent("conditionalValue3", Is::Null);
            attest(mut.second()).mustBeEqual("conditionalValue3");
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

        if ( tuple instanceof LTriple.Mut mut) {

            mut.setThirdIf("conditionalValue", (o, n)  -> n == null);
            attest(mut.third()).mustBeEqual(V3);
            mut.setThirdIf("conditionalValue2", (o, n)  -> n != null);
            attest(mut.third()).mustBeEqual("conditionalValue2");

            mut.setThirdIfNew(null, Is::notNull);
            attest(mut.third()).mustBeEqual("conditionalValue2");
            mut.setThirdIfNew(null, Is::Null);
            attest(mut.third()).mustBeEqual(null);

            mut.setThirdIfCurrent("conditionalValue3", Is::notNull);
            attest(mut.third()).mustBeEqual(null);
            mut.setThirdIfCurrent("conditionalValue3", Is::Null);
            attest(mut.third()).mustBeEqual("conditionalValue3");
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

        if ( tuple instanceof LQuad.Mut mut) {

            mut.setFourthIf("conditionalValue", (o, n)  -> n == null);
            attest(mut.fourth()).mustBeEqual(V4);
            mut.setFourthIf("conditionalValue2", (o, n)  -> n != null);
            attest(mut.fourth()).mustBeEqual("conditionalValue2");

            mut.setFourthIfNew(null, Is::notNull);
            attest(mut.fourth()).mustBeEqual("conditionalValue2");
            mut.setFourthIfNew(null, Is::Null);
            attest(mut.fourth()).mustBeEqual(null);

            mut.setFourthIfCurrent("conditionalValue3", Is::notNull);
            attest(mut.fourth()).mustBeEqual(null);
            mut.setFourthIfCurrent("conditionalValue3", Is::Null);
            attest(mut.fourth()).mustBeEqual("conditionalValue3");
        }
    }

}
