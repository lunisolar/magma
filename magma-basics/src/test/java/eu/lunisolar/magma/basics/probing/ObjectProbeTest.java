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

package eu.lunisolar.magma.basics.probing;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectProbeTest {

    private static final String TEXT = "text";

    @Test
    public void testIsSameAs() throws Exception {
        assertThat(ObjectProbe.of(null).isSameAs(null)).isTrue();
        assertThat(ObjectProbe.of(1).isSameAs(2l)).isFalse();
        assertThat(ObjectProbe.of(TEXT).isSameAs(TEXT)).isTrue();
        assertThat(ObjectProbe.of(TEXT).isSameAs(new String("text"))).isFalse();
    }

    @Test
    public void testIsNotSameAs() throws Exception {
        assertThat(ObjectProbe.of(null).isNotSameAs(null)).isFalse();
        assertThat(ObjectProbe.of(1).isNotSameAs(2l)).isTrue();
        assertThat(ObjectProbe.of(TEXT).isNotSameAs(TEXT)).isFalse();
        assertThat(ObjectProbe.of(TEXT).isNotSameAs(new String("text"))).isTrue();
    }

    @Test
    public void testIsEqualTo() throws Exception {
        assertThat(ObjectProbe.of(null).isEqualTo(null)).isTrue();
        assertThat(ObjectProbe.of(1).isEqualTo(2l)).isFalse();
        assertThat(ObjectProbe.of(TEXT).isEqualTo(TEXT)).isTrue();
        assertThat(ObjectProbe.of(TEXT).isEqualTo(new String("text"))).isTrue();
        assertThat(ObjectProbe.of(TEXT).isEqualTo(2)).isFalse();
    }

    @Test
    public void testIsNotEqualTo() throws Exception {
        assertThat(ObjectProbe.of(null).isNotEqualTo(null)).isFalse();
        assertThat(ObjectProbe.of(1).isNotEqualTo(2l)).isTrue();
        assertThat(ObjectProbe.of(TEXT).isNotEqualTo(TEXT)).isFalse();
        assertThat(ObjectProbe.of(TEXT).isNotEqualTo(new String("text"))).isFalse();
        assertThat(ObjectProbe.of(TEXT).isNotEqualTo(2)).isTrue();
    }

    @Test
    public void testIsIn() throws Exception {
        assertThat(ObjectProbe.of(null).isIn()).isFalse();
        assertThat(ObjectProbe.of(null).isIn(null)).isFalse();
        assertThat(ObjectProbe.of(null).isIn(new Object[]{null})).isTrue();

        assertThat(ObjectProbe.of(1).isIn()).isFalse();
        assertThat(ObjectProbe.of(1).isIn(null)).isFalse();
        assertThat(ObjectProbe.of(1).isIn(new Object[]{null})).isFalse();

        assertThat(ObjectProbe.of(1).isIn(1, 2)).isTrue();
        assertThat(ObjectProbe.of(2).isIn(1, 2)).isTrue();
        assertThat(ObjectProbe.of(3).isIn(1, 2)).isFalse();

        assertThat(ObjectProbe.of(1).isIn(1, 2, null)).isTrue();
        assertThat(ObjectProbe.of(2).isIn(1, 2, null)).isTrue();
        assertThat(ObjectProbe.of(3).isIn(1, 2, null)).isFalse();
        assertThat(ObjectProbe.of(null).isIn(1, 2, null)).isTrue();
        assertThat(ObjectProbe.of(null).isIn(1, 2)).isFalse();

        assertThat(ObjectProbe.of(TEXT).isIn(1, 2)).isFalse();
    }

    @Test
    public void testIsNotIn() throws Exception {
        assertThat(ObjectProbe.of(null).isNotIn()).isTrue();
        assertThat(ObjectProbe.of(null).isNotIn(null)).isTrue();
        assertThat(ObjectProbe.of(null).isNotIn(new Object[]{null})).isFalse();

        assertThat(ObjectProbe.of(1).isNotIn()).isTrue();
        assertThat(ObjectProbe.of(1).isNotIn(null)).isTrue();
        assertThat(ObjectProbe.of(1).isNotIn(new Object[]{null})).isTrue();

        assertThat(ObjectProbe.of(1).isNotIn(1, 2)).isFalse();
        assertThat(ObjectProbe.of(2).isNotIn(1, 2)).isFalse();
        assertThat(ObjectProbe.of(3).isNotIn(1, 2)).isTrue();

        assertThat(ObjectProbe.of(1).isNotIn(1, 2, null)).isFalse();
        assertThat(ObjectProbe.of(2).isNotIn(1, 2, null)).isFalse();
        assertThat(ObjectProbe.of(3).isNotIn(1, 2, null)).isTrue();
        assertThat(ObjectProbe.of(null).isNotIn(1, 2, null)).isFalse();
        assertThat(ObjectProbe.of(null).isNotIn(1, 2)).isTrue();

        assertThat(ObjectProbe.of(TEXT).isNotIn(1, 2)).isTrue();
    }

    @Test
    public void testIsInstanceOf() throws Exception {
        assertThat(ObjectProbe.of(null).isInstanceOf(Object.class)).isFalse();
        assertThat(ObjectProbe.of(1).isInstanceOf(Object.class)).isTrue();
        assertThat(ObjectProbe.of(1).isInstanceOf(Integer.class)).isTrue();
        assertThat(ObjectProbe.of(1).isInstanceOf(Number.class)).isTrue();
        assertThat(ObjectProbe.of(1).isInstanceOf(String.class)).isFalse();
    }

    @Test
    public void testIsNotInstanceOf() throws Exception {
        assertThat(ObjectProbe.of(null).isNotInstanceOf(Object.class)).isTrue();
        assertThat(ObjectProbe.of(1).isNotInstanceOf(Object.class)).isFalse();
        assertThat(ObjectProbe.of(1).isNotInstanceOf(Integer.class)).isFalse();
        assertThat(ObjectProbe.of(1).isNotInstanceOf(Number.class)).isFalse();
        assertThat(ObjectProbe.of(1).isNotInstanceOf(String.class)).isTrue();
    }

    @Test
    public void testIsInstanceOfAny() throws Exception {
        assertThat(ObjectProbe.of(null).isInstanceOfAny(Object.class)).isFalse();
        assertThat(ObjectProbe.of(1).isInstanceOfAny(Object.class)).isTrue();
        assertThat(ObjectProbe.of(1).isInstanceOfAny(Integer.class)).isTrue();
        assertThat(ObjectProbe.of(1).isInstanceOfAny(Number.class)).isTrue();
        assertThat(ObjectProbe.of(1).isInstanceOfAny(String.class)).isFalse();

    }

    @Test
    public void testIsNotInstanceOfAny() throws Exception {
        assertThat(ObjectProbe.of(null).isNotInstanceOfAny(Object.class)).isTrue();
        assertThat(ObjectProbe.of(1).isNotInstanceOfAny(Object.class)).isFalse();
        assertThat(ObjectProbe.of(1).isNotInstanceOfAny(Integer.class)).isFalse();
        assertThat(ObjectProbe.of(1).isNotInstanceOfAny(Number.class)).isFalse();
        assertThat(ObjectProbe.of(1).isNotInstanceOfAny(String.class)).isTrue();
    }

    @Test
    public void testHasSameClassAs() throws Exception {
        assertThat(ObjectProbe.of(null).hasSameClassAs(1)).isFalse();
        assertThat(ObjectProbe.of(1).hasSameClassAs(1)).isTrue();
        assertThat(ObjectProbe.of("1").hasSameClassAs(1)).isFalse();
    }

    @Test
    public void testDoesNotHaveSameClassAs() throws Exception {
        assertThat(ObjectProbe.of(null).doesNotHaveSameClassAs(1)).isTrue();
        assertThat(ObjectProbe.of(1).doesNotHaveSameClassAs(1)).isFalse();
        assertThat(ObjectProbe.of("1").doesNotHaveSameClassAs(1)).isTrue();
    }

    @Test
    public void testIsExactlyInstanceOf() throws Exception {
        assertThat(ObjectProbe.of(null).isExactlyInstanceOf(Object.class)).isFalse();
        assertThat(ObjectProbe.of(1).isExactlyInstanceOf(Object.class)).isFalse();
        assertThat(ObjectProbe.of(1).isExactlyInstanceOf(Integer.class)).isTrue();
        assertThat(ObjectProbe.of(1).isExactlyInstanceOf(Number.class)).isFalse();
        assertThat(ObjectProbe.of("1").isExactlyInstanceOf(String.class)).isTrue();
    }

    @Test
    public void testIsNotExactlyInstanceOf() throws Exception {
        assertThat(ObjectProbe.of(null).isNotExactlyInstanceOf(Object.class)).isTrue();
        assertThat(ObjectProbe.of(1).isNotExactlyInstanceOf(Object.class)).isTrue();
        assertThat(ObjectProbe.of(1).isNotExactlyInstanceOf(Integer.class)).isFalse();
        assertThat(ObjectProbe.of(1).isNotExactlyInstanceOf(Number.class)).isTrue();
        assertThat(ObjectProbe.of("1").isNotExactlyInstanceOf(String.class)).isFalse();
    }
}