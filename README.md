# Java Engineering Lab

Engineering Java from fundamentals to production-grade design.

A study and interview portfolio focused on understanding behavior, explaining trade-offs, and proving decisions with tests. Java 25, Maven 3.9+, JUnit 5, AssertJ. Documentation and code use English.

## Current delivery

**Milestone 01 — Problem Modeling.** Maven reactor, pricing specification, immutable input contracts, invariant tests, acceptance scenarios, and CI configuration.

The pricing engines are deliberately **not implemented yet**. Engine acceptance tests are explicitly disabled and are not evidence of pricing correctness. Input invariant tests run normally. This milestone is a specification and foundation, not a completed pricing engine.

## Run

Install JDK 25 and Maven 3.9.x, then run:

```sh
java -version
mvn -version
mvn clean verify
mvn -pl modules/01-java-paradigms -am test
```

No Spring, database, Docker, AWS, network access at application runtime, or preview features. Maven needs Internet access to resolve build dependencies initially.

## Start here

- [Pricing specification](modules/01-java-paradigms/docs/problem.md)
- [Module guide](modules/01-java-paradigms/README.md)
- [Roadmap](docs/learning-roadmap.md)
- [Engineering principles](docs/engineering-principles.md)
- [Pricing decisions](docs/adr/0001-pricing-semantics.md)
- [Learning notes](modules/01-java-paradigms/docs/learning-notes.md)

## Planned modules

1. Java paradigms and OOP — Pricing Rules Engine (current)
2. SOLID and refactoring — Notification Processing System
3. Type system — Type-safe Validation Library
4. Collections and functional programming — Data Processing Pipeline
5. Concurrency — Concurrent Task Processor
6. JVM internals — JVM Behavior Experiments
7. Error handling — Resilient Batch Processor
8. Performance — Performance Comparison Suite

Only module 01 is registered in Maven. Future modules will be independent and added when implemented.

## Provenance

Based on the [shared Java Engineering Lab plan](https://chatgpt.com/s/t_6adab489c38881918982108e3ed7e7ec). Ambiguous pricing choices are resolved explicitly in ADR 0001 and can be revised with acceptance tests.

On Windows, run `.\scripts\verify.ps1`. It uses a local ignored `.tools` JDK/Maven installation if present, otherwise Maven on PATH. Portable tools were downloaded locally for initial verification and are not versioned.

