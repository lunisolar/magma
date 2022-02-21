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

import eu.lunisolar.magma.func.supp.opt.Opt;
import eu.lunisolar.magma.func.supp.opt.OptInt;
import eu.lunisolar.magma.func.supp.opt.OptIntTrait;
import eu.lunisolar.magma.func.supp.opt.OptTrait;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;
import static eu.lunisolar.magma.func.supp.check.Checks.check;

public class P_OptInt_Test {

    public static final int         VALUE = 0;
    public static final OptIntTrait OPT   = Opt.of(1);
    public static final OptIntTrait VOID  = OptInt.empty();

    @Test
    public void test_valuePresentEx_OPT() {
        check(OPT).must$(Have::valuePresent$);
    }

    @Test
    public void test_valuePresent$_VOID() {
        attestThrownBy(() -> check(VOID).must$(Have::valuePresent$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <OptInt.empty> must have value.");

        attestThrownBy(() -> check(VOID).verbose().must$(Have::valuePresent$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?=='OptInt.empty']: Optional <OptInt.empty> must have value.");
    }

    @Test
    public void test_noValuePresentEx_OPT() {
        attestThrownBy(() -> check(OPT).must$(Have::noValuePresent$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <OptInt['1'^^int]> must NOT have value.");
    }

    @Test
    public void test_noValuePresentEx_VOID() {
        check(VOID).must$(Have::noValuePresent$);
    }

    @Test
    public void test_VoidEx_OPT() {
        attestThrownBy(() -> check(OPT).must$(Be::Void$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <OptInt['1'^^int]> must be void.");
    }

    @Test
    public void test_VoidEx_VOID() {
        check(VOID).must$(Be::Void$);
    }

    @Test
    public void test_notVoidEx_OPT() {
        check(OPT).must$(Be::notVoid$);
    }

    @Test
    public void test_notVoidEx_VOID() {
        attestThrownBy(() -> check(VOID).must$(Be::notVoid$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <OptInt.empty> must NOT be void.");
    }

}
