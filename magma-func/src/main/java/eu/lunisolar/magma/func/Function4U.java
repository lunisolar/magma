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

package eu.lunisolar.magma.func;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.NestedException; //NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import java.util.function.*; // NOSONAR

/**
 *  Methods that du not reference interface classes directly or are suplements for the JRE interfaces.
 */
@SuppressWarnings("unused")
public final class Function4U {

	private static final Object defaultObject = null;
	private static final byte defaultByte = 0;
	private static final byte defaultShort = 0;
	private static final int defaultInteger = 0;
	private static final long defaultLong = 0;
	private static final float defaultFloat = 0;
	private static final double defaultDouble = 0;
	private static final char defaultCharacter = 0;
	private static final boolean defaultBoolean = false;

	// <editor-fold desc="no-instance constructor">
	private Function4U() {
	}
	// </editor-fold>

	/** You can use this as a reference method whenever nothing should be done. */
	public static void doNothing() {
		// NOSONAR
	}

	/** Does nothing (LObjByteConsumer)*/
	public static <T> void doNothing(T a1, byte a2) {
		// NOSONAR
	}

	/** Does nothing (LByteObjConsumer.V1)*/
	public static <T> void doNothing(byte a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjShortConsumer)*/
	public static <T> void doNothing(T a1, short a2) {
		// NOSONAR
	}

	/** Does nothing (LShortObjConsumer.V1)*/
	public static <T> void doNothing(short a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjIntConsumer)*/
	public static <T> void doNothing(T a1, int a2) {
		// NOSONAR
	}

	/** Does nothing (LIntObjConsumer.V1)*/
	public static <T> void doNothing(int a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjLongConsumer)*/
	public static <T> void doNothing(T a1, long a2) {
		// NOSONAR
	}

	/** Does nothing (LLongObjConsumer.V1)*/
	public static <T> void doNothing(long a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjFloatConsumer)*/
	public static <T> void doNothing(T a1, float a2) {
		// NOSONAR
	}

	/** Does nothing (LFloatObjConsumer.V1)*/
	public static <T> void doNothing(float a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjDoubleConsumer)*/
	public static <T> void doNothing(T a1, double a2) {
		// NOSONAR
	}

	/** Does nothing (LDoubleObjConsumer.V1)*/
	public static <T> void doNothing(double a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjCharConsumer)*/
	public static <T> void doNothing(T a1, char a2) {
		// NOSONAR
	}

	/** Does nothing (LCharObjConsumer.V1)*/
	public static <T> void doNothing(char a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjBoolConsumer)*/
	public static <T> void doNothing(T a1, boolean a2) {
		// NOSONAR
	}

	/** Does nothing (LBoolObjConsumer.V1)*/
	public static <T> void doNothing(boolean a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LBiObjByteConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, byte a3) {
		// NOSONAR
	}

	/** Does nothing (LObjByteObjConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, byte a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LByteBiObjConsumer.V4)*/
	public static <T1, T2> void doNothing(byte a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjShortConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, short a3) {
		// NOSONAR
	}

	/** Does nothing (LObjShortObjConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, short a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LShortBiObjConsumer.V4)*/
	public static <T1, T2> void doNothing(short a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjIntConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, int a3) {
		// NOSONAR
	}

	/** Does nothing (LObjIntObjConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, int a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LIntBiObjConsumer.V4)*/
	public static <T1, T2> void doNothing(int a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjLongConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, long a3) {
		// NOSONAR
	}

	/** Does nothing (LObjLongObjConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, long a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LLongBiObjConsumer.V4)*/
	public static <T1, T2> void doNothing(long a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjFloatConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, float a3) {
		// NOSONAR
	}

	/** Does nothing (LObjFloatObjConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, float a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LFloatBiObjConsumer.V4)*/
	public static <T1, T2> void doNothing(float a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjDoubleConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, double a3) {
		// NOSONAR
	}

	/** Does nothing (LObjDoubleObjConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, double a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LDoubleBiObjConsumer.V4)*/
	public static <T1, T2> void doNothing(double a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjCharConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, char a3) {
		// NOSONAR
	}

	/** Does nothing (LObjCharObjConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, char a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LCharBiObjConsumer.V4)*/
	public static <T1, T2> void doNothing(char a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjBoolConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, boolean a3) {
		// NOSONAR
	}

	/** Does nothing (LObjBoolObjConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, boolean a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBoolBiObjConsumer.V4)*/
	public static <T1, T2> void doNothing(boolean a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiByteConsumer)*/
	public static void doNothing(byte a1, byte a2) {
		// NOSONAR
	}

	/** Does nothing (LBiShortConsumer)*/
	public static void doNothing(short a1, short a2) {
		// NOSONAR
	}

	/** Does nothing (LBiIntConsumer)*/
	public static void doNothing(int a1, int a2) {
		// NOSONAR
	}

	/** Does nothing (LBiLongConsumer)*/
	public static void doNothing(long a1, long a2) {
		// NOSONAR
	}

	/** Does nothing (LBiFloatConsumer)*/
	public static void doNothing(float a1, float a2) {
		// NOSONAR
	}

	/** Does nothing (LBiDoubleConsumer)*/
	public static void doNothing(double a1, double a2) {
		// NOSONAR
	}

	/** Does nothing (LBiCharConsumer)*/
	public static void doNothing(char a1, char a2) {
		// NOSONAR
	}

	/** Does nothing (LBiBoolConsumer)*/
	public static void doNothing(boolean a1, boolean a2) {
		// NOSONAR
	}

	/** Does nothing (LTriBoolConsumer)*/
	public static void doNothing(boolean a1, boolean a2, boolean a3) {
		// NOSONAR
	}

	/** Does nothing (LByteConsumer)*/
	public static void doNothing(byte a1) {
		// NOSONAR
	}

	/** Does nothing (LShortConsumer)*/
	public static void doNothing(short a1) {
		// NOSONAR
	}

	/** Does nothing (LIntConsumer)*/
	public static void doNothing(int a1) {
		// NOSONAR
	}

	/** Does nothing (LLongConsumer)*/
	public static void doNothing(long a1) {
		// NOSONAR
	}

	/** Does nothing (LFloatConsumer)*/
	public static void doNothing(float a1) {
		// NOSONAR
	}

	/** Does nothing (LDoubleConsumer)*/
	public static void doNothing(double a1) {
		// NOSONAR
	}

	/** Does nothing (LCharConsumer)*/
	public static void doNothing(char a1) {
		// NOSONAR
	}

	/** Does nothing (LBoolConsumer)*/
	public static void doNothing(boolean a1) {
		// NOSONAR
	}

	/** Does nothing (LConsumer)*/
	public static <T> void doNothing(T a1) {
		// NOSONAR
	}

	/** Does nothing (LBiConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LTriConsumer)*/
	public static <T1, T2, T3> void doNothing(T1 a1, T2 a2, T3 a3) {
		// NOSONAR
	}

	/** Does nothing (LByteSupplier)   supplier*/
	public static byte produceByte() {
		return defaultByte;
	}

	/** Does nothing (LShortSupplier)   supplier*/
	public static short produceShort() {
		return defaultShort;
	}

	/** Does nothing (LIntSupplier)   supplier*/
	public static int produceInt() {
		return defaultInteger;
	}

	/** Does nothing (LLongSupplier)   supplier*/
	public static long produceLong() {
		return defaultLong;
	}

	/** Does nothing (LFloatSupplier)   supplier*/
	public static float produceFloat() {
		return defaultFloat;
	}

	/** Does nothing (LDoubleSupplier)   supplier*/
	public static double produceDouble() {
		return defaultDouble;
	}

	/** Does nothing (LCharSupplier)   supplier*/
	public static char produceChar() {
		return defaultCharacter;
	}

	/** Does nothing (LBoolSupplier)   supplier*/
	public static boolean produceBoolean() {
		return defaultBoolean;
	}

	/** Does nothing (LSupplier)   supplier*/
	public static <R> R produce() {
		return (R) defaultObject;
	}

	/** Does nothing (LByteUnaryOperator)   operator*/
	public static byte produceByte(byte a1) {
		return defaultByte;
	}

	/** Does nothing (LByteBinaryOperator)   operator*/
	public static byte produceByte(byte a1, byte a2) {
		return defaultByte;
	}

	/** Does nothing (LShortUnaryOperator)   operator*/
	public static short produceShort(short a1) {
		return defaultShort;
	}

	/** Does nothing (LShortBinaryOperator)   operator*/
	public static short produceShort(short a1, short a2) {
		return defaultShort;
	}

	/** Does nothing (LIntUnaryOperator)   operator*/
	public static int produceInt(int a1) {
		return defaultInteger;
	}

	/** Does nothing (LIntBinaryOperator)   operator*/
	public static int produceInt(int a1, int a2) {
		return defaultInteger;
	}

	/** Does nothing (LLongUnaryOperator)   operator*/
	public static long produceLong(long a1) {
		return defaultLong;
	}

	/** Does nothing (LLongBinaryOperator)   operator*/
	public static long produceLong(long a1, long a2) {
		return defaultLong;
	}

	/** Does nothing (LFloatUnaryOperator)   operator*/
	public static float produceFloat(float a1) {
		return defaultFloat;
	}

	/** Does nothing (LFloatBinaryOperator)   operator*/
	public static float produceFloat(float a1, float a2) {
		return defaultFloat;
	}

	/** Does nothing (LDoubleUnaryOperator)   operator*/
	public static double produceDouble(double a1) {
		return defaultDouble;
	}

	/** Does nothing (LDoubleBinaryOperator)   operator*/
	public static double produceDouble(double a1, double a2) {
		return defaultDouble;
	}

	/** Does nothing (LCharUnaryOperator)   operator*/
	public static char produceChar(char a1) {
		return defaultCharacter;
	}

	/** Does nothing (LCharBinaryOperator)   operator*/
	public static char produceChar(char a1, char a2) {
		return defaultCharacter;
	}

	/** Does nothing (LLogicalOperator)   operator*/
	public static boolean produceBoolean(boolean a1) {
		return defaultBoolean;
	}

	/** Does nothing (LLogicalBinaryOperator)   operator*/
	public static boolean produceBoolean(boolean a1, boolean a2) {
		return defaultBoolean;
	}

	/** Does nothing (LLogicalTernaryOperator)   operator*/
	public static boolean produceBoolean(boolean a1, boolean a2, boolean a3) {
		return defaultBoolean;
	}

	/** Does nothing (LFunction)   function*/
	public static <T, R> R produce(T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LTriFunction)   function*/
	public static <T1, T2, T3, R> R produce(T1 a1, T2 a2, T3 a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LByteFunction)   function*/
	public static <R> R produce(byte a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiByteFunction)   function*/
	public static <R> R produce(byte a1, byte a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjByteFunction)   function*/
	public static <T, R> R produce(T a1, byte a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LByteObjFunction.V1)   function*/
	public static <T, R> R produce(byte a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjByteFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, byte a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjByteObjFunction.V1)   function*/
	public static <T1, T2, R> R produce(T1 a1, byte a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LByteBiObjFunction.V4)   function*/
	public static <T1, T2, R> R produce(byte a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LShortFunction)   function*/
	public static <R> R produce(short a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiShortFunction)   function*/
	public static <R> R produce(short a1, short a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjShortFunction)   function*/
	public static <T, R> R produce(T a1, short a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LShortObjFunction.V1)   function*/
	public static <T, R> R produce(short a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjShortFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, short a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjShortObjFunction.V1)   function*/
	public static <T1, T2, R> R produce(T1 a1, short a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LShortBiObjFunction.V4)   function*/
	public static <T1, T2, R> R produce(short a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LIntFunction)   function*/
	public static <R> R produce(int a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiIntFunction)   function*/
	public static <R> R produce(int a1, int a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjIntFunction)   function*/
	public static <T, R> R produce(T a1, int a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LIntObjFunction.V1)   function*/
	public static <T, R> R produce(int a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjIntFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, int a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjIntObjFunction.V1)   function*/
	public static <T1, T2, R> R produce(T1 a1, int a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LIntBiObjFunction.V4)   function*/
	public static <T1, T2, R> R produce(int a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LLongFunction)   function*/
	public static <R> R produce(long a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiLongFunction)   function*/
	public static <R> R produce(long a1, long a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjLongFunction)   function*/
	public static <T, R> R produce(T a1, long a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LLongObjFunction.V1)   function*/
	public static <T, R> R produce(long a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjLongFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, long a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjLongObjFunction.V1)   function*/
	public static <T1, T2, R> R produce(T1 a1, long a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LLongBiObjFunction.V4)   function*/
	public static <T1, T2, R> R produce(long a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LFloatFunction)   function*/
	public static <R> R produce(float a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiFloatFunction)   function*/
	public static <R> R produce(float a1, float a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjFloatFunction)   function*/
	public static <T, R> R produce(T a1, float a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LFloatObjFunction.V1)   function*/
	public static <T, R> R produce(float a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjFloatFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, float a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjFloatObjFunction.V1)   function*/
	public static <T1, T2, R> R produce(T1 a1, float a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LFloatBiObjFunction.V4)   function*/
	public static <T1, T2, R> R produce(float a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LDoubleFunction)   function*/
	public static <R> R produce(double a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiDoubleFunction)   function*/
	public static <R> R produce(double a1, double a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjDoubleFunction)   function*/
	public static <T, R> R produce(T a1, double a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LDoubleObjFunction.V1)   function*/
	public static <T, R> R produce(double a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjDoubleFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, double a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjDoubleObjFunction.V1)   function*/
	public static <T1, T2, R> R produce(T1 a1, double a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LDoubleBiObjFunction.V4)   function*/
	public static <T1, T2, R> R produce(double a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LCharFunction)   function*/
	public static <R> R produce(char a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiCharFunction)   function*/
	public static <R> R produce(char a1, char a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjCharFunction)   function*/
	public static <T, R> R produce(T a1, char a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LCharObjFunction.V1)   function*/
	public static <T, R> R produce(char a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjCharFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, char a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjCharObjFunction.V1)   function*/
	public static <T1, T2, R> R produce(T1 a1, char a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LCharBiObjFunction.V4)   function*/
	public static <T1, T2, R> R produce(char a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBoolFunction)   function*/
	public static <R> R produce(boolean a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiBoolFunction)   function*/
	public static <R> R produce(boolean a1, boolean a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LTriBoolFunction)   function*/
	public static <R> R produce(boolean a1, boolean a2, boolean a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjBoolFunction)   function*/
	public static <T, R> R produce(T a1, boolean a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBoolObjFunction.V1)   function*/
	public static <T, R> R produce(boolean a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjBoolFunction)   function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, boolean a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjBoolObjFunction.V1)   function*/
	public static <T1, T2, R> R produce(T1 a1, boolean a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBoolBiObjFunction.V4)   function*/
	public static <T1, T2, R> R produce(boolean a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LToByteFunction)   function*/
	public static <T> byte produceByte(T a1) {
		return defaultByte;
	}

	/** Does nothing (LToByteBiFunction)   function*/
	public static <T1, T2> byte produceByte(T1 a1, T2 a2) {
		return defaultByte;
	}

	/** Does nothing (LToShortFunction)   function*/
	public static <T> short produceShort(T a1) {
		return defaultShort;
	}

	/** Does nothing (LToShortBiFunction)   function*/
	public static <T1, T2> short produceShort(T1 a1, T2 a2) {
		return defaultShort;
	}

	/** Does nothing (LToIntFunction)   function*/
	public static <T> int produceInt(T a1) {
		return defaultInteger;
	}

	/** Does nothing (LToIntBiFunction)   function*/
	public static <T1, T2> int produceInt(T1 a1, T2 a2) {
		return defaultInteger;
	}

	/** Does nothing (LObjIntToIntFunction)   function*/
	public static <T> int produceInt(T a1, int a2) {
		return defaultInteger;
	}

	/** Does nothing (LIntObjToIntFunction.V1)   function*/
	public static <T> int produceInt(int a2, T a1) {
		return defaultInteger;
	}

	/** Does nothing (LToLongFunction)   function*/
	public static <T> long produceLong(T a1) {
		return defaultLong;
	}

	/** Does nothing (LToLongBiFunction)   function*/
	public static <T1, T2> long produceLong(T1 a1, T2 a2) {
		return defaultLong;
	}

	/** Does nothing (LToFloatFunction)   function*/
	public static <T> float produceFloat(T a1) {
		return defaultFloat;
	}

	/** Does nothing (LToFloatBiFunction)   function*/
	public static <T1, T2> float produceFloat(T1 a1, T2 a2) {
		return defaultFloat;
	}

	/** Does nothing (LToDoubleFunction)   function*/
	public static <T> double produceDouble(T a1) {
		return defaultDouble;
	}

	/** Does nothing (LToDoubleBiFunction)   function*/
	public static <T1, T2> double produceDouble(T1 a1, T2 a2) {
		return defaultDouble;
	}

	/** Does nothing (LToCharFunction)   function*/
	public static <T> char produceChar(T a1) {
		return defaultCharacter;
	}

	/** Does nothing (LToCharBiFunction)   function*/
	public static <T1, T2> char produceChar(T1 a1, T2 a2) {
		return defaultCharacter;
	}

	/** Does nothing (LShortToByteFunction)   function*/
	public static byte produceByte(short a1) {
		return defaultByte;
	}

	/** Does nothing (LIntToByteFunction)   function*/
	public static byte produceByte(int a1) {
		return defaultByte;
	}

	/** Does nothing (LLongToByteFunction)   function*/
	public static byte produceByte(long a1) {
		return defaultByte;
	}

	/** Does nothing (LFloatToByteFunction)   function*/
	public static byte produceByte(float a1) {
		return defaultByte;
	}

	/** Does nothing (LDoubleToByteFunction)   function*/
	public static byte produceByte(double a1) {
		return defaultByte;
	}

	/** Does nothing (LCharToByteFunction)   function*/
	public static byte produceByte(char a1) {
		return defaultByte;
	}

	/** Does nothing (LBoolToByteFunction)   function*/
	public static byte produceByte(boolean a1) {
		return defaultByte;
	}

	/** Does nothing (LByteToShortFunction)   function*/
	public static short produceShort(byte a1) {
		return defaultShort;
	}

	/** Does nothing (LIntToShortFunction)   function*/
	public static short produceShort(int a1) {
		return defaultShort;
	}

	/** Does nothing (LLongToShortFunction)   function*/
	public static short produceShort(long a1) {
		return defaultShort;
	}

	/** Does nothing (LFloatToShortFunction)   function*/
	public static short produceShort(float a1) {
		return defaultShort;
	}

	/** Does nothing (LDoubleToShortFunction)   function*/
	public static short produceShort(double a1) {
		return defaultShort;
	}

	/** Does nothing (LCharToShortFunction)   function*/
	public static short produceShort(char a1) {
		return defaultShort;
	}

	/** Does nothing (LBoolToShortFunction)   function*/
	public static short produceShort(boolean a1) {
		return defaultShort;
	}

	/** Does nothing (LByteToIntFunction)   function*/
	public static int produceInt(byte a1) {
		return defaultInteger;
	}

	/** Does nothing (LShortToIntFunction)   function*/
	public static int produceInt(short a1) {
		return defaultInteger;
	}

	/** Does nothing (LLongToIntFunction)   function*/
	public static int produceInt(long a1) {
		return defaultInteger;
	}

	/** Does nothing (LFloatToIntFunction)   function*/
	public static int produceInt(float a1) {
		return defaultInteger;
	}

	/** Does nothing (LDoubleToIntFunction)   function*/
	public static int produceInt(double a1) {
		return defaultInteger;
	}

	/** Does nothing (LCharToIntFunction)   function*/
	public static int produceInt(char a1) {
		return defaultInteger;
	}

	/** Does nothing (LBoolToIntFunction)   function*/
	public static int produceInt(boolean a1) {
		return defaultInteger;
	}

	/** Does nothing (LByteToLongFunction)   function*/
	public static long produceLong(byte a1) {
		return defaultLong;
	}

	/** Does nothing (LShortToLongFunction)   function*/
	public static long produceLong(short a1) {
		return defaultLong;
	}

	/** Does nothing (LIntToLongFunction)   function*/
	public static long produceLong(int a1) {
		return defaultLong;
	}

	/** Does nothing (LFloatToLongFunction)   function*/
	public static long produceLong(float a1) {
		return defaultLong;
	}

	/** Does nothing (LDoubleToLongFunction)   function*/
	public static long produceLong(double a1) {
		return defaultLong;
	}

	/** Does nothing (LCharToLongFunction)   function*/
	public static long produceLong(char a1) {
		return defaultLong;
	}

	/** Does nothing (LBoolToLongFunction)   function*/
	public static long produceLong(boolean a1) {
		return defaultLong;
	}

	/** Does nothing (LByteToFloatFunction)   function*/
	public static float produceFloat(byte a1) {
		return defaultFloat;
	}

	/** Does nothing (LShortToFloatFunction)   function*/
	public static float produceFloat(short a1) {
		return defaultFloat;
	}

	/** Does nothing (LIntToFloatFunction)   function*/
	public static float produceFloat(int a1) {
		return defaultFloat;
	}

	/** Does nothing (LLongToFloatFunction)   function*/
	public static float produceFloat(long a1) {
		return defaultFloat;
	}

	/** Does nothing (LDoubleToFloatFunction)   function*/
	public static float produceFloat(double a1) {
		return defaultFloat;
	}

	/** Does nothing (LCharToFloatFunction)   function*/
	public static float produceFloat(char a1) {
		return defaultFloat;
	}

	/** Does nothing (LBoolToFloatFunction)   function*/
	public static float produceFloat(boolean a1) {
		return defaultFloat;
	}

	/** Does nothing (LByteToDoubleFunction)   function*/
	public static double produceDouble(byte a1) {
		return defaultDouble;
	}

	/** Does nothing (LShortToDoubleFunction)   function*/
	public static double produceDouble(short a1) {
		return defaultDouble;
	}

	/** Does nothing (LIntToDoubleFunction)   function*/
	public static double produceDouble(int a1) {
		return defaultDouble;
	}

	/** Does nothing (LLongToDoubleFunction)   function*/
	public static double produceDouble(long a1) {
		return defaultDouble;
	}

	/** Does nothing (LFloatToDoubleFunction)   function*/
	public static double produceDouble(float a1) {
		return defaultDouble;
	}

	/** Does nothing (LCharToDoubleFunction)   function*/
	public static double produceDouble(char a1) {
		return defaultDouble;
	}

	/** Does nothing (LBoolToDoubleFunction)   function*/
	public static double produceDouble(boolean a1) {
		return defaultDouble;
	}

	/** Does nothing (LByteToCharFunction)   function*/
	public static char produceChar(byte a1) {
		return defaultCharacter;
	}

	/** Does nothing (LShortToCharFunction)   function*/
	public static char produceChar(short a1) {
		return defaultCharacter;
	}

	/** Does nothing (LIntToCharFunction)   function*/
	public static char produceChar(int a1) {
		return defaultCharacter;
	}

	/** Does nothing (LLongToCharFunction)   function*/
	public static char produceChar(long a1) {
		return defaultCharacter;
	}

	/** Does nothing (LFloatToCharFunction)   function*/
	public static char produceChar(float a1) {
		return defaultCharacter;
	}

	/** Does nothing (LDoubleToCharFunction)   function*/
	public static char produceChar(double a1) {
		return defaultCharacter;
	}

	/** Does nothing (LBoolToCharFunction)   function*/
	public static char produceChar(boolean a1) {
		return defaultCharacter;
	}

	// <editor-fold desc="predicate::method">

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, byte a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, byte a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, short a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, short a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, int a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, long a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, long a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, float a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, float a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, double a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, double a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, char a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, char a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, boolean a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, boolean a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, byte a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, byte a3) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, short a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, short a3) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, int a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, int a3) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, long a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, long a3) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, float a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, float a3) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, double a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, double a3) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, char a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, char a3) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, boolean a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, boolean a3) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte a1, byte a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte a1, byte a2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(short a1, short a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(short a1, short a2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(int a1, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(int a1, int a2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(long a1, long a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(long a1, long a2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(float a1, float a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(float a1, float a2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(double a1, double a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(double a1, double a2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(char a1, char a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(char a1, char a2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte a1) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(short a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(short a1) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(int a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(int a1) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(long a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(long a1) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(float a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(float a1) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(double a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(double a1) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(char a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(char a1) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2, T3> boolean alwaysTrue(T1 a1, T2 a2, T3 a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2, T3> boolean alwaysFalse(T1 a1, T2 a2, T3 a3) {
		return false;
	}

	// </editor-fold>

	// <editor-fold desc="wrapper for lambda- JRE">

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.UnaryOperator l(final java.util.function.UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.UnaryOperator unaryOperator(final java.util.function.UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.BinaryOperator l(final java.util.function.BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.BinaryOperator binaryOperator(final java.util.function.BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntUnaryOperator l(final java.util.function.IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntUnaryOperator intUnaryOperator(final java.util.function.IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongUnaryOperator l(final java.util.function.LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongUnaryOperator longUnaryOperator(final java.util.function.LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleUnaryOperator l(final java.util.function.DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleUnaryOperator doubleUnaryOperator(final java.util.function.DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntBinaryOperator l(final java.util.function.IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntBinaryOperator intBinaryOperator(final java.util.function.IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongBinaryOperator l(final java.util.function.LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongBinaryOperator longBinaryOperator(final java.util.function.LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleBinaryOperator l(final java.util.function.DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleBinaryOperator doubleBinaryOperator(final java.util.function.DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T, R> java.util.function.Function l(final java.util.function.Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T, R> java.util.function.Function function(final java.util.function.Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2, R> java.util.function.BiFunction l(final java.util.function.BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2, R> java.util.function.BiFunction biFunction(final java.util.function.BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> java.util.function.IntFunction l(final java.util.function.IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> java.util.function.IntFunction intFunction(final java.util.function.IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> java.util.function.LongFunction l(final java.util.function.LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> java.util.function.LongFunction longFunction(final java.util.function.LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> java.util.function.DoubleFunction l(final java.util.function.DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> java.util.function.DoubleFunction doubleFunction(final java.util.function.DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ToIntFunction l(final java.util.function.ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ToIntFunction toIntFunction(final java.util.function.ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ToLongFunction l(final java.util.function.ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ToLongFunction toLongFunction(final java.util.function.ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ToDoubleFunction l(final java.util.function.ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ToDoubleFunction toDoubleFunction(final java.util.function.ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.ToIntBiFunction l(final java.util.function.ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.ToIntBiFunction toIntBiFunction(final java.util.function.ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.ToLongBiFunction l(final java.util.function.ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.ToLongBiFunction toLongBiFunction(final java.util.function.ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.ToDoubleBiFunction l(final java.util.function.ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.ToDoubleBiFunction toDoubleBiFunction(final java.util.function.ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntToLongFunction l(final java.util.function.IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntToLongFunction intToLongFunction(final java.util.function.IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntToDoubleFunction l(final java.util.function.IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntToDoubleFunction intToDoubleFunction(final java.util.function.IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongToIntFunction l(final java.util.function.LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongToIntFunction longToIntFunction(final java.util.function.LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongToDoubleFunction l(final java.util.function.LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongToDoubleFunction longToDoubleFunction(final java.util.function.LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleToIntFunction l(final java.util.function.DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleToIntFunction doubleToIntFunction(final java.util.function.DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleToLongFunction l(final java.util.function.DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleToLongFunction doubleToLongFunction(final java.util.function.DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.Predicate l(final java.util.function.Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.Predicate predicate(final java.util.function.Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.BiPredicate l(final java.util.function.BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.BiPredicate biPredicate(final java.util.function.BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntPredicate l(final java.util.function.IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntPredicate intPredicate(final java.util.function.IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongPredicate l(final java.util.function.LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongPredicate longPredicate(final java.util.function.LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoublePredicate l(final java.util.function.DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoublePredicate doublePredicate(final java.util.function.DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> java.util.function.Supplier l(final java.util.function.Supplier<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> java.util.function.Supplier supplier(final java.util.function.Supplier<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntSupplier l(final java.util.function.IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntSupplier intSupplier(final java.util.function.IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongSupplier l(final java.util.function.LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongSupplier longSupplier(final java.util.function.LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleSupplier l(final java.util.function.DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleSupplier doubleSupplier(final java.util.function.DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.BooleanSupplier l(final java.util.function.BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.BooleanSupplier booleanSupplier(final java.util.function.BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.Consumer l(final java.util.function.Consumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.Consumer consumer(final java.util.function.Consumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.BiConsumer l(final java.util.function.BiConsumer<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> java.util.function.BiConsumer biConsumer(final java.util.function.BiConsumer<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntConsumer l(final java.util.function.IntConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.IntConsumer intConsumer(final java.util.function.IntConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongConsumer l(final java.util.function.LongConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.LongConsumer longConsumer(final java.util.function.LongConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleConsumer l(final java.util.function.DoubleConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.util.function.DoubleConsumer doubleConsumer(final java.util.function.DoubleConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ObjIntConsumer l(final java.util.function.ObjIntConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ObjIntConsumer objIntConsumer(final java.util.function.ObjIntConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ObjLongConsumer l(final java.util.function.ObjLongConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ObjLongConsumer objLongConsumer(final java.util.function.ObjLongConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ObjDoubleConsumer l(final java.util.function.ObjDoubleConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> java.util.function.ObjDoubleConsumer objDoubleConsumer(final java.util.function.ObjDoubleConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.lang.Runnable l(final java.lang.Runnable lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static java.lang.Runnable runnable(final java.lang.Runnable lambda) {
		return lambda;
	}

	// </editor-fold>

}