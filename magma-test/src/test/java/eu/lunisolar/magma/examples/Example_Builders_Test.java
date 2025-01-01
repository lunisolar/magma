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

package eu.lunisolar.magma.examples;

import eu.lunisolar.magma.func.action.LAction;
import eu.lunisolar.magma.func.build.action.LActionBuilder;
import eu.lunisolar.magma.func.build.function.to.LToIntBiFunctionBuilder;
import eu.lunisolar.magma.func.build.function.to.LToIntFunctionBuilder;
import eu.lunisolar.magma.func.build.function.to.LToIntTriFunctionBuilder;
import eu.lunisolar.magma.func.function.to.LToIntBiFunction;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.function.to.LToIntTriFunction;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.Have;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static eu.lunisolar.magma.asserts.func.FuncAttests.attestToIntBiFunc;
import static eu.lunisolar.magma.asserts.func.FuncAttests.attestToIntFunc;
import static eu.lunisolar.magma.basics.exceptions.Handling.throwThe;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;

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
                .forClass(Number.class, Number::intValue)
                .inCase(String.class::isInstance).evaluate(o -> Integer.parseInt((String) o))
                .otherwise(o -> throwThe(new IllegalArgumentException()))
                .build();

        attestToIntFunc(function)
                .doesApplyAsInt(0).toEqualTo(0)
                .doesApplyAsInt(5).toEqualTo(5)
                .doesApplyAsInt(44f).toEqualTo(44)
                .doesApplyAsInt(3_000_000_000L).toEqualTo(-1294967296)
                .doesApplyAsInt("-4").toEqualTo(-4)
                .doesApplyAsInt("non number").withException(a -> a.mustEx(Be::exactlyInstanceOfEx, NumberFormatException.class))
                .doesApplyAsInt(new Object()).withException(a -> a.mustEx(Be::exactlyInstanceOfEx, IllegalArgumentException.class));
    }
    //>example<

    @Test
    public void testBuildMoreChecks() {
        //>example<
        LToIntBiFunction<Object, Object> function = LToIntBiFunctionBuilder
                .toIntBiFunction()
                .forValue(100, 200, (_1, _2) -> 999)
                .forValue(101, 200).produce(997)
                .forClass(Short.class, null, (_1, _2) -> 998)
                .forClass(Character.class, null).produce(44)
                .casesOf(Long.class, null, pcp -> pcp
                        .inCase((l, a2) -> isInIntRange((long) l)).evaluate((a1, a2) -> a1.intValue())
                        .otherwise((l, o) -> throwThe(new IllegalArgumentException("To large for int."))))
                .aCase((s, a2) -> s instanceof Short, (s, a2) -> ((Short) s).intValue())
                .forClass(Number.class, null, (a1, a2) -> a1.intValue())
                .inCase((s, a2) -> s instanceof String).evaluate((s, a2) -> Integer.parseInt((String) s))
                .otherwise((s, a2) -> throwThe(new IllegalArgumentException()))
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

        attestToIntBiFunc(function)
                .doesApplyAsInt(0, null)
                .toEqualTo(0)
                .doesApplyAsInt(5, null)
                .toEqualTo(5)
                .doesApplyAsInt(44f, null)
                .toEqualTo(44)
                .doesApplyAsInt(3_000L, null)
                .toEqualTo(3000)
                .doesApplyAsInt(100, 200)
                .toEqualTo(999)
                .doesApplyAsInt((short)100, 200)
                .toEqualTo(998)
                .doesApplyAsInt(101, 200)
                .toEqualTo(997)
                .doesApplyAsInt(3_000_000_000L, null)
                .withException(a -> a.mustEx(Be::instanceOfEx, IllegalArgumentException.class).mustEx(Have::msgEqualEx, "To large for int."))
                .doesApplyAsInt("-4", null)
                .toEqualTo(-4)
                .doesApplyAsInt("non number", null)
                .withException(a -> a.mustEx(Be::instanceOfEx, NumberFormatException.class))
                .doesApplyAsInt(new Object(), null)
                .withException(a -> a.mustEx(Be::instanceOfEx, IllegalArgumentException.class));
    }

/// > Just in case you wonder `test1st` is static method in <a href="https://github.com/lunisolar/magma/blob/master/magma-func/src/main/java/eu/lunisolar/magma/func/predicate/LBiPredicate.java" target="_blank">LBiPredicate</a>

    private static boolean isLongInIntRange(Object o) {
        return o instanceof Long && isInIntRange((long) o);
    }

    private static boolean isInIntRange(long o) {
        return ((long) Integer.MAX_VALUE) >= o && o >= ((long) Integer.MIN_VALUE);
    }

    ///
/// ### Examples with Generics and type arguments.
/// 
/// Previous examples actually avoid one ot the issues a Java compiler might have (all the generic types are compiled as Object). There is a limitation to
/// what Java compiler can guess.  This example would not actually compile without repeating the argument type `<T>`:
/// 
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
     * That is not the issue if there is only one generic argument and if it has such short name. But here is an example with a better way to do it:
     */
    //>example<
    @Test
    public <T, V> void testGeneric2() {
        LToIntTriFunction<T, Object, V> function = LToIntTriFunctionBuilder.toIntTriFunctionFrom(b -> b
                .inCase((a1, s, a3) -> s instanceof String).evaluate((t, s, v) -> Integer.parseInt((String) s))
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

        Runnable function = LActionBuilder.actionFrom(b -> b
                .aCase(() -> GLOBAL_STATE.get(), () -> GLOBAL_STATE.set(false))
        );

        attest(function).mustEx(Be::instanceOfEx, Runnable.class)
                        .mustEx(Be::instanceOfEx, LAction.class);

    }
    //>example<

    //>inject<:generated

}
