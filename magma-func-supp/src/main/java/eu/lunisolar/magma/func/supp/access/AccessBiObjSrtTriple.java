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
public interface AccessBiObjSrtTriple<T1, T2> {

	static <T1, T2> AccessBiObjSrtTriple<T1, T2> wrap(AccessBiObjSrtTriple<T1, T2> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBiObjSrtTriple<T1, T2> accessBiObjSrtTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBiObjSrtTriple(LBiObjSrtTriple<T1, T2> a) {
		// NOOP
	}

	default void useWith(LBiObjSrtConsumer<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjSrtConsumer.LObjSrtObj1Cons<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.doAcceptObjSrtObj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjSrtConsumer.LObj1Obj0SrtCons<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.doAcceptObj1Obj0Srt(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjSrtConsumer.LObj1SrtObj0Cons<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.doAcceptObj1SrtObj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjSrtConsumer.LSrtObj0Obj1Cons<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.doAcceptSrtObj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjSrtConsumer.LSrtObjObj0Cons<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.doAcceptSrtObjObj0(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiObjSrtFunction<T1, T2, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LObjSrtObj1Func<T1, T2, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.doApplyObjSrtObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LObj1Obj0SrtFunc<T2, T1, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.doApplyObj1Obj0Srt(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LObj1SrtObj0Func<T2, T1, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.doApplyObj1SrtObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LSrtObj0Obj1Func<T1, T2, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.doApplySrtObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LSrtObjObj0Func<T2, T1, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.doApplySrtObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LObjSrtObj1Pred<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.doTestObjSrtObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LObj1Obj0SrtPred<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.doTestObj1Obj0Srt(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LObj1SrtObj0Pred<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.doTestObj1SrtObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LSrtObj0Obj1Pred<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.doTestSrtObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LSrtObjObj0Pred<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.doTestSrtObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

}
