package com.example.draftticketservice.enums;

public enum TaxTypeEnum {
    VAT(21),
    OTHER(15);

    public final Integer value;

    TaxTypeEnum(Integer value) {
        this.value = value;
    }
}
