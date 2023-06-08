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

import eu.lunisolar.magma.basics.exceptions.IllegalValueException;
import eu.lunisolar.magma.func.supp.opt.Opt;
import eu.lunisolar.magma.func.supp.opt.OptTrait;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;
import static eu.lunisolar.magma.func.supp.check.Checks.check;

public class P_Opt_Test {

    public static final Object   VALUE       = 0;
    public static final Object   OTHER_VALUE = 1;
    public static final OptTrait OPT         = Opt.of(VALUE);
    public static final OptTrait VOID        = Opt.empty();
    public static final OptTrait NULL        = null;

    public static final String VALUE_STR       = "'0'^^Integer";
    public static final String OTHER_VALUE_STR = "'1'^^Integer";

    @Test
    public void test_valuePresentEx_OPT() {
        check(OPT).mustEx(Have::valuePresentEx);
    }

    @Test
    public void test_valuePresentEx_VOID() {
        attestThrownBy(() -> check(VOID).mustEx(Have::valuePresentEx))
                .mustEx(Be::instanceOfEx, IllegalStateException.class)
                .mustEx(Have::msgEqualEx, "Check [?]: Optional <Opt.empty> must have value.");

        attestThrownBy(() -> check(VOID).verbose().mustEx(Have::valuePresentEx))
                .mustEx(Be::instanceOfEx, IllegalStateException.class)
                .mustEx(Have::msgEqualEx, "Check [?=='Opt.empty']: Optional <Opt.empty> must have value.");
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = ".*Argument 'opt' must not be null.*")
    public void test_valuePresentEx_NULL() {
        check(NULL).mustEx(Have::valuePresentEx);
    }

    @Test
    public void test_sameValueEx_OPT() {
        check(OPT).mustEx(Have::sameValueEx, VALUE);
    }

    @Test
    public void test_sameValueEx_VOID() {
        attestThrownBy(() -> check(VOID).mustEx(Have::sameValueEx, VALUE))
                .mustEx(Be::instanceOfEx, IllegalStateException.class)
                .mustEx(Have::msgEqualEx, "Check [?]: Optional <Opt.empty> must have value equal <0>.");

        check(VOID).mustEx(Have::sameValueEx, (Object) null);
    }

    @Test
    public void test_notSameValueEx_OPT() {
        attestThrownBy(() -> check(OPT).mustEx(Have::notSameValueEx, VALUE))
                .mustEx(Be::instanceOfEx, IllegalStateException.class)
                .mustEx(Have::msgEqualEx, "Check [?]: Optional <Opt['0'^^Integer]> must NOT have value equal <0>.");
    }

    @Test
    public void test_notSameValueEx_VOID() {
        check(VOID).mustEx(Have::notSameValueEx, VALUE);
    }


    @Test
    public void test_noValuePresentEx_OPT() {
        attestThrownBy(() -> check(OPT).mustEx(Have::noValuePresentEx))
                .mustEx(Be::instanceOfEx, IllegalStateException.class)
                .mustEx(Have::msgEqualEx, "Check [?]: Optional <Opt['0'^^Integer]> must NOT have value.");
    }

    @Test
    public void test_noValuePresentEx_VOID() {
        check(VOID).mustEx(Have::noValuePresentEx);
    }

    @Test
    public void test_VoidEx_OPT() {
        attestThrownBy(() -> check(OPT).mustEx(Be::VoidEx))
                .mustEx(Be::instanceOfEx, IllegalStateException.class)
                .mustEx(Have::msgEqualEx, "Check [?]: Optional <Opt['0'^^Integer]> must be void.");
    }

    @Test
    public void test_VoidEx_VOID() {
        check(VOID).mustEx(Be::VoidEx);
    }

    @Test
    public void test_notVoidEx_OPT() {
        check(OPT).mustEx(Be::notVoidEx);
    }

    @Test
    public void test_notVoidEx_VOID() {
        attestThrownBy(() -> check(VOID).mustEx(Be::notVoidEx))
                .mustEx(Be::instanceOfEx, IllegalStateException.class)
                .mustEx(Have::msgEqualEx, "Check [?]: Optional <Opt.empty> must NOT be void.");
    }

    @Test
    public void test_mustBeInstanceOf() {
        try {
            Opt.obj(null).mustBeInstanceOf(Integer.class);
            throw new AssertionError("Nothing happened!");
        } catch (Throwable e) {
            if (!(e instanceof NoSuchElementException)) {
                throw new AssertionError("Wrong exception class!: " + e.getClass());
            }

            if (!"No value present.".equals(e.getMessage())) {
                throw new AssertionError("Wrong exception message!: " + e.getMessage());
            }
        }

    }

    @Test
    public void test_shouldBeInstanceOf() {
        try {
            Opt.obj(null).shouldBeInstanceOf(Integer.class);
        } catch (Throwable e) {
            throw new AssertionError("No exception should be thrown.");
        }

        try {
            Opt.obj("text").shouldBeInstanceOf(Integer.class);
            throw new AssertionError("Nothing happened!");
        } catch (Throwable e) {
            if (!(e instanceof IllegalValueException)) {
                throw new AssertionError("Wrong exception class!: " + e.getClass());
            }

            if (!"Opt [?]: Value <text> of actual class <class java.lang.String> must be instance of class <class java.lang.Integer> but is not.".equals(e.getMessage())) {
                throw new AssertionError("Wrong exception message!: " + e.getMessage());
            }
        }
    }

    @Test
    public void test_mustEx() {
        try {
            Opt.obj(null).mustEx(__-> null);
        } catch (Throwable e) {
            if (!(e instanceof NoSuchElementException)) {
                throw new AssertionError("Wrong exception class!: " + e.getClass());
            }

            if (!"No value present.".equals(e.getMessage())) {
                throw new AssertionError("Wrong exception message!: " + e.getMessage());
            }
        }
    }

}
