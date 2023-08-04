package com.example.draftticketservice.resource.model.response;

import com.example.draftticketservice.enums.AgeGroupEnum;
import lombok.Builder;

@Builder
public record TicketPrice(
        AgeGroupEnum ageGroup,
        Double passengerTicketPrice,
        Integer numberOfBags,

        Double luggagePrice) {

}
