package com.example.demo.Request;

public class SearchRequest {
    private String name;
    private String nameKana;
    private String dateOfBirth;
    private String company;
    private Integer contractNum;

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

    public Integer getContractNum() {
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

    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }
}
