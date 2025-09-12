package org.skypro.skyshop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exeption.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @InjectMocks
    private BasketService basketService;


    private final UUID existingProductId = UUID.randomUUID();
    private final UUID nonExistingProductId = UUID.randomUUID();
    private final Product product = new FixPriceProduct("Печенье", existingProductId);

    @Test
    void addProductToCart_whenProductNotExists_shouldThrowException() {

        when(storageService.getProductById(nonExistingProductId))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> {
            basketService.addProduct(nonExistingProductId);
        });

        verify(productBasket, never()).addProduct(any(UUID.class));
        verify(storageService, times(1)).getProductById(nonExistingProductId);
    }

    @Test
    void addProduct_whenProductExists_shouldAddToBasket() {
        // Подготовка
        when(storageService.getProductById(existingProductId))
                .thenReturn(Optional.of(product));

        // Действие
        basketService.addProduct(existingProductId);

        // Проверка
        verify(storageService, times(1)).getProductById(existingProductId);
        verify(productBasket, times(1)).addProduct(existingProductId);
    }

    @Test
    void getUserBasket_whenProductBasketEmpty_shouldReturnEmptyUserBasket() {
        // Arrange
        when(productBasket.getBasketMap()).thenReturn(Collections.emptyMap());

        // Act
        UserBasket result = basketService.getUserBasket();

        // Assert
        assertTrue(result.isEmpty());
        assertTrue(result.getBasketItemList().isEmpty());
        verify(productBasket, times(1)).getBasketMap();

    }

    @Test
    void getUserBasket_shouldReturnCorrectBasket() {
        // Подготовка
        UUID productId = UUID.randomUUID();
        Product testProduct = new FixPriceProduct("Молоко", productId);

        // Настраиваем корзину
        Map<UUID, Integer> basketMap = new HashMap<>();
        basketMap.put(productId, 2);
        when(productBasket.getBasketMap()).thenReturn(basketMap);

        // Настраиваем хранилище
        Map<UUID, Product> storageMap = new HashMap<>();
        storageMap.put(productId, testProduct);
        when(storageService.getAllProducts()).thenReturn(storageMap);

        // Действие
        UserBasket result = basketService.getUserBasket();

        // Проверка
        assertFalse(result.getBasketItemList().isEmpty());
        assertEquals(1, result.getBasketItemList().size());
        assertEquals(testProduct, result.getBasketItemList().get(0).getProduct());
        assertEquals(2, result.getBasketItemList().get(0).getScore());
    }
}
