package com.example.demo.Controller;

import com.example.demo.DTO.ContractDetail;
import com.example.demo.DTO.CustomResponseDTO;
import com.example.demo.DTO.ReqInfo;
import com.example.demo.DTO.RequestCommon;
import com.example.demo.DTO.RequestIndividual;
import com.example.demo.DTO.ResInfo;
import com.example.demo.DTO.ResponseCommon;
import com.example.demo.DTO.ResponseIndividual;
import com.example.demo.DTO.SearchInfo;
import com.example.demo.DTO.SearchInfoDetailDTO;
import com.example.demo.DTO.SearchRequestDTO;
import com.example.demo.Service.ContractService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//対象クラスの指定
@WebMvcTest(ContractController.class)
public class ContractControllerTest {

        @Autowired
        private MockMvc mockMvc;

        // 依存するサービスのモック化
        @MockBean
        private ContractService contractService;

        @BeforeEach
        public void setUp() {
                MockitoAnnotations.openMocks(this);
        }

        @Autowired
        private ObjectMapper objectMapper;

        private SearchRequestDTO createSearchRequestDTO(String name, String nameKana, String birthday, String company,
                        String contractNum) {
                // ReqInfo
                ReqInfo reqInfo = new ReqInfo();
                reqInfo.setTime("2024-06-15T15:00:00");
                reqInfo.setTest("aaaaaaaaaaaaaaaaa");

                // ReqCommon
                RequestCommon requestCommon = new RequestCommon();
                requestCommon.setReqInfo(reqInfo);

                // SearchInfoDetail
                SearchInfoDetailDTO searchInfoDetail = new SearchInfoDetailDTO();
                searchInfoDetail.setName(name);
                searchInfoDetail.setNameKana(nameKana);
                searchInfoDetail.setBirthday(birthday);
                searchInfoDetail.setCompany(company);
                searchInfoDetail.setContractNum(Collections.singletonList(contractNum));

                // SearchInfo
                SearchInfo searchInfo = new SearchInfo();
                searchInfo.setSearchInfoDetail(searchInfoDetail);

                // RequestIndividual
                RequestIndividual requestIndividual = new RequestIndividual();
                requestIndividual.setSearchInfo(searchInfo);

                // SearchRequestDTO
                SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
                searchRequestDTO.setRequestCommon(requestCommon);
                searchRequestDTO.setRequestIndividual(requestIndividual);

                return searchRequestDTO;
        }

        @Test
        public void testSearchContracts() throws Exception {
                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "ヤマダ　タロウ", "1980-01-01",
                                "Company A", "123456");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // モックのレスポンスを設定
                CustomResponseDTO mockResponse = new CustomResponseDTO();
                ResponseIndividual responseIndividual = new ResponseIndividual();
                ContractDetail contractDetail = new ContractDetail();
                contractDetail.setContractName("Type A");
                contractDetail.setContractAmount(1000);
                responseIndividual.setContractList(Collections.singletonList(contractDetail));
                mockResponse.setResponseIndividual(responseIndividual);

                ResInfo resInfo = new ResInfo();
                resInfo.setResultFlag("0");
                resInfo.setErrors(Collections.emptyList());

                ResponseCommon responseCommon = new ResponseCommon();
                responseCommon.setResInfo(resInfo);
                mockResponse.setResponseCommon(responseCommon);

                // contractService.findContractsのモック設定
                when(contractService.findContracts(anyString(), any(), anyString(), anyString(), anyList()))
                                .thenReturn(mockResponse);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("contracts"))
                                .andExpect(model().attribute("contracts", Collections.singletonList(contractDetail)));
        }

        @Test
        public void testSearchContractsWithEmptyName() throws Exception {

                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("", "ヤマダ　タロウ", "1980-01-01",
                                "Company A", "123456");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("nameErrors"))
                                .andExpect(model().attribute("nameErrors", Collections.singletonList("名前を入力してください。")));
        }

        @Test
        public void testSearchContractsWithInvalidNameKana() throws Exception {

                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "やまだ　たろう", "1980-01-01",
                                "Company A", "123456");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("nameKanaErrors"))
                                .andExpect(model().attribute("nameKanaErrors",
                                                Collections.singletonList("名前(カナ)は全角カタカナで入力して下さい。")));
        }

        @Test
        public void testSearchContractsWithEmptyNameKana() throws Exception {

                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "", "1980-01-01",
                                "Company A", "123456");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("nameKanaErrors"))
                                .andExpect(model().attribute("nameKanaErrors",
                                                Matchers.containsInAnyOrder("名前(カナ)は全角カタカナで入力して下さい。",
                                                                "名前(カナ)を入力してください。")));
        }

        @Test
        public void testSearchContractsWithInvalidBirthday() throws Exception {

                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "ヤマダ タロウ", "invalid-date",
                                "Company A", "123456");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("birthdayErrors"))
                                .andExpect(model().attribute("birthdayErrors",
                                                Collections.singletonList("日付はyyyy/MM/ddの形式で入力して下さい。")));
        }

        @Test
        public void testSearchContractsWithEmptyCompany() throws Exception {

                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "ヤマダ タロウ", "1980-01-01",
                                "", "123456");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("companyErrors"))
                                .andExpect(model().attribute("companyErrors",
                                                Collections.singletonList("契約会社名を入力してください。")));
        }

        @Test
        public void testSearchContractsWithSingleDigitContractNum() throws Exception {

                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "ヤマダ タロウ", "1980-01-01",
                                "Company A", "1");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("contractNumErrors"))
                                .andExpect(
                                                model().attribute("contractNumErrors",
                                                                Collections.singletonList("契約番号は2桁以上10桁以内で入力して下さい。")));
        }

        @Test
        public void testSearchContractsWithOverTenDigitContractNum() throws Exception {
                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "ヤマダ タロウ", "1980-01-01",
                                "Company A", "01234567890");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("contractNumErrors"))
                                .andExpect(
                                                model().attribute("contractNumErrors",
                                                                Collections.singletonList("契約番号は2桁以上10桁以内で入力して下さい。")));
        }

        @Test
        public void testSearchContractsWithEmptyContractNum() throws Exception {
                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "ヤマダ タロウ", "1980-01-01",
                                "Company A", "");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("contractNumErrors"))
                                .andExpect(
                                                model().attribute("contractNumErrors",
                                                                Collections.singletonList("契約番号は2桁以上10桁以内で入力して下さい。")));
        }

        @Test
        public void testSearchContractsNoData() throws Exception {
                SearchRequestDTO searchRequestDTO = createSearchRequestDTO("山田　太郎", "ヤマダ タロウ", "1980-01-01",
                                "Company A", "123");

                // JSONにシリアライズ
                String jsonRequest = objectMapper.writeValueAsString(searchRequestDTO);

                // HTTP POSTリクエストを送信して、レスポンスコードを確認
                mockMvc.perform(post("/search")
                                .contentType("application/json")
                                .content(jsonRequest))
                                .andExpect(status().isOk())
                                .andExpect(view().name("contract-form"))
                                .andExpect(model().attributeExists("noData"))
                                .andExpect(model().attribute("noData", "E10003"));
        }
}