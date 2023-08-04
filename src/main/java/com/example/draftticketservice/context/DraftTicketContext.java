package com.example.draftticketservice.context;

import com.example.draftticketservice.enums.DestinationEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@EqualsAndHashCode
public class DraftTicketContext {
    private final DestinationEnum destination;
    private final List<TicketCalculationData> calculationDataList;
    private Double totalPrice;
    private Double vat;
    private Double basePrice;

    public DraftTicketContext setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public DraftTicketContext setVat(Double vat) {
        this.vat = vat;
        return this;
    }

    public DraftTicketContext setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    private DraftTicketContext(Builder b) {
        destination = b.destination;
        calculationDataList = b.calculationDataList;
        totalPrice = b.totalPrice;
        vat = b.vat;
        basePrice = b.basePrice;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private DestinationEnum destination;
        private List<TicketCalculationData> calculationDataList = new ArrayList<>();
        private Double totalPrice;
        private Double vat;
        private Double basePrice;

        private Builder() {
        }

        public Builder destination(DestinationEnum destination) {
            this.destination = destination;
            return this;
        }

        public Builder calculationDataList(List<TicketCalculationData> calculationDataList) {
            this.calculationDataList = calculationDataList;
            return this;
        }

        public Builder totalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder vat(Double vat) {
            this.vat = vat;
            return this;
        }

        public Builder basePrice(Double basePrice) {
            this.basePrice = basePrice;
            return this;
        }

        public DraftTicketContext build() {
            return new DraftTicketContext(this);
        }
    }
}
