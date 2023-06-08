/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.supp.check;

import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.P2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.MsgVerbosity.ALL;
import static eu.lunisolar.magma.func.supp.MsgVerbosity.MIN;
import static eu.lunisolar.magma.func.supp.check.Checks.arg;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static java.util.Arrays.asList;

public class Check_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    public static <V> Object[] case_(LConsumer<V> check, V passing, V nonPassing, Class<? extends Throwable> clazz, String message) {
        return new Object[]{check, passing, nonPassing, clazz, message};
    }

    @DataProvider(name = "cases")
    public static Object[][] cases() {
        return new Object[][]{
                //
                // 0 arg
                case_(v -> arg(v).mustEx(Be::TrueEx), true, false, IllegalArgumentException.class,
                      "Argument [?]: <false> must be true."),
                case_(v -> arg(v).must(Be::True, ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [?]: >MyMsg<"),
                case_(v -> arg(v, "arg1").must(Be::True, ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [arg1]: >MyMsg<"),
                case_(v -> arg(v).verbose().must(Be::True, ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [?=='false']: >MyMsg<"),
                case_(v -> arg(v, "arg1").verbose().must(Be::True, ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [arg1=='false']: >MyMsg<"),
                case_(v -> arg(v, "arg1").verbosity(ALL).must(Be::True, ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [arg1=='false']: >MyMsg<"),
                case_(v -> arg(v, "arg1").verbosity(ALL).must1(Be::True, ">MyMsg< %s", "str"), true, false, IllegalArgumentException.class,
                      "Argument [arg1=='false']: >MyMsg< str"),
                case_(v -> arg(v, "arg1").verbosity(ALL).must2(Be::True, ">MyMsg< %s %d", "str", 34), true, false, IllegalArgumentException.class,
                      "Argument [arg1=='false']: >MyMsg< str 34"),
                case_(v -> arg(v, "arg1").verbosity(ALL).must_(Be::True, __ -> ">" + __ + "<"), true, false, IllegalArgumentException.class,
                      ">false<"),
                case_(v -> arg(v, "arg1").verbosity(ALL).mustEx0(Be::TrueEx, ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [arg1=='false']: <false> must be true. - >MyMsg<"),
                case_(v -> arg(v, "arg1").verbosity(ALL).mustEx2(Be::TrueEx, ">MyMsg< %s %d", "str", 34), true, false, IllegalArgumentException.class,
                      "Argument [arg1=='false']: <false> must be true. - >MyMsg< str 34"),
                //
                case_(v -> attest(v).must(Be::True, ">MyMsg<"), true, false, AssertionError.class,
                      "Check/attest [?=='false']: >MyMsg<"),
                case_(v -> attest(v, "result1").must(Be::True, ">MyMsg<"), true, false, AssertionError.class,
                      "Check/attest [result1=='false']: >MyMsg<"),
                case_(v -> attest(v, "result1").verbosity(MIN).must(Be::True, ">MyMsg<"), true, false, AssertionError.class,
                      "Check/attest [result1]: >MyMsg<"),
                case_(v -> attest(v).mustEx(Be::TrueEx), true, false, AssertionError.class,
                      "Check/attest [?=='false']: <false> must be true."),

                //
                // 1 arg
                case_(v -> arg(v).mustEx(Be::sameEx, true), true, false, IllegalArgumentException.class,
                      "Argument [?]: Object <false> must be the same as <true>."),

                case_(v -> arg(v).must(Be::same, true, ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [?]: >MyMsg<"),

                // 1 arg + 2 extra
                case_(v -> arg(v).mustEx(P2::sameEx, true, "x1", "x2"), true, false, IllegalArgumentException.class,
                      "Argument [?]: Object <false> must be the same as <true>."),// no effect of having two additional args in predicate
                case_(v -> arg(v).must(P2::same, true, "x1", "x2", ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [?]: >MyMsg<"),// no effect of having two additional args in predicate

                case_(v -> arg(v).verbose().mustEx(P2::sameEx, true, "x1", "x2"), true, false, IllegalArgumentException.class,
                      "Argument [?=='false'](params: 'true', 'x1', 'x2'): Object <false> must be the same as <true>."),
                case_(v -> arg(v).verbose().must(P2::same, true, "x1", "x2", ">MyMsg<"), true, false, IllegalArgumentException.class,
                      "Argument [?=='false'](params: 'true', 'x1', 'x2'): >MyMsg<"),

                case_(v -> arg(v).must_(P2::same, true, "x1", "x2", (__, a1, x1, x2) -> "" + __ + "," + a1 + "," + x2 + "," + x2), true, false,
                      IllegalArgumentException.class, "false,true,x2,x2"), // real reason why use predicate with extra args.
                // array
                case_(v -> arg(v).mustAEx(P::containExactlyEx, 1, 2, 3, 4), asList(1, 2, 3, 4), asList(1, 3, 2, 4), IllegalArgumentException.class,
                      "Argument [?]: Collection <[1, 3, 2, 4]> must contain exactly elements in order: <[1, 2, 3, 4]>."),

                // LAST
                case_(v -> arg(v).mustEx(Be::TrueEx), true, false, IllegalArgumentException.class,
                      "Argument [?]: <false> must be true."),
        };
    }

    @Test(dataProvider = "cases") <V> void checkTest(LConsumer<V> check, V passing, V nonPassing, Class<? extends Throwable> clazz, String message) {

        // Positive check - no exception thrown.
        check.accept(passing);

        // Negative check - no exception thrown.
        try {
            check.accept(nonPassing);
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), clazz);
            Assert.assertEquals(e.getMessage(), message);
        }
    }

    @Test
    public void test_mustBeInstanceOf() {
        try {
            Checks.attest(null).mustBeInstanceOf(Integer.class);
            throw new AssertionError("Nothing happened!");
        } catch (Throwable e) {
            if (!(e instanceof AssertionError)) {
                throw new AssertionError("Wrong exception class!: " + e.getClass());
            }

            if (!"Check/attest [?=='null']: Value <null> of actual class <null> must be instance of class <class java.lang.Integer> but is not.".equals(e.getMessage())) {
                throw new AssertionError("Wrong exception message!: " + e.getMessage());
            }
        }

    }

}
