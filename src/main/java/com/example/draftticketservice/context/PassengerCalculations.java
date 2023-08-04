package com.example.draftticketservice.context;

import com.example.draftticketservice.enums.AgeGroupEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
public class PassengerCalculations {
    private final AgeGroupEnum ageGroup;
    private Double ticketPrice;

    public PassengerCalculations setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public static Builder builder() {
        return new Builder();
    }

    private PassengerCalculations(Builder b) {
        ageGroup = b.ageGroup;
        ticketPrice = b.ticketPrice;
    }

    public static final class Builder {
        private AgeGroupEnum ageGroup;
        private Double ticketPrice;

        private Builder() {
        }

        public Builder ageGroup(AgeGroupEnum ageGroup) {
            this.ageGroup = ageGroup;
            return this;
        }

        public Builder ticketPrice(Double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public PassengerCalculations build() {
            return new PassengerCalculations(this);
        }
    }
}
