# ADR 0001: deterministic pricing semantics

Status: accepted for the initial exercise; revise through tests.

Use USD only, two decimal places, HALF_EVEN rounding per rule. This limits currency scope so paradigm comparisons share one precise contract. Reject fractional cents in input prices and fixed discounts; round only calculated percentage amounts.

Every rule uses the original eligible subtotal, not a progressively discounted amount. Sort by ascending integer priority, then unique case-sensitive rule ID using String natural ordering. Duplicate IDs are invalid. This makes results independent of input order.

An exclusive applicable rule suppresses all other rules, including earlier stackable rules. If several exclusive rules qualify, choose the first by the same ordering. Priority wins over the largest discount. Eligibility is evaluated before applying the global cap.

The global cap is an absolute USD amount. Allocate remaining cap sequentially; trim the last applied discount and prevent a negative final total. Report every configured rule in sorted order with proposed/applied amounts and a reason: APPLIED, NOT_ELIGIBLE, EXCLUSIVE_CONFLICT, or CAP_REACHED. An eligible rule rounded to zero has APPLIED with zero amounts.

Alternatives: compounding discounts, maximizing savings, mixed currencies, percent caps. Deferred because they materially change business semantics. None is implicitly implemented.
