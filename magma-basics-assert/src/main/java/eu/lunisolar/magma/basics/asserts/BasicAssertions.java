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

package eu.lunisolar.magma.basics.asserts;

import org.assertj.core.api.*;

import javax.annotation.Nonnull;

/**
 * A very base of interfaces producing assertions.
 *
 * - The intention is to have a single instance of a single class implementing all interfaces that are required.
 * - It is possible to override factory method for ObjectAssert and provide more specialized implementation.
 *
 * @param <OS> It should be assert class for objects. Unfortunately it cannot be restricted so easily with "extends Assert<OS,?>". But Thankfully interface
 *             itself is not intended to be used directly.
 */
@SuppressWarnings("unchecked")
public interface BasicAssertions<OS extends Assert> extends AssertContext<OS> {

    @Nonnull
    default OS assertThatObj(Object actual) {
        
        Class a = Assertions.class;

        return (OS) Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractBooleanAssert assertThatBool(Boolean actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractByteAssert assertThatByte(Byte actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractDoubleAssert assertThatDbl(Double actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractFloatAssert assertThatFlt(Float actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractIntegerAssert assertThatInt(Integer actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractLongAssert assertThatLong(Long actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractShortAssert assertThatSrt(Short actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractCharacterAssert assertThatChar(Character actual) {
        return Assertions.assertThat(actual);
    }

}
