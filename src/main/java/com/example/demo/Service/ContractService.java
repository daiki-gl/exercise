package com.example.demo.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContractEntity;
import com.example.demo.Repository.ContractRepository;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public ContractEntity getContractType(String name, Date dateOfBirth) {
        ContractEntity contract = contractRepository.findByNameAndDateOfBirth(name, dateOfBirth);
        if (contract != null) {
            return new ContractEntity(contract.getContractName(), contract.getContractAmount());
        } else {
            return null;
        }
    }
}
