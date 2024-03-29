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

package eu.lunisolar.magma.func.tuple;

import eu.lunisolar.magma.basics.meta.LTuple;

@SuppressWarnings("UnusedDeclaration")
public final class Tuple4U {

	public static <T1, T2> LPair.MutPair<T1, T2> tuple(T1 a1, T2 a2) {
		return LPair.of(a1, a2);
	}

	public static <T1, T2> LPair.MutPair<T1, T2> pair(T1 a1, T2 a2) {
		return LPair.of(a1, a2);
	}

	public static <T1, T2> LPair<T1, T2> immutable(T1 a1, T2 a2) {
		return LPair.immutableOf(a1, a2);
	}

	public static <T> LSingle.MutSingle<T> tuple(T a) {
		return LSingle.of(a);
	}

	public static <T> LSingle.MutSingle<T> single(T a) {
		return LSingle.of(a);
	}

	public static <T> LSingle<T> immutable(T a) {
		return LSingle.immutableOf(a);
	}

	public static <T1, T2, T3, T4> LQuad.MutQuad<T1, T2, T3, T4> tuple(T1 a1, T2 a2, T3 a3, T4 a4) {
		return LQuad.of(a1, a2, a3, a4);
	}

	public static <T1, T2, T3, T4> LQuad.MutQuad<T1, T2, T3, T4> quad(T1 a1, T2 a2, T3 a3, T4 a4) {
		return LQuad.of(a1, a2, a3, a4);
	}

	public static <T1, T2, T3, T4> LQuad<T1, T2, T3, T4> immutable(T1 a1, T2 a2, T3 a3, T4 a4) {
		return LQuad.immutableOf(a1, a2, a3, a4);
	}

	public static <T1, T2, T3, T4, T5> LQuint.MutQuint<T1, T2, T3, T4, T5> tuple(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		return LQuint.of(a1, a2, a3, a4, a5);
	}

	public static <T1, T2, T3, T4, T5> LQuint.MutQuint<T1, T2, T3, T4, T5> quint(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		return LQuint.of(a1, a2, a3, a4, a5);
	}

	public static <T1, T2, T3, T4, T5> LQuint<T1, T2, T3, T4, T5> immutable(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		return LQuint.immutableOf(a1, a2, a3, a4, a5);
	}

	public static <T1, T2, T3> LTriple.MutTriple<T1, T2, T3> tuple(T1 a1, T2 a2, T3 a3) {
		return LTriple.of(a1, a2, a3);
	}

	public static <T1, T2, T3> LTriple.MutTriple<T1, T2, T3> triple(T1 a1, T2 a2, T3 a3) {
		return LTriple.of(a1, a2, a3);
	}

	public static <T1, T2, T3> LTriple<T1, T2, T3> immutable(T1 a1, T2 a2, T3 a3) {
		return LTriple.immutableOf(a1, a2, a3);
	}

	public static LBoolSingle.MutBoolSingle tuple(boolean a) {
		return LBoolSingle.of(a);
	}

	public static LBoolSingle.MutBoolSingle boolSingle(boolean a) {
		return LBoolSingle.of(a);
	}

	public static LBoolSingle immutable(boolean a) {
		return LBoolSingle.immutableOf(a);
	}

	public static LByteSingle.MutByteSingle tuple(byte a) {
		return LByteSingle.of(a);
	}

	public static LByteSingle.MutByteSingle byteSingle(byte a) {
		return LByteSingle.of(a);
	}

	public static LByteSingle immutable(byte a) {
		return LByteSingle.immutableOf(a);
	}

	public static LCharSingle.MutCharSingle tuple(char a) {
		return LCharSingle.of(a);
	}

	public static LCharSingle.MutCharSingle charSingle(char a) {
		return LCharSingle.of(a);
	}

	public static LCharSingle immutable(char a) {
		return LCharSingle.immutableOf(a);
	}

	public static LDblSingle.MutDblSingle tuple(double a) {
		return LDblSingle.of(a);
	}

	public static LDblSingle.MutDblSingle dblSingle(double a) {
		return LDblSingle.of(a);
	}

	public static LDblSingle immutable(double a) {
		return LDblSingle.immutableOf(a);
	}

	public static LFltSingle.MutFltSingle tuple(float a) {
		return LFltSingle.of(a);
	}

	public static LFltSingle.MutFltSingle fltSingle(float a) {
		return LFltSingle.of(a);
	}

	public static LFltSingle immutable(float a) {
		return LFltSingle.immutableOf(a);
	}

	public static LIntSingle.MutIntSingle tuple(int a) {
		return LIntSingle.of(a);
	}

	public static LIntSingle.MutIntSingle intSingle(int a) {
		return LIntSingle.of(a);
	}

	public static LIntSingle immutable(int a) {
		return LIntSingle.immutableOf(a);
	}

	public static LLongSingle.MutLongSingle tuple(long a) {
		return LLongSingle.of(a);
	}

	public static LLongSingle.MutLongSingle longSingle(long a) {
		return LLongSingle.of(a);
	}

	public static LLongSingle immutable(long a) {
		return LLongSingle.immutableOf(a);
	}

	public static LSrtSingle.MutSrtSingle tuple(short a) {
		return LSrtSingle.of(a);
	}

	public static LSrtSingle.MutSrtSingle srtSingle(short a) {
		return LSrtSingle.of(a);
	}

	public static LSrtSingle immutable(short a) {
		return LSrtSingle.immutableOf(a);
	}

	public static LBoolPair.MutBoolPair tuple(boolean a1, boolean a2) {
		return LBoolPair.of(a1, a2);
	}

	public static LBoolPair.MutBoolPair boolPair(boolean a1, boolean a2) {
		return LBoolPair.of(a1, a2);
	}

	public static LBoolPair immutable(boolean a1, boolean a2) {
		return LBoolPair.immutableOf(a1, a2);
	}

	public static LBytePair.MutBytePair tuple(byte a1, byte a2) {
		return LBytePair.of(a1, a2);
	}

	public static LBytePair.MutBytePair bytePair(byte a1, byte a2) {
		return LBytePair.of(a1, a2);
	}

	public static LBytePair immutable(byte a1, byte a2) {
		return LBytePair.immutableOf(a1, a2);
	}

	public static LCharPair.MutCharPair tuple(char a1, char a2) {
		return LCharPair.of(a1, a2);
	}

	public static LCharPair.MutCharPair charPair(char a1, char a2) {
		return LCharPair.of(a1, a2);
	}

	public static LCharPair immutable(char a1, char a2) {
		return LCharPair.immutableOf(a1, a2);
	}

	public static LDblPair.MutDblPair tuple(double a1, double a2) {
		return LDblPair.of(a1, a2);
	}

	public static LDblPair.MutDblPair dblPair(double a1, double a2) {
		return LDblPair.of(a1, a2);
	}

	public static LDblPair immutable(double a1, double a2) {
		return LDblPair.immutableOf(a1, a2);
	}

	public static LFltPair.MutFltPair tuple(float a1, float a2) {
		return LFltPair.of(a1, a2);
	}

	public static LFltPair.MutFltPair fltPair(float a1, float a2) {
		return LFltPair.of(a1, a2);
	}

	public static LFltPair immutable(float a1, float a2) {
		return LFltPair.immutableOf(a1, a2);
	}

	public static LIntPair.MutIntPair tuple(int a1, int a2) {
		return LIntPair.of(a1, a2);
	}

	public static LIntPair.MutIntPair intPair(int a1, int a2) {
		return LIntPair.of(a1, a2);
	}

	public static LIntPair immutable(int a1, int a2) {
		return LIntPair.immutableOf(a1, a2);
	}

	public static LLongPair.MutLongPair tuple(long a1, long a2) {
		return LLongPair.of(a1, a2);
	}

	public static LLongPair.MutLongPair longPair(long a1, long a2) {
		return LLongPair.of(a1, a2);
	}

	public static LLongPair immutable(long a1, long a2) {
		return LLongPair.immutableOf(a1, a2);
	}

	public static LSrtPair.MutSrtPair tuple(short a1, short a2) {
		return LSrtPair.of(a1, a2);
	}

	public static LSrtPair.MutSrtPair srtPair(short a1, short a2) {
		return LSrtPair.of(a1, a2);
	}

	public static LSrtPair immutable(short a1, short a2) {
		return LSrtPair.immutableOf(a1, a2);
	}

	public static LBoolIntPair.MutBoolIntPair tuple(boolean a1, int a2) {
		return LBoolIntPair.of(a1, a2);
	}

	public static LBoolIntPair.MutBoolIntPair boolIntPair(boolean a1, int a2) {
		return LBoolIntPair.of(a1, a2);
	}

	public static LBoolIntPair immutable(boolean a1, int a2) {
		return LBoolIntPair.immutableOf(a1, a2);
	}

	public static LByteIntPair.MutByteIntPair tuple(byte a1, int a2) {
		return LByteIntPair.of(a1, a2);
	}

	public static LByteIntPair.MutByteIntPair byteIntPair(byte a1, int a2) {
		return LByteIntPair.of(a1, a2);
	}

	public static LByteIntPair immutable(byte a1, int a2) {
		return LByteIntPair.immutableOf(a1, a2);
	}

	public static LCharIntPair.MutCharIntPair tuple(char a1, int a2) {
		return LCharIntPair.of(a1, a2);
	}

	public static LCharIntPair.MutCharIntPair charIntPair(char a1, int a2) {
		return LCharIntPair.of(a1, a2);
	}

	public static LCharIntPair immutable(char a1, int a2) {
		return LCharIntPair.immutableOf(a1, a2);
	}

	public static LDblIntPair.MutDblIntPair tuple(double a1, int a2) {
		return LDblIntPair.of(a1, a2);
	}

	public static LDblIntPair.MutDblIntPair dblIntPair(double a1, int a2) {
		return LDblIntPair.of(a1, a2);
	}

	public static LDblIntPair immutable(double a1, int a2) {
		return LDblIntPair.immutableOf(a1, a2);
	}

	public static LFltIntPair.MutFltIntPair tuple(float a1, int a2) {
		return LFltIntPair.of(a1, a2);
	}

	public static LFltIntPair.MutFltIntPair fltIntPair(float a1, int a2) {
		return LFltIntPair.of(a1, a2);
	}

	public static LFltIntPair immutable(float a1, int a2) {
		return LFltIntPair.immutableOf(a1, a2);
	}

	public static LLongIntPair.MutLongIntPair tuple(long a1, int a2) {
		return LLongIntPair.of(a1, a2);
	}

	public static LLongIntPair.MutLongIntPair longIntPair(long a1, int a2) {
		return LLongIntPair.of(a1, a2);
	}

	public static LLongIntPair immutable(long a1, int a2) {
		return LLongIntPair.immutableOf(a1, a2);
	}

	public static LSrtIntPair.MutSrtIntPair tuple(short a1, int a2) {
		return LSrtIntPair.of(a1, a2);
	}

	public static LSrtIntPair.MutSrtIntPair srtIntPair(short a1, int a2) {
		return LSrtIntPair.of(a1, a2);
	}

	public static LSrtIntPair immutable(short a1, int a2) {
		return LSrtIntPair.immutableOf(a1, a2);
	}

	public static <T1, T2> LBiObjBoolTriple.MutBiObjBoolTriple<T1, T2> tuple(T1 a1, T2 a2, boolean a3) {
		return LBiObjBoolTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjBoolTriple.MutBiObjBoolTriple<T1, T2> biObjBoolTriple(T1 a1, T2 a2, boolean a3) {
		return LBiObjBoolTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjBoolTriple<T1, T2> immutable(T1 a1, T2 a2, boolean a3) {
		return LBiObjBoolTriple.immutableOf(a1, a2, a3);
	}

	public static <T1, T2> LBiObjByteTriple.MutBiObjByteTriple<T1, T2> tuple(T1 a1, T2 a2, byte a3) {
		return LBiObjByteTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjByteTriple.MutBiObjByteTriple<T1, T2> biObjByteTriple(T1 a1, T2 a2, byte a3) {
		return LBiObjByteTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjByteTriple<T1, T2> immutable(T1 a1, T2 a2, byte a3) {
		return LBiObjByteTriple.immutableOf(a1, a2, a3);
	}

	public static <T1, T2> LBiObjCharTriple.MutBiObjCharTriple<T1, T2> tuple(T1 a1, T2 a2, char a3) {
		return LBiObjCharTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjCharTriple.MutBiObjCharTriple<T1, T2> biObjCharTriple(T1 a1, T2 a2, char a3) {
		return LBiObjCharTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjCharTriple<T1, T2> immutable(T1 a1, T2 a2, char a3) {
		return LBiObjCharTriple.immutableOf(a1, a2, a3);
	}

	public static <T1, T2> LBiObjDblTriple.MutBiObjDblTriple<T1, T2> tuple(T1 a1, T2 a2, double a3) {
		return LBiObjDblTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjDblTriple.MutBiObjDblTriple<T1, T2> biObjDblTriple(T1 a1, T2 a2, double a3) {
		return LBiObjDblTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjDblTriple<T1, T2> immutable(T1 a1, T2 a2, double a3) {
		return LBiObjDblTriple.immutableOf(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFltTriple.MutBiObjFltTriple<T1, T2> tuple(T1 a1, T2 a2, float a3) {
		return LBiObjFltTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFltTriple.MutBiObjFltTriple<T1, T2> biObjFltTriple(T1 a1, T2 a2, float a3) {
		return LBiObjFltTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFltTriple<T1, T2> immutable(T1 a1, T2 a2, float a3) {
		return LBiObjFltTriple.immutableOf(a1, a2, a3);
	}

	public static <T1, T2> LBiObjIntTriple.MutBiObjIntTriple<T1, T2> tuple(T1 a1, T2 a2, int a3) {
		return LBiObjIntTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjIntTriple.MutBiObjIntTriple<T1, T2> biObjIntTriple(T1 a1, T2 a2, int a3) {
		return LBiObjIntTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjIntTriple<T1, T2> immutable(T1 a1, T2 a2, int a3) {
		return LBiObjIntTriple.immutableOf(a1, a2, a3);
	}

	public static <T1, T2> LBiObjLongTriple.MutBiObjLongTriple<T1, T2> tuple(T1 a1, T2 a2, long a3) {
		return LBiObjLongTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjLongTriple.MutBiObjLongTriple<T1, T2> biObjLongTriple(T1 a1, T2 a2, long a3) {
		return LBiObjLongTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjLongTriple<T1, T2> immutable(T1 a1, T2 a2, long a3) {
		return LBiObjLongTriple.immutableOf(a1, a2, a3);
	}

	public static <T1, T2> LBiObjSrtTriple.MutBiObjSrtTriple<T1, T2> tuple(T1 a1, T2 a2, short a3) {
		return LBiObjSrtTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjSrtTriple.MutBiObjSrtTriple<T1, T2> biObjSrtTriple(T1 a1, T2 a2, short a3) {
		return LBiObjSrtTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjSrtTriple<T1, T2> immutable(T1 a1, T2 a2, short a3) {
		return LBiObjSrtTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjBiLongTriple.MutObjBiLongTriple<T> tuple(T a1, long a2, long a3) {
		return LObjBiLongTriple.of(a1, a2, a3);
	}

	public static <T> LObjBiLongTriple.MutObjBiLongTriple<T> objBiLongTriple(T a1, long a2, long a3) {
		return LObjBiLongTriple.of(a1, a2, a3);
	}

	public static <T> LObjBiLongTriple<T> immutable(T a1, long a2, long a3) {
		return LObjBiLongTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjBoolPair.MutObjBoolPair<T> tuple(T a1, boolean a2) {
		return LObjBoolPair.of(a1, a2);
	}

	public static <T> LObjBoolPair.MutObjBoolPair<T> objBoolPair(T a1, boolean a2) {
		return LObjBoolPair.of(a1, a2);
	}

	public static <T> LObjBoolPair<T> immutable(T a1, boolean a2) {
		return LObjBoolPair.immutableOf(a1, a2);
	}

	public static <T> LObjBytePair.MutObjBytePair<T> tuple(T a1, byte a2) {
		return LObjBytePair.of(a1, a2);
	}

	public static <T> LObjBytePair.MutObjBytePair<T> objBytePair(T a1, byte a2) {
		return LObjBytePair.of(a1, a2);
	}

	public static <T> LObjBytePair<T> immutable(T a1, byte a2) {
		return LObjBytePair.immutableOf(a1, a2);
	}

	public static <T> LObjCharPair.MutObjCharPair<T> tuple(T a1, char a2) {
		return LObjCharPair.of(a1, a2);
	}

	public static <T> LObjCharPair.MutObjCharPair<T> objCharPair(T a1, char a2) {
		return LObjCharPair.of(a1, a2);
	}

	public static <T> LObjCharPair<T> immutable(T a1, char a2) {
		return LObjCharPair.immutableOf(a1, a2);
	}

	public static <T> LObjDblPair.MutObjDblPair<T> tuple(T a1, double a2) {
		return LObjDblPair.of(a1, a2);
	}

	public static <T> LObjDblPair.MutObjDblPair<T> objDblPair(T a1, double a2) {
		return LObjDblPair.of(a1, a2);
	}

	public static <T> LObjDblPair<T> immutable(T a1, double a2) {
		return LObjDblPair.immutableOf(a1, a2);
	}

	public static <T> LObjFltPair.MutObjFltPair<T> tuple(T a1, float a2) {
		return LObjFltPair.of(a1, a2);
	}

	public static <T> LObjFltPair.MutObjFltPair<T> objFltPair(T a1, float a2) {
		return LObjFltPair.of(a1, a2);
	}

	public static <T> LObjFltPair<T> immutable(T a1, float a2) {
		return LObjFltPair.immutableOf(a1, a2);
	}

	public static <T> LObjIntPair.MutObjIntPair<T> tuple(T a1, int a2) {
		return LObjIntPair.of(a1, a2);
	}

	public static <T> LObjIntPair.MutObjIntPair<T> objIntPair(T a1, int a2) {
		return LObjIntPair.of(a1, a2);
	}

	public static <T> LObjIntPair<T> immutable(T a1, int a2) {
		return LObjIntPair.immutableOf(a1, a2);
	}

	public static <T> LObjLongPair.MutObjLongPair<T> tuple(T a1, long a2) {
		return LObjLongPair.of(a1, a2);
	}

	public static <T> LObjLongPair.MutObjLongPair<T> objLongPair(T a1, long a2) {
		return LObjLongPair.of(a1, a2);
	}

	public static <T> LObjLongPair<T> immutable(T a1, long a2) {
		return LObjLongPair.immutableOf(a1, a2);
	}

	public static <T> LObjSrtPair.MutObjSrtPair<T> tuple(T a1, short a2) {
		return LObjSrtPair.of(a1, a2);
	}

	public static <T> LObjSrtPair.MutObjSrtPair<T> objSrtPair(T a1, short a2) {
		return LObjSrtPair.of(a1, a2);
	}

	public static <T> LObjSrtPair<T> immutable(T a1, short a2) {
		return LObjSrtPair.immutableOf(a1, a2);
	}

	public static <T> LObjIntBoolTriple.MutObjIntBoolTriple<T> tuple(T a1, int a2, boolean a3) {
		return LObjIntBoolTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntBoolTriple.MutObjIntBoolTriple<T> objIntBoolTriple(T a1, int a2, boolean a3) {
		return LObjIntBoolTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntBoolTriple<T> immutable(T a1, int a2, boolean a3) {
		return LObjIntBoolTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjIntByteTriple.MutObjIntByteTriple<T> tuple(T a1, int a2, byte a3) {
		return LObjIntByteTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntByteTriple.MutObjIntByteTriple<T> objIntByteTriple(T a1, int a2, byte a3) {
		return LObjIntByteTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntByteTriple<T> immutable(T a1, int a2, byte a3) {
		return LObjIntByteTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjIntCharTriple.MutObjIntCharTriple<T> tuple(T a1, int a2, char a3) {
		return LObjIntCharTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntCharTriple.MutObjIntCharTriple<T> objIntCharTriple(T a1, int a2, char a3) {
		return LObjIntCharTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntCharTriple<T> immutable(T a1, int a2, char a3) {
		return LObjIntCharTriple.immutableOf(a1, a2, a3);
	}

	public static <T1, T2> LObjIntObjTriple.MutObjIntObjTriple<T1, T2> tuple(T1 a1, int a2, T2 a3) {
		return LObjIntObjTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LObjIntObjTriple.MutObjIntObjTriple<T1, T2> objIntObjTriple(T1 a1, int a2, T2 a3) {
		return LObjIntObjTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LObjIntObjTriple<T1, T2> immutable(T1 a1, int a2, T2 a3) {
		return LObjIntObjTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjIntDblTriple.MutObjIntDblTriple<T> tuple(T a1, int a2, double a3) {
		return LObjIntDblTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntDblTriple.MutObjIntDblTriple<T> objIntDblTriple(T a1, int a2, double a3) {
		return LObjIntDblTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntDblTriple<T> immutable(T a1, int a2, double a3) {
		return LObjIntDblTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjIntFltTriple.MutObjIntFltTriple<T> tuple(T a1, int a2, float a3) {
		return LObjIntFltTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntFltTriple.MutObjIntFltTriple<T> objIntFltTriple(T a1, int a2, float a3) {
		return LObjIntFltTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntFltTriple<T> immutable(T a1, int a2, float a3) {
		return LObjIntFltTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjBiIntTriple.MutObjBiIntTriple<T> tuple(T a1, int a2, int a3) {
		return LObjBiIntTriple.of(a1, a2, a3);
	}

	public static <T> LObjBiIntTriple.MutObjBiIntTriple<T> objBiIntTriple(T a1, int a2, int a3) {
		return LObjBiIntTriple.of(a1, a2, a3);
	}

	public static <T> LObjBiIntTriple<T> immutable(T a1, int a2, int a3) {
		return LObjBiIntTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjIntLongTriple.MutObjIntLongTriple<T> tuple(T a1, int a2, long a3) {
		return LObjIntLongTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntLongTriple.MutObjIntLongTriple<T> objIntLongTriple(T a1, int a2, long a3) {
		return LObjIntLongTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntLongTriple<T> immutable(T a1, int a2, long a3) {
		return LObjIntLongTriple.immutableOf(a1, a2, a3);
	}

	public static <T> LObjIntSrtTriple.MutObjIntSrtTriple<T> tuple(T a1, int a2, short a3) {
		return LObjIntSrtTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntSrtTriple.MutObjIntSrtTriple<T> objIntSrtTriple(T a1, int a2, short a3) {
		return LObjIntSrtTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntSrtTriple<T> immutable(T a1, int a2, short a3) {
		return LObjIntSrtTriple.immutableOf(a1, a2, a3);
	}

	public static LBoolTriple.MutBoolTriple tuple(boolean a1, boolean a2, boolean a3) {
		return LBoolTriple.of(a1, a2, a3);
	}

	public static LBoolTriple.MutBoolTriple boolTriple(boolean a1, boolean a2, boolean a3) {
		return LBoolTriple.of(a1, a2, a3);
	}

	public static LBoolTriple immutable(boolean a1, boolean a2, boolean a3) {
		return LBoolTriple.immutableOf(a1, a2, a3);
	}

	public static LByteTriple.MutByteTriple tuple(byte a1, byte a2, byte a3) {
		return LByteTriple.of(a1, a2, a3);
	}

	public static LByteTriple.MutByteTriple byteTriple(byte a1, byte a2, byte a3) {
		return LByteTriple.of(a1, a2, a3);
	}

	public static LByteTriple immutable(byte a1, byte a2, byte a3) {
		return LByteTriple.immutableOf(a1, a2, a3);
	}

	public static LCharTriple.MutCharTriple tuple(char a1, char a2, char a3) {
		return LCharTriple.of(a1, a2, a3);
	}

	public static LCharTriple.MutCharTriple charTriple(char a1, char a2, char a3) {
		return LCharTriple.of(a1, a2, a3);
	}

	public static LCharTriple immutable(char a1, char a2, char a3) {
		return LCharTriple.immutableOf(a1, a2, a3);
	}

	public static LDblTriple.MutDblTriple tuple(double a1, double a2, double a3) {
		return LDblTriple.of(a1, a2, a3);
	}

	public static LDblTriple.MutDblTriple dblTriple(double a1, double a2, double a3) {
		return LDblTriple.of(a1, a2, a3);
	}

	public static LDblTriple immutable(double a1, double a2, double a3) {
		return LDblTriple.immutableOf(a1, a2, a3);
	}

	public static LFltTriple.MutFltTriple tuple(float a1, float a2, float a3) {
		return LFltTriple.of(a1, a2, a3);
	}

	public static LFltTriple.MutFltTriple fltTriple(float a1, float a2, float a3) {
		return LFltTriple.of(a1, a2, a3);
	}

	public static LFltTriple immutable(float a1, float a2, float a3) {
		return LFltTriple.immutableOf(a1, a2, a3);
	}

	public static LIntTriple.MutIntTriple tuple(int a1, int a2, int a3) {
		return LIntTriple.of(a1, a2, a3);
	}

	public static LIntTriple.MutIntTriple intTriple(int a1, int a2, int a3) {
		return LIntTriple.of(a1, a2, a3);
	}

	public static LIntTriple immutable(int a1, int a2, int a3) {
		return LIntTriple.immutableOf(a1, a2, a3);
	}

	public static LLongTriple.MutLongTriple tuple(long a1, long a2, long a3) {
		return LLongTriple.of(a1, a2, a3);
	}

	public static LLongTriple.MutLongTriple longTriple(long a1, long a2, long a3) {
		return LLongTriple.of(a1, a2, a3);
	}

	public static LLongTriple immutable(long a1, long a2, long a3) {
		return LLongTriple.immutableOf(a1, a2, a3);
	}

	public static LSrtTriple.MutSrtTriple tuple(short a1, short a2, short a3) {
		return LSrtTriple.of(a1, a2, a3);
	}

	public static LSrtTriple.MutSrtTriple srtTriple(short a1, short a2, short a3) {
		return LSrtTriple.of(a1, a2, a3);
	}

	public static LSrtTriple immutable(short a1, short a2, short a3) {
		return LSrtTriple.immutableOf(a1, a2, a3);
	}

	public static LTuple.Void tuple() {
		return LTuple.Void.INSTANCE;
	}

	private Tuple4U() {

	}
}