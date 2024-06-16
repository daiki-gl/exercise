package com.example.demo.DTO;

public class CustomResponseDTO {
    private ResponseCommon ResponseCommon;
    private ResponseIndividual ResponseIndividual;

    // Getter and Setter
    public ResponseCommon getResponseCommon() {
        return ResponseCommon;
    }

    public void setResponseCommon(ResponseCommon ResponseCommon) {
        this.ResponseCommon = ResponseCommon;
    }

    public ResponseIndividual getResponseIndividual() {
        return ResponseIndividual;
    }

    public void setResponseIndividual(ResponseIndividual ResponseIndividual) {
        this.ResponseIndividual = ResponseIndividual;
    }
}
