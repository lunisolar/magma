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

package eu.lunisolar.magma.func.asserts.function.conversion;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

import eu.lunisolar.magma.func.function.conversion.*;
import eu.lunisolar.magma.func.consumer.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR; // NOSONAR
import eu.lunisolar.magma.func.action.LAction;

import static org.assertj.core.api.Fail.fail;

/** Assert for LShortToCharFunction. */
public interface LShortToCharFunctionAssert<S extends LShortToCharFunctionAssert<S, A, RS>, A extends LShortToCharFunction, RS extends AbstractCharacterAssert<RS>>
		extends
			Assert<S, A>,
			FullFunctionalAssert<S, LShortConsumerX<Exception>, A, RS, Character, Exception> {

	@Nonnull
	Evaluation<S, LShortConsumerX<Exception>, A, RS, Character, Exception> doesApplyAsChar(short s);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class The<A extends LShortToCharFunction, RS extends AbstractCharacterAssert<RS>> extends Base<The<A, RS>, A, RS> {

		public The(A actual, java.util.function.Function<Character, RS> assertFactory) {
			super(actual, The.class, assertFactory);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS>, A extends LShortToCharFunction, RS extends AbstractCharacterAssert<RS>> extends FullFunctionalAssert.Base<S, LShortConsumerX<Exception>, A, RS, Character, Exception>
			implements
				LShortToCharFunctionAssert<S, A, RS> {

		protected final java.util.function.Function<Character, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Character, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, LShortConsumerX<Exception>, A, RS, Character, Exception> doesApplyAsChar(short s) {

			return evaluation(pc -> {
				if (pc != null) {
					pc.doAccept(s);
				}
				return assertFactory.apply((Character) actual.doApplyAsChar(s));
			});

		}

	}

}
