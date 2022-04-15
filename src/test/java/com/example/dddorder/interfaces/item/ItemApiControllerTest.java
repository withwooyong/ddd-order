package com.example.dddorder.interfaces.item;

import com.example.dddorder.domain.partner.Partner;
import com.example.dddorder.infrastructure.partner.PartnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
class ItemApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    PartnerRepository partnerRepository;


    @BeforeEach
    void setUp() {
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
    void registerItem() throws Exception {
        String partnerToken = createPartner();

        ItemDto.RegisterItemRequest registerItemRequest = new ItemDto.RegisterItemRequest();
        registerItemRequest.setPartnerToken(partnerToken);
        registerItemRequest.setItemName("티셔츠");
        registerItemRequest.setItemPrice(30000L);

        List<ItemDto.RegisterItemOptionGroupRequest> registerItemOptionGroupRequestList = new ArrayList<>();
        ItemDto.RegisterItemOptionGroupRequest registerItemOptionGroupRequest = new ItemDto.RegisterItemOptionGroupRequest();

        registerItemOptionGroupRequest.setOrdering(1);
        registerItemOptionGroupRequest.setItemOptionGroupName("사이즈");

        List<ItemDto.RegisterItemOptionRequest> registerItemOptionRequestList = new ArrayList<>();
        ItemDto.RegisterItemOptionRequest registerItemOptionRequest = new ItemDto.RegisterItemOptionRequest();
        registerItemOptionRequest.setOrdering(1);
        registerItemOptionRequest.setItemOptionName("SMALL");
        registerItemOptionRequest.setItemOptionPrice(100L);
        registerItemOptionRequestList.add(registerItemOptionRequest);
        registerItemOptionRequest.setOrdering(2);
        registerItemOptionRequest.setItemOptionName("MEDIUM");
        registerItemOptionRequest.setItemOptionPrice(200L);
        registerItemOptionRequestList.add(registerItemOptionRequest);
        registerItemOptionRequest.setOrdering(2);
        registerItemOptionRequest.setItemOptionName("LARGE");
        registerItemOptionRequest.setItemOptionPrice(300L);
        registerItemOptionRequestList.add(registerItemOptionRequest);

        registerItemOptionGroupRequest.setItemOptionList(registerItemOptionRequestList);
        registerItemOptionGroupRequestList.add(registerItemOptionGroupRequest);

        registerItemOptionGroupRequest.setOrdering(2);
        registerItemOptionGroupRequest.setItemOptionGroupName("컬러");

        registerItemOptionRequest.setOrdering(1);
        registerItemOptionRequest.setItemOptionName("RED");
        registerItemOptionRequest.setItemOptionPrice(100L);
        registerItemOptionRequestList.add(registerItemOptionRequest);
        registerItemOptionRequest.setOrdering(2);
        registerItemOptionRequest.setItemOptionName("GOLD");
        registerItemOptionRequest.setItemOptionPrice(200L);
        registerItemOptionRequestList.add(registerItemOptionRequest);

        registerItemOptionGroupRequest.setItemOptionList(registerItemOptionRequestList);
        registerItemOptionGroupRequestList.add(registerItemOptionGroupRequest);

        registerItemRequest.setItemOptionGroupList(registerItemOptionGroupRequestList);

        String content = objectMapper.writeValueAsString(registerItemRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/items")
                .content(content).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void changeOnSaleItem() throws Exception {
        // POST http://localhost:8080/api/v1/items/change-on-sales
        // "itemToken": "itm_yEYbyzvZmwMmKpoJ"
        String itemToken = "";
        String content = objectMapper.writeValueAsString(itemToken);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/items/change-on-sales")
                .content(content).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void changeEndOfSaleItem() throws Exception {

    }

    @Test
    @DisplayName("아이템 조회")
    void retrieveItemInfo() throws Exception {
        // GET http://localhost:8080/api/v1/items/itm_m7N0Kn6iWarg6OFM
        String itemToken = "";
        String content = objectMapper.writeValueAsString(itemToken);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/items/")
                .content(content).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data.partnerName").value("test"))
//                .andExpect(jsonPath("$.data.businessNo").value("1111"))
//                .andExpect(jsonPath("$.data.email").value("test@email"))
                .andDo(print());
    }
}