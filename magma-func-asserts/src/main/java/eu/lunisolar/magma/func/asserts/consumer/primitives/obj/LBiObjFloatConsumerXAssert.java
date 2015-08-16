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
import eu.lunisolar.magma.func.action.*; // NOSONAR

import eu.lunisolar.magma.func.consumer.primitives.obj.*;
import eu.lunisolar.magma.func.consumer.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.action.LAction;

import static org.assertj.core.api.Fail.fail;

/** Assert for LBiObjFloatConsumerX. */
public interface LBiObjFloatConsumerXAssert<S extends LBiObjFloatConsumerXAssert<S, A, T1, T2, X>, A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable>
		extends
			Assert<S, A>,
			FunctionalAssert.Simple<S, LBiObjFloatConsumerX<T1, T2, Exception>, A, Exception> {

	@Nonnull
	SemiEvaluation<S, LBiObjFloatConsumerX<T1, T2, Exception>, A, Exception> doesAccept(T1 t1, T2 t2, float f);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class The<A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable> extends Base<The<A, T1, T2, X>, A, T1, T2, X> {

		public The(A actual) {
			super(actual, The.class);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, T1, T2, X>, A extends LBiObjFloatConsumerX<T1, T2, X>, T1, T2, X extends Throwable> extends FunctionalAssert.Simple.Base<S, LBiObjFloatConsumerX<T1, T2, Exception>, A, Exception>
			implements
				LBiObjFloatConsumerXAssert<S, A, T1, T2, X> {

		public Base(A actual, Class<?> selfType) {
			super(actual, selfType);
		}

		@Nonnull
		public SemiEvaluation<S, LBiObjFloatConsumerX<T1, T2, Exception>, A, Exception> doesAccept(T1 t1, T2 t2, float f) {

			return evaluation(pc -> {
				if (pc != null) {
					pc.doAccept(t1, t2, f);
				}
				actual.doAccept(t1, t2, f);
				return null;
			});

		}

	}

}
