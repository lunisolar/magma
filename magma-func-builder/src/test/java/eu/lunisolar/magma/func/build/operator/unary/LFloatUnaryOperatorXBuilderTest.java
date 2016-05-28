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

package eu.lunisolar.magma.func.build.operator.unary;

import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.*; // NOSONAR
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

import static eu.lunisolar.magma.func.Function4U.doNothing;
import static eu.lunisolar.magma.func.build.operator.unary.LFloatUnaryOperatorXBuilder.floatUnaryOperatorX;
import static eu.lunisolar.magma.func.build.operator.unary.LFloatUnaryOperatorXBuilder.floatUnaryOperatorXFrom;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LFloatUnaryOperatorXBuilderTest<X extends ParseException>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow()  throws X {

        assertThatThrownBy(() -> {
            LFloatUnaryOperatorX<X> function = floatUnaryOperatorXFrom(b-> b
                .build()
            );

            function.doApplyAsFloat(100f);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("There is no case configured for the arguments (if any).");
    }

    @Test
    public void testHandlingCanBeSetOnlyOnce()  throws X {


        assertThatThrownBy(() -> {
            LFloatUnaryOperatorX<X> function = floatUnaryOperatorXFrom(b-> b
                .withHandling(h -> h.wrapWhen(p -> p.isRuntime(), RuntimeException::new))
                .build(h -> h.wrapWhen(p -> p.isRuntime(), RuntimeException::new))
            );

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(UnsupportedOperationException.class)
                    .hasMessageContaining("Handling is already set for this builder.");
    }

    @Test
    public void testHandling()  throws X {

        assertThatThrownBy(() -> {
            LFloatUnaryOperatorX<X> function = floatUnaryOperatorXFrom(b -> b
                .eventually(a -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapWhen(p -> p.isRuntime(),  IllegalStateException::new, "NEW EXCEPTION"))
            );

            function.doApplyAsFloat(100f);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild()  throws X {

        LFloatUnaryOperatorX<X> function = floatUnaryOperatorXFrom( b -> b
            .aCase(ce -> ce.of(a -> a == 0f)
                             .evaluate(a -> 0f))
            .inCase(a -> a > 0f && a < 10f).evaluate(a -> 1f)
            .inCase(a -> a > 10f && a < 20f).evaluate(a -> 2f)
            .eventually(a -> 99f)
            .build()
        );


        A.assertThat(function)
            .doesApplyAsFloat(0f).when(null).to(a -> a.isEqualTo(0f))
            .doesApplyAsFloat(5f).when(null).to(a -> a.isEqualTo(1f))
            .doesApplyAsFloat(15f).when(null).to(a -> a.isEqualTo(2f))
            .doesApplyAsFloat(10f).when(null).to(a -> a.isEqualTo(99f))
        ;

    }

}
