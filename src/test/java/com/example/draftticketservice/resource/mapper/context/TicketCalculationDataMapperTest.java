package com.example.draftticketservice.resource.mapper.context;

import com.example.draftticketservice.context.LuggageCalculations;
import com.example.draftticketservice.context.PassengerCalculations;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.enums.AgeGroupEnum;
import com.example.draftticketservice.exception.MissingDataException;
import com.example.draftticketservice.resource.model.request.PassengerInformation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketCalculationDataMapperTest {

    private final TicketCalculationDataMapper mapper = new TicketCalculationDataMapper();

    @Test
    void testMapToContext() {
        // Prepare
        var passengerInformation = createPassengerInformation();
        var expectedResult = createTicketCalculationData();

        // Perform
        var result = mapper.toContext(passengerInformation);

        // Verify
        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    @Test
    void testMapToContextWhenNull_ThrowsException() {
        assertThrows(MissingDataException.class, () -> mapper.toContext(null));
    }

    private TicketCalculationData createTicketCalculationData() {
        return TicketCalculationData.builder()
                .passengerCalculations(PassengerCalculations.builder()
                        .ageGroup(AgeGroupEnum.ADULT)
                        .build())
                .luggageCalculations(LuggageCalculations.builder()
                        .numberOfBags(2)
                        .build())
                .build();
    }

    private PassengerInformation createPassengerInformation() {
        return PassengerInformation.builder()
                .ageGroup(AgeGroupEnum.ADULT)
                .numberOfBags(2)
                .build();
    }
}
