package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    int priceProduct;
    private final UUID id;

    public SimpleProduct(String nameProduct, int priceProduct) {

        super(nameProduct);
        this.id = UUID.randomUUID();
        if (priceProduct < 0) {
            throw new IllegalArgumentException();
        }
        this.priceProduct = priceProduct;
    }

    @Override
    public int getPriceProduct() {
        return priceProduct;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        return getNameProduct() + " : " + priceProduct;
    }
    @Override
    public UUID getId() {
        return id;
    }
}
