package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {

    private static final int FIX_PRICE = 100;


    public FixPriceProduct(String nameProduct, UUID id) {
        super(nameProduct, id);

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
}
