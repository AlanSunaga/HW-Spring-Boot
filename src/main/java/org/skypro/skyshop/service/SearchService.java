package org.skypro.skyshop.service;

import org.skypro.skyshop.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String term) {
        List<SearchResult> list = new ArrayList<>();
        for (Searchable s : storageService.getAllSearchables()) {
            if (s.getSearchTerm().toLowerCase().contains(term.toLowerCase())) {
                SearchResult searchResult = SearchResult.fromSearchable(s);
                list.add(searchResult);
            }
        }
        return list;
    }

}
