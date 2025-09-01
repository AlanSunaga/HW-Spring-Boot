package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private final Product product;
    private final int score;

    public BasketItem(Product product, int score) {
        this.score = score;
        this.product = product;
    }

    public double getTotalPrice() {
        return product.getPriceProduct() * score;
    }

    public Product getProduct() {
        return product;
    }

    public int getScore() {
        return score;
    }
}
