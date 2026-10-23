# Pricing Rules Engine specification

## Inputs and outputs

Input: an immutable cart, customer ID and VIP status, rules, and an absolute USD discount cap. Products have ID, category, unit price and positive integer quantity. Empty carts are valid. IDs and categories must be nonblank. Negative money, null values and quantities below one are invalid.

Rules will have unique nonblank IDs, nonnegative priorities and an exclusive flag. Supported types: category percentage (0 through 1), fixed VIP discount, and fixed order discount conditional on minimum original subtotal. Threshold comparisons are inclusive. Rule amounts must be nonnegative.

Output: original subtotal, actual total discount, final total, and immutable audit entries. Each entry must include rule ID, proposed amount, applied amount and status. All money uses USD. No binary floating point.

## Calculation

1. Validate all inputs before evaluation, including ineligible rules.
2. Sort rules by priority ascending, then ID ascending.
3. Calculate each proposal on the original eligible subtotal. Category rules use the sum of matching line subtotals. Fixed VIP rules require a VIP customer. Order rules compare the original total to their threshold.
4. Round each percentage proposal once to two decimal places with HALF_EVEN.
5. Select the highest-priority eligible exclusive rule if any; suppress every other rule. Otherwise use all eligible stackable rules.
6. Allocate discounts in sorted order up to min(global cap, subtotal).
7. Return total = subtotal - applied discounts, plus audit entries for every rule.

No time, random values, mutable global state or network calls may influence the result. Repeated calls and shuffled input rules must produce identical results.

## Acceptance examples (USD)

| Case | Cart / rules / cap | Expected discount | Final |
| --- | --- | ---: | ---: |
| No rules | 100.00 / none / 100.00 | 0.00 | 100.00 |
| Category | books 80.00 + food 20.00 / books 10% / 100.00 | 8.00 | 92.00 |
| VIP | 100.00 / VIP fixed 15.00 / 100.00 | 15.00 | 85.00 |
| Regular customer | 100.00 / VIP fixed 15.00 / 100.00 | 0.00 | 100.00 |
| Minimum met | 100.00 / fixed 12.00 when total >= 100.00 / 100.00 | 12.00 | 88.00 |
| Below minimum | 99.99 / same threshold rule / 100.00 | 0.00 | 99.99 |
| Stack | books 100.00 / 10% + VIP 15.00 / 100.00 | 25.00 | 75.00 |
| Exclusive | 100.00 / stackable 20.00 + exclusive 5.00 / 100.00 | 5.00 | 95.00 |
| Cap | 100.00 / 10.00 priority 1 + 20.00 priority 2 / 25.00 | 25.00 | 75.00 |
| Oversized discount | 10.00 / fixed 20.00 / 100.00 | 10.00 | 0.00 |
| Empty | 0.00 / none / 100.00 | 0.00 | 0.00 |
| Zero cap | 100.00 / VIP 15.00 / 0.00 | 0.00 | 100.00 |
| Rounding | books 0.05 / 10% / 100.00 | 0.00 | 0.05 |

## Additional required cases before completing an engine

Multiple exclusive rules; exclusive with zero proposal; equal priority tie resolved by ID; shuffled rules; cap trimmed audit amounts; missing category; 0% and 100%; duplicate IDs; invalid percentages; negative priorities; null rules; attempted list mutation. All three implementations must satisfy the same tests.

## Scope

Pure Java only. Rule configuration and engine strategies arrive in milestone 02 onward. This specification does not imply current production support for these rules.
