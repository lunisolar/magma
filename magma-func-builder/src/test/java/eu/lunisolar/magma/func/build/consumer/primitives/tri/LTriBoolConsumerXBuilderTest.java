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

package eu.lunisolar.magma.func.build.consumer.primitives.tri;

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
import static eu.lunisolar.magma.func.build.consumer.primitives.tri.LTriBoolConsumerXBuilder.triBoolConsumerX;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LTriBoolConsumerXBuilderTest<X extends Throwable>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws X {

        assertThatThrownBy(() -> {
            LTriBoolConsumerX function = LTriBoolConsumerXBuilder.triBoolConsumerX()
                .build();

            function.doAccept(true,true,true);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(LTriBoolConsumerX.DESCRIPTION);
    }

    @Test
    public void testHandlingCanBesetOnlyOnce() throws X {


        assertThatThrownBy(() -> {
            LTriBoolConsumerX function = LTriBoolConsumerXBuilder.triBoolConsumerX()
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
            LTriBoolConsumerX function = LTriBoolConsumerXBuilder.triBoolConsumerX()
                .eventually((b1,b2,b3) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapWhen(p -> p.isRuntime(),  IllegalStateException::new, "NEW EXCEPTION"));

            function.doAccept(true,true,true);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild() throws X {
        final AtomicInteger externalEffect = new AtomicInteger(0);

        LTriBoolConsumerX<ParseException> function = triBoolConsumerX((LTriBoolConsumerX<ParseException> f)-> doNothing())
            .aCase(ce -> ce.of((b1,b2,b3) -> b1 == false)
                             .evaluate((b1,b2,b3) -> externalEffect.set(0)))
            .inCase((b1,b2,b3) -> b1 == true ).evaluate((b1,b2,b3) -> externalEffect.set(1))
            .eventually((b1,b2,b3) -> externalEffect.set(99))
            .build();


        A.assertThat(function)
            .doesAccept(false,false,false).when(null).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(0)))
            .doesAccept(true,true,true).when(null).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(1)))
        ;

    }

}

