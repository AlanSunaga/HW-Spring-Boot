package org.skypro.skyshop.model.basket;


import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class ProductBasket {
    private final Map<UUID, Integer> products = new HashMap<>();
    private static final Integer productScore = 1;

    public void addProduct(UUID id) {
        if (!products.containsKey(id)) {
            products.put(id, productScore);
        } else products.put(id, products.get(id) + 1);


    }

    public Map<UUID, Integer> getBasketMap() {
        return Collections.unmodifiableMap(products);
    }
}