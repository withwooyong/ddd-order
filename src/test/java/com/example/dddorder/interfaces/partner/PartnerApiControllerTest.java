package com.example.dddorder.interfaces.partner;

import com.example.dddorder.domain.partner.Partner;
import com.example.dddorder.infrastructure.partner.PartnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
class PartnerApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    PartnerRepository partnerRepository;

    @BeforeAll
    static void beforeAll() {
    }

    public String createPartner() {
        Partner partner = Partner.builder()
                .partnerName("test")
                .email("test@email")
                .businessNo("1111")
                .build();
        Partner result = partnerRepository.save(partner);
        System.out.println(result.getPartnerToken());
        return result.getPartnerToken();
    }

    @Test
        //@DisplayName("등록")
    void registerPartner() throws Exception {
        String content = objectMapper.writeValueAsString(
                new PartnerDto.RegisterRequest("test1", "1111", "test1@mail"));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/partners")
                .content(content).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getPartnerInfo() throws Exception {
        String partnerToken = createPartner();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/partners/getPartnerInfo").param("partnerToken", partnerToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.partnerName").value("test"))
                .andExpect(jsonPath("$.data.businessNo").value("1111"))
                .andExpect(jsonPath("$.data.email").value("test@email"))
                .andDo(print());
    }

    @Test
    @DisplayName("활성화")
    void enablePartner() {
    }

    @Test
    @DisplayName("비활성화")
    void disablePartner() {
    }


}