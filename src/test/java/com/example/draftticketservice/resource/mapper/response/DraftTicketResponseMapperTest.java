package com.example.draftticketservice.resource.mapper.response;

import com.example.draftticketservice.context.DraftTicketContext;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.resource.model.response.DraftTicketResponse;
import com.example.draftticketservice.resource.model.response.TicketPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DraftTicketResponseMapperTest {

    private static final double TICKET_PRICE = 100.3;

    @Mock
    private TicketPriceMapper ticketPriceMapper;

    @InjectMocks
    private DraftTicketResponseMapper draftTicketResponseMapper;

    @Test
    void testMapToResponse() {
        // Prepare
        var draftTicketContext = createDraftTicketContext();
        var ticketPrice = TicketPrice.builder().build();
        var expectedResult = createDraftTicketResponse(ticketPrice);

        // Mock
        when(ticketPriceMapper.toResponse(draftTicketContext.getCalculationDataList().get(0))).thenReturn(ticketPrice);

        // Perform
        var result = draftTicketResponseMapper.toResponse(draftTicketContext);

        // Verify
        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    private DraftTicketResponse createDraftTicketResponse(TicketPrice ticketPrice) {
        return DraftTicketResponse.builder()
                .totalPrice(TICKET_PRICE)
                .ticketPrices(List.of(ticketPrice)).build();
    }

    private DraftTicketContext createDraftTicketContext() {
        return DraftTicketContext.builder()
                .totalPrice(TICKET_PRICE)
                .calculationDataList(List.of(TicketCalculationData.builder().build()))
                .build();
    }
}
