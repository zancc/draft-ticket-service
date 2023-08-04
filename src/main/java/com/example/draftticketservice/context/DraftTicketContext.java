package com.example.draftticketservice.context;

import com.example.draftticketservice.enums.DestinationEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@EqualsAndHashCode
@Builder
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
}
