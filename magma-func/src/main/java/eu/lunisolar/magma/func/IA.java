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

package eu.lunisolar.magma.func;

import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.exceptions.Handling;
import eu.lunisolar.magma.basics.exceptions.X;
import eu.lunisolar.magma.basics.meta.aType;
import eu.lunisolar.magma.basics.meta.aType.*;
import eu.lunisolar.magma.basics.meta.functional.IndexedRead;
import eu.lunisolar.magma.basics.meta.functional.IndexedWrite;
import eu.lunisolar.magma.basics.meta.functional.type.OiFunction;
import eu.lunisolar.magma.basics.meta.functional.type.TieConsumer;
import eu.lunisolar.magma.basics.meta.functional.type.TieFunction;
import eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer;
import eu.lunisolar.magma.func.function.from.LOiFunction;
import eu.lunisolar.magma.func.function.to.*;
import eu.lunisolar.magma.func.function.to.LToIntFunction;
import eu.lunisolar.magma.func.predicate.LObjIntPredicate;

import javax.annotation.Nonnull;
import java.lang.reflect.*;
import java.util.*;

import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieBoolConsumer.tieBoolCons;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieByteConsumer.tieByteCons;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieCharConsumer.tieCharCons;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer.tieCons;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieDblConsumer.tieDblCons;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieFltConsumer.tieFltCons;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer.tieIntCons;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieLongConsumer.tieLongCons;
import static eu.lunisolar.magma.func.consumer.primitives.obj.LTieSrtConsumer.tieSrtCons;
import static eu.lunisolar.magma.func.function.from.LOiFunction.oiFunc;
import static eu.lunisolar.magma.func.function.to.LOiToByteFunction.oiToByteFunc;
import static eu.lunisolar.magma.func.function.to.LOiToCharFunction.oiToCharFunc;
import static eu.lunisolar.magma.func.function.to.LOiToDblFunction.oiToDblFunc;
import static eu.lunisolar.magma.func.function.to.LOiToFltFunction.oiToFltFunc;
import static eu.lunisolar.magma.func.function.to.LOiToIntFunction.oiToIntFunc;
import static eu.lunisolar.magma.func.function.to.LOiToLongFunction.oiToLongFunc;
import static eu.lunisolar.magma.func.function.to.LOiToSrtFunction.oiToSrtFunc;
import static eu.lunisolar.magma.func.predicate.LObjIntPredicate.objIntPred;

/**
 * Aggregates access functions (with index) to a specific container (e.g. array, list) of specific type (e.g. int[])
 * Such access do not protect against concurrent modification.
 */
public interface IA<C, E extends aType> extends IndexedRead<C, E>, IndexedWrite<C, E> {

	public static class The<C, E extends aType> implements IA<C, E> {

		private static final IA<List, a<Object>> LIST = iA(List::size, oiFunc(List::get), tieCons(List::set));

		private static final IA<Object[], a<Object>> ARRAY = iA(a -> a.length, oiFunc((a, i) -> a[i]), tieCons((a, i, e) -> a[i] = e));
		private static final IA<int[], aInt> INT_ARRAY = iA(IA::length, oiToIntFunc(IA::getInt), tieIntCons(IA::setInt));
		private static final IA<long[], aLong> LONG_ARRAY = iA(IA::length, oiToLongFunc(IA::getLong), tieLongCons(IA::setLong));
		private static final IA<double[], aDouble> DOUBLE_ARRAY = iA(IA::length, oiToDblFunc(IA::getDouble), tieDblCons(IA::setDouble));
		private static final IA<byte[], aByte> BYTE_ARRAY = iA(IA::length, oiToByteFunc(IA::getByte), tieByteCons(IA::setByte));
		private static final IA<short[], aShort> SHORT_ARRAY = iA(IA::length, oiToSrtFunc(IA::getShort), tieSrtCons(IA::setShort));
		private static final IA<char[], aChar> CHAR_ARRAY = iA(IA::length, oiToCharFunc(IA::getChar), tieCharCons(IA::setChar));
		private static final IA<float[], aFloat> FLOAT_ARRAY = iA(IA::length, oiToFltFunc(IA::getFloat), tieFltCons(IA::setFloat));
		private static final IA<boolean[], aBool> BOOL_ARRAY = iA(IA::length, objIntPred(IA::getBoolean), tieBoolCons(IA::setBoolean));

		private final LToIntFunction<C> sizeFunc;

		private final OiFunction<C, E> getter;
		private final TieConsumer<C, E> setter;
		private final TieFunction<C, E> setFunc;

		public The(LToIntFunction<C> size, OiFunction<C, E> getter, TieConsumer<C, E> setter) {
			this.sizeFunc = size;
			this.getter = getter;
			this.setter = setter;
			this.setFunc = setter.toTieFunction();
		}

		public LToIntFunction<C> genericSizeFunc() {
			return sizeFunc;
		}

		public LToIntFunction sizeFunc() {
			return genericSizeFunc();
		}

		public OiFunction<C, E> genericGetter() {
			return getter;
		}

		public TieConsumer<C, E> genericSetter() {
			return setter;
		}

		public TieFunction<C, E> genericSetFunc() {
			return setFunc;
		}

	}

	public static <C, E extends aType> IA<C, E> iA(LToIntFunction<C> size, OiFunction<C, E> oi, TieConsumer<C, E> tie) {
		return new IA.The<>(size, oi, tie);
	}

	public static <T, L extends List<T>, A extends a<T>> IA<L, A> list() {
		return (IA) The.LIST;
	}

	public static <T, L extends List<T>, A extends a<T>> IA<L, A> ia(List ignored) {
		return list();
	}

	public static <T, A extends a<T>> IA<T[], A> array() {
		return (IA) The.ARRAY;
	}

	public static <T, A extends a<T>> IA<T[], A> ia(T[] ignored) {
		return array();
	}

	public static IA<int[], aInt> intArray() {
		return (IA) The.INT_ARRAY;
	}

	public static IA<int[], aInt> ia(int[] ignored) {
		return intArray();
	}

	public static IA<long[], aLong> longArray() {
		return (IA) The.LONG_ARRAY;
	}

	public static IA<long[], aLong> ia(long[] ignored) {
		return longArray();
	}

	public static IA<double[], aDouble> doubleArray() {
		return (IA) The.DOUBLE_ARRAY;
	}

	public static IA<double[], aDouble> ia(double[] ignored) {
		return doubleArray();
	}

	public static IA<byte[], aByte> byteArray() {
		return (IA) The.BYTE_ARRAY;
	}

	public static IA<byte[], aByte> ia(byte[] ignored) {
		return byteArray();
	}

	public static IA<short[], aShort> shortArray() {
		return (IA) The.SHORT_ARRAY;
	}

	public static IA<short[], aShort> ia(short[] ignored) {
		return shortArray();
	}

	public static IA<char[], aChar> charArray() {
		return (IA) The.CHAR_ARRAY;
	}

	public static IA<char[], aChar> ia(char[] ignored) {
		return charArray();
	}

	public static IA<float[], aFloat> floatArray() {
		return (IA) The.FLOAT_ARRAY;
	}

	public static IA<float[], aFloat> ia(float[] ignored) {
		return floatArray();
	}

	public static IA<boolean[], aBool> boolArray() {
		return (IA) The.BOOL_ARRAY;
	}

	public static IA<boolean[], aBool> ia(boolean[] ignored) {
		return boolArray();
	}

	//<editor-fold desc="convenience methods for reference">

	// boolean

	public static int length(boolean[] array) {
		return array.length;
	}

	public static void setBoolean(boolean[] array, int index, boolean element) {
		array[index] = element;
	}

	public static boolean getBoolean(boolean[] array, int index) {
		return array[index];
	}

	// byte

	public static int length(byte[] array) {
		return array.length;
	}

	public static void setByte(byte[] array, int index, byte element) {
		array[index] = element;
	}

	public static byte getByte(byte[] array, int index) {
		return array[index];
	}

	// double

	public static int length(double[] array) {
		return array.length;
	}

	public static void setDouble(double[] array, int index, double element) {
		array[index] = element;
	}

	public static double getDouble(double[] array, int index) {
		return array[index];
	}

	// char

	public static int length(char[] array) {
		return array.length;
	}

	public static void setChar(char[] array, int index, char element) {
		array[index] = element;
	}

	public static char getChar(char[] array, int index) {
		return array[index];
	}

	// short

	public static int length(short[] array) {
		return array.length;
	}

	public static void setShort(short[] array, int index, short element) {
		array[index] = element;
	}

	public static short getShort(short[] array, int index) {
		return array[index];
	}

	// float

	public static int length(float[] array) {
		return array.length;
	}

	public static void setFloat(float[] array, int index, float element) {
		array[index] = element;
	}

	public static float getFloat(float[] array, int index) {
		return array[index];
	}

	// int

	public static int length(int[] array) {
		return array.length;
	}

	public static void setInt(int[] array, int index, int element) {
		array[index] = element;
	}

	public static int getInt(int[] array, int index) {
		return array[index];
	}

	// long

	public static int length(long[] array) {
		return array.length;
	}

	public static void setLong(long[] array, int index, long element) {
		array[index] = element;
	}

	public static long getLong(long[] array, int index) {
		return array[index];
	}

	// T

	public static <T> int length(T[] array) {
		return array.length;
	}

	public static <T> void set(T[] array, int index, T element) {
		array[index] = element;
	}

	public static <T> T get(T[] array, int index) {
		return array[index];
	}

	//</editor-fold>

	public static <E, C> @Nonnull List<E> toList(@Nonnull IndexedRead<C, a<E>> indexedRead, @Nonnull C container) {
		try {
			LToIntFunction<C> size = indexedRead.sizeFunc();
			LOiFunction<C, E> getter = indexedRead.getter();
			return new IndexedReadWrapper<>(container, size, getter);
		} catch (ClassCastException e) {
			throw Handling.wrap(e, X::arg, "Argument [indexedRead] is not compatible (e.g. supports only primitive types).");
		}
	}

	public static <E, C> @Nonnull List<E> toList(@Nonnull IA<C, a<E>> ia, @Nonnull C container) {
		try {
			LToIntFunction<C> size = ia.sizeFunc();
			LOiFunction<C, E> getter = ia.getter();
			LTieConsumer<C, E> setter = ia.setter();
			return new IAWrapper<>(container, size, getter, setter);
		} catch (ClassCastException e) {
			throw Handling.wrap(e, X::arg, "Argument [indexedRead] is not compatible (e.g. supports only primitive types).");
		}
	}

	class IndexedReadWrapper<C, E> extends AbstractList<E> implements RandomAccess {

		protected final C container;
		protected final LToIntFunction<C> size;
		protected final LOiFunction<C, E> getter;

		private IndexedReadWrapper(C container, @Nonnull LToIntFunction<C> size, @Nonnull LOiFunction<C, E> getter) {
			this.container = Null.nonNullArg(container, "container");
			this.size = Null.nonNullArg(size, "size");
			this.getter = Null.nonNullArg(getter, "getter");
		}

		@Override
		public E get(int index) {
			return (E) getter.apply(container, index);
		}

		@Override
		public int size() {
			return size.applyAsInt(container);
		}
	}

	class IAWrapper<C, E> extends IndexedReadWrapper<C, E> {

		protected final LTieConsumer<C, E> setter;

		private IAWrapper(C container, @Nonnull LToIntFunction<C> size, @Nonnull LOiFunction<C, E> getter, @Nonnull LTieConsumer<C, E> setter) {
			super(container, size, getter);
			this.setter = Null.nonNullArg(setter, "setter");
		}

		@Override
		public E set(int index, E element) {
			var oldElement = get(index);
			setter.accept(container, index, element);
			return oldElement;
		}
	}

	public static @Nonnull <C> LObjIntPredicate<C> boolGetter(@Nonnull IndexedRead<C, aBool> ia) {
		Null.nonNullArg(ia, "ia");
		return (LObjIntPredicate<C>) ia.getter();
	}

	public static @Nonnull <C> LOiToByteFunction<C> byteGetter(@Nonnull IndexedRead<C, aByte> ia) {
		Null.nonNullArg(ia, "ia");
		return (LOiToByteFunction<C>) ia.getter();
	}

	public static @Nonnull <C> LOiToDblFunction<C> dblGetter(@Nonnull IndexedRead<C, aDouble> ia) {
		Null.nonNullArg(ia, "ia");
		return (LOiToDblFunction<C>) ia.getter();
	}

	public static @Nonnull <C> LOiToCharFunction<C> charGetter(@Nonnull IndexedRead<C, aChar> ia) {
		Null.nonNullArg(ia, "ia");
		return (LOiToCharFunction<C>) ia.getter();
	}

	public static @Nonnull <C> LOiToSrtFunction<C> srtGetter(@Nonnull IndexedRead<C, aShort> ia) {
		Null.nonNullArg(ia, "ia");
		return (LOiToSrtFunction<C>) ia.getter();
	}

	public static @Nonnull <C> LOiToFltFunction<C> fltGetter(@Nonnull IndexedRead<C, aFloat> ia) {
		Null.nonNullArg(ia, "ia");
		return (LOiToFltFunction<C>) ia.getter();
	}

	public static @Nonnull <C> LOiToIntFunction<C> intGetter(@Nonnull IndexedRead<C, aInt> ia) {
		Null.nonNullArg(ia, "ia");
		return (LOiToIntFunction<C>) ia.getter();
	}

	public static @Nonnull <C> LOiToLongFunction<C> longGetter(@Nonnull IndexedRead<C, aLong> ia) {
		Null.nonNullArg(ia, "ia");
		return (LOiToLongFunction<C>) ia.getter();
	}

	public static @Nonnull <C, V> LOiFunction<C, V> getter(@Nonnull IndexedRead<C, a<V>> ia) {
		Null.nonNullArg(ia, "ia");
		return (LOiFunction<C, V>) ia.getter();
	}

}