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
public interface AccessBiObjLongTriple<T1, T2> {

	static <T1, T2> AccessBiObjLongTriple<T1, T2> wrap(AccessBiObjLongTriple<T1, T2> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBiObjLongTriple<T1, T2> accessBiObjLongTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBiObjLongTriple(LBiObjLongTriple<T1, T2> a) {
		// NOOP
	}

	default void useWith(LBiObjLongConsumer<T1, T2> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjLongConsumer.LObjLongObj1Cons<T1, T2> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		accessFunction.doAcceptObjLongObj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjLongConsumer.LObj1Obj0LongCons<T2, T1> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		accessFunction.doAcceptObj1Obj0Long(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjLongConsumer.LObj1LongObj0Cons<T2, T1> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		accessFunction.doAcceptObj1LongObj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjLongConsumer.LLongObj0Obj1Cons<T1, T2> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		accessFunction.doAcceptLongObj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjLongConsumer.LLongObjObj0Cons<T2, T1> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		accessFunction.doAcceptLongObjObj0(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiObjLongFunction<T1, T2, R> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjLongFunction.LObjLongObj1Func<T1, T2, R> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		R retval = accessFunction.doApplyObjLongObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjLongFunction.LObj1Obj0LongFunc<T2, T1, R> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		R retval = accessFunction.doApplyObj1Obj0Long(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjLongFunction.LObj1LongObj0Func<T2, T1, R> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		R retval = accessFunction.doApplyObj1LongObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjLongFunction.LLongObj0Obj1Func<T1, T2, R> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		R retval = accessFunction.doApplyLongObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjLongFunction.LLongObjObj0Func<T2, T1, R> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		R retval = accessFunction.doApplyLongObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjLongPredicate<T1, T2> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjLongPredicate.LObjLongObj1Pred<T1, T2> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		boolean retval = accessFunction.doTestObjLongObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjLongPredicate.LObj1Obj0LongPred<T2, T1> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		boolean retval = accessFunction.doTestObj1Obj0Long(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjLongPredicate.LObj1LongObj0Pred<T2, T1> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		boolean retval = accessFunction.doTestObj1LongObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjLongPredicate.LLongObj0Obj1Pred<T1, T2> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		boolean retval = accessFunction.doTestLongObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjLongPredicate.LLongObjObj0Pred<T2, T1> accessFunction) {
		LBiObjLongTriple<T1, T2> tuple = accessBiObjLongTriple();
		boolean retval = accessFunction.doTestLongObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjLongTriple(tuple);
		return retval;
	}

}
