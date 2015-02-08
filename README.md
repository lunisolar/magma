Lunisolar's Magma
=================

Library of Java 8 functional interfaces (AKA Lambda interfaces).

Build status: [![https://travis-ci.org/lunisolar/magma.svg?branch=master](https://travis-ci.org/lunisolar/magma.svg?branch=master)]

## Overview

Java 8 JRE implements only limited number (around 30) of functional interfaces. Those are the most important and universal once. That is usually enough for most
projects, but there might be also projects where team end up with creating additional set of interfaces to serve their own purposes. In some cases that might be
about functions with very specific argument types that are only valid for the given project. But it also might be a missing in JRE interfaces for rest ov the
primitives or different combination of arguments that include or not primitives.

JRE functional interfaces also leave complete  exception handling to the programmers as also in many cases it is enough and there is no need for nothing else.

This library is about those cases not covered in JRE:

+ more primitives
+ more combinations of arguments
+ exception handling

Additionally following is provided:

+ assertions for AspectJ for all interfaces + JRE interfaces.
+ builders for complex implementations that can be divided into multiple cases consisted of pairs:
    + predicate (tests condition if given function should be applied)
    + function (that will be evaluated if predicate tests positively)
+ each functional interface has a non-throwing and throwing version e.g. _Function<T,R>_ and _FunctionX<T,R,X extends Exception>_

If you do not need primitives, exception handling nor assertions then this library might simply be not for you.

## Interface table

Here is the table of functional interfaces in the library: [table.md](table.md)

## History

From time to time I was witting one of the functions just like here. Either it was a throwing one or some permutation of domain arguments not yet covered.
The issue was that each time:

+ it was always a dilemma if should I use non-throwing version and wrap any exceptions to RuntimeException, or do some redesign, or have handy implementation of throwing and non-throwing version of the function.
+ it was always a dilemma that supporting methods should also be written (having, some here, some there, do not look consistent).
+ it started to produce naming inconsistencies as it usually was a different context (and the previous time I added a function was some time ago).
+ some JRE functional interfaces were lacking some support methods.

So for better or worst I finally wrote the library that beforehand contains most common cases.

## Design decisions

### Throwing declarations should always surface in the compilation.

If you want to handle exceptions outside functions/lambdas, and those are the checked exception, you need to declare throw clause in the method. Since I wanted
to have throwing variants of the functions it means that there are two separate sets of the functional interfaces. And unfortunately in any given case you need
to narrow list of exceptions just to one common denominator (if there are more).

### X extends Exception, throws X

There are drawbacks of using generics for **throws** declaration.

1. Since exception are declared as generic type argument it can be re-casted.
For example:

```java
ActionX<Exception> checkedExceptionAction;
ActionX<RuntimeException> uncheckedException = (ActionX) checkedExceptionAction;
```

As a result _uncheckedException_ still will throw checked exception but compiler will no longer care about that.

1. Type inferring.

There are obvious situations where compiler could infer the type of exception (and thus choose throwing interface) and stop complaining about the implementation of lambda that has uncaught exception in it. But the compiler does not do that (at least not always).
In most cases this happen just in places where other generic types could not be inferred either. However there are some situations where compilers (Oracle JDK, GA releases) can throw NullPointerException.
Compiler throwing NPE is not a usual situation and it also can confuse tools like maven with diagnostic information about place of the problem (placing it even in different class). It also changes with newer releases of Java JDK.
In such cases I usually go to assigning lambda expressions to properly declared variables and then simplifying statements one single change at a time.

### All JRE interfaces from **java.util.functions** will have its variant in the library.

Each interface have a lot of convenience default methods so each JRE variant has its duplicate in the library that inherits from the JRE one. Each such variant, with exception for throwing once, inherits from the original JRE variant.

### More primitive variants

JRE also embraces primitives in the functional interfaces it just does so only for cases that have a usage in the JRE itself. In case of this library we go little further so the number of convenience methods and interfaces grows considerably. At least to some point.

### Mirrored methods

Some methods like nonNull() for primitive variants of functions are implemented to just mirror the method that exists elsewhere. They cannot be declared in meta-interface since return type changes and it includes primitives but nonetheless they are implemented anyway.
There are few more of such examples.

### Class loading - no utilities with all cases.

There might be a potential need to have all static methods in a utility class like **Functions** or **Lambdas** that would have some methods like xyzBuilder() that would create a builder instances.
There will be no such cover-all-cases-utility-class (with oen exception) for following reasons:

- Static methods that will cover all cases will simply load absolutely all those classes on first use - a hundreds of them. That is no problem if yu utilise all of them. but I doubt that will be a common case.
- Having so many cases would require to use a class name (or some other denominator) in the method anyway, so instead of Function.intToShortFunctionBuilder() you can use IntToShortFunction.builder().

The only exception is **FunctionalAssertions** since it is not intended to be use in production code anyway.

## Other rules

- interface type precedence (naming convention): _Operator_, _Predicate_, _Function_, _Consumer_ | _Supplier_ | _Execution_
- naming convention: I tried to be consistent with JRE naming (extrapolation).
- _Bi_, _Tri_, _Quad_ as a quantitative prefixes for number of arguments in consumers and functions ( _BiConsumer_, _TriConsumer_).
- _Unary_, _Binary_, _Ternary_ as a quantitative prefixes for Operators ( _UnaryOperator_, _BinaryOperator_, _TernaryOperator_).
- Some methods are mirrored even if they have only cosmetic effect ( _nonNull_, _uncheck_, _min_, _max_, _and_, _or_).
- For each case there is a non-throwing and throwing variant + conversion methods.
- If there is a variant in JRE the library it will be extended (to provide/mirror all the methods).
