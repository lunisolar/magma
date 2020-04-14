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
import eu.lunisolar.magma.func.supp.Have;
import eu.lunisolar.magma.func.supp.MsgVerbosity;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.check.Checks;
import eu.lunisolar.magma.func.supp.opt.Opt;
import org.testng.annotations.Test;

import java.util.*;

import static eu.lunisolar.magma.func.supp.MsgVerbosity.*;
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
 *
 * > Fluent assertions always include current/actual value, values of parameters. If that is not desired then simple validations in form of static methods on predicates could be used instead (methods 'throwIf').
 *
 */
public class Example_Validations_Fluent_Test {

    private int arg45 = 45;

    /**
     * Lets consider very simple validation that need to be done.
     */
    //>example<
    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Argument \\[\\?\\]: cannot be greater or equal 50")
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
    @Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "State \\[number=='60'\\]: cannot be greater or equal 40")
    public void test2() throws Exception {

        //fails
        var i = state(60, "number").verbosity(VAL).mustNot(Be::gtEq, 40, "cannot be greater or equal 40").get();
    }
    //>example<

    /**
     * Another good example:
     */
    //>example<
    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[arg45\\]: Fully custom message: must be 5 < V=45 < 40")
    public void test3() {

        //fails
        value(arg45, "arg45").must(Be::between, 5, 40, "Fully custom message: must be %2$d < V=%1$d < %3$d");

    }
    //>example<

    /**
     * Example with values derived from the object:
     */
    //>example<
    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Check \\[\\?\\]: Collection size must be >1")
    public void test4() {
        value(Collections.singleton(2), "collection name")
                .mustNot(Be::Null, "This collection cannot be null.")
                //fails:
                .checkInt(Collection::size, v -> v.must(Be::gtEq, 2, "Collection size must be >1"));
    }
    //>example<

    /**
     * Similar but with name being used:
     */
    //>example<
    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Check \\[size\\]: Collection size must be >1")
    public void test5() {
        value(Collections.singleton(2), "collection_name")
                .mustNot(Be::Null, "This collection cannot be null.")
                //fails:
                .checkInt(Collection::size, "size", v -> v.must(Be::gtEq, 2, "Collection size must be >1"));
    }
    //>example<

    /**
     * Another good example:
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Check/attest \\[arg45=='45'\\]\\(params: '5', '40'\\): Fully custom message: must be 5 < V=45 < 40")
    public void test6() {

        //fails
        attest(arg45, "arg45").must(Be::between, 5, 40, "Fully custom message: must be %2$d < V=%1$d < %3$d");

    }
    //>example<

    /**
     * We can also use method that will include check parameters in the message for us.
     */
    //>example<
    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Check/attest \\[arg45=='45'\\]\\(params: '5', '40'\\): not between")
    public void test7() {

        //fails
        attest(arg45, "arg45").verbosity(ALL).must(Be::between, 5, 40, "not between");

    }
    //>example<

    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[\\?\\]: _message_")
    public void valueTrait() {
        CheckInt opt = Checks.value(45);

        assertThat(opt.value(45)).isNotSameAs(opt);

        assertThatThrownBy(opt::voidValue).isInstanceOf(UnsupportedOperationException.class)
                                          .hasMessage("Trait implementation (CheckInt) does not support empty value.");

        Checks.value(null).must(Be::notNull, "_message_");
    }

    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[\\?\\]: There is no key in the map")
    public void checkWith() {
        Map<String, String> map = new HashMap();
        Checks.value("key22").mustWith(map, Map::containsKey, "There is no key in the map");
    }

    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[\\?\\]: There is no key in the map")
    public void checkWith_MIN() {
        Map<String, String> map = new HashMap();
        Checks.value("key22").verbosity(MIN).mustWith(map, Map::containsKey, "There is no key in the map");
    }

    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[\\?=='key22'\\]: There is no key in the map")
    public void checkWith_VAL() {
        Map<String, String> map = new HashMap();
        Checks.value("key22").verbosity(VAL).mustWith(map, Map::containsKey, "There is no key in the map");
    }

    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[\\?=='key22'\\]\\(param: '\\{A=a\\}'\\): There is no key in the map")
    public void checkWith_ALL() {
        Map<String, String> map = new HashMap();
        map.put("A", "a");
        Checks.value("key22").verbosity(ALL).mustWith(map, Map::containsKey, "There is no key in the map");
    }

    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[\\?\\]\\(param: '\\{A=a\\}'\\): There is no key in the map")
    public void checkWith__PARAM() {
        Map<String, String> map = new HashMap();
        map.put("A", "a");
        Checks.value("key22").verbosity(PARAMS).mustWith(map, Map::containsKey, "There is no key in the map");
    }

    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[\\?\\]: All references must be null.")
    public void specialPredicates() {
        Checks.value(new Object[]{1, 2}).must$(Be::allNull$);
    }

    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Value \\[\\?\\]: Exception <java.lang.RuntimeException: Message2!> must have message containing <'I'm Expecting this>'.")
    public void specialPredicates2() {
        var e = new Exception("Message1!", new RuntimeException("Message2!"));

        Checks.value(e).must$(P.have$(Exception::getCause, Have::msgContain$, "I'm Expecting this"));
    }


    /**
     * Alternatively you can build predicate with P.have (or Does.have) methods.
     */
    //>example<
    @Test
    public void test8() {
        attest(new Integer(1))
                .must(P.have(Object::toString, P::notNull), "serialise to non-null string")
                .must(P.have(Object::toString, P::equal, "1"), "serialize to '1'")
                .must(P.haveInt(Integer::intValue, P::equal, 1), "not between");
    }
    //>example<

    @Test
    public void toString1() {
        assertThat(Opt.of(45).toString()).isEqualTo("OptInt['45'^^int]");
        assertThat(Opt.of("45").toString()).isEqualTo("Opt['45'^^String]");
        assertThat(Opt.of(null).toString()).isEqualTo("Opt.empty");
        assertThat(Checks.value(null).toString()).isEqualTo("Value [?==<null>]");
        assertThat(Checks.value(45).toString()).isEqualTo("Value [?=='45'^^int]");
        assertThat(Checks.arg(45, "arg1").toString()).isEqualTo("Argument [arg1=='45'^^int]");
        assertThat(Checks.arg(45).toString()).isEqualTo("Argument [?=='45'^^int]");
        assertThat(Checks.state(45, "count").toString()).isEqualTo("State [count=='45'^^int]");
        assertThat(Checks.state(45).toString()).isEqualTo("State [?=='45'^^int]");
        assertThat(Checks.attest(45, "arg1").toString()).isEqualTo("Check/attest [arg1=='45'^^int]");
        assertThat(Checks.attest(45).toString()).isEqualTo("Check/attest [?=='45'^^int]");
    }

    //>inject<:generated

}
