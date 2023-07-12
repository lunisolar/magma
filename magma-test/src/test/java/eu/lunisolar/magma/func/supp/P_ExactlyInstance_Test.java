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

package eu.lunisolar.magma.func.supp;

import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class P_ExactlyInstance_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    @Test void exactlyInstanceOf_P() {
        attest(P.exactlyInstanceOf(new Object(), Object.class)).mustEx(Be::TrueEx);
        attest(P.exactlyInstanceOf(new Object(), Integer.class)).mustEx(Be::FalseEx);
        attest(P.exactlyInstanceOf(new Integer(1), Object.class)).mustEx(Be::FalseEx);
        attest(P.exactlyInstanceOf(null, Object.class)).mustEx(Be::FalseEx);
    }

    @Test void exactlyInstanceOf_Be() {
        attest(Be.exactlyInstanceOf(new Object(), Object.class)).mustEx(Be::TrueEx);
        attest(Be.exactlyInstanceOf(new Object(), Integer.class)).mustEx(Be::FalseEx);
        attest(Be.exactlyInstanceOf(new Integer(1), Object.class)).mustEx(Be::FalseEx);
    }

    @Test void exactlyInstanceOfEx() {
        attest(P.exactlyInstanceOfEx(new Integer(1), Integer.class)).mustEx(Be::NullEx);

        attest(P.exactlyInstanceOfEx(new Integer(1), Object.class)).mustEx(Be::equalEx, "Object <1> of class <class java.lang.Integer> must be exactly instance of <class java.lang.Object>.");
    }

}
