package com.example.draftticketservice.context;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@Builder
public class TicketCalculationData {
    private final PassengerCalculations passengerCalculations;

    private final LuggageCalculations luggageCalculations;

}
