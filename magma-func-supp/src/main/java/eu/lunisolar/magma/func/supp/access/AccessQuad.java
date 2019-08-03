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
public interface AccessQuad<T1, T2, T3, T4> {

	static <T1, T2, T3, T4> AccessQuad<T1, T2, T3, T4> wrap(AccessQuad<T1, T2, T3, T4> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LQuad<T1, T2, T3, T4> accessQuad();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseQuad(LQuad<T1, T2, T3, T4> a) {
		// NOOP
	}

	default void useWith(LQuadConsumer<T1, T2, T3, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
	}

	default void useWith(LQuadConsumer.LBiObj1Obj3Obj2Cons<T1, T2, T4, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptBiObj1Obj3Obj2(tuple.first(), tuple.second(), tuple.fourth(), tuple.third());
	}

	default void useWith(LQuadConsumer.LObj0Obj2BiObj3Cons<T1, T3, T2, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj0Obj2BiObj3(tuple.first(), tuple.third(), tuple.second(), tuple.fourth());
	}

	default void useWith(LQuadConsumer.LObj0Obj2Obj3Obj1Cons<T1, T3, T4, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj0Obj2Obj3Obj1(tuple.first(), tuple.third(), tuple.fourth(), tuple.second());
	}

	default void useWith(LQuadConsumer.LObj0Obj3Obj1Obj2Cons<T1, T4, T2, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj0Obj3Obj1Obj2(tuple.first(), tuple.fourth(), tuple.second(), tuple.third());
	}

	default void useWith(LQuadConsumer.LObj0BiObj2Obj1Cons<T1, T4, T3, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj0BiObj2Obj1(tuple.first(), tuple.fourth(), tuple.third(), tuple.second());
	}

	default void useWith(LQuadConsumer.LObj1TriObj3Cons<T2, T1, T3, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj1TriObj3(tuple.second(), tuple.first(), tuple.third(), tuple.fourth());
	}

	default void useWith(LQuadConsumer.LObj1Obj0Obj3Obj2Cons<T2, T1, T4, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj1Obj0Obj3Obj2(tuple.second(), tuple.first(), tuple.fourth(), tuple.third());
	}

	default void useWith(LQuadConsumer.LObj1Obj2BiObj3Cons<T2, T3, T1, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj1Obj2BiObj3(tuple.second(), tuple.third(), tuple.first(), tuple.fourth());
	}

	default void useWith(LQuadConsumer.LObj1Obj2Obj3Obj0Cons<T2, T3, T4, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj1Obj2Obj3Obj0(tuple.second(), tuple.third(), tuple.fourth(), tuple.first());
	}

	default void useWith(LQuadConsumer.LObj1Obj3Obj0Obj2Cons<T2, T4, T1, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj1Obj3Obj0Obj2(tuple.second(), tuple.fourth(), tuple.first(), tuple.third());
	}

	default void useWith(LQuadConsumer.LObj1BiObj2Obj0Cons<T2, T4, T3, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj1BiObj2Obj0(tuple.second(), tuple.fourth(), tuple.third(), tuple.first());
	}

	default void useWith(LQuadConsumer.LObj2Obj0BiObj3Cons<T3, T1, T2, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj2Obj0BiObj3(tuple.third(), tuple.first(), tuple.second(), tuple.fourth());
	}

	default void useWith(LQuadConsumer.LObj2Obj0Obj3Obj1Cons<T3, T1, T4, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj2Obj0Obj3Obj1(tuple.third(), tuple.first(), tuple.fourth(), tuple.second());
	}

	default void useWith(LQuadConsumer.LBiObj1BiObj3Cons<T3, T2, T1, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptBiObj1BiObj3(tuple.third(), tuple.second(), tuple.first(), tuple.fourth());
	}

	default void useWith(LQuadConsumer.LBiObj1Obj3Obj0Cons<T3, T2, T4, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptBiObj1Obj3Obj0(tuple.third(), tuple.second(), tuple.fourth(), tuple.first());
	}

	default void useWith(LQuadConsumer.LObj2Obj3Obj0Obj1Cons<T3, T4, T1, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj2Obj3Obj0Obj1(tuple.third(), tuple.fourth(), tuple.first(), tuple.second());
	}

	default void useWith(LQuadConsumer.LObj2Obj3Obj1Obj0Cons<T3, T4, T2, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj2Obj3Obj1Obj0(tuple.third(), tuple.fourth(), tuple.second(), tuple.first());
	}

	default void useWith(LQuadConsumer.LObj3Obj0Obj1Obj2Cons<T4, T1, T2, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj3Obj0Obj1Obj2(tuple.fourth(), tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LQuadConsumer.LObj3BiObj2Obj1Cons<T4, T1, T3, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj3BiObj2Obj1(tuple.fourth(), tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LQuadConsumer.LBiObj1Obj0Obj2Cons<T4, T2, T1, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptBiObj1Obj0Obj2(tuple.fourth(), tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LQuadConsumer.LTriObj2Obj0Cons<T4, T2, T3, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptTriObj2Obj0(tuple.fourth(), tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LQuadConsumer.LObj3Obj2Obj0Obj1Cons<T4, T3, T1, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj3Obj2Obj0Obj1(tuple.fourth(), tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LQuadConsumer.LObj3Obj2Obj1Obj0Cons<T4, T3, T2, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		accessFunction.acceptObj3Obj2Obj1Obj0(tuple.fourth(), tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LQuadFunction<T1, T2, T3, T4, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LBiObj1Obj3Obj2Func<T1, T2, T4, T3, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyBiObj1Obj3Obj2(tuple.first(), tuple.second(), tuple.fourth(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj0Obj2BiObj3Func<T1, T3, T2, T4, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj0Obj2BiObj3(tuple.first(), tuple.third(), tuple.second(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj0Obj2Obj3Obj1Func<T1, T3, T4, T2, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj0Obj2Obj3Obj1(tuple.first(), tuple.third(), tuple.fourth(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj0Obj3Obj1Obj2Func<T1, T4, T2, T3, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj0Obj3Obj1Obj2(tuple.first(), tuple.fourth(), tuple.second(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj0BiObj2Obj1Func<T1, T4, T3, T2, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj0BiObj2Obj1(tuple.first(), tuple.fourth(), tuple.third(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj1TriObj3Func<T2, T1, T3, T4, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj1TriObj3(tuple.second(), tuple.first(), tuple.third(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj1Obj0Obj3Obj2Func<T2, T1, T4, T3, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj1Obj0Obj3Obj2(tuple.second(), tuple.first(), tuple.fourth(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj1Obj2BiObj3Func<T2, T3, T1, T4, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj1Obj2BiObj3(tuple.second(), tuple.third(), tuple.first(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj1Obj2Obj3Obj0Func<T2, T3, T4, T1, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj1Obj2Obj3Obj0(tuple.second(), tuple.third(), tuple.fourth(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj1Obj3Obj0Obj2Func<T2, T4, T1, T3, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj1Obj3Obj0Obj2(tuple.second(), tuple.fourth(), tuple.first(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj1BiObj2Obj0Func<T2, T4, T3, T1, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj1BiObj2Obj0(tuple.second(), tuple.fourth(), tuple.third(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj2Obj0BiObj3Func<T3, T1, T2, T4, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj2Obj0BiObj3(tuple.third(), tuple.first(), tuple.second(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj2Obj0Obj3Obj1Func<T3, T1, T4, T2, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj2Obj0Obj3Obj1(tuple.third(), tuple.first(), tuple.fourth(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LBiObj1BiObj3Func<T3, T2, T1, T4, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyBiObj1BiObj3(tuple.third(), tuple.second(), tuple.first(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LBiObj1Obj3Obj0Func<T3, T2, T4, T1, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyBiObj1Obj3Obj0(tuple.third(), tuple.second(), tuple.fourth(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj2Obj3Obj0Obj1Func<T3, T4, T1, T2, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj2Obj3Obj0Obj1(tuple.third(), tuple.fourth(), tuple.first(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj2Obj3Obj1Obj0Func<T3, T4, T2, T1, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj2Obj3Obj1Obj0(tuple.third(), tuple.fourth(), tuple.second(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj3Obj0Obj1Obj2Func<T4, T1, T2, T3, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj3Obj0Obj1Obj2(tuple.fourth(), tuple.first(), tuple.second(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj3BiObj2Obj1Func<T4, T1, T3, T2, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj3BiObj2Obj1(tuple.fourth(), tuple.first(), tuple.third(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LBiObj1Obj0Obj2Func<T4, T2, T1, T3, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyBiObj1Obj0Obj2(tuple.fourth(), tuple.second(), tuple.first(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LTriObj2Obj0Func<T4, T2, T3, T1, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyTriObj2Obj0(tuple.fourth(), tuple.second(), tuple.third(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj3Obj2Obj0Obj1Func<T4, T3, T1, T2, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj3Obj2Obj0Obj1(tuple.fourth(), tuple.third(), tuple.first(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default <R> R useWith(LQuadFunction.LObj3Obj2Obj1Obj0Func<T4, T3, T2, T1, R> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		R retval = accessFunction.applyObj3Obj2Obj1Obj0(tuple.fourth(), tuple.third(), tuple.second(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate<T1, T2, T3, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LBiObj1Obj3Obj2Pred<T1, T2, T4, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testBiObj1Obj3Obj2(tuple.first(), tuple.second(), tuple.fourth(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj0Obj2BiObj3Pred<T1, T3, T2, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj0Obj2BiObj3(tuple.first(), tuple.third(), tuple.second(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj0Obj2Obj3Obj1Pred<T1, T3, T4, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj0Obj2Obj3Obj1(tuple.first(), tuple.third(), tuple.fourth(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj0Obj3Obj1Obj2Pred<T1, T4, T2, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj0Obj3Obj1Obj2(tuple.first(), tuple.fourth(), tuple.second(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj0BiObj2Obj1Pred<T1, T4, T3, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj0BiObj2Obj1(tuple.first(), tuple.fourth(), tuple.third(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj1TriObj3Pred<T2, T1, T3, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj1TriObj3(tuple.second(), tuple.first(), tuple.third(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj1Obj0Obj3Obj2Pred<T2, T1, T4, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj1Obj0Obj3Obj2(tuple.second(), tuple.first(), tuple.fourth(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj1Obj2BiObj3Pred<T2, T3, T1, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj1Obj2BiObj3(tuple.second(), tuple.third(), tuple.first(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj1Obj2Obj3Obj0Pred<T2, T3, T4, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj1Obj2Obj3Obj0(tuple.second(), tuple.third(), tuple.fourth(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj1Obj3Obj0Obj2Pred<T2, T4, T1, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj1Obj3Obj0Obj2(tuple.second(), tuple.fourth(), tuple.first(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj1BiObj2Obj0Pred<T2, T4, T3, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj1BiObj2Obj0(tuple.second(), tuple.fourth(), tuple.third(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj2Obj0BiObj3Pred<T3, T1, T2, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj2Obj0BiObj3(tuple.third(), tuple.first(), tuple.second(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj2Obj0Obj3Obj1Pred<T3, T1, T4, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj2Obj0Obj3Obj1(tuple.third(), tuple.first(), tuple.fourth(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LBiObj1BiObj3Pred<T3, T2, T1, T4> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testBiObj1BiObj3(tuple.third(), tuple.second(), tuple.first(), tuple.fourth());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LBiObj1Obj3Obj0Pred<T3, T2, T4, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testBiObj1Obj3Obj0(tuple.third(), tuple.second(), tuple.fourth(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj2Obj3Obj0Obj1Pred<T3, T4, T1, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj2Obj3Obj0Obj1(tuple.third(), tuple.fourth(), tuple.first(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj2Obj3Obj1Obj0Pred<T3, T4, T2, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj2Obj3Obj1Obj0(tuple.third(), tuple.fourth(), tuple.second(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj3Obj0Obj1Obj2Pred<T4, T1, T2, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj3Obj0Obj1Obj2(tuple.fourth(), tuple.first(), tuple.second(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj3BiObj2Obj1Pred<T4, T1, T3, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj3BiObj2Obj1(tuple.fourth(), tuple.first(), tuple.third(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LBiObj1Obj0Obj2Pred<T4, T2, T1, T3> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testBiObj1Obj0Obj2(tuple.fourth(), tuple.second(), tuple.first(), tuple.third());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LTriObj2Obj0Pred<T4, T2, T3, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testTriObj2Obj0(tuple.fourth(), tuple.second(), tuple.third(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj3Obj2Obj0Obj1Pred<T4, T3, T1, T2> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj3Obj2Obj0Obj1(tuple.fourth(), tuple.third(), tuple.first(), tuple.second());
		releaseQuad(tuple);
		return retval;
	}

	default boolean useWith(LQuadPredicate.LObj3Obj2Obj1Obj0Pred<T4, T3, T2, T1> accessFunction) {
		LQuad<T1, T2, T3, T4> tuple = accessQuad();
		boolean retval = accessFunction.testObj3Obj2Obj1Obj0(tuple.fourth(), tuple.third(), tuple.second(), tuple.first());
		releaseQuad(tuple);
		return retval;
	}

}
