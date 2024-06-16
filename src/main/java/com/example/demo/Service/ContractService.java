package com.example.demo.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ContractDetail;
import com.example.demo.DTO.CustomResponseDTO;
import com.example.demo.DTO.ResInfo;
import com.example.demo.DTO.ResponseCommon;
import com.example.demo.DTO.ResponseIndividual;
import com.example.demo.Entity.ContractEntity;
import com.example.demo.Repository.ContractRepository;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public CustomResponseDTO findContracts(String name, Date birthday, String nameKana, String company,
            List<String> contractNum) {

        List<ContractEntity> contracts = contractRepository.findContracts(name, birthday, nameKana, company,
                contractNum);

        ResponseCommon responseCommon = new ResponseCommon();
        ResInfo resInfo = new ResInfo();
        resInfo.setResultFlag("0");
        resInfo.setErrors(List.of());
        responseCommon.setResInfo(resInfo);

        ResponseIndividual responseIndividual = new ResponseIndividual();
        List<ContractDetail> contractLists = contracts.stream().map(contract -> {
            ContractDetail contractDetail = new ContractDetail();
            contractDetail.setContractName(contract.getContractName());
            contractDetail.setContractAmount(contract.getContractAmount());
            return contractDetail;
        })
                .collect(Collectors.toList());
        responseIndividual.setContractList(contractLists);

        CustomResponseDTO customResponseDTO = new CustomResponseDTO();
        customResponseDTO.setResponseCommon(responseCommon);
        customResponseDTO.setResponseIndividual(responseIndividual);

        return customResponseDTO;
    }
}
