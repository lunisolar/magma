/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2024 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.func.supp.check.Checks;
import org.testng.annotations.Test;

public class ChecksTest {

    @Test void attestNoExceptionTest() {
        var theCause = new RuntimeException("Unexpected");

        try {
            Checks.attestNoException(() -> {throw theCause;});
        } catch (AssertionError e) {

            if (!(e instanceof AssertionError)) {
                throw new AssertionError("'attest' failed - exception class: " + e.getClass().getName());
            }

            if (!e.getMessage().equals("Expecting no exception.")) {
                throw new AssertionError("'attest' failed: exception message: " + e.getMessage());
            }

            if (e.getCause() != theCause) {
                throw new AssertionError("'attest' failed: wrong cause.");
            }


            return;
        }

        throw new AssertionError("'attest' failed: no exception thrown.");
    }

}
