package com.example.demo.Entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "contracts_list")
public class ContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(name = "name_kana")
    private String nameKana;

    @Column(name = "birthday")
    private Date dateOfBirth;

    private String company;

    @Column(name = "contract_num")
    private Integer contractNum;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "contract_amount")
    private String contractAmount;

    // Constructors

    public ContractEntity() {
    }

    public ContractEntity(String contractName, String contractAmount) {
        this.contractName = contractName;
        this.contractAmount = contractAmount;
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameKana() {
        return nameKana;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCompany() {
        return company;
    }

    public Integer getContractNum() {
        return contractNum;
    }

    public String getContractName() {
        return contractName;
    }

    public String getContractAmount() {
        return contractAmount;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setContractNum(Integer contractNum) {
        this.contractNum = contractNum;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public void setContractAmount(String contractAmount) {
        this.contractAmount = contractAmount;
    }
}
