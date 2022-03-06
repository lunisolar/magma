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

package eu.lunisolar.magma.func.build.function.from;

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
import org.testng.Assert;
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
import org.testng.annotations.*;      //NOSONAR
import java.util.regex.Pattern;          //NOSONAR
import java.text.ParseException;         //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; //NOSONAR
import java.util.concurrent.atomic.AtomicInteger; //NOSONAR
import java.util.function.*; //NOSONAR

import static eu.lunisolar.magma.func.build.function.from.LTriLongFunctionBuilder.triLongFunction;
import static eu.lunisolar.magma.func.build.function.from.LTriLongFunctionBuilder.triLongFunctionFrom;

public class LTriLongFunctionBuilderTest<R>{

    @Test
    public void testOtherwiseThrow()  {

        try {
            LTriLongFunction<Integer> function = triLongFunctionFrom(b-> b
                .build()
            );

            function.apply(100L,100L,100L);

            Assert.fail("No exception was thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), IllegalStateException.class);
            Assert.assertTrue(e.getMessage().contains("There is no case configured for the arguments (if any)."));
        }
    }

    @Test
    public void testHandlingCanBeSetOnlyOnce()  {

        try {
            LTriLongFunction<Integer> function = triLongFunctionFrom(b-> b
                .withHandling(h -> h.wrapIf(RuntimeException.class::isInstance, RuntimeException::new))
                .build(h -> h.wrapIf(RuntimeException.class::isInstance, RuntimeException::new))
            );

            Assert.fail("No exception was thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), UnsupportedOperationException.class);
            Assert.assertTrue(e.getMessage().contains("Handling is already set for this builder."));
        }
    }

    @Test
    public void testHandling()  {

        try {
            LTriLongFunction<Integer> function = triLongFunctionFrom(b -> b
                .otherwise((a1,a2,a3) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapIf(RuntimeException.class::isInstance,  IllegalStateException::new, "NEW EXCEPTION"))
            );

            function.apply(100L,100L,100L);

            Assert.fail("No exception was thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), IllegalStateException.class);
            Assert.assertTrue(e.getMessage().contains("NEW EXCEPTION"));
            Assert.assertSame(e.getCause().getClass(), RuntimeException.class);
        }

    }


    @Test
    public void testBuild()  {

        LTriLongFunction<Integer> function = triLongFunctionFrom( b -> b
            .aCase(ce -> ce.of((a1,a2,a3) -> a1 == 0L)
                             .evaluate((a1,a2,a3) -> 0))
            .inCase((a1,a2,a3) -> a1 > 0L && a1 < 10L).evaluate((a1,a2,a3) -> 1)
            .inCase((a1,a2,a3) -> a1 > 10L && a1 < 20L).evaluate((a1,a2,a3) -> 2)
            .otherwise((a1,a2,a3) -> 99)
            .build()
        );


        FuncAttests.attestTriLongFunc(function)
            .doesApply(0L,0L,0L).when(null).to(a -> a.must$(Be::equal$, 0))
            .doesApply(5L,5L,5L).when(null).to(a -> a.must$(Be::equal$, 1))
            .doesApply(15L,15L,15L).when(null).to(a -> a.must$(Be::equal$, 2))
            .doesApply(10L,10L,10L).when(null).to(a -> a.must$(Be::equal$, 99))
        ;

    }

}
