package io.github.fabiogessner.pricing;

public record Customer(String id, boolean vip) {
    public Customer {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Customer ID is required");
    }
}
