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
import eu.lunisolar.magma.func.function.conversion.LDblToByteFunction;
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.*; //NOSONAR
import java.util.function.*; //NOSONAR

@SuppressWarnings("ALL")
public class LDblToByteFunctionAttestTest {

    private byte testValue = (byte)100;

    private LDblToByteFunction function = a ->
            testValue;


    private LDblToByteFunction functionThrowing = a -> {
        throw new UnsupportedOperationException();
    };

    @Test
    public void testAssertPositive() throws ParseException {

        FuncAttests.attestDblToByteFunc(function)
         .doesApplyAsByte(100d)
            .to(a -> a.mustEx(Be::equalEx, testValue));

    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertNegative() throws ParseException {

        FuncAttests.attestDblToByteFunc(function)
         .doesApplyAsByte(100d)
            .to(a -> a.mustEx(Be::equalEx, (byte)0));

    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s)Actual .+: Case .+, check \\?; Should evaluate without problem: null")
    public void testAssertThrowsUnexpected() throws ParseException {

        FuncAttests.attestDblToByteFunc(functionThrowing)
         .doesApplyAsByte(100d)
            .to(a -> a.mustEx(Be::equalEx, (byte)0));
    }

    @Test
    public void testAssertThrowsExpected() throws ParseException {

        FuncAttests.attestDblToByteFunc(functionThrowing)
         .doesApplyAsByte(100d).withException(a -> a
                   .mustEx(Be::exactlyInstanceOfEx, UnsupportedOperationException.class)
                   .mustEx(Have::noMsgEx));

    }

    @Test
    public void testRecurringAssertsPositive() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        FuncAttests.attestDblToByteFunc(function)
         .inAllFollowingCases(a-> {
            recurringAssertsCalls.incrementAndGet();
            a.mustEx(Be::equalEx, testValue);
         })
         .doesApplyAsByte(100d)
            .to(a -> a.mustEx(Be::equalEx, testValue))
         .doesApplyAsByte(100d)
            .to(a -> a.mustEx(Be::equalEx, testValue));

        Checks.attest(recurringAssertsCalls.get()).mustEx(Be::equalEx, 2);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s)Actual .+: Case .+, check \\?; Recurring assertion failed: .*")
    public void testRecurringAssertsNegative() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        FuncAttests.attestDblToByteFunc(function)
         .inAllFollowingCases(a-> {
            int i = recurringAssertsCalls.incrementAndGet();
            if (i>1) {
                a.mustEx(Be::equalEx, (byte)0);
            }
         })
         .doesApplyAsByte(100d)
            .to(a -> a.mustEx(Be::equalEx, testValue))
         .doesApplyAsByte(100d)
            .to(a -> a.mustEx(Be::equalEx, testValue));

        Checks.attest(recurringAssertsCalls.get()).mustEx(Be::equalEx, 2);
    }

}
