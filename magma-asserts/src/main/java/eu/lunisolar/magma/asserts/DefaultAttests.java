/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.asserts;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.math.*;
import java.time.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.function.*;
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.std.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.action.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.asserts.func.supplier.*; // NOSONAR
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
import eu.lunisolar.magma.asserts.opt.*; // NOSONAR
import eu.lunisolar.magma.func.supp.opt.*; // NOSONAR
//import eu.lunisolar.magma.asserts.std.*; // NOSONAR

import static org.assertj.core.api.Fail.fail;

/**
 * Default implementation of assertion factories. Always use with/by provided type argument OS otherwise compiler will not be able to infer the type of
 * assertion class.
 *
 * @param OS required base class for object assertions. It need to be provided in the use case otherwise compiler will not be able to infer the type.
 */
@SuppressWarnings("ALL")
public interface DefaultAttests<OS extends Assert> extends BasicAssertions<OS> {

	default <RS extends Assert, R> DefaultAttests<RS> withinCodomain(final Function<R, RS> codomainAssertFactory) {
		return new DefaultAttests<RS>() {
			public RS attestThatObj(Object actual) {
				return codomainAssertFactory.apply((R) actual);
			}
		};
	}

	default <RS extends Assert, R> DefaultAttests<RS> withinCodomain(final Function<R, RS> codomainAssertFactory, Class<R> r, Class<RS> rs) {
		return new DefaultAttests<RS>() {
			public RS attestThatObj(Object actual) {
				return codomainAssertFactory.apply((R) actual);
			}
		};
	}

	default DefaultAttests<AbstractOptionalAssert> withinOptionalCodomain() {
		return new DefaultAttests<AbstractOptionalAssert>() {
			@Override
			public AbstractOptionalAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Optional) actual);
			}
		};
	}

	default DefaultAttests<AbstractBigDecimalAssert> withinBigDecimalCodomain() {
		return new DefaultAttests<AbstractBigDecimalAssert>() {
			@Override
			public AbstractBigDecimalAssert attestThatObj(Object actual) {
				return Assertions.assertThat((BigDecimal) actual);
			}
		};
	}

	default DefaultAttests<AbstractBooleanAssert> withinBooleanCodomain() {
		return new DefaultAttests<AbstractBooleanAssert>() {
			@Override
			public AbstractBooleanAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Boolean) actual);
			}
		};
	}

	default DefaultAttests<AbstractBooleanArrayAssert> withinBooleanArrayCodomain() {
		return new DefaultAttests<AbstractBooleanArrayAssert>() {
			@Override
			public AbstractBooleanArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((boolean[]) actual);
			}
		};
	}

	default DefaultAttests<AbstractByteAssert> withinByteCodomain() {
		return new DefaultAttests<AbstractByteAssert>() {
			@Override
			public AbstractByteAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Byte) actual);
			}
		};
	}

	default DefaultAttests<AbstractByteArrayAssert> withinByteArrayCodomain() {
		return new DefaultAttests<AbstractByteArrayAssert>() {
			@Override
			public AbstractByteArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((byte[]) actual);
			}
		};
	}

	default DefaultAttests<AbstractCharacterAssert> withinCharacterCodomain() {
		return new DefaultAttests<AbstractCharacterAssert>() {
			@Override
			public AbstractCharacterAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Character) actual);
			}
		};
	}

	default DefaultAttests<AbstractCharArrayAssert> withinCharArrayCodomain() {
		return new DefaultAttests<AbstractCharArrayAssert>() {
			@Override
			public AbstractCharArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((char[]) actual);
			}
		};
	}

	default DefaultAttests<AbstractClassAssert> withinClassCodomain() {
		return new DefaultAttests<AbstractClassAssert>() {
			@Override
			public AbstractClassAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Class) actual);
			}
		};
	}

	default DefaultAttests<AbstractComparableAssert> withinComparableCodomain() {
		return new DefaultAttests<AbstractComparableAssert>() {
			@Override
			public AbstractComparableAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Comparable) actual);
			}
		};
	}

	default DefaultAttests<AbstractIterableAssert> withinIterableCodomain() {
		return new DefaultAttests<AbstractIterableAssert>() {
			@Override
			public AbstractIterableAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Iterable) actual);
			}
		};
	}

	default DefaultAttests<AbstractIteratorAssert> withinIteratorCodomain() {
		return new DefaultAttests<AbstractIteratorAssert>() {
			@Override
			public AbstractIteratorAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Iterator) actual);
			}
		};
	}

	default DefaultAttests<AbstractDoubleAssert> withinDoubleCodomain() {
		return new DefaultAttests<AbstractDoubleAssert>() {
			@Override
			public AbstractDoubleAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Double) actual);
			}
		};
	}

	default DefaultAttests<AbstractDoubleArrayAssert> withinDoubleArrayCodomain() {
		return new DefaultAttests<AbstractDoubleArrayAssert>() {
			@Override
			public AbstractDoubleArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((double[]) actual);
			}
		};
	}

	default DefaultAttests<AbstractPathAssert> withinPathCodomain() {
		return new DefaultAttests<AbstractPathAssert>() {
			@Override
			public AbstractPathAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Path) actual);
			}
		};
	}

	default DefaultAttests<AbstractInputStreamAssert> withinInputStreamCodomain() {
		return new DefaultAttests<AbstractInputStreamAssert>() {
			@Override
			public AbstractInputStreamAssert attestThatObj(Object actual) {
				return Assertions.assertThat((InputStream) actual);
			}
		};
	}

	default DefaultAttests<AbstractFloatAssert> withinFloatCodomain() {
		return new DefaultAttests<AbstractFloatAssert>() {
			@Override
			public AbstractFloatAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Float) actual);
			}
		};
	}

	default DefaultAttests<AbstractFloatArrayAssert> withinFloatArrayCodomain() {
		return new DefaultAttests<AbstractFloatArrayAssert>() {
			@Override
			public AbstractFloatArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((float[]) actual);
			}
		};
	}

	default DefaultAttests<AbstractIntegerAssert> withinIntegerCodomain() {
		return new DefaultAttests<AbstractIntegerAssert>() {
			@Override
			public AbstractIntegerAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Integer) actual);
			}
		};
	}

	default DefaultAttests<AbstractIntArrayAssert> withinIntArrayCodomain() {
		return new DefaultAttests<AbstractIntArrayAssert>() {
			@Override
			public AbstractIntArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((int[]) actual);
			}
		};
	}

	default DefaultAttests<AbstractListAssert> withinListCodomain() {
		return new DefaultAttests<AbstractListAssert>() {
			@Override
			public AbstractListAssert attestThatObj(Object actual) {
				return Assertions.assertThat((List) actual);
			}
		};
	}

	default DefaultAttests<AbstractLongAssert> withinLongCodomain() {
		return new DefaultAttests<AbstractLongAssert>() {
			@Override
			public AbstractLongAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Long) actual);
			}
		};
	}

	default DefaultAttests<AbstractLongArrayAssert> withinLongArrayCodomain() {
		return new DefaultAttests<AbstractLongArrayAssert>() {
			@Override
			public AbstractLongArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((long[]) actual);
			}
		};
	}

	default DefaultAttests<AbstractMapAssert> withinMapCodomain() {
		return new DefaultAttests<AbstractMapAssert>() {
			@Override
			public AbstractMapAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Map) actual);
			}
		};
	}

	default DefaultAttests<AbstractShortAssert> withinShortCodomain() {
		return new DefaultAttests<AbstractShortAssert>() {
			@Override
			public AbstractShortAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Short) actual);
			}
		};
	}

	default DefaultAttests<AbstractShortArrayAssert> withinShortArrayCodomain() {
		return new DefaultAttests<AbstractShortArrayAssert>() {
			@Override
			public AbstractShortArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((short[]) actual);
			}
		};
	}

	default DefaultAttests<AbstractCharSequenceAssert> withinCharSequenceCodomain() {
		return new DefaultAttests<AbstractCharSequenceAssert>() {
			@Override
			public AbstractCharSequenceAssert attestThatObj(Object actual) {
				return Assertions.assertThat((CharSequence) actual);
			}
		};
	}

	default DefaultAttests<AbstractCharSequenceAssert> withinStringCodomain() {
		return new DefaultAttests<AbstractCharSequenceAssert>() {
			@Override
			public AbstractCharSequenceAssert attestThatObj(Object actual) {
				return Assertions.assertThat((String) actual);
			}
		};
	}

	default DefaultAttests<AbstractDateAssert> withinDateCodomain() {
		return new DefaultAttests<AbstractDateAssert>() {
			@Override
			public AbstractDateAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Date) actual);
			}
		};
	}

	default DefaultAttests<AbstractZonedDateTimeAssert> withinZonedDateTimeCodomain() {
		return new DefaultAttests<AbstractZonedDateTimeAssert>() {
			@Override
			public AbstractZonedDateTimeAssert attestThatObj(Object actual) {
				return Assertions.assertThat((ZonedDateTime) actual);
			}
		};
	}

	default DefaultAttests<AbstractLocalDateTimeAssert> withinLocalDateTimeCodomain() {
		return new DefaultAttests<AbstractLocalDateTimeAssert>() {
			@Override
			public AbstractLocalDateTimeAssert attestThatObj(Object actual) {
				return Assertions.assertThat((LocalDateTime) actual);
			}
		};
	}

	default DefaultAttests<AbstractLocalTimeAssert> withinLocalTimeCodomain() {
		return new DefaultAttests<AbstractLocalTimeAssert>() {
			@Override
			public AbstractLocalTimeAssert attestThatObj(Object actual) {
				return Assertions.assertThat((LocalTime) actual);
			}
		};
	}

	default DefaultAttests<AbstractLocalDateAssert> withinLocalDateCodomain() {
		return new DefaultAttests<AbstractLocalDateAssert>() {
			@Override
			public AbstractLocalDateAssert attestThatObj(Object actual) {
				return Assertions.assertThat((LocalDate) actual);
			}
		};
	}

	default DefaultAttests<AbstractThrowableAssert> withinThrowableCodomain() {
		return new DefaultAttests<AbstractThrowableAssert>() {
			@Override
			public AbstractThrowableAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Throwable) actual);
			}
		};
	}

	default <T> DefaultAttests<AbstractObjectArrayAssert> withinTArrayCodomain() {
		return new DefaultAttests<AbstractObjectArrayAssert>() {
			@Override
			public AbstractObjectArrayAssert attestThatObj(Object actual) {
				return Assertions.assertThat((Object[]) actual);
			}
		};
	}

	@Nonnull
	default <A extends LAction> LActionAssert.The<A> attestAct(LAction func) {
		return new LActionAssert.The(func);
	}

	@Nonnull
	default <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> attestBiCons(LBiConsumer<T1, T2> func) {
		return new LBiConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiConsumer.LObj1Obj0Cons<T2, T1>, T2, T1> LObj1Obj0ConsAssert.The<A, T2, T1> attestObj1Obj0Cons(LBiConsumer.LObj1Obj0Cons<T2, T1> func) {
		return new LObj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> attestCons(LConsumer<T> func) {
		return new LConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LQuadConsumer<T1, T2, T3, T4>, T1, T2, T3, T4> LQuadConsumerAssert.The<A, T1, T2, T3, T4> attestQuadCons(LQuadConsumer<T1, T2, T3, T4> func) {
		return new LQuadConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LQuintConsumer<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> LQuintConsumerAssert.The<A, T1, T2, T3, T4, T5> attestQuintCons(LQuintConsumer<T1, T2, T3, T4, T5> func) {
		return new LQuintConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> attestTriCons(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2>, T1, T3, T2> LObj0Obj2Obj1ConsAssert.The<A, T1, T3, T2> attestObj0Obj2Obj1Cons(LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2> func) {
		return new LObj0Obj2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LObj1BiObj2Cons<T2, T1, T3>, T2, T1, T3> LObj1BiObj2ConsAssert.The<A, T2, T1, T3> attestObj1BiObj2Cons(LTriConsumer.LObj1BiObj2Cons<T2, T1, T3> func) {
		return new LObj1BiObj2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1>, T2, T3, T1> LObj1Obj2Obj0ConsAssert.The<A, T2, T3, T1> attestObj1Obj2Obj0Cons(LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1> func) {
		return new LObj1Obj2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2>, T3, T1, T2> LObj2Obj0Obj1ConsAssert.The<A, T3, T1, T2> attestObj2Obj0Obj1Cons(LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2> func) {
		return new LObj2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1>, T3, T2, T1> LBiObj1Obj0ConsAssert.The<A, T3, T2, T1> attestBiObj1Obj0Cons(LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1> func) {
		return new LBiObj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolConsumer> LBoolConsumerAssert.The<A> attestBoolCons(LBoolConsumer func) {
		return new LBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LByteConsumer> LByteConsumerAssert.The<A> attestByteCons(LByteConsumer func) {
		return new LByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LCharConsumer> LCharConsumerAssert.The<A> attestCharCons(LCharConsumer func) {
		return new LCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LDblConsumer> LDblConsumerAssert.The<A> attestDblCons(LDblConsumer func) {
		return new LDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LFltConsumer> LFltConsumerAssert.The<A> attestFltCons(LFltConsumer func) {
		return new LFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LIntConsumer> LIntConsumerAssert.The<A> attestIntCons(LIntConsumer func) {
		return new LIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LLongConsumer> LLongConsumerAssert.The<A> attestLongCons(LLongConsumer func) {
		return new LLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LSrtConsumer> LSrtConsumerAssert.The<A> attestSrtCons(LSrtConsumer func) {
		return new LSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiBoolConsumer> LBiBoolConsumerAssert.The<A> attestBiBoolCons(LBiBoolConsumer func) {
		return new LBiBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiBoolConsumer.LBool1Bool0Cons> LBool1Bool0ConsAssert.The<A> attestBool1Bool0Cons(LBiBoolConsumer.LBool1Bool0Cons func) {
		return new LBool1Bool0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> attestBiByteCons(LBiByteConsumer func) {
		return new LBiByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiByteConsumer.LByte1Byte0Cons> LByte1Byte0ConsAssert.The<A> attestByte1Byte0Cons(LBiByteConsumer.LByte1Byte0Cons func) {
		return new LByte1Byte0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> attestBiCharCons(LBiCharConsumer func) {
		return new LBiCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiCharConsumer.LChar1Char0Cons> LChar1Char0ConsAssert.The<A> attestChar1Char0Cons(LBiCharConsumer.LChar1Char0Cons func) {
		return new LChar1Char0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiDblConsumer> LBiDblConsumerAssert.The<A> attestBiDblCons(LBiDblConsumer func) {
		return new LBiDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiDblConsumer.LDbl1Dbl0Cons> LDbl1Dbl0ConsAssert.The<A> attestDbl1Dbl0Cons(LBiDblConsumer.LDbl1Dbl0Cons func) {
		return new LDbl1Dbl0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiFltConsumer> LBiFltConsumerAssert.The<A> attestBiFltCons(LBiFltConsumer func) {
		return new LBiFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiFltConsumer.LFlt1Flt0Cons> LFlt1Flt0ConsAssert.The<A> attestFlt1Flt0Cons(LBiFltConsumer.LFlt1Flt0Cons func) {
		return new LFlt1Flt0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> attestBiIntCons(LBiIntConsumer func) {
		return new LBiIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiIntConsumer.LInt1Int0Cons> LInt1Int0ConsAssert.The<A> attestInt1Int0Cons(LBiIntConsumer.LInt1Int0Cons func) {
		return new LInt1Int0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> attestBiLongCons(LBiLongConsumer func) {
		return new LBiLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiLongConsumer.LLong1Long0Cons> LLong1Long0ConsAssert.The<A> attestLong1Long0Cons(LBiLongConsumer.LLong1Long0Cons func) {
		return new LLong1Long0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiSrtConsumer> LBiSrtConsumerAssert.The<A> attestBiSrtCons(LBiSrtConsumer func) {
		return new LBiSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiSrtConsumer.LSrt1Srt0Cons> LSrt1Srt0ConsAssert.The<A> attestSrt1Srt0Cons(LBiSrtConsumer.LSrt1Srt0Cons func) {
		return new LSrt1Srt0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolIntConsumer> LBoolIntConsumerAssert.The<A> attestBoolIntCons(LBoolIntConsumer func) {
		return new LBoolIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolIntConsumer.LIntBoolCons> LIntBoolConsAssert.The<A> attestIntBoolCons(LBoolIntConsumer.LIntBoolCons func) {
		return new LIntBoolConsAssert.The(func);
	}

	@Nonnull
	default <A extends LByteIntConsumer> LByteIntConsumerAssert.The<A> attestByteIntCons(LByteIntConsumer func) {
		return new LByteIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LByteIntConsumer.LIntByteCons> LIntByteConsAssert.The<A> attestIntByteCons(LByteIntConsumer.LIntByteCons func) {
		return new LIntByteConsAssert.The(func);
	}

	@Nonnull
	default <A extends LCharIntConsumer> LCharIntConsumerAssert.The<A> attestCharIntCons(LCharIntConsumer func) {
		return new LCharIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LCharIntConsumer.LIntCharCons> LIntCharConsAssert.The<A> attestIntCharCons(LCharIntConsumer.LIntCharCons func) {
		return new LIntCharConsAssert.The(func);
	}

	@Nonnull
	default <A extends LDblIntConsumer> LDblIntConsumerAssert.The<A> attestDblIntCons(LDblIntConsumer func) {
		return new LDblIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LDblIntConsumer.LIntDblCons> LIntDblConsAssert.The<A> attestIntDblCons(LDblIntConsumer.LIntDblCons func) {
		return new LIntDblConsAssert.The(func);
	}

	@Nonnull
	default <A extends LFltIntConsumer> LFltIntConsumerAssert.The<A> attestFltIntCons(LFltIntConsumer func) {
		return new LFltIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LFltIntConsumer.LIntFltCons> LIntFltConsAssert.The<A> attestIntFltCons(LFltIntConsumer.LIntFltCons func) {
		return new LIntFltConsAssert.The(func);
	}

	@Nonnull
	default <A extends LLongIntConsumer> LLongIntConsumerAssert.The<A> attestLongIntCons(LLongIntConsumer func) {
		return new LLongIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LLongIntConsumer.LIntLongCons> LIntLongConsAssert.The<A> attestIntLongCons(LLongIntConsumer.LIntLongCons func) {
		return new LIntLongConsAssert.The(func);
	}

	@Nonnull
	default <A extends LSrtIntConsumer> LSrtIntConsumerAssert.The<A> attestSrtIntCons(LSrtIntConsumer func) {
		return new LSrtIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LSrtIntConsumer.LIntSrtCons> LIntSrtConsAssert.The<A> attestIntSrtCons(LSrtIntConsumer.LIntSrtCons func) {
		return new LIntSrtConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> attestBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2>, T1, T2> LObj0Bool2Obj1ConsAssert.The<A, T1, T2> attestObj0Bool2Obj1Cons(LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2> func) {
		return new LObj0Bool2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1>, T2, T1> LObj1Obj0Bool2ConsAssert.The<A, T2, T1> attestObj1Obj0Bool2Cons(LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1> func) {
		return new LObj1Obj0Bool2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1>, T2, T1> LObj1Bool2Obj0ConsAssert.The<A, T2, T1> attestObj1Bool2Obj0Cons(LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1> func) {
		return new LObj1Bool2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2>, T1, T2> LBool2Obj0Obj1ConsAssert.The<A, T1, T2> attestBool2Obj0Obj1Cons(LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2> func) {
		return new LBool2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1>, T2, T1> LBool2Obj1Obj0ConsAssert.The<A, T2, T1> attestBool2Obj1Obj0Cons(LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1> func) {
		return new LBool2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> attestBiObjByteCons(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2>, T1, T2> LObj0Byte2Obj1ConsAssert.The<A, T1, T2> attestObj0Byte2Obj1Cons(LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> func) {
		return new LObj0Byte2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1>, T2, T1> LObj1Obj0Byte2ConsAssert.The<A, T2, T1> attestObj1Obj0Byte2Cons(LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> func) {
		return new LObj1Obj0Byte2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1>, T2, T1> LObj1Byte2Obj0ConsAssert.The<A, T2, T1> attestObj1Byte2Obj0Cons(LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> func) {
		return new LObj1Byte2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2>, T1, T2> LByte2Obj0Obj1ConsAssert.The<A, T1, T2> attestByte2Obj0Obj1Cons(LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> func) {
		return new LByte2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1>, T2, T1> LByte2Obj1Obj0ConsAssert.The<A, T2, T1> attestByte2Obj1Obj0Cons(LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> func) {
		return new LByte2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> attestBiObjCharCons(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2>, T1, T2> LObj0Char2Obj1ConsAssert.The<A, T1, T2> attestObj0Char2Obj1Cons(LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2> func) {
		return new LObj0Char2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1>, T2, T1> LObj1Obj0Char2ConsAssert.The<A, T2, T1> attestObj1Obj0Char2Cons(LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1> func) {
		return new LObj1Obj0Char2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1>, T2, T1> LObj1Char2Obj0ConsAssert.The<A, T2, T1> attestObj1Char2Obj0Cons(LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1> func) {
		return new LObj1Char2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2>, T1, T2> LChar2Obj0Obj1ConsAssert.The<A, T1, T2> attestChar2Obj0Obj1Cons(LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2> func) {
		return new LChar2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1>, T2, T1> LChar2Obj1Obj0ConsAssert.The<A, T2, T1> attestChar2Obj1Obj0Cons(LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1> func) {
		return new LChar2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer<T1, T2>, T1, T2> LBiObjDblConsumerAssert.The<A, T1, T2> attestBiObjDblCons(LBiObjDblConsumer<T1, T2> func) {
		return new LBiObjDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2>, T1, T2> LObj0Dbl2Obj1ConsAssert.The<A, T1, T2> attestObj0Dbl2Obj1Cons(LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2> func) {
		return new LObj0Dbl2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1>, T2, T1> LObj1Obj0Dbl2ConsAssert.The<A, T2, T1> attestObj1Obj0Dbl2Cons(LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1> func) {
		return new LObj1Obj0Dbl2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1>, T2, T1> LObj1Dbl2Obj0ConsAssert.The<A, T2, T1> attestObj1Dbl2Obj0Cons(LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1> func) {
		return new LObj1Dbl2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2>, T1, T2> LDbl2Obj0Obj1ConsAssert.The<A, T1, T2> attestDbl2Obj0Obj1Cons(LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2> func) {
		return new LDbl2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1>, T2, T1> LDbl2Obj1Obj0ConsAssert.The<A, T2, T1> attestDbl2Obj1Obj0Cons(LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1> func) {
		return new LDbl2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer<T1, T2>, T1, T2> LBiObjFltConsumerAssert.The<A, T1, T2> attestBiObjFltCons(LBiObjFltConsumer<T1, T2> func) {
		return new LBiObjFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2>, T1, T2> LObj0Flt2Obj1ConsAssert.The<A, T1, T2> attestObj0Flt2Obj1Cons(LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2> func) {
		return new LObj0Flt2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1>, T2, T1> LObj1Obj0Flt2ConsAssert.The<A, T2, T1> attestObj1Obj0Flt2Cons(LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1> func) {
		return new LObj1Obj0Flt2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1>, T2, T1> LObj1Flt2Obj0ConsAssert.The<A, T2, T1> attestObj1Flt2Obj0Cons(LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1> func) {
		return new LObj1Flt2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2>, T1, T2> LFlt2Obj0Obj1ConsAssert.The<A, T1, T2> attestFlt2Obj0Obj1Cons(LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2> func) {
		return new LFlt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1>, T2, T1> LFlt2Obj1Obj0ConsAssert.The<A, T2, T1> attestFlt2Obj1Obj0Cons(LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1> func) {
		return new LFlt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> attestBiObjIntCons(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1>, T2, T1> LObj1Obj0Int2ConsAssert.The<A, T2, T1> attestObj1Obj0Int2Cons(LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1> func) {
		return new LObj1Obj0Int2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2>, T1, T2> LInt2Obj0Obj1ConsAssert.The<A, T1, T2> attestInt2Obj0Obj1Cons(LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> func) {
		return new LInt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1>, T2, T1> LInt2Obj1Obj0ConsAssert.The<A, T2, T1> attestInt2Obj1Obj0Cons(LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1> func) {
		return new LInt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> attestBiObjLongCons(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2>, T1, T2> LObj0Long2Obj1ConsAssert.The<A, T1, T2> attestObj0Long2Obj1Cons(LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2> func) {
		return new LObj0Long2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1>, T2, T1> LObj1Obj0Long2ConsAssert.The<A, T2, T1> attestObj1Obj0Long2Cons(LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1> func) {
		return new LObj1Obj0Long2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1>, T2, T1> LObj1Long2Obj0ConsAssert.The<A, T2, T1> attestObj1Long2Obj0Cons(LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1> func) {
		return new LObj1Long2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2>, T1, T2> LLong2Obj0Obj1ConsAssert.The<A, T1, T2> attestLong2Obj0Obj1Cons(LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2> func) {
		return new LLong2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1>, T2, T1> LLong2Obj1Obj0ConsAssert.The<A, T2, T1> attestLong2Obj1Obj0Cons(LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1> func) {
		return new LLong2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer<T1, T2>, T1, T2> LBiObjSrtConsumerAssert.The<A, T1, T2> attestBiObjSrtCons(LBiObjSrtConsumer<T1, T2> func) {
		return new LBiObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2>, T1, T2> LObj0Srt2Obj1ConsAssert.The<A, T1, T2> attestObj0Srt2Obj1Cons(LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> func) {
		return new LObj0Srt2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1>, T2, T1> LObj1Obj0Srt2ConsAssert.The<A, T2, T1> attestObj1Obj0Srt2Cons(LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1> func) {
		return new LObj1Obj0Srt2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1>, T2, T1> LObj1Srt2Obj0ConsAssert.The<A, T2, T1> attestObj1Srt2Obj0Cons(LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1> func) {
		return new LObj1Srt2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2>, T1, T2> LSrt2Obj0Obj1ConsAssert.The<A, T1, T2> attestSrt2Obj0Obj1Cons(LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> func) {
		return new LSrt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1>, T2, T1> LSrt2Obj1Obj0ConsAssert.The<A, T2, T1> attestSrt2Obj1Obj0Cons(LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1> func) {
		return new LSrt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> attestObjBoolCons(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjBoolConsumer.LBoolObjCons<T>, T> LBoolObjConsAssert.The<A, T> attestBoolObjCons(LObjBoolConsumer.LBoolObjCons<T> func) {
		return new LBoolObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> attestObjByteCons(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjByteConsumer.LByteObjCons<T>, T> LByteObjConsAssert.The<A, T> attestByteObjCons(LObjByteConsumer.LByteObjCons<T> func) {
		return new LByteObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> attestObjCharCons(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjCharConsumer.LCharObjCons<T>, T> LCharObjConsAssert.The<A, T> attestCharObjCons(LObjCharConsumer.LCharObjCons<T> func) {
		return new LCharObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjDblConsumer<T>, T> LObjDblConsumerAssert.The<A, T> attestObjDblCons(LObjDblConsumer<T> func) {
		return new LObjDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjDblConsumer.LDblObjCons<T>, T> LDblObjConsAssert.The<A, T> attestDblObjCons(LObjDblConsumer.LDblObjCons<T> func) {
		return new LDblObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjFltConsumer<T>, T> LObjFltConsumerAssert.The<A, T> attestObjFltCons(LObjFltConsumer<T> func) {
		return new LObjFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjFltConsumer.LFltObjCons<T>, T> LFltObjConsAssert.The<A, T> attestFltObjCons(LObjFltConsumer.LFltObjCons<T> func) {
		return new LFltObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> attestObjIntCons(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjIntConsumer.LIntObjCons<T>, T> LIntObjConsAssert.The<A, T> attestIntObjCons(LObjIntConsumer.LIntObjCons<T> func) {
		return new LIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> attestObjLongCons(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjLongConsumer.LLongObjCons<T>, T> LLongObjConsAssert.The<A, T> attestLongObjCons(LObjLongConsumer.LLongObjCons<T> func) {
		return new LLongObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjSrtConsumer<T>, T> LObjSrtConsumerAssert.The<A, T> attestObjSrtCons(LObjSrtConsumer<T> func) {
		return new LObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjSrtConsumer.LSrtObjCons<T>, T> LSrtObjConsAssert.The<A, T> attestSrtObjCons(LObjSrtConsumer.LSrtObjCons<T> func) {
		return new LSrtObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer<T>, T> LTieBoolConsumerAssert.The<A, T> attestTieBoolCons(LTieBoolConsumer<T> func) {
		return new LTieBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LObjBoolIntCons<T>, T> LObjBoolIntConsAssert.The<A, T> attestObjBoolIntCons(LTieBoolConsumer.LObjBoolIntCons<T> func) {
		return new LObjBoolIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LIntObjBoolCons<T>, T> LIntObjBoolConsAssert.The<A, T> attestIntObjBoolCons(LTieBoolConsumer.LIntObjBoolCons<T> func) {
		return new LIntObjBoolConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LIntBoolObjCons<T>, T> LIntBoolObjConsAssert.The<A, T> attestIntBoolObjCons(LTieBoolConsumer.LIntBoolObjCons<T> func) {
		return new LIntBoolObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LBoolObjIntCons<T>, T> LBoolObjIntConsAssert.The<A, T> attestBoolObjIntCons(LTieBoolConsumer.LBoolObjIntCons<T> func) {
		return new LBoolObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LBoolIntObjCons<T>, T> LBoolIntObjConsAssert.The<A, T> attestBoolIntObjCons(LTieBoolConsumer.LBoolIntObjCons<T> func) {
		return new LBoolIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer<T>, T> LTieByteConsumerAssert.The<A, T> attestTieByteCons(LTieByteConsumer<T> func) {
		return new LTieByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LObjByteIntCons<T>, T> LObjByteIntConsAssert.The<A, T> attestObjByteIntCons(LTieByteConsumer.LObjByteIntCons<T> func) {
		return new LObjByteIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LIntObjByteCons<T>, T> LIntObjByteConsAssert.The<A, T> attestIntObjByteCons(LTieByteConsumer.LIntObjByteCons<T> func) {
		return new LIntObjByteConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LIntByteObjCons<T>, T> LIntByteObjConsAssert.The<A, T> attestIntByteObjCons(LTieByteConsumer.LIntByteObjCons<T> func) {
		return new LIntByteObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LByteObjIntCons<T>, T> LByteObjIntConsAssert.The<A, T> attestByteObjIntCons(LTieByteConsumer.LByteObjIntCons<T> func) {
		return new LByteObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LByteIntObjCons<T>, T> LByteIntObjConsAssert.The<A, T> attestByteIntObjCons(LTieByteConsumer.LByteIntObjCons<T> func) {
		return new LByteIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer<T>, T> LTieCharConsumerAssert.The<A, T> attestTieCharCons(LTieCharConsumer<T> func) {
		return new LTieCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LObjCharIntCons<T>, T> LObjCharIntConsAssert.The<A, T> attestObjCharIntCons(LTieCharConsumer.LObjCharIntCons<T> func) {
		return new LObjCharIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LIntObjCharCons<T>, T> LIntObjCharConsAssert.The<A, T> attestIntObjCharCons(LTieCharConsumer.LIntObjCharCons<T> func) {
		return new LIntObjCharConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LIntCharObjCons<T>, T> LIntCharObjConsAssert.The<A, T> attestIntCharObjCons(LTieCharConsumer.LIntCharObjCons<T> func) {
		return new LIntCharObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LCharObjIntCons<T>, T> LCharObjIntConsAssert.The<A, T> attestCharObjIntCons(LTieCharConsumer.LCharObjIntCons<T> func) {
		return new LCharObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LCharIntObjCons<T>, T> LCharIntObjConsAssert.The<A, T> attestCharIntObjCons(LTieCharConsumer.LCharIntObjCons<T> func) {
		return new LCharIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer<T1, T2>, T1, T2> LTieConsumerAssert.The<A, T1, T2> attestTieCons(LTieConsumer<T1, T2> func) {
		return new LTieConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer.LInt1BiObj2Cons<T1, T2>, T1, T2> LInt1BiObj2ConsAssert.The<A, T1, T2> attestInt1BiObj2Cons(LTieConsumer.LInt1BiObj2Cons<T1, T2> func) {
		return new LInt1BiObj2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer.LInt1Obj2Obj0Cons<T2, T1>, T2, T1> LInt1Obj2Obj0ConsAssert.The<A, T2, T1> attestInt1Obj2Obj0Cons(LTieConsumer.LInt1Obj2Obj0Cons<T2, T1> func) {
		return new LInt1Obj2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer.LObj2Int1Obj0Cons<T2, T1>, T2, T1> LObj2Int1Obj0ConsAssert.The<A, T2, T1> attestObj2Int1Obj0Cons(LTieConsumer.LObj2Int1Obj0Cons<T2, T1> func) {
		return new LObj2Int1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer<T>, T> LTieDblConsumerAssert.The<A, T> attestTieDblCons(LTieDblConsumer<T> func) {
		return new LTieDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LObjDblIntCons<T>, T> LObjDblIntConsAssert.The<A, T> attestObjDblIntCons(LTieDblConsumer.LObjDblIntCons<T> func) {
		return new LObjDblIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LIntObjDblCons<T>, T> LIntObjDblConsAssert.The<A, T> attestIntObjDblCons(LTieDblConsumer.LIntObjDblCons<T> func) {
		return new LIntObjDblConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LIntDblObjCons<T>, T> LIntDblObjConsAssert.The<A, T> attestIntDblObjCons(LTieDblConsumer.LIntDblObjCons<T> func) {
		return new LIntDblObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LDblObjIntCons<T>, T> LDblObjIntConsAssert.The<A, T> attestDblObjIntCons(LTieDblConsumer.LDblObjIntCons<T> func) {
		return new LDblObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LDblIntObjCons<T>, T> LDblIntObjConsAssert.The<A, T> attestDblIntObjCons(LTieDblConsumer.LDblIntObjCons<T> func) {
		return new LDblIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer<T>, T> LTieFltConsumerAssert.The<A, T> attestTieFltCons(LTieFltConsumer<T> func) {
		return new LTieFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LObjFltIntCons<T>, T> LObjFltIntConsAssert.The<A, T> attestObjFltIntCons(LTieFltConsumer.LObjFltIntCons<T> func) {
		return new LObjFltIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LIntObjFltCons<T>, T> LIntObjFltConsAssert.The<A, T> attestIntObjFltCons(LTieFltConsumer.LIntObjFltCons<T> func) {
		return new LIntObjFltConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LIntFltObjCons<T>, T> LIntFltObjConsAssert.The<A, T> attestIntFltObjCons(LTieFltConsumer.LIntFltObjCons<T> func) {
		return new LIntFltObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LFltObjIntCons<T>, T> LFltObjIntConsAssert.The<A, T> attestFltObjIntCons(LTieFltConsumer.LFltObjIntCons<T> func) {
		return new LFltObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LFltIntObjCons<T>, T> LFltIntObjConsAssert.The<A, T> attestFltIntObjCons(LTieFltConsumer.LFltIntObjCons<T> func) {
		return new LFltIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer<T>, T> LTieIntConsumerAssert.The<A, T> attestTieIntCons(LTieIntConsumer<T> func) {
		return new LTieIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LObj0Int2Int1Cons<T>, T> LObj0Int2Int1ConsAssert.The<A, T> attestObj0Int2Int1Cons(LTieIntConsumer.LObj0Int2Int1Cons<T> func) {
		return new LObj0Int2Int1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LInt1Obj0Int2Cons<T>, T> LInt1Obj0Int2ConsAssert.The<A, T> attestInt1Obj0Int2Cons(LTieIntConsumer.LInt1Obj0Int2Cons<T> func) {
		return new LInt1Obj0Int2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LInt1Int2Obj0Cons<T>, T> LInt1Int2Obj0ConsAssert.The<A, T> attestInt1Int2Obj0Cons(LTieIntConsumer.LInt1Int2Obj0Cons<T> func) {
		return new LInt1Int2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LInt2Obj0Int1Cons<T>, T> LInt2Obj0Int1ConsAssert.The<A, T> attestInt2Obj0Int1Cons(LTieIntConsumer.LInt2Obj0Int1Cons<T> func) {
		return new LInt2Obj0Int1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LBiInt1Obj0Cons<T>, T> LBiInt1Obj0ConsAssert.The<A, T> attestBiInt1Obj0Cons(LTieIntConsumer.LBiInt1Obj0Cons<T> func) {
		return new LBiInt1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer<T>, T> LTieLongConsumerAssert.The<A, T> attestTieLongCons(LTieLongConsumer<T> func) {
		return new LTieLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LObjLongIntCons<T>, T> LObjLongIntConsAssert.The<A, T> attestObjLongIntCons(LTieLongConsumer.LObjLongIntCons<T> func) {
		return new LObjLongIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LIntObjLongCons<T>, T> LIntObjLongConsAssert.The<A, T> attestIntObjLongCons(LTieLongConsumer.LIntObjLongCons<T> func) {
		return new LIntObjLongConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LIntLongObjCons<T>, T> LIntLongObjConsAssert.The<A, T> attestIntLongObjCons(LTieLongConsumer.LIntLongObjCons<T> func) {
		return new LIntLongObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LLongObjIntCons<T>, T> LLongObjIntConsAssert.The<A, T> attestLongObjIntCons(LTieLongConsumer.LLongObjIntCons<T> func) {
		return new LLongObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LLongIntObjCons<T>, T> LLongIntObjConsAssert.The<A, T> attestLongIntObjCons(LTieLongConsumer.LLongIntObjCons<T> func) {
		return new LLongIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer<T>, T> LTieSrtConsumerAssert.The<A, T> attestTieSrtCons(LTieSrtConsumer<T> func) {
		return new LTieSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LObjSrtIntCons<T>, T> LObjSrtIntConsAssert.The<A, T> attestObjSrtIntCons(LTieSrtConsumer.LObjSrtIntCons<T> func) {
		return new LObjSrtIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LIntObjSrtCons<T>, T> LIntObjSrtConsAssert.The<A, T> attestIntObjSrtCons(LTieSrtConsumer.LIntObjSrtCons<T> func) {
		return new LIntObjSrtConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LIntSrtObjCons<T>, T> LIntSrtObjConsAssert.The<A, T> attestIntSrtObjCons(LTieSrtConsumer.LIntSrtObjCons<T> func) {
		return new LIntSrtObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LSrtObjIntCons<T>, T> LSrtObjIntConsAssert.The<A, T> attestSrtObjIntCons(LTieSrtConsumer.LSrtObjIntCons<T> func) {
		return new LSrtObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LSrtIntObjCons<T>, T> LSrtIntObjConsAssert.The<A, T> attestSrtIntObjCons(LTieSrtConsumer.LSrtIntObjCons<T> func) {
		return new LSrtIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> attestTriBoolCons(LTriBoolConsumer func) {
		return new LTriBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriByteConsumer> LTriByteConsumerAssert.The<A> attestTriByteCons(LTriByteConsumer func) {
		return new LTriByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriCharConsumer> LTriCharConsumerAssert.The<A> attestTriCharCons(LTriCharConsumer func) {
		return new LTriCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriDblConsumer> LTriDblConsumerAssert.The<A> attestTriDblCons(LTriDblConsumer func) {
		return new LTriDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriFltConsumer> LTriFltConsumerAssert.The<A> attestTriFltCons(LTriFltConsumer func) {
		return new LTriFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriIntConsumer> LTriIntConsumerAssert.The<A> attestTriIntCons(LTriIntConsumer func) {
		return new LTriIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriLongConsumer> LTriLongConsumerAssert.The<A> attestTriLongCons(LTriLongConsumer func) {
		return new LTriLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriSrtConsumer> LTriSrtConsumerAssert.The<A> attestTriSrtCons(LTriSrtConsumer func) {
		return new LTriSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBinaryOperator<T>, T> LBinaryOperatorAssert.The<A, ? extends OS, T> attestBinaryOp(LBinaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteBinaryOperator> LByteBinaryOperatorAssert.The<A, ? extends AbstractByteAssert> attestByteBinaryOp(LByteBinaryOperator func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LByteBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharBinaryOperator> LCharBinaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> attestCharBinaryOp(LCharBinaryOperator func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LCharBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblBinaryOperator> LDblBinaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> attestDblBinaryOp(LDblBinaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LDblBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltBinaryOperator> LFltBinaryOperatorAssert.The<A, ? extends AbstractFloatAssert> attestFltBinaryOp(LFltBinaryOperator func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LFltBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntBinaryOperator> LIntBinaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> attestIntBinaryOp(LIntBinaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalBinaryOperator> LLogicalBinaryOperatorAssert.The<A, ? extends AbstractBooleanAssert> attestLogicalBinaryOp(LLogicalBinaryOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLogicalBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongBinaryOperator> LLongBinaryOperatorAssert.The<A, ? extends AbstractLongAssert> attestLongBinaryOp(LLongBinaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LLongBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtBinaryOperator> LSrtBinaryOperatorAssert.The<A, ? extends AbstractShortAssert> attestSrtBinaryOp(LSrtBinaryOperator func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LSrtBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteTernaryOperator> LByteTernaryOperatorAssert.The<A, ? extends AbstractByteAssert> attestByteTernaryOp(LByteTernaryOperator func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LByteTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharTernaryOperator> LCharTernaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> attestCharTernaryOp(LCharTernaryOperator func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LCharTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblTernaryOperator> LDblTernaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> attestDblTernaryOp(LDblTernaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LDblTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltTernaryOperator> LFltTernaryOperatorAssert.The<A, ? extends AbstractFloatAssert> attestFltTernaryOp(LFltTernaryOperator func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LFltTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntTernaryOperator> LIntTernaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> attestIntTernaryOp(LIntTernaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalTernaryOperator> LLogicalTernaryOperatorAssert.The<A, ? extends AbstractBooleanAssert> attestLogicalTernaryOp(LLogicalTernaryOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLogicalTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongTernaryOperator> LLongTernaryOperatorAssert.The<A, ? extends AbstractLongAssert> attestLongTernaryOp(LLongTernaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LLongTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtTernaryOperator> LSrtTernaryOperatorAssert.The<A, ? extends AbstractShortAssert> attestSrtTernaryOp(LSrtTernaryOperator func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LSrtTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTernaryOperator<T>, T> LTernaryOperatorAssert.The<A, ? extends OS, T> attestTernaryOp(LTernaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteUnaryOperator> LByteUnaryOperatorAssert.The<A, ? extends AbstractByteAssert> attestByteUnaryOp(LByteUnaryOperator func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LByteUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharUnaryOperator> LCharUnaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> attestCharUnaryOp(LCharUnaryOperator func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LCharUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblUnaryOperator> LDblUnaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> attestDblUnaryOp(LDblUnaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LDblUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltUnaryOperator> LFltUnaryOperatorAssert.The<A, ? extends AbstractFloatAssert> attestFltUnaryOp(LFltUnaryOperator func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LFltUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntUnaryOperator> LIntUnaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> attestIntUnaryOp(LIntUnaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalOperator> LLogicalOperatorAssert.The<A, ? extends AbstractBooleanAssert> attestLogicalOp(LLogicalOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLogicalOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongUnaryOperator> LLongUnaryOperatorAssert.The<A, ? extends AbstractLongAssert> attestLongUnaryOp(LLongUnaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LLongUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtUnaryOperator> LSrtUnaryOperatorAssert.The<A, ? extends AbstractShortAssert> attestSrtUnaryOp(LSrtUnaryOperator func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LSrtUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LUnaryOperator<T>, T> LUnaryOperatorAssert.The<A, ? extends OS, T> attestUnaryOp(LUnaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunction<T1, T2, R>, T1, T2, R> LBiFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiFunc(LBiFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunction.LObj1Obj0Func<T2, T1, R>, T2, T1, R> LObj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Func(LBiFunction.LObj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFunction<T, R>, T, R> LFunctionAssert.The<A, ? extends OS, T, R> attestFunc(LFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LQuadFunction<T1, T2, T3, T4, R>, T1, T2, T3, T4, R> LQuadFunctionAssert.The<A, ? extends OS, T1, T2, T3, T4, R> attestQuadFunc(LQuadFunction<T1, T2, T3, T4, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LQuadFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LQuintFunction<T1, T2, T3, T4, T5, R>, T1, T2, T3, T4, T5, R> LQuintFunctionAssert.The<A, ? extends OS, T1, T2, T3, T4, T5, R> attestQuintFunc(LQuintFunction<T1, T2, T3, T4, T5, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LQuintFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction<T1, T2, T3, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, ? extends OS, T1, T2, T3, R> attestTriFunc(LTriFunction<T1, T2, T3, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LTriFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R>, T1, T3, T2, R> LObj0Obj2Obj1FuncAssert.The<A, ? extends OS, T1, T3, T2, R> attestObj0Obj2Obj1Func(LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Obj2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LObj1BiObj2Func<T2, T1, T3, R>, T2, T1, T3, R> LObj1BiObj2FuncAssert.The<A, ? extends OS, T2, T1, T3, R> attestObj1BiObj2Func(LTriFunction.LObj1BiObj2Func<T2, T1, T3, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1BiObj2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R>, T2, T3, T1, R> LObj1Obj2Obj0FuncAssert.The<A, ? extends OS, T2, T3, T1, R> attestObj1Obj2Obj0Func(LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R>, T3, T1, T2, R> LObj2Obj0Obj1FuncAssert.The<A, ? extends OS, T3, T1, T2, R> attestObj2Obj0Obj1Func(LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R>, T3, T2, T1, R> LBiObj1Obj0FuncAssert.The<A, ? extends OS, T3, T2, T1, R> attestBiObj1Obj0Func(LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToByteFunction> LBoolToByteFunctionAssert.The<A, ? extends AbstractByteAssert> attestBoolToByteFunc(LBoolToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LBoolToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToCharFunction> LBoolToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> attestBoolToCharFunc(LBoolToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LBoolToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToDblFunction> LBoolToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestBoolToDblFunc(LBoolToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LBoolToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToFltFunction> LBoolToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> attestBoolToFltFunc(LBoolToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LBoolToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToIntFunction> LBoolToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestBoolToIntFunc(LBoolToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LBoolToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToLongFunction> LBoolToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestBoolToLongFunc(LBoolToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LBoolToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToSrtFunction> LBoolToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> attestBoolToSrtFunc(LBoolToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LBoolToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToCharFunction> LByteToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> attestByteToCharFunc(LByteToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LByteToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToDblFunction> LByteToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestByteToDblFunc(LByteToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LByteToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToFltFunction> LByteToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> attestByteToFltFunc(LByteToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LByteToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToIntFunction> LByteToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestByteToIntFunc(LByteToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LByteToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToLongFunction> LByteToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestByteToLongFunc(LByteToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LByteToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToSrtFunction> LByteToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> attestByteToSrtFunc(LByteToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LByteToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToByteFunction> LCharToByteFunctionAssert.The<A, ? extends AbstractByteAssert> attestCharToByteFunc(LCharToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LCharToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToDblFunction> LCharToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestCharToDblFunc(LCharToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LCharToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToFltFunction> LCharToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> attestCharToFltFunc(LCharToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LCharToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToIntFunction> LCharToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestCharToIntFunc(LCharToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LCharToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToLongFunction> LCharToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestCharToLongFunc(LCharToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LCharToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToSrtFunction> LCharToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> attestCharToSrtFunc(LCharToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LCharToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToByteFunction> LDblToByteFunctionAssert.The<A, ? extends AbstractByteAssert> attestDblToByteFunc(LDblToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LDblToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToCharFunction> LDblToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> attestDblToCharFunc(LDblToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LDblToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToFltFunction> LDblToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> attestDblToFltFunc(LDblToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LDblToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToIntFunction> LDblToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestDblToIntFunc(LDblToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LDblToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToLongFunction> LDblToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestDblToLongFunc(LDblToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LDblToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToSrtFunction> LDblToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> attestDblToSrtFunc(LDblToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LDblToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToByteFunction> LFltToByteFunctionAssert.The<A, ? extends AbstractByteAssert> attestFltToByteFunc(LFltToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LFltToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToCharFunction> LFltToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> attestFltToCharFunc(LFltToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LFltToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToDblFunction> LFltToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestFltToDblFunc(LFltToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LFltToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToIntFunction> LFltToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestFltToIntFunc(LFltToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LFltToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToLongFunction> LFltToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestFltToLongFunc(LFltToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LFltToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToSrtFunction> LFltToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> attestFltToSrtFunc(LFltToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LFltToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToByteFunction> LIntToByteFunctionAssert.The<A, ? extends AbstractByteAssert> attestIntToByteFunc(LIntToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LIntToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToCharFunction> LIntToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> attestIntToCharFunc(LIntToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LIntToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToDblFunction> LIntToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestIntToDblFunc(LIntToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LIntToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToFltFunction> LIntToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> attestIntToFltFunc(LIntToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LIntToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToLongFunction> LIntToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestIntToLongFunc(LIntToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LIntToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToSrtFunction> LIntToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> attestIntToSrtFunc(LIntToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LIntToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToByteFunction> LLongToByteFunctionAssert.The<A, ? extends AbstractByteAssert> attestLongToByteFunc(LLongToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LLongToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToCharFunction> LLongToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> attestLongToCharFunc(LLongToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LLongToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToDblFunction> LLongToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestLongToDblFunc(LLongToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LLongToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToFltFunction> LLongToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> attestLongToFltFunc(LLongToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LLongToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToIntFunction> LLongToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestLongToIntFunc(LLongToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LLongToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToSrtFunction> LLongToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> attestLongToSrtFunc(LLongToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LLongToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToByteFunction> LSrtToByteFunctionAssert.The<A, ? extends AbstractByteAssert> attestSrtToByteFunc(LSrtToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LSrtToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToCharFunction> LSrtToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> attestSrtToCharFunc(LSrtToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LSrtToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToDblFunction> LSrtToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestSrtToDblFunc(LSrtToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LSrtToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToFltFunction> LSrtToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> attestSrtToFltFunc(LSrtToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LSrtToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToIntFunction> LSrtToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestSrtToIntFunc(LSrtToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LSrtToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToLongFunction> LSrtToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestSrtToLongFunc(LSrtToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LSrtToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBoolFunction<R>, R> LBiBoolFunctionAssert.The<A, ? extends OS, R> attestBiBoolFunc(LBiBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBoolFunction.LBool1Bool0Func<R>, R> LBool1Bool0FuncAssert.The<A, ? extends OS, R> attestBool1Bool0Func(LBiBoolFunction.LBool1Bool0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBool1Bool0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiByteFunction<R>, R> LBiByteFunctionAssert.The<A, ? extends OS, R> attestBiByteFunc(LBiByteFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiByteFunction.LByte1Byte0Func<R>, R> LByte1Byte0FuncAssert.The<A, ? extends OS, R> attestByte1Byte0Func(LBiByteFunction.LByte1Byte0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LByte1Byte0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunction<R>, R> LBiCharFunctionAssert.The<A, ? extends OS, R> attestBiCharFunc(LBiCharFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunction.LChar1Char0Func<R>, R> LChar1Char0FuncAssert.The<A, ? extends OS, R> attestChar1Char0Func(LBiCharFunction.LChar1Char0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LChar1Char0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblFunction<R>, R> LBiDblFunctionAssert.The<A, ? extends OS, R> attestBiDblFunc(LBiDblFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblFunction.LDbl1Dbl0Func<R>, R> LDbl1Dbl0FuncAssert.The<A, ? extends OS, R> attestDbl1Dbl0Func(LBiDblFunction.LDbl1Dbl0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LDbl1Dbl0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltFunction<R>, R> LBiFltFunctionAssert.The<A, ? extends OS, R> attestBiFltFunc(LBiFltFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltFunction.LFlt1Flt0Func<R>, R> LFlt1Flt0FuncAssert.The<A, ? extends OS, R> attestFlt1Flt0Func(LBiFltFunction.LFlt1Flt0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LFlt1Flt0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunction<R>, R> LBiIntFunctionAssert.The<A, ? extends OS, R> attestBiIntFunc(LBiIntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunction.LInt1Int0Func<R>, R> LInt1Int0FuncAssert.The<A, ? extends OS, R> attestInt1Int0Func(LBiIntFunction.LInt1Int0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LInt1Int0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunction<R>, R> LBiLongFunctionAssert.The<A, ? extends OS, R> attestBiLongFunc(LBiLongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunction.LLong1Long0Func<R>, R> LLong1Long0FuncAssert.The<A, ? extends OS, R> attestLong1Long0Func(LBiLongFunction.LLong1Long0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LLong1Long0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction<T1, T2, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObjBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Bool2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestObj0Bool2Obj1Func(LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Bool2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Bool2FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Bool2Func(LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0Bool2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Bool2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Bool2Obj0Func(LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Bool2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LBool2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestBool2Obj0Obj1Func(LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBool2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LBool2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestBool2Obj1Obj0Func(LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBool2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction<T1, T2, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiObjByteFunc(LBiObjByteFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObjByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Byte2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestObj0Byte2Obj1Func(LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Byte2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Byte2FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Byte2Func(LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0Byte2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Byte2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Byte2Obj0Func(LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Byte2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LByte2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestByte2Obj0Obj1Func(LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LByte2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LByte2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestByte2Obj1Obj0Func(LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LByte2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction<T1, T2, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObjCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Char2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestObj0Char2Obj1Func(LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Char2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Char2FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Char2Func(LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0Char2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Char2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Char2Obj0Func(LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Char2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LChar2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestChar2Obj0Obj1Func(LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LChar2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LChar2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestChar2Obj1Obj0Func(LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LChar2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction<T1, T2, R>, T1, T2, R> LBiObjDblFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiObjDblFunc(LBiObjDblFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObjDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Dbl2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestObj0Dbl2Obj1Func(LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Dbl2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Dbl2FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Dbl2Func(LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0Dbl2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Dbl2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Dbl2Obj0Func(LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Dbl2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LDbl2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestDbl2Obj0Obj1Func(LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LDbl2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LDbl2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestDbl2Obj1Obj0Func(LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LDbl2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction<T1, T2, R>, T1, T2, R> LBiObjFltFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObjFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Flt2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestObj0Flt2Obj1Func(LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Flt2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Flt2FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Flt2Func(LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0Flt2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Flt2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Flt2Obj0Func(LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Flt2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LFlt2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestFlt2Obj0Obj1Func(LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LFlt2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LFlt2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestFlt2Obj1Obj0Func(LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LFlt2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction<T1, T2, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiObjIntFunc(LBiObjIntFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObjIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Int2FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Int2Func(LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0Int2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LInt2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestInt2Obj0Obj1Func(LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LInt2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LInt2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestInt2Obj1Obj0Func(LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LInt2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction<T1, T2, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObjLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Long2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestObj0Long2Obj1Func(LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Long2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Long2FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Long2Func(LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0Long2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Long2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Long2Obj0Func(LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Long2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LLong2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestLong2Obj0Obj1Func(LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LLong2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LLong2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestLong2Obj1Obj0Func(LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LLong2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction<T1, T2, R>, T1, T2, R> LBiObjSrtFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiObjSrtFunc(LBiObjSrtFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiObjSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Srt2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestObj0Srt2Obj1Func(LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Srt2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Srt2FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Obj0Srt2Func(LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Obj0Srt2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Srt2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj1Srt2Obj0Func(LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj1Srt2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LSrt2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> attestSrt2Obj0Obj1Func(LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LSrt2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LSrt2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestSrt2Obj1Obj0Func(LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LSrt2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtFunction<R>, R> LBiSrtFunctionAssert.The<A, ? extends OS, R> attestBiSrtFunc(LBiSrtFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtFunction.LSrt1Srt0Func<R>, R> LSrt1Srt0FuncAssert.The<A, ? extends OS, R> attestSrt1Srt0Func(LBiSrtFunction.LSrt1Srt0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LSrt1Srt0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolFunction<R>, R> LBoolFunctionAssert.The<A, ? extends OS, R> attestBoolFunc(LBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteFunction<R>, R> LByteFunctionAssert.The<A, ? extends OS, R> attestByteFunc(LByteFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharFunction<R>, R> LCharFunctionAssert.The<A, ? extends OS, R> attestCharFunc(LCharFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblFunction<R>, R> LDblFunctionAssert.The<A, ? extends OS, R> attestDblFunc(LDblFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltFunction<R>, R> LFltFunctionAssert.The<A, ? extends OS, R> attestFltFunc(LFltFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntFunction<R>, R> LIntFunctionAssert.The<A, ? extends OS, R> attestIntFunc(LIntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongFunction<R>, R> LLongFunctionAssert.The<A, ? extends OS, R> attestLongFunc(LLongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction<T, R>, T, R> LObjBiIntFunctionAssert.The<A, ? extends OS, T, R> attestObjBiIntFunc(LObjBiIntFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjBiIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LObj0Int2Int1Func<T, R>, T, R> LObj0Int2Int1FuncAssert.The<A, ? extends OS, T, R> attestObj0Int2Int1Func(LObjBiIntFunction.LObj0Int2Int1Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj0Int2Int1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LInt1Obj0Int2Func<T, R>, T, R> LInt1Obj0Int2FuncAssert.The<A, ? extends OS, T, R> attestInt1Obj0Int2Func(LObjBiIntFunction.LInt1Obj0Int2Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LInt1Obj0Int2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LInt1Int2Obj0Func<T, R>, T, R> LInt1Int2Obj0FuncAssert.The<A, ? extends OS, T, R> attestInt1Int2Obj0Func(LObjBiIntFunction.LInt1Int2Obj0Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LInt1Int2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LInt2Obj0Int1Func<T, R>, T, R> LInt2Obj0Int1FuncAssert.The<A, ? extends OS, T, R> attestInt2Obj0Int1Func(LObjBiIntFunction.LInt2Obj0Int1Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LInt2Obj0Int1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LBiInt1Obj0Func<T, R>, T, R> LBiInt1Obj0FuncAssert.The<A, ? extends OS, T, R> attestBiInt1Obj0Func(LObjBiIntFunction.LBiInt1Obj0Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBiInt1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolFunction<T, R>, T, R> LObjBoolFunctionAssert.The<A, ? extends OS, T, R> attestObjBoolFunc(LObjBoolFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolFunction.LBoolObjFunc<T, R>, T, R> LBoolObjFuncAssert.The<A, ? extends OS, T, R> attestBoolObjFunc(LObjBoolFunction.LBoolObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBoolObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunction<T, R>, T, R> LObjByteFunctionAssert.The<A, ? extends OS, T, R> attestObjByteFunc(LObjByteFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunction.LByteObjFunc<T, R>, T, R> LByteObjFuncAssert.The<A, ? extends OS, T, R> attestByteObjFunc(LObjByteFunction.LByteObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LByteObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunction<T, R>, T, R> LObjCharFunctionAssert.The<A, ? extends OS, T, R> attestObjCharFunc(LObjCharFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunction.LCharObjFunc<T, R>, T, R> LCharObjFuncAssert.The<A, ? extends OS, T, R> attestCharObjFunc(LObjCharFunction.LCharObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LCharObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblFunction<T, R>, T, R> LObjDblFunctionAssert.The<A, ? extends OS, T, R> attestObjDblFunc(LObjDblFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblFunction.LDblObjFunc<T, R>, T, R> LDblObjFuncAssert.The<A, ? extends OS, T, R> attestDblObjFunc(LObjDblFunction.LDblObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LDblObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltFunction<T, R>, T, R> LObjFltFunctionAssert.The<A, ? extends OS, T, R> attestObjFltFunc(LObjFltFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltFunction.LFltObjFunc<T, R>, T, R> LFltObjFuncAssert.The<A, ? extends OS, T, R> attestFltObjFunc(LObjFltFunction.LFltObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LFltObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction<T, R>, T, R> LObjIntBoolFunctionAssert.The<A, ? extends OS, T, R> attestObjIntBoolFunc(LObjIntBoolFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjIntBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LObjBoolIntFunc<T, R>, T, R> LObjBoolIntFuncAssert.The<A, ? extends OS, T, R> attestObjBoolIntFunc(LObjIntBoolFunction.LObjBoolIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjBoolIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LIntObjBoolFunc<T, R>, T, R> LIntObjBoolFuncAssert.The<A, ? extends OS, T, R> attestIntObjBoolFunc(LObjIntBoolFunction.LIntObjBoolFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntObjBoolFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LIntBoolObjFunc<T, R>, T, R> LIntBoolObjFuncAssert.The<A, ? extends OS, T, R> attestIntBoolObjFunc(LObjIntBoolFunction.LIntBoolObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntBoolObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LBoolObjIntFunc<T, R>, T, R> LBoolObjIntFuncAssert.The<A, ? extends OS, T, R> attestBoolObjIntFunc(LObjIntBoolFunction.LBoolObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBoolObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LBoolIntObjFunc<T, R>, T, R> LBoolIntObjFuncAssert.The<A, ? extends OS, T, R> attestBoolIntObjFunc(LObjIntBoolFunction.LBoolIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LBoolIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction<T, R>, T, R> LObjIntByteFunctionAssert.The<A, ? extends OS, T, R> attestObjIntByteFunc(LObjIntByteFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjIntByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LObjByteIntFunc<T, R>, T, R> LObjByteIntFuncAssert.The<A, ? extends OS, T, R> attestObjByteIntFunc(LObjIntByteFunction.LObjByteIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjByteIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LIntObjByteFunc<T, R>, T, R> LIntObjByteFuncAssert.The<A, ? extends OS, T, R> attestIntObjByteFunc(LObjIntByteFunction.LIntObjByteFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntObjByteFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LIntByteObjFunc<T, R>, T, R> LIntByteObjFuncAssert.The<A, ? extends OS, T, R> attestIntByteObjFunc(LObjIntByteFunction.LIntByteObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntByteObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LByteObjIntFunc<T, R>, T, R> LByteObjIntFuncAssert.The<A, ? extends OS, T, R> attestByteObjIntFunc(LObjIntByteFunction.LByteObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LByteObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LByteIntObjFunc<T, R>, T, R> LByteIntObjFuncAssert.The<A, ? extends OS, T, R> attestByteIntObjFunc(LObjIntByteFunction.LByteIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LByteIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction<T, R>, T, R> LObjIntCharFunctionAssert.The<A, ? extends OS, T, R> attestObjIntCharFunc(LObjIntCharFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjIntCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LObjCharIntFunc<T, R>, T, R> LObjCharIntFuncAssert.The<A, ? extends OS, T, R> attestObjCharIntFunc(LObjIntCharFunction.LObjCharIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjCharIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LIntObjCharFunc<T, R>, T, R> LIntObjCharFuncAssert.The<A, ? extends OS, T, R> attestIntObjCharFunc(LObjIntCharFunction.LIntObjCharFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntObjCharFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LIntCharObjFunc<T, R>, T, R> LIntCharObjFuncAssert.The<A, ? extends OS, T, R> attestIntCharObjFunc(LObjIntCharFunction.LIntCharObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntCharObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LCharObjIntFunc<T, R>, T, R> LCharObjIntFuncAssert.The<A, ? extends OS, T, R> attestCharObjIntFunc(LObjIntCharFunction.LCharObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LCharObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LCharIntObjFunc<T, R>, T, R> LCharIntObjFuncAssert.The<A, ? extends OS, T, R> attestCharIntObjFunc(LObjIntCharFunction.LCharIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LCharIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction<T, R>, T, R> LObjIntDblFunctionAssert.The<A, ? extends OS, T, R> attestObjIntDblFunc(LObjIntDblFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjIntDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LObjDblIntFunc<T, R>, T, R> LObjDblIntFuncAssert.The<A, ? extends OS, T, R> attestObjDblIntFunc(LObjIntDblFunction.LObjDblIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjDblIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LIntObjDblFunc<T, R>, T, R> LIntObjDblFuncAssert.The<A, ? extends OS, T, R> attestIntObjDblFunc(LObjIntDblFunction.LIntObjDblFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntObjDblFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LIntDblObjFunc<T, R>, T, R> LIntDblObjFuncAssert.The<A, ? extends OS, T, R> attestIntDblObjFunc(LObjIntDblFunction.LIntDblObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntDblObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LDblObjIntFunc<T, R>, T, R> LDblObjIntFuncAssert.The<A, ? extends OS, T, R> attestDblObjIntFunc(LObjIntDblFunction.LDblObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LDblObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LDblIntObjFunc<T, R>, T, R> LDblIntObjFuncAssert.The<A, ? extends OS, T, R> attestDblIntObjFunc(LObjIntDblFunction.LDblIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LDblIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction<T, R>, T, R> LObjIntFltFunctionAssert.The<A, ? extends OS, T, R> attestObjIntFltFunc(LObjIntFltFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjIntFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LObjFltIntFunc<T, R>, T, R> LObjFltIntFuncAssert.The<A, ? extends OS, T, R> attestObjFltIntFunc(LObjIntFltFunction.LObjFltIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjFltIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LIntObjFltFunc<T, R>, T, R> LIntObjFltFuncAssert.The<A, ? extends OS, T, R> attestIntObjFltFunc(LObjIntFltFunction.LIntObjFltFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntObjFltFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LIntFltObjFunc<T, R>, T, R> LIntFltObjFuncAssert.The<A, ? extends OS, T, R> attestIntFltObjFunc(LObjIntFltFunction.LIntFltObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntFltObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LFltObjIntFunc<T, R>, T, R> LFltObjIntFuncAssert.The<A, ? extends OS, T, R> attestFltObjIntFunc(LObjIntFltFunction.LFltObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LFltObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LFltIntObjFunc<T, R>, T, R> LFltIntObjFuncAssert.The<A, ? extends OS, T, R> attestFltIntObjFunc(LObjIntFltFunction.LFltIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LFltIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction<T, R>, T, R> LObjIntLongFunctionAssert.The<A, ? extends OS, T, R> attestObjIntLongFunc(LObjIntLongFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjIntLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LObjLongIntFunc<T, R>, T, R> LObjLongIntFuncAssert.The<A, ? extends OS, T, R> attestObjLongIntFunc(LObjIntLongFunction.LObjLongIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjLongIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LIntObjLongFunc<T, R>, T, R> LIntObjLongFuncAssert.The<A, ? extends OS, T, R> attestIntObjLongFunc(LObjIntLongFunction.LIntObjLongFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntObjLongFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LIntLongObjFunc<T, R>, T, R> LIntLongObjFuncAssert.The<A, ? extends OS, T, R> attestIntLongObjFunc(LObjIntLongFunction.LIntLongObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntLongObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LLongObjIntFunc<T, R>, T, R> LLongObjIntFuncAssert.The<A, ? extends OS, T, R> attestLongObjIntFunc(LObjIntLongFunction.LLongObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LLongObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LLongIntObjFunc<T, R>, T, R> LLongIntObjFuncAssert.The<A, ? extends OS, T, R> attestLongIntObjFunc(LObjIntLongFunction.LLongIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LLongIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction<T1, T2, R>, T1, T2, R> LObjIntObjFunctionAssert.The<A, ? extends OS, T1, T2, R> attestObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjIntObjFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R>, T1, T2, R> LInt1BiObj2FuncAssert.The<A, ? extends OS, T1, T2, R> attestInt1BiObj2Func(LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LInt1BiObj2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R>, T2, T1, R> LInt1Obj2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestInt1Obj2Obj0Func(LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LInt1Obj2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R>, T2, T1, R> LObj2Int1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> attestObj2Int1Obj0Func(LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObj2Int1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction<T, R>, T, R> LObjIntSrtFunctionAssert.The<A, ? extends OS, T, R> attestObjIntSrtFunc(LObjIntSrtFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjIntSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LObjSrtIntFunc<T, R>, T, R> LObjSrtIntFuncAssert.The<A, ? extends OS, T, R> attestObjSrtIntFunc(LObjIntSrtFunction.LObjSrtIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjSrtIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LIntObjSrtFunc<T, R>, T, R> LIntObjSrtFuncAssert.The<A, ? extends OS, T, R> attestIntObjSrtFunc(LObjIntSrtFunction.LIntObjSrtFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntObjSrtFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LIntSrtObjFunc<T, R>, T, R> LIntSrtObjFuncAssert.The<A, ? extends OS, T, R> attestIntSrtObjFunc(LObjIntSrtFunction.LIntSrtObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntSrtObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LSrtObjIntFunc<T, R>, T, R> LSrtObjIntFuncAssert.The<A, ? extends OS, T, R> attestSrtObjIntFunc(LObjIntSrtFunction.LSrtObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LSrtObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LSrtIntObjFunc<T, R>, T, R> LSrtIntObjFuncAssert.The<A, ? extends OS, T, R> attestSrtIntObjFunc(LObjIntSrtFunction.LSrtIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LSrtIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunction<T, R>, T, R> LObjLongFunctionAssert.The<A, ? extends OS, T, R> attestObjLongFunc(LObjLongFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunction.LLongObjFunc<T, R>, T, R> LLongObjFuncAssert.The<A, ? extends OS, T, R> attestLongObjFunc(LObjLongFunction.LLongObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LLongObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtFunction<T, R>, T, R> LObjSrtFunctionAssert.The<A, ? extends OS, T, R> attestObjSrtFunc(LObjSrtFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LObjSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtFunction.LSrtObjFunc<T, R>, T, R> LSrtObjFuncAssert.The<A, ? extends OS, T, R> attestSrtObjFunc(LObjSrtFunction.LSrtObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LSrtObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiFunction<T, R>, T, R> LOiFunctionAssert.The<A, ? extends OS, T, R> attestOiFunc(LOiFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LOiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiFunction.LIntObjFunc<T, R>, T, R> LIntObjFuncAssert.The<A, ? extends OS, T, R> attestIntObjFunc(LOiFunction.LIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtFunction<R>, R> LSrtFunctionAssert.The<A, ? extends OS, R> attestSrtFunc(LSrtFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriBoolFunction<R>, R> LTriBoolFunctionAssert.The<A, ? extends OS, R> attestTriBoolFunc(LTriBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LTriBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToByteFunction<T>, T> LOiToByteFunctionAssert.The<A, ? extends AbstractByteAssert, T> attestOiToByteFunc(LOiToByteFunction<T> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LOiToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToByteFunction.LIntObjToByteFunc<T>, T> LIntObjToByteFuncAssert.The<A, ? extends AbstractByteAssert, T> attestIntObjToByteFunc(LOiToByteFunction.LIntObjToByteFunc<T> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LIntObjToByteFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToCharFunction<T>, T> LOiToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert, T> attestOiToCharFunc(LOiToCharFunction<T> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LOiToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToCharFunction.LIntObjToCharFunc<T>, T> LIntObjToCharFuncAssert.The<A, ? extends AbstractCharacterAssert, T> attestIntObjToCharFunc(LOiToCharFunction.LIntObjToCharFunc<T> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LIntObjToCharFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToDblFunction<T>, T> LOiToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> attestOiToDblFunc(LOiToDblFunction<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LOiToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToDblFunction.LIntObjToDblFunc<T>, T> LIntObjToDblFuncAssert.The<A, ? extends AbstractDoubleAssert, T> attestIntObjToDblFunc(LOiToDblFunction.LIntObjToDblFunc<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LIntObjToDblFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToFltFunction<T>, T> LOiToFltFunctionAssert.The<A, ? extends AbstractFloatAssert, T> attestOiToFltFunc(LOiToFltFunction<T> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LOiToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToFltFunction.LIntObjToFltFunc<T>, T> LIntObjToFltFuncAssert.The<A, ? extends AbstractFloatAssert, T> attestIntObjToFltFunc(LOiToFltFunction.LIntObjToFltFunc<T> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LIntObjToFltFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToIntFunction<T>, T> LOiToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestOiToIntFunc(LOiToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LOiToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToIntFunction.LIntObjToIntFunc<T>, T> LIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntObjToIntFunc(LOiToIntFunction.LIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToLongFunction<T>, T> LOiToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> attestOiToLongFunc(LOiToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LOiToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToLongFunction.LIntObjToLongFunc<T>, T> LIntObjToLongFuncAssert.The<A, ? extends AbstractLongAssert, T> attestIntObjToLongFunc(LOiToLongFunction.LIntObjToLongFunc<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LIntObjToLongFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToSrtFunction<T>, T> LOiToSrtFunctionAssert.The<A, ? extends AbstractShortAssert, T> attestOiToSrtFunc(LOiToSrtFunction<T> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LOiToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToSrtFunction.LIntObjToSrtFunc<T>, T> LIntObjToSrtFuncAssert.The<A, ? extends AbstractShortAssert, T> attestIntObjToSrtFunc(LOiToSrtFunction.LIntObjToSrtFunc<T> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LIntObjToSrtFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction<T>, T> LTieBoolFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestTieBoolFunc(LTieBoolFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LObjBoolIntToIntFunc<T>, T> LObjBoolIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestObjBoolIntToIntFunc(LTieBoolFunction.LObjBoolIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObjBoolIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LIntObjBoolToIntFunc<T>, T> LIntObjBoolToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntObjBoolToIntFunc(LTieBoolFunction.LIntObjBoolToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntObjBoolToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LIntBoolObjToIntFunc<T>, T> LIntBoolObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntBoolObjToIntFunc(LTieBoolFunction.LIntBoolObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntBoolObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LBoolObjIntToIntFunc<T>, T> LBoolObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestBoolObjIntToIntFunc(LTieBoolFunction.LBoolObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LBoolObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LBoolIntObjToIntFunc<T>, T> LBoolIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestBoolIntObjToIntFunc(LTieBoolFunction.LBoolIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LBoolIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction<T>, T> LTieByteFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestTieByteFunc(LTieByteFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LObjByteIntToIntFunc<T>, T> LObjByteIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestObjByteIntToIntFunc(LTieByteFunction.LObjByteIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObjByteIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LIntObjByteToIntFunc<T>, T> LIntObjByteToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntObjByteToIntFunc(LTieByteFunction.LIntObjByteToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntObjByteToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LIntByteObjToIntFunc<T>, T> LIntByteObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntByteObjToIntFunc(LTieByteFunction.LIntByteObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntByteObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LByteObjIntToIntFunc<T>, T> LByteObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestByteObjIntToIntFunc(LTieByteFunction.LByteObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LByteObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LByteIntObjToIntFunc<T>, T> LByteIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestByteIntObjToIntFunc(LTieByteFunction.LByteIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LByteIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction<T>, T> LTieCharFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestTieCharFunc(LTieCharFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LObjCharIntToIntFunc<T>, T> LObjCharIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestObjCharIntToIntFunc(LTieCharFunction.LObjCharIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObjCharIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LIntObjCharToIntFunc<T>, T> LIntObjCharToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntObjCharToIntFunc(LTieCharFunction.LIntObjCharToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntObjCharToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LIntCharObjToIntFunc<T>, T> LIntCharObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntCharObjToIntFunc(LTieCharFunction.LIntCharObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntCharObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LCharObjIntToIntFunc<T>, T> LCharObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestCharObjIntToIntFunc(LTieCharFunction.LCharObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LCharObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LCharIntObjToIntFunc<T>, T> LCharIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestCharIntObjToIntFunc(LTieCharFunction.LCharIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LCharIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction<T>, T> LTieDblFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestTieDblFunc(LTieDblFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LObjDblIntToIntFunc<T>, T> LObjDblIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestObjDblIntToIntFunc(LTieDblFunction.LObjDblIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObjDblIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LIntObjDblToIntFunc<T>, T> LIntObjDblToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntObjDblToIntFunc(LTieDblFunction.LIntObjDblToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntObjDblToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LIntDblObjToIntFunc<T>, T> LIntDblObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntDblObjToIntFunc(LTieDblFunction.LIntDblObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntDblObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LDblObjIntToIntFunc<T>, T> LDblObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestDblObjIntToIntFunc(LTieDblFunction.LDblObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LDblObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LDblIntObjToIntFunc<T>, T> LDblIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestDblIntObjToIntFunc(LTieDblFunction.LDblIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LDblIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction<T>, T> LTieFltFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestTieFltFunc(LTieFltFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LObjFltIntToIntFunc<T>, T> LObjFltIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestObjFltIntToIntFunc(LTieFltFunction.LObjFltIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObjFltIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LIntObjFltToIntFunc<T>, T> LIntObjFltToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntObjFltToIntFunc(LTieFltFunction.LIntObjFltToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntObjFltToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LIntFltObjToIntFunc<T>, T> LIntFltObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntFltObjToIntFunc(LTieFltFunction.LIntFltObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntFltObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LFltObjIntToIntFunc<T>, T> LFltObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestFltObjIntToIntFunc(LTieFltFunction.LFltObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LFltObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LFltIntObjToIntFunc<T>, T> LFltIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestFltIntObjToIntFunc(LTieFltFunction.LFltIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LFltIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction<T1, T2>, T1, T2> LTieFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> attestTieFunc(LTieFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2>, T1, T2> LObj0Obj2Int1ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> attestObj0Obj2Int1ToIntFunc(LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObj0Obj2Int1ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LInt1BiObj2ToIntFunc<T1, T2>, T1, T2> LInt1BiObj2ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> attestInt1BiObj2ToIntFunc(LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LInt1BiObj2ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1>, T2, T1> LInt1Obj2Obj0ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1> attestInt1Obj2Obj0ToIntFunc(LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LInt1Obj2Obj0ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1>, T2, T1> LObj2Obj0Int1ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1> attestObj2Obj0Int1ToIntFunc(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObj2Obj0Int1ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1>, T2, T1> LObj2Int1Obj0ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1> attestObj2Int1Obj0ToIntFunc(LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObj2Int1Obj0ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction<T>, T> LTieIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestTieIntFunc(LTieIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LObj0Int2Int1ToIntFunc<T>, T> LObj0Int2Int1ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestObj0Int2Int1ToIntFunc(LTieIntFunction.LObj0Int2Int1ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObj0Int2Int1ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LInt1Obj0Int2ToIntFunc<T>, T> LInt1Obj0Int2ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestInt1Obj0Int2ToIntFunc(LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LInt1Obj0Int2ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LInt1Int2Obj0ToIntFunc<T>, T> LInt1Int2Obj0ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestInt1Int2Obj0ToIntFunc(LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LInt1Int2Obj0ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LInt2Obj0Int1ToIntFunc<T>, T> LInt2Obj0Int1ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestInt2Obj0Int1ToIntFunc(LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LInt2Obj0Int1ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LBiInt1Obj0ToIntFunc<T>, T> LBiInt1Obj0ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestBiInt1Obj0ToIntFunc(LTieIntFunction.LBiInt1Obj0ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LBiInt1Obj0ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction<T>, T> LTieLongFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestTieLongFunc(LTieLongFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LObjLongIntToIntFunc<T>, T> LObjLongIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestObjLongIntToIntFunc(LTieLongFunction.LObjLongIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObjLongIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LIntObjLongToIntFunc<T>, T> LIntObjLongToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntObjLongToIntFunc(LTieLongFunction.LIntObjLongToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntObjLongToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LIntLongObjToIntFunc<T>, T> LIntLongObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntLongObjToIntFunc(LTieLongFunction.LIntLongObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntLongObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LLongObjIntToIntFunc<T>, T> LLongObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestLongObjIntToIntFunc(LTieLongFunction.LLongObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LLongObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LLongIntObjToIntFunc<T>, T> LLongIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestLongIntObjToIntFunc(LTieLongFunction.LLongIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LLongIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction<T>, T> LTieSrtFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestTieSrtFunc(LTieSrtFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LTieSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LObjSrtIntToIntFunc<T>, T> LObjSrtIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestObjSrtIntToIntFunc(LTieSrtFunction.LObjSrtIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LObjSrtIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LIntObjSrtToIntFunc<T>, T> LIntObjSrtToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntObjSrtToIntFunc(LTieSrtFunction.LIntObjSrtToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntObjSrtToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LIntSrtObjToIntFunc<T>, T> LIntSrtObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestIntSrtObjToIntFunc(LTieSrtFunction.LIntSrtObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntSrtObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LSrtObjIntToIntFunc<T>, T> LSrtObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestSrtObjIntToIntFunc(LTieSrtFunction.LSrtObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LSrtObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LSrtIntObjToIntFunc<T>, T> LSrtIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> attestSrtIntObjToIntFunc(LTieSrtFunction.LSrtIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LSrtIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunction<T1, T2>, T1, T2> LToByteBiFunctionAssert.The<A, ? extends AbstractByteAssert, T1, T2> attestToByteBiFunc(LToByteBiFunction<T1, T2> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LToByteBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunction.LToByteObj1Obj0Func<T2, T1>, T2, T1> LToByteObj1Obj0FuncAssert.The<A, ? extends AbstractByteAssert, T2, T1> attestToByteObj1Obj0Func(LToByteBiFunction.LToByteObj1Obj0Func<T2, T1> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LToByteObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteFunction<T>, T> LToByteFunctionAssert.The<A, ? extends AbstractByteAssert, T> attestToByteFunc(LToByteFunction<T> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunction<T1, T2>, T1, T2> LToCharBiFunctionAssert.The<A, ? extends AbstractCharacterAssert, T1, T2> attestToCharBiFunc(LToCharBiFunction<T1, T2> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LToCharBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunction.LToCharObj1Obj0Func<T2, T1>, T2, T1> LToCharObj1Obj0FuncAssert.The<A, ? extends AbstractCharacterAssert, T2, T1> attestToCharObj1Obj0Func(LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LToCharObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharFunction<T>, T> LToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert, T> attestToCharFunc(LToCharFunction<T> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDblBiFunction<T1, T2>, T1, T2> LToDblBiFunctionAssert.The<A, ? extends AbstractDoubleAssert, T1, T2> attestToDblBiFunc(LToDblBiFunction<T1, T2> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LToDblBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDblBiFunction.LToDblObj1Obj0Func<T2, T1>, T2, T1> LToDblObj1Obj0FuncAssert.The<A, ? extends AbstractDoubleAssert, T2, T1> attestToDblObj1Obj0Func(LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LToDblObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDblFunction<T>, T> LToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> attestToDblFunc(LToDblFunction<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFltBiFunction<T1, T2>, T1, T2> LToFltBiFunctionAssert.The<A, ? extends AbstractFloatAssert, T1, T2> attestToFltBiFunc(LToFltBiFunction<T1, T2> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LToFltBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFltBiFunction.LToFltObj1Obj0Func<T2, T1>, T2, T1> LToFltObj1Obj0FuncAssert.The<A, ? extends AbstractFloatAssert, T2, T1> attestToFltObj1Obj0Func(LToFltBiFunction.LToFltObj1Obj0Func<T2, T1> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LToFltObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFltFunction<T>, T> LToFltFunctionAssert.The<A, ? extends AbstractFloatAssert, T> attestToFltFunc(LToFltFunction<T> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunction<T1, T2>, T1, T2> LToIntBiFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> attestToIntBiFunc(LToIntBiFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunction.LToIntObj1Obj0Func<T2, T1>, T2, T1> LToIntObj1Obj0FuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1> attestToIntObj1Obj0Func(LToIntBiFunction.LToIntObj1Obj0Func<T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntFunction<T>, T> LToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestToIntFunc(LToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction<T1, T2, T3>, T1, T2, T3> LToIntTriFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2, T3> attestToIntTriFunc(LToIntTriFunction<T1, T2, T3> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntTriFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2>, T1, T3, T2> LToIntObj0Obj2Obj1FuncAssert.The<A, ? extends AbstractIntegerAssert, T1, T3, T2> attestToIntObj0Obj2Obj1Func(LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntObj0Obj2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3>, T2, T1, T3> LToIntObj1BiObj2FuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1, T3> attestToIntObj1BiObj2Func(LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntObj1BiObj2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1>, T2, T3, T1> LToIntObj1Obj2Obj0FuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T3, T1> attestToIntObj1Obj2Obj0Func(LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntObj1Obj2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2>, T3, T1, T2> LToIntObj2Obj0Obj1FuncAssert.The<A, ? extends AbstractIntegerAssert, T3, T1, T2> attestToIntObj2Obj0Obj1Func(LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntObj2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1>, T3, T2, T1> LToIntBiObj1Obj0FuncAssert.The<A, ? extends AbstractIntegerAssert, T3, T2, T1> attestToIntBiObj1Obj0Func(LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LToIntBiObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunction<T1, T2>, T1, T2> LToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> attestToLongBiFunc(LToLongBiFunction<T1, T2> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LToLongBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunction.LToLongObj1Obj0Func<T2, T1>, T2, T1> LToLongObj1Obj0FuncAssert.The<A, ? extends AbstractLongAssert, T2, T1> attestToLongObj1Obj0Func(LToLongBiFunction.LToLongObj1Obj0Func<T2, T1> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LToLongObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongFunction<T>, T> LToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> attestToLongFunc(LToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToSrtBiFunction<T1, T2>, T1, T2> LToSrtBiFunctionAssert.The<A, ? extends AbstractShortAssert, T1, T2> attestToSrtBiFunc(LToSrtBiFunction<T1, T2> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LToSrtBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1>, T2, T1> LToSrtObj1Obj0FuncAssert.The<A, ? extends AbstractShortAssert, T2, T1> attestToSrtObj1Obj0Func(LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LToSrtObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToSrtFunction<T>, T> LToSrtFunctionAssert.The<A, ? extends AbstractShortAssert, T> attestToSrtFunc(LToSrtFunction<T> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicate> LBiBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBiBytePred(LBiBytePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicate.LByte1Byte0Pred> LByte1Byte0PredAssert.The<A, ? extends AbstractBooleanAssert> attestByte1Byte0Pred(LBiBytePredicate.LByte1Byte0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LByte1Byte0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicate> LBiCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBiCharPred(LBiCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicate.LChar1Char0Pred> LChar1Char0PredAssert.The<A, ? extends AbstractBooleanAssert> attestChar1Char0Pred(LBiCharPredicate.LChar1Char0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LChar1Char0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblPredicate> LBiDblPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBiDblPred(LBiDblPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblPredicate.LDbl1Dbl0Pred> LDbl1Dbl0PredAssert.The<A, ? extends AbstractBooleanAssert> attestDbl1Dbl0Pred(LBiDblPredicate.LDbl1Dbl0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LDbl1Dbl0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltPredicate> LBiFltPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBiFltPred(LBiFltPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltPredicate.LFlt1Flt0Pred> LFlt1Flt0PredAssert.The<A, ? extends AbstractBooleanAssert> attestFlt1Flt0Pred(LBiFltPredicate.LFlt1Flt0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LFlt1Flt0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicate> LBiIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBiIntPred(LBiIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicate.LInt1Int0Pred> LInt1Int0PredAssert.The<A, ? extends AbstractBooleanAssert> attestInt1Int0Pred(LBiIntPredicate.LInt1Int0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LInt1Int0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicate> LBiLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBiLongPred(LBiLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicate.LLong1Long0Pred> LLong1Long0PredAssert.The<A, ? extends AbstractBooleanAssert> attestLong1Long0Pred(LBiLongPredicate.LLong1Long0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLong1Long0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate<T1, T2>, T1, T2> LBiObjBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiObjBoolPred(LBiObjBoolPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObjBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2>, T1, T2> LObj0Bool2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestObj0Bool2Obj1Pred(LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Bool2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1>, T2, T1> LObj1Obj0Bool2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Bool2Pred(LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0Bool2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1>, T2, T1> LObj1Bool2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Bool2Obj0Pred(LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Bool2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2>, T1, T2> LBool2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBool2Obj0Obj1Pred(LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBool2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1>, T2, T1> LBool2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestBool2Obj1Obj0Pred(LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBool2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate<T1, T2>, T1, T2> LBiObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiObjBytePred(LBiObjBytePredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObjBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2>, T1, T2> LObj0Byte2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestObj0Byte2Obj1Pred(LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Byte2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1>, T2, T1> LObj1Obj0Byte2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Byte2Pred(LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0Byte2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1>, T2, T1> LObj1Byte2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Byte2Obj0Pred(LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Byte2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2>, T1, T2> LByte2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestByte2Obj0Obj1Pred(LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LByte2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1>, T2, T1> LByte2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestByte2Obj1Obj0Pred(LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LByte2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate<T1, T2>, T1, T2> LBiObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiObjCharPred(LBiObjCharPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObjCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2>, T1, T2> LObj0Char2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestObj0Char2Obj1Pred(LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Char2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1>, T2, T1> LObj1Obj0Char2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Char2Pred(LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0Char2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1>, T2, T1> LObj1Char2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Char2Obj0Pred(LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Char2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2>, T1, T2> LChar2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestChar2Obj0Obj1Pred(LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LChar2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1>, T2, T1> LChar2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestChar2Obj1Obj0Pred(LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LChar2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate<T1, T2>, T1, T2> LBiObjDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiObjDblPred(LBiObjDblPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObjDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2>, T1, T2> LObj0Dbl2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestObj0Dbl2Obj1Pred(LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Dbl2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1>, T2, T1> LObj1Obj0Dbl2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Dbl2Pred(LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0Dbl2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1>, T2, T1> LObj1Dbl2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Dbl2Obj0Pred(LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Dbl2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2>, T1, T2> LDbl2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestDbl2Obj0Obj1Pred(LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LDbl2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1>, T2, T1> LDbl2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestDbl2Obj1Obj0Pred(LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LDbl2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate<T1, T2>, T1, T2> LBiObjFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiObjFltPred(LBiObjFltPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObjFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2>, T1, T2> LObj0Flt2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestObj0Flt2Obj1Pred(LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Flt2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1>, T2, T1> LObj1Obj0Flt2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Flt2Pred(LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0Flt2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1>, T2, T1> LObj1Flt2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Flt2Obj0Pred(LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Flt2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2>, T1, T2> LFlt2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestFlt2Obj0Obj1Pred(LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LFlt2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1>, T2, T1> LFlt2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestFlt2Obj1Obj0Pred(LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LFlt2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate<T1, T2>, T1, T2> LBiObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiObjIntPred(LBiObjIntPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObjIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1>, T2, T1> LObj1Obj0Int2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Int2Pred(LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0Int2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2>, T1, T2> LInt2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestInt2Obj0Obj1Pred(LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LInt2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1>, T2, T1> LInt2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestInt2Obj1Obj0Pred(LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LInt2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate<T1, T2>, T1, T2> LBiObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiObjLongPred(LBiObjLongPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObjLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2>, T1, T2> LObj0Long2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestObj0Long2Obj1Pred(LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Long2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1>, T2, T1> LObj1Obj0Long2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Long2Pred(LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0Long2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1>, T2, T1> LObj1Long2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Long2Obj0Pred(LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Long2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2>, T1, T2> LLong2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestLong2Obj0Obj1Pred(LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLong2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1>, T2, T1> LLong2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestLong2Obj1Obj0Pred(LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLong2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate<T1, T2>, T1, T2> LBiObjSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObjSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2>, T1, T2> LObj0Srt2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestObj0Srt2Obj1Pred(LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Srt2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1>, T2, T1> LObj1Obj0Srt2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Srt2Pred(LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0Srt2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1>, T2, T1> LObj1Srt2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Srt2Obj0Pred(LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Srt2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2>, T1, T2> LSrt2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestSrt2Obj0Obj1Pred(LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LSrt2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1>, T2, T1> LSrt2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestSrt2Obj1Obj0Pred(LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LSrt2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicate<T1, T2>, T1, T2> LBiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiPred(LBiPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicate.LObj1Obj0Pred<T2, T1>, T2, T1> LObj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj1Obj0Pred(LBiPredicate.LObj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtPredicate> LBiSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBiSrtPred(LBiSrtPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtPredicate.LSrt1Srt0Pred> LSrt1Srt0PredAssert.The<A, ? extends AbstractBooleanAssert> attestSrt1Srt0Pred(LBiSrtPredicate.LSrt1Srt0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LSrt1Srt0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolIntPredicate> LBoolIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBoolIntPred(LBoolIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBoolIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolIntPredicate.LIntBoolPred> LIntBoolPredAssert.The<A, ? extends AbstractBooleanAssert> attestIntBoolPred(LBoolIntPredicate.LIntBoolPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntBoolPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteIntPredicate> LByteIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestByteIntPred(LByteIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LByteIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteIntPredicate.LIntBytePred> LIntBytePredAssert.The<A, ? extends AbstractBooleanAssert> attestIntBytePred(LByteIntPredicate.LIntBytePred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntBytePredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBytePredicate> LBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> attestBytePred(LBytePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharIntPredicate> LCharIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestCharIntPred(LCharIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LCharIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharIntPredicate.LIntCharPred> LIntCharPredAssert.The<A, ? extends AbstractBooleanAssert> attestIntCharPred(LCharIntPredicate.LIntCharPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntCharPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharPredicate> LCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestCharPred(LCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblIntPredicate> LDblIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestDblIntPred(LDblIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LDblIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblIntPredicate.LIntDblPred> LIntDblPredAssert.The<A, ? extends AbstractBooleanAssert> attestIntDblPred(LDblIntPredicate.LIntDblPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntDblPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblPredicate> LDblPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestDblPred(LDblPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltIntPredicate> LFltIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestFltIntPred(LFltIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LFltIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltIntPredicate.LIntFltPred> LIntFltPredAssert.The<A, ? extends AbstractBooleanAssert> attestIntFltPred(LFltIntPredicate.LIntFltPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntFltPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltPredicate> LFltPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestFltPred(LFltPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntPredicate> LIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestIntPred(LIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongIntPredicate> LLongIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestLongIntPred(LLongIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLongIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongIntPredicate.LIntLongPred> LIntLongPredAssert.The<A, ? extends AbstractBooleanAssert> attestIntLongPred(LLongIntPredicate.LIntLongPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntLongPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongPredicate> LLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestLongPred(LLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate<T>, T> LObjBiIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjBiIntPred(LObjBiIntPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjBiIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LObj0Int2Int1Pred<T>, T> LObj0Int2Int1PredAssert.The<A, ? extends AbstractBooleanAssert, T> attestObj0Int2Int1Pred(LObjBiIntPredicate.LObj0Int2Int1Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Int2Int1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LInt1Obj0Int2Pred<T>, T> LInt1Obj0Int2PredAssert.The<A, ? extends AbstractBooleanAssert, T> attestInt1Obj0Int2Pred(LObjBiIntPredicate.LInt1Obj0Int2Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LInt1Obj0Int2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LInt1Int2Obj0Pred<T>, T> LInt1Int2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T> attestInt1Int2Obj0Pred(LObjBiIntPredicate.LInt1Int2Obj0Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LInt1Int2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LInt2Obj0Int1Pred<T>, T> LInt2Obj0Int1PredAssert.The<A, ? extends AbstractBooleanAssert, T> attestInt2Obj0Int1Pred(LObjBiIntPredicate.LInt2Obj0Int1Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LInt2Obj0Int1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LBiInt1Obj0Pred<T>, T> LBiInt1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T> attestBiInt1Obj0Pred(LObjBiIntPredicate.LBiInt1Obj0Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiInt1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolPredicate<T>, T> LObjBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjBoolPred(LObjBoolPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolPredicate.LBoolObjPred<T>, T> LBoolObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestBoolObjPred(LObjBoolPredicate.LBoolObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBoolObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicate<T>, T> LObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjBytePred(LObjBytePredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicate.LByteObjPred<T>, T> LByteObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestByteObjPred(LObjBytePredicate.LByteObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LByteObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicate<T>, T> LObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjCharPred(LObjCharPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicate.LCharObjPred<T>, T> LCharObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestCharObjPred(LObjCharPredicate.LCharObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LCharObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblPredicate<T>, T> LObjDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjDblPred(LObjDblPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblPredicate.LDblObjPred<T>, T> LDblObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestDblObjPred(LObjDblPredicate.LDblObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LDblObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltPredicate<T>, T> LObjFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjFltPred(LObjFltPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltPredicate.LFltObjPred<T>, T> LFltObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestFltObjPred(LObjFltPredicate.LFltObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LFltObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate<T>, T> LObjIntBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjIntBoolPred(LObjIntBoolPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LObjBoolIntPred<T>, T> LObjBoolIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjBoolIntPred(LObjIntBoolPredicate.LObjBoolIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjBoolIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LIntObjBoolPred<T>, T> LIntObjBoolPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntObjBoolPred(LObjIntBoolPredicate.LIntObjBoolPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntObjBoolPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LIntBoolObjPred<T>, T> LIntBoolObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntBoolObjPred(LObjIntBoolPredicate.LIntBoolObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntBoolObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LBoolObjIntPred<T>, T> LBoolObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestBoolObjIntPred(LObjIntBoolPredicate.LBoolObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBoolObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LBoolIntObjPred<T>, T> LBoolIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestBoolIntObjPred(LObjIntBoolPredicate.LBoolIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBoolIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate<T>, T> LObjIntBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjIntBytePred(LObjIntBytePredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LObjByteIntPred<T>, T> LObjByteIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjByteIntPred(LObjIntBytePredicate.LObjByteIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjByteIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LIntObjBytePred<T>, T> LIntObjBytePredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntObjBytePred(LObjIntBytePredicate.LIntObjBytePred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntObjBytePredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LIntByteObjPred<T>, T> LIntByteObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntByteObjPred(LObjIntBytePredicate.LIntByteObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntByteObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LByteObjIntPred<T>, T> LByteObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestByteObjIntPred(LObjIntBytePredicate.LByteObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LByteObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LByteIntObjPred<T>, T> LByteIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestByteIntObjPred(LObjIntBytePredicate.LByteIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LByteIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate<T>, T> LObjIntCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjIntCharPred(LObjIntCharPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LObjCharIntPred<T>, T> LObjCharIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjCharIntPred(LObjIntCharPredicate.LObjCharIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjCharIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LIntObjCharPred<T>, T> LIntObjCharPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntObjCharPred(LObjIntCharPredicate.LIntObjCharPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntObjCharPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LIntCharObjPred<T>, T> LIntCharObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntCharObjPred(LObjIntCharPredicate.LIntCharObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntCharObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LCharObjIntPred<T>, T> LCharObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestCharObjIntPred(LObjIntCharPredicate.LCharObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LCharObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LCharIntObjPred<T>, T> LCharIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestCharIntObjPred(LObjIntCharPredicate.LCharIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LCharIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate<T>, T> LObjIntDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjIntDblPred(LObjIntDblPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LObjDblIntPred<T>, T> LObjDblIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjDblIntPred(LObjIntDblPredicate.LObjDblIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjDblIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LIntObjDblPred<T>, T> LIntObjDblPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntObjDblPred(LObjIntDblPredicate.LIntObjDblPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntObjDblPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LIntDblObjPred<T>, T> LIntDblObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntDblObjPred(LObjIntDblPredicate.LIntDblObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntDblObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LDblObjIntPred<T>, T> LDblObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestDblObjIntPred(LObjIntDblPredicate.LDblObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LDblObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LDblIntObjPred<T>, T> LDblIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestDblIntObjPred(LObjIntDblPredicate.LDblIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LDblIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate<T>, T> LObjIntFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjIntFltPred(LObjIntFltPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LObjFltIntPred<T>, T> LObjFltIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjFltIntPred(LObjIntFltPredicate.LObjFltIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjFltIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LIntObjFltPred<T>, T> LIntObjFltPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntObjFltPred(LObjIntFltPredicate.LIntObjFltPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntObjFltPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LIntFltObjPred<T>, T> LIntFltObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntFltObjPred(LObjIntFltPredicate.LIntFltObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntFltObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LFltObjIntPred<T>, T> LFltObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestFltObjIntPred(LObjIntFltPredicate.LFltObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LFltObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LFltIntObjPred<T>, T> LFltIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestFltIntObjPred(LObjIntFltPredicate.LFltIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LFltIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate<T>, T> LObjIntLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjIntLongPred(LObjIntLongPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LObjLongIntPred<T>, T> LObjLongIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjLongIntPred(LObjIntLongPredicate.LObjLongIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjLongIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LIntObjLongPred<T>, T> LIntObjLongPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntObjLongPred(LObjIntLongPredicate.LIntObjLongPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntObjLongPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LIntLongObjPred<T>, T> LIntLongObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntLongObjPred(LObjIntLongPredicate.LIntLongObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntLongObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LLongObjIntPred<T>, T> LLongObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestLongObjIntPred(LObjIntLongPredicate.LLongObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLongObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LLongIntObjPred<T>, T> LLongIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestLongIntObjPred(LObjIntLongPredicate.LLongIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLongIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate<T1, T2>, T1, T2> LObjIntObjPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestObjIntObjPred(LObjIntObjPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntObjPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2>, T1, T2> LInt1BiObj2PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestInt1BiObj2Pred(LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LInt1BiObj2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1>, T2, T1> LInt1Obj2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestInt1Obj2Obj0Pred(LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LInt1Obj2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1>, T2, T1> LObj2Int1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> attestObj2Int1Obj0Pred(LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj2Int1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicate<T>, T> LObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjIntPred(LObjIntPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicate.LIntObjPred<T>, T> LIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntObjPred(LObjIntPredicate.LIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate<T>, T> LObjIntSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjIntSrtPred(LObjIntSrtPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjIntSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LObjSrtIntPred<T>, T> LObjSrtIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjSrtIntPred(LObjIntSrtPredicate.LObjSrtIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjSrtIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LIntObjSrtPred<T>, T> LIntObjSrtPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntObjSrtPred(LObjIntSrtPredicate.LIntObjSrtPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntObjSrtPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LIntSrtObjPred<T>, T> LIntSrtObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestIntSrtObjPred(LObjIntSrtPredicate.LIntSrtObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntSrtObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LSrtObjIntPred<T>, T> LSrtObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestSrtObjIntPred(LObjIntSrtPredicate.LSrtObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LSrtObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LSrtIntObjPred<T>, T> LSrtIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestSrtIntObjPred(LObjIntSrtPredicate.LSrtIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LSrtIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicate<T>, T> LObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjLongPred(LObjLongPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicate.LLongObjPred<T>, T> LLongObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestLongObjPred(LObjLongPredicate.LLongObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LLongObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtPredicate<T>, T> LObjSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestObjSrtPred(LObjSrtPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObjSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtPredicate.LSrtObjPred<T>, T> LSrtObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> attestSrtObjPred(LObjSrtPredicate.LSrtObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LSrtObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LPredicate<T>, T> LPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestPred(LPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LQuadPredicate<T1, T2, T3, T4>, T1, T2, T3, T4> LQuadPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3, T4> attestQuadPred(LQuadPredicate<T1, T2, T3, T4> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LQuadPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LQuintPredicate<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> LQuintPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3, T4, T5> attestQuintPred(LQuintPredicate<T1, T2, T3, T4, T5> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LQuintPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtIntPredicate> LSrtIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestSrtIntPred(LSrtIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LSrtIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtIntPredicate.LIntSrtPred> LIntSrtPredAssert.The<A, ? extends AbstractBooleanAssert> attestIntSrtPred(LSrtIntPredicate.LIntSrtPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LIntSrtPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtPredicate> LSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestSrtPred(LSrtPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriBytePredicate> LTriBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> attestTriBytePred(LTriBytePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LTriBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriCharPredicate> LTriCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestTriCharPred(LTriCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LTriCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriDblPredicate> LTriDblPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestTriDblPred(LTriDblPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LTriDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFltPredicate> LTriFltPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestTriFltPred(LTriFltPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LTriFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriIntPredicate> LTriIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestTriIntPred(LTriIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LTriIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriLongPredicate> LTriLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestTriLongPred(LTriLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LTriLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate<T1, T2, T3>, T1, T2, T3> LTriPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3> attestTriPred(LTriPredicate<T1, T2, T3> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LTriPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2>, T1, T3, T2> LObj0Obj2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T3, T2> attestObj0Obj2Obj1Pred(LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj0Obj2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LObj1BiObj2Pred<T2, T1, T3>, T2, T1, T3> LObj1BiObj2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1, T3> attestObj1BiObj2Pred(LTriPredicate.LObj1BiObj2Pred<T2, T1, T3> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1BiObj2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1>, T2, T3, T1> LObj1Obj2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T3, T1> attestObj1Obj2Obj0Pred(LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj1Obj2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2>, T3, T1, T2> LObj2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T3, T1, T2> attestObj2Obj0Obj1Pred(LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LObj2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1>, T3, T2, T1> LBiObj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T3, T2, T1> attestBiObj1Obj0Pred(LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBiObj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriSrtPredicate> LTriSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestTriSrtPred(LTriSrtPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LTriSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolSupplier> LBoolSupplierAssert.The<A, ? extends AbstractBooleanAssert> attestBoolSup(LBoolSupplier func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new LBoolSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteSupplier> LByteSupplierAssert.The<A, ? extends AbstractByteAssert> attestByteSup(LByteSupplier func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::attestThatByte;
		return new LByteSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharSupplier> LCharSupplierAssert.The<A, ? extends AbstractCharacterAssert> attestCharSup(LCharSupplier func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::attestThatChar;
		return new LCharSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblSupplier> LDblSupplierAssert.The<A, ? extends AbstractDoubleAssert> attestDblSup(LDblSupplier func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new LDblSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltSupplier> LFltSupplierAssert.The<A, ? extends AbstractFloatAssert> attestFltSup(LFltSupplier func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::attestThatFlt;
		return new LFltSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntSupplier> LIntSupplierAssert.The<A, ? extends AbstractIntegerAssert> attestIntSup(LIntSupplier func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new LIntSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongSupplier> LLongSupplierAssert.The<A, ? extends AbstractLongAssert> attestLongSup(LLongSupplier func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new LLongSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtSupplier> LSrtSupplierAssert.The<A, ? extends AbstractShortAssert> attestSrtSup(LSrtSupplier func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::attestThatSrt;
		return new LSrtSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSupplier<T>, T> LSupplierAssert.The<A, ? extends OS, T> attestSup(LSupplier<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new LSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Runnable> JreRunnableAssert.The<A> attestAct(Runnable func) {
		return new JreRunnableAssert.The(func);
	}

	@Nonnull
	default <A extends BiConsumer<T1, T2>, T1, T2> JreBiConsumerAssert.The<A, T1, T2> attestBiCons(BiConsumer<T1, T2> func) {
		return new JreBiConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends Consumer<T>, T> JreConsumerAssert.The<A, T> attestCons(Consumer<T> func) {
		return new JreConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends DoubleConsumer> JreDoubleConsumerAssert.The<A> attestDblCons(DoubleConsumer func) {
		return new JreDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends IntConsumer> JreIntConsumerAssert.The<A> attestIntCons(IntConsumer func) {
		return new JreIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LongConsumer> JreLongConsumerAssert.The<A> attestLongCons(LongConsumer func) {
		return new JreLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjDoubleConsumer<T>, T> JreObjDoubleConsumerAssert.The<A, T> attestObjDblCons(ObjDoubleConsumer<T> func) {
		return new JreObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjIntConsumer<T>, T> JreObjIntConsumerAssert.The<A, T> attestObjIntCons(ObjIntConsumer<T> func) {
		return new JreObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjLongConsumer<T>, T> JreObjLongConsumerAssert.The<A, T> attestObjLongCons(ObjLongConsumer<T> func) {
		return new JreObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends BinaryOperator<T>, T> JreBinaryOperatorAssert.The<A, ? extends OS, T> attestBinaryOp(BinaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new JreBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleBinaryOperator> JreDoubleBinaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> attestDblBinaryOp(DoubleBinaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new JreDoubleBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleUnaryOperator> JreDoubleUnaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> attestDblUnaryOp(DoubleUnaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new JreDoubleUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntBinaryOperator> JreIntBinaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> attestIntBinaryOp(IntBinaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new JreIntBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntUnaryOperator> JreIntUnaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> attestIntUnaryOp(IntUnaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new JreIntUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongBinaryOperator> JreLongBinaryOperatorAssert.The<A, ? extends AbstractLongAssert> attestLongBinaryOp(LongBinaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new JreLongBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongUnaryOperator> JreLongUnaryOperatorAssert.The<A, ? extends AbstractLongAssert> attestLongUnaryOp(LongUnaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new JreLongUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends UnaryOperator<T>, T> JreUnaryOperatorAssert.The<A, ? extends OS, T> attestUnaryOp(UnaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new JreUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BiFunction<T1, T2, R>, T1, T2, R> JreBiFunctionAssert.The<A, ? extends OS, T1, T2, R> attestBiFunc(BiFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new JreBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleFunction<R>, R> JreDoubleFunctionAssert.The<A, ? extends OS, R> attestDblFunc(DoubleFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new JreDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToIntFunction> JreDoubleToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestDblToIntFunc(DoubleToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new JreDoubleToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToLongFunction> JreDoubleToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestDblToLongFunc(DoubleToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new JreDoubleToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Function<T, R>, T, R> JreFunctionAssert.The<A, ? extends OS, T, R> attestFunc(Function<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new JreFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntFunction<R>, R> JreIntFunctionAssert.The<A, ? extends OS, R> attestIntFunc(IntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new JreIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntToDoubleFunction> JreIntToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestIntToDblFunc(IntToDoubleFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new JreIntToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntToLongFunction> JreIntToLongFunctionAssert.The<A, ? extends AbstractLongAssert> attestIntToLongFunc(IntToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new JreIntToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongFunction<R>, R> JreLongFunctionAssert.The<A, ? extends OS, R> attestLongFunc(LongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new JreLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongToDoubleFunction> JreLongToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> attestLongToDblFunc(LongToDoubleFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new JreLongToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongToIntFunction> JreLongToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> attestLongToIntFunc(LongToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new JreLongToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleBiFunction<T1, T2>, T1, T2> JreToDoubleBiFunctionAssert.The<A, ? extends AbstractDoubleAssert, T1, T2> attestToDblBiFunc(ToDoubleBiFunction<T1, T2> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new JreToDoubleBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleFunction<T>, T> JreToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> attestToDblFunc(ToDoubleFunction<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new JreToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToIntBiFunction<T1, T2>, T1, T2> JreToIntBiFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> attestToIntBiFunc(ToIntBiFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new JreToIntBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToIntFunction<T>, T> JreToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> attestToIntFunc(ToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new JreToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToLongBiFunction<T1, T2>, T1, T2> JreToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> attestToLongBiFunc(ToLongBiFunction<T1, T2> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new JreToLongBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToLongFunction<T>, T> JreToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> attestToLongFunc(ToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new JreToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BiPredicate<T1, T2>, T1, T2> JreBiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> attestBiPred(BiPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new JreBiPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoublePredicate> JreDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert> attestDblPred(DoublePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new JreDoublePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntPredicate> JreIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestIntPred(IntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new JreIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongPredicate> JreLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> attestLongPred(LongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new JreLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Predicate<T>, T> JrePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> attestPred(Predicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new JrePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BooleanSupplier> JreBooleanSupplierAssert.The<A, ? extends AbstractBooleanAssert> attestBoolSup(BooleanSupplier func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::attestThatBool;
		return new JreBooleanSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleSupplier> JreDoubleSupplierAssert.The<A, ? extends AbstractDoubleAssert> attestDblSup(DoubleSupplier func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::attestThatDbl;
		return new JreDoubleSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntSupplier> JreIntSupplierAssert.The<A, ? extends AbstractIntegerAssert> attestIntSup(IntSupplier func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::attestThatInt;
		return new JreIntSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongSupplier> JreLongSupplierAssert.The<A, ? extends AbstractLongAssert> attestLongSup(LongSupplier func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::attestThatLong;
		return new JreLongSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Supplier<T>, T> JreSupplierAssert.The<A, ? extends OS, T> attestSup(Supplier<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::attestThatObj;
		return new JreSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default OptBoolTraitAssert attestThat(OptBoolTrait<?> actual) {
		return new OptBoolTraitAssert(actual);
	}

	@Nonnull
	default <T> OptTraitAssert<T> attestThat(OptTrait<T, ?> actual) {
		return new OptTraitAssert<T>(actual);
	}

	@Nonnull
	default OptByteTraitAssert attestThat(OptByteTrait<?> actual) {
		return new OptByteTraitAssert(actual);
	}

	@Nonnull
	default OptDblTraitAssert attestThat(OptDblTrait<?> actual) {
		return new OptDblTraitAssert(actual);
	}

	@Nonnull
	default OptCharTraitAssert attestThat(OptCharTrait<?> actual) {
		return new OptCharTraitAssert(actual);
	}

	@Nonnull
	default OptSrtTraitAssert attestThat(OptSrtTrait<?> actual) {
		return new OptSrtTraitAssert(actual);
	}

	@Nonnull
	default OptFltTraitAssert attestThat(OptFltTrait<?> actual) {
		return new OptFltTraitAssert(actual);
	}

	@Nonnull
	default OptIntTraitAssert attestThat(OptIntTrait<?> actual) {
		return new OptIntTraitAssert(actual);
	}

	@Nonnull
	default OptLongTraitAssert attestThat(OptLongTrait<?> actual) {
		return new OptLongTraitAssert(actual);
	}

	@Nonnull
	default <A> MagmaAssert.ObjAssert<A> attestThat(A actual) {
		return new MagmaAssert.ObjAssert(actual);
	}

	@Nonnull
	default <A extends Throwable> MagmaAssert.ThrowableAssert<A> attestThat(A actual) {
		return new MagmaAssert.ThrowableAssert(actual);
	}

	@Nonnull
	default MagmaAssert.ThrowableAssert<? extends Throwable> attestThatThrownBy(ThrowableAssert.ThrowingCallable shouldRaiseThrowable) {
		return attestThat(Assertions.catchThrowable(shouldRaiseThrowable)).hasBeenThrown();
	}
}