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

import eu.lunisolar.magma.func.asserts.DefaultFunctionalAssertions;
import eu.lunisolar.magma.func.build.function.to.LToIntBiFunctionBuilder;
import eu.lunisolar.magma.func.build.function.to.LToIntFunctionBuilder;
import eu.lunisolar.magma.func.build.std.IntUnaryOperatorBuilder;
import eu.lunisolar.magma.func.function.to.LToIntBiFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import org.assertj.core.api.ObjectAssert;
import org.testng.annotations.Test;

import java.util.function.*;

import static eu.lunisolar.magma.basics.exceptions.Handling.throwThe;
import static eu.lunisolar.magma.func.asserts.FunctionalAssertions.THEN;
import static eu.lunisolar.magma.func.function.to.LToIntBiFunction.apply1stAsInt;
import static eu.lunisolar.magma.func.predicate.LBiPredicate.test1st;

//>transform-to-MD<
/**
 * Basic introduction (by example) to how builders work with functional interfaces.
 */
//>inject<:readmore

/**
 * Builders
 * ==========================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to how builders work with functional interfaces.
 *
 * In case there is any value in decoratively specific function implementations, a fluent builders might come handy.
 */
public class Example_Builders_Test {

    /**
     * In case you want to define function little more decoratively or you just have methods ready to be referenced, then depending on the context you might
     * want to use Builder. Builders are available for each functional interface in library and also those in JRE (java.lang.function.*). Basically each
     * builder
     * gathers separates cases that consist of predicate and function implementation.
     *
     * Final function will go through those cases in a order and execute predicate on input arguments and if predicate evaluate to true then its paired
     * function implementation will be called. For obvious reasons functions build this way are good candidate to be cached instead of being build each time
     * they are used.
     *
     * Lets define function that tries to convert any object of specific type to a integer:
     */
    @Test
    public void testBuild() throws Exception {
        //>example<
        LToIntFunction<Object> function = LToIntFunctionBuilder
                .toIntFunction()
                .aCase(Number.class, Number::intValue)
                .inCase(String.class::isInstance).evaluate(o -> Integer.parseInt((String) o))
                .eventually(o -> throwThe(new IllegalArgumentException()))
                .build();
        //>example<

        THEN.assertToIntFunc(function)
            .doesApplyAsInt(0).to(a -> a.isEqualTo(0))
            .doesApplyAsInt(5).to(a -> a.isEqualTo(5))
            .doesApplyAsInt(44f).to(a -> a.isEqualTo(44))
            .doesApplyAsInt(3_000_000_000L).to(a -> a.isEqualTo(-1294967296))
            .doesApplyAsInt("-4").to(a -> a.isEqualTo(-4))
            .doesApplyAsInt("non number").withException(a -> a.isInstanceOf(NumberFormatException.class))
            .doesApplyAsInt(new Object()).withException(a -> a.isInstanceOf(IllegalArgumentException.class));
    }

    @Test
    public void testBuildMoreChecks() throws Exception {
        //>example<
        LToIntBiFunction<Object, Object> function = LToIntBiFunctionBuilder
                .toIntBiFunction()
                .casesOf(Long.class, null, pcp -> pcp
                        .inCase(test1st(l -> isInIntRange((long) l))).evaluate(apply1stAsInt(Long::intValue))
                        .eventually((l, o) -> throwThe(new IllegalArgumentException("To large for int."))))
                .aCase(test1st(s -> s instanceof Short), apply1stAsInt(s -> ((Short) s).intValue()))
                .aCase(Number.class, null, apply1stAsInt((Number::intValue)))
                .inCase(test1st(s -> s instanceof String)).evaluate(apply1stAsInt(s -> Integer.parseInt((String) s)))
                .eventually(apply1stAsInt(s -> throwThe(new IllegalArgumentException())))
                .build();
        //>example<

        LToIntBiFunction<Object, Object> function2 = (o, o_2nd) -> {
            if (o instanceof Long) {
                if (isInIntRange((long) o)) {
                    return ((Long) o).intValue();
                } else {
                    throw new IllegalArgumentException("To large for int.");
                }
            } else if (o instanceof Short) {
                return ((Short) o).intValue();
            } else if (o instanceof Number) {
                return ((Number) o).intValue();
            } else if (o instanceof String) {
                return Integer.parseInt(o.toString());
            } else {
                throw new IllegalArgumentException();
            }
        };

        THEN.assertToIntBiFunc(function)
            .doesApplyAsInt(0, null).to(a -> a.isEqualTo(0))
            .doesApplyAsInt(5, null).to(a -> a.isEqualTo(5))
            .doesApplyAsInt(44f, null).to(a -> a.isEqualTo(44))
            .doesApplyAsInt(3_000L, null).toEqualTo(3000)
            .doesApplyAsInt(3_000_000_000L, null).withException(a -> a.isInstanceOf(IllegalArgumentException.class).hasMessage("To large for int."))
            .doesApplyAsInt("-4", null).to(a -> a.isEqualTo(-4))
            .doesApplyAsInt("non number", null).withException(a -> a.isInstanceOf(NumberFormatException.class))
            .doesApplyAsInt(new Object(), null).withException(a -> a.isInstanceOf(IllegalArgumentException.class));
    }

    private static boolean isLongInIntRange(Object o) {
        return o instanceof Long && isInIntRange((long) o);
    }

    private static boolean isInIntRange(long o) {
        return ((long) Integer.MAX_VALUE) >= o && o >= ((long) Integer.MIN_VALUE);
    }

    /**
     * Each functional interface has its own builder (even those from JRE) thus we can use also builders for primitive cases.
     */
    @Test
    //>example<
    public void testBuildPrimitive() throws Exception {

        IntUnaryOperator function = IntUnaryOperatorBuilder
                .intUnaryOperator()
                .inCase(i -> i == 0).produce(-1)
                .inCase(i -> i >= 10).evaluate(i -> i * 2)
                .inCase(i -> i > 0).evaluate(i -> i)
                .build();

        THEN.assertIntUnaryOp(function)
            .doesApplyAsInt(0).to(a -> a.isEqualTo(-1))
            .doesApplyAsInt(2).to(a -> a.isEqualTo(2))
            .doesApplyAsInt(44).to(a -> a.isEqualTo(88));

    }
    //>example<

}
