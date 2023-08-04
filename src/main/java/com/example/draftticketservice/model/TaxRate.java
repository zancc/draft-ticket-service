package com.example.draftticketservice.model;

import com.example.draftticketservice.enums.TaxTypeEnum;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@EqualsAndHashCode
@Builder
public class TaxRate {
    private final TaxTypeEnum taxType;

    private final Integer rate;
}
