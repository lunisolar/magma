/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.basics.exceptions.IllegalValueException;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.check.Checks;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//>transform-to-MD<
/**
 * Basic introduction (by example) to fluent validations with library functions.
 */
//>inject<:readmore

//>inject<:generated

///
///Fluent Validations
///==========================
///
///### Abstract
///
///Basic introduction (by example) to fluent validations with library functions. Described here functionality is available since version 2.1.0.
///
///### Examples
///
///> Before you read more please read the article about the simple validations with predicates, that explains premise of
///> <a href="https://github.com/lunisolar/magma/blob/master/magma-basics/src/main/java/eu/lunisolar/magma/basics/exceptions/X.java" target="_blank">X</a>
///> <a href="https://github.com/lunisolar/magma/blob/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/Be.java" target="_blank">Be</a>,
///> <a href="https://github.com/lunisolar/magma/blob/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/Does.java" target="_blank">Does</a>,
///> <a href="https://github.com/lunisolar/magma/blob/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/Is.java" target="_blank">Is</a>,
///> <a href="https://github.com/lunisolar/magma/blob/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/P.java" target="_blank">P</a>.
///

/**
 * Introduced here examples cover few most common cases of validation/assertions.
 */
public class Example_Validations_Fluent_Test {

    private int arg45 = 45;

    /**
     * Lets consider very simple validation that need to be done.
     */
    //>example<
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument \\[\\?\\]: cannot be greater or equal 50.")
    public void test1() {

        Checks.arg(arg45)
              .mustNot(Be::gtEq, 50, "cannot be greater or equal 50") //passes
              .must(Be::lt, 50, "cannot be greater or equal 50"); //passes

        var i = arg(60)
                .mustNot(Be::gtEq, 50, "cannot be greater or equal 50") //fails
                .get();
    }
    //>example<

    /**
     * Now lets include the value of the argument to the message and the name.
     */
    //>example<
    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "State \\[number\\]: cannot be greater or equal 40. Value: 60")
    public void test2() throws Exception {

        //fails
        var i = state(60, "number").mustNot$(Be::gtEq, 40, "cannot be greater or equal 40").get();
    }
    //>example<

    /**
     * Another good example:
     */
    //>example<
    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Fully custom message: must be 5 < V\\(45\\) < 40")
    public void test3() {

        //fails
        value(arg45, "arg45").must(Be::between, 5, 40, "Fully custom message: must be %d < V(%d) < %s", 5, arg45, 40);

    }
    //>example<

    @Test
    public void valueTrait() {
        CheckInt opt = Checks.value(45);

        assertThat(opt.value(45)).isNotSameAs(opt);

        assertThatThrownBy(opt::voidValue).isInstanceOf(UnsupportedOperationException.class)
                                          .hasMessage("Trait implementation (CheckInt) does not support empty value.");

    }

    //>inject<:generated

}
