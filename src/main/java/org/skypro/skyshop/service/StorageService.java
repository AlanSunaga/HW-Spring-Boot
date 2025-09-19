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

    public StorageService() {
        this.allProducts = new HashMap<>();
        this.articleMap = new HashMap<>();
        this.addProduct();
    }

    public Map<UUID, Article> getArticleMap() {
        return articleMap;
    }

    public Map<UUID, Product> getAllProducts() {
        return allProducts;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(allProducts.get(id));
    }

    private void addProduct() {
        Product product1 = new SimpleProduct("Хлеб", 50, UUID.fromString("4f0a5747-710a-4ae1-839c-a740a54060aa"));
        Product product2 = new DiscountedProduct("Молоко", 80, 50, UUID.randomUUID());
        Product product3 = new SimpleProduct("Сыр", 300, UUID.randomUUID());
        Product product4 = new FixPriceProduct("Чипсы", UUID.randomUUID());
        Product product5 = new DiscountedProduct("Пепси", 50, 20, UUID.randomUUID());

        allProducts.put(product1.getId(), product1);
        allProducts.put(product2.getId(), product2);
        allProducts.put(product3.getId(), product3);
        allProducts.put(product4.getId(), product4);
        allProducts.put(product5.getId(), product5);

        Article article1 = new Article("1", "1", UUID.randomUUID());
        Article article2 = new Article("2", "2", UUID.randomUUID());
        Article article3 = new Article("3", "3", UUID.randomUUID());

        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
        articleMap.put(article3.getId(), article3);
    }

    public List<Searchable> getAllSearchables() {
        List<Searchable> allProducts = new ArrayList<>();
        allProducts.addAll(this.allProducts.values());
        allProducts.addAll(this.articleMap.values());
        return allProducts;
    }
}
