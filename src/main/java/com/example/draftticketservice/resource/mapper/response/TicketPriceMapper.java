package com.example.draftticketservice.resource.mapper.response;

import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.resource.model.response.TicketPrice;
import org.springframework.stereotype.Component;

@Component
public class TicketPriceMapper {

    public TicketPrice toResponse(TicketCalculationData ticketCalculationData) {
        if (ticketCalculationData == null) {
            return null;
        }

        var passengerCalculations = ticketCalculationData.getPassengerCalculations();
        var luggageCalculations = ticketCalculationData.getLuggageCalculations();

        return TicketPrice.builder()
                .ageGroup(passengerCalculations.getAgeGroup())
                .passengerTicketPrice(passengerCalculations.getTicketPrice())
                .numberOfBags(luggageCalculations.getNumberOfBags())
                .luggagePrice(luggageCalculations.getLuggagePrice())
                .build();
    }
}
