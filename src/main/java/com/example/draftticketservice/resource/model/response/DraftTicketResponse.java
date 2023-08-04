package com.example.draftticketservice.resource.model.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@EqualsAndHashCode
public class DraftTicketResponse {
    private final List<TicketPrice> ticketPrices;

    private final Double totalPrice;

    public static Builder builder() {
        return new Builder();
    }

    private DraftTicketResponse(Builder b) {
        ticketPrices = b.ticketPrices;
        totalPrice = b.totalPrice;
    }

    public static final class Builder {
        private List<TicketPrice> ticketPrices;
        private Double totalPrice;

        private Builder() {
        }

        public Builder ticketPrices(List<TicketPrice> ticketPrices) {
            this.ticketPrices = ticketPrices;
            return this;
        }

        public Builder totalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public DraftTicketResponse build() {
            return new DraftTicketResponse(this);
        }
    }
}
