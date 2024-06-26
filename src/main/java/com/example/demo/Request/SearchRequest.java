package com.example.demo.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SearchRequest {
    @NotBlank(message = "{E10000.name}")
    private String Name;

    @NotBlank(message = "{E10000.nameKana}")
    @Pattern(regexp = "\\A[ァ-ヴー　]+\\z", message = "{E10004}")
    private String NameKana;

    @NotBlank(message = "{E10000.dateOfBirth}")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "{E10001}")
    private String Birthday;

    @NotBlank(message = "{E10000.company}")
    private String Company;

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
    private String ContractNum;

    // Constructors

    public SearchRequest() {
    }

    public String getName() {
        return Name;
    }

    public String getNameKana() {
        return NameKana;
    }

    public String getBirthday() {
        return Birthday;
    }

    public String getCompany() {
        return Company;
    }

    public String getContractNum() {
        return ContractNum;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setNameKana(String nameKana) {
        this.NameKana = nameKana;
    }

    public void setBirthday(String dateOfBirth) {
        this.Birthday = dateOfBirth;
    }

    public void setCompany(String company) {
        this.Company = company;
    }

    public void setContractNum(String contractNum) {
        this.ContractNum = contractNum;
    }
}
