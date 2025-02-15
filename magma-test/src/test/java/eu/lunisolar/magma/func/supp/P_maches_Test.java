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

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;
import static eu.lunisolar.magma.func.supp.check.Checks.attestThrownBy;

public class P_maches_Test {

    @Test void matches_P() {
        attest(P.match("123", "123")).mustEx(Be::TrueEx);
        attest(P.notMatch("123", "123")).mustEx(Be::FalseEx);
        attest(P.notMatch("123", "NNN")).mustEx(Be::TrueEx);

        attest(P.matchEx("123", "NNN")).mustBeEqual("String <'123'> must match pattern <'NNN'>.");
        attest(P.matchEx("123", Pattern.compile("NNN"))).mustBeEqual("String <'123'> must match pattern <'NNN'>.");

        attest(P.notMatchEx("123", "123")).mustBeEqual("String <'123'> must NOT match pattern <'123'>.");
        attest(P.notMatchEx("123", Pattern.compile("123"))).mustBeEqual("String <'123'> must NOT match pattern <'123'>.");


        attestThrownBy(()-> P.notMatchEx("123", "[123"))
                .mustBeInstanceOf(PatternSyntaxException.class)
                .mustEx(Have::msgStartWithEx, "Unclosed character class near index ");

    }

    @Test void msgMatches_P() {
        attest(P.msgMatch(e("123"), "123")).mustEx(Be::TrueEx);
        attest(P.msgNotMatch(e("123"), "123")).mustEx(Be::FalseEx);
        attest(P.msgNotMatch(e("123"), "NNN")).mustEx(Be::TrueEx);

        attest(P.msgMatchEx(e("123"), "NNN")).mustBeEqual("Exception <java.lang.RuntimeException: 123> must have message matching pattern <'NNN'>.");
        attest(P.msgMatchEx(e("123"), Pattern.compile("NNN"))).mustBeEqual("Exception <java.lang.RuntimeException: 123> must have message matching pattern <'NNN'>.");

        attest(P.msgNotMatchEx(e("123"), "123")).mustBeEqual("Exception <java.lang.RuntimeException: 123> must NOT have message matching pattern <'123'>.");
        attest(P.msgNotMatchEx(e("123"), Pattern.compile("123"))).mustBeEqual("Exception <java.lang.RuntimeException: 123> must NOT have message matching pattern <'123'>.");


        attestThrownBy(()-> P.msgNotMatchEx(e("123"), "[123"))
                .mustBeInstanceOf(PatternSyntaxException.class)
                .mustEx(Have::msgStartWithEx, "Unclosed character class near index ");

    }

    private static RuntimeException e(String msg) {
        return new RuntimeException(msg);
    }


}
