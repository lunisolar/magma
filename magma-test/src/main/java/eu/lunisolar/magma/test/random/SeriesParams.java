/*
 *  This file is part of "lunisolar-lava".
 *  (C) Copyright 2018 Jakub Wach
 */

package eu.lunisolar.magma.test.random;

import eu.lunisolar.magma.basics.exceptions.X;
import eu.lunisolar.magma.func.predicate.LBiIntPredicate;
import eu.lunisolar.magma.func.supp.P;
import eu.lunisolar.magma.func.supplier.LSupplier;

import java.math.*;
import java.util.*;

import static eu.lunisolar.magma.func.predicate.LIntPredicate.doIf;
import static eu.lunisolar.magma.func.predicate.LPredicate.throwIf;
import static eu.lunisolar.magma.test.random.SimpleRandoms.*;

@SuppressWarnings("unchecked")
public final class SeriesParams<T> {

    String name;

    int size;

    int percentageA;
    int poolASize;

    int percentageB;
    int poolBSize;

    LSupplier<T> poolAProducer;
    LSupplier<T> poolBProducer;
    LSupplier<T> poolCProducer;

    //<editor-fold desc="data">
    public String name() {
        return name;
    }

    public SeriesParams name(String name) {
        this.name = name;
        return this;
    }

    public int size() {
        return size;
    }

    public SeriesParams size(int size) {
        this.size = size;
        return this;
    }

    public int percentageA() {
        return percentageA;
    }

    public SeriesParams percentageA(int percentageA) {
        this.percentageA = percentageA;
        return this;
    }

    public int poolASize() {
        return poolASize;
    }

    public SeriesParams poolASize(int poolASize) {
        this.poolASize = poolASize;
        return this;
    }

    public int percentageB() {
        return percentageB;
    }

    public SeriesParams percentageB(int percentageB) {
        this.percentageB = percentageB;
        return this;
    }

    public int poolBSize() {
        return poolBSize;
    }

    public SeriesParams poolBSize(int poolBSize) {
        this.poolBSize = poolBSize;
        return this;
    }

    public LSupplier<T> poolAProducer() {
        return poolAProducer;
    }

    public SeriesParams poolAProducer(LSupplier<T> poolAProducer) {
        this.poolAProducer = poolAProducer;
        return this;
    }

    public LSupplier<T> poolBProducer() {
        return poolBProducer;
    }

    public SeriesParams poolBProducer(LSupplier<T> poolBProducer) {
        this.poolBProducer = poolBProducer;
        return this;
    }

    public LSupplier<T> poolCProducer() {
        return poolCProducer;
    }

    public SeriesParams poolCProducer(LSupplier<T> poolCProducer) {
        this.poolCProducer = poolCProducer;
        return this;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("SeriesParams{");
        sb.append("name='").append(name).append('\'');
        sb.append(", size=").append(size);
        sb.append(", percentageA=").append(percentageA);
        sb.append(", poolASize=").append(poolASize);
        sb.append(", percentageB=").append(percentageB);
        sb.append(", poolBSize=").append(poolBSize);
        sb.append('}');
        return sb.toString();
    }

    //</editor-fold>

    public static <T> SeriesParams<T> params() {
        return new SeriesParams<>();
    }

    public SeriesParams<T> validate() {

        validatePercentageValue(percentageA, "Percentage A");
        validatePercentageValue(percentageB, "Percentage B");
        validatePercentageValue(percentageA + percentageB, "Percentage A+B");

        poolSize(poolASize, size, "PoolA");
        poolSize(poolBSize, size, "PoolB");

        poolSize(poolASize + poolBSize, size, "Pool A+B");

//        doIf(poolASize, v -> v != 0, () -> throwIf(poolAProducer, Objects::isNull, X::arg, "Producer %s cannot be null", "A"));
//        doIf(poolBSize, v -> v != 0, () -> throwIf(poolBProducer, Objects::isNull, X::arg, "Producer %s cannot be null", "B"));
//        doIf(poolASize + poolBSize, v -> v < 100, () -> throwIf(poolCProducer, Objects::isNull, X::arg, "Producer %s cannot be null", "C"));

        return this;
    }

    static void poolSize(int poolSize, int size, String poolName) {
//        LBiIntPredicate.throwIf(poolSize, P::lt, 0, X::arg, "%s size cannot be lower than 0, current is: %s", poolName, poolSize);
//        LBiIntPredicate.throwIf(poolSize, P::gt, size, X::arg, "%s size cannot be larger than %s, current is: %s", poolName, size, poolSize);
    }

    static void validatePercentageValue(int value, final String name) {
//        throwIf(value, v -> v < 0, X::arg, "%s cannot be lower than 0, current is: %s", name, value);
//        throwIf(value, v -> v > 100, X::arg, "%s cannot be higher than 100, current is: %s", name, value);
    }

    public static <T> SeriesParams<T> allRandom(LSupplier<T> supplierC) {
        return SeriesParams.<T>params().size(65_000).poolASize(0).poolBSize(0).poolCProducer(supplierC);
    }

    public enum Distribution {

        LITERALS(() -> SeriesParams.params()
                                   // A set of values loosely corresponding to RDF literals, A (repetitive values), B (less repetitive), C (random).
                                   .size(65_000)                   // as a result will prepare 65'000 element array, of elements sets A, B, C
                                   .poolASize(100)                 // set A will have 100 values to choose from
                                   .percentageA(30)                // there is x% chance that position in final array will be from set A
                                   .poolBSize(3000)                // set B will have 3000 values to choose from
                                   .percentageB(30)                // there is x% chance that position in final array will be from setB
        ),

        PREDICATES(() -> SeriesParams.params()
                                     // A set of values loosely corresponding to RDF predicates
                                     .size(65_000)
                                     .poolASize(200)
                                     .percentageA(60)
                                     .poolBSize(6000)
                                     .percentageB(20)
        ),

        RANDOM(() -> SeriesParams.params()
                                 .size(65_000)
                                 .poolASize(200)
                                 .percentageA(10)
                                 .poolBSize(6000)
                                 .percentageB(20)
        ),

        //
        ;

        private final LSupplier<SeriesParams> supplier;
        Distribution(LSupplier<SeriesParams> supplier) {this.supplier = supplier;}
    }

    public static <T> SeriesParams<T> distributed(Distribution distribution, LSupplier<T> supplierA, LSupplier<T> supplierB, LSupplier<T> supplierC) {
        return distribution.supplier.get()
                                    .size(65_000)
                                    .poolAProducer(supplierA)
                                    .poolBProducer(supplierB)
                                    .poolCProducer(supplierC);
    }

    public static SeriesParams<Byte> bytes(Distribution distribution) {
        return distributed(distribution, () -> SimpleRandoms.aByte((byte) 127), SimpleRandoms::aByte, SimpleRandoms::aByte);
    }

    public static SeriesParams<Short> unsignedBytes(Distribution distribution) {
        return distributed(distribution, SimpleRandoms::aUnsignedByte, SimpleRandoms::aUnsignedByte, SimpleRandoms::aUnsignedByte);
    }

    public static SeriesParams<Integer> ints(Distribution distribution) {
        return distributed(distribution, () -> anInt(100), () -> anInt(3000), SimpleRandoms::anInt);
    }

    public static SeriesParams<Long> unsignedInts(Distribution distribution) {
        return distributed(distribution, () -> anUnsignedInt(100), () -> anUnsignedInt(3000), SimpleRandoms::anUnsignedInt);
    }

    public static SeriesParams<Long> longs(Distribution distribution) {
        return distributed(distribution, () -> aLong(100), () -> aLong(3000), SimpleRandoms::aLong);
    }

    public static SeriesParams<BigInteger> bigIntegers(Distribution distribution) {
        return distributed(distribution, () -> aBigInt(100), () -> aBigInt(3000), SimpleRandoms::aBigInt);
    }

    public static SeriesParams<String> strings(Distribution distribution) {
        return distributed(distribution, () -> aString(5, 16), () -> aString(5, 100), () -> aString(40, 250));
    }

    public static SeriesParams<String> urls(Distribution distribution) {
        return distributed(distribution, () -> aUrl(40, 80), () -> aUrl(40, 100), () -> aUrl(40, 100));
    }
}
