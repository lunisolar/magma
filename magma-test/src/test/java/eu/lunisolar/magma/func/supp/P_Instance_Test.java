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

package eu.lunisolar.magma.func.supp;

import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

public class P_Instance_Test {

    public static final int I1 = 1;
    public static final int I2 = 2;

    @Test void instanceOf_P() {
        attest(P.instanceOf(new Object(), Object.class)).must$(Be::True$);
        attest(P.instanceOf(new Object(), Integer.class)).must$(Be::False$);
        attest(P.instanceOf(new Integer(1), Object.class)).must$(Be::True$);

        attest(P.notInstanceOf(new Object(), Object.class)).must$(Be::False$);
        attest(P.notInstanceOf(new Object(), Integer.class)).must$(Be::True$);
        attest(P.notInstanceOf(new Integer(1), Object.class)).must$(Be::False$);
    }

    @Test void instanceOf_Be() {
        attest(Be.instanceOf(new Object(), Object.class)).must$(Be::True$);
        attest(Be.instanceOf(new Object(), Integer.class)).must$(Be::False$);
        attest(Be.instanceOf(new Integer(1), Object.class)).must$(Be::True$);

        attest(Be.notInstanceOf(new Object(), Object.class)).must$(Be::False$);
        attest(Be.notInstanceOf(new Object(), Integer.class)).must$(Be::True$);
        attest(Be.notInstanceOf(new Integer(1), Object.class)).must$(Be::False$);
    }

    @Test void instanceOf$() {
        attest(P.instanceOf$(new Integer(1), Integer.class)).must$(Be::Null$);
        attest(P.notInstanceOf$(new Integer(1), Integer.class)).must$(Be::equal$, "Object <1> of class <class java.lang.Integer> must NOT be instance of <class java.lang.Integer>.");

        attest(P.instanceOf$(new Integer(1), Object.class)).must$(Be::Null$);
        attest(P.notInstanceOf$(new Integer(1), Object.class)).must$(Be::equal$, "Object <1> of class <class java.lang.Integer> must NOT be instance of <class java.lang.Object>.");
    }

}
