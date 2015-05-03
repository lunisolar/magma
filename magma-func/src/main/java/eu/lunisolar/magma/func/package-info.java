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

/**
 * Functional interfaces / lambdas
 * ===================
 *
 * | Name                                                                              | Domain                                   | Co-domain  |
 * |:----------------------------------------------------------------------------------|:-----------------------------------------|:-----------|
 * | {@link eu.lunisolar.magma.func.action.Action}                                    |                                          |            |
 * | {@link eu.lunisolar.magma.func.action.ActionX}                                   |                                          |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.Consumer}                                | T t                                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.ConsumerX}                               | T t                                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.BiConsumer}                              | T1 t1,T2 t2                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.BiConsumerX}                             | T1 t1,T2 t2                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.TriConsumer}                             | T1 t1,T2 t2,T3 t3                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.TriConsumerX}                            | T1 t1,T2 t2,T3 t3                        |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.ByteConsumer}                 | byte b                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.ByteConsumerX}                | byte b                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.ShortConsumer}                | short s                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.ShortConsumerX}               | short s                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.IntConsumer}                  | int i                                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.IntConsumerX}                 | int i                                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LongConsumer}                 | long l                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LongConsumerX}                | long l                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.FloatConsumer}                | float f                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.FloatConsumerX}               | float f                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.DoubleConsumer}               | double d                                 |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.DoubleConsumerX}              | double d                                 |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.CharConsumer}                 | char c                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.CharConsumerX}                | char c                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.BooleanConsumer}              | boolean b                                |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.BooleanConsumerX}             | boolean b                                |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.ByteBiConsumer}            | byte b1,byte b2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.ByteBiConsumerX}           | byte b1,byte b2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.ShortBiConsumer}           | short s1,short s2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.ShortBiConsumerX}          | short s1,short s2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.IntBiConsumer}             | int i1,int i2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.IntBiConsumerX}            | int i1,int i2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LongBiConsumer}            | long l1,long l2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LongBiConsumerX}           | long l1,long l2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.FloatBiConsumer}           | float f1,float f2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.FloatBiConsumerX}          | float f1,float f2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.DoubleBiConsumer}          | double d1,double d2                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.DoubleBiConsumerX}         | double d1,double d2                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.CharBiConsumer}            | char c1,char c2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.CharBiConsumerX}           | char c1,char c2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.BooleanBiConsumer}         | boolean b1,boolean b2                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.BooleanBiConsumerX}        | boolean b1,boolean b2                    |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.BooleanTriConsumer}       | boolean b1,boolean b2,boolean b3         |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.BooleanTriConsumerX}      | boolean b1,boolean b2,boolean b3         |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjByteConsumer}          | T t, byte b                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjByteConsumerX}         | T t, byte b                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjShortConsumer}         | T t, short s                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjShortConsumerX}        | T t, short s                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjIntConsumer}           | T t, int i                               |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjIntConsumerX}          | T t, int i                               |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjLongConsumer}          | T t, long l                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjLongConsumerX}         | T t, long l                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjFloatConsumer}         | T t, float f                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjFloatConsumerX}        | T t, float f                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjDoubleConsumer}        | T t, double d                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjDoubleConsumerX}       | T t, double d                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjCharConsumer}          | T t, char c                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjCharConsumerX}         | T t, char c                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjBooleanConsumer}       | T t, boolean b                           |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.ObjBooleanConsumerX}      | T t, boolean b                           |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjByteConsumer}        | T1 t1,T2 t2, byte b                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjByteConsumerX}       | T1 t1,T2 t2, byte b                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjShortConsumer}       | T1 t1,T2 t2, short s                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjShortConsumerX}      | T1 t1,T2 t2, short s                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjIntConsumer}         | T1 t1,T2 t2, int i                       |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjIntConsumerX}        | T1 t1,T2 t2, int i                       |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjLongConsumer}        | T1 t1,T2 t2, long l                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjLongConsumerX}       | T1 t1,T2 t2, long l                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjFloatConsumer}       | T1 t1,T2 t2, float f                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjFloatConsumerX}      | T1 t1,T2 t2, float f                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjDoubleConsumer}      | T1 t1,T2 t2, double d                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjDoubleConsumerX}     | T1 t1,T2 t2, double d                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjCharConsumer}        | T1 t1,T2 t2, char c                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjCharConsumerX}       | T1 t1,T2 t2, char c                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjBooleanConsumer}     | T1 t1,T2 t2, boolean b                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.BiObjBooleanConsumerX}    | T1 t1,T2 t2, boolean b                   |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.operator.unary.UnaryOperator}                     | T t                                      | T          |
 * | {@link eu.lunisolar.magma.func.operator.unary.UnaryOperatorX}                    | T t                                      | T          |
 * | {@link eu.lunisolar.magma.func.operator.binary.BinaryOperator}                   | T t1,T t2                                | T          |
 * | {@link eu.lunisolar.magma.func.operator.binary.BinaryOperatorX}                  | T t1,T t2                                | T          |
 * | {@link eu.lunisolar.magma.func.operator.ternary.TernaryOperator}                 | T t1,T t2,T t3                           | T          |
 * | {@link eu.lunisolar.magma.func.operator.ternary.TernaryOperatorX}                | T t1,T t2,T t3                           | T          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.operator.unary.ByteUnaryOperator}                 | byte b                                   | byte       |
 * | {@link eu.lunisolar.magma.func.operator.unary.ByteUnaryOperatorX}                | byte b                                   | byte       |
 * | {@link eu.lunisolar.magma.func.operator.unary.ShortUnaryOperator}                | short s                                  | short      |
 * | {@link eu.lunisolar.magma.func.operator.unary.ShortUnaryOperatorX}               | short s                                  | short      |
 * | {@link eu.lunisolar.magma.func.operator.unary.IntUnaryOperator}                  | int i                                    | int        |
 * | {@link eu.lunisolar.magma.func.operator.unary.IntUnaryOperatorX}                 | int i                                    | int        |
 * | {@link eu.lunisolar.magma.func.operator.unary.LongUnaryOperator}                 | long l                                   | long       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LongUnaryOperatorX}                | long l                                   | long       |
 * | {@link eu.lunisolar.magma.func.operator.unary.FloatUnaryOperator}                | float f                                  | float      |
 * | {@link eu.lunisolar.magma.func.operator.unary.FloatUnaryOperatorX}               | float f                                  | float      |
 * | {@link eu.lunisolar.magma.func.operator.unary.DoubleUnaryOperator}               | double d                                 | double     |
 * | {@link eu.lunisolar.magma.func.operator.unary.DoubleUnaryOperatorX}              | double d                                 | double     |
 * | {@link eu.lunisolar.magma.func.operator.unary.CharUnaryOperator}                 | char c                                   | char       |
 * | {@link eu.lunisolar.magma.func.operator.unary.CharUnaryOperatorX}                | char c                                   | char       |
 * | {@link eu.lunisolar.magma.func.operator.unary.BooleanUnaryOperator}              | boolean b                                | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.unary.BooleanUnaryOperatorX}             | boolean b                                | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.operator.binary.ByteBinaryOperator}               | byte b1,byte b2                          | byte       |
 * | {@link eu.lunisolar.magma.func.operator.binary.ByteBinaryOperatorX}              | byte b1,byte b2                          | byte       |
 * | {@link eu.lunisolar.magma.func.operator.binary.ShortBinaryOperator}              | short s1,short s2                        | short      |
 * | {@link eu.lunisolar.magma.func.operator.binary.ShortBinaryOperatorX}             | short s1,short s2                        | short      |
 * | {@link eu.lunisolar.magma.func.operator.binary.IntBinaryOperator}                | int i1,int i2                            | int        |
 * | {@link eu.lunisolar.magma.func.operator.binary.IntBinaryOperatorX}               | int i1,int i2                            | int        |
 * | {@link eu.lunisolar.magma.func.operator.binary.LongBinaryOperator}               | long l1,long l2                          | long       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LongBinaryOperatorX}              | long l1,long l2                          | long       |
 * | {@link eu.lunisolar.magma.func.operator.binary.FloatBinaryOperator}              | float f1,float f2                        | float      |
 * | {@link eu.lunisolar.magma.func.operator.binary.FloatBinaryOperatorX}             | float f1,float f2                        | float      |
 * | {@link eu.lunisolar.magma.func.operator.binary.DoubleBinaryOperator}             | double d1,double d2                      | double     |
 * | {@link eu.lunisolar.magma.func.operator.binary.DoubleBinaryOperatorX}            | double d1,double d2                      | double     |
 * | {@link eu.lunisolar.magma.func.operator.binary.CharBinaryOperator}               | char c1,char c2                          | char       |
 * | {@link eu.lunisolar.magma.func.operator.binary.CharBinaryOperatorX}              | char c1,char c2                          | char       |
 * | {@link eu.lunisolar.magma.func.operator.binary.BooleanBinaryOperator}            | boolean b1,boolean b2                    | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.binary.BooleanBinaryOperatorX}           | boolean b1,boolean b2                    | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.operator.ternary.BooleanTernaryOperator}          | boolean b1,boolean b2,boolean b3         | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.ternary.BooleanTernaryOperatorX}         | boolean b1,boolean b2,boolean b3         | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.Function}                                | T t                                      | R          |
 * | {@link eu.lunisolar.magma.func.function.FunctionX}                               | T t                                      | R          |
 * | {@link eu.lunisolar.magma.func.function.BiFunction}                              | T1 t1,T2 t2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.BiFunctionX}                             | T1 t1,T2 t2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.TriFunction}                             | T1 t1,T2 t2,T3 t3                        | R          |
 * | {@link eu.lunisolar.magma.func.function.TriFunctionX}                            | T1 t1,T2 t2,T3 t3                        | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.ByteFunction}                       | byte b                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ByteFunctionX}                      | byte b                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ShortFunction}                      | short s                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ShortFunctionX}                     | short s                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.IntFunction}                        | int i                                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.IntFunctionX}                       | int i                                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LongFunction}                       | long l                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LongFunctionX}                      | long l                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.FloatFunction}                      | float f                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.FloatFunctionX}                     | float f                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.DoubleFunction}                     | double d                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.DoubleFunctionX}                    | double d                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.CharFunction}                       | char c                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.CharFunctionX}                      | char c                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BooleanFunction}                    | boolean b                                | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BooleanFunctionX}                   | boolean b                                | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.ByteBiFunction}                     | byte b1,byte b2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ByteBiFunctionX}                    | byte b1,byte b2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ShortBiFunction}                    | short s1,short s2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ShortBiFunctionX}                   | short s1,short s2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.IntBiFunction}                      | int i1,int i2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.IntBiFunctionX}                     | int i1,int i2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LongBiFunction}                     | long l1,long l2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LongBiFunctionX}                    | long l1,long l2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.FloatBiFunction}                    | float f1,float f2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.FloatBiFunctionX}                   | float f1,float f2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.DoubleBiFunction}                   | double d1,double d2                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.DoubleBiFunctionX}                  | double d1,double d2                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.CharBiFunction}                     | char c1,char c2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.CharBiFunctionX}                    | char c1,char c2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BooleanBiFunction}                  | boolean b1,boolean b2                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BooleanBiFunctionX}                 | boolean b1,boolean b2                    | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.BooleanTriFunction}                 | boolean b1,boolean b2,boolean b3         | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BooleanTriFunctionX}                | boolean b1,boolean b2,boolean b3         | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.ObjByteFunction}                    | T t, byte i                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjByteFunctionX}                   | T t, byte i                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjShortFunction}                   | T t, short s                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjShortFunctionX}                  | T t, short s                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjIntFunction}                     | T t, int i                               | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjIntFunctionX}                    | T t, int i                               | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjLongFunction}                    | T t, long l                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjLongFunctionX}                   | T t, long l                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjFloatFunction}                   | T t, float f                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjFloatFunctionX}                  | T t, float f                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjDoubleFunction}                  | T t, double d                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjDoubleFunctionX}                 | T t, double d                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjCharFunction}                    | T t, char c                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjCharFunctionX}                   | T t, char c                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjBooleanFunction}                 | T t, boolean b                           | R          |
 * | {@link eu.lunisolar.magma.func.function.from.ObjBooleanFunctionX}                | T t, boolean b                           | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjByteFunction}                  | T1 t1,T2 t2, byte i                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjByteFunctionX}                 | T1 t1,T2 t2, byte i                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjShortFunction}                 | T1 t1,T2 t2, short s                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjShortFunctionX}                | T1 t1,T2 t2, short s                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjIntFunction}                   | T1 t1,T2 t2, int i                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjIntFunctionX}                  | T1 t1,T2 t2, int i                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjLongFunction}                  | T1 t1,T2 t2, long l                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjLongFunctionX}                 | T1 t1,T2 t2, long l                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjFloatFunction}                 | T1 t1,T2 t2, float f                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjFloatFunctionX}                | T1 t1,T2 t2, float f                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjDoubleFunction}                | T1 t1,T2 t2, double d                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjDoubleFunctionX}               | T1 t1,T2 t2, double d                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjCharFunction}                  | T1 t1,T2 t2, char c                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjCharFunctionX}                 | T1 t1,T2 t2, char c                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjBooleanFunction}               | T1 t1,T2 t2, boolean b                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.BiObjBooleanFunctionX}              | T1 t1,T2 t2, boolean b                   | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.to.ToByteFunction}                       | T t                                      | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.ToByteFunctionX}                      | T t                                      | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.ToShortFunction}                      | T t                                      | short      |
 * | {@link eu.lunisolar.magma.func.function.to.ToShortFunctionX}                     | T t                                      | short      |
 * | {@link eu.lunisolar.magma.func.function.to.ToIntFunction}                        | T t                                      | int        |
 * | {@link eu.lunisolar.magma.func.function.to.ToIntFunctionX}                       | T t                                      | int        |
 * | {@link eu.lunisolar.magma.func.function.to.ToLongFunction}                       | T t                                      | long       |
 * | {@link eu.lunisolar.magma.func.function.to.ToLongFunctionX}                      | T t                                      | long       |
 * | {@link eu.lunisolar.magma.func.function.to.ToFloatFunction}                      | T t                                      | float      |
 * | {@link eu.lunisolar.magma.func.function.to.ToFloatFunctionX}                     | T t                                      | float      |
 * | {@link eu.lunisolar.magma.func.function.to.ToDoubleFunction}                     | T t                                      | double     |
 * | {@link eu.lunisolar.magma.func.function.to.ToDoubleFunctionX}                    | T t                                      | double     |
 * | {@link eu.lunisolar.magma.func.function.to.ToCharFunction}                       | T t                                      | char       |
 * | {@link eu.lunisolar.magma.func.function.to.ToCharFunctionX}                      | T t                                      | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.to.ToByteBiFunction}                     | T1 t1,T2 t2                              | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.ToByteBiFunctionX}                    | T1 t1,T2 t2                              | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.ToShortBiFunction}                    | T1 t1,T2 t2                              | short      |
 * | {@link eu.lunisolar.magma.func.function.to.ToShortBiFunctionX}                   | T1 t1,T2 t2                              | short      |
 * | {@link eu.lunisolar.magma.func.function.to.ToIntBiFunction}                      | T1 t1,T2 t2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.ToIntBiFunctionX}                     | T1 t1,T2 t2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.ToLongBiFunction}                     | T1 t1,T2 t2                              | long       |
 * | {@link eu.lunisolar.magma.func.function.to.ToLongBiFunctionX}                    | T1 t1,T2 t2                              | long       |
 * | {@link eu.lunisolar.magma.func.function.to.ToFloatBiFunction}                    | T1 t1,T2 t2                              | float      |
 * | {@link eu.lunisolar.magma.func.function.to.ToFloatBiFunctionX}                   | T1 t1,T2 t2                              | float      |
 * | {@link eu.lunisolar.magma.func.function.to.ToDoubleBiFunction}                   | T1 t1,T2 t2                              | double     |
 * | {@link eu.lunisolar.magma.func.function.to.ToDoubleBiFunctionX}                  | T1 t1,T2 t2                              | double     |
 * | {@link eu.lunisolar.magma.func.function.to.ToCharBiFunction}                     | T1 t1,T2 t2                              | char       |
 * | {@link eu.lunisolar.magma.func.function.to.ToCharBiFunctionX}                    | T1 t1,T2 t2                              | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.to.ObjIntToIntFunction}                  | T t, int i                               | int        |
 * | {@link eu.lunisolar.magma.func.function.to.ObjIntToIntFunctionX}                 | T t, int i                               | int        |
 * |                                                                                   |                                          |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToShortFunction}          | byte b                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToShortFunctionX}         | byte b                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToIntFunction}            | byte b                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToIntFunctionX}           | byte b                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToLongFunction}           | byte b                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToLongFunctionX}          | byte b                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToFloatFunction}          | byte b                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToFloatFunctionX}         | byte b                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToDoubleFunction}         | byte b                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToDoubleFunctionX}        | byte b                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToCharFunction}           | byte b                                   | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.ByteToCharFunctionX}          | byte b                                   | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToByteFunction}          | short s                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToByteFunctionX}         | short s                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToIntFunction}           | short s                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToIntFunctionX}          | short s                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToLongFunction}          | short s                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToLongFunctionX}         | short s                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToFloatFunction}         | short s                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToFloatFunctionX}        | short s                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToDoubleFunction}        | short s                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToDoubleFunctionX}       | short s                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToCharFunction}          | short s                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.ShortToCharFunctionX}         | short s                                  | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToByteFunction}            | int i                                    | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToByteFunctionX}           | int i                                    | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToShortFunction}           | int i                                    | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToShortFunctionX}          | int i                                    | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToLongFunction}            | int i                                    | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToLongFunctionX}           | int i                                    | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToFloatFunction}           | int i                                    | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToFloatFunctionX}          | int i                                    | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToDoubleFunction}          | int i                                    | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToDoubleFunctionX}         | int i                                    | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToCharFunction}            | int i                                    | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.IntToCharFunctionX}           | int i                                    | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToByteFunction}           | long l                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToByteFunctionX}          | long l                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToShortFunction}          | long l                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToShortFunctionX}         | long l                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToIntFunction}            | long l                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToIntFunctionX}           | long l                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToFloatFunction}          | long l                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToFloatFunctionX}         | long l                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToDoubleFunction}         | long l                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToDoubleFunctionX}        | long l                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToCharFunction}           | long l                                   | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LongToCharFunctionX}          | long l                                   | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToByteFunction}          | float f                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToByteFunctionX}         | float f                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToShortFunction}         | float f                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToShortFunctionX}        | float f                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToIntFunction}           | float f                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToIntFunctionX}          | float f                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToLongFunction}          | float f                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToLongFunctionX}         | float f                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToDoubleFunction}        | float f                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToDoubleFunctionX}       | float f                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToCharFunction}          | float f                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.FloatToCharFunctionX}         | float f                                  | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToByteFunction}         | double d                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToByteFunctionX}        | double d                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToShortFunction}        | double d                                 | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToShortFunctionX}       | double d                                 | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToIntFunction}          | double d                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToIntFunctionX}         | double d                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToLongFunction}         | double d                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToLongFunctionX}        | double d                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToFloatFunction}        | double d                                 | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToFloatFunctionX}       | double d                                 | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToCharFunction}         | double d                                 | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.DoubleToCharFunctionX}        | double d                                 | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToByteFunction}           | char c                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToByteFunctionX}          | char c                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToShortFunction}          | char c                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToShortFunctionX}         | char c                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToIntFunction}            | char c                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToIntFunctionX}           | char c                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToLongFunction}           | char c                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToLongFunctionX}          | char c                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToFloatFunction}          | char c                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToFloatFunctionX}         | char c                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToDoubleFunction}         | char c                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.CharToDoubleFunctionX}        | char c                                   | double     |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToByteFunction}        | boolean b                                | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToByteFunctionX}       | boolean b                                | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToShortFunction}       | boolean b                                | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToShortFunctionX}      | boolean b                                | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToIntFunction}         | boolean b                                | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToIntFunctionX}        | boolean b                                | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToLongFunction}        | boolean b                                | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToLongFunctionX}       | boolean b                                | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToFloatFunction}       | boolean b                                | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToFloatFunctionX}      | boolean b                                | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToDoubleFunction}      | boolean b                                | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToDoubleFunctionX}     | boolean b                                | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToCharFunction}        | boolean b                                | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.BooleanToCharFunctionX}       | boolean b                                | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.Predicate}                              | T t                                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.PredicateX}                             | T t                                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiPredicate}                            | T1 t1,T2 t2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiPredicateX}                           | T1 t1,T2 t2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.TriPredicate}                           | T1 t1,T2 t2,T3 t3                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.TriPredicateX}                          | T1 t1,T2 t2,T3 t3                        | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.BytePredicate}                          | byte b                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BytePredicateX}                         | byte b                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ShortPredicate}                         | short s                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ShortPredicateX}                        | short s                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.IntPredicate}                           | int i                                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.IntPredicateX}                          | int i                                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LongPredicate}                          | long l                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LongPredicateX}                         | long l                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.FloatPredicate}                         | float f                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.FloatPredicateX}                        | float f                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.DoublePredicate}                        | double d                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.DoublePredicateX}                       | double d                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.CharPredicate}                          | char c                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.CharPredicateX}                         | char c                                   | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.BiBytePredicate}                        | byte b1,byte b2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiBytePredicateX}                       | byte b1,byte b2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiShortPredicate}                       | short s1,short s2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiShortPredicateX}                      | short s1,short s2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiIntPredicate}                         | int i1,int i2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiIntPredicateX}                        | int i1,int i2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiLongPredicate}                        | long l1,long l2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiLongPredicateX}                       | long l1,long l2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiFloatPredicate}                       | float f1,float f2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiFloatPredicateX}                      | float f1,float f2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiDoublePredicate}                      | double d1,double d2                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiDoublePredicateX}                     | double d1,double d2                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiCharPredicate}                        | char c1,char c2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiCharPredicateX}                       | char c1,char c2                          | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.ObjBytePredicate}                       | T t, byte b                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjBytePredicateX}                      | T t, byte b                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjShortPredicate}                      | T t, short s                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjShortPredicateX}                     | T t, short s                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjIntPredicate}                        | T t, int i                               | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjIntPredicateX}                       | T t, int i                               | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjLongPredicate}                       | T t, long l                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjLongPredicateX}                      | T t, long l                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjFloatPredicate}                      | T t, float f                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjFloatPredicateX}                     | T t, float f                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjDoublePredicate}                     | T t, double d                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjDoublePredicateX}                    | T t, double d                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjCharPredicate}                       | T t, char c                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjCharPredicateX}                      | T t, char c                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjBooleanPredicate}                    | T t, boolean b                           | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.ObjBooleanPredicateX}                   | T t, boolean b                           | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjBytePredicate}                     | T1 t1,T2 t2, byte b                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjBytePredicateX}                    | T1 t1,T2 t2, byte b                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjShortPredicate}                    | T1 t1,T2 t2, short s                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjShortPredicateX}                   | T1 t1,T2 t2, short s                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjIntPredicate}                      | T1 t1,T2 t2, int i                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjIntPredicateX}                     | T1 t1,T2 t2, int i                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjLongPredicate}                     | T1 t1,T2 t2, long l                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjLongPredicateX}                    | T1 t1,T2 t2, long l                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjFloatPredicate}                    | T1 t1,T2 t2, float f                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjFloatPredicateX}                   | T1 t1,T2 t2, float f                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjDoublePredicate}                   | T1 t1,T2 t2, double d                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjDoublePredicateX}                  | T1 t1,T2 t2, double d                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjCharPredicate}                     | T1 t1,T2 t2, char c                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjCharPredicateX}                    | T1 t1,T2 t2, char c                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjBooleanPredicate}                  | T1 t1,T2 t2, boolean b                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.BiObjBooleanPredicateX}                 | T1 t1,T2 t2, boolean b                   | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.supplier.Supplier}                                |                                          | R          |
 * | {@link eu.lunisolar.magma.func.supplier.SupplierX}                               |                                          | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.supplier.ByteSupplier}                            |                                          | byte       |
 * | {@link eu.lunisolar.magma.func.supplier.ByteSupplierX}                           |                                          | byte       |
 * | {@link eu.lunisolar.magma.func.supplier.ShortSupplier}                           |                                          | short      |
 * | {@link eu.lunisolar.magma.func.supplier.ShortSupplierX}                          |                                          | short      |
 * | {@link eu.lunisolar.magma.func.supplier.IntSupplier}                             |                                          | int        |
 * | {@link eu.lunisolar.magma.func.supplier.IntSupplierX}                            |                                          | int        |
 * | {@link eu.lunisolar.magma.func.supplier.LongSupplier}                            |                                          | long       |
 * | {@link eu.lunisolar.magma.func.supplier.LongSupplierX}                           |                                          | long       |
 * | {@link eu.lunisolar.magma.func.supplier.FloatSupplier}                           |                                          | float      |
 * | {@link eu.lunisolar.magma.func.supplier.FloatSupplierX}                          |                                          | float      |
 * | {@link eu.lunisolar.magma.func.supplier.DoubleSupplier}                          |                                          | double     |
 * | {@link eu.lunisolar.magma.func.supplier.DoubleSupplierX}                         |                                          | double     |
 * | {@link eu.lunisolar.magma.func.supplier.CharSupplier}                            |                                          | char       |
 * | {@link eu.lunisolar.magma.func.supplier.CharSupplierX}                           |                                          | char       |
 * | {@link eu.lunisolar.magma.func.supplier.BooleanSupplier}                         |                                          | boolean    |
 * | {@link eu.lunisolar.magma.func.supplier.BooleanSupplierX}                        |                                          | boolean    |
 * | java.util.function.UnaryOperator                                                                                         | T t                                                                              | T          |
 * | java.util.function.BinaryOperator                                                                                        | T t1,T t2                                                                        | T          |
 * | java.util.function.IntUnaryOperator                                                                                      | int i                                                                            | int        |
 * | java.util.function.LongUnaryOperator                                                                                     | long l                                                                           | long       |
 * | java.util.function.DoubleUnaryOperator                                                                                   | double d                                                                         | double     |
 * | java.util.function.IntBinaryOperator                                                                                     | int i1,int i2                                                                    | int        |
 * | java.util.function.LongBinaryOperator                                                                                    | long l1,long l2                                                                  | long       |
 * | java.util.function.DoubleBinaryOperator                                                                                  | double d1,double d2                                                              | double     |
 * | java.util.function.Function                                                                                              | T t                                                                              | R          |
 * | java.util.function.BiFunction                                                                                            | T1 t1,T2 t2                                                                      | R          |
 * | java.util.function.IntFunction                                                                                           | int i                                                                            | R          |
 * | java.util.function.LongFunction                                                                                          | long l                                                                           | R          |
 * | java.util.function.DoubleFunction                                                                                        | double d                                                                         | R          |
 * | java.util.function.ToIntFunction                                                                                         | T t                                                                              | int        |
 * | java.util.function.ToLongFunction                                                                                        | T t                                                                              | long       |
 * | java.util.function.ToDoubleFunction                                                                                      | T t                                                                              | double     |
 * | java.util.function.ToIntBiFunction                                                                                       | T1 t1,T2 t2                                                                      | int        |
 * | java.util.function.ToLongBiFunction                                                                                      | T1 t1,T2 t2                                                                      | long       |
 * | java.util.function.ToDoubleBiFunction                                                                                    | T1 t1,T2 t2                                                                      | double     |
 * | java.util.function.IntToLongFunction                                                                                     | int i                                                                            | long       |
 * | java.util.function.IntToDoubleFunction                                                                                   | int i                                                                            | double     |
 * | java.util.function.LongToIntFunction                                                                                     | long l                                                                           | int        |
 * | java.util.function.LongToDoubleFunction                                                                                  | long l                                                                           | double     |
 * | java.util.function.DoubleToIntFunction                                                                                   | double d                                                                         | int        |
 * | java.util.function.DoubleToLongFunction                                                                                  | double d                                                                         | long       |
 * | java.util.function.Predicate                                                                                             | T t                                                                              | boolean    |
 * | java.util.function.BiPredicate                                                                                           | T1 t1,T2 t2                                                                      | boolean    |
 * | java.util.function.IntPredicate                                                                                          | int i                                                                            | boolean    |
 * | java.util.function.LongPredicate                                                                                         | long l                                                                           | boolean    |
 * | java.util.function.DoublePredicate                                                                                       | double d                                                                         | boolean    |
 * | java.util.function.Supplier                                                                                              |                                                                                  | R          |
 * | java.util.function.IntSupplier                                                                                           |                                                                                  | int        |
 * | java.util.function.LongSupplier                                                                                          |                                                                                  | long       |
 * | java.util.function.DoubleSupplier                                                                                        |                                                                                  | double     |
 * | java.util.function.BooleanSupplier                                                                                       |                                                                                  | boolean    |
 * | java.util.function.Consumer                                                                                              | T t                                                                              |            |
 * | java.util.function.BiConsumer                                                                                            | T1 t1,T2 t2                                                                      |            |
 * | java.util.function.IntConsumer                                                                                           | int i                                                                            |            |
 * | java.util.function.LongConsumer                                                                                          | long l                                                                           |            |
 * | java.util.function.DoubleConsumer                                                                                        | double d                                                                         |            |
 * | java.util.function.ObjIntConsumer                                                                                        | T t, int i                                                                       |            |
 * | java.util.function.ObjLongConsumer                                                                                       | T t, long l                                                                      |            |
 * | java.util.function.ObjDoubleConsumer                                                                                     | T t, double d                                                                    |            |
 */
package eu.lunisolar.magma.func;