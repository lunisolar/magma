/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

import eu.lunisolar.magma.func.function.conversion.LIntToFltFunction;
import eu.lunisolar.magma.func.supp.Be;
import org.testng.annotations.Test;

import static eu.lunisolar.magma.func.supp.check.Checks.attest;

//>transform-to-MD<
/**
 * Basic introduction (by example) to details of main features:
 * **More primitive types supported**. **More combinations of arguments** .
 */
//>inject<:readmore

//>inject<:generated

/**
 * More primitive types and combinations
 * ==========================
 *
 * ###  Abstract
 *
 * Basic introduction (by example) to details of main features:
 * **More primitive types supported**. **More combinations of arguments** .
 */
public class Example_Goal2And3_Test {

///
///### More primitive types.
///
/// The issue is simple. Functional interface library from JRE contains functional interfaces required by JRE itself.
/// While in most of circumstances what JRE offers is enough, there are obvious and not so obvious limitations:
///
/// + sometimes you need more arguments
/// + sometimes you want use more primitives
/// + sometimes you want all interfaces to have some default and static methods - Function(3) vs ToIntBiFunction (0)
///
///
/// > No silver bullet (note about performance)
/// >
/// > Do not take this library as a universal tool that improves performance (e.g. by creating more possibilities to avoid capturing lambdas or to avoid boxing/unboxing).
/// >
/// > First, there is a lot of cases that actually Java compiler, JIT and GC are already doing a lot of optimizations for you.
/// >
/// > Second, diminishing returns might actually mean that any gain you will have is marginal at best.
///
///Basically this library supports:
///
///+ More of functional interfaces
///   + More primitive types are supported
///   + More combinations of arguments.
///   + further documentation:
///      + [action](http://lunisolar.eu/magma/all-functions/actions)
///      + [suppliers](http://lunisolar.eu/magma/all-functions/suppliers)
///      + [consumers](http://lunisolar.eu/magma/all-functions/consumers)
///      + [predicates](http://lunisolar.eu/magma/all-functions/predicates)
///      + [operators](http://lunisolar.eu/magma/all-functions/operators)
///      + [ordinary functions](http://lunisolar.eu/magma/all-functions/functions)
///      + [all interfaces](http://lunisolar.eu/magma/all-functions)
///+ Thus, reducing number of cases where:
///   + You cannot directly reference method
///   + JVM cannot optimize code better (although nothing is guaranteed)
///+ Default and static utility methods (applicable to the case and availability of other interfaces).
///  [read more](http://lunisolar.eu/magma/defaults)
///
///

    /**
     * Just short example:
     */
    //>example<
    private static String typeToString(Float f) {
        return f.getClass().getSimpleName();
    }

    private static String typeToString(float f) {
        return "float primitive";
    }

    @Test
    public void someExample() {
        LIntToFltFunction toFloat = i -> (float) i;

        attest(typeToString(toFloat.applyAsFlt(10)))
                .must$(Be::equal$, "float primitive");
    }
    //>example<

    //>inject<:generated
}

