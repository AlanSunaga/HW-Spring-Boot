package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String getSearchTerm();

    String returnTypeContent();


    default void getStringRepresentation() {
        System.out.println(getSearchTerm() + "-объекта — тип " + returnTypeContent() + "-объекта");
    }

    UUID getId();
}
