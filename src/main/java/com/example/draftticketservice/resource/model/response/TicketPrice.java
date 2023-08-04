package com.example.draftticketservice.resource.model.response;

import com.example.draftticketservice.enums.AgeGroupEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
public class TicketPrice {

    private final AgeGroupEnum ageGroup;
    private final Double passengerTicketPrice;
    private final Integer numberOfBags;
    private final Double luggagePrice;

    public static Builder builder() {
        return new Builder();
    }

    private TicketPrice(Builder b) {
        ageGroup = b.ageGroup;
        passengerTicketPrice = b.passengerTicketPrice;
        numberOfBags = b.numberOfBags;
        luggagePrice = b.luggagePrice;
    }

    public static final class Builder {
        private AgeGroupEnum ageGroup;
        private Double passengerTicketPrice;
        private Integer numberOfBags;

        private Double luggagePrice;

        private Builder() {
        }

        public Builder ageGroup(AgeGroupEnum ageGroup) {
            this.ageGroup = ageGroup;
            return this;
        }

        public Builder passengerTicketPrice(Double passengerTicketPrice) {
            this.passengerTicketPrice = passengerTicketPrice;
            return this;
        }

        public Builder numberOfBags(Integer numberOfBags) {
            this.numberOfBags = numberOfBags;
            return this;
        }

        public Builder luggagePrice(Double luggagePrice) {
            this.luggagePrice = luggagePrice;
            return this;
        }

        public TicketPrice build() {
            return new TicketPrice(this);
        }
    }
}
