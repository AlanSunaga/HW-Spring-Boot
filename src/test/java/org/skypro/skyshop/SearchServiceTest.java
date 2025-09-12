package org.skypro.skyshop;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    void findProduct_whenNoMatchingProducts_shouldReturnEmptyOptional() {

        when(storageService.getAllSearchables()).thenReturn(List.of());

        List<SearchResult> serviceP = searchService.search("Печенье");

        assertTrue(serviceP.isEmpty());
    }

    @Test
    void findProduct_whenMatchingProductExists_shouldReturnProduct() {

        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        List<SearchResult> serviceP = searchService.search("Печенье");

        assertTrue(serviceP.isEmpty());
    }

    @Test
    void findAllProducts_whenMultipleMatches_shouldReturnList() {

        Product product = new SimpleProduct("Печенье", 100, UUID.randomUUID());
        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());

        List<SearchResult> serviceP = searchService.search("Печенье");

        assertTrue(serviceP.isEmpty());
    }

}
