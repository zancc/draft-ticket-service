package com.example.draftticketservice.resource.model.request;

import com.example.draftticketservice.enums.DestinationEnum;
import lombok.Builder;

import java.util.List;

@Builder
public record DraftTicketRequest(
        DestinationEnum destination,
        List<PassengerInformation> passengers) {

}
