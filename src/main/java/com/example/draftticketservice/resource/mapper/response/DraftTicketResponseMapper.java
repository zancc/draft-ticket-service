package com.example.draftticketservice.resource.mapper.response;

import com.example.draftticketservice.context.DraftTicketContext;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.resource.model.response.DraftTicketResponse;
import com.example.draftticketservice.resource.model.response.TicketPrice;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
@Validated
public class DraftTicketResponseMapper {

    private final TicketPriceMapper ticketPriceMapper;

    public DraftTicketResponseMapper(TicketPriceMapper ticketPriceMapper) {
        this.ticketPriceMapper = ticketPriceMapper;
    }

    public DraftTicketResponse toResponse(DraftTicketContext context) {
        return DraftTicketResponse.builder()
                .ticketPrices(getTicketPrices(context.getCalculationDataList()))
                .totalPrice(context.getTotalPrice())
                .build();
    }

    private List<TicketPrice> getTicketPrices(List<TicketCalculationData> ticketCalculationData) {
        return ticketCalculationData.stream()
                .map(ticketPriceMapper::toResponse)
                .toList();
    }
}
