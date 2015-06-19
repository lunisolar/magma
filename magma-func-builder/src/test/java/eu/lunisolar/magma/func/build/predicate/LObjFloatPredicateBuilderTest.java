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

package eu.lunisolar.magma.func.build.predicate;

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
import static eu.lunisolar.magma.func.build.predicate.LObjFloatPredicateBuilder.objFloatPredicate;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LObjFloatPredicateBuilderTest<T,X extends Throwable>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws Throwable {

        assertThatThrownBy(() -> {
            LObjFloatPredicate function = LObjFloatPredicateBuilder.objFloatPredicate()
                .build();

            function.doTest((T)Integer.valueOf(100),(float)100);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(LObjFloatPredicate.DESCRIPTION);
    }

    @Test
    public void testHandlingCanBesetOnlyOnce() throws Throwable {


        assertThatThrownBy(() -> {
            LObjFloatPredicate function = LObjFloatPredicateBuilder.objFloatPredicate()
                .withHandling(h -> h.wrapWhen(p -> p.isRuntime(), RuntimeException::new))
                .build(h -> h.wrapWhen(p -> p.isRuntime(), RuntimeException::new));

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("Handling is allready set for this builder.");
    }

    @Test
    public void testHandling() throws Throwable {

        assertThatThrownBy(() -> {
            LObjFloatPredicate function = LObjFloatPredicateBuilder.objFloatPredicate()
                .eventually((t, f) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapWhen(p -> p.isRuntime(),  IllegalStateException::new, "NEW EXCEPTION"));

            function.doTest((T)Integer.valueOf(100),(float)100);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild() throws Throwable {

        LObjFloatPredicate<Integer > function = objFloatPredicate((LObjFloatPredicate<Integer > f)-> doNothing())
            .addCase(ce -> ce.of((t, f) -> t == Integer.valueOf(0))
                             .evaluate((t, f) -> false))
            .inCase((t, f) -> t > 0 && t < 10).evaluate((t, f) -> true)
            .inCase((t, f) -> t > 10 && t < 20).evaluate((t, f) -> true)
            .eventually((t, f) -> true)
            .build();


        A.assertThat(function)
            .doesTest(Integer.valueOf(0),(float)0).to(a -> a.isEqualTo(false))
            .doesTest(Integer.valueOf(5),(float)5).to(a -> a.isEqualTo(true))
            .doesTest(Integer.valueOf(15),(float)15).to(a -> a.isEqualTo(true))
            .doesTest(Integer.valueOf(10),(float)10).to(a -> a.isEqualTo(true))
        ;

    }

}
