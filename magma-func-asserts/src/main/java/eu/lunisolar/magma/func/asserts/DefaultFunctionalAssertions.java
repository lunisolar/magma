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

package eu.lunisolar.magma.func.asserts;

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
import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.action.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.asserts.supplier.*; // NOSONAR
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
//import eu.lunisolar.magma.func.asserts.std.*; // NOSONAR

import static org.assertj.core.api.Fail.fail;

/**
 * Default implementation of assertion factories. Always use with/by provided type argument OS otherwise compiler will not be able to infer the type of
 * assertion class.
 *
 * @param OS required base class for object assertions. It need to be provided in the use case otherwise compiler will not be able to infer the type.
 */
@SuppressWarnings("ALL")
public interface DefaultFunctionalAssertions<OS extends Assert> extends BasicAssertions<OS> {

	default <RS extends Assert, R> DefaultFunctionalAssertions<RS> withinCodomain(final Function<R, RS> codomainAssertFactory) {
		return new DefaultFunctionalAssertions<RS>() {
			public RS assertThatObj(Object actual) {
				return codomainAssertFactory.apply((R) actual);
			}
		};
	}

	default <RS extends Assert, R> DefaultFunctionalAssertions<RS> withinCodomain(final Function<R, RS> codomainAssertFactory, Class<R> r, Class<RS> rs) {
		return new DefaultFunctionalAssertions<RS>() {
			public RS assertThatObj(Object actual) {
				return codomainAssertFactory.apply((R) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractOptionalAssert> withinOptionalCodomain() {
		return new DefaultFunctionalAssertions<AbstractOptionalAssert>() {
			@Override
			public AbstractOptionalAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Optional) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractBigDecimalAssert> withinBigDecimalCodomain() {
		return new DefaultFunctionalAssertions<AbstractBigDecimalAssert>() {
			@Override
			public AbstractBigDecimalAssert assertThatObj(Object actual) {
				return Assertions.assertThat((BigDecimal) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractBooleanAssert> withinBooleanCodomain() {
		return new DefaultFunctionalAssertions<AbstractBooleanAssert>() {
			@Override
			public AbstractBooleanAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Boolean) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractBooleanArrayAssert> withinBooleanArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractBooleanArrayAssert>() {
			@Override
			public AbstractBooleanArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((boolean[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractByteAssert> withinByteCodomain() {
		return new DefaultFunctionalAssertions<AbstractByteAssert>() {
			@Override
			public AbstractByteAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Byte) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractByteArrayAssert> withinByteArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractByteArrayAssert>() {
			@Override
			public AbstractByteArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((byte[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractCharacterAssert> withinCharacterCodomain() {
		return new DefaultFunctionalAssertions<AbstractCharacterAssert>() {
			@Override
			public AbstractCharacterAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Character) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractCharArrayAssert> withinCharArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractCharArrayAssert>() {
			@Override
			public AbstractCharArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((char[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractClassAssert> withinClassCodomain() {
		return new DefaultFunctionalAssertions<AbstractClassAssert>() {
			@Override
			public AbstractClassAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Class) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractComparableAssert> withinComparableCodomain() {
		return new DefaultFunctionalAssertions<AbstractComparableAssert>() {
			@Override
			public AbstractComparableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Comparable) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractIterableAssert> withinIterableCodomain() {
		return new DefaultFunctionalAssertions<AbstractIterableAssert>() {
			@Override
			public AbstractIterableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Iterable) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractIteratorAssert> withinIteratorCodomain() {
		return new DefaultFunctionalAssertions<AbstractIteratorAssert>() {
			@Override
			public AbstractIteratorAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Iterator) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractDoubleAssert> withinDoubleCodomain() {
		return new DefaultFunctionalAssertions<AbstractDoubleAssert>() {
			@Override
			public AbstractDoubleAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Double) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractDoubleArrayAssert> withinDoubleArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractDoubleArrayAssert>() {
			@Override
			public AbstractDoubleArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((double[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractPathAssert> withinPathCodomain() {
		return new DefaultFunctionalAssertions<AbstractPathAssert>() {
			@Override
			public AbstractPathAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Path) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractInputStreamAssert> withinInputStreamCodomain() {
		return new DefaultFunctionalAssertions<AbstractInputStreamAssert>() {
			@Override
			public AbstractInputStreamAssert assertThatObj(Object actual) {
				return Assertions.assertThat((InputStream) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractFloatAssert> withinFloatCodomain() {
		return new DefaultFunctionalAssertions<AbstractFloatAssert>() {
			@Override
			public AbstractFloatAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Float) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractFloatArrayAssert> withinFloatArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractFloatArrayAssert>() {
			@Override
			public AbstractFloatArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((float[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractIntegerAssert> withinIntegerCodomain() {
		return new DefaultFunctionalAssertions<AbstractIntegerAssert>() {
			@Override
			public AbstractIntegerAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Integer) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractIntArrayAssert> withinIntArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractIntArrayAssert>() {
			@Override
			public AbstractIntArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((int[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractListAssert> withinListCodomain() {
		return new DefaultFunctionalAssertions<AbstractListAssert>() {
			@Override
			public AbstractListAssert assertThatObj(Object actual) {
				return Assertions.assertThat((List) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLongAssert> withinLongCodomain() {
		return new DefaultFunctionalAssertions<AbstractLongAssert>() {
			@Override
			public AbstractLongAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Long) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLongArrayAssert> withinLongArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractLongArrayAssert>() {
			@Override
			public AbstractLongArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((long[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractMapAssert> withinMapCodomain() {
		return new DefaultFunctionalAssertions<AbstractMapAssert>() {
			@Override
			public AbstractMapAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Map) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractShortAssert> withinShortCodomain() {
		return new DefaultFunctionalAssertions<AbstractShortAssert>() {
			@Override
			public AbstractShortAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Short) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractShortArrayAssert> withinShortArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractShortArrayAssert>() {
			@Override
			public AbstractShortArrayAssert assertThatObj(Object actual) {
				return Assertions.assertThat((short[]) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractCharSequenceAssert> withinCharSequenceCodomain() {
		return new DefaultFunctionalAssertions<AbstractCharSequenceAssert>() {
			@Override
			public AbstractCharSequenceAssert assertThatObj(Object actual) {
				return Assertions.assertThat((CharSequence) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractCharSequenceAssert> withinStringCodomain() {
		return new DefaultFunctionalAssertions<AbstractCharSequenceAssert>() {
			@Override
			public AbstractCharSequenceAssert assertThatObj(Object actual) {
				return Assertions.assertThat((String) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractDateAssert> withinDateCodomain() {
		return new DefaultFunctionalAssertions<AbstractDateAssert>() {
			@Override
			public AbstractDateAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Date) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractZonedDateTimeAssert> withinZonedDateTimeCodomain() {
		return new DefaultFunctionalAssertions<AbstractZonedDateTimeAssert>() {
			@Override
			public AbstractZonedDateTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((ZonedDateTime) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLocalDateTimeAssert> withinLocalDateTimeCodomain() {
		return new DefaultFunctionalAssertions<AbstractLocalDateTimeAssert>() {
			@Override
			public AbstractLocalDateTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalDateTime) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLocalTimeAssert> withinLocalTimeCodomain() {
		return new DefaultFunctionalAssertions<AbstractLocalTimeAssert>() {
			@Override
			public AbstractLocalTimeAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalTime) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractLocalDateAssert> withinLocalDateCodomain() {
		return new DefaultFunctionalAssertions<AbstractLocalDateAssert>() {
			@Override
			public AbstractLocalDateAssert assertThatObj(Object actual) {
				return Assertions.assertThat((LocalDate) actual);
			}
		};
	}

	default DefaultFunctionalAssertions<AbstractThrowableAssert> withinThrowableCodomain() {
		return new DefaultFunctionalAssertions<AbstractThrowableAssert>() {
			@Override
			public AbstractThrowableAssert assertThatObj(Object actual) {
				return Assertions.assertThat((Throwable) actual);
			}
		};
	}

	default <T> DefaultFunctionalAssertions<AbstractObjectArrayAssert> withinTArrayCodomain() {
		return new DefaultFunctionalAssertions<AbstractObjectArrayAssert>() {
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
	default <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> assertCons(LConsumer<T> func) {
		return new LConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LQuadConsumer<T1, T2, T3, T4>, T1, T2, T3, T4> LQuadConsumerAssert.The<A, T1, T2, T3, T4> assertQuadCons(LQuadConsumer<T1, T2, T3, T4> func) {
		return new LQuadConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> assertTriCons(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAssert.The(func);
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
	default <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> assertBiByteCons(LBiByteConsumer func) {
		return new LBiByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> assertBiCharCons(LBiCharConsumer func) {
		return new LBiCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiDblConsumer> LBiDblConsumerAssert.The<A> assertBiDblCons(LBiDblConsumer func) {
		return new LBiDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiFltConsumer> LBiFltConsumerAssert.The<A> assertBiFltCons(LBiFltConsumer func) {
		return new LBiFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> assertBiIntCons(LBiIntConsumer func) {
		return new LBiIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> assertBiLongCons(LBiLongConsumer func) {
		return new LBiLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiSrtConsumer> LBiSrtConsumerAssert.The<A> assertBiSrtCons(LBiSrtConsumer func) {
		return new LBiSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolIntConsumer> LBoolIntConsumerAssert.The<A> assertBoolIntCons(LBoolIntConsumer func) {
		return new LBoolIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LByteIntConsumer> LByteIntConsumerAssert.The<A> assertByteIntCons(LByteIntConsumer func) {
		return new LByteIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LCharIntConsumer> LCharIntConsumerAssert.The<A> assertCharIntCons(LCharIntConsumer func) {
		return new LCharIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LDblIntConsumer> LDblIntConsumerAssert.The<A> assertDblIntCons(LDblIntConsumer func) {
		return new LDblIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LFltIntConsumer> LFltIntConsumerAssert.The<A> assertFltIntCons(LFltIntConsumer func) {
		return new LFltIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LLongIntConsumer> LLongIntConsumerAssert.The<A> assertLongIntCons(LLongIntConsumer func) {
		return new LLongIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LSrtIntConsumer> LSrtIntConsumerAssert.The<A> assertSrtIntCons(LSrtIntConsumer func) {
		return new LSrtIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> assertBiObjBoolCons(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> assertBiObjByteCons(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> assertBiObjCharCons(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDblConsumer<T1, T2>, T1, T2> LBiObjDblConsumerAssert.The<A, T1, T2> assertBiObjDblCons(LBiObjDblConsumer<T1, T2> func) {
		return new LBiObjDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFltConsumer<T1, T2>, T1, T2> LBiObjFltConsumerAssert.The<A, T1, T2> assertBiObjFltCons(LBiObjFltConsumer<T1, T2> func) {
		return new LBiObjFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> assertBiObjIntCons(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> assertBiObjLongCons(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjSrtConsumer<T1, T2>, T1, T2> LBiObjSrtConsumerAssert.The<A, T1, T2> assertBiObjSrtCons(LBiObjSrtConsumer<T1, T2> func) {
		return new LBiObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> assertObjBoolCons(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> assertObjByteCons(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> assertObjCharCons(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjDblConsumer<T>, T> LObjDblConsumerAssert.The<A, T> assertObjDblCons(LObjDblConsumer<T> func) {
		return new LObjDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjFltConsumer<T>, T> LObjFltConsumerAssert.The<A, T> assertObjFltCons(LObjFltConsumer<T> func) {
		return new LObjFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> assertObjIntCons(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> assertObjLongCons(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjSrtConsumer<T>, T> LObjSrtConsumerAssert.The<A, T> assertObjSrtCons(LObjSrtConsumer<T> func) {
		return new LObjSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieBoolConsumer<T>, T> LTieBoolConsumerAssert.The<A, T> assertTieBoolCons(LTieBoolConsumer<T> func) {
		return new LTieBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieByteConsumer<T>, T> LTieByteConsumerAssert.The<A, T> assertTieByteCons(LTieByteConsumer<T> func) {
		return new LTieByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieCharConsumer<T>, T> LTieCharConsumerAssert.The<A, T> assertTieCharCons(LTieCharConsumer<T> func) {
		return new LTieCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieConsumer<T1, T2>, T1, T2> LTieConsumerAssert.The<A, T1, T2> assertTieCons(LTieConsumer<T1, T2> func) {
		return new LTieConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieDblConsumer<T>, T> LTieDblConsumerAssert.The<A, T> assertTieDblCons(LTieDblConsumer<T> func) {
		return new LTieDblConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieFltConsumer<T>, T> LTieFltConsumerAssert.The<A, T> assertTieFltCons(LTieFltConsumer<T> func) {
		return new LTieFltConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieIntConsumer<T>, T> LTieIntConsumerAssert.The<A, T> assertTieIntCons(LTieIntConsumer<T> func) {
		return new LTieIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieLongConsumer<T>, T> LTieLongConsumerAssert.The<A, T> assertTieLongCons(LTieLongConsumer<T> func) {
		return new LTieLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTieSrtConsumer<T>, T> LTieSrtConsumerAssert.The<A, T> assertTieSrtCons(LTieSrtConsumer<T> func) {
		return new LTieSrtConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> assertTriBoolCons(LTriBoolConsumer func) {
		return new LTriBoolConsumerAssert.The(func);
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
	default <A extends LTriFunction<T1, T2, T3, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, ? extends OS, T1, T2, T3, R> assertTriFunc(LTriFunction<T1, T2, T3, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTriFunctionAssert.The(func, assertFunc);
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
	default <A extends LBiByteFunction<R>, R> LBiByteFunctionAssert.The<A, ? extends OS, R> assertBiByteFunc(LBiByteFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunction<R>, R> LBiCharFunctionAssert.The<A, ? extends OS, R> assertBiCharFunc(LBiCharFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblFunction<R>, R> LBiDblFunctionAssert.The<A, ? extends OS, R> assertBiDblFunc(LBiDblFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltFunction<R>, R> LBiFltFunctionAssert.The<A, ? extends OS, R> assertBiFltFunc(LBiFltFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunction<R>, R> LBiIntFunctionAssert.The<A, ? extends OS, R> assertBiIntFunc(LBiIntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunction<R>, R> LBiLongFunctionAssert.The<A, ? extends OS, R> assertBiLongFunc(LBiLongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction<T1, T2, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjBoolFunc(LBiObjBoolFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction<T1, T2, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjByteFunc(LBiObjByteFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction<T1, T2, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjCharFunc(LBiObjCharFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblFunction<T1, T2, R>, T1, T2, R> LBiObjDblFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjDblFunc(LBiObjDblFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltFunction<T1, T2, R>, T1, T2, R> LBiObjFltFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction<T1, T2, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjIntFunc(LBiObjIntFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction<T1, T2, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjLongFunc(LBiObjLongFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtFunction<T1, T2, R>, T1, T2, R> LBiObjSrtFunctionAssert.The<A, ? extends OS, T1, T2, R> assertBiObjSrtFunc(LBiObjSrtFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtFunction<R>, R> LBiSrtFunctionAssert.The<A, ? extends OS, R> assertBiSrtFunc(LBiSrtFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiSrtFunctionAssert.The(func, assertFunc);
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
	default <A extends LObjBoolFunction<T, R>, T, R> LObjBoolFunctionAssert.The<A, ? extends OS, T, R> assertObjBoolFunc(LObjBoolFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunction<T, R>, T, R> LObjByteFunctionAssert.The<A, ? extends OS, T, R> assertObjByteFunc(LObjByteFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunction<T, R>, T, R> LObjCharFunctionAssert.The<A, ? extends OS, T, R> assertObjCharFunc(LObjCharFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblFunction<T, R>, T, R> LObjDblFunctionAssert.The<A, ? extends OS, T, R> assertObjDblFunc(LObjDblFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltFunction<T, R>, T, R> LObjFltFunctionAssert.The<A, ? extends OS, T, R> assertObjFltFunc(LObjFltFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolFunction<T, R>, T, R> LObjIntBoolFunctionAssert.The<A, ? extends OS, T, R> assertObjIntBoolFunc(LObjIntBoolFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntByteFunction<T, R>, T, R> LObjIntByteFunctionAssert.The<A, ? extends OS, T, R> assertObjIntByteFunc(LObjIntByteFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharFunction<T, R>, T, R> LObjIntCharFunctionAssert.The<A, ? extends OS, T, R> assertObjIntCharFunc(LObjIntCharFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblFunction<T, R>, T, R> LObjIntDblFunctionAssert.The<A, ? extends OS, T, R> assertObjIntDblFunc(LObjIntDblFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltFunction<T, R>, T, R> LObjIntFltFunctionAssert.The<A, ? extends OS, T, R> assertObjIntFltFunc(LObjIntFltFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongFunction<T, R>, T, R> LObjIntLongFunctionAssert.The<A, ? extends OS, T, R> assertObjIntLongFunc(LObjIntLongFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjFunction<T1, T2, R>, T1, T2, R> LObjIntObjFunctionAssert.The<A, ? extends OS, T1, T2, R> assertObjIntObjFunc(LObjIntObjFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntObjFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtFunction<T, R>, T, R> LObjIntSrtFunctionAssert.The<A, ? extends OS, T, R> assertObjIntSrtFunc(LObjIntSrtFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunction<T, R>, T, R> LObjLongFunctionAssert.The<A, ? extends OS, T, R> assertObjLongFunc(LObjLongFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtFunction<T, R>, T, R> LObjSrtFunctionAssert.The<A, ? extends OS, T, R> assertObjSrtFunc(LObjSrtFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiFunction<T, R>, T, R> LOiFunctionAssert.The<A, ? extends OS, T, R> assertOiFunc(LOiFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LOiFunctionAssert.The(func, assertFunc);
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
	default <A extends LOiToCharFunction<T>, T> LOiToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert, T> assertOiToCharFunc(LOiToCharFunction<T> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LOiToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToDblFunction<T>, T> LOiToDblFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> assertOiToDblFunc(LOiToDblFunction<T> func) {
		LDblFunction<AbstractDoubleAssert> assertFunc = this::assertThatDbl;
		return new LOiToDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToFltFunction<T>, T> LOiToFltFunctionAssert.The<A, ? extends AbstractFloatAssert, T> assertOiToFltFunc(LOiToFltFunction<T> func) {
		LFltFunction<AbstractFloatAssert> assertFunc = this::assertThatFlt;
		return new LOiToFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToIntFunction<T>, T> LOiToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertOiToIntFunc(LOiToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LOiToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToLongFunction<T>, T> LOiToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> assertOiToLongFunc(LOiToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LOiToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LOiToSrtFunction<T>, T> LOiToSrtFunctionAssert.The<A, ? extends AbstractShortAssert, T> assertOiToSrtFunc(LOiToSrtFunction<T> func) {
		LSrtFunction<AbstractShortAssert> assertFunc = this::assertThatSrt;
		return new LOiToSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieBoolFunction<T>, T> LTieBoolFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieBoolFunc(LTieBoolFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieByteFunction<T>, T> LTieByteFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieByteFunc(LTieByteFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieCharFunction<T>, T> LTieCharFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieCharFunc(LTieCharFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieDblFunction<T>, T> LTieDblFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieDblFunc(LTieDblFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieDblFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFltFunction<T>, T> LTieFltFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieFltFunc(LTieFltFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieFltFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieFunction<T1, T2>, T1, T2> LTieFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertTieFunc(LTieFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieIntFunction<T>, T> LTieIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieIntFunc(LTieIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieLongFunction<T>, T> LTieLongFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieLongFunc(LTieLongFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTieSrtFunction<T>, T> LTieSrtFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertTieSrtFunc(LTieSrtFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LTieSrtFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunction<T1, T2>, T1, T2> LToByteBiFunctionAssert.The<A, ? extends AbstractByteAssert, T1, T2> assertToByteBiFunc(LToByteBiFunction<T1, T2> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteBiFunctionAssert.The(func, assertFunc);
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
	default <A extends LToLongBiFunction<T1, T2>, T1, T2> LToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> assertToLongBiFunc(LToLongBiFunction<T1, T2> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongBiFunctionAssert.The(func, assertFunc);
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
	default <A extends LBiCharPredicate> LBiCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiCharPred(LBiCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDblPredicate> LBiDblPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiDblPred(LBiDblPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFltPredicate> LBiFltPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiFltPred(LBiFltPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicate> LBiIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiIntPred(LBiIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicate> LBiLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiLongPred(LBiLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate<T1, T2>, T1, T2> LBiObjBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjBoolPred(LBiObjBoolPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate<T1, T2>, T1, T2> LBiObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjBytePred(LBiObjBytePredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate<T1, T2>, T1, T2> LBiObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjCharPred(LBiObjCharPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDblPredicate<T1, T2>, T1, T2> LBiObjDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjDblPred(LBiObjDblPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFltPredicate<T1, T2>, T1, T2> LBiObjFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjFltPred(LBiObjFltPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate<T1, T2>, T1, T2> LBiObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjIntPred(LBiObjIntPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate<T1, T2>, T1, T2> LBiObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjLongPred(LBiObjLongPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjSrtPredicate<T1, T2>, T1, T2> LBiObjSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiObjSrtPred(LBiObjSrtPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicate<T1, T2>, T1, T2> LBiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertBiPred(LBiPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiSrtPredicate> LBiSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBiSrtPred(LBiSrtPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolIntPredicate> LBoolIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertBoolIntPred(LBoolIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBoolIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteIntPredicate> LByteIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertByteIntPred(LByteIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LByteIntPredicateAssert.The(func, assertFunc);
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
	default <A extends LObjBoolPredicate<T>, T> LObjBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjBoolPred(LObjBoolPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicate<T>, T> LObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjBytePred(LObjBytePredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicate<T>, T> LObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjCharPred(LObjCharPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDblPredicate<T>, T> LObjDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjDblPred(LObjDblPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFltPredicate<T>, T> LObjFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjFltPred(LObjFltPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBoolPredicate<T>, T> LObjIntBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntBoolPred(LObjIntBoolPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntBytePredicate<T>, T> LObjIntBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntBytePred(LObjIntBytePredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntCharPredicate<T>, T> LObjIntCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntCharPred(LObjIntCharPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntDblPredicate<T>, T> LObjIntDblPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntDblPred(LObjIntDblPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntDblPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFltPredicate<T>, T> LObjIntFltPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntFltPred(LObjIntFltPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntFltPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntLongPredicate<T>, T> LObjIntLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntLongPred(LObjIntLongPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntObjPredicate<T1, T2>, T1, T2> LObjIntObjPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertObjIntObjPred(LObjIntObjPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntObjPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicate<T>, T> LObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntPred(LObjIntPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntSrtPredicate<T>, T> LObjIntSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjIntSrtPred(LObjIntSrtPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicate<T>, T> LObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjLongPred(LObjLongPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjSrtPredicate<T>, T> LObjSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertObjSrtPred(LObjSrtPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjSrtPredicateAssert.The(func, assertFunc);
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
	default <A extends LSrtIntPredicate> LSrtIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertSrtIntPred(LSrtIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrtIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSrtPredicate> LSrtPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertSrtPred(LSrtPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LSrtPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate<T1, T2, T3>, T1, T2, T3> LTriPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3> assertTriPred(LTriPredicate<T1, T2, T3> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriPredicateAssert.The(func, assertFunc);
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

}