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

	default DefaultFunctionalAssertions<AbstractIterableAssert> withinIteratorCodomain() {
		return new DefaultFunctionalAssertions<AbstractIterableAssert>() {
			@Override
			public AbstractIterableAssert assertThatObj(Object actual) {
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
	default <A extends LAction> LActionAssert.The<A> assertThat(LAction func) {
		return new LActionAssert.The(func);
	}

	@Nonnull
	default <A extends LActionX<X>, X extends Throwable> LActionXAssert.The<A, X> assertThat(LActionX<X> func) {
		return new LActionXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiConsumer<T1, T2>, T1, T2> LBiConsumerAssert.The<A, T1, T2> assertThat(LBiConsumer<T1, T2> func) {
		return new LBiConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiConsumerXAssert.The<A, T1, T2, X> assertThat(LBiConsumerX<T1, T2, X> func) {
		return new LBiConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LConsumer<T>, T> LConsumerAssert.The<A, T> assertThat(LConsumer<T> func) {
		return new LConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LConsumerX<T, X>, T, X extends Throwable> LConsumerXAssert.The<A, T, X> assertThat(LConsumerX<T, X> func) {
		return new LConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumer<T1, T2, T3>, T1, T2, T3> LTriConsumerAssert.The<A, T1, T2, T3> assertThat(LTriConsumer<T1, T2, T3> func) {
		return new LTriConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriConsumerX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriConsumerXAssert.The<A, T1, T2, T3, X> assertThat(LTriConsumerX<T1, T2, T3, X> func) {
		return new LTriConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolConsumer> LBoolConsumerAssert.The<A> assertThat(LBoolConsumer func) {
		return new LBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBoolConsumerX<X>, X extends Throwable> LBoolConsumerXAssert.The<A, X> assertThat(LBoolConsumerX<X> func) {
		return new LBoolConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LByteConsumer> LByteConsumerAssert.The<A> assertThat(LByteConsumer func) {
		return new LByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LByteConsumerX<X>, X extends Throwable> LByteConsumerXAssert.The<A, X> assertThat(LByteConsumerX<X> func) {
		return new LByteConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LCharConsumer> LCharConsumerAssert.The<A> assertThat(LCharConsumer func) {
		return new LCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LCharConsumerX<X>, X extends Throwable> LCharConsumerXAssert.The<A, X> assertThat(LCharConsumerX<X> func) {
		return new LCharConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LDoubleConsumer> LDoubleConsumerAssert.The<A> assertThat(LDoubleConsumer func) {
		return new LDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LDoubleConsumerX<X>, X extends Throwable> LDoubleConsumerXAssert.The<A, X> assertThat(LDoubleConsumerX<X> func) {
		return new LDoubleConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LFloatConsumer> LFloatConsumerAssert.The<A> assertThat(LFloatConsumer func) {
		return new LFloatConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LFloatConsumerX<X>, X extends Throwable> LFloatConsumerXAssert.The<A, X> assertThat(LFloatConsumerX<X> func) {
		return new LFloatConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LIntConsumer> LIntConsumerAssert.The<A> assertThat(LIntConsumer func) {
		return new LIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LIntConsumerX<X>, X extends Throwable> LIntConsumerXAssert.The<A, X> assertThat(LIntConsumerX<X> func) {
		return new LIntConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LLongConsumer> LLongConsumerAssert.The<A> assertThat(LLongConsumer func) {
		return new LLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LLongConsumerX<X>, X extends Throwable> LLongConsumerXAssert.The<A, X> assertThat(LLongConsumerX<X> func) {
		return new LLongConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LShortConsumer> LShortConsumerAssert.The<A> assertThat(LShortConsumer func) {
		return new LShortConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LShortConsumerX<X>, X extends Throwable> LShortConsumerXAssert.The<A, X> assertThat(LShortConsumerX<X> func) {
		return new LShortConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiBoolConsumer> LBiBoolConsumerAssert.The<A> assertThat(LBiBoolConsumer func) {
		return new LBiBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiBoolConsumerX<X>, X extends Throwable> LBiBoolConsumerXAssert.The<A, X> assertThat(LBiBoolConsumerX<X> func) {
		return new LBiBoolConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiByteConsumer> LBiByteConsumerAssert.The<A> assertThat(LBiByteConsumer func) {
		return new LBiByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiByteConsumerX<X>, X extends Throwable> LBiByteConsumerXAssert.The<A, X> assertThat(LBiByteConsumerX<X> func) {
		return new LBiByteConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiCharConsumer> LBiCharConsumerAssert.The<A> assertThat(LBiCharConsumer func) {
		return new LBiCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiCharConsumerX<X>, X extends Throwable> LBiCharConsumerXAssert.The<A, X> assertThat(LBiCharConsumerX<X> func) {
		return new LBiCharConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiDoubleConsumer> LBiDoubleConsumerAssert.The<A> assertThat(LBiDoubleConsumer func) {
		return new LBiDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiDoubleConsumerX<X>, X extends Throwable> LBiDoubleConsumerXAssert.The<A, X> assertThat(LBiDoubleConsumerX<X> func) {
		return new LBiDoubleConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiFloatConsumer> LBiFloatConsumerAssert.The<A> assertThat(LBiFloatConsumer func) {
		return new LBiFloatConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiFloatConsumerX<X>, X extends Throwable> LBiFloatConsumerXAssert.The<A, X> assertThat(LBiFloatConsumerX<X> func) {
		return new LBiFloatConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiIntConsumer> LBiIntConsumerAssert.The<A> assertThat(LBiIntConsumer func) {
		return new LBiIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiIntConsumerX<X>, X extends Throwable> LBiIntConsumerXAssert.The<A, X> assertThat(LBiIntConsumerX<X> func) {
		return new LBiIntConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiLongConsumer> LBiLongConsumerAssert.The<A> assertThat(LBiLongConsumer func) {
		return new LBiLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiLongConsumerX<X>, X extends Throwable> LBiLongConsumerXAssert.The<A, X> assertThat(LBiLongConsumerX<X> func) {
		return new LBiLongConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiShortConsumer> LBiShortConsumerAssert.The<A> assertThat(LBiShortConsumer func) {
		return new LBiShortConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiShortConsumerX<X>, X extends Throwable> LBiShortConsumerXAssert.The<A, X> assertThat(LBiShortConsumerX<X> func) {
		return new LBiShortConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumer<T1, T2>, T1, T2> LBiObjBoolConsumerAssert.The<A, T1, T2> assertThat(LBiObjBoolConsumer<T1, T2> func) {
		return new LBiObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjBoolConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBoolConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjBoolConsumerX<T1, T2, X> func) {
		return new LBiObjBoolConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumer<T1, T2>, T1, T2> LBiObjByteConsumerAssert.The<A, T1, T2> assertThat(LBiObjByteConsumer<T1, T2> func) {
		return new LBiObjByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjByteConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjByteConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjByteConsumerX<T1, T2, X> func) {
		return new LBiObjByteConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumer<T1, T2>, T1, T2> LBiObjCharConsumerAssert.The<A, T1, T2> assertThat(LBiObjCharConsumer<T1, T2> func) {
		return new LBiObjCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjCharConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjCharConsumerX<T1, T2, X> func) {
		return new LBiObjCharConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDoubleConsumer<T1, T2>, T1, T2> LBiObjDoubleConsumerAssert.The<A, T1, T2> assertThat(LBiObjDoubleConsumer<T1, T2> func) {
		return new LBiObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjDoubleConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoubleConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjDoubleConsumerX<T1, T2, X> func) {
		return new LBiObjDoubleConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFloatConsumer<T1, T2>, T1, T2> LBiObjFloatConsumerAssert.The<A, T1, T2> assertThat(LBiObjFloatConsumer<T1, T2> func) {
		return new LBiObjFloatConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjFloatConsumerX<T1, T2, X> func) {
		return new LBiObjFloatConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumer<T1, T2>, T1, T2> LBiObjIntConsumerAssert.The<A, T1, T2> assertThat(LBiObjIntConsumer<T1, T2> func) {
		return new LBiObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjIntConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjIntConsumerX<T1, T2, X> func) {
		return new LBiObjIntConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumer<T1, T2>, T1, T2> LBiObjLongConsumerAssert.The<A, T1, T2> assertThat(LBiObjLongConsumer<T1, T2> func) {
		return new LBiObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjLongConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjLongConsumerX<T1, T2, X> func) {
		return new LBiObjLongConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjShortConsumer<T1, T2>, T1, T2> LBiObjShortConsumerAssert.The<A, T1, T2> assertThat(LBiObjShortConsumer<T1, T2> func) {
		return new LBiObjShortConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LBiObjShortConsumerX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortConsumerXAssert.The<A, T1, T2, X> assertThat(LBiObjShortConsumerX<T1, T2, X> func) {
		return new LBiObjShortConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LObjBoolConsumer<T>, T> LObjBoolConsumerAssert.The<A, T> assertThat(LObjBoolConsumer<T> func) {
		return new LObjBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjBoolConsumerX<T, X>, T, X extends Throwable> LObjBoolConsumerXAssert.The<A, T, X> assertThat(LObjBoolConsumerX<T, X> func) {
		return new LObjBoolConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LObjByteConsumer<T>, T> LObjByteConsumerAssert.The<A, T> assertThat(LObjByteConsumer<T> func) {
		return new LObjByteConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjByteConsumerX<T, X>, T, X extends Throwable> LObjByteConsumerXAssert.The<A, T, X> assertThat(LObjByteConsumerX<T, X> func) {
		return new LObjByteConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LObjCharConsumer<T>, T> LObjCharConsumerAssert.The<A, T> assertThat(LObjCharConsumer<T> func) {
		return new LObjCharConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjCharConsumerX<T, X>, T, X extends Throwable> LObjCharConsumerXAssert.The<A, T, X> assertThat(LObjCharConsumerX<T, X> func) {
		return new LObjCharConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LObjDoubleConsumer<T>, T> LObjDoubleConsumerAssert.The<A, T> assertThat(LObjDoubleConsumer<T> func) {
		return new LObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjDoubleConsumerX<T, X>, T, X extends Throwable> LObjDoubleConsumerXAssert.The<A, T, X> assertThat(LObjDoubleConsumerX<T, X> func) {
		return new LObjDoubleConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LObjFloatConsumer<T>, T> LObjFloatConsumerAssert.The<A, T> assertThat(LObjFloatConsumer<T> func) {
		return new LObjFloatConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjFloatConsumerX<T, X>, T, X extends Throwable> LObjFloatConsumerXAssert.The<A, T, X> assertThat(LObjFloatConsumerX<T, X> func) {
		return new LObjFloatConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LObjIntConsumer<T>, T> LObjIntConsumerAssert.The<A, T> assertThat(LObjIntConsumer<T> func) {
		return new LObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjIntConsumerX<T, X>, T, X extends Throwable> LObjIntConsumerXAssert.The<A, T, X> assertThat(LObjIntConsumerX<T, X> func) {
		return new LObjIntConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LObjLongConsumer<T>, T> LObjLongConsumerAssert.The<A, T> assertThat(LObjLongConsumer<T> func) {
		return new LObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjLongConsumerX<T, X>, T, X extends Throwable> LObjLongConsumerXAssert.The<A, T, X> assertThat(LObjLongConsumerX<T, X> func) {
		return new LObjLongConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LObjShortConsumer<T>, T> LObjShortConsumerAssert.The<A, T> assertThat(LObjShortConsumer<T> func) {
		return new LObjShortConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LObjShortConsumerX<T, X>, T, X extends Throwable> LObjShortConsumerXAssert.The<A, T, X> assertThat(LObjShortConsumerX<T, X> func) {
		return new LObjShortConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LTriBoolConsumer> LTriBoolConsumerAssert.The<A> assertThat(LTriBoolConsumer func) {
		return new LTriBoolConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LTriBoolConsumerX<X>, X extends Throwable> LTriBoolConsumerXAssert.The<A, X> assertThat(LTriBoolConsumerX<X> func) {
		return new LTriBoolConsumerXAssert.The(func);
	}

	@Nonnull
	default <A extends LBinaryOperator<T>, T> LBinaryOperatorAssert.The<A, ? extends OS, T> assertThat(LBinaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBinaryOperatorX<T, X>, T, X extends Throwable> LBinaryOperatorXAssert.The<A, ? extends OS, T, X> assertThat(LBinaryOperatorX<T, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteBinaryOperator> LByteBinaryOperatorAssert.The<A, ? extends AbstractByteAssert> assertThat(LByteBinaryOperator func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteBinaryOperatorX<X>, X extends Throwable> LByteBinaryOperatorXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LByteBinaryOperatorX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharBinaryOperator> LCharBinaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LCharBinaryOperator func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharBinaryOperatorX<X>, X extends Throwable> LCharBinaryOperatorXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LCharBinaryOperatorX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleBinaryOperator> LDoubleBinaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LDoubleBinaryOperator func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleBinaryOperatorX<X>, X extends Throwable> LDoubleBinaryOperatorXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleBinaryOperatorX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatBinaryOperator> LFloatBinaryOperatorAssert.The<A, ? extends AbstractFloatAssert> assertThat(LFloatBinaryOperator func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatBinaryOperatorX<X>, X extends Throwable> LFloatBinaryOperatorXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LFloatBinaryOperatorX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntBinaryOperator> LIntBinaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LIntBinaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntBinaryOperatorX<X>, X extends Throwable> LIntBinaryOperatorXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LIntBinaryOperatorX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalBinaryOperator> LLogicalBinaryOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LLogicalBinaryOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalBinaryOperatorX<X>, X extends Throwable> LLogicalBinaryOperatorXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalBinaryOperatorX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongBinaryOperator> LLongBinaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertThat(LLongBinaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongBinaryOperatorX<X>, X extends Throwable> LLongBinaryOperatorXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LLongBinaryOperatorX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortBinaryOperator> LShortBinaryOperatorAssert.The<A, ? extends AbstractShortAssert> assertThat(LShortBinaryOperator func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortBinaryOperatorX<X>, X extends Throwable> LShortBinaryOperatorXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LShortBinaryOperatorX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortBinaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalTernaryOperator> LLogicalTernaryOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LLogicalTernaryOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalTernaryOperatorX<X>, X extends Throwable> LLogicalTernaryOperatorXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalTernaryOperatorX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalTernaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTernaryOperator<T>, T> LTernaryOperatorAssert.The<A, ? extends OS, T> assertThat(LTernaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTernaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTernaryOperatorX<T, X>, T, X extends Throwable> LTernaryOperatorXAssert.The<A, ? extends OS, T, X> assertThat(LTernaryOperatorX<T, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTernaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteUnaryOperator> LByteUnaryOperatorAssert.The<A, ? extends AbstractByteAssert> assertThat(LByteUnaryOperator func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteUnaryOperatorX<X>, X extends Throwable> LByteUnaryOperatorXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LByteUnaryOperatorX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteUnaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharUnaryOperator> LCharUnaryOperatorAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LCharUnaryOperator func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharUnaryOperatorX<X>, X extends Throwable> LCharUnaryOperatorXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LCharUnaryOperatorX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharUnaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleUnaryOperator> LDoubleUnaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LDoubleUnaryOperator func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleUnaryOperatorX<X>, X extends Throwable> LDoubleUnaryOperatorXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleUnaryOperatorX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleUnaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatUnaryOperator> LFloatUnaryOperatorAssert.The<A, ? extends AbstractFloatAssert> assertThat(LFloatUnaryOperator func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatUnaryOperatorX<X>, X extends Throwable> LFloatUnaryOperatorXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LFloatUnaryOperatorX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatUnaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntUnaryOperator> LIntUnaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LIntUnaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntUnaryOperatorX<X>, X extends Throwable> LIntUnaryOperatorXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LIntUnaryOperatorX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntUnaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalOperator> LLogicalOperatorAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LLogicalOperator func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLogicalOperatorX<X>, X extends Throwable> LLogicalOperatorXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LLogicalOperatorX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLogicalOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongUnaryOperator> LLongUnaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertThat(LLongUnaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongUnaryOperatorX<X>, X extends Throwable> LLongUnaryOperatorXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LLongUnaryOperatorX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongUnaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortUnaryOperator> LShortUnaryOperatorAssert.The<A, ? extends AbstractShortAssert> assertThat(LShortUnaryOperator func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortUnaryOperatorX<X>, X extends Throwable> LShortUnaryOperatorXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LShortUnaryOperatorX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortUnaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LUnaryOperator<T>, T> LUnaryOperatorAssert.The<A, ? extends OS, T> assertThat(LUnaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LUnaryOperatorX<T, X>, T, X extends Throwable> LUnaryOperatorXAssert.The<A, ? extends OS, T, X> assertThat(LUnaryOperatorX<T, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LUnaryOperatorXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunction<T1, T2, R>, T1, T2, R> LBiFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFunction<T, R>, T, R> LFunctionAssert.The<A, ? extends OS, T, R> assertThat(LFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFunctionX<T, R, X>, T, R, X extends Throwable> LFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunction<T1, T2, T3, R>, T1, T2, T3, R> LTriFunctionAssert.The<A, ? extends OS, T1, T2, T3, R> assertThat(LTriFunction<T1, T2, T3, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTriFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriFunctionX<T1, T2, T3, R, X>, T1, T2, T3, R, X extends Throwable> LTriFunctionXAssert.The<A, ? extends OS, T1, T2, T3, R, X> assertThat(LTriFunctionX<T1, T2, T3, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTriFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToByteFunction> LBoolToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LBoolToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LBoolToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToByteFunctionX<X>, X extends Throwable> LBoolToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LBoolToByteFunctionX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LBoolToByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToCharFunction> LBoolToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LBoolToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LBoolToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToCharFunctionX<X>, X extends Throwable> LBoolToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LBoolToCharFunctionX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LBoolToCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToDoubleFunction> LBoolToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LBoolToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LBoolToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToDoubleFunctionX<X>, X extends Throwable> LBoolToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LBoolToDoubleFunctionX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LBoolToDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToFloatFunction> LBoolToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LBoolToFloatFunction func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LBoolToFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToFloatFunctionX<X>, X extends Throwable> LBoolToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LBoolToFloatFunctionX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LBoolToFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToIntFunction> LBoolToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LBoolToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBoolToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToIntFunctionX<X>, X extends Throwable> LBoolToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LBoolToIntFunctionX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LBoolToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToLongFunction> LBoolToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LBoolToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LBoolToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToLongFunctionX<X>, X extends Throwable> LBoolToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LBoolToLongFunctionX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LBoolToLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToShortFunction> LBoolToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LBoolToShortFunction func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LBoolToShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolToShortFunctionX<X>, X extends Throwable> LBoolToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LBoolToShortFunctionX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LBoolToShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToCharFunction> LByteToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LByteToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LByteToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToCharFunctionX<X>, X extends Throwable> LByteToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LByteToCharFunctionX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LByteToCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToDoubleFunction> LByteToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LByteToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LByteToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToDoubleFunctionX<X>, X extends Throwable> LByteToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LByteToDoubleFunctionX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LByteToDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToFloatFunction> LByteToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LByteToFloatFunction func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LByteToFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToFloatFunctionX<X>, X extends Throwable> LByteToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LByteToFloatFunctionX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LByteToFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToIntFunction> LByteToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LByteToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToIntFunctionX<X>, X extends Throwable> LByteToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LByteToIntFunctionX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LByteToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToLongFunction> LByteToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LByteToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LByteToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToLongFunctionX<X>, X extends Throwable> LByteToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LByteToLongFunctionX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LByteToLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToShortFunction> LByteToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LByteToShortFunction func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LByteToShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteToShortFunctionX<X>, X extends Throwable> LByteToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LByteToShortFunctionX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LByteToShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToByteFunction> LCharToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LCharToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LCharToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToByteFunctionX<X>, X extends Throwable> LCharToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LCharToByteFunctionX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LCharToByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToDoubleFunction> LCharToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LCharToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LCharToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToDoubleFunctionX<X>, X extends Throwable> LCharToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LCharToDoubleFunctionX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LCharToDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToFloatFunction> LCharToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LCharToFloatFunction func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LCharToFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToFloatFunctionX<X>, X extends Throwable> LCharToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LCharToFloatFunctionX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LCharToFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToIntFunction> LCharToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LCharToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToIntFunctionX<X>, X extends Throwable> LCharToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LCharToIntFunctionX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LCharToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToLongFunction> LCharToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LCharToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LCharToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToLongFunctionX<X>, X extends Throwable> LCharToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LCharToLongFunctionX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LCharToLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToShortFunction> LCharToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LCharToShortFunction func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LCharToShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharToShortFunctionX<X>, X extends Throwable> LCharToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LCharToShortFunctionX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LCharToShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToByteFunction> LDoubleToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LDoubleToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LDoubleToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToByteFunctionX<X>, X extends Throwable> LDoubleToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LDoubleToByteFunctionX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LDoubleToByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToCharFunction> LDoubleToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LDoubleToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LDoubleToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToCharFunctionX<X>, X extends Throwable> LDoubleToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LDoubleToCharFunctionX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LDoubleToCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToFloatFunction> LDoubleToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LDoubleToFloatFunction func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LDoubleToFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToFloatFunctionX<X>, X extends Throwable> LDoubleToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LDoubleToFloatFunctionX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LDoubleToFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToIntFunction> LDoubleToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LDoubleToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDoubleToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToIntFunctionX<X>, X extends Throwable> LDoubleToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LDoubleToIntFunctionX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LDoubleToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToLongFunction> LDoubleToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LDoubleToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LDoubleToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToLongFunctionX<X>, X extends Throwable> LDoubleToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LDoubleToLongFunctionX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LDoubleToLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToShortFunction> LDoubleToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LDoubleToShortFunction func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LDoubleToShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleToShortFunctionX<X>, X extends Throwable> LDoubleToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LDoubleToShortFunctionX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LDoubleToShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToByteFunction> LFloatToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LFloatToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LFloatToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToByteFunctionX<X>, X extends Throwable> LFloatToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LFloatToByteFunctionX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LFloatToByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToCharFunction> LFloatToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LFloatToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LFloatToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToCharFunctionX<X>, X extends Throwable> LFloatToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LFloatToCharFunctionX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LFloatToCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToDoubleFunction> LFloatToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LFloatToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LFloatToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToDoubleFunctionX<X>, X extends Throwable> LFloatToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LFloatToDoubleFunctionX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LFloatToDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToIntFunction> LFloatToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LFloatToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFloatToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToIntFunctionX<X>, X extends Throwable> LFloatToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LFloatToIntFunctionX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LFloatToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToLongFunction> LFloatToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LFloatToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LFloatToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToLongFunctionX<X>, X extends Throwable> LFloatToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LFloatToLongFunctionX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LFloatToLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToShortFunction> LFloatToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LFloatToShortFunction func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LFloatToShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatToShortFunctionX<X>, X extends Throwable> LFloatToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LFloatToShortFunctionX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LFloatToShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToByteFunction> LIntToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LIntToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LIntToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToByteFunctionX<X>, X extends Throwable> LIntToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LIntToByteFunctionX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LIntToByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToCharFunction> LIntToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LIntToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LIntToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToCharFunctionX<X>, X extends Throwable> LIntToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LIntToCharFunctionX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LIntToCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToDoubleFunction> LIntToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LIntToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LIntToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToDoubleFunctionX<X>, X extends Throwable> LIntToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LIntToDoubleFunctionX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LIntToDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToFloatFunction> LIntToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LIntToFloatFunction func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LIntToFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToFloatFunctionX<X>, X extends Throwable> LIntToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LIntToFloatFunctionX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LIntToFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToLongFunction> LIntToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LIntToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LIntToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToLongFunctionX<X>, X extends Throwable> LIntToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LIntToLongFunctionX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LIntToLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToShortFunction> LIntToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LIntToShortFunction func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LIntToShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntToShortFunctionX<X>, X extends Throwable> LIntToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LIntToShortFunctionX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LIntToShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToByteFunction> LLongToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LLongToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LLongToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToByteFunctionX<X>, X extends Throwable> LLongToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LLongToByteFunctionX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LLongToByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToCharFunction> LLongToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LLongToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LLongToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToCharFunctionX<X>, X extends Throwable> LLongToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LLongToCharFunctionX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LLongToCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToDoubleFunction> LLongToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LLongToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LLongToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToDoubleFunctionX<X>, X extends Throwable> LLongToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LLongToDoubleFunctionX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LLongToDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToFloatFunction> LLongToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LLongToFloatFunction func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LLongToFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToFloatFunctionX<X>, X extends Throwable> LLongToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LLongToFloatFunctionX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LLongToFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToIntFunction> LLongToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LLongToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToIntFunctionX<X>, X extends Throwable> LLongToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LLongToIntFunctionX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LLongToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToShortFunction> LLongToShortFunctionAssert.The<A, ? extends AbstractShortAssert> assertThat(LLongToShortFunction func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LLongToShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongToShortFunctionX<X>, X extends Throwable> LLongToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LLongToShortFunctionX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LLongToShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToByteFunction> LShortToByteFunctionAssert.The<A, ? extends AbstractByteAssert> assertThat(LShortToByteFunction func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LShortToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToByteFunctionX<X>, X extends Throwable> LShortToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LShortToByteFunctionX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LShortToByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToCharFunction> LShortToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LShortToCharFunction func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LShortToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToCharFunctionX<X>, X extends Throwable> LShortToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LShortToCharFunctionX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LShortToCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToDoubleFunction> LShortToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LShortToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LShortToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToDoubleFunctionX<X>, X extends Throwable> LShortToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LShortToDoubleFunctionX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LShortToDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToFloatFunction> LShortToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert> assertThat(LShortToFloatFunction func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LShortToFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToFloatFunctionX<X>, X extends Throwable> LShortToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LShortToFloatFunctionX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LShortToFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToIntFunction> LShortToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LShortToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LShortToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToIntFunctionX<X>, X extends Throwable> LShortToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LShortToIntFunctionX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LShortToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToLongFunction> LShortToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(LShortToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LShortToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortToLongFunctionX<X>, X extends Throwable> LShortToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LShortToLongFunctionX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LShortToLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBoolFunction<R>, R> LBiBoolFunctionAssert.The<A, ? extends OS, R> assertThat(LBiBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBoolFunctionX<R, X>, R, X extends Throwable> LBiBoolFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiBoolFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiBoolFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiByteFunction<R>, R> LBiByteFunctionAssert.The<A, ? extends OS, R> assertThat(LBiByteFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiByteFunctionX<R, X>, R, X extends Throwable> LBiByteFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiByteFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunction<R>, R> LBiCharFunctionAssert.The<A, ? extends OS, R> assertThat(LBiCharFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharFunctionX<R, X>, R, X extends Throwable> LBiCharFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiCharFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoubleFunction<R>, R> LBiDoubleFunctionAssert.The<A, ? extends OS, R> assertThat(LBiDoubleFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoubleFunctionX<R, X>, R, X extends Throwable> LBiDoubleFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiDoubleFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatFunction<R>, R> LBiFloatFunctionAssert.The<A, ? extends OS, R> assertThat(LBiFloatFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatFunctionX<R, X>, R, X extends Throwable> LBiFloatFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiFloatFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunction<R>, R> LBiIntFunctionAssert.The<A, ? extends OS, R> assertThat(LBiIntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntFunctionX<R, X>, R, X extends Throwable> LBiIntFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiIntFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunction<R>, R> LBiLongFunctionAssert.The<A, ? extends OS, R> assertThat(LBiLongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongFunctionX<R, X>, R, X extends Throwable> LBiLongFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiLongFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunction<T1, T2, R>, T1, T2, R> LBiObjBoolFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjBoolFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjBoolFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjBoolFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjBoolFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunction<T1, T2, R>, T1, T2, R> LBiObjByteFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjByteFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjByteFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjByteFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjByteFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunction<T1, T2, R>, T1, T2, R> LBiObjCharFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjCharFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjCharFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjCharFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoubleFunction<T1, T2, R>, T1, T2, R> LBiObjDoubleFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjDoubleFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoubleFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjDoubleFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjDoubleFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatFunction<T1, T2, R>, T1, T2, R> LBiObjFloatFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjFloatFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjFloatFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjFloatFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunction<T1, T2, R>, T1, T2, R> LBiObjIntFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjIntFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjIntFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjIntFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunction<T1, T2, R>, T1, T2, R> LBiObjLongFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjLongFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjLongFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjLongFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortFunction<T1, T2, R>, T1, T2, R> LBiObjShortFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(LBiObjShortFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortFunctionX<T1, T2, R, X>, T1, T2, R, X extends Throwable> LBiObjShortFunctionXAssert.The<A, ? extends OS, T1, T2, R, X> assertThat(LBiObjShortFunctionX<T1, T2, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiObjShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortFunction<R>, R> LBiShortFunctionAssert.The<A, ? extends OS, R> assertThat(LBiShortFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortFunctionX<R, X>, R, X extends Throwable> LBiShortFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBiShortFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBiShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolFunction<R>, R> LBoolFunctionAssert.The<A, ? extends OS, R> assertThat(LBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolFunctionX<R, X>, R, X extends Throwable> LBoolFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LBoolFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LBoolFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteFunction<R>, R> LByteFunctionAssert.The<A, ? extends OS, R> assertThat(LByteFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteFunctionX<R, X>, R, X extends Throwable> LByteFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LByteFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharFunction<R>, R> LCharFunctionAssert.The<A, ? extends OS, R> assertThat(LCharFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharFunctionX<R, X>, R, X extends Throwable> LCharFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LCharFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleFunction<R>, R> LDoubleFunctionAssert.The<A, ? extends OS, R> assertThat(LDoubleFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleFunctionX<R, X>, R, X extends Throwable> LDoubleFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LDoubleFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatFunction<R>, R> LFloatFunctionAssert.The<A, ? extends OS, R> assertThat(LFloatFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatFunctionX<R, X>, R, X extends Throwable> LFloatFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LFloatFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntFunction<R>, R> LIntFunctionAssert.The<A, ? extends OS, R> assertThat(LIntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntFunctionX<R, X>, R, X extends Throwable> LIntFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LIntFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongFunction<R>, R> LLongFunctionAssert.The<A, ? extends OS, R> assertThat(LLongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongFunctionX<R, X>, R, X extends Throwable> LLongFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LLongFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolFunction<T, R>, T, R> LObjBoolFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjBoolFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolFunctionX<T, R, X>, T, R, X extends Throwable> LObjBoolFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjBoolFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjBoolFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunction<T, R>, T, R> LObjByteFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjByteFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjByteFunctionX<T, R, X>, T, R, X extends Throwable> LObjByteFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjByteFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunction<T, R>, T, R> LObjCharFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjCharFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharFunctionX<T, R, X>, T, R, X extends Throwable> LObjCharFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjCharFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoubleFunction<T, R>, T, R> LObjDoubleFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjDoubleFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoubleFunctionX<T, R, X>, T, R, X extends Throwable> LObjDoubleFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjDoubleFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatFunction<T, R>, T, R> LObjFloatFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjFloatFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatFunctionX<T, R, X>, T, R, X extends Throwable> LObjFloatFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjFloatFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFunction<T, R>, T, R> LObjIntFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjIntFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntFunctionX<T, R, X>, T, R, X extends Throwable> LObjIntFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjIntFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunction<T, R>, T, R> LObjLongFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjLongFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongFunctionX<T, R, X>, T, R, X extends Throwable> LObjLongFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjLongFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortFunction<T, R>, T, R> LObjShortFunctionAssert.The<A, ? extends OS, T, R> assertThat(LObjShortFunction<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortFunctionX<T, R, X>, T, R, X extends Throwable> LObjShortFunctionXAssert.The<A, ? extends OS, T, R, X> assertThat(LObjShortFunctionX<T, R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LObjShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortFunction<R>, R> LShortFunctionAssert.The<A, ? extends OS, R> assertThat(LShortFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortFunctionX<R, X>, R, X extends Throwable> LShortFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LShortFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriBoolFunction<R>, R> LTriBoolFunctionAssert.The<A, ? extends OS, R> assertThat(LTriBoolFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTriBoolFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriBoolFunctionX<R, X>, R, X extends Throwable> LTriBoolFunctionXAssert.The<A, ? extends OS, R, X> assertThat(LTriBoolFunctionX<R, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LTriBoolFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntToIntFunction<T>, T> LObjIntToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertThat(LObjIntToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjIntToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntToIntFunctionX<T, X>, T, X extends Throwable> LObjIntToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, T, X> assertThat(LObjIntToIntFunctionX<T, X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LObjIntToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunction<T1, T2>, T1, T2> LToByteBiFunctionAssert.The<A, ? extends AbstractByteAssert, T1, T2> assertThat(LToByteBiFunction<T1, T2> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToByteBiFunctionXAssert.The<A, ? extends AbstractByteAssert, T1, T2, X> assertThat(LToByteBiFunctionX<T1, T2, X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteBiFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteFunction<T>, T> LToByteFunctionAssert.The<A, ? extends AbstractByteAssert, T> assertThat(LToByteFunction<T> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToByteFunctionX<T, X>, T, X extends Throwable> LToByteFunctionXAssert.The<A, ? extends AbstractByteAssert, T, X> assertThat(LToByteFunctionX<T, X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LToByteFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunction<T1, T2>, T1, T2> LToCharBiFunctionAssert.The<A, ? extends AbstractCharacterAssert, T1, T2> assertThat(LToCharBiFunction<T1, T2> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToCharBiFunctionXAssert.The<A, ? extends AbstractCharacterAssert, T1, T2, X> assertThat(LToCharBiFunctionX<T1, T2, X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharBiFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharFunction<T>, T> LToCharFunctionAssert.The<A, ? extends AbstractCharacterAssert, T> assertThat(LToCharFunction<T> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToCharFunctionX<T, X>, T, X extends Throwable> LToCharFunctionXAssert.The<A, ? extends AbstractCharacterAssert, T, X> assertThat(LToCharFunctionX<T, X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LToCharFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleBiFunction<T1, T2>, T1, T2> LToDoubleBiFunctionAssert.The<A, ? extends AbstractDoubleAssert, T1, T2> assertThat(LToDoubleBiFunction<T1, T2> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToDoubleBiFunctionXAssert.The<A, ? extends AbstractDoubleAssert, T1, T2, X> assertThat(LToDoubleBiFunctionX<T1, T2, X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleBiFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleFunction<T>, T> LToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> assertThat(LToDoubleFunction<T> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToDoubleFunctionX<T, X>, T, X extends Throwable> LToDoubleFunctionXAssert.The<A, ? extends AbstractDoubleAssert, T, X> assertThat(LToDoubleFunctionX<T, X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LToDoubleFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatBiFunction<T1, T2>, T1, T2> LToFloatBiFunctionAssert.The<A, ? extends AbstractFloatAssert, T1, T2> assertThat(LToFloatBiFunction<T1, T2> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToFloatBiFunctionXAssert.The<A, ? extends AbstractFloatAssert, T1, T2, X> assertThat(LToFloatBiFunctionX<T1, T2, X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatBiFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatFunction<T>, T> LToFloatFunctionAssert.The<A, ? extends AbstractFloatAssert, T> assertThat(LToFloatFunction<T> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToFloatFunctionX<T, X>, T, X extends Throwable> LToFloatFunctionXAssert.The<A, ? extends AbstractFloatAssert, T, X> assertThat(LToFloatFunctionX<T, X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LToFloatFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunction<T1, T2>, T1, T2> LToIntBiFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertThat(LToIntBiFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToIntBiFunctionXAssert.The<A, ? extends AbstractIntegerAssert, T1, T2, X> assertThat(LToIntBiFunctionX<T1, T2, X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntBiFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntFunction<T>, T> LToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertThat(LToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToIntFunctionX<T, X>, T, X extends Throwable> LToIntFunctionXAssert.The<A, ? extends AbstractIntegerAssert, T, X> assertThat(LToIntFunctionX<T, X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LToIntFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunction<T1, T2>, T1, T2> LToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> assertThat(LToLongBiFunction<T1, T2> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToLongBiFunctionXAssert.The<A, ? extends AbstractLongAssert, T1, T2, X> assertThat(LToLongBiFunctionX<T1, T2, X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongBiFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongFunction<T>, T> LToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> assertThat(LToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToLongFunctionX<T, X>, T, X extends Throwable> LToLongFunctionXAssert.The<A, ? extends AbstractLongAssert, T, X> assertThat(LToLongFunctionX<T, X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LToLongFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToShortBiFunction<T1, T2>, T1, T2> LToShortBiFunctionAssert.The<A, ? extends AbstractShortAssert, T1, T2> assertThat(LToShortBiFunction<T1, T2> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToShortBiFunctionX<T1, T2, X>, T1, T2, X extends Throwable> LToShortBiFunctionXAssert.The<A, ? extends AbstractShortAssert, T1, T2, X> assertThat(LToShortBiFunctionX<T1, T2, X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortBiFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToShortFunction<T>, T> LToShortFunctionAssert.The<A, ? extends AbstractShortAssert, T> assertThat(LToShortFunction<T> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LToShortFunctionX<T, X>, T, X extends Throwable> LToShortFunctionXAssert.The<A, ? extends AbstractShortAssert, T, X> assertThat(LToShortFunctionX<T, X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LToShortFunctionXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicate> LBiBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiBytePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiBytePredicateX<X>, X extends Throwable> LBiBytePredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiBytePredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiBytePredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicate> LBiCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiCharPredicateX<X>, X extends Throwable> LBiCharPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiCharPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiCharPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoublePredicate> LBiDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiDoublePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiDoublePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiDoublePredicateX<X>, X extends Throwable> LBiDoublePredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiDoublePredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiDoublePredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatPredicate> LBiFloatPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiFloatPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiFloatPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiFloatPredicateX<X>, X extends Throwable> LBiFloatPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiFloatPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiFloatPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicate> LBiIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiIntPredicateX<X>, X extends Throwable> LBiIntPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiIntPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiIntPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicate> LBiLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiLongPredicateX<X>, X extends Throwable> LBiLongPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiLongPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiLongPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicate<T1, T2>, T1, T2> LBiObjBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjBoolPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBoolPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBoolPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjBoolPredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjBoolPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicate<T1, T2>, T1, T2> LBiObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjBytePredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjBytePredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjBytePredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjBytePredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjBytePredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicate<T1, T2>, T1, T2> LBiObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjCharPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjCharPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjCharPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjCharPredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjCharPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoublePredicate<T1, T2>, T1, T2> LBiObjDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjDoublePredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjDoublePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjDoublePredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjDoublePredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjDoublePredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjDoublePredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatPredicate<T1, T2>, T1, T2> LBiObjFloatPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjFloatPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjFloatPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjFloatPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjFloatPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjFloatPredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjFloatPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicate<T1, T2>, T1, T2> LBiObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjIntPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjIntPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjIntPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjIntPredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjIntPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicate<T1, T2>, T1, T2> LBiObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjLongPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjLongPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjLongPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjLongPredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjLongPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortPredicate<T1, T2>, T1, T2> LBiObjShortPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiObjShortPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjShortPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiObjShortPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiObjShortPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiObjShortPredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiObjShortPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicate<T1, T2>, T1, T2> LBiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(LBiPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiPredicateX<T1, T2, X>, T1, T2, X extends Throwable> LBiPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, X> assertThat(LBiPredicateX<T1, T2, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortPredicate> LBiShortPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBiShortPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiShortPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBiShortPredicateX<X>, X extends Throwable> LBiShortPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBiShortPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBiShortPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBytePredicate> LBytePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBytePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBytePredicateX<X>, X extends Throwable> LBytePredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBytePredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBytePredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharPredicate> LCharPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LCharPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharPredicateX<X>, X extends Throwable> LCharPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LCharPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LCharPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoublePredicate> LDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LDoublePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDoublePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoublePredicateX<X>, X extends Throwable> LDoublePredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LDoublePredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LDoublePredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatPredicate> LFloatPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LFloatPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFloatPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatPredicateX<X>, X extends Throwable> LFloatPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LFloatPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LFloatPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntPredicate> LIntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LIntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntPredicateX<X>, X extends Throwable> LIntPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LIntPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LIntPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongPredicate> LLongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LLongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongPredicateX<X>, X extends Throwable> LLongPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LLongPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LLongPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolPredicate<T>, T> LObjBoolPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjBoolPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBoolPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBoolPredicateX<T, X>, T, X extends Throwable> LObjBoolPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjBoolPredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBoolPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicate<T>, T> LObjBytePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjBytePredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBytePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjBytePredicateX<T, X>, T, X extends Throwable> LObjBytePredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjBytePredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjBytePredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicate<T>, T> LObjCharPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjCharPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjCharPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjCharPredicateX<T, X>, T, X extends Throwable> LObjCharPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjCharPredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjCharPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoublePredicate<T>, T> LObjDoublePredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjDoublePredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjDoublePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjDoublePredicateX<T, X>, T, X extends Throwable> LObjDoublePredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjDoublePredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjDoublePredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatPredicate<T>, T> LObjFloatPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjFloatPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjFloatPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjFloatPredicateX<T, X>, T, X extends Throwable> LObjFloatPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjFloatPredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjFloatPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicate<T>, T> LObjIntPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjIntPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjIntPredicateX<T, X>, T, X extends Throwable> LObjIntPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjIntPredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjIntPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicate<T>, T> LObjLongPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjLongPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjLongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjLongPredicateX<T, X>, T, X extends Throwable> LObjLongPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjLongPredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjLongPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortPredicate<T>, T> LObjShortPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LObjShortPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjShortPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LObjShortPredicateX<T, X>, T, X extends Throwable> LObjShortPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LObjShortPredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LObjShortPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LPredicate<T>, T> LPredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(LPredicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LPredicateX<T, X>, T, X extends Throwable> LPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T, X> assertThat(LPredicateX<T, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortPredicate> LShortPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LShortPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LShortPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortPredicateX<X>, X extends Throwable> LShortPredicateXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LShortPredicateX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LShortPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicate<T1, T2, T3>, T1, T2, T3> LTriPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3> assertThat(LTriPredicate<T1, T2, T3> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LTriPredicateX<T1, T2, T3, X>, T1, T2, T3, X extends Throwable> LTriPredicateXAssert.The<A, ? extends AbstractBooleanAssert, T1, T2, T3, X> assertThat(LTriPredicateX<T1, T2, T3, X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LTriPredicateXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolSupplier> LBoolSupplierAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LBoolSupplier func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBoolSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LBoolSupplierX<X>, X extends Throwable> LBoolSupplierXAssert.The<A, ? extends AbstractBooleanAssert, X> assertThat(LBoolSupplierX<X> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LBoolSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteSupplier> LByteSupplierAssert.The<A, ? extends AbstractByteAssert> assertThat(LByteSupplier func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LByteSupplierX<X>, X extends Throwable> LByteSupplierXAssert.The<A, ? extends AbstractByteAssert, X> assertThat(LByteSupplierX<X> func) {
		LByteFunction<AbstractByteAssert> assertFunc = this::assertThatByte;
		return new LByteSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharSupplier> LCharSupplierAssert.The<A, ? extends AbstractCharacterAssert> assertThat(LCharSupplier func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LCharSupplierX<X>, X extends Throwable> LCharSupplierXAssert.The<A, ? extends AbstractCharacterAssert, X> assertThat(LCharSupplierX<X> func) {
		LCharFunction<AbstractCharacterAssert> assertFunc = this::assertThatChar;
		return new LCharSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleSupplier> LDoubleSupplierAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LDoubleSupplier func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LDoubleSupplierX<X>, X extends Throwable> LDoubleSupplierXAssert.The<A, ? extends AbstractDoubleAssert, X> assertThat(LDoubleSupplierX<X> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LDoubleSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatSupplier> LFloatSupplierAssert.The<A, ? extends AbstractFloatAssert> assertThat(LFloatSupplier func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LFloatSupplierX<X>, X extends Throwable> LFloatSupplierXAssert.The<A, ? extends AbstractFloatAssert, X> assertThat(LFloatSupplierX<X> func) {
		LFloatFunction<AbstractFloatAssert> assertFunc = this::assertThatFloat;
		return new LFloatSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntSupplier> LIntSupplierAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LIntSupplier func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LIntSupplierX<X>, X extends Throwable> LIntSupplierXAssert.The<A, ? extends AbstractIntegerAssert, X> assertThat(LIntSupplierX<X> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LIntSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongSupplier> LLongSupplierAssert.The<A, ? extends AbstractLongAssert> assertThat(LLongSupplier func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LLongSupplierX<X>, X extends Throwable> LLongSupplierXAssert.The<A, ? extends AbstractLongAssert, X> assertThat(LLongSupplierX<X> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LLongSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortSupplier> LShortSupplierAssert.The<A, ? extends AbstractShortAssert> assertThat(LShortSupplier func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LShortSupplierX<X>, X extends Throwable> LShortSupplierXAssert.The<A, ? extends AbstractShortAssert, X> assertThat(LShortSupplierX<X> func) {
		LShortFunction<AbstractShortAssert> assertFunc = this::assertThatShort;
		return new LShortSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSupplier<T>, T> LSupplierAssert.The<A, ? extends OS, T> assertThat(LSupplier<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LSupplierX<T, X>, T, X extends Throwable> LSupplierXAssert.The<A, ? extends OS, T, X> assertThat(LSupplierX<T, X> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LSupplierXAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Runnable> RunnableAssert.The<A> assertThat(Runnable func) {
		return new RunnableAssert.The(func);
	}

	@Nonnull
	default <A extends BiConsumer<T1, T2>, T1, T2> BiConsumerAssert.The<A, T1, T2> assertThat(BiConsumer<T1, T2> func) {
		return new BiConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends Consumer<T>, T> ConsumerAssert.The<A, T> assertThat(Consumer<T> func) {
		return new ConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends DoubleConsumer> DoubleConsumerAssert.The<A> assertThat(DoubleConsumer func) {
		return new DoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends IntConsumer> IntConsumerAssert.The<A> assertThat(IntConsumer func) {
		return new IntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends LongConsumer> LongConsumerAssert.The<A> assertThat(LongConsumer func) {
		return new LongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjDoubleConsumer<T>, T> ObjDoubleConsumerAssert.The<A, T> assertThat(ObjDoubleConsumer<T> func) {
		return new ObjDoubleConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjIntConsumer<T>, T> ObjIntConsumerAssert.The<A, T> assertThat(ObjIntConsumer<T> func) {
		return new ObjIntConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends ObjLongConsumer<T>, T> ObjLongConsumerAssert.The<A, T> assertThat(ObjLongConsumer<T> func) {
		return new ObjLongConsumerAssert.The(func);
	}

	@Nonnull
	default <A extends BinaryOperator<T>, T> BinaryOperatorAssert.The<A, ? extends OS, T> assertThat(BinaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new BinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleBinaryOperator> DoubleBinaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertThat(DoubleBinaryOperator func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleUnaryOperator> DoubleUnaryOperatorAssert.The<A, ? extends AbstractDoubleAssert> assertThat(DoubleUnaryOperator func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntBinaryOperator> IntBinaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertThat(IntBinaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntUnaryOperator> IntUnaryOperatorAssert.The<A, ? extends AbstractIntegerAssert> assertThat(IntUnaryOperator func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongBinaryOperator> LongBinaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertThat(LongBinaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongBinaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongUnaryOperator> LongUnaryOperatorAssert.The<A, ? extends AbstractLongAssert> assertThat(LongUnaryOperator func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongUnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends UnaryOperator<T>, T> UnaryOperatorAssert.The<A, ? extends OS, T> assertThat(UnaryOperator<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new UnaryOperatorAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BiFunction<T1, T2, R>, T1, T2, R> BiFunctionAssert.The<A, ? extends OS, T1, T2, R> assertThat(BiFunction<T1, T2, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new BiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleFunction<R>, R> DoubleFunctionAssert.The<A, ? extends OS, R> assertThat(DoubleFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new DoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToIntFunction> DoubleToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(DoubleToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new DoubleToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleToLongFunction> DoubleToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(DoubleToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new DoubleToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Function<T, R>, T, R> FunctionAssert.The<A, ? extends OS, T, R> assertThat(Function<T, R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new FunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntFunction<R>, R> IntFunctionAssert.The<A, ? extends OS, R> assertThat(IntFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new IntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntToDoubleFunction> IntToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(IntToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new IntToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntToLongFunction> IntToLongFunctionAssert.The<A, ? extends AbstractLongAssert> assertThat(IntToLongFunction func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new IntToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongFunction<R>, R> LongFunctionAssert.The<A, ? extends OS, R> assertThat(LongFunction<R> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new LongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongToDoubleFunction> LongToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert> assertThat(LongToDoubleFunction func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new LongToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongToIntFunction> LongToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert> assertThat(LongToIntFunction func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new LongToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleBiFunction<T1, T2>, T1, T2> ToDoubleBiFunctionAssert.The<A, ? extends AbstractDoubleAssert, T1, T2> assertThat(ToDoubleBiFunction<T1, T2> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new ToDoubleBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToDoubleFunction<T>, T> ToDoubleFunctionAssert.The<A, ? extends AbstractDoubleAssert, T> assertThat(ToDoubleFunction<T> func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new ToDoubleFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToIntBiFunction<T1, T2>, T1, T2> ToIntBiFunctionAssert.The<A, ? extends AbstractIntegerAssert, T1, T2> assertThat(ToIntBiFunction<T1, T2> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new ToIntBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToIntFunction<T>, T> ToIntFunctionAssert.The<A, ? extends AbstractIntegerAssert, T> assertThat(ToIntFunction<T> func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new ToIntFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToLongBiFunction<T1, T2>, T1, T2> ToLongBiFunctionAssert.The<A, ? extends AbstractLongAssert, T1, T2> assertThat(ToLongBiFunction<T1, T2> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new ToLongBiFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends ToLongFunction<T>, T> ToLongFunctionAssert.The<A, ? extends AbstractLongAssert, T> assertThat(ToLongFunction<T> func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new ToLongFunctionAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BiPredicate<T1, T2>, T1, T2> BiPredicateAssert.The<A, ? extends AbstractBooleanAssert, T1, T2> assertThat(BiPredicate<T1, T2> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new BiPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoublePredicate> DoublePredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(DoublePredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new DoublePredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntPredicate> IntPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(IntPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new IntPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongPredicate> LongPredicateAssert.The<A, ? extends AbstractBooleanAssert> assertThat(LongPredicate func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new LongPredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Predicate<T>, T> PredicateAssert.The<A, ? extends AbstractBooleanAssert, T> assertThat(Predicate<T> func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new PredicateAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends BooleanSupplier> BooleanSupplierAssert.The<A, ? extends AbstractBooleanAssert> assertThat(BooleanSupplier func) {
		LBoolFunction<AbstractBooleanAssert> assertFunc = this::assertThatBool;
		return new BooleanSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends DoubleSupplier> DoubleSupplierAssert.The<A, ? extends AbstractDoubleAssert> assertThat(DoubleSupplier func) {
		LDoubleFunction<AbstractDoubleAssert> assertFunc = this::assertThatDouble;
		return new DoubleSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends IntSupplier> IntSupplierAssert.The<A, ? extends AbstractIntegerAssert> assertThat(IntSupplier func) {
		LIntFunction<AbstractIntegerAssert> assertFunc = this::assertThatInt;
		return new IntSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends LongSupplier> LongSupplierAssert.The<A, ? extends AbstractLongAssert> assertThat(LongSupplier func) {
		LLongFunction<AbstractLongAssert> assertFunc = this::assertThatLong;
		return new LongSupplierAssert.The(func, assertFunc);
	}

	@Nonnull
	default <A extends Supplier<T>, T> SupplierAssert.The<A, ? extends OS, T> assertThat(Supplier<T> func) { // NOSONAR
		// ?: makes possible to merge captures OS & RS
		LFunction<Object, OS> assertFunc = this::assertThatObj;
		return new SupplierAssert.The(func, assertFunc);
	}

}