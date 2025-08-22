package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String nameTitleArticle;
    private final String textTitleArticle;
    private final UUID id;

    public Article(String nameTitleArticle, String textTitleArticle,UUID id) {
        this.nameTitleArticle = nameTitleArticle;
        this.textTitleArticle = textTitleArticle;
        this.id = id;
    }

    public String getNameTitleArticle() {
        return nameTitleArticle;
    }

    public String getTextTitleArticle() {
        return textTitleArticle;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article article)) return false;
        return Objects.equals(nameTitleArticle, article.nameTitleArticle) && Objects.equals(textTitleArticle, article.textTitleArticle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameTitleArticle, textTitleArticle);
    }

    @Override
    public String toString() {
        return "Название " + getNameTitleArticle() + '\n'
                + "Текст " + getTextTitleArticle();
    }
    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return toString();
    }
    @JsonIgnore
    @Override
    public String returnTypeContent() {
        return "ARTICLE";
    }

    @Override
    public UUID getId() {
        return id;
    }
}
