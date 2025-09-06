package org.skypro.skyshop.service;

import org.skypro.skyshop.exeption.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);
        if (productOptional.isEmpty()) {
            throw new NoSuchProductException("Товар с ID " + id + " не найден в каталоге!");

        }
        productBasket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Product> productMap = storageService.getAllProducts();
        return new UserBasket(productBasket.getBasketMap().entrySet().stream().
                filter(entry -> productMap.containsKey(entry.getKey())).
                map(entry -> new BasketItem
                        (productMap.get(entry.getKey()), entry.getValue())).collect(Collectors.toList()));
    }
}
