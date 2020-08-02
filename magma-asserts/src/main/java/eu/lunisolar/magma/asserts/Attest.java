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
import java.util.*; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.*; // NOSONAR
import eu.lunisolar.magma.func.supp.check.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; // NOSONAR
import org.assertj.core.api.*; // NOSONAR

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
 * Extension over CheckTrait with specific purpose of unit test assertions. Advantage is that properly written assert class includes messages similar like AssertJ, otherwise
 * adhoc usage of CHeckTrait methods is still possible.     
 */
public interface Attest<SELF extends Attest<SELF, A>, A> extends Assert<SELF, A>, FluentTrait<SELF>, CheckTrait<A, SELF> {

	@Nullable
	A actual();

	@Deprecated
	@Nonnull
	@Override
	default SELF value(@Nullable A value) {
		throw X.unsupported(); // otherwise it would make assertions very confusing.
	}

	@Nullable
	@Override
	default A get() {
		return actual();
	}
	@Nonnull
	default String checkTraitType() {
		return "Actual";
	}
	@Nonnull
	default String checkTraitName() {
		return "actual";
	}
	@Nonnull
	default ExMF<RuntimeException> checkTraitFactory() {
		return ExMF.shoving(X::assertion);
	}
	@Override
	@Nonnull
	default MsgVerbosity verbosity() {
		return MsgVerbosity.ALL;
	}

	@Deprecated
	default <R> SELF has(LFunction<A, R> predicate, R expected, String message, Object... args) {
		isNotNull();
		R derivedActual = predicate.apply(actual());
		Assertions.assertThat(derivedActual).as(message, args).isEqualTo(expected);
		return self();
	}

	@Deprecated
	default SELF mustBe(LPredicate<A> predicate, boolean expected, String message, Object... args) {
		isNotNull();
		BooleanAssert as = (BooleanAssert) Assertions.assertThat(predicate.test(actual())).as(message, args);
		as.isEqualTo(expected);
		return self();
	}

	public static abstract class AbstractObjAttest<SELF extends AbstractObjAttest<SELF, A>, A> extends AbstractObjectAssert<SELF, A> implements Attest<SELF, A> {

		public AbstractObjAttest(A a, Class<?> selfType) {
			super(a, selfType);
		}

		@Nullable
		@Override
		public A actual() {
			return actual;
		}
	}

	public static class ObjAttest<A> extends AbstractObjAttest<ObjAttest<A>, A> implements Attest<ObjAttest<A>, A> {

		public ObjAttest(A a) {
			super(a, ObjAttest.class);
		}

		@Nullable
		@Override
		public A actual() {
			return actual;
		}
	}

	public static class ThrowableAttest<A extends Throwable> extends AbstractThrowableAssert<ThrowableAttest<A>, A> implements Attest<ThrowableAttest<A>, A> {

		public ThrowableAttest(A a) {
			super(a, ThrowableAttest.class);
		}

		@Nullable
		@Override
		public A actual() {
			return actual;
		}

		@Override
		protected ThrowableAttest<A> hasBeenThrown() {
			return super.hasBeenThrown();
		}
	}
}