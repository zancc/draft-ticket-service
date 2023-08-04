package com.example.draftticketservice.resource.model.response;

import lombok.Builder;

import java.util.List;

@Builder
public record DraftTicketResponse(
        List<TicketPrice> ticketPrices,
        Double totalPrice) {
}
