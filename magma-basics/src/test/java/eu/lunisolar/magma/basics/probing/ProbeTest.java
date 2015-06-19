/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.basics.probing;

import org.testng.annotations.Test;

import javax.annotation.Nullable;

import static org.assertj.core.api.Assertions.assertThat;

public class ProbeTest {

    private static final String TEXT = "text";

    private <T> Probe<T> probeOf(T o) {

        return new Probe<T>() {
            @Nullable @Override public T getTarget() {
                return o;
            }
        };
    }

    @Test
    public void testGetTarget() throws Exception {
        assertThat(probeOf(TEXT).getTarget())
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