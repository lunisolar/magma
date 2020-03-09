/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.build.std;

import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects;// NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import org.assertj.core.api.Assertions;  //NOSONAR
import org.assertj.core.api.ObjectAssert;//NOSONAR
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import java.util.function.*; //NOSONAR

import static eu.lunisolar.magma.func.build.std.IntToLongFunctionBuilder.intToLongFunction;
import static eu.lunisolar.magma.func.build.std.IntToLongFunctionBuilder.intToLongFunctionFrom;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class IntToLongFunctionBuilderTest{

    @SuppressWarnings("unchecked")
    public static final DefaultMagmaAssertions<ObjectAssert> A = new DefaultMagmaAssertions() {
    };

    @Test
    public void testOtherwiseThrow()  {

        assertThatThrownBy(() -> {
            IntToLongFunction function = intToLongFunctionFrom(b-> b
                .build()
            );

            function.applyAsLong(100);

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("There is no case configured for the arguments (if any).");
    }

    @Test
    public void testHandlingCanBeSetOnlyOnce()  {


        assertThatThrownBy(() -> {
            IntToLongFunction function = intToLongFunctionFrom(b-> b
                .withHandling(h -> h.wrapIf(RuntimeException.class::isInstance, RuntimeException::new))
                .build(h -> h.wrapIf(RuntimeException.class::isInstance, RuntimeException::new))
            );

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("Handling is already set for this builder.");
    }

    @Test
    public void testHandling()  {

        assertThatThrownBy(() -> {
            IntToLongFunction function = intToLongFunctionFrom(b -> b
                .otherwise(a -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapIf(RuntimeException.class::isInstance,  IllegalStateException::new, "NEW EXCEPTION"))
            );

            function.applyAsLong(100);

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild()  {

        IntToLongFunction function = intToLongFunctionFrom( b -> b
            .aCase(ce -> ce.of(a -> a == 0)
                             .evaluate(a -> 0L))
            .inCase(a -> a > 0 && a < 10).evaluate(a -> 1L)
            .inCase(a -> a > 10 && a < 20).evaluate(a -> 2L)
            .otherwise(a -> 99L)
            .build()
        );


        A.assertIntToLongFunc(function)
            .doesApplyAsLong(0).when(null).to(a -> a.isEqualTo(0L))
            .doesApplyAsLong(5).when(null).to(a -> a.isEqualTo(1L))
            .doesApplyAsLong(15).when(null).to(a -> a.isEqualTo(2L))
            .doesApplyAsLong(10).when(null).to(a -> a.isEqualTo(99L))
        ;

    }

}
