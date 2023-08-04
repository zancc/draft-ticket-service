package com.example.draftticketservice.context;

import com.example.draftticketservice.enums.AgeGroupEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@Builder
public class PassengerCalculations {
    private final AgeGroupEnum ageGroup;
    private Double ticketPrice;

    public PassengerCalculations setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }
}
