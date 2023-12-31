package com.example.draftticketservice.service;

import com.example.draftticketservice.enums.TaxTypeEnum;
import com.example.draftticketservice.model.TaxRate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxRateService {

    /**
     * Mock service method to provide tax rates
     */
    public List<TaxRate> getTaxRates() {
        return List.of(
                TaxRate.builder()
                        .taxType(TaxTypeEnum.VAT)
                        .rate(TaxTypeEnum.VAT.value)
                        .build(),
                TaxRate.builder()
                        .taxType(TaxTypeEnum.OTHER)
                        .rate(TaxTypeEnum.OTHER.value)
                        .build());
    }
}
