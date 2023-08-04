package com.example.draftticketservice.context;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
public class TicketCalculationData {
    private final PassengerCalculations passengerCalculations;

    private final LuggageCalculations luggageCalculations;

    public static Builder builder() {
        return new Builder();
    }

    private TicketCalculationData(Builder b) {
        passengerCalculations = b.passengerCalculations;
        luggageCalculations = b.luggageCalculations;
    }

    public static final class Builder {
        private PassengerCalculations passengerCalculations;
        private LuggageCalculations luggageCalculations;

        private Builder() {
        }

        public Builder passengerCalculations(PassengerCalculations passengerCalculations) {
            this.passengerCalculations = passengerCalculations;
            return this;
        }

        public Builder luggageCalculations(LuggageCalculations luggageCalculations) {
            this.luggageCalculations = luggageCalculations;
            return this;
        }

        public TicketCalculationData build() {
            return new TicketCalculationData(this);
        }
    }
}
