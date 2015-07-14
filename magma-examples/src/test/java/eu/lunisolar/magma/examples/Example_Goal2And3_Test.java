/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.basics.exceptions.NestedException;
import eu.lunisolar.magma.examples.support.CheckedException;
import eu.lunisolar.magma.func.function.conversion.LIntToFloatFunction;
import eu.lunisolar.magma.func.predicate.LPredicateX;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

//>transform-to-MD<
/**
 * Basic explanations (by example) how second and third of main goals where realised -
 * **More primitive types supported**. **More combinations of arguments**.
 */
//>inject<:readmore

/**
 * More primitive types and combinations
 * ==========================
 *
 * ###  Abstract
 *
 * Basic explanations (by example) how second and third of main goals where realised -
 * **More primitive types supported**. **More combinations of arguments**.
 */
public class Example_Goal2And3_Test {

    private static final List<Integer> integerList = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    /**
     * ###  More primitive types.
     *
     * The issue is simple. Functional interface library from JRE contains functional interfaces required by JRE itself. As a result it do not cover all
     * primitive types. There might be places that you would like to use those primitive types. So for example you would need to write your own FloatFunction
     * interface. And that would not be a problem, unless in next you would not need FloatPredicate, then FloatConsumer. It is still no
     * problem but then you realise that, The original Function or Predicate has some default functions that others are missing.
     *
     * Then second dimension to the problem arises when you need something like this: FloatToIntFunction, IntToFloatFunction, ObjFloatFunction,
     * BiFloatPredicate.
     *
     * If you consider all default and static methods that you eventually might want to have in each of them, then to put it simple it is separate project from
     * the one you want use those interfaces in.
     *
     * The list of the all interfaces is split into the separate articles.
     */
    //>example<
    private static String typeToString(Float f) {
        return f.getClass().getSimpleName();
    }

    private static String typeToString(float f) {
        return "float primitive";
    }

    @Test
    public void problemToSolve() {
        LIntToFloatFunction toFloat = i -> (float) i;

        assertThat(typeToString(toFloat.doApplyAsFloat(10)))
                .isEqualTo("float primitive");
    }
    //>example<

    //>inject<:generated
}

