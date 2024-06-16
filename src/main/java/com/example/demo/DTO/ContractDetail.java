package com.example.demo.DTO;

public class ContractDetail {
    private String ContractName;
    private int ContractAmount;

    public String getContractName() {
        return ContractName;
    }

    public int getContractAmount() {
        return ContractAmount;
    }

    public void setContractName(String contractName) {
        this.ContractName = contractName;
    }

    public void setContractAmount(int contractAmount) {
        this.ContractAmount = contractAmount;
    }
}
