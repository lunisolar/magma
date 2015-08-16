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

package eu.lunisolar.magma.func.build.consumer.primitives.bi;

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
import static eu.lunisolar.magma.func.build.consumer.primitives.bi.LBiIntConsumerBuilder.biIntConsumer;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LBiIntConsumerBuilderTest<X extends Throwable>{

    @SuppressWarnings("unchecked")
    public static final DefaultFunctionalAssertions<ObjectAssert> A = new DefaultFunctionalAssertions() {
    };

    @Test
    public void testEventuallyThrow() throws X {

        assertThatThrownBy(() -> {
            LBiIntConsumer function = LBiIntConsumerBuilder.biIntConsumer()
                .build();

            function.doAccept((int)100,(int)100);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("No case specified for:")
                    .hasMessageContaining(LBiIntConsumer.DESCRIPTION);
    }

    @Test
    public void testHandlingCanBesetOnlyOnce() throws X {


        assertThatThrownBy(() -> {
            LBiIntConsumer function = LBiIntConsumerBuilder.biIntConsumer()
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
            LBiIntConsumer function = LBiIntConsumerBuilder.biIntConsumer()
                .eventually((i1,i2) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapWhen(p -> p.isRuntime(),  IllegalStateException::new, "NEW EXCEPTION"));

            function.doAccept((int)100,(int)100);

            fail("No exception were thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild() throws X {
        final AtomicInteger externalEffect = new AtomicInteger(0);

        LBiIntConsumer function = biIntConsumer((LBiIntConsumer f)-> doNothing())
            .aCase(ce -> ce.of((i1,i2) -> i1 == (int)0)
                             .evaluate((i1,i2) -> externalEffect.set(0)))
            .inCase((i1,i2) -> i1 > 0 && i1 < 10).evaluate((i1,i2) -> externalEffect.set(1))
            .inCase((i1,i2) -> i1 > 10 && i1 < 20).evaluate((i1,i2) -> externalEffect.set(2))
            .eventually((i1,i2) -> externalEffect.set(99))
            .build();


        A.assertThat(function)
            .doesAccept((int)0,(int)0).when(null).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(0)))
            .doesAccept((int)5,(int)5).when(null).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(1)))
            .doesAccept((int)15,(int)15).when(null).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(2)))
            .doesAccept((int)10,(int)10).when(null).soThat(() -> assertThat(externalEffect.get()).isEqualTo(Integer.valueOf(99)))
        ;

    }

}

