/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.supp.value;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.ThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.Fluent; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.supp.Clazz; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.FluentTrait; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; //NOSONAR

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

import eu.lunisolar.magma.func.supp.opt.*;

public interface ValueTrait<T, SELF extends ValueTrait<T, SELF>> extends FluentTrait<SELF>, aValue<a<T>>, OneTrait<T> {

	T value();

	/**
	 * Returns either the same or new Value object (depends on implementation) that is holding the value (mutating vs immutable).
	 * It gives specializations to create instance of SELF.
	 * Whether 'null' value is supported depends on the implementation.
	 **/
	@Nonnull
	SELF value(@Nullable T value);

	/** Provided that implementation supports empty values this will produce 'SELF' representing it. */
	default @Nonnull SELF voidValue() {
		throw Handling.create(X::unsupported, "Trait implementation (%s) does not support empty value.", this.getClass().getSimpleName());
	}

	default SELF valueFrom(@Nonnull ValueTrait<? extends T, ?> trait) {
		return getClass().isInstance(trait) ? (SELF) trait : valueFromOther(trait);
	}

	private SELF valueFromOther(@Nonnull ValueTrait<? extends T, ?> trait) {
		var value = trait.nullable();
		return (value == null) ? voidValue() : value(value);
	}

	default @Nonnull T nonnull() {
		var value = value();
		if (value == null) {
			throw new IllegalStateException("Value cannot be null!");
		}
		return value();
	}

	@Override
	default @Nullable T nullable() {
		return value();
	}

}
