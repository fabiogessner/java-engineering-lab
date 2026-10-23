# Learning roadmap

## Module 01 milestones

1. Problem modeling (current): specify rules, examples, edge cases, initial acceptance tests, immutable input contracts.
2. Imperative engine: straightforward loops and conditions; enable the acceptance contract against this implementation.
3. OOP engine: composed DiscountRule strategies injected through a constructor; run the same contract.
4. Functional engine: pure functions, predicates, immutable reduction; run the same contract.
5. Review: equivalence tests, randomized rule permutations, trade-off comparison, coverage review and mutation testing.

Never claim a milestone is complete because skipped tests exist. Enable every acceptance scenario before declaring an engine complete.

## Remaining modules

02 SOLID/refactoring; 03 generics, records, sealed types; 04 collections/streams; 05 memory model and concurrency; 06 JVM; 07 reliability; 08 performance with JMH.

Each module must explain how it works, why it matters, trade-offs, and experimental evidence. Benchmarks and mutation testing belong to later milestones; no performance claims have been made.
