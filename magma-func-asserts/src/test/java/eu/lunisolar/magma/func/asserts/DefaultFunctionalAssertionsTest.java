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

package eu.lunisolar.magma.func.asserts;

import javax.annotation.Nonnull;  // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.math.*;
import java.time.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.function.*;
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.action.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.supplier.*; // NOSONAR
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
//import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR

import static org.assertj.core.api.Fail.fail;

import org.testng.annotations.Test;

@SuppressWarnings("ALL")
public class DefaultFunctionalAssertionsTest {



    @Test
    public void withinOptionalCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractOptionalAssert> specialized = initial.withinOptionalCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractOptionalAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBigDecimalCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractBigDecimalAssert> specialized = initial.withinBigDecimalCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBigDecimalAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBooleanCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractBooleanAssert> specialized = initial.withinBooleanCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBooleanAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinBooleanArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractBooleanArrayAssert> specialized = initial.withinBooleanArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractBooleanArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinByteCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractByteAssert> specialized = initial.withinByteCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractByteAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinByteArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractByteArrayAssert> specialized = initial.withinByteArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractByteArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharacterCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractCharacterAssert> specialized = initial.withinCharacterCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharacterAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractCharArrayAssert> specialized = initial.withinCharArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinClassCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractClassAssert> specialized = initial.withinClassCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractClassAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinComparableCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractComparableAssert> specialized = initial.withinComparableCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractComparableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIterableCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractIterableAssert> specialized = initial.withinIterableCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIterableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIteratorCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractIteratorAssert> specialized = initial.withinIteratorCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIteratorAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDoubleCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractDoubleAssert> specialized = initial.withinDoubleCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDoubleAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDoubleArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractDoubleArrayAssert> specialized = initial.withinDoubleArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDoubleArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinPathCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractPathAssert> specialized = initial.withinPathCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractPathAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinInputStreamCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractInputStreamAssert> specialized = initial.withinInputStreamCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractInputStreamAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinFloatCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractFloatAssert> specialized = initial.withinFloatCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractFloatAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinFloatArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractFloatArrayAssert> specialized = initial.withinFloatArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractFloatArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIntegerCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractIntegerAssert> specialized = initial.withinIntegerCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIntegerAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinIntArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractIntArrayAssert> specialized = initial.withinIntArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractIntArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinListCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractListAssert> specialized = initial.withinListCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractListAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLongCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractLongAssert> specialized = initial.withinLongCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLongAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLongArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractLongArrayAssert> specialized = initial.withinLongArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLongArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinMapCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractMapAssert> specialized = initial.withinMapCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractMapAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinShortCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractShortAssert> specialized = initial.withinShortCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractShortAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinShortArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractShortArrayAssert> specialized = initial.withinShortArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractShortArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinCharSequenceCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractCharSequenceAssert> specialized = initial.withinCharSequenceCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharSequenceAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinStringCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractCharSequenceAssert> specialized = initial.withinStringCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractCharSequenceAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinDateCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractDateAssert> specialized = initial.withinDateCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractDateAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinZonedDateTimeCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractZonedDateTimeAssert> specialized = initial.withinZonedDateTimeCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractZonedDateTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalDateTimeCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractLocalDateTimeAssert> specialized = initial.withinLocalDateTimeCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalDateTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalTimeCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractLocalTimeAssert> specialized = initial.withinLocalTimeCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalTimeAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinLocalDateCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractLocalDateAssert> specialized = initial.withinLocalDateCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractLocalDateAssert.class))
            .doesApply(null).toEqualTo(null);
    }

    @Test
    public void withinThrowableCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractThrowableAssert> specialized = initial.withinThrowableCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractThrowableAssert.class))
            .doesApply(null).toEqualTo(null);
    }

@Test
    public void withinTArrayCodomain() {

        DefaultFunctionalAssertions initial = new DefaultFunctionalAssertions(){
        };

        DefaultFunctionalAssertions<AbstractObjectArrayAssert> specialized = initial.withinTArrayCodomain();

        specialized.assertFunc(LFunction.func(i->i))
            .doesApply(null).to(a -> Assertions.assertThat(a).isInstanceOf(AbstractObjectArrayAssert.class))
            .doesApply(null).toEqualTo(null);
    }

}