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

package eu.lunisolar.magma.func.supp.access;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR

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
 * Interface representing a value(s) that can be optionally combined with additional arguments and function call that might produce some other value.
 * Interface implementation is not necessarily holding nor owning the value(s).
 */
@SuppressWarnings("UnusedDeclaration")
public interface AccessQuint<T1, T2, T3, T4, T5> {

	static <T1, T2, T3, T4, T5> AccessQuint<T1, T2, T3, T4, T5> wrap(AccessQuint<T1, T2, T3, T4, T5> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LQuint<T1, T2, T3, T4, T5> accessQuint();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseQuint(LQuint<T1, T2, T3, T4, T5> a) {
		// NOOP
	}

	default void useWith(LQuintConsumer<T1, T2, T3, T4, T5> accessFunction) {
		LQuint<T1, T2, T3, T4, T5> tuple = accessQuint();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
	}

	default <R> R useWith(LQuintFunction<T1, T2, T3, T4, T5, R> accessFunction) {
		LQuint<T1, T2, T3, T4, T5> tuple = accessQuint();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
		releaseQuint(tuple);
		return retval;
	}

	default boolean useWith(LQuintPredicate<T1, T2, T3, T4, T5> accessFunction) {
		LQuint<T1, T2, T3, T4, T5> tuple = accessQuint();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third(), tuple.fourth(), tuple.fifth());
		releaseQuint(tuple);
		return retval;
	}

}
