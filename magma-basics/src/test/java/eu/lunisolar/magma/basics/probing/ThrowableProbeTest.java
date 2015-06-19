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