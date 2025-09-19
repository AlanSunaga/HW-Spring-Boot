package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void findProduct_whenNoMatchingProducts_shouldReturnEmptyOptional() {
        // Подготовка
        when(storageService.getAllSearchables()).thenReturn(List.of());
        // Действие
        List<SearchResult> serviceP = searchService.search("Печенье");
        // Проверка
        assertTrue(serviceP.isEmpty());
    }

    @Test
    void findProduct_whenMatchingProductExists_shouldReturnProduct() {
        // Подготовка
        Product product2 = new DiscountedProduct("Молоко", 80, 50, UUID.randomUUID());
        Article article1 = new Article("1", "1", UUID.randomUUID());

        List<Searchable> searchableItems = Arrays.asList(article1, product2);

        when(storageService.getAllSearchables()).thenReturn(searchableItems);
        // Действие
        List<SearchResult> result = searchService.search("Печенье");

        // Проверка
        assertEquals(0, result.size());

    }

    @Test
    void findAllProducts_whenMultipleMatches_shouldReturnList() {
        // Подготовка
        Product product1 = new SimpleProduct("Хлеб", 50, UUID.fromString("4f0a5747-710a-4ae1-839c-a740a54060aa"));
        Product product2 = new DiscountedProduct("Молоко", 80, 50, UUID.randomUUID());
        Product product3 = new SimpleProduct("Сыр", 300, UUID.randomUUID());

        List<Searchable> searchableItems = Arrays.asList(product1, product2, product3);

        when(storageService.getAllSearchables()).thenReturn(searchableItems);
        // Действие
        List<SearchResult> result = searchService.search("Хлеб");
        // Проверка
        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(item ->
                item.getName().contains("Хлеб")));
    }


}
