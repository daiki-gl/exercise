package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class RequestIndividual {
    @JsonProperty("SearchInfo")
    @Valid
    private SearchInfo SearchInfo;

    // Getters and setters
    public SearchInfo getSearchInfo() {
        return SearchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
        this.SearchInfo = searchInfo;
    }
}
