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

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ThrowableProbeTest {

    @Test
    public void testIsRuntime() throws Exception {
        assertThat(ThrowableProbe.of(new RuntimeException()).isRuntime()).isTrue();
        assertThat(ThrowableProbe.of(new IllegalArgumentException()).isRuntime()).isTrue();
        assertThat(ThrowableProbe.of(new Exception()).isRuntime()).isFalse();
        assertThat(ThrowableProbe.of(new IOException()).isRuntime()).isFalse();
    }

    @Test
    public void testIsNotRuntime() throws Exception {
        assertThat(ThrowableProbe.of(new RuntimeException()).isNotRuntime()).isFalse();
        assertThat(ThrowableProbe.of(new IllegalArgumentException()).isNotRuntime()).isFalse();
        assertThat(ThrowableProbe.of(new Exception()).isNotRuntime()).isTrue();
        assertThat(ThrowableProbe.of(new IOException()).isNotRuntime()).isTrue();
    }

    @Test
    public void testHasCause() throws Exception {
        assertThat(ThrowableProbe.of(new RuntimeException()).hasCause()).isFalse();
        assertThat(ThrowableProbe.of(new RuntimeException(new RuntimeException())).hasCause()).isTrue();
    }

    @Test
    public void testHasNoCause() throws Exception {
        assertThat(ThrowableProbe.of(new RuntimeException()).hasNoCause()).isTrue();
        assertThat(ThrowableProbe.of(new RuntimeException(new RuntimeException())).hasNoCause()).isFalse();
    }

    @Test
    public void testHasMessageStartingWith() throws Exception {
        assertThat(ThrowableProbe.of(new RuntimeException()).hasMessageStartingWith("123")).isFalse();
        assertThat(ThrowableProbe.of(new RuntimeException("123")).hasMessageStartingWith("123")).isTrue();
        assertThat(ThrowableProbe.of(new RuntimeException("1234")).hasMessageStartingWith("123")).isTrue();
        assertThat(ThrowableProbe.of(new RuntimeException("_1234")).hasMessageStartingWith("123")).isFalse();
    }

    @Test
    public void testHasMessageContaining() throws Exception {
        assertThat(ThrowableProbe.of(new RuntimeException()).hasMessageContaining("123")).isFalse();
        assertThat(ThrowableProbe.of(new RuntimeException("123")).hasMessageContaining("123")).isTrue();
        assertThat(ThrowableProbe.of(new RuntimeException("1234")).hasMessageContaining("123")).isTrue();
        assertThat(ThrowableProbe.of(new RuntimeException("_1234")).hasMessageContaining("123")).isTrue();
    }

    @Test
    public void testHasMessageEndingWith() throws Exception {
        assertThat(ThrowableProbe.of(new RuntimeException()).hasMessageEndingWith("123")).isFalse();
        assertThat(ThrowableProbe.of(new RuntimeException("123")).hasMessageEndingWith("123")).isTrue();
        assertThat(ThrowableProbe.of(new RuntimeException("1234")).hasMessageEndingWith("234")).isTrue();
        assertThat(ThrowableProbe.of(new RuntimeException("_1234")).hasMessageEndingWith("234")).isTrue();
    }

    @Test
    public void testHasCauseThat() throws Exception {
        assertThat(ThrowableProbe.of(new RuntimeException(new RuntimeException())).hasCauseThat(ThrowableProbe::isRuntime)).isTrue();
        assertThat(ThrowableProbe.of(new RuntimeException(new RuntimeException())).hasCauseThat(ThrowableProbe::isNotRuntime)).isFalse();
    }

    @Test
    public void testHasCauseInstanceOf() throws Exception {
        RuntimeException exception = new RuntimeException(new RuntimeException(new IllegalArgumentException()));

        assertThat(ThrowableProbe.of(exception).hasRootCauseThat(t -> t.isInstanceOf(IllegalArgumentException.class))).isTrue();
        assertThat(ThrowableProbe.of(exception).hasRootCauseThat(t -> t.isInstanceOf(IOException.class))).isFalse();
    }
}