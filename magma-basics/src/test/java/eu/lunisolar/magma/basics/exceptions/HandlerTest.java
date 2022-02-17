/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.basics.exceptions;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HandlerTest {

    public static final String    ORIGINAL_MESSAGE = "OriginalMessage";
    public static final String    WRAP_ME          = "WRAP_ME";
    public static final Throwable RUNTIME          = new OriginalRuntimeException(ORIGINAL_MESSAGE);
    public static final Throwable CHECKED          = new OriginalException(ORIGINAL_MESSAGE);

    @Test
    public void testHandler() throws Exception {
        assertThat(Handler.handler(RUNTIME))
                .isExactlyInstanceOf(Handler.class);
    }

    @Test
    public void testGetTarget() throws Exception {
        assertThat(Handler.handler(RUNTIME).target())
                .isSameAs(RUNTIME);
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
            h.rethrowIf(OriginalException.class);
        }))
                .isInstanceOf(OriginalException.class)
                .hasMessage(ORIGINAL_MESSAGE);
    }

    @Test
    public void testThrowIf_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.rethrowIf(OriginalRuntimeException.class);
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

    @Test
    public void testCombineIf1_conditionMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.combineIf(x -> true, Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params OriginalMessage")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testCombineIf1_conditionNotMeet() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.combineIf(x -> false, Exception1::new, "New message %s", "with params");
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
    public void testReplace() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.replace(Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params")
                .hasNoCause();
    }

    @Test
    public void testWrap1() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrap(Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params")
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testWrap2() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.wrap(Exception1::new);
        }))
                .isInstanceOf(Exception1.class)
                .hasMessageContaining(ORIGINAL_MESSAGE)
                .hasCauseExactlyInstanceOf(OriginalException.class);
    }

    @Test
    public void testCombine1() throws Exception {
        assertThatThrownBy(() -> Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
            h.combine(Exception1::new, "New message %s", "with params");
        }))
                .isInstanceOf(Exception1.class)
                .hasMessage("New message with params OriginalMessage")
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

    public interface Interface {
        default int returnOrThrow(int arg, Throwable e) {
            if (e != null) {
                throw Handling.throwIt(e);
            }

            return arg;
        }
    }

    @Test
    public void proxy() throws Exception {

        // GIVEN
        HandlingInstructions<Throwable, RuntimeException> handlingInstructions = h -> h
                .rethrowIf(IllegalArgumentException.class)
                .wrapIf(e -> WRAP_ME.equals(e.getMessage()), IllegalStateException::new);

        Interface proxy = Handling.proxy(this.getClass().getClassLoader(), new Interface() {}, handlingInstructions, Interface.class);

        // WHEN/THEN
        assertThatThrownBy(() -> proxy.returnOrThrow(0, new RuntimeException(WRAP_ME)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("java.lang.RuntimeException: " + WRAP_ME)
                .hasCauseExactlyInstanceOf(RuntimeException.class)
                .hasRootCauseMessage(WRAP_ME);

        // WHEN/THEN
        assertThatThrownBy(() -> proxy.returnOrThrow(0, new IllegalArgumentException(ORIGINAL_MESSAGE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ORIGINAL_MESSAGE)
                .hasNoCause();

        // WHEN/THEN
        assertThatThrownBy(() -> proxy.returnOrThrow(0, new RuntimeException(ORIGINAL_MESSAGE)))
                .isInstanceOf(ExceptionNotHandled.class)
                .hasMessage("Exception has not been handled.")
                .hasCauseExactlyInstanceOf(RuntimeException.class)
                .hasRootCauseMessage(ORIGINAL_MESSAGE);

    }

}