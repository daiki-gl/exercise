package com.example.demo.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SearchInfoDetailDTO {
    @JsonProperty("Name")
    @NotBlank(message = "{E10000.name}")
    private String Name;

    @JsonProperty("NameKana")
    @NotBlank(message = "{E10000.nameKana}")
    @Pattern(regexp = "\\A[ァ-ヴー 　]+\\z", message = "{E10004}")
    private String NameKana;

    @JsonProperty("Birthday")
    @NotBlank(message = "{E10000.dateOfBirth}")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "{E10001}")
    private String Birthday;

    @JsonProperty("Company")
    @NotBlank(message = "{E10000.company}")
    private String Company;

    @JsonProperty("ContractNum")
    @NotEmpty(message = "{E10000.contractNum}")
    @Size(min = 1, max = 10, message = "{E10002}")
    @Valid
    private List<@Pattern(regexp = "[0-9]{2,10}", message = "{E10002}") String> ContractNum;

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

    public List<String> getContractNum() {
        return ContractNum;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setNameKana(String nameKana) {
        this.NameKana = nameKana;
    }

    public void setBirthday(String birthday) {
        this.Birthday = birthday;
    }

    public void setCompany(String company) {
        this.Company = company;
    }

    public void setContractNum(List<String> contractNum) {
        this.ContractNum = contractNum;
    }
}