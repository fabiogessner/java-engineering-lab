package io.github.fabiogessner.pricing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Milestone 01 acceptance backlog. Replace fail() with real engine calls in milestone 02.
 * Never enable by deleting assertions or substituting expected values for actual results.
 */
@Disabled("Milestone 02: pricing engines and rule configuration are not implemented")
class PricingAcceptanceTest {
    @ParameterizedTest(name = "{0}: discount={1}, final={2}")
    @CsvSource({
        "no rules,0.00,100.00",
        "category books,8.00,92.00",
        "VIP customer,15.00,85.00",
        "regular customer,0.00,100.00",
        "minimum met,12.00,88.00",
        "below minimum,0.00,99.99",
        "stackable rules,25.00,75.00",
        "exclusive rule,5.00,95.00",
        "global cap,25.00,75.00",
        "oversized discount,10.00,0.00",
        "empty cart,0.00,0.00",
        "zero cap,0.00,100.00",
        "half even rounding,0.00,0.05"
    })
    void satisfiesSpecifiedBusinessScenario(String scenario, String discount, String total) {
        org.junit.jupiter.api.Assertions.fail(
                "Pending engine fixture for " + scenario + "; see docs/problem.md");
    }
}
