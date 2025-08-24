package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> allProducts;
    private final Map<UUID, Article> articleMap;

    public StorageService(Map<UUID, Product> allProducts, Map<UUID, Article> articleMap) {
        this.allProducts = new HashMap<>();
        this.articleMap = new HashMap<>();
        addProduct();
    }

    public Map<UUID, Article> getArticleMap() {
        return articleMap;
    }

    public Map<UUID, Product> getAllProducts() {
        return allProducts;
    }

    private void addProduct() {
        allProducts.put(UUID.randomUUID(), new SimpleProduct("Хлеб", 50));
        allProducts.put(UUID.randomUUID(), new DiscountedProduct("Молоко", 80, 50));
        allProducts.put(UUID.randomUUID(), new SimpleProduct("Сыр", 300));
        allProducts.put(UUID.randomUUID(), new FixPriceProduct("Чипсы"));
        allProducts.put(UUID.randomUUID(), new DiscountedProduct("Пепси", 50, 20));

        articleMap.put(UUID.randomUUID(), new Article("1", "1"));
        articleMap.put(UUID.randomUUID(), new Article("2", "2"));
        articleMap.put(UUID.randomUUID(), new Article("3", "3"));
    }

    public List<Searchable> getAllSearchables() {
        List<Searchable> allSearchablesProducts = new ArrayList<>();
        allSearchablesProducts.addAll(allProducts.values());
        allSearchablesProducts.addAll(articleMap.values());
        return allSearchablesProducts;
    }
}
