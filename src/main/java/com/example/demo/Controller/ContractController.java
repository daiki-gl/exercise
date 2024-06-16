package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ContractDetail;
import com.example.demo.DTO.CustomResponseDTO;
import com.example.demo.DTO.SearchInfoDetailDTO;
import com.example.demo.DTO.SearchRequestDTO;
import com.example.demo.Service.ContractService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ContractController {
    private static final Logger logger = LoggerFactory.getLogger(ContractController.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ContractService contractService;

    @ModelAttribute("searchRequest")
    public SearchRequestDTO setUpForm() {
        return new SearchRequestDTO();
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/AN1120")
    public String showForm(Model model) {
        model.addAttribute("searchRequest", new SearchRequestDTO());
        return "contract-form";
    }

    @PostMapping("/search")
    public String searchContracts(@RequestBody @Validated SearchRequestDTO searchRequestDTO,
            BindingResult bindingResult,
            Model model) {

        Map<String, Object> responseShow = new HashMap<>();

        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errorMap = new HashMap<>();
            List<Map<String, String>> errorDetails = new ArrayList<>();

            for (FieldError error : bindingResult.getFieldErrors()) {

                String fieldName = error.getField();
                String errorMessage = error.getDefaultMessage();

                Map<String, String> errorDetail = new HashMap<>();
                errorDetail.put("Code", error.getCode());
                errorDetail.put("Message", errorMessage);
                errorDetails.add(errorDetail);

                if (fieldName.contains("NameKana")) {
                    errorMap.computeIfAbsent("nameKanaErrors", k -> new ArrayList<>()).add(errorMessage);
                } else if (fieldName.contains("Name")) {
                    errorMap.computeIfAbsent("nameErrors", k -> new ArrayList<>()).add(errorMessage);
                } else if (fieldName.contains("ContractNum")) {
                    errorMap.computeIfAbsent("contractNumErrors", k -> new ArrayList<>()).add(errorMessage);
                } else if (fieldName.contains("Birthday")) {
                    errorMap.computeIfAbsent("birthdayErrors", k -> new ArrayList<>()).add(errorMessage);
                } else {
                    errorMap.computeIfAbsent("companyErrors", k -> new ArrayList<>()).add(errorMessage);
                }
            }
            model.addAllAttributes(errorMap);

            responseShow.put("ResponseIndividual", Map.of("contractList", List.of()));
            responseShow.put("ResponseCommon", Map.of(
                    "ResInfo", Map.of(
                            "ResultFlag", bindingResult.getErrorCount(),
                            "Errors", errorDetails)));
            logResponse(responseShow);
            return "contract-form";
        }

        try {
            String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);
            System.out.println("==================REQUEST====================");
            logger.info("Received search request: {}", jsonRequest);
            System.out.println("=============================================");
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert search request to JSON", e);
        }

        SearchInfoDetailDTO searchInfoDetail = searchRequestDTO.getRequestIndividual().getSearchInfo()
                .getSearchInfoDetail();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateOfBirth = sdf.parse(searchInfoDetail.getBirthday());

            CustomResponseDTO response = contractService.findContracts(
                    searchInfoDetail.getName(),
                    dateOfBirth,
                    searchInfoDetail.getNameKana(),
                    searchInfoDetail.getCompany(),
                    searchInfoDetail.getContractNum());

            if (response == null || response.getResponseIndividual().getContractList().size() == 0) {
                model.addAttribute("noData", "E10003");
            } else {
                List<ContractDetail> contracts = response.getResponseIndividual().getContractList();
                model.addAttribute("contracts", contracts);
            }

            try {
                String jsonResponse = objectMapper.writeValueAsString(response);
                System.out.println("***********************RESPONSE***********************");
                logger.info("Returning contracts: {}", jsonResponse);
                System.out.println("******************************************************");
            } catch (JsonProcessingException e) {
                logger.error("Failed to convert search request to JSON", e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return "contract-form";
    }

    private void logResponse(Map<String, Object> response) {
        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            System.err.println("***********************RESPONSE SHOW***********************");
            logger.info("Response show: {}", jsonResponse);
            System.err.println("***********************************************************");
        } catch (JsonProcessingException e) {
            logger.error("Failed to convert response to JSON", e);
        }
    }
}
