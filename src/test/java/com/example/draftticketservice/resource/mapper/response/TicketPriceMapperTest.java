package com.example.draftticketservice.resource.mapper.response;

import com.example.draftticketservice.context.LuggageCalculations;
import com.example.draftticketservice.context.PassengerCalculations;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.enums.AgeGroupEnum;
import com.example.draftticketservice.resource.model.response.TicketPrice;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TicketPriceMapperTest {

    private static final AgeGroupEnum AGE_GROUP_ADULT = AgeGroupEnum.ADULT;
    private static final Double TICKET_PRICE = 30.3;
    private static final Integer BAG_COUNT = 1;
    private static final Double LUGGAGE_PRICE = 15.1;

    private final TicketPriceMapper mapper = new TicketPriceMapper();

    @Test
    void testMapToResponse() {
        // Prepare
        var ticketCalculationData = createTicketCalculationData();
        var expectedResult = createTicketPrice();

        // Perform
        var result = mapper.toResponse(ticketCalculationData);

        // Verify
        assertThat(result)
                .isNotNull()
                .isEqualTo(expectedResult);
    }

    @Test
    void testMapToResponseWhenNull() {
        TicketPrice result = mapper.toResponse(null);

        assertThat(result).isNull();
    }

    private static TicketCalculationData createTicketCalculationData() {
        return TicketCalculationData.builder()
                .passengerCalculations(PassengerCalculations.builder()
                        .ageGroup(AGE_GROUP_ADULT)
                        .ticketPrice(TICKET_PRICE)
                        .build())
                .luggageCalculations(LuggageCalculations.builder()
                        .numberOfBags(BAG_COUNT)
                        .luggagePrice(LUGGAGE_PRICE)
                        .build())
                .build();
    }

    private static TicketPrice createTicketPrice() {
        return TicketPrice.builder()
                .passengerTicketPrice(TICKET_PRICE)
                .ageGroup(AGE_GROUP_ADULT)
                .numberOfBags(BAG_COUNT)
                .luggagePrice(LUGGAGE_PRICE)
                .build();
    }
}
