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

import eu.lunisolar.magma.basics.exceptions.ExWF;
import eu.lunisolar.magma.basics.exceptions.ExWMF;
import eu.lunisolar.magma.basics.exceptions.HandlingInstructions;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.consumer.primitives.*;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.conversion.*;
import eu.lunisolar.magma.func.function.from.LByteFunction;
import eu.lunisolar.magma.func.function.from.LIntFunction;
import eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator;
import eu.lunisolar.magma.func.operator.unary.LLogicalOperator;
import eu.lunisolar.magma.func.operator.unary.LUnaryOperator;
import eu.lunisolar.magma.func.predicate.*;
import eu.lunisolar.magma.func.supplier.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.*;

/**
 * Following cases must compile.
 */
public class Example_Collision_Test {

    public static class StdFrankenstein<T> implements Function<T, T>, Supplier<T>, Predicate<T>, UnaryOperator<T>, Consumer<T> {

        @Override public T apply(T t) {
            return null;
        }

        @Override public boolean test(T t) {
            return false;
        }

        @Override public T get() {
            return null;
        }

        @Override public void accept(T t) {

        }
    }

    public static class StdIntFrankenstein<T> implements IntFunction<T>, IntSupplier, IntPredicate, IntUnaryOperator, IntConsumer {

        @Override public void accept(int value) {

        }

        @Override public T apply(int value) {
            return null;
        }

        @Override public boolean test(int value) {
            return false;
        }

        @Override public int getAsInt() {
            return 0;
        }

        @Override public int applyAsInt(int operand) {
            return 0;
        }
    }

//    public static class MegaFunction<T, X extends Exception> implements LFunction<T, T>, LSupplier<T>, LPredicate<T>, LUnaryOperator<T>, LConsumer<T> {
//
//        @Override public void acceptX(T a) throws Throwable {
//
//        }
//        @Override public T applyX(T a) throws Throwable {
//            return null;
//        }
//        @Override public boolean testX(T a) throws Throwable {
//            return false;
//        }
//        @Override public T getX() throws Throwable {
//            return null;
//        }
//    }


//    public static class MegaIntFunction<T> implements LIntFunction<T>, LIntSupplier, LIntPredicate, LIntUnaryOperator, LIntConsumer {
//
//        @Override public void acceptX(int a) throws Throwable {
//
//        }
//        @Override public T applyX(int a) throws Throwable {
//            return null;
//        }
//        @Override public int applyAsIntX(int a) throws Throwable {
//            return 0;
//        }
//        @Override public boolean testX(int a) throws Throwable {
//            return false;
//        }
//        @Override public int getAsIntX() throws Throwable {
//            return 0;
//        }
//    }

//    public static class MegaSupplier<T> implements LBoolSupplier, LByteSupplier, LSrtSupplier, LIntSupplier, LLongSupplier, LFltSupplier, LDblSupplier, LCharSupplier, LSupplier<T> {
//
//        @Override public boolean getAsBoolX() throws Throwable {
//            return false;
//        }
//        @Override public byte getAsByteX() throws Throwable {
//            return 0;
//        }
//        @Override public char getAsCharX() throws Throwable {
//            return 0;
//        }
//        @Override public double getAsDblX() throws Throwable {
//            return 0;
//        }
//        @Override public float getAsFltX() throws Throwable {
//            return 0;
//        }
//        @Override public int getAsIntX() throws Throwable {
//            return 0;
//        }
//        @Override public long getAsLongX() throws Throwable {
//            return 0;
//        }
//        @Override public short getAsSrtX() throws Throwable {
//            return 0;
//        }
//        @Override public T getX() throws Throwable {
//            return null;
//        }
//    }

//    public static class MegaConsumer<T> implements LBoolConsumer, LByteConsumer, LSrtConsumer, LIntConsumer, LLongConsumer, LFltConsumer, LDblConsumer, LCharConsumer, LConsumer<T> {
//
//        @Override public void acceptX(T a) throws Throwable {
//
//        }
//        @Override public void acceptX(boolean a) throws Throwable {
//
//        }
//        @Override public void acceptX(byte a) throws Throwable {
//
//        }
//        @Override public void acceptX(char a) throws Throwable {
//
//        }
//        @Override public void acceptX(double a) throws Throwable {
//
//        }
//        @Override public void acceptX(float a) throws Throwable {
//
//        }
//        @Override public void acceptX(int a) throws Throwable {
//
//        }
//        @Override public void acceptX(long a) throws Throwable {
//
//        }
//        @Override public void acceptX(short a) throws Throwable {
//
//        }
//    }

//    public static class MegaConsumerX<T, X extends Exception> implements LBoolConsumer, LByteConsumer, LSrtConsumer, LIntConsumer, LLongConsumer, LFltConsumer, LDblConsumer, LCharConsumer, LConsumer<T> {
//
//        @Override public void acceptX(T a) throws Throwable {
//
//        }
//        @Override public void acceptX(boolean a) throws Throwable {
//
//        }
//        @Override public void acceptX(byte a) throws Throwable {
//
//        }
//        @Override public void acceptX(char a) throws Throwable {
//
//        }
//        @Override public void acceptX(double a) throws Throwable {
//
//        }
//        @Override public void acceptX(float a) throws Throwable {
//
//        }
//        @Override public void acceptX(int a) throws Throwable {
//
//        }
//        @Override public void acceptX(long a) throws Throwable {
//
//        }
//        @Override public void acceptX(short a) throws Throwable {
//
//        }
//    }

    public static class StdMegaPredicate<T> implements Predicate<T>, IntPredicate, LongPredicate, DoublePredicate {
        @Override public boolean test(double value) {
            return false;
        }
        @Override public boolean test(int value) {
            return false;
        }
        @Override public boolean test(long value) {
            return false;
        }
        @Override public boolean test(T t) {
            return false;
        }

        @Nonnull @Override public StdMegaPredicate<T> negate() {
            return new StdMegaPredicate<>();
        }
    }

//    public static class MegaPredicate<T> implements LLogicalOperator, LBytePredicate, LSrtPredicate, LIntPredicate, LLongPredicate, LFltPredicate, LDblPredicate, LCharPredicate, LPredicate<T> {
//
//        @Override public boolean applyX(boolean a) throws Throwable {
//            return false;
//        }
//        @Override public boolean testX(byte a) throws Throwable {
//            return false;
//        }
//        @Override public boolean testX(char a) throws Throwable {
//            return false;
//        }
//        @Override public boolean testX(double a) throws Throwable {
//            return false;
//        }
//        @Override public boolean testX(float a) throws Throwable {
//            return false;
//        }
//        @Override public boolean testX(int a) throws Throwable {
//            return false;
//        }
//        @Override public boolean testX(long a) throws Throwable {
//            return false;
//        }
//        @Override public boolean testX(T a) throws Throwable {
//            return false;
//        }
//        @Override public boolean testX(short a) throws Throwable {
//            return false;
//        }
//    }

//    public static class MegaByteConverter<T> implements LByteToSrtFunction, LByteToCharFunction, LByteToIntFunction, LByteToLongFunction, LByteToFltFunction, LByteToDblFunction, LBytePredicate, LByteFunction<T> {
//
//        @Override public char applyAsCharX(byte a) throws Throwable {
//            return 0;
//        }
//        @Override public double applyAsDblX(byte a) throws Throwable {
//            return 0;
//        }
//        @Override public float applyAsFltX(byte a) throws Throwable {
//            return 0;
//        }
//        @Override public int applyAsIntX(byte a) throws Throwable {
//            return 0;
//        }
//        @Override public long applyAsLongX(byte a) throws Throwable {
//            return 0;
//        }
//        @Override public short applyAsSrtX(byte a) throws Throwable {
//            return 0;
//        }
//        @Override public T applyX(byte a) throws Throwable {
//            return null;
//        }
//        @Override public boolean testX(byte a) throws Throwable {
//            return false;
//        }
//    }
}
