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
public interface AccessBiObjByteTriple<T1, T2> {

	static <T1, T2> AccessBiObjByteTriple<T1, T2> wrap(AccessBiObjByteTriple<T1, T2> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBiObjByteTriple<T1, T2> accessBiObjByteTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBiObjByteTriple(LBiObjByteTriple<T1, T2> a) {
		// NOOP
	}

	default void useWith(LBiObjByteConsumer<T1, T2> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjByteConsumer.LObj0Byte2Obj1Cons<T1, T2> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		accessFunction.acceptObj0Byte2Obj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjByteConsumer.LObj1Obj0Byte2Cons<T2, T1> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		accessFunction.acceptObj1Obj0Byte2(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjByteConsumer.LObj1Byte2Obj0Cons<T2, T1> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		accessFunction.acceptObj1Byte2Obj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjByteConsumer.LByte2Obj0Obj1Cons<T1, T2> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		accessFunction.acceptByte2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjByteConsumer.LByte2Obj1Obj0Cons<T2, T1> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		accessFunction.acceptByte2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiObjByteFunction<T1, T2, R> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjByteFunction.LObj0Byte2Obj1Func<T1, T2, R> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		R retval = accessFunction.applyObj0Byte2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjByteFunction.LObj1Obj0Byte2Func<T2, T1, R> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		R retval = accessFunction.applyObj1Obj0Byte2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjByteFunction.LObj1Byte2Obj0Func<T2, T1, R> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		R retval = accessFunction.applyObj1Byte2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjByteFunction.LByte2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		R retval = accessFunction.applyByte2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjByteFunction.LByte2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		R retval = accessFunction.applyByte2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBytePredicate<T1, T2> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBytePredicate.LObj0Byte2Obj1Pred<T1, T2> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		boolean retval = accessFunction.testObj0Byte2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBytePredicate.LObj1Obj0Byte2Pred<T2, T1> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		boolean retval = accessFunction.testObj1Obj0Byte2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBytePredicate.LObj1Byte2Obj0Pred<T2, T1> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		boolean retval = accessFunction.testObj1Byte2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBytePredicate.LByte2Obj0Obj1Pred<T1, T2> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		boolean retval = accessFunction.testByte2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjBytePredicate.LByte2Obj1Obj0Pred<T2, T1> accessFunction) {
		LBiObjByteTriple<T1, T2> tuple = accessBiObjByteTriple();
		boolean retval = accessFunction.testByte2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjByteTriple(tuple);
		return retval;
	}

}
