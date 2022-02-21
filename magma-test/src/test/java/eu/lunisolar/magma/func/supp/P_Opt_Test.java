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
import eu.lunisolar.magma.func.supp.opt.OptTrait;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;
import static eu.lunisolar.magma.func.supp.check.Checks.check;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class P_Opt_Test {

    public static final Object   VALUE       = 0;
    public static final Object   OTHER_VALUE = 1;
    public static final OptTrait OPT         = Opt.of(VALUE);
    public static final OptTrait VOID        = Opt.empty();
    public static final OptTrait NULL        = null;

    public static final String VALUE_STR       = "'0'^^Integer";
    public static final String OTHER_VALUE_STR = "'1'^^Integer";

    @Test
    public void test_valuePresent$_OPT() {
        check(OPT).must$(Have::valuePresent$);
    }

    @Test
    public void test_valuePresent$_VOID() {
        attestThrownBy(() -> check(VOID).must$(Have::valuePresent$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <Opt.empty> must have value.");

        attestThrownBy(() -> check(VOID).verbose().must$(Have::valuePresent$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?=='Opt.empty']: Optional <Opt.empty> must have value.");
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = ".*Argument 'opt' must not be null.*")
    public void test_valuePresent$_NULL() {
        check(NULL).must$(Have::valuePresent$);
    }

    @Test
    public void test_sameValue$_OPT() {
        check(OPT).must$(Have::sameValue$, VALUE);
    }

    @Test
    public void test_sameValue$_VOID() {
        attestThrownBy(() -> check(VOID).must$(Have::sameValue$, VALUE))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <Opt.empty> must have value equal <0>.");

        check(VOID).must$(Have::sameValue$, (Object) null);
    }

    @Test
    public void test_notSameValue$_OPT() {
        attestThrownBy(() -> check(OPT).must$(Have::notSameValue$, VALUE))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <Opt['0'^^Integer]> must NOT have value equal <0>.");
    }

    @Test
    public void test_notSameValue$_VOID() {
        check(VOID).must$(Have::notSameValue$, VALUE);
    }


    @Test
    public void test_noValuePresent$_OPT() {
        attestThrownBy(() -> check(OPT).must$(Have::noValuePresent$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <Opt['0'^^Integer]> must NOT have value.");
    }

    @Test
    public void test_noValuePresent$_VOID() {
        check(VOID).must$(Have::noValuePresent$);
    }

    @Test
    public void test_Void$_OPT() {
        attestThrownBy(() -> check(OPT).must$(Be::Void$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <Opt['0'^^Integer]> must be void.");
    }

    @Test
    public void test_Void$_VOID() {
        check(VOID).must$(Be::Void$);
    }

    @Test
    public void test_notVoid$_OPT() {
        check(OPT).must$(Be::notVoid$);
    }

    @Test
    public void test_notVoid$_VOID() {
        attestThrownBy(() -> check(VOID).must$(Be::notVoid$))
                .must$(Be::instanceOf$, IllegalStateException.class)
                .must$(Have::msgEqual$, "Check [?]: Optional <Opt.empty> must NOT be void.");
    }

}
