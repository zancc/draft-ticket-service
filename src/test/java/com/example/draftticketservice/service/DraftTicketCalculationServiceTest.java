package com.example.draftticketservice.service;

import com.example.draftticketservice.context.DraftTicketContext;
import com.example.draftticketservice.context.LuggageCalculations;
import com.example.draftticketservice.context.PassengerCalculations;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.enums.AgeGroupEnum;
import com.example.draftticketservice.enums.DestinationEnum;
import com.example.draftticketservice.enums.TaxTypeEnum;
import com.example.draftticketservice.model.TaxRate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DraftTicketCalculationServiceTest {

    private static final double BASE_PRICE = 10d;
    private static final DestinationEnum DESTINATION_VILNIUS = DestinationEnum.VILNIUS;

    @Mock
    private TaxRateService taxRateService;
    @Mock
    private BasePriceService basePriceService;

    @InjectMocks
    DraftTicketCalculationService draftService;

    @ParameterizedTest(name = "Scenario {index}: {0}.")
    @MethodSource("getScenarioForResolveDraftPricesTest")
    void testResolveDraftPrices(String title,
                                Integer taxRate,
                                DraftTicketContext inputContext,
                                DraftTicketContext expectedContext) {
        // Mock
        when(taxRateService.getTaxRates()).thenReturn(getTaxRate(taxRate));
        when(basePriceService.getBasePriceForDestination(DestinationEnum.VILNIUS)).thenReturn(BASE_PRICE);

        // Perform
        DraftTicketContext result = draftService.resolveDraftPrices(inputContext);

        // Verify
        assertThat(result).isNotNull().isEqualTo(expectedContext);

    }

    private static Stream<Arguments> getScenarioForResolveDraftPricesTest() {
        return Stream.of(
                Arguments.of("Two passengers - adult with two bags, child with one bag",
                        21,
                        getContextForTwoPassengers(AgeGroupEnum.ADULT, 2, AgeGroupEnum.CHILD, 1),
                        updateContextForTwoPassengers(getContextForTwoPassengers(AgeGroupEnum.ADULT, 2, AgeGroupEnum.CHILD, 1), 0.21, 29.04, 12.10, 7.26, 6.05, 3.63)
                ),
                Arguments.of("Two passengers - child with no bag, adult with one bag, vat is null and defaulted to 21%",
                        null,
                        getContextForTwoPassengers(AgeGroupEnum.CHILD, 0, AgeGroupEnum.ADULT, 1),
                        updateContextForTwoPassengers(getContextForTwoPassengers(AgeGroupEnum.CHILD, 0, AgeGroupEnum.ADULT, 1), 0.21, 21.78, 6.05, 0, 12.10, 3.63)
                ),
                Arguments.of("One passenger - child with three bags, vat = 0",
                        0,
                        getContextForOnePassenger(AgeGroupEnum.CHILD, 3),
                        updateContextForOnePassenger(getContextForOnePassenger(AgeGroupEnum.CHILD, 3), 0d, 14d, 5d, 9d)
                )
        );
    }

    private static List<TaxRate> getTaxRate(Integer taxRate) {
        return List.of(
                TaxRate.builder()
                        .taxType(TaxTypeEnum.VAT)
                        .rate(taxRate)
                        .build(),
                TaxRate.builder()
                        .taxType(TaxTypeEnum.OTHER)
                        .rate(15)
                        .build());
    }

    private static DraftTicketContext getContextForTwoPassengers(AgeGroupEnum firstAgeGroup,
                                                                 int firstNumberOfBags,
                                                                 AgeGroupEnum secondAgeGroup,
                                                                 int secondNumberOfBags) {
        return DraftTicketContext.builder()
                .destination(DESTINATION_VILNIUS)
                .calculationDataList(List.of(
                                TicketCalculationData.builder()
                                        .passengerCalculations(PassengerCalculations.builder()
                                                .ageGroup(firstAgeGroup)
                                                .build())
                                        .luggageCalculations(LuggageCalculations.builder()
                                                .numberOfBags(firstNumberOfBags)
                                                .build())
                                        .build(),
                                TicketCalculationData.builder()
                                        .passengerCalculations(PassengerCalculations.builder()
                                                .ageGroup(secondAgeGroup)
                                                .build())
                                        .luggageCalculations(LuggageCalculations.builder()
                                                .numberOfBags(secondNumberOfBags)
                                                .build())
                                        .build()
                        )
                )
                .build();
    }

    private static DraftTicketContext getContextForOnePassenger(AgeGroupEnum ageGroup,
                                                                int numberOfBags) {
        return DraftTicketContext.builder()
                .destination(DESTINATION_VILNIUS)
                .calculationDataList(List.of(
                                TicketCalculationData.builder()
                                        .passengerCalculations(PassengerCalculations.builder()
                                                .ageGroup(ageGroup)
                                                .build())
                                        .luggageCalculations(LuggageCalculations.builder()
                                                .numberOfBags(numberOfBags)
                                                .build())
                                        .build()
                        )
                )
                .build();
    }

    private static DraftTicketContext updateContextForTwoPassengers(DraftTicketContext context,
                                                                    double vat,
                                                                    double totalPrice,
                                                                    double firstTicketPrice,
                                                                    double firstLuggagePrice,
                                                                    double secondTicketPrice,
                                                                    double secondLuggagePrice) {
        context.getCalculationDataList().get(0).getPassengerCalculations().setTicketPrice(firstTicketPrice);
        context.getCalculationDataList().get(1).getPassengerCalculations().setTicketPrice(secondTicketPrice);
        context.getCalculationDataList().get(0).getLuggageCalculations().setLuggagePrice(firstLuggagePrice);
        context.getCalculationDataList().get(1).getLuggageCalculations().setLuggagePrice(secondLuggagePrice);
        return context
                .setBasePrice(BASE_PRICE)
                .setVat(vat)
                .setTotalPrice(totalPrice);
    }

    private static DraftTicketContext updateContextForOnePassenger(DraftTicketContext context,
                                                                   double vat,
                                                                   double totalPrice,
                                                                   double ticketPrice,
                                                                   double luggagePrice) {
        context.getCalculationDataList().get(0).getPassengerCalculations().setTicketPrice(ticketPrice);
        context.getCalculationDataList().get(0).getLuggageCalculations().setLuggagePrice(luggagePrice);
        return context
                .setBasePrice(BASE_PRICE)
                .setVat(vat)
                .setTotalPrice(totalPrice);
    }
}
