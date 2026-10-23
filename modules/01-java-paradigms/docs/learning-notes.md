# Learning notes

## Initial observations

Pricing order is a business decision. Exclusive does not necessarily mean best savings. Rounding each rule differs from rounding the combined percentage. Defensive copies matter even when a record is immutable at the field level.

## Questions to investigate

- Should applicability and proposal share a single result type?
- What changes if discounts compound?
- When does a Strategy interface improve the design?
- How do we prove equality of all three implementations?

## Evidence log

Milestone 01 introduces contracts and acceptance scenarios. Engine behavior, performance, coverage and mutation resistance remain unproven. Record actual commands and results as work progresses.

## Local verification — 2026-10-22

JDK 25 and Maven 3.9.11: clean verify succeeded. Four production sources and two test sources compiled. DomainInvariantTest: 14 passed. PricingAcceptanceTest: one disabled parameterized method containing 13 pending examples (reported by Surefire as one skipped test). No pricing engine has been validated.
