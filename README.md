Lunisolar Magma
===============

![https://img.shields.io/github/license/lunisolar/magma.svg](https://img.shields.io/github/license/lunisolar/magma.svg)
![https://img.shields.io/github/release/lunisolar/magma.svg](https://img.shields.io/github/release/lunisolar/magma.svg)
![https://img.shields.io/github/tag/lunisolar/magma.svg](https://img.shields.io/github/tag/lunisolar/magma.svg)
[![Build Status](https://travis-ci.org/lunisolar/magma.svg?branch=master)](https://travis-ci.org/lunisolar/magma)

-----------------------------------------
#### What is it?

A library of throwing and non-throwing functional interfaces (lambda interfaces) for Java 8, supplemented with assertion classes and builders.

A library of throwing and non-throwing functional interfaces (lambda interfaces) for Java 8 with sole purpose to better or more importantly more easily use 
lambda expressions.

It started with few interfaces that I happened to write and use. Then I found that extending, synchronising, and testing, and most of all making sure it is 
behaving consistently between growing number of classes that look and do almost the same except for the specifics, is getting annoying. So I started to moving 
those classes to the separate project.     
 
There are three main goals that this library was started to be build for:  

+ **Throwing lambda expressions and functional interfaces that declare and throw checked exceptions**.      
+ **More primitive types supported**. 
+ **More combinations of arguments.**
 
Each of the above goals adds to the actual number of interfaces so this is not very tinny library as one would think. And behind each of those goals there are
reasons that do not apply for every day to day use cases that programmer can run into. Apart from that I can easily state programming paradigms that are in 
opposition to the goals of ths library:
 
+ Checked exceptions are evil. 
+ Fully object oriented programming should avoid usage of primitive types.
+ Any multiple arguments can be replaced with instance of single object wrapping them. 

My personal stand on this _dualism_ is that it all depends on the context. **So choose the weapon accordingly to what you are aiming _at at any given time_.**  
And if you **absolutely agree with the anti-reasons** then probably Java is _absolutely not_ for you since Java:  
+ allows, and most importantly uses checked exceptions in JRE
+ allows, and most importantly uses primitive types in JRE
+ non _real-time/performance_ programmer always wanders, where those memory usage and low performance comes from.     

Additionally some supporting functionality were implemented: 
+ **default methods** for each end very interface (inspired my JRE default methods for the main interfaces).  
+ **assertions for AspectJ** for all interfaces + JRE interfaces.
+ **builders** for complex implementations that can be divided into multiple cases consisted of pairs:
    + predicate (tests condition if given function should be applied)
    + function (that will be evaluated if predicate tests positively)
    + that might seem not so useful for simple lambda expression cases, but trust me I had a use case for that so I did it. 
+ full **exception handling**, not just preset propagation and wrapping rules.     

#### General advice
By writing and using those interfaces even if just in the unit tests that were generated for those interfaces I found that a Java compiler sometimes need a help
in inferring the type of the generics. And then sometimes even more help is required to infer the generic types in throwing expressions. In the end mind that it
is after all only 3rd level language and compiler do not YET utilise AI engine.
   
### Conventions
One big _convention_ that most might disagree with, is totally abandoning JavaDoc/HTML convention. I do not like to use a HTML browser to actually be able to
read the comments in the code, so I use MD. Thankfully there is a doclet for that :) - if not that plugin I would simply use doxygen :p

[...]

### The interface(s) I want is not here!
Create an issue for that. It will not be immediate, but I could add it. If not in the main library module, then in auxiliary one.    

#### Code repository

Code is hosted at github: <a href="https://github.com/lunisolar/magma/" target="_blank">repository</a>

#### Documentation

[Documentation](http://lunisolar.eu/magma)

## Interface table

Here is the table of functional interfaces in the library: [table.md](table.md)






         
     