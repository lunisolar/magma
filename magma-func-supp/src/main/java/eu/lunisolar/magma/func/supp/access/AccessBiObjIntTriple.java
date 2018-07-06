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
public interface AccessBiObjIntTriple<T1, T2> {

	static <T1, T2> AccessBiObjIntTriple<T1, T2> wrap(AccessBiObjIntTriple<T1, T2> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LBiObjIntTriple<T1, T2> accessBiObjIntTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseBiObjIntTriple(LBiObjIntTriple<T1, T2> a) {
		// NOOP
	}

	default void useWith(LBiObjIntConsumer<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAccept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjIntConsumer.LObjIntObj1Cons<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptObjIntObj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjIntConsumer.LObj1Obj0IntCons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptObj1Obj0Int(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjIntConsumer.LObj1IntObj0Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptObj1IntObj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjIntConsumer.LIntObj0Obj1Cons<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptIntObj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjIntConsumer.LIntObjObj0Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptIntObjObj0(tuple.third(), tuple.second(), tuple.first());
	}

	default void useWith(LTieConsumer<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAccept(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieConsumer.LObjObj2IntCons<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptObjObj2Int(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieConsumer.LIntBiObjCons<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptIntBiObj(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieConsumer.LIntObj2Obj0Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptIntObj2Obj0(tuple.third(), tuple.second(), tuple.first());
	}

	default void useWith(LTieConsumer.LObj2Obj0IntCons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptObj2Obj0Int(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieConsumer.LObj2IntObj0Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.doAcceptObj2IntObj0(tuple.second(), tuple.third(), tuple.first());
	}

	default <R> R useWith(LBiObjIntFunction<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LObjIntObj1Func<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyObjIntObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LObj1Obj0IntFunc<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyObj1Obj0Int(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LObj1IntObj0Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyObj1IntObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LIntObj0Obj1Func<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyIntObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LIntObjObj0Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyIntObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApply(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LObjObj2IntFunc<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyObjObj2Int(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LIntBiObjFunc<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyIntBiObj(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LIntObj2Obj0Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyIntObj2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LObj2Obj0IntFunc<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyObj2Obj0Int(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LObj2IntObj0Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.doApplyObj2IntObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.doApplyAsInt(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LObjObj2IntToIntFunc<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.doApplyAsIntObjObj2Int(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LIntBiObjToIntFunc<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.doApplyAsIntIntBiObj(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LIntObj2Obj0ToIntFunc<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.doApplyAsIntIntObj2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LObj2Obj0IntToIntFunc<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.doApplyAsIntObj2Obj0Int(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LObj2IntObj0ToIntFunc<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.doApplyAsIntObj2IntObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LObjIntObj1Pred<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestObjIntObj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LObj1Obj0IntPred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestObj1Obj0Int(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LObj1IntObj0Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestObj1IntObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LIntObj0Obj1Pred<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestIntObj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LIntObjObj0Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestIntObjObj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTest(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LObjObj2IntPred<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestObjObj2Int(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LIntBiObjPred<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestIntBiObj(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LIntObj2Obj0Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestIntObj2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LObj2Obj0IntPred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestObj2Obj0Int(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LObj2IntObj0Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.doTestObj2IntObj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

}
