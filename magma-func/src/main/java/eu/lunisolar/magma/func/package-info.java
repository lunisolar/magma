/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
 * | {@link eu.lunisolar.magma.func.action.LActionX}                                  |                                          |            |
 * | {@link eu.lunisolar.magma.func.action.LAction}                                   |                                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjByteConsumerX}        | T a1,byte a2                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjByteConsumer}         | T a1,byte a2                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjShortConsumerX}       | T a1,short a2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjShortConsumer}        | T a1,short a2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumerX}         | T a1,int a2                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjIntConsumer}          | T a1,int a2                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjLongConsumerX}        | T a1,long a2                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjLongConsumer}         | T a1,long a2                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjFloatConsumerX}       | T a1,float a2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjFloatConsumer}        | T a1,float a2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjDoubleConsumerX}      | T a1,double a2                           |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjDoubleConsumer}       | T a1,double a2                           |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjCharConsumerX}        | T a1,char a2                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjCharConsumer}         | T a1,char a2                             |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjBoolConsumerX}        | T a1,boolean a2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LObjBoolConsumer}         | T a1,boolean a2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumerX}      | T1 a1,T2 a2,byte a3                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjByteConsumer}       | T1 a1,T2 a2,byte a3                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjShortConsumerX}     | T1 a1,T2 a2,short a3                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjShortConsumer}      | T1 a1,T2 a2,short a3                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjIntConsumerX}       | T1 a1,T2 a2,int a3                       |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjIntConsumer}        | T1 a1,T2 a2,int a3                       |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjLongConsumerX}      | T1 a1,T2 a2,long a3                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjLongConsumer}       | T1 a1,T2 a2,long a3                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjFloatConsumerX}     | T1 a1,T2 a2,float a3                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjFloatConsumer}      | T1 a1,T2 a2,float a3                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjDoubleConsumerX}    | T1 a1,T2 a2,double a3                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjDoubleConsumer}     | T1 a1,T2 a2,double a3                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjCharConsumerX}      | T1 a1,T2 a2,char a3                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjCharConsumer}       | T1 a1,T2 a2,char a3                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjBoolConsumerX}      | T1 a1,T2 a2,boolean a3                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.obj.LBiObjBoolConsumer}       | T1 a1,T2 a2,boolean a3                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiByteConsumerX}          | byte a1,byte a2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiByteConsumer}           | byte a1,byte a2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiShortConsumerX}         | short a1,short a2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiShortConsumer}          | short a1,short a2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiIntConsumerX}           | int a1,int a2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiIntConsumer}            | int a1,int a2                            |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiLongConsumerX}          | long a1,long a2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiLongConsumer}           | long a1,long a2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiFloatConsumerX}         | float a1,float a2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiFloatConsumer}          | float a1,float a2                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiDoubleConsumerX}        | double a1,double a2                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiDoubleConsumer}         | double a1,double a2                      |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiCharConsumerX}          | char a1,char a2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiCharConsumer}           | char a1,char a2                          |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiBoolConsumerX}          | boolean a1,boolean a2                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.bi.LBiBoolConsumer}           | boolean a1,boolean a2                    |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriBoolConsumerX}        | boolean a1,boolean a2,boolean a3         |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.tri.LTriBoolConsumer}         | boolean a1,boolean a2,boolean a3         |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LByteConsumerX}               | byte a1                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LByteConsumer}                | byte a1                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LShortConsumerX}              | short a1                                 |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LShortConsumer}               | short a1                                 |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LIntConsumerX}                | int a1                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LIntConsumer}                 | int a1                                   |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LLongConsumerX}               | long a1                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LLongConsumer}                | long a1                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LFloatConsumerX}              | float a1                                 |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LFloatConsumer}               | float a1                                 |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LDoubleConsumerX}             | double a1                                |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LDoubleConsumer}              | double a1                                |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LCharConsumerX}               | char a1                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LCharConsumer}                | char a1                                  |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LBoolConsumerX}               | boolean a1                               |            |
 * | {@link eu.lunisolar.magma.func.consumer.primitives.LBoolConsumer}                | boolean a1                               |            |
 * | {@link eu.lunisolar.magma.func.consumer.LConsumerX}                              | T a1                                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.LConsumer}                               | T a1                                     |            |
 * | {@link eu.lunisolar.magma.func.consumer.LBiConsumerX}                            | T1 a1,T2 a2                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.LBiConsumer}                             | T1 a1,T2 a2                              |            |
 * | {@link eu.lunisolar.magma.func.consumer.LTriConsumerX}                           | T1 a1,T2 a2,T3 a3                        |            |
 * | {@link eu.lunisolar.magma.func.consumer.LTriConsumer}                            | T1 a1,T2 a2,T3 a3                        |            |
 * | {@link eu.lunisolar.magma.func.operator.unary.LByteUnaryOperatorX}               | byte a1                                  | byte       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LByteUnaryOperator}                | byte a1                                  | byte       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LByteBinaryOperatorX}             | byte a1,byte a2                          | byte       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LByteBinaryOperator}              | byte a1,byte a2                          | byte       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LShortUnaryOperatorX}              | short a1                                 | short      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LShortUnaryOperator}               | short a1                                 | short      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LShortBinaryOperatorX}            | short a1,short a2                        | short      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LShortBinaryOperator}             | short a1,short a2                        | short      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LIntUnaryOperatorX}                | int a1                                   | int        |
 * | {@link eu.lunisolar.magma.func.operator.unary.LIntUnaryOperator}                 | int a1                                   | int        |
 * | {@link eu.lunisolar.magma.func.operator.binary.LIntBinaryOperatorX}              | int a1,int a2                            | int        |
 * | {@link eu.lunisolar.magma.func.operator.binary.LIntBinaryOperator}               | int a1,int a2                            | int        |
 * | {@link eu.lunisolar.magma.func.operator.unary.LLongUnaryOperatorX}               | long a1                                  | long       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LLongUnaryOperator}                | long a1                                  | long       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LLongBinaryOperatorX}             | long a1,long a2                          | long       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LLongBinaryOperator}              | long a1,long a2                          | long       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LFloatUnaryOperatorX}              | float a1                                 | float      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LFloatUnaryOperator}               | float a1                                 | float      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LFloatBinaryOperatorX}            | float a1,float a2                        | float      |
 * | {@link eu.lunisolar.magma.func.operator.binary.LFloatBinaryOperator}             | float a1,float a2                        | float      |
 * | {@link eu.lunisolar.magma.func.operator.unary.LDoubleUnaryOperatorX}             | double a1                                | double     |
 * | {@link eu.lunisolar.magma.func.operator.unary.LDoubleUnaryOperator}              | double a1                                | double     |
 * | {@link eu.lunisolar.magma.func.operator.binary.LDoubleBinaryOperatorX}           | double a1,double a2                      | double     |
 * | {@link eu.lunisolar.magma.func.operator.binary.LDoubleBinaryOperator}            | double a1,double a2                      | double     |
 * | {@link eu.lunisolar.magma.func.operator.unary.LCharUnaryOperatorX}               | char a1                                  | char       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LCharUnaryOperator}                | char a1                                  | char       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LCharBinaryOperatorX}             | char a1,char a2                          | char       |
 * | {@link eu.lunisolar.magma.func.operator.binary.LCharBinaryOperator}              | char a1,char a2                          | char       |
 * | {@link eu.lunisolar.magma.func.operator.unary.LLogicalOperatorX}                 | boolean a1                               | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.unary.LLogicalOperator}                  | boolean a1                               | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.binary.LLogicalBinaryOperatorX}          | boolean a1,boolean a2                    | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.binary.LLogicalBinaryOperator}           | boolean a1,boolean a2                    | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LLogicalTernaryOperatorX}        | boolean a1,boolean a2,boolean a3         | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LLogicalTernaryOperator}         | boolean a1,boolean a2,boolean a3         | boolean    |
 * | {@link eu.lunisolar.magma.func.operator.unary.LUnaryOperatorX}                   | T a1                                     | T          |
 * | {@link eu.lunisolar.magma.func.operator.unary.LUnaryOperator}                    | T a1                                     | T          |
 * | {@link eu.lunisolar.magma.func.operator.binary.LBinaryOperatorX}                 | T a1,T a2                                | T          |
 * | {@link eu.lunisolar.magma.func.operator.binary.LBinaryOperator}                  | T a1,T a2                                | T          |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LTernaryOperatorX}               | T a1,T a2,T a3                           | T          |
 * | {@link eu.lunisolar.magma.func.operator.ternary.LTernaryOperator}                | T a1,T a2,T a3                           | T          |
 * | {@link eu.lunisolar.magma.func.function.LFunctionX}                              | T a1                                     | R          |
 * | {@link eu.lunisolar.magma.func.function.LFunction}                               | T a1                                     | R          |
 * | {@link eu.lunisolar.magma.func.function.LBiFunctionX}                            | T1 a1,T2 a2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.LBiFunction}                             | T1 a1,T2 a2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.LTriFunctionX}                           | T1 a1,T2 a2,T3 a3                        | R          |
 * | {@link eu.lunisolar.magma.func.function.LTriFunction}                            | T1 a1,T2 a2,T3 a3                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LByteFunctionX}                     | byte a1                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LByteFunction}                      | byte a1                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiByteFunctionX}                   | byte a1,byte a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiByteFunction}                    | byte a1,byte a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjByteFunctionX}                  | T a1,byte a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjByteFunction}                   | T a1,byte a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjByteFunctionX}                | T1 a1,T2 a2,byte a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjByteFunction}                 | T1 a1,T2 a2,byte a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LShortFunctionX}                    | short a1                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LShortFunction}                     | short a1                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiShortFunctionX}                  | short a1,short a2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiShortFunction}                   | short a1,short a2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjShortFunctionX}                 | T a1,short a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjShortFunction}                  | T a1,short a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjShortFunctionX}               | T1 a1,T2 a2,short a3                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjShortFunction}                | T1 a1,T2 a2,short a3                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LIntFunctionX}                      | int a1                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LIntFunction}                       | int a1                                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiIntFunctionX}                    | int a1,int a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiIntFunction}                     | int a1,int a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntFunctionX}                   | T a1,int a2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjIntFunction}                    | T a1,int a2                              | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjIntFunctionX}                 | T1 a1,T2 a2,int a3                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjIntFunction}                  | T1 a1,T2 a2,int a3                       | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LLongFunctionX}                     | long a1                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LLongFunction}                      | long a1                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiLongFunctionX}                   | long a1,long a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiLongFunction}                    | long a1,long a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjLongFunctionX}                  | T a1,long a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjLongFunction}                   | T a1,long a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjLongFunctionX}                | T1 a1,T2 a2,long a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjLongFunction}                 | T1 a1,T2 a2,long a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LFloatFunctionX}                    | float a1                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LFloatFunction}                     | float a1                                 | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiFloatFunctionX}                  | float a1,float a2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiFloatFunction}                   | float a1,float a2                        | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjFloatFunctionX}                 | T a1,float a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjFloatFunction}                  | T a1,float a2                            | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjFloatFunctionX}               | T1 a1,T2 a2,float a3                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjFloatFunction}                | T1 a1,T2 a2,float a3                     | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LDoubleFunctionX}                   | double a1                                | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LDoubleFunction}                    | double a1                                | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiDoubleFunctionX}                 | double a1,double a2                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiDoubleFunction}                  | double a1,double a2                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjDoubleFunctionX}                | T a1,double a2                           | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjDoubleFunction}                 | T a1,double a2                           | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjDoubleFunctionX}              | T1 a1,T2 a2,double a3                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjDoubleFunction}               | T1 a1,T2 a2,double a3                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LCharFunctionX}                     | char a1                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LCharFunction}                      | char a1                                  | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiCharFunctionX}                   | char a1,char a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiCharFunction}                    | char a1,char a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjCharFunctionX}                  | T a1,char a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjCharFunction}                   | T a1,char a2                             | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjCharFunctionX}                | T1 a1,T2 a2,char a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjCharFunction}                 | T1 a1,T2 a2,char a3                      | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBoolFunctionX}                     | boolean a1                               | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBoolFunction}                      | boolean a1                               | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiBoolFunctionX}                   | boolean a1,boolean a2                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiBoolFunction}                    | boolean a1,boolean a2                    | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LTriBoolFunctionX}                  | boolean a1,boolean a2,boolean a3         | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LTriBoolFunction}                   | boolean a1,boolean a2,boolean a3         | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjBoolFunctionX}                  | T a1,boolean a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LObjBoolFunction}                   | T a1,boolean a2                          | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjBoolFunctionX}                | T1 a1,T2 a2,boolean a3                   | R          |
 * | {@link eu.lunisolar.magma.func.function.from.LBiObjBoolFunction}                 | T1 a1,T2 a2,boolean a3                   | R          |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteFunctionX}                     | T a1                                     | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteFunction}                      | T a1                                     | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteBiFunctionX}                   | T1 a1,T2 a2                              | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToByteBiFunction}                    | T1 a1,T2 a2                              | byte       |
 * | {@link eu.lunisolar.magma.func.function.to.LToShortFunctionX}                    | T a1                                     | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToShortFunction}                     | T a1                                     | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToShortBiFunctionX}                  | T1 a1,T2 a2                              | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToShortBiFunction}                   | T1 a1,T2 a2                              | short      |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntFunctionX}                      | T a1                                     | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntFunction}                       | T a1                                     | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntBiFunctionX}                    | T1 a1,T2 a2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToIntBiFunction}                     | T1 a1,T2 a2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LObjIntToIntFunctionX}                | T a1,int a2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LObjIntToIntFunction}                 | T a1,int a2                              | int        |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongFunctionX}                     | T a1                                     | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongFunction}                      | T a1                                     | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongBiFunctionX}                   | T1 a1,T2 a2                              | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToLongBiFunction}                    | T1 a1,T2 a2                              | long       |
 * | {@link eu.lunisolar.magma.func.function.to.LToFloatFunctionX}                    | T a1                                     | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToFloatFunction}                     | T a1                                     | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToFloatBiFunctionX}                  | T1 a1,T2 a2                              | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToFloatBiFunction}                   | T1 a1,T2 a2                              | float      |
 * | {@link eu.lunisolar.magma.func.function.to.LToDoubleFunctionX}                   | T a1                                     | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToDoubleFunction}                    | T a1                                     | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToDoubleBiFunctionX}                 | T1 a1,T2 a2                              | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToDoubleBiFunction}                  | T1 a1,T2 a2                              | double     |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharFunctionX}                     | T a1                                     | char       |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharFunction}                      | T a1                                     | char       |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharBiFunctionX}                   | T1 a1,T2 a2                              | char       |
 * | {@link eu.lunisolar.magma.func.function.to.LToCharBiFunction}                    | T1 a1,T2 a2                              | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToByteFunctionX}        | short a1                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToByteFunction}         | short a1                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToByteFunctionX}          | int a1                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToByteFunction}           | int a1                                   | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToByteFunctionX}         | long a1                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToByteFunction}          | long a1                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToByteFunctionX}        | float a1                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToByteFunction}         | float a1                                 | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToByteFunctionX}       | double a1                                | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToByteFunction}        | double a1                                | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToByteFunctionX}         | char a1                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToByteFunction}          | char a1                                  | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToByteFunctionX}         | boolean a1                               | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToByteFunction}          | boolean a1                               | byte       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToShortFunctionX}        | byte a1                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToShortFunction}         | byte a1                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToShortFunctionX}         | int a1                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToShortFunction}          | int a1                                   | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToShortFunctionX}        | long a1                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToShortFunction}         | long a1                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToShortFunctionX}       | float a1                                 | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToShortFunction}        | float a1                                 | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToShortFunctionX}      | double a1                                | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToShortFunction}       | double a1                                | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToShortFunctionX}        | char a1                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToShortFunction}         | char a1                                  | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToShortFunctionX}        | boolean a1                               | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToShortFunction}         | boolean a1                               | short      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToIntFunctionX}          | byte a1                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToIntFunction}           | byte a1                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToIntFunctionX}         | short a1                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToIntFunction}          | short a1                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToIntFunctionX}          | long a1                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToIntFunction}           | long a1                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToIntFunctionX}         | float a1                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToIntFunction}          | float a1                                 | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToIntFunctionX}        | double a1                                | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToIntFunction}         | double a1                                | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToIntFunctionX}          | char a1                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToIntFunction}           | char a1                                  | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToIntFunctionX}          | boolean a1                               | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToIntFunction}           | boolean a1                               | int        |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToLongFunctionX}         | byte a1                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToLongFunction}          | byte a1                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToLongFunctionX}        | short a1                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToLongFunction}         | short a1                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToLongFunctionX}          | int a1                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToLongFunction}           | int a1                                   | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToLongFunctionX}        | float a1                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToLongFunction}         | float a1                                 | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToLongFunctionX}       | double a1                                | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToLongFunction}        | double a1                                | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToLongFunctionX}         | char a1                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToLongFunction}          | char a1                                  | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToLongFunctionX}         | boolean a1                               | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToLongFunction}          | boolean a1                               | long       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToFloatFunctionX}        | byte a1                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToFloatFunction}         | byte a1                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToFloatFunctionX}       | short a1                                 | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToFloatFunction}        | short a1                                 | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToFloatFunctionX}         | int a1                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToFloatFunction}          | int a1                                   | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToFloatFunctionX}        | long a1                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToFloatFunction}         | long a1                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToFloatFunctionX}      | double a1                                | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToFloatFunction}       | double a1                                | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToFloatFunctionX}        | char a1                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToFloatFunction}         | char a1                                  | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToFloatFunctionX}        | boolean a1                               | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToFloatFunction}         | boolean a1                               | float      |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToDoubleFunctionX}       | byte a1                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToDoubleFunction}        | byte a1                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToDoubleFunctionX}      | short a1                                 | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToDoubleFunction}       | short a1                                 | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToDoubleFunctionX}        | int a1                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToDoubleFunction}         | int a1                                   | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToDoubleFunctionX}       | long a1                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToDoubleFunction}        | long a1                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToDoubleFunctionX}      | float a1                                 | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToDoubleFunction}       | float a1                                 | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToDoubleFunctionX}       | char a1                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LCharToDoubleFunction}        | char a1                                  | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToDoubleFunctionX}       | boolean a1                               | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToDoubleFunction}        | boolean a1                               | double     |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToCharFunctionX}         | byte a1                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LByteToCharFunction}          | byte a1                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToCharFunctionX}        | short a1                                 | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LShortToCharFunction}         | short a1                                 | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToCharFunctionX}          | int a1                                   | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LIntToCharFunction}           | int a1                                   | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToCharFunctionX}         | long a1                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LLongToCharFunction}          | long a1                                  | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToCharFunctionX}        | float a1                                 | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LFloatToCharFunction}         | float a1                                 | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToCharFunctionX}       | double a1                                | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LDoubleToCharFunction}        | double a1                                | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToCharFunctionX}         | boolean a1                               | char       |
 * | {@link eu.lunisolar.magma.func.function.conversion.LBoolToCharFunction}          | boolean a1                               | char       |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBytePredicateX}                     | T a1,byte a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBytePredicate}                      | T a1,byte a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjShortPredicateX}                    | T a1,short a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjShortPredicate}                     | T a1,short a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntPredicateX}                      | T a1,int a2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjIntPredicate}                       | T a1,int a2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjLongPredicateX}                     | T a1,long a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjLongPredicate}                      | T a1,long a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjFloatPredicateX}                    | T a1,float a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjFloatPredicate}                     | T a1,float a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjDoublePredicateX}                   | T a1,double a2                           | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjDoublePredicate}                    | T a1,double a2                           | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjCharPredicateX}                     | T a1,char a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjCharPredicate}                      | T a1,char a2                             | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBoolPredicateX}                     | T a1,boolean a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LObjBoolPredicate}                      | T a1,boolean a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBytePredicateX}                   | T1 a1,T2 a2,byte a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBytePredicate}                    | T1 a1,T2 a2,byte a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjShortPredicateX}                  | T1 a1,T2 a2,short a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjShortPredicate}                   | T1 a1,T2 a2,short a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjIntPredicateX}                    | T1 a1,T2 a2,int a3                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjIntPredicate}                     | T1 a1,T2 a2,int a3                       | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjLongPredicateX}                   | T1 a1,T2 a2,long a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjLongPredicate}                    | T1 a1,T2 a2,long a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjFloatPredicateX}                  | T1 a1,T2 a2,float a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjFloatPredicate}                   | T1 a1,T2 a2,float a3                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjDoublePredicateX}                 | T1 a1,T2 a2,double a3                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjDoublePredicate}                  | T1 a1,T2 a2,double a3                    | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjCharPredicateX}                   | T1 a1,T2 a2,char a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjCharPredicate}                    | T1 a1,T2 a2,char a3                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBoolPredicateX}                   | T1 a1,T2 a2,boolean a3                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiObjBoolPredicate}                    | T1 a1,T2 a2,boolean a3                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiBytePredicateX}                      | byte a1,byte a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiBytePredicate}                       | byte a1,byte a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiShortPredicateX}                     | short a1,short a2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiShortPredicate}                      | short a1,short a2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiIntPredicateX}                       | int a1,int a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiIntPredicate}                        | int a1,int a2                            | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiLongPredicateX}                      | long a1,long a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiLongPredicate}                       | long a1,long a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiFloatPredicateX}                     | float a1,float a2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiFloatPredicate}                      | float a1,float a2                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiDoublePredicateX}                    | double a1,double a2                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiDoublePredicate}                     | double a1,double a2                      | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiCharPredicateX}                      | char a1,char a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiCharPredicate}                       | char a1,char a2                          | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBytePredicateX}                        | byte a1                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBytePredicate}                         | byte a1                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LShortPredicateX}                       | short a1                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LShortPredicate}                        | short a1                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LIntPredicateX}                         | int a1                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LIntPredicate}                          | int a1                                   | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LLongPredicateX}                        | long a1                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LLongPredicate}                         | long a1                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LFloatPredicateX}                       | float a1                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LFloatPredicate}                        | float a1                                 | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LDoublePredicateX}                      | double a1                                | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LDoublePredicate}                       | double a1                                | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LCharPredicateX}                        | char a1                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LCharPredicate}                         | char a1                                  | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LPredicateX}                            | T a1                                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LPredicate}                             | T a1                                     | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiPredicateX}                          | T1 a1,T2 a2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LBiPredicate}                           | T1 a1,T2 a2                              | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriPredicateX}                         | T1 a1,T2 a2,T3 a3                        | boolean    |
 * | {@link eu.lunisolar.magma.func.predicate.LTriPredicate}                          | T1 a1,T2 a2,T3 a3                        | boolean    |
 * | {@link eu.lunisolar.magma.func.supplier.LByteSupplierX}                          |                                          | byte       |
 * | {@link eu.lunisolar.magma.func.supplier.LByteSupplier}                           |                                          | byte       |
 * | {@link eu.lunisolar.magma.func.supplier.LShortSupplierX}                         |                                          | short      |
 * | {@link eu.lunisolar.magma.func.supplier.LShortSupplier}                          |                                          | short      |
 * | {@link eu.lunisolar.magma.func.supplier.LIntSupplierX}                           |                                          | int        |
 * | {@link eu.lunisolar.magma.func.supplier.LIntSupplier}                            |                                          | int        |
 * | {@link eu.lunisolar.magma.func.supplier.LLongSupplierX}                          |                                          | long       |
 * | {@link eu.lunisolar.magma.func.supplier.LLongSupplier}                           |                                          | long       |
 * | {@link eu.lunisolar.magma.func.supplier.LFloatSupplierX}                         |                                          | float      |
 * | {@link eu.lunisolar.magma.func.supplier.LFloatSupplier}                          |                                          | float      |
 * | {@link eu.lunisolar.magma.func.supplier.LDoubleSupplierX}                        |                                          | double     |
 * | {@link eu.lunisolar.magma.func.supplier.LDoubleSupplier}                         |                                          | double     |
 * | {@link eu.lunisolar.magma.func.supplier.LCharSupplierX}                          |                                          | char       |
 * | {@link eu.lunisolar.magma.func.supplier.LCharSupplier}                           |                                          | char       |
 * | {@link eu.lunisolar.magma.func.supplier.LBoolSupplierX}                          |                                          | boolean    |
 * | {@link eu.lunisolar.magma.func.supplier.LBoolSupplier}                           |                                          | boolean    |
 * | {@link eu.lunisolar.magma.func.supplier.LSupplierX}                              |                                          | R          |
 * | {@link eu.lunisolar.magma.func.supplier.LSupplier}                               |                                          | R          |
 * | java.util.function.UnaryOperator                                                                                         | T a1                                                                             | T          |
 * | java.util.function.BinaryOperator                                                                                        | T a1,T a2                                                                        | T          |
 * | java.util.function.IntUnaryOperator                                                                                      | int a1                                                                           | int        |
 * | java.util.function.LongUnaryOperator                                                                                     | long a1                                                                          | long       |
 * | java.util.function.DoubleUnaryOperator                                                                                   | double a1                                                                        | double     |
 * | java.util.function.IntBinaryOperator                                                                                     | int a1,int a2                                                                    | int        |
 * | java.util.function.LongBinaryOperator                                                                                    | long a1,long a2                                                                  | long       |
 * | java.util.function.DoubleBinaryOperator                                                                                  | double a1,double a2                                                              | double     |
 * | java.util.function.Function                                                                                              | T a1                                                                             | R          |
 * | java.util.function.BiFunction                                                                                            | T1 a1,T2 a2                                                                      | R          |
 * | java.util.function.IntFunction                                                                                           | int a1                                                                           | R          |
 * | java.util.function.LongFunction                                                                                          | long a1                                                                          | R          |
 * | java.util.function.DoubleFunction                                                                                        | double a1                                                                        | R          |
 * | java.util.function.ToIntFunction                                                                                         | T a1                                                                             | int        |
 * | java.util.function.ToLongFunction                                                                                        | T a1                                                                             | long       |
 * | java.util.function.ToDoubleFunction                                                                                      | T a1                                                                             | double     |
 * | java.util.function.ToIntBiFunction                                                                                       | T1 a1,T2 a2                                                                      | int        |
 * | java.util.function.ToLongBiFunction                                                                                      | T1 a1,T2 a2                                                                      | long       |
 * | java.util.function.ToDoubleBiFunction                                                                                    | T1 a1,T2 a2                                                                      | double     |
 * | java.util.function.IntToLongFunction                                                                                     | int a1                                                                           | long       |
 * | java.util.function.IntToDoubleFunction                                                                                   | int a1                                                                           | double     |
 * | java.util.function.LongToIntFunction                                                                                     | long a1                                                                          | int        |
 * | java.util.function.LongToDoubleFunction                                                                                  | long a1                                                                          | double     |
 * | java.util.function.DoubleToIntFunction                                                                                   | double a1                                                                        | int        |
 * | java.util.function.DoubleToLongFunction                                                                                  | double a1                                                                        | long       |
 * | java.util.function.Predicate                                                                                             | T a1                                                                             | boolean    |
 * | java.util.function.BiPredicate                                                                                           | T1 a1,T2 a2                                                                      | boolean    |
 * | java.util.function.IntPredicate                                                                                          | int a1                                                                           | boolean    |
 * | java.util.function.LongPredicate                                                                                         | long a1                                                                          | boolean    |
 * | java.util.function.DoublePredicate                                                                                       | double a1                                                                        | boolean    |
 * | java.util.function.Supplier                                                                                              |                                                                                  | R          |
 * | java.util.function.IntSupplier                                                                                           |                                                                                  | int        |
 * | java.util.function.LongSupplier                                                                                          |                                                                                  | long       |
 * | java.util.function.DoubleSupplier                                                                                        |                                                                                  | double     |
 * | java.util.function.BooleanSupplier                                                                                       |                                                                                  | boolean    |
 * | java.util.function.Consumer                                                                                              | T a1                                                                             |            |
 * | java.util.function.BiConsumer                                                                                            | T1 a1,T2 a2                                                                      |            |
 * | java.util.function.IntConsumer                                                                                           | int a1                                                                           |            |
 * | java.util.function.LongConsumer                                                                                          | long a1                                                                          |            |
 * | java.util.function.DoubleConsumer                                                                                        | double a1                                                                        |            |
 * | java.util.function.ObjIntConsumer                                                                                        | T a1,int a2                                                                      |            |
 * | java.util.function.ObjLongConsumer                                                                                       | T a1,long a2                                                                     |            |
 * | java.util.function.ObjDoubleConsumer                                                                                     | T a1,double a2                                                                   |            |
 * | java.lang.Runnable                                                                                                       |                                                                                  |            |
 */
package eu.lunisolar.magma.func;