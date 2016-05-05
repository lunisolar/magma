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

package eu.lunisolar.magma.struct.tuple;

import eu.lunisolar.magma.basics.meta.LTuple;

@SuppressWarnings("UnusedDeclaration")
public final class Tuple4U {

	public static <T1, T2> LPair.MutPair<T1, T2> tuple(T1 a1, T2 a2) {
		return LPair.MutPair.of(a1, a2);
	}

	public static <T1, T2> LPair.MutPair<T1, T2> pair(T1 a1, T2 a2) {
		return LPair.MutPair.of(a1, a2);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LPair.MutCompPair<T1, T2> comparable(T1 a1, T2 a2) {
		return LPair.MutCompPair.of(a1, a2);
	}

	public static <T1, T2> LPair<T1, T2> immutable(T1 a1, T2 a2) {
		return LPair.ImmPair.of(a1, a2);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LPair.ComparablePair immComp(T1 a1, T2 a2) {
		return LPair.ImmCompPair.of(a1, a2);
	}

	public static <T> LSingle.MutSingle<T> tuple(T a1) {
		return LSingle.MutSingle.of(a1);
	}

	public static <T> LSingle.MutSingle<T> single(T a1) {
		return LSingle.MutSingle.of(a1);
	}

	public static <T extends Comparable<T>> LSingle.MutCompSingle<T> comparable(T a1) {
		return LSingle.MutCompSingle.of(a1);
	}

	public static <T> LSingle<T> immutable(T a1) {
		return LSingle.ImmSingle.of(a1);
	}

	public static <T extends Comparable<T>> LSingle.ComparableSingle immComp(T a1) {
		return LSingle.ImmCompSingle.of(a1);
	}

	public static <T1, T2, T3> LTriple.MutTriple<T1, T2, T3> tuple(T1 a1, T2 a2, T3 a3) {
		return LTriple.MutTriple.of(a1, a2, a3);
	}

	public static <T1, T2, T3> LTriple.MutTriple<T1, T2, T3> triple(T1 a1, T2 a2, T3 a3) {
		return LTriple.MutTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> LTriple.MutCompTriple<T1, T2, T3> comparable(T1 a1, T2 a2, T3 a3) {
		return LTriple.MutCompTriple.of(a1, a2, a3);
	}

	public static <T1, T2, T3> LTriple<T1, T2, T3> immutable(T1 a1, T2 a2, T3 a3) {
		return LTriple.ImmTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> LTriple.ComparableTriple immComp(T1 a1, T2 a2, T3 a3) {
		return LTriple.ImmCompTriple.of(a1, a2, a3);
	}

	public static LBoolSingle.MutBoolSingle tuple(boolean a1) {
		return LBoolSingle.MutBoolSingle.of(a1);
	}

	public static LBoolSingle.MutBoolSingle boolSingle(boolean a1) {
		return LBoolSingle.MutBoolSingle.of(a1);
	}

	public static LBoolSingle.MutCompBoolSingle comparable(boolean a1) {
		return LBoolSingle.MutCompBoolSingle.of(a1);
	}

	public static LBoolSingle immutable(boolean a1) {
		return LBoolSingle.ImmBoolSingle.of(a1);
	}

	public static LBoolSingle.ComparableBoolSingle immComp(boolean a1) {
		return LBoolSingle.ImmCompBoolSingle.of(a1);
	}

	public static LByteSingle.MutByteSingle tuple(byte a1) {
		return LByteSingle.MutByteSingle.of(a1);
	}

	public static LByteSingle.MutByteSingle byteSingle(byte a1) {
		return LByteSingle.MutByteSingle.of(a1);
	}

	public static LByteSingle.MutCompByteSingle comparable(byte a1) {
		return LByteSingle.MutCompByteSingle.of(a1);
	}

	public static LByteSingle immutable(byte a1) {
		return LByteSingle.ImmByteSingle.of(a1);
	}

	public static LByteSingle.ComparableByteSingle immComp(byte a1) {
		return LByteSingle.ImmCompByteSingle.of(a1);
	}

	public static LCharSingle.MutCharSingle tuple(char a1) {
		return LCharSingle.MutCharSingle.of(a1);
	}

	public static LCharSingle.MutCharSingle charSingle(char a1) {
		return LCharSingle.MutCharSingle.of(a1);
	}

	public static LCharSingle.MutCompCharSingle comparable(char a1) {
		return LCharSingle.MutCompCharSingle.of(a1);
	}

	public static LCharSingle immutable(char a1) {
		return LCharSingle.ImmCharSingle.of(a1);
	}

	public static LCharSingle.ComparableCharSingle immComp(char a1) {
		return LCharSingle.ImmCompCharSingle.of(a1);
	}

	public static LDoubleSingle.MutDoubleSingle tuple(double a1) {
		return LDoubleSingle.MutDoubleSingle.of(a1);
	}

	public static LDoubleSingle.MutDoubleSingle doubleSingle(double a1) {
		return LDoubleSingle.MutDoubleSingle.of(a1);
	}

	public static LDoubleSingle.MutCompDoubleSingle comparable(double a1) {
		return LDoubleSingle.MutCompDoubleSingle.of(a1);
	}

	public static LDoubleSingle immutable(double a1) {
		return LDoubleSingle.ImmDoubleSingle.of(a1);
	}

	public static LDoubleSingle.ComparableDoubleSingle immComp(double a1) {
		return LDoubleSingle.ImmCompDoubleSingle.of(a1);
	}

	public static LFloatSingle.MutFloatSingle tuple(float a1) {
		return LFloatSingle.MutFloatSingle.of(a1);
	}

	public static LFloatSingle.MutFloatSingle floatSingle(float a1) {
		return LFloatSingle.MutFloatSingle.of(a1);
	}

	public static LFloatSingle.MutCompFloatSingle comparable(float a1) {
		return LFloatSingle.MutCompFloatSingle.of(a1);
	}

	public static LFloatSingle immutable(float a1) {
		return LFloatSingle.ImmFloatSingle.of(a1);
	}

	public static LFloatSingle.ComparableFloatSingle immComp(float a1) {
		return LFloatSingle.ImmCompFloatSingle.of(a1);
	}

	public static LIntSingle.MutIntSingle tuple(int a1) {
		return LIntSingle.MutIntSingle.of(a1);
	}

	public static LIntSingle.MutIntSingle intSingle(int a1) {
		return LIntSingle.MutIntSingle.of(a1);
	}

	public static LIntSingle.MutCompIntSingle comparable(int a1) {
		return LIntSingle.MutCompIntSingle.of(a1);
	}

	public static LIntSingle immutable(int a1) {
		return LIntSingle.ImmIntSingle.of(a1);
	}

	public static LIntSingle.ComparableIntSingle immComp(int a1) {
		return LIntSingle.ImmCompIntSingle.of(a1);
	}

	public static LLongSingle.MutLongSingle tuple(long a1) {
		return LLongSingle.MutLongSingle.of(a1);
	}

	public static LLongSingle.MutLongSingle longSingle(long a1) {
		return LLongSingle.MutLongSingle.of(a1);
	}

	public static LLongSingle.MutCompLongSingle comparable(long a1) {
		return LLongSingle.MutCompLongSingle.of(a1);
	}

	public static LLongSingle immutable(long a1) {
		return LLongSingle.ImmLongSingle.of(a1);
	}

	public static LLongSingle.ComparableLongSingle immComp(long a1) {
		return LLongSingle.ImmCompLongSingle.of(a1);
	}

	public static LShortSingle.MutShortSingle tuple(short a1) {
		return LShortSingle.MutShortSingle.of(a1);
	}

	public static LShortSingle.MutShortSingle shortSingle(short a1) {
		return LShortSingle.MutShortSingle.of(a1);
	}

	public static LShortSingle.MutCompShortSingle comparable(short a1) {
		return LShortSingle.MutCompShortSingle.of(a1);
	}

	public static LShortSingle immutable(short a1) {
		return LShortSingle.ImmShortSingle.of(a1);
	}

	public static LShortSingle.ComparableShortSingle immComp(short a1) {
		return LShortSingle.ImmCompShortSingle.of(a1);
	}

	public static LBoolPair.MutBoolPair tuple(boolean a1, boolean a2) {
		return LBoolPair.MutBoolPair.of(a1, a2);
	}

	public static LBoolPair.MutBoolPair boolPair(boolean a1, boolean a2) {
		return LBoolPair.MutBoolPair.of(a1, a2);
	}

	public static LBoolPair.MutCompBoolPair comparable(boolean a1, boolean a2) {
		return LBoolPair.MutCompBoolPair.of(a1, a2);
	}

	public static LBoolPair immutable(boolean a1, boolean a2) {
		return LBoolPair.ImmBoolPair.of(a1, a2);
	}

	public static LBoolPair.ComparableBoolPair immComp(boolean a1, boolean a2) {
		return LBoolPair.ImmCompBoolPair.of(a1, a2);
	}

	public static LBytePair.MutBytePair tuple(byte a1, byte a2) {
		return LBytePair.MutBytePair.of(a1, a2);
	}

	public static LBytePair.MutBytePair bytePair(byte a1, byte a2) {
		return LBytePair.MutBytePair.of(a1, a2);
	}

	public static LBytePair.MutCompBytePair comparable(byte a1, byte a2) {
		return LBytePair.MutCompBytePair.of(a1, a2);
	}

	public static LBytePair immutable(byte a1, byte a2) {
		return LBytePair.ImmBytePair.of(a1, a2);
	}

	public static LBytePair.ComparableBytePair immComp(byte a1, byte a2) {
		return LBytePair.ImmCompBytePair.of(a1, a2);
	}

	public static LCharPair.MutCharPair tuple(char a1, char a2) {
		return LCharPair.MutCharPair.of(a1, a2);
	}

	public static LCharPair.MutCharPair charPair(char a1, char a2) {
		return LCharPair.MutCharPair.of(a1, a2);
	}

	public static LCharPair.MutCompCharPair comparable(char a1, char a2) {
		return LCharPair.MutCompCharPair.of(a1, a2);
	}

	public static LCharPair immutable(char a1, char a2) {
		return LCharPair.ImmCharPair.of(a1, a2);
	}

	public static LCharPair.ComparableCharPair immComp(char a1, char a2) {
		return LCharPair.ImmCompCharPair.of(a1, a2);
	}

	public static LDoublePair.MutDoublePair tuple(double a1, double a2) {
		return LDoublePair.MutDoublePair.of(a1, a2);
	}

	public static LDoublePair.MutDoublePair doublePair(double a1, double a2) {
		return LDoublePair.MutDoublePair.of(a1, a2);
	}

	public static LDoublePair.MutCompDoublePair comparable(double a1, double a2) {
		return LDoublePair.MutCompDoublePair.of(a1, a2);
	}

	public static LDoublePair immutable(double a1, double a2) {
		return LDoublePair.ImmDoublePair.of(a1, a2);
	}

	public static LDoublePair.ComparableDoublePair immComp(double a1, double a2) {
		return LDoublePair.ImmCompDoublePair.of(a1, a2);
	}

	public static LFloatPair.MutFloatPair tuple(float a1, float a2) {
		return LFloatPair.MutFloatPair.of(a1, a2);
	}

	public static LFloatPair.MutFloatPair floatPair(float a1, float a2) {
		return LFloatPair.MutFloatPair.of(a1, a2);
	}

	public static LFloatPair.MutCompFloatPair comparable(float a1, float a2) {
		return LFloatPair.MutCompFloatPair.of(a1, a2);
	}

	public static LFloatPair immutable(float a1, float a2) {
		return LFloatPair.ImmFloatPair.of(a1, a2);
	}

	public static LFloatPair.ComparableFloatPair immComp(float a1, float a2) {
		return LFloatPair.ImmCompFloatPair.of(a1, a2);
	}

	public static LIntPair.MutIntPair tuple(int a1, int a2) {
		return LIntPair.MutIntPair.of(a1, a2);
	}

	public static LIntPair.MutIntPair intPair(int a1, int a2) {
		return LIntPair.MutIntPair.of(a1, a2);
	}

	public static LIntPair.MutCompIntPair comparable(int a1, int a2) {
		return LIntPair.MutCompIntPair.of(a1, a2);
	}

	public static LIntPair immutable(int a1, int a2) {
		return LIntPair.ImmIntPair.of(a1, a2);
	}

	public static LIntPair.ComparableIntPair immComp(int a1, int a2) {
		return LIntPair.ImmCompIntPair.of(a1, a2);
	}

	public static LLongPair.MutLongPair tuple(long a1, long a2) {
		return LLongPair.MutLongPair.of(a1, a2);
	}

	public static LLongPair.MutLongPair longPair(long a1, long a2) {
		return LLongPair.MutLongPair.of(a1, a2);
	}

	public static LLongPair.MutCompLongPair comparable(long a1, long a2) {
		return LLongPair.MutCompLongPair.of(a1, a2);
	}

	public static LLongPair immutable(long a1, long a2) {
		return LLongPair.ImmLongPair.of(a1, a2);
	}

	public static LLongPair.ComparableLongPair immComp(long a1, long a2) {
		return LLongPair.ImmCompLongPair.of(a1, a2);
	}

	public static LShortPair.MutShortPair tuple(short a1, short a2) {
		return LShortPair.MutShortPair.of(a1, a2);
	}

	public static LShortPair.MutShortPair shortPair(short a1, short a2) {
		return LShortPair.MutShortPair.of(a1, a2);
	}

	public static LShortPair.MutCompShortPair comparable(short a1, short a2) {
		return LShortPair.MutCompShortPair.of(a1, a2);
	}

	public static LShortPair immutable(short a1, short a2) {
		return LShortPair.ImmShortPair.of(a1, a2);
	}

	public static LShortPair.ComparableShortPair immComp(short a1, short a2) {
		return LShortPair.ImmCompShortPair.of(a1, a2);
	}

	public static <T1, T2> LBiObjBoolTriple.MutBiObjBoolTriple<T1, T2> tuple(T1 a1, T2 a2, boolean a3) {
		return LBiObjBoolTriple.MutBiObjBoolTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjBoolTriple.MutBiObjBoolTriple<T1, T2> biObjBoolTriple(T1 a1, T2 a2, boolean a3) {
		return LBiObjBoolTriple.MutBiObjBoolTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjBoolTriple.MutCompBiObjBoolTriple<T1, T2> comparable(T1 a1, T2 a2, boolean a3) {
		return LBiObjBoolTriple.MutCompBiObjBoolTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjBoolTriple<T1, T2> immutable(T1 a1, T2 a2, boolean a3) {
		return LBiObjBoolTriple.ImmBiObjBoolTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjBoolTriple.ComparableBiObjBoolTriple immComp(T1 a1, T2 a2, boolean a3) {
		return LBiObjBoolTriple.ImmCompBiObjBoolTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjByteTriple.MutBiObjByteTriple<T1, T2> tuple(T1 a1, T2 a2, byte a3) {
		return LBiObjByteTriple.MutBiObjByteTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjByteTriple.MutBiObjByteTriple<T1, T2> biObjByteTriple(T1 a1, T2 a2, byte a3) {
		return LBiObjByteTriple.MutBiObjByteTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjByteTriple.MutCompBiObjByteTriple<T1, T2> comparable(T1 a1, T2 a2, byte a3) {
		return LBiObjByteTriple.MutCompBiObjByteTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjByteTriple<T1, T2> immutable(T1 a1, T2 a2, byte a3) {
		return LBiObjByteTriple.ImmBiObjByteTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjByteTriple.ComparableBiObjByteTriple immComp(T1 a1, T2 a2, byte a3) {
		return LBiObjByteTriple.ImmCompBiObjByteTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjCharTriple.MutBiObjCharTriple<T1, T2> tuple(T1 a1, T2 a2, char a3) {
		return LBiObjCharTriple.MutBiObjCharTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjCharTriple.MutBiObjCharTriple<T1, T2> biObjCharTriple(T1 a1, T2 a2, char a3) {
		return LBiObjCharTriple.MutBiObjCharTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjCharTriple.MutCompBiObjCharTriple<T1, T2> comparable(T1 a1, T2 a2, char a3) {
		return LBiObjCharTriple.MutCompBiObjCharTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjCharTriple<T1, T2> immutable(T1 a1, T2 a2, char a3) {
		return LBiObjCharTriple.ImmBiObjCharTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjCharTriple.ComparableBiObjCharTriple immComp(T1 a1, T2 a2, char a3) {
		return LBiObjCharTriple.ImmCompBiObjCharTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjDoubleTriple.MutBiObjDoubleTriple<T1, T2> tuple(T1 a1, T2 a2, double a3) {
		return LBiObjDoubleTriple.MutBiObjDoubleTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjDoubleTriple.MutBiObjDoubleTriple<T1, T2> biObjDoubleTriple(T1 a1, T2 a2, double a3) {
		return LBiObjDoubleTriple.MutBiObjDoubleTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjDoubleTriple.MutCompBiObjDoubleTriple<T1, T2> comparable(T1 a1, T2 a2, double a3) {
		return LBiObjDoubleTriple.MutCompBiObjDoubleTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjDoubleTriple<T1, T2> immutable(T1 a1, T2 a2, double a3) {
		return LBiObjDoubleTriple.ImmBiObjDoubleTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjDoubleTriple.ComparableBiObjDoubleTriple immComp(T1 a1, T2 a2, double a3) {
		return LBiObjDoubleTriple.ImmCompBiObjDoubleTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFloatTriple.MutBiObjFloatTriple<T1, T2> tuple(T1 a1, T2 a2, float a3) {
		return LBiObjFloatTriple.MutBiObjFloatTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFloatTriple.MutBiObjFloatTriple<T1, T2> biObjFloatTriple(T1 a1, T2 a2, float a3) {
		return LBiObjFloatTriple.MutBiObjFloatTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjFloatTriple.MutCompBiObjFloatTriple<T1, T2> comparable(T1 a1, T2 a2, float a3) {
		return LBiObjFloatTriple.MutCompBiObjFloatTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFloatTriple<T1, T2> immutable(T1 a1, T2 a2, float a3) {
		return LBiObjFloatTriple.ImmBiObjFloatTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjFloatTriple.ComparableBiObjFloatTriple immComp(T1 a1, T2 a2, float a3) {
		return LBiObjFloatTriple.ImmCompBiObjFloatTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjIntTriple.MutBiObjIntTriple<T1, T2> tuple(T1 a1, T2 a2, int a3) {
		return LBiObjIntTriple.MutBiObjIntTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjIntTriple.MutBiObjIntTriple<T1, T2> biObjIntTriple(T1 a1, T2 a2, int a3) {
		return LBiObjIntTriple.MutBiObjIntTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjIntTriple.MutCompBiObjIntTriple<T1, T2> comparable(T1 a1, T2 a2, int a3) {
		return LBiObjIntTriple.MutCompBiObjIntTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjIntTriple<T1, T2> immutable(T1 a1, T2 a2, int a3) {
		return LBiObjIntTriple.ImmBiObjIntTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjIntTriple.ComparableBiObjIntTriple immComp(T1 a1, T2 a2, int a3) {
		return LBiObjIntTriple.ImmCompBiObjIntTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjLongTriple.MutBiObjLongTriple<T1, T2> tuple(T1 a1, T2 a2, long a3) {
		return LBiObjLongTriple.MutBiObjLongTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjLongTriple.MutBiObjLongTriple<T1, T2> biObjLongTriple(T1 a1, T2 a2, long a3) {
		return LBiObjLongTriple.MutBiObjLongTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjLongTriple.MutCompBiObjLongTriple<T1, T2> comparable(T1 a1, T2 a2, long a3) {
		return LBiObjLongTriple.MutCompBiObjLongTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjLongTriple<T1, T2> immutable(T1 a1, T2 a2, long a3) {
		return LBiObjLongTriple.ImmBiObjLongTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjLongTriple.ComparableBiObjLongTriple immComp(T1 a1, T2 a2, long a3) {
		return LBiObjLongTriple.ImmCompBiObjLongTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjShortTriple.MutBiObjShortTriple<T1, T2> tuple(T1 a1, T2 a2, short a3) {
		return LBiObjShortTriple.MutBiObjShortTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjShortTriple.MutBiObjShortTriple<T1, T2> biObjShortTriple(T1 a1, T2 a2, short a3) {
		return LBiObjShortTriple.MutBiObjShortTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjShortTriple.MutCompBiObjShortTriple<T1, T2> comparable(T1 a1, T2 a2, short a3) {
		return LBiObjShortTriple.MutCompBiObjShortTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjShortTriple<T1, T2> immutable(T1 a1, T2 a2, short a3) {
		return LBiObjShortTriple.ImmBiObjShortTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjShortTriple.ComparableBiObjShortTriple immComp(T1 a1, T2 a2, short a3) {
		return LBiObjShortTriple.ImmCompBiObjShortTriple.of(a1, a2, a3);
	}

	public static <T> LObjBoolPair.MutObjBoolPair<T> tuple(T a1, boolean a2) {
		return LObjBoolPair.MutObjBoolPair.of(a1, a2);
	}

	public static <T> LObjBoolPair.MutObjBoolPair<T> objBoolPair(T a1, boolean a2) {
		return LObjBoolPair.MutObjBoolPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjBoolPair.MutCompObjBoolPair<T> comparable(T a1, boolean a2) {
		return LObjBoolPair.MutCompObjBoolPair.of(a1, a2);
	}

	public static <T> LObjBoolPair<T> immutable(T a1, boolean a2) {
		return LObjBoolPair.ImmObjBoolPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjBoolPair.ComparableObjBoolPair immComp(T a1, boolean a2) {
		return LObjBoolPair.ImmCompObjBoolPair.of(a1, a2);
	}

	public static <T> LObjBytePair.MutObjBytePair<T> tuple(T a1, byte a2) {
		return LObjBytePair.MutObjBytePair.of(a1, a2);
	}

	public static <T> LObjBytePair.MutObjBytePair<T> objBytePair(T a1, byte a2) {
		return LObjBytePair.MutObjBytePair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjBytePair.MutCompObjBytePair<T> comparable(T a1, byte a2) {
		return LObjBytePair.MutCompObjBytePair.of(a1, a2);
	}

	public static <T> LObjBytePair<T> immutable(T a1, byte a2) {
		return LObjBytePair.ImmObjBytePair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjBytePair.ComparableObjBytePair immComp(T a1, byte a2) {
		return LObjBytePair.ImmCompObjBytePair.of(a1, a2);
	}

	public static <T> LObjCharPair.MutObjCharPair<T> tuple(T a1, char a2) {
		return LObjCharPair.MutObjCharPair.of(a1, a2);
	}

	public static <T> LObjCharPair.MutObjCharPair<T> objCharPair(T a1, char a2) {
		return LObjCharPair.MutObjCharPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjCharPair.MutCompObjCharPair<T> comparable(T a1, char a2) {
		return LObjCharPair.MutCompObjCharPair.of(a1, a2);
	}

	public static <T> LObjCharPair<T> immutable(T a1, char a2) {
		return LObjCharPair.ImmObjCharPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjCharPair.ComparableObjCharPair immComp(T a1, char a2) {
		return LObjCharPair.ImmCompObjCharPair.of(a1, a2);
	}

	public static <T> LObjDoublePair.MutObjDoublePair<T> tuple(T a1, double a2) {
		return LObjDoublePair.MutObjDoublePair.of(a1, a2);
	}

	public static <T> LObjDoublePair.MutObjDoublePair<T> objDoublePair(T a1, double a2) {
		return LObjDoublePair.MutObjDoublePair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjDoublePair.MutCompObjDoublePair<T> comparable(T a1, double a2) {
		return LObjDoublePair.MutCompObjDoublePair.of(a1, a2);
	}

	public static <T> LObjDoublePair<T> immutable(T a1, double a2) {
		return LObjDoublePair.ImmObjDoublePair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjDoublePair.ComparableObjDoublePair immComp(T a1, double a2) {
		return LObjDoublePair.ImmCompObjDoublePair.of(a1, a2);
	}

	public static <T> LObjFloatPair.MutObjFloatPair<T> tuple(T a1, float a2) {
		return LObjFloatPair.MutObjFloatPair.of(a1, a2);
	}

	public static <T> LObjFloatPair.MutObjFloatPair<T> objFloatPair(T a1, float a2) {
		return LObjFloatPair.MutObjFloatPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjFloatPair.MutCompObjFloatPair<T> comparable(T a1, float a2) {
		return LObjFloatPair.MutCompObjFloatPair.of(a1, a2);
	}

	public static <T> LObjFloatPair<T> immutable(T a1, float a2) {
		return LObjFloatPair.ImmObjFloatPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjFloatPair.ComparableObjFloatPair immComp(T a1, float a2) {
		return LObjFloatPair.ImmCompObjFloatPair.of(a1, a2);
	}

	public static <T> LObjIntPair.MutObjIntPair<T> tuple(T a1, int a2) {
		return LObjIntPair.MutObjIntPair.of(a1, a2);
	}

	public static <T> LObjIntPair.MutObjIntPair<T> objIntPair(T a1, int a2) {
		return LObjIntPair.MutObjIntPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjIntPair.MutCompObjIntPair<T> comparable(T a1, int a2) {
		return LObjIntPair.MutCompObjIntPair.of(a1, a2);
	}

	public static <T> LObjIntPair<T> immutable(T a1, int a2) {
		return LObjIntPair.ImmObjIntPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjIntPair.ComparableObjIntPair immComp(T a1, int a2) {
		return LObjIntPair.ImmCompObjIntPair.of(a1, a2);
	}

	public static <T> LObjLongPair.MutObjLongPair<T> tuple(T a1, long a2) {
		return LObjLongPair.MutObjLongPair.of(a1, a2);
	}

	public static <T> LObjLongPair.MutObjLongPair<T> objLongPair(T a1, long a2) {
		return LObjLongPair.MutObjLongPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjLongPair.MutCompObjLongPair<T> comparable(T a1, long a2) {
		return LObjLongPair.MutCompObjLongPair.of(a1, a2);
	}

	public static <T> LObjLongPair<T> immutable(T a1, long a2) {
		return LObjLongPair.ImmObjLongPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjLongPair.ComparableObjLongPair immComp(T a1, long a2) {
		return LObjLongPair.ImmCompObjLongPair.of(a1, a2);
	}

	public static <T> LObjShortPair.MutObjShortPair<T> tuple(T a1, short a2) {
		return LObjShortPair.MutObjShortPair.of(a1, a2);
	}

	public static <T> LObjShortPair.MutObjShortPair<T> objShortPair(T a1, short a2) {
		return LObjShortPair.MutObjShortPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjShortPair.MutCompObjShortPair<T> comparable(T a1, short a2) {
		return LObjShortPair.MutCompObjShortPair.of(a1, a2);
	}

	public static <T> LObjShortPair<T> immutable(T a1, short a2) {
		return LObjShortPair.ImmObjShortPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjShortPair.ComparableObjShortPair immComp(T a1, short a2) {
		return LObjShortPair.ImmCompObjShortPair.of(a1, a2);
	}

	public static LBoolTriple.MutBoolTriple tuple(boolean a1, boolean a2, boolean a3) {
		return LBoolTriple.MutBoolTriple.of(a1, a2, a3);
	}

	public static LBoolTriple.MutBoolTriple boolTriple(boolean a1, boolean a2, boolean a3) {
		return LBoolTriple.MutBoolTriple.of(a1, a2, a3);
	}

	public static LBoolTriple.MutCompBoolTriple comparable(boolean a1, boolean a2, boolean a3) {
		return LBoolTriple.MutCompBoolTriple.of(a1, a2, a3);
	}

	public static LBoolTriple immutable(boolean a1, boolean a2, boolean a3) {
		return LBoolTriple.ImmBoolTriple.of(a1, a2, a3);
	}

	public static LBoolTriple.ComparableBoolTriple immComp(boolean a1, boolean a2, boolean a3) {
		return LBoolTriple.ImmCompBoolTriple.of(a1, a2, a3);
	}

	public static LTuple.Void tuple() {
		return LTuple.Void.INSTANCE;
	}

	private Tuple4U() {

	}
}