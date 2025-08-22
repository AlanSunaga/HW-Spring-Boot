package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String nameProduct;
    private final UUID id;


    public Product(String nameProduct,UUID id) {

        if (nameProduct == null || nameProduct.equals("")) {
            throw new IllegalArgumentException("Имя null или строка пустая");
        }
        this.nameProduct = nameProduct;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameProduct);
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public abstract int getPriceProduct();

    public boolean isSpecial() {
        return true;
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getNameProduct();
    }

    @Override
    public UUID getId() {
        return id;
    }
    @JsonIgnore
    @Override
    public String returnTypeContent() {
        return "PRODUCT";
    }
}
