package com.example.draftticketservice.resource.mapper.context;

import com.example.draftticketservice.context.LuggageCalculations;
import com.example.draftticketservice.context.PassengerCalculations;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.exception.MissingDataException;
import com.example.draftticketservice.resource.model.request.PassengerInformation;
import org.springframework.stereotype.Component;

@Component
public class TicketCalculationDataMapper {

    public TicketCalculationData toContext(PassengerInformation passengerInformation) {
        if (passengerInformation == null) {
            throw new MissingDataException("Missing passenger information");
        }

        return TicketCalculationData.builder()
                .passengerCalculations(
                        PassengerCalculations.builder()
                                .ageGroup(passengerInformation.getAgeGroup())
                                .build())
                .luggageCalculations(
                        LuggageCalculations.builder()
                                .numberOfBags(passengerInformation.getNumberOfBags())
                                .build())
                .build();
    }
}
