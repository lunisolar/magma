# AGENTS.md

## Overview
**lunisolar-magma** is a Java library of throwing and non-throwing functional interfaces (lambda interfaces) targeting Java 21. It provides 260+ `L*` interfaces covering every combination of primitive types, argument counts (0–5+), return types, and checked/unchecked exception handling strategies.

- Build tool: Maven (`pom.xml` reactor at repo root). No Maven wrapper — `mvn` must be on PATH.
- Java target: 21 (configured in `magma-pom`).
- Test framework: TestNG 6.14.3.

---

## Module structure

The reactor build order reflects dependency layers:

```
magma-pom
  └─ magma-basics          (foundation: exceptions, metadata, null, builders)
       └─ magma-func        (core 260+ L* functional interfaces + tuples)
            └─ magma-func-supp   (predicate facades, fluent checks, opt/lazy/traits)
                 └─ magma-asserts      (fluent test assertions for L* interfaces)
            └─ magma-func-builder (per-case / partial-application builders)
  └─ magma-test            (examples, integration tests, JMH benchmarks)
```

### `magma-basics`
Foundation utilities that all other modules depend on.

| Package                                | Key classes                                                                           |
|----------------------------------------|---------------------------------------------------------------------------------------|
| `eu.lunisolar.magma.basics.exceptions` | `Handling` (wrap/nest/shove), `X` (exception factories), `NestedException`, `Handler` |
| `eu.lunisolar.magma.basics.meta`       | Metadata annotations and type descriptors (`aType`, `functional.*`, `domain.*`)       |
| `eu.lunisolar.magma.basics.fluent`     | `Fluent`, `FluentSyntax`, `AbstractFluentSubcontext`                                  |
| `eu.lunisolar.magma.basics`            | `Null` (null checks), `ToStr`                                                         |
| `eu.lunisolar.magma.basics.builder`    | Builder base utilities                                                                |

`Handling.java` is the canonical exception handler: `nestCheckedAndThrow()` converts checked exceptions to `RuntimeException`, `shoveIt()` force-throws bypassing the compiler, and `handleOrNest()` applies `HandlingInstructions`.

### `magma-func`
Core API. Every `L*` interface lives here.

| Package             | Contents                                                                                                                                |
|---------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| `...func.action`    | `LAction` — no-arg, no-return                                                                                                           |
| `...func.consumer`  | `LConsumer`, `LBiConsumer`, `LTriConsumer`, `LQuadConsumer`, `LQuintConsumer`; `primitives/*` (Byte/Int/Long/Dbl/Flt/Char/Srt variants) |
| `...func.function`  | `LFunction` (canonical), `from/*`, `to/*`, `conversion/*`                                                                               |
| `...func.operator`  | `unary/LUnaryOperator`, `binary/LBinaryOperator`, `ternary/LTernaryOperator`; primitive variants                                        |
| `...func.predicate` | 65+ predicate interfaces (`LPredicate`, `LBiPredicate`, `LObjIntPredicate`, etc.)                                                       |
| `...func.supplier`  | Object and primitive suppliers                                                                                                          |
| `...func.tuple`     | `LSingle`, `LPair`, `LTriple`, `LQuad`, `LQuint`, `LSextet`                                                                             |
| `...func` (root)    | `CallContext`, `AsyncCallContext`, `CallContexts`, `IA`, `SA`, `Function4U`                                                             |

### `magma-func-supp`
Higher-level support on top of `magma-func`.

| Package               | Key contents                                                                                             |
|-----------------------|----------------------------------------------------------------------------------------------------------|
| `...func.supp`        | `Predicates` base; synonym inner classes: `Is`, `Be`, `Are`, `Do`, `Does`, `Have`, `Has`, `As`, `P`, `ꝓ` |
| `...func.supp.check`  | `Checks` — fluent assertion entry points (`attest`, `attestThrownBy`)                                    |
| `...func.supp.opt`    | Optional wrapper utilities                                                                               |
| `...func.supp.value`  | Value holders and lazy evaluation                                                                        |
| `...func.supp.traits` | Trait composition interfaces                                                                             |
| `...func.supp.access` | Accessor patterns                                                                                        |
| `...func.supp.lazy`   | Lazy evaluation helpers                                                                                  |

The predicate synonym classes (`Is`, `Be`, `P`, etc.) are **method-reference facades** — all extend `Predicates` and expose readable static methods (`Is.equal`, `Is.Null`, `Be.instanceOf`, etc.) for use with method references in validation chains.

### `magma-asserts`
Fluent assertion infrastructure for testing `L*` interfaces.

| Package                                                                      | Key classes                                         |
|------------------------------------------------------------------------------|-----------------------------------------------------|
| `eu.lunisolar.magma.asserts`                                                 | `Attest`, `TestFlow`, `TestFlowTraits`, `TestsCons` |
| `...asserts.func`                                                            | `FuncAttests`, `FunctionalAttest`                   |
| `...asserts.func.{action,consumer,function,operator,predicate,supplier,std}` | Per-category attestation classes                    |

### `magma-func-builder`
Per-case builders and partial-application support.

- `eu.lunisolar.magma.func.build` — `PerCaseBuilder*` (Bool, Byte, Char, Dbl, Flt, Int, Long, Srt variants), `PartialCase*`
- Sub-packages mirror `magma-func` categories: `action`, `consumer`, `function`, `operator`, `predicate`, `supplier`

### `magma-test`
Examples, integration tests, and JMH benchmarks. Not a shipped artifact.

- Examples: `magma-test/src/test/java/eu/lunisolar/magma/examples/`
  - `Example_Goal1_Test` — throwing lambdas
  - `Example_Validations_Simple_Test` — `throwIf`/`throwIfNot` patterns
  - `Example_Validations_Fluent_Test` — fluent validation chains
  - `Example_ExceptionHandling_Test` — wrapping strategies
  - `Example_Builders_Test` — builder usage
  - `Example_Opt_Test` — optional patterns
  - `Example_Assertions_Test`, `Example_Defaults_Test`, `Example_Naming_Test`, `Example_ForEach_Test`
- Benchmarks: `**/*Perf.java` — expose a `main` using `eu.lunisolar.magma.test.JMH`

---

## API design conventions

### Method naming on `L*` interfaces

| Pattern                          | Description                                      | Example on `LFunction<T,R>`                        |
|----------------------------------|--------------------------------------------------|----------------------------------------------------|
| `applyX(T)`                      | Core contract; declares `throws Throwable`       | `R applyX(T a) throws Throwable`                   |
| `apply(T)`                       | Public entry point; wraps via `Handling`         | `R apply(T a)`                                     |
| `nesting*`                       | Nest checked exception inside `RuntimeException` | `nestingApply(T)`                                  |
| `shoving*`                       | Force-throw any exception bypassing compiler     | `shovingApply(T)`                                  |
| `trying(factory, msg)`           | Wrap with a custom exception factory             | `trying(ExWMF, String)`                            |
| `handlingApply(T, instructions)` | Custom `HandlingInstructions`                    | —                                                  |
| `applyThen(T, errorHandler)`     | Apply with a fallback on exception               | —                                                  |
| `nonNull*`                       | Assert result non-null before returning          | `nonNullApply(T)`                                  |
| `tupleApply(tuple)`              | Accept a tuple argument                          | `tupleApply(LSingle<T>)`                           |
| `compose(before)`                | Standard function composition (input side)       | `compose(LFunction<V,T>)`                          |
| `then*(after)`                   | Chaining after this function                     | `then(LFunction)`, `thenToByte()`, `thenConsume()` |
| `wrap(jre)`                      | Adapt a standard JRE functional interface        | `wrap(Function<T,R>)`                              |
| `func(lambda)`                   | Factory / type-inference helper                  | `func(t -> …)`                                     |
| `identity()`                     | Identity instance                                | —                                                  |

### Interface hierarchy
Every `L*` interface extends:
- The corresponding JRE interface where applicable (e.g., `Function<T,R>`)
- `MetaInterface.NonThrowing` **or** `MetaInterface.Throwing` (checked exception contract)
- `MetaFunction` / `MetaConsumer` / etc. (category metadata)
- `Codomain<a<R>>`, `Domain1<a<T>>` (type descriptor metadata)

### Exception handling
- Checked exceptions are a **first-class feature**. The `X` (throws Throwable) variant is the core contract.
- `Handling` in `magma-basics` is the implementation backbone for all wrapping/nesting/shoving.
- `HandlingInstructions<Throwable, RuntimeException>` and exception factories `ExWMF<T>` / `ExWF<T>` are passed as parameters for custom handling.
- Preserve symmetry in the overload family when adding new methods: if you add a `nestingFoo()`, also add `shovingFoo()` and `tryingFoo(…)`.

### Overload symmetry rule
When adding or modifying an exception-handling method, keep the full family consistent:
`nesting*` / `shoving*` / `trying*` / `handling*` / `applyThen` (+ `nonNull` where applicable).

---

## Code style rules

- **Do not** reformat, reorganize imports, or remove `// NOSONAR` comments in bulk — many files are generated artifacts treated as normal source and carry intentional patterns.
- **Broad wildcard imports** are standard in this codebase; do not convert them to specific imports.
- **Comments and markers** in `magma-test` examples (`//>inject<:generated`, `//>transform-to-MD<`, `//>example<`) are documentation generation hooks — keep them intact.
- Only comment code that genuinely needs clarification; avoid noise comments.

---

## Test conventions

Tests use **TestNG**, not JUnit. Key patterns from `LFunctionTest.java`:

```java
@Test
public void testApply() throws Throwable {
    Assert.assertEquals(sut.apply(100), testValue);
}

@Test(expectedExceptions = NullPointerException.class,
      expectedExceptionsMessageRegExp = "\\QEvaluated value by nonNullApply()...\\E")
public void testNonNullCapturesNull() throws Throwable {
    sutNull.nonNullApply(100);
}

@Test
public void testCompose() throws Throwable {
    final ThreadLocal<Boolean> mainFunctionCalled = ThreadLocal.withInitial(() -> false);
    final AtomicInteger beforeCalls = new AtomicInteger(0);
    LFunction<Integer, Integer> sut = a -> { mainFunctionCalled.set(true); return a; };
    // assertions inside lambda bodies using ThreadLocal / AtomicInteger
}
```

- Import `org.testng.annotations.*` and `org.testng.Assert`.
- Use `@Test(expectedExceptions=…, expectedExceptionsMessageRegExp=…)` for exception validation.
- Use `ThreadLocal` and `AtomicInteger` to assert behavior inside composed/chained lambdas.
- Fluent assertions from `magma-asserts` (`Attest`, `TestFlow`) are available for richer checks.

---

## Build, test, and debug commands

```bash
# Full reactor build and test
mvn clean verify

# Build one module and all its upstream dependencies, then test
mvn -pl magma-func -am test

# Build only magma-func-supp with upstream deps
mvn -pl magma-func-supp -am test

# Run a single TestNG class
mvn -pl magma-func -Dtest=LFunctionTest test

# Run all example tests in magma-test
mvn -pl magma-test -am test

# Run benchmarks (via main method)
mvn -pl magma-test -am compile exec:java -Dexec.mainClass=eu.lunisolar.magma.examples.Validations4U_Perf
```

CI runs `mvn clean verify` on Ubuntu with JDK 21 (see `.github/workflows/build.yml`). Surefire TestNG reports are uploaded as artifacts.

---

## Safe change strategy

- Make **minimal, pattern-consistent** edits within one functional family at a time (e.g., only `function/*` or only `predicate/*`).
- When changing behavior, update tests in the affected module **and** add or update at least one example in `magma-test`.
- For cross-module API changes, verify downstream compile impact in `magma-func-supp`, `magma-asserts`, and `magma-func-builder` before finishing (`mvn -pl magma-func-supp,magma-asserts,magma-func-builder -am test`).
- Preserve overload symmetry: `nesting*`, `shoving*`, `trying*`, `handling*` must stay in sync.
