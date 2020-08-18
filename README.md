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
   + More primitive types supported
   + More combinations of arguments.
   + List of interfaces:
      + [action](http://lunisolar.eu/magma/all-functions/actions)
      + [suppliers](http://lunisolar.eu/magma/all-functions/suppliers)
      + [consumers](http://lunisolar.eu/magma/all-functions/consumers)
      + [predicates](http://lunisolar.eu/magma/all-functions/predicates)
      + [operators](http://lunisolar.eu/magma/all-functions/operators)
      + [ordinary functions](http://lunisolar.eu/magma/all-functions/functions)
      + [all interfaces](http://lunisolar.eu/magma/all-functions)
   + Support for [throwing lambda](http://lunisolar.eu/magma/throwing-lambda) expressions. 
   + Support for [exception handling](http://lunisolar.eu/magma/exception-handling)  
   + [Default and static functions](http://lunisolar.eu/magma/defaults)
      + including simple [validations (throwIf)](http://lunisolar.eu/magma/validations-throwif) 
   + With AssertJ [assertions](http://lunisolar.eu/magma/assertions)
   + With [builders](http://lunisolar.eu/magma/builders)
+ Thus, reducing number of cases where:
   + You cannot directly reference method
   + JVM cannot optimize code better (although nothing is guaranteed)
+ [Tuples](https://github.com/lunisolar/magma/tree/master/magma-func/src/main/java/eu/lunisolar/magma/func/tuple)
    + For each function that has a domain there exists in addition to that function a tuple class (e.g. Pair, Triple).
+ Java 'monads'
    + [Opt(ionals)](http://lunisolar.eu/magma/opt-ional)
    + [Fluent validations/checks](http://lunisolar.eu/magma/validations-fluent)     

Most of the above goals adds to the actual number of interfaces so this is not 
very tinny library as one would think.

#### Download

Artifacts are available in Maven Central.  

For your production code:  

- [eu.lunisolar.magma:magma-func:3.0.0](https://search.maven.org/artifact/eu.lunisolar.magma/magma-func/3.0.0/jar) 
- [eu.lunisolar.magma:magma-func-builder:3.0.0](https://search.maven.org/artifact/eu.lunisolar.magma/magma-func-builder/3.0.0/jar)
- [eu.lunisolar.magma:magma-func-supp:3.0.0](https://search.maven.org/artifact/eu.lunisolar.magma/magma-func-supp/3.0.0/jar)

For your test code: 

- [eu.lunisolar.magma:magma-asserts:3.0.0](https://search.maven.org/artifact/eu.lunisolar.magma/magma-asserts/3.0.0/jar)

#### Code repository

Code is hosted at github: <a href="https://github.com/lunisolar/magma/" target="_blank">repository</a>

#### Documentation

[Documentation](http://lunisolar.eu/magma)