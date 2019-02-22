/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.asserts.function.conversion;

import eu.lunisolar.magma.func.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import eu.lunisolar.magma.func.function.conversion.LBoolToIntFunction;
import org.assertj.core.api.Assertions;  //NOSONAR
import org.assertj.core.api.ObjectAssert;//NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.*; //NOSONAR
import static org.assertj.core.api.Assertions.*; //NOSONAR
import java.util.function.*; //NOSONAR

@SuppressWarnings("ALL")
public class LBoolToIntFunctionAssertTest {

    private int testValue = 100;

    @SuppressWarnings("unchecked") public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    private LBoolToIntFunction function = a ->
            testValue;


    private LBoolToIntFunction functionThrowing = a -> {
        throw new UnsupportedOperationException();
    };

    @Test
    public void testAssertPositive() throws ParseException {

        A.assertBoolToIntFunc(function)
         .doesApplyAsInt(true)
            .to(a -> a.isEqualTo(testValue));

    }

    @Test(expectedExceptions = AssertionError.class)
    public void testAssertNegative() throws ParseException {

        A.assertBoolToIntFunc(function)
         .doesApplyAsInt(true)
            .to( a -> a.isEqualTo(2));

    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Case .* should evaluate without problem.")
    public void testAssertThrowsUnexpected() throws ParseException {

        A.assertBoolToIntFunc(functionThrowing)
         .doesApplyAsInt(true)
            .to( a -> a.isEqualTo(1));
    }

    @Test
    public void testAssertThrowsExpected() throws ParseException {

        A.assertBoolToIntFunc(functionThrowing)
         .doesApplyAsInt(true).withException(a -> a
                   .isExactlyInstanceOf(UnsupportedOperationException.class)
                   .hasMessage(null));

    }

    @Test
    public void testRecurringAssertsPositive() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        A.assertBoolToIntFunc(function)
         .inAllFollowingCases(a-> {
            recurringAssertsCalls.incrementAndGet();
            a.isEqualTo(testValue);
         })
         .doesApplyAsInt(true)
            .to(a -> a.isEqualTo(testValue))
         .doesApplyAsInt(true)
            .to(a -> a.isEqualTo(testValue));

        assertThat(recurringAssertsCalls.get()).isEqualTo(2);
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "(?s).*Recurring assertion failed.*")
    public void testRecurringAssertsNegative() throws ParseException {

        final AtomicInteger recurringAssertsCalls = new AtomicInteger(0);

        A.assertBoolToIntFunc(function)
         .inAllFollowingCases(a-> {
            int i = recurringAssertsCalls.incrementAndGet();
            if (i>1) {
                a.isEqualTo(0);
            }
         })
         .doesApplyAsInt(true)
            .to(a -> a.isEqualTo(testValue))
         .doesApplyAsInt(true)
            .to(a -> a.isEqualTo(testValue));

        assertThat(recurringAssertsCalls.get()).isEqualTo(2);
    }

}
