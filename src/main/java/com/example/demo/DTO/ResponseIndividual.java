package com.example.demo.DTO;

import java.util.List;

public class ResponseIndividual {
    private List<ContractDetail> ContractDetail;

    // Getter and Setter
    public List<ContractDetail> getContractList() {
        return ContractDetail;
    }

    public void setContractList(List<ContractDetail> contractList) {
        this.ContractDetail = contractList;
    }

}
