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
public interface AccessObjBiIntTriple<T> {

	static <T> AccessObjBiIntTriple<T> wrap(AccessObjBiIntTriple<T> lambda) {
		return lambda;
	}

	/**
	 * Before each access this method is called in order to potentially prepare the resources behind value (void)
	 */
	LObjBiIntTriple<T> accessObjBiIntTriple();

	/**
	 * After each access this method is called in order to potentially release the resources behind value (void).
	 */
	default void releaseObjBiIntTriple(LObjBiIntTriple<T> a) {
		// NOOP
	}

	default void useWith(LTieIntConsumer<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWithO1(LTieIntConsumer<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.accept(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieIntConsumer.LObj0Int2Int1Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptObj0Int2Int1(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWithO1(LTieIntConsumer.LObj0Int2Int1Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptObj0Int2Int1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LTieIntConsumer.LInt1Obj0Int2Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptInt1Obj0Int2(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWithO1(LTieIntConsumer.LInt1Obj0Int2Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptInt1Obj0Int2(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieIntConsumer.LInt1Int2Obj0Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptInt1Int2Obj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWithO1(LTieIntConsumer.LInt1Int2Obj0Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptInt1Int2Obj0(tuple.third(), tuple.second(), tuple.first());
	}

	default void useWith(LTieIntConsumer.LInt2Obj0Int1Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptInt2Obj0Int1(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWithO1(LTieIntConsumer.LInt2Obj0Int1Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptInt2Obj0Int1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LTieIntConsumer.LBiInt1Obj0Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptBiInt1Obj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWithO1(LTieIntConsumer.LBiInt1Obj0Cons<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		accessFunction.acceptBiInt1Obj0(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LObjBiIntFunction<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LObj0Int2Int1Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyObj0Int2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LObj0Int2Int1Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyObj0Int2Int1(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LInt1Obj0Int2Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyInt1Obj0Int2(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LInt1Obj0Int2Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyInt1Obj0Int2(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LInt1Int2Obj0Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyInt1Int2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LInt1Int2Obj0Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyInt1Int2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LInt2Obj0Int1Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyInt2Obj0Int1(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LInt2Obj0Int1Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyInt2Obj0Int1(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWith(LObjBiIntFunction.LBiInt1Obj0Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyBiInt1Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default <R> R useWithO1(LObjBiIntFunction.LBiInt1Obj0Func<T, R> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		R retval = accessFunction.applyBiInt1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsInt(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LObj0Int2Int1ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntObj0Int2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LObj0Int2Int1ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntObj0Int2Int1(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntInt1Obj0Int2(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LInt1Obj0Int2ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntInt1Obj0Int2(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntInt1Int2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LInt1Int2Obj0ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntInt1Int2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntInt2Obj0Int1(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LInt2Obj0Int1ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntInt2Obj0Int1(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWith(LTieIntFunction.LBiInt1Obj0ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntBiInt1Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default int useWithO1(LTieIntFunction.LBiInt1Obj0ToIntFunc<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		int retval = accessFunction.applyAsIntBiInt1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LObj0Int2Int1Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testObj0Int2Int1(tuple.first(), tuple.second(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LObj0Int2Int1Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testObj0Int2Int1(tuple.first(), tuple.third(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LInt1Obj0Int2Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testInt1Obj0Int2(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LInt1Obj0Int2Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testInt1Obj0Int2(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LInt1Int2Obj0Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testInt1Int2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LInt1Int2Obj0Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testInt1Int2Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LInt2Obj0Int1Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testInt2Obj0Int1(tuple.second(), tuple.first(), tuple.third());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LInt2Obj0Int1Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testInt2Obj0Int1(tuple.third(), tuple.first(), tuple.second());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWith(LObjBiIntPredicate.LBiInt1Obj0Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testBiInt1Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

	default boolean useWithO1(LObjBiIntPredicate.LBiInt1Obj0Pred<T> accessFunction) {
		LObjBiIntTriple<T> tuple = accessObjBiIntTriple();
		boolean retval = accessFunction.testBiInt1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseObjBiIntTriple(tuple);
		return retval;
	}

}
