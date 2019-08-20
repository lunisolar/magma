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

/**
 * Functional interfaces / lambdas
 * ===================
 *
 * | Name                                                                              | Domain                                   | Co-domain  |
 * |:----------------------------------------------------------------------------------|:-----------------------------------------|:-----------|
 * | {@link eu.lunisolar.magma.func.action.LAction}                                   |                                          | void       |
 * | {@link eu.lunisolar.magma.func.consumer.LBiConsumer}                             | T1 a1,T2 a2                              | void       |
 * | {@link eu.lunisolar.magma.func.consumer.LConsumer}                               | T a                                      | void       |
 * | {@link eu.lunisolar.magma.func.consumer.LQuadConsumer}                           | T1 a1,T2 a2,T3 a3,T4 a4                  | void       |
 * | {@link eu.lunisolar.magma.func.consumer.LQuintConsumer}                          | T1 a1,T2 a2,T3 a3,T4 a4,T5 a5            | void       |
 * | {@link eu.lunisolar.magma.func.consumer.LTriConsumer}                            | T1 a1,T2 a2,T3 a3                        | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LBoolConsumer}                | boolean a                                | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LByteConsumer}                | byte a                                   | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LCharConsumer}                | char a                                   | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LDblConsumer}                 | double a                                 | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LFltConsumer}                 | float a                                  | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LIntConsumer}                 | int a                                    | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LLongConsumer}                | long a                                   | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LSrtConsumer}                 | short a                                  | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiBoolConsumer}           | boolean a1,boolean a2                    | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiByteConsumer}           | byte a1,byte a2                          | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiCharConsumer}           | char a1,char a2                          | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiDblConsumer}            | double a1,double a2                      | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiFltConsumer}            | float a1,float a2                        | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiIntConsumer}            | int a1,int a2                            | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiLongConsumer}           | long a1,long a2                          | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiSrtConsumer}            | short a1,short a2                        | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBoolIntConsumer}          | boolean a1,int a2                        | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LByteIntConsumer}          | byte a1,int a2                           | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LCharIntConsumer}          | char a1,int a2                           | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LDblIntConsumer}           | double a1,int a2                         | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LFltIntConsumer}           | float a1,int a2                          | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LLongIntConsumer}          | long a1,int a2                           | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LSrtIntConsumer}           | short a1,int a2                          | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjBoolConsumer}       | T1 a1,T2 a2,boolean a3                   | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumer}       | T1 a1,T2 a2,byte a3                      | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjCharConsumer}       | T1 a1,T2 a2,char a3                      | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjDblConsumer}        | T1 a1,T2 a2,double a3                    | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjFltConsumer}        | T1 a1,T2 a2,float a3                     | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjIntConsumer}        | T1 a1,T2 a2,int a3                       | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjLongConsumer}       | T1 a1,T2 a2,long a3                      | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjSrtConsumer}        | T1 a1,T2 a2,short a3                     | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjBoolConsumer}         | T a1,boolean a2                          | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjByteConsumer}         | T a1,byte a2                             | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjCharConsumer}         | T a1,char a2                             | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjDblConsumer}          | T a1,double a2                           | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjFltConsumer}          | T a1,float a2                            | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumer}          | T a1,int a2                              | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjLongConsumer}         | T a1,long a2                             | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjSrtConsumer}          | T a1,short a2                            | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieBoolConsumer}         | T a1,int a2,boolean a3                   | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieByteConsumer}         | T a1,int a2,byte a3                      | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieCharConsumer}         | T a1,int a2,char a3                      | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieConsumer}             | T1 a1,int a2,T2 a3                       | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieDblConsumer}          | T a1,int a2,double a3                    | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieFltConsumer}          | T a1,int a2,float a3                     | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieIntConsumer}          | T a1,int a2,int a3                       | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieLongConsumer}         | T a1,int a2,long a3                      | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LTieSrtConsumer}          | T a1,int a2,short a3                     | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriBoolConsumer}         | boolean a1,boolean a2,boolean a3         | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriByteConsumer}         | byte a1,byte a2,byte a3                  | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriCharConsumer}         | char a1,char a2,char a3                  | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriDblConsumer}          | double a1,double a2,double a3            | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriFltConsumer}          | float a1,float a2,float a3               | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriIntConsumer}          | int a1,int a2,int a3                     | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriLongConsumer}         | long a1,long a2,long a3                  | void       |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriSrtConsumer}          | short a1,short a2,short a3               | void       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LBinaryOperator}                  | T a1,T a2                                | T          |
 * | {@link eu.lunisolar.magma.func.operator.binary.LByteBinaryOperator}              | byte a1,byte a2                          | byte       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LCharBinaryOperator}              | char a1,char a2                          | char       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LDblBinaryOperator}               | double a1,double a2                      | double     |
 * | {@link eu.lunisolar.magma.func.operator.binary.LFltBinaryOperator}               | float a1,float a2                        | float      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LIntBinaryOperator}               | int a1,int a2                            | int        |
 * | {@link eu.lunisolar.magma.func.operator.binary.LLogicalBinaryOperator}           | boolean a1,boolean a2                    | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.binary.LLongBinaryOperator}              | long a1,long a2                          | long       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LSrtBinaryOperator}               | short a1,short a2                        | short      |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LLogicalTernaryOperator}         | boolean a1,boolean a2,boolean a3         | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LTernaryOperator}                | T a1,T a2,T a3                           | T          |
 * | {@link eu.lunisolar.magma.func.operator.unary.LByteUnaryOperator}                | byte a                                   | byte       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LCharUnaryOperator}                | char a                                   | char       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LDblUnaryOperator}                 | double a                                 | double     |
 * | {@link eu.lunisolar.magma.func.operator.unary.LFltUnaryOperator}                 | float a                                  | float      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator}                 | int a                                    | int        |
 * | {@link eu.lunisolar.magma.func.operator.unary.LLogicalOperator}                  | boolean a                                | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.unary.LLongUnaryOperator}                | long a                                   | long       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LSrtUnaryOperator}                 | short a                                  | short      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LUnaryOperator}                    | T a                                      | T          |
 * | {@link eu.lunisolar.magma.func.function.LBiFunction}                             | T1 a1,T2 a2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.LFunction}                               | T a                                      | R          |
 * | {@link eu.lunisolar.magma.func.function.LQuadFunction}                           | T1 a1,T2 a2,T3 a3,T4 a4                  | R          |
 * | {@link eu.lunisolar.magma.func.function.LQuintFunction}                          | T1 a1,T2 a2,T3 a3,T4 a4,T5 a5            | R          |
 * | {@link eu.lunisolar.magma.func.function.LTriFunction}                            | T1 a1,T2 a2,T3 a3                        | R          |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToByteFunction}          | boolean a                                | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToCharFunction}          | boolean a                                | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToDblFunction}           | boolean a                                | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToFltFunction}           | boolean a                                | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToIntFunction}           | boolean a                                | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToLongFunction}          | boolean a                                | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToSrtFunction}           | boolean a                                | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToCharFunction}          | byte a                                   | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToDblFunction}           | byte a                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToFltFunction}           | byte a                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToIntFunction}           | byte a                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToLongFunction}          | byte a                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToSrtFunction}           | byte a                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToByteFunction}          | char a                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToDblFunction}           | char a                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToFltFunction}           | char a                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToIntFunction}           | char a                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToLongFunction}          | char a                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToSrtFunction}           | char a                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDblToByteFunction}           | double a                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDblToCharFunction}           | double a                                 | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDblToFltFunction}            | double a                                 | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDblToIntFunction}            | double a                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDblToLongFunction}           | double a                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDblToSrtFunction}            | double a                                 | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFltToByteFunction}           | float a                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFltToCharFunction}           | float a                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFltToDblFunction}            | float a                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFltToIntFunction}            | float a                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFltToLongFunction}           | float a                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFltToSrtFunction}            | float a                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToByteFunction}           | int a                                    | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToCharFunction}           | int a                                    | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToDblFunction}            | int a                                    | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToFltFunction}            | int a                                    | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToLongFunction}           | int a                                    | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToSrtFunction}            | int a                                    | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToByteFunction}          | long a                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToCharFunction}          | long a                                   | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToDblFunction}           | long a                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToFltFunction}           | long a                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToIntFunction}           | long a                                   | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToSrtFunction}           | long a                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LSrtToByteFunction}           | short a                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LSrtToCharFunction}           | short a                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LSrtToDblFunction}            | short a                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LSrtToFltFunction}            | short a                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LSrtToIntFunction}            | short a                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LSrtToLongFunction}           | short a                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.from.LBiBoolFunction}                    | boolean a1,boolean a2                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiByteFunction}                    | byte a1,byte a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiCharFunction}                    | char a1,char a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiDblFunction}                     | double a1,double a2                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiFltFunction}                     | float a1,float a2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiIntFunction}                     | int a1,int a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiLongFunction}                    | long a1,long a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjBoolFunction}                 | T1 a1,T2 a2,boolean a3                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjByteFunction}                 | T1 a1,T2 a2,byte a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjCharFunction}                 | T1 a1,T2 a2,char a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjDblFunction}                  | T1 a1,T2 a2,double a3                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjFltFunction}                  | T1 a1,T2 a2,float a3                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjIntFunction}                  | T1 a1,T2 a2,int a3                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjLongFunction}                 | T1 a1,T2 a2,long a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjSrtFunction}                  | T1 a1,T2 a2,short a3                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiSrtFunction}                     | short a1,short a2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBoolFunction}                      | boolean a                                | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LByteFunction}                      | byte a                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LCharFunction}                      | char a                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LDblFunction}                       | double a                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LFltFunction}                       | float a                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LIntFunction}                       | int a                                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LLongFunction}                      | long a                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjBiIntFunction}                  | T a1,int a2,int a3                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjBoolFunction}                   | T a1,boolean a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjByteFunction}                   | T a1,byte a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjCharFunction}                   | T a1,char a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjDblFunction}                    | T a1,double a2                           | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjFltFunction}                    | T a1,float a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntBoolFunction}                | T a1,int a2,boolean a3                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntByteFunction}                | T a1,int a2,byte a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntCharFunction}                | T a1,int a2,char a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntDblFunction}                 | T a1,int a2,double a3                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntFltFunction}                 | T a1,int a2,float a3                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntLongFunction}                | T a1,int a2,long a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntObjFunction}                 | T1 a1,int a2,T2 a3                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntSrtFunction}                 | T a1,int a2,short a3                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjLongFunction}                   | T a1,long a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjSrtFunction}                    | T a1,short a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LOiFunction}                        | T a1,int a2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LSrtFunction}                       | short a                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LTriBoolFunction}                   | boolean a1,boolean a2,boolean a3         | R          |
 * | {@link eu.lunisolar.magma.func.function.to.LOiToByteFunction}                    | T a1,int a2                              | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LOiToCharFunction}                    | T a1,int a2                              | char       |
 * | {@link eu.lunisolar.magma.func.function.to.LOiToDblFunction}                     | T a1,int a2                              | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LOiToFltFunction}                     | T a1,int a2                              | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LOiToIntFunction}                     | T a1,int a2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LOiToLongFunction}                    | T a1,int a2                              | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LOiToSrtFunction}                     | T a1,int a2                              | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LTieBoolFunction}                     | T a1,int a2,boolean a3                   | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LTieByteFunction}                     | T a1,int a2,byte a3                      | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LTieCharFunction}                     | T a1,int a2,char a3                      | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LTieDblFunction}                      | T a1,int a2,double a3                    | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LTieFltFunction}                      | T a1,int a2,float a3                     | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LTieFunction}                         | T1 a1,int a2,T2 a3                       | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LTieIntFunction}                      | T a1,int a2,int a3                       | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LTieLongFunction}                     | T a1,int a2,long a3                      | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LTieSrtFunction}                      | T a1,int a2,short a3                     | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteBiFunction}                    | T1 a1,T2 a2                              | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteFunction}                      | T a                                      | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharBiFunction}                    | T1 a1,T2 a2                              | char       |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharFunction}                      | T a                                      | char       |
 * | {@link eu.lunisolar.magma.func.function.to.LToDblBiFunction}                     | T1 a1,T2 a2                              | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToDblFunction}                       | T a                                      | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToFltBiFunction}                     | T1 a1,T2 a2                              | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToFltFunction}                       | T a                                      | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntBiFunction}                     | T1 a1,T2 a2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntFunction}                       | T a                                      | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntTriFunction}                    | T1 a1,T2 a2,T3 a3                        | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongBiFunction}                    | T1 a1,T2 a2                              | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongFunction}                      | T a                                      | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToSrtBiFunction}                     | T1 a1,T2 a2                              | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToSrtFunction}                       | T a                                      | short      |
 * | {@link eu.lunisolar.magma.func.predicate.LBiBytePredicate}                       | byte a1,byte a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiCharPredicate}                       | char a1,char a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiDblPredicate}                        | double a1,double a2                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiFltPredicate}                        | float a1,float a2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiIntPredicate}                        | int a1,int a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiLongPredicate}                       | long a1,long a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBoolPredicate}                    | T1 a1,T2 a2,boolean a3                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBytePredicate}                    | T1 a1,T2 a2,byte a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjCharPredicate}                    | T1 a1,T2 a2,char a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjDblPredicate}                     | T1 a1,T2 a2,double a3                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjFltPredicate}                     | T1 a1,T2 a2,float a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjIntPredicate}                     | T1 a1,T2 a2,int a3                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjLongPredicate}                    | T1 a1,T2 a2,long a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjSrtPredicate}                     | T1 a1,T2 a2,short a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiPredicate}                           | T1 a1,T2 a2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiSrtPredicate}                        | short a1,short a2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBoolIntPredicate}                      | boolean a1,int a2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LByteIntPredicate}                      | byte a1,int a2                           | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBytePredicate}                         | byte a                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LCharIntPredicate}                      | char a1,int a2                           | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LCharPredicate}                         | char a                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LDblIntPredicate}                       | double a1,int a2                         | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LDblPredicate}                          | double a                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LFltIntPredicate}                       | float a1,int a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LFltPredicate}                          | float a                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LIntPredicate}                          | int a                                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LLongIntPredicate}                      | long a1,int a2                           | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LLongPredicate}                         | long a                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBiIntPredicate}                     | T a1,int a2,int a3                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBoolPredicate}                      | T a1,boolean a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBytePredicate}                      | T a1,byte a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjCharPredicate}                      | T a1,char a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjDblPredicate}                       | T a1,double a2                           | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjFltPredicate}                       | T a1,float a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntBoolPredicate}                   | T a1,int a2,boolean a3                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntBytePredicate}                   | T a1,int a2,byte a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntCharPredicate}                   | T a1,int a2,char a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntDblPredicate}                    | T a1,int a2,double a3                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntFltPredicate}                    | T a1,int a2,float a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntLongPredicate}                   | T a1,int a2,long a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntObjPredicate}                    | T1 a1,int a2,T2 a3                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntPredicate}                       | T a1,int a2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntSrtPredicate}                    | T a1,int a2,short a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjLongPredicate}                      | T a1,long a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjSrtPredicate}                       | T a1,short a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LPredicate}                             | T a                                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LQuadPredicate}                         | T1 a1,T2 a2,T3 a3,T4 a4                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LQuintPredicate}                        | T1 a1,T2 a2,T3 a3,T4 a4,T5 a5            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LSrtIntPredicate}                       | short a1,int a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LSrtPredicate}                          | short a                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriBytePredicate}                      | byte a1,byte a2,byte a3                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriCharPredicate}                      | char a1,char a2,char a3                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriDblPredicate}                       | double a1,double a2,double a3            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriFltPredicate}                       | float a1,float a2,float a3               | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriIntPredicate}                       | int a1,int a2,int a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriLongPredicate}                      | long a1,long a2,long a3                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriPredicate}                          | T1 a1,T2 a2,T3 a3                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriSrtPredicate}                       | short a1,short a2,short a3               | boolean    |
 * | {@link eu.lunisolar.magma.func.supplier.LBoolSupplier}                           |                                          | boolean    |
 * | {@link eu.lunisolar.magma.func.supplier.LByteSupplier}                           |                                          | byte       |
 * | {@link eu.lunisolar.magma.func.supplier.LCharSupplier}                           |                                          | char       |
 * | {@link eu.lunisolar.magma.func.supplier.LDblSupplier}                            |                                          | double     |
 * | {@link eu.lunisolar.magma.func.supplier.LFltSupplier}                            |                                          | float      |
 * | {@link eu.lunisolar.magma.func.supplier.LIntSupplier}                            |                                          | int        |
 * | {@link eu.lunisolar.magma.func.supplier.LLongSupplier}                           |                                          | long       |
 * | {@link eu.lunisolar.magma.func.supplier.LSrtSupplier}                            |                                          | short      |
 * | {@link eu.lunisolar.magma.func.supplier.LSupplier}                               |                                          | T          |
 * | Runnable                                                                                                                 |                                                                                  | void       |
 * | BiConsumer                                                                                                               | T1 a1,T2 a2                                                                      | void       |
 * | Consumer                                                                                                                 | T a                                                                              | void       |
 * | DoubleConsumer                                                                                                           | double a                                                                         | void       |
 * | IntConsumer                                                                                                              | int a                                                                            | void       |
 * | LongConsumer                                                                                                             | long a                                                                           | void       |
 * | ObjDoubleConsumer                                                                                                        | T a1,double a2                                                                   | void       |
 * | ObjIntConsumer                                                                                                           | T a1,int a2                                                                      | void       |
 * | ObjLongConsumer                                                                                                          | T a1,long a2                                                                     | void       |
 * | BinaryOperator                                                                                                           | T a1,T a2                                                                        | T          |
 * | DoubleBinaryOperator                                                                                                     | double a1,double a2                                                              | double     |
 * | DoubleUnaryOperator                                                                                                      | double a                                                                         | double     |
 * | IntBinaryOperator                                                                                                        | int a1,int a2                                                                    | int        |
 * | IntUnaryOperator                                                                                                         | int a                                                                            | int        |
 * | LongBinaryOperator                                                                                                       | long a1,long a2                                                                  | long       |
 * | LongUnaryOperator                                                                                                        | long a                                                                           | long       |
 * | UnaryOperator                                                                                                            | T a                                                                              | T          |
 * | BiFunction                                                                                                               | T1 a1,T2 a2                                                                      | R          |
 * | DoubleFunction                                                                                                           | double a                                                                         | R          |
 * | DoubleToIntFunction                                                                                                      | double a                                                                         | int        |
 * | DoubleToLongFunction                                                                                                     | double a                                                                         | long       |
 * | Function                                                                                                                 | T a                                                                              | R          |
 * | IntFunction                                                                                                              | int a                                                                            | R          |
 * | IntToDoubleFunction                                                                                                      | int a                                                                            | double     |
 * | IntToLongFunction                                                                                                        | int a                                                                            | long       |
 * | LongFunction                                                                                                             | long a                                                                           | R          |
 * | LongToDoubleFunction                                                                                                     | long a                                                                           | double     |
 * | LongToIntFunction                                                                                                        | long a                                                                           | int        |
 * | ToDoubleBiFunction                                                                                                       | T1 a1,T2 a2                                                                      | double     |
 * | ToDoubleFunction                                                                                                         | T a                                                                              | double     |
 * | ToIntBiFunction                                                                                                          | T1 a1,T2 a2                                                                      | int        |
 * | ToIntFunction                                                                                                            | T a                                                                              | int        |
 * | ToLongBiFunction                                                                                                         | T1 a1,T2 a2                                                                      | long       |
 * | ToLongFunction                                                                                                           | T a                                                                              | long       |
 * | BiPredicate                                                                                                              | T1 a1,T2 a2                                                                      | boolean    |
 * | DoublePredicate                                                                                                          | double a                                                                         | boolean    |
 * | IntPredicate                                                                                                             | int a                                                                            | boolean    |
 * | LongPredicate                                                                                                            | long a                                                                           | boolean    |
 * | Predicate                                                                                                                | T a                                                                              | boolean    |
 * | BooleanSupplier                                                                                                          |                                                                                  | boolean    |
 * | DoubleSupplier                                                                                                           |                                                                                  | double     |
 * | IntSupplier                                                                                                              |                                                                                  | int        |
 * | LongSupplier                                                                                                             |                                                                                  | long       |
 * | Supplier                                                                                                                 |                                                                                  | T          |
 */
package eu.lunisolar.magma.func;