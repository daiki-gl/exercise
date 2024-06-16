package com.example.demo.DTO;

import java.util.List;

public class ResInfo {
    private String ResultFlag;
    private List<ErrorDetail> Errors;

    // Getter and Setter
    public String getResultFlag() {
        return ResultFlag;
    }

    public void setResultFlag(String ResultFlag) {
        this.ResultFlag = ResultFlag;
    }

    public List<ErrorDetail> getErrors() {
        return Errors;
    }

    public void setErrors(List<ErrorDetail> Errors) {
        this.Errors = Errors;
    }
}
