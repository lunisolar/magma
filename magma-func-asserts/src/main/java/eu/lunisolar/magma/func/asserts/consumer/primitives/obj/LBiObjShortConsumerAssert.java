/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.asserts.consumer.primitives.obj;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*;
import eu.lunisolar.magma.func.action.LAction;

import static org.assertj.core.api.Fail.fail;

/** Assert for LBiObjShortConsumer. */
public interface LBiObjShortConsumerAssert<S extends LBiObjShortConsumerAssert<S, A, T1, T2>, A extends LBiObjShortConsumer<T1, T2>, T1, T2> extends Assert<S, A>, FunctionalAssert.Simple<S, A, Exception> {

	@Nonnull
	SemiEvaluation<S, A, Exception> doesAccept(T1 t1, T2 t2, short s);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends LBiObjShortConsumer<T1, T2>, T1, T2> extends Base<Impl<A, T1, T2>, A, T1, T2> {

		public Impl(A actual) {
			super(actual, Impl.class);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, T1, T2>, A extends LBiObjShortConsumer<T1, T2>, T1, T2> extends FunctionalAssert.Simple.Base<S, A, Exception> implements LBiObjShortConsumerAssert<S, A, T1, T2> {

		public Base(A actual, Class<?> selfType) {
			super(actual, selfType);
		}

		@Nonnull
		public SemiEvaluation<S, A, Exception> doesAccept(T1 t1, T2 t2, short s) {
			return evaluation(() -> actual.doAccept(t1, t2, s));
		}

	}

}
