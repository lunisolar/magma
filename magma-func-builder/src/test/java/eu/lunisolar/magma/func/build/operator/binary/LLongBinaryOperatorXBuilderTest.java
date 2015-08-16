/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.build.operator.binary;

import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.assertj.core.api.ObjectAssert;//NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR

import static eu.lunisolar.magma.func.Function4U.doNothing;
import static eu.lunisolar.magma.func.build.operator.binary.LLongBinaryOperatorXBuilder.longBinaryOperatorX;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LLongBinaryOperatorXBuilderTest<X extends Throwable>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws X {

        assertThatThrownBy(() -> {
            LLongBinaryOperatorX function = LLongBinaryOperatorXBuilder.longBinaryOperatorX()
                .build();

            function.doApplyAsLong((long)100,(long)100);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(LLongBinaryOperatorX.DESCRIPTION);
    }

    @Test
    public void testHandlingCanBesetOnlyOnce() throws X {


        assertThatThrownBy(() -> {
            LLongBinaryOperatorX function = LLongBinaryOperatorXBuilder.longBinaryOperatorX()
                .withHandling(h -> h.wrapWhen(p -> p.isRuntime(), RuntimeException::new))
                .build(h -> h.wrapWhen(p -> p.isRuntime(), RuntimeException::new));

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("Handling is already set for this builder.");
    }

    @Test
    public void testHandling() throws X {

        assertThatThrownBy(() -> {
            LLongBinaryOperatorX function = LLongBinaryOperatorXBuilder.longBinaryOperatorX()
                .eventually((l1,l2) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapWhen(p -> p.isRuntime(),  IllegalStateException::new, "NEW EXCEPTION"));

            function.doApplyAsLong((long)100,(long)100);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild() throws X {

        LLongBinaryOperatorX<ParseException> function = longBinaryOperatorX((LLongBinaryOperatorX<ParseException> f)-> doNothing())
            .aCase(ce -> ce.of((l1,l2) -> l1 == (long)0)
                             .evaluate((l1,l2) -> (long)0))
            .inCase((l1,l2) -> l1 > 0 && l1 < 10).evaluate((l1,l2) -> (long)1)
            .inCase((l1,l2) -> l1 > 10 && l1 < 20).evaluate((l1,l2) -> (long)2)
            .eventually((l1,l2) -> (long)99)
            .build();


        A.assertThat(function)
            .doesApplyAsLong((long)0,(long)0).when(null).to(a -> a.isEqualTo((long)0))
            .doesApplyAsLong((long)5,(long)5).when(null).to(a -> a.isEqualTo((long)1))
            .doesApplyAsLong((long)15,(long)15).when(null).to(a -> a.isEqualTo((long)2))
            .doesApplyAsLong((long)10,(long)10).when(null).to(a -> a.isEqualTo((long)99))
        ;

    }

}


