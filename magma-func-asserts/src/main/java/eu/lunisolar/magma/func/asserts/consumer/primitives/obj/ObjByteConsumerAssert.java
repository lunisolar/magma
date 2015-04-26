/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func.asserts.consumer.primitives.obj;

import eu.lunisolar.magma.basics.asserts.*; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*;
import eu.lunisolar.magma.func.action.Action;

import static org.assertj.core.api.Fail.fail;

/** Assert for ObjByteConsumer. */
public interface ObjByteConsumerAssert<S extends ObjByteConsumerAssert<S, A, T>, A extends ObjByteConsumer<T>, T> extends Assert<S, A>, FunctionalAssert.Simple<S, A, Exception> {

	@Nonnull
	SemiEvaluation<S, A, Exception> doesAccept(T t, byte b);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends ObjByteConsumer<T>, T> extends Base<Impl<A, T>, A, T> {

		public Impl(A actual) {
			super(actual, Impl.class);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, T>, A extends ObjByteConsumer<T>, T> extends FunctionalAssert.Simple.Base<S, A, Exception> implements ObjByteConsumerAssert<S, A, T> {

		public Base(A actual, Class<?> selfType) {
			super(actual, selfType);
		}

		@Nonnull
		public SemiEvaluation<S, A, Exception> doesAccept(T t, byte b) {
			return evaluation(() -> actual.accept(t, b));
		}

	}

}