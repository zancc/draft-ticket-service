package com.example.draftticketservice.service;

import com.example.draftticketservice.enums.DestinationEnum;
import org.springframework.stereotype.Service;

@Service
public class BasePriceService {

    /**
     * Mock service method to provide base price for an adult
     */
    public Double getBasePriceForDestination(DestinationEnum destination) {
        return switch (destination) {
            case VILNIUS -> 10d;
            case TALLINN -> 8.8;
            default -> 0d;
        };
    }
}
