package com.example.demo.DTO;

public class ErrorDetail {
    private String Code;
    private String Message;

    public ErrorDetail(String code, String message) {
        this.Code = code;
        this.Message = message;
    }

    public String getCode() {
        return Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public void setMessage(String message) {
        this.Message = message;
    }
}
