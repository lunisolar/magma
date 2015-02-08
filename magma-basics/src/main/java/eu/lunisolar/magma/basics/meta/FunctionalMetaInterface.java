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
package eu.lunisolar.magma.basics.meta;

import java.util.Formattable;
import java.util.Formatter;

/** Meta interface for functional interfaces in the library. */
public interface FunctionalMetaInterface extends Formattable {

    String functionalInterfaceDescription();

    default boolean isFunction() {
        return false;
    }

    default boolean isOperator() {
        return false;
    }

    default boolean isPredicate() {
        return false;
    }

    default boolean isConsumer() {
        return false;
    }

    default boolean isSupplier() {
        return false;
    }

    default boolean isAction() {
        return false;
    }

    default boolean isThrowing() {
        return false;
    }

    @Override
    default void formatTo(Formatter formatter, int flags, int width, int precision) {
        formatter.format("%s {defined as %s}", this.functionalInterfaceDescription(), this.toString());
    }

}
