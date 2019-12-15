/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.basics.exceptions.X;
import eu.lunisolar.magma.func.supp.Is;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.predicate.LBiIntPredicate.throwIf$;
import static eu.lunisolar.magma.func.predicate.LIntPredicate.throwIf;
import static eu.lunisolar.magma.func.predicate.LIntPredicate.throwIf$;
import static eu.lunisolar.magma.func.predicate.LIntPredicate.throwIfNot;
import static eu.lunisolar.magma.func.predicate.LTriIntPredicate.throwIfNot;
import static eu.lunisolar.magma.func.predicate.LTriIntPredicate.throwIfNot$;

//>transform-to-MD<
/**
 * Basic introduction (by example) to simple validations with library functions.
 */
//>inject<:readmore

//>inject<:generated

/**
 * Predicate Validations
 * ==========================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to simple validations with library functions.
 *
 * ### Examples
 *
 * There is no need to actually explain much. Each of the predicate interfaces in the library have static methods 'throwIf' and 'throwIfNot' that basically can
 * be used as a very simplistic validations or assertions (in production code).
 */
public class Example_Validations_Simple_Test {

    private int arg45 = 45;

    /**
     * Lets consider very simple validation that need to be done.
     */
    //>example<
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "'arg' cannot be greater or equal 50")
    public void test1() {

        //passes
        throwIf(arg45, arg -> arg >= 50, IllegalArgumentException::new, "'arg' cannot be greater or equal 50");

        //passes
        throwIfNot(arg45, arg -> arg < 50, IllegalArgumentException::new, "'arg' cannot be greater or equal 50");

        //fails
        throwIf(60, arg -> arg >= 50, IllegalArgumentException::new, "'arg' cannot be greater or equal 50");
    }
    //>example<

    /**
     * Now lets include the value of the argument to the message.
     */
    //>example<
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "'arg'=45  cannot be greater or equal 40")
    public void test2() throws Exception {

        //fails
        throwIf(arg45, arg -> arg >= 40, IllegalArgumentException::new, "'arg'=%s  cannot be greater or equal 40", arg45);
    }
    //>example<

    /**
     * And here is a shorter version:
     */
    //>example<
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "'arg'=45  cannot be greater or equal 40")
    public void test3() {

        //fails
        throwIf$(arg45, arg -> arg >= 40, IllegalArgumentException::new, "'arg'=%s  cannot be greater or equal 40");
    }
    //>example<

///Bellow is more descriptive example, that references one of the methods from classes
///<a href="https://github.com/lunisolar/magma/blob/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/Be.java" target="_blank">Be</a>,
///<a href="https://github.com/lunisolar/magma/blob/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/Does.java" target="_blank">Does</a>,
///<a href="https://github.com/lunisolar/magma/blob/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/Is.java" target="_blank">Is</a>,
///<a href="https://github.com/lunisolar/magma/blob/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/P.java" target="_blank">P</a>.
///

    //>example<
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "'arg'=45  cannot be greater or equal 40")
    public void test4() {

        //fails
        throwIf$(arg45, Is::gtEq, 40, IllegalArgumentException::new, "'arg'=%s  cannot be greater or equal 40");

        //would do exactly the same, but is less self explanatory
        throwIf$(arg45, 40, Is::gtEq, IllegalArgumentException::new, "'arg'=%s  cannot be greater or equal 40");

    }
    //>example<

///  Library includes also class with factory methods for most common exceptions - <a href="https://github.com/lunisolar/magma/blob/master/magma-basics/src/main/java/eu/lunisolar/magma/basics/exceptions/X.java" target="_blank">X</a>

    /**
     * Another good example:
     */
    //>example<
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "'arg'=45  must be between 5 and 40.")
    public void test5() {

        //fails
        throwIfNot$(arg45, Is::between, 5, 40, X::arg, "'arg'=%s  must be between 5 and 40.");

        //would do exactly the same, but you have full control over message.
        throwIfNot(arg45, Is::between, 5, 40, X::arg, "'arg'=%d  must be between %d and %d.", arg45, 5, 40);

    }
    //>example<

    /**
     * AssertionError example:
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "'arg'=45  must be between 5 and 40.")
    public void test6() {
        throwIfNot$(arg45, Is::between, 5, 40, X::assertion, "'arg'=%s  must be between 5 and 40.");
    }
    //>example<

    //>inject<:generated

}
