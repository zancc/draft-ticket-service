package com.example.draftticketservice.context;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@Builder
public class LuggageCalculations {
    private final Integer numberOfBags;

    private Double luggagePrice;

    public LuggageCalculations setLuggagePrice(Double luggagePrice) {
        this.luggagePrice = luggagePrice;
        return this;
    }
}
