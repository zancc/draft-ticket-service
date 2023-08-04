package com.example.draftticketservice.resource.model.request;

import com.example.draftticketservice.enums.AgeGroupEnum;
import lombok.Builder;

@Builder
public record PassengerInformation(
        AgeGroupEnum ageGroup,
        int numberOfBags) {
}
