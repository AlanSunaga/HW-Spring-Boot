package org.skypro.skyshop.exeption;

public class ShopError {
    private final Integer code;
    private final String message;

    public ShopError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
