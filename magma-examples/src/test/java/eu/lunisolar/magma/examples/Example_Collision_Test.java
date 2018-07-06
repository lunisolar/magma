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
//
//    public static class MegaFunction<T, X extends Exception> implements LFunction<T, T>, LSupplier<T>, LPredicate<T>, LUnaryOperator<T>, LConsumer<T> {
//
//        @Override public void doAccept(T t) {
//
//        }
//
//        @Nullable @Override public T doApply(T t) {
//            return null;
//        }
//
//        @Override public boolean doTest(T t) {
//            return false;
//        }
//
//        @Nullable @Override public T doGet() {
//            return null;
//        }
//
//        @Nonnull @Override public String functionalInterfaceDescription() {
//
//            return "";
//        }
//    }
//
//
//    public static class MegaIntFunction<T, X extends Exception> implements LIntFunction<T>, LIntSupplier, LIntPredicate, LIntUnaryOperator, LIntConsumer {
//
//        @Override public void doAccept(int i) {
//
//        }
//
//        @Nullable @Override public T doApply(int i) {
//            return null;
//        }
//
//        @Override public boolean doTest(int i) {
//            return false;
//        }
//
//        @Override public int doGetAsInt() {
//            return 0;
//        }
//
//        @Override public int doApplyAsInt(int i) {
//            return 0;
//        }
//
//        @Nonnull @Override public String functionalInterfaceDescription() {
//
//            return "";
//        }
//    }

    public static class MegaSupplier<T> implements LBoolSupplier, LByteSupplier, LSrtSupplier, LIntSupplier, LLongSupplier, LFltSupplier, LDblSupplier, LCharSupplier, LSupplier<T> {

        @Override public boolean doGetAsBoolX() {
            return false;
        }

        @Override public byte doGetAsByteX() {
            return 0;
        }

        @Override public char doGetAsCharX() {
            return 0;
        }

        @Override public double doGetAsDblX() {
            return 0;
        }

        @Override public float doGetAsFltX() {
            return 0;
        }

        @Override public int doGetAsIntX() {
            return 0;
        }

        @Override public long doGetAsLongX() {
            return 0;
        }

        @Override public short doGetAsSrtX() {
            return 0;
        }

        @Nullable @Override public T doGetX() {
            return null;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }
  
    public static class MegaConsumer<T> implements LBoolConsumer, LByteConsumer, LSrtConsumer, LIntConsumer, LLongConsumer, LFltConsumer, LDblConsumer, LCharConsumer, LConsumer<T> {

        @Override public void doAcceptX(boolean b) {

        }

        @Override public void doAcceptX(byte b) {

        }

        @Override public void doAcceptX(char c) {

        }

        @Override public void doAcceptX(T t) {

        }

        @Override public void doAcceptX(double d) {

        }

        @Override public void doAcceptX(float f) {

        }

        @Override public void doAcceptX(int i) {

        }

        @Override public void doAcceptX(long l) {

        }

        @Override public void doAcceptX(short s) {

        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaConsumerX<T, X extends Exception> implements LBoolConsumer, LByteConsumer, LSrtConsumer, LIntConsumer, LLongConsumer, LFltConsumer, LDblConsumer, LCharConsumer, LConsumer<T> {

        @Override public void doAcceptX(boolean b)  {

        }

        @Override public void doAcceptX(byte b)  {

        }

        @Override public void doAcceptX(char c)  {

        }

        @Override public void doAcceptX(T t)  {

        }

        @Override public void doAcceptX(double d)  {

        }

        @Override public void doAcceptX(float f)  {

        }

        @Override public void doAcceptX(int i)  {

        }

        @Override public void doAcceptX(long l)  {

        }

        @Override public void doAcceptX(short s)  {

        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

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

        @Override public StdMegaPredicate<T> negate() {
            return null;
        }
    }

    public static class MegaPredicate<T> implements LLogicalOperator, LBytePredicate, LSrtPredicate, LIntPredicate, LLongPredicate, LFltPredicate, LDblPredicate, LCharPredicate, LPredicate<T> {

        @Override public boolean doApplyX(boolean b) {
            return false;
        }

        @Override public boolean doTestX(byte b)  {
            return false;
        }

        @Override public boolean doTestX(char c)  {
            return false;
        }

        @Override public boolean doTestX(double d)  {
            return false;
        }

        @Override public boolean doTestX(float f)  {
            return false;
        }

        @Override public boolean doTestX(int i)  {
            return false;
        }

        @Override public boolean doTestX(long l)  {
            return false;
        }

        @Override public boolean doTestX(T t)  {
            return false;
        }

        @Override public boolean doTestX(short s)  {
            return false;
        }
      
        @Nonnull @Override public MegaPredicate<T> negate() {
            return null;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaByteConverter<T> implements LByteToSrtFunction, LByteToCharFunction,LByteToIntFunction,LByteToLongFunction,LByteToFltFunction,LByteToDblFunction,LBytePredicate, LByteFunction<T> {

        @Nullable @Override public T doApplyX(byte b)  {
            return null;
        }

        @Override public boolean doTestX(byte b)  {
            return false;
        }

        @Override public char doApplyAsCharX(byte b)  {
            return 0;
        }

        @Override public double doApplyAsDblX(byte b)  {
            return 0;
        }

        @Override public float doApplyAsFltX(byte b)  {
            return 0;
        }

        @Override public int doApplyAsIntX(byte b)  {
            return 0;
        }

        @Override public long doApplyAsLongX(byte b)  {
            return 0;
        }

        @Override public short doApplyAsSrtX(byte b)  {
            return 0;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {
            return null;
        }
    }
}
