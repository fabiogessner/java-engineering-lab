package io.github.fabiogessner.pricing;

import java.util.List;

public record Cart(List<CartItem> items) {
    public Cart { items = List.copyOf(items); }

    public Money subtotal() {
        return items.stream().map(CartItem::subtotal).reduce(Money.zero(), Money::add);
    }
}
