/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.build.operator.ternary;

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

import static eu.lunisolar.magma.func.build.operator.ternary.LSrtTernaryOperatorBuilder.srtTernaryOperator;
import static eu.lunisolar.magma.func.build.operator.ternary.LSrtTernaryOperatorBuilder.srtTernaryOperatorFrom;

public class LSrtTernaryOperatorBuilderTest{

    @Test
    public void testOtherwiseThrow()  {

        try {
            LSrtTernaryOperator function = srtTernaryOperatorFrom(b-> b
                .build()
            );

            function.applyAsSrt((short)100,(short)100,(short)100);

            Assert.fail("No exception was thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), IllegalStateException.class);
            Assert.assertTrue(e.getMessage().contains("There is no case configured for the arguments (if any)."));
        }
    }

    @Test
    public void testHandlingCanBeSetOnlyOnce()  {

        try {
            LSrtTernaryOperator function = srtTernaryOperatorFrom(b-> b
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
            LSrtTernaryOperator function = srtTernaryOperatorFrom(b -> b
                .otherwise((a1,a2,a3) -> {
                        throw new RuntimeException("ORIGINAL");
                    })
                .build(h -> h.wrapIf(RuntimeException.class::isInstance,  IllegalStateException::new, "NEW EXCEPTION"))
            );

            function.applyAsSrt((short)100,(short)100,(short)100);

            Assert.fail("No exception was thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), IllegalStateException.class);
            Assert.assertTrue(e.getMessage().contains("NEW EXCEPTION"));
            Assert.assertSame(e.getCause().getClass(), RuntimeException.class);
        }

    }


    @Test
    public void testBuild()  {

        LSrtTernaryOperator function = srtTernaryOperatorFrom( b -> b
            .aCase(ce -> ce.of((a1,a2,a3) -> a1 == (short)0)
                             .evaluate((a1,a2,a3) -> (short)0))
            .inCase((a1,a2,a3) -> a1 > (short)0 && a1 < (short)10).evaluate((a1,a2,a3) -> (short)1)
            .inCase((a1,a2,a3) -> a1 > (short)10 && a1 < (short)20).evaluate((a1,a2,a3) -> (short)2)
            .otherwise((a1,a2,a3) -> (short)99)
            .build()
        );


        FuncAttests.attestSrtTernaryOp(function)
            .doesApplyAsSrt((short)0,(short)0,(short)0).when(null).to(a -> a.mustEx(Be::equalEx, (short)0))
            .doesApplyAsSrt((short)5,(short)5,(short)5).when(null).to(a -> a.mustEx(Be::equalEx, (short)1))
            .doesApplyAsSrt((short)15,(short)15,(short)15).when(null).to(a -> a.mustEx(Be::equalEx, (short)2))
            .doesApplyAsSrt((short)10,(short)10,(short)10).when(null).to(a -> a.mustEx(Be::equalEx, (short)99))
        ;

    }

}
