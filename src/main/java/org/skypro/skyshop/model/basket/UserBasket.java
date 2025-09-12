package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private List<BasketItem> basketItemList;
    private double totalBasket;

    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;

    }

    private double calculateTotal() {
        return basketItemList.stream()
                .mapToDouble(BasketItem::getTotalPrice)
                .sum();
    }

    public List<BasketItem> getBasketItemList() {
        return basketItemList;
    }

    public boolean isEmpty() {
        return basketItemList.isEmpty();
    }

}
