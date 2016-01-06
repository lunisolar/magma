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
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.assertj.core.api.ObjectAssert;//NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR

import static eu.lunisolar.magma.func.Function4U.doNothing;
import static eu.lunisolar.magma.func.build.operator.binary.LBinaryOperatorBuilder.binaryOperator;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LBinaryOperatorBuilderTest<T,X extends Throwable>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws X {

        assertThatThrownBy(() -> {
            LBinaryOperator function = LBinaryOperatorBuilder.binaryOperator()
                .build();

            function.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(LBinaryOperator.DESCRIPTION);
    }

    @Test
    public void testHandlingCanBesetOnlyOnce() throws X {


        assertThatThrownBy(() -> {
            LBinaryOperator function = LBinaryOperatorBuilder.binaryOperator()
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
            LBinaryOperator function = LBinaryOperatorBuilder.binaryOperator()
                .eventually((a1,a2) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapWhen(p -> p.isRuntime(),  IllegalStateException::new, "NEW EXCEPTION"));

            function.doApply((T)Integer.valueOf(100),(T)Integer.valueOf(100));

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild() throws X {

        LBinaryOperator<Integer > function = binaryOperator((LBinaryOperator<Integer > f)-> doNothing())
            .aCase(ce -> ce.of((a1,a2) -> a1 == Integer.valueOf(0))
                             .evaluate((a1,a2) -> Integer.valueOf(0)))
            .inCase((a1,a2) -> a1 > 0 && a1 < 10).evaluate((a1,a2) -> Integer.valueOf(1))
            .inCase((a1,a2) -> a1 > 10 && a1 < 20).evaluate((a1,a2) -> Integer.valueOf(2))
            .eventually((a1,a2) -> Integer.valueOf(99))
            .build();


        A.assertThat(function)
            .doesApply(Integer.valueOf(0),Integer.valueOf(0)).when(null).to(a -> a.isEqualTo(Integer.valueOf(0)))
            .doesApply(Integer.valueOf(5),Integer.valueOf(5)).when(null).to(a -> a.isEqualTo(Integer.valueOf(1)))
            .doesApply(Integer.valueOf(15),Integer.valueOf(15)).when(null).to(a -> a.isEqualTo(Integer.valueOf(2)))
            .doesApply(Integer.valueOf(10),Integer.valueOf(10)).when(null).to(a -> a.isEqualTo(Integer.valueOf(99)))
        ;

    }

}
