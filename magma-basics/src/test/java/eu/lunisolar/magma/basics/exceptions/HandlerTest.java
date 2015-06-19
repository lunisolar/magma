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

public class HandlerTest {

    public static final String    ORIGINAL_MESSAGE = "OriginalMessage";
    public static final Throwable RUNTIME          = new OriginalRuntimeException(ORIGINAL_MESSAGE);
    public static final Throwable CHECKED          = new OriginalException(ORIGINAL_MESSAGE);

    @Test
    public void testHandler() throws Exception {
        assertThat(Handler.handler(RUNTIME))
                .isExactlyInstanceOf(Handler.The.class);
    }

    @Test
    public void testGetTarget() throws Exception {
        assertThat(Handler.handler(RUNTIME).getTarget())
                .isSameAs(RUNTIME);
    }

    @Test
    public void testHandleInstructions() throws Exception {

        Handler.The<Throwable, RuntimeException> handle = Handler.handleInstructions(RUNTIME, h -> {
        });

        assertThat(handle)
                .isExactlyInstanceOf(Handler.The.class);

    }

    @Test
    public void testHandleOrFail() throws Exception {

        assertThatThrownBy(() -> Handler.handleOrFail(RUNTIME, h -> {
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void testHandleOrNest_runtime() throws Exception {
        assertThatThrownBy(() -> Handler.handleOrNest(RUNTIME, h -> {
        }))
                .isInstanceOf(OriginalRuntimeException.class)
                .hasMessage(ORIGINAL_MESSAGE);
    }

    @Test
    public void testHandleOrNest_checked() throws Exception {
        assertThatThrownBy(() -> Handler.handleOrNest(CHECKED, h -> {
        }))
                .isInstanceOf(NestedException.class)
                .hasMessage(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testThrowIf_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.throwIf(OriginalException.class);
        }))
                .isInstanceOf(OriginalException.class)
                .hasMessage(ORIGINAL_MESSAGE);
    }

    @Test
    public void testThrowIf_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.throwIf(OriginalRuntimeException.class);
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    // <editor-fold desc="if">

    @Test
    public void testReplaceIf_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.replaceIf(x -> true, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params")
                .hasNoCause();
    }

    @Test
    public void testReplaceIf_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.replaceIf(x -> false, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrapIf1_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrapIf(x -> true, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrapIf1_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrapIf(x -> false, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrapIf2_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrapIf(x -> true, Exception1::new);
        }))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrapIf2_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrapIf(x -> false, Exception1::new);
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    // </editor-fold>

    // <editor-fold desc="when">

    @Test
    public void testReplaceWhen_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.replaceWhen(p -> true, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params")
                .hasNoCause();
    }

    @Test
    public void testReplaceWhen_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.replaceWhen(p -> false, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrapWhen1_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrapWhen(p -> true, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrapWhen1_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrapWhen(p -> false, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrapWhen2_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrapWhen(p -> true, Exception1::new);
        }))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrapWhen2_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrapWhen(p -> false, Exception1::new);
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    // </editor-fold>

    @Test
    public void testNestIfChecked_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.nestIfChecked();
        }))
                .isInstanceOf(NestedException.class)
                .hasMessage(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testNestIfChecked_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(RUNTIME, h -> {
            h.nestIfChecked();
        }))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalRuntimeException.class);
    }

    @Test
    public void testThrowReplacement() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.throwReplacement(Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params")
                .hasNoCause();
    }

    @Test
    public void testThrowWrapper1() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.throwWrapper(Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testThrowWrapper2() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.throwWrapper(Exception1::new);
        }))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testThrowFailure() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, Handler::throwFailure))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testThrowAsIs() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, Handler::throwAsIs))
                .isInstanceOf(OriginalException.class)
                .hasMessage(ORIGINAL_MESSAGE)
                .hasNoCause();
    }

    @Test
    public void testHandleRest() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, Handler::handleRest))
                .isInstanceOf(NestedException.class)
                .hasMessage(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalException.class);

        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(RUNTIME, Handler::handleRest))
                .isInstanceOf(OriginalRuntimeException.class)
                .hasMessage(ORIGINAL_MESSAGE)
                .hasNoCause();
    }

}