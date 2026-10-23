# Design decisions

Inputs are records with validated constructors and defensive list copies. Money centralizes scale and rounding. A future PricingEngine contract will be the boundary for implementations; there is no stub that pretends to calculate prices. Rule APIs and result audit types will be introduced when backed by acceptance tests. See ADR 0001 for combination policies. Compare imperative clarity, OOP extension cost and functional immutability after implementations exist.
