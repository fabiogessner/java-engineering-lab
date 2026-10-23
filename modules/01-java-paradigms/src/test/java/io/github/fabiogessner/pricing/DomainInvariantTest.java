package io.github.fabiogessner.pricing;

import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class DomainInvariantTest {
    @Test void rejectsNegativeMoneyAndFractionalCents() {
        assertThatIllegalArgumentException().isThrownBy(() -> Money.of("-0.01"));
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(() -> Money.of("1.001"));
        assertThatNullPointerException().isThrownBy(() -> new Money(null));
    }

    @Test void normalizesScaleAndValueEquality() {
        assertThat(Money.of("10")).isEqualTo(Money.of("10.00"));
    }

    @ParameterizedTest
    @CsvSource({"0.05,0.1,0.00", "0.15,0.1,0.02", "80,0.1,8.00", "10,0,0", "10,1,10"})
    void roundsCalculatedDiscounts(String amount, String rate, String expected) {
        assertThat(Money.of(amount).percentage(new BigDecimal(rate))).isEqualTo(Money.of(expected));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-0.01", "1.01"})
    void rejectsInvalidRates(String rate) {
        assertThatIllegalArgumentException().isThrownBy(() -> Money.of("10").percentage(new BigDecimal(rate)));
    }

    @Test void protectsCartFromExternalMutation() {
        var source = new ArrayList<CartItem>();
        source.add(new CartItem("book", "books", Money.of("20"), 2));
        var cart = new Cart(source);
        source.clear();
        assertThat(cart.subtotal()).isEqualTo(Money.of("40"));
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> cart.items().clear());
    }

    @Test void emptyCartHasZeroSubtotal() {
        assertThat(new Cart(List.of()).subtotal()).isEqualTo(Money.zero());
    }

    @ParameterizedTest @ValueSource(ints = {0, -1})
    void rejectsInvalidQuantities(int quantity) {
        assertThatIllegalArgumentException().isThrownBy(() -> new CartItem("book", "books", Money.of("1"), quantity));
    }

    @Test void rejectsMissingDomainValues() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Customer(" ", false));
        assertThatIllegalArgumentException().isThrownBy(() -> new CartItem("", "books", Money.zero(), 1));
        assertThatIllegalArgumentException().isThrownBy(() -> new CartItem("book", " ", Money.zero(), 1));
        assertThatNullPointerException().isThrownBy(() -> new CartItem("book", "books", null, 1));
        assertThatNullPointerException().isThrownBy(() -> new Cart(null));
        assertThatNullPointerException().isThrownBy(() -> new Cart(java.util.Arrays.asList((CartItem) null)));
    }
}
