package io.github.fabiogessner.pricing;

import java.util.Objects;

public record CartItem(String productId, String category, Money unitPrice, int quantity) {
    public CartItem {
        if (productId == null || productId.isBlank()) throw new IllegalArgumentException("Product ID is required");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Category is required");
        Objects.requireNonNull(unitPrice, "unitPrice");
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be positive");
    }

    public Money subtotal() { return unitPrice.multiply(quantity); }
}
