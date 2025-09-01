package org.skypro.skyshop.exeption;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException(String message) {
        super(message);
    }

    public NoSuchProductException(UUID productId) {
        super("Товар с этим ID не найден: " + productId);
    }
}
