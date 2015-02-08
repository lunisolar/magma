/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
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
public interface BasicAssertions<OS> {

    @Nonnull
    default OS assertThat(Object actual) {
        return (OS) Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractBooleanAssert<?> assertThat(boolean actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractBooleanAssert<?> assertThat(Boolean actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractByteAssert<?> assertThat(byte actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractByteAssert<?> assertThat(Byte actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractDoubleAssert<?> assertThat(Double actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractDoubleAssert<?> assertThat(double actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractFloatAssert<?> assertThat(float actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractFloatAssert<?> assertThat(Float actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractIntegerAssert<?> assertThat(int actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractIntegerAssert<?> assertThat(Integer actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractLongAssert<?> assertThat(long actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractLongAssert<?> assertThat(Long actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractShortAssert<?> assertThat(short actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractShortAssert<?> assertThat(Short actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractCharacterAssert<?> assertThat(char actual) {
        return Assertions.assertThat(actual);
    }

    @Nonnull
    default AbstractCharacterAssert<?> assertThat(Character actual) {
        return Assertions.assertThat(actual);
    }

}
