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
 * "lunisolar-magma" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with "lunisolar-magma".  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Functional interfaces / lambdas
 * ===================
 *
 * | Name                                                                              | Domain                                   | Co-domain  |
 * |:----------------------------------------------------------------------------------|:-----------------------------------------|:-----------|
 * | {@link eu.lunisolar.magma.func.action.LAction}                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.action.LActionX}                                  |                                          |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.LConsumer}                               | T t                                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.LConsumerX}                              | T t                                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.LBiConsumer}                             | T1 t1,T2 t2                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.LBiConsumerX}                            | T1 t1,T2 t2                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.LTriConsumer}                            | T1 t1,T2 t2,T3 t3                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.LTriConsumerX}                           | T1 t1,T2 t2,T3 t3                        |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LByteConsumer}                | byte b                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LByteConsumerX}               | byte b                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LShortConsumer}               | short s                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LShortConsumerX}              | short s                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LIntConsumer}                 | int i                                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LIntConsumerX}                | int i                                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LLongConsumer}                | long l                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LLongConsumerX}               | long l                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LFloatConsumer}               | float f                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LFloatConsumerX}              | float f                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LDoubleConsumer}              | double d                                 |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LDoubleConsumerX}             | double d                                 |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LCharConsumer}                | char c                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LCharConsumerX}               | char c                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LBooleanConsumer}             | boolean b                                |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LBooleanConsumerX}            | boolean b                                |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LByteBiConsumer}           | byte b1,byte b2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LByteBiConsumerX}          | byte b1,byte b2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LShortBiConsumer}          | short s1,short s2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LShortBiConsumerX}         | short s1,short s2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LIntBiConsumer}            | int i1,int i2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LIntBiConsumerX}           | int i1,int i2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LLongBiConsumer}           | long l1,long l2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LLongBiConsumerX}          | long l1,long l2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LFloatBiConsumer}          | float f1,float f2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LFloatBiConsumerX}         | float f1,float f2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LDoubleBiConsumer}         | double d1,double d2                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LDoubleBiConsumerX}        | double d1,double d2                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LCharBiConsumer}           | char c1,char c2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LCharBiConsumerX}          | char c1,char c2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBooleanBiConsumer}        | boolean b1,boolean b2                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBooleanBiConsumerX}       | boolean b1,boolean b2                    |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LBooleanTriConsumer}      | boolean b1,boolean b2,boolean b3         |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LBooleanTriConsumerX}     | boolean b1,boolean b2,boolean b3         |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjByteConsumer}         | T t, byte b                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjByteConsumerX}        | T t, byte b                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjShortConsumer}        | T t, short s                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjShortConsumerX}       | T t, short s                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumer}          | T t, int i                               |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumerX}         | T t, int i                               |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjLongConsumer}         | T t, long l                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjLongConsumerX}        | T t, long l                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjFloatConsumer}        | T t, float f                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjFloatConsumerX}       | T t, float f                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjDoubleConsumer}       | T t, double d                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjDoubleConsumerX}      | T t, double d                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjCharConsumer}         | T t, char c                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjCharConsumerX}        | T t, char c                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjBooleanConsumer}      | T t, boolean b                           |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjBooleanConsumerX}     | T t, boolean b                           |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumer}       | T1 t1,T2 t2, byte b                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumerX}      | T1 t1,T2 t2, byte b                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjShortConsumer}      | T1 t1,T2 t2, short s                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjShortConsumerX}     | T1 t1,T2 t2, short s                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjIntConsumer}        | T1 t1,T2 t2, int i                       |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjIntConsumerX}       | T1 t1,T2 t2, int i                       |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjLongConsumer}       | T1 t1,T2 t2, long l                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjLongConsumerX}      | T1 t1,T2 t2, long l                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjFloatConsumer}      | T1 t1,T2 t2, float f                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjFloatConsumerX}     | T1 t1,T2 t2, float f                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjDoubleConsumer}     | T1 t1,T2 t2, double d                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjDoubleConsumerX}    | T1 t1,T2 t2, double d                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjCharConsumer}       | T1 t1,T2 t2, char c                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjCharConsumerX}      | T1 t1,T2 t2, char c                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjBooleanConsumer}    | T1 t1,T2 t2, boolean b                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjBooleanConsumerX}   | T1 t1,T2 t2, boolean b                   |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.operator.unary.LUnaryOperator}                    | T t                                      | T          |
 * | {@link eu.lunisolar.magma.func.operator.unary.LUnaryOperatorX}                   | T t                                      | T          |
 * | {@link eu.lunisolar.magma.func.operator.binary.LBinaryOperator}                  | T t1,T t2                                | T          |
 * | {@link eu.lunisolar.magma.func.operator.binary.LBinaryOperatorX}                 | T t1,T t2                                | T          |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LTernaryOperator}                | T t1,T t2,T t3                           | T          |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LTernaryOperatorX}               | T t1,T t2,T t3                           | T          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.operator.unary.LByteUnaryOperator}                | byte b                                   | byte       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LByteUnaryOperatorX}               | byte b                                   | byte       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LShortUnaryOperator}               | short s                                  | short      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LShortUnaryOperatorX}              | short s                                  | short      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator}                 | int i                                    | int        |
 * | {@link eu.lunisolar.magma.func.operator.unary.LIntUnaryOperatorX}                | int i                                    | int        |
 * | {@link eu.lunisolar.magma.func.operator.unary.LLongUnaryOperator}                | long l                                   | long       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LLongUnaryOperatorX}               | long l                                   | long       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LFloatUnaryOperator}               | float f                                  | float      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LFloatUnaryOperatorX}              | float f                                  | float      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LDoubleUnaryOperator}              | double d                                 | double     |
 * | {@link eu.lunisolar.magma.func.operator.unary.LDoubleUnaryOperatorX}             | double d                                 | double     |
 * | {@link eu.lunisolar.magma.func.operator.unary.LCharUnaryOperator}                | char c                                   | char       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LCharUnaryOperatorX}               | char c                                   | char       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LBooleanUnaryOperator}             | boolean b                                | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.unary.LBooleanUnaryOperatorX}            | boolean b                                | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.operator.binary.LByteBinaryOperator}              | byte b1,byte b2                          | byte       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LByteBinaryOperatorX}             | byte b1,byte b2                          | byte       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LShortBinaryOperator}             | short s1,short s2                        | short      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LShortBinaryOperatorX}            | short s1,short s2                        | short      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LIntBinaryOperator}               | int i1,int i2                            | int        |
 * | {@link eu.lunisolar.magma.func.operator.binary.LIntBinaryOperatorX}              | int i1,int i2                            | int        |
 * | {@link eu.lunisolar.magma.func.operator.binary.LLongBinaryOperator}              | long l1,long l2                          | long       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LLongBinaryOperatorX}             | long l1,long l2                          | long       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LFloatBinaryOperator}             | float f1,float f2                        | float      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LFloatBinaryOperatorX}            | float f1,float f2                        | float      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LDoubleBinaryOperator}            | double d1,double d2                      | double     |
 * | {@link eu.lunisolar.magma.func.operator.binary.LDoubleBinaryOperatorX}           | double d1,double d2                      | double     |
 * | {@link eu.lunisolar.magma.func.operator.binary.LCharBinaryOperator}              | char c1,char c2                          | char       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LCharBinaryOperatorX}             | char c1,char c2                          | char       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LBooleanBinaryOperator}           | boolean b1,boolean b2                    | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.binary.LBooleanBinaryOperatorX}          | boolean b1,boolean b2                    | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LBooleanTernaryOperator}         | boolean b1,boolean b2,boolean b3         | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LBooleanTernaryOperatorX}        | boolean b1,boolean b2,boolean b3         | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.LFunction}                               | T t                                      | R          |
 * | {@link eu.lunisolar.magma.func.function.LFunctionX}                              | T t                                      | R          |
 * | {@link eu.lunisolar.magma.func.function.LBiFunction}                             | T1 t1,T2 t2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.LBiFunctionX}                            | T1 t1,T2 t2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.LTriFunction}                            | T1 t1,T2 t2,T3 t3                        | R          |
 * | {@link eu.lunisolar.magma.func.function.LTriFunctionX}                           | T1 t1,T2 t2,T3 t3                        | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.LByteFunction}                      | byte b                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LByteFunctionX}                     | byte b                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LShortFunction}                     | short s                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LShortFunctionX}                    | short s                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LIntFunction}                       | int i                                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LIntFunctionX}                      | int i                                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LLongFunction}                      | long l                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LLongFunctionX}                     | long l                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LFloatFunction}                     | float f                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LFloatFunctionX}                    | float f                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LDoubleFunction}                    | double d                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LDoubleFunctionX}                   | double d                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LCharFunction}                      | char c                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LCharFunctionX}                     | char c                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBooleanFunction}                   | boolean b                                | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBooleanFunctionX}                  | boolean b                                | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.LByteBiFunction}                    | byte b1,byte b2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LByteBiFunctionX}                   | byte b1,byte b2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LShortBiFunction}                   | short s1,short s2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LShortBiFunctionX}                  | short s1,short s2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LIntBiFunction}                     | int i1,int i2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LIntBiFunctionX}                    | int i1,int i2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LLongBiFunction}                    | long l1,long l2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LLongBiFunctionX}                   | long l1,long l2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LFloatBiFunction}                   | float f1,float f2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LFloatBiFunctionX}                  | float f1,float f2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LDoubleBiFunction}                  | double d1,double d2                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LDoubleBiFunctionX}                 | double d1,double d2                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LCharBiFunction}                    | char c1,char c2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LCharBiFunctionX}                   | char c1,char c2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBooleanBiFunction}                 | boolean b1,boolean b2                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBooleanBiFunctionX}                | boolean b1,boolean b2                    | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.LBooleanTriFunction}                | boolean b1,boolean b2,boolean b3         | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBooleanTriFunctionX}               | boolean b1,boolean b2,boolean b3         | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.LObjByteFunction}                   | T t, byte i                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjByteFunctionX}                  | T t, byte i                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjShortFunction}                  | T t, short s                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjShortFunctionX}                 | T t, short s                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntFunction}                    | T t, int i                               | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntFunctionX}                   | T t, int i                               | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjLongFunction}                   | T t, long l                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjLongFunctionX}                  | T t, long l                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjFloatFunction}                  | T t, float f                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjFloatFunctionX}                 | T t, float f                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjDoubleFunction}                 | T t, double d                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjDoubleFunctionX}                | T t, double d                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjCharFunction}                   | T t, char c                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjCharFunctionX}                  | T t, char c                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjBooleanFunction}                | T t, boolean b                           | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjBooleanFunctionX}               | T t, boolean b                           | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjByteFunction}                 | T1 t1,T2 t2, byte i                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjByteFunctionX}                | T1 t1,T2 t2, byte i                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjShortFunction}                | T1 t1,T2 t2, short s                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjShortFunctionX}               | T1 t1,T2 t2, short s                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjIntFunction}                  | T1 t1,T2 t2, int i                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjIntFunctionX}                 | T1 t1,T2 t2, int i                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjLongFunction}                 | T1 t1,T2 t2, long l                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjLongFunctionX}                | T1 t1,T2 t2, long l                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjFloatFunction}                | T1 t1,T2 t2, float f                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjFloatFunctionX}               | T1 t1,T2 t2, float f                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjDoubleFunction}               | T1 t1,T2 t2, double d                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjDoubleFunctionX}              | T1 t1,T2 t2, double d                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjCharFunction}                 | T1 t1,T2 t2, char c                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjCharFunctionX}                | T1 t1,T2 t2, char c                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjBooleanFunction}              | T1 t1,T2 t2, boolean b                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjBooleanFunctionX}             | T1 t1,T2 t2, boolean b                   | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteFunction}                      | T t                                      | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteFunctionX}                     | T t                                      | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToShortFunction}                     | T t                                      | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToShortFunctionX}                    | T t                                      | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntFunction}                       | T t                                      | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntFunctionX}                      | T t                                      | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongFunction}                      | T t                                      | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongFunctionX}                     | T t                                      | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToFloatFunction}                     | T t                                      | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToFloatFunctionX}                    | T t                                      | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToDoubleFunction}                    | T t                                      | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToDoubleFunctionX}                   | T t                                      | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharFunction}                      | T t                                      | char       |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharFunctionX}                     | T t                                      | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteBiFunction}                    | T1 t1,T2 t2                              | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteBiFunctionX}                   | T1 t1,T2 t2                              | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToShortBiFunction}                   | T1 t1,T2 t2                              | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToShortBiFunctionX}                  | T1 t1,T2 t2                              | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntBiFunction}                     | T1 t1,T2 t2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntBiFunctionX}                    | T1 t1,T2 t2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongBiFunction}                    | T1 t1,T2 t2                              | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongBiFunctionX}                   | T1 t1,T2 t2                              | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToFloatBiFunction}                   | T1 t1,T2 t2                              | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToFloatBiFunctionX}                  | T1 t1,T2 t2                              | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToDoubleBiFunction}                  | T1 t1,T2 t2                              | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToDoubleBiFunctionX}                 | T1 t1,T2 t2                              | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharBiFunction}                    | T1 t1,T2 t2                              | char       |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharBiFunctionX}                   | T1 t1,T2 t2                              | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.to.LObjIntToIntFunction}                 | T t, int i                               | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LObjIntToIntFunctionX}                | T t, int i                               | int        |
 * |                                                                                   |                                          |            |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToShortFunction}         | byte b                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToShortFunctionX}        | byte b                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToIntFunction}           | byte b                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToIntFunctionX}          | byte b                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToLongFunction}          | byte b                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToLongFunctionX}         | byte b                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToFloatFunction}         | byte b                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToFloatFunctionX}        | byte b                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToDoubleFunction}        | byte b                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToDoubleFunctionX}       | byte b                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToCharFunction}          | byte b                                   | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToCharFunctionX}         | byte b                                   | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToByteFunction}         | short s                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToByteFunctionX}        | short s                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToIntFunction}          | short s                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToIntFunctionX}         | short s                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToLongFunction}         | short s                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToLongFunctionX}        | short s                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToFloatFunction}        | short s                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToFloatFunctionX}       | short s                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToDoubleFunction}       | short s                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToDoubleFunctionX}      | short s                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToCharFunction}         | short s                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToCharFunctionX}        | short s                                  | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToByteFunction}           | int i                                    | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToByteFunctionX}          | int i                                    | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToShortFunction}          | int i                                    | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToShortFunctionX}         | int i                                    | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToLongFunction}           | int i                                    | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToLongFunctionX}          | int i                                    | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToFloatFunction}          | int i                                    | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToFloatFunctionX}         | int i                                    | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToDoubleFunction}         | int i                                    | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToDoubleFunctionX}        | int i                                    | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToCharFunction}           | int i                                    | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToCharFunctionX}          | int i                                    | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToByteFunction}          | long l                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToByteFunctionX}         | long l                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToShortFunction}         | long l                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToShortFunctionX}        | long l                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToIntFunction}           | long l                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToIntFunctionX}          | long l                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToFloatFunction}         | long l                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToFloatFunctionX}        | long l                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToDoubleFunction}        | long l                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToDoubleFunctionX}       | long l                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToCharFunction}          | long l                                   | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToCharFunctionX}         | long l                                   | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToByteFunction}         | float f                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToByteFunctionX}        | float f                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToShortFunction}        | float f                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToShortFunctionX}       | float f                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToIntFunction}          | float f                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToIntFunctionX}         | float f                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToLongFunction}         | float f                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToLongFunctionX}        | float f                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToDoubleFunction}       | float f                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToDoubleFunctionX}      | float f                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToCharFunction}         | float f                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToCharFunctionX}        | float f                                  | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToByteFunction}        | double d                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToByteFunctionX}       | double d                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToShortFunction}       | double d                                 | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToShortFunctionX}      | double d                                 | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToIntFunction}         | double d                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToIntFunctionX}        | double d                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToLongFunction}        | double d                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToLongFunctionX}       | double d                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToFloatFunction}       | double d                                 | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToFloatFunctionX}      | double d                                 | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToCharFunction}        | double d                                 | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToCharFunctionX}       | double d                                 | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToByteFunction}          | char c                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToByteFunctionX}         | char c                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToShortFunction}         | char c                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToShortFunctionX}        | char c                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToIntFunction}           | char c                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToIntFunctionX}          | char c                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToLongFunction}          | char c                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToLongFunctionX}         | char c                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToFloatFunction}         | char c                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToFloatFunctionX}        | char c                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToDoubleFunction}        | char c                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToDoubleFunctionX}       | char c                                   | double     |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToByteFunction}       | boolean b                                | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToByteFunctionX}      | boolean b                                | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToShortFunction}      | boolean b                                | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToShortFunctionX}     | boolean b                                | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToIntFunction}        | boolean b                                | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToIntFunctionX}       | boolean b                                | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToLongFunction}       | boolean b                                | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToLongFunctionX}      | boolean b                                | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToFloatFunction}      | boolean b                                | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToFloatFunctionX}     | boolean b                                | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToDoubleFunction}     | boolean b                                | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToDoubleFunctionX}    | boolean b                                | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToCharFunction}       | boolean b                                | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBooleanToCharFunctionX}      | boolean b                                | char       |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.LPredicate}                             | T t                                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LPredicateX}                            | T t                                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiPredicate}                           | T1 t1,T2 t2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiPredicateX}                          | T1 t1,T2 t2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriPredicate}                          | T1 t1,T2 t2,T3 t3                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriPredicateX}                         | T1 t1,T2 t2,T3 t3                        | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.LBytePredicate}                         | byte b                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBytePredicateX}                        | byte b                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LShortPredicate}                        | short s                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LShortPredicateX}                       | short s                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LIntPredicate}                          | int i                                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LIntPredicateX}                         | int i                                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LLongPredicate}                         | long l                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LLongPredicateX}                        | long l                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LFloatPredicate}                        | float f                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LFloatPredicateX}                       | float f                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LDoublePredicate}                       | double d                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LDoublePredicateX}                      | double d                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LCharPredicate}                         | char c                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LCharPredicateX}                        | char c                                   | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.LBiBytePredicate}                       | byte b1,byte b2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiBytePredicateX}                      | byte b1,byte b2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiShortPredicate}                      | short s1,short s2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiShortPredicateX}                     | short s1,short s2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiIntPredicate}                        | int i1,int i2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiIntPredicateX}                       | int i1,int i2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiLongPredicate}                       | long l1,long l2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiLongPredicateX}                      | long l1,long l2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiFloatPredicate}                      | float f1,float f2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiFloatPredicateX}                     | float f1,float f2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiDoublePredicate}                     | double d1,double d2                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiDoublePredicateX}                    | double d1,double d2                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiCharPredicate}                       | char c1,char c2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiCharPredicateX}                      | char c1,char c2                          | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBytePredicate}                      | T t, byte b                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBytePredicateX}                     | T t, byte b                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjShortPredicate}                     | T t, short s                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjShortPredicateX}                    | T t, short s                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntPredicate}                       | T t, int i                               | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntPredicateX}                      | T t, int i                               | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjLongPredicate}                      | T t, long l                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjLongPredicateX}                     | T t, long l                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjFloatPredicate}                     | T t, float f                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjFloatPredicateX}                    | T t, float f                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjDoublePredicate}                    | T t, double d                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjDoublePredicateX}                   | T t, double d                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjCharPredicate}                      | T t, char c                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjCharPredicateX}                     | T t, char c                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBooleanPredicate}                   | T t, boolean b                           | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBooleanPredicateX}                  | T t, boolean b                           | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBytePredicate}                    | T1 t1,T2 t2, byte b                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBytePredicateX}                   | T1 t1,T2 t2, byte b                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjShortPredicate}                   | T1 t1,T2 t2, short s                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjShortPredicateX}                  | T1 t1,T2 t2, short s                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjIntPredicate}                     | T1 t1,T2 t2, int i                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjIntPredicateX}                    | T1 t1,T2 t2, int i                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjLongPredicate}                    | T1 t1,T2 t2, long l                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjLongPredicateX}                   | T1 t1,T2 t2, long l                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjFloatPredicate}                   | T1 t1,T2 t2, float f                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjFloatPredicateX}                  | T1 t1,T2 t2, float f                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjDoublePredicate}                  | T1 t1,T2 t2, double d                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjDoublePredicateX}                 | T1 t1,T2 t2, double d                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjCharPredicate}                    | T1 t1,T2 t2, char c                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjCharPredicateX}                   | T1 t1,T2 t2, char c                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBooleanPredicate}                 | T1 t1,T2 t2, boolean b                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBooleanPredicateX}                | T1 t1,T2 t2, boolean b                   | boolean    |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.supplier.LSupplier}                               |                                          | R          |
 * | {@link eu.lunisolar.magma.func.supplier.LSupplierX}                              |                                          | R          |
 * |                                                                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.supplier.LByteSupplier}                           |                                          | byte       |
 * | {@link eu.lunisolar.magma.func.supplier.LByteSupplierX}                          |                                          | byte       |
 * | {@link eu.lunisolar.magma.func.supplier.LShortSupplier}                          |                                          | short      |
 * | {@link eu.lunisolar.magma.func.supplier.LShortSupplierX}                         |                                          | short      |
 * | {@link eu.lunisolar.magma.func.supplier.LIntSupplier}                            |                                          | int        |
 * | {@link eu.lunisolar.magma.func.supplier.LIntSupplierX}                           |                                          | int        |
 * | {@link eu.lunisolar.magma.func.supplier.LLongSupplier}                           |                                          | long       |
 * | {@link eu.lunisolar.magma.func.supplier.LLongSupplierX}                          |                                          | long       |
 * | {@link eu.lunisolar.magma.func.supplier.LFloatSupplier}                          |                                          | float      |
 * | {@link eu.lunisolar.magma.func.supplier.LFloatSupplierX}                         |                                          | float      |
 * | {@link eu.lunisolar.magma.func.supplier.LDoubleSupplier}                         |                                          | double     |
 * | {@link eu.lunisolar.magma.func.supplier.LDoubleSupplierX}                        |                                          | double     |
 * | {@link eu.lunisolar.magma.func.supplier.LCharSupplier}                           |                                          | char       |
 * | {@link eu.lunisolar.magma.func.supplier.LCharSupplierX}                          |                                          | char       |
 * | {@link eu.lunisolar.magma.func.supplier.LBooleanSupplier}                        |                                          | boolean    |
 * | {@link eu.lunisolar.magma.func.supplier.LBooleanSupplierX}                       |                                          | boolean    |
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