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
public interface AccessTriple<T1, T2, T3> {

	static <T1, T2, T3> AccessTriple<T1, T2, T3> wrap(AccessTriple<T1, T2, T3> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LTriple<T1, T2, T3> accessTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseTriple(LTriple<T1, T2, T3> a) {
		// NOOP
	}

	default void useWith(LTriConsumer<T1, T2, T3> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTriConsumer.LObjObj2Obj1Cons<T1, T3, T2> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		accessFunction.doAcceptObjObj2Obj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTriConsumer.LObj1BiObjCons<T2, T1, T3> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		accessFunction.doAcceptObj1BiObj(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTriConsumer.LObj1Obj2Obj0Cons<T2, T3, T1> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		accessFunction.doAcceptObj1Obj2Obj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LTriConsumer.LObj2Obj0Obj1Cons<T3, T1, T2> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		accessFunction.doAcceptObj2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTriConsumer.LBiObjObj0Cons<T3, T2, T1> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		accessFunction.doAcceptBiObjObj0(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LTriFunction<T1, T2, T3, R> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseTriple(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunction.LObjObj2Obj1Func<T1, T3, T2, R> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		R retval = accessFunction.doApplyObjObj2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseTriple(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunction.LObj1BiObjFunc<T2, T1, T3, R> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		R retval = accessFunction.doApplyObj1BiObj(tuple.second(), tuple.first(), tuple.third());
		releaseTriple(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunction.LObj1Obj2Obj0Func<T2, T3, T1, R> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		R retval = accessFunction.doApplyObj1Obj2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseTriple(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunction.LObj2Obj0Obj1Func<T3, T1, T2, R> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		R retval = accessFunction.doApplyObj2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseTriple(tuple);
		return retval;
	}

	default <R> R useWith(LTriFunction.LBiObjObj0Func<T3, T2, T1, R> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		R retval = accessFunction.doApplyBiObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseTriple(tuple);
		return retval;
	}

	default int useWith(LToIntTriFunction<T1, T2, T3> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseTriple(tuple);
		return retval;
	}

	default int useWith(LToIntTriFunction.LToIntObjObj2Obj1Func<T1, T3, T2> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		int retval = accessFunction.doApplyAsIntObjObj2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseTriple(tuple);
		return retval;
	}

	default int useWith(LToIntTriFunction.LToIntObj1BiObjFunc<T2, T1, T3> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		int retval = accessFunction.doApplyAsIntObj1BiObj(tuple.second(), tuple.first(), tuple.third());
		releaseTriple(tuple);
		return retval;
	}

	default int useWith(LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		int retval = accessFunction.doApplyAsIntObj1Obj2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseTriple(tuple);
		return retval;
	}

	default int useWith(LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		int retval = accessFunction.doApplyAsIntObj2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseTriple(tuple);
		return retval;
	}

	default int useWith(LToIntTriFunction.LToIntBiObjObj0Func<T3, T2, T1> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		int retval = accessFunction.doApplyAsIntBiObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseTriple(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicate<T1, T2, T3> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseTriple(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicate.LObjObj2Obj1Pred<T1, T3, T2> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		boolean retval = accessFunction.doTestObjObj2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseTriple(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicate.LObj1BiObjPred<T2, T1, T3> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		boolean retval = accessFunction.doTestObj1BiObj(tuple.second(), tuple.first(), tuple.third());
		releaseTriple(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicate.LObj1Obj2Obj0Pred<T2, T3, T1> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		boolean retval = accessFunction.doTestObj1Obj2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseTriple(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicate.LObj2Obj0Obj1Pred<T3, T1, T2> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		boolean retval = accessFunction.doTestObj2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseTriple(tuple);
		return retval;
	}

	default boolean useWith(LTriPredicate.LBiObjObj0Pred<T3, T2, T1> accessFunction) {
		LTriple<T1, T2, T3> tuple = accessTriple();
		boolean retval = accessFunction.doTestBiObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseTriple(tuple);
		return retval;
	}

}
