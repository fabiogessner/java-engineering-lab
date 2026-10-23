package io.github.fabiogessner.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Nonnegative USD amount. Input must have at most two significant decimal places. */
public record Money(BigDecimal amount) {
    public Money {
        Objects.requireNonNull(amount, "amount");
        if (amount.signum() < 0) throw new IllegalArgumentException("Money cannot be negative");
        amount = amount.setScale(2, RoundingMode.UNNECESSARY);
    }

    public static Money of(String amount) {
        return new Money(new BigDecimal(amount));
    }

    public static Money zero() { return of("0.00"); }

    public Money add(Money other) { return new Money(amount.add(other.amount)); }

    public Money multiply(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        return new Money(amount.multiply(BigDecimal.valueOf(quantity)));
    }

    public Money percentage(BigDecimal rate) {
        Objects.requireNonNull(rate, "rate");
        if (rate.signum() < 0 || rate.compareTo(BigDecimal.ONE) > 0)
            throw new IllegalArgumentException("Rate must be between zero and one");
        return new Money(amount.multiply(rate).setScale(2, RoundingMode.HALF_EVEN));
    }
}
