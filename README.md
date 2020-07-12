Lunisolar Magma
===============

![https://img.shields.io/github/license/lunisolar/magma.svg](https://img.shields.io/github/license/lunisolar/magma.svg)
![https://img.shields.io/github/release/lunisolar/magma.svg](https://img.shields.io/github/release/lunisolar/magma.svg)
![https://img.shields.io/github/tag/lunisolar/magma.svg](https://img.shields.io/github/tag/lunisolar/magma.svg)
[![Build Status](https://travis-ci.org/lunisolar/magma.svg?branch=master)](https://travis-ci.org/lunisolar/magma)

-----------------------------------------
### What is it?

A library of functional interfaces for Java 8 (now 11), concentrated on ability to handle 
checked exceptions (throwing lambda) and/or primitive types, supplemented with 
assertion classes, builders and some additional classes. 

### Features
 
+ More of functional interfaces 
   + More primitive types are supported
   + More combinations of arguments.
   + further documentation:
      + [action](http://lunisolar.eu/magma/all-functions/actions)
      + [suppliers](http://lunisolar.eu/magma/all-functions/suppliers)
      + [consumers](http://lunisolar.eu/magma/all-functions/consumers)
      + [predicates](http://lunisolar.eu/magma/all-functions/predicates)
      + [operators](http://lunisolar.eu/magma/all-functions/operators)
      + [ordinary functions](http://lunisolar.eu/magma/all-functions/functions)
      + [all interfaces](http://lunisolar.eu/magma/all-functions)
+ Thus, reducing number of cases where:
   + You cannot directly reference method
   + JVM cannot optimize code better (although nothing is guaranteed)
+ [Default and static utility methods](http://lunisolar.eu/magma/defaults)  (applicable to the case and availability of other interfaces).
+ [Tuples](https://github.com/lunisolar/magma/tree/master/magma-func/src/main/java/eu/lunisolar/magma/func/tuple)
    + For each function that has a domain there exists in addition to that function a tuple class (e.g. Pair, Triple). 
    + Obviously number of tuples is much smaller because for each domain there is also a list of codomain types 
      (return values) that multiply number of functions.
    + Tuples have their mutable and immutable variants.    
+ Java 'monads'     

Most of the above goals adds to the actual number of interfaces so this is not 
very tinny library as one would think.


#### Additional goals

 
+ **assertions for AspectJ** for all interfaces + JRE interfaces.
+ **builders** for complex implementations that can be divided into multiple cases 
consisted of pairs:
    + predicate (tests condition if given function should be applied)
    + function (that will be evaluated if predicate tests positively)
    + that might seem not so useful for simple lambda expression cases, but trust 
    me I had a use case for that so I did it. 
+ **exception handling** 
+ custom set of optionals [Opt](https://github.com/lunisolar/magma/tree/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/opt/Opt.java) ...
+ set of argument/state [Checks](https://github.com/lunisolar/magma/tree/master/magma-func-supp/src/main/java/eu/lunisolar/magma/func/supp/check/Checks.java)


#### Code repository

Code is hosted at github: <a href="https://github.com/lunisolar/magma/" target="_blank">repository</a>

#### Documentation

[Documentation](http://lunisolar.eu/magma)