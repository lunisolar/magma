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

import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.consumer.LBiConsumer;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer;
import eu.lunisolar.magma.func.function.*;
import eu.lunisolar.magma.func.function.from.LBoolFunction;
import eu.lunisolar.magma.func.function.from.LObjBiIntFunction;
import eu.lunisolar.magma.func.function.from.LOiFunction;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.operator.ternary.LIntTernaryOperator;
import eu.lunisolar.magma.func.operator.ternary.LTernaryOperator;
import eu.lunisolar.magma.func.operator.unary.LLogicalOperator;
import eu.lunisolar.magma.func.operator.unary.LUnaryOperator;
import eu.lunisolar.magma.func.predicate.LPredicate;
import eu.lunisolar.magma.func.supp.Is;
import eu.lunisolar.magma.func.supplier.LSupplier;
import org.testng.annotations.Test;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static eu.lunisolar.magma.func.function.LFunction.func;
import static eu.lunisolar.magma.func.operator.unary.LUnaryOperator.unaryOp;
import static java.lang.System.*;

//>transform-to-MD<
/**
 * Basic introduction (by example) to default methods available in functional interfaces in this library.
 */
//>inject<:readmore

//>inject<:generated

/**
 * Naming conventions
 * ==========================
 *
 * ###  Abstract
 *
 * Basic introduction (by example) to default methods available in functional interfaces in this library.
 *
 * ### Prefix 'L'
 *
 * Because some function names are very close or exactly like JRE, or possibly like other 3rd party libraries,
 * all names of functional interfaces are prefixed with 'L'. As much as such prefix is redundant it actually makes difference between Function from LFunction,
 * and alike.
 */
public class Example_Naming_Test {

    /**
     * ### Arguments
     * Argument types names in interface name are shortened.
     *
     * | Type    | Name part  |
     * |--------:|:-----------|
     * | int     | 'Int'      |
     * | long    | 'Long'     |
     * | short   | 'Srt'      |
     * | float   | 'Flt'      |
     * | double  | 'Dbl'      |
     * | boolean | 'Bool'     |
     *
     * Example:
     */
    //>example<
    @Test
    public void namingParts_args() {

        LToIntFunction<Number> f1 = Number::intValue;
        LToLongFunction<Number> f2 = Number::longValue;
        LToByteFunction<Number> f3 = Number::byteValue;
        LToSrtFunction<Number> f4 = Number::shortValue;
        LToFltFunction<Number> f5 = Number::floatValue;
        LToDblFunction<Number> f6 = Number::doubleValue;
        LBoolFunction<Boolean> f7 = i -> i ? Boolean.TRUE : Boolean.FALSE;

        LObjBiIntFunction<String, String> f8 = (String str, int i1, int i2) -> String.format("%s[%d,%d]", str, i1, i2);

        // not all interface names need to include arg types names
        LTriFunction<String, String, String, String> f9 = (s1, s2, s3) -> String.format("%s[%s,%s, %s]", s1, s2, s3);
    }
    //>example<

    /**
     * ### Functional interface type
     *
     * In respect to the interface type, compared to JRE, there are no surprises (except maybe for Action). But there are also some methods that include the
     * interface name
     *
     * | Type                | Interface suffix  | Static method |
     * |--------------------:|:------------------|:--------------|
     * | action              | 'Action'          | 'act'         |
     * | consumer            | 'Consumer'        | 'cons'        |
     * | predicate           | 'Predicate'       | 'pred'        |
     * | operator            | 'Operator'        | 'op'          |
     * | logical operator    | 'LogicalOperator' | 'logicalOp'   |
     * | function            | 'Function'        | 'func'        |
     *
     * Example:
     */
    //>example<
    @Test
    public void namingFunctionTypes() {

        LAction f1 = LAction.act(() -> out.println("done"));
        LConsumer<String> f2 = LConsumer.cons(out::println);
        LPredicate<String> f3 = LPredicate.pred(s -> Is.equal(s, "true"));
        LUnaryOperator<String> f4 = LUnaryOperator.unaryOp(s -> s);
        LLogicalOperator f5 = LLogicalOperator.logicalOp(s -> s);
        LFunction<String, String> f6 = LFunction.func(s -> s);

    }
    //>example<

    /**
     * Cases where you might want to use static wrapper methods are explained [here](http://lunisolar.eu/magma/defaults).
     */

    /**
     * ### Argument numbers
     *
     * Table presenting prefixes for Functional interfaces ans names of Tuples for ALL generic arguments:
     *
     * | Quantity   | non-operator | operator      | tuple         |
     * |-----------:|:-------------|:--------------|---------------|
     * | 0          | ''           | -             | LTuple.Void   |
     * | 1          | ''           | 'Unary'       | LSingle       |
     * | 2          | 'Bi'         | 'Binary'      | LPair         |
     * | 3          | 'Tri'        | 'Ternary'     | LTriple       |
     * | 4          | 'Quad'       | -             | LQuad         |
     * | 5          | 'Quint'      | -             | LQuint        |
     */
///> If you find those prefixes inconsistent, you are not alone - [Numeral prefixes](https://en.wikipedia.org/wiki/Numeral_prefix).
///> Inconsistency was already broken in JRE  ('Bi'/'Tri' vs 'Di'/'Tri'). For this library potentially the most expected one were used.
    /**
     * Example:
     */
    //>example<
    @Test
    public <T1, T2, T3, T4, T5, R> void argNo() {
        LSupplier<T1> f0;
        LFunction<T1, R> f1;
        LBiFunction<T1, T2, R> f2;
        LTriFunction<T1, T2, T3, R> f3;
        LQuadFunction<T1, T2, T3, T4, R> f4;
        LQuintFunction<T1, T2, T3, T4, T5, R> f5;

        LTernaryOperator<T1> op;
        LIntTernaryOperator intOp;

        LObjBiIntFunction<T1, String> objIntInt_To_R = (obj, i1, i2) -> String.format("%s[%d, %d]", obj, i1, i2);
    }
    //>example<

    /**
     * ### Special Cases
     *
     * Some special types of functional interfaces are breaking the naming convention.
     * You can see those in table ([here](http://lunisolar.eu/magma/all-functions)) with note in "Special" column.
     */
    //>example<
    @Test
    public <T1, T2, T3, T4, T5, R> void specialNamingCases() {
        LOiFunction<List<T1>, T1> f0 = (source, index) -> source.get(index);
        LOiFunction<List<T1>, T1> f0_bis = List::get;

        LTieConsumer<List<T2>, T2> f1 = (targetList, index, element) -> targetList.add(element);
        LTieConsumer<List<T2>, T2> f1_bis = List::add;

        LBiConsumer<List<T2>, T2> f2 = (targetList, element) -> targetList.add(element);
        LBiConsumer<List<T2>, T2> f2_bis = List::add;

    }
    //>example<

    //>inject<:generated
}

