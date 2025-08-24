package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final int FIX_PRICE = 100;
    private final UUID id;

    public FixPriceProduct(String nameProduct) {
        super(nameProduct);
        this.id = UUID.randomUUID();
    }

    @Override
    public int getPriceProduct() {
        isSpecial();
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getNameProduct() + " c фиксированной ценой: " + FIX_PRICE;
    }
    @Override
    public UUID getId() {
        return id;
    }
}
