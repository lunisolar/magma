/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.asserts.func.FuncAttests;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.check.Checks;
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

import static eu.lunisolar.magma.func.build.consumer.primitives.bi.LBiByteConsumerBuilder.biByteConsumer;
import static eu.lunisolar.magma.func.build.consumer.primitives.bi.LBiByteConsumerBuilder.biByteConsumerFrom;
import static org.assertj.core.api.Assertions.*; //NOSONAR

public class LBiByteConsumerBuilderTest{

    @Test
    public void testOtherwiseThrow()  {

        assertThatThrownBy(() -> {
            LBiByteConsumer function = biByteConsumerFrom(b-> b
                .build()
            );

            function.accept((byte)100,(byte)100);

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("There is no case configured for the arguments (if any).");
    }

    @Test
    public void testHandlingCanBeSetOnlyOnce()  {


        assertThatThrownBy(() -> {
            LBiByteConsumer function = biByteConsumerFrom(b-> b
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
            LBiByteConsumer function = biByteConsumerFrom(b -> b
                .otherwise((a1,a2) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapIf(RuntimeException.class::isInstance,  IllegalStateException::new, "NEW EXCEPTION"))
            );

            function.accept((byte)100,(byte)100);

            fail("No exception was thrown.");
        })
                    .isExactlyInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("NEW EXCEPTION")
                    .hasCauseExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void testBuild()  {
        final AtomicInteger externalEffect = new AtomicInteger(0);

        LBiByteConsumer function = biByteConsumerFrom( b -> b
            .aCase(ce -> ce.of((a1,a2) -> a1 == (byte)0)
                             .evaluate((a1,a2) -> externalEffect.set(0)))
            .inCase((a1,a2) -> a1 > (byte)0 && a1 < (byte)10).evaluate((a1,a2) -> externalEffect.set(1))
            .inCase((a1,a2) -> a1 > (byte)10 && a1 < (byte)20).evaluate((a1,a2) -> externalEffect.set(2))
            .otherwise((a1,a2) -> externalEffect.set(99))
            .build()
        );


        FuncAttests.attestBiByteCons(function)
            .doesAccept((byte)0,(byte)0).when(null).soThat(() -> Checks.attest(externalEffect.get()).must$(Be::equal$, 0))
            .doesAccept((byte)5,(byte)5).when(null).soThat(() -> Checks.attest(externalEffect.get()).must$(Be::equal$, 1))
            .doesAccept((byte)15,(byte)15).when(null).soThat(() -> Checks.attest(externalEffect.get()).must$(Be::equal$, 2))
            .doesAccept((byte)10,(byte)10).when(null).soThat(() -> Checks.attest(externalEffect.get()).must$(Be::equal$, 99))
        ;

    }

}
