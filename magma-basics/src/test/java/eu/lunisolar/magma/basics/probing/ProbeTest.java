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

import javax.annotation.Nullable;

import static org.assertj.core.api.Assertions.assertThat;

public class ProbeTest {

    private static final String TEXT = "text";

    private <T> Probe<T> probeOf(T o) {

        return new Probe<T>() {
            @Nullable @Override public T target() {
                return o;
            }
        };
    }

    @Test
    public void testGetTarget() throws Exception {
        assertThat(probeOf(TEXT).target())
                .isNotNull()
                .isSameAs(TEXT);
    }

    @Test
    public void testIsNotNull() throws Exception {
        assertThat(probeOf(TEXT).isNotNull())
                .isTrue();

        assertThat(probeOf(null).isNotNull())
                .isFalse();
    }

    @Test
    public void testIsNull() throws Exception {
        assertThat(probeOf(TEXT).isNull())
                .isFalse();

        assertThat(probeOf(null).isNull())
                .isTrue();
    }

    @Test
    public void testCheck1() throws Exception {
        assertThat(probeOf(TEXT).check(s -> s.length() == 4))
                .isTrue();

        assertThat(probeOf(TEXT).check(s -> s.length() != 4))
                .isFalse();
    }

    @Test
    public void testCheck2() throws Exception {
        assertThat(probeOf(TEXT).check(4, (s, l) -> s.length() == l))
                .isTrue();

        assertThat(probeOf(TEXT).check(4, (s, l) -> s.length() != l))
                .isFalse();
    }

    @Test
    public void testCheckWhen1() throws Exception {
        assertThat(probeOf(TEXT).checkOnlyWhen(s -> s != null, s -> s.length() == 4))
                .isTrue();

        assertThat(probeOf(TEXT).checkOnlyWhen(s -> s != null, s -> s.length() != 4))
                .isFalse();

        assertThat(probeOf((String) null).checkOnlyWhen(s -> s != null, s -> s.length() == 4))
                .isFalse();

        assertThat(probeOf((String) null).checkOnlyWhen(s -> s != null, s -> s.length() != 4))
                .isFalse();
    }

    @Test
    public void testCheckWhen2() throws Exception {
        assertThat(probeOf(TEXT).checkOnlyWhen(s -> s != null, 4, (s, l) -> s.length() == l))
                .isTrue();

        assertThat(probeOf(TEXT).checkOnlyWhen(s -> s != null, 4, (s, l) -> s.length() != l))
                .isFalse();

        assertThat(probeOf((String) null).checkOnlyWhen(s -> s != null, 4, (s, l) -> s.length() == l))
                .isFalse();

        assertThat(probeOf((String) null).checkOnlyWhen(s -> s != null, 4, (s, l) -> s.length() != l))
                .isFalse();
    }

    @Test
    public void testCheckWhenNotNull1() throws Exception {
        assertThat(probeOf(TEXT).checkWhenTargetNotNull(s -> s.length() == 4))
                .isTrue();

        assertThat(probeOf(TEXT).checkWhenTargetNotNull(s -> s.length() != 4))
                .isFalse();

        assertThat(probeOf((String) null).checkWhenTargetNotNull(s -> s.length() == 4))
                .isFalse();

        assertThat(probeOf((String) null).checkWhenTargetNotNull(s -> s.length() != 4))
                .isFalse();
    }

    @Test
    public void testCheckWhenNotNull2() throws Exception {
        assertThat(probeOf(TEXT).checkWhenTargetNotNull(4, (s, l) -> s.length() == l))
                .isTrue();

        assertThat(probeOf(TEXT).checkWhenTargetNotNull(4, (s, l) -> s.length() != l))
                .isFalse();

        assertThat(probeOf((String) null).checkWhenTargetNotNull(4, (s, l) -> s.length() == l))
                .isFalse();

        assertThat(probeOf((String) null).checkWhenTargetNotNull(4, (s, l) -> s.length() != l))
                .isFalse();
    }

    @Test
    public void testCheckWhenNotNullAnd1() throws Exception {
        assertThat(probeOf(TEXT).checkWhenTargetNotNullAnd(s -> s != null, s -> s.length() == 4))
                .isTrue();

        assertThat(probeOf(TEXT).checkWhenTargetNotNullAnd(s -> s != null, s -> s.length() != 4))
                .isFalse();

        assertThat(probeOf((String) null).checkWhenTargetNotNullAnd(s -> s != null, s -> s.length() == 4))
                .isFalse();

        assertThat(probeOf((String) null).checkWhenTargetNotNullAnd(s -> s != null, s -> s.length() != 4))
                .isFalse();

        assertThat(probeOf("12").checkWhenTargetNotNullAnd(s -> s != null, s -> s.length() == 4))
                .isFalse();

        assertThat(probeOf("12").checkWhenTargetNotNullAnd(s -> s != null, s -> s.length() != 4))
                .isTrue();
    }

    @Test
    public void testCheckWhenNotNullAnd2() throws Exception {
        assertThat(probeOf(TEXT).checkWhenTargetNotNullAnd(s -> s != null, 4, (s, l) -> s.length() == l))
                .isTrue();

        assertThat(probeOf(TEXT).checkWhenTargetNotNullAnd(s -> s != null, 4, (s, l) -> s.length() != l))
                .isFalse();

        assertThat(probeOf((String) null).checkWhenTargetNotNullAnd(s -> s != null, 4, (s, l) -> s.length() == l))
                .isFalse();

        assertThat(probeOf((String) null).checkWhenTargetNotNullAnd(s -> s != null, 4, (s, l) -> s.length() != l))
                .isFalse();

        assertThat(probeOf("12").checkWhenTargetNotNullAnd(s -> s != null, 4, (s, l) -> s.length() == l))
                .isFalse();

        assertThat(probeOf("12").checkWhenTargetNotNullAnd(s -> s != null, 4, (s, l) -> s.length() != l))
                .isTrue();
    }
}