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

    @Column(name = "birthday")
    private Date dateOfBirth;

    private String company;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "contract_amount")
    private String contractAmount;

    // Constructors

    public ContractEntity() {
    }

    // public ContractEntity(String name, Date dateOfBirth, String company) {
    // this.name = name;
    // this.dateOfBirth = dateOfBirth;
    // this.company = company;
    // }
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCompany() {
        return company;
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

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public void setContractAmount(String contractAmount) {
        this.contractAmount = contractAmount;
    }
}
