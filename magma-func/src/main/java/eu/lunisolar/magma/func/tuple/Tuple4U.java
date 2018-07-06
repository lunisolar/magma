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

package eu.lunisolar.magma.func.tuple;

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

	public static <T> LSingle.MutSingle<T> tuple(T a) {
		return LSingle.MutSingle.of(a);
	}

	public static <T> LSingle.MutSingle<T> single(T a) {
		return LSingle.MutSingle.of(a);
	}

	public static <T extends Comparable<T>> LSingle.MutCompSingle<T> comparable(T a) {
		return LSingle.MutCompSingle.of(a);
	}

	public static <T> LSingle<T> immutable(T a) {
		return LSingle.ImmSingle.of(a);
	}

	public static <T extends Comparable<T>> LSingle.ComparableSingle immComp(T a) {
		return LSingle.ImmCompSingle.of(a);
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

	public static LBoolSingle.MutBoolSingle tuple(boolean a) {
		return LBoolSingle.MutBoolSingle.of(a);
	}

	public static LBoolSingle.MutBoolSingle boolSingle(boolean a) {
		return LBoolSingle.MutBoolSingle.of(a);
	}

	public static LBoolSingle.MutCompBoolSingle comparable(boolean a) {
		return LBoolSingle.MutCompBoolSingle.of(a);
	}

	public static LBoolSingle immutable(boolean a) {
		return LBoolSingle.ImmBoolSingle.of(a);
	}

	public static LBoolSingle.ComparableBoolSingle immComp(boolean a) {
		return LBoolSingle.ImmCompBoolSingle.of(a);
	}

	public static LByteSingle.MutByteSingle tuple(byte a) {
		return LByteSingle.MutByteSingle.of(a);
	}

	public static LByteSingle.MutByteSingle byteSingle(byte a) {
		return LByteSingle.MutByteSingle.of(a);
	}

	public static LByteSingle.MutCompByteSingle comparable(byte a) {
		return LByteSingle.MutCompByteSingle.of(a);
	}

	public static LByteSingle immutable(byte a) {
		return LByteSingle.ImmByteSingle.of(a);
	}

	public static LByteSingle.ComparableByteSingle immComp(byte a) {
		return LByteSingle.ImmCompByteSingle.of(a);
	}

	public static LCharSingle.MutCharSingle tuple(char a) {
		return LCharSingle.MutCharSingle.of(a);
	}

	public static LCharSingle.MutCharSingle charSingle(char a) {
		return LCharSingle.MutCharSingle.of(a);
	}

	public static LCharSingle.MutCompCharSingle comparable(char a) {
		return LCharSingle.MutCompCharSingle.of(a);
	}

	public static LCharSingle immutable(char a) {
		return LCharSingle.ImmCharSingle.of(a);
	}

	public static LCharSingle.ComparableCharSingle immComp(char a) {
		return LCharSingle.ImmCompCharSingle.of(a);
	}

	public static LDblSingle.MutDblSingle tuple(double a) {
		return LDblSingle.MutDblSingle.of(a);
	}

	public static LDblSingle.MutDblSingle dblSingle(double a) {
		return LDblSingle.MutDblSingle.of(a);
	}

	public static LDblSingle.MutCompDblSingle comparable(double a) {
		return LDblSingle.MutCompDblSingle.of(a);
	}

	public static LDblSingle immutable(double a) {
		return LDblSingle.ImmDblSingle.of(a);
	}

	public static LDblSingle.ComparableDblSingle immComp(double a) {
		return LDblSingle.ImmCompDblSingle.of(a);
	}

	public static LFltSingle.MutFltSingle tuple(float a) {
		return LFltSingle.MutFltSingle.of(a);
	}

	public static LFltSingle.MutFltSingle fltSingle(float a) {
		return LFltSingle.MutFltSingle.of(a);
	}

	public static LFltSingle.MutCompFltSingle comparable(float a) {
		return LFltSingle.MutCompFltSingle.of(a);
	}

	public static LFltSingle immutable(float a) {
		return LFltSingle.ImmFltSingle.of(a);
	}

	public static LFltSingle.ComparableFltSingle immComp(float a) {
		return LFltSingle.ImmCompFltSingle.of(a);
	}

	public static LIntSingle.MutIntSingle tuple(int a) {
		return LIntSingle.MutIntSingle.of(a);
	}

	public static LIntSingle.MutIntSingle intSingle(int a) {
		return LIntSingle.MutIntSingle.of(a);
	}

	public static LIntSingle.MutCompIntSingle comparable(int a) {
		return LIntSingle.MutCompIntSingle.of(a);
	}

	public static LIntSingle immutable(int a) {
		return LIntSingle.ImmIntSingle.of(a);
	}

	public static LIntSingle.ComparableIntSingle immComp(int a) {
		return LIntSingle.ImmCompIntSingle.of(a);
	}

	public static LLongSingle.MutLongSingle tuple(long a) {
		return LLongSingle.MutLongSingle.of(a);
	}

	public static LLongSingle.MutLongSingle longSingle(long a) {
		return LLongSingle.MutLongSingle.of(a);
	}

	public static LLongSingle.MutCompLongSingle comparable(long a) {
		return LLongSingle.MutCompLongSingle.of(a);
	}

	public static LLongSingle immutable(long a) {
		return LLongSingle.ImmLongSingle.of(a);
	}

	public static LLongSingle.ComparableLongSingle immComp(long a) {
		return LLongSingle.ImmCompLongSingle.of(a);
	}

	public static LSrtSingle.MutSrtSingle tuple(short a) {
		return LSrtSingle.MutSrtSingle.of(a);
	}

	public static LSrtSingle.MutSrtSingle srtSingle(short a) {
		return LSrtSingle.MutSrtSingle.of(a);
	}

	public static LSrtSingle.MutCompSrtSingle comparable(short a) {
		return LSrtSingle.MutCompSrtSingle.of(a);
	}

	public static LSrtSingle immutable(short a) {
		return LSrtSingle.ImmSrtSingle.of(a);
	}

	public static LSrtSingle.ComparableSrtSingle immComp(short a) {
		return LSrtSingle.ImmCompSrtSingle.of(a);
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

	public static LDblPair.MutDblPair tuple(double a1, double a2) {
		return LDblPair.MutDblPair.of(a1, a2);
	}

	public static LDblPair.MutDblPair dblPair(double a1, double a2) {
		return LDblPair.MutDblPair.of(a1, a2);
	}

	public static LDblPair.MutCompDblPair comparable(double a1, double a2) {
		return LDblPair.MutCompDblPair.of(a1, a2);
	}

	public static LDblPair immutable(double a1, double a2) {
		return LDblPair.ImmDblPair.of(a1, a2);
	}

	public static LDblPair.ComparableDblPair immComp(double a1, double a2) {
		return LDblPair.ImmCompDblPair.of(a1, a2);
	}

	public static LFltPair.MutFltPair tuple(float a1, float a2) {
		return LFltPair.MutFltPair.of(a1, a2);
	}

	public static LFltPair.MutFltPair fltPair(float a1, float a2) {
		return LFltPair.MutFltPair.of(a1, a2);
	}

	public static LFltPair.MutCompFltPair comparable(float a1, float a2) {
		return LFltPair.MutCompFltPair.of(a1, a2);
	}

	public static LFltPair immutable(float a1, float a2) {
		return LFltPair.ImmFltPair.of(a1, a2);
	}

	public static LFltPair.ComparableFltPair immComp(float a1, float a2) {
		return LFltPair.ImmCompFltPair.of(a1, a2);
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

	public static LSrtPair.MutSrtPair tuple(short a1, short a2) {
		return LSrtPair.MutSrtPair.of(a1, a2);
	}

	public static LSrtPair.MutSrtPair srtPair(short a1, short a2) {
		return LSrtPair.MutSrtPair.of(a1, a2);
	}

	public static LSrtPair.MutCompSrtPair comparable(short a1, short a2) {
		return LSrtPair.MutCompSrtPair.of(a1, a2);
	}

	public static LSrtPair immutable(short a1, short a2) {
		return LSrtPair.ImmSrtPair.of(a1, a2);
	}

	public static LSrtPair.ComparableSrtPair immComp(short a1, short a2) {
		return LSrtPair.ImmCompSrtPair.of(a1, a2);
	}

	public static LBoolIntPair.MutBoolIntPair tuple(boolean a1, int a2) {
		return LBoolIntPair.MutBoolIntPair.of(a1, a2);
	}

	public static LBoolIntPair.MutBoolIntPair boolIntPair(boolean a1, int a2) {
		return LBoolIntPair.MutBoolIntPair.of(a1, a2);
	}

	public static LBoolIntPair.MutCompBoolIntPair comparable(boolean a1, int a2) {
		return LBoolIntPair.MutCompBoolIntPair.of(a1, a2);
	}

	public static LBoolIntPair immutable(boolean a1, int a2) {
		return LBoolIntPair.ImmBoolIntPair.of(a1, a2);
	}

	public static LBoolIntPair.ComparableBoolIntPair immComp(boolean a1, int a2) {
		return LBoolIntPair.ImmCompBoolIntPair.of(a1, a2);
	}

	public static LByteIntPair.MutByteIntPair tuple(byte a1, int a2) {
		return LByteIntPair.MutByteIntPair.of(a1, a2);
	}

	public static LByteIntPair.MutByteIntPair byteIntPair(byte a1, int a2) {
		return LByteIntPair.MutByteIntPair.of(a1, a2);
	}

	public static LByteIntPair.MutCompByteIntPair comparable(byte a1, int a2) {
		return LByteIntPair.MutCompByteIntPair.of(a1, a2);
	}

	public static LByteIntPair immutable(byte a1, int a2) {
		return LByteIntPair.ImmByteIntPair.of(a1, a2);
	}

	public static LByteIntPair.ComparableByteIntPair immComp(byte a1, int a2) {
		return LByteIntPair.ImmCompByteIntPair.of(a1, a2);
	}

	public static LCharIntPair.MutCharIntPair tuple(char a1, int a2) {
		return LCharIntPair.MutCharIntPair.of(a1, a2);
	}

	public static LCharIntPair.MutCharIntPair charIntPair(char a1, int a2) {
		return LCharIntPair.MutCharIntPair.of(a1, a2);
	}

	public static LCharIntPair.MutCompCharIntPair comparable(char a1, int a2) {
		return LCharIntPair.MutCompCharIntPair.of(a1, a2);
	}

	public static LCharIntPair immutable(char a1, int a2) {
		return LCharIntPair.ImmCharIntPair.of(a1, a2);
	}

	public static LCharIntPair.ComparableCharIntPair immComp(char a1, int a2) {
		return LCharIntPair.ImmCompCharIntPair.of(a1, a2);
	}

	public static LDblIntPair.MutDblIntPair tuple(double a1, int a2) {
		return LDblIntPair.MutDblIntPair.of(a1, a2);
	}

	public static LDblIntPair.MutDblIntPair dblIntPair(double a1, int a2) {
		return LDblIntPair.MutDblIntPair.of(a1, a2);
	}

	public static LDblIntPair.MutCompDblIntPair comparable(double a1, int a2) {
		return LDblIntPair.MutCompDblIntPair.of(a1, a2);
	}

	public static LDblIntPair immutable(double a1, int a2) {
		return LDblIntPair.ImmDblIntPair.of(a1, a2);
	}

	public static LDblIntPair.ComparableDblIntPair immComp(double a1, int a2) {
		return LDblIntPair.ImmCompDblIntPair.of(a1, a2);
	}

	public static LFltIntPair.MutFltIntPair tuple(float a1, int a2) {
		return LFltIntPair.MutFltIntPair.of(a1, a2);
	}

	public static LFltIntPair.MutFltIntPair fltIntPair(float a1, int a2) {
		return LFltIntPair.MutFltIntPair.of(a1, a2);
	}

	public static LFltIntPair.MutCompFltIntPair comparable(float a1, int a2) {
		return LFltIntPair.MutCompFltIntPair.of(a1, a2);
	}

	public static LFltIntPair immutable(float a1, int a2) {
		return LFltIntPair.ImmFltIntPair.of(a1, a2);
	}

	public static LFltIntPair.ComparableFltIntPair immComp(float a1, int a2) {
		return LFltIntPair.ImmCompFltIntPair.of(a1, a2);
	}

	public static LLongIntPair.MutLongIntPair tuple(long a1, int a2) {
		return LLongIntPair.MutLongIntPair.of(a1, a2);
	}

	public static LLongIntPair.MutLongIntPair longIntPair(long a1, int a2) {
		return LLongIntPair.MutLongIntPair.of(a1, a2);
	}

	public static LLongIntPair.MutCompLongIntPair comparable(long a1, int a2) {
		return LLongIntPair.MutCompLongIntPair.of(a1, a2);
	}

	public static LLongIntPair immutable(long a1, int a2) {
		return LLongIntPair.ImmLongIntPair.of(a1, a2);
	}

	public static LLongIntPair.ComparableLongIntPair immComp(long a1, int a2) {
		return LLongIntPair.ImmCompLongIntPair.of(a1, a2);
	}

	public static LSrtIntPair.MutSrtIntPair tuple(short a1, int a2) {
		return LSrtIntPair.MutSrtIntPair.of(a1, a2);
	}

	public static LSrtIntPair.MutSrtIntPair srtIntPair(short a1, int a2) {
		return LSrtIntPair.MutSrtIntPair.of(a1, a2);
	}

	public static LSrtIntPair.MutCompSrtIntPair comparable(short a1, int a2) {
		return LSrtIntPair.MutCompSrtIntPair.of(a1, a2);
	}

	public static LSrtIntPair immutable(short a1, int a2) {
		return LSrtIntPair.ImmSrtIntPair.of(a1, a2);
	}

	public static LSrtIntPair.ComparableSrtIntPair immComp(short a1, int a2) {
		return LSrtIntPair.ImmCompSrtIntPair.of(a1, a2);
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

	public static <T1, T2> LBiObjDblTriple.MutBiObjDblTriple<T1, T2> tuple(T1 a1, T2 a2, double a3) {
		return LBiObjDblTriple.MutBiObjDblTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjDblTriple.MutBiObjDblTriple<T1, T2> biObjDblTriple(T1 a1, T2 a2, double a3) {
		return LBiObjDblTriple.MutBiObjDblTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjDblTriple.MutCompBiObjDblTriple<T1, T2> comparable(T1 a1, T2 a2, double a3) {
		return LBiObjDblTriple.MutCompBiObjDblTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjDblTriple<T1, T2> immutable(T1 a1, T2 a2, double a3) {
		return LBiObjDblTriple.ImmBiObjDblTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjDblTriple.ComparableBiObjDblTriple immComp(T1 a1, T2 a2, double a3) {
		return LBiObjDblTriple.ImmCompBiObjDblTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFltTriple.MutBiObjFltTriple<T1, T2> tuple(T1 a1, T2 a2, float a3) {
		return LBiObjFltTriple.MutBiObjFltTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFltTriple.MutBiObjFltTriple<T1, T2> biObjFltTriple(T1 a1, T2 a2, float a3) {
		return LBiObjFltTriple.MutBiObjFltTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjFltTriple.MutCompBiObjFltTriple<T1, T2> comparable(T1 a1, T2 a2, float a3) {
		return LBiObjFltTriple.MutCompBiObjFltTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjFltTriple<T1, T2> immutable(T1 a1, T2 a2, float a3) {
		return LBiObjFltTriple.ImmBiObjFltTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjFltTriple.ComparableBiObjFltTriple immComp(T1 a1, T2 a2, float a3) {
		return LBiObjFltTriple.ImmCompBiObjFltTriple.of(a1, a2, a3);
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

	public static <T1, T2> LBiObjSrtTriple.MutBiObjSrtTriple<T1, T2> tuple(T1 a1, T2 a2, short a3) {
		return LBiObjSrtTriple.MutBiObjSrtTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjSrtTriple.MutBiObjSrtTriple<T1, T2> biObjSrtTriple(T1 a1, T2 a2, short a3) {
		return LBiObjSrtTriple.MutBiObjSrtTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjSrtTriple.MutCompBiObjSrtTriple<T1, T2> comparable(T1 a1, T2 a2, short a3) {
		return LBiObjSrtTriple.MutCompBiObjSrtTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LBiObjSrtTriple<T1, T2> immutable(T1 a1, T2 a2, short a3) {
		return LBiObjSrtTriple.ImmBiObjSrtTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjSrtTriple.ComparableBiObjSrtTriple immComp(T1 a1, T2 a2, short a3) {
		return LBiObjSrtTriple.ImmCompBiObjSrtTriple.of(a1, a2, a3);
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

	public static <T> LObjDblPair.MutObjDblPair<T> tuple(T a1, double a2) {
		return LObjDblPair.MutObjDblPair.of(a1, a2);
	}

	public static <T> LObjDblPair.MutObjDblPair<T> objDblPair(T a1, double a2) {
		return LObjDblPair.MutObjDblPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjDblPair.MutCompObjDblPair<T> comparable(T a1, double a2) {
		return LObjDblPair.MutCompObjDblPair.of(a1, a2);
	}

	public static <T> LObjDblPair<T> immutable(T a1, double a2) {
		return LObjDblPair.ImmObjDblPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjDblPair.ComparableObjDblPair immComp(T a1, double a2) {
		return LObjDblPair.ImmCompObjDblPair.of(a1, a2);
	}

	public static <T> LObjFltPair.MutObjFltPair<T> tuple(T a1, float a2) {
		return LObjFltPair.MutObjFltPair.of(a1, a2);
	}

	public static <T> LObjFltPair.MutObjFltPair<T> objFltPair(T a1, float a2) {
		return LObjFltPair.MutObjFltPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjFltPair.MutCompObjFltPair<T> comparable(T a1, float a2) {
		return LObjFltPair.MutCompObjFltPair.of(a1, a2);
	}

	public static <T> LObjFltPair<T> immutable(T a1, float a2) {
		return LObjFltPair.ImmObjFltPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjFltPair.ComparableObjFltPair immComp(T a1, float a2) {
		return LObjFltPair.ImmCompObjFltPair.of(a1, a2);
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

	public static <T> LObjSrtPair.MutObjSrtPair<T> tuple(T a1, short a2) {
		return LObjSrtPair.MutObjSrtPair.of(a1, a2);
	}

	public static <T> LObjSrtPair.MutObjSrtPair<T> objSrtPair(T a1, short a2) {
		return LObjSrtPair.MutObjSrtPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjSrtPair.MutCompObjSrtPair<T> comparable(T a1, short a2) {
		return LObjSrtPair.MutCompObjSrtPair.of(a1, a2);
	}

	public static <T> LObjSrtPair<T> immutable(T a1, short a2) {
		return LObjSrtPair.ImmObjSrtPair.of(a1, a2);
	}

	public static <T extends Comparable<T>> LObjSrtPair.ComparableObjSrtPair immComp(T a1, short a2) {
		return LObjSrtPair.ImmCompObjSrtPair.of(a1, a2);
	}

	public static <T> LObjIntBoolTriple.MutObjIntBoolTriple<T> tuple(T a1, int a2, boolean a3) {
		return LObjIntBoolTriple.MutObjIntBoolTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntBoolTriple.MutObjIntBoolTriple<T> objIntBoolTriple(T a1, int a2, boolean a3) {
		return LObjIntBoolTriple.MutObjIntBoolTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntBoolTriple.MutCompObjIntBoolTriple<T> comparable(T a1, int a2, boolean a3) {
		return LObjIntBoolTriple.MutCompObjIntBoolTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntBoolTriple<T> immutable(T a1, int a2, boolean a3) {
		return LObjIntBoolTriple.ImmObjIntBoolTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntBoolTriple.ComparableObjIntBoolTriple immComp(T a1, int a2, boolean a3) {
		return LObjIntBoolTriple.ImmCompObjIntBoolTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntByteTriple.MutObjIntByteTriple<T> tuple(T a1, int a2, byte a3) {
		return LObjIntByteTriple.MutObjIntByteTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntByteTriple.MutObjIntByteTriple<T> objIntByteTriple(T a1, int a2, byte a3) {
		return LObjIntByteTriple.MutObjIntByteTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntByteTriple.MutCompObjIntByteTriple<T> comparable(T a1, int a2, byte a3) {
		return LObjIntByteTriple.MutCompObjIntByteTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntByteTriple<T> immutable(T a1, int a2, byte a3) {
		return LObjIntByteTriple.ImmObjIntByteTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntByteTriple.ComparableObjIntByteTriple immComp(T a1, int a2, byte a3) {
		return LObjIntByteTriple.ImmCompObjIntByteTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntCharTriple.MutObjIntCharTriple<T> tuple(T a1, int a2, char a3) {
		return LObjIntCharTriple.MutObjIntCharTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntCharTriple.MutObjIntCharTriple<T> objIntCharTriple(T a1, int a2, char a3) {
		return LObjIntCharTriple.MutObjIntCharTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntCharTriple.MutCompObjIntCharTriple<T> comparable(T a1, int a2, char a3) {
		return LObjIntCharTriple.MutCompObjIntCharTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntCharTriple<T> immutable(T a1, int a2, char a3) {
		return LObjIntCharTriple.ImmObjIntCharTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntCharTriple.ComparableObjIntCharTriple immComp(T a1, int a2, char a3) {
		return LObjIntCharTriple.ImmCompObjIntCharTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LObjIntObjTriple.MutObjIntObjTriple<T1, T2> tuple(T1 a1, int a2, T2 a3) {
		return LObjIntObjTriple.MutObjIntObjTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LObjIntObjTriple.MutObjIntObjTriple<T1, T2> objIntObjTriple(T1 a1, int a2, T2 a3) {
		return LObjIntObjTriple.MutObjIntObjTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LObjIntObjTriple.MutCompObjIntObjTriple<T1, T2> comparable(T1 a1, int a2, T2 a3) {
		return LObjIntObjTriple.MutCompObjIntObjTriple.of(a1, a2, a3);
	}

	public static <T1, T2> LObjIntObjTriple<T1, T2> immutable(T1 a1, int a2, T2 a3) {
		return LObjIntObjTriple.ImmObjIntObjTriple.of(a1, a2, a3);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LObjIntObjTriple.ComparableObjIntObjTriple immComp(T1 a1, int a2, T2 a3) {
		return LObjIntObjTriple.ImmCompObjIntObjTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntDblTriple.MutObjIntDblTriple<T> tuple(T a1, int a2, double a3) {
		return LObjIntDblTriple.MutObjIntDblTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntDblTriple.MutObjIntDblTriple<T> objIntDblTriple(T a1, int a2, double a3) {
		return LObjIntDblTriple.MutObjIntDblTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntDblTriple.MutCompObjIntDblTriple<T> comparable(T a1, int a2, double a3) {
		return LObjIntDblTriple.MutCompObjIntDblTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntDblTriple<T> immutable(T a1, int a2, double a3) {
		return LObjIntDblTriple.ImmObjIntDblTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntDblTriple.ComparableObjIntDblTriple immComp(T a1, int a2, double a3) {
		return LObjIntDblTriple.ImmCompObjIntDblTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntFltTriple.MutObjIntFltTriple<T> tuple(T a1, int a2, float a3) {
		return LObjIntFltTriple.MutObjIntFltTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntFltTriple.MutObjIntFltTriple<T> objIntFltTriple(T a1, int a2, float a3) {
		return LObjIntFltTriple.MutObjIntFltTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntFltTriple.MutCompObjIntFltTriple<T> comparable(T a1, int a2, float a3) {
		return LObjIntFltTriple.MutCompObjIntFltTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntFltTriple<T> immutable(T a1, int a2, float a3) {
		return LObjIntFltTriple.ImmObjIntFltTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntFltTriple.ComparableObjIntFltTriple immComp(T a1, int a2, float a3) {
		return LObjIntFltTriple.ImmCompObjIntFltTriple.of(a1, a2, a3);
	}

	public static <T> LObjBiIntTriple.MutObjBiIntTriple<T> tuple(T a1, int a2, int a3) {
		return LObjBiIntTriple.MutObjBiIntTriple.of(a1, a2, a3);
	}

	public static <T> LObjBiIntTriple.MutObjBiIntTriple<T> objBiIntTriple(T a1, int a2, int a3) {
		return LObjBiIntTriple.MutObjBiIntTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjBiIntTriple.MutCompObjBiIntTriple<T> comparable(T a1, int a2, int a3) {
		return LObjBiIntTriple.MutCompObjBiIntTriple.of(a1, a2, a3);
	}

	public static <T> LObjBiIntTriple<T> immutable(T a1, int a2, int a3) {
		return LObjBiIntTriple.ImmObjBiIntTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjBiIntTriple.ComparableObjBiIntTriple immComp(T a1, int a2, int a3) {
		return LObjBiIntTriple.ImmCompObjBiIntTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntLongTriple.MutObjIntLongTriple<T> tuple(T a1, int a2, long a3) {
		return LObjIntLongTriple.MutObjIntLongTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntLongTriple.MutObjIntLongTriple<T> objIntLongTriple(T a1, int a2, long a3) {
		return LObjIntLongTriple.MutObjIntLongTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntLongTriple.MutCompObjIntLongTriple<T> comparable(T a1, int a2, long a3) {
		return LObjIntLongTriple.MutCompObjIntLongTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntLongTriple<T> immutable(T a1, int a2, long a3) {
		return LObjIntLongTriple.ImmObjIntLongTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntLongTriple.ComparableObjIntLongTriple immComp(T a1, int a2, long a3) {
		return LObjIntLongTriple.ImmCompObjIntLongTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntSrtTriple.MutObjIntSrtTriple<T> tuple(T a1, int a2, short a3) {
		return LObjIntSrtTriple.MutObjIntSrtTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntSrtTriple.MutObjIntSrtTriple<T> objIntSrtTriple(T a1, int a2, short a3) {
		return LObjIntSrtTriple.MutObjIntSrtTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntSrtTriple.MutCompObjIntSrtTriple<T> comparable(T a1, int a2, short a3) {
		return LObjIntSrtTriple.MutCompObjIntSrtTriple.of(a1, a2, a3);
	}

	public static <T> LObjIntSrtTriple<T> immutable(T a1, int a2, short a3) {
		return LObjIntSrtTriple.ImmObjIntSrtTriple.of(a1, a2, a3);
	}

	public static <T extends Comparable<T>> LObjIntSrtTriple.ComparableObjIntSrtTriple immComp(T a1, int a2, short a3) {
		return LObjIntSrtTriple.ImmCompObjIntSrtTriple.of(a1, a2, a3);
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