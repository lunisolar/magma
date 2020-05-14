
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

package eu.lunisolar.magma.asserts.opt;

import javax.annotation.Nonnull;  // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.*; // NOSONAR
import eu.lunisolar.magma.asserts.*; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.check.*; // NOSONAR
import eu.lunisolar.magma.func.supp.opt.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; // NOSONAR
import org.assertj.core.api.AbstractObjectAssert; // NOSONAR
import org.assertj.core.internal.ComparisonStrategy; // NOSONAR
import org.assertj.core.internal.Failures; // NOSONAR
import org.assertj.core.internal.StandardComparisonStrategy; // NOSONAR

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
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.arg; // NOSONAR
import static org.assertj.core.api.Assertions.*; // NOSONAR


public class OptTraitAssertTest {

    public static final Object                      VALUE       = 0;
    public static final Object                      OTHER_VALUE = 1;
    public static final OptTrait OPT         = Opt.of(VALUE);
    public static final OptTrait VOID        = Opt.empty();
    public static final OptTrait NULL        = null;

    public static final String                      VALUE_STR       = "'0'^^Integer";
    public static final String                      OTHER_VALUE_STR = "'1'^^Integer";

    @Test
    public void testActual() {
        var sut = new OptTraitAssert(OPT);

        assertThat(sut.actual()).isSameAs(OPT);
        assertThat(sut.value()).isSameAs(OPT);
        assertThat(sut.get()).isSameAs(OPT);
        assertThat(sut.nullable()).isSameAs(OPT);
        assertThat(sut.nonnull()).isSameAs(OPT);
    }

    @Test
    public void testIsPresent_OPT() {
        var sut = new OptTraitAssert(OPT).isPresent();
    }

    @Test
    public void testIsPresent_VOID() {
        var sut = new OptTraitAssert(VOID);
        assertThatThrownBy(() -> sut.isPresent()).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt.empty']: <Opt.empty> is expected to have value, but is void.");
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*Expecting actual not to be null.*")
    public void testIsPresent_NULL() {
        var sut = new OptTraitAssert(NULL).isPresent();
    }

    @Test
    public void testIsNotVoid_OPT() {
        var sut = new OptTraitAssert(OPT).isNotVoid();
    }

    @Test
    public void testIsNotVoid_VOID() {
        var sut = new OptTraitAssert(VOID);
        assertThatThrownBy(() -> sut.isNotVoid()).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt.empty']: <Opt.empty> is expected to have value, but is void.");
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*Expecting actual not to be null.*")
    public void testIsNotVoid_NULL() {
        var sut = new OptTraitAssert(NULL).isNotVoid();
    }

    @Test
    public void testIsVoid_OPT() {
        var sut = new OptTraitAssert(OPT);
        assertThatThrownBy(() -> sut.isVoid()).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt[" + VALUE_STR + "]']: <Opt[" + VALUE_STR + "]> is expected to NOT have value, but it does.");
    }

    @Test
    public void testIsVoid_VOID() {
        var sut = new OptTraitAssert(VOID).isVoid();
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*Expecting actual not to be null.*")
    public void testIsVoid_NULL() {
        var sut = new OptTraitAssert(NULL).isVoid();
    }

    @Test
    public void testIsNotPresent_OPT() {
        var sut = new OptTraitAssert(OPT);
        assertThatThrownBy(() -> sut.isNotPresent()).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt[" + VALUE_STR + "]']: <Opt[" + VALUE_STR + "]> is expected to NOT have value, but it does.");
    }

    @Test
    public void testIsNotPresent_VOID() {
        var sut = new OptTraitAssert(VOID).isNotPresent();
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*Expecting actual not to be null.*")
    public void testIsNotPresent_NULL() {
        var sut = new OptTraitAssert(NULL).isNotPresent();
    }

    @Test
    public void testContains_OPT() {
        var sut = new OptTraitAssert(OPT);
        assertThatThrownBy(() -> sut.contains(null)).isInstanceOf(IllegalArgumentException.class).hasMessage("Argument [expectedValue]: The expected value should not be null.");
        assertThatThrownBy(() -> sut.contains(OTHER_VALUE)).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt[" + VALUE_STR + "]']: Optional value <" + VALUE + "> should be equal to <" + OTHER_VALUE + ">.");
        sut.contains(VALUE);
    }

    @Test
    public void testContains_VOID() {
        var sut = new OptTraitAssert(VOID);
        assertThatThrownBy(() -> sut.contains(null)).isInstanceOf(IllegalArgumentException.class).hasMessage("Argument [expectedValue]: The expected value should not be null.");
        assertThatThrownBy(() -> sut.contains(OTHER_VALUE)).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt.empty']: <Opt.empty> is expected to have value <" + OTHER_VALUE + ">, but is void.");
        assertThatThrownBy(() -> sut.contains(VALUE)).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt.empty']: <Opt.empty> is expected to have value <" + VALUE + ">, but is void.");
    }

    @Test
    public void testContains_NULL() {
        var sut = new OptTraitAssert(NULL);
        assertThatThrownBy(() -> sut.contains(null)).isInstanceOf(AssertionError.class).hasMessageContaining("Expecting actual not to be null");
        assertThatThrownBy(() -> sut.contains(OTHER_VALUE)).isInstanceOf(AssertionError.class).hasMessageContaining("Expecting actual not to be null");
        assertThatThrownBy(() -> sut.contains(VALUE)).isInstanceOf(AssertionError.class).hasMessageContaining("Expecting actual not to be null");
    }


    @Test
    public void testContainsSame_OPT() {
        var sut = new OptTraitAssert(OPT);
        assertThatThrownBy(() -> sut.containsSame(null)).isInstanceOf(IllegalArgumentException.class).hasMessage("Argument [expectedValue]: The expected value should not be null.");
        assertThatThrownBy(() -> sut.containsSame(OTHER_VALUE)).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt[" + VALUE_STR + "]']: Optional value <" + VALUE + "> should refer to the same object as <" + OTHER_VALUE + ">, but is not.");
        sut.containsSame(VALUE);
    }

    @Test
    public void testContainsSame_VOID() {
        var sut = new OptTraitAssert(VOID);
        assertThatThrownBy(() -> sut.containsSame(null)).isInstanceOf(IllegalArgumentException.class).hasMessage("Argument [expectedValue]: The expected value should not be null.");
        assertThatThrownBy(() -> sut.containsSame(OTHER_VALUE)).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt.empty']: <Opt.empty> is expected to have value refer to the object <" + OTHER_VALUE + ">, but is void.");
        assertThatThrownBy(() -> sut.containsSame(VALUE)).isInstanceOf(AssertionError.class).hasMessage("Actual [actual=='Opt.empty']: <Opt.empty> is expected to have value refer to the object <" + VALUE + ">, but is void.");
    }

    @Test
    public void testContainsSame_NULL() {
        var sut = new OptTraitAssert(NULL);
        assertThatThrownBy(() -> sut.containsSame(null)).isInstanceOf(AssertionError.class).hasMessageContaining("Expecting actual not to be null");
        assertThatThrownBy(() -> sut.containsSame(OTHER_VALUE)).isInstanceOf(AssertionError.class).hasMessageContaining("Expecting actual not to be null");
        assertThatThrownBy(() -> sut.containsSame(VALUE)).isInstanceOf(AssertionError.class).hasMessageContaining("Expecting actual not to be null");
    }


    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = ".*Expecting:.*<.*>.*to be equal to:.*<.*>.*but was not.*")
    public void testHasValue_OPT() {
        var sut = new OptTraitAssert(OPT);
        sut.hasValueThat().isEqualTo(VALUE);
        sut.hasValueThat().isEqualTo(OTHER_VALUE);
    }

    @Test
    public void testHasValue_VOID() {
        var sut = new OptTraitAssert(VOID);
        assertThatThrownBy(() -> sut.hasValueThat())
            .isInstanceOf(AssertionError.class).hasMessageContaining("Actual [actual=='Opt.empty']: <Opt.empty> is expected to have value, but is void.");
        assertThatThrownBy(() -> sut.hasValueThat())
            .isInstanceOf(AssertionError.class).hasMessageContaining("Actual [actual=='Opt.empty']: <Opt.empty> is expected to have value, but is void.");
    }

}

