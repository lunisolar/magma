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

import javax.net.ssl.SNIHostName;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class P_AnyInstance_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    @Test void instanceOf_P() {
        attest(P.instanceOfAny(new Object(), Object.class)).mustEx(Be::TrueEx);
        attest(P.instanceOfAny(new Object(), Integer.class)).mustEx(Be::FalseEx);
        attest(P.instanceOfAny(new Integer(1), Object.class)).mustEx(Be::TrueEx);
        attest(P.instanceOfAny(null, Object.class)).mustEx(Be::FalseEx);

        attest(P.notInstanceOfAny(new Object(), Object.class)).mustEx(Be::FalseEx);
        attest(P.notInstanceOfAny(new Object(), Integer.class)).mustEx(Be::TrueEx);
        attest(P.notInstanceOfAny(new Integer(1), Object.class)).mustEx(Be::FalseEx);
        attest(P.notInstanceOfAny(null, Object.class)).mustEx(Be::TrueEx);

        //

        attest(P.instanceOfAny(new Integer(1), Short.class, Number.class)).mustEx(Be::TrueEx);
        attest(P.instanceOfAny(new Integer(1), Number.class, Short.class)).mustEx(Be::TrueEx);

        attest(P.instanceOfAny(new Object(), Short.class, Number.class)).mustEx(Be::FalseEx);
        attest(P.instanceOfAny(new Object(), Number.class, Short.class)).mustEx(Be::FalseEx);
    }

    @Test void instanceOf_Be() {
        attest(Be.instanceOfAny(new Object(), Object.class)).mustEx(Be::TrueEx);
        attest(Be.instanceOfAny(new Object(), Integer.class)).mustEx(Be::FalseEx);
        attest(Be.instanceOfAny(new Integer(1), Object.class)).mustEx(Be::TrueEx);

        attest(Be.notInstanceOfAny(new Object(), Object.class)).mustEx(Be::FalseEx);
        attest(Be.notInstanceOfAny(new Object(), Integer.class)).mustEx(Be::TrueEx);
        attest(Be.notInstanceOfAny(new Integer(1), Object.class)).mustEx(Be::FalseEx);
    }

    @Test void instanceOfEx() {
        attest(P.instanceOfAnyEx(new Integer(1), Integer.class)).mustEx(Be::NullEx);
        attest(P.notInstanceOfAnyEx(new Integer(1), Integer.class)).mustEx(Be::equalEx, "Object <1> of class <class java.lang.Integer> must NOT be instance of any <[class java.lang.Integer]>.");

        attest(P.instanceOfAnyEx(new Integer(1), Object.class)).mustEx(Be::NullEx);
        attest(P.notInstanceOfAnyEx(new Integer(1), Object.class)).mustEx(Be::equalEx, "Object <1> of class <class java.lang.Integer> must NOT be instance of any <[class java.lang.Object]>.");

        attest(P.instanceOfAnyEx(null, Integer.class)).mustBeEqual("Object <null> of class <null> must be instance of any <[class java.lang.Integer]>.");
        attest(P.notInstanceOfAnyEx(null, Integer.class)).mustBeNull();

    }

}
