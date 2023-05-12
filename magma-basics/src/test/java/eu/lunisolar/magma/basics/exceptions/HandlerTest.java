/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertSame;

public class HandlerTest {

    public static final String    ORIGINAL_MESSAGE = "OriginalMessage";
    public static final String    WRAP_ME          = "WRAP_ME";
    public static final Throwable RUNTIME          = new OriginalRuntimeException(ORIGINAL_MESSAGE);
    public static final Throwable CHECKED          = new OriginalException(ORIGINAL_MESSAGE);

    @Test
    public void testHandler() throws Exception {
        assertSame(Handler.handler(RUNTIME).getClass(), Handler.class);
    }

    @Test
    public void testGetTarget() throws Exception {
        assertSame(Handler.handler(RUNTIME).target(), RUNTIME);
    }

    @Test
    public void testHandleOrFail() throws Exception {

        try {
            Handler.handleOrFail(RUNTIME, h -> {
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
        }
    }

    @Test
    public void testHandleOrNest_runtime() throws Exception {
        try {
            Handler.handleOrNest(RUNTIME, h -> {
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), OriginalRuntimeException.class);
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
            Assert.assertNull(e.getCause());
        }
    }

    @Test
    public void testHandleOrNest_checked() throws Exception {
        try {
            Handler.handleOrNest(CHECKED, h -> {
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), NestedException.class);
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testThrowIf_conditionMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.rethrowIf(OriginalException.class);
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), OriginalException.class);
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
            Assert.assertNull(e.getCause());
        }
    }

    @Test
    public void testThrowIf_conditionNotMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.rethrowIf(OriginalRuntimeException.class);
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    // <editor-fold desc="if">

    @Test
    public void testReplaceIf_conditionMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.replaceIf(x -> true, Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertEquals(e.getMessage(), "New message with params");
            Assert.assertNull(e.getCause());
        }
    }

    @Test
    public void testReplaceIf_conditionNotMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.replaceIf(x -> false, Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testWrapIf1_conditionMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.wrapIf(x -> true, Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertEquals(e.getMessage(), "New message with params");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testWrapIf1_conditionNotMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.wrapIf(x -> false, Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testWrapIf2_conditionMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.wrapIf(x -> true, Exception1::new);
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertTrue(e.getMessage().contains(ORIGINAL_MESSAGE));
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testWrapIf2_conditionNotMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.wrapIf(x -> false, Exception1::new);
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testCombineIf1_conditionMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.combineIf(x -> true, Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertEquals(e.getMessage(), "New message with params OriginalMessage");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testCombineIf1_conditionNotMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.combineIf(x -> false, Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    // </editor-fold>

    @Test
    public void testNestIfChecked_conditionMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.nestIfChecked();
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), NestedException.class);
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testNestIfChecked_conditionNotMeet() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(RUNTIME, h -> {
                h.nestIfChecked();
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
        }
    }

    @Test
    public void testReplace() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.replace(Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertEquals(e.getMessage(), "New message with params");
            Assert.assertNull(e.getCause());
        }
    }

    @Test
    public void testWrap1() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.wrap(Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertEquals(e.getMessage(), "New message with params");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testWrap2() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.wrap(Exception1::new);
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertTrue(e.getMessage().contains(ORIGINAL_MESSAGE));
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testCombine1() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, h -> {
                h.combine(Exception1::new, "New message %s", "with params");
            });
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertEquals(e.getMessage(), "New message with params OriginalMessage");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testThrowFailure() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, Handler::throwFailure);
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }
    }

    @Test
    public void testThrowAsIs() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, Handler::throwAsIs);
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), OriginalException.class);
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
            Assert.assertNull(e.getCause());
        }
    }

    @Test
    public void testHandleRest() throws Exception {
        try {
            Handler.<Throwable, Throwable>handleOrFail(CHECKED, Handler::handleRest);
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), NestedException.class);
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
            Assert.assertSame(e.getCause().getClass(), OriginalException.class);
        }

        try {
            Handler.<Throwable, Throwable>handleOrFail(RUNTIME, Handler::handleRest);
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), OriginalRuntimeException.class);
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
            Assert.assertNull(e.getCause());
        }
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
        try {
            proxy.returnOrThrow(0, new RuntimeException(WRAP_ME));
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), IllegalStateException.class);
            Assert.assertEquals(e.getMessage(), "java.lang.RuntimeException: " + WRAP_ME);
            Assert.assertSame(e.getCause().getClass(), RuntimeException.class);
            Assert.assertEquals(e.getCause().getMessage(), WRAP_ME);
        }

        // WHEN/THEN
        try {
            proxy.returnOrThrow(0, new IllegalArgumentException(ORIGINAL_MESSAGE));
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), IllegalArgumentException.class);
            Assert.assertEquals(e.getMessage(), ORIGINAL_MESSAGE);
            Assert.assertNull(e.getCause());
        }

        // WHEN/THEN
        try {
            proxy.returnOrThrow(0, new RuntimeException(ORIGINAL_MESSAGE));
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), ExceptionNotHandled.class);
            Assert.assertEquals(e.getMessage(), "Exception has not been handled.");
            Assert.assertSame(e.getCause().getClass(), RuntimeException.class);
            Assert.assertEquals(e.getCause().getMessage(), ORIGINAL_MESSAGE);
        }
    }

}