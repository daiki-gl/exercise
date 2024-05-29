package com.example.demo.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.ContractEntity;
import com.example.demo.Request.SearchRequest;
import com.example.demo.Service.ContractService;

@Controller
public class ContractController {
    @Autowired
    private ContractService contractService;

    @RequestMapping("/AN1120")
    public String showForm(Model model) {
        model.addAttribute("searchRequest", new SearchRequest());
        return "contract-form";
    }

    @PostMapping("/search")
    public String searchContract(@ModelAttribute SearchRequest request, Model model) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = sdf.parse(request.getDateOfBirth());
        ContractEntity contractResponse = contractService.getContractType(request.getName(), dateOfBirth);
        model.addAttribute("contractType", contractResponse.getContractName());
        model.addAttribute("contractAmount", contractResponse.getContractAmount());
        return "contract-result";
    }

}
