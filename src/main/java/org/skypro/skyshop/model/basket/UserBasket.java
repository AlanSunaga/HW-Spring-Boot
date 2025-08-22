package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basketItemList;
    private final double totalBasket;

    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
        this.totalBasket = calculateTotal(basketItemList);
    }
    private double calculateTotal(List<BasketItem> basketItemList) {
        return basketItemList.stream()
                .mapToDouble(BasketItem::getTotalPrice)
                .sum();
    }
}
