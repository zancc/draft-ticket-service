package com.example.draftticketservice.resource.model.request;

import com.example.draftticketservice.enums.AgeGroupEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class PassengerInformation {
    private final AgeGroupEnum ageGroup;

    private final int numberOfBags;

    public static Builder builder() {
        return new Builder();
    }

    private PassengerInformation(Builder b) {
        ageGroup = b.ageGroup;
        numberOfBags = b.numberOfBags;
    }

    public static final class Builder {
        private AgeGroupEnum ageGroup;
        private int numberOfBags;

        private Builder() {
        }

        public Builder ageGroup(AgeGroupEnum ageGroup) {
            this.ageGroup = ageGroup;
            return this;
        }

        public Builder numberOfBags(int numberOfBags) {
            this.numberOfBags = numberOfBags;
            return this;
        }

        public PassengerInformation build() {
            return new PassengerInformation(this);
        }
    }
}
