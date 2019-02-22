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
		accessFunction.accept(tuple.first(), tuple.second(), tuple.third());
	}

	default void useWith(LBiObjSrtConsumer.LObj0Srt2Obj1Cons<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.acceptObj0Srt2Obj1(tuple.first(), tuple.third(), tuple.second());
	}

	default void useWith(LBiObjSrtConsumer.LObj1Obj0Srt2Cons<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.acceptObj1Obj0Srt2(tuple.second(), tuple.first(), tuple.third());
	}

	default void useWith(LBiObjSrtConsumer.LObj1Srt2Obj0Cons<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.acceptObj1Srt2Obj0(tuple.second(), tuple.third(), tuple.first());
	}

	default void useWith(LBiObjSrtConsumer.LSrt2Obj0Obj1Cons<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.acceptSrt2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
	}

	default void useWith(LBiObjSrtConsumer.LSrt2Obj1Obj0Cons<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		accessFunction.acceptSrt2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
	}

	default <R> R useWith(LBiObjSrtFunction<T1, T2, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.apply(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LObj0Srt2Obj1Func<T1, T2, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.applyObj0Srt2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LObj1Obj0Srt2Func<T2, T1, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.applyObj1Obj0Srt2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LObj1Srt2Obj0Func<T2, T1, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.applyObj1Srt2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LSrt2Obj0Obj1Func<T1, T2, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.applySrt2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default <R> R useWith(LBiObjSrtFunction.LSrt2Obj1Obj0Func<T2, T1, R> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		R retval = accessFunction.applySrt2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.test(tuple.first(), tuple.second(), tuple.third());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LObj0Srt2Obj1Pred<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.testObj0Srt2Obj1(tuple.first(), tuple.third(), tuple.second());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LObj1Obj0Srt2Pred<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.testObj1Obj0Srt2(tuple.second(), tuple.first(), tuple.third());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LObj1Srt2Obj0Pred<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.testObj1Srt2Obj0(tuple.second(), tuple.third(), tuple.first());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LSrt2Obj0Obj1Pred<T1, T2> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.testSrt2Obj0Obj1(tuple.third(), tuple.first(), tuple.second());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

	default boolean useWith(LBiObjSrtPredicate.LSrt2Obj1Obj0Pred<T2, T1> accessFunction) {
		LBiObjSrtTriple<T1, T2> tuple = accessBiObjSrtTriple();
		boolean retval = accessFunction.testSrt2Obj1Obj0(tuple.third(), tuple.second(), tuple.first());
		releaseBiObjSrtTriple(tuple);
		return retval;
	}

}
