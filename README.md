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

It started with few interfaces that I happened to write and use. Then I found 
that extending, synchronising, and testing, and most of all making sure it is 
behaving consistently between growing number of classes (that look and do almost 
the same except for the specifics of arguments and return value) is getting 
annoying. So I started to moving those classes to the separate project.     
 
### Main goals
 
There are three main goals that this library was started to be build for:  

+ **Throwing lambda expressions and functional interfaces that declare and throw
 checked exceptions (since 2.0 throwing non-throwing lambdas are merged)**.      
+ **More functions** 
  + **More primitive types supported** 
  + **More combinations of arguments.**
  + list is here: [Functions](table.md)
+ **Reducing number of cases where:** 
  + you cannot directly reference method 
  + JVM cannot optimize code better (although nothing is guaranteed)  

Most of the above goals adds to the actual number of interfaces so this is not 
very tinny library as one would think. Behind each of those goals there are
reasons that do not apply for every day to day use cases that programmer can run 
into. Apart from that, I can easily state programming paradigms that are in 
opposition to the goals of ths library, e.g.:
 
+ <strike>Checked exceptions are evil</strike> (since 2.0 all checked exception are wrapped by default). 
+ Fully object oriented programming should avoid usage of primitive types.
+ Any multiple arguments can be replaced with instance of single object wrapping them. 

So, this is no silver bullet. **Choose the weapon accordingly to what you 
are aiming _at at any given time_.**  

#### Additional goals

Additionally some supporting functionality were implemented: 

+ **default methods** for each end very interface (inspired by some JRE default 
methods for the main interfaces).  
+ **assertions for AspectJ** for all interfaces + JRE interfaces.
+ **builders** for complex implementations that can be divided into multiple cases 
consisted of pairs:
    + predicate (tests condition if given function should be applied)
    + function (that will be evaluated if predicate tests positively)
    + that might seem not so useful for simple lambda expression cases, but trust 
    me I had a use case for that so I did it. 
+ **exception handling** 
+ custom set of optionals [Opt](magma-func-supp/src/main/eu/lunisolar/magma/meta/functional/model/func/Opt.java) ...
+ set of argument/state [Checks](magma-func-supp/src/main/eu/lunisolar/magma/func/supp/check/Cheks.java)

### Conventions
One big _convention_ that most might disagree with, is totally abandoning JavaDoc/HTML 
convention. I do not like to use a HTML browser to actually be able to _read_ the 
comments in the code, so I use MD. Thankfully there is a doclet for that :) - if not 
that plugin I would simply use doxygen.

#### Code repository

Code is hosted at github: <a href="https://github.com/lunisolar/magma/" target="_blank">repository</a>

#### Documentation

[Documentation](http://lunisolar.eu/magma)