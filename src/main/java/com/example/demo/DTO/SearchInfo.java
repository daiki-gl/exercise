package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class SearchInfo {
    @JsonProperty("SearchInfoDetail")
    @Valid
    private SearchInfoDetailDTO SearchInfoDetail;

    // Getters and setters
    public SearchInfoDetailDTO getSearchInfoDetail() {
        return SearchInfoDetail;
    }

    public void setSearchInfoDetail(SearchInfoDetailDTO searchInfoDetail) {
        this.SearchInfoDetail = searchInfoDetail;
    }
}
