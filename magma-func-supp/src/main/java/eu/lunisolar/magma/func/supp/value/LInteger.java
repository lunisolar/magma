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
import eu.lunisolar.magma.func.supp.traits.*; // NOSONAR
import eu.lunisolar.magma.func.supp.traits.FluentTrait; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import eu.lunisolar.magma.basics.fluent.*; //NOSONAR
import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

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

/**
 * Convenient value holder that allows mutation.
 * Mutability is intended to be used in places where lambda captured values need to be "modified" in single-threaded use (or similar situations).
 * Otherwise, use non-mutable {@link Opt}-like or synchronized {@link AtomicReference}-like solution.
 */
@NotThreadSafe
public final class LInteger
		implements
			FluentTrait<LInteger>,
			aValue<aInt>,
			CheckIntTrait<LInteger>,
			FilterIntSingleTrait<LInteger>,
			IsIntTrait<LInteger>,
			DoIfIntSingleTrait<LInteger>,
			UseIntSingleTrait<LInteger>,
			UniMapIntTrait<LInteger>,
			IntValueTrait<LInteger>,
			LIntSingle.Mut<LInteger> {

	private int value;

	public LInteger(int value) {
		value(value);
	}

	public static LInteger intValue(int value) {
		return new LInteger(value);
	}

	@Override
	public int value() {
		return value;
	}

	@Nonnull
	@Override
	public LInteger value(int value) {
		this.value = value;
		return this;
	}

	@Nonnull
	@Override
	public LInteger voidValue() {
		this.value = 0;
		return this;
	}

	@Override
	public int get() {
		return value;
	}

	//<editor-fold desc="equals/hashcode/toString">

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(this.getClass().isInstance(obj))) {
			return false;
		}

		LInteger other = (LInteger) obj;
		return value() == other.value();

	}

	public int hashCode() {
		return Integer.hashCode(value());
	}

	public String toString() {
		var v = value();
		var sb = new StringBuilder().append(getClass().getSimpleName()).append("[");
		ToStr.toSb(sb, v);
		return sb.append("]").toString();
	}

	//</editor-fold>

}
