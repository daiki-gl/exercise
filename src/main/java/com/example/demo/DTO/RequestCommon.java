package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;

public class RequestCommon {
    @JsonProperty("ReqInfo")
    @Valid
    private ReqInfo ReqInfo;

    // Getters and setters
    public ReqInfo getReqInfo() {
        return ReqInfo;
    }

    public void setReqInfo(ReqInfo reqInfo) {
        this.ReqInfo = reqInfo;
    }
}
