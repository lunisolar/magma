
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

	public static <T> LObjBytePair.MutObjBytePair<T> tuple(T first, byte second) {
		return LObjBytePair.MutObjBytePair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjBytePair.MutCompObjBytePair<T> comparable(T first, byte second) {
		return LObjBytePair.MutCompObjBytePair.of(first, second);
	}

	public static <T> LObjBytePair<T> immutable(T first, byte second) {
		return LObjBytePair.ImmObjBytePair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjBytePair.ComparableObjBytePair<T> immComp(T first, byte second) {
		return LObjBytePair.ImmCompObjBytePair.of(first, second);
	}

	public static <T> LObjShortPair.MutObjShortPair<T> tuple(T first, short second) {
		return LObjShortPair.MutObjShortPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjShortPair.MutCompObjShortPair<T> comparable(T first, short second) {
		return LObjShortPair.MutCompObjShortPair.of(first, second);
	}

	public static <T> LObjShortPair<T> immutable(T first, short second) {
		return LObjShortPair.ImmObjShortPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjShortPair.ComparableObjShortPair<T> immComp(T first, short second) {
		return LObjShortPair.ImmCompObjShortPair.of(first, second);
	}

	public static <T> LObjIntPair.MutObjIntPair<T> tuple(T first, int second) {
		return LObjIntPair.MutObjIntPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjIntPair.MutCompObjIntPair<T> comparable(T first, int second) {
		return LObjIntPair.MutCompObjIntPair.of(first, second);
	}

	public static <T> LObjIntPair<T> immutable(T first, int second) {
		return LObjIntPair.ImmObjIntPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjIntPair.ComparableObjIntPair<T> immComp(T first, int second) {
		return LObjIntPair.ImmCompObjIntPair.of(first, second);
	}

	public static <T> LObjLongPair.MutObjLongPair<T> tuple(T first, long second) {
		return LObjLongPair.MutObjLongPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjLongPair.MutCompObjLongPair<T> comparable(T first, long second) {
		return LObjLongPair.MutCompObjLongPair.of(first, second);
	}

	public static <T> LObjLongPair<T> immutable(T first, long second) {
		return LObjLongPair.ImmObjLongPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjLongPair.ComparableObjLongPair<T> immComp(T first, long second) {
		return LObjLongPair.ImmCompObjLongPair.of(first, second);
	}

	public static <T> LObjFloatPair.MutObjFloatPair<T> tuple(T first, float second) {
		return LObjFloatPair.MutObjFloatPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjFloatPair.MutCompObjFloatPair<T> comparable(T first, float second) {
		return LObjFloatPair.MutCompObjFloatPair.of(first, second);
	}

	public static <T> LObjFloatPair<T> immutable(T first, float second) {
		return LObjFloatPair.ImmObjFloatPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjFloatPair.ComparableObjFloatPair<T> immComp(T first, float second) {
		return LObjFloatPair.ImmCompObjFloatPair.of(first, second);
	}

	public static <T> LObjDoublePair.MutObjDoublePair<T> tuple(T first, double second) {
		return LObjDoublePair.MutObjDoublePair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjDoublePair.MutCompObjDoublePair<T> comparable(T first, double second) {
		return LObjDoublePair.MutCompObjDoublePair.of(first, second);
	}

	public static <T> LObjDoublePair<T> immutable(T first, double second) {
		return LObjDoublePair.ImmObjDoublePair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjDoublePair.ComparableObjDoublePair<T> immComp(T first, double second) {
		return LObjDoublePair.ImmCompObjDoublePair.of(first, second);
	}

	public static <T> LObjCharPair.MutObjCharPair<T> tuple(T first, char second) {
		return LObjCharPair.MutObjCharPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjCharPair.MutCompObjCharPair<T> comparable(T first, char second) {
		return LObjCharPair.MutCompObjCharPair.of(first, second);
	}

	public static <T> LObjCharPair<T> immutable(T first, char second) {
		return LObjCharPair.ImmObjCharPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjCharPair.ComparableObjCharPair<T> immComp(T first, char second) {
		return LObjCharPair.ImmCompObjCharPair.of(first, second);
	}

	public static <T> LObjBoolPair.MutObjBoolPair<T> tuple(T first, boolean second) {
		return LObjBoolPair.MutObjBoolPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjBoolPair.MutCompObjBoolPair<T> comparable(T first, boolean second) {
		return LObjBoolPair.MutCompObjBoolPair.of(first, second);
	}

	public static <T> LObjBoolPair<T> immutable(T first, boolean second) {
		return LObjBoolPair.ImmObjBoolPair.of(first, second);
	}

	public static <T extends Comparable<T>> LObjBoolPair.ComparableObjBoolPair<T> immComp(T first, boolean second) {
		return LObjBoolPair.ImmCompObjBoolPair.of(first, second);
	}

	public static <T1, T2> LBiObjByteTriple.MutBiObjByteTriple<T1, T2> tuple(T1 first, T2 second, byte third) {
		return LBiObjByteTriple.MutBiObjByteTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjByteTriple.MutCompBiObjByteTriple<T1, T2> comparable(T1 first, T2 second, byte third) {
		return LBiObjByteTriple.MutCompBiObjByteTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjByteTriple<T1, T2> immutable(T1 first, T2 second, byte third) {
		return LBiObjByteTriple.ImmBiObjByteTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjByteTriple.ComparableBiObjByteTriple<T1, T2> immComp(T1 first, T2 second, byte third) {
		return LBiObjByteTriple.ImmCompBiObjByteTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjShortTriple.MutBiObjShortTriple<T1, T2> tuple(T1 first, T2 second, short third) {
		return LBiObjShortTriple.MutBiObjShortTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjShortTriple.MutCompBiObjShortTriple<T1, T2> comparable(T1 first, T2 second, short third) {
		return LBiObjShortTriple.MutCompBiObjShortTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjShortTriple<T1, T2> immutable(T1 first, T2 second, short third) {
		return LBiObjShortTriple.ImmBiObjShortTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjShortTriple.ComparableBiObjShortTriple<T1, T2> immComp(T1 first, T2 second, short third) {
		return LBiObjShortTriple.ImmCompBiObjShortTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjIntTriple.MutBiObjIntTriple<T1, T2> tuple(T1 first, T2 second, int third) {
		return LBiObjIntTriple.MutBiObjIntTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjIntTriple.MutCompBiObjIntTriple<T1, T2> comparable(T1 first, T2 second, int third) {
		return LBiObjIntTriple.MutCompBiObjIntTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjIntTriple<T1, T2> immutable(T1 first, T2 second, int third) {
		return LBiObjIntTriple.ImmBiObjIntTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjIntTriple.ComparableBiObjIntTriple<T1, T2> immComp(T1 first, T2 second, int third) {
		return LBiObjIntTriple.ImmCompBiObjIntTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjLongTriple.MutBiObjLongTriple<T1, T2> tuple(T1 first, T2 second, long third) {
		return LBiObjLongTriple.MutBiObjLongTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjLongTriple.MutCompBiObjLongTriple<T1, T2> comparable(T1 first, T2 second, long third) {
		return LBiObjLongTriple.MutCompBiObjLongTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjLongTriple<T1, T2> immutable(T1 first, T2 second, long third) {
		return LBiObjLongTriple.ImmBiObjLongTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjLongTriple.ComparableBiObjLongTriple<T1, T2> immComp(T1 first, T2 second, long third) {
		return LBiObjLongTriple.ImmCompBiObjLongTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjFloatTriple.MutBiObjFloatTriple<T1, T2> tuple(T1 first, T2 second, float third) {
		return LBiObjFloatTriple.MutBiObjFloatTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjFloatTriple.MutCompBiObjFloatTriple<T1, T2> comparable(T1 first, T2 second, float third) {
		return LBiObjFloatTriple.MutCompBiObjFloatTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjFloatTriple<T1, T2> immutable(T1 first, T2 second, float third) {
		return LBiObjFloatTriple.ImmBiObjFloatTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjFloatTriple.ComparableBiObjFloatTriple<T1, T2> immComp(T1 first, T2 second, float third) {
		return LBiObjFloatTriple.ImmCompBiObjFloatTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjDoubleTriple.MutBiObjDoubleTriple<T1, T2> tuple(T1 first, T2 second, double third) {
		return LBiObjDoubleTriple.MutBiObjDoubleTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjDoubleTriple.MutCompBiObjDoubleTriple<T1, T2> comparable(T1 first, T2 second, double third) {
		return LBiObjDoubleTriple.MutCompBiObjDoubleTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjDoubleTriple<T1, T2> immutable(T1 first, T2 second, double third) {
		return LBiObjDoubleTriple.ImmBiObjDoubleTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjDoubleTriple.ComparableBiObjDoubleTriple<T1, T2> immComp(T1 first, T2 second, double third) {
		return LBiObjDoubleTriple.ImmCompBiObjDoubleTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjCharTriple.MutBiObjCharTriple<T1, T2> tuple(T1 first, T2 second, char third) {
		return LBiObjCharTriple.MutBiObjCharTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjCharTriple.MutCompBiObjCharTriple<T1, T2> comparable(T1 first, T2 second, char third) {
		return LBiObjCharTriple.MutCompBiObjCharTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjCharTriple<T1, T2> immutable(T1 first, T2 second, char third) {
		return LBiObjCharTriple.ImmBiObjCharTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjCharTriple.ComparableBiObjCharTriple<T1, T2> immComp(T1 first, T2 second, char third) {
		return LBiObjCharTriple.ImmCompBiObjCharTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjBoolTriple.MutBiObjBoolTriple<T1, T2> tuple(T1 first, T2 second, boolean third) {
		return LBiObjBoolTriple.MutBiObjBoolTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjBoolTriple.MutCompBiObjBoolTriple<T1, T2> comparable(T1 first, T2 second, boolean third) {
		return LBiObjBoolTriple.MutCompBiObjBoolTriple.of(first, second, third);
	}

	public static <T1, T2> LBiObjBoolTriple<T1, T2> immutable(T1 first, T2 second, boolean third) {
		return LBiObjBoolTriple.ImmBiObjBoolTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LBiObjBoolTriple.ComparableBiObjBoolTriple<T1, T2> immComp(T1 first, T2 second, boolean third) {
		return LBiObjBoolTriple.ImmCompBiObjBoolTriple.of(first, second, third);
	}

	public static LBytePair.MutBytePair tuple(byte first, byte second) {
		return LBytePair.MutBytePair.of(first, second);
	}

	public static LBytePair.MutCompBytePair comparable(byte first, byte second) {
		return LBytePair.MutCompBytePair.of(first, second);
	}

	public static LBytePair immutable(byte first, byte second) {
		return LBytePair.ImmBytePair.of(first, second);
	}

	public static LBytePair.ComparableBytePair immComp(byte first, byte second) {
		return LBytePair.ImmCompBytePair.of(first, second);
	}

	public static LShortPair.MutShortPair tuple(short first, short second) {
		return LShortPair.MutShortPair.of(first, second);
	}

	public static LShortPair.MutCompShortPair comparable(short first, short second) {
		return LShortPair.MutCompShortPair.of(first, second);
	}

	public static LShortPair immutable(short first, short second) {
		return LShortPair.ImmShortPair.of(first, second);
	}

	public static LShortPair.ComparableShortPair immComp(short first, short second) {
		return LShortPair.ImmCompShortPair.of(first, second);
	}

	public static LIntPair.MutIntPair tuple(int first, int second) {
		return LIntPair.MutIntPair.of(first, second);
	}

	public static LIntPair.MutCompIntPair comparable(int first, int second) {
		return LIntPair.MutCompIntPair.of(first, second);
	}

	public static LIntPair immutable(int first, int second) {
		return LIntPair.ImmIntPair.of(first, second);
	}

	public static LIntPair.ComparableIntPair immComp(int first, int second) {
		return LIntPair.ImmCompIntPair.of(first, second);
	}

	public static LLongPair.MutLongPair tuple(long first, long second) {
		return LLongPair.MutLongPair.of(first, second);
	}

	public static LLongPair.MutCompLongPair comparable(long first, long second) {
		return LLongPair.MutCompLongPair.of(first, second);
	}

	public static LLongPair immutable(long first, long second) {
		return LLongPair.ImmLongPair.of(first, second);
	}

	public static LLongPair.ComparableLongPair immComp(long first, long second) {
		return LLongPair.ImmCompLongPair.of(first, second);
	}

	public static LFloatPair.MutFloatPair tuple(float first, float second) {
		return LFloatPair.MutFloatPair.of(first, second);
	}

	public static LFloatPair.MutCompFloatPair comparable(float first, float second) {
		return LFloatPair.MutCompFloatPair.of(first, second);
	}

	public static LFloatPair immutable(float first, float second) {
		return LFloatPair.ImmFloatPair.of(first, second);
	}

	public static LFloatPair.ComparableFloatPair immComp(float first, float second) {
		return LFloatPair.ImmCompFloatPair.of(first, second);
	}

	public static LDoublePair.MutDoublePair tuple(double first, double second) {
		return LDoublePair.MutDoublePair.of(first, second);
	}

	public static LDoublePair.MutCompDoublePair comparable(double first, double second) {
		return LDoublePair.MutCompDoublePair.of(first, second);
	}

	public static LDoublePair immutable(double first, double second) {
		return LDoublePair.ImmDoublePair.of(first, second);
	}

	public static LDoublePair.ComparableDoublePair immComp(double first, double second) {
		return LDoublePair.ImmCompDoublePair.of(first, second);
	}

	public static LCharPair.MutCharPair tuple(char first, char second) {
		return LCharPair.MutCharPair.of(first, second);
	}

	public static LCharPair.MutCompCharPair comparable(char first, char second) {
		return LCharPair.MutCompCharPair.of(first, second);
	}

	public static LCharPair immutable(char first, char second) {
		return LCharPair.ImmCharPair.of(first, second);
	}

	public static LCharPair.ComparableCharPair immComp(char first, char second) {
		return LCharPair.ImmCompCharPair.of(first, second);
	}

	public static LBoolPair.MutBoolPair tuple(boolean first, boolean second) {
		return LBoolPair.MutBoolPair.of(first, second);
	}

	public static LBoolPair.MutCompBoolPair comparable(boolean first, boolean second) {
		return LBoolPair.MutCompBoolPair.of(first, second);
	}

	public static LBoolPair immutable(boolean first, boolean second) {
		return LBoolPair.ImmBoolPair.of(first, second);
	}

	public static LBoolPair.ComparableBoolPair immComp(boolean first, boolean second) {
		return LBoolPair.ImmCompBoolPair.of(first, second);
	}

	public static LBoolTriple.MutBoolTriple tuple(boolean first, boolean second, boolean third) {
		return LBoolTriple.MutBoolTriple.of(first, second, third);
	}

	public static LBoolTriple.MutCompBoolTriple comparable(boolean first, boolean second, boolean third) {
		return LBoolTriple.MutCompBoolTriple.of(first, second, third);
	}

	public static LBoolTriple immutable(boolean first, boolean second, boolean third) {
		return LBoolTriple.ImmBoolTriple.of(first, second, third);
	}

	public static LBoolTriple.ComparableBoolTriple immComp(boolean first, boolean second, boolean third) {
		return LBoolTriple.ImmCompBoolTriple.of(first, second, third);
	}

	public static LByteSingle.MutByteSingle tuple(byte first) {
		return LByteSingle.MutByteSingle.of(first);
	}

	public static LByteSingle.MutCompByteSingle comparable(byte first) {
		return LByteSingle.MutCompByteSingle.of(first);
	}

	public static LByteSingle immutable(byte first) {
		return LByteSingle.ImmByteSingle.of(first);
	}

	public static LByteSingle.ComparableByteSingle immComp(byte first) {
		return LByteSingle.ImmCompByteSingle.of(first);
	}

	public static LShortSingle.MutShortSingle tuple(short first) {
		return LShortSingle.MutShortSingle.of(first);
	}

	public static LShortSingle.MutCompShortSingle comparable(short first) {
		return LShortSingle.MutCompShortSingle.of(first);
	}

	public static LShortSingle immutable(short first) {
		return LShortSingle.ImmShortSingle.of(first);
	}

	public static LShortSingle.ComparableShortSingle immComp(short first) {
		return LShortSingle.ImmCompShortSingle.of(first);
	}

	public static LIntSingle.MutIntSingle tuple(int first) {
		return LIntSingle.MutIntSingle.of(first);
	}

	public static LIntSingle.MutCompIntSingle comparable(int first) {
		return LIntSingle.MutCompIntSingle.of(first);
	}

	public static LIntSingle immutable(int first) {
		return LIntSingle.ImmIntSingle.of(first);
	}

	public static LIntSingle.ComparableIntSingle immComp(int first) {
		return LIntSingle.ImmCompIntSingle.of(first);
	}

	public static LLongSingle.MutLongSingle tuple(long first) {
		return LLongSingle.MutLongSingle.of(first);
	}

	public static LLongSingle.MutCompLongSingle comparable(long first) {
		return LLongSingle.MutCompLongSingle.of(first);
	}

	public static LLongSingle immutable(long first) {
		return LLongSingle.ImmLongSingle.of(first);
	}

	public static LLongSingle.ComparableLongSingle immComp(long first) {
		return LLongSingle.ImmCompLongSingle.of(first);
	}

	public static LFloatSingle.MutFloatSingle tuple(float first) {
		return LFloatSingle.MutFloatSingle.of(first);
	}

	public static LFloatSingle.MutCompFloatSingle comparable(float first) {
		return LFloatSingle.MutCompFloatSingle.of(first);
	}

	public static LFloatSingle immutable(float first) {
		return LFloatSingle.ImmFloatSingle.of(first);
	}

	public static LFloatSingle.ComparableFloatSingle immComp(float first) {
		return LFloatSingle.ImmCompFloatSingle.of(first);
	}

	public static LDoubleSingle.MutDoubleSingle tuple(double first) {
		return LDoubleSingle.MutDoubleSingle.of(first);
	}

	public static LDoubleSingle.MutCompDoubleSingle comparable(double first) {
		return LDoubleSingle.MutCompDoubleSingle.of(first);
	}

	public static LDoubleSingle immutable(double first) {
		return LDoubleSingle.ImmDoubleSingle.of(first);
	}

	public static LDoubleSingle.ComparableDoubleSingle immComp(double first) {
		return LDoubleSingle.ImmCompDoubleSingle.of(first);
	}

	public static LCharSingle.MutCharSingle tuple(char first) {
		return LCharSingle.MutCharSingle.of(first);
	}

	public static LCharSingle.MutCompCharSingle comparable(char first) {
		return LCharSingle.MutCompCharSingle.of(first);
	}

	public static LCharSingle immutable(char first) {
		return LCharSingle.ImmCharSingle.of(first);
	}

	public static LCharSingle.ComparableCharSingle immComp(char first) {
		return LCharSingle.ImmCompCharSingle.of(first);
	}

	public static LBoolSingle.MutBoolSingle tuple(boolean first) {
		return LBoolSingle.MutBoolSingle.of(first);
	}

	public static LBoolSingle.MutCompBoolSingle comparable(boolean first) {
		return LBoolSingle.MutCompBoolSingle.of(first);
	}

	public static LBoolSingle immutable(boolean first) {
		return LBoolSingle.ImmBoolSingle.of(first);
	}

	public static LBoolSingle.ComparableBoolSingle immComp(boolean first) {
		return LBoolSingle.ImmCompBoolSingle.of(first);
	}

	public static <T> LSingle.MutSingle<T> tuple(T first) {
		return LSingle.MutSingle.of(first);
	}

	public static <T extends Comparable<T>> LSingle.MutCompSingle<T> comparable(T first) {
		return LSingle.MutCompSingle.of(first);
	}

	public static <T> LSingle<T> immutable(T first) {
		return LSingle.ImmSingle.of(first);
	}

	public static <T extends Comparable<T>> LSingle.ComparableSingle<T> immComp(T first) {
		return LSingle.ImmCompSingle.of(first);
	}

	public static <T1, T2> LPair.MutPair<T1, T2> tuple(T1 first, T2 second) {
		return LPair.MutPair.of(first, second);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LPair.MutCompPair<T1, T2> comparable(T1 first, T2 second) {
		return LPair.MutCompPair.of(first, second);
	}

	public static <T1, T2> LPair<T1, T2> immutable(T1 first, T2 second) {
		return LPair.ImmPair.of(first, second);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> LPair.ComparablePair<T1, T2> immComp(T1 first, T2 second) {
		return LPair.ImmCompPair.of(first, second);
	}

	public static <T1, T2, T3> LTriple.MutTriple<T1, T2, T3> tuple(T1 first, T2 second, T3 third) {
		return LTriple.MutTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> LTriple.MutCompTriple<T1, T2, T3> comparable(T1 first, T2 second, T3 third) {
		return LTriple.MutCompTriple.of(first, second, third);
	}

	public static <T1, T2, T3> LTriple<T1, T2, T3> immutable(T1 first, T2 second, T3 third) {
		return LTriple.ImmTriple.of(first, second, third);
	}

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> LTriple.ComparableTriple<T1, T2, T3> immComp(T1 first, T2 second, T3 third) {
		return LTriple.ImmCompTriple.of(first, second, third);
	}

	public static LTuple.Void tuple() {
		return LTuple.Void.INSTANCE;
	}

	private Tuple4U() {

	}
}