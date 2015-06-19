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

package eu.lunisolar.magma.basics.exceptions;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HandlingTest {

    public static final String    ORIGINAL_MESSAGE = "OriginalMessage";
    public static final Throwable RUNTIME          = new OriginalRuntimeException(ORIGINAL_MESSAGE);
    public static final Throwable CHECKED          = new OriginalException(ORIGINAL_MESSAGE);

    @Test
    public void testConstructMessage() throws Exception {
        assertThat(Handling.constructMessage(RUNTIME, "Message with %s + %d", "param1", 2)).isEqualTo("Message with param1 + 2");
        assertThat(Handling.constructMessage(RUNTIME, "Message with %s + %d", null, null)).isEqualTo("Message with null + null");
        assertThat(Handling.constructMessage(RUNTIME, "Message with %s + %d", null)).isEqualTo("Message with null + null");
        assertThat(Handling.constructMessage(RUNTIME, "Message with %s + %d", new Object[]{1, 2})).isEqualTo("Message with 1 + 2");
        assertThat(Handling.constructMessage(RUNTIME, "Message with %s + %d", (Object[]) null)).isEqualTo("Message with null + null");
        assertThat(Handling.constructMessage(RUNTIME, "Message with")).isEqualTo("Message with");

        assertThat(Handling.constructMessage(null, "Message with %s + %d", "param1", 2)).isEqualTo("Message with param1 + 2");
        assertThat(Handling.constructMessage(null, "Message with %s + %d", null, null)).isEqualTo("Message with null + null");
        assertThat(Handling.constructMessage(null, "Message with %s + %d", null)).isEqualTo("Message with null + null");
        assertThat(Handling.constructMessage(null, "Message with %s + %d", new Object[]{1, 2})).isEqualTo("Message with 1 + 2");
        assertThat(Handling.constructMessage(null, "Message with %s + %d", (Object[]) null)).isEqualTo("Message with null + null");
        assertThat(Handling.constructMessage(null, "Message with")).isEqualTo("Message with");

        assertThat(Handling.constructMessage(RUNTIME, null)).isEqualTo(ORIGINAL_MESSAGE);
        assertThat(Handling.constructMessage(null, null)).isEqualTo(Handling.UNKNOWN_PROBLEM);
    }

    @Test
    public void throwReplacement() throws Exception {
        assertThatThrownBy(() -> Handling.throwReplacement(Exception1::new, "New message %s", "with param"))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining("New message with param")
                .hasNoCause();
    }

    @Test
    public void throwWrapper1() throws Exception {
        assertThatThrownBy(() -> Handling.throwWrapper(RUNTIME, Exception1::new))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void throwWrapper2() throws Exception {
        assertThatThrownBy(() -> Handling.throwWrapper(RUNTIME, Exception1::new, "New message %s", "with param"))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining("New message with param")
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void throwReplacementIf1() throws Exception {
        assertThatThrownBy(() -> {
            Handling.throwReplacementIf(false, Exception1::new, "New message %s", "with param");
            Handling.throwReplacementIf(true, Exception2::new, "New message %s", "with param");
        })
                .isInstanceOf(Exception2.class)
                .hasMessageContaining("New message with param")
                .hasNoCause();
    }

    @Test
    public void throwWrapperIf1() throws Exception {
        assertThatThrownBy(() -> {
            Handling.throwWrapperIf(false, RUNTIME, Exception1::new);
            Handling.throwWrapperIf(true, RUNTIME, Exception2::new);
        })
                .isInstanceOf(Exception2.class)
                .hasMessageContaining(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void throwWrapperIf2() throws Exception {
        assertThatThrownBy(() -> {
            Handling.throwWrapperIf(false, RUNTIME, Exception1::new, "New message %s", "with param");
            Handling.throwWrapperIf(true, RUNTIME, Exception2::new, "New message %s", "with param");
        })
                .isInstanceOf(Exception2.class)
                .hasMessageContaining("New message with param")
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void throwReplacementIf2() throws Exception {
        assertThatThrownBy(() -> {
            Handling.throwReplacementIf(e -> false, RUNTIME, Exception1::new, "New message %s", "with param");
            Handling.throwReplacementIf(e -> true, RUNTIME, Exception2::new, "New message %s", "with param");
        })
                .isInstanceOf(Exception2.class)
                .hasMessageContaining("New message with param")
                .hasNoCause();
    }

    @Test
    public void throwWrapperIf3() throws Exception {
        assertThatThrownBy(() -> {
            Handling.throwWrapperIf(e -> false, RUNTIME, Exception1::new);
            Handling.throwWrapperIf(e -> true, RUNTIME, Exception2::new);
        })
                .isInstanceOf(Exception2.class)
                .hasMessageContaining(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void throwWrapperIf4() throws Exception {
        assertThatThrownBy(() -> {
            Handling.throwWrapperIf(e -> false, RUNTIME, Exception1::new, "New message %s", "with param");
            Handling.throwWrapperIf(e -> true, RUNTIME, Exception2::new, "New message %s", "with param");
        })
                .isInstanceOf(Exception2.class)
                .hasMessageContaining("New message with param")
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void testCreate() throws Exception {
        assertThat((Throwable) Handling.create(Exception1::new, "New message %s", "with param"))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining("New message with param")
                .hasNoCause();
    }

    @Test
    public void testWrap1() throws Exception {
        assertThat((Throwable) Handling.wrap(RUNTIME, Exception1::new))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void testWrap2() throws Exception {
        assertThat((Throwable) Handling.wrap(RUNTIME, Exception1::new, "New message %s", "with param"))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining("New message with param")
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test(expectedExceptions = OriginalException.class)
    public void testShoveIt() {
        Handling.shoveIt(CHECKED);
    }
}