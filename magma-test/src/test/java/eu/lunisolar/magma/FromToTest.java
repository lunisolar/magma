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

import eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjIntConsumer;
import eu.lunisolar.magma.func.supp.Be;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.*;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static java.util.Arrays.*;

public class FromToTest {

    @DataProvider(name = "fromToData")
    public static Object[][] fromToData() {
        return new Object[][]{
                {0, 0, asList(0), asList()},
                {0, 1, asList(0, 1), asList(0)},
                {0, 2, asList(0, 1, 2), asList(0, 1)},

                {0, -1, asList(0, -1), asList(0)},
                {0, -2, asList(0, -1, -2), asList(0, -1)},

                {-2, 3, asList(-2, -1, 0, 1, 2, 3), asList(-2, -1, 0, 1, 2)},
                {3, -2, asList(3, 2, 1, 0, -1, -2), asList(3, 2, 1, 0, -1)},
        };
    }

    @Test(dataProvider = "fromToData")
    public void fromTo(int from, int to, List fromTo, List fromTill) {
        {
            ArrayList<Object> list = new ArrayList<>();
            LBiObjIntConsumer.fromTo(from, to, list, null, (l, nil, i) -> l.add(i));
            attest(list).mustEx(Be::equalEx, fromTo);
        }

        {
            ArrayList<Object> list = new ArrayList<>();
            LBiObjIntConsumer.fromTill(from, to, list, null, (l, nil, i) -> l.add(i));
            attest(list).mustEx(Be::equalEx, fromTill);
        }
    }

    @DataProvider(name = "timesData")
    public static Object[][] timesData() {
        return new Object[][]{
                {0, asList()},
                {1, asList(0)},
                {2, asList(0, 1)},
                {-1, asList()},
                {-2, asList()},
        };
    }

    @Test(dataProvider = "timesData")
    public void times(int times, List expectedResult) {
        ArrayList<Object> list = new ArrayList<>();
        LBiObjIntConsumer.times(times, list, null, (l, nil, i) -> l.add(i));
        attest(list).mustEx(Be::equalEx, expectedResult);
    }

}
