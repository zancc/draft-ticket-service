package com.example.draftticketservice.resource;

import com.example.draftticketservice.context.DraftTicketContext;
import com.example.draftticketservice.resource.mapper.context.DraftTicketContextMapper;
import com.example.draftticketservice.resource.mapper.response.DraftTicketResponseMapper;
import com.example.draftticketservice.resource.model.request.DraftTicketRequest;
import com.example.draftticketservice.resource.model.response.DraftTicketResponse;
import com.example.draftticketservice.service.DraftTicketCalculationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DraftTicketResource.class)
class DraftTicketResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DraftTicketContextMapper draftTicketContextMapper;

    @MockBean
    DraftTicketCalculationService draftTicketCalculationService;

    @MockBean
    DraftTicketResponseMapper draftTicketResponseMapper;

    @Autowired
    DraftTicketResource resource;

    @Test
    void testGetDraftTicketPricing() throws Exception {
        var request = DraftTicketRequest.builder().build();
        var context = DraftTicketContext.builder().build();
        var response = DraftTicketResponse.builder().build();
        String jsonContent = mapToJson(request);

        when(draftTicketContextMapper.toContext(request)).thenReturn(context);
        when(draftTicketCalculationService.resolveDraftPrices(context)).thenReturn(context);
        when(draftTicketResponseMapper.toResponse(context)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/draft")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDraftTicketPricingWithIncorrectUri() throws Exception {
        var request = DraftTicketRequest.builder().build();
        String jsonContent = mapToJson(request);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/draft")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonContent))
                .andExpect(status().isNotFound());
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
