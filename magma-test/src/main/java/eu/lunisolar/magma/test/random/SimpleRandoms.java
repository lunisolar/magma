/*
 *  This file is part of "lunisolar-lava".
 *  (C) Copyright 2018 Jakub Wach
 */

package eu.lunisolar.magma.test.random;

import eu.lunisolar.magma.basics.exceptions.X;
import eu.lunisolar.magma.func.predicate.LPredicate;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supplier.LSupplier;

import java.math.*;
import java.util.function.*;

import static eu.lunisolar.magma.func.action.LAction.times;
import static eu.lunisolar.magma.func.predicate.LBiIntPredicate.throwIf$;
import static java.lang.Math.*;
import static java.math.BigInteger.*;
import static java.util.concurrent.ThreadLocalRandom.*;

@SuppressWarnings("unused")
public final class SimpleRandoms {

    private static final int  UNSIGNED_BYTE_MAX = (short) (pow(2, 8) - 1);
    private static final long UNSIGNED_INT_MAX  = (long) (pow(2, 32) - 1);

    static final LSupplier<Character>  RANDOM_CHAR      = () -> (char) current().nextInt();
    static final LPredicate<Character> IS_READABLE_CHAR = i -> Character.isLetterOrDigit(i) && (i < 127);

    public static byte aByte(byte bound)                 { return (byte) current().nextInt(bound);}
    public static byte aByte()                           { return (byte) current().nextInt();}
    public static short aUnsignedByte()                  { return (short) current().nextInt(0, UNSIGNED_BYTE_MAX + 1);}
    public static int anInt()                            { return current().nextInt();}
    public static int anInt(int bound)                   { return current().nextInt(bound);}
    public static int anInt(int min, int max)            { return current().nextInt(min, max + 1);}

    public static long anUnsignedInt()                   { return current().nextLong(0, UNSIGNED_INT_MAX + 1);}
    public static long anUnsignedInt(int bound)          { return current().nextInt(bound);}
    public static long anUnsignedInt(int min, int max)   { return current().nextInt(min, max + 1);}

    public static long aLong()                           { return current().nextLong();}
    public static long aLong(long bound)                 { return current().nextLong(bound);}
    public static long aLong(long min, long max)         { return current().nextLong(min, max);}
    public static BigInteger aBigInt()                   { return valueOf(aLong());}
    public static BigInteger aBigInt(long bound)         { return valueOf(aLong(bound));}
    public static BigInteger aBigInt(long min, long max) { return valueOf(aLong(min, max));}
    public static char aChar()                           { return conditional(RANDOM_CHAR, IS_READABLE_CHAR); }
    public static boolean aBoolean()                     { return current().nextBoolean();}

    public static String aString(int length) {
        return aString(length, length);
    }

    public static String aString(int minLength, int maxLength) {

        throwIf$(minLength, P::gt, maxLength, X::arg, "Min length (%d) must be lower or equal to max length (%d).");
        throwIf$(minLength, P::lt, 0, X::arg, "Min length must be higher than zero, current value: %d");
        throwIf$(maxLength, P::lt, 0, X::arg, "Max length must be higher than zero, current value: %d");

        var length = anInt(minLength, maxLength);

        StringBuffer sb = new StringBuffer(length);
        times(length, () -> sb.append(aChar()));
        String build = sb.toString();
//        log.info("String: {}", build);
        return build;
    }

    public static String aUrl(int length) {
        return aUrl(length, length);
    }

    public static String aUrl(int minLength, int maxLength) {
        return "http://server/" + aString(minLength, maxLength);
    }

    private static SimpleRandoms INSTANCE = new SimpleRandoms();

    public static SimpleRandoms rnd() {
        return INSTANCE;
    }

    private static <V> V conditional(Supplier<V> supplier, Predicate<V> acceptanceTest) {
        V v;
        do { v = supplier.get(); } while (!acceptanceTest.test(v));
        return v;
    }
}