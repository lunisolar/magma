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
import eu.lunisolar.magma.func.build.function.to.LToIntBiFunctionBuilder;
import eu.lunisolar.magma.func.build.function.to.LToIntFunctionBuilder;
import eu.lunisolar.magma.func.build.function.to.LToIntTriFunctionBuilder;
import eu.lunisolar.magma.func.build.std.RunnableBuilder;
import eu.lunisolar.magma.func.function.to.LToIntBiFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.function.to.LToIntTriFunction;
import eu.lunisolar.magma.func.predicate.LTriPredicate;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.*;

import static eu.lunisolar.magma.basics.exceptions.Handling.throwThe;
import static eu.lunisolar.magma.asserts.Attests.THEN;
import static eu.lunisolar.magma.func.function.to.LToIntBiFunction.apply1stAsInt;
import static eu.lunisolar.magma.func.predicate.LBiPredicate.test1st;
import static org.assertj.core.api.Assertions.assertThat;

//>transform-to-MD<
/**
 * Basic introduction (by example) to builders.
 */
//>inject<:readmore

//>inject<:generated

/**
 * Builders
 * ==========================
 *
 * ### Abstract
 *
 * Basic introduction (by example) to builders.
 */
public class Example_Builders_Test {

///> Please note that examples are conceptual just to show some possibilities.

    /**
     * In case you want to define function little more decoratively or you just have methods ready to be referenced, depending on the context, you might
     * want to use Builder. Builders are available for each functional interface in library and also those in JRE (java.lang.function.*). Basically each
     * builder gathers separates cases that consist of predicate and function implementation.
     *
     * Final (build) function will go through those cases in a order and execute predicate on input arguments and if predicate evaluate to true then its paired
     * function implementation will be called. For obvious reasons functions build this way are good candidate to be cached instead of being build each time
     * they are used.
     *
     * ### Examples
     *
     * Lets define function that tries to convert any object of specific type to a integer:
     */
    //>example<
    @Test
    public void testBuild() {

        LToIntFunction<Object> function = LToIntFunctionBuilder
                .toIntFunction()
                .aCase(Number.class, Number::intValue)
                .inCase(String.class::isInstance).evaluate(o -> Integer.parseInt((String) o))
                .otherwise(o -> throwThe(new IllegalArgumentException()))
                .build();

        THEN.attestToIntFunc(function)
            .doesApplyAsInt(0).to(a -> a.isEqualTo(0))
            .doesApplyAsInt(5).to(a -> a.isEqualTo(5))
            .doesApplyAsInt(44f).to(a -> a.isEqualTo(44))
            .doesApplyAsInt(3_000_000_000L).to(a -> a.isEqualTo(-1294967296))
            .doesApplyAsInt("-4").to(a -> a.isEqualTo(-4))
            .doesApplyAsInt("non number").withException(a -> a.isInstanceOf(NumberFormatException.class))
            .doesApplyAsInt(new Object()).withException(a -> a.isInstanceOf(IllegalArgumentException.class));
    }
    //>example<

    @Test
    public void testBuildMoreChecks() {
        //>example<
        LToIntBiFunction<Object, Object> function = LToIntBiFunctionBuilder
                .toIntBiFunction()
                .casesOf(Long.class, null, pcp -> pcp
                        .inCase(test1st(l -> isInIntRange((long) l))).evaluate(apply1stAsInt(Long::intValue))
                        .otherwise((l, o) -> throwThe(new IllegalArgumentException("To large for int."))))
                .aCase(test1st(s -> s instanceof Short), apply1stAsInt(s -> ((Short) s).intValue()))
                .aCase(Number.class, null, apply1stAsInt((Number::intValue)))
                .inCase(test1st(s -> s instanceof String)).evaluate(apply1stAsInt(s -> Integer.parseInt((String) s)))
                .otherwise(apply1stAsInt(s -> throwThe(new IllegalArgumentException())))
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

        THEN.attestToIntBiFunc(function)
            .doesApplyAsInt(0, null).to(a -> a.isEqualTo(0))
            .doesApplyAsInt(5, null).to(a -> a.isEqualTo(5))
            .doesApplyAsInt(44f, null).to(a -> a.isEqualTo(44))
            .doesApplyAsInt(3_000L, null).toEqualTo(3000)
            .doesApplyAsInt(3_000_000_000L, null).withException(a -> a.isInstanceOf(IllegalArgumentException.class).hasMessage("To large for int."))
            .doesApplyAsInt("-4", null).to(a -> a.isEqualTo(-4))
            .doesApplyAsInt("non number", null).withException(a -> a.isInstanceOf(NumberFormatException.class))
            .doesApplyAsInt(new Object(), null).withException(a -> a.isInstanceOf(IllegalArgumentException.class));
    }

/// > Just in case you wonder `test1st` is static method in <a href="https://github.com/lunisolar/magma/blob/master/magma-func/src/main/java/eu/lunisolar/magma/func/predicate/LBiPredicate.java" target="_blank">LBiPredicate</a>

    private static boolean isLongInIntRange(Object o) {
        return o instanceof Long && isInIntRange((long) o);
    }

    private static boolean isInIntRange(long o) {
        return ((long) Integer.MAX_VALUE) >= o && o >= ((long) Integer.MIN_VALUE);
    }

    /**
     * ### Examples with Generics and type arguments.
     *
     * Previous examples actually avoid one ot the issues a Java compiler might have (all the generic types are declared as Object). There is a limitation to
     * what Java compiler can guess.  This example would not actually compile without repeating the argument type `<T>`:
     */
    //>example<
    @Test
    public <T> void testGeneric1() {

        LToIntFunction<T> function = LToIntFunctionBuilder.<T>toIntFunction()
                .inCase(String.class::isInstance).evaluate(o -> Integer.parseInt((String) o))
                .otherwise(o -> throwThe(new IllegalArgumentException()))
                .build();
    }
    //>example<

    /**
     * That is not the issue if there is only one generic argument and if it has such short name. But this example is obviously better way to do it:
     */
    //>example<
    @Test
    public <T, V> void testGeneric2() {
        LToIntTriFunction<T, String, V> function = LToIntTriFunctionBuilder.toIntTriFunctionFrom(b -> b
                .inCase(LTriPredicate.test2nd(String.class::isInstance)).evaluate((t, s, v) -> Integer.parseInt(s))
                .otherwise((t, s, v) -> throwThe(new IllegalArgumentException()))
        );
    }
    //>example<

    private static AtomicBoolean GLOBAL_STATE = new AtomicBoolean();

    /**
     * ### Function builder for JRE interfaces.
     *
     * Just in case it would matter there are builders that just build function implementing only JRE interface. Even for Runnable. Premise behind the
     * builder for a function that does not take arguments is that it might depend on the external state.
     */
    //>example<
    @Test
    public void testBuildPrimitive() {

        Runnable function = RunnableBuilder.actionFrom(b -> b
                .aCase(() -> GLOBAL_STATE.get(), () -> GLOBAL_STATE.set(false))
        );

        assertThat(function)
                .isInstanceOf(Runnable.class)
                .isNotInstanceOf(LAction.class);

    }
    //>example<

    //>inject<:generated

}
