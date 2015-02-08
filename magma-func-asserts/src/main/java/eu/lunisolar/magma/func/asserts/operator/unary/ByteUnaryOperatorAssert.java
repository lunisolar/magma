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

package eu.lunisolar.magma.func.asserts.operator.unary;

import eu.lunisolar.magma.basics.asserts.Evaluation; // NOSONAR
import eu.lunisolar.magma.basics.asserts.FunctionalAssert; // NOSONAR
import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import org.assertj.core.api.*; // NOSONAR
import eu.lunisolar.magma.basics.asserts.RecurringAsserts; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.func.operator.unary.*;

import static org.assertj.core.api.Fail.fail;

/** Assertions for ByteUnaryOperator. */
public interface ByteUnaryOperatorAssert<S extends ByteUnaryOperatorAssert<S, A, RS>, A extends ByteUnaryOperator, RS extends AbstractByteAssert<RS>> extends Assert<S, A>, FunctionalAssert<S, A, RS, Byte, Exception>, RecurringAsserts<S, A, RS, Byte> {

	@Nonnull
	Evaluation<S, A, RS, Byte, Exception> doesApplyAsByte(byte b);

	/** Convenience implementation - if you want instantiate not to extend (uses one less generic parameter). */
	public final static class Impl<A extends ByteUnaryOperator, RS extends AbstractByteAssert<RS>> extends Base<Impl<A, RS>, A, RS> {

		public Impl(A actual, java.util.function.Function<Byte, RS> assertFunction) {
			super(actual, Impl.class, assertFunction);
		}
	}

	/** Base implementation. For potentiall extending (requires to define all generic parameters). */
	public static class Base<S extends Base<S, A, RS>, A extends ByteUnaryOperator, RS extends AbstractByteAssert<RS>> extends FunctionalAssert.Base<S, A, RS, Byte, Exception> implements ByteUnaryOperatorAssert<S, A, RS> {

		protected final java.util.function.Function<Byte, RS> assertFactory;

		public Base(A actual, Class<?> selfType, java.util.function.Function<Byte, RS> assertFactory) {
			super(actual, selfType);
			this.assertFactory = assertFactory;
		}

		@Nonnull
		public Evaluation<S, A, RS, Byte, Exception> doesApplyAsByte(byte b) {
			return evaluation(() -> assertFactory.apply((Byte) actual.applyAsByte(b)));
		}
	}

}
