/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.basics;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * - What do you mean "45 does not equal to 45"?
 * - '45'^^String does not equal to '45'^^int
 */
public final class ToStr {

    //<editor-fold desc="no instance">
    private ToStr() {
    }
    //</editor-fold>

    public static String NULL_REPRESENTATION = "<null>";

    @Nonnull private static StringBuilder sb() {
        return new StringBuilder();
    }

    public static @Nonnull String toStr(@Nullable Object o) {
        if (o == null) {
            return NULL_REPRESENTATION;
        }
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, @Nullable Object o) {
        if (o == null) {
            return sb.append(NULL_REPRESENTATION);
        }
        return sb.append('\'').append(o).append('\'').append("^^").append(o.getClass().getSimpleName());
    }

    public static String toStr(byte o) {
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, byte o) {
        return sb.append('\'').append(o).append('\'').append("^^byte");
    }

    public static String toStr(short o) {
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, short o) {
        return sb.append('\'').append(o).append('\'').append("^^short");
    }

    public static String toStr(int o) {
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, int o) {
        return sb.append('\'').append(o).append('\'').append("^^int");
    }

    public static String toStr(long o) {
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, long o) {
        return sb.append('\'').append(o).append('\'').append("^^long");
    }

    public static String toStr(float o) {
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, float o) {
        return sb.append('\'').append(o).append('\'').append("^^float");
    }

    public static String toStr(double o) {
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, double o) {
        return sb.append('\'').append(o).append('\'').append("^^double");
    }

    public static String toStr(boolean o) {
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, boolean o) {
        return sb.append('\'').append(o).append('\'').append("^^boolean");
    }

    public static String toStr(char o) {
        return toSb(sb(), o).toString();
    }

    public static StringBuilder toSb(@Nonnull StringBuilder sb, char o) {
        return sb.append('\'').append(o).append('\'').append("^^char");
    }

}