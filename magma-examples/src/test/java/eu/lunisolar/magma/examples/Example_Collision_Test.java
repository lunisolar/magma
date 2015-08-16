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

import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.consumer.LConsumerX;
import eu.lunisolar.magma.func.consumer.primitives.*;
import eu.lunisolar.magma.func.function.LFunction;
import eu.lunisolar.magma.func.function.LFunctionX;
import eu.lunisolar.magma.func.function.conversion.*;
import eu.lunisolar.magma.func.function.from.LByteFunction;
import eu.lunisolar.magma.func.function.from.LByteFunctionX;
import eu.lunisolar.magma.func.function.from.LIntFunction;
import eu.lunisolar.magma.func.function.from.LIntFunctionX;
import eu.lunisolar.magma.func.operator.unary.*;
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

    public static class MegaFunction<T, X extends Exception> implements LFunction<T, T>, LSupplier<T>, LPredicate<T>, LUnaryOperator<T>, LConsumer<T> {

        @Override public void doAccept(T t) {

        }

        @Nullable @Override public T doApply(T t) {
            return null;
        }

        @Override public boolean doTest(T t) {
            return false;
        }

        @Nullable @Override public T doGet() {
            return null;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaFunctionX<T, X extends Exception> implements LFunctionX<T, T, X>, LSupplierX<T, X>, LPredicateX<T, X>, LUnaryOperatorX<T, X>, LConsumerX<T, X> {

        @Nullable @Override public T doApply(T t) throws X {
            return null;
        }

        @Override public void doAccept(T t) throws X {

        }

        @Override public boolean doTest(T t) throws X {
            return false;
        }

        @Nullable @Override public T doGet() throws X {
            return null;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }


    public static class MegaIntFunction<T, X extends Exception> implements LIntFunction<T>, LIntSupplier, LIntPredicate, LIntUnaryOperator, LIntConsumer {

        @Override public void doAccept(int i) {

        }

        @Nullable @Override public T doApply(int i) {
            return null;
        }

        @Override public boolean doTest(int i) {
            return false;
        }

        @Override public int doGetAsInt() {
            return 0;
        }

        @Override public int doApplyAsInt(int i) {
            return 0;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaIntFunctionX<T, X extends Exception> implements LIntFunctionX<T, X>, LIntSupplierX<X>, LIntPredicateX<X>, LIntUnaryOperatorX<X>, LIntConsumerX<X> {

        @Override public void doAccept(int i) throws X {

        }

        @Nullable @Override public T doApply(int i) throws X {
            return null;
        }

        @Override public boolean doTest(int i) throws X {
            return false;
        }

        @Override public int doGetAsInt() throws X {
            return 0;
        }

        @Override public int doApplyAsInt(int i) throws X {
            return 0;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaSupplier<T> implements LBoolSupplier, LByteSupplier, LShortSupplier, LIntSupplier, LLongSupplier, LFloatSupplier, LDoubleSupplier, LCharSupplier, LSupplier<T> {

        @Override public boolean doGetAsBool() {
            return false;
        }

        @Override public byte doGetAsByte() {
            return 0;
        }

        @Override public char doGetAsChar() {
            return 0;
        }

        @Override public double doGetAsDouble() {
            return 0;
        }

        @Override public float doGetAsFloat() {
            return 0;
        }

        @Override public int doGetAsInt() {
            return 0;
        }

        @Override public long doGetAsLong() {
            return 0;
        }

        @Override public short doGetAsShort() {
            return 0;
        }

        @Nullable @Override public T doGet() {
            return null;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaSupplierX<T, X extends Exception> implements LBoolSupplierX<X>, LByteSupplierX<X>, LShortSupplierX<X>, LIntSupplierX<X>, LLongSupplierX<X>, LFloatSupplierX<X>, LDoubleSupplierX<X>, LCharSupplierX<X>, LSupplierX<T, X> {

        @Override public boolean doGetAsBool() throws X {
            return false;
        }

        @Override public byte doGetAsByte() throws X {
            return 0;
        }

        @Override public char doGetAsChar() throws X {
            return 0;
        }

        @Override public double doGetAsDouble() throws X {
            return 0;
        }

        @Override public float doGetAsFloat() throws X {
            return 0;
        }

        @Override public int doGetAsInt() throws X {
            return 0;
        }

        @Override public long doGetAsLong() throws X {
            return 0;
        }

        @Override public short doGetAsShort() throws X {
            return 0;
        }

        @Nullable @Override public T doGet() throws X {
            return null;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaConsumer<T> implements LBoolConsumer, LByteConsumer, LShortConsumer, LIntConsumer, LLongConsumer, LFloatConsumer, LDoubleConsumer, LCharConsumer, LConsumer<T> {

        @Override public void doAccept(boolean b) {

        }

        @Override public void doAccept(byte b) {

        }

        @Override public void doAccept(char c) {

        }

        @Override public void doAccept(T t) {

        }

        @Override public void doAccept(double d) {

        }

        @Override public void doAccept(float f) {

        }

        @Override public void doAccept(int i) {

        }

        @Override public void doAccept(long l) {

        }

        @Override public void doAccept(short s) {

        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaConsumerX<T, X extends Exception> implements LBoolConsumerX<X>, LByteConsumerX<X>, LShortConsumerX<X>, LIntConsumerX<X>, LLongConsumerX<X>, LFloatConsumerX<X>, LDoubleConsumerX<X>, LCharConsumerX<X>, LConsumerX<T, X> {

        @Override public void doAccept(boolean b) throws X {

        }

        @Override public void doAccept(byte b) throws X {

        }

        @Override public void doAccept(char c) throws X {

        }

        @Override public void doAccept(T t) throws X {

        }

        @Override public void doAccept(double d) throws X {

        }

        @Override public void doAccept(float f) throws X {

        }

        @Override public void doAccept(int i) throws X {

        }

        @Override public void doAccept(long l) throws X {

        }

        @Override public void doAccept(short s) throws X {

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

    public static class MegaPredicateX<T, X extends Exception> implements LLogicalOperatorX<X>, LBytePredicateX<X>, LShortPredicateX<X>, LIntPredicateX<X>, LLongPredicateX<X>, LFloatPredicateX<X>, LDoublePredicateX<X>, LCharPredicateX<X>, LPredicateX<T, X> {

        @Override public boolean doApply(boolean b) {
            return false;
        }

        @Override public boolean doTest(byte b) throws X {
            return false;
        }

        @Override public boolean doTest(char c) throws X {
            return false;
        }

        @Override public boolean doTest(double d) throws X {
            return false;
        }

        @Override public boolean doTest(float f) throws X {
            return false;
        }

        @Override public boolean doTest(int i) throws X {
            return false;
        }

        @Override public boolean doTest(long l) throws X {
            return false;
        }

        @Override public boolean doTest(T t) throws X {
            return false;
        }

        @Override public boolean doTest(short s) throws X {
            return false;
        }

        @Nonnull @Override public MegaPredicateX<T, X> negate() {
            return null;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {

            return "";
        }
    }

    public static class MegaConverter<T, X extends Exception> implements LByteToShortFunctionX<X>, LByteToCharFunctionX<X>,LByteToIntFunctionX<X>,LByteToLongFunctionX<X>,LByteToFloatFunctionX<X>,LByteToDoubleFunctionX<X>,LBytePredicateX<X>, LByteFunctionX<T,X> {

        @Nullable @Override public T doApply(byte b) throws X {
            return null;
        }

        @Override public boolean doTest(byte b) throws X {
            return false;
        }

        @Override public char doApplyAsChar(byte b) throws X {
            return 0;
        }

        @Override public double doApplyAsDouble(byte b) throws X {
            return 0;
        }

        @Override public float doApplyAsFloat(byte b) throws X {
            return 0;
        }

        @Override public int doApplyAsInt(byte b) throws X {
            return 0;
        }

        @Override public long doApplyAsLong(byte b) throws X {
            return 0;
        }

        @Override public short doApplyAsShort(byte b) throws X {
            return 0;
        }

        @Nonnull @Override public String functionalInterfaceDescription() {
            return null;
        }
    }
}
