Lunisolar Magma
===============


Status: ![https://img.shields.io/github/release/lunisolar/magma.svg](https://img.shields.io/github/release/lunisolar/magma.svg)
![https://img.shields.io/github/tag/lunisolar/magma.svg](https://img.shields.io/github/tag/lunisolar/magma.svg)
[![Build Status](https://travis-ci.org/lunisolar/magma.svg?branch=master)](https://travis-ci.org/lunisolar/magma)

-----------------------------------------
#### What it is?

A library of throwing and non-throwing functional interfaces (lambda interfaces) for Java 8, supplemented with assertion classes and builders.

It provides: 

+ **all primitives types** (not only int, long and double)
+ **more combinations of arguments**
+ **exception handling**
+ **assertions for AspectJ** for all interfaces + JRE interfaces.
+ **builders** for complex implementations that can be divided into multiple cases consisted of pairs:
    + predicate (tests condition if given function should be applied)
    + function (that will be evaluated if predicate tests positively)
+ **non-throwing** and **throwing** - each variant of arguments/result has a one of non-throwing and throwing version e.g. _Function<T,R>_ and _FunctionX<T,R,X extends Exception>_
+ **avoids boxing/unboxing** of primitives.


#### Documentation

[Documentation](http://lunisolar.eu/magma)

## Interface table

Here is the table of functional interfaces in the library: [table.md](table.md)