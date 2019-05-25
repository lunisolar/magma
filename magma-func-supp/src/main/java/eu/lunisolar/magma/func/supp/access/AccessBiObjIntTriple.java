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
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjIntConsumer.LObj0Int2Obj1Cons<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptObj0Int2Obj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjIntConsumer.LObj1Obj0Int2Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptObj1Obj0Int2(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjIntConsumer.LObj1Int2Obj0Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptObj1Int2Obj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjIntConsumer.LInt2Obj0Obj1Cons<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptInt2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjIntConsumer.LInt2Obj1Obj0Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptInt2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
	}

	default void useWith(LTieConsumer<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.accept(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieConsumer.LObj0Obj2Int1Cons<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptObj0Obj2Int1(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LTieConsumer.LInt1BiObj2Cons<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptInt1BiObj2(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieConsumer.LInt1Obj2Obj0Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptInt1Obj2Obj0(tuple.third(), tuple.second(), tuple.first());
	}

	default void useWith(LTieConsumer.LObj2Obj0Int1Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptObj2Obj0Int1(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LTieConsumer.LObj2Int1Obj0Cons<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		accessFunction.acceptObj2Int1Obj0(tuple.second(), tuple.third(), tuple.first());
	}

	default <R> R useWith(LBiObjIntFunction<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LObj0Int2Obj1Func<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyObj0Int2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyObj1Obj0Int2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LObj1Int2Obj0Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyObj1Int2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyInt2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyInt2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LObj0Obj2Int1Func<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyObj0Obj2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LInt1BiObj2Func<T1, T2, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyInt1BiObj2(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LInt1Obj2Obj0Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyInt1Obj2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LObj2Obj0Int1Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyObj2Obj0Int1(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjIntObjFunction.LObj2Int1Obj0Func<T2, T1, R> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		R retval = accessFunction.applyObj2Int1Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.applyAsIntObj0Obj2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.applyAsIntInt1BiObj2(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.applyAsIntInt1Obj2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.applyAsIntObj2Obj0Int1(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		int retval = accessFunction.applyAsIntObj2Int1Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LObj0Int2Obj1Pred<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testObj0Int2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LObj1Obj0Int2Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testObj1Obj0Int2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LObj1Int2Obj0Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testObj1Int2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LInt2Obj0Obj1Pred<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testInt2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjIntPredicate.LInt2Obj1Obj0Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testInt2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LObj0Obj2Int1Pred<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testObj0Obj2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testInt1BiObj2(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testInt1Obj2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LObj2Obj0Int1Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testObj2Obj0Int1(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> accessFunction) {
		LBiObjIntTriple<T1, T2> tuple = accessBiObjIntTriple();
		boolean retval = accessFunction.testObj2Int1Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjIntTriple(tuple);
		return retval;
	}

}
