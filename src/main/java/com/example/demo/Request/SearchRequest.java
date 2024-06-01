package com.example.demo.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SearchRequest {
    @NotBlank(message = "{E10000.name}")
    private String name;

    @NotBlank(message = "{E10000.nameKana}")
    @Pattern(regexp = "\\A[ァ-ヴー　]+\\z", message = "{E10004}")
    private String nameKana;

    @NotBlank(message = "{E10000.dateOfBirth}")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "{E10001}")
    private String dateOfBirth;

    @NotBlank(message = "{E10000.company}")
    private String company;

    /**
     *
     * 下記はIntegerに使用するもの
     * フォームからの数値が文字列としてきてエラーになる
     * なので一度StringとしてcontractNumを扱ってみる
     * 
     * @Digits(integer = 10, fraction = 0, message = "{E10002}")
     * @Size(min = 2, max = 10, message = "{E10002}")
     */

    @NotBlank(message = "{E10000.contractNum}")
    @Pattern(regexp = "[0-9]{2,10}", message = "{E10002}")
    private String contractNum;

    // Constructors

    public SearchRequest() {
    }

    public String getName() {
        return name;
    }

    public String getNameKana() {
        return nameKana;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCompany() {
        return company;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }
}
