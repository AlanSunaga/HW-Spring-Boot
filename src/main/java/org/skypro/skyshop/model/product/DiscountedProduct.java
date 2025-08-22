package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    int basePrice;
    int discount;
    private final UUID id;

    public DiscountedProduct(String nameProduct, int basePrice, int discount) {
        super(nameProduct);
        this.id = UUID.randomUUID();
        if (basePrice < 0) {
            throw new IllegalArgumentException("Сумма базовой цены должна быть больше нуля");
        }
        this.basePrice = basePrice;
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должа быть в диапозоне от 0 до 100");
        }
        this.discount = discount;
    }

    @Override
    public int getPriceProduct() {

        return basePrice - (basePrice * discount) / 100;
    }

    @Override
    public String toString() {
        return getNameProduct() + " : " + basePrice + '\n'
                + getNameProduct() + " со скидкой: " + getPriceProduct() + ", скидка: " + discount + " %";
    }

    @Override
    public UUID getId() {
        return id;
    }
}
