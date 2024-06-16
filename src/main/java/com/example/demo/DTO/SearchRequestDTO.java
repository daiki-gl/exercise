package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class SearchRequestDTO {
    @JsonProperty("RequestCommon")
    @Valid
    private RequestCommon RequestCommon;
    @JsonProperty("RequestIndividual")
    @Valid
    private RequestIndividual RequestIndividual;

    // Getters and setters
    public RequestCommon getRequestCommon() {
        return RequestCommon;
    }

    public RequestIndividual getRequestIndividual() {
        return RequestIndividual;
    }

    public void setRequestCommon(RequestCommon requestCommon) {
        this.RequestCommon = requestCommon;
    }

    public void setRequestIndividual(RequestIndividual requestIndividual) {
        this.RequestIndividual = requestIndividual;
    }
}
