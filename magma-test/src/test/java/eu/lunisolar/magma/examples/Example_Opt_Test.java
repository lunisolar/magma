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
import eu.lunisolar.magma.func.supp.Is;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supp.opt.Opt;
import eu.lunisolar.magma.func.supp.opt.OptBase;
import eu.lunisolar.magma.func.supp.opt.OptInt;
import org.testng.annotations.Test;

import javax.annotation.Nullable;
import java.util.*;

import static eu.lunisolar.magma.asserts.Attests.attestSup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//>transform-to-MD<
/**
 * Basic introduction (by example) to Opt classes.
 */
//>inject<:readmore

//>inject<:generated

/**
 * Opt(ional)
 * ==========================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to Opt classes.
 * Available since 3.0.0.
 *
 * ### Description
 * Opt/OptInt/OptLong/OptDouble and other primitive examples are like spiritual extension to the Optional/OptionalInt/OptionalLong/OptionalDouble (since those
 * cannot be extended), and you can use them as spiritual replacement in 'optional' response. Optional classes from this library aims to provide:
 *
 * - more possibility for cases where simply referencing existing method (that can be provided by editor completion).
 * - that means more possibility to build 'sentences' that actually tell what happens.
 *
 * ### Examples
 */
public class Example_Opt_Test {

    /**
     * A very abstract example of few mapping methods:
     */
    //>example<

    public static interface S {
        S add(D all);
        Opt<S> addO(D all);
    }

    public static interface D {
        D add(S all);
        Opt<D> addO(S all);
    }

    @Test
    public void test1() {
        Opt<S> optS = Opt.of(null);
        Opt<S> v1 = optS.map((D) null, S::add);
        Opt<D> v2 = optS.mapWith((D) null, D::add);

        Opt<S> v3 = optS.flatMap((D) null, S::addO);
        Opt<D> v4 = optS.flatMapWith((D) null, D::addO);
    }

    //>example<

    /**
     * Example of filtering:
     */
    //>example<
    @Test
    public void test2() {

        Opt<Integer> ooo = Opt.obj(5);

        var result = ooo
                .uniFilter(Is::equal, 5) // uniFilter resolves ambiguity of argument type
                .filter(Is::inRange, 5, 7) // 5 is in range from 5 to 7
                .filter(Is::between, 5, 7); // 5 is NOT between 5 to 7

        assertThat(result.toOpt()).isEmpty();
    }
    //>example<

    @Test
    public void test2Bis() {

        OptInt ooo = Opt.of(5);

        var result = ooo
                .filter(Is::equal, 5)
                .filter(Is::inRange, 5, 7) // 5 is in range from 5 to 7
                .filter(Is::between, 5, 7); // 5 is NOT between 5 to 7

        assertThat(result.toOpt()).isEmpty();
    }

    /**
     * Here is example of `is` alternative to `filter` methods along with a `must` check, since Opt(*) inherit most check methods [described here](http://lunisolar.eu/magma/validations-fluent).
     */
    //>example<
    @Test(expectedExceptions = IllegalValueException.class, expectedExceptionsMessageRegExp = "Opt \\[\\?\\]: must be 99")
    public void test3() {

        Opt<Integer> ooo = Opt.obj(5);

        assertThat(ooo.uniIs(P::equal, 5)).isTrue();
        assertThat(ooo.is(P::inRange, 5, 7)).isTrue();
        assertThat(ooo.is(P::between, 5, 7)).isFalse();

        ooo.uniMust2(Be::equal, 99, "must be 99");
    }
    //>example<

    /**
     * For the new types of methods following is true in regards to the `empty` value:
     *
     * - `is` evaluates always false (no predicate is true).
     * - `must`/`mustNot` will always throw exception that there is no value present (the predicate cannot be asserted)
     */
    //>example<
    @Test
    public void test4() {
        Opt ooo = Opt.empty();

        assertThat(ooo.is(P::Null)).isFalse();
    }

    @Test(expectedExceptions = NoSuchElementException.class, expectedExceptionsMessageRegExp = "No value present.")
    public void test5() {
        OptInt ooo = OptInt.empty();

        ooo.must2(Be::equal, 99, "must be 99");
    }
    //>example<

    @Test
    public void get_value_nullable() {
        Opt<String> opt = Opt.of(null);

        // testing value against ANY predicate is impossible.
        assertThatThrownBy(() -> opt.must(Be::Null, "must be null!"))
                .isInstanceOf(NoSuchElementException.class).hasMessage("No value present.");

        // that's part of Optional 'contract'
        attestSup(opt::get).doesGet().withException(ea -> ea.isInstanceOf(NoSuchElementException.class).hasMessage("No value present."));

        // that's part of Opt 'contract'
        assertThat(opt.nullable()).isNull();

        // that's part of Opt/Check 'contract'
        attestSup(opt::value).doesGet().withException(ea -> ea.isInstanceOf(NoSuchElementException.class).hasMessage("No value present."));
    }

    @Test
    public void get_value_nullable_int() {
        OptInt opt = OptInt.empty();

        // testing value against ANY predicate is impossible.
        assertThatThrownBy(() -> opt.must(Be::Null, "must be null!"))
                .isInstanceOf(NoSuchElementException.class).hasMessage("No value present.");

        // that's part of Optional 'contract'
        attestSup(opt::get).doesGet().withException(ea -> ea.isInstanceOf(NoSuchElementException.class).hasMessage("No value present."));

        // that's part of Opt 'contract'
        assertThat(opt.nullable()).isNull();

        // that's part of Opt/Check 'contract'
        attestSup(opt::value).doesGet().withException(ea -> ea.isInstanceOf(NoSuchElementException.class).hasMessage("No value present."));
    }

    @Test
    public void valueTrait() {
        OptInt opt = OptInt.empty();

        assertThat(opt.voidValue()).isSameAs(opt);
        assertThat(opt.value(45)).isNotSameAs(opt);

        opt = OptInt.valueOf(45);

        assertThat(opt.voidValue()).isNotSameAs(opt);
        assertThat(opt.value(45)).isNotSameAs(opt);
    }

    static {
        Opt<Integer> ooo = Opt.obj(5);
        ooo.uniIs(5, Objects::equals);
        ooo.uniMust2(Be::equal, 5, "must be 5");
        ooo.uniIs(Objects::equals, 5);
    }

    static {
        Opt<S> optS = Opt.of(null);
        Opt<S> v1 = optS.map((D) null, S::add);
        Opt<D> v2 = optS.mapWith((D) null, D::add);

        Opt<S> v3 = optS.flatMap((D) null, S::addO);
        Opt<D> v4 = optS.flatMapWith((D) null, D::addO);
    }

    public static <S> boolean filterA(S s1, S s3) {
        return true;
    }

    public static <S1, S2> boolean filterB(S1 s1, S2 s3) {
        return true;
    }

    static {
        Opt<S> optS = Opt.of(null);
        optS.uniFilter(null, Example_Opt_Test::filterA);
        optS.uniFilter(null, Example_Opt_Test::filterB);

        optS.filter((Object) null, Example_Opt_Test::filterA);
        optS.filter((Object) null, Example_Opt_Test::filterB);

        optS.uniFilter(null, P::equal);
        optS.uniFilter(null, P::equal);

        optS.filter((Object) null, P::equal);
        optS.filter((Object) null, P::equal);
    }


    /**
     * Do you need specialized optional for your application?
     */
    //>example<
    public final static class OptStr extends OptBase<String, OptStr> {

        public static final OptStr EMPTY_STR = new OptStr("");
        public static final OptStr VOID      = new OptStr(null);

        private OptStr(String value)                   { super(value);}
        @Override public OptStr value(String value)    { return str(value);}
        @Override public OptStr voidValue()            { return VOID;}
        public static OptStr str(@Nullable String str) { return new OptStr(str);}
        public static OptStr emptyOptStr()             { return EMPTY_STR;}
        public String toString()                       {return value;}

        //<editor-fold desc="custom methods">

        public OptStr replace(String target, String replacement) {
            return this.uniMap(s -> s.replace(target, replacement));
        }

        public OptStr removeTail(String tail) {
            return this.uniMap(s -> s.endsWith(tail) ? s.substring(0, s.length() - tail.length()) : s);
        }

        //</editor-fold>
    }

    @Test
    public void test6() {

        OptStr str = OptStr.str("This is optional string.")
                           .replace("This", "THIS")
                           .removeTail(".")
                           .must2Ex(Be::equalEx, "THIS is optional string");

    }
    //>example<

    //>inject<:generated

}
