/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.asserts.func;

import eu.lunisolar.magma.asserts.Attest;
import eu.lunisolar.magma.basics.Null;
import eu.lunisolar.magma.basics.exceptions.Handling;
import eu.lunisolar.magma.basics.fluent.Fluent;
import eu.lunisolar.magma.basics.fluent.FluentSubcontext;
import eu.lunisolar.magma.basics.meta.functional.aCheck;
import eu.lunisolar.magma.func.consumer.LConsumer;
import eu.lunisolar.magma.func.consumer.primitives.*;
import eu.lunisolar.magma.func.operator.unary.LUnaryOperator;
import eu.lunisolar.magma.func.supp.Be;
import eu.lunisolar.magma.func.supp.check.Checks;
import eu.lunisolar.magma.func.supp.traits.CheckTrait;
import eu.lunisolar.magma.func.supp.traits.FailPoint;
import eu.lunisolar.magma.func.supp.traits.FluentTrait;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.function.*;

import static java.util.stream.Collectors.*;

public final class FunctionalAttest {

	private FunctionalAttest() {
		// no instance
	}

	@FunctionalInterface
	public interface AssertionFunction<PC, R_CHECK> {
		@Nonnull
		R_CHECK applyAndCreateResultAssert(@Nullable String description, @Nullable PC preconditions) throws Throwable;
	}

	@FunctionalInterface
	public interface AssertionsCheck {

		void assertionsCheck() throws AssertionError;
	}

	public interface FAttest<SELF extends FAttest<SELF, FUNC, PC, R_CHECK, CHECK_CONSUMER>, FUNC, PC, R_CHECK, CHECK_CONSUMER> extends RecurringAsserts<SELF, CHECK_CONSUMER>, Fluent<SELF> {

	}

	@SuppressWarnings("unchecked")
	public static abstract class Simple<SELF extends Simple<SELF, FUNC, PC>, FUNC, PC> extends RecurringAsserts.Base<SELF, FUNC, AssertionsCheck> implements FAttest<SELF, FUNC, PC, AssertionsCheck, AssertionsCheck> {

		public Simple(FUNC value) {
			super(value);
		}

		public Simple(FUNC value, String name) {
			super(value, name);
		}

		@Nonnull
		protected SemiEvaluation<SELF, PC, AssertionsCheck> evaluation(Supplier<String> argDescription, @Nonnull AssertionFunction<PC, ?> assertFunction) {
			return new SemiEvaluation(this, argDescription, assertFunction, recurringAssert);
		}

	}

	public static abstract class Full<SELF extends Full<SELF, FUNC, PC, R_CHECK>, FUNC, PC, R_CHECK> extends RecurringAsserts.Base<SELF, FUNC, LConsumer<R_CHECK>> implements FAttest<SELF, FUNC, PC, R_CHECK, LConsumer<R_CHECK>> {

		public Full(FUNC value) {
			super(value);
		}

		public Full(FUNC value, String name) {
			super(value, name);
		}

	}

	public interface RecurringAsserts<SELF extends RecurringAsserts<SELF, RA>, RA> extends FluentTrait<SELF> {

		/**
		 * In case of some assertion that could be applied each time a method call result is tested, the argument assertion will be checked before the assertion
		 * for specific case.
		 */
		@Nonnull
		SELF inAllFollowingCases(@Nonnull RA recurringAssert);

		static abstract class Base<SELF extends Base<SELF, FUNC, RA>, FUNC, RA> extends Attest.Base<SELF, FUNC> implements RecurringAsserts<SELF, RA> {

			protected List<RA> recurringAssert = new ArrayList<>();

			public Base(FUNC value) {
				super(value);
			}

			public Base(FUNC value, String name) {
				super(value, name);
			}

			@Override
			@Nonnull
			public SELF inAllFollowingCases(@Nonnull RA recurringAssert) {
				this.recurringAssert.add(recurringAssert);
				return fluentCtx();
			}

			@Nonnull
			protected List<RA> recurringAssert() {
				return recurringAssert;
			}

		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 *
	 * @param <PC> Preconditioner can establish external conditions that are required in order for evaluation case to be completed.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static abstract class AbstractEvaluation<SELF extends AbstractEvaluation<SELF, CTX, PC, R_CHECK>, CTX extends Attest<CTX, ?>, PC, R_CHECK> implements FluentSubcontext<SELF, CTX> {

		protected @Nonnull Supplier<String> argDescription;
		protected @Nullable PC preconditioner;
		protected final @Nonnull AssertionFunction<PC, R_CHECK> assertFunction;
		protected final @Nullable List<LConsumer<R_CHECK>> assertPreConsumer;

		protected final @Nonnull CTX context;

		protected AbstractEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, R_CHECK> assertFunction, @Nullable List<LConsumer<R_CHECK>> assertPreConsumer) {
			this.argDescription = argDescription;
			this.assertPreConsumer = assertPreConsumer;
			this.assertFunction = Objects.requireNonNull(assertFunction);
			this.context = context;

		}

		protected AbstractEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nullable List<AssertionsCheck> assertPreConsumer, AssertionFunction<PC, R_CHECK> assertFunction) {
			this(context, argDescription, assertFunction, assertPreConsumer == null ? null : assertPreConsumer.stream().map(a -> (LConsumer<R_CHECK>) __ -> a.assertionsCheck()).collect(toList()));
		}

		public SELF when(PC preconditioner) {
			this.preconditioner = preconditioner;
			return fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX soThat(@Nonnull AssertionsCheck assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, RCHECK -> assertions.assertionsCheck());
			return context.fluentCtx();
		}

		public CTX withoutException() {
			exceptionCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, a -> {
			});
			return context.fluentCtx();
		}

		/** Assertion for the failure of the method under test. */
		public CTX withException(@Nonnull LConsumer<Checks.Check<? extends Throwable>> assertions) {
			exceptionCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the failure of the method under test. */
		public CTX withException(@Nullable String checkDescription, @Nonnull LConsumer<Checks.Check<? extends Throwable>> assertions) {
			exceptionCheck(checkDescription, argDescription, preconditioner, assertFunction, assertions);
			return context.fluentCtx();
		}

		protected R_CHECK normalCheck(@Nullable String checkDescription, @Nonnull Supplier<String> caseDescription, @Nullable PC preconditioner, @Nonnull AssertionFunction<PC, R_CHECK> assertFunction, @Nullable List<LConsumer<R_CHECK>> assertPreConsumer,
				@Nonnull LConsumer<R_CHECK> assertConsumer) {
			try {
				R_CHECK resultAssert = assertFunction.applyAndCreateResultAssert(checkDescription, preconditioner);

				try {
					if (assertPreConsumer != null) {
						assertPreConsumer.forEach(c -> c.accept(resultAssert));
					}
				} catch (AssertionError e) {
					throw new AssertionError(errorMessage(checkDescription, caseDescription, "Recurring assertion failed", e.getMessage()), e);
				}

				assertConsumer.accept(resultAssert);
				return resultAssert;
			} catch (AssertionError e) {
				throw new AssertionError(errorMessage(checkDescription, caseDescription, "Evaluation check failed", e.getMessage()), e);
			} catch (Throwable e) { // NOSONAR
				throw new AssertionError(errorMessage(checkDescription, caseDescription, "Should evaluate without problem", e.getMessage()), e);
			}
		}

		protected String errorMessage(@Nullable String checkDescription, @Nonnull Supplier<String> caseDescription, String msg1) {
			return context.failMessage(String.format("Case %s, check %s; %s", caseDescription.get(), checkDescription, msg1));
		}

		protected String errorMessage(@Nullable String checkDescription, @Nonnull Supplier<String> caseDescription, String msg1, String msg2) {
			return context.failMessage(String.format("Case %s, check %s; %s: %s", caseDescription.get(), checkDescription, msg1, msg2));
		}

		protected <X extends Throwable> void exceptionCheck(@Nullable String checkDescription, @Nonnull Supplier<String> caseDescription, @Nullable PC preconditioner, @Nonnull AssertionFunction<PC, R_CHECK> assertFunction,
				@Nonnull LConsumer<Checks.Check<? extends Throwable>> assertConsumer) {

			R_CHECK resultAssert = null;
			try {
				resultAssert = assertFunction.applyAndCreateResultAssert(checkDescription, preconditioner);
			} catch (Throwable e) { // NOSONAR
				assertConsumer.shovingAccept(Checks.attest(e, checkDescription));
				return;
			}

			String message = "Should evaluate with exception.";
			if (resultAssert instanceof FailPoint) {
				message = ((FailPoint) resultAssert).failMessage(message);
			}

			throw new AssertionError(errorMessage(checkDescription, caseDescription, message));
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class SemiEvaluation<CTX extends Simple<CTX, ?, PC>, PC, R_CHECK> extends AbstractEvaluation<SemiEvaluation<CTX, PC, R_CHECK>, CTX, PC, R_CHECK> {

		public SemiEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, R_CHECK> assertFunction, @Nullable List<AssertionsCheck> assertPreConsumer) {
			super(context, argDescription, assertPreConsumer, assertFunction);
		}
	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class BoolEvaluation<CTX extends Attest<CTX, ?>, PC> extends AbstractEvaluation<BoolEvaluation<CTX, PC>, CTX, PC, Checks.CheckBool> {

		public BoolEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.CheckBool> assertFunction, @Nullable List<LConsumer<Checks.CheckBool>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private boolean stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.CheckBool> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.CheckBool> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckBool> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckBool> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(boolean equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(boolean equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LBoolConsumer customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			boolean actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class Evaluation<CTX extends Attest<CTX, ?>, PC, V> extends AbstractEvaluation<Evaluation<CTX, PC, V>, CTX, PC, Checks.Check<V>> {

		public Evaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.Check<V>> assertFunction, @Nullable List<LConsumer<Checks.Check<V>>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private V stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.Check<V>> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.Check<V>> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.Check<V>> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.Check<V>> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(V equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(V equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LConsumer<V> customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			V actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class ByteEvaluation<CTX extends Attest<CTX, ?>, PC> extends AbstractEvaluation<ByteEvaluation<CTX, PC>, CTX, PC, Checks.CheckByte> {

		public ByteEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.CheckByte> assertFunction, @Nullable List<LConsumer<Checks.CheckByte>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private byte stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.CheckByte> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.CheckByte> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckByte> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckByte> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(byte equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(byte equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LByteConsumer customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			byte actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class DblEvaluation<CTX extends Attest<CTX, ?>, PC> extends AbstractEvaluation<DblEvaluation<CTX, PC>, CTX, PC, Checks.CheckDbl> {

		public DblEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.CheckDbl> assertFunction, @Nullable List<LConsumer<Checks.CheckDbl>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private double stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.CheckDbl> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.CheckDbl> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckDbl> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckDbl> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(double equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(double equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LDblConsumer customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			double actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class CharEvaluation<CTX extends Attest<CTX, ?>, PC> extends AbstractEvaluation<CharEvaluation<CTX, PC>, CTX, PC, Checks.CheckChar> {

		public CharEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.CheckChar> assertFunction, @Nullable List<LConsumer<Checks.CheckChar>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private char stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.CheckChar> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.CheckChar> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckChar> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckChar> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(char equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(char equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LCharConsumer customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			char actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class SrtEvaluation<CTX extends Attest<CTX, ?>, PC> extends AbstractEvaluation<SrtEvaluation<CTX, PC>, CTX, PC, Checks.CheckSrt> {

		public SrtEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.CheckSrt> assertFunction, @Nullable List<LConsumer<Checks.CheckSrt>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private short stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.CheckSrt> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.CheckSrt> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckSrt> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckSrt> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(short equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(short equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LSrtConsumer customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			short actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class FltEvaluation<CTX extends Attest<CTX, ?>, PC> extends AbstractEvaluation<FltEvaluation<CTX, PC>, CTX, PC, Checks.CheckFlt> {

		public FltEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.CheckFlt> assertFunction, @Nullable List<LConsumer<Checks.CheckFlt>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private float stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.CheckFlt> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.CheckFlt> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckFlt> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckFlt> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(float equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(float equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LFltConsumer customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			float actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class IntEvaluation<CTX extends Attest<CTX, ?>, PC> extends AbstractEvaluation<IntEvaluation<CTX, PC>, CTX, PC, Checks.CheckInt> {

		public IntEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.CheckInt> assertFunction, @Nullable List<LConsumer<Checks.CheckInt>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private int stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.CheckInt> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.CheckInt> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckInt> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckInt> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(int equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(int equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LIntConsumer customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			int actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

	/**
	 * Fluent sub-context for functional interface assertions.
	 */
	@Immutable
	@ThreadSafe
	@SuppressWarnings("unchecked")
	public static final class LongEvaluation<CTX extends Attest<CTX, ?>, PC> extends AbstractEvaluation<LongEvaluation<CTX, PC>, CTX, PC, Checks.CheckLong> {

		public LongEvaluation(@Nonnull CTX context, @Nonnull Supplier<String> argDescription, @Nonnull AssertionFunction<PC, Checks.CheckLong> assertFunction, @Nullable List<LConsumer<Checks.CheckLong>> assertPreConsumer) {
			super(context, argDescription, assertFunction, assertPreConsumer);
		}

		private long stealActualResult() {
			return normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, __ -> {
			}).value();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull LConsumer<Checks.CheckLong> assertions) {
			normalCheck(Checks.DEFAULT_DESCRIPTION, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull LConsumer<Checks.CheckLong> assertions) {
			return to(assertions);
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX to(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckLong> assertions) {
			normalCheck(checkDescription, argDescription, preconditioner, assertFunction, assertPreConsumer, assertions);
			return context.fluentCtx();
		}

		/** Assertion for the result. Depending on the CTX either "as" or "to" will have more sense. */
		public CTX as(@Nonnull String checkDescription, @Nonnull LConsumer<Checks.CheckLong> assertions) {
			return to(checkDescription, assertions);
		}

		/** Convenient method to just check equality */
		public CTX toEqualTo(long equalsTo) {
			to(__ -> __.must$(Be::equal$, equalsTo));
			return context.fluentCtx();
		}

		/** Convenient method to just check equality */
		public CTX asEqualTo(long equalsTo) {
			return toEqualTo(equalsTo);
		}

		/** Adds possibility to add custom checks for the value. The block is responsible for throwing exceptions on its own! */
		public CTX that(@Nonnull LLongConsumer customCheckBlock) {
			Null.nonNullArg(customCheckBlock, "customCheckBlock");
			long actualResult = stealActualResult();
			customCheckBlock.accept(actualResult);
			return context.fluentCtx();
		}

	}

}