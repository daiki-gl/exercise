package com.example.demo.Entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "contacts_list")
public class ContractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String Name;
    @Column(name = "name_kana")
    private String NameKana;
    @Column(name = "birthday")
    private Date Birthday;
    @Column(name = "company")
    private String Company;
    @Column(name = "contract_num")
    private Integer ContractNum;
    @Column(name = "contract_name")
    private String ContractName;
    @Column(name = "contract_amount")
    private Integer ContractAmount;

    public Long getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getNameKana() {
        return NameKana;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public String getCompany() {
        return Company;
    }

    public Integer getContractNum() {
        return ContractNum;
    }

    public String getContractName() {
        return ContractName;
    }

    public Integer getContractAmount() {
        return ContractAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setNameKana(String NameKana) {
        this.NameKana = NameKana;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public void setCompany(String Company) {
        this.Company = Company;
    }

    public void setContractNum(Integer ContractNum) {
        this.ContractNum = ContractNum;
    }

    public void setContractName(String ContractName) {
        this.ContractName = ContractName;
    }

    public void setContractAmount(Integer ContractAmount) {
        this.ContractAmount = ContractAmount;
    }

}
