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
public interface DefaultMagmaAssertions<OS extends Assert> extends BasicAssertions<OS> {

	default <RS extends Assert, R> DefaultMagmaAssertions<RS> withinCodomain(final Function<R, RS> codomainAssertFactory) {
		return new DefaultMagmaAssertions<RS>() {
			public RS assertThatObj(Object actual) {
				return codomainAssertFactory.apply((R) actual);
			}
		};
	}

	default <RS extends Assert, R> DefaultMagmaAssertions<RS> withinCodomain(final Function<R, RS> codomainAssertFactory, Class<R> r, Class<RS> rs) {
		return new DefaultMagmaAssertions<RS>() {
			public RS assertThatObj(Object actual) {
				return codomainAssertFactory.apply((R) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractOptionalAssert> withinOptionalCodomain() {
		return new DefaultMagmaAssertions<AbstractOptionalAssert>() {
			@Override
			public AbstractOptionalAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Optional) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractBigDecimalAssert> withinBigDecimalCodomain() {
		return new DefaultMagmaAssertions<AbstractBigDecimalAssert>() {
			@Override
			public AbstractBigDecimalAssert assertThatObj(Object actual) {
				return Assertions.assertThat((BigDecimal) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractBooleanAssert> withinBooleanCodomain() {
		return new DefaultMagmaAssertions<AbstractBooleanAssert>() {
			@Override
			public AbstractBooleanAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Boolean) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractBooleanArrayAssert> withinBooleanArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractBooleanArrayAssert>() {
			@Override
			public AbstractBooleanArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((boolean[]) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractByteAssert> withinByteCodomain() {
		return new DefaultMagmaAssertions<AbstractByteAssert>() {
			@Override
			public AbstractByteAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Byte) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractByteArrayAssert> withinByteArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractByteArrayAssert>() {
			@Override
			public AbstractByteArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((byte[]) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractCharacterAssert> withinCharacterCodomain() {
		return new DefaultMagmaAssertions<AbstractCharacterAssert>() {
			@Override
			public AbstractCharacterAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Character) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractCharArrayAssert> withinCharArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractCharArrayAssert>() {
			@Override
			public AbstractCharArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((char[]) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractClassAssert> withinClassCodomain() {
		return new DefaultMagmaAssertions<AbstractClassAssert>() {
			@Override
			public AbstractClassAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Class) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractComparableAssert> withinComparableCodomain() {
		return new DefaultMagmaAssertions<AbstractComparableAssert>() {
			@Override
			public AbstractComparableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Comparable) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractIterableAssert> withinIterableCodomain() {
		return new DefaultMagmaAssertions<AbstractIterableAssert>() {
			@Override
			public AbstractIterableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Iterable) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractIteratorAssert> withinIteratorCodomain() {
		return new DefaultMagmaAssertions<AbstractIteratorAssert>() {
			@Override
			public AbstractIteratorAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Iterator) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractDoubleAssert> withinDoubleCodomain() {
		return new DefaultMagmaAssertions<AbstractDoubleAssert>() {
			@Override
			public AbstractDoubleAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Double) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractDoubleArrayAssert> withinDoubleArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractDoubleArrayAssert>() {
			@Override
			public AbstractDoubleArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((double[]) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractPathAssert> withinPathCodomain() {
		return new DefaultMagmaAssertions<AbstractPathAssert>() {
			@Override
			public AbstractPathAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Path) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractInputStreamAssert> withinInputStreamCodomain() {
		return new DefaultMagmaAssertions<AbstractInputStreamAssert>() {
			@Override
			public AbstractInputStreamAssert assertThatObj(Object actual) {
				return Assertions.assertThat((InputStream) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractFloatAssert> withinFloatCodomain() {
		return new DefaultMagmaAssertions<AbstractFloatAssert>() {
			@Override
			public AbstractFloatAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Float) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractFloatArrayAssert> withinFloatArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractFloatArrayAssert>() {
			@Override
			public AbstractFloatArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((float[]) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractIntegerAssert> withinIntegerCodomain() {
		return new DefaultMagmaAssertions<AbstractIntegerAssert>() {
			@Override
			public AbstractIntegerAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Integer) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractIntArrayAssert> withinIntArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractIntArrayAssert>() {
			@Override
			public AbstractIntArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((int[]) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractListAssert> withinListCodomain() {
		return new DefaultMagmaAssertions<AbstractListAssert>() {
			@Override
			public AbstractListAssert assertThatObj(Object actual) {
				return Assertions.assertThat((List) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractLongAssert> withinLongCodomain() {
		return new DefaultMagmaAssertions<AbstractLongAssert>() {
			@Override
			public AbstractLongAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Long) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractLongArrayAssert> withinLongArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractLongArrayAssert>() {
			@Override
			public AbstractLongArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((long[]) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractMapAssert> withinMapCodomain() {
		return new DefaultMagmaAssertions<AbstractMapAssert>() {
			@Override
			public AbstractMapAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Map) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractShortAssert> withinShortCodomain() {
		return new DefaultMagmaAssertions<AbstractShortAssert>() {
			@Override
			public AbstractShortAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Short) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractShortArrayAssert> withinShortArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractShortArrayAssert>() {
			@Override
			public AbstractShortArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((short[]) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractCharSequenceAssert> withinCharSequenceCodomain() {
		return new DefaultMagmaAssertions<AbstractCharSequenceAssert>() {
			@Override
			public AbstractCharSequenceAssert assertThatObj(Object actual) {
				return Assertions.assertThat((CharSequence) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractCharSequenceAssert> withinStringCodomain() {
		return new DefaultMagmaAssertions<AbstractCharSequenceAssert>() {
			@Override
			public AbstractCharSequenceAssert assertThatObj(Object actual) {
				return Assertions.assertThat((String) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractDateAssert> withinDateCodomain() {
		return new DefaultMagmaAssertions<AbstractDateAssert>() {
			@Override
			public AbstractDateAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Date) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractZonedDateTimeAssert> withinZonedDateTimeCodomain() {
		return new DefaultMagmaAssertions<AbstractZonedDateTimeAssert>() {
			@Override
			public AbstractZonedDateTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((ZonedDateTime) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractLocalDateTimeAssert> withinLocalDateTimeCodomain() {
		return new DefaultMagmaAssertions<AbstractLocalDateTimeAssert>() {
			@Override
			public AbstractLocalDateTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalDateTime) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractLocalTimeAssert> withinLocalTimeCodomain() {
		return new DefaultMagmaAssertions<AbstractLocalTimeAssert>() {
			@Override
			public AbstractLocalTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalTime) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractLocalDateAssert> withinLocalDateCodomain() {
		return new DefaultMagmaAssertions<AbstractLocalDateAssert>() {
			@Override
			public AbstractLocalDateAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalDate) actual);
			}
		};
	}

	default DefaultMagmaAssertions<AbstractThrowableAssert> withinThrowableCodomain() {
		return new DefaultMagmaAssertions<AbstractThrowableAssert>() {
			@Override
			public AbstractThrowableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Throwable) actual);
			}
		};
	}

	default <T> DefaultMagmaAssertions<AbstractObjectArrayAssert> withinTArrayCodomain() {
		return new DefaultMagmaAssertions<AbstractObjectArrayAssert>() {
			@Override
			public AbstractObjectArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Object[]) actual);
			}
		};
	}

	@Nonnull
	default <A extends LAction> LActionAssert.The<A> assertAct(LAction func) {
		return new LActionAssert.The(func);
	}

	@Nonnull
	default <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> assertBiCons(LBiConsumer<T1, T2> func) {
		return new LBiConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiConsumer.LObj1Obj0Cons<T2, T1>, T2, T1> LObj1Obj0ConsAssert.The<A, T2, T1> assertObj1Obj0Cons(LBiConsumer.LObj1Obj0Cons<T2, T1> func) {
		return new LObj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> assertCons(LConsumer<T> func) {
		return new LConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LQuadConsumer<T1, T2, T3, T4>, T1, T2, T3, T4> LQuadConsumerAssert.The<A, T1, T2, T3, T4> assertQuadCons(LQuadConsumer<T1, T2, T3, T4> func) {
		return new LQuadConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LQuintConsumer<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> LQuintConsumerAssert.The<A, T1, T2, T3, T4, T5> assertQuintCons(LQuintConsumer<T1, T2, T3, T4, T5> func) {
		return new LQuintConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> assertTriCons(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2>, T1, T3, T2> LObj0Obj2Obj1ConsAssert.The<A, T1, T3, T2> assertObj0Obj2Obj1Cons(LTriConsumer.LObj0Obj2Obj1Cons<T1, T3, T2> func) {
		return new LObj0Obj2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LObj1BiObj2Cons<T2, T1, T3>, T2, T1, T3> LObj1BiObj2ConsAssert.The<A, T2, T1, T3> assertObj1BiObj2Cons(LTriConsumer.LObj1BiObj2Cons<T2, T1, T3> func) {
		return new LObj1BiObj2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1>, T2, T3, T1> LObj1Obj2Obj0ConsAssert.The<A, T2, T3, T1> assertObj1Obj2Obj0Cons(LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1> func) {
		return new LObj1Obj2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2>, T3, T1, T2> LObj2Obj0Obj1ConsAssert.The<A, T3, T1, T2> assertObj2Obj0Obj1Cons(LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2> func) {
		return new LObj2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1>, T3, T2, T1> LBiObj1Obj0ConsAssert.The<A, T3, T2, T1> assertBiObj1Obj0Cons(LTriConsumer.LBiObj1Obj0Cons<T3, T2, T1> func) {
		return new LBiObj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolConsumer> LBoolConsumerAssert.The<A> assertBoolCons(LBoolConsumer func) {
		return new LBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LByteConsumer> LByteConsumerAssert.The<A> assertByteCons(LByteConsumer func) {
		return new LByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LCharConsumer> LCharConsumerAssert.The<A> assertCharCons(LCharConsumer func) {
		return new LCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LDblConsumer> LDblConsumerAssert.The<A> assertDblCons(LDblConsumer func) {
		return new LDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LFltConsumer> LFltConsumerAssert.The<A> assertFltCons(LFltConsumer func) {
		return new LFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LIntConsumer> LIntConsumerAssert.The<A> assertIntCons(LIntConsumer func) {
		return new LIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LLongConsumer> LLongConsumerAssert.The<A> assertLongCons(LLongConsumer func) {
		return new LLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LSrtConsumer> LSrtConsumerAssert.The<A> assertSrtCons(LSrtConsumer func) {
		return new LSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiBoolConsumer> LBiBoolConsumerAssert.The<A> assertBiBoolCons(LBiBoolConsumer func) {
		return new LBiBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiBoolConsumer.LBool1Bool0Cons> LBool1Bool0ConsAssert.The<A> assertBool1Bool0Cons(LBiBoolConsumer.LBool1Bool0Cons func) {
		return new LBool1Bool0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> assertBiByteCons(LBiByteConsumer func) {
		return new LBiByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiByteConsumer.LByte1Byte0Cons> LByte1Byte0ConsAssert.The<A> assertByte1Byte0Cons(LBiByteConsumer.LByte1Byte0Cons func) {
		return new LByte1Byte0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> assertBiCharCons(LBiCharConsumer func) {
		return new LBiCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiCharConsumer.LChar1Char0Cons> LChar1Char0ConsAssert.The<A> assertChar1Char0Cons(LBiCharConsumer.LChar1Char0Cons func) {
		return new LChar1Char0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiDblConsumer> LBiDblConsumerAssert.The<A> assertBiDblCons(LBiDblConsumer func) {
		return new LBiDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiDblConsumer.LDbl1Dbl0Cons> LDbl1Dbl0ConsAssert.The<A> assertDbl1Dbl0Cons(LBiDblConsumer.LDbl1Dbl0Cons func) {
		return new LDbl1Dbl0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiFltConsumer> LBiFltConsumerAssert.The<A> assertBiFltCons(LBiFltConsumer func) {
		return new LBiFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiFltConsumer.LFlt1Flt0Cons> LFlt1Flt0ConsAssert.The<A> assertFlt1Flt0Cons(LBiFltConsumer.LFlt1Flt0Cons func) {
		return new LFlt1Flt0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> assertBiIntCons(LBiIntConsumer func) {
		return new LBiIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiIntConsumer.LInt1Int0Cons> LInt1Int0ConsAssert.The<A> assertInt1Int0Cons(LBiIntConsumer.LInt1Int0Cons func) {
		return new LInt1Int0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> assertBiLongCons(LBiLongConsumer func) {
		return new LBiLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiLongConsumer.LLong1Long0Cons> LLong1Long0ConsAssert.The<A> assertLong1Long0Cons(LBiLongConsumer.LLong1Long0Cons func) {
		return new LLong1Long0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiSrtConsumer> LBiSrtConsumerAssert.The<A> assertBiSrtCons(LBiSrtConsumer func) {
		return new LBiSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiSrtConsumer.LSrt1Srt0Cons> LSrt1Srt0ConsAssert.The<A> assertSrt1Srt0Cons(LBiSrtConsumer.LSrt1Srt0Cons func) {
		return new LSrt1Srt0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolIntConsumer> LBoolIntConsumerAssert.The<A> assertBoolIntCons(LBoolIntConsumer func) {
		return new LBoolIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolIntConsumer.LIntBoolCons> LIntBoolConsAssert.The<A> assertIntBoolCons(LBoolIntConsumer.LIntBoolCons func) {
		return new LIntBoolConsAssert.The(func);
	}

	@Nonnull
	default <A extends LByteIntConsumer> LByteIntConsumerAssert.The<A> assertByteIntCons(LByteIntConsumer func) {
		return new LByteIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LByteIntConsumer.LIntByteCons> LIntByteConsAssert.The<A> assertIntByteCons(LByteIntConsumer.LIntByteCons func) {
		return new LIntByteConsAssert.The(func);
	}

	@Nonnull
	default <A extends LCharIntConsumer> LCharIntConsumerAssert.The<A> assertCharIntCons(LCharIntConsumer func) {
		return new LCharIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LCharIntConsumer.LIntCharCons> LIntCharConsAssert.The<A> assertIntCharCons(LCharIntConsumer.LIntCharCons func) {
		return new LIntCharConsAssert.The(func);
	}

	@Nonnull
	default <A extends LDblIntConsumer> LDblIntConsumerAssert.The<A> assertDblIntCons(LDblIntConsumer func) {
		return new LDblIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LDblIntConsumer.LIntDblCons> LIntDblConsAssert.The<A> assertIntDblCons(LDblIntConsumer.LIntDblCons func) {
		return new LIntDblConsAssert.The(func);
	}

	@Nonnull
	default <A extends LFltIntConsumer> LFltIntConsumerAssert.The<A> assertFltIntCons(LFltIntConsumer func) {
		return new LFltIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LFltIntConsumer.LIntFltCons> LIntFltConsAssert.The<A> assertIntFltCons(LFltIntConsumer.LIntFltCons func) {
		return new LIntFltConsAssert.The(func);
	}

	@Nonnull
	default <A extends LLongIntConsumer> LLongIntConsumerAssert.The<A> assertLongIntCons(LLongIntConsumer func) {
		return new LLongIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LLongIntConsumer.LIntLongCons> LIntLongConsAssert.The<A> assertIntLongCons(LLongIntConsumer.LIntLongCons func) {
		return new LIntLongConsAssert.The(func);
	}

	@Nonnull
	default <A extends LSrtIntConsumer> LSrtIntConsumerAssert.The<A> assertSrtIntCons(LSrtIntConsumer func) {
		return new LSrtIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LSrtIntConsumer.LIntSrtCons> LIntSrtConsAssert.The<A> assertIntSrtCons(LSrtIntConsumer.LIntSrtCons func) {
		return new LIntSrtConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> assertBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2>, T1, T2> LObj0Bool2Obj1ConsAssert.The<A, T1, T2> assertObj0Bool2Obj1Cons(LBiObjBoolConsumer.LObj0Bool2Obj1Cons<T1, T2> func) {
		return new LObj0Bool2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1>, T2, T1> LObj1Obj0Bool2ConsAssert.The<A, T2, T1> assertObj1Obj0Bool2Cons(LBiObjBoolConsumer.LObj1Obj0Bool2Cons<T2, T1> func) {
		return new LObj1Obj0Bool2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1>, T2, T1> LObj1Bool2Obj0ConsAssert.The<A, T2, T1> assertObj1Bool2Obj0Cons(LBiObjBoolConsumer.LObj1Bool2Obj0Cons<T2, T1> func) {
		return new LObj1Bool2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2>, T1, T2> LBool2Obj0Obj1ConsAssert.The<A, T1, T2> assertBool2Obj0Obj1Cons(LBiObjBoolConsumer.LBool2Obj0Obj1Cons<T1, T2> func) {
		return new LBool2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1>, T2, T1> LBool2Obj1Obj0ConsAssert.The<A, T2, T1> assertBool2Obj1Obj0Cons(LBiObjBoolConsumer.LBool2Obj1Obj0Cons<T2, T1> func) {
		return new LBool2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> assertBiObjByteCons(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2>, T1, T2> LObj0Byte2Obj1ConsAssert.The<A, T1, T2> assertObj0Byte2Obj1Cons(LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> func) {
		return new LObj0Byte2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1>, T2, T1> LObj1Obj0Byte2ConsAssert.The<A, T2, T1> assertObj1Obj0Byte2Cons(LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> func) {
		return new LObj1Obj0Byte2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1>, T2, T1> LObj1Byte2Obj0ConsAssert.The<A, T2, T1> assertObj1Byte2Obj0Cons(LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> func) {
		return new LObj1Byte2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2>, T1, T2> LByte2Obj0Obj1ConsAssert.The<A, T1, T2> assertByte2Obj0Obj1Cons(LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> func) {
		return new LByte2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1>, T2, T1> LByte2Obj1Obj0ConsAssert.The<A, T2, T1> assertByte2Obj1Obj0Cons(LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> func) {
		return new LByte2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> assertBiObjCharCons(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2>, T1, T2> LObj0Char2Obj1ConsAssert.The<A, T1, T2> assertObj0Char2Obj1Cons(LBiObjCharConsumer.LObj0Char2Obj1Cons<T1, T2> func) {
		return new LObj0Char2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1>, T2, T1> LObj1Obj0Char2ConsAssert.The<A, T2, T1> assertObj1Obj0Char2Cons(LBiObjCharConsumer.LObj1Obj0Char2Cons<T2, T1> func) {
		return new LObj1Obj0Char2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1>, T2, T1> LObj1Char2Obj0ConsAssert.The<A, T2, T1> assertObj1Char2Obj0Cons(LBiObjCharConsumer.LObj1Char2Obj0Cons<T2, T1> func) {
		return new LObj1Char2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2>, T1, T2> LChar2Obj0Obj1ConsAssert.The<A, T1, T2> assertChar2Obj0Obj1Cons(LBiObjCharConsumer.LChar2Obj0Obj1Cons<T1, T2> func) {
		return new LChar2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1>, T2, T1> LChar2Obj1Obj0ConsAssert.The<A, T2, T1> assertChar2Obj1Obj0Cons(LBiObjCharConsumer.LChar2Obj1Obj0Cons<T2, T1> func) {
		return new LChar2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer<T1, T2>, T1, T2> LBiObjDblConsumerAssert.The<A, T1, T2> assertBiObjDblCons(LBiObjDblConsumer<T1, T2> func) {
		return new LBiObjDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2>, T1, T2> LObj0Dbl2Obj1ConsAssert.The<A, T1, T2> assertObj0Dbl2Obj1Cons(LBiObjDblConsumer.LObj0Dbl2Obj1Cons<T1, T2> func) {
		return new LObj0Dbl2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1>, T2, T1> LObj1Obj0Dbl2ConsAssert.The<A, T2, T1> assertObj1Obj0Dbl2Cons(LBiObjDblConsumer.LObj1Obj0Dbl2Cons<T2, T1> func) {
		return new LObj1Obj0Dbl2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1>, T2, T1> LObj1Dbl2Obj0ConsAssert.The<A, T2, T1> assertObj1Dbl2Obj0Cons(LBiObjDblConsumer.LObj1Dbl2Obj0Cons<T2, T1> func) {
		return new LObj1Dbl2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2>, T1, T2> LDbl2Obj0Obj1ConsAssert.The<A, T1, T2> assertDbl2Obj0Obj1Cons(LBiObjDblConsumer.LDbl2Obj0Obj1Cons<T1, T2> func) {
		return new LDbl2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1>, T2, T1> LDbl2Obj1Obj0ConsAssert.The<A, T2, T1> assertDbl2Obj1Obj0Cons(LBiObjDblConsumer.LDbl2Obj1Obj0Cons<T2, T1> func) {
		return new LDbl2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer<T1, T2>, T1, T2> LBiObjFltConsumerAssert.The<A, T1, T2> assertBiObjFltCons(LBiObjFltConsumer<T1, T2> func) {
		return new LBiObjFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2>, T1, T2> LObj0Flt2Obj1ConsAssert.The<A, T1, T2> assertObj0Flt2Obj1Cons(LBiObjFltConsumer.LObj0Flt2Obj1Cons<T1, T2> func) {
		return new LObj0Flt2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1>, T2, T1> LObj1Obj0Flt2ConsAssert.The<A, T2, T1> assertObj1Obj0Flt2Cons(LBiObjFltConsumer.LObj1Obj0Flt2Cons<T2, T1> func) {
		return new LObj1Obj0Flt2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1>, T2, T1> LObj1Flt2Obj0ConsAssert.The<A, T2, T1> assertObj1Flt2Obj0Cons(LBiObjFltConsumer.LObj1Flt2Obj0Cons<T2, T1> func) {
		return new LObj1Flt2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2>, T1, T2> LFlt2Obj0Obj1ConsAssert.The<A, T1, T2> assertFlt2Obj0Obj1Cons(LBiObjFltConsumer.LFlt2Obj0Obj1Cons<T1, T2> func) {
		return new LFlt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1>, T2, T1> LFlt2Obj1Obj0ConsAssert.The<A, T2, T1> assertFlt2Obj1Obj0Cons(LBiObjFltConsumer.LFlt2Obj1Obj0Cons<T2, T1> func) {
		return new LFlt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> assertBiObjIntCons(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1>, T2, T1> LObj1Obj0Int2ConsAssert.The<A, T2, T1> assertObj1Obj0Int2Cons(LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1> func) {
		return new LObj1Obj0Int2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2>, T1, T2> LInt2Obj0Obj1ConsAssert.The<A, T1, T2> assertInt2Obj0Obj1Cons(LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> func) {
		return new LInt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1>, T2, T1> LInt2Obj1Obj0ConsAssert.The<A, T2, T1> assertInt2Obj1Obj0Cons(LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1> func) {
		return new LInt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> assertBiObjLongCons(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2>, T1, T2> LObj0Long2Obj1ConsAssert.The<A, T1, T2> assertObj0Long2Obj1Cons(LBiObjLongConsumer.LObj0Long2Obj1Cons<T1, T2> func) {
		return new LObj0Long2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1>, T2, T1> LObj1Obj0Long2ConsAssert.The<A, T2, T1> assertObj1Obj0Long2Cons(LBiObjLongConsumer.LObj1Obj0Long2Cons<T2, T1> func) {
		return new LObj1Obj0Long2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1>, T2, T1> LObj1Long2Obj0ConsAssert.The<A, T2, T1> assertObj1Long2Obj0Cons(LBiObjLongConsumer.LObj1Long2Obj0Cons<T2, T1> func) {
		return new LObj1Long2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2>, T1, T2> LLong2Obj0Obj1ConsAssert.The<A, T1, T2> assertLong2Obj0Obj1Cons(LBiObjLongConsumer.LLong2Obj0Obj1Cons<T1, T2> func) {
		return new LLong2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1>, T2, T1> LLong2Obj1Obj0ConsAssert.The<A, T2, T1> assertLong2Obj1Obj0Cons(LBiObjLongConsumer.LLong2Obj1Obj0Cons<T2, T1> func) {
		return new LLong2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer<T1, T2>, T1, T2> LBiObjSrtConsumerAssert.The<A, T1, T2> assertBiObjSrtCons(LBiObjSrtConsumer<T1, T2> func) {
		return new LBiObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2>, T1, T2> LObj0Srt2Obj1ConsAssert.The<A, T1, T2> assertObj0Srt2Obj1Cons(LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> func) {
		return new LObj0Srt2Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1>, T2, T1> LObj1Obj0Srt2ConsAssert.The<A, T2, T1> assertObj1Obj0Srt2Cons(LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1> func) {
		return new LObj1Obj0Srt2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1>, T2, T1> LObj1Srt2Obj0ConsAssert.The<A, T2, T1> assertObj1Srt2Obj0Cons(LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1> func) {
		return new LObj1Srt2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2>, T1, T2> LSrt2Obj0Obj1ConsAssert.The<A, T1, T2> assertSrt2Obj0Obj1Cons(LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> func) {
		return new LSrt2Obj0Obj1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1>, T2, T1> LSrt2Obj1Obj0ConsAssert.The<A, T2, T1> assertSrt2Obj1Obj0Cons(LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1> func) {
		return new LSrt2Obj1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> assertObjBoolCons(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjBoolConsumer.LBoolObjCons<T>, T> LBoolObjConsAssert.The<A, T> assertBoolObjCons(LObjBoolConsumer.LBoolObjCons<T> func) {
		return new LBoolObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> assertObjByteCons(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjByteConsumer.LByteObjCons<T>, T> LByteObjConsAssert.The<A, T> assertByteObjCons(LObjByteConsumer.LByteObjCons<T> func) {
		return new LByteObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> assertObjCharCons(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjCharConsumer.LCharObjCons<T>, T> LCharObjConsAssert.The<A, T> assertCharObjCons(LObjCharConsumer.LCharObjCons<T> func) {
		return new LCharObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjDblConsumer<T>, T> LObjDblConsumerAssert.The<A, T> assertObjDblCons(LObjDblConsumer<T> func) {
		return new LObjDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjDblConsumer.LDblObjCons<T>, T> LDblObjConsAssert.The<A, T> assertDblObjCons(LObjDblConsumer.LDblObjCons<T> func) {
		return new LDblObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjFltConsumer<T>, T> LObjFltConsumerAssert.The<A, T> assertObjFltCons(LObjFltConsumer<T> func) {
		return new LObjFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjFltConsumer.LFltObjCons<T>, T> LFltObjConsAssert.The<A, T> assertFltObjCons(LObjFltConsumer.LFltObjCons<T> func) {
		return new LFltObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> assertObjIntCons(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjIntConsumer.LIntObjCons<T>, T> LIntObjConsAssert.The<A, T> assertIntObjCons(LObjIntConsumer.LIntObjCons<T> func) {
		return new LIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> assertObjLongCons(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjLongConsumer.LLongObjCons<T>, T> LLongObjConsAssert.The<A, T> assertLongObjCons(LObjLongConsumer.LLongObjCons<T> func) {
		return new LLongObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LObjSrtConsumer<T>, T> LObjSrtConsumerAssert.The<A, T> assertObjSrtCons(LObjSrtConsumer<T> func) {
		return new LObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjSrtConsumer.LSrtObjCons<T>, T> LSrtObjConsAssert.The<A, T> assertSrtObjCons(LObjSrtConsumer.LSrtObjCons<T> func) {
		return new LSrtObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer<T>, T> LTieBoolConsumerAssert.The<A, T> assertTieBoolCons(LTieBoolConsumer<T> func) {
		return new LTieBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LObjBoolIntCons<T>, T> LObjBoolIntConsAssert.The<A, T> assertObjBoolIntCons(LTieBoolConsumer.LObjBoolIntCons<T> func) {
		return new LObjBoolIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LIntObjBoolCons<T>, T> LIntObjBoolConsAssert.The<A, T> assertIntObjBoolCons(LTieBoolConsumer.LIntObjBoolCons<T> func) {
		return new LIntObjBoolConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LIntBoolObjCons<T>, T> LIntBoolObjConsAssert.The<A, T> assertIntBoolObjCons(LTieBoolConsumer.LIntBoolObjCons<T> func) {
		return new LIntBoolObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LBoolObjIntCons<T>, T> LBoolObjIntConsAssert.The<A, T> assertBoolObjIntCons(LTieBoolConsumer.LBoolObjIntCons<T> func) {
		return new LBoolObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer.LBoolIntObjCons<T>, T> LBoolIntObjConsAssert.The<A, T> assertBoolIntObjCons(LTieBoolConsumer.LBoolIntObjCons<T> func) {
		return new LBoolIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer<T>, T> LTieByteConsumerAssert.The<A, T> assertTieByteCons(LTieByteConsumer<T> func) {
		return new LTieByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LObjByteIntCons<T>, T> LObjByteIntConsAssert.The<A, T> assertObjByteIntCons(LTieByteConsumer.LObjByteIntCons<T> func) {
		return new LObjByteIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LIntObjByteCons<T>, T> LIntObjByteConsAssert.The<A, T> assertIntObjByteCons(LTieByteConsumer.LIntObjByteCons<T> func) {
		return new LIntObjByteConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LIntByteObjCons<T>, T> LIntByteObjConsAssert.The<A, T> assertIntByteObjCons(LTieByteConsumer.LIntByteObjCons<T> func) {
		return new LIntByteObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LByteObjIntCons<T>, T> LByteObjIntConsAssert.The<A, T> assertByteObjIntCons(LTieByteConsumer.LByteObjIntCons<T> func) {
		return new LByteObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer.LByteIntObjCons<T>, T> LByteIntObjConsAssert.The<A, T> assertByteIntObjCons(LTieByteConsumer.LByteIntObjCons<T> func) {
		return new LByteIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer<T>, T> LTieCharConsumerAssert.The<A, T> assertTieCharCons(LTieCharConsumer<T> func) {
		return new LTieCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LObjCharIntCons<T>, T> LObjCharIntConsAssert.The<A, T> assertObjCharIntCons(LTieCharConsumer.LObjCharIntCons<T> func) {
		return new LObjCharIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LIntObjCharCons<T>, T> LIntObjCharConsAssert.The<A, T> assertIntObjCharCons(LTieCharConsumer.LIntObjCharCons<T> func) {
		return new LIntObjCharConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LIntCharObjCons<T>, T> LIntCharObjConsAssert.The<A, T> assertIntCharObjCons(LTieCharConsumer.LIntCharObjCons<T> func) {
		return new LIntCharObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LCharObjIntCons<T>, T> LCharObjIntConsAssert.The<A, T> assertCharObjIntCons(LTieCharConsumer.LCharObjIntCons<T> func) {
		return new LCharObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer.LCharIntObjCons<T>, T> LCharIntObjConsAssert.The<A, T> assertCharIntObjCons(LTieCharConsumer.LCharIntObjCons<T> func) {
		return new LCharIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer<T1, T2>, T1, T2> LTieConsumerAssert.The<A, T1, T2> assertTieCons(LTieConsumer<T1, T2> func) {
		return new LTieConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer.LInt1BiObj2Cons<T1, T2>, T1, T2> LInt1BiObj2ConsAssert.The<A, T1, T2> assertInt1BiObj2Cons(LTieConsumer.LInt1BiObj2Cons<T1, T2> func) {
		return new LInt1BiObj2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer.LInt1Obj2Obj0Cons<T2, T1>, T2, T1> LInt1Obj2Obj0ConsAssert.The<A, T2, T1> assertInt1Obj2Obj0Cons(LTieConsumer.LInt1Obj2Obj0Cons<T2, T1> func) {
		return new LInt1Obj2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer.LObj2Int1Obj0Cons<T2, T1>, T2, T1> LObj2Int1Obj0ConsAssert.The<A, T2, T1> assertObj2Int1Obj0Cons(LTieConsumer.LObj2Int1Obj0Cons<T2, T1> func) {
		return new LObj2Int1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer<T>, T> LTieDblConsumerAssert.The<A, T> assertTieDblCons(LTieDblConsumer<T> func) {
		return new LTieDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LObjDblIntCons<T>, T> LObjDblIntConsAssert.The<A, T> assertObjDblIntCons(LTieDblConsumer.LObjDblIntCons<T> func) {
		return new LObjDblIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LIntObjDblCons<T>, T> LIntObjDblConsAssert.The<A, T> assertIntObjDblCons(LTieDblConsumer.LIntObjDblCons<T> func) {
		return new LIntObjDblConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LIntDblObjCons<T>, T> LIntDblObjConsAssert.The<A, T> assertIntDblObjCons(LTieDblConsumer.LIntDblObjCons<T> func) {
		return new LIntDblObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LDblObjIntCons<T>, T> LDblObjIntConsAssert.The<A, T> assertDblObjIntCons(LTieDblConsumer.LDblObjIntCons<T> func) {
		return new LDblObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer.LDblIntObjCons<T>, T> LDblIntObjConsAssert.The<A, T> assertDblIntObjCons(LTieDblConsumer.LDblIntObjCons<T> func) {
		return new LDblIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer<T>, T> LTieFltConsumerAssert.The<A, T> assertTieFltCons(LTieFltConsumer<T> func) {
		return new LTieFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LObjFltIntCons<T>, T> LObjFltIntConsAssert.The<A, T> assertObjFltIntCons(LTieFltConsumer.LObjFltIntCons<T> func) {
		return new LObjFltIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LIntObjFltCons<T>, T> LIntObjFltConsAssert.The<A, T> assertIntObjFltCons(LTieFltConsumer.LIntObjFltCons<T> func) {
		return new LIntObjFltConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LIntFltObjCons<T>, T> LIntFltObjConsAssert.The<A, T> assertIntFltObjCons(LTieFltConsumer.LIntFltObjCons<T> func) {
		return new LIntFltObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LFltObjIntCons<T>, T> LFltObjIntConsAssert.The<A, T> assertFltObjIntCons(LTieFltConsumer.LFltObjIntCons<T> func) {
		return new LFltObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer.LFltIntObjCons<T>, T> LFltIntObjConsAssert.The<A, T> assertFltIntObjCons(LTieFltConsumer.LFltIntObjCons<T> func) {
		return new LFltIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer<T>, T> LTieIntConsumerAssert.The<A, T> assertTieIntCons(LTieIntConsumer<T> func) {
		return new LTieIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LObj0Int2Int1Cons<T>, T> LObj0Int2Int1ConsAssert.The<A, T> assertObj0Int2Int1Cons(LTieIntConsumer.LObj0Int2Int1Cons<T> func) {
		return new LObj0Int2Int1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LInt1Obj0Int2Cons<T>, T> LInt1Obj0Int2ConsAssert.The<A, T> assertInt1Obj0Int2Cons(LTieIntConsumer.LInt1Obj0Int2Cons<T> func) {
		return new LInt1Obj0Int2ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LInt1Int2Obj0Cons<T>, T> LInt1Int2Obj0ConsAssert.The<A, T> assertInt1Int2Obj0Cons(LTieIntConsumer.LInt1Int2Obj0Cons<T> func) {
		return new LInt1Int2Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LInt2Obj0Int1Cons<T>, T> LInt2Obj0Int1ConsAssert.The<A, T> assertInt2Obj0Int1Cons(LTieIntConsumer.LInt2Obj0Int1Cons<T> func) {
		return new LInt2Obj0Int1ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer.LBiInt1Obj0Cons<T>, T> LBiInt1Obj0ConsAssert.The<A, T> assertBiInt1Obj0Cons(LTieIntConsumer.LBiInt1Obj0Cons<T> func) {
		return new LBiInt1Obj0ConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer<T>, T> LTieLongConsumerAssert.The<A, T> assertTieLongCons(LTieLongConsumer<T> func) {
		return new LTieLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LObjLongIntCons<T>, T> LObjLongIntConsAssert.The<A, T> assertObjLongIntCons(LTieLongConsumer.LObjLongIntCons<T> func) {
		return new LObjLongIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LIntObjLongCons<T>, T> LIntObjLongConsAssert.The<A, T> assertIntObjLongCons(LTieLongConsumer.LIntObjLongCons<T> func) {
		return new LIntObjLongConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LIntLongObjCons<T>, T> LIntLongObjConsAssert.The<A, T> assertIntLongObjCons(LTieLongConsumer.LIntLongObjCons<T> func) {
		return new LIntLongObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LLongObjIntCons<T>, T> LLongObjIntConsAssert.The<A, T> assertLongObjIntCons(LTieLongConsumer.LLongObjIntCons<T> func) {
		return new LLongObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer.LLongIntObjCons<T>, T> LLongIntObjConsAssert.The<A, T> assertLongIntObjCons(LTieLongConsumer.LLongIntObjCons<T> func) {
		return new LLongIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer<T>, T> LTieSrtConsumerAssert.The<A, T> assertTieSrtCons(LTieSrtConsumer<T> func) {
		return new LTieSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LObjSrtIntCons<T>, T> LObjSrtIntConsAssert.The<A, T> assertObjSrtIntCons(LTieSrtConsumer.LObjSrtIntCons<T> func) {
		return new LObjSrtIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LIntObjSrtCons<T>, T> LIntObjSrtConsAssert.The<A, T> assertIntObjSrtCons(LTieSrtConsumer.LIntObjSrtCons<T> func) {
		return new LIntObjSrtConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LIntSrtObjCons<T>, T> LIntSrtObjConsAssert.The<A, T> assertIntSrtObjCons(LTieSrtConsumer.LIntSrtObjCons<T> func) {
		return new LIntSrtObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LSrtObjIntCons<T>, T> LSrtObjIntConsAssert.The<A, T> assertSrtObjIntCons(LTieSrtConsumer.LSrtObjIntCons<T> func) {
		return new LSrtObjIntConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer.LSrtIntObjCons<T>, T> LSrtIntObjConsAssert.The<A, T> assertSrtIntObjCons(LTieSrtConsumer.LSrtIntObjCons<T> func) {
		return new LSrtIntObjConsAssert.The(func);
	}

	@Nonnull
	default <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> assertTriBoolCons(LTriBoolConsumer func) {
		return new LTriBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriByteConsumer> LTriByteConsumerAssert.The<A> assertTriByteCons(LTriByteConsumer func) {
		return new LTriByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriCharConsumer> LTriCharConsumerAssert.The<A> assertTriCharCons(LTriCharConsumer func) {
		return new LTriCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriDblConsumer> LTriDblConsumerAssert.The<A> assertTriDblCons(LTriDblConsumer func) {
		return new LTriDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriFltConsumer> LTriFltConsumerAssert.The<A> assertTriFltCons(LTriFltConsumer func) {
		return new LTriFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriIntConsumer> LTriIntConsumerAssert.The<A> assertTriIntCons(LTriIntConsumer func) {
		return new LTriIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriLongConsumer> LTriLongConsumerAssert.The<A> assertTriLongCons(LTriLongConsumer func) {
		return new LTriLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriSrtConsumer> LTriSrtConsumerAssert.The<A> assertTriSrtCons(LTriSrtConsumer func) {
		return new LTriSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBinaryOperator<T>, T> LBinaryOperatorAssert.The<A, ? extends OS, T> assertBinaryOp(LBinaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteBinaryOperator> LByteBinaryOperatorAssert.The<A, ? extends AbstractByteAssert> assertByteBinaryOp(LByteBinaryOperator func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharBinaryOperator> LCharBinaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> assertCharBinaryOp(LCharBinaryOperator func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblBinaryOperator> LDblBinaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertDblBinaryOp(LDblBinaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LDblBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltBinaryOperator> LFltBinaryOperatorAssert.The<A, ? extends AbstractFloatAssert> assertFltBinaryOp(LFltBinaryOperator func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LFltBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntBinaryOperator> LIntBinaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertIntBinaryOp(LIntBinaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalBinaryOperator> LLogicalBinaryOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertLogicalBinaryOp(LLogicalBinaryOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongBinaryOperator> LLongBinaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertLongBinaryOp(LLongBinaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtBinaryOperator> LSrtBinaryOperatorAssert.The<A, ? extends AbstractShortAssert> assertSrtBinaryOp(LSrtBinaryOperator func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LSrtBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalTernaryOperator> LLogicalTernaryOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertLogicalTernaryOp(LLogicalTernaryOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTernaryOperator<T>, T> LTernaryOperatorAssert.The<A, ? extends OS, T> assertTernaryOp(LTernaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteUnaryOperator> LByteUnaryOperatorAssert.The<A, ? extends AbstractByteAssert> assertByteUnaryOp(LByteUnaryOperator func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharUnaryOperator> LCharUnaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> assertCharUnaryOp(LCharUnaryOperator func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblUnaryOperator> LDblUnaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertDblUnaryOp(LDblUnaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LDblUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltUnaryOperator> LFltUnaryOperatorAssert.The<A, ? extends AbstractFloatAssert> assertFltUnaryOp(LFltUnaryOperator func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LFltUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntUnaryOperator> LIntUnaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertIntUnaryOp(LIntUnaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalOperator> LLogicalOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertLogicalOp(LLogicalOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongUnaryOperator> LLongUnaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertLongUnaryOp(LLongUnaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtUnaryOperator> LSrtUnaryOperatorAssert.The<A, ? extends AbstractShortAssert> assertSrtUnaryOp(LSrtUnaryOperator func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LSrtUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LUnaryOperator<T>, T> LUnaryOperatorAssert.The<A, ? extends OS, T> assertUnaryOp(LUnaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunction<T1, T2, R>, T1, T2, R> LBiFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiFunc(LBiFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunction.LObj1Obj0Func<T2, T1, R>, T2, T1, R> LObj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Func(LBiFunction.LObj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFunction<T, R>, T, R> LFunctionAssert.The<A, ? extends OS, T, R> assertFunc(LFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LQuadFunction<T1, T2, T3, T4, R>, T1, T2, T3, T4, R> LQuadFunctionAssert.The<A, ? extends OS, T1, T2, T3, T4, R> assertQuadFunc(LQuadFunction<T1, T2, T3, T4, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LQuadFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LQuintFunction<T1, T2, T3, T4, T5, R>, T1, T2, T3, T4, T5, R> LQuintFunctionAssert.The<A, ? extends OS, T1, T2, T3, T4, T5, R> assertQuintFunc(LQuintFunction<T1, T2, T3, T4, T5, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LQuintFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction<T1, T2, T3, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, ? extends OS, T1, T2, T3, R> assertTriFunc(LTriFunction<T1, T2, T3, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTriFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R>, T1, T3, T2, R> LObj0Obj2Obj1FuncAssert.The<A, ? extends OS, T1, T3, T2, R> assertObj0Obj2Obj1Func(LTriFunction.LObj0Obj2Obj1Func<T1, T3, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Obj2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LObj1BiObj2Func<T2, T1, T3, R>, T2, T1, T3, R> LObj1BiObj2FuncAssert.The<A, ? extends OS, T2, T1, T3, R> assertObj1BiObj2Func(LTriFunction.LObj1BiObj2Func<T2, T1, T3, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1BiObj2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R>, T2, T3, T1, R> LObj1Obj2Obj0FuncAssert.The<A, ? extends OS, T2, T3, T1, R> assertObj1Obj2Obj0Func(LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R>, T3, T1, T2, R> LObj2Obj0Obj1FuncAssert.The<A, ? extends OS, T3, T1, T2, R> assertObj2Obj0Obj1Func(LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R>, T3, T2, T1, R> LBiObj1Obj0FuncAssert.The<A, ? extends OS, T3, T2, T1, R> assertBiObj1Obj0Func(LTriFunction.LBiObj1Obj0Func<T3, T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToByteFunction> LBoolToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertBoolToByteFunc(LBoolToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LBoolToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToCharFunction> LBoolToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertBoolToCharFunc(LBoolToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LBoolToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToDblFunction> LBoolToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertBoolToDblFunc(LBoolToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LBoolToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToFltFunction> LBoolToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> assertBoolToFltFunc(LBoolToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LBoolToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToIntFunction> LBoolToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertBoolToIntFunc(LBoolToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBoolToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToLongFunction> LBoolToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertBoolToLongFunc(LBoolToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LBoolToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToSrtFunction> LBoolToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> assertBoolToSrtFunc(LBoolToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LBoolToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToCharFunction> LByteToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertByteToCharFunc(LByteToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LByteToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToDblFunction> LByteToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertByteToDblFunc(LByteToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LByteToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToFltFunction> LByteToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> assertByteToFltFunc(LByteToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LByteToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToIntFunction> LByteToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertByteToIntFunc(LByteToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToLongFunction> LByteToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertByteToLongFunc(LByteToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LByteToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToSrtFunction> LByteToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> assertByteToSrtFunc(LByteToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LByteToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToByteFunction> LCharToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertCharToByteFunc(LCharToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LCharToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToDblFunction> LCharToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertCharToDblFunc(LCharToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LCharToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToFltFunction> LCharToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> assertCharToFltFunc(LCharToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LCharToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToIntFunction> LCharToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertCharToIntFunc(LCharToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToLongFunction> LCharToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertCharToLongFunc(LCharToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LCharToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToSrtFunction> LCharToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> assertCharToSrtFunc(LCharToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LCharToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToByteFunction> LDblToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertDblToByteFunc(LDblToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LDblToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToCharFunction> LDblToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertDblToCharFunc(LDblToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LDblToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToFltFunction> LDblToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> assertDblToFltFunc(LDblToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LDblToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToIntFunction> LDblToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertDblToIntFunc(LDblToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDblToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToLongFunction> LDblToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertDblToLongFunc(LDblToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LDblToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblToSrtFunction> LDblToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> assertDblToSrtFunc(LDblToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LDblToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToByteFunction> LFltToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertFltToByteFunc(LFltToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LFltToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToCharFunction> LFltToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertFltToCharFunc(LFltToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LFltToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToDblFunction> LFltToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertFltToDblFunc(LFltToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LFltToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToIntFunction> LFltToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertFltToIntFunc(LFltToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFltToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToLongFunction> LFltToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertFltToLongFunc(LFltToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LFltToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltToSrtFunction> LFltToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> assertFltToSrtFunc(LFltToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LFltToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToByteFunction> LIntToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertIntToByteFunc(LIntToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LIntToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToCharFunction> LIntToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertIntToCharFunc(LIntToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LIntToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToDblFunction> LIntToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertIntToDblFunc(LIntToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LIntToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToFltFunction> LIntToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> assertIntToFltFunc(LIntToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LIntToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToLongFunction> LIntToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertIntToLongFunc(LIntToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LIntToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToSrtFunction> LIntToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> assertIntToSrtFunc(LIntToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LIntToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToByteFunction> LLongToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertLongToByteFunc(LLongToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LLongToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToCharFunction> LLongToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertLongToCharFunc(LLongToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LLongToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToDblFunction> LLongToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertLongToDblFunc(LLongToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LLongToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToFltFunction> LLongToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> assertLongToFltFunc(LLongToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LLongToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToIntFunction> LLongToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertLongToIntFunc(LLongToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToSrtFunction> LLongToSrtFunctionAssert.The<A, ? extends AbstractShortAssert> assertLongToSrtFunc(LLongToSrtFunction func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LLongToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToByteFunction> LSrtToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertSrtToByteFunc(LSrtToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LSrtToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToCharFunction> LSrtToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertSrtToCharFunc(LSrtToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LSrtToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToDblFunction> LSrtToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertSrtToDblFunc(LSrtToDblFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LSrtToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToFltFunction> LSrtToFltFunctionAssert.The<A, ? extends AbstractFloatAssert> assertSrtToFltFunc(LSrtToFltFunction func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LSrtToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToIntFunction> LSrtToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertSrtToIntFunc(LSrtToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LSrtToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtToLongFunction> LSrtToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertSrtToLongFunc(LSrtToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LSrtToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBoolFunction<R>, R> LBiBoolFunctionAssert.The<A, ? extends OS, R> assertBiBoolFunc(LBiBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBoolFunction.LBool1Bool0Func<R>, R> LBool1Bool0FuncAssert.The<A, ? extends OS, R> assertBool1Bool0Func(LBiBoolFunction.LBool1Bool0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBool1Bool0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiByteFunction<R>, R> LBiByteFunctionAssert.The<A, ? extends OS, R> assertBiByteFunc(LBiByteFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiByteFunction.LByte1Byte0Func<R>, R> LByte1Byte0FuncAssert.The<A, ? extends OS, R> assertByte1Byte0Func(LBiByteFunction.LByte1Byte0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByte1Byte0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunction<R>, R> LBiCharFunctionAssert.The<A, ? extends OS, R> assertBiCharFunc(LBiCharFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunction.LChar1Char0Func<R>, R> LChar1Char0FuncAssert.The<A, ? extends OS, R> assertChar1Char0Func(LBiCharFunction.LChar1Char0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LChar1Char0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblFunction<R>, R> LBiDblFunctionAssert.The<A, ? extends OS, R> assertBiDblFunc(LBiDblFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblFunction.LDbl1Dbl0Func<R>, R> LDbl1Dbl0FuncAssert.The<A, ? extends OS, R> assertDbl1Dbl0Func(LBiDblFunction.LDbl1Dbl0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDbl1Dbl0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltFunction<R>, R> LBiFltFunctionAssert.The<A, ? extends OS, R> assertBiFltFunc(LBiFltFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltFunction.LFlt1Flt0Func<R>, R> LFlt1Flt0FuncAssert.The<A, ? extends OS, R> assertFlt1Flt0Func(LBiFltFunction.LFlt1Flt0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFlt1Flt0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunction<R>, R> LBiIntFunctionAssert.The<A, ? extends OS, R> assertBiIntFunc(LBiIntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunction.LInt1Int0Func<R>, R> LInt1Int0FuncAssert.The<A, ? extends OS, R> assertInt1Int0Func(LBiIntFunction.LInt1Int0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LInt1Int0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunction<R>, R> LBiLongFunctionAssert.The<A, ? extends OS, R> assertBiLongFunc(LBiLongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunction.LLong1Long0Func<R>, R> LLong1Long0FuncAssert.The<A, ? extends OS, R> assertLong1Long0Func(LBiLongFunction.LLong1Long0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLong1Long0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction<T1, T2, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Bool2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertObj0Bool2Obj1Func(LBiObjBoolFunction.LObj0Bool2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Bool2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Bool2FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Bool2Func(LBiObjBoolFunction.LObj1Obj0Bool2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0Bool2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Bool2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Bool2Obj0Func(LBiObjBoolFunction.LObj1Bool2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Bool2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LBool2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertBool2Obj0Obj1Func(LBiObjBoolFunction.LBool2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBool2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LBool2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertBool2Obj1Obj0Func(LBiObjBoolFunction.LBool2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBool2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction<T1, T2, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjByteFunc(LBiObjByteFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Byte2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertObj0Byte2Obj1Func(LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Byte2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Byte2FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Byte2Func(LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0Byte2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Byte2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Byte2Obj0Func(LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Byte2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LByte2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertByte2Obj0Obj1Func(LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByte2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LByte2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertByte2Obj1Obj0Func(LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByte2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction<T1, T2, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Char2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertObj0Char2Obj1Func(LBiObjCharFunction.LObj0Char2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Char2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Char2FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Char2Func(LBiObjCharFunction.LObj1Obj0Char2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0Char2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Char2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Char2Obj0Func(LBiObjCharFunction.LObj1Char2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Char2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LChar2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertChar2Obj0Obj1Func(LBiObjCharFunction.LChar2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LChar2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LChar2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertChar2Obj1Obj0Func(LBiObjCharFunction.LChar2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LChar2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction<T1, T2, R>, T1, T2, R> LBiObjDblFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjDblFunc(LBiObjDblFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Dbl2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertObj0Dbl2Obj1Func(LBiObjDblFunction.LObj0Dbl2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Dbl2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Dbl2FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Dbl2Func(LBiObjDblFunction.LObj1Obj0Dbl2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0Dbl2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Dbl2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Dbl2Obj0Func(LBiObjDblFunction.LObj1Dbl2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Dbl2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LDbl2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertDbl2Obj0Obj1Func(LBiObjDblFunction.LDbl2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDbl2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LDbl2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertDbl2Obj1Obj0Func(LBiObjDblFunction.LDbl2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDbl2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction<T1, T2, R>, T1, T2, R> LBiObjFltFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Flt2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertObj0Flt2Obj1Func(LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Flt2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Flt2FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Flt2Func(LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0Flt2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Flt2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Flt2Obj0Func(LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Flt2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LFlt2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertFlt2Obj0Obj1Func(LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFlt2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LFlt2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertFlt2Obj1Obj0Func(LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFlt2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction<T1, T2, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjIntFunc(LBiObjIntFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Int2FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Int2Func(LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0Int2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LInt2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertInt2Obj0Obj1Func(LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LInt2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LInt2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertInt2Obj1Obj0Func(LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LInt2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction<T1, T2, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Long2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertObj0Long2Obj1Func(LBiObjLongFunction.LObj0Long2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Long2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Long2FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Long2Func(LBiObjLongFunction.LObj1Obj0Long2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0Long2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Long2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Long2Obj0Func(LBiObjLongFunction.LObj1Long2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Long2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LLong2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertLong2Obj0Obj1Func(LBiObjLongFunction.LLong2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLong2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LLong2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertLong2Obj1Obj0Func(LBiObjLongFunction.LLong2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLong2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction<T1, T2, R>, T1, T2, R> LBiObjSrtFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjSrtFunc(LBiObjSrtFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R>, T1, T2, R> LObj0Srt2Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertObj0Srt2Obj1Func(LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Srt2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R>, T2, T1, R> LObj1Obj0Srt2FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Obj0Srt2Func(LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Obj0Srt2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R>, T2, T1, R> LObj1Srt2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj1Srt2Obj0Func(LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj1Srt2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R>, T1, T2, R> LSrt2Obj0Obj1FuncAssert.The<A, ? extends OS, T1, T2, R> assertSrt2Obj0Obj1Func(LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSrt2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R>, T2, T1, R> LSrt2Obj1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertSrt2Obj1Obj0Func(LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSrt2Obj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtFunction<R>, R> LBiSrtFunctionAssert.The<A, ? extends OS, R> assertBiSrtFunc(LBiSrtFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtFunction.LSrt1Srt0Func<R>, R> LSrt1Srt0FuncAssert.The<A, ? extends OS, R> assertSrt1Srt0Func(LBiSrtFunction.LSrt1Srt0Func<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSrt1Srt0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolFunction<R>, R> LBoolFunctionAssert.The<A, ? extends OS, R> assertBoolFunc(LBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteFunction<R>, R> LByteFunctionAssert.The<A, ? extends OS, R> assertByteFunc(LByteFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharFunction<R>, R> LCharFunctionAssert.The<A, ? extends OS, R> assertCharFunc(LCharFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblFunction<R>, R> LDblFunctionAssert.The<A, ? extends OS, R> assertDblFunc(LDblFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltFunction<R>, R> LFltFunctionAssert.The<A, ? extends OS, R> assertFltFunc(LFltFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntFunction<R>, R> LIntFunctionAssert.The<A, ? extends OS, R> assertIntFunc(LIntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongFunction<R>, R> LLongFunctionAssert.The<A, ? extends OS, R> assertLongFunc(LLongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction<T, R>, T, R> LObjBiIntFunctionAssert.The<A, ? extends OS, T, R> assertObjBiIntFunc(LObjBiIntFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBiIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LObj0Int2Int1Func<T, R>, T, R> LObj0Int2Int1FuncAssert.The<A, ? extends OS, T, R> assertObj0Int2Int1Func(LObjBiIntFunction.LObj0Int2Int1Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj0Int2Int1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LInt1Obj0Int2Func<T, R>, T, R> LInt1Obj0Int2FuncAssert.The<A, ? extends OS, T, R> assertInt1Obj0Int2Func(LObjBiIntFunction.LInt1Obj0Int2Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LInt1Obj0Int2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LInt1Int2Obj0Func<T, R>, T, R> LInt1Int2Obj0FuncAssert.The<A, ? extends OS, T, R> assertInt1Int2Obj0Func(LObjBiIntFunction.LInt1Int2Obj0Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LInt1Int2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LInt2Obj0Int1Func<T, R>, T, R> LInt2Obj0Int1FuncAssert.The<A, ? extends OS, T, R> assertInt2Obj0Int1Func(LObjBiIntFunction.LInt2Obj0Int1Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LInt2Obj0Int1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntFunction.LBiInt1Obj0Func<T, R>, T, R> LBiInt1Obj0FuncAssert.The<A, ? extends OS, T, R> assertBiInt1Obj0Func(LObjBiIntFunction.LBiInt1Obj0Func<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiInt1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolFunction<T, R>, T, R> LObjBoolFunctionAssert.The<A, ? extends OS, T, R> assertObjBoolFunc(LObjBoolFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolFunction.LBoolObjFunc<T, R>, T, R> LBoolObjFuncAssert.The<A, ? extends OS, T, R> assertBoolObjFunc(LObjBoolFunction.LBoolObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBoolObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunction<T, R>, T, R> LObjByteFunctionAssert.The<A, ? extends OS, T, R> assertObjByteFunc(LObjByteFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunction.LByteObjFunc<T, R>, T, R> LByteObjFuncAssert.The<A, ? extends OS, T, R> assertByteObjFunc(LObjByteFunction.LByteObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByteObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunction<T, R>, T, R> LObjCharFunctionAssert.The<A, ? extends OS, T, R> assertObjCharFunc(LObjCharFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunction.LCharObjFunc<T, R>, T, R> LCharObjFuncAssert.The<A, ? extends OS, T, R> assertCharObjFunc(LObjCharFunction.LCharObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LCharObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblFunction<T, R>, T, R> LObjDblFunctionAssert.The<A, ? extends OS, T, R> assertObjDblFunc(LObjDblFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblFunction.LDblObjFunc<T, R>, T, R> LDblObjFuncAssert.The<A, ? extends OS, T, R> assertDblObjFunc(LObjDblFunction.LDblObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDblObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltFunction<T, R>, T, R> LObjFltFunctionAssert.The<A, ? extends OS, T, R> assertObjFltFunc(LObjFltFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltFunction.LFltObjFunc<T, R>, T, R> LFltObjFuncAssert.The<A, ? extends OS, T, R> assertFltObjFunc(LObjFltFunction.LFltObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFltObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction<T, R>, T, R> LObjIntBoolFunctionAssert.The<A, ? extends OS, T, R> assertObjIntBoolFunc(LObjIntBoolFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LObjBoolIntFunc<T, R>, T, R> LObjBoolIntFuncAssert.The<A, ? extends OS, T, R> assertObjBoolIntFunc(LObjIntBoolFunction.LObjBoolIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBoolIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LIntObjBoolFunc<T, R>, T, R> LIntObjBoolFuncAssert.The<A, ? extends OS, T, R> assertIntObjBoolFunc(LObjIntBoolFunction.LIntObjBoolFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntObjBoolFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LIntBoolObjFunc<T, R>, T, R> LIntBoolObjFuncAssert.The<A, ? extends OS, T, R> assertIntBoolObjFunc(LObjIntBoolFunction.LIntBoolObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntBoolObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LBoolObjIntFunc<T, R>, T, R> LBoolObjIntFuncAssert.The<A, ? extends OS, T, R> assertBoolObjIntFunc(LObjIntBoolFunction.LBoolObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBoolObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction.LBoolIntObjFunc<T, R>, T, R> LBoolIntObjFuncAssert.The<A, ? extends OS, T, R> assertBoolIntObjFunc(LObjIntBoolFunction.LBoolIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBoolIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction<T, R>, T, R> LObjIntByteFunctionAssert.The<A, ? extends OS, T, R> assertObjIntByteFunc(LObjIntByteFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LObjByteIntFunc<T, R>, T, R> LObjByteIntFuncAssert.The<A, ? extends OS, T, R> assertObjByteIntFunc(LObjIntByteFunction.LObjByteIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LIntObjByteFunc<T, R>, T, R> LIntObjByteFuncAssert.The<A, ? extends OS, T, R> assertIntObjByteFunc(LObjIntByteFunction.LIntObjByteFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntObjByteFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LIntByteObjFunc<T, R>, T, R> LIntByteObjFuncAssert.The<A, ? extends OS, T, R> assertIntByteObjFunc(LObjIntByteFunction.LIntByteObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntByteObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LByteObjIntFunc<T, R>, T, R> LByteObjIntFuncAssert.The<A, ? extends OS, T, R> assertByteObjIntFunc(LObjIntByteFunction.LByteObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByteObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction.LByteIntObjFunc<T, R>, T, R> LByteIntObjFuncAssert.The<A, ? extends OS, T, R> assertByteIntObjFunc(LObjIntByteFunction.LByteIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByteIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction<T, R>, T, R> LObjIntCharFunctionAssert.The<A, ? extends OS, T, R> assertObjIntCharFunc(LObjIntCharFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LObjCharIntFunc<T, R>, T, R> LObjCharIntFuncAssert.The<A, ? extends OS, T, R> assertObjCharIntFunc(LObjIntCharFunction.LObjCharIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LIntObjCharFunc<T, R>, T, R> LIntObjCharFuncAssert.The<A, ? extends OS, T, R> assertIntObjCharFunc(LObjIntCharFunction.LIntObjCharFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntObjCharFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LIntCharObjFunc<T, R>, T, R> LIntCharObjFuncAssert.The<A, ? extends OS, T, R> assertIntCharObjFunc(LObjIntCharFunction.LIntCharObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntCharObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LCharObjIntFunc<T, R>, T, R> LCharObjIntFuncAssert.The<A, ? extends OS, T, R> assertCharObjIntFunc(LObjIntCharFunction.LCharObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LCharObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction.LCharIntObjFunc<T, R>, T, R> LCharIntObjFuncAssert.The<A, ? extends OS, T, R> assertCharIntObjFunc(LObjIntCharFunction.LCharIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LCharIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction<T, R>, T, R> LObjIntDblFunctionAssert.The<A, ? extends OS, T, R> assertObjIntDblFunc(LObjIntDblFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LObjDblIntFunc<T, R>, T, R> LObjDblIntFuncAssert.The<A, ? extends OS, T, R> assertObjDblIntFunc(LObjIntDblFunction.LObjDblIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDblIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LIntObjDblFunc<T, R>, T, R> LIntObjDblFuncAssert.The<A, ? extends OS, T, R> assertIntObjDblFunc(LObjIntDblFunction.LIntObjDblFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntObjDblFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LIntDblObjFunc<T, R>, T, R> LIntDblObjFuncAssert.The<A, ? extends OS, T, R> assertIntDblObjFunc(LObjIntDblFunction.LIntDblObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntDblObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LDblObjIntFunc<T, R>, T, R> LDblObjIntFuncAssert.The<A, ? extends OS, T, R> assertDblObjIntFunc(LObjIntDblFunction.LDblObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDblObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction.LDblIntObjFunc<T, R>, T, R> LDblIntObjFuncAssert.The<A, ? extends OS, T, R> assertDblIntObjFunc(LObjIntDblFunction.LDblIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDblIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction<T, R>, T, R> LObjIntFltFunctionAssert.The<A, ? extends OS, T, R> assertObjIntFltFunc(LObjIntFltFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LObjFltIntFunc<T, R>, T, R> LObjFltIntFuncAssert.The<A, ? extends OS, T, R> assertObjFltIntFunc(LObjIntFltFunction.LObjFltIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFltIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LIntObjFltFunc<T, R>, T, R> LIntObjFltFuncAssert.The<A, ? extends OS, T, R> assertIntObjFltFunc(LObjIntFltFunction.LIntObjFltFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntObjFltFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LIntFltObjFunc<T, R>, T, R> LIntFltObjFuncAssert.The<A, ? extends OS, T, R> assertIntFltObjFunc(LObjIntFltFunction.LIntFltObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntFltObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LFltObjIntFunc<T, R>, T, R> LFltObjIntFuncAssert.The<A, ? extends OS, T, R> assertFltObjIntFunc(LObjIntFltFunction.LFltObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFltObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction.LFltIntObjFunc<T, R>, T, R> LFltIntObjFuncAssert.The<A, ? extends OS, T, R> assertFltIntObjFunc(LObjIntFltFunction.LFltIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFltIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction<T, R>, T, R> LObjIntLongFunctionAssert.The<A, ? extends OS, T, R> assertObjIntLongFunc(LObjIntLongFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LObjLongIntFunc<T, R>, T, R> LObjLongIntFuncAssert.The<A, ? extends OS, T, R> assertObjLongIntFunc(LObjIntLongFunction.LObjLongIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LIntObjLongFunc<T, R>, T, R> LIntObjLongFuncAssert.The<A, ? extends OS, T, R> assertIntObjLongFunc(LObjIntLongFunction.LIntObjLongFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntObjLongFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LIntLongObjFunc<T, R>, T, R> LIntLongObjFuncAssert.The<A, ? extends OS, T, R> assertIntLongObjFunc(LObjIntLongFunction.LIntLongObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntLongObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LLongObjIntFunc<T, R>, T, R> LLongObjIntFuncAssert.The<A, ? extends OS, T, R> assertLongObjIntFunc(LObjIntLongFunction.LLongObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLongObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction.LLongIntObjFunc<T, R>, T, R> LLongIntObjFuncAssert.The<A, ? extends OS, T, R> assertLongIntObjFunc(LObjIntLongFunction.LLongIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLongIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction<T1, T2, R>, T1, T2, R> LObjIntObjFunctionAssert.The<A, ? extends OS, T1, T2, R> assertObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntObjFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R>, T1, T2, R> LInt1BiObj2FuncAssert.The<A, ? extends OS, T1, T2, R> assertInt1BiObj2Func(LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LInt1BiObj2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R>, T2, T1, R> LInt1Obj2Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertInt1Obj2Obj0Func(LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LInt1Obj2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R>, T2, T1, R> LObj2Int1Obj0FuncAssert.The<A, ? extends OS, T2, T1, R> assertObj2Int1Obj0Func(LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObj2Int1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction<T, R>, T, R> LObjIntSrtFunctionAssert.The<A, ? extends OS, T, R> assertObjIntSrtFunc(LObjIntSrtFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LObjSrtIntFunc<T, R>, T, R> LObjSrtIntFuncAssert.The<A, ? extends OS, T, R> assertObjSrtIntFunc(LObjIntSrtFunction.LObjSrtIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjSrtIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LIntObjSrtFunc<T, R>, T, R> LIntObjSrtFuncAssert.The<A, ? extends OS, T, R> assertIntObjSrtFunc(LObjIntSrtFunction.LIntObjSrtFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntObjSrtFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LIntSrtObjFunc<T, R>, T, R> LIntSrtObjFuncAssert.The<A, ? extends OS, T, R> assertIntSrtObjFunc(LObjIntSrtFunction.LIntSrtObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntSrtObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LSrtObjIntFunc<T, R>, T, R> LSrtObjIntFuncAssert.The<A, ? extends OS, T, R> assertSrtObjIntFunc(LObjIntSrtFunction.LSrtObjIntFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSrtObjIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction.LSrtIntObjFunc<T, R>, T, R> LSrtIntObjFuncAssert.The<A, ? extends OS, T, R> assertSrtIntObjFunc(LObjIntSrtFunction.LSrtIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSrtIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunction<T, R>, T, R> LObjLongFunctionAssert.The<A, ? extends OS, T, R> assertObjLongFunc(LObjLongFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunction.LLongObjFunc<T, R>, T, R> LLongObjFuncAssert.The<A, ? extends OS, T, R> assertLongObjFunc(LObjLongFunction.LLongObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLongObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtFunction<T, R>, T, R> LObjSrtFunctionAssert.The<A, ? extends OS, T, R> assertObjSrtFunc(LObjSrtFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtFunction.LSrtObjFunc<T, R>, T, R> LSrtObjFuncAssert.The<A, ? extends OS, T, R> assertSrtObjFunc(LObjSrtFunction.LSrtObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSrtObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiFunction<T, R>, T, R> LOiFunctionAssert.The<A, ? extends OS, T, R> assertOiFunc(LOiFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LOiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiFunction.LIntObjFunc<T, R>, T, R> LIntObjFuncAssert.The<A, ? extends OS, T, R> assertIntObjFunc(LOiFunction.LIntObjFunc<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntObjFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtFunction<R>, R> LSrtFunctionAssert.The<A, ? extends OS, R> assertSrtFunc(LSrtFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriBoolFunction<R>, R> LTriBoolFunctionAssert.The<A, ? extends OS, R> assertTriBoolFunc(LTriBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTriBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToByteFunction<T>, T> LOiToByteFunctionAssert.The<A, ? extends AbstractByteAssert, T> assertOiToByteFunc(LOiToByteFunction<T> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LOiToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToByteFunction.LIntObjToByteFunc<T>, T> LIntObjToByteFuncAssert.The<A, ? extends AbstractByteAssert, T> assertIntObjToByteFunc(LOiToByteFunction.LIntObjToByteFunc<T> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LIntObjToByteFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToCharFunction<T>, T> LOiToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert, T> assertOiToCharFunc(LOiToCharFunction<T> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LOiToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToCharFunction.LIntObjToCharFunc<T>, T> LIntObjToCharFuncAssert.The<A, ? extends AbstractCharacterAssert, T> assertIntObjToCharFunc(LOiToCharFunction.LIntObjToCharFunc<T> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LIntObjToCharFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToDblFunction<T>, T> LOiToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> assertOiToDblFunc(LOiToDblFunction<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LOiToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToDblFunction.LIntObjToDblFunc<T>, T> LIntObjToDblFuncAssert.The<A, ? extends AbstractDoubleAssert, T> assertIntObjToDblFunc(LOiToDblFunction.LIntObjToDblFunc<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LIntObjToDblFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToFltFunction<T>, T> LOiToFltFunctionAssert.The<A, ? extends AbstractFloatAssert, T> assertOiToFltFunc(LOiToFltFunction<T> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LOiToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToFltFunction.LIntObjToFltFunc<T>, T> LIntObjToFltFuncAssert.The<A, ? extends AbstractFloatAssert, T> assertIntObjToFltFunc(LOiToFltFunction.LIntObjToFltFunc<T> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LIntObjToFltFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToIntFunction<T>, T> LOiToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertOiToIntFunc(LOiToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LOiToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToIntFunction.LIntObjToIntFunc<T>, T> LIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntObjToIntFunc(LOiToIntFunction.LIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToLongFunction<T>, T> LOiToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> assertOiToLongFunc(LOiToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LOiToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToLongFunction.LIntObjToLongFunc<T>, T> LIntObjToLongFuncAssert.The<A, ? extends AbstractLongAssert, T> assertIntObjToLongFunc(LOiToLongFunction.LIntObjToLongFunc<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LIntObjToLongFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToSrtFunction<T>, T> LOiToSrtFunctionAssert.The<A, ? extends AbstractShortAssert, T> assertOiToSrtFunc(LOiToSrtFunction<T> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LOiToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToSrtFunction.LIntObjToSrtFunc<T>, T> LIntObjToSrtFuncAssert.The<A, ? extends AbstractShortAssert, T> assertIntObjToSrtFunc(LOiToSrtFunction.LIntObjToSrtFunc<T> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LIntObjToSrtFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction<T>, T> LTieBoolFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieBoolFunc(LTieBoolFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LObjBoolIntToIntFunc<T>, T> LObjBoolIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertObjBoolIntToIntFunc(LTieBoolFunction.LObjBoolIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjBoolIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LIntObjBoolToIntFunc<T>, T> LIntObjBoolToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntObjBoolToIntFunc(LTieBoolFunction.LIntObjBoolToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntObjBoolToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LIntBoolObjToIntFunc<T>, T> LIntBoolObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntBoolObjToIntFunc(LTieBoolFunction.LIntBoolObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntBoolObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LBoolObjIntToIntFunc<T>, T> LBoolObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertBoolObjIntToIntFunc(LTieBoolFunction.LBoolObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBoolObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction.LBoolIntObjToIntFunc<T>, T> LBoolIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertBoolIntObjToIntFunc(LTieBoolFunction.LBoolIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBoolIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction<T>, T> LTieByteFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieByteFunc(LTieByteFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LObjByteIntToIntFunc<T>, T> LObjByteIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertObjByteIntToIntFunc(LTieByteFunction.LObjByteIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjByteIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LIntObjByteToIntFunc<T>, T> LIntObjByteToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntObjByteToIntFunc(LTieByteFunction.LIntObjByteToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntObjByteToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LIntByteObjToIntFunc<T>, T> LIntByteObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntByteObjToIntFunc(LTieByteFunction.LIntByteObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntByteObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LByteObjIntToIntFunc<T>, T> LByteObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertByteObjIntToIntFunc(LTieByteFunction.LByteObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction.LByteIntObjToIntFunc<T>, T> LByteIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertByteIntObjToIntFunc(LTieByteFunction.LByteIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction<T>, T> LTieCharFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieCharFunc(LTieCharFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LObjCharIntToIntFunc<T>, T> LObjCharIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertObjCharIntToIntFunc(LTieCharFunction.LObjCharIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjCharIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LIntObjCharToIntFunc<T>, T> LIntObjCharToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntObjCharToIntFunc(LTieCharFunction.LIntObjCharToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntObjCharToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LIntCharObjToIntFunc<T>, T> LIntCharObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntCharObjToIntFunc(LTieCharFunction.LIntCharObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntCharObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LCharObjIntToIntFunc<T>, T> LCharObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertCharObjIntToIntFunc(LTieCharFunction.LCharObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction.LCharIntObjToIntFunc<T>, T> LCharIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertCharIntObjToIntFunc(LTieCharFunction.LCharIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction<T>, T> LTieDblFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieDblFunc(LTieDblFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LObjDblIntToIntFunc<T>, T> LObjDblIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertObjDblIntToIntFunc(LTieDblFunction.LObjDblIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjDblIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LIntObjDblToIntFunc<T>, T> LIntObjDblToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntObjDblToIntFunc(LTieDblFunction.LIntObjDblToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntObjDblToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LIntDblObjToIntFunc<T>, T> LIntDblObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntDblObjToIntFunc(LTieDblFunction.LIntDblObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntDblObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LDblObjIntToIntFunc<T>, T> LDblObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertDblObjIntToIntFunc(LTieDblFunction.LDblObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDblObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction.LDblIntObjToIntFunc<T>, T> LDblIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertDblIntObjToIntFunc(LTieDblFunction.LDblIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDblIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction<T>, T> LTieFltFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieFltFunc(LTieFltFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LObjFltIntToIntFunc<T>, T> LObjFltIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertObjFltIntToIntFunc(LTieFltFunction.LObjFltIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjFltIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LIntObjFltToIntFunc<T>, T> LIntObjFltToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntObjFltToIntFunc(LTieFltFunction.LIntObjFltToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntObjFltToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LIntFltObjToIntFunc<T>, T> LIntFltObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntFltObjToIntFunc(LTieFltFunction.LIntFltObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntFltObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LFltObjIntToIntFunc<T>, T> LFltObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertFltObjIntToIntFunc(LTieFltFunction.LFltObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFltObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction.LFltIntObjToIntFunc<T>, T> LFltIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertFltIntObjToIntFunc(LTieFltFunction.LFltIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFltIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction<T1, T2>, T1, T2> LTieFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertTieFunc(LTieFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2>, T1, T2> LObj0Obj2Int1ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertObj0Obj2Int1ToIntFunc(LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObj0Obj2Int1ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LInt1BiObj2ToIntFunc<T1, T2>, T1, T2> LInt1BiObj2ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertInt1BiObj2ToIntFunc(LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LInt1BiObj2ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1>, T2, T1> LInt1Obj2Obj0ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1> assertInt1Obj2Obj0ToIntFunc(LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LInt1Obj2Obj0ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1>, T2, T1> LObj2Obj0Int1ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1> assertObj2Obj0Int1ToIntFunc(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObj2Obj0Int1ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1>, T2, T1> LObj2Int1Obj0ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1> assertObj2Int1Obj0ToIntFunc(LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObj2Int1Obj0ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction<T>, T> LTieIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieIntFunc(LTieIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LObj0Int2Int1ToIntFunc<T>, T> LObj0Int2Int1ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertObj0Int2Int1ToIntFunc(LTieIntFunction.LObj0Int2Int1ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObj0Int2Int1ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LInt1Obj0Int2ToIntFunc<T>, T> LInt1Obj0Int2ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertInt1Obj0Int2ToIntFunc(LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LInt1Obj0Int2ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LInt1Int2Obj0ToIntFunc<T>, T> LInt1Int2Obj0ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertInt1Int2Obj0ToIntFunc(LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LInt1Int2Obj0ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LInt2Obj0Int1ToIntFunc<T>, T> LInt2Obj0Int1ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertInt2Obj0Int1ToIntFunc(LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LInt2Obj0Int1ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction.LBiInt1Obj0ToIntFunc<T>, T> LBiInt1Obj0ToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertBiInt1Obj0ToIntFunc(LTieIntFunction.LBiInt1Obj0ToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBiInt1Obj0ToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction<T>, T> LTieLongFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieLongFunc(LTieLongFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LObjLongIntToIntFunc<T>, T> LObjLongIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertObjLongIntToIntFunc(LTieLongFunction.LObjLongIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjLongIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LIntObjLongToIntFunc<T>, T> LIntObjLongToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntObjLongToIntFunc(LTieLongFunction.LIntObjLongToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntObjLongToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LIntLongObjToIntFunc<T>, T> LIntLongObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntLongObjToIntFunc(LTieLongFunction.LIntLongObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntLongObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LLongObjIntToIntFunc<T>, T> LLongObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertLongObjIntToIntFunc(LTieLongFunction.LLongObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction.LLongIntObjToIntFunc<T>, T> LLongIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertLongIntObjToIntFunc(LTieLongFunction.LLongIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction<T>, T> LTieSrtFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieSrtFunc(LTieSrtFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LObjSrtIntToIntFunc<T>, T> LObjSrtIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertObjSrtIntToIntFunc(LTieSrtFunction.LObjSrtIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjSrtIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LIntObjSrtToIntFunc<T>, T> LIntObjSrtToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntObjSrtToIntFunc(LTieSrtFunction.LIntObjSrtToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntObjSrtToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LIntSrtObjToIntFunc<T>, T> LIntSrtObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertIntSrtObjToIntFunc(LTieSrtFunction.LIntSrtObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntSrtObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LSrtObjIntToIntFunc<T>, T> LSrtObjIntToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertSrtObjIntToIntFunc(LTieSrtFunction.LSrtObjIntToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LSrtObjIntToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction.LSrtIntObjToIntFunc<T>, T> LSrtIntObjToIntFuncAssert.The<A, ? extends AbstractIntegerAssert, T> assertSrtIntObjToIntFunc(LTieSrtFunction.LSrtIntObjToIntFunc<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LSrtIntObjToIntFuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunction<T1, T2>, T1, T2> LToByteBiFunctionAssert.The<A, ? extends AbstractByteAssert, T1, T2> assertToByteBiFunc(LToByteBiFunction<T1, T2> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunction.LToByteObj1Obj0Func<T2, T1>, T2, T1> LToByteObj1Obj0FuncAssert.The<A, ? extends AbstractByteAssert, T2, T1> assertToByteObj1Obj0Func(LToByteBiFunction.LToByteObj1Obj0Func<T2, T1> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteFunction<T>, T> LToByteFunctionAssert.The<A, ? extends AbstractByteAssert, T> assertToByteFunc(LToByteFunction<T> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunction<T1, T2>, T1, T2> LToCharBiFunctionAssert.The<A, ? extends AbstractCharacterAssert, T1, T2> assertToCharBiFunc(LToCharBiFunction<T1, T2> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunction.LToCharObj1Obj0Func<T2, T1>, T2, T1> LToCharObj1Obj0FuncAssert.The<A, ? extends AbstractCharacterAssert, T2, T1> assertToCharObj1Obj0Func(LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharFunction<T>, T> LToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert, T> assertToCharFunc(LToCharFunction<T> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDblBiFunction<T1, T2>, T1, T2> LToDblBiFunctionAssert.The<A, ? extends AbstractDoubleAssert, T1, T2> assertToDblBiFunc(LToDblBiFunction<T1, T2> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LToDblBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDblBiFunction.LToDblObj1Obj0Func<T2, T1>, T2, T1> LToDblObj1Obj0FuncAssert.The<A, ? extends AbstractDoubleAssert, T2, T1> assertToDblObj1Obj0Func(LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LToDblObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDblFunction<T>, T> LToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> assertToDblFunc(LToDblFunction<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFltBiFunction<T1, T2>, T1, T2> LToFltBiFunctionAssert.The<A, ? extends AbstractFloatAssert, T1, T2> assertToFltBiFunc(LToFltBiFunction<T1, T2> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LToFltBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFltBiFunction.LToFltObj1Obj0Func<T2, T1>, T2, T1> LToFltObj1Obj0FuncAssert.The<A, ? extends AbstractFloatAssert, T2, T1> assertToFltObj1Obj0Func(LToFltBiFunction.LToFltObj1Obj0Func<T2, T1> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LToFltObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFltFunction<T>, T> LToFltFunctionAssert.The<A, ? extends AbstractFloatAssert, T> assertToFltFunc(LToFltFunction<T> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunction<T1, T2>, T1, T2> LToIntBiFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertToIntBiFunc(LToIntBiFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunction.LToIntObj1Obj0Func<T2, T1>, T2, T1> LToIntObj1Obj0FuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1> assertToIntObj1Obj0Func(LToIntBiFunction.LToIntObj1Obj0Func<T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntFunction<T>, T> LToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertToIntFunc(LToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction<T1, T2, T3>, T1, T2, T3> LToIntTriFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2, T3> assertToIntTriFunc(LToIntTriFunction<T1, T2, T3> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntTriFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2>, T1, T3, T2> LToIntObj0Obj2Obj1FuncAssert.The<A, ? extends AbstractIntegerAssert, T1, T3, T2> assertToIntObj0Obj2Obj1Func(LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntObj0Obj2Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3>, T2, T1, T3> LToIntObj1BiObj2FuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T1, T3> assertToIntObj1BiObj2Func(LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntObj1BiObj2FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1>, T2, T3, T1> LToIntObj1Obj2Obj0FuncAssert.The<A, ? extends AbstractIntegerAssert, T2, T3, T1> assertToIntObj1Obj2Obj0Func(LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntObj1Obj2Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2>, T3, T1, T2> LToIntObj2Obj0Obj1FuncAssert.The<A, ? extends AbstractIntegerAssert, T3, T1, T2> assertToIntObj2Obj0Obj1Func(LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntObj2Obj0Obj1FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1>, T3, T2, T1> LToIntBiObj1Obj0FuncAssert.The<A, ? extends AbstractIntegerAssert, T3, T2, T1> assertToIntBiObj1Obj0Func(LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntBiObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunction<T1, T2>, T1, T2> LToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> assertToLongBiFunc(LToLongBiFunction<T1, T2> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunction.LToLongObj1Obj0Func<T2, T1>, T2, T1> LToLongObj1Obj0FuncAssert.The<A, ? extends AbstractLongAssert, T2, T1> assertToLongObj1Obj0Func(LToLongBiFunction.LToLongObj1Obj0Func<T2, T1> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongFunction<T>, T> LToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> assertToLongFunc(LToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToSrtBiFunction<T1, T2>, T1, T2> LToSrtBiFunctionAssert.The<A, ? extends AbstractShortAssert, T1, T2> assertToSrtBiFunc(LToSrtBiFunction<T1, T2> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LToSrtBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1>, T2, T1> LToSrtObj1Obj0FuncAssert.The<A, ? extends AbstractShortAssert, T2, T1> assertToSrtObj1Obj0Func(LToSrtBiFunction.LToSrtObj1Obj0Func<T2, T1> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LToSrtObj1Obj0FuncAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToSrtFunction<T>, T> LToSrtFunctionAssert.The<A, ? extends AbstractShortAssert, T> assertToSrtFunc(LToSrtFunction<T> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicate> LBiBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiBytePred(LBiBytePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicate.LByte1Byte0Pred> LByte1Byte0PredAssert.The<A, ? extends AbstractBooleanAssert> assertByte1Byte0Pred(LBiBytePredicate.LByte1Byte0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LByte1Byte0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicate> LBiCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiCharPred(LBiCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicate.LChar1Char0Pred> LChar1Char0PredAssert.The<A, ? extends AbstractBooleanAssert> assertChar1Char0Pred(LBiCharPredicate.LChar1Char0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LChar1Char0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblPredicate> LBiDblPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiDblPred(LBiDblPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblPredicate.LDbl1Dbl0Pred> LDbl1Dbl0PredAssert.The<A, ? extends AbstractBooleanAssert> assertDbl1Dbl0Pred(LBiDblPredicate.LDbl1Dbl0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDbl1Dbl0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltPredicate> LBiFltPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiFltPred(LBiFltPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltPredicate.LFlt1Flt0Pred> LFlt1Flt0PredAssert.The<A, ? extends AbstractBooleanAssert> assertFlt1Flt0Pred(LBiFltPredicate.LFlt1Flt0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFlt1Flt0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicate> LBiIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiIntPred(LBiIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicate.LInt1Int0Pred> LInt1Int0PredAssert.The<A, ? extends AbstractBooleanAssert> assertInt1Int0Pred(LBiIntPredicate.LInt1Int0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LInt1Int0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicate> LBiLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiLongPred(LBiLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicate.LLong1Long0Pred> LLong1Long0PredAssert.The<A, ? extends AbstractBooleanAssert> assertLong1Long0Pred(LBiLongPredicate.LLong1Long0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLong1Long0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate<T1, T2>, T1, T2> LBiObjBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjBoolPred(LBiObjBoolPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2>, T1, T2> LObj0Bool2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObj0Bool2Obj1Pred(LBiObjBoolPredicate.LObj0Bool2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Bool2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1>, T2, T1> LObj1Obj0Bool2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Bool2Pred(LBiObjBoolPredicate.LObj1Obj0Bool2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0Bool2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1>, T2, T1> LObj1Bool2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Bool2Obj0Pred(LBiObjBoolPredicate.LObj1Bool2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Bool2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2>, T1, T2> LBool2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBool2Obj0Obj1Pred(LBiObjBoolPredicate.LBool2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBool2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1>, T2, T1> LBool2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertBool2Obj1Obj0Pred(LBiObjBoolPredicate.LBool2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBool2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate<T1, T2>, T1, T2> LBiObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjBytePred(LBiObjBytePredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2>, T1, T2> LObj0Byte2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObj0Byte2Obj1Pred(LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Byte2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1>, T2, T1> LObj1Obj0Byte2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Byte2Pred(LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0Byte2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1>, T2, T1> LObj1Byte2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Byte2Obj0Pred(LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Byte2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2>, T1, T2> LByte2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertByte2Obj0Obj1Pred(LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LByte2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1>, T2, T1> LByte2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertByte2Obj1Obj0Pred(LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LByte2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate<T1, T2>, T1, T2> LBiObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjCharPred(LBiObjCharPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2>, T1, T2> LObj0Char2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObj0Char2Obj1Pred(LBiObjCharPredicate.LObj0Char2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Char2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1>, T2, T1> LObj1Obj0Char2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Char2Pred(LBiObjCharPredicate.LObj1Obj0Char2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0Char2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1>, T2, T1> LObj1Char2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Char2Obj0Pred(LBiObjCharPredicate.LObj1Char2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Char2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2>, T1, T2> LChar2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertChar2Obj0Obj1Pred(LBiObjCharPredicate.LChar2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LChar2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1>, T2, T1> LChar2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertChar2Obj1Obj0Pred(LBiObjCharPredicate.LChar2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LChar2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate<T1, T2>, T1, T2> LBiObjDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjDblPred(LBiObjDblPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2>, T1, T2> LObj0Dbl2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObj0Dbl2Obj1Pred(LBiObjDblPredicate.LObj0Dbl2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Dbl2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1>, T2, T1> LObj1Obj0Dbl2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Dbl2Pred(LBiObjDblPredicate.LObj1Obj0Dbl2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0Dbl2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1>, T2, T1> LObj1Dbl2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Dbl2Obj0Pred(LBiObjDblPredicate.LObj1Dbl2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Dbl2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2>, T1, T2> LDbl2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertDbl2Obj0Obj1Pred(LBiObjDblPredicate.LDbl2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDbl2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1>, T2, T1> LDbl2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertDbl2Obj1Obj0Pred(LBiObjDblPredicate.LDbl2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDbl2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate<T1, T2>, T1, T2> LBiObjFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjFltPred(LBiObjFltPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2>, T1, T2> LObj0Flt2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObj0Flt2Obj1Pred(LBiObjFltPredicate.LObj0Flt2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Flt2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1>, T2, T1> LObj1Obj0Flt2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Flt2Pred(LBiObjFltPredicate.LObj1Obj0Flt2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0Flt2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1>, T2, T1> LObj1Flt2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Flt2Obj0Pred(LBiObjFltPredicate.LObj1Flt2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Flt2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2>, T1, T2> LFlt2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertFlt2Obj0Obj1Pred(LBiObjFltPredicate.LFlt2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFlt2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1>, T2, T1> LFlt2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertFlt2Obj1Obj0Pred(LBiObjFltPredicate.LFlt2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFlt2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate<T1, T2>, T1, T2> LBiObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjIntPred(LBiObjIntPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1>, T2, T1> LObj1Obj0Int2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Int2Pred(LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0Int2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2>, T1, T2> LInt2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertInt2Obj0Obj1Pred(LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LInt2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1>, T2, T1> LInt2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertInt2Obj1Obj0Pred(LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LInt2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate<T1, T2>, T1, T2> LBiObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjLongPred(LBiObjLongPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2>, T1, T2> LObj0Long2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObj0Long2Obj1Pred(LBiObjLongPredicate.LObj0Long2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Long2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1>, T2, T1> LObj1Obj0Long2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Long2Pred(LBiObjLongPredicate.LObj1Obj0Long2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0Long2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1>, T2, T1> LObj1Long2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Long2Obj0Pred(LBiObjLongPredicate.LObj1Long2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Long2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2>, T1, T2> LLong2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertLong2Obj0Obj1Pred(LBiObjLongPredicate.LLong2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLong2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1>, T2, T1> LLong2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertLong2Obj1Obj0Pred(LBiObjLongPredicate.LLong2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLong2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate<T1, T2>, T1, T2> LBiObjSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2>, T1, T2> LObj0Srt2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObj0Srt2Obj1Pred(LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Srt2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1>, T2, T1> LObj1Obj0Srt2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Srt2Pred(LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0Srt2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1>, T2, T1> LObj1Srt2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Srt2Obj0Pred(LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Srt2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2>, T1, T2> LSrt2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertSrt2Obj0Obj1Pred(LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrt2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1>, T2, T1> LSrt2Obj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertSrt2Obj1Obj0Pred(LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrt2Obj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicate<T1, T2>, T1, T2> LBiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiPred(LBiPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicate.LObj1Obj0Pred<T2, T1>, T2, T1> LObj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj1Obj0Pred(LBiPredicate.LObj1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtPredicate> LBiSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiSrtPred(LBiSrtPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtPredicate.LSrt1Srt0Pred> LSrt1Srt0PredAssert.The<A, ? extends AbstractBooleanAssert> assertSrt1Srt0Pred(LBiSrtPredicate.LSrt1Srt0Pred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrt1Srt0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolIntPredicate> LBoolIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBoolIntPred(LBoolIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBoolIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolIntPredicate.LIntBoolPred> LIntBoolPredAssert.The<A, ? extends AbstractBooleanAssert> assertIntBoolPred(LBoolIntPredicate.LIntBoolPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntBoolPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteIntPredicate> LByteIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertByteIntPred(LByteIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LByteIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteIntPredicate.LIntBytePred> LIntBytePredAssert.The<A, ? extends AbstractBooleanAssert> assertIntBytePred(LByteIntPredicate.LIntBytePred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntBytePredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBytePredicate> LBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBytePred(LBytePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharIntPredicate> LCharIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertCharIntPred(LCharIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LCharIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharIntPredicate.LIntCharPred> LIntCharPredAssert.The<A, ? extends AbstractBooleanAssert> assertIntCharPred(LCharIntPredicate.LIntCharPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntCharPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharPredicate> LCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertCharPred(LCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblIntPredicate> LDblIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertDblIntPred(LDblIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDblIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblIntPredicate.LIntDblPred> LIntDblPredAssert.The<A, ? extends AbstractBooleanAssert> assertIntDblPred(LDblIntPredicate.LIntDblPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntDblPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblPredicate> LDblPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertDblPred(LDblPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltIntPredicate> LFltIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertFltIntPred(LFltIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFltIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltIntPredicate.LIntFltPred> LIntFltPredAssert.The<A, ? extends AbstractBooleanAssert> assertIntFltPred(LFltIntPredicate.LIntFltPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntFltPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltPredicate> LFltPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertFltPred(LFltPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntPredicate> LIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertIntPred(LIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongIntPredicate> LLongIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertLongIntPred(LLongIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLongIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongIntPredicate.LIntLongPred> LIntLongPredAssert.The<A, ? extends AbstractBooleanAssert> assertIntLongPred(LLongIntPredicate.LIntLongPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntLongPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongPredicate> LLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertLongPred(LLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate<T>, T> LObjBiIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjBiIntPred(LObjBiIntPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBiIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LObj0Int2Int1Pred<T>, T> LObj0Int2Int1PredAssert.The<A, ? extends AbstractBooleanAssert, T> assertObj0Int2Int1Pred(LObjBiIntPredicate.LObj0Int2Int1Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Int2Int1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LInt1Obj0Int2Pred<T>, T> LInt1Obj0Int2PredAssert.The<A, ? extends AbstractBooleanAssert, T> assertInt1Obj0Int2Pred(LObjBiIntPredicate.LInt1Obj0Int2Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LInt1Obj0Int2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LInt1Int2Obj0Pred<T>, T> LInt1Int2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T> assertInt1Int2Obj0Pred(LObjBiIntPredicate.LInt1Int2Obj0Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LInt1Int2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LInt2Obj0Int1Pred<T>, T> LInt2Obj0Int1PredAssert.The<A, ? extends AbstractBooleanAssert, T> assertInt2Obj0Int1Pred(LObjBiIntPredicate.LInt2Obj0Int1Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LInt2Obj0Int1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBiIntPredicate.LBiInt1Obj0Pred<T>, T> LBiInt1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T> assertBiInt1Obj0Pred(LObjBiIntPredicate.LBiInt1Obj0Pred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiInt1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolPredicate<T>, T> LObjBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjBoolPred(LObjBoolPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolPredicate.LBoolObjPred<T>, T> LBoolObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertBoolObjPred(LObjBoolPredicate.LBoolObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBoolObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicate<T>, T> LObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjBytePred(LObjBytePredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicate.LByteObjPred<T>, T> LByteObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertByteObjPred(LObjBytePredicate.LByteObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LByteObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicate<T>, T> LObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjCharPred(LObjCharPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicate.LCharObjPred<T>, T> LCharObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertCharObjPred(LObjCharPredicate.LCharObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LCharObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblPredicate<T>, T> LObjDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjDblPred(LObjDblPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblPredicate.LDblObjPred<T>, T> LDblObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertDblObjPred(LObjDblPredicate.LDblObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDblObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltPredicate<T>, T> LObjFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjFltPred(LObjFltPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltPredicate.LFltObjPred<T>, T> LFltObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertFltObjPred(LObjFltPredicate.LFltObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFltObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate<T>, T> LObjIntBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntBoolPred(LObjIntBoolPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LObjBoolIntPred<T>, T> LObjBoolIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjBoolIntPred(LObjIntBoolPredicate.LObjBoolIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBoolIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LIntObjBoolPred<T>, T> LIntObjBoolPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntObjBoolPred(LObjIntBoolPredicate.LIntObjBoolPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntObjBoolPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LIntBoolObjPred<T>, T> LIntBoolObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntBoolObjPred(LObjIntBoolPredicate.LIntBoolObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntBoolObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LBoolObjIntPred<T>, T> LBoolObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertBoolObjIntPred(LObjIntBoolPredicate.LBoolObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBoolObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate.LBoolIntObjPred<T>, T> LBoolIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertBoolIntObjPred(LObjIntBoolPredicate.LBoolIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBoolIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate<T>, T> LObjIntBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntBytePred(LObjIntBytePredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LObjByteIntPred<T>, T> LObjByteIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjByteIntPred(LObjIntBytePredicate.LObjByteIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjByteIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LIntObjBytePred<T>, T> LIntObjBytePredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntObjBytePred(LObjIntBytePredicate.LIntObjBytePred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntObjBytePredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LIntByteObjPred<T>, T> LIntByteObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntByteObjPred(LObjIntBytePredicate.LIntByteObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntByteObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LByteObjIntPred<T>, T> LByteObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertByteObjIntPred(LObjIntBytePredicate.LByteObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LByteObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate.LByteIntObjPred<T>, T> LByteIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertByteIntObjPred(LObjIntBytePredicate.LByteIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LByteIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate<T>, T> LObjIntCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntCharPred(LObjIntCharPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LObjCharIntPred<T>, T> LObjCharIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjCharIntPred(LObjIntCharPredicate.LObjCharIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjCharIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LIntObjCharPred<T>, T> LIntObjCharPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntObjCharPred(LObjIntCharPredicate.LIntObjCharPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntObjCharPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LIntCharObjPred<T>, T> LIntCharObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntCharObjPred(LObjIntCharPredicate.LIntCharObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntCharObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LCharObjIntPred<T>, T> LCharObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertCharObjIntPred(LObjIntCharPredicate.LCharObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LCharObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate.LCharIntObjPred<T>, T> LCharIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertCharIntObjPred(LObjIntCharPredicate.LCharIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LCharIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate<T>, T> LObjIntDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntDblPred(LObjIntDblPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LObjDblIntPred<T>, T> LObjDblIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjDblIntPred(LObjIntDblPredicate.LObjDblIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjDblIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LIntObjDblPred<T>, T> LIntObjDblPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntObjDblPred(LObjIntDblPredicate.LIntObjDblPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntObjDblPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LIntDblObjPred<T>, T> LIntDblObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntDblObjPred(LObjIntDblPredicate.LIntDblObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntDblObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LDblObjIntPred<T>, T> LDblObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertDblObjIntPred(LObjIntDblPredicate.LDblObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDblObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate.LDblIntObjPred<T>, T> LDblIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertDblIntObjPred(LObjIntDblPredicate.LDblIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDblIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate<T>, T> LObjIntFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntFltPred(LObjIntFltPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LObjFltIntPred<T>, T> LObjFltIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjFltIntPred(LObjIntFltPredicate.LObjFltIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjFltIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LIntObjFltPred<T>, T> LIntObjFltPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntObjFltPred(LObjIntFltPredicate.LIntObjFltPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntObjFltPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LIntFltObjPred<T>, T> LIntFltObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntFltObjPred(LObjIntFltPredicate.LIntFltObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntFltObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LFltObjIntPred<T>, T> LFltObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertFltObjIntPred(LObjIntFltPredicate.LFltObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFltObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate.LFltIntObjPred<T>, T> LFltIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertFltIntObjPred(LObjIntFltPredicate.LFltIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFltIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate<T>, T> LObjIntLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntLongPred(LObjIntLongPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LObjLongIntPred<T>, T> LObjLongIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjLongIntPred(LObjIntLongPredicate.LObjLongIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjLongIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LIntObjLongPred<T>, T> LIntObjLongPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntObjLongPred(LObjIntLongPredicate.LIntObjLongPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntObjLongPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LIntLongObjPred<T>, T> LIntLongObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntLongObjPred(LObjIntLongPredicate.LIntLongObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntLongObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LLongObjIntPred<T>, T> LLongObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertLongObjIntPred(LObjIntLongPredicate.LLongObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLongObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate.LLongIntObjPred<T>, T> LLongIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertLongIntObjPred(LObjIntLongPredicate.LLongIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLongIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate<T1, T2>, T1, T2> LObjIntObjPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObjIntObjPred(LObjIntObjPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntObjPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2>, T1, T2> LInt1BiObj2PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertInt1BiObj2Pred(LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LInt1BiObj2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1>, T2, T1> LInt1Obj2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertInt1Obj2Obj0Pred(LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LInt1Obj2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1>, T2, T1> LObj2Int1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1> assertObj2Int1Obj0Pred(LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj2Int1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicate<T>, T> LObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntPred(LObjIntPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicate.LIntObjPred<T>, T> LIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntObjPred(LObjIntPredicate.LIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate<T>, T> LObjIntSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntSrtPred(LObjIntSrtPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LObjSrtIntPred<T>, T> LObjSrtIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjSrtIntPred(LObjIntSrtPredicate.LObjSrtIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjSrtIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LIntObjSrtPred<T>, T> LIntObjSrtPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntObjSrtPred(LObjIntSrtPredicate.LIntObjSrtPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntObjSrtPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LIntSrtObjPred<T>, T> LIntSrtObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertIntSrtObjPred(LObjIntSrtPredicate.LIntSrtObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntSrtObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LSrtObjIntPred<T>, T> LSrtObjIntPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertSrtObjIntPred(LObjIntSrtPredicate.LSrtObjIntPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrtObjIntPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate.LSrtIntObjPred<T>, T> LSrtIntObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertSrtIntObjPred(LObjIntSrtPredicate.LSrtIntObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrtIntObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicate<T>, T> LObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjLongPred(LObjLongPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicate.LLongObjPred<T>, T> LLongObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertLongObjPred(LObjLongPredicate.LLongObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLongObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtPredicate<T>, T> LObjSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjSrtPred(LObjSrtPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtPredicate.LSrtObjPred<T>, T> LSrtObjPredAssert.The<A, ? extends AbstractBooleanAssert, T> assertSrtObjPred(LObjSrtPredicate.LSrtObjPred<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrtObjPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LPredicate<T>, T> LPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertPred(LPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LQuadPredicate<T1, T2, T3, T4>, T1, T2, T3, T4> LQuadPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3, T4> assertQuadPred(LQuadPredicate<T1, T2, T3, T4> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LQuadPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LQuintPredicate<T1, T2, T3, T4, T5>, T1, T2, T3, T4, T5> LQuintPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3, T4, T5> assertQuintPred(LQuintPredicate<T1, T2, T3, T4, T5> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LQuintPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtIntPredicate> LSrtIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertSrtIntPred(LSrtIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrtIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtIntPredicate.LIntSrtPred> LIntSrtPredAssert.The<A, ? extends AbstractBooleanAssert> assertIntSrtPred(LSrtIntPredicate.LIntSrtPred func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntSrtPredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtPredicate> LSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertSrtPred(LSrtPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriBytePredicate> LTriBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertTriBytePred(LTriBytePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriCharPredicate> LTriCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertTriCharPred(LTriCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriDblPredicate> LTriDblPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertTriDblPred(LTriDblPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFltPredicate> LTriFltPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertTriFltPred(LTriFltPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriIntPredicate> LTriIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertTriIntPred(LTriIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriLongPredicate> LTriLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertTriLongPred(LTriLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate<T1, T2, T3>, T1, T2, T3> LTriPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3> assertTriPred(LTriPredicate<T1, T2, T3> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2>, T1, T3, T2> LObj0Obj2Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T1, T3, T2> assertObj0Obj2Obj1Pred(LTriPredicate.LObj0Obj2Obj1Pred<T1, T3, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj0Obj2Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LObj1BiObj2Pred<T2, T1, T3>, T2, T1, T3> LObj1BiObj2PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T1, T3> assertObj1BiObj2Pred(LTriPredicate.LObj1BiObj2Pred<T2, T1, T3> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1BiObj2PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1>, T2, T3, T1> LObj1Obj2Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T2, T3, T1> assertObj1Obj2Obj0Pred(LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj1Obj2Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2>, T3, T1, T2> LObj2Obj0Obj1PredAssert.The<A, ? extends AbstractBooleanAssert, T3, T1, T2> assertObj2Obj0Obj1Pred(LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObj2Obj0Obj1PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1>, T3, T2, T1> LBiObj1Obj0PredAssert.The<A, ? extends AbstractBooleanAssert, T3, T2, T1> assertBiObj1Obj0Pred(LTriPredicate.LBiObj1Obj0Pred<T3, T2, T1> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObj1Obj0PredAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriSrtPredicate> LTriSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertTriSrtPred(LTriSrtPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolSupplier> LBoolSupplierAssert.The<A, ? extends AbstractBooleanAssert> assertBoolSup(LBoolSupplier func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBoolSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteSupplier> LByteSupplierAssert.The<A, ? extends AbstractByteAssert> assertByteSup(LByteSupplier func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharSupplier> LCharSupplierAssert.The<A, ? extends AbstractCharacterAssert> assertCharSup(LCharSupplier func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDblSupplier> LDblSupplierAssert.The<A, ? extends AbstractDoubleAssert> assertDblSup(LDblSupplier func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LDblSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFltSupplier> LFltSupplierAssert.The<A, ? extends AbstractFloatAssert> assertFltSup(LFltSupplier func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LFltSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntSupplier> LIntSupplierAssert.The<A, ? extends AbstractIntegerAssert> assertIntSup(LIntSupplier func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongSupplier> LLongSupplierAssert.The<A, ? extends AbstractLongAssert> assertLongSup(LLongSupplier func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtSupplier> LSrtSupplierAssert.The<A, ? extends AbstractShortAssert> assertSrtSup(LSrtSupplier func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LSrtSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSupplier<T>, T> LSupplierAssert.The<A, ? extends OS, T> assertSup(LSupplier<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Runnable> JreRunnableAssert.The<A> assertAct(Runnable func) {
		return new JreRunnableAssert.The(func);
	}

	@Nonnull
	default <A extends BiConsumer<T1, T2>, T1, T2> JreBiConsumerAssert.The<A, T1, T2> assertBiCons(BiConsumer<T1, T2> func) {
		return new JreBiConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends Consumer<T>, T> JreConsumerAssert.The<A, T> assertCons(Consumer<T> func) {
		return new JreConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends DoubleConsumer> JreDoubleConsumerAssert.The<A> assertDblCons(DoubleConsumer func) {
		return new JreDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends IntConsumer> JreIntConsumerAssert.The<A> assertIntCons(IntConsumer func) {
		return new JreIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LongConsumer> JreLongConsumerAssert.The<A> assertLongCons(LongConsumer func) {
		return new JreLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjDoubleConsumer<T>, T> JreObjDoubleConsumerAssert.The<A, T> assertObjDblCons(ObjDoubleConsumer<T> func) {
		return new JreObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjIntConsumer<T>, T> JreObjIntConsumerAssert.The<A, T> assertObjIntCons(ObjIntConsumer<T> func) {
		return new JreObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjLongConsumer<T>, T> JreObjLongConsumerAssert.The<A, T> assertObjLongCons(ObjLongConsumer<T> func) {
		return new JreObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends BinaryOperator<T>, T> JreBinaryOperatorAssert.The<A, ? extends OS, T> assertBinaryOp(BinaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new JreBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleBinaryOperator> JreDoubleBinaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertDblBinaryOp(DoubleBinaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new JreDoubleBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleUnaryOperator> JreDoubleUnaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertDblUnaryOp(DoubleUnaryOperator func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new JreDoubleUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntBinaryOperator> JreIntBinaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertIntBinaryOp(IntBinaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new JreIntBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntUnaryOperator> JreIntUnaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertIntUnaryOp(IntUnaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new JreIntUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongBinaryOperator> JreLongBinaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertLongBinaryOp(LongBinaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new JreLongBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongUnaryOperator> JreLongUnaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertLongUnaryOp(LongUnaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new JreLongUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends UnaryOperator<T>, T> JreUnaryOperatorAssert.The<A, ? extends OS, T> assertUnaryOp(UnaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new JreUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BiFunction<T1, T2, R>, T1, T2, R> JreBiFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiFunc(BiFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new JreBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleFunction<R>, R> JreDoubleFunctionAssert.The<A, ? extends OS, R> assertDblFunc(DoubleFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new JreDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToIntFunction> JreDoubleToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertDblToIntFunc(DoubleToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new JreDoubleToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToLongFunction> JreDoubleToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertDblToLongFunc(DoubleToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new JreDoubleToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Function<T, R>, T, R> JreFunctionAssert.The<A, ? extends OS, T, R> assertFunc(Function<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new JreFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntFunction<R>, R> JreIntFunctionAssert.The<A, ? extends OS, R> assertIntFunc(IntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new JreIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntToDoubleFunction> JreIntToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertIntToDblFunc(IntToDoubleFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new JreIntToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntToLongFunction> JreIntToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertIntToLongFunc(IntToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new JreIntToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongFunction<R>, R> JreLongFunctionAssert.The<A, ? extends OS, R> assertLongFunc(LongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new JreLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongToDoubleFunction> JreLongToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertLongToDblFunc(LongToDoubleFunction func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new JreLongToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongToIntFunction> JreLongToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertLongToIntFunc(LongToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new JreLongToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleBiFunction<T1, T2>, T1, T2> JreToDoubleBiFunctionAssert.The<A, ? extends AbstractDoubleAssert, T1, T2> assertToDblBiFunc(ToDoubleBiFunction<T1, T2> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new JreToDoubleBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleFunction<T>, T> JreToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> assertToDblFunc(ToDoubleFunction<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new JreToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToIntBiFunction<T1, T2>, T1, T2> JreToIntBiFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertToIntBiFunc(ToIntBiFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new JreToIntBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToIntFunction<T>, T> JreToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertToIntFunc(ToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new JreToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToLongBiFunction<T1, T2>, T1, T2> JreToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> assertToLongBiFunc(ToLongBiFunction<T1, T2> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new JreToLongBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToLongFunction<T>, T> JreToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> assertToLongFunc(ToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new JreToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BiPredicate<T1, T2>, T1, T2> JreBiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiPred(BiPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new JreBiPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoublePredicate> JreDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertDblPred(DoublePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new JreDoublePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntPredicate> JreIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertIntPred(IntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new JreIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongPredicate> JreLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertLongPred(LongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new JreLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Predicate<T>, T> JrePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertPred(Predicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new JrePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BooleanSupplier> JreBooleanSupplierAssert.The<A, ? extends AbstractBooleanAssert> assertBoolSup(BooleanSupplier func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new JreBooleanSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleSupplier> JreDoubleSupplierAssert.The<A, ? extends AbstractDoubleAssert> assertDblSup(DoubleSupplier func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new JreDoubleSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntSupplier> JreIntSupplierAssert.The<A, ? extends AbstractIntegerAssert> assertIntSup(IntSupplier func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new JreIntSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongSupplier> JreLongSupplierAssert.The<A, ? extends AbstractLongAssert> assertLongSup(LongSupplier func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new JreLongSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Supplier<T>, T> JreSupplierAssert.The<A, ? extends OS, T> assertSup(Supplier<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new JreSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default OptBoolTraitAssert assertThat(OptBoolTrait<?> actual) {
		return new OptBoolTraitAssert(actual);
	}

	@Nonnull
	default <T> OptTraitAssert<T> assertThat(OptTrait<T, ?> actual) {
		return new OptTraitAssert<T>(actual);
	}

	@Nonnull
	default OptByteTraitAssert assertThat(OptByteTrait<?> actual) {
		return new OptByteTraitAssert(actual);
	}

	@Nonnull
	default OptDblTraitAssert assertThat(OptDblTrait<?> actual) {
		return new OptDblTraitAssert(actual);
	}

	@Nonnull
	default OptCharTraitAssert assertThat(OptCharTrait<?> actual) {
		return new OptCharTraitAssert(actual);
	}

	@Nonnull
	default OptSrtTraitAssert assertThat(OptSrtTrait<?> actual) {
		return new OptSrtTraitAssert(actual);
	}

	@Nonnull
	default OptFltTraitAssert assertThat(OptFltTrait<?> actual) {
		return new OptFltTraitAssert(actual);
	}

	@Nonnull
	default OptIntTraitAssert assertThat(OptIntTrait<?> actual) {
		return new OptIntTraitAssert(actual);
	}

	@Nonnull
	default OptLongTraitAssert assertThat(OptLongTrait<?> actual) {
		return new OptLongTraitAssert(actual);
	}

	@Nonnull
	default <A> MagmaAssert.ObjAssert<A> assertThat(A actual) {
		return new MagmaAssert.ObjAssert(actual);
	}

}