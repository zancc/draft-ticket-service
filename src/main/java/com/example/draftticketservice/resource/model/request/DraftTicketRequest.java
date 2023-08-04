package com.example.draftticketservice.resource.model.request;

import com.example.draftticketservice.enums.DestinationEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class DraftTicketRequest {

    private final DestinationEnum destination;
    private final List<PassengerInformation> passengers;

    public static Builder builder() {
        return new Builder();
    }

    private DraftTicketRequest(Builder b) {
        destination = b.destination;
        passengers = b.passengers;
    }

    public static final class Builder {
        private DestinationEnum destination;
        private List<PassengerInformation> passengers;

        private Builder() {
        }

        public Builder destination(DestinationEnum destination) {
            this.destination = destination;
            return this;
        }

        public Builder passengers(List<PassengerInformation> passengers) {
            this.passengers = passengers;
            return this;
        }

        public DraftTicketRequest build() {
            return new DraftTicketRequest(this);
        }
    }
}
