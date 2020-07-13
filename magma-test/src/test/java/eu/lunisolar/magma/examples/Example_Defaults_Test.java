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

import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.consumer.primitives.LIntConsumer;
import eu.lunisolar.magma.func.consumer.primitives.LLongConsumer;
import eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumer;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.operator.binary.LBinaryOperator;
import eu.lunisolar.magma.func.operator.binary.LIntBinaryOperator;
import eu.lunisolar.magma.func.operator.unary.LLongUnaryOperator;
import eu.lunisolar.magma.func.operator.unary.LUnaryOperator;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supplier.LLongSupplier;
import eu.lunisolar.magma.func.supplier.LSupplier;
import eu.lunisolar.magma.func.tuple.LIntPair;
import org.testng.annotations.Test;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static eu.lunisolar.magma.asserts.Attests.*;
import static eu.lunisolar.magma.asserts.TestFlow.test;
import static eu.lunisolar.magma.asserts.func.operator.binary.LBinaryOperatorAssert.attestBinaryOp;
import static eu.lunisolar.magma.asserts.func.supplier.LSupplierAssert.attestSup;
import static eu.lunisolar.magma.func.function.LFunction.func;
import static eu.lunisolar.magma.func.operator.unary.LUnaryOperator.unaryOp;
import static eu.lunisolar.magma.func.supp.check.Checks.attest;

//>transform-to-MD<
/**
 * Basic introduction (by example) to default and static methods available in functional interfaces in this library.
 */
//>inject<:readmore

//>inject<:generated

/**
 * Default and static methods
 * ==========================
 *
 * ###  Abstract
 *
 * Basic introduction (by example) to default and static methods available in functional interfaces in this library.
 */
public class Example_Defaults_Test {

///### General comments
///
/// + Not all functional interfaces have exactly the same default and static methods.
///   + a matter of difference of interface type (e.g. consumer vs predicate),
///   + a matter of availability of other specialised interfaces (especially with growing number of arguments).
/// + In examples that follow following other features of the library are used:
///   + [assertions for functional interfaces](http://lunisolar.eu/magma/assertions)
///   + [fluent validations for objects](http://lunisolar.eu/magma/validations-fluent)
///

    /**
     * ### The LFunction.apply() is actually a default method.
     *
     * Because library interfaces inherit from JRE interfaces, and in addition to that library interfaces must handle checked exception, all the methods
     * 'accept', 'get', 'apply', and 'test' are already implemented and the actual methods to implement are having almost the same name but only with additional
     * suffix 'X". This suffix comes from the fact that the method is allowed to throw other than runtime exceptions.
     *
     * Example:
     */
    //>example<
    @Test
    public void defaultX() {

        LFunction<String, String> f1 = new LFunction<String, String>() {
            @Override public String applyX(String a) throws Throwable {
                return a;
            }
        };

    }
    //>example<

    /**
     * ### Static wrapper
     *
     * Obviously, you are not required to use those static wrapper methods, they only exists to help in certain circumstances:
     *
     * + specialize interface (either because of hidden type requirement, or to actually help compiler to guess the interface)
     * + exception handling is needed for standard JRE functional interface (and tidy code is welcomed)
     *
     * Example:
     */
    //>example<
    @Test
    public void staticWrappers() {

        LFunction<String, String> f1 = unaryOp(s -> s); // specialize actual implementation of interface

        Stream.of(new File("")).map(
                func(File::getCanonicalPath) // wrap lambda to handle exceptions in places where JRE functional interface is used
        );
    }
    //>example<

    /**
     * Another static wrapper is for JRE functions. Every interface in this library has this method if it inherits from actual JRE interface.
     */
    //>example<
    @Test
    public void wrapJre() {
        Function<String, String> f1 = s-> s;
        LFunction<String, String> f2 = LFunction.wrap(f1);
    }
    //>example<

    //>inject<:generated

    /**
     * ### Tuple call
     *
     * Since there is a tuple interface representing all domains of all functions (mentioned in [Overview](http://lunisolar.eu/magma/overview)), it is natural
     * that there is a default method dedicated to call function with such tuple.
     *
     * Example:
     */
    //>example<
    @Test
    public void tupleCall() {
        LIntBinaryOperator f1 = Math::addExact;

        int actual = f1.tupleApplyAsInt(LIntPair.of(2, 5));

        attest(actual).mustEx(Be::equalEx, 7);
    }
    //>example<

    /**
     * ### Multiple handling possibilities for exceptions.
     *
     * Example: TODO move to {@link Example_ExceptionHandling_Test}
     */
    //>example<
    @Test
    public void handlingCalls() {
        LBinaryOperator<Integer> badFunction = Math::addExact;

        attestBinaryOp(badFunction)
                .doesApply(2, 5).asEqualTo(7)
                .doesApply(null, 7).withException(e -> e.isInstanceOf(NullPointerException.class));

    }
    //>example<

    /**
     * ### Non null result of a function.
     *
     * Default method -nonNull*()- (e.g. -nonNullApply-), that execute call to a function with validation for non-null result.
     * Default method -nonNullable()- that returns implementation of a function that does that on every call.
     *
     * Example:
     */
    //>example<
    @Test
    public void nonNullApply() {
        LUnaryOperator<Integer> badFunction = LUnaryOperator.identity();

        attestUnaryOp(badFunction)
                .doesApply(2).asEqualTo(2)
                .doesApply(null).asEqualTo(null);

        attestThatThrownBy(
                () -> badFunction.nonNullApply(null)
        ).mustEx(Be::instanceOfEx, RuntimeException.class)
         .mustEx(P.haveEx(Throwable::getMessage, P::containEx, "Evaluated value by nonNullApply() method cannot be null"));

        attestUnaryOp(badFunction.nonNullable())
                .doesApply(2).asEqualTo(2)
                .doesApply(null)
                .withException(e -> e
                        .isInstanceOf(NullPointerException.class)
                        .hasMessageContaining("Evaluated value by nonNullApply() method cannot be null")
                );
    }
    //>example<

    /**
     * ### fromTo, fromTill, times
     *
     * Best to present example:
     */
    //>example<
    @Test
    public void fromTo() {
        LIntConsumer.fromTo(1, 10, x -> System.out.println(x));
        LIntConsumer.fromTill(1, 10, System.out::println);

        LConsumer.fromTo(1, 10, System.out, o -> o.println("tick"));

        LObjIntConsumer.fromTo(1, 10, System.out, (printStream, x) -> printStream.println(x));
        LObjIntConsumer.fromTo(1, 10, System.out, PrintStream::println);

        LObjIntConsumer.times(10, System.out, (o, i) -> o.println(i));
        LObjIntConsumer.times(10, System.out, PrintStream::println);
    }
    //>example<

    /**
     * ### untyped(), cast()
     *
     * Both methods have the same purpose - sometimes it is faster and actually more readable to make compiler just stop complaining,
     * than fully declare the whole set of generic parameters.
     *
     * It is not recommended to use those methods freely.
     */
    //>example<
    @Test
    public void untyped() {
        List<LConsumer<Integer>> list = new ArrayList<>();
        LConsumer<?> f1 = LConsumer::doNothing;

        list.add(f1.untyped());                 // untyped changes compilation error to warning
        list.add(f1.cast());                    // cast make sure there are no complains
        list.add(LConsumer.<Integer>cast(f1));  // static version of the cast()

    }
    //>example<

    /**
     * ### beforeDo(), afterDo()
     *
     * Both methods allow to 'visit' actual values of either domain or codomain on each call of newly created function.
     */
    //>example<
    @Test
    public void beforeAfter() {

        test().given(() -> new Object() {
            StringBuilder sb = new StringBuilder();
            LBinaryOperator<Integer> func = Math::addExact;
        }).precondition(state -> {
            // the assignment is required
            state.func = state.func
                    .beforeDo((i1, i2) -> state.sb.append(i1).append("+").append(i2))
                    .afterDo(r -> state.sb.append("=").append(r));
        }).when(state -> {
            state.func.apply(3, 4);
        }).then(state -> {
            attest(state.sb.toString()).mustEx(Be::equalEx, "3+4=7");
        });

    }
    //>example<

    /**
     * ### capture
     *
     * 'Capture' the fixed arguments and returns the supplier that returns the result of a call to original function. The function is called each time.
     */
    //>example<
    @Test
    public void capture() {

        LUnaryOperator<String> f1 = s -> s;
        LSupplier<String> f2 = f1.capture("345");

        attestSup(f2).doesGet().asEqualTo("345");
    }
    //>example<

    /**
     * ### recursive()
     *
     * In cases where needed, recursive method allows to define recursive implementation of a function (by declaring higher order function).
     */
    //>example<
    @Test
    public void recursive() {
        StringBuilder sb = new StringBuilder();

        LLongUnaryOperator factorial = LLongUnaryOperator.recursive(f -> n -> (n <= 1) ? 1 : n * f.applyAsLong(n - 1L));

        attestLongUnaryOp(factorial)
                .doesApplyAsLong(0).asEqualTo(1L)
                .doesApplyAsLong(1).asEqualTo(1L)
                .doesApplyAsLong(2).asEqualTo(2L)
                .doesApplyAsLong(3).asEqualTo(6L);

    }
    //>example<

    /**
     * ### memento(), deltaOf()
     *
     * Within this library, 'Memento' is a derivative function, that keeps state. Specifically two values: last value returned by original function and last
     * value returned by derivative function. This allows for creating functions that calculate for example: delta or sum.
     *
     * The limitations/complications are obvious:
     * - it is inherently not threadsafe
     * - it keeps references to objects (if codomain is object)
     */
    //>example<
    @Test
    public void memento() {
        LLongUnaryOperator originalFunction = i -> i; // lets keep it simple

        LLongUnaryOperator.M sum = LLongUnaryOperator.memento(0, 0, originalFunction, (lastR_bis, lastR, r) -> lastR_bis + r);
        LLongUnaryOperator.M delta = LLongUnaryOperator.memento(0, 0, originalFunction, (lastR_bis, lastR, r) -> r - lastR);

        attestLongUnaryOp(sum)
                .doesApplyAsLong(0).asEqualTo(0L)
                .doesApplyAsLong(2).asEqualTo(2L)
                .doesApplyAsLong(3).asEqualTo(5L)
                .doesApplyAsLong(50).asEqualTo(55L);

        attestLongUnaryOp(delta)
                .doesApplyAsLong(0).asEqualTo(0L)
                .doesApplyAsLong(2).asEqualTo(2L)
                .doesApplyAsLong(3).asEqualTo(1L)
                .doesApplyAsLong(50).asEqualTo(47L)
                .doesApplyAsLong(40).asEqualTo(-10L);

    }
    //>example<

    /**
     *  An example that actually could be used more frequently is:
     */
    //>example<
    @Test
    public void timeDelta() {
        LLongSupplier.M timeDelta = LLongSupplier.deltaOf(System::currentTimeMillis);

        LLongConsumer.tryAccept(200, Thread::sleep);

        long elapsedTime = timeDelta.getAsLong();

        attest(elapsedTime).must(Be::inRange, 150, 500, "Elapsed time must be between 150 and 500 ms !"); // sleep() CPU scheduling is not precise
    }
    //>example<

    //>inject<:generated
}

