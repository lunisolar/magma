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

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HandlingTest {

    public static final String    ORIGINAL_MESSAGE = "OriginalMessage";
    public static final Throwable RUNTIME          = new OriginalRuntimeException(ORIGINAL_MESSAGE);
    public static final Throwable CHECKED          = new OriginalException(ORIGINAL_MESSAGE);

    @Test
    public void testConstructMessage() throws Exception {
        assertEquals(Handling.constructMessage(RUNTIME, "Message with %s + %d", "param1", 2), "Message with param1 + 2");
        assertEquals(Handling.constructMessage(RUNTIME, "Message with %s + %d", null, null), "Message with null + null");
        assertEquals(Handling.constructMessage(RUNTIME, "Message with %s + %d", null), "Message with null + null");
        assertEquals(Handling.constructMessage(RUNTIME, "Message with %s + %d", new Object[]{1, 2}), "Message with 1 + 2");
        assertEquals(Handling.constructMessage(RUNTIME, "Message with %s + %d", (Object[]) null), "Message with null + null");
        assertEquals(Handling.constructMessage(RUNTIME, "Message with"), "Message with");

        assertEquals(Handling.constructMessage(null, "Message with %s + %d", "param1", 2), "Message with param1 + 2");
        assertEquals(Handling.constructMessage(null, "Message with %s + %d", null, null), "Message with null + null");
        assertEquals(Handling.constructMessage(null, "Message with %s + %d", null), "Message with null + null");
        assertEquals(Handling.constructMessage(null, "Message with %s + %d", new Object[]{1, 2}), "Message with 1 + 2");
        assertEquals(Handling.constructMessage(null, "Message with %s + %d", (Object[]) null), "Message with null + null");
        assertEquals(Handling.constructMessage(null, "Message with"), "Message with");

        assertEquals(Handling.constructMessage(RUNTIME, null), ORIGINAL_MESSAGE);
        assertEquals(Handling.constructMessage(null, null), Handling.UNKNOWN_ISSUE);
    }

    @Test
    public void testConstructMessageCombine() throws Exception {
        assertEquals(Handling.constructMessage(true, RUNTIME, "Message with %s + %d"), "Message with %s + %d OriginalMessage");
        assertEquals(Handling.constructMessage(true, RUNTIME, "Message with %s + %d", 1, 2), "Message with 1 + 2 OriginalMessage");
        assertEquals(Handling.constructMessage(false, RUNTIME, "Message with %s + %d"), "Message with %s + %d");
        assertEquals(Handling.constructMessage(false, RUNTIME, "Message with %s + %d", 1, 2), "Message with 1 + 2");

        assertEquals(Handling.constructMessage(true, null, "Message with %s + %d"), "Message with %s + %d");
        assertEquals(Handling.constructMessage(true, null, "Message with %s + %d", 1, 2), "Message with 1 + 2");
        assertEquals(Handling.constructMessage(false, null, "Message with %s + %d"), "Message with %s + %d");
        assertEquals(Handling.constructMessage(false, null, "Message with %s + %d", 1, 2), "Message with 1 + 2");

        assertEquals(Handling.constructMessage(true, RUNTIME, null), "OriginalMessage");
        assertEquals(Handling.constructMessage(true, RUNTIME, null, 1, 2), "OriginalMessage");
        assertEquals(Handling.constructMessage(false, RUNTIME, null), "OriginalMessage");
        assertEquals(Handling.constructMessage(false, RUNTIME, null, 1, 2), "OriginalMessage");

        assertEquals(Handling.constructMessage(true, null, null), Handling.UNKNOWN_ISSUE);
        assertEquals(Handling.constructMessage(true, null, null, null, null), Handling.UNKNOWN_ISSUE);
        assertEquals(Handling.constructMessage(false, null, null), Handling.UNKNOWN_ISSUE);
        assertEquals(Handling.constructMessage(false, null, null, null, null), Handling.UNKNOWN_ISSUE);
    }

    @Test
    public void testHandleInstructions() throws Exception {

        Handler<Throwable> handle = Handling.handleInstructions(RUNTIME, h -> {
        });

        assertTrue(handle.getClass() == Handler.class);
    }

    @Test
    public void throwReplacement() throws Exception {
        try {
            Handling.throwReplacement(Exception1::new, "New message %s", "with param");
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertTrue(e.getMessage().contains("New message with param"));
            Assert.assertNull(e.getCause());
        }
    }

    @Test
    public void throwWrapper1() throws Exception {
        try {
            Handling.throwWrapper(RUNTIME, Exception1::new);
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertTrue(e.getMessage().contains(ORIGINAL_MESSAGE));
            Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
        }
    }

    @Test
    public void throwWrapper2() throws Exception {
        try {
            Handling.throwWrapper(RUNTIME, Exception1::new, "New message %s", "with param");
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception1.class);
            Assert.assertTrue(e.getMessage().contains("New message with param"));
            Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
        }
    }

    @Test
    public void throwReplacementIf1() throws Exception {
        try {
            Handling.throwReplacementIf(false, Exception1::new, "New message %s", "with param");
            Handling.throwReplacementIf(true, Exception2::new, "New message %s", "with param");
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception2.class);
            Assert.assertTrue(e.getMessage().contains("New message with param"));
            Assert.assertNull(e.getCause());
        }
    }

    @Test
    public void throwWrapperIf1() throws Exception {
        try {
            Handling.throwWrapperIf(false, RUNTIME, Exception1::new);
            Handling.throwWrapperIf(true, RUNTIME, Exception2::new);
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception2.class);
            Assert.assertTrue(e.getMessage().contains(ORIGINAL_MESSAGE));
            Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
        }
    }

    @Test
    public void throwWrapperIf2() throws Exception {
        try {
            Handling.throwWrapperIf(false, RUNTIME, Exception1::new, "New message %s", "with param");
            Handling.throwWrapperIf(true, RUNTIME, Exception2::new, "New message %s", "with param");
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception2.class);
            Assert.assertTrue(e.getMessage().contains("New message with param"));
            Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
        }
    }

    @Test
    public void throwReplacementIf2() throws Exception {
        try {
            Handling.throwReplacementIf(e -> false, RUNTIME, Exception1::new, "New message %s", "with param");
            Handling.throwReplacementIf(e -> true, RUNTIME, Exception2::new, "New message %s", "with param");
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception2.class);
            Assert.assertTrue(e.getMessage().contains("New message with param"));
            Assert.assertNull(e.getCause());
        }
    }

    @Test
    public void throwWrapperIf3() throws Exception {
        try {
            Handling.throwWrapperIf(e -> false, RUNTIME, Exception1::new);
            Handling.throwWrapperIf(e -> true, RUNTIME, Exception2::new);
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception2.class);
            Assert.assertTrue(e.getMessage().contains(ORIGINAL_MESSAGE));
            Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
        }
    }

    @Test
    public void throwWrapperIf4() throws Exception {
        try {
            Handling.throwWrapperIf(e -> false, RUNTIME, Exception1::new, "New message %s", "with param");
            Handling.throwWrapperIf(e -> true, RUNTIME, Exception2::new, "New message %s", "with param");
            Assert.fail("No exception thrown.");
        } catch (Throwable e) {
            Assert.assertSame(e.getClass(), Exception2.class);
            Assert.assertTrue(e.getMessage().contains("New message with param"));
            Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
        }
    }

    @Test
    public void testCreate() throws Exception {
        var e = Handling.create(Exception1::new, "New message %s", "with param");
        Assert.assertSame(e.getClass(), Exception1.class);
        Assert.assertTrue(e.getMessage().contains("New message with param"));
        Assert.assertNull(e.getCause());
    }

    @Test
    public void testWrap1() throws Exception {
        var e = Handling.wrap(RUNTIME, Exception1::new);
        Assert.assertSame(e.getClass(), Exception1.class);
        Assert.assertTrue(e.getMessage().contains(ORIGINAL_MESSAGE));
        Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
    }

    @Test
    public void testWrap2() throws Exception {
        var e = Handling.wrap(RUNTIME, Exception1::new, "New message %s", "with param");
        Assert.assertSame(e.getClass(), Exception1.class);
        Assert.assertTrue(e.getMessage().contains("New message with param"));
        Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
    }

    @Test
    public void testWrap3_messageToPropagate() throws Exception {
        var e = Handling.combine(RUNTIME, Exception1::new, "New message %s", "with param");
        Assert.assertSame(e.getClass(), Exception1.class);
        Assert.assertTrue(e.getMessage().contains("New message with param"));
        Assert.assertTrue(e.getMessage().contains(" " + ORIGINAL_MESSAGE));
        Assert.assertSame(e.getCause().getClass(), OriginalRuntimeException.class);
    }

    @Test
    public void testWrap3_noMessageToPropagate() throws Exception {
        var e = Handling.combine(new RuntimeException(), Exception1::new, "New message %s", "with param");
        Assert.assertSame(e.getClass(), Exception1.class);
        Assert.assertTrue(e.getMessage().contains("New message with param"));
        Assert.assertFalse(e.getMessage().contains(ORIGINAL_MESSAGE));
        Assert.assertSame(e.getCause().getClass(), RuntimeException.class);
    }

    @Test(expectedExceptions = OriginalException.class)
    public void testShoveIt() {
        Handling.shoveIt(CHECKED);
    }
}