package com.example.draftticketservice.context;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
public class LuggageCalculations {
    private final Integer numberOfBags;

    private Double luggagePrice;

    public LuggageCalculations setLuggagePrice(Double luggagePrice) {
        this.luggagePrice = luggagePrice;
        return this;
    }

    private LuggageCalculations(Builder b) {
        numberOfBags = b.numberOfBags;
        luggagePrice = b.luggagePrice;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer numberOfBags;
        private Double luggagePrice;

        private Builder() {
        }

        public Builder numberOfBags(Integer numberOfBags) {
            this.numberOfBags = numberOfBags;
            return this;
        }

        public Builder luggagePrice(Double luggagePrice) {
            this.luggagePrice = luggagePrice;
            return this;
        }

        public LuggageCalculations build() {
            return new LuggageCalculations(this);
        }
    }
}
