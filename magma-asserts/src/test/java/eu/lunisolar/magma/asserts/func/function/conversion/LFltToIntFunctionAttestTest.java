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

package eu.lunisolar.magma.asserts.func.function.conversion;

import eu.lunisolar.magma.asserts.func.FuncAttests;
import eu.lunisolar.magma.func.supp.check.Checks;
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.LFltToIntFunction;
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.*; //NOSONAR
import java.util.function.*; //NOSONAR

@SuppressWarnings("ALL")
public class LFltToIntFunctionAttestTest {

    private int testValue = 100;

    private LFltToIntFunction function = a ->
            testValue;


    private LFltToIntFunction functionThrowing = a -> {
        throw new UnsupportedOperationException();
    };

    @Test
    public void testAssertPositive() throws ParseException {

        FuncAttests.attestFltToIntFunc(function)
         .doesApplyAsInt(100f)
            .to(a -> a.mustEx(Be::equalEx, testValue));

    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertNegative() throws ParseException {

        FuncAttests.attestFltToIntFunc(function)
         .doesApplyAsInt(100f)
            .to(a -> a.mustEx(Be::equalEx, 0));

    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s)Actual .+: Case .+, check \\?; Should evaluate without problem: null")
    public void testAssertThrowsUnexpected() throws ParseException {

        FuncAttests.attestFltToIntFunc(functionThrowing)
         .doesApplyAsInt(100f)
            .to(a -> a.mustEx(Be::equalEx, 0));
    }

    @Test
    public void testAssertThrowsExpected() throws ParseException {

        FuncAttests.attestFltToIntFunc(functionThrowing)
         .doesApplyAsInt(100f).withException(a -> a
                   .mustEx(Be::exactlyInstanceOfEx, UnsupportedOperationException.class)
                   .mustEx(Have::noMsgEx));

    }

    @Test
    public void testRecurringAssertsPositive() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        FuncAttests.attestFltToIntFunc(function)
         .inAllFollowingCases(a-> {
            recurringAssertsCalls.incrementAndGet();
            a.mustEx(Be::equalEx, testValue);
         })
         .doesApplyAsInt(100f)
            .to(a -> a.mustEx(Be::equalEx, testValue))
         .doesApplyAsInt(100f)
            .to(a -> a.mustEx(Be::equalEx, testValue));

        Checks.attest(recurringAssertsCalls.get()).mustEx(Be::equalEx, 2);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s)Actual .+: Case .+, check \\?; Recurring assertion failed: .*")
    public void testRecurringAssertsNegative() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        FuncAttests.attestFltToIntFunc(function)
         .inAllFollowingCases(a-> {
            int i = recurringAssertsCalls.incrementAndGet();
            if (i>1) {
                a.mustEx(Be::equalEx, 0);
            }
         })
         .doesApplyAsInt(100f)
            .to(a -> a.mustEx(Be::equalEx, testValue))
         .doesApplyAsInt(100f)
            .to(a -> a.mustEx(Be::equalEx, testValue));

        Checks.attest(recurringAssertsCalls.get()).mustEx(Be::equalEx, 2);
    }

}
