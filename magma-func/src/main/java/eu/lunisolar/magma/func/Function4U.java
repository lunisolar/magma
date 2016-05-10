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
import java.util.function.*;
import eu.lunisolar.magma.basics.exceptions.NestedException; //NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR

/**
 *  Methods that do not reference interface classes directly or are supplements for the JRE interfaces.
 */
@SuppressWarnings("unused")
public final class Function4U {

	public static final Object defaultObject = null;
	public static final byte defaultByte = 0;
	public static final byte defaultShort = 0;
	public static final int defaultInteger = 0;
	public static final long defaultLong = 0;
	public static final float defaultFloat = 0;
	public static final double defaultDouble = 0;
	public static final char defaultCharacter = 0;
	public static final boolean defaultBoolean = false;

	// <editor-fold desc="no-instance constructor">
	private Function4U() {
	}
	// </editor-fold>

	/** You can use this as a reference method whenever nothing should be done. */
	public static void doNothing() {
		// NOSONAR
	}

	/** Does nothing (LBiConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LConsumer)*/
	public static <T> void doNothing(T a1) {
		// NOSONAR
	}

	/** Does nothing (LTriConsumer)*/
	public static <T1, T2, T3> void doNothing(T1 a1, T2 a2, T3 a3) {
		// NOSONAR
	}

	/** Does nothing (LBoolConsumer)*/
	public static void doNothing(boolean a1) {
		// NOSONAR
	}

	/** Does nothing (LByteConsumer)*/
	public static void doNothing(byte a1) {
		// NOSONAR
	}

	/** Does nothing (LCharConsumer)*/
	public static void doNothing(char a1) {
		// NOSONAR
	}

	/** Does nothing (LDoubleConsumer)*/
	public static void doNothing(double a1) {
		// NOSONAR
	}

	/** Does nothing (LFloatConsumer)*/
	public static void doNothing(float a1) {
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

	/** Does nothing (LShortConsumer)*/
	public static void doNothing(short a1) {
		// NOSONAR
	}

	/** Does nothing (LBiBoolConsumer)*/
	public static void doNothing(boolean a1, boolean a2) {
		// NOSONAR
	}

	/** Does nothing (LBiByteConsumer)*/
	public static void doNothing(byte a1, byte a2) {
		// NOSONAR
	}

	/** Does nothing (LBiCharConsumer)*/
	public static void doNothing(char a1, char a2) {
		// NOSONAR
	}

	/** Does nothing (LBiDoubleConsumer)*/
	public static void doNothing(double a1, double a2) {
		// NOSONAR
	}

	/** Does nothing (LBiFloatConsumer)*/
	public static void doNothing(float a1, float a2) {
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

	/** Does nothing (LBiShortConsumer)*/
	public static void doNothing(short a1, short a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjBoolConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, boolean a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjBoolConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, boolean a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjBoolConsumer.V4)*/
	public static <T1, T2> void doNothing(boolean a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjByteConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, byte a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjByteConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, byte a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjByteConsumer.V4)*/
	public static <T1, T2> void doNothing(byte a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjCharConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, char a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjCharConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, char a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjCharConsumer.V4)*/
	public static <T1, T2> void doNothing(char a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjDoubleConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, double a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjDoubleConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, double a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjDoubleConsumer.V4)*/
	public static <T1, T2> void doNothing(double a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjFloatConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, float a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjFloatConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, float a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjFloatConsumer.V4)*/
	public static <T1, T2> void doNothing(float a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjIntConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, int a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjIntConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, int a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjIntConsumer.V4)*/
	public static <T1, T2> void doNothing(int a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjLongConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, long a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjLongConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, long a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjLongConsumer.V4)*/
	public static <T1, T2> void doNothing(long a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjShortConsumer)*/
	public static <T1, T2> void doNothing(T1 a1, T2 a2, short a3) {
		// NOSONAR
	}

	/** Does nothing (LBiObjShortConsumer.V1)*/
	public static <T1, T2> void doNothing(T1 a1, short a3, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LBiObjShortConsumer.V4)*/
	public static <T1, T2> void doNothing(short a3, T1 a1, T2 a2) {
		// NOSONAR
	}

	/** Does nothing (LObjBoolConsumer)*/
	public static <T> void doNothing(T a1, boolean a2) {
		// NOSONAR
	}

	/** Does nothing (LObjBoolConsumer.V1)*/
	public static <T> void doNothing(boolean a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjByteConsumer)*/
	public static <T> void doNothing(T a1, byte a2) {
		// NOSONAR
	}

	/** Does nothing (LObjByteConsumer.V1)*/
	public static <T> void doNothing(byte a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjCharConsumer)*/
	public static <T> void doNothing(T a1, char a2) {
		// NOSONAR
	}

	/** Does nothing (LObjCharConsumer.V1)*/
	public static <T> void doNothing(char a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjDoubleConsumer)*/
	public static <T> void doNothing(T a1, double a2) {
		// NOSONAR
	}

	/** Does nothing (LObjDoubleConsumer.V1)*/
	public static <T> void doNothing(double a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjFloatConsumer)*/
	public static <T> void doNothing(T a1, float a2) {
		// NOSONAR
	}

	/** Does nothing (LObjFloatConsumer.V1)*/
	public static <T> void doNothing(float a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjIntConsumer)*/
	public static <T> void doNothing(T a1, int a2) {
		// NOSONAR
	}

	/** Does nothing (LObjIntConsumer.V1)*/
	public static <T> void doNothing(int a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjLongConsumer)*/
	public static <T> void doNothing(T a1, long a2) {
		// NOSONAR
	}

	/** Does nothing (LObjLongConsumer.V1)*/
	public static <T> void doNothing(long a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LObjShortConsumer)*/
	public static <T> void doNothing(T a1, short a2) {
		// NOSONAR
	}

	/** Does nothing (LObjShortConsumer.V1)*/
	public static <T> void doNothing(short a2, T a1) {
		// NOSONAR
	}

	/** Does nothing (LTriBoolConsumer)*/
	public static void doNothing(boolean a1, boolean a2, boolean a3) {
		// NOSONAR
	}

	/** Does nothing (LBoolSupplier)   Supplier*/
	public static boolean produceBoolean() {
		return defaultBoolean;
	}

	/** Does nothing (LByteSupplier)   Supplier*/
	public static byte produceByte() {
		return defaultByte;
	}

	/** Does nothing (LCharSupplier)   Supplier*/
	public static char produceChar() {
		return defaultCharacter;
	}

	/** Does nothing (LDoubleSupplier)   Supplier*/
	public static double produceDouble() {
		return defaultDouble;
	}

	/** Does nothing (LFloatSupplier)   Supplier*/
	public static float produceFloat() {
		return defaultFloat;
	}

	/** Does nothing (LIntSupplier)   Supplier*/
	public static int produceInt() {
		return defaultInteger;
	}

	/** Does nothing (LLongSupplier)   Supplier*/
	public static long produceLong() {
		return defaultLong;
	}

	/** Does nothing (LShortSupplier)   Supplier*/
	public static short produceShort() {
		return defaultShort;
	}

	/** Does nothing (LSupplier)   Supplier*/
	public static <T> T produce() {
		return (T) defaultObject;
	}

	/** Does nothing (LBiFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LFunction)   Function*/
	public static <T, R> R produce(T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LTriFunction)   Function*/
	public static <T1, T2, T3, R> R produce(T1 a1, T2 a2, T3 a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBoolToByteFunction)   Function*/
	public static byte produceByte(boolean a1) {
		return defaultByte;
	}

	/** Does nothing (LBoolToCharFunction)   Function*/
	public static char produceChar(boolean a1) {
		return defaultCharacter;
	}

	/** Does nothing (LBoolToDoubleFunction)   Function*/
	public static double produceDouble(boolean a1) {
		return defaultDouble;
	}

	/** Does nothing (LBoolToFloatFunction)   Function*/
	public static float produceFloat(boolean a1) {
		return defaultFloat;
	}

	/** Does nothing (LBoolToIntFunction)   Function*/
	public static int produceInt(boolean a1) {
		return defaultInteger;
	}

	/** Does nothing (LBoolToLongFunction)   Function*/
	public static long produceLong(boolean a1) {
		return defaultLong;
	}

	/** Does nothing (LBoolToShortFunction)   Function*/
	public static short produceShort(boolean a1) {
		return defaultShort;
	}

	/** Does nothing (LByteToCharFunction)   Function*/
	public static char produceChar(byte a1) {
		return defaultCharacter;
	}

	/** Does nothing (LByteToDoubleFunction)   Function*/
	public static double produceDouble(byte a1) {
		return defaultDouble;
	}

	/** Does nothing (LByteToFloatFunction)   Function*/
	public static float produceFloat(byte a1) {
		return defaultFloat;
	}

	/** Does nothing (LByteToIntFunction)   Function*/
	public static int produceInt(byte a1) {
		return defaultInteger;
	}

	/** Does nothing (LByteToLongFunction)   Function*/
	public static long produceLong(byte a1) {
		return defaultLong;
	}

	/** Does nothing (LByteToShortFunction)   Function*/
	public static short produceShort(byte a1) {
		return defaultShort;
	}

	/** Does nothing (LCharToByteFunction)   Function*/
	public static byte produceByte(char a1) {
		return defaultByte;
	}

	/** Does nothing (LCharToDoubleFunction)   Function*/
	public static double produceDouble(char a1) {
		return defaultDouble;
	}

	/** Does nothing (LCharToFloatFunction)   Function*/
	public static float produceFloat(char a1) {
		return defaultFloat;
	}

	/** Does nothing (LCharToIntFunction)   Function*/
	public static int produceInt(char a1) {
		return defaultInteger;
	}

	/** Does nothing (LCharToLongFunction)   Function*/
	public static long produceLong(char a1) {
		return defaultLong;
	}

	/** Does nothing (LCharToShortFunction)   Function*/
	public static short produceShort(char a1) {
		return defaultShort;
	}

	/** Does nothing (LDoubleToByteFunction)   Function*/
	public static byte produceByte(double a1) {
		return defaultByte;
	}

	/** Does nothing (LDoubleToCharFunction)   Function*/
	public static char produceChar(double a1) {
		return defaultCharacter;
	}

	/** Does nothing (LDoubleToFloatFunction)   Function*/
	public static float produceFloat(double a1) {
		return defaultFloat;
	}

	/** Does nothing (LDoubleToIntFunction)   Function*/
	public static int produceInt(double a1) {
		return defaultInteger;
	}

	/** Does nothing (LDoubleToLongFunction)   Function*/
	public static long produceLong(double a1) {
		return defaultLong;
	}

	/** Does nothing (LDoubleToShortFunction)   Function*/
	public static short produceShort(double a1) {
		return defaultShort;
	}

	/** Does nothing (LFloatToByteFunction)   Function*/
	public static byte produceByte(float a1) {
		return defaultByte;
	}

	/** Does nothing (LFloatToCharFunction)   Function*/
	public static char produceChar(float a1) {
		return defaultCharacter;
	}

	/** Does nothing (LFloatToDoubleFunction)   Function*/
	public static double produceDouble(float a1) {
		return defaultDouble;
	}

	/** Does nothing (LFloatToIntFunction)   Function*/
	public static int produceInt(float a1) {
		return defaultInteger;
	}

	/** Does nothing (LFloatToLongFunction)   Function*/
	public static long produceLong(float a1) {
		return defaultLong;
	}

	/** Does nothing (LFloatToShortFunction)   Function*/
	public static short produceShort(float a1) {
		return defaultShort;
	}

	/** Does nothing (LIntToByteFunction)   Function*/
	public static byte produceByte(int a1) {
		return defaultByte;
	}

	/** Does nothing (LIntToCharFunction)   Function*/
	public static char produceChar(int a1) {
		return defaultCharacter;
	}

	/** Does nothing (LIntToDoubleFunction)   Function*/
	public static double produceDouble(int a1) {
		return defaultDouble;
	}

	/** Does nothing (LIntToFloatFunction)   Function*/
	public static float produceFloat(int a1) {
		return defaultFloat;
	}

	/** Does nothing (LIntToLongFunction)   Function*/
	public static long produceLong(int a1) {
		return defaultLong;
	}

	/** Does nothing (LIntToShortFunction)   Function*/
	public static short produceShort(int a1) {
		return defaultShort;
	}

	/** Does nothing (LLongToByteFunction)   Function*/
	public static byte produceByte(long a1) {
		return defaultByte;
	}

	/** Does nothing (LLongToCharFunction)   Function*/
	public static char produceChar(long a1) {
		return defaultCharacter;
	}

	/** Does nothing (LLongToDoubleFunction)   Function*/
	public static double produceDouble(long a1) {
		return defaultDouble;
	}

	/** Does nothing (LLongToFloatFunction)   Function*/
	public static float produceFloat(long a1) {
		return defaultFloat;
	}

	/** Does nothing (LLongToIntFunction)   Function*/
	public static int produceInt(long a1) {
		return defaultInteger;
	}

	/** Does nothing (LLongToShortFunction)   Function*/
	public static short produceShort(long a1) {
		return defaultShort;
	}

	/** Does nothing (LShortToByteFunction)   Function*/
	public static byte produceByte(short a1) {
		return defaultByte;
	}

	/** Does nothing (LShortToCharFunction)   Function*/
	public static char produceChar(short a1) {
		return defaultCharacter;
	}

	/** Does nothing (LShortToDoubleFunction)   Function*/
	public static double produceDouble(short a1) {
		return defaultDouble;
	}

	/** Does nothing (LShortToFloatFunction)   Function*/
	public static float produceFloat(short a1) {
		return defaultFloat;
	}

	/** Does nothing (LShortToIntFunction)   Function*/
	public static int produceInt(short a1) {
		return defaultInteger;
	}

	/** Does nothing (LShortToLongFunction)   Function*/
	public static long produceLong(short a1) {
		return defaultLong;
	}

	/** Does nothing (LBiBoolFunction)   Function*/
	public static <R> R produce(boolean a1, boolean a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiByteFunction)   Function*/
	public static <R> R produce(byte a1, byte a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiCharFunction)   Function*/
	public static <R> R produce(char a1, char a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiDoubleFunction)   Function*/
	public static <R> R produce(double a1, double a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiFloatFunction)   Function*/
	public static <R> R produce(float a1, float a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiIntFunction)   Function*/
	public static <R> R produce(int a1, int a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiLongFunction)   Function*/
	public static <R> R produce(long a1, long a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjBoolFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, boolean a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjBoolFunction.V1)   Function*/
	public static <T1, T2, R> R produce(T1 a1, boolean a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjBoolFunction.V4)   Function*/
	public static <T1, T2, R> R produce(boolean a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjByteFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, byte a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjByteFunction.V1)   Function*/
	public static <T1, T2, R> R produce(T1 a1, byte a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjByteFunction.V4)   Function*/
	public static <T1, T2, R> R produce(byte a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjCharFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, char a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjCharFunction.V1)   Function*/
	public static <T1, T2, R> R produce(T1 a1, char a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjCharFunction.V4)   Function*/
	public static <T1, T2, R> R produce(char a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjDoubleFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, double a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjDoubleFunction.V1)   Function*/
	public static <T1, T2, R> R produce(T1 a1, double a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjDoubleFunction.V4)   Function*/
	public static <T1, T2, R> R produce(double a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjFloatFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, float a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjFloatFunction.V1)   Function*/
	public static <T1, T2, R> R produce(T1 a1, float a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjFloatFunction.V4)   Function*/
	public static <T1, T2, R> R produce(float a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjIntFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, int a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjIntFunction.V1)   Function*/
	public static <T1, T2, R> R produce(T1 a1, int a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjIntFunction.V4)   Function*/
	public static <T1, T2, R> R produce(int a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjLongFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, long a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjLongFunction.V1)   Function*/
	public static <T1, T2, R> R produce(T1 a1, long a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjLongFunction.V4)   Function*/
	public static <T1, T2, R> R produce(long a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjShortFunction)   Function*/
	public static <T1, T2, R> R produce(T1 a1, T2 a2, short a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjShortFunction.V1)   Function*/
	public static <T1, T2, R> R produce(T1 a1, short a3, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiObjShortFunction.V4)   Function*/
	public static <T1, T2, R> R produce(short a3, T1 a1, T2 a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBiShortFunction)   Function*/
	public static <R> R produce(short a1, short a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LBoolFunction)   Function*/
	public static <R> R produce(boolean a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LByteFunction)   Function*/
	public static <R> R produce(byte a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LCharFunction)   Function*/
	public static <R> R produce(char a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LDoubleFunction)   Function*/
	public static <R> R produce(double a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LFloatFunction)   Function*/
	public static <R> R produce(float a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LIntFunction)   Function*/
	public static <R> R produce(int a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LLongFunction)   Function*/
	public static <R> R produce(long a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjBoolFunction)   Function*/
	public static <T, R> R produce(T a1, boolean a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjBoolFunction.V1)   Function*/
	public static <T, R> R produce(boolean a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjByteFunction)   Function*/
	public static <T, R> R produce(T a1, byte a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjByteFunction.V1)   Function*/
	public static <T, R> R produce(byte a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjCharFunction)   Function*/
	public static <T, R> R produce(T a1, char a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjCharFunction.V1)   Function*/
	public static <T, R> R produce(char a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjDoubleFunction)   Function*/
	public static <T, R> R produce(T a1, double a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjDoubleFunction.V1)   Function*/
	public static <T, R> R produce(double a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjFloatFunction)   Function*/
	public static <T, R> R produce(T a1, float a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjFloatFunction.V1)   Function*/
	public static <T, R> R produce(float a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjIntFunction)   Function*/
	public static <T, R> R produce(T a1, int a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjIntFunction.V1)   Function*/
	public static <T, R> R produce(int a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjLongFunction)   Function*/
	public static <T, R> R produce(T a1, long a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjLongFunction.V1)   Function*/
	public static <T, R> R produce(long a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjShortFunction)   Function*/
	public static <T, R> R produce(T a1, short a2) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjShortFunction.V1)   Function*/
	public static <T, R> R produce(short a2, T a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LShortFunction)   Function*/
	public static <R> R produce(short a1) {
		return (R) defaultObject;
	}

	/** Does nothing (LTriBoolFunction)   Function*/
	public static <R> R produce(boolean a1, boolean a2, boolean a3) {
		return (R) defaultObject;
	}

	/** Does nothing (LObjIntToIntFunction)   Function*/
	public static <T> int produceInt(T a1, int a2) {
		return defaultInteger;
	}

	/** Does nothing (LObjIntToIntFunction.V1)   Function*/
	public static <T> int produceInt(int a2, T a1) {
		return defaultInteger;
	}

	/** Does nothing (LToByteBiFunction)   Function*/
	public static <T1, T2> byte produceByte(T1 a1, T2 a2) {
		return defaultByte;
	}

	/** Does nothing (LToByteFunction)   Function*/
	public static <T> byte produceByte(T a1) {
		return defaultByte;
	}

	/** Does nothing (LToCharBiFunction)   Function*/
	public static <T1, T2> char produceChar(T1 a1, T2 a2) {
		return defaultCharacter;
	}

	/** Does nothing (LToCharFunction)   Function*/
	public static <T> char produceChar(T a1) {
		return defaultCharacter;
	}

	/** Does nothing (LToDoubleBiFunction)   Function*/
	public static <T1, T2> double produceDouble(T1 a1, T2 a2) {
		return defaultDouble;
	}

	/** Does nothing (LToDoubleFunction)   Function*/
	public static <T> double produceDouble(T a1) {
		return defaultDouble;
	}

	/** Does nothing (LToFloatBiFunction)   Function*/
	public static <T1, T2> float produceFloat(T1 a1, T2 a2) {
		return defaultFloat;
	}

	/** Does nothing (LToFloatFunction)   Function*/
	public static <T> float produceFloat(T a1) {
		return defaultFloat;
	}

	/** Does nothing (LToIntBiFunction)   Function*/
	public static <T1, T2> int produceInt(T1 a1, T2 a2) {
		return defaultInteger;
	}

	/** Does nothing (LToIntFunction)   Function*/
	public static <T> int produceInt(T a1) {
		return defaultInteger;
	}

	/** Does nothing (LToLongBiFunction)   Function*/
	public static <T1, T2> long produceLong(T1 a1, T2 a2) {
		return defaultLong;
	}

	/** Does nothing (LToLongFunction)   Function*/
	public static <T> long produceLong(T a1) {
		return defaultLong;
	}

	/** Does nothing (LToShortBiFunction)   Function*/
	public static <T1, T2> short produceShort(T1 a1, T2 a2) {
		return defaultShort;
	}

	/** Does nothing (LToShortFunction)   Function*/
	public static <T> short produceShort(T a1) {
		return defaultShort;
	}

	/** Does nothing (LByteBinaryOperator)   Operator*/
	public static byte produceByte(byte a1, byte a2) {
		return defaultByte;
	}

	/** Does nothing (LCharBinaryOperator)   Operator*/
	public static char produceChar(char a1, char a2) {
		return defaultCharacter;
	}

	/** Does nothing (LDoubleBinaryOperator)   Operator*/
	public static double produceDouble(double a1, double a2) {
		return defaultDouble;
	}

	/** Does nothing (LFloatBinaryOperator)   Operator*/
	public static float produceFloat(float a1, float a2) {
		return defaultFloat;
	}

	/** Does nothing (LIntBinaryOperator)   Operator*/
	public static int produceInt(int a1, int a2) {
		return defaultInteger;
	}

	/** Does nothing (LLogicalBinaryOperator)   Operator*/
	public static boolean produceBoolean(boolean a1, boolean a2) {
		return defaultBoolean;
	}

	/** Does nothing (LLongBinaryOperator)   Operator*/
	public static long produceLong(long a1, long a2) {
		return defaultLong;
	}

	/** Does nothing (LShortBinaryOperator)   Operator*/
	public static short produceShort(short a1, short a2) {
		return defaultShort;
	}

	/** Does nothing (LLogicalTernaryOperator)   Operator*/
	public static boolean produceBoolean(boolean a1, boolean a2, boolean a3) {
		return defaultBoolean;
	}

	/** Does nothing (LByteUnaryOperator)   Operator*/
	public static byte produceByte(byte a1) {
		return defaultByte;
	}

	/** Does nothing (LCharUnaryOperator)   Operator*/
	public static char produceChar(char a1) {
		return defaultCharacter;
	}

	/** Does nothing (LDoubleUnaryOperator)   Operator*/
	public static double produceDouble(double a1) {
		return defaultDouble;
	}

	/** Does nothing (LFloatUnaryOperator)   Operator*/
	public static float produceFloat(float a1) {
		return defaultFloat;
	}

	/** Does nothing (LIntUnaryOperator)   Operator*/
	public static int produceInt(int a1) {
		return defaultInteger;
	}

	/** Does nothing (LLogicalOperator)   Operator*/
	public static boolean produceBoolean(boolean a1) {
		return defaultBoolean;
	}

	/** Does nothing (LLongUnaryOperator)   Operator*/
	public static long produceLong(long a1) {
		return defaultLong;
	}

	/** Does nothing (LShortUnaryOperator)   Operator*/
	public static short produceShort(short a1) {
		return defaultShort;
	}

	// <editor-fold desc="predicate::method">

	// >>> LBiBytePredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte a1, byte a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte a1, byte a2) {
		return false;
	}

	// >>> LBiCharPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(char a1, char a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(char a1, char a2) {
		return false;
	}

	// >>> LBiDoublePredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(double a1, double a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(double a1, double a2) {
		return false;
	}

	// >>> LBiFloatPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(float a1, float a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(float a1, float a2) {
		return false;
	}

	// >>> LBiIntPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(int a1, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(int a1, int a2) {
		return false;
	}

	// >>> LBiLongPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(long a1, long a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(long a1, long a2) {
		return false;
	}

	// >>> LBiObjBoolPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, boolean a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, boolean a3) {
		return false;
	}

	// >>> LBiObjBytePredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, byte a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, byte a3) {
		return false;
	}

	// >>> LBiObjCharPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, char a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, char a3) {
		return false;
	}

	// >>> LBiObjDoublePredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, double a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, double a3) {
		return false;
	}

	// >>> LBiObjFloatPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, float a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, float a3) {
		return false;
	}

	// >>> LBiObjIntPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, int a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, int a3) {
		return false;
	}

	// >>> LBiObjLongPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, long a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, long a3) {
		return false;
	}

	// >>> LBiObjShortPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, short a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, short a3) {
		return false;
	}

	// >>> LBiPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2) {
		return false;
	}

	// >>> LBiShortPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(short a1, short a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(short a1, short a2) {
		return false;
	}

	// >>> LBytePredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte a1) {
		return false;
	}

	// >>> LCharPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(char a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(char a1) {
		return false;
	}

	// >>> LDoublePredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(double a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(double a1) {
		return false;
	}

	// >>> LFloatPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(float a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(float a1) {
		return false;
	}

	// >>> LIntPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(int a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(int a1) {
		return false;
	}

	// >>> LLongPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(long a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(long a1) {
		return false;
	}

	// >>> LObjBoolPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, boolean a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, boolean a2) {
		return false;
	}

	// >>> LObjBytePredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, byte a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, byte a2) {
		return false;
	}

	// >>> LObjCharPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, char a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, char a2) {
		return false;
	}

	// >>> LObjDoublePredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, double a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, double a2) {
		return false;
	}

	// >>> LObjFloatPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, float a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, float a2) {
		return false;
	}

	// >>> LObjIntPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, int a2) {
		return false;
	}

	// >>> LObjLongPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, long a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, long a2) {
		return false;
	}

	// >>> LObjShortPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, short a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, short a2) {
		return false;
	}

	// >>> LPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1) {
		return false;
	}

	// >>> LShortPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(short a1) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(short a1) {
		return false;
	}

	// >>> LTriPredicate<T1,T2,T3>

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
	public static Runnable l(final Runnable lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static Runnable runnable(final Runnable lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> BiConsumer l(final BiConsumer<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> BiConsumer biConsumer(final BiConsumer<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Consumer l(final Consumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Consumer consumer(final Consumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleConsumer l(final DoubleConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleConsumer doubleConsumer(final DoubleConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntConsumer l(final IntConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntConsumer intConsumer(final IntConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongConsumer l(final LongConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongConsumer longConsumer(final LongConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjDoubleConsumer l(final ObjDoubleConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjDoubleConsumer objDoubleConsumer(final ObjDoubleConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjIntConsumer l(final ObjIntConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjIntConsumer objIntConsumer(final ObjIntConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjLongConsumer l(final ObjLongConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjLongConsumer objLongConsumer(final ObjLongConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> BinaryOperator l(final BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> BinaryOperator binaryOperator(final BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleBinaryOperator l(final DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleUnaryOperator l(final DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntBinaryOperator l(final IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntBinaryOperator intBinaryOperator(final IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntUnaryOperator l(final IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntUnaryOperator intUnaryOperator(final IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongBinaryOperator l(final LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongBinaryOperator longBinaryOperator(final LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongUnaryOperator l(final LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongUnaryOperator longUnaryOperator(final LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> UnaryOperator l(final UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> UnaryOperator unaryOperator(final UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2, R> BiFunction l(final BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2, R> BiFunction biFunction(final BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> DoubleFunction l(final DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> DoubleFunction doubleFunction(final DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleToIntFunction l(final DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleToLongFunction l(final DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T, R> Function l(final Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T, R> Function function(final Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> IntFunction l(final IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> IntFunction intFunction(final IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntToDoubleFunction l(final IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntToLongFunction l(final IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntToLongFunction intToLongFunction(final IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> LongFunction l(final LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> LongFunction longFunction(final LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongToDoubleFunction l(final LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongToIntFunction l(final LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongToIntFunction longToIntFunction(final LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToDoubleBiFunction l(final ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToDoubleBiFunction toDoubleBiFunction(final ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToDoubleFunction l(final ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToDoubleFunction toDoubleFunction(final ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToIntBiFunction l(final ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToIntBiFunction toIntBiFunction(final ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToIntFunction l(final ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToIntFunction toIntFunction(final ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToLongBiFunction l(final ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToLongBiFunction toLongBiFunction(final ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToLongFunction l(final ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToLongFunction toLongFunction(final ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> BiPredicate l(final BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> BiPredicate biPredicate(final BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoublePredicate l(final DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoublePredicate doublePredicate(final DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntPredicate l(final IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntPredicate intPredicate(final IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongPredicate l(final LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongPredicate longPredicate(final LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Predicate l(final Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Predicate predicate(final Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static BooleanSupplier l(final BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static BooleanSupplier booleanSupplier(final BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleSupplier l(final DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleSupplier doubleSupplier(final DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntSupplier l(final IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntSupplier intSupplier(final IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongSupplier l(final LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongSupplier longSupplier(final LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Supplier l(final Supplier<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Supplier supplier(final Supplier<T> lambda) {
		return lambda;
	}

	// </editor-fold>

}