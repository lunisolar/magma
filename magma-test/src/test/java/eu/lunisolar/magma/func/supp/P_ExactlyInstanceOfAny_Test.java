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

public class P_ExactlyInstanceOfAny_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    private static class HasNoInstances {
        public HasNoInstances() {
            throw new Error();
        }
    }

    @Test void exactlyInstanceOfAny_P() {
        attest(P.exactlyInstanceOfAny(new Object(), Object.class)).mustEx(Be::TrueEx);
        attest(P.exactlyInstanceOfAny(new Object(), Object.class, HasNoInstances.class)).mustEx(Be::TrueEx);
        attest(P.exactlyInstanceOfAny(new Object(), HasNoInstances.class, Object.class)).mustEx(Be::TrueEx);

        attest(P.exactlyInstanceOfAny(new Object(), Integer.class)).mustEx(Be::FalseEx);
        attest(P.exactlyInstanceOfAny(new Object(), Integer.class, HasNoInstances.class)).mustEx(Be::FalseEx);
        attest(P.exactlyInstanceOfAny(new Object(), HasNoInstances.class, Integer.class)).mustEx(Be::FalseEx);

        attest(P.exactlyInstanceOfAny(new Integer(1), Object.class)).mustEx(Be::FalseEx);
        attest(P.exactlyInstanceOfAny(new Integer(1), Object.class, HasNoInstances.class)).mustEx(Be::FalseEx);
        attest(P.exactlyInstanceOfAny(new Integer(1), HasNoInstances.class, Object.class)).mustEx(Be::FalseEx);

        attest(P.exactlyInstanceOfAny(null, Object.class)).mustEx(Be::FalseEx);
        attest(P.exactlyInstanceOfAny(null, Object.class, HasNoInstances.class)).mustEx(Be::FalseEx);
        attest(P.exactlyInstanceOfAny(null, HasNoInstances.class, Object.class)).mustEx(Be::FalseEx);
    }

    @Test void notExactlyInstanceOfAny_P() {
        attest(P.notExactlyInstanceOfAny(new Object(), Object.class)).mustEx(Be::FalseEx);
        attest(P.notExactlyInstanceOfAny(new Object(), Object.class, HasNoInstances.class)).mustEx(Be::FalseEx);
        attest(P.notExactlyInstanceOfAny(new Object(), HasNoInstances.class, Object.class)).mustEx(Be::FalseEx);

        attest(P.notExactlyInstanceOfAny(new Object(), Integer.class)).mustEx(Be::TrueEx);
        attest(P.notExactlyInstanceOfAny(new Object(), Integer.class, HasNoInstances.class)).mustEx(Be::TrueEx);
        attest(P.notExactlyInstanceOfAny(new Object(), HasNoInstances.class, Integer.class)).mustEx(Be::TrueEx);

        attest(P.notExactlyInstanceOfAny(new Integer(1), Object.class)).mustEx(Be::TrueEx);
        attest(P.notExactlyInstanceOfAny(new Integer(1), Object.class, HasNoInstances.class)).mustEx(Be::TrueEx);
        attest(P.notExactlyInstanceOfAny(new Integer(1), HasNoInstances.class, Object.class)).mustEx(Be::TrueEx);

        attest(P.notExactlyInstanceOfAny(null, Object.class)).mustEx(Be::TrueEx);
        attest(P.notExactlyInstanceOfAny(null, Object.class, HasNoInstances.class)).mustEx(Be::TrueEx);
        attest(P.notExactlyInstanceOfAny(null, HasNoInstances.class, Object.class)).mustEx(Be::TrueEx);
    }

    @Test void exactlyInstanceOfAny_Be() {
        attest(Be.exactlyInstanceOfAny(new Object(), Object.class)).mustEx(Be::TrueEx);
        attest(Be.exactlyInstanceOfAny(new Object(), Object.class, HasNoInstances.class)).mustEx(Be::TrueEx);
        attest(Be.exactlyInstanceOfAny(new Object(), HasNoInstances.class, Object.class)).mustEx(Be::TrueEx);

        attest(Be.exactlyInstanceOfAny(new Object(), Integer.class)).mustEx(Be::FalseEx);
        attest(Be.exactlyInstanceOfAny(new Object(), Integer.class, HasNoInstances.class)).mustEx(Be::FalseEx);
        attest(Be.exactlyInstanceOfAny(new Object(), HasNoInstances.class, Integer.class)).mustEx(Be::FalseEx);

        attest(Be.exactlyInstanceOfAny(new Integer(1), Object.class)).mustEx(Be::FalseEx);
        attest(Be.exactlyInstanceOfAny(new Integer(1), Object.class, HasNoInstances.class)).mustEx(Be::FalseEx);
        attest(Be.exactlyInstanceOfAny(new Integer(1), HasNoInstances.class, Object.class)).mustEx(Be::FalseEx);
    }

    @Test void exactlyInstanceOfAnyEx() {
        attest(P.exactlyInstanceOfAnyEx(new Integer(1), Integer.class, HasNoInstances.class)).mustEx(Be::NullEx);

        attest(P.exactlyInstanceOfAnyEx(new Integer(1), Object.class, HasNoInstances.class)).mustEx(Be::equalEx, "Object <1> of class <class java.lang.Integer> must be exactly instance of any <[class java.lang.Object, class eu.lunisolar.magma.func.supp.P_ExactlyInstanceOfAny_Test$HasNoInstances]>.");
    }

}
